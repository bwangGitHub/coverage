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
 * ----------
 * Range.java
 * ----------
 * (C) Copyright 2002-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Chuanhao Chiu;
 *                   Bill Kelemen; 
 *                   Nicolas Brodu;
 *
 * Changes (from 23-Jun-2001)
 * --------------------------
 * 22-Apr-2002 : Version 1, loosely based by code by Bill Kelemen (DG);
 * 30-Apr-2002 : Added getLength() and getCentralValue() methods.  Changed
 *               argument check in constructor (DG);
 * 13-Jun-2002 : Added contains(double) method (DG);
 * 22-Aug-2002 : Added fix to combine method where both ranges are null, thanks
 *               to Chuanhao Chiu for reporting and fixing this (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 14-Aug-2003 : Added equals() method (DG);
 * 27-Aug-2003 : Added toString() method (BK);
 * 11-Sep-2003 : Added Clone Support (NB);
 * 23-Sep-2003 : Fixed Checkstyle issues (DG);
 * 25-Sep-2003 : Oops, Range immutable, clone not necessary (NB);
 * 05-May-2004 : Added constrain() and intersects() methods (DG);
 * 18-May-2004 : Added expand() method (DG);
 * ------------- JFreeChart 1.0.x ---------------------------------------------
 * 11-Jan-2006 : Added new method expandToInclude(Range, double) (DG);
 * 
 */

package org.jfree.data;

import java.io.Serializable;

/**
 * Represents an immutable range of values.
 */
public strictfp class Range implements Serializable {
  static {
    CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -906333695431863380L;
  static {
    CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[1]++;
  }
    
    /** The lower bound of the range. */
    private double lower;

    /** The upper bound of the range. */
    private double upper;

    /**
     * Creates a new range.
     *
     * @param lower  the lower bound (must be <= upper bound).
     * @param upper  the upper bound (must be >= lower bound).
     */
    public Range(double lower, double upper) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((lower > upper) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[1]++;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[3]++;
            String msg = "Range(double, double): require lower (" + lower 
                + ") <= upper (" + upper + ").";
            throw new IllegalArgumentException(msg);

        } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[2]++;}
        this.lower = lower;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[4]++;
        this.upper = upper;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[5]++;
    }

    /**
     * Returns the lower bound for the range.
     *
     * @return The lower bound.
     */
    public double getLowerBound() {
        return this.lower;
    }

    /**
     * Returns the upper bound for the range.
     *
     * @return The upper bound.
     */
    public double getUpperBound() {
        return this.upper;
    }

    /**
     * Returns the length of the range.
     *
     * @return The length.
     */
    public double getLength() {
        return this.upper - this.lower;
    }

    /**
     * Returns the central value for the range.
     *
     * @return The central value.
     */
    public double getCentralValue() {
        return this.lower / 2.0 + this.upper / 2.0;
    }

    /**
     * Returns <code>true</code> if the range contains the specified value and 
     * <code>false</code> otherwise.
     *
     * @param value  the value to lookup.
     *
     * @return <code>true</code> if the range contains the specified value.
     */
    public boolean contains(double value) {
        return (value >= this.lower && value <= this.upper);
    }
    
    /**
     * Returns <code>true</code> if the range intersects with the specified 
     * range, and <code>false</code> otherwise.
     * 
     * @param b0  the lower bound (should be <= b1).
     * @param b1  the upper bound (should be >= b0).
     * 
     * @return A boolean.
     */
    public boolean intersects(double b0, double b1) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((b0 <= this.lower) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[3]++;
            return (b1 > this.lower);

        }
        else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[4]++;
            return (b0 < this.upper && b1 >= b0);
        }
    }

    /**
     * Returns the value within the range that is closest to the specified 
     * value.
     * 
     * @param value  the value.
     * 
     * @return The constrained value.
     */
    public double constrain(double value) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[7]++;
        double result = value;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[5]++;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value > this.upper) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[7]++;
                result = this.upper;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[10]++;
   
            }
            else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[8]++;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[11]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value < this.lower) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[9]++;
                result = this.lower;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[12]++;
   
            } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[10]++;}
}

        } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[6]++;}
        return result;
    }
    
    /**
     * Creates a new range by combining two existing ranges.
     * <P>
     * Note that:
     * <ul>
     *   <li>either range can be <code>null</code>, in which case the other 
     *       range is returned;</li>
     *   <li>if both ranges are <code>null</code> the return value is 
     *       <code>null</code>.</li>
     * </ul>
     *
     * @param range1  the first range (<code>null</code> permitted).
     * @param range2  the second range (<code>null</code> permitted).
     *
     * @return A new range (possibly <code>null</code>).
     */
    public static Range combine(Range range1, Range range2) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((range1 == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[11]++;
            return range2;

        }
        else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[12]++;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((range2 == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[13]++;
                return range1;

            }
            else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[14]++;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[15]++;
                double l = Math.min(range1.getLowerBound(), 
                        range2.getLowerBound());
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[16]++;
                double u = Math.max(range1.getUpperBound(), 
                        range2.getUpperBound());
                return new Range(l, u);
            }
        }
    }
    
    /**
     * Returns a range that includes all the values in the specified 
     * <code>range</code> AND the specified <code>value</code>.
     * 
     * @param range  the range (<code>null</code> permitted).
     * @param value  the value that must be included.
     * 
     * @return A range.
     * 
     * @since 1.0.1
     */
    public static Range expandToInclude(Range range, double value) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[17]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[15]++;
            return new Range(value, value);

        } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[16]++;}
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[18]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value < range.getLowerBound()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[17]++;
            return new Range(value, range.getUpperBound());

        }
        else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[18]++;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[19]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value > range.getUpperBound()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[19]++;
            return new Range(range.getLowerBound(), value);

        }
        else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[20]++;
            return range;
        }
}
    }
    
    /**
     * Creates a new range by adding margins to an existing range.
     * 
     * @param range  the range (<code>null</code> not permitted).
     * @param lowerMargin  the lower margin (expressed as a percentage of the 
     *                     range length).
     * @param upperMargin  the upper margin (expressed as a percentage of the 
     *                     range length).
     * 
     * @return The expanded range.
     */
    public static Range expand(Range range, 
                               double lowerMargin, double upperMargin) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[20]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[21]++;
            throw new IllegalArgumentException("Null 'range' argument.");
   
        } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[22]++;}
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[21]++;
        double length = range.getLength();
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[22]++;
        double lower = length * lowerMargin;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[23]++;
        double upper = length * upperMargin;
        return new Range(range.getLowerBound() - lower, 
                range.getUpperBound() + upper);
    }

    /**
     * Shifts the range by the specified amount.
     * 
     * @param base  the base range.
     * @param delta  the shift amount.
     * 
     * @return A new range.
     */
    public static Range shift(Range base, double delta) {
        return shift(base, delta, false);
    }
    
    /**
     * Shifts the range by the specified amount.
     * 
     * @param base  the base range.
     * @param delta  the shift amount.
     * @param allowZeroCrossing  a flag that determines whether or not the 
     *                           bounds of the range are allowed to cross
     *                           zero after adjustment.
     * 
     * @return A new range.
     */
    public static Range shift(Range base, double delta, 
                              boolean allowZeroCrossing) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[24]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((allowZeroCrossing) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[23]++;
            return new Range(base.getLowerBound() + delta, 
                    base.getUpperBound() + delta);

        }
        else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[24]++;
            return new Range(shiftWithNoZeroCrossing(base.getLowerBound(), 
                    delta), shiftWithNoZeroCrossing(base.getUpperBound(), 
                    delta));
        }
    }

    /**
     * Returns the given <code>value</code> adjusted by <code>delta</code> but
     * with a check to prevent the result from crossing <code>0.0</code>.
     * 
     * @param value  the value.
     * @param delta  the adjustment.
     * 
     * @return The adjusted value.
     */
    private static double shiftWithNoZeroCrossing(double value, double delta) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[25]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[25]++;
            return Math.max(value + delta, 0.0);
  
        }
        else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[26]++;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[26]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[27]++;
            return Math.min(value + delta, 0.0);

        }
        else {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[28]++;
            return value + delta;   
        }
}
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[27]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj instanceof Range) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[30]++;}
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[28]++;
        Range range = (Range) obj;
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[29]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.lower == range.lower) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[32]++;}
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[30]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.upper == range.upper) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.branches[34]++;}
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.lower);
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[31]++;
        result = (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[32]++;
        temp = Double.doubleToLongBits(this.upper);
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[33]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$1rcm5qxhprzzgzfl.statements[34]++;
        return result;
    }

    /**
     * Returns a string representation of this Range.
     *
     * @return A String "Range[lower,upper]" where lower=lower range and 
     *         upper=upper range.
     */
    public String toString() {
        return ("Range[" + this.lower + "," + this.upper + "]");
    }

}

class CodeCoverCoverageCounter$1rcm5qxhprzzgzfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1rcm5qxhprzzgzfl ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.data.Range.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$1rcm5qxhprzzgzfl () {
    super("org.jfree.data.Range.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.Range.java");
      for (int i = 1; i <= 34; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
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

