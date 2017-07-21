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
 * DefaultWindDataset.java
 * -----------------------
 * (C) Copyright 2001-2007, by Achilleus Mantzios and Contributors.
 *
 * Original Author:  Achilleus Mantzios;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 06-Feb-2002 : Version 1, based on code contributed by Achilleus 
 *               Mantzios (DG);
 * 05-May-2004 : Now extends AbstractXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * A default implementation of the {@link WindDataset} interface.
 */
public class DefaultWindDataset extends AbstractXYDataset 
                                implements WindDataset {
  static {
    CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.ping();
  }


    /** The keys for the series. */
    private List seriesKeys;

    /** Storage for the series data. */
    private List allSeriesData;

    /**
     * Constructs a new, empty, dataset.  Since there are currently no methods
     * to add data to an existing dataset, you should probably use a different
     * constructor.
     */
    public DefaultWindDataset() {
        this.seriesKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[1]++;
        this.allSeriesData = new java.util.ArrayList();
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[2]++;
    }

    /**
     * Constructs a dataset based on the specified data array.
     *
     * @param data  the data (<code>null</code> not permitted).
     * 
     * @throws NullPointerException if <code>data</code> is <code>null</code>.
     */
    public DefaultWindDataset(Object[][][] data) {
        this(seriesNameListFromDataArray(data), data);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[3]++;
    }

    /**
     * Constructs a dataset based on the specified data array.
     *
     * @param seriesNames  the names of the series (<code>null</code> not 
     *     permitted).
     * @param data  the wind data.
     * 
     * @throws NullPointerException if <code>seriesNames</code> is 
     *     <code>null</code>.
     */
    public DefaultWindDataset(String[] seriesNames, Object[][][] data) {
        this(Arrays.asList(seriesNames), data);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[4]++;
    }

    /**
     * Constructs a dataset based on the specified data array.  The array
     * can contain multiple series, each series can contain multiple items,
     * and each item is as follows:
     * <ul>
     * <li><code>data[series][item][0]</code> - the date (either a 
     *   <code>Date</code> or a <code>Number</code> that is the milliseconds 
     *   since 1-Jan-1970);</li>
     * <li><code>data[series][item][1]</code> - the wind direction (1 - 12, 
     *   like the numbers on a clock face);</li>
     * <li><code>data[series][item][2]</code> - the wind force (1 - 12 on the
     *   Beaufort scale)</li>
     * </ul>
     * 
     * @param seriesKeys  the names of the series (<code>null</code> not 
     *     permitted).
     * @param data  the wind dataset (<code>null</code> not permitted).
     * 
     * @throws IllegalArgumentException if <code>seriesKeys</code> is 
     *     <code>null</code>.
     * @throws IllegalArgumentException if the number of series keys does not
     *     match the number of series in the array.
     * @throws NullPointerException if <code>data</code> is <code>null</code>.
     */
    public DefaultWindDataset(List seriesKeys, Object[][][] data) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((seriesKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[1]++;
            throw new IllegalArgumentException("Null 'seriesKeys' argument.");

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[2]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((seriesKeys.size() != data.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[3]++;
            throw new IllegalArgumentException("The number of series keys does "
                    + "not match the number of series in the data array.");

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[4]++;}
        this.seriesKeys = seriesKeys;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[7]++;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[8]++;
        int seriesCount = data.length;
        this.allSeriesData = new java.util.ArrayList(seriesCount);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[9]++;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;

        for (int seriesIndex = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seriesIndex < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); seriesIndex++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[1]--;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[2]--;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[3]++;
}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[11]++;
            List oneSeriesData = new java.util.ArrayList();
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[12]++;
            int maxItemCount = data[seriesIndex].length;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
            for (int itemIndex = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((itemIndex < maxItemCount) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); itemIndex++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[4]--;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[5]--;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[6]++;
}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[14]++;
                Object xObject = data[seriesIndex][itemIndex][0];
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((xObject != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[5]++;
                    Number xNumber;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((xObject instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[7]++;
                        xNumber = (Number) xObject;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[17]++;

                    }
                    else {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[8]++;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
                        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((xObject instanceof Date) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[9]++;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[19]++;
                            Date xDate = (Date) xObject;
                            xNumber = new Long(xDate.getTime());
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[20]++;

                        }
                        else {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[10]++;
                            xNumber = new Integer(0);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[21]++;
                        }
                    }
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[22]++;
                    Number windDir = (Number) data[seriesIndex][itemIndex][1];
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[23]++;
                    Number windForce = (Number) data[seriesIndex][itemIndex][2];
                    oneSeriesData.add(new WindDataItem(xNumber, windDir, 
                            windForce));
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[24]++;

                } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[6]++;}
            }
            Collections.sort(oneSeriesData);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[25]++;
            this.allSeriesData.add(seriesIndex, oneSeriesData);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[26]++;
        }

    }

    /**
     * Returns the number of series in the dataset.
     * 
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.allSeriesData.size();
    }

    /**
     * Returns the number of items in a series.
     * 
     * @param series  the series (zero-based index).
     * 
     * @return The item count.
     */
    public int getItemCount(int series) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[11]++;
            throw new IllegalArgumentException("Invalid series index: " 
                    + series);

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[12]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[28]++;
        List oneSeriesData = (List) this.allSeriesData.get(series);
        return oneSeriesData.size();
    }

    /**
     * Returns the key for a series.
     * 
     * @param series  the series (zero-based index).
     * 
     * @return The series key.
     */
    public Comparable getSeriesKey(int series) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[13]++;
            throw new IllegalArgumentException("Invalid series index: " 
                    + series);

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[14]++;}
        return (Comparable) this.seriesKeys.get(series);
    }

    /**
     * Returns the x-value for one item within a series.  This should represent
     * a point in time, encoded as milliseconds in the same way as
     * java.util.Date.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The x-value for the item within the series.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[30]++;
        List oneSeriesData = (List) this.allSeriesData.get(series);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[31]++;
        WindDataItem windItem = (WindDataItem) oneSeriesData.get(item);
        return windItem.getX();
    }

    /**
     * Returns the y-value for one item within a series.  This maps to the
     * {@link #getWindForce(int, int)} method and is implemented because 
     * <code>WindDataset</code> is an extension of {@link XYDataset}.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The y-value for the item within the series.
     */
    public Number getY(int series, int item) {
        return getWindForce(series, item);
    }

    /**
     * Returns the wind direction for one item within a series.  This is a
     * number between 0 and 12, like the numbers on an upside-down clock face.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The wind direction for the item within the series.
     */
    public Number getWindDirection(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[32]++;
        List oneSeriesData = (List) this.allSeriesData.get(series);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[33]++;
        WindDataItem windItem = (WindDataItem) oneSeriesData.get(item);
        return windItem.getWindDirection();
    }

    /**
     * Returns the wind force for one item within a series.  This is a number
     * between 0 and 12, as defined by the Beaufort scale.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The wind force for the item within the series.
     */
    public Number getWindForce(int series, int item) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[34]++;
        List oneSeriesData = (List) this.allSeriesData.get(series);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[35]++;
        WindDataItem windItem = (WindDataItem) oneSeriesData.get(item);
        return windItem.getWindForce();
    }

    /**
     * Utility method for automatically generating series names.
     * 
     * @param data  the wind data (<code>null</code> not permitted).
     *
     * @return An array of <i>Series N</i> with N = { 1 .. data.length }.
     * 
     * @throws NullPointerException if <code>data</code> is <code>null</code>.
     */
    public static List seriesNameListFromDataArray(Object[][] data) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[36]++;

        int seriesCount = data.length;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[37]++;
        List seriesNameList = new java.util.ArrayList(seriesCount);
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[7]--;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[8]--;
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.loops[9]++;
}
            seriesNameList.add("Series " + (i + 1));
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[39]++;
        }
        return seriesNameList;

    }
    
    /**
     * Checks this <code>WindDataset</code> for equality with an arbitrary
     * object.  This method returns <code>true</code> if and only if:
     * <ul>
     *   <li><code>obj</code> is not <code>null</code>;</li>
     *   <li><code>obj</code> is an instance of 
     *       <code>DefaultWindDataset</code>;</li>
     *   <li>both datasets have the same number of series containing identical
     *       values.</li>
     * <ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[16]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultWindDataset) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[18]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[42]++;
        DefaultWindDataset that = (DefaultWindDataset) obj;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.seriesKeys.equals(that.seriesKeys)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[20]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.allSeriesData.equals(that.allSeriesData)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[22]++;}
        return true;
    }

}

/**
 * A wind data item.
 */
