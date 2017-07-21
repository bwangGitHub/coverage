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
 * ModuloAxis.java
 * ---------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 13-Aug-2004 : Version 1 (DG);
 * 13-Nov-2007 : Implemented equals() (DG);
 * 
 */

package org.jfree.chart.axis;

import java.awt.geom.Rectangle2D;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.data.Range;
import org.jfree.ui.RectangleEdge;

/**
 * An axis that displays numerical values within a fixed range using a modulo 
 * calculation.
 */
public class ModuloAxis extends NumberAxis {
  static {
    CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.ping();
  }

    
    /** 
     * The fixed range for the axis - all data values will be mapped to this
     * range using a modulo calculation. 
     */
    private Range fixedRange;
    
    /**
     * The display start value (this will sometimes be > displayEnd, in which
     * case the axis wraps around at some point in the middle of the axis).
     */
    private double displayStart;
    
    /**
     * The display end value.
     */
    private double displayEnd;
    
    /**
     * Creates a new axis.
     * 
     * @param label  the axis label (<code>null</code> permitted).
     * @param fixedRange  the fixed range (<code>null</code> not permitted).
     */
    public ModuloAxis(String label, Range fixedRange) {
        super(label);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[1]++;
        this.fixedRange = fixedRange;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[2]++;
        this.displayStart = 270.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[3]++;
        this.displayEnd = 90.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[4]++;
    }

    /**
     * Returns the display start value.
     * 
     * @return The display start value.
     */
    public double getDisplayStart() {
        return this.displayStart;
    }

    /**
     * Returns the display end value.
     * 
     * @return The display end value.
     */
    public double getDisplayEnd() {
        return this.displayEnd;
    }
    
