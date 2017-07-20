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
 * Statistics.java
 * ---------------
 * (C) Copyright 2000-2007, by Matthew Wright and Contributors.
 *
 * Original Author:  Matthew Wright;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes (from 08-Nov-2001)
 * --------------------------
 * 08-Nov-2001 : Added standard header and tidied Javadoc comments (DG);
 *               Moved from JFreeChart to package com.jrefinery.data.* in 
 *               JCommon class library (DG);
 * 24-Jun-2002 : Removed unnecessary local variable (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-May-2004 : Moved calculateMean() method from BoxAndWhiskerCalculator (DG);
 * 02-Jun-2004 : Fixed bug in calculateMedian() method (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 *
 */

package org.jfree.data.statistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A utility class that provides some common statistical functions.
 */
public abstract class Statistics {
  static {
    CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.ping();
  }


    /**
     * Returns the mean of an array of numbers.  This is equivalent to calling
     * <code>calculateMean(values, true)</code>.
     *
     * @param values  the values (<code>null</code> not permitted).
     *
     * @return The mean.
     */
    public static double calculateMean(Number[] values) {
        return calculateMean(values, true);
    }
    
    /**
     * Returns the mean of an array of numbers.
     *
     * @param values  the values (<code>null</code> not permitted).
     * @param includeNullAndNaN  a flag that controls whether or not 
     *     <code>null</code> and <code>Double.NaN</code> values are included
     *     in the calculation (if either is present in the array, the result is 
     *     {@link Double#NaN}).
     *
     * @return The mean.
     * 
     * @since 1.0.3
     */
    public static double calculateMean(Number[] values, 
            boolean includeNullAndNaN) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[1]++;
            throw new IllegalArgumentException("Null 'values' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[2]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[2]++;
        double sum = 0.0;
        double current;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[3]++;
        int counter = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[1]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[2]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[3]++;
}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
            // treat nulls the same as NaNs
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((values[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[3]++;
                current = values[i].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[6]++;
    
            }
            else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[4]++;
                current = Double.NaN;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[7]++;
            }
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
            // calculate the sum and count
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((includeNullAndNaN) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(current)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[5]++;
                sum = sum + current;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[9]++;
                counter++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[10]++;

            } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[6]++;}
        }
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[11]++;
        double result = (sum / counter);
        return result;
    }

    /**
     * Returns the mean of a collection of <code>Number</code> objects.
     * 
     * @param values  the values (<code>null</code> not permitted).
     * 
     * @return The mean.
     */
    public static double calculateMean(Collection values) {
        return calculateMean(values, true);
    }
    
    /**
     * Returns the mean of a collection of <code>Number</code> objects.
     * 
     * @param values  the values (<code>null</code> not permitted).
     * @param includeNullAndNaN  a flag that controls whether or not 
     *     <code>null</code> and <code>Double.NaN</code> values are included
     *     in the calculation (if either is present in the array, the result is 
     *     {@link Double#NaN}).
     * 
     * @return The mean.
     * 
     * @since 1.0.3
     */
    public static double calculateMean(Collection values, 
            boolean includeNullAndNaN) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[7]++;
            throw new IllegalArgumentException("Null 'values' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[8]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[13]++;
        int count = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[14]++;
        double total = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[15]++;
        Iterator iterator = values.iterator();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[16]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[4]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[5]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[6]++;
}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[17]++;
            Object object = iterator.next();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[9]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((includeNullAndNaN) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[11]++;
                    return Double.NaN;

                } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[12]++;}

            }
            else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[10]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[20]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((object instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[13]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[21]++;
                    Number number = (Number) object;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[22]++;
                    double value = number.doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[15]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[24]++;
int CodeCoverConditionCoverageHelper_C11;
                        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((includeNullAndNaN) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[17]++;
                            return Double.NaN;

                        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[18]++;}

                    }
                    else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[16]++;
                        total = total + number.doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[25]++;
                        count = count + 1;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[26]++;
                    }

                } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[14]++;}
            }
        }      
        return total / count;
    }

    /**
     * Calculates the median for a list of values (<code>Number</code> objects).
     * The list of values will be copied, and the copy sorted, before 
     * calculating the median.  To avoid this step (if your list of values
     * is already sorted), use the {@link #calculateMedian(List, boolean)} 
     * method.
     * 
     * @param values  the values (<code>null</code> permitted).
     * 
     * @return The median.
     */
    public static double calculateMedian(List values) {
        return calculateMedian(values, true);
    }
    
    /**
     * Calculates the median for a list of values (<code>Number</code> objects).
     * If <code>copyAndSort</code> is <code>false</code>, the list is assumed
     * to be presorted in ascending order by value.
     * 
     * @param values  the values (<code>null</code> permitted).
     * @param copyAndSort  a flag that controls whether the list of values is
     *                     copied and sorted.
     * 
     * @return The median.
     */
    public static double calculateMedian(List values, boolean copyAndSort) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[27]++;
        
        double result = Double.NaN;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[28]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((values != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[19]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[29]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((copyAndSort) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[21]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[30]++;
                int itemCount = values.size();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[31]++;
                List copy = new ArrayList(itemCount);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[7]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[8]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[9]++;
}
                    copy.add(i, values.get(i));
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[33]++;   
                }
                Collections.sort(copy);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[34]++;
                values = copy;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[35]++;

            } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[22]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[36]++;
            int count = values.size();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[37]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[23]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[38]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((count % 2 == 1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[25]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[39]++;
int CodeCoverConditionCoverageHelper_C17;
                    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[27]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[40]++;
                        Number value = (Number) values.get((count - 1) / 2);
                        result = value.doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[41]++;

                    }
                    else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[28]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[42]++;
                        Number value = (Number) values.get(0);
                        result = value.doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[43]++;
                    }

                }
                else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[26]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[44]++;
                    Number value1 = (Number) values.get(count / 2 - 1);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[45]++;
                    Number value2 = (Number) values.get(count / 2);
                    result = (value1.doubleValue() + value2.doubleValue()) 
                             / 2.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[46]++;
                }

            } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[20]++;}
        return result;
    }
    
    /**
     * Calculates the median for a sublist within a list of values 
     * (<code>Number</code> objects).
     * 
     * @param values  the values, in any order (<code>null</code> not 
     *                permitted).
     * @param start  the start index.
     * @param end  the end index.
     * 
     * @return The median.
     */
    public static double calculateMedian(List values, int start, int end) {
        return calculateMedian(values, start, end, true);
    }

    /**
     * Calculates the median for a sublist within a list of values 
     * (<code>Number</code> objects).  The entire list will be sorted if the 
     * <code>ascending</code< argument is <code>false</code>.
     * 
     * @param values  the values (<code>null</code> not permitted).
     * @param start  the start index.
     * @param end  the end index.
     * @param copyAndSort  a flag that that controls whether the list of values 
     *                     is copied and sorted.
     * 
     * @return The median.
     */
    public static double calculateMedian(List values, int start, int end,
                                         boolean copyAndSort) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[47]++;
        
        double result = Double.NaN;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((copyAndSort) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[29]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[49]++;
            List working = new ArrayList(end - start + 1);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[50]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
            for (int i = start;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i <= end) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[10]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[11]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[12]++;
}
                working.add(values.get(i));
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[51]++;  
            }
            Collections.sort(working);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[52]++; 
            result = calculateMedian(working, false);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[53]++;

        }
        else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[30]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[54]++;
            int count = end - start + 1;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[55]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[31]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[56]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((count % 2 == 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[33]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[57]++;
int CodeCoverConditionCoverageHelper_C22;
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[35]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[58]++;
                        Number value 
                            = (Number) values.get(start + (count - 1) / 2);
                        result = value.doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[59]++;

                    }
                    else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[36]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[60]++;
                        Number value = (Number) values.get(start);
                        result = value.doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[61]++;
                    }

                }
                else {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[34]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[62]++;
                    Number value1 = (Number) values.get(start + count / 2 - 1);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[63]++;
                    Number value2 = (Number) values.get(start + count / 2);
                    result 
                        = (value1.doubleValue() + value2.doubleValue()) / 2.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[64]++;
                }

            } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[32]++;}
        }
        return result;    
        
    }
    
    /**
     * Returns the standard deviation of a set of numbers.
     *
     * @param data  the data (<code>null</code> or zero length array not 
     *     permitted).
     *
     * @return The standard deviation of a set of numbers.
     */
    public static double getStdDev(Number[] data) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[65]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[37]++;
            throw new IllegalArgumentException("Null 'data' array.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[38]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[66]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((data.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[39]++;
            throw new IllegalArgumentException("Zero length 'data' array.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[40]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[67]++;
        double avg = calculateMean(data);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[68]++;
        double sum = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[69]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[13]++;


int CodeCoverConditionCoverageHelper_C25;

        for (int counter = 0;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((counter < data.length) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); counter++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[13]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[14]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[15]++;
}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[70]++;
            double diff = data[counter].doubleValue() - avg;
            sum = sum + diff * diff;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[71]++;
        }
        return Math.sqrt(sum / (data.length - 1));
    }

    /**
     * Fits a straight line to a set of (x, y) data, returning the slope and
     * intercept.
     *
     * @param xData  the x-data (<code>null</code> not permitted).
     * @param yData  the y-data (<code>null</code> not permitted).
     *
     * @return A double array with the intercept in [0] and the slope in [1].
     */
    public static double[] getLinearFit(Number[] xData, Number[] yData) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[72]++;
