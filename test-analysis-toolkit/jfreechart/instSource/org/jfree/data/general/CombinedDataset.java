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
 * --------------------
 * CombinedDataset.java
 * --------------------
 * (C) Copyright 2001-2007, by Bill Kelemen and Contributors.
 *
 * Original Author:  Bill Kelemen;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 06-Dec-2001 : Version 1 (BK);
 * 27-Dec-2001 : Fixed bug in getChildPosition method (BK);
 * 29-Dec-2001 : Fixed bug in getChildPosition method with complex 
 *               CombinePlot (BK);
 * 05-Feb-2002 : Small addition to the interface HighLowDataset, as requested 
 *               by Sylvain Vieujot (DG);
 * 14-Feb-2002 : Added bug fix for IntervalXYDataset methods, submitted by 
 *               Gyula Kun-Szabo (DG);
 * 11-Jun-2002 : Updated for change in event constructor (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 06-May-2004 : Now extends AbstractIntervalXYDataset and added other methods 
 *               that return double primitives (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */

package org.jfree.data.general;

import java.util.List;

import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;

/**
 * This class can combine instances of {@link XYDataset}, {@link OHLCDataset} 
 * and {@link IntervalXYDataset} together exposing the union of all the series 
 * under one dataset.  
 */
public class CombinedDataset extends AbstractIntervalXYDataset
                             implements XYDataset, 
                                        OHLCDataset, 
                                        IntervalXYDataset,
                                        CombinationDataset {
  static {
    CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.ping();
  }


    /** Storage for the datasets we combine. */
    private List datasetInfo = new java.util.ArrayList();
  {
    CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[1]++;
  }

    /**
     * Default constructor for an empty combination.
     */
    public CombinedDataset() {
        super();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[2]++;
    }

    /**
     * Creates a CombinedDataset initialized with an array of SeriesDatasets.
     *
     * @param data  array of SeriesDataset that contains the SeriesDatasets to 
     *              combine.
     */
    public CombinedDataset(SeriesDataset[] data) {
        add(data);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[3]++;
    }

    /**
     * Adds one SeriesDataset to the combination. Listeners are notified of the
     * change.
     *
     * @param data  the SeriesDataset to add.
     */
    public void add(SeriesDataset data) {
        fastAdd(data);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[4]++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[5]++;
        DatasetChangeEvent event = new DatasetChangeEvent(this, this);
        notifyListeners(event);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[6]++;
    }

    /**
     * Adds an array of SeriesDataset's to the combination. Listeners are
     * notified of the change.
     *
     * @param data  array of SeriesDataset to add
     */
    public void add(SeriesDataset[] data) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < data.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[1]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[2]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[3]++;
}
            fastAdd(data[i]);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[8]++;
        }
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[9]++;
        DatasetChangeEvent event = new DatasetChangeEvent(this, this);
        notifyListeners(event);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[10]++;

    }

    /**
     * Adds one series from a SeriesDataset to the combination. Listeners are
     * notified of the change.
     *
     * @param data  the SeriesDataset where series is contained
     * @param series  series to add
     */
    public void add(SeriesDataset data, int series) {
        add(new SubSeriesDataset(data, series));
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[11]++;
    }

    /**
     * Fast add of a SeriesDataset. Does not notify listeners of the change.
     *
     * @param data  SeriesDataset to add
     */
    private void fastAdd(SeriesDataset data) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[12]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < data.getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[4]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[5]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[6]++;
}
            this.datasetInfo.add(new DatasetInfo(data, i));
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[13]++;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // From SeriesDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the number of series in the dataset.
     *
     * @return The number of series in the dataset.
     */
    public int getSeriesCount() {
        return this.datasetInfo.size();
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The key for a series.
     */
    public Comparable getSeriesKey(int series) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[14]++;
        DatasetInfo di = getDatasetInfo(series);
        return di.data.getSeriesKey(di.series);
    }

    ///////////////////////////////////////////////////////////////////////////
    // From XYDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the X-value for the specified series and item.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from 
     * a {@link XYDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The X-value for the specified series and item.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[15]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((XYDataset) di.data).getX(di.series, item);
    }

    /**
     * Returns the Y-value for the specified series and item.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from 
     * a {@link XYDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The Y-value for the specified series and item.
     */
    public Number getY(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[16]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((XYDataset) di.data).getY(di.series, item);
    }

    /**
     * Returns the number of items in a series.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from 
     * a {@link XYDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     *
     * @return The number of items in a series.
     */
    public int getItemCount(int series) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[17]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((XYDataset) di.data).getItemCount(di.series);
    }

    ///////////////////////////////////////////////////////////////////////////
    // From HighLowDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the high-value for the specified series and item.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from a
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The high-value for the specified series and item.
     */
    public Number getHigh(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[18]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((OHLCDataset) di.data).getHigh(di.series, item);
    }

    /**
     * Returns the high-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The high-value.
     */
    public double getHighValue(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[19]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[20]++;
        Number high = getHigh(series, item);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((high != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[1]++;
            result = high.doubleValue();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[22]++;
   
        } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[2]++;}
        return result;   
    }

    /**
     * Returns the low-value for the specified series and item.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from a
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The low-value for the specified series and item.
     */
    public Number getLow(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[23]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((OHLCDataset) di.data).getLow(di.series, item);
    }

    /**
     * Returns the low-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The low-value.
     */
    public double getLowValue(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[24]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[25]++;
        Number low = getLow(series, item);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((low != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[3]++;
            result = low.doubleValue();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[27]++;
   
        } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[4]++;}
        return result;   
    }

    /**
     * Returns the open-value for the specified series and item.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from a
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The open-value for the specified series and item.
     */
    public Number getOpen(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[28]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((OHLCDataset) di.data).getOpen(di.series, item);
    }

    /**
     * Returns the open-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The open-value.
     */
    public double getOpenValue(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[29]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[30]++;
        Number open = getOpen(series, item);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((open != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[5]++;
            result = open.doubleValue();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[32]++;
   
        } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[6]++;}
        return result;   
    }

    /**
     * Returns the close-value for the specified series and item.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from a
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The close-value for the specified series and item.
     */
    public Number getClose(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[33]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((OHLCDataset) di.data).getClose(di.series, item);
    }

    /**
     * Returns the close-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The close-value.
     */
    public double getCloseValue(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[34]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[35]++;
        Number close = getClose(series, item);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((close != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[7]++;
            result = close.doubleValue();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[37]++;
   
        } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[8]++;}
        return result;   
    }

    /**
     * Returns the volume value for the specified series and item.
     * <P>
     * Note:  throws <code>ClassCastException</code> if the series is not from a
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The volume value for the specified series and item.
     */
    public Number getVolume(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[38]++;
        DatasetInfo di = getDatasetInfo(series);
        return ((OHLCDataset) di.data).getVolume(di.series, item);
    }

    /**
     * Returns the volume-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The volume-value.
     */
    public double getVolumeValue(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[39]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[40]++;
        Number volume = getVolume(series, item);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((volume != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[9]++;
            result = volume.doubleValue();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[42]++;
   
        } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[10]++;}
        return result;   
    }

    ///////////////////////////////////////////////////////////////////////////
    // From IntervalXYDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The value.
     */
    public Number getStartX(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[43]++;
        DatasetInfo di = getDatasetInfo(series);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((di.data instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[11]++;
            return ((IntervalXYDataset) di.data).getStartX(di.series, item);

        }
        else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[12]++;
            return getX(series, item);
        }
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The value.
     */
    public Number getEndX(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[45]++;
        DatasetInfo di = getDatasetInfo(series);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((di.data instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[13]++;
            return ((IntervalXYDataset) di.data).getEndX(di.series, item);

        }
        else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[14]++;
            return getX(series, item);
        }
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The starting Y value for the specified series and item.
     */
    public Number getStartY(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[47]++;
        DatasetInfo di = getDatasetInfo(series);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((di.data instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[15]++;
            return ((IntervalXYDataset) di.data).getStartY(di.series, item);

        }
        else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[16]++;
            return getY(series, item);
        }
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The ending Y value for the specified series and item.
     */
    public Number getEndY(int series, int item) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[49]++;
        DatasetInfo di = getDatasetInfo(series);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((di.data instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[17]++;
            return ((IntervalXYDataset) di.data).getEndY(di.series, item);

        }
        else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[18]++;
            return getY(series, item);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // New methods from CombinationDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the parent Dataset of this combination. If there is more than
     * one parent, or a child is found that is not a CombinationDataset, then
     * returns <code>null</code>.
     *
     * @return The parent Dataset of this combination or <code>null</code>.
     */
    public SeriesDataset getParent() {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[51]++;

        SeriesDataset parent = null;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[52]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < this.datasetInfo.size()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[7]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[8]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[9]++;
}
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[53]++;
            SeriesDataset child = getDatasetInfo(i).data;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((child instanceof CombinationDataset) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[19]++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[55]++;
                SeriesDataset childParent 
                    = ((CombinationDataset) child).getParent();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[21]++;
                    parent = childParent;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[57]++;

                }
                else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[22]++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[58]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((parent != childParent) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[23]++;
                    return null;

                } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[24]++;}
}

            }
            else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[20]++;
                return null;
            }
        }
        return parent;

    }

    /**
     * Returns a map or indirect indexing form our series into parent's series.
     * Prior to calling this method, the client should check getParent() to make
     * sure the CombinationDataset uses the same parent. If not, the map
     * returned by this method will be invalid or null.
     *
     * @return A map or indirect indexing form our series into parent's series.
     *
     * @see #getParent()
     */
    public int[] getMap() {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[59]++;

        int[] map = null;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[60]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[10]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < this.datasetInfo.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[10]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[11]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[12]++;
}
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[61]++;
            SeriesDataset child = getDatasetInfo(i).data;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((child instanceof CombinationDataset) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[25]++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[63]++;
                int[] childMap = ((CombinationDataset) child).getMap();
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[64]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((childMap == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[27]++;
                    return null;

                } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[28]++;}
                map = joinMap(map, childMap);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[65]++;

            }
            else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[26]++;
                return null;
            }
        }
        return map;
    }

    ///////////////////////////////////////////////////////////////////////////
    // New Methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the child position.
     *
     * @param child  the child dataset.
     *
     * @return The position.
     */
    public int getChildPosition(Dataset child) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[66]++;

        int n = 0;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[67]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < this.datasetInfo.size()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[13]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[14]--;
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.loops[15]++;
}
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[68]++;
            SeriesDataset childDataset = getDatasetInfo(i).data;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[69]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((childDataset instanceof CombinedDataset) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[29]++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[70]++;
                int m = ((CombinedDataset) childDataset)
                    .getChildPosition(child);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[71]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((m >= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[31]++;
                    return n + m;

                } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[32]++;}
                n++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[72]++;

            }
            else {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[30]++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[73]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((child == childDataset) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[33]++;
                    return n;

                } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[34]++;}
                n++;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[74]++;
            }
        }
        return -1;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the DatasetInfo object associated with the series.
     *
     * @param series  the index of the series.
     *
     * @return The DatasetInfo object associated with the series.
     */
    private DatasetInfo getDatasetInfo(int series) {
        return (DatasetInfo) this.datasetInfo.get(series);
    }

    /**
     * Joins two map arrays (int[]) together.
     *
     * @param a  the first array.
     * @param b  the second array.
     *
     * @return A copy of { a[], b[] }.
     */
    private int[] joinMap(int[] a, int[] b) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[75]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((a == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[35]++;
            return b;

        } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[36]++;}
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[76]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[37]++;
            return a;

        } else {
  CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.branches[38]++;}
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[77]++;
        int[] result = new int[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[78]++;
        System.arraycopy(b, 0, result, a.length, b.length);
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[79]++;
        return result;
    }

    /**
     * Private class to store as pairs (SeriesDataset, series) for all combined
     * series.
     */
    private class DatasetInfo {

        /** The dataset. */
        private SeriesDataset data;

        /** The series. */
        private int series;

        /**
         * Creates a new dataset info record.
         *
         * @param data  the dataset.
         * @param series  the series.
         */
        DatasetInfo(SeriesDataset data, int series) {
            this.data = data;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[80]++;
            this.series = series;
CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd.statements[81]++;
        }
    }

}

class CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd ());
  }
    public static long[] statements = new long[82];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "org.jfree.data.general.CombinedDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$7vku7xdx1tpzpqbbe9q6o9plrraqlpd () {
    super("org.jfree.data.general.CombinedDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 81; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.CombinedDataset.java");
      for (int i = 1; i <= 81; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
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
      for (int i = 1; i <= 5; i++) {
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

