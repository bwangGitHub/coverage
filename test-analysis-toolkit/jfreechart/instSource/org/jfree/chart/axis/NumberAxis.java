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
 * ---------------
 * NumberAxis.java
 * ---------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Laurence Vanhelsuwe;
 *
 * Changes
 * -------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 22-Sep-2001 : Changed setMinimumAxisValue() and setMaximumAxisValue() so 
 *               that they clear the autoRange flag (DG);
 * 27-Nov-2001 : Removed old, redundant code (DG);
 * 30-Nov-2001 : Added accessor methods for the standard tick units (DG);
 * 08-Jan-2002 : Added setAxisRange() method (since renamed setRange()) (DG);
 * 16-Jan-2002 : Added setTickUnit() method.  Extended ValueAxis to support an 
 *               optional cross-hair (DG);
 * 08-Feb-2002 : Fixes bug to ensure the autorange is recalculated if the
 *               setAutoRangeIncludesZero flag is changed (DG);
 * 25-Feb-2002 : Added a new flag autoRangeStickyZero to provide further 
 *               control over margins in the auto-range mechanism.  Updated 
 *               constructors.  Updated import statements.  Moved the 
 *               createStandardTickUnits() method to the TickUnits class (DG);
 * 19-Apr-2002 : Updated Javadoc comments (DG);
 * 01-May-2002 : Updated for changes to TickUnit class, removed valueToString()
 *               method (DG);
 * 25-Jul-2002 : Moved the lower and upper margin attributes, and the
 *               auto-range minimum size, up one level to the ValueAxis 
 *               class (DG);
 * 05-Sep-2002 : Updated constructor to match changes in Axis class (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 04-Oct-2002 : Moved standardTickUnits from NumberAxis --> ValueAxis (DG);
 * 24-Oct-2002 : Added a number format override (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 19-Nov-2002 : Removed grid settings (now controlled by the plot) (DG);
 * 14-Jan-2003 : Changed autoRangeMinimumSize from Number --> double, and moved
 *               crosshair settings to the plot classes (DG);
 * 20-Jan-2003 : Removed the monolithic constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 16-Jul-2003 : Reworked to allow for multiple secondary axes (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 07-Oct-2003 : Fixed bug (815028) in the auto range calculation (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 07-Nov-2003 : Modified to use NumberTick class (DG);
 * 21-Jan-2004 : Renamed translateJava2DToValue --> java2DToValue, and 
 *               translateValueToJava2D --> valueToJava2D (DG); 
 * 03-Mar-2004 : Added plotState to draw() method (DG);
 * 07-Apr-2004 : Changed string width calculation (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0 
 *               release (DG);
 * 28-Mar-2005 : Renamed autoRangeIncludesZero() --> getAutoRangeIncludesZero()
 *               and autoRangeStickyZero() --> getAutoRangeStickyZero() (DG);
 * 21-Apr-2005 : Removed redundant argument from selectAutoTickUnit() (DG);
 * 22-Apr-2005 : Renamed refreshHorizontalTicks --> refreshTicksHorizontal
 *               (and likewise the vertical version) for consistency with
 *               other axis classes (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 10-Feb-2006 : Added some API doc comments in respect of bug 821046 (DG);
 * 20-Feb-2006 : Modified equals() method to check rangeType field (fixes bug
 *               1435461) (DG);
 * 04-Sep-2006 : Fix auto range calculation for the case where all data values
 *               are constant and large (see bug report 1549218) (DG);
 * 11-Dec-2006 : Fix bug in auto-tick unit selection with tick format override,
 *               see bug 1608371 (DG);
 * 22-Mar-2007 : Use new defaultAutoRange attribute (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.data.Range;
import org.jfree.data.RangeType;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;

/**
 * An axis for displaying numerical data.
 * <P>
 * If the axis is set up to automatically determine its range to fit the data,
 * you can ensure that the range includes zero (statisticians usually prefer
 * this) by setting the <code>autoRangeIncludesZero</code> flag to 
 * <code>true</code>.
 * <P>
 * The <code>NumberAxis</code> class has a mechanism for automatically 
 * selecting a tick unit that is appropriate for the current axis range.  This
 * mechanism is an adaptation of code suggested by Laurence Vanhelsuwe.
 */
public class NumberAxis extends ValueAxis implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2805933088476185789L;
  static {
    CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[1]++;
  }
    
    /** The default value for the autoRangeIncludesZero flag. */
    public static final boolean DEFAULT_AUTO_RANGE_INCLUDES_ZERO = true;
  static {
    CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[2]++;
  }

    /** The default value for the autoRangeStickyZero flag. */
    public static final boolean DEFAULT_AUTO_RANGE_STICKY_ZERO = true;
  static {
    CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[3]++;
  }

    /** The default tick unit. */
    public static final NumberTickUnit DEFAULT_TICK_UNIT = new NumberTickUnit(
            1.0, new DecimalFormat("0"));
  static {
    CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[4]++;
  }

    /** The default setting for the vertical tick labels flag. */
    public static final boolean DEFAULT_VERTICAL_TICK_LABELS = false;
  static {
    CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[5]++;
  }

    /** 
     * The range type (can be used to force the axis to display only positive
     * values or only negative values).
     */
    private RangeType rangeType;
    
    /**
     * A flag that affects the axis range when the range is determined
     * automatically.  If the auto range does NOT include zero and this flag
     * is TRUE, then the range is changed to include zero.
     */
    private boolean autoRangeIncludesZero;

    /**
     * A flag that affects the size of the margins added to the axis range when
     * the range is determined automatically.  If the value 0 falls within the
     * margin and this flag is TRUE, then the margin is truncated at zero.
     */
    private boolean autoRangeStickyZero;

    /** The tick unit for the axis. */
    private NumberTickUnit tickUnit;

    /** The override number format. */
    private NumberFormat numberFormatOverride;

    /** An optional band for marking regions on the axis. */
    private MarkerAxisBand markerBand;

    /**
     * Default constructor.
     */
    public NumberAxis() {
        this(null);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[6]++;    
    }
    
    /**
     * Constructs a number axis, using default values where necessary.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    public NumberAxis(String label) {
        super(label, NumberAxis.createStandardTickUnits());
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[7]++;
        this.rangeType = RangeType.FULL;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[8]++;
        this.autoRangeIncludesZero = DEFAULT_AUTO_RANGE_INCLUDES_ZERO;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[9]++;
        this.autoRangeStickyZero = DEFAULT_AUTO_RANGE_STICKY_ZERO;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[10]++;
        this.tickUnit = DEFAULT_TICK_UNIT;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[11]++;
        this.numberFormatOverride = null;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[12]++;
        this.markerBand = null;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[13]++;
    }
    
    /**
     * Returns the axis range type.
     * 
     * @return The axis range type (never <code>null</code>).
     * 
     * @see #setRangeType(RangeType)
     */
    public RangeType getRangeType() {
        return this.rangeType;   
    }
    
    /**
     * Sets the axis range type.
     * 
     * @param rangeType  the range type (<code>null</code> not permitted).
     * 
     * @see #getRangeType()
     */
    public void setRangeType(RangeType rangeType) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((rangeType == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[1]++;
            throw new IllegalArgumentException("Null 'rangeType' argument.");
   
        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[2]++;}
        this.rangeType = rangeType;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[15]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[16]++;
    }
    
    /**
     * Returns the flag that indicates whether or not the automatic axis range
     * (if indeed it is determined automatically) is forced to include zero.
     *
     * @return The flag.
     */
    public boolean getAutoRangeIncludesZero() {
        return this.autoRangeIncludesZero;
    }

    /**
     * Sets the flag that indicates whether or not the axis range, if 
     * automatically calculated, is forced to include zero.
     * <p>
     * If the flag is changed to <code>true</code>, the axis range is 
     * recalculated.
     * <p>
     * Any change to the flag will trigger an {@link AxisChangeEvent}.
     *
     * @param flag  the new value of the flag.
     * 
     * @see #getAutoRangeIncludesZero()
     */
    public void setAutoRangeIncludesZero(boolean flag) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.autoRangeIncludesZero != flag) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[3]++;
            this.autoRangeIncludesZero = flag;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[18]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[5]++;
                autoAdjustRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[20]++;

            } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[6]++;}
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[21]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[4]++;}
    }

    /**
     * Returns a flag that affects the auto-range when zero falls outside the
     * data range but inside the margins defined for the axis.
     *
     * @return The flag.
     * 
     * @see #setAutoRangeStickyZero(boolean)
     */
    public boolean getAutoRangeStickyZero() {
        return this.autoRangeStickyZero;
    }

    /**
     * Sets a flag that affects the auto-range when zero falls outside the data
     * range but inside the margins defined for the axis.
     *
     * @param flag  the new flag.
     * 
     * @see #getAutoRangeStickyZero()
     */
    public void setAutoRangeStickyZero(boolean flag) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.autoRangeStickyZero != flag) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[7]++;
            this.autoRangeStickyZero = flag;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[23]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[9]++;
                autoAdjustRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[25]++;

            } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[10]++;}
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[26]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[8]++;}
    }

    /**
     * Returns the tick unit for the axis.  
     * <p>
     * Note: if the <code>autoTickUnitSelection</code> flag is 
     * <code>true</code> the tick unit may be changed while the axis is being 
     * drawn, so in that case the return value from this method may be
     * irrelevant if the method is called before the axis has been drawn.
     *
     * @return The tick unit for the axis.
     * 
     * @see #setTickUnit(NumberTickUnit)
     * @see ValueAxis#isAutoTickUnitSelection()
     */
    public NumberTickUnit getTickUnit() {
        return this.tickUnit;
    }

    /**
     * Sets the tick unit for the axis and sends an {@link AxisChangeEvent} to 
     * all registered listeners.  A side effect of calling this method is that
     * the "auto-select" feature for tick units is switched off (you can 
     * restore it using the {@link ValueAxis#setAutoTickUnitSelection(boolean)}
     * method).
     *
     * @param unit  the new tick unit (<code>null</code> not permitted).
     * 
     * @see #getTickUnit()
     * @see #setTickUnit(NumberTickUnit, boolean, boolean)
     */
    public void setTickUnit(NumberTickUnit unit) {
        // defer argument checking...
        setTickUnit(unit, true, true);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[27]++;
    }

    /**
     * Sets the tick unit for the axis and, if requested, sends an 
     * {@link AxisChangeEvent} to all registered listeners.  In addition, an 
     * option is provided to turn off the "auto-select" feature for tick units 
     * (you can restore it using the 
     * {@link ValueAxis#setAutoTickUnitSelection(boolean)} method).
     *
     * @param unit  the new tick unit (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * @param turnOffAutoSelect  turn off the auto-tick selection?
     */
    public void setTickUnit(NumberTickUnit unit, boolean notify, 
                            boolean turnOffAutoSelect) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((unit == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[11]++;
            throw new IllegalArgumentException("Null 'unit' argument.");
   
        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[12]++;}
        this.tickUnit = unit;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[29]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((turnOffAutoSelect) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[13]++;
            setAutoTickUnitSelection(false, false);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[31]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[14]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[15]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[33]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[16]++;}

    }

    /**
     * Returns the number format override.  If this is non-null, then it will 
     * be used to format the numbers on the axis.
     *
     * @return The number formatter (possibly <code>null</code>).
     * 
     * @see #setNumberFormatOverride(NumberFormat)
     */
    public NumberFormat getNumberFormatOverride() {
        return this.numberFormatOverride;
    }

    /**
     * Sets the number format override.  If this is non-null, then it will be 
     * used to format the numbers on the axis.
     *
     * @param formatter  the number formatter (<code>null</code> permitted).
     * 
     * @see #getNumberFormatOverride()
     */
    public void setNumberFormatOverride(NumberFormat formatter) {
        this.numberFormatOverride = formatter;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[34]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[35]++;
    }

    /**
     * Returns the (optional) marker band for the axis.
     *
     * @return The marker band (possibly <code>null</code>).
     * 
     * @see #setMarkerBand(MarkerAxisBand)
     */
    public MarkerAxisBand getMarkerBand() {
        return this.markerBand;
    }

    /**
     * Sets the marker band for the axis.
     * <P>
     * The marker band is optional, leave it set to <code>null</code> if you 
     * don't require it.
     *
     * @param band the new band (<code>null<code> permitted).
     * 
     * @see #getMarkerBand()
     */
    public void setMarkerBand(MarkerAxisBand band) {
        this.markerBand = band;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[36]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[37]++;
    }

    /**
     * Configures the axis to work with the specified plot.  If the axis has
     * auto-scaling, then sets the maximum and minimum values.
     */
    public void configure() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[17]++;
            autoAdjustRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[39]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[18]++;}
    }

    /**
     * Rescales the axis to ensure that all data is visible.
     */
    protected void autoAdjustRange() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[40]++;

        Plot plot = getPlot();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[19]++;
            return;
  // no plot, no data
        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[20]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((plot instanceof ValueAxisPlot) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[21]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[43]++;
            ValueAxisPlot vap = (ValueAxisPlot) plot;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[44]++;

            Range r = vap.getDataRange(this);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[23]++;
                r = getDefaultAutoRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[46]++;

            } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[24]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[47]++;
            
            double upper = r.getUpperBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[48]++;
            double lower = r.getLowerBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[49]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.rangeType == RangeType.POSITIVE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[25]++;
                lower = Math.max(0.0, lower);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[50]++;
                upper = Math.max(0.0, upper);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[51]++;

            }
            else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[26]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[52]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.rangeType == RangeType.NEGATIVE) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[27]++;
                lower = Math.min(0.0, lower);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[53]++;
                upper = Math.min(0.0, upper);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[54]++;
                   
            } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[28]++;}
}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[55]++;
int CodeCoverConditionCoverageHelper_C15;
            
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getAutoRangeIncludesZero()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[29]++;
                lower = Math.min(lower, 0.0);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[56]++;
                upper = Math.max(upper, 0.0);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[57]++;

            } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[30]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[58]++;
            double range = upper - lower;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[59]++;

            // if fixed auto range, then derive lower bound...
            double fixedAutoRange = getFixedAutoRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((fixedAutoRange > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[31]++;
                lower = upper - fixedAutoRange;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[61]++;

            }
            else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[32]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[62]++;
                // ensure the autorange is at least <minRange> in size...
                double minRange = getAutoRangeMinimumSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((range < minRange) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[33]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[64]++;
                    double expand = (minRange - range) / 2;
                    upper = upper + expand;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[65]++;
                    lower = lower - expand;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[66]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;
                    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((lower == upper) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[35]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[68]++; // see bug report 1549218
                        double adjust = Math.abs(lower) / 10.0;
                        lower = lower - adjust;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[69]++;
                        upper = upper + adjust;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[70]++;

                    } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[36]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;
                    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.rangeType == RangeType.POSITIVE) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[37]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[72]++;
int CodeCoverConditionCoverageHelper_C20;
                        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((lower < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[39]++;
                            upper = upper - lower;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[73]++;
                            lower = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[74]++;

                        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[40]++;}

                    }
                    else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[38]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[75]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.rangeType == RangeType.NEGATIVE) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[41]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[76]++;