int CodeCoverConditionCoverageHelper_C26;

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((xData == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[41]++; 
            throw new IllegalArgumentException("Null 'xData' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[42]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[73]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((yData == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[43]++; 
            throw new IllegalArgumentException("Null 'yData' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[44]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[74]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((xData.length != yData.length) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[45]++;
            throw new IllegalArgumentException(
                "Statistics.getLinearFit(): array lengths must be equal.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[46]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[75]++;

        double[] result = new double[2];
        // slope
        result[1] = getSlope(xData, yData);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[76]++;
        // intercept
        result[0] = calculateMean(yData) - result[1] * calculateMean(xData);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[77]++;

        return result;

    }

    /**
     * Finds the slope of a regression line using least squares.
     *
     * @param xData  the x-values (<code>null</code> not permitted).
     * @param yData  the y-values (<code>null</code> not permitted).
     *
     * @return The slope.
     */
    public static double getSlope(Number[] xData, Number[] yData) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[78]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((xData == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[47]++; 
            throw new IllegalArgumentException("Null 'xData' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[48]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[79]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((yData == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[49]++; 
            throw new IllegalArgumentException("Null 'yData' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[50]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[80]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((xData.length != yData.length) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[51]++;
            throw new IllegalArgumentException("Array lengths must be equal.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[52]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[81]++;

        // ********* stat function for linear slope ********
        // y = a + bx
        // a = ybar - b * xbar
        //     sum(x * y) - (sum (x) * sum(y)) / n
        // b = ------------------------------------
        //     sum (x^2) - (sum(x)^2 / n
        // *************************************************

        // sum of x, x^2, x * y, y
        double sx = 0.0, sxx = 0.0, sxy = 0.0, sy = 0.0;
        int counter;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[82]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[16]++;


int CodeCoverConditionCoverageHelper_C32;
        for (counter = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((counter < xData.length) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); counter++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[16]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[17]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[18]++;
}
            sx = sx + xData[counter].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[83]++;
            sxx = sxx + Math.pow(xData[counter].doubleValue(), 2);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[84]++;
            sxy = sxy + yData[counter].doubleValue() 
                      * xData[counter].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[85]++;
            sy = sy + yData[counter].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[86]++;
        }
        return (sxy - (sx * sy) / counter) / (sxx - (sx * sx) / counter);

    }

    /**
     * Calculates the correlation between two datasets.  Both arrays should 
     * contain the same number of items.  Null values are treated as zero.
     * <P>
     * Information about the correlation calculation was obtained from:
     * 
     * http://trochim.human.cornell.edu/kb/statcorr.htm
     * 
     * @param data1  the first dataset.
     * @param data2  the second dataset.
     * 
     * @return The correlation.
     */
    public static double getCorrelation(Number[] data1, Number[] data2) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[87]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((data1 == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[53]++;
            throw new IllegalArgumentException("Null 'data1' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[54]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[88]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((data2 == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[55]++;
            throw new IllegalArgumentException("Null 'data2' argument.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[56]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[89]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((data1.length != data2.length) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[57]++;
            throw new IllegalArgumentException(
                "'data1' and 'data2' arrays must have same length."
            );
   
        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[58]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[90]++;
        int n = data1.length;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[91]++;
        double sumX = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[92]++;
        double sumY = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[93]++;
        double sumX2 = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[94]++;
        double sumY2 = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[95]++;
        double sumXY = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[96]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[19]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[19]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[20]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[21]++;
}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[97]++;
            double x = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[98]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((data1[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[59]++;
                x = data1[i].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[99]++;
   
            } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[60]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[100]++;
            double y = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[101]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((data2[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[61]++;
                y = data2[i].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[102]++;
   
            } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[62]++;}
            sumX = sumX + x;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[103]++;
            sumY = sumY + y;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[104]++;
            sumXY = sumXY + (x * y);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[105]++;
            sumX2 = sumX2 + (x * x);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[106]++;
            sumY2 = sumY2 + (y * y);
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[107]++;
        }
        return (n * sumXY - sumX * sumY) / Math.pow((n * sumX2 - sumX * sumX) 
                * (n * sumY2 - sumY * sumY), 0.5);      
    }

    /**
     * Returns a data set for a moving average on the data set passed in.
     *
     * @param xData  an array of the x data.
     * @param yData  an array of the y data.
     * @param period  the number of data points to average
     *
     * @return A double[][] the length of the data set in the first dimension,
     *         with two doubles for x and y in the second dimension
     */
    public static double[][] getMovingAverage(Number[] xData, 
                                              Number[] yData, 
                                              int period) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[108]++;
int CodeCoverConditionCoverageHelper_C39;

        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((xData.length != yData.length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[63]++;
            throw new IllegalArgumentException("Array lengths must be equal.");

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[64]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[109]++;
int CodeCoverConditionCoverageHelper_C40;

        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((period > xData.length) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[65]++;
            throw new IllegalArgumentException(
                "Period can't be longer than dataset."
            );

        } else {
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.branches[66]++;}
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[110]++;

        double[][] result = new double[xData.length - period][2];
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[111]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[22]++;


int CodeCoverConditionCoverageHelper_C41;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i < result.length) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[22]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[23]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[24]++;
}
            result[i][0] = xData[i + period].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[112]++;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[113]++;
            // holds the moving average sum
            double sum = 0.0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[114]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[25]++;


int CodeCoverConditionCoverageHelper_C42;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((j < period) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[25]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[26]--;
  CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.loops[27]++;
}
                sum += yData[i + j].doubleValue();
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[115]++;
            }
            sum = sum / period;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[116]++;
            result[i][1] = sum;
CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep.statements[117]++;
        }
        return result;

    }

}

class CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep ());
  }
    public static long[] statements = new long[118];
    public static long[] branches = new long[67];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[43];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.Statistics.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 42; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$p0g14fatfq2xomtrr19rpep () {
    super("org.jfree.data.statistics.Statistics.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 117; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 66; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 42; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.Statistics.java");
      for (int i = 1; i <= 117; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 66; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 42; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