    /**
     * Sets the display range.  The values will be mapped to the fixed range if
     * necessary.
     * 
     * @param start  the start value.
     * @param end  the end value.
     */
    public void setDisplayRange(double start, double end) {
        this.displayStart = mapValueToFixedRange(start);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[5]++;
        this.displayEnd = mapValueToFixedRange(end);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[6]++;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.displayStart < this.displayEnd) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[1]++;
            setRange(this.displayStart, this.displayEnd);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[8]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[2]++;
            setRange(this.displayStart, this.fixedRange.getUpperBound() 
                  + (this.displayEnd - this.fixedRange.getLowerBound()));
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[9]++;
        }
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[10]++;        
    }
    
    /**
     * This method should calculate a range that will show all the data values.
     * For now, it just sets the axis range to the fixedRange.
     */
    protected void autoAdjustRange() {
        setRange(this.fixedRange, false, false);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[11]++;
    }
    
    /**
     * Translates a data value to a Java2D coordinate.
     * 
     * @param value  the value.
     * @param area  the area.
     * @param edge  the edge.
     * 
     * @return A Java2D coordinate.
     */
    public double valueToJava2D(double value, Rectangle2D area, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[12]++;
        double result = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[13]++;
        double v = mapValueToFixedRange(value);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.displayStart < this.displayEnd) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[3]++;  // regular number axis
            result = trans(v, area, edge);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[15]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[4]++;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[16]++;  // displayStart > displayEnd, need to handle split
            double cutoff = (this.displayStart + this.displayEnd) / 2.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[17]++;
            double length1 = this.fixedRange.getUpperBound() 
                             - this.displayStart;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[18]++;
            double length2 = this.displayEnd - this.fixedRange.getLowerBound();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v > cutoff) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[5]++;
                result = transStart(v, area, edge, length1, length2);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[20]++;

            }
            else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[6]++;
                result = transEnd(v, area, edge, length1, length2);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[21]++;
            }
        }
        return result;
    }

    /**
     * A regular translation from a data value to a Java2D value.
     * 
     * @param value  the value.
     * @param area  the data area.
     * @param edge  the edge along which the axis lies.
     * 
     * @return The Java2D coordinate.
     */
    private double trans(double value, Rectangle2D area, RectangleEdge edge) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[22]++;
        double min = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[23]++;
        double max = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[7]++;
            min = area.getX();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[25]++;
            max = area.getX() + area.getWidth();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[26]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[8]++;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[27]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[9]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[28]++;
            max = area.getMaxY() - area.getHeight();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[29]++;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[10]++;}
}
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[11]++;
            return max - ((value - this.displayStart) 
                   / (this.displayEnd - this.displayStart)) * (max - min);

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[12]++;
            return min + ((value - this.displayStart) 
                   / (this.displayEnd - this.displayStart)) * (max - min);
        }

    }

    /**
     * Translates a data value to a Java2D value for the first section of the 
     * axis.
     * 
     * @param value  the value.
     * @param area  the data area.
     * @param edge  the edge along which the axis lies.
     * @param length1  the length of the first section.
     * @param length2  the length of the second section.
     * 
     * @return The Java2D coordinate.
     */
    private double transStart(double value, Rectangle2D area, 
                              RectangleEdge edge,
                              double length1, double length2) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[31]++;
        double min = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[32]++;
        double max = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[13]++;
            min = area.getX();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[34]++;
            max = area.getX() + area.getWidth() * length1 / (length1 + length2);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[35]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[14]++;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[36]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[15]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[37]++;
            max = area.getMaxY() - area.getHeight() * length1 
                  / (length1 + length2);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[38]++;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[16]++;}
}
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[17]++;
            return max - ((value - this.displayStart) 
                / (this.fixedRange.getUpperBound() - this.displayStart)) 
                * (max - min);

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[18]++;
            return min + ((value - this.displayStart) 
                / (this.fixedRange.getUpperBound() - this.displayStart)) 
                * (max - min);
        }

    }
    
    /**
     * Translates a data value to a Java2D value for the second section of the 
     * axis.
     * 
     * @param value  the value.
     * @param area  the data area.
     * @param edge  the edge along which the axis lies.
     * @param length1  the length of the first section.
     * @param length2  the length of the second section.
     * 
     * @return The Java2D coordinate.
     */
    private double transEnd(double value, Rectangle2D area, RectangleEdge edge,
                            double length1, double length2) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[40]++;
        double min = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[41]++;
        double max = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[19]++;
            max = area.getMaxX();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[43]++;
            min = area.getMaxX() - area.getWidth() * length2 
                  / (length1 + length2);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[44]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[20]++;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[45]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[21]++;
            max = area.getMinY();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[46]++;
            min = area.getMinY() + area.getHeight() * length2 
                  / (length1 + length2);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[47]++;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[22]++;}
}
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[23]++;
            return max - ((value - this.fixedRange.getLowerBound()) 
                    / (this.displayEnd - this.fixedRange.getLowerBound())) 
                    * (max - min);

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[24]++;
            return min + ((value - this.fixedRange.getLowerBound()) 
                    / (this.displayEnd - this.fixedRange.getLowerBound())) 
                    * (max - min);
        }

    }

    /**
     * Maps a data value into the fixed range.
     * 
     * @param value  the value.
     * 
     * @return The mapped value.
     */
    private double mapValueToFixedRange(double value) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[49]++;
        double lower = this.fixedRange.getLowerBound();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[50]++;
        double length = this.fixedRange.getLength();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value < lower) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[25]++;
            return lower + length + ((value - lower) % length);

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[26]++;
            return lower + ((value - lower) % length);
        }
    }
    
    /**
     * Translates a Java2D coordinate into a data value.
     * 
     * @param java2DValue  the Java2D coordinate.
     * @param area  the area.
     * @param edge  the edge.
     * 
     * @return The Java2D coordinate.
     */
    public double java2DToValue(double java2DValue, Rectangle2D area, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[52]++;
        double result = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.displayStart < this.displayEnd) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[27]++;  // regular number axis
            result = super.java2DToValue(java2DValue, area, edge);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[54]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[28]++;  // displayStart > displayEnd, need to handle split
            
        }
        return result;
    }
    
    /**
     * Returns the display length for the axis.
     * 
     * @return The display length.
     */
    private double getDisplayLength() {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[55]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.displayStart < this.displayEnd) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[29]++;
            return (this.displayEnd - this.displayStart);

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[30]++;
            return (this.fixedRange.getUpperBound() - this.displayStart)
                + (this.displayEnd - this.fixedRange.getLowerBound());
        }
    }
    
    /**
     * Returns the central value of the current display range.
     * 
     * @return The central value.
     */
    private double getDisplayCentralValue() {
        return mapValueToFixedRange(
            this.displayStart + (getDisplayLength() / 2)
        );    
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
     */
    public void resizeRange(double percent) {
        resizeRange(percent, getDisplayCentralValue());
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[56]++;
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
     */
    public void resizeRange(double percent, double anchorValue) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[57]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((percent > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[31]++;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[58]++;
            double halfLength = getDisplayLength() * percent / 2;
            setDisplayRange(anchorValue - halfLength, anchorValue + halfLength);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[59]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[32]++;
            setAutoRange(true);
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[60]++;
        }

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
     */
    public double lengthToJava2D(double length, Rectangle2D area, 
                                 RectangleEdge edge) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[61]++;
        double axisLength = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.displayEnd > this.displayStart) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[33]++;
            axisLength = this.displayEnd - this.displayStart;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[63]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[34]++;
            axisLength = (this.fixedRange.getUpperBound() - this.displayStart) 
                + (this.displayEnd - this.fixedRange.getLowerBound());
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[64]++;
        }
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[65]++;
        double areaLength = 0.0;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[66]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[35]++;
            areaLength = area.getHeight();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[67]++;

        }
        else {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[36]++;
            areaLength = area.getWidth();
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[68]++;
        }
        return (length / axisLength) * areaLength;
    }
    
    /**
     * Tests this axis for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[69]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[37]++;
            return true;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[38]++;}
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj instanceof ModuloAxis) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[40]++;}
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[71]++;
        ModuloAxis that = (ModuloAxis) obj;
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.displayStart != that.displayStart) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[42]++;}
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[73]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.displayEnd != that.displayEnd) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[44]++;}
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.statements[74]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.fixedRange.equals(that.fixedRange)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5.branches[46]++;}
        return super.equals(obj);
    }
    
}

class CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5 ());
  }
    public static long[] statements = new long[75];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.ModuloAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$n7hwegzffy5nzsusxlcn1b5 () {
    super("org.jfree.chart.axis.ModuloAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 74; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.ModuloAxis.java");
      for (int i = 1; i <= 74; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

