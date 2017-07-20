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
 * DefaultXYZDataset.java
 * ----------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 12-Jul-2006 : Version 1 (DG);
 * 06-Oct-2006 : Fixed API doc warnings (DG);
 * 02-Nov-2006 : Fixed a problem with adding a new series with the same key
 *               as an existing series (see bug 1589392) (DG);
 *
 */

package org.jfree.data.xy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeEvent;

/**
 * A default implementation of the {@link XYZDataset} interface that stores
 * data values in arrays of double primitives.
 * 
 * @since 1.0.2
 */
public class DefaultXYZDataset extends AbstractXYZDataset 
        implements XYZDataset {
  static {
    CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.ping();
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
     * Creates a new <code>DefaultXYZDataset</code> instance, initially 
     * containing no data.
     */
    public DefaultXYZDataset() {
        this.seriesKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[1]++;
        this.seriesList = new java.util.ArrayList();
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[2]++;    
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
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[1]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[2]++;}
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
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[3]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[4]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[5]++;
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
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[6]++;
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
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[7]++;
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
     * Returns the z-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The z-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getZ(int, int)
     */
    public double getZValue(int series, int item) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[8]++;
        double[][] seriesData = (double[][]) this.seriesList.get(series);
        return seriesData[2][item];
    }

    /**
     * Returns the z-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The z-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     *     
     * @see #getZ(int, int)
     */
    public Number getZ(int series, int item) {
        return new Double(getZValue(series, item));
    }

    /**
     * Adds a series or if a series with the same key already exists replaces
     * the data for that series, then sends a {@link DatasetChangeEvent} to 
     * all registered listeners.
     * 
     * @param seriesKey  the series key (<code>null</code> not permitted).
     * @param data  the data (must be an array with length 3, containing three 
     *     arrays of equal length, the first containing the x-values, the
     *     second containing the y-values and the third containing the 
     *     z-values). 
     */
    public void addSeries(Comparable seriesKey, double[][] data) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seriesKey == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[5]++;
            throw new IllegalArgumentException(
                    "The 'seriesKey' cannot be null.");

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[6]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[7]++;
            throw new IllegalArgumentException("The 'data' is null.");

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[8]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((data.length != 3) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[9]++;
            throw new IllegalArgumentException(
                    "The 'data' array must have length == 3.");

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[10]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[12]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((data[0].length != data[1].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((data[0].length != data[2].length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[11]++;
            throw new IllegalArgumentException("The 'data' array must contain "
                    + "three arrays all having the same length.");

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[12]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[13]++;
        int seriesIndex = indexOf(seriesKey);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((seriesIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[13]++;  // add a new series
            this.seriesKeys.add(seriesKey);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[15]++;
            this.seriesList.add(data);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[16]++;

        }
        else {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[14]++;  // replace an existing series
            this.seriesList.remove(seriesIndex);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[17]++;
            this.seriesList.add(seriesIndex, data);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[18]++;
        }
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[19]++;
    }

    /**
     * Removes a series from the dataset, then sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @param seriesKey  the series key (<code>null</code> not permitted).
     * 
     */
    public void removeSeries(Comparable seriesKey) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[20]++;
        int seriesIndex = indexOf(seriesKey);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((seriesIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[15]++;
            this.seriesKeys.remove(seriesIndex);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[22]++;
            this.seriesList.remove(seriesIndex);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[23]++;
            notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[24]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[16]++;}
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
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[17]++;
            return true;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[18]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultXYZDataset) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[20]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[27]++;
        DefaultXYZDataset that = (DefaultXYZDataset) obj;
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.seriesKeys.equals(that.seriesKeys)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[22]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[29]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < this.seriesList.size()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[1]--;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[2]--;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[3]++;
}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[30]++;
            double[][] d1 = (double[][]) this.seriesList.get(i);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[31]++;
            double[][] d2 = (double[][]) that.seriesList.get(i);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[32]++;
            double[] d1x = d1[0];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[33]++;
            double[] d2x = d2[0];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[34]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1x, d2x)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[23]++;
                return false;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[24]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[35]++;
            double[] d1y = d1[1];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[36]++;
            double[] d2y = d2[1];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[37]++;
int CodeCoverConditionCoverageHelper_C14;            
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1y, d2y)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[25]++;
                return false;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[26]++;}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[38]++;
            double[] d1z = d1[2];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[39]++;
            double[] d2z = d2[2];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;            
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((Arrays.equals(d1z, d2z)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[27]++;
                return false;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.branches[28]++;}
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
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[41]++;
        result = 29 * result + this.seriesList.hashCode();
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[42]++;
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
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[43]++;
        DefaultXYZDataset clone = (DefaultXYZDataset) super.clone();
        clone.seriesKeys = new java.util.ArrayList(this.seriesKeys);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[44]++;
        clone.seriesList = new ArrayList(this.seriesList.size());
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[45]++;
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[46]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < this.seriesList.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[4]--;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[5]--;
  CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.loops[6]++;
}
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[47]++;
            double[][] data = (double[][]) this.seriesList.get(i);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[48]++;
            double[] x = data[0];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[49]++;
            double[] y = data[1];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[50]++;
            double[] z = data[2];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[51]++;
            double[] xx = new double[x.length];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[52]++;
            double[] yy = new double[y.length];
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[53]++;
            double[] zz = new double[z.length];
            System.arraycopy(x, 0, xx, 0, x.length);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[54]++;
            System.arraycopy(y, 0, yy, 0, y.length);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[55]++;
            System.arraycopy(z, 0, zz, 0, z.length);
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[56]++;
            clone.seriesList.add(i, new double[][] {xx, yy, zz});
CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h.statements[57]++;
        }
        return clone;
    }

}

class CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.DefaultXYZDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 16; i++) {
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

  public CodeCoverCoverageCounter$b80b2dtcd299uojuurt76l4fegnb209h0h () {
    super("org.jfree.data.xy.DefaultXYZDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.DefaultXYZDataset.java");
      for (int i = 1; i <= 57; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
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

