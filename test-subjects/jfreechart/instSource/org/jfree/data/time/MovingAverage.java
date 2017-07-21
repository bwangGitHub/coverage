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
 * ------------------
 * MovingAverage.java
 * ------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Benoit Xhenseval;
 *
 * Changes
 * -------
 * 28-Jan-2003 : Version 1 (DG);
 * 10-Mar-2003 : Added createPointMovingAverage() method contributed by Benoit 
 *               Xhenseval (DG);
 * 01-Aug-2003 : Added new method for TimeSeriesCollection, and fixed bug in 
 *               XYDataset method (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 *
 */

package org.jfree.data.time;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * A utility class for calculating moving averages of time series data.
 */
public class MovingAverage {
  static {
    CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.ping();
  }


    /**
     * Creates a new {@link TimeSeriesCollection} containing a moving average 
     * series for each series in the source collection.
     * 
     * @param source  the source collection.
     * @param suffix  the suffix added to each source series name to create the
     *                corresponding moving average series name.
     * @param periodCount  the number of periods in the moving average 
     *                     calculation.
     * @param skip  the number of initial periods to skip.
     * 
     * @return A collection of moving average time series.
     */
    public static TimeSeriesCollection createMovingAverage(
        TimeSeriesCollection source, String suffix, int periodCount,
        int skip) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
    
        // check arguments
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[1]++;
            throw new IllegalArgumentException(
                "MovingAverage.createMovingAverage() : null source."
            );

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[2]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((periodCount < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[3]++;
            throw new IllegalArgumentException(
                "periodCount must be greater than or equal to 1."
            );

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[4]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[3]++;

        TimeSeriesCollection result = new TimeSeriesCollection();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < source.getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[1]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[2]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[3]++;
}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[5]++;
            TimeSeries sourceSeries = source.getSeries(i);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[6]++;
            TimeSeries maSeries = createMovingAverage(
                sourceSeries, sourceSeries.getKey() + suffix, periodCount, skip
            );
            result.addSeries(maSeries);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[7]++;       
        }
        
        return result;
        
    }
    
    /**
     * Creates a new {@link TimeSeries} containing moving average values for 
     * the given series.  If the series is empty (contains zero items), the 
     * result is an empty series.
     *
     * @param source  the source series.
     * @param name  the name of the new series.
     * @param periodCount  the number of periods used in the average 
     *                     calculation.
     * @param skip  the number of initial periods to skip.
     *
     * @return The moving average series.
     */
    public static TimeSeries createMovingAverage(TimeSeries source,
                                                 String name,
                                                 int periodCount,
                                                 int skip) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;

        // check arguments
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[5]++;
            throw new IllegalArgumentException("Null source.");

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[6]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[9]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((periodCount < 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[7]++;
            throw new IllegalArgumentException(
                "periodCount must be greater than or equal to 1."
            );


        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[8]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[10]++;

        TimeSeries result = new TimeSeries(name, source.getTimePeriodClass());
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[11]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((source.getItemCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[9]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[12]++;

            // if the initial averaging period is to be excluded, then 
            // calculate the index of the
            // first data item to have an average calculated...
            long firstSerial 
                = source.getDataItem(0).getPeriod().getSerialIndex() + skip;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;

            for (int i = source.getItemCount() - 1;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[4]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[5]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[6]++;
}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[14]++;

                // get the current data item...
                TimeSeriesDataItem current = source.getDataItem(i);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[15]++;
                RegularTimePeriod period = current.getPeriod();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[16]++;
                long serial = period.getSerialIndex();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[17]++;
int CodeCoverConditionCoverageHelper_C8;

                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((serial >= firstSerial) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[11]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[18]++;
                    // work out the average for the earlier values...
                    int n = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[19]++;
                    double sum = 0.0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[20]++;
                    long serialLimit = period.getSerialIndex() - periodCount;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[21]++;
                    int offset = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[22]++;
                    boolean finished = false;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[23]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;

                    while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((offset < periodCount) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((finished) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[7]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[8]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[9]++;
}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[24]++;
int CodeCoverConditionCoverageHelper_C10;
                        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 (((i - offset) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[13]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[25]++;
                            TimeSeriesDataItem item 
                                = source.getDataItem(i - offset);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[26]++;
                            RegularTimePeriod p = item.getPeriod();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[27]++;
                            Number v = item.getValue();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[28]++;
                            long currentIndex = p.getSerialIndex();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[29]++;
int CodeCoverConditionCoverageHelper_C11;
                            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((currentIndex > serialLimit) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[15]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[30]++;
int CodeCoverConditionCoverageHelper_C12;
                                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[17]++;
                                    sum = sum + v.doubleValue();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[31]++;
                                    n = n + 1;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[32]++;

                                } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[18]++;}

                            }
                            else {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[16]++;
                                finished = true;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[33]++;
                            }

                        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[14]++;}
                        offset = offset + 1;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[34]++;
                    }
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[35]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[19]++;
                        result.add(period, sum / n);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[36]++;

                    }
                    else {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[20]++;
                        result.add(period, null);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[37]++;
                    }

                } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[12]++;}

            }

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[10]++;}

        return result;

    }

    /**
     * Creates a new {@link TimeSeries} containing moving average values for 
     * the given series, calculated by number of points (irrespective of the 
     * 'age' of those points).  If the series is empty (contains zero items), 
     * the result is an empty series.
     * <p>
     * Developed by Benoit Xhenseval (www.ObjectLab.co.uk).
     *
     * @param source  the source series.
     * @param name  the name of the new series.
     * @param pointCount  the number of POINTS used in the average calculation 
     *                    (not periods!)
     *
     * @return The moving average series.
     */
    public static TimeSeries createPointMovingAverage(TimeSeries source,
                                                      String name, 
                                                      int pointCount) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[38]++;
