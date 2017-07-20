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
 * -----------------------
 * SimpleHistogramBin.java
 * -----------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 10-Jan-2005 : Version 1 (DG);
 *
 */

package org.jfree.data.statistics;

import java.io.Serializable;

import org.jfree.util.PublicCloneable;

/**
 * A bin for the {@link SimpleHistogramDataset}.
 */
public class SimpleHistogramBin implements Comparable, 
                                           Cloneable, PublicCloneable, 
                                           Serializable {
  static {
    CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3480862537505941742L;
  static {
    CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[1]++;
  }
    
    /** The lower bound for the bin. */
    private double lowerBound;
    
    /** The upper bound for the bin. */
    private double upperBound;
    
    /** 
     * A flag that controls whether the lower bound is included in the bin 
     * range. 
     */
    private boolean includeLowerBound;
    
    /** 
     * A flag that controls whether the upper bound is included in the bin 
     * range. 
     */
    private boolean includeUpperBound;
    
    /** The item count. */
    private int itemCount;
    
    /**
     * Creates a new bin.
     * 
     * @param lowerBound  the lower bound (inclusive).
     * @param upperBound  the upper bound (inclusive);
     */
    public SimpleHistogramBin(double lowerBound, double upperBound) {
        this(lowerBound, upperBound, true, true);
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[2]++;
    }

    /**
     * Creates a new bin.
     * 
     * @param lowerBound  the lower bound.
     * @param upperBound  the upper bound.
     * @param includeLowerBound  include the lower bound?
     * @param includeUpperBound  include the upper bound?
     */
    public SimpleHistogramBin(double lowerBound, double upperBound,
                              boolean includeLowerBound, 
                              boolean includeUpperBound) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((lowerBound >= upperBound) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[1]++;
            throw new IllegalArgumentException("Invalid bounds");

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[2]++;}
        this.lowerBound = lowerBound;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[4]++;
        this.upperBound = upperBound;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[5]++;
        this.includeLowerBound = includeLowerBound;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[6]++;
        this.includeUpperBound = includeUpperBound;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[7]++;
        this.itemCount = 0;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[8]++;
    }
    
    /**
     * Returns the lower bound.
     * 
     * @return The lower bound.
     */
    public double getLowerBound() {
        return this.lowerBound;
    }
    
    /**
     * Return the upper bound.
     * 
     * @return The upper bound.
     */
    public double getUpperBound() {
        return this.upperBound;
    }
    
    /**
     * Returns the item count.
     * 
     * @return The item count.
     */
    public int getItemCount() {
        return this.itemCount;
    }
   
    /**
     * Sets the item count.
     * 
     * @param count  the item count.
     */
    public void setItemCount(int count) {
        this.itemCount = count;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[9]++;
    }

    /**
     * Returns <code>true</code> if the specified value belongs in the bin, 
     * and <code>false</code> otherwise.
     * 
     * @param value  the value.
     * 
     * @return A boolean.
     */
    public boolean accepts(double value) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[4]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value < this.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[6]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value > this.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[8]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value == this.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[9]++;
            return this.includeLowerBound;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[10]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value == this.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[11]++;
            return this.includeUpperBound;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[12]++;}
        return true;
    }
    
    /**
     * Returns <code>true</code> if this bin overlaps with the specified bin,
     * and <code>false</code> otherwise.
     * 
     * @param bin  the other bin (<code>null</code> not permitted).
     * 
     * @return A boolean.
     */
    public boolean overlapsWith(SimpleHistogramBin bin) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.upperBound < bin.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[14]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.lowerBound > bin.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[16]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[17]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.upperBound == bin.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[17]++;
            return this.includeUpperBound && bin.includeLowerBound;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[18]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[18]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.lowerBound == bin.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[19]++;
            return this.includeLowerBound && bin.includeUpperBound;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[20]++;}
        return true;
    }
    
    /**
     * Compares the bin to an arbitrary object and returns the relative 
     * ordering.
     * 
     * @param obj  the object.
     * 
     * @return An integer indicating the relative ordering of the this bin and 
     *         the given object.
     */
    public int compareTo(Object obj) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[19]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((obj instanceof SimpleHistogramBin) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[21]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[22]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[20]++;
        SimpleHistogramBin bin = (SimpleHistogramBin) obj;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[21]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.lowerBound < bin.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[23]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[24]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[22]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.lowerBound > bin.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[25]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[26]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[23]++;
int CodeCoverConditionCoverageHelper_C14;
        // lower bounds are the same
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.upperBound < bin.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[27]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[28]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[24]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.upperBound > bin.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[29]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[30]++;}
        return 0;
    }
    
    /**
     * Tests this bin for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[25]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj instanceof SimpleHistogramBin) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[32]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[26]++;
        SimpleHistogramBin that = (SimpleHistogramBin) obj;
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[27]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.lowerBound != that.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[34]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[28]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.upperBound != that.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[36]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[29]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.includeLowerBound != that.includeLowerBound) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[38]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[30]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.includeUpperBound != that.includeUpperBound) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[40]++;}
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.statements[31]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.itemCount != that.itemCount) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd.branches[42]++;}
        return true;
    }
    
    /**
     * Returns a clone of the bin.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not thrown by this class.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }
    
}

class CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.SimpleHistogramBin.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$2pbogb14nbeg6xdtpttbnmlaievq2j9gw8dd () {
    super("org.jfree.data.statistics.SimpleHistogramBin.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.SimpleHistogramBin.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
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

