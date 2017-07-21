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
 * ------------
 * LogAxis.java
 * ------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 24-Aug-2006 : Version 1 (DG);
 * 22-Mar-2007 : Use defaultAutoArrange attribute (DG);
 * 02-Aug-2007 : Fixed zooming bug, added support for margins (DG);
 * 
 */

package org.jfree.chart.axis;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.data.Range;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

/**
 * A numerical axis that uses a logarithmic scale.  The class is an 
 * alternative to the {@link LogarithmicAxis} class.
 * 
 * @since 1.0.7
 */
public class LogAxis extends ValueAxis {
  static {
    CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.ping();
  }


    /** The logarithm base. */
    private double base = 10.0;
  {
    CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[1]++;
  }
    
    /** The logarithm of the base value - cached for performance. */
    private double baseLog = Math.log(10.0);
  {
    CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[2]++;
  }
    
    /**  The smallest value permitted on the axis. */
    private double smallestValue = 1E-100;
  {
    CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[3]++;
  }
    
    /** The current tick unit. */
    private NumberTickUnit tickUnit;
    
    /** The override number format. */
    private NumberFormat numberFormatOverride;

    /** The number of minor ticks per major tick unit. */
    private int minorTickCount; 
    
    /**
     * Creates a new <code>LogAxis</code> with no label.
     */
    public LogAxis() {
        this(null);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[4]++;    
    }
    
    /**
     * Creates a new <code>LogAxis</code> with the given label.
     * 
     * @param label  the axis label (<code>null</code> permitted).
     */
    public LogAxis(String label) {
        super(label,  createLogTickUnits(Locale.getDefault()));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[5]++;
        setDefaultAutoRange(new Range(0.01, 1.0));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[6]++;
        this.tickUnit = new NumberTickUnit(1.0, new DecimalFormat("0.#"));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[7]++;
        this.minorTickCount = 10;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[8]++;
    }
    
    /**
     * Returns the base for the logarithm calculation.
     * 
     * @return The base for the logarithm calculation.
     * 
     * @see #setBase(double)
     */
    public double getBase() {
        return this.base;
    }
    
    /**
     * Sets the base for the logarithm calculation and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param base  the base value (must be > 1.0).
     * 
     * @see #getBase()
     */
    public void setBase(double base) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((base <= 1.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[1]++;
            throw new IllegalArgumentException("Requires 'base' > 1.0.");

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[2]++;}
        this.base = base;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[10]++;
        this.baseLog = Math.log(base);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[11]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[12]++;
    }
    
    /**
     * Returns the smallest value represented by the axis.
     * 
     * @return The smallest value represented by the axis.
     * 
     * @see #setSmallestValue(double)
     */
    public double getSmallestValue() {
        return this.smallestValue;
    }
    
