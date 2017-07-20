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
 * ---------------------
 * SubseriesDataset.java
 * ---------------------
 * (C) Copyright 2001-2007, by Bill Kelemen and Contributors.
 *
 * Original Author:  Bill Kelemen;
 * Contributor(s):   Sylvain Vieujot;
 *                   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 06-Dec-2001 : Version 1 (BK);
 * 05-Feb-2002 : Added SignalsDataset (and small change to HighLowDataset 
 *               interface) as requested by Sylvain Vieujot (DG);
 * 28-Feb-2002 : Fixed bug: missing map[series] in IntervalXYDataset and 
 *               SignalsDataset methods (BK);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 06-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 29-Nov-2005 : Removed SignalsDataset (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */

package org.jfree.data.general;

import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;

/**
 * This class will create a dataset with one or more series from another
 * {@link SeriesDataset}. 
 */
public class SubSeriesDataset extends AbstractIntervalXYDataset
                              implements OHLCDataset,
                                         IntervalXYDataset,
                                         CombinationDataset {
  static {
    CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.ping();
  }


    /** The parent dataset. */
    private SeriesDataset parent = null;
  {
    CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[1]++;
  }

    /** Storage for map. */
    private int[] map;  // maps our series into our parent's

    /**
     * Creates a SubSeriesDataset using one or more series from 
     * <code>parent</code>.  The series to use are passed as an array of int.
     *
     * @param parent  underlying dataset
     * @param map  int[] of series from parent to include in this Dataset
     */
    public SubSeriesDataset(SeriesDataset parent, int[] map) {
        this.parent = parent;
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[2]++;
        this.map = map;
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[3]++;
    }

    /**
     * Creates a SubSeriesDataset using one series from <code>parent</code>.
     * The series to is passed as an int.
     *
     * @param parent  underlying dataset
     * @param series  series from parent to include in this Dataset
     */
    public SubSeriesDataset(SeriesDataset parent, int series) {
        this(parent, new int[] {series});
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[4]++;
    }

    ///////////////////////////////////////////////////////////////////////////
    // From HighLowDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the high-value for the specified series and item.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The high-value for the specified series and item.
     */
    public Number getHigh(int series, int item) {
        return ((OHLCDataset) this.parent).getHigh(this.map[series], item);
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
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[5]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[6]++;
        Number high = getHigh(series, item);
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((high != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[1]++;
            result = high.doubleValue();
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[8]++;
   
        } else {
  CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[2]++;}
        return result;   
    }

    /**
     * Returns the low-value for the specified series and item.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The low-value for the specified series and item.
     */
    public Number getLow(int series, int item) {
        return ((OHLCDataset) this.parent).getLow(this.map[series], item);
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
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[9]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[10]++;
        Number low = getLow(series, item);
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((low != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[3]++;
            result = low.doubleValue();
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[12]++;
   
        } else {
  CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[4]++;}
        return result;   
    }

    /**
     * Returns the open-value for the specified series and item.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The open-value for the specified series and item.
     */
    public Number getOpen(int series, int item) {
        return ((OHLCDataset) this.parent).getOpen(this.map[series], item);
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
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[13]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[14]++;
        Number open = getOpen(series, item);
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((open != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[5]++;
            result = open.doubleValue();
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[16]++;
   
        } else {
  CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[6]++;}
        return result;   
    }

    /**
     * Returns the close-value for the specified series and item.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link OHLCDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The close-value for the specified series and item.
     */
    public Number getClose(int series, int item) {
        return ((OHLCDataset) this.parent).getClose(this.map[series], item);
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
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[17]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[18]++;
        Number close = getClose(series, item);
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((close != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[7]++;
            result = close.doubleValue();
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[20]++;
   
        } else {
  CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[8]++;}
        return result;   
    }

    /**
     * Returns the volume.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link OHLCDataset}.
     *
     * @param series  the series (zero based index).
     * @param item  the item (zero based index).
     *
     * @return The volume.
     */
    public Number getVolume(int series, int item) {
        return ((OHLCDataset) this.parent).getVolume(this.map[series], item);
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
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[21]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[22]++;
        Number volume = getVolume(series, item);
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((volume != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[9]++;
            result = volume.doubleValue();
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[24]++;
   
        } else {
  CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[10]++;}
        return result;   
    }

    ///////////////////////////////////////////////////////////////////////////
    // From XYDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the X-value for the specified series and item.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link XYDataset}.
     *
     * @param series  the index of the series of interest (zero-based);
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The X-value for the specified series and item.
     */
    public Number getX(int series, int item) {
        return ((XYDataset) this.parent).getX(this.map[series], item);
    }

    /**
     * Returns the Y-value for the specified series and item.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link XYDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The Y-value for the specified series and item.
     */
    public Number getY(int series, int item) {
        return ((XYDataset) this.parent).getY(this.map[series], item);
    }

    /**
     * Returns the number of items in a series.
     * <p>
     * Note: throws <code>ClassCastException</code> if the series if not from a 
     * {@link XYDataset}.
     *
     * @param series  the index of the series of interest (zero-based).
     *
     * @return The number of items in a series.
     */
    public int getItemCount(int series) {
        return ((XYDataset) this.parent).getItemCount(this.map[series]);
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
        return this.map.length;
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The name of a series.
     */
    public Comparable getSeriesKey(int series) {
        return this.parent.getSeriesKey(this.map[series]);
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
     * @return The starting X value for the specified series and item.
     */
    public Number getStartX(int series, int item) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.parent instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[11]++;
            return ((IntervalXYDataset) this.parent).getStartX(
                this.map[series], item
            );

        }
        else {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[12]++;
            return getX(series, item);
        }
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the index of the series of interest (zero-based).
     * @param item  the index of the item of interest (zero-based).
     *
     * @return The ending X value for the specified series and item.
     */
    public Number getEndX(int series, int item) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.parent instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[13]++;
            return ((IntervalXYDataset) this.parent).getEndX(
                this.map[series], item
            );

        }
        else {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[14]++;
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
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.parent instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[15]++;
            return ((IntervalXYDataset) this.parent).getStartY(
                this.map[series], item
            );

        }
        else {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[16]++;
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
    public Number getEndY(int series,  int item) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.parent instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[17]++;
            return ((IntervalXYDataset) this.parent).getEndY(
                this.map[series], item
            );

        }
        else {
CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox.branches[18]++;
            return getY(series, item);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // New methods from CombinationDataset
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns the parent Dataset of this combination.
     *
     * @return The parent Dataset of this combination.
     */
    public SeriesDataset getParent() {
        return this.parent;
    }

    /**
     * Returns a map or indirect indexing form our series into parent's series.
     *
     * @return A map or indirect indexing form our series into parent's series.
     */
    public int[] getMap() {
        return (int[]) this.map.clone();
    }

}

class CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.data.general.SubSeriesDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$1xbpqvxk84k4ak2yce1nz8ihotk8ibyox () {
    super("org.jfree.data.general.SubSeriesDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.SubSeriesDataset.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