int CodeCoverConditionCoverageHelper_C22;
                        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((upper > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[43]++;
                            lower = lower - upper;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[77]++;
                            upper = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[78]++;

                        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[44]++;}

                    } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[42]++;}
}

                } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[34]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[79]++;
int CodeCoverConditionCoverageHelper_C23;

                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getAutoRangeStickyZero()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[45]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[80]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((upper <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[47]++;
                        upper = Math.min(0.0, upper + getUpperMargin() * range);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[81]++;

                    }
                    else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[48]++;
                        upper = upper + getUpperMargin() * range;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[82]++;
                    }
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[83]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((lower >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[49]++;
                        lower = Math.max(0.0, lower - getLowerMargin() * range);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[84]++;

                    }
                    else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[50]++;
                        lower = lower - getLowerMargin() * range;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[85]++;
                    }

                }
                else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[46]++;
                    upper = upper + getUpperMargin() * range;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[86]++;
                    lower = lower - getLowerMargin() * range;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[87]++;
                }
            }

            setRange(new Range(lower, upper), false, false);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[88]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[22]++;}

    }

    /**
     * Converts a data value to a coordinate in Java2D space, assuming that the
     * axis runs along one edge of the specified dataArea.
     * <p>
     * Note that it is possible for the coordinate to fall outside the plotArea.
     *
     * @param value  the data value.
     * @param area  the area for plotting the data.
     * @param edge  the axis location.
     *
     * @return The Java2D coordinate.
     * 
     * @see #java2DToValue(double, Rectangle2D, RectangleEdge)
     */
    public double valueToJava2D(double value, Rectangle2D area, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[89]++;
        
        Range range = getRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[90]++;
        double axisMin = range.getLowerBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[91]++;
        double axisMax = range.getUpperBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[92]++;

        double min = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[93]++;
        double max = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[94]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[51]++;
            min = area.getX();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[95]++;
            max = area.getMaxX();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[96]++;

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[52]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[97]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[53]++;
            max = area.getMinY();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[98]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[99]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[54]++;}
}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[100]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[55]++;
            return max 
                   - ((value - axisMin) / (axisMax - axisMin)) * (max - min);

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[56]++;
            return min 
                   + ((value - axisMin) / (axisMax - axisMin)) * (max - min);
        }

    }

    /**
     * Converts a coordinate in Java2D space to the corresponding data value,
     * assuming that the axis runs along one edge of the specified dataArea.
     *
     * @param java2DValue  the coordinate in Java2D space.
     * @param area  the area in which the data is plotted.
     * @param edge  the location.
     *
     * @return The data value.
     * 
     * @see #valueToJava2D(double, Rectangle2D, RectangleEdge)
     */
    public double java2DToValue(double java2DValue, Rectangle2D area, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[101]++;
        
        Range range = getRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[102]++;
        double axisMin = range.getLowerBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[103]++;
        double axisMax = range.getUpperBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[104]++;

        double min = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[105]++;
        double max = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[106]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[57]++;
            min = area.getX();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[107]++;
            max = area.getMaxX();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[108]++;

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[58]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[109]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[59]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[110]++;
            max = area.getY();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[111]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[60]++;}
}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[112]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[61]++;
            return axisMax 
                   - (java2DValue - min) / (max - min) * (axisMax - axisMin);

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[62]++;
            return axisMin 
                   + (java2DValue - min) / (max - min) * (axisMax - axisMin);
        }

    }

    /**
     * Calculates the value of the lowest visible tick on the axis.
     *
     * @return The value of the lowest visible tick on the axis.
     * 
     * @see #calculateHighestVisibleTickValue()
     */
    protected double calculateLowestVisibleTickValue() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[113]++;

        double unit = getTickUnit().getSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[114]++;
        double index = Math.ceil(getRange().getLowerBound() / unit);
        return index * unit;

    }

    /**
     * Calculates the value of the highest visible tick on the axis.
     *
     * @return The value of the highest visible tick on the axis.
     * 
     * @see #calculateLowestVisibleTickValue()
     */
    protected double calculateHighestVisibleTickValue() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[115]++;

        double unit = getTickUnit().getSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[116]++;
        double index = Math.floor(getRange().getUpperBound() / unit);
        return index * unit;

    }

    /**
     * Calculates the number of visible ticks.
     *
     * @return The number of visible ticks on the axis.
     */
    protected int calculateVisibleTickCount() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[117]++;

        double unit = getTickUnit().getSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[118]++;
        Range range = getRange();
        return (int) (Math.floor(range.getUpperBound() / unit)
                      - Math.ceil(range.getLowerBound() / unit) + 1);

    }

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the axes and data should be drawn
     *                  (<code>null</code> not permitted).
     * @param dataArea  the area within which the data should be drawn 
     *                  (<code>null</code> not permitted).
     * @param edge  the location of the axis (<code>null</code> not permitted).
     * @param plotState  collects information about the plot 
     *                   (<code>null</code> permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Graphics2D g2, 
                          double cursor,
                          Rectangle2D plotArea, 
                          Rectangle2D dataArea, 
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[119]++;

        AxisState state = null;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[120]++;
int CodeCoverConditionCoverageHelper_C32;
        // if the axis is not visible, don't draw it...
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[63]++;
            state = new AxisState(cursor);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[121]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[122]++;
            // even though the axis is not visible, we need ticks for the 
            // gridlines...
            List ticks = refreshTicks(g2, state, dataArea, edge); 
            state.setTicks(ticks);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[123]++;
            return state;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[64]++;}

        // draw the tick marks and labels...
        state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[124]++;