    /**
     * Sets the smallest value represented by the axis and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param value  the value.
     * 
     * @see #getSmallestValue()
     */
    public void setSmallestValue(double value) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[3]++;
            throw new IllegalArgumentException("Requires 'value' > 0.0.");

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[4]++;}
        this.smallestValue = value;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[14]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[15]++;
    }
    
    /**
     * Returns the current tick unit.
     * 
     * @return The current tick unit.
     * 
     * @see #setTickUnit(NumberTickUnit)
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
     */
    public void setTickUnit(NumberTickUnit unit) {
        // defer argument checking...
        setTickUnit(unit, true, true);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[16]++;
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
     * 
     * @see #getTickUnit()
     */
    public void setTickUnit(NumberTickUnit unit, boolean notify, 
                            boolean turnOffAutoSelect) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((unit == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[5]++;
            throw new IllegalArgumentException("Null 'unit' argument.");
   
        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[6]++;}
        this.tickUnit = unit;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[18]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((turnOffAutoSelect) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[7]++;
            setAutoTickUnitSelection(false, false);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[20]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[8]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[9]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[22]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[10]++;}

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
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[23]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[24]++;
    }

    /**
     * Returns the number of minor tick marks to display.
     * 
     * @return The number of minor tick marks to display.
     * 
     * @see #setMinorTickCount(int)
     */
    public int getMinorTickCount() {
        return this.minorTickCount;
    }
    
    /**
     * Sets the number of minor tick marks to display, and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param count  the count.
     * 
     * @see #getMinorTickCount()
     */
    public void setMinorTickCount(int count) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((count <= 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[11]++;
            throw new IllegalArgumentException("Requires 'count' > 0.");

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[12]++;}
        this.minorTickCount = count;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[26]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[27]++;
    }
    
    /**
     * Calculates the log of the given value, using the current base.
     * 
     * @param value  the value.
     * 
     * @return The log of the given value.
     * 
     * @see #calculateValue(double)
     * @see #getBase()
     */
    public double calculateLog(double value) {
        return Math.log(value) / this.baseLog;  
    }
    
    /**
     * Calculates the value from a given log.
     * 
     * @param log  the log value (must be > 0.0).
     * 
     * @return The value with the given log.
     * 
     * @see #calculateLog(double)
     * @see #getBase()
     */
    public double calculateValue(double log) {
        return Math.pow(this.base, log);
    }
    
    /**
     * Converts a Java2D coordinate to an axis value, assuming that the
     * axis covers the specified <code>edge</code> of the <code>area</code>.
     * 
     * @param java2DValue  the Java2D coordinate.
     * @param area  the area.
     * @param edge  the edge that the axis belongs to.
     * 
     * @return A value along the axis scale.
     */
    public double java2DToValue(double java2DValue, Rectangle2D area, 
            RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[28]++;
        
        Range range = getRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[29]++;
        double axisMin = calculateLog(range.getLowerBound());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[30]++;
        double axisMax = calculateLog(range.getUpperBound());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[31]++;

        double min = 0.0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[32]++;
        double max = 0.0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[13]++;
            min = area.getX();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[34]++;
            max = area.getMaxX();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[35]++;

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[14]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[36]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[15]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[37]++;
            max = area.getY();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[38]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[16]++;}
}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[39]++;
        double log = 0.0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[17]++;
            log = axisMax - (java2DValue - min) / (max - min) 
                    * (axisMax - axisMin);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[41]++;

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[18]++;
            log = axisMin + (java2DValue - min) / (max - min) 
                    * (axisMax - axisMin);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[42]++;
        }
        return calculateValue(log);
    }

    /**
     * Converts a value on the axis scale to a Java2D coordinate relative to 
     * the given <code>area</code>, based on the axis running along the 
     * specified <code>edge</code>.
     * 
     * @param value  the data value.
     * @param area  the area.
     * @param edge  the edge.
     * 
     * @return The Java2D coordinate corresponding to <code>value</code>.
     */
    public double valueToJava2D(double value, Rectangle2D area, 
            RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[43]++;
        
        Range range = getRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[44]++;
        double axisMin = calculateLog(range.getLowerBound());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[45]++;
        double axisMax = calculateLog(range.getUpperBound());
        value = calculateLog(value);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[46]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[47]++;
        
        double min = 0.0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[48]++;
        double max = 0.0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[49]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[19]++;
            min = area.getX();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[50]++;
            max = area.getMaxX();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[51]++;

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[20]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[52]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[21]++;
            max = area.getMinY();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[53]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[54]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[22]++;}
}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[55]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[23]++;
            return max 
                   - ((value - axisMin) / (axisMax - axisMin)) * (max - min);

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[24]++;
            return min 
                   + ((value - axisMin) / (axisMax - axisMin)) * (max - min);
        }
    }
    
    /**
     * Configures the axis.  This method is typically called when an axis
     * is assigned to a new plot.
     */
    public void configure() {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[56]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[25]++;
            autoAdjustRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[57]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[26]++;}
    }

    /**
     * Adjusts the axis range to match the data range that the axis is
     * required to display.
     */
    protected void autoAdjustRange() {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[58]++;
        Plot plot = getPlot();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[59]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[27]++;
            return;
  // no plot, no data
        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[28]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[60]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((plot instanceof ValueAxisPlot) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[29]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[61]++;
            ValueAxisPlot vap = (ValueAxisPlot) plot;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[62]++;

            Range r = vap.getDataRange(this);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[63]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[31]++;
                r = getDefaultAutoRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[64]++;

            } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[32]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[65]++;
            
            double upper = r.getUpperBound();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[66]++;
            double lower = Math.max(r.getLowerBound(), this.smallestValue);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[67]++;
            double range = upper - lower;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[68]++;

            // if fixed auto range, then derive lower bound...
            double fixedAutoRange = getFixedAutoRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((fixedAutoRange > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[33]++;
                lower = Math.max(upper - fixedAutoRange, this.smallestValue);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[70]++;

            }
            else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[34]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[71]++;
                // ensure the autorange is at least <minRange> in size...
                double minRange = getAutoRangeMinimumSize();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((range < minRange) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[35]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[73]++;
                    double expand = (minRange - range) / 2;
                    upper = upper + expand;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[74]++;
                    lower = lower - expand;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[75]++;

                } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[36]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[76]++;

                // apply the margins - these should apply to the exponent range
                double logUpper = calculateLog(upper);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[77]++;
                double logLower = calculateLog(lower);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[78]++;
                double logRange = logUpper - logLower;
                logUpper = logUpper + getUpperMargin() * logRange;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[79]++;
                logLower = logLower - getLowerMargin() * logRange;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[80]++;
                upper = calculateValue(logUpper);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[81]++;
                lower = calculateValue(logLower);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[82]++;
            }

            setRange(new Range(lower, upper), false, false);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[83]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[30]++;}

    }

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
    public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, 
            Rectangle2D dataArea, RectangleEdge edge, 
            PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[84]++;
        
        AxisState state = null;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[85]++;
