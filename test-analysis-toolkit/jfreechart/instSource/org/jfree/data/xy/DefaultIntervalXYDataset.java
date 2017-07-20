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
 * -----------------------------
 * DefaultIntervalXYDataset.java
 * -----------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Oct-2006 : Version 1 (DG);
 * 02-Nov-2006 : Fixed a problem with adding a new series with the same key
 *               as an existing series (see bug 1589392) (DG);
 * 28-Nov-2006 : New override for clone() (DG);
 *
 */

package org.jfree.data.xy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.data.general.DatasetChangeEvent;

/**
 * A dataset that defines a range (interval) for both the x-values and the
 * y-values.  This implementation uses six arrays to store the x, start-x,
 * end-x, y, start-y and end-y values.
 * <br><br>
 * An alternative implementation of the {@link IntervalXYDataset} interface
 * is provided by the {@link XYIntervalSeriesCollection} class.
 * 
 * @since 1.0.3
 */
public class DefaultIntervalXYDataset extends AbstractIntervalXYDataset {
  static {
    CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.ping();
  }


    /**
     * Storage for the series keys.  This list must be kept in sync with the
     * seriesList.
     */
    private List seriesKeys;
    
    /** 
     * Storage for the series in the dataset.  We use a list because the
     * order of the series is significant.  This list must be kept in sync 
     * with the seriesKeys list.
     */ 
    private List seriesList;
    