//        // draw the marker band (if there is one)...
//        if (getMarkerBand() != null) {
//            if (edge == RectangleEdge.BOTTOM) {
//                cursor = cursor - getMarkerBand().getHeight(g2);
//            }
//            getMarkerBand().draw(g2, plotArea, dataArea, 0, cursor);
//        }
        
        // draw the axis label...
        state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[125]++;

        return state;
        
    }

    /**
     * Creates the standard tick units.
     * <P>
     * If you don't like these defaults, create your own instance of TickUnits
     * and then pass it to the setStandardTickUnits() method in the
     * NumberAxis class.
     *
     * @return The standard tick units.
     * 
     * @see #setStandardTickUnits(TickUnitSource)
     * @see #createIntegerTickUnits()
     */
    public static TickUnitSource createStandardTickUnits() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[126]++;

        TickUnits units = new TickUnits();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[127]++;
        DecimalFormat df0 = new DecimalFormat("0.00000000");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[128]++;
        DecimalFormat df1 = new DecimalFormat("0.0000000");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[129]++;
        DecimalFormat df2 = new DecimalFormat("0.000000");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[130]++;
        DecimalFormat df3 = new DecimalFormat("0.00000");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[131]++;
        DecimalFormat df4 = new DecimalFormat("0.0000");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[132]++;
        DecimalFormat df5 = new DecimalFormat("0.000");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[133]++;
        DecimalFormat df6 = new DecimalFormat("0.00");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[134]++;
        DecimalFormat df7 = new DecimalFormat("0.0");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[135]++;
        DecimalFormat df8 = new DecimalFormat("#,##0");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[136]++;
        DecimalFormat df9 = new DecimalFormat("#,###,##0");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[137]++;
        DecimalFormat df10 = new DecimalFormat("#,###,###,##0");
        
        // we can add the units in any order, the TickUnits collection will 
        // sort them...
        units.add(new NumberTickUnit(0.0000001, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[138]++;
        units.add(new NumberTickUnit(0.000001, df2));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[139]++;
        units.add(new NumberTickUnit(0.00001, df3));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[140]++;
        units.add(new NumberTickUnit(0.0001, df4));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[141]++;
        units.add(new NumberTickUnit(0.001, df5));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[142]++;
        units.add(new NumberTickUnit(0.01, df6));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[143]++;
        units.add(new NumberTickUnit(0.1, df7));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[144]++;
        units.add(new NumberTickUnit(1, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[145]++;
        units.add(new NumberTickUnit(10, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[146]++;
        units.add(new NumberTickUnit(100, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[147]++;
        units.add(new NumberTickUnit(1000, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[148]++;
        units.add(new NumberTickUnit(10000, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[149]++;
        units.add(new NumberTickUnit(100000, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[150]++;
        units.add(new NumberTickUnit(1000000, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[151]++;
        units.add(new NumberTickUnit(10000000, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[152]++;
        units.add(new NumberTickUnit(100000000, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[153]++;
        units.add(new NumberTickUnit(1000000000, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[154]++;
        units.add(new NumberTickUnit(10000000000.0, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[155]++;
        units.add(new NumberTickUnit(100000000000.0, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[156]++;
        
        units.add(new NumberTickUnit(0.00000025, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[157]++;
        units.add(new NumberTickUnit(0.0000025, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[158]++;
        units.add(new NumberTickUnit(0.000025, df2));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[159]++;
        units.add(new NumberTickUnit(0.00025, df3));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[160]++;
        units.add(new NumberTickUnit(0.0025, df4));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[161]++;
        units.add(new NumberTickUnit(0.025, df5));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[162]++;
        units.add(new NumberTickUnit(0.25, df6));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[163]++;
        units.add(new NumberTickUnit(2.5, df7));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[164]++;
        units.add(new NumberTickUnit(25, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[165]++;
        units.add(new NumberTickUnit(250, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[166]++;
        units.add(new NumberTickUnit(2500, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[167]++;
        units.add(new NumberTickUnit(25000, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[168]++;
        units.add(new NumberTickUnit(250000, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[169]++;
        units.add(new NumberTickUnit(2500000, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[170]++;
        units.add(new NumberTickUnit(25000000, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[171]++;
        units.add(new NumberTickUnit(250000000, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[172]++;
        units.add(new NumberTickUnit(2500000000.0, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[173]++;
        units.add(new NumberTickUnit(25000000000.0, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[174]++;
        units.add(new NumberTickUnit(250000000000.0, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[175]++;

        units.add(new NumberTickUnit(0.0000005, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[176]++;
        units.add(new NumberTickUnit(0.000005, df2));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[177]++;
        units.add(new NumberTickUnit(0.00005, df3));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[178]++;
        units.add(new NumberTickUnit(0.0005, df4));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[179]++;
        units.add(new NumberTickUnit(0.005, df5));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[180]++;
        units.add(new NumberTickUnit(0.05, df6));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[181]++;
        units.add(new NumberTickUnit(0.5, df7));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[182]++;
        units.add(new NumberTickUnit(5L, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[183]++;
        units.add(new NumberTickUnit(50L, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[184]++;
        units.add(new NumberTickUnit(500L, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[185]++;
        units.add(new NumberTickUnit(5000L, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[186]++;
        units.add(new NumberTickUnit(50000L, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[187]++;
        units.add(new NumberTickUnit(500000L, df8));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[188]++;
        units.add(new NumberTickUnit(5000000L, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[189]++;
        units.add(new NumberTickUnit(50000000L, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[190]++;
        units.add(new NumberTickUnit(500000000L, df9));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[191]++;
        units.add(new NumberTickUnit(5000000000L, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[192]++;
        units.add(new NumberTickUnit(50000000000L, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[193]++;
        units.add(new NumberTickUnit(500000000000L, df10));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[194]++;

        return units;

    }

    /**
     * Returns a collection of tick units for integer values.
     *
     * @return A collection of tick units for integer values.
     * 
     * @see #setStandardTickUnits(TickUnitSource)
     * @see #createStandardTickUnits()
     */
    public static TickUnitSource createIntegerTickUnits() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[195]++;

        TickUnits units = new TickUnits();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[196]++;
        DecimalFormat df0 = new DecimalFormat("0");
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[197]++;
        DecimalFormat df1 = new DecimalFormat("#,##0");
        units.add(new NumberTickUnit(1, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[198]++;
        units.add(new NumberTickUnit(2, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[199]++;
        units.add(new NumberTickUnit(5, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[200]++;
        units.add(new NumberTickUnit(10, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[201]++;
        units.add(new NumberTickUnit(20, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[202]++;
        units.add(new NumberTickUnit(50, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[203]++;
        units.add(new NumberTickUnit(100, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[204]++;
        units.add(new NumberTickUnit(200, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[205]++;
        units.add(new NumberTickUnit(500, df0));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[206]++;
        units.add(new NumberTickUnit(1000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[207]++;
        units.add(new NumberTickUnit(2000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[208]++;
        units.add(new NumberTickUnit(5000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[209]++;
        units.add(new NumberTickUnit(10000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[210]++;
        units.add(new NumberTickUnit(20000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[211]++;
        units.add(new NumberTickUnit(50000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[212]++;
        units.add(new NumberTickUnit(100000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[213]++;
        units.add(new NumberTickUnit(200000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[214]++;
        units.add(new NumberTickUnit(500000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[215]++;
        units.add(new NumberTickUnit(1000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[216]++;
        units.add(new NumberTickUnit(2000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[217]++;
        units.add(new NumberTickUnit(5000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[218]++;
        units.add(new NumberTickUnit(10000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[219]++;
        units.add(new NumberTickUnit(20000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[220]++;
        units.add(new NumberTickUnit(50000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[221]++;
        units.add(new NumberTickUnit(100000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[222]++;
        units.add(new NumberTickUnit(200000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[223]++;
        units.add(new NumberTickUnit(500000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[224]++;
        units.add(new NumberTickUnit(1000000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[225]++;
        units.add(new NumberTickUnit(2000000000, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[226]++;
        units.add(new NumberTickUnit(5000000000.0, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[227]++;
        units.add(new NumberTickUnit(10000000000.0, df1));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[228]++;

        return units;

    }

    /**
     * Creates a collection of standard tick units.  The supplied locale is 
     * used to create the number formatter (a localised instance of 
     * <code>NumberFormat</code>).
     * <P>
     * If you don't like these defaults, create your own instance of 
     * {@link TickUnits} and then pass it to the 
     * <code>setStandardTickUnits()</code> method.
     *
     * @param locale  the locale.
     *
     * @return A tick unit collection.
     * 
     * @see #setStandardTickUnits(TickUnitSource)
     */
    public static TickUnitSource createStandardTickUnits(Locale locale) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[229]++;

        TickUnits units = new TickUnits();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[230]++;

        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

        // we can add the units in any order, the TickUnits collection will 
        // sort them...
        units.add(new NumberTickUnit(0.0000001,    numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[231]++;
        units.add(new NumberTickUnit(0.000001,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[232]++;
        units.add(new NumberTickUnit(0.00001,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[233]++;
        units.add(new NumberTickUnit(0.0001,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[234]++;
        units.add(new NumberTickUnit(0.001,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[235]++;
        units.add(new NumberTickUnit(0.01,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[236]++;
        units.add(new NumberTickUnit(0.1,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[237]++;
        units.add(new NumberTickUnit(1,            numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[238]++;
        units.add(new NumberTickUnit(10,           numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[239]++;
        units.add(new NumberTickUnit(100,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[240]++;
        units.add(new NumberTickUnit(1000,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[241]++;
        units.add(new NumberTickUnit(10000,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[242]++;
        units.add(new NumberTickUnit(100000,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[243]++;
        units.add(new NumberTickUnit(1000000,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[244]++;
        units.add(new NumberTickUnit(10000000,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[245]++;
        units.add(new NumberTickUnit(100000000,    numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[246]++;
        units.add(new NumberTickUnit(1000000000,   numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[247]++;
        units.add(new NumberTickUnit(10000000000.0,   numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[248]++;

        units.add(new NumberTickUnit(0.00000025,   numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[249]++;
        units.add(new NumberTickUnit(0.0000025,    numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[250]++;
        units.add(new NumberTickUnit(0.000025,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[251]++;
        units.add(new NumberTickUnit(0.00025,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[252]++;
        units.add(new NumberTickUnit(0.0025,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[253]++;
        units.add(new NumberTickUnit(0.025,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[254]++;
        units.add(new NumberTickUnit(0.25,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[255]++;
        units.add(new NumberTickUnit(2.5,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[256]++;
        units.add(new NumberTickUnit(25,           numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[257]++;
        units.add(new NumberTickUnit(250,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[258]++;
        units.add(new NumberTickUnit(2500,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[259]++;
        units.add(new NumberTickUnit(25000,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[260]++;
        units.add(new NumberTickUnit(250000,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[261]++;
        units.add(new NumberTickUnit(2500000,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[262]++;
        units.add(new NumberTickUnit(25000000,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[263]++;
        units.add(new NumberTickUnit(250000000,    numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[264]++;
        units.add(new NumberTickUnit(2500000000.0,   numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[265]++;
        units.add(new NumberTickUnit(25000000000.0,   numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[266]++;

        units.add(new NumberTickUnit(0.0000005,    numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[267]++;
        units.add(new NumberTickUnit(0.000005,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[268]++;
        units.add(new NumberTickUnit(0.00005,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[269]++;
        units.add(new NumberTickUnit(0.0005,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[270]++;
        units.add(new NumberTickUnit(0.005,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[271]++;
        units.add(new NumberTickUnit(0.05,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[272]++;
        units.add(new NumberTickUnit(0.5,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[273]++;
        units.add(new NumberTickUnit(5L,           numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[274]++;
        units.add(new NumberTickUnit(50L,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[275]++;
        units.add(new NumberTickUnit(500L,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[276]++;
        units.add(new NumberTickUnit(5000L,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[277]++;
        units.add(new NumberTickUnit(50000L,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[278]++;
        units.add(new NumberTickUnit(500000L,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[279]++;
        units.add(new NumberTickUnit(5000000L,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[280]++;
        units.add(new NumberTickUnit(50000000L,    numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[281]++;
        units.add(new NumberTickUnit(500000000L,   numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[282]++;
        units.add(new NumberTickUnit(5000000000L,  numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[283]++;
        units.add(new NumberTickUnit(50000000000L,  numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[284]++;

        return units;

    }

    /**
     * Returns a collection of tick units for integer values.
     * Uses a given Locale to create the DecimalFormats.
     *
     * @param locale the locale to use to represent Numbers.
     *
     * @return A collection of tick units for integer values.
     * 
     * @see #setStandardTickUnits(TickUnitSource)
     */
    public static TickUnitSource createIntegerTickUnits(Locale locale) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[285]++;

        TickUnits units = new TickUnits();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[286]++;

        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

        units.add(new NumberTickUnit(1,              numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[287]++;
        units.add(new NumberTickUnit(2,              numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[288]++;
        units.add(new NumberTickUnit(5,              numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[289]++;
        units.add(new NumberTickUnit(10,             numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[290]++;
        units.add(new NumberTickUnit(20,             numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[291]++;
        units.add(new NumberTickUnit(50,             numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[292]++;
        units.add(new NumberTickUnit(100,            numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[293]++;
        units.add(new NumberTickUnit(200,            numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[294]++;
        units.add(new NumberTickUnit(500,            numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[295]++;
        units.add(new NumberTickUnit(1000,           numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[296]++;
        units.add(new NumberTickUnit(2000,           numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[297]++;
        units.add(new NumberTickUnit(5000,           numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[298]++;
        units.add(new NumberTickUnit(10000,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[299]++;
        units.add(new NumberTickUnit(20000,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[300]++;
        units.add(new NumberTickUnit(50000,          numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[301]++;
        units.add(new NumberTickUnit(100000,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[302]++;
        units.add(new NumberTickUnit(200000,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[303]++;
        units.add(new NumberTickUnit(500000,         numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[304]++;
        units.add(new NumberTickUnit(1000000,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[305]++;
        units.add(new NumberTickUnit(2000000,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[306]++;
        units.add(new NumberTickUnit(5000000,        numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[307]++;
        units.add(new NumberTickUnit(10000000,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[308]++;
        units.add(new NumberTickUnit(20000000,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[309]++;
        units.add(new NumberTickUnit(50000000,       numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[310]++;
        units.add(new NumberTickUnit(100000000,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[311]++;
        units.add(new NumberTickUnit(200000000,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[312]++;
        units.add(new NumberTickUnit(500000000,      numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[313]++;
        units.add(new NumberTickUnit(1000000000,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[314]++;
        units.add(new NumberTickUnit(2000000000,     numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[315]++;
        units.add(new NumberTickUnit(5000000000.0,   numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[316]++;
        units.add(new NumberTickUnit(10000000000.0,  numberFormat));
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[317]++;

        return units;

    }

    /**
     * Estimates the maximum tick label height.
     * 
     * @param g2  the graphics device.
     * 
     * @return The maximum height.
     */
    protected double estimateMaximumTickLabelHeight(Graphics2D g2) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[318]++;

        RectangleInsets tickLabelInsets = getTickLabelInsets();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[319]++;
        double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[320]++;
        
        Font tickLabelFont = getTickLabelFont();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[321]++;
        FontRenderContext frc = g2.getFontRenderContext();
        result += tickLabelFont.getLineMetrics("123", frc).getHeight();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[322]++;
        return result;
        
    }

    /**
     * Estimates the maximum width of the tick labels, assuming the specified 
     * tick unit is used.
     * <P>
     * Rather than computing the string bounds of every tick on the axis, we 
     * just look at two values: the lower bound and the upper bound for the 
     * axis.  These two values will usually be representative.
     *
     * @param g2  the graphics device.
     * @param unit  the tick unit to use for calculation.
     *
     * @return The estimated maximum width of the tick labels.
     */
    protected double estimateMaximumTickLabelWidth(Graphics2D g2, 
                                                   TickUnit unit) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[323]++;

        RectangleInsets tickLabelInsets = getTickLabelInsets();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[324]++;
        double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[325]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[65]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[326]++;
            // all tick labels have the same width (equal to the height of the 
            // font)...
            FontRenderContext frc = g2.getFontRenderContext();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[327]++;
            LineMetrics lm = getTickLabelFont().getLineMetrics("0", frc);
            result += lm.getHeight();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[328]++;

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[66]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[329]++;
            // look at lower and upper bounds...
            FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[330]++;
            Range range = getRange();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[331]++;
            double lower = range.getLowerBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[332]++;
            double upper = range.getUpperBound();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[333]++;
            String lowerStr = "";
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[334]++;
            String upperStr = "";
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[335]++;
            NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[336]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[67]++;
                lowerStr = formatter.format(lower);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[337]++;
                upperStr = formatter.format(upper);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[338]++;

            }
            else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[68]++;
                lowerStr = unit.valueToString(lower);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[339]++;
                upperStr = unit.valueToString(upper);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[340]++;                
            }
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[341]++;
            double w1 = fm.stringWidth(lowerStr);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[342]++;
            double w2 = fm.stringWidth(upperStr);
            result += Math.max(w1, w2);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[343]++;
        }

        return result;

    }
    
    /**
     * Selects an appropriate tick value for the axis.  The strategy is to
     * display as many ticks as possible (selected from an array of 'standard'
     * tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area defined by the axes.
     * @param edge  the axis location.
     */
    protected void selectAutoTickUnit(Graphics2D g2,
                                      Rectangle2D dataArea,
                                      RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[344]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[69]++;
            selectHorizontalAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[345]++;

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[70]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[346]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[71]++;
            selectVerticalAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[347]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[72]++;}
}

    }

    /**
     * Selects an appropriate tick value for the axis.  The strategy is to
     * display as many ticks as possible (selected from an array of 'standard'
     * tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area defined by the axes.
     * @param edge  the axis location.
     */
   protected void selectHorizontalAutoTickUnit(Graphics2D g2,
                                               Rectangle2D dataArea,
                                               RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[348]++;

        double tickLabelWidth = estimateMaximumTickLabelWidth(
            g2, getTickUnit()
        );
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[349]++;

        // start with the current tick unit...
        TickUnitSource tickUnits = getStandardTickUnits();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[350]++;
        TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[351]++;
        double unit1Width = lengthToJava2D(unit1.getSize(), dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[352]++;

        // then extrapolate...
        double guess = (tickLabelWidth / unit1Width) * unit1.getSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[353]++;

        NumberTickUnit unit2 
            = (NumberTickUnit) tickUnits.getCeilingTickUnit(guess);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[354]++;
        double unit2Width = lengthToJava2D(unit2.getSize(), dataArea, edge);

        tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[355]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[356]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((tickLabelWidth > unit2Width) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[73]++;
            unit2 = (NumberTickUnit) tickUnits.getLargerTickUnit(unit2);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[357]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[74]++;}

        setTickUnit(unit2, false, false);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[358]++;

    }

    /**
     * Selects an appropriate tick value for the axis.  The strategy is to
     * display as many ticks as possible (selected from an array of 'standard'
     * tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the axis location.
     */
    protected void selectVerticalAutoTickUnit(Graphics2D g2, 
                                              Rectangle2D dataArea, 
                                              RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[359]++;

        double tickLabelHeight = estimateMaximumTickLabelHeight(g2);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[360]++;

        // start with the current tick unit...
        TickUnitSource tickUnits = getStandardTickUnits();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[361]++;
        TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[362]++;
        double unitHeight = lengthToJava2D(unit1.getSize(), dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[363]++;

        // then extrapolate...
        double guess = (tickLabelHeight / unitHeight) * unit1.getSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[364]++;
        
        NumberTickUnit unit2 
            = (NumberTickUnit) tickUnits.getCeilingTickUnit(guess);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[365]++;
        double unit2Height = lengthToJava2D(unit2.getSize(), dataArea, edge);

        tickLabelHeight = estimateMaximumTickLabelHeight(g2);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[366]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[367]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((tickLabelHeight > unit2Height) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[75]++;
            unit2 = (NumberTickUnit) tickUnits.getLargerTickUnit(unit2);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[368]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[76]++;}

        setTickUnit(unit2, false, false);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[369]++;

    }
    
    /**
     * Calculates the positions of the tick labels for the axis, storing the 
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     * 
     * @return A list of ticks.
     *
     */
    public List refreshTicks(Graphics2D g2, 
                             AxisState state,
                             Rectangle2D dataArea,
                             RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[370]++;

        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[371]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[77]++;
            result = refreshTicksHorizontal(g2, dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[372]++;

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[78]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[373]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[79]++;
            result = refreshTicksVertical(g2, dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[374]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[80]++;}
}
        return result;

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the 
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data should be drawn.
     * @param edge  the location of the axis.
     * 
     * @return A list of ticks.
     */
    protected List refreshTicksHorizontal(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[375]++;

        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[376]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[377]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[378]++;
int CodeCoverConditionCoverageHelper_C41;
        
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[81]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[379]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[82]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[380]++;

        double size = getTickUnit().getSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[381]++;
        int count = calculateVisibleTickCount();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[382]++;
        double lowestTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[383]++;
int CodeCoverConditionCoverageHelper_C42;

        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((count <= ValueAxis.MAXIMUM_TICK_COUNT) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[83]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[384]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[1]++;


int CodeCoverConditionCoverageHelper_C43;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[1]--;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[2]--;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[3]++;
}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[385]++;
                double currentTickValue = lowestTickValue + (i * size);
                String tickLabel;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[386]++;
                NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[387]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[85]++;
                    tickLabel = formatter.format(currentTickValue);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[388]++;

                }
                else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[86]++;
                    tickLabel = getTickUnit().valueToString(currentTickValue);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[389]++;
                }
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[390]++;
                TextAnchor anchor = null;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[391]++;
                TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[392]++;
                double angle = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[393]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[87]++;
                    anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[394]++;
                    rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[395]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[396]++;
int CodeCoverConditionCoverageHelper_C46;
                    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[89]++;
                        angle = Math.PI / 2.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[397]++;

                    }
                    else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[90]++;
                        angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[398]++;
                    }

                }
                else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[88]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[399]++;
int CodeCoverConditionCoverageHelper_C47;
                    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[91]++;
                        anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[400]++;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[401]++;

                    }
                    else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[92]++;
                        anchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[402]++;
                        rotationAnchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[403]++;
                    }
                }
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[404]++;

                Tick tick = new NumberTick(
                    new Double(currentTickValue), tickLabel, anchor, 
                    rotationAnchor, angle
                );
                result.add(tick);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[405]++;
            }

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[84]++;}
        return result;

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the 
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     * 
     * @return A list of ticks.
     *
     */
    protected List refreshTicksVertical(Graphics2D g2,
                                        Rectangle2D dataArea,
                                        RectangleEdge edge) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[406]++;

        List result = new java.util.ArrayList();
        result.clear();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[407]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[408]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[409]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[410]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[93]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[411]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[94]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[412]++;

        double size = getTickUnit().getSize();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[413]++;
        int count = calculateVisibleTickCount();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[414]++;
        double lowestTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[415]++;
int CodeCoverConditionCoverageHelper_C49;

        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((count <= ValueAxis.MAXIMUM_TICK_COUNT) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[95]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[416]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[4]++;


int CodeCoverConditionCoverageHelper_C50;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[4]--;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[5]--;
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.loops[6]++;
}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[417]++;
                double currentTickValue = lowestTickValue + (i * size);
                String tickLabel;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[418]++;
                NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[419]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[97]++;
                    tickLabel = formatter.format(currentTickValue);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[420]++;

                }
                else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[98]++;
                    tickLabel = getTickUnit().valueToString(currentTickValue);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[421]++;
                }
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[422]++;

                TextAnchor anchor = null;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[423]++;
                TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[424]++;
                double angle = 0.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[425]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[99]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[426]++;
int CodeCoverConditionCoverageHelper_C53;
                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[101]++; 
                        anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[427]++;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[428]++;
                        angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[429]++;

                    }
                    else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[102]++;
                        anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[430]++;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[431]++;
                        angle = Math.PI / 2.0;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[432]++;
                    }

                }
                else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[100]++;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[433]++;
int CodeCoverConditionCoverageHelper_C54;
                    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[103]++;
                        anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[434]++;
                        rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[435]++;

                    }
                    else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[104]++;
                        anchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[436]++;
                        rotationAnchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[437]++;
                    }
                }
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[438]++;

                Tick tick = new NumberTick(
                    new Double(currentTickValue), tickLabel, anchor, 
                    rotationAnchor, angle
                );
                result.add(tick);
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[439]++;
            }

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[96]++;}
        return result;

    }
    
    /**
     * Returns a clone of the axis.
     * 
     * @return A clone
     * 
     * @throws CloneNotSupportedException if some component of the axis does 
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[440]++;
        NumberAxis clone = (NumberAxis) super.clone();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[441]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.numberFormatOverride != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[105]++;
            clone.numberFormatOverride 
                = (NumberFormat) this.numberFormatOverride.clone();
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[442]++;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[106]++;}
        return clone;
    }

    /**
     * Tests the axis for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */    
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[443]++;
int CodeCoverConditionCoverageHelper_C56;           
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[107]++;
            return true;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[108]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[444]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((obj instanceof NumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[109]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[110]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[445]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[111]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[112]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[446]++;
        NumberAxis that = (NumberAxis) obj;
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[447]++;
int CodeCoverConditionCoverageHelper_C59;        
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((this.autoRangeIncludesZero != that.autoRangeIncludesZero) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[114]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[448]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.autoRangeStickyZero != that.autoRangeStickyZero) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[115]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[116]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[449]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickUnit, that.tickUnit)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[117]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[118]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[450]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.numberFormatOverride, 
                that.numberFormatOverride)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[119]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[120]++;}
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[451]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.rangeType.equals(that.rangeType)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[121]++;
            return false;

        } else {
  CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[122]++;}
        return true; 
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.statements[452]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((getLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[123]++;
            return getLabel().hashCode();

        }
        else {
CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld.branches[124]++;
            return 0;
        }
    }

}

class CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld ());
  }
    public static long[] statements = new long[453];
    public static long[] branches = new long[125];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[65];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.NumberAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 64; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$nijhezzg6hz5ukrtigd3qld () {
    super("org.jfree.chart.axis.NumberAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 452; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 124; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 64; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.NumberAxis.java");
      for (int i = 1; i <= 452; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 124; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 64; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

