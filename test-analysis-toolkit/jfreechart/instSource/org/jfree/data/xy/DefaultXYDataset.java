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
 * DefaultXYDataset.java
 * ---------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Jul-2006 : Version 1 (DG);
 * 02-Nov-2006 : Fixed a problem with adding a new series with the same key
 *               as an existing series (see bug 1589392) (DG);
 * 25-Jan-2007 : Implemented PublicCloneable (DG);
 *
 */

package org.jfree.data.xy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.util.PublicCloneable;

/**
 * A default implementation of the {@link XYDataset} interface that stores
 * data values in arrays of double primitives.
 * 
 * @since 1.0.2
 */
public class DefaultXYDataset extends AbstractXYDataset 
        implements XYDataset, PublicCloneable {
  static {
    CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.ping();
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
     * Creates a new <code>DefaultXYDataset</code> instance, initially 
     * containing no data.
     */
    public DefaultXYDataset() {
        this.seriesKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[1]++;
        this.seriesList = new java.util.ArrayList();
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[2]++;    
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
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[1]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[2]++;}
        return (Comparable) this.seriesKeys.get(series);
    }

    /**
     * Returns the index of the series with the specified key, or -1 if there 
     * is no such series in the dataset.
     * 
     * @param seriesKey  the series key (<code>null</code> permitted).
     * 
     * @return The index, or -1.
     */
    public int indexOf(Comparable seriesKey) {
        return this.seriesKeys.indexOf(seriesKey);
    }

    /**
     * Returns the order of the domain (x-) values in the dataset.  In this
     * implementation, we cannot guarantee that the x-values are ordered, so 
     * this method returns <code>DomainOrder.NONE</code>.
     * 
     * @return <code>DomainOrder.NONE</code>.
     */
    public DomainOrder getDomainOrder() {
        return DomainOrder.NONE;
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
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[3]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[4]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[5]++;
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
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[6]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[0][item];
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
     * @see #getY(int, int)
     */
    public double getYValue(int series, int item) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[7]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[1][item];
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
     * @see #getX(int, int)
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
     * @param data  the data (must be an array with length 2, containing two 
     *     arrays of equal length, the first containing the x-values and the
     *     second containing the y-values). 
     */
    public void addSeries(Comparable seriesKey, double[][] data) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seriesKey == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[5]++;
            throw new IllegalArgumentException(
                    "The 'seriesKey' cannot be null.");

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[6]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[7]++;
            throw new IllegalArgumentException("The 'data' is null.");

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[8]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((data.length != 2) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[9]++;
            throw new IllegalArgumentException(
                    "The 'data' array must have length == 2.");

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[10]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[11]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((data[0].length != data[1].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[11]++;
            throw new IllegalArgumentException(
                "The 'data' array must contain two arrays with equal length.");

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[12]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[12]++;
        int seriesIndex = indexOf(seriesKey);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((seriesIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[13]++;  // add a new series
            this.seriesKeys.add(seriesKey);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[14]++;
            this.seriesList.add(data);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[15]++;

        }
        else {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[14]++;  // replace an existing series
            this.seriesList.remove(seriesIndex);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[16]++;
            this.seriesList.add(seriesIndex, data);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[17]++;
        }
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[18]++;
    }

    /**
     * Removes a series from the dataset, then sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @param seriesKey  the series key (<code>null</code> not permitted).
     * 
     */
    public void removeSeries(Comparable seriesKey) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[19]++;
        int seriesIndex = indexOf(seriesKey);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((seriesIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[15]++;
            this.seriesKeys.remove(seriesIndex);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[21]++;
            this.seriesList.remove(seriesIndex);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[22]++;
            notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[23]++;

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[16]++;}
    }
    
    /**
     * Tests this <code>DefaultXYDataset</code> instance for equality with an
     * arbitrary object.  This method returns <code>true</code> if and only if:
     * <ul>
     * <li><code>obj</code> is not <code>null</code>;</li>
     * <li><code>obj</code> is an instance of 
     *         <code>DefaultXYDataset</code>;</li>
     * <li>both datasets have the same number of series, each containing 
     *         exactly the same values.</li>
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[17]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[18]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[20]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[26]++;
        DefaultXYDataset that = (DefaultXYDataset) obj;
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[27]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.seriesKeys.equals(that.seriesKeys)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[22]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < this.seriesList.size()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[1]--;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[2]--;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[3]++;
}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[29]++;
            double[][] d1 = (double[][]) this.seriesList.get(i);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[30]++;
            double[][] d2 = (double[][]) that.seriesList.get(i);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[31]++;
            double[] d1x = d1[0];
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[32]++;
            double[] d2x = d2[0];
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1x, d2x)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[23]++;
                return false;

            } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[24]++;}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[34]++;
            double[] d1y = d1[1];
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[35]++;
            double[] d2y = d2[1];
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[36]++;
int CodeCoverConditionCoverageHelper_C14;            
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1y, d2y)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[25]++;
                return false;

            } else {
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.branches[26]++;}
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
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[37]++;
        result = 29 * result + this.seriesList.hashCode();
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[38]++;
        return result;
    }
    
    /**
     * Creates an independent copy of this dataset.
     * 
     * @return The cloned dataset.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning the
     *     dataset (for instance, if a non-cloneable object is used for a
     *     series key).
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[39]++;
        DefaultXYDataset clone = (DefaultXYDataset) super.clone();
        clone.seriesKeys = new java.util.ArrayList(this.seriesKeys);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[40]++;
        clone.seriesList = new ArrayList(this.seriesList.size());
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[41]++;
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[42]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[4]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < this.seriesList.size()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[4]--;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[5]--;
  CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.loops[6]++;
}
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[43]++;
            double[][] data = (double[][]) this.seriesList.get(i);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[44]++;
            double[] x = data[0];
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[45]++;
            double[] y = data[1];
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[46]++;
            double[] xx = new double[x.length];
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[47]++;
            double[] yy = new double[y.length];
            System.arraycopy(x, 0, xx, 0, x.length);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[48]++;
            System.arraycopy(y, 0, yy, 0, y.length);
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[49]++;
            clone.seriesList.add(i, new double[][] {xx, yy});
CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5.statements[50]++;
        }
        return clone;
    }

}

class CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5 ());
  }
    public static long[] statements = new long[51];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.DefaultXYDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$1ktak0tfsu4og4k4vv42u4q928desqsf5 () {
    super("org.jfree.data.xy.DefaultXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 50; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.DefaultXYDataset.java");
      for (int i = 1; i <= 50; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

