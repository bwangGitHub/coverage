/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as publihed by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * --------------
 * ValueAxis.java
 * --------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Jonathan Nash;
 *                   Nicolas Brodu (for Astrium and EADS Corporate Research 
 *                   Center);
 *
 * Changes
 * -------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 23-Nov-2001 : Overhauled standard tick unit code (DG);
 * 04-Dec-2001 : Changed constructors to protected, and tidied up default 
 *               values (DG);
 * 12-Dec-2001 : Fixed vertical gridlines bug (DG);
 * 16-Jan-2002 : Added an optional crosshair, based on the implementation by 
 *               Jonathan Nash (DG);
 * 23-Jan-2002 : Moved the minimum and maximum values to here from NumberAxis, 
 *               and changed the type from Number to double (DG);
 * 25-Feb-2002 : Added default value for autoRange. Changed autoAdjustRange 
 *               from public to protected. Updated import statements (DG);
 * 23-Apr-2002 : Added setRange() method (DG);
 * 29-Apr-2002 : Added range adjustment methods (DG);
 * 13-Jun-2002 : Modified setCrosshairValue() to notify listeners only when the
 *               crosshairs are visible, to avoid unnecessary repaints, as 
 *               suggested by Kees Kuip (DG);
 * 25-Jul-2002 : Moved lower and upper margin attributes from the NumberAxis 
 *               class (DG);
 * 05-Sep-2002 : Updated constructor for changes in Axis class (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 04-Oct-2002 : Moved standardTickUnits from NumberAxis --> ValueAxis (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 19-Nov-2002 : Removed grid settings (now controlled by the plot) (DG);
 * 27-Nov-2002 : Moved the 'inverted' attributed from NumberAxis to 
 *               ValueAxis (DG);
 * 03-Jan-2003 : Small fix to ensure auto-range minimum is observed 
 *               immediately (DG);
 * 14-Jan-2003 : Changed autoRangeMinimumSize from Number --> double (DG);
 * 20-Jan-2003 : Replaced monolithic constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 09-May-2003 : Added AxisLocation parameter to translation methods (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 01-Sep-2003 : Fixed bug 793167 (setMaximumAxisValue exception) (DG);
 * 02-Sep-2003 : Fixed bug 795366 (zooming on inverted axes) (DG);
 * 08-Sep-2003 : Completed Serialization support (NB);
 * 08-Sep-2003 : Renamed get/setMinimumValue --> get/setLowerBound,
 *               and get/setMaximumValue --> get/setUpperBound (DG);
 * 27-Oct-2003 : Changed DEFAULT_AUTO_RANGE_MINIMUM_SIZE value - see bug ID 
 *               829606 (DG);
 * 07-Nov-2003 : Changes to tick mechanism (DG);
 * 06-Jan-2004 : Moved axis line attributes to Axis class (DG);
 * 21-Jan-2004 : Removed redundant axisLineVisible attribute.  Renamed 
 *               translateJava2DToValue --> java2DToValue, and 
 *               translateValueToJava2D --> valueToJava2D (DG); 
 * 23-Jan-2004 : Fixed setAxisLinePaint() and setAxisLineStroke() which had no 
 *               effect (andreas.gawecki@coremedia.com);
 * 07-Apr-2004 : Changed text bounds calculation (DG);
 * 26-Apr-2004 : Added getter/setter methods for arrow shapes (DG);
 * 18-May-2004 : Added methods to set axis range *including* current 
 *               margins (DG);
 * 02-Jun-2004 : Fixed bug in setRangeWithMargins() method (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities 
 *               --> TextUtilities (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0 
 *               release (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 10-Oct-2006 : Source reformatting (DG);
 * 22-Mar-2007 : Added new defaultAutoRange attribute (DG);
 * 02-Aug-2007 : Check for major tick when drawing label (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * The base class for axes that display value data, where values are measured 
 * using the <code>double</code> primitive.  The two key subclasses are 
 * {@link DateAxis} and {@link NumberAxis}.
 */
public abstract class ValueAxis extends Axis 
                                implements Cloneable, PublicCloneable, 
                                           Serializable {
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3698345477322391456L;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[1]++;
  }
    
    /** The default axis range. */
    public static final Range DEFAULT_RANGE = new Range(0.0, 1.0);
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[2]++;
  }

    /** The default auto-range value. */
    public static final boolean DEFAULT_AUTO_RANGE = true;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[3]++;
  }

    /** The default inverted flag setting. */
    public static final boolean DEFAULT_INVERTED = false;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[4]++;
  }

    /** The default minimum auto range. */
    public static final double DEFAULT_AUTO_RANGE_MINIMUM_SIZE = 0.00000001;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[5]++;
  }

    /** The default value for the lower margin (0.05 = 5%). */
    public static final double DEFAULT_LOWER_MARGIN = 0.05;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[6]++;
  }

    /** The default value for the upper margin (0.05 = 5%). */
    public static final double DEFAULT_UPPER_MARGIN = 0.05;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[7]++;
  }

    /** 
     * The default lower bound for the axis.
     * 
     * @deprecated From 1.0.5 onwards, the axis defines a defaultRange 
     *     attribute (see {@link #getDefaultAutoRange()}).
     */
    public static final double DEFAULT_LOWER_BOUND = 0.0;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[8]++;
  }

    /** 
     * The default upper bound for the axis. 
     * 
     * @deprecated From 1.0.5 onwards, the axis defines a defaultRange 
     *     attribute (see {@link #getDefaultAutoRange()}).
     */
    public static final double DEFAULT_UPPER_BOUND = 1.0;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[9]++;
  }

    /** The default auto-tick-unit-selection value. */
    public static final boolean DEFAULT_AUTO_TICK_UNIT_SELECTION = true;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[10]++;
  }

    /** The maximum tick count. */
    public static final int MAXIMUM_TICK_COUNT = 500;
  static {
    CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[11]++;
  }
    
    /** 
     * A flag that controls whether an arrow is drawn at the positive end of 
     * the axis line. 
     */
    private boolean positiveArrowVisible;
    
    /** 
     * A flag that controls whether an arrow is drawn at the negative end of 
     * the axis line. 
     */
    private boolean negativeArrowVisible;
    
    /** The shape used for an up arrow. */
    private transient Shape upArrow;
    
    /** The shape used for a down arrow. */
    private transient Shape downArrow;
    
    /** The shape used for a left arrow. */
    private transient Shape leftArrow;
    
    /** The shape used for a right arrow. */
    private transient Shape rightArrow;
    
    /** A flag that affects the orientation of the values on the axis. */
    private boolean inverted;

    /** The axis range. */
    private Range range;

    /** 
     * Flag that indicates whether the axis automatically scales to fit the 
     * chart data. 
     */
    private boolean autoRange;

    /** The minimum size for the 'auto' axis range (excluding margins). */
    private double autoRangeMinimumSize;

    /**
     * The default range is used when the dataset is empty and the axis needs
     * to determine the auto range.
     * 
     * @since 1.0.5
     */
    private Range defaultAutoRange;
    
    /**
     * The upper margin percentage.  This indicates the amount by which the 
     * maximum axis value exceeds the maximum data value (as a percentage of 
     * the range on the axis) when the axis range is determined automatically.
     */
    private double upperMargin;

    /**
     * The lower margin.  This is a percentage that indicates the amount by
     * which the minimum axis value is "less than" the minimum data value when
     * the axis range is determined automatically.
     */
    private double lowerMargin;

    /**
     * If this value is positive, the amount is subtracted from the maximum
     * data value to determine the lower axis range.  This can be used to
     * provide a fixed "window" on dynamic data.
     */
    private double fixedAutoRange;

    /** 
     * Flag that indicates whether or not the tick unit is selected 
     * automatically. 
     */
    private boolean autoTickUnitSelection;

    /** The standard tick units for the axis. */
    private TickUnitSource standardTickUnits;

    /** An index into an array of standard tick values. */
    private int autoTickIndex;
    
    /** A flag indicating whether or not tick labels are rotated to vertical. */
    private boolean verticalTickLabels;

    /**
     * Constructs a value axis.
     *
     * @param label  the axis label (<code>null</code> permitted).
     * @param standardTickUnits  the source for standard tick units 
     *                           (<code>null</code> permitted).
     */
    protected ValueAxis(String label, TickUnitSource standardTickUnits) {

        super(label);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[12]++;

        this.positiveArrowVisible = false;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[13]++;
        this.negativeArrowVisible = false;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[14]++;

        this.range = DEFAULT_RANGE;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[15]++;
        this.autoRange = DEFAULT_AUTO_RANGE;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[16]++;
        this.defaultAutoRange = DEFAULT_RANGE;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[17]++;

        this.inverted = DEFAULT_INVERTED;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[18]++;
        this.autoRangeMinimumSize = DEFAULT_AUTO_RANGE_MINIMUM_SIZE;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[19]++;

        this.lowerMargin = DEFAULT_LOWER_MARGIN;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[20]++;
        this.upperMargin = DEFAULT_UPPER_MARGIN;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[21]++;

        this.fixedAutoRange = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[22]++;

        this.autoTickUnitSelection = DEFAULT_AUTO_TICK_UNIT_SELECTION;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[23]++;
        this.standardTickUnits = standardTickUnits;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[24]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[25]++;
        
        Polygon p1 = new Polygon();
        p1.addPoint(0, 0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[26]++;
        p1.addPoint(-2, 2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[27]++;
        p1.addPoint(2, 2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[28]++;
        
        this.upArrow = p1;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[29]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[30]++;

        Polygon p2 = new Polygon();
        p2.addPoint(0, 0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[31]++;
        p2.addPoint(-2, -2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[32]++;
        p2.addPoint(2, -2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[33]++;

        this.downArrow = p2;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[34]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[35]++;

        Polygon p3 = new Polygon();
        p3.addPoint(0, 0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[36]++;
        p3.addPoint(-2, -2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[37]++;
        p3.addPoint(-2, 2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[38]++;
        
        this.rightArrow = p3;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[39]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[40]++;

        Polygon p4 = new Polygon();
        p4.addPoint(0, 0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[41]++;
        p4.addPoint(2, -2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[42]++;
        p4.addPoint(2, 2);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[43]++;

        this.leftArrow = p4;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[44]++;
        
        this.verticalTickLabels = false;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[45]++;
        
    }

    /**
     * Returns <code>true</code> if the tick labels should be rotated (to 
     * vertical), and <code>false</code> otherwise.
     *
     * @return <code>true</code> or <code>false</code>.
     * 
     * @see #setVerticalTickLabels(boolean)
     */
    public boolean isVerticalTickLabels() {
        return this.verticalTickLabels;
    }

    /**
     * Sets the flag that controls whether the tick labels are displayed 
     * vertically (that is, rotated 90 degrees from horizontal).  If the flag 
     * is changed, an {@link AxisChangeEvent} is sent to all registered 
     * listeners.
     *
     * @param flag  the flag.
     * 
     * @see #isVerticalTickLabels()
     */
    public void setVerticalTickLabels(boolean flag) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[46]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.verticalTickLabels != flag) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[1]++;
            this.verticalTickLabels = flag;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[47]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[48]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[2]++;}
    }

    /**
     * Returns a flag that controls whether or not the axis line has an arrow 
     * drawn that points in the positive direction for the axis.
     * 
     * @return A boolean.
     * 
     * @see #setPositiveArrowVisible(boolean)
     */
    public boolean isPositiveArrowVisible() {
        return this.positiveArrowVisible;
    }
    
    /**
     * Sets a flag that controls whether or not the axis lines has an arrow 
     * drawn that points in the positive direction for the axis, and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #isPositiveArrowVisible()
     */
    public void setPositiveArrowVisible(boolean visible) {
        this.positiveArrowVisible = visible;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[49]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[50]++;
    }
    
    /**
     * Returns a flag that controls whether or not the axis line has an arrow 
     * drawn that points in the negative direction for the axis.
     * 
     * @return A boolean.
     * 
     * @see #setNegativeArrowVisible(boolean)
     */
    public boolean isNegativeArrowVisible() {
        return this.negativeArrowVisible;
    }
    
    /**
     * Sets a flag that controls whether or not the axis lines has an arrow 
     * drawn that points in the negative direction for the axis, and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #setNegativeArrowVisible(boolean)
     */
    public void setNegativeArrowVisible(boolean visible) {
        this.negativeArrowVisible = visible;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[51]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[52]++;
    }
    
    /**
     * Returns a shape that can be displayed as an arrow pointing upwards at 
     * the end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setUpArrow(Shape)
     */
    public Shape getUpArrow() {
        return this.upArrow;   
    }
    
    /**
     * Sets the shape that can be displayed as an arrow pointing upwards at 
     * the end of an axis line and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     * 
     * @param arrow  the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getUpArrow()
     */
    public void setUpArrow(Shape arrow) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[53]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((arrow == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[3]++;
            throw new IllegalArgumentException("Null 'arrow' argument.");
   
        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[4]++;}
        this.upArrow = arrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[54]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[55]++;
    }
    
    /**
     * Returns a shape that can be displayed as an arrow pointing downwards at 
     * the end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setDownArrow(Shape)
     */
    public Shape getDownArrow() {
        return this.downArrow;   
    }
    
    /**
     * Sets the shape that can be displayed as an arrow pointing downwards at 
     * the end of an axis line and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     * 
     * @param arrow  the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getDownArrow()
     */
    public void setDownArrow(Shape arrow) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[56]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((arrow == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[5]++;
            throw new IllegalArgumentException("Null 'arrow' argument.");
   
        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[6]++;}
        this.downArrow = arrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[57]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[58]++;
    }
    
    /**
     * Returns a shape that can be displayed as an arrow pointing left at the 
     * end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setLeftArrow(Shape)
     */
    public Shape getLeftArrow() {
        return this.leftArrow;   
    }
    
    /**
     * Sets the shape that can be displayed as an arrow pointing left at the 
     * end of an axis line and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     * 
     * @param arrow  the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getLeftArrow()
     */
    public void setLeftArrow(Shape arrow) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[59]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((arrow == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[7]++;
            throw new IllegalArgumentException("Null 'arrow' argument.");
   
        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[8]++;}
        this.leftArrow = arrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[60]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[61]++;
    }
    
    /**
     * Returns a shape that can be displayed as an arrow pointing right at the 
     * end of an axis line.
     * 
     * @return A shape (never <code>null</code>).
     * 
     * @see #setRightArrow(Shape)
     */
    public Shape getRightArrow() {
        return this.rightArrow;   
    }
    
    /**
     * Sets the shape that can be displayed as an arrow pointing rightwards at 
     * the end of an axis line and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     * 
     * @param arrow  the arrow shape (<code>null</code> not permitted).
     * 
     * @see #getRightArrow()
     */
    public void setRightArrow(Shape arrow) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[62]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((arrow == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[9]++;
            throw new IllegalArgumentException("Null 'arrow' argument.");
   
        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[10]++;}
        this.rightArrow = arrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[63]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[64]++;
    }
    
    /**
     * Draws an axis line at the current cursor position and edge.
     * 
     * @param g2  the graphics device.
     * @param cursor  the cursor position.
     * @param dataArea  the data area.
     * @param edge  the edge.
     */
    protected void drawAxisLine(Graphics2D g2, double cursor,
                                Rectangle2D dataArea, RectangleEdge edge) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[65]++;
        Line2D axisLine = null;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[66]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[11]++;
            axisLine = new Line2D.Double(dataArea.getX(), cursor, 
                    dataArea.getMaxX(), cursor);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[67]++;
  
        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[12]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[68]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[13]++;
            axisLine = new Line2D.Double(dataArea.getX(), cursor, 
                    dataArea.getMaxX(), cursor);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[69]++;
  
        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[14]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[70]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[15]++;
            axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[71]++;
  
        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[16]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[72]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[17]++;
            axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[73]++;
  
        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[18]++;}
}
}
}
        g2.setPaint(getAxisLinePaint());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[74]++;
        g2.setStroke(getAxisLineStroke());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[75]++;
        g2.draw(axisLine);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[76]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[77]++;
        
        boolean drawUpOrRight = false;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[78]++;  
        boolean drawDownOrLeft = false;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[79]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.positiveArrowVisible) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[19]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[80]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.inverted) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[21]++;
                drawDownOrLeft = true;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[81]++;
   
            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[22]++;
                drawUpOrRight = true;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[82]++;   
            }

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[20]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[83]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.negativeArrowVisible) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[23]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[84]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.inverted) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[25]++;
                drawUpOrRight = true;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[85]++;
   
            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[26]++;
                drawDownOrLeft = true;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[86]++;   
            }

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[24]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[87]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((drawUpOrRight) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[27]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[88]++;
            double x = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[89]++;
            double y = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[90]++;
            Shape arrow = null;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[91]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[29]++;
                x = dataArea.getMaxX();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[92]++;
                y = cursor;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[93]++;
                arrow = this.rightArrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[94]++;
 
            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[30]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[95]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[31]++;
                x = cursor;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[96]++;
                y = dataArea.getMinY();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[97]++;
                arrow = this.upArrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[98]++;
 
            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[32]++;}
}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[99]++;

            // draw the arrow...
            AffineTransform transformer = new AffineTransform();
            transformer.setToTranslation(x, y);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[100]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[101]++;
            Shape shape = transformer.createTransformedShape(arrow);
            g2.fill(shape);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[102]++;
            g2.draw(shape);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[103]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[28]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[104]++;
