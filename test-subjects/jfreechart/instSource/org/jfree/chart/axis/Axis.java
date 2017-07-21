/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
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
 * ---------
 * Axis.java
 * ---------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Bill Kelemen; Nicolas Brodu
 *
 * Changes
 * -------
 * 21-Aug-2001 : Added standard header, fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 07-Nov-2001 : Allow null axis labels (DG);
 *             : Added default font values (DG);
 * 13-Nov-2001 : Modified the setPlot() method to check compatibility between 
 *               the axis and the plot (DG);
 * 30-Nov-2001 : Changed default font from "Arial" --> "SansSerif" (DG);
 * 06-Dec-2001 : Allow null in setPlot() method (BK);
 * 06-Mar-2002 : Added AxisConstants interface (DG);
 * 23-Apr-2002 : Added a visible property.  Moved drawVerticalString to 
 *               RefineryUtilities.  Added fixedDimension property for use in 
 *               combined plots (DG);
 * 25-Jun-2002 : Removed unnecessary imports (DG);
 * 05-Sep-2002 : Added attribute for tick mark paint (DG);
 * 18-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 07-Nov-2002 : Added attributes to control the inside and outside length of 
 *               the tick marks (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 18-Nov-2002 : Added axis location to refreshTicks() parameters (DG);
 * 15-Jan-2003 : Removed monolithic constructor (DG);
 * 17-Jan-2003 : Moved plot classes to separate package (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 03-Jul-2003 : Modified reserveSpace method (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 06-Nov-2003 : Modified refreshTicks() signature (DG);
 * 06-Jan-2004 : Added axis line attributes (DG);
 * 16-Mar-2004 : Added plot state to draw() method (DG);
 * 07-Apr-2004 : Modified text bounds calculation (DG);
 * 18-May-2004 : Eliminated AxisConstants.java (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities --> 
 *               TextUtilities (DG);
 * 04-Oct-2004 : Modified getLabelEnclosure() method to treat an empty String 
 *               the same way as a null string - see bug 1026521 (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 01-Jun-2005 : Added hasListener() method for unit testing (DG);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 22-Aug-2006 : API doc updates (DG);
 * 
 */

package org.jfree.chart.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

import javax.swing.event.EventListenerList;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * The base class for all axes in JFreeChart.  Subclasses are divided into 
 * those that display values ({@link ValueAxis}) and those that display 
 * categories ({@link CategoryAxis}).
 */
public abstract class Axis implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7719289504573298271L;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[1]++;
  }
    
    /** The default axis visibility. */
    public static final boolean DEFAULT_AXIS_VISIBLE = true;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[2]++;
  }

    /** The default axis label font. */
    public static final Font DEFAULT_AXIS_LABEL_FONT 
        = new Font("SansSerif", Font.PLAIN, 12);
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[3]++;
  }

    /** The default axis label paint. */
    public static final Paint DEFAULT_AXIS_LABEL_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[4]++;
  }

    /** The default axis label insets. */
    public static final RectangleInsets DEFAULT_AXIS_LABEL_INSETS 
        = new RectangleInsets(3.0, 3.0, 3.0, 3.0);
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[5]++;
  }

    /** The default axis line paint. */
    public static final Paint DEFAULT_AXIS_LINE_PAINT = Color.gray;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[6]++;
  }
    
    /** The default axis line stroke. */
    public static final Stroke DEFAULT_AXIS_LINE_STROKE = new BasicStroke(1.0f);
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[7]++;
  }

    /** The default tick labels visibility. */
    public static final boolean DEFAULT_TICK_LABELS_VISIBLE = true;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[8]++;
  }

    /** The default tick label font. */
    public static final Font DEFAULT_TICK_LABEL_FONT 
        = new Font("SansSerif", Font.PLAIN, 10);
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[9]++;
  }

    /** The default tick label paint. */
    public static final Paint DEFAULT_TICK_LABEL_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[10]++;
  }

    /** The default tick label insets. */
    public static final RectangleInsets DEFAULT_TICK_LABEL_INSETS 
        = new RectangleInsets(2.0, 4.0, 2.0, 4.0);
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[11]++;
  }

    /** The default tick marks visible. */
    public static final boolean DEFAULT_TICK_MARKS_VISIBLE = true;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[12]++;
  }

    /** The default tick stroke. */
    public static final Stroke DEFAULT_TICK_MARK_STROKE = new BasicStroke(1);
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[13]++;
  }

    /** The default tick paint. */
    public static final Paint DEFAULT_TICK_MARK_PAINT = Color.gray;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[14]++;
  }

    /** The default tick mark inside length. */
    public static final float DEFAULT_TICK_MARK_INSIDE_LENGTH = 0.0f;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[15]++;
  }

    /** The default tick mark outside length. */
    public static final float DEFAULT_TICK_MARK_OUTSIDE_LENGTH = 2.0f;
  static {
    CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[16]++;
  }

    /** A flag indicating whether or not the axis is visible. */
    private boolean visible;

    /** The label for the axis. */
    private String label;

    /** The font for displaying the axis label. */
    private Font labelFont;

    /** The paint for drawing the axis label. */
    private transient Paint labelPaint;

    /** The insets for the axis label. */
    private RectangleInsets labelInsets;

    /** The label angle. */
    private double labelAngle;

    /** A flag that controls whether or not the axis line is visible. */
    private boolean axisLineVisible;

    /** The stroke used for the axis line. */
    private transient Stroke axisLineStroke;
    
    /** The paint used for the axis line. */
    private transient Paint axisLinePaint;
    
    /** 
     * A flag that indicates whether or not tick labels are visible for the 
     * axis. 
     */
    private boolean tickLabelsVisible;

    /** The font used to display the tick labels. */
    private Font tickLabelFont;

    /** The color used to display the tick labels. */
    private transient Paint tickLabelPaint;

    /** The blank space around each tick label. */
    private RectangleInsets tickLabelInsets;

    /** 
     * A flag that indicates whether or not tick marks are visible for the 
     * axis. 
     */
    private boolean tickMarksVisible;

    /** The length of the tick mark inside the data area (zero permitted). */
    private float tickMarkInsideLength;

    /** The length of the tick mark outside the data area (zero permitted). */
    private float tickMarkOutsideLength;

    /** The stroke used to draw tick marks. */
    private transient Stroke tickMarkStroke;

    /** The paint used to draw tick marks. */
    private transient Paint tickMarkPaint;

    /** The fixed (horizontal or vertical) dimension for the axis. */
    private double fixedDimension;

    /** 
     * A reference back to the plot that the axis is assigned to (can be 
     * <code>null</code>). 
     */
    private transient Plot plot;

    /** Storage for registered listeners. */
    private transient EventListenerList listenerList;

    /**
     * Constructs an axis, using default values where necessary.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    protected Axis(String label) {

        this.label = label;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[17]++;
        this.visible = DEFAULT_AXIS_VISIBLE;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[18]++;
        this.labelFont = DEFAULT_AXIS_LABEL_FONT;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[19]++;
        this.labelPaint = DEFAULT_AXIS_LABEL_PAINT;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[20]++;
        this.labelInsets = DEFAULT_AXIS_LABEL_INSETS;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[21]++;
        this.labelAngle = 0.0;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[22]++;
        
        this.axisLineVisible = true;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[23]++;
        this.axisLinePaint = DEFAULT_AXIS_LINE_PAINT;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[24]++;
        this.axisLineStroke = DEFAULT_AXIS_LINE_STROKE;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[25]++;
        
        this.tickLabelsVisible = DEFAULT_TICK_LABELS_VISIBLE;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[26]++;
        this.tickLabelFont = DEFAULT_TICK_LABEL_FONT;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[27]++;
        this.tickLabelPaint = DEFAULT_TICK_LABEL_PAINT;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[28]++;
        this.tickLabelInsets = DEFAULT_TICK_LABEL_INSETS;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[29]++;
        
        this.tickMarksVisible = DEFAULT_TICK_MARKS_VISIBLE;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[30]++;
        this.tickMarkStroke = DEFAULT_TICK_MARK_STROKE;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[31]++;
        this.tickMarkPaint = DEFAULT_TICK_MARK_PAINT;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[32]++;
        this.tickMarkInsideLength = DEFAULT_TICK_MARK_INSIDE_LENGTH;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[33]++;
        this.tickMarkOutsideLength = DEFAULT_TICK_MARK_OUTSIDE_LENGTH;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[34]++;

        this.plot = null;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[35]++;

        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[36]++;

    }

    /**
     * Returns <code>true</code> if the axis is visible, and 
     * <code>false</code> otherwise.
     *
     * @return A boolean.
     * 
     * @see #setVisible(boolean)
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Sets a flag that controls whether or not the axis is visible and sends 
     * an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #isVisible()
     */
    public void setVisible(boolean flag) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[37]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((flag != this.visible) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[1]++;
            this.visible = flag;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[38]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[39]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[2]++;}
    }

    /**
     * Returns the label for the axis.
     *
     * @return The label for the axis (<code>null</code> possible).
     * 
     * @see #getLabelFont()
     * @see #getLabelPaint()
     * @see #setLabel(String)
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the label for the axis and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     *
     * @param label  the new label (<code>null</code> permitted).
     * 
     * @see #getLabel()
     * @see #setLabelFont(Font)
     * @see #setLabelPaint(Paint)
     */
    public void setLabel(String label) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[40]++;
        
        String existing = this.label;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[41]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[3]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[42]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((existing.equals(label)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[5]++;
                this.label = label;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[43]++;
                notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[44]++;

            } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[6]++;}

        }
        else {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[4]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[45]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[7]++;
                this.label = label;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[46]++;
                notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[47]++;

            } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[8]++;}
        }

    }

    /**
     * Returns the font for the axis label.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the font for the axis label and sends an {@link AxisChangeEvent} 
     * to all registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[48]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[9]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[10]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[49]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.labelFont.equals(font)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[11]++;
            this.labelFont = font;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[50]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[51]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[12]++;}
    }

    /**
     * Returns the color/shade used to draw the axis label.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setLabelPaint(Paint)
     */
    public Paint getLabelPaint() {
        return this.labelPaint;
    }

    /**
     * Sets the paint used to draw the axis label and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelPaint()
     */
    public void setLabelPaint(Paint paint) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[52]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[13]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[14]++;}
        this.labelPaint = paint;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[53]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[54]++;
    }

    /**
     * Returns the insets for the label (that is, the amount of blank space
     * that should be left around the label).
     *
     * @return The label insets (never <code>null</code>).
     * 
     * @see #setLabelInsets(RectangleInsets)
     */
    public RectangleInsets getLabelInsets() {
        return this.labelInsets;
    }

    /**
     * Sets the insets for the axis label, and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     *
     * @param insets  the insets (<code>null</code> not permitted).
     * 
     * @see #getLabelInsets()
     */
    public void setLabelInsets(RectangleInsets insets) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[55]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((insets == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[15]++;
            throw new IllegalArgumentException("Null 'insets' argument.");
   
        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[16]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[56]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((insets.equals(this.labelInsets)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[17]++;
            this.labelInsets = insets;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[57]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[58]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[18]++;}
    }

    /**
     * Returns the angle of the axis label.
     *
     * @return The angle (in radians).
     * 
     * @see #setLabelAngle(double)
     */
    public double getLabelAngle() {
        return this.labelAngle;
    }

    /**
     * Sets the angle for the label and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     *
     * @param angle  the angle (in radians).
     * 
     * @see #getLabelAngle()
     */
    public void setLabelAngle(double angle) {
        this.labelAngle = angle;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[59]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[60]++;
    }

    /**
     * A flag that controls whether or not the axis line is drawn.
     * 
     * @return A boolean.
     * 
     * @see #getAxisLinePaint()
     * @see #getAxisLineStroke()
     * @see #setAxisLineVisible(boolean)
     */
    public boolean isAxisLineVisible() {
        return this.axisLineVisible;
    }
    
    /**
     * Sets a flag that controls whether or not the axis line is visible and 
     * sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #isAxisLineVisible()
     * @see #setAxisLinePaint(Paint)
     * @see #setAxisLineStroke(Stroke)
     */
    public void setAxisLineVisible(boolean visible) {
        this.axisLineVisible = visible;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[61]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[62]++;
    }
    
    /**
     * Returns the paint used to draw the axis line.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setAxisLinePaint(Paint)
     */
    public Paint getAxisLinePaint() {
        return this.axisLinePaint;
    }
    
    /**
     * Sets the paint used to draw the axis line and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getAxisLinePaint()
     */
    public void setAxisLinePaint(Paint paint) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[63]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[19]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[20]++;}
        this.axisLinePaint = paint;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[64]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[65]++;
    }
    
    /**
     * Returns the stroke used to draw the axis line.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setAxisLineStroke(Stroke)
     */
    public Stroke getAxisLineStroke() {
        return this.axisLineStroke;
    }
    
    /**
     * Sets the stroke used to draw the axis line and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getAxisLineStroke()
     */
    public void setAxisLineStroke(Stroke stroke) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[66]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[21]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[22]++;}
        this.axisLineStroke = stroke;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[67]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[68]++;
    }
    
    /**
     * Returns a flag indicating whether or not the tick labels are visible.
     *
     * @return The flag.
     * 
     * @see #getTickLabelFont()
     * @see #getTickLabelPaint()
     * @see #setTickLabelsVisible(boolean)
     */
    public boolean isTickLabelsVisible() {
        return this.tickLabelsVisible;
    }

    /**
     * Sets the flag that determines whether or not the tick labels are 
     * visible and sends an {@link AxisChangeEvent} to all registered 
     * listeners.
     *
     * @param flag  the flag.
     * 
     * @see #isTickLabelsVisible()
     * @see #setTickLabelFont(Font)
     * @see #setTickLabelPaint(Paint)
     */
    public void setTickLabelsVisible(boolean flag) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[69]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((flag != this.tickLabelsVisible) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[23]++;
            this.tickLabelsVisible = flag;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[70]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[71]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[24]++;}

    }

    /**
     * Returns the font used for the tick labels (if showing).
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setTickLabelFont(Font)
     */
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }

    /**
     * Sets the font for the tick labels and sends an {@link AxisChangeEvent} 
     * to all registered listeners.
     *
     * @param font  the font (<code>null</code> not allowed).
     * 
     * @see #getTickLabelFont()
     */
    public void setTickLabelFont(Font font) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[72]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[25]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[26]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[73]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.tickLabelFont.equals(font)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[27]++;
            this.tickLabelFont = font;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[74]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[75]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[28]++;}

    }

    /**
     * Returns the color/shade used for the tick labels.
     *
     * @return The paint used for the tick labels.
     * 
     * @see #setTickLabelPaint(Paint)
     */
    public Paint getTickLabelPaint() {
        return this.tickLabelPaint;
    }

    /**
     * Sets the paint used to draw tick labels (if they are showing) and 
     * sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getTickLabelPaint()
     */
    public void setTickLabelPaint(Paint paint) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[76]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[29]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[30]++;}
        this.tickLabelPaint = paint;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[77]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[78]++;
    }

    /**
     * Returns the insets for the tick labels.
     *
     * @return The insets (never <code>null</code>).
     * 
     * @see #setTickLabelInsets(RectangleInsets)
     */
    public RectangleInsets getTickLabelInsets() {
        return this.tickLabelInsets;
    }

    /**
     * Sets the insets for the tick labels and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     *
     * @param insets  the insets (<code>null</code> not permitted).
     * 
     * @see #getTickLabelInsets()
     */
    public void setTickLabelInsets(RectangleInsets insets) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[79]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((insets == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[31]++;
            throw new IllegalArgumentException("Null 'insets' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[32]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[80]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.tickLabelInsets.equals(insets)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[33]++;
            this.tickLabelInsets = insets;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[81]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[82]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[34]++;}
    }

    /**
     * Returns the flag that indicates whether or not the tick marks are
     * showing.
     *
     * @return The flag that indicates whether or not the tick marks are 
     *         showing.
     *         
     * @see #setTickMarksVisible(boolean)
     */
    public boolean isTickMarksVisible() {
        return this.tickMarksVisible;
    }

    /**
     * Sets the flag that indicates whether or not the tick marks are showing
     * and sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #isTickMarksVisible()
     */
    public void setTickMarksVisible(boolean flag) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[83]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((flag != this.tickMarksVisible) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[35]++;
            this.tickMarksVisible = flag;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[84]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[85]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[36]++;}
    }

    /**
     * Returns the inside length of the tick marks.
     *
     * @return The length.
     * 
     * @see #getTickMarkOutsideLength()
     * @see #setTickMarkInsideLength(float)
     */
    public float getTickMarkInsideLength() {
        return this.tickMarkInsideLength;
    }

    /**
     * Sets the inside length of the tick marks and sends
     * an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param length  the new length.
     * 
     * @see #getTickMarkInsideLength()
     */
    public void setTickMarkInsideLength(float length) {
        this.tickMarkInsideLength = length;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[86]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[87]++;
    }

    /**
     * Returns the outside length of the tick marks.
     *
     * @return The length.
     * 
     * @see #getTickMarkInsideLength()
     * @see #setTickMarkOutsideLength(float)
     */
    public float getTickMarkOutsideLength() {
        return this.tickMarkOutsideLength;
    }

    /**
     * Sets the outside length of the tick marks and sends
     * an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param length  the new length.
     * 
     * @see #getTickMarkInsideLength()
     */
    public void setTickMarkOutsideLength(float length) {
        this.tickMarkOutsideLength = length;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[88]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[89]++;
    }

    /**
     * Returns the stroke used to draw tick marks.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setTickMarkStroke(Stroke)
     */
    public Stroke getTickMarkStroke() {
        return this.tickMarkStroke;
    }

    /**
     * Sets the stroke used to draw tick marks and sends
     * an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getTickMarkStroke()
     */
    public void setTickMarkStroke(Stroke stroke) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[90]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[37]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[38]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[91]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.tickMarkStroke.equals(stroke)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[39]++;
            this.tickMarkStroke = stroke;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[92]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[93]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[40]++;}
    }

    /**
     * Returns the paint used to draw tick marks (if they are showing).
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setTickMarkPaint(Paint)
     */
    public Paint getTickMarkPaint() {
        return this.tickMarkPaint;
    }

    /**
     * Sets the paint used to draw tick marks and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getTickMarkPaint()
     */
    public void setTickMarkPaint(Paint paint) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[94]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[41]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[42]++;}
        this.tickMarkPaint = paint;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[95]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[96]++;
    }

    /**
     * Returns the plot that the axis is assigned to.  This method will return 
     * <code>null</code> if the axis is not currently assigned to a plot.
     *
     * @return The plot that the axis is assigned to (possibly 
     *         <code>null</code>).
     *         
     * @see #setPlot(Plot)
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Sets a reference to the plot that the axis is assigned to.
     * <P>
     * This method is used internally, you shouldn't need to call it yourself.
     *
     * @param plot  the plot.
     * 
     * @see #getPlot()
     */
    public void setPlot(Plot plot) {
        this.plot = plot;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[97]++;
        configure();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[98]++;
    }

    /**
     * Returns the fixed dimension for the axis.
     *
     * @return The fixed dimension.
     * 
     * @see #setFixedDimension(double)
     */
    public double getFixedDimension() {
        return this.fixedDimension;
    }

    /**
     * Sets the fixed dimension for the axis.
     * <P>
     * This is used when combining more than one plot on a chart.  In this case,
     * there may be several axes that need to have the same height or width so
     * that they are aligned.  This method is used to fix a dimension for the
     * axis (the context determines whether the dimension is horizontal or
     * vertical).
     *
     * @param dimension  the fixed dimension.
     * 
     * @see #getFixedDimension()
     */
    public void setFixedDimension(double dimension) {
        this.fixedDimension = dimension;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[99]++;
    }

    /**
     * Configures the axis to work with the current plot.  Override this method
     * to perform any special processing (such as auto-rescaling).
     */
    public abstract void configure();

    /**
     * Estimates the space (height or width) required to draw the axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot that the axis belongs to.
     * @param plotArea  the area within which the plot (including axes) should 
     *                  be drawn.
     * @param edge  the axis location.
     * @param space  space already reserved.
     *
     * @return The space required to draw the axis (including pre-reserved 
     *         space).
     */
    public abstract AxisSpace reserveSpace(Graphics2D g2, Plot plot, 
                                           Rectangle2D plotArea, 
                                           RectangleEdge edge, 
                                           AxisSpace space);

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location (determines where to draw the axis).
     * @param plotArea  the area within which the axes and plot should be drawn.
     * @param dataArea  the area within which the data should be drawn.
     * @param edge  the axis location (<code>null</code> not permitted).
     * @param plotState  collects information about the plot 
     *                   (<code>null</code> permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public abstract AxisState draw(Graphics2D g2, 
                                   double cursor,
                                   Rectangle2D plotArea, 
                                   Rectangle2D dataArea,
                                   RectangleEdge edge,
                                   PlotRenderingInfo plotState);

    /**
     * Calculates the positions of the ticks for the axis, storing the results
     * in the tick list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the area inside the axes.
     * @param edge  the edge on which the axis is located.
     * 
     * @return The list of ticks.
     */
    public abstract List refreshTicks(Graphics2D g2, 
                                      AxisState state,
                                      Rectangle2D dataArea,
                                      RectangleEdge edge);

    /**
     * Registers an object for notification of changes to the axis.
     *
     * @param listener  the object that is being registered.
     * 
     * @see #removeChangeListener(AxisChangeListener)
     */
    public void addChangeListener(AxisChangeListener listener) {
        this.listenerList.add(AxisChangeListener.class, listener);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[100]++;
    }

    /**
     * Deregisters an object for notification of changes to the axis.
     *
     * @param listener  the object to deregister.
     * 
     * @see #addChangeListener(AxisChangeListener)
     */
    public void removeChangeListener(AxisChangeListener listener) {
        this.listenerList.remove(AxisChangeListener.class, listener);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[101]++;
    }

    /**
     * Returns <code>true</code> if the specified object is registered with
     * the dataset as a listener.  Most applications won't need to call this 
     * method, it exists mainly for use by unit testing code.
     * 
     * @param listener  the listener.
     * 
     * @return A boolean.
     */
    public boolean hasListener(EventListener listener) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[102]++;
        List list = Arrays.asList(this.listenerList.getListenerList());
        return list.contains(listener);
    }
    
    /**
     * Notifies all registered listeners that the axis has changed.
     * The AxisChangeEvent provides information about the change.
     *
     * @param event  information about the change to the axis.
     */
    protected void notifyListeners(AxisChangeEvent event) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[103]++;

        Object[] listeners = this.listenerList.getListenerList();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[104]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.loops[1]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.loops[1]--;
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.loops[2]--;
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.loops[3]++;
}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[105]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((listeners[i] == AxisChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[43]++;
                ((AxisChangeListener) listeners[i + 1]).axisChanged(event);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[106]++;

            } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[44]++;}
        }

    }

    /**
     * Returns a rectangle that encloses the axis label.  This is typically 
     * used for layout purposes (it gives the maximum dimensions of the label).
     *
     * @param g2  the graphics device.
     * @param edge  the edge of the plot area along which the axis is measuring.
     *
     * @return The enclosing rectangle.
     */
    protected Rectangle2D getLabelEnclosure(Graphics2D g2, RectangleEdge edge) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[107]++;

        Rectangle2D result = new Rectangle2D.Double();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[108]++;
        String axisLabel = getLabel();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[109]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((axisLabel != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((axisLabel.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[45]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[110]++;
            FontMetrics fm = g2.getFontMetrics(getLabelFont());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[111]++;
            Rectangle2D bounds = TextUtilities.getTextBounds(axisLabel, g2, fm);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[112]++;
            RectangleInsets insets = getLabelInsets();
            bounds = insets.createOutsetRectangle(bounds);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[113]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[114]++;
            double angle = getLabelAngle();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[115]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[47]++;
                angle = angle - Math.PI / 2.0;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[116]++;

            } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[48]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[117]++;
            double x = bounds.getCenterX();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[118]++;
            double y = bounds.getCenterY();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[119]++;
            AffineTransform transformer 
                = AffineTransform.getRotateInstance(angle, x, y);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[120]++;
            Shape labelBounds = transformer.createTransformedShape(bounds);
            result = labelBounds.getBounds2D();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[121]++;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[46]++;}

        return result;

    }

    /**
     * Draws the axis label.
     *
     * @param label  the label text.
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param dataArea  the area inside the axes.
     * @param edge  the location of the axis.
     * @param state  the axis state (<code>null</code> not permitted).
     *
     * @return Information about the axis.
     */
    protected AxisState drawLabel(String label,
                                  Graphics2D g2, 
                                  Rectangle2D plotArea, 
                                  Rectangle2D dataArea,
                                  RectangleEdge edge, 
                                  AxisState state) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[122]++;
int CodeCoverConditionCoverageHelper_C26;

        // it is unlikely that 'state' will be null, but check anyway...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((state == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[49]++;
            throw new IllegalArgumentException("Null 'state' argument.");

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[50]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[123]++;
int CodeCoverConditionCoverageHelper_C27;
        
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((label.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[51]++;
            return state;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[52]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[124]++;

        Font font = getLabelFont();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[125]++;
        RectangleInsets insets = getLabelInsets();
        g2.setFont(font);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[126]++;
        g2.setPaint(getLabelPaint());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[127]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[128]++;
        FontMetrics fm = g2.getFontMetrics();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[129]++;
        Rectangle2D labelBounds = TextUtilities.getTextBounds(label, g2, fm);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[130]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[53]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[131]++;

            AffineTransform t = AffineTransform.getRotateInstance(
                    getLabelAngle(), labelBounds.getCenterX(), 
                    labelBounds.getCenterY());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[132]++;
            Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[133]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[134]++;
            double labelx = dataArea.getCenterX();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[135]++;
            double labely = state.getCursor() - insets.getBottom() 
                            - labelBounds.getHeight() / 2.0;
            TextUtilities.drawRotatedString(label, g2, (float) labelx, 
                    (float) labely, TextAnchor.CENTER, getLabelAngle(), 
                    TextAnchor.CENTER);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[136]++;
            state.cursorUp(insets.getTop() + labelBounds.getHeight() 
                    + insets.getBottom());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[137]++;


        }
        else {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[54]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[138]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[55]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[139]++;

            AffineTransform t = AffineTransform.getRotateInstance(
                    getLabelAngle(), labelBounds.getCenterX(), 
                    labelBounds.getCenterY());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[140]++;
            Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[141]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[142]++;
            double labelx = dataArea.getCenterX();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[143]++;
            double labely = state.getCursor() 
                            + insets.getTop() + labelBounds.getHeight() / 2.0;
            TextUtilities.drawRotatedString(label, g2, (float) labelx, 
                    (float) labely, TextAnchor.CENTER, getLabelAngle(), 
                    TextAnchor.CENTER);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[144]++;
            state.cursorDown(insets.getTop() + labelBounds.getHeight() 
                    + insets.getBottom());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[145]++;


        }
        else {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[56]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[146]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[57]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[147]++;

            AffineTransform t = AffineTransform.getRotateInstance(
                    getLabelAngle() - Math.PI / 2.0, labelBounds.getCenterX(), 
                    labelBounds.getCenterY());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[148]++;
            Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[149]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[150]++;
            double labelx = state.getCursor() 
                            - insets.getRight() - labelBounds.getWidth() / 2.0;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[151]++;
            double labely = dataArea.getCenterY();
            TextUtilities.drawRotatedString(label, g2, (float) labelx, 
                    (float) labely, TextAnchor.CENTER, 
                    getLabelAngle() - Math.PI / 2.0, TextAnchor.CENTER);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[152]++;
            state.cursorLeft(insets.getLeft() + labelBounds.getWidth() 
                    + insets.getRight());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[153]++;

        }
        else {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[58]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[154]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[59]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[155]++;

            AffineTransform t = AffineTransform.getRotateInstance(
                    getLabelAngle() + Math.PI / 2.0, 
                    labelBounds.getCenterX(), labelBounds.getCenterY());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[156]++;
            Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[157]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[158]++;
            double labelx = state.getCursor() 
                            + insets.getLeft() + labelBounds.getWidth() / 2.0;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[159]++;
            double labely = dataArea.getY() + dataArea.getHeight() / 2.0;
            TextUtilities.drawRotatedString(label, g2, (float) labelx, 
                    (float) labely, TextAnchor.CENTER,
                    getLabelAngle() + Math.PI / 2.0, TextAnchor.CENTER);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[160]++;
            state.cursorRight(insets.getLeft() + labelBounds.getWidth() 
                    + insets.getRight());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[161]++;


        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[60]++;}
}
}
}

        return state;

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
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[162]++;
        
        Line2D axisLine = null;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[163]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[61]++;
            axisLine = new Line2D.Double(dataArea.getX(), cursor, 
                    dataArea.getMaxX(), cursor);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[164]++;
  
        }
        else {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[62]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[165]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[63]++;
            axisLine = new Line2D.Double(dataArea.getX(), cursor, 
                    dataArea.getMaxX(), cursor);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[166]++;
  
        }
        else {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[64]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[167]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[65]++;
            axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[168]++;
  
        }
        else {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[66]++;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[169]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[67]++;
            axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[170]++;
  
        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[68]++;}
}
}
}
        g2.setPaint(this.axisLinePaint);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[171]++;
        g2.setStroke(this.axisLineStroke);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[172]++;
        g2.draw(axisLine);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[173]++;
        
    }

    /**
     * Returns a clone of the axis.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some component of the axis does 
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[174]++;
        Axis clone = (Axis) super.clone();
        // It's up to the plot which clones up to restore the correct references
        clone.plot = null;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[175]++;        
        clone.listenerList = new EventListenerList();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[176]++;
        return clone;
    }
    
    /**
     * Tests this axis for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[177]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[69]++;
            return true;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[70]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[178]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((obj instanceof Axis) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[72]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[179]++;
        Axis that = (Axis) obj;
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[180]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.visible != that.visible) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[74]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[181]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.label, that.label)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[76]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[182]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelFont, that.labelFont)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[78]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[183]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelPaint, that.labelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[80]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[184]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelInsets, that.labelInsets)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[82]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[185]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.labelAngle != that.labelAngle) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[84]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[186]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.axisLineVisible != that.axisLineVisible) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[86]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[187]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.axisLineStroke, that.axisLineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[87]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[88]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[188]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.axisLinePaint, that.axisLinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[90]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[189]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.tickLabelsVisible != that.tickLabelsVisible) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[92]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[190]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickLabelFont, that.tickLabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[93]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[94]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[191]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[95]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[96]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[192]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.tickLabelInsets, that.tickLabelInsets
        )) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[97]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[98]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[193]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.tickMarksVisible != that.tickMarksVisible) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[99]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[100]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[194]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((this.tickMarkInsideLength != that.tickMarkInsideLength) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[101]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[102]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[195]++;
int CodeCoverConditionCoverageHelper_C53;                  
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this.tickMarkOutsideLength != that.tickMarkOutsideLength) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[103]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[104]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[196]++;
int CodeCoverConditionCoverageHelper_C54;                  
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.tickMarkPaint, that.tickMarkPaint)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[105]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[106]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[197]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickMarkStroke, that.tickMarkStroke)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[107]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[108]++;}
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[198]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this.fixedDimension != that.fixedDimension) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$72vnh2d6k4hkkh.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[109]++;
            return false;

        } else {
  CodeCoverCoverageCounter$72vnh2d6k4hkkh.branches[110]++;}
        return true;
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
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[199]++;
        SerialUtilities.writePaint(this.labelPaint, stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[200]++;
        SerialUtilities.writePaint(this.tickLabelPaint, stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[201]++;
        SerialUtilities.writeStroke(this.axisLineStroke, stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[202]++;
        SerialUtilities.writePaint(this.axisLinePaint, stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[203]++;
        SerialUtilities.writeStroke(this.tickMarkStroke, stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[204]++;
        SerialUtilities.writePaint(this.tickMarkPaint, stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[205]++;
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
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[206]++;
        this.labelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[207]++;
        this.tickLabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[208]++;
        this.axisLineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[209]++;
        this.axisLinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[210]++;
        this.tickMarkStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[211]++;
        this.tickMarkPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[212]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$72vnh2d6k4hkkh.statements[213]++;
    }

}

class CodeCoverCoverageCounter$72vnh2d6k4hkkh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$72vnh2d6k4hkkh ());
  }
    public static long[] statements = new long[214];
    public static long[] branches = new long[111];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[57];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.Axis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 56; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$72vnh2d6k4hkkh () {
    super("org.jfree.chart.axis.Axis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 213; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 110; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 56; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.Axis.java");
      for (int i = 1; i <= 213; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 110; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 56; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