int CodeCoverConditionCoverageHelper_C19;
        // if the axis is not visible, don't draw it...
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[37]++;
            state = new AxisState(cursor);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[86]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[87]++;
            // even though the axis is not visible, we need ticks for the 
            // gridlines...
            List ticks = refreshTicks(g2, state, dataArea, edge); 
            state.setTicks(ticks);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[88]++;
            return state;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[38]++;}
        state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[89]++;
        state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[90]++;
        return state;
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
    public List refreshTicks(Graphics2D g2, AxisState state, 
            Rectangle2D dataArea, RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[91]++;

        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[92]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[39]++;
            result = refreshTicksHorizontal(g2, dataArea, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[93]++;

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[40]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[94]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[41]++;
            result = refreshTicksVertical(g2, dataArea, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[95]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[42]++;}
}
        return result;

    }

    /**
     * Returns a list of ticks for an axis at the top or bottom of the chart.
     * 
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param edge  the edge.
     * 
     * @return A list of ticks.
     */
    protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, 
            RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[96]++;
        
        Range range = getRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[97]++;
        List ticks = new ArrayList();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[98]++;
        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[99]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[100]++;
int CodeCoverConditionCoverageHelper_C22;
        
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[43]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[101]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[44]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[102]++;
        double start = Math.floor(calculateLog(getLowerBound()));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[103]++;
        double end = Math.ceil(calculateLog(getUpperBound()));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[104]++;
        double current = start;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[105]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[1]++;