int CodeCoverConditionCoverageHelper_C14;

        // check arguments
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[21]++;
            throw new IllegalArgumentException("Null 'source'.");

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[22]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[39]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pointCount < 2) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[23]++;
            throw new IllegalArgumentException(
                "periodCount must be greater than or equal to 2."
            );

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[24]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[40]++;

        TimeSeries result = new TimeSeries(name, source.getTimePeriodClass());
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[41]++;
        double rollingSumForPeriod = 0.0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[42]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[10]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < source.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[10]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[11]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[12]++;
}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[43]++;
            // get the current data item...
            TimeSeriesDataItem current = source.getDataItem(i);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[44]++;
            RegularTimePeriod period = current.getPeriod();
            rollingSumForPeriod += current.getValue().doubleValue();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[45]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[46]++;
int CodeCoverConditionCoverageHelper_C17;

            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i > pointCount - 1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[25]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[47]++;
                // remove the point i-periodCount out of the rolling sum.
                TimeSeriesDataItem startOfMovingAvg 
                    = source.getDataItem(i - pointCount);
                rollingSumForPeriod 
                    -= startOfMovingAvg.getValue().doubleValue();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[48]++;
                result.add(period, rollingSumForPeriod / pointCount);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[49]++;

            }
            else {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[26]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[50]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i == pointCount - 1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[27]++;
                result.add(period, rollingSumForPeriod / pointCount);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[51]++;

            } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[28]++;}
}
        }
        return result;
    }

    /**
     * Creates a new {@link XYDataset} containing the moving averages of each 
     * series in the <code>source</code> dataset.
     *
     * @param source  the source dataset.
     * @param suffix  the string to append to source series names to create 
     *                target series names.
     * @param period  the averaging period.
     * @param skip  the length of the initial skip period.
     *
     * @return The dataset.
     */
    public static XYDataset createMovingAverage(XYDataset source, String suffix,
                                                long period, final long skip) {

        return createMovingAverage(
            source, suffix, (double) period, (double) skip
        );
        
    }


    /**
     * Creates a new {@link XYDataset} containing the moving averages of each 
     * series in the <code>source</code> dataset.
     *
     * @param source  the source dataset.
     * @param suffix  the string to append to source series names to create 
     *                target series names.
     * @param period  the averaging period.
     * @param skip  the length of the initial skip period.
     *
     * @return The dataset.
     */
    public static XYDataset createMovingAverage(XYDataset source, String suffix,
                                                double period, double skip) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[52]++;
