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
 * ----------------------
 * RendererUtilities.java
 * ----------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Apr-2007 : Version 1 (DG);
 * 
 */

package org.jfree.chart.renderer;

import org.jfree.data.DomainOrder;
import org.jfree.data.xy.XYDataset;

/**
 * Utility methods related to the rendering process.
 * 
 * @since 1.0.6
 */
public class RendererUtilities {
  static {
    CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.ping();
  }

    
    /**
     * Finds the lower index of the range of live items in the specified data
     * series.  
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index.
     * @param xLow  the lowest x-value in the live range.
     * @param xHigh  the highest x-value in the live range.
     * 
     * @return The index of the required item.
     * 
     * @since 1.0.6
     * 
     * @see #findLiveItemsUpperBound(XYDataset, int, double, double)
     */
    public static int findLiveItemsLowerBound(XYDataset dataset, int series, 
            double xLow, double xHigh) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[1]++;
        int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((itemCount <= 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[1]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[2]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset.getDomainOrder() == DomainOrder.ASCENDING) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[3]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[4]++;
            // for data in ascending order by x-value, we are (broadly) looking
            // for the index of the highest x-value that is less that xLow
            int low = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[5]++;
            int high = itemCount - 1;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[6]++;
            int mid = (low + high) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[7]++;
            double lowValue = dataset.getXValue(series, low);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lowValue >= xLow) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[5]++;
                // special case where the lowest x-value is >= xLow
                return low;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[6]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[9]++;
            double highValue = dataset.getXValue(series, high);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((highValue < xLow) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[7]++;
                // special case where the highest x-value is < xLow
                return high;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[8]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
            while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((high - low > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[1]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[2]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[3]++;
}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[12]++;
                double midV = dataset.getXValue(series, mid);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((midV >= xLow) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[9]++;
                    high = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[14]++;

                }
                else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[10]++;
                    low = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[15]++;
                }
                mid = (low + high) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[16]++;
            }
            return mid;

        }
        else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[4]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[17]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dataset.getDomainOrder() == DomainOrder.DESCENDING) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[11]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[18]++;
            // when the x-values are sorted in descending order, the lower
            // bound is found by calculating relative to the xHigh value
            int low = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[19]++;
            int high = itemCount - 1;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[20]++;
            int mid = (low + high) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[21]++;
            double lowValue = dataset.getXValue(series, low);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lowValue <= xHigh) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[13]++;
                return low;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[14]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[23]++;
            double highValue = dataset.getXValue(series, high);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((highValue > xHigh) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[15]++;
                return high;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[16]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[25]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;
            while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((high - low > 1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[4]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[5]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[6]++;
}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[26]++;
                double midV = dataset.getXValue(series, mid);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[27]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((midV > xHigh) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[17]++;
                    low = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[28]++;

                }
                else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[18]++;
                    high = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[29]++;
                }
                mid = (low + high) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[30]++;
            }
            return mid;

        }
        else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[12]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[31]++;
            // we don't know anything about the ordering of the x-values,
            // but we can still skip any initial values that fall outside the
            // range...
            int index = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;
            // skip any items that don't need including...
            while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((index < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dataset.getXValue(series, index) 
                    < xLow) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[7]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[8]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[9]++;
}
                index++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[33]++;
            }
            return Math.max(0, index - 1);
        }
}
    }
    
    /**
     * Finds the index of the item in the specified series that...
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index.
     * @param xLow  the lowest x-value in the live range.
     * @param xHigh  the highest x-value in the live range.
     *
     * @return The index of the required item.
     * 
     * @since 1.0.6
     * 
     * @see #findLiveItemsLowerBound(XYDataset, int, double, double)
     */
    public static int findLiveItemsUpperBound(XYDataset dataset, int series, 
            double xLow, double xHigh) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[34]++;
        int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[35]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((itemCount <= 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[19]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[20]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[36]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((dataset.getDomainOrder() == DomainOrder.ASCENDING) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[21]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[37]++;
            int low = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[38]++;
            int high = itemCount - 1;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[39]++;
            int mid = (low + high + 1) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[40]++;
            double lowValue = dataset.getXValue(series, low);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[41]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((lowValue > xHigh) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[23]++;
                return low;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[24]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[42]++;
            double highValue = dataset.getXValue(series, high);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[43]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((highValue <= xHigh) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[25]++;
                return high;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[26]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[44]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[10]++;


int CodeCoverConditionCoverageHelper_C17;
            while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((high - low > 1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[10]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[11]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[12]++;
}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[45]++;
                double midV = dataset.getXValue(series, mid);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[46]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((midV <= xHigh) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[27]++;
                    low = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[47]++;

                }
                else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[28]++;
                    high = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[48]++;
                }
                mid = (low + high + 1) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[49]++;
            }
            return mid;

        }
        else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[22]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[50]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((dataset.getDomainOrder() == DomainOrder.DESCENDING) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[29]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[51]++;
            // when the x-values are descending, the upper bound is found by
            // comparing against xLow
            int low = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[52]++;
            int high = itemCount - 1;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[53]++;
            int mid = (low + high) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[54]++;
            double lowValue = dataset.getXValue(series, low);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[55]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((lowValue < xLow) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[31]++;
                return low;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[32]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[56]++;
            double highValue = dataset.getXValue(series, high);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[57]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((highValue >= xLow) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[33]++;
                return high;

            } else {
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[34]++;}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[58]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[13]++;


int CodeCoverConditionCoverageHelper_C22;
            while ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((high - low > 1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[13]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[14]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[15]++;
}
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[59]++;
                double midV = dataset.getXValue(series, mid);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[60]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((midV >= xLow) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[35]++;
                    low = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[61]++;

                }
                else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[36]++;
                    high = mid;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[62]++;
                }
                mid = (low + high) / 2;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[63]++;
            }
            return mid;

        }
        else {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.branches[30]++;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[64]++;
            // we don't know anything about the ordering of the x-values,
            // but we can still skip any trailing values that fall outside the
            // range...
            int index = itemCount - 1;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[65]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[16]++;


int CodeCoverConditionCoverageHelper_C24;
            // skip any items that don't need including...
            while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((dataset.getXValue(series, index) 
                    > xHigh) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[16]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[17]--;
  CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.loops[18]++;
}
                index--;
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[66]++;
            }
            return Math.min(itemCount - 1, index + 1);
        }
}
    }
    
    /**
     * Finds a range of item indices that is guaranteed to contain all the
     * x-values from x0 to x1 (inclusive).
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index.
     * @param xLow  the lower bound of the x-value range.
     * @param xHigh  the upper bound of the x-value range.
     * 
     * @return The indices of the boundary items.
     */
    public static int[] findLiveItems(XYDataset dataset, int series, 
            double xLow, double xHigh) {
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[67]++;
        // here we could probably be a little faster by searching for both
        // indices simultaneously, but I'll look at that later if it seems
        // like it matters...
        int i0 = findLiveItemsLowerBound(dataset, series, xLow, xHigh);
CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t.statements[68]++;
        int i1 = findLiveItemsUpperBound(dataset, series, xLow, xHigh);
        return new int[] {i0, i1};
    }

}

class CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t ());
  }
    public static long[] statements = new long[69];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.RendererUtilities.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 24; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$dipepcb4czad4ja5rbuf7nqlst50kifk9t () {
    super("org.jfree.chart.renderer.RendererUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 68; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.RendererUtilities.java");
      for (int i = 1; i <= 68; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