int CodeCoverConditionCoverageHelper_C23;
        while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((current <= end) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[1]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[2]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[3]++;
}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[106]++;
            double v = calculateValue(current);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[107]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((range.contains(v)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[45]++;
                ticks.add(new NumberTick(TickType.MAJOR, v, createTickLabel(v), 
                        TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[108]++;

            } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[46]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[109]++;
            // add minor ticks (for gridlines)
            double next = Math.pow(this.base, current 
                    + this.tickUnit.getSize());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[110]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[4]++;


int CodeCoverConditionCoverageHelper_C25;
            for (int i = 1;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i < this.minorTickCount) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[4]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[5]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[6]++;
}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[111]++;
                double minorV = v + i * ((next - v) / this.minorTickCount);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[112]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((range.contains(minorV)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[47]++;
                    ticks.add(new NumberTick(TickType.MINOR, minorV, 
                        "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[113]++;

                } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[48]++;}
            }
            current = current + this.tickUnit.getSize();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[114]++;
        }
        return ticks;
    }
    
    /**
     * Returns a list of ticks for an axis at the left or right of the chart.
     * 
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param edge  the edge.
     * 
     * @return A list of ticks.
     */
    protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, 
            RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[115]++;
        
        Range range = getRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[116]++;
        List ticks = new ArrayList();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[117]++;
        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[118]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[119]++;
int CodeCoverConditionCoverageHelper_C27;
        
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[49]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[120]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[50]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[121]++;
        double start = Math.floor(calculateLog(getLowerBound()));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[122]++;
        double end = Math.ceil(calculateLog(getUpperBound()));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[123]++;
        double current = start;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[124]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[7]++;


int CodeCoverConditionCoverageHelper_C28;
        while ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((current <= end) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[7]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[8]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[9]++;
}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[125]++;
            double v = calculateValue(current);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[126]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((range.contains(v)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[51]++;
                ticks.add(new NumberTick(TickType.MINOR, v, createTickLabel(v), 
                        TextAnchor.CENTER_RIGHT, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[127]++;

            } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[52]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[128]++;
            // add minor ticks (for gridlines)
            double next = Math.pow(this.base, current 
                    + this.tickUnit.getSize());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[129]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[10]++;


int CodeCoverConditionCoverageHelper_C30;
            for (int i = 1;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i < this.minorTickCount) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[10]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[11]--;
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.loops[12]++;
}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[130]++;
                double minorV = v + i * ((next - v) / this.minorTickCount);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[131]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((range.contains(minorV)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[53]++;
                    ticks.add(new NumberTick(TickType.MINOR, minorV, "", 
                            TextAnchor.CENTER_RIGHT, TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[132]++;

                } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[54]++;}
            }
            current = current + this.tickUnit.getSize();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[133]++;
        }
        return ticks;
    }
    
    /**
     * Selects an appropriate tick value for the axis.  The strategy is to
     * display as many ticks as possible (selected from an array of 'standard'
     * tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area defined by the axes.
     * @param edge  the axis location.
     *
     * @since 1.0.7
     */
    protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea,
            RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[134]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[55]++;
            selectHorizontalAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[135]++;

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[56]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[136]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[57]++;
            selectVerticalAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[137]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[58]++;}
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
     *
     * @since 1.0.7
     */
   protected void selectHorizontalAutoTickUnit(Graphics2D g2, 
           Rectangle2D dataArea, RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[138]++;

        double tickLabelWidth = estimateMaximumTickLabelWidth(g2, 
                getTickUnit());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[139]++;

        // start with the current tick unit...
        TickUnitSource tickUnits = getStandardTickUnits();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[140]++;
        TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[141]++;
        double unit1Width = exponentLengthToJava2D(unit1.getSize(), dataArea, 
                edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[142]++;

        // then extrapolate...
        double guess = (tickLabelWidth / unit1Width) * unit1.getSize();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[143]++;

        NumberTickUnit unit2 = (NumberTickUnit) 
                tickUnits.getCeilingTickUnit(guess);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[144]++;
        double unit2Width = exponentLengthToJava2D(unit2.getSize(), dataArea, 
                edge);

        tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[145]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[146]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((tickLabelWidth > unit2Width) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[59]++;
            unit2 = (NumberTickUnit) tickUnits.getLargerTickUnit(unit2);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[147]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[60]++;}

        setTickUnit(unit2, false, false);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[148]++;

    }
   
    /**
     * Converts a length in data coordinates into the corresponding length in 
     * Java2D coordinates.
     * 
     * @param length  the length.
     * @param area  the plot area.
     * @param edge  the edge along which the axis lies.
     * 
     * @return The length in Java2D coordinates.
     *
     * @since 1.0.7
     */
    public double exponentLengthToJava2D(double length, Rectangle2D area, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[149]++;
        double one = valueToJava2D(calculateValue(1.0), area, edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[150]++;
        double l = valueToJava2D(calculateValue(length + 1.0), area, edge);
        return Math.abs(l - one);
    }

    /**
     * Selects an appropriate tick value for the axis.  The strategy is to
     * display as many ticks as possible (selected from an array of 'standard'
     * tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the axis location.
     *
     * @since 1.0.7
     */
    protected void selectVerticalAutoTickUnit(Graphics2D g2, 
                                              Rectangle2D dataArea, 
                                              RectangleEdge edge) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[151]++;

        double tickLabelHeight = estimateMaximumTickLabelHeight(g2);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[152]++;

        // start with the current tick unit...
        TickUnitSource tickUnits = getStandardTickUnits();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[153]++;
        TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[154]++;
        double unitHeight = exponentLengthToJava2D(unit1.getSize(), dataArea, 
                edge);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[155]++;

        // then extrapolate...
        double guess = (tickLabelHeight / unitHeight) * unit1.getSize();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[156]++;
        
        NumberTickUnit unit2 = (NumberTickUnit) 
                tickUnits.getCeilingTickUnit(guess);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[157]++;
        double unit2Height = exponentLengthToJava2D(unit2.getSize(), dataArea, 
                edge);

        tickLabelHeight = estimateMaximumTickLabelHeight(g2);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[158]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[159]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((tickLabelHeight > unit2Height) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[61]++;
            unit2 = (NumberTickUnit) tickUnits.getLargerTickUnit(unit2);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[160]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[62]++;}

        setTickUnit(unit2, false, false);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[161]++;

    }

    /**
     * Estimates the maximum tick label height.
     * 
     * @param g2  the graphics device.
     * 
     * @return The maximum height.
     *
     * @since 1.0.7
     */
    protected double estimateMaximumTickLabelHeight(Graphics2D g2) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[162]++;

        RectangleInsets tickLabelInsets = getTickLabelInsets();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[163]++;
        double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[164]++;
        
        Font tickLabelFont = getTickLabelFont();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[165]++;
        FontRenderContext frc = g2.getFontRenderContext();
        result += tickLabelFont.getLineMetrics("123", frc).getHeight();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[166]++;
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
     *
     * @since 1.0.7
     */
    protected double estimateMaximumTickLabelWidth(Graphics2D g2, 
                                                   TickUnit unit) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[167]++;

        RectangleInsets tickLabelInsets = getTickLabelInsets();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[168]++;
        double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[169]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[63]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[170]++;
            // all tick labels have the same width (equal to the height of the 
            // font)...
            FontRenderContext frc = g2.getFontRenderContext();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[171]++;
            LineMetrics lm = getTickLabelFont().getLineMetrics("0", frc);
            result += lm.getHeight();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[172]++;

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[64]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[173]++;
            // look at lower and upper bounds...
            FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[174]++;
            Range range = getRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[175]++;
            double lower = range.getLowerBound();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[176]++;
            double upper = range.getUpperBound();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[177]++;
            String lowerStr = "";
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[178]++;
            String upperStr = "";
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[179]++;
            NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[180]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[65]++;
                lowerStr = formatter.format(lower);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[181]++;
                upperStr = formatter.format(upper);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[182]++;

            }
            else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[66]++;
                lowerStr = unit.valueToString(lower);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[183]++;
                upperStr = unit.valueToString(upper);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[184]++;                
            }
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[185]++;
            double w1 = fm.stringWidth(lowerStr);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[186]++;
            double w2 = fm.stringWidth(upperStr);
            result += Math.max(w1, w2);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[187]++;
        }

        return result;

    }
    
    /**
     * Zooms in on the current range.
     * 
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     */
    public void zoomRange(double lowerPercent, double upperPercent) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[188]++;
        Range range = getRange();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[189]++;
        double start = range.getLowerBound();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[190]++;
        double end = range.getUpperBound();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[191]++;
        double log1 = calculateLog(start);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[192]++;
        double log2 = calculateLog(end);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[193]++;
        double length = log2 - log1;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[194]++;
        Range adjusted = null;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[195]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[67]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[196]++;
            double logA = log1 + length * (1 - upperPercent);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[197]++;
            double logB = log1 + length * (1 - lowerPercent);
            adjusted = new Range(calculateValue(logA), calculateValue(logB));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[198]++;
 
        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[68]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[199]++;
            double logA = log1 + length * lowerPercent;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[200]++;
            double logB = log1 + length * upperPercent;
            adjusted = new Range(calculateValue(logA), calculateValue(logB));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[201]++; 
        }
        setRange(adjusted);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[202]++;
    }

    /**
     * Creates a tick label for the specified value.
     * 
     * @param value  the value.
     * 
     * @return The label.
     */
    private String createTickLabel(double value) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[203]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.numberFormatOverride != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[69]++;
            return this.numberFormatOverride.format(value);

        }
        else {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[70]++;
            return this.tickUnit.valueToString(value);
        }
    }
    
    /**
     * Tests this axis for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[204]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[71]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[72]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[205]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((obj instanceof LogAxis) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[74]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[206]++;
        LogAxis that = (LogAxis) obj;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[207]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.base != that.base) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[76]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[208]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.smallestValue != that.smallestValue) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[78]++;}
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[209]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.minorTickCount != that.minorTickCount) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[80]++;}
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[210]++;
        int result = 193;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[211]++;
        long temp = Double.doubleToLongBits(this.base);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[212]++;
        result = 37 * result + this.minorTickCount;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[213]++;
        temp = Double.doubleToLongBits(this.smallestValue);
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[214]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[215]++;
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[216]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.numberFormatOverride != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[81]++;
            result = 37 * result + this.numberFormatOverride.hashCode();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[217]++;

        } else {
  CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.branches[82]++;}
        result = 37 * result + this.tickUnit.hashCode();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[218]++;
        return result; 
    }
    
    /**
     * Returns a collection of tick units for log (base 10) values.
     * Uses a given Locale to create the DecimalFormats.
     *
     * @param locale the locale to use to represent Numbers.
     *
     * @return A collection of tick units for integer values.
     *
     * @since 1.0.7
     */
    public static TickUnitSource createLogTickUnits(Locale locale) {
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[219]++;

        TickUnits units = new TickUnits();
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[220]++;

        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

        units.add(new NumberTickUnit(1, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[221]++;
        units.add(new NumberTickUnit(2, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[222]++;
        units.add(new NumberTickUnit(5, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[223]++;
        units.add(new NumberTickUnit(10, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[224]++;
        units.add(new NumberTickUnit(20, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[225]++;
        units.add(new NumberTickUnit(50, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[226]++;
        units.add(new NumberTickUnit(100, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[227]++;
        units.add(new NumberTickUnit(200, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[228]++;
        units.add(new NumberTickUnit(500, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[229]++;
        units.add(new NumberTickUnit(1000, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[230]++;
        units.add(new NumberTickUnit(2000, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[231]++;
        units.add(new NumberTickUnit(5000, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[232]++;
        units.add(new NumberTickUnit(10000, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[233]++;
        units.add(new NumberTickUnit(20000, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[234]++;
        units.add(new NumberTickUnit(50000, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[235]++;
        units.add(new NumberTickUnit(100000, numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[236]++;
        units.add(new NumberTickUnit(200000,         numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[237]++;
        units.add(new NumberTickUnit(500000,         numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[238]++;
        units.add(new NumberTickUnit(1000000,        numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[239]++;
        units.add(new NumberTickUnit(2000000,        numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[240]++;
        units.add(new NumberTickUnit(5000000,        numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[241]++;
        units.add(new NumberTickUnit(10000000,       numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[242]++;
        units.add(new NumberTickUnit(20000000,       numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[243]++;
        units.add(new NumberTickUnit(50000000,       numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[244]++;
        units.add(new NumberTickUnit(100000000,      numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[245]++;
        units.add(new NumberTickUnit(200000000,      numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[246]++;
        units.add(new NumberTickUnit(500000000,      numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[247]++;
        units.add(new NumberTickUnit(1000000000,     numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[248]++;
        units.add(new NumberTickUnit(2000000000,     numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[249]++;
        units.add(new NumberTickUnit(5000000000.0,   numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[250]++;
        units.add(new NumberTickUnit(10000000000.0,  numberFormat));
CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp.statements[251]++;

        return units;

    }
}

class CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp ());
  }
    public static long[] statements = new long[252];
    public static long[] branches = new long[83];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[46];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.LogAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 45; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$2akaujkdt27m2bu6wyp () {
    super("org.jfree.chart.axis.LogAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 251; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 82; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.LogAxis.java");
      for (int i = 1; i <= 251; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 82; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