    /**
     * Creates a new <code>DefaultIntervalXYDataset</code> instance, initially 
     * containing no data.
     */
    public DefaultIntervalXYDataset() {
        this.seriesKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[1]++;
        this.seriesList = new java.util.ArrayList();
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[2]++;    
    }

    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.seriesList.size();
    }

    /**
     * Returns the key for a series.  
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     *
     * @return The key for the series.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the 
     *     specified range.
     */
    public Comparable getSeriesKey(int series) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[1]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[2]++;}
        return (Comparable) this.seriesKeys.get(series);
    }

    /**
     * Returns the number of items in the specified series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * 
     * @return The item count.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the 
     *     specified range.
     */
    public int getItemCount(int series) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[3]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[4]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[5]++;
        double[][] seriesArray = (double[][]) this.seriesList.get(series);
        return seriesArray[0].length;
    }

    /**
     * Returns the x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getX(int, int)
     */
    public double getXValue(int series, int item) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[6]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[0][item];
    }

    /**
     * Returns the y-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getY(int, int)
     */
    public double getYValue(int series, int item) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[7]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[3][item];
    }

    /**
     * Returns the starting x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The starting x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getStartX(int, int)
     */
    public double getStartXValue(int series, int item) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[8]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[1][item];
    }

    /**
     * Returns the ending x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The ending x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getEndX(int, int)
     */
    public double getEndXValue(int series, int item) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[9]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[2][item];
    }

    /**
     * Returns the starting y-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The starting y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getStartY(int, int)
     */
    public double getStartYValue(int series, int item) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[10]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[4][item];
    }

    /**
     * Returns the ending y-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The ending y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getEndY(int, int)
     */
    public double getEndYValue(int series, int item) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[11]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[5][item];
    }

    /**
     * Returns the ending x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The ending x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getEndXValue(int, int)
     */
    public Number getEndX(int series, int item) {
        return new Double(getEndXValue(series, item));
    }

    /**
     * Returns the ending y-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The ending y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getEndYValue(int, int)
     */
    public Number getEndY(int series, int item) {
        return new Double(getEndYValue(series, item));
    }

    /**
     * Returns the starting x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The starting x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getStartXValue(int, int)
     */
    public Number getStartX(int series, int item) {
        return new Double(getStartXValue(series, item));
    }

    /**
     * Returns the starting y-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The starting y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getStartYValue(int, int)
     */
    public Number getStartY(int series, int item) {
        return new Double(getStartYValue(series, item));
    }

    /**
     * Returns the x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getXValue(int, int)
     */
    public Number getX(int series, int item) {
        return new Double(getXValue(series, item));
    }

    /**
     * Returns the y-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getYValue(int, int)
     */
    public Number getY(int series, int item) {
        return new Double(getYValue(series, item));
    }
    
    /**
     * Adds a series or if a series with the same key already exists replaces
     * the data for that series, then sends a {@link DatasetChangeEvent} to 
     * all registered listeners.
     * 
     * @param seriesKey  the series key (<code>null</code> not permitted).
     * @param data  the data (must be an array with length 6, containing six 
     *     arrays of equal length, the first containing the x-values and the
     *     second containing the y-values). 
     */
    public void addSeries(Comparable seriesKey, double[][] data) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seriesKey == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[5]++;
            throw new IllegalArgumentException(
                    "The 'seriesKey' cannot be null.");

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[6]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[7]++;
            throw new IllegalArgumentException("The 'data' is null.");

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[8]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((data.length != 6) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[9]++;
            throw new IllegalArgumentException(
                    "The 'data' array must have length == 6.");

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[10]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[15]++;
        int length = data[0].length;
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (512)) == 0 || true) &&
 ((length != data[1].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (128)) == 0 || true) &&
 ((length != data[2].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((length != data[3].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((length != data[4].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((length != data[5].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 5) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 5) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[11]++;
            throw new IllegalArgumentException(
                "The 'data' array must contain two arrays with equal length.");

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[12]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[17]++;
        int seriesIndex = indexOf(seriesKey);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((seriesIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[13]++;  // add a new series
            this.seriesKeys.add(seriesKey);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[19]++;
            this.seriesList.add(data);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[20]++;

        }
        else {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[14]++;  // replace an existing series
            this.seriesList.remove(seriesIndex);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[21]++;
            this.seriesList.add(seriesIndex, data);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[22]++;
        }
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[23]++;
    }
    
    /**
     * Tests this <code>DefaultIntervalXYDataset</code> instance for equality 
     * with an arbitrary object.  This method returns <code>true</code> if and 
     * only if:
     * <ul>
     * <li><code>obj</code> is not <code>null</code>;</li>
     * <li><code>obj</code> is an instance of 
     *         <code>DefaultIntervalXYDataset</code>;</li>
     * <li>both datasets have the same number of series, each containing 
     *         exactly the same values.</li>
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[16]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultIntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[18]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[26]++;
        DefaultIntervalXYDataset that = (DefaultIntervalXYDataset) obj;
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.seriesKeys.equals(that.seriesKeys)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[20]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < this.seriesList.size()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[1]--;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[2]--;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[3]++;
}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[29]++;
            double[][] d1 = (double[][]) this.seriesList.get(i);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[30]++;
            double[][] d2 = (double[][]) that.seriesList.get(i);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[31]++;
            double[] d1x = d1[0];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[32]++;
            double[] d2x = d2[0];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[33]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1x, d2x)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[21]++;
                return false;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[22]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[34]++;
            double[] d1xs = d1[1];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[35]++;
            double[] d2xs = d2[1];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1xs, d2xs)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[23]++;
                return false;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[24]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[37]++;
            double[] d1xe = d1[2];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[38]++;
            double[] d2xe = d2[2];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1xe, d2xe)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[25]++;
                return false;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[26]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[40]++;
            double[] d1y = d1[3];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[41]++;
            double[] d2y = d2[3];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;            
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1y, d2y)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[27]++;
                return false;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[28]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[43]++;
            double[] d1ys = d1[4];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[44]++;
            double[] d2ys = d2[4];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[45]++;
int CodeCoverConditionCoverageHelper_C16;            
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1ys, d2ys)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[29]++;
                return false;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[30]++;}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[46]++;
            double[] d1ye = d1[5];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[47]++;
            double[] d2ye = d2[5];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[48]++;
int CodeCoverConditionCoverageHelper_C17;            
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1ye, d2ye)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[31]++;
                return false;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.branches[32]++;}
        }
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = this.seriesKeys.hashCode();
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[49]++;
        result = 29 * result + this.seriesList.hashCode();
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[50]++;
        return result;
    }
    
    /**
     * Returns a clone of this dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the dataset contains a series with
     *         a key that cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[51]++;
        DefaultIntervalXYDataset clone 
                = (DefaultIntervalXYDataset) super.clone();
        clone.seriesKeys = new java.util.ArrayList(this.seriesKeys);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[52]++;
        clone.seriesList = new ArrayList(this.seriesList.size());
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[53]++;
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[54]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < this.seriesList.size()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[4]--;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[5]--;
  CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.loops[6]++;
}
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[55]++;
            double[][] data = (double[][]) this.seriesList.get(i);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[56]++;
            double[] x = data[0];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[57]++;
            double[] xStart = data[1];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[58]++;
            double[] xEnd = data[2];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[59]++;
            double[] y = data[3];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[60]++;
            double[] yStart = data[4];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[61]++;
            double[] yEnd = data[5];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[62]++;
            double[] xx = new double[x.length];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[63]++;
            double[] xxStart = new double[xStart.length];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[64]++;
            double[] xxEnd = new double[xEnd.length];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[65]++;
            double[] yy = new double[y.length];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[66]++;
            double[] yyStart = new double[yStart.length];
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[67]++;
            double[] yyEnd = new double[yEnd.length];
            System.arraycopy(x, 0, xx, 0, x.length);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[68]++;
            System.arraycopy(xStart, 0, xxStart, 0, xStart.length);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[69]++;
            System.arraycopy(xEnd, 0, xxEnd, 0, xEnd.length);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[70]++;
            System.arraycopy(y, 0, yy, 0, y.length);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[71]++;
            System.arraycopy(yStart, 0, yyStart, 0, yStart.length);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[72]++;
            System.arraycopy(yEnd, 0, yyEnd, 0, yEnd.length);
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[73]++;
            clone.seriesList.add(i, new double[][] {xx, xxStart, xxEnd, yy, 
                    yyStart, yyEnd});
CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5.statements[74]++;
        }
        return clone;
    }

}

class CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5 ());
  }
    public static long[] statements = new long[75];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.DefaultIntervalXYDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$656f9cjx0e7mcmlbt4b7lk9uuc23zlej57om57ked1un5 () {
    super("org.jfree.data.xy.DefaultIntervalXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 74; i++) {
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
    log.startNamedSection("org.jfree.data.xy.DefaultIntervalXYDataset.java");
      for (int i = 1; i <= 74; i++) {
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