class WindDataItem implements Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.ping();
  }


    /** The x-value. */
    private Number x;

    /** The wind direction. */
    private Number windDir;

    /** The wind force. */
    private Number windForce;

    /**
     * Creates a new wind data item.
     *
     * @param x  the x-value.
     * @param windDir  the direction.
     * @param windForce  the force.
     */
    public WindDataItem(Number x, Number windDir, Number windForce) {
        this.x = x;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[45]++;
        this.windDir = windDir;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[46]++;
        this.windForce = windForce;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[47]++;
    }

    /**
     * Returns the x-value.
     *
     * @return The x-value.
     */
    public Number getX() {
        return this.x;
    }

    /**
     * Returns the wind direction.
     *
     * @return The wind direction.
     */
    public Number getWindDirection() {
        return this.windDir;
    }

    /**
     * Returns the wind force.
     *
     * @return The wind force.
     */
    public Number getWindForce() {
        return this.windForce;
    }

    /**
     * Compares this item to another object.
     *
     * @param object  the other object.
     *
     * @return An int that indicates the relative comparison.
     */
    public int compareTo(Object object) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[48]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((object instanceof WindDataItem) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[23]++;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[49]++;
            WindDataItem item = (WindDataItem) object;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[50]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.x.doubleValue() > item.x.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[25]++;
                return 1;

            }
            else {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[26]++;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[51]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.x.equals(item.x)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[27]++;
                return 0;

            }
            else {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[28]++;
                return -1;
            }
}

        }
        else {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[24]++;
            throw new ClassCastException("WindDataItem.compareTo(error)");
        }
    }
    
    /**
     * Tests this <code>WindDataItem</code> for equality with an arbitrary
     * object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[52]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[30]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[53]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj instanceof WindDataItem) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[32]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[54]++;
        WindDataItem that = (WindDataItem) obj;
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[55]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.x.equals(that.x)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[34]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[56]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.windDir.equals(that.windDir)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[36]++;}
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.statements[57]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.windForce.equals(that.windForce)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35.branches[38]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35 ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.DefaultWindDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 22; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$27sy6oy8nww1w29x8pbdu5bm4790xopu3z35 () {
    super("org.jfree.data.xy.DefaultWindDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.DefaultWindDataset.java");
      for (int i = 1; i <= 57; i++) {
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
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