int CodeCoverConditionCoverageHelper_C17;
        
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((drawDownOrLeft) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[33]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[105]++;
            double x = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[106]++;
            double y = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[107]++;
            Shape arrow = null;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[108]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[35]++;
                x = dataArea.getMinX();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[109]++;
                y = cursor;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[110]++;
                arrow = this.leftArrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[111]++;
 
            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[36]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[112]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[37]++;
                x = cursor;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[113]++;
                y = dataArea.getMaxY();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[114]++;
                arrow = this.downArrow;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[115]++;
 
            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[38]++;}
}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[116]++;

            // draw the arrow...
            AffineTransform transformer = new AffineTransform();
            transformer.setToTranslation(x, y);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[117]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[118]++;
            Shape shape = transformer.createTransformedShape(arrow);
            g2.fill(shape);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[119]++;
            g2.draw(shape);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[120]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[34]++;}
        
    }
    
    /**
     * Calculates the anchor point for a tick label.
     * 
     * @param tick  the tick.
     * @param cursor  the cursor.
     * @param dataArea  the data area.
     * @param edge  the edge on which the axis is drawn.
     * 
     * @return The x and y coordinates of the anchor point.
     */
    protected float[] calculateAnchorPoint(ValueTick tick, 
                                           double cursor, 
                                           Rectangle2D dataArea, 
                                           RectangleEdge edge) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[121]++;
    
        RectangleInsets insets = getTickLabelInsets();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[122]++;
        float[] result = new float[2];
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[123]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[39]++;
            result[0] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[124]++;
            result[1] = (float) (cursor - insets.getBottom() - 2.0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[125]++;

        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[40]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[126]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[41]++;
            result[0] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[127]++;
            result[1] = (float) (cursor + insets.getTop() + 2.0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[128]++;
 
        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[42]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[129]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[43]++;
            result[0] = (float) (cursor - insets.getLeft() - 2.0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[130]++;    
            result[1] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[131]++;

        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[44]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[132]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[45]++;
            result[0] = (float) (cursor + insets.getRight() + 2.0);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[133]++;    
            result[1] = (float) valueToJava2D(tick.getValue(), dataArea, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[134]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[46]++;}
}
}
}
        return result;
    }
    
    /**
     * Draws the axis line, tick marks and tick mark labels.
     * 
     * @param g2  the graphics device.
     * @param cursor  the cursor.
     * @param plotArea  the plot area.
     * @param dataArea  the data area.
     * @param edge  the edge that the axis is aligned with.
     * 
     * @return The width or height used to draw the axis.
     */
    protected AxisState drawTickMarksAndLabels(Graphics2D g2, 
                                               double cursor,
                                               Rectangle2D plotArea,
                                               Rectangle2D dataArea, 
                                               RectangleEdge edge) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[135]++;
                                              
        AxisState state = new AxisState(cursor);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[136]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isAxisLineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[47]++;
            drawAxisLine(g2, cursor, dataArea, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[137]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[48]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[138]++;

        double ol = getTickMarkOutsideLength();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[139]++;
        double il = getTickMarkInsideLength();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[140]++;

        List ticks = refreshTicks(g2, state, dataArea, edge);
        state.setTicks(ticks);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[141]++;
        g2.setFont(getTickLabelFont());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[142]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[143]++;
        Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[144]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[1]++;


int CodeCoverConditionCoverageHelper_C25;
        while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[1]--;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[2]--;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[3]++;
}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[145]++;
            ValueTick tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[146]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((isTickLabelsVisible()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[49]++;
                g2.setPaint(getTickLabelPaint());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[147]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[148]++;
                float[] anchorPoint = calculateAnchorPoint(tick, cursor, 
                        dataArea, edge);
                TextUtilities.drawRotatedString(tick.getText(), g2, 
                        anchorPoint[0], anchorPoint[1], tick.getTextAnchor(), 
                        tick.getAngle(), tick.getRotationAnchor());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[149]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[50]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[150]++;
int CodeCoverConditionCoverageHelper_C27;

            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((isTickMarksVisible()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((tick.getTickType().equals(
                    TickType.MAJOR)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[51]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[151]++;
                float xx = (float) valueToJava2D(tick.getValue(), dataArea, 
                        edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[152]++;
                Line2D mark = null;
                g2.setStroke(getTickMarkStroke());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[153]++;
                g2.setPaint(getTickMarkPaint());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[154]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[155]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[53]++;
                    mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[156]++;

                }
                else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[54]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[157]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[55]++;
                    mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[158]++;

                }
                else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[56]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[159]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[57]++;
                    mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[160]++;

                }
                else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[58]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[161]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[59]++;
                    mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[162]++;

                } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[60]++;}
}
}
}
                g2.draw(mark);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[163]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[52]++;}
        }
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[164]++;
        
        // need to work out the space used by the tick labels...
        // so we can update the cursor...
        double used = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[165]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isTickLabelsVisible()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[61]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[166]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[63]++;
                used += findMaximumTickLabelWidth(ticks, g2, plotArea, 
                        isVerticalTickLabels());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[167]++;  
                state.cursorLeft(used);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[168]++;
      
            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[64]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[169]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[65]++;
                used = findMaximumTickLabelWidth(ticks, g2, plotArea, 
                        isVerticalTickLabels());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[170]++;
                state.cursorRight(used);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[171]++;
      
            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[66]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[172]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[67]++;
                used = findMaximumTickLabelHeight(ticks, g2, plotArea, 
                        isVerticalTickLabels());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[173]++;
                state.cursorUp(used);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[174]++;

            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[68]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[175]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[69]++;
                used = findMaximumTickLabelHeight(ticks, g2, plotArea, 
                        isVerticalTickLabels());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[176]++;
                state.cursorDown(used);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[177]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[70]++;}
}
}
}

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[62]++;}
       
        return state;
    }
    
    /**
     * Returns the space required to draw the axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot that the axis belongs to.
     * @param plotArea  the area within which the plot should be drawn.
     * @param edge  the axis location.
     * @param space  the space already reserved (for other axes).
     *
     * @return The space required to draw the axis (including pre-reserved 
     *         space).
     */
    public AxisSpace reserveSpace(Graphics2D g2, Plot plot,
                                  Rectangle2D plotArea, 
                                  RectangleEdge edge, AxisSpace space) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[178]++;