int CodeCoverConditionCoverageHelper_C19;

        // check arguments
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[29]++;
            throw new IllegalArgumentException("Null source (XYDataset).");

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[30]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[53]++;
        
        XYSeriesCollection result = new XYSeriesCollection();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[54]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[13]++;


int CodeCoverConditionCoverageHelper_C20;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < source.getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[13]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[14]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[15]++;
}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[55]++;
            XYSeries s = createMovingAverage(
                source, i, source.getSeriesKey(i) + suffix, period, skip
            );
            result.addSeries(s);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[56]++;
        }

        return result;

    }

    /**
     * Creates a new {@link XYSeries} containing the moving averages of one 
     * series in the <code>source</code> dataset.
     *
     * @param source  the source dataset.
     * @param series  the series index (zero based).
     * @param name  the name for the new series.
     * @param period  the averaging period.
     * @param skip  the length of the initial skip period.
     *
     * @return The dataset.
     */
    public static XYSeries createMovingAverage(XYDataset source, 
                                               int series, String name,
                                               double period, double skip) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[57]++;
int CodeCoverConditionCoverageHelper_C21;

                                               
        // check arguments
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((source == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[31]++;
            throw new IllegalArgumentException("Null source (XYDataset).");

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[32]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[58]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((period < Double.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[33]++;
            throw new IllegalArgumentException("period must be positive.");


        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[34]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[59]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((skip < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[35]++;
            throw new IllegalArgumentException("skip must be >= 0.0.");


        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[36]++;}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[60]++;

        XYSeries result = new XYSeries(name);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[61]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((source.getItemCount(series) > 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[37]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[62]++;

            // if the initial averaging period is to be excluded, then 
            // calculate the lowest x-value to have an average calculated...
            double first = source.getXValue(series, 0) + skip;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[63]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[16]++;


int CodeCoverConditionCoverageHelper_C25;

            for (int i = source.getItemCount(series) - 1;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[16]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[17]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[18]++;
}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[64]++;

                // get the current data item...
                double x = source.getXValue(series, i);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[65]++;
int CodeCoverConditionCoverageHelper_C26;

                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((x >= first) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[39]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[66]++;
                    // work out the average for the earlier values...
                    int n = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[67]++;
                    double sum = 0.0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[68]++;
                    double limit = x - period;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[69]++;
                    int offset = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[70]++;
                    boolean finished = false;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[71]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[19]++;


int CodeCoverConditionCoverageHelper_C27;

                    while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((finished) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[19]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[20]--;
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.loops[21]++;
}
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[72]++;
int CodeCoverConditionCoverageHelper_C28;
                        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 (((i - offset) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[41]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[73]++;
                            double xx = source.getXValue(series, i - offset);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[74]++;
                            Number yy = source.getY(series, i - offset);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[75]++;
int CodeCoverConditionCoverageHelper_C29;
                            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((xx > limit) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[43]++;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[76]++;
int CodeCoverConditionCoverageHelper_C30;
                                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((yy != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[45]++;
                                    sum = sum + yy.doubleValue();
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[77]++;
                                    n = n + 1;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[78]++;

                                } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[46]++;}

                            }
                            else {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[44]++;
                                finished = true;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[79]++;
                            }

                        }
                        else {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[42]++;
                            finished = true;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[80]++;
                        }
                        offset = offset + 1;
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[81]++;
                    }
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[82]++;
int CodeCoverConditionCoverageHelper_C31;
                    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((n > 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[47]++;
                        result.add(x, sum / n);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[83]++;

                    }
                    else {
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[48]++;
                        result.add(x, null);
CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.statements[84]++;
                    }

                } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[40]++;}

            }

        } else {
  CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht.branches[38]++;}

        return result;

    }

}

class CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht ());
  }
    public static long[] statements = new long[85];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "org.jfree.data.time.MovingAverage.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$6ftktkzphe6kn87bvtk2dzjq3iht () {
    super("org.jfree.data.time.MovingAverage.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 84; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.MovingAverage.java");
      for (int i = 1; i <= 84; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

