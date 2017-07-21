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
 * ----------------------------
 * BoxAndWhiskerCalculator.java
 * ----------------------------
 * (C) Copyright 2003-2007,  by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 28-Aug-2003 : Version 1 (DG);
 * 17-Nov-2003 : Fixed bug in calculations of outliers and median (DG);
 * 10-Jan-2005 : Removed deprecated methods in preparation for 1.0.0 
 *               release (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 15-Nov-2006 : Cleaned up handling of null arguments, and null or NaN items 
 *               in the list (DG);
 *
 */

package org.jfree.data.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A utility class that calculates the mean, median, quartiles Q1 and Q3, plus
 * a list of outlier values...all from an arbitrary list of 
 * <code>Number</code> objects.
 */
public abstract class BoxAndWhiskerCalculator {
  static {
    CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.ping();
  }

    
    /**
     * Calculates the statistics required for a {@link BoxAndWhiskerItem}
     * from a list of <code>Number</code> objects.  Any items in the list
     * that are <code>null</code>, not an instance of <code>Number</code>, or
     * equivalent to <code>Double.NaN</code>, will be ignored.
     * 
     * @param values  a list of numbers (a <code>null</code> list is not 
     *                permitted).
     * 
     * @return A box-and-whisker item.
     */
    public static BoxAndWhiskerItem calculateBoxAndWhiskerStatistics(
                                        List values) {
        return calculateBoxAndWhiskerStatistics(values, true); 
    }

    /**
     * Calculates the statistics required for a {@link BoxAndWhiskerItem}
     * from a list of <code>Number</code> objects.  Any items in the list
     * that are <code>null</code>, not an instance of <code>Number</code>, or
     * equivalent to <code>Double.NaN</code>, will be ignored.
     * 
     * @param values  a list of numbers (a <code>null</code> list is not 
     *                permitted).
     * @param stripNullAndNaNItems  a flag that controls the handling of null
     *     and NaN items.
     * 
     * @return A box-and-whisker item.
     * 
     * @since 1.0.3
     */
    public static BoxAndWhiskerItem calculateBoxAndWhiskerStatistics(
            List values, boolean stripNullAndNaNItems) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[1]++; 
            throw new IllegalArgumentException("Null 'values' argument.");

        } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[2]++;}
        
        List vlist;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((stripNullAndNaNItems) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[3]++;        
            vlist = new ArrayList(values.size());
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[3]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[4]++;
            Iterator iterator = values.listIterator();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
            while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[1]--;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[2]--;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[3]++;
}
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[6]++;
                Object obj = iterator.next();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[5]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[8]++;
                    Number n = (Number) obj;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[9]++;
                    double v = n.doubleValue();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Double.isNaN(v)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[7]++;
                        vlist.add(n);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[11]++;

                    } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[8]++;}

                } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[6]++;}
            }

        }
        else {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[4]++;
            vlist = values;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[12]++;
        }
        Collections.sort(vlist);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[13]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[14]++;
        
        double mean = Statistics.calculateMean(vlist, false);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[15]++;
        double median = Statistics.calculateMedian(vlist, false);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[16]++;
        double q1 = calculateQ1(vlist);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[17]++;
        double q3 = calculateQ3(vlist);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[18]++;
        
        double interQuartileRange = q3 - q1;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[19]++;
        
        double upperOutlierThreshold = q3 + (interQuartileRange * 1.5);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[20]++;
        double lowerOutlierThreshold = q1 - (interQuartileRange * 1.5);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[21]++;
        
        double upperFaroutThreshold = q3 + (interQuartileRange * 2.0);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[22]++;
        double lowerFaroutThreshold = q1 - (interQuartileRange * 2.0);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[23]++;

        double minRegularValue = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[24]++;
        double maxRegularValue = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[25]++;
        double minOutlier = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[26]++;
        double maxOutlier = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[27]++;
        List outliers = new ArrayList();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[28]++;
        
        Iterator iterator = vlist.iterator();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[29]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[4]--;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[5]--;
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.loops[6]++;
}
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[30]++;
            Number number = (Number) iterator.next();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[31]++;
            double value = number.doubleValue();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value > upperOutlierThreshold) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[9]++;
                outliers.add(number);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[33]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((value > maxOutlier) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value <= upperFaroutThreshold) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[11]++;
                    maxOutlier = value;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[35]++;

                } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[12]++;}

            }
            else {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[10]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[36]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value < lowerOutlierThreshold) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[13]++;
                outliers.add(number);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[37]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;                    
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((value < minOutlier) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value >= lowerFaroutThreshold) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[15]++;
                    minOutlier = value;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[39]++;

                } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[16]++;}

            }
            else {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[14]++;
                minRegularValue = Math.min(minRegularValue, value);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[40]++;
                maxRegularValue = Math.max(maxRegularValue, value);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[41]++;
            }
}
            minOutlier = Math.min(minOutlier, minRegularValue);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[42]++;
            maxOutlier = Math.max(maxOutlier, maxRegularValue);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[43]++;
        }
        
        return new BoxAndWhiskerItem(new Double(mean), new Double(median),
                new Double(q1), new Double(q3), new Double(minRegularValue),
                new Double(maxRegularValue), new Double(minOutlier),
                new Double(maxOutlier), outliers);
        
    }

    /**
     * Calculates the first quartile for a list of numbers in ascending order.
     * If the items in the list are not in ascending order, the result is
     * unspecified.  If the list contains items that are <code>null</code>, not 
     * an instance of <code>Number</code>, or equivalent to 
     * <code>Double.NaN</code>, the result is unspecified.
     * 
     * @param values  the numbers in ascending order (<code>null</code> not 
     *     permitted).
     * 
     * @return The first quartile.
     */
    public static double calculateQ1(List values) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[17]++;
            throw new IllegalArgumentException("Null 'values' argument.");

        } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[18]++;}
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[45]++;
        
        double result = Double.NaN;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[46]++;
        int count = values.size();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[19]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((count % 2 == 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[21]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[49]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[23]++;
                    result = Statistics.calculateMedian(values, 0, count / 2);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[50]++;

                }
                else {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[24]++;
                    result = Statistics.calculateMedian(values, 0, 0);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[51]++;
                }

            }
            else {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[22]++;
                result = Statistics.calculateMedian(values, 0, count / 2 - 1);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[52]++;
            }

            
        } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[20]++;}
        return result;
    }
    
    /**
     * Calculates the third quartile for a list of numbers in ascending order.
     * If the items in the list are not in ascending order, the result is
     * unspecified.  If the list contains items that are <code>null</code>, not 
     * an instance of <code>Number</code>, or equivalent to 
     * <code>Double.NaN</code>, the result is unspecified.
     * 
     * @param values  the list of values (<code>null</code> not permitted).
     * 
     * @return The third quartile.
     */
    public static double calculateQ3(List values) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[25]++;
            throw new IllegalArgumentException("Null 'values' argument.");

        } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[26]++;}
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[54]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[55]++;
        int count = values.size();
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[27]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[57]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((count % 2 == 1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[29]++;
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[58]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[31]++;
                    result = Statistics.calculateMedian(values, count / 2, 
                            count - 1);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[59]++;

                }
                else {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[32]++;
                    result = Statistics.calculateMedian(values, 0, 0);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[60]++;
                }

            }
            else {
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[30]++;
                result = Statistics.calculateMedian(values, count / 2, 
                        count - 1);
CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.statements[61]++;
            }

        } else {
  CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1.branches[28]++;}
        return result;
    }
    
}

class CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1 ());
  }
    public static long[] statements = new long[62];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.BoxAndWhiskerCalculator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$u7m8k8j3uyfunggduz8onsjiiqbvcs3ifbz2e531eg1 () {
    super("org.jfree.data.statistics.BoxAndWhiskerCalculator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 61; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.BoxAndWhiskerCalculator.java");
      for (int i = 1; i <= 61; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
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