int CodeCoverConditionCoverageHelper_C37;

        // create a new space object if one wasn't supplied...
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[71]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[179]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[72]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[180]++;
int CodeCoverConditionCoverageHelper_C38;
        
        // if the axis is not visible, no additional space is required...
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[73]++;
            return space;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[74]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[181]++;

        // if the axis has a fixed dimension, return it...
        double dimension = getFixedDimension();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[182]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((dimension > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[75]++;
            space.ensureAtLeast(dimension, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[183]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[76]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[184]++;

        // calculate the max size of the tick labels (if visible)...
        double tickLabelHeight = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[185]++;
        double tickLabelWidth = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[186]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((isTickLabelsVisible()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[77]++;
            g2.setFont(getTickLabelFont());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[187]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[188]++;
            List ticks = refreshTicks(g2, new AxisState(), plotArea, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[189]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[79]++;
                tickLabelHeight = findMaximumTickLabelHeight(ticks, g2, 
                        plotArea, isVerticalTickLabels());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[190]++;

            }
            else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[80]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[191]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[81]++;
                tickLabelWidth = findMaximumTickLabelWidth(ticks, g2, plotArea,
                        isVerticalTickLabels());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[192]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[82]++;}
}

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[78]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[193]++;

        // get the axis label size and update the space object...
        Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[194]++;
        double labelHeight = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[195]++;
        double labelWidth = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[196]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[83]++;
            labelHeight = labelEnclosure.getHeight();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[197]++;
            space.add(labelHeight + tickLabelHeight, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[198]++;

        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[84]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[199]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[85]++;
            labelWidth = labelEnclosure.getWidth();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[200]++;
            space.add(labelWidth + tickLabelWidth, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[201]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[86]++;}
}

        return space;

    }

    /**
     * A utility method for determining the height of the tallest tick label.
     *
     * @param ticks  the ticks.
     * @param g2  the graphics device.
     * @param drawArea  the area within which the plot and axes should be drawn.
     * @param vertical  a flag that indicates whether or not the tick labels 
     *                  are 'vertical'.
     *
     * @return The height of the tallest tick label.
     */
    protected double findMaximumTickLabelHeight(List ticks,
                                                Graphics2D g2, 
                                                Rectangle2D drawArea, 
                                                boolean vertical) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[202]++;
                                                    
        RectangleInsets insets = getTickLabelInsets();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[203]++;
        Font font = getTickLabelFont();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[204]++;
        double maxHeight = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[205]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((vertical) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[87]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[206]++;
            FontMetrics fm = g2.getFontMetrics(font);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[207]++;
            Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[208]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[4]++;


int CodeCoverConditionCoverageHelper_C46;
            while ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[4]--;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[5]--;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[6]++;
}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[209]++;
                Tick tick = (Tick) iterator.next();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[210]++;
                Rectangle2D labelBounds = TextUtilities.getTextBounds(
                        tick.getText(), g2, fm);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[211]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((labelBounds.getWidth() + insets.getTop() 
                        + insets.getBottom() > maxHeight) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[89]++;
                    maxHeight = labelBounds.getWidth() 
                                + insets.getTop() + insets.getBottom();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[212]++;

                } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[90]++;}
            }

        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[88]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[213]++;
            LineMetrics metrics = font.getLineMetrics("ABCxyz", 
                    g2.getFontRenderContext());
            maxHeight = metrics.getHeight() 
                        + insets.getTop() + insets.getBottom();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[214]++;
        }
        return maxHeight;
        
    }

    /**
     * A utility method for determining the width of the widest tick label.
     *
     * @param ticks  the ticks.
     * @param g2  the graphics device.
     * @param drawArea  the area within which the plot and axes should be drawn.
     * @param vertical  a flag that indicates whether or not the tick labels 
     *                  are 'vertical'.
     *
     * @return The width of the tallest tick label.
     */
    protected double findMaximumTickLabelWidth(List ticks, 
                                               Graphics2D g2, 
                                               Rectangle2D drawArea, 
                                               boolean vertical) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[215]++;
                                                   
        RectangleInsets insets = getTickLabelInsets();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[216]++;
        Font font = getTickLabelFont();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[217]++;
        double maxWidth = 0.0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[218]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((vertical) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[91]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[219]++;
            FontMetrics fm = g2.getFontMetrics(font);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[220]++;
            Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[221]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[7]++;


int CodeCoverConditionCoverageHelper_C49;
            while ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[7]--;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[8]--;
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.loops[9]++;
}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[222]++;
                Tick tick = (Tick) iterator.next();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[223]++;
                Rectangle2D labelBounds = TextUtilities.getTextBounds(
                        tick.getText(), g2, fm);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[224]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((labelBounds.getWidth() + insets.getLeft() 
                        + insets.getRight() > maxWidth) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[93]++;
                    maxWidth = labelBounds.getWidth() 
                               + insets.getLeft() + insets.getRight();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[225]++;

                } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[94]++;}
            }

        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[92]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[226]++;
            LineMetrics metrics = font.getLineMetrics("ABCxyz", 
                    g2.getFontRenderContext());
            maxWidth = metrics.getHeight() 
                       + insets.getTop() + insets.getBottom();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[227]++;
        }
        return maxWidth;
        
    }

    /**
     * Returns a flag that controls the direction of values on the axis.
     * <P>
     * For a regular axis, values increase from left to right (for a horizontal
     * axis) and bottom to top (for a vertical axis).  When the axis is
     * 'inverted', the values increase in the opposite direction.
     *
     * @return The flag.
     * 
     * @see #setInverted(boolean)
     */
    public boolean isInverted() {
        return this.inverted;
    }

    /**
     * Sets a flag that controls the direction of values on the axis, and
     * notifies registered listeners that the axis has changed.
     *
     * @param flag  the flag.
     * 
     * @see #isInverted()
     */
    public void setInverted(boolean flag) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[228]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.inverted != flag) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[95]++;
            this.inverted = flag;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[229]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[230]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[96]++;}

    }

    /**
     * Returns the flag that controls whether or not the axis range is 
     * automatically adjusted to fit the data values.
     *
     * @return The flag.
     * 
     * @see #setAutoRange(boolean)
     */
    public boolean isAutoRange() {
        return this.autoRange;
    }

    /**
     * Sets a flag that determines whether or not the axis range is
     * automatically adjusted to fit the data, and notifies registered
     * listeners that the axis has been modified.
     *
     * @param auto  the new value of the flag.
     * 
     * @see #isAutoRange()
     */
    public void setAutoRange(boolean auto) {
        setAutoRange(auto, true);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[231]++;
    }

    /**
     * Sets the auto range attribute.  If the <code>notify</code> flag is set, 
     * an {@link AxisChangeEvent} is sent to registered listeners.
     *
     * @param auto  the flag.
     * @param notify  notify listeners?
     * 
     * @see #isAutoRange()
     */
    protected void setAutoRange(boolean auto, boolean notify) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[232]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((this.autoRange != auto) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[97]++;
            this.autoRange = auto;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[233]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[234]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this.autoRange) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[99]++;
                autoAdjustRange();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[235]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[100]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[236]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[101]++;
                notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[237]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[102]++;}

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[98]++;}
    }

    /**
     * Returns the minimum size allowed for the axis range when it is 
     * automatically calculated.
     *
     * @return The minimum range.
     * 
     * @see #setAutoRangeMinimumSize(double)
     */
    public double getAutoRangeMinimumSize() {
        return this.autoRangeMinimumSize;
    }

    /**
     * Sets the auto range minimum size and sends an {@link AxisChangeEvent} 
     * to all registered listeners.
     *
     * @param size  the size.
     * 
     * @see #getAutoRangeMinimumSize()
     */
    public void setAutoRangeMinimumSize(double size) {
        setAutoRangeMinimumSize(size, true);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[238]++;
    }

    /**
     * Sets the minimum size allowed for the axis range when it is 
     * automatically calculated.
     * <p>
     * If requested, an {@link AxisChangeEvent} is forwarded to all registered 
     * listeners.
     *
     * @param size  the new minimum.
     * @param notify  notify listeners?
     */
    public void setAutoRangeMinimumSize(double size, boolean notify) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[239]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((size <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[103]++;
            throw new IllegalArgumentException(
                "NumberAxis.setAutoRangeMinimumSize(double): must be > 0.0.");

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[104]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[240]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this.autoRangeMinimumSize != size) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[105]++;
            this.autoRangeMinimumSize = size;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[241]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[242]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.autoRange) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[107]++;
                autoAdjustRange();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[243]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[108]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[244]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[109]++;
                notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[245]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[110]++;}

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[106]++;}

    }
    
    /**
     * Returns the default auto range.
     * 
     * @return The default auto range (never <code>null</code>).
     * 
     * @see #setDefaultAutoRange(Range)
     *
     * @since 1.0.5
     */
    public Range getDefaultAutoRange() {
        return this.defaultAutoRange;
    }
    
    /**
     * Sets the default auto range and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * 
     * @param range  the range (<code>null</code> not permitted).
     * 
     * @see #getDefaultAutoRange()
     * 
     * @since 1.0.5
     */
    public void setDefaultAutoRange(Range range) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[246]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[111]++;
            throw new IllegalArgumentException("Null 'range' argument.");

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[112]++;}
        this.defaultAutoRange = range;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[247]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[248]++;
    }

    /**
     * Returns the lower margin for the axis, expressed as a percentage of the 
     * axis range.  This controls the space added to the lower end of the axis 
     * when the axis range is automatically calculated (it is ignored when the 
     * axis range is set explicitly). The default value is 0.05 (five percent).
     *
     * @return The lower margin.
     *
     * @see #setLowerMargin(double)
     */
    public double getLowerMargin() {
        return this.lowerMargin;
    }

    /**
     * Sets the lower margin for the axis (as a percentage of the axis range) 
     * and sends an {@link AxisChangeEvent} to all registered listeners.  This
     * margin is added only when the axis range is auto-calculated - if you set 
     * the axis range manually, the margin is ignored.
     *
     * @param margin  the margin percentage (for example, 0.05 is five percent).
     *
     * @see #getLowerMargin()
     * @see #setUpperMargin(double)
     */
    public void setLowerMargin(double margin) {
        this.lowerMargin = margin;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[249]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[250]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[113]++;
            autoAdjustRange();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[251]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[114]++;}
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[252]++;
    }

    /**
     * Returns the upper margin for the axis, expressed as a percentage of the 
     * axis range.  This controls the space added to the lower end of the axis 
     * when the axis range is automatically calculated (it is ignored when the 
     * axis range is set explicitly). The default value is 0.05 (five percent).
     *
     * @return The upper margin.
     *
     * @see #setUpperMargin(double)
     */
    public double getUpperMargin() {
        return this.upperMargin;
    }

    /**
     * Sets the upper margin for the axis (as a percentage of the axis range) 
     * and sends an {@link AxisChangeEvent} to all registered listeners.  This 
     * margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin  the margin percentage (for example, 0.05 is five percent).
     *
     * @see #getLowerMargin()
     * @see #setLowerMargin(double)
     */
    public void setUpperMargin(double margin) {
        this.upperMargin = margin;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[253]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[254]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[115]++;
            autoAdjustRange();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[255]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[116]++;}
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[256]++;
    }

    /**
     * Returns the fixed auto range.
     *
     * @return The length.
     * 
     * @see #setFixedAutoRange(double)
     */
    public double getFixedAutoRange() {
        return this.fixedAutoRange;
    }

    /**
     * Sets the fixed auto range for the axis.
     *
     * @param length  the range length.
     * 
     * @see #getFixedAutoRange()
     */
    public void setFixedAutoRange(double length) {
        this.fixedAutoRange = length;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[257]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[258]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[117]++;
            autoAdjustRange();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[259]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[118]++;}
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[260]++;
    }

    /**
     * Returns the lower bound of the axis range.
     *
     * @return The lower bound.
     * 
     * @see #setLowerBound(double)
     */
    public double getLowerBound() {
        return this.range.getLowerBound();
    }

    /**
     * Sets the lower bound for the axis range.  An {@link AxisChangeEvent} is 
     * sent to all registered listeners.
     *
     * @param min  the new minimum.
     * 
     * @see #getLowerBound()
     */
    public void setLowerBound(double min) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[261]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.range.getUpperBound() > min) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[119]++;
            setRange(new Range(min, this.range.getUpperBound()));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[262]++;
            
        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[120]++;
            setRange(new Range(min, min + 1.0));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[263]++;                        
        }
    }

    /**
     * Returns the upper bound for the axis range.
     *
     * @return The upper bound.
     * 
     * @see #setUpperBound(double)
     */
    public double getUpperBound() {
        return this.range.getUpperBound();
    }

    /**
     * Sets the upper bound for the axis range, and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param max  the new maximum.
     * 
     * @see #getUpperBound()
     */
    public void setUpperBound(double max) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[264]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((this.range.getLowerBound() < max) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[121]++;
            setRange(new Range(this.range.getLowerBound(), max));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[265]++;

        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[122]++;
            setRange(max - 1.0, max);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[266]++;
        }
    }

    /**
     * Returns the range for the axis.
     *
     * @return The axis range (never <code>null</code>).
     * 
     * @see #setRange(Range)
     */
    public Range getRange() {
        return this.range;
    }

    /**
     * Sets the range attribute and sends an {@link AxisChangeEvent} to all 
     * registered listeners.  As a side-effect, the auto-range flag is set to 
     * <code>false</code>.
     *
     * @param range  the range (<code>null</code> not permitted).
     * 
     * @see #getRange()
     */
    public void setRange(Range range) {
        // defer argument checking
        setRange(range, true, true);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[267]++;
    }

    /**
     * Sets the range for the axis, if requested, sends an 
     * {@link AxisChangeEvent} to all registered listeners.  As a side-effect, 
     * the auto-range flag is set to <code>false</code> (optional).
     *
     * @param range  the range (<code>null</code> not permitted).
     * @param turnOffAutoRange  a flag that controls whether or not the auto 
     *                          range is turned off.         
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getRange()
     */
    public void setRange(Range range, boolean turnOffAutoRange, 
                         boolean notify) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[268]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[123]++;
            throw new IllegalArgumentException("Null 'range' argument.");

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[124]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[269]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((turnOffAutoRange) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[125]++;
            this.autoRange = false;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[270]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[126]++;}
        this.range = range;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[271]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[272]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[127]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[273]++;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[128]++;}
    }

    /**
     * Sets the axis range and sends an {@link AxisChangeEvent} to all 
     * registered listeners.  As a side-effect, the auto-range flag is set to 
     * <code>false</code>.
     *
     * @param lower  the lower axis limit.
     * @param upper  the upper axis limit.
     * 
     * @see #getRange()
     * @see #setRange(Range)
     */
    public void setRange(double lower, double upper) {
        setRange(new Range(lower, upper));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[274]++;
    }
    
    /**
     * Sets the range for the axis (after first adding the current margins to 
     * the specified range) and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     * 
     * @param range  the range (<code>null</code> not permitted).
     */
    public void setRangeWithMargins(Range range) {
        setRangeWithMargins(range, true, true);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[275]++;
    }

    /**
     * Sets the range for the axis after first adding the current margins to 
     * the range and, if requested, sends an {@link AxisChangeEvent} to all 
     * registered listeners.  As a side-effect, the auto-range flag is set to 
     * <code>false</code> (optional).
     *
     * @param range  the range (excluding margins, <code>null</code> not 
     *               permitted).
     * @param turnOffAutoRange  a flag that controls whether or not the auto 
     *                          range is turned off.
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     */
    public void setRangeWithMargins(Range range, boolean turnOffAutoRange, 
                                    boolean notify) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[276]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[129]++;
            throw new IllegalArgumentException("Null 'range' argument.");

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[130]++;}
        setRange(Range.expand(range, getLowerMargin(), getUpperMargin()), 
                turnOffAutoRange, notify);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[277]++;
    }

    /**
     * Sets the axis range (after first adding the current margins to the 
     * range) and sends an {@link AxisChangeEvent} to all registered listeners.
     * As a side-effect, the auto-range flag is set to <code>false</code>.
     *
     * @param lower  the lower axis limit.
     * @param upper  the upper axis limit.
     */
    public void setRangeWithMargins(double lower, double upper) {
        setRangeWithMargins(new Range(lower, upper));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[278]++;
    }
    
    /**
     * Sets the axis range, where the new range is 'size' in length, and 
     * centered on 'value'.
     *
     * @param value  the central value.
     * @param length  the range length.
     */
    public void setRangeAboutValue(double value, double length) {
        setRange(new Range(value - length / 2, value + length / 2));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[279]++;
    }

    /**
     * Returns a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units.
     *
     * @return A flag indicating whether or not the tick unit is automatically
     *         selected.
     *         
     * @see #setAutoTickUnitSelection(boolean)
     */
    public boolean isAutoTickUnitSelection() {
        return this.autoTickUnitSelection;
    }

    /**
     * Sets a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units.  If the flag is changed, 
     * registered listeners are notified that the chart has changed.
     *
     * @param flag  the new value of the flag.
     * 
     * @see #isAutoTickUnitSelection()
     */
    public void setAutoTickUnitSelection(boolean flag) {
        setAutoTickUnitSelection(flag, true);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[280]++;
    }

    /**
     * Sets a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units.
     *
     * @param flag  the new value of the flag.
     * @param notify  notify listeners?
     * 
     * @see #isAutoTickUnitSelection()
     */
    public void setAutoTickUnitSelection(boolean flag, boolean notify) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[281]++;
int CodeCoverConditionCoverageHelper_C69;

        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.autoTickUnitSelection != flag) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[131]++;
            this.autoTickUnitSelection = flag;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[282]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[283]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[133]++;
                notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[284]++;

            } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[134]++;}

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[132]++;}
    }

    /**
     * Returns the source for obtaining standard tick units for the axis.
     *
     * @return The source (possibly <code>null</code>).
     * 
     * @see #setStandardTickUnits(TickUnitSource)
     */
    public TickUnitSource getStandardTickUnits() {
        return this.standardTickUnits;
    }

    /**
     * Sets the source for obtaining standard tick units for the axis and sends
     * an {@link AxisChangeEvent} to all registered listeners.  The axis will 
     * try to select the smallest tick unit from the source that does not cause
     * the tick labels to overlap (see also the 
     * {@link #setAutoTickUnitSelection(boolean)} method.
     *
     * @param source  the source for standard tick units (<code>null</code> 
     *                permitted).
     *                
     * @see #getStandardTickUnits()
     */
    public void setStandardTickUnits(TickUnitSource source) {
        this.standardTickUnits = source;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[285]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[286]++;
    }
    
    /**
     * Converts a data value to a coordinate in Java2D space, assuming that the
     * axis runs along one edge of the specified dataArea.
     * <p>
     * Note that it is possible for the coordinate to fall outside the area.
     *
     * @param value  the data value.
     * @param area  the area for plotting the data.
     * @param edge  the edge along which the axis lies.
     *
     * @return The Java2D coordinate.
     * 
     * @see #java2DToValue(double, Rectangle2D, RectangleEdge)
     */
    public abstract double valueToJava2D(double value, Rectangle2D area, 
                                         RectangleEdge edge);
    
    /**
     * Converts a length in data coordinates into the corresponding length in 
     * Java2D coordinates.
     * 
     * @param length  the length.
     * @param area  the plot area.
     * @param edge  the edge along which the axis lies.
     * 
     * @return The length in Java2D coordinates.
     */
    public double lengthToJava2D(double length, Rectangle2D area, 
                                 RectangleEdge edge) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[287]++;
        double zero = valueToJava2D(0.0, area, edge);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[288]++;
        double l = valueToJava2D(length, area, edge);
        return Math.abs(l - zero);
    }

    /**
     * Converts a coordinate in Java2D space to the corresponding data value,
     * assuming that the axis runs along one edge of the specified dataArea.
     *
     * @param java2DValue  the coordinate in Java2D space.
     * @param area  the area in which the data is plotted.
     * @param edge  the edge along which the axis lies.
     *
     * @return The data value.
     * 
     * @see #valueToJava2D(double, Rectangle2D, RectangleEdge)
     */
    public abstract double java2DToValue(double java2DValue,
                                         Rectangle2D area,
                                         RectangleEdge edge);

    /**
     * Automatically sets the axis range to fit the range of values in the 
     * dataset.  Sometimes this can depend on the renderer used as well (for 
     * example, the renderer may "stack" values, requiring an axis range 
     * greater than otherwise necessary).
     */
    protected abstract void autoAdjustRange();

    /**
     * Centers the axis range about the specified value and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param value  the center value.
     */
    public void centerRange(double value) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[289]++;

        double central = this.range.getCentralValue();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[290]++;
        Range adjusted = new Range(this.range.getLowerBound() + value - central,
                this.range.getUpperBound() + value - central);
        setRange(adjusted);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[291]++;

    }

    /**
     * Increases or decreases the axis range by the specified percentage about 
     * the central value and sends an {@link AxisChangeEvent} to all registered
     * listeners.
     * <P>
     * To double the length of the axis range, use 200% (2.0).
     * To halve the length of the axis range, use 50% (0.5).
     *
     * @param percent  the resize factor.
     * 
     * @see #resizeRange(double, double)
     */
    public void resizeRange(double percent) {
        resizeRange(percent, this.range.getCentralValue());
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[292]++;
    }

    /**
     * Increases or decreases the axis range by the specified percentage about
     * the specified anchor value and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     * <P>
     * To double the length of the axis range, use 200% (2.0).
     * To halve the length of the axis range, use 50% (0.5).
     *
     * @param percent  the resize factor.
     * @param anchorValue  the new central value after the resize.
     * 
     * @see #resizeRange(double)
     */
    public void resizeRange(double percent, double anchorValue) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[293]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((percent > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[135]++;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[294]++;
            double halfLength = this.range.getLength() * percent / 2;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[295]++;
            Range adjusted = new Range(anchorValue - halfLength, 
                    anchorValue + halfLength);
            setRange(adjusted);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[296]++;

        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[136]++;
            setAutoRange(true);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[297]++;
        }
    }
    
    /**
     * Zooms in on the current range.
     * 
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     */
    public void zoomRange(double lowerPercent, double upperPercent) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[298]++;
        double start = this.range.getLowerBound();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[299]++;
        double length = this.range.getLength();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[300]++;
        Range adjusted = null;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[301]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[137]++;
            adjusted = new Range(start + (length * (1 - upperPercent)), 
                                 start + (length * (1 - lowerPercent)));
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[302]++;
 
        }
        else {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[138]++;
            adjusted = new Range(start + length * lowerPercent, 
                    start + length * upperPercent);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[303]++;
        }
        setRange(adjusted);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[304]++;
    }

    /**
     * Returns the auto tick index.
     *
     * @return The auto tick index.
     * 
     * @see #setAutoTickIndex(int)
     */
    protected int getAutoTickIndex() {
        return this.autoTickIndex;
    }

    /**
     * Sets the auto tick index.
     *
     * @param index  the new value.
     * 
     * @see #getAutoTickIndex()
     */
    protected void setAutoTickIndex(int index) {
        this.autoTickIndex = index;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[305]++;
    }

    /**
     * Tests the axis for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[306]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[139]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[140]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[307]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((obj instanceof ValueAxis) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[141]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[142]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[308]++;

        ValueAxis that = (ValueAxis) obj;
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[309]++;
int CodeCoverConditionCoverageHelper_C75;
        
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.positiveArrowVisible != that.positiveArrowVisible) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[143]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[144]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[310]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((this.negativeArrowVisible != that.negativeArrowVisible) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[145]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[146]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[311]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((this.inverted != that.inverted) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[147]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[148]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[312]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.range, that.range)) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[149]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[150]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[313]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((this.autoRange != that.autoRange) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[151]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[152]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[314]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((this.autoRangeMinimumSize != that.autoRangeMinimumSize) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[153]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[154]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[315]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((this.defaultAutoRange.equals(that.defaultAutoRange)) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[155]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[156]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[316]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((this.upperMargin != that.upperMargin) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[157]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[158]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[317]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((this.lowerMargin != that.lowerMargin) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[159]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[160]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[318]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((this.fixedAutoRange != that.fixedAutoRange) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[161]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[162]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[319]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((this.autoTickUnitSelection != that.autoTickUnitSelection) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[163]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[164]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[320]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.standardTickUnits, 
                that.standardTickUnits)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[165]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[166]++;}
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[321]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((this.verticalTickLabels != that.verticalTickLabels) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[167]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.branches[168]++;}

        return super.equals(obj);

    }
    
    /**
     * Returns a clone of the object.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some component of the axis does 
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[322]++;
        ValueAxis clone = (ValueAxis) super.clone();
        return clone;
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[323]++;
        SerialUtilities.writeShape(this.upArrow, stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[324]++;
        SerialUtilities.writeShape(this.downArrow, stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[325]++;
        SerialUtilities.writeShape(this.leftArrow, stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[326]++;
        SerialUtilities.writeShape(this.rightArrow, stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[327]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException {

        stream.defaultReadObject();
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[328]++;
        this.upArrow = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[329]++;
        this.downArrow = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[330]++;
        this.leftArrow = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[331]++;
        this.rightArrow = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1.statements[332]++;

    }
   
}

class CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1 ());
  }
    public static long[] statements = new long[333];
    public static long[] branches = new long[169];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[88];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.ValueAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,2,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 87; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$3n2bs2hkyfx77ovmhu3mg1 () {
    super("org.jfree.chart.axis.ValueAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 332; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 168; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 87; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.ValueAxis.java");
      for (int i = 1; i <= 332; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 168; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 87; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
        if (loops[i * 3 - 2] != 0L) {
          log.passCounter("L" + i + "-0", loops[i * 3 - 2]);
          loops[i * 3 - 2] = 0L;
        }
        if ( loops[i * 3 - 1] != 0L) {
          log.passCounter("L" + i + "-1", loops[i * 3 - 1]);
          loops[i * 3 - 1] = 0L;
        }
        if ( loops[i * 3] != 0L) {
          log.passCounter("L" + i + "-2", loops[i * 3]);
          loops[i * 3] = 0L;
        }
      }
  }
}

