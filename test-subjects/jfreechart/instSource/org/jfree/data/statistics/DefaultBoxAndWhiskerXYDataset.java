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
 * ----------------------------------
 * DefaultBoxAndWhiskerXYDataset.java
 * ----------------------------------
 * (C) Copyright 2003-2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 08-Aug-2003 : Minor changes to comments (DB)
 *               Allow average to be null  - average is a perculiar AIMS 
 *               requirement which probably should be stripped out and overlaid
 *               if required...
 *               Added a number of methods to allow the max and min non-outlier
 *               and non-farout values to be calculated
 * 12-Aug-2003   Changed the getYValue to return the highest outlier value
 *               Added getters and setters for outlier and farout coefficients
 * 27-Aug-2003 : Renamed DefaultBoxAndWhiskerDataset 
 *               --> DefaultBoxAndWhiskerXYDataset (DG);
 * 06-May-2004 : Now extends AbstractXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 18-Nov-2004 : Updated for changes in RangeInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 12-Nov-2007 : Implemented equals() and clone() (DG);
 *
 */

package org.jfree.data.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A simple implementation of the {@link BoxAndWhiskerXYDataset} interface.  
 * This dataset implementation can hold only one series.
 */
public class DefaultBoxAndWhiskerXYDataset extends AbstractXYDataset 
            implements BoxAndWhiskerXYDataset, RangeInfo {
  static {
    CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.ping();
  }


    /** The series key. */
    private Comparable seriesKey;

    /** Storage for the dates. */
    private List dates;

    /** Storage for the box and whisker statistics. */
    private List items;

    /** The minimum range value. */
    private Number minimumRangeValue;

    /** The maximum range value. */
    private Number maximumRangeValue;

    /** The range of values. */
    private Range rangeBounds;

    /** 
     * The coefficient used to calculate outliers. Tukey's default value is 
     * 1.5 (see EDA) Any value which is greater than Q3 + (interquartile range 
     * * outlier coefficient) is considered to be an outlier.  Can be altered 
     * if the data is particularly skewed.
     */
    private double outlierCoefficient = 1.5;
  {
    CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[1]++;
  }

    /** 
     * The coefficient used to calculate farouts. Tukey's default value is 2 
     * (see EDA) Any value which is greater than Q3 + (interquartile range * 
     * farout coefficient) is considered to be a farout.  Can be altered if the 
     * data is particularly skewed.
     */
    private double faroutCoefficient = 2.0;
  {
    CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[2]++;
  }

    /**
     * Constructs a new box and whisker dataset.
     * <p>
     * The current implementation allows only one series in the dataset.
     * This may be extended in a future version.
     *
     * @param seriesKey  the key for the series.
     */
    public DefaultBoxAndWhiskerXYDataset(Comparable seriesKey) {
        this.seriesKey = seriesKey;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[3]++;
        this.dates = new ArrayList();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[4]++;
        this.items = new ArrayList();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[5]++;
        this.minimumRangeValue = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[6]++;
        this.maximumRangeValue = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[7]++;
        this.rangeBounds = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[8]++; 
    }

    /**
     * Returns the value used as the outlier coefficient. The outlier 
     * coefficient gives an indication of the degree of certainty in an 
     * unskewed distribution.  Increasing the coefficient increases the number 
     * of values included. Currently only used to ensure farout coefficient is 
     * greater than the outlier coefficient
     *
     * @return A <code>double</code> representing the value used to calculate 
     *         outliers.
     *         
     * @see #setOutlierCoefficient(double)
     */
    public double getOutlierCoefficient() {
        return this.outlierCoefficient;
    }

    /**
     * Sets the value used as the outlier coefficient
     *
     * @param outlierCoefficient  being a <code>double</code> representing the 
     *                            value used to calculate outliers.
     *                            
     * @see #getOutlierCoefficient()
     */
    public void setOutlierCoefficient(double outlierCoefficient) {
        this.outlierCoefficient = outlierCoefficient;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[9]++;
    }

    /**
     * Returns the value used as the farout coefficient. The farout coefficient
     * allows the calculation of which values will be off the graph.
     *
     * @return A <code>double</code> representing the value used to calculate 
     *         farouts.
     *         
     * @see #setFaroutCoefficient(double)
     */
    public double getFaroutCoefficient() {
        return this.faroutCoefficient;
    }

    /**
     * Sets the value used as the farouts coefficient. The farout coefficient 
     * must b greater than the outlier coefficient.
     * 
     * @param faroutCoefficient being a <code>double</code> representing the 
     *                          value used to calculate farouts.
     *                          
     * @see #getFaroutCoefficient()
     */
    public void setFaroutCoefficient(double faroutCoefficient) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((faroutCoefficient > getOutlierCoefficient()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[1]++;
            this.faroutCoefficient = faroutCoefficient;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[11]++;

        } 
        else {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[2]++;
            throw new IllegalArgumentException("Farout value must be greater " 
                + "than the outlier value, which is currently set at: (" 
                + getOutlierCoefficient() + ")");
        }
    }

    /**
     * Returns the number of series in the dataset.
     * <p>
     * This implementation only allows one series.
     *
     * @return The number of series.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param series  the index (zero-based) of the series.
     *
     * @return The number of items in the specified series.
     */
    public int getItemCount(int series) {
        return this.dates.size();
    }

    /**
     * Adds an item to the dataset and sends a {@link DatasetChangeEvent} to 
     * all registered listeners.
     * 
     * @param date  the date (<code>null</code> not permitted).
     * @param item  the item (<code>null</code> not permitted).
     */
    public void add(Date date, BoxAndWhiskerItem item) {
        this.dates.add(date);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[12]++;
        this.items.add(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[13]++;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.minimumRangeValue == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[3]++;
            this.minimumRangeValue = item.getMinRegularValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[15]++;

        }
        else {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[4]++;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((item.getMinRegularValue().doubleValue() 
                    < this.minimumRangeValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[5]++;
                this.minimumRangeValue = item.getMinRegularValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[17]++;

            } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[6]++;}
        }
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.maximumRangeValue == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[7]++;
            this.maximumRangeValue = item.getMaxRegularValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[19]++;

        }
        else {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[8]++;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((item.getMaxRegularValue().doubleValue() 
                    > this.maximumRangeValue.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[9]++;
                this.maximumRangeValue = item.getMaxRegularValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[21]++;

            } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[10]++;}
        }
        this.rangeBounds = new Range(this.minimumRangeValue.doubleValue(), 
                this.maximumRangeValue.doubleValue());
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[22]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[23]++;
    }
    
    /**
     * Returns the name of the series stored in this dataset.
     *
     * @param i  the index of the series. Currently ignored.
     * 
     * @return The name of this series.
     */
    public Comparable getSeriesKey(int i) {
        return this.seriesKey;
    }
    
    /**
     * Return an item from within the dataset.
     * 
     * @param series  the series index (ignored, since this dataset contains
     *                only one series).
     * @param item  the item within the series (zero-based index)
     * 
     * @return The item.
     */
    public BoxAndWhiskerItem getItem(int series, int item) {
        return (BoxAndWhiskerItem) this.items.get(item);  
    }

    /**
     * Returns the x-value for one item in a series.
     * <p>
     * The value returned is a Long object generated from the underlying Date 
     * object.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value.
     */
    public Number getX(int series, int item) {
        return new Long(((Date) this.dates.get(item)).getTime());
    }

    /**
     * Returns the x-value for one item in a series, as a Date.
     * <p>
     * This method is provided for convenience only.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value as a Date.
     */
    public Date getXDate(int series, int item) {
        return (Date) this.dates.get(item);
    }

    /**
     * Returns the y-value for one item in a series.
     * <p>
     * This method (from the XYDataset interface) is mapped to the 
     * getMeanValue() method.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The y-value.
     */
    public Number getY(int series, int item) {
        return getMeanValue(series, item);  
    }

    /**
     * Returns the mean for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The mean for the specified series and item.
     */
    public Number getMeanValue(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[24]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[25]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[11]++;
            result = stats.getMean();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[27]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[12]++;}
        return result;
    }

    /**
     * Returns the median-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The median-value for the specified series and item.
     */
    public Number getMedianValue(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[28]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[29]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[13]++;
            result = stats.getMedian();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[31]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[14]++;}
        return result;
    }

    /**
     * Returns the Q1 median-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The Q1 median-value for the specified series and item.
     */
    public Number getQ1Value(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[32]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[33]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[15]++;
            result = stats.getQ1();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[35]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[16]++;}
        return result;
    }

    /**
     * Returns the Q3 median-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The Q3 median-value for the specified series and item.
     */
    public Number getQ3Value(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[36]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[37]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[17]++;
            result = stats.getQ3();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[39]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[18]++;}
        return result;
    }

    /**
     * Returns the min-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The min-value for the specified series and item.
     */
    public Number getMinRegularValue(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[40]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[41]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[19]++;
            result = stats.getMinRegularValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[43]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[20]++;}
        return result;
    }

    /**
     * Returns the max-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The max-value for the specified series and item.
     */
    public Number getMaxRegularValue(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[44]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[45]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[21]++;
            result = stats.getMaxRegularValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[47]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[22]++;}
        return result;
    }

    /**
     * Returns the minimum value which is not a farout.
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return A <code>Number</code> representing the maximum non-farout value.
     */
    public Number getMinOutlier(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[48]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[49]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[23]++;
            result = stats.getMinOutlier();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[51]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[24]++;}
        return result;
    }
 
    /**
     * Returns the maximum value which is not a farout, ie Q3 + (interquartile 
     * range * farout coefficient).
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return A <code>Number</code> representing the maximum non-farout value.
     */
    public Number getMaxOutlier(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[52]++;
        Number result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[53]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[25]++;
            result = stats.getMaxOutlier();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[55]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[26]++;}
        return result;
    }

    /**
     * Returns an array of outliers for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The array of outliers for the specified series and item.
     */
    public List getOutliers(int series, int item) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[56]++;
        List result = null;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[57]++;
        BoxAndWhiskerItem stats = (BoxAndWhiskerItem) this.items.get(item);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[58]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((stats != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[27]++;
            result = stats.getOutliers();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[59]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[28]++;}
        return result;
    }

    /**
     * Returns the minimum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The minimum value.
     */
    public double getRangeLowerBound(boolean includeInterval) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[60]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.minimumRangeValue != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[29]++;
            result = this.minimumRangeValue.doubleValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[62]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[30]++;}
        return result;        
    }

    /**
     * Returns the maximum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The maximum value.
     */
    public double getRangeUpperBound(boolean includeInterval) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[63]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.maximumRangeValue != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[31]++;
            result = this.maximumRangeValue.doubleValue();
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[65]++;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[32]++;}
        return result;        
    }

    /**
     * Returns the range of the values in this dataset's range.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The range.
     */
    public Range getRangeBounds(boolean includeInterval) {
        return this.rangeBounds;
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[33]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[34]++;}
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultBoxAndWhiskerXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[36]++;}
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[68]++;
        DefaultBoxAndWhiskerXYDataset that 
                = (DefaultBoxAndWhiskerXYDataset) obj;
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[69]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesKey, that.seriesKey)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[38]++;}
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.dates.equals(that.dates)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[40]++;}
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[71]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.items.equals(that.items)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.branches[42]++;}
        return true;
    }
    
    /**
     * Returns a clone of the plot.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[72]++;
        DefaultBoxAndWhiskerXYDataset clone 
                = (DefaultBoxAndWhiskerXYDataset) super.clone();
        clone.dates = new java.util.ArrayList(this.dates);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[73]++;
        clone.items = new java.util.ArrayList(this.items);
CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh.statements[74]++;
        return clone;
    }

}

class CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh ());
  }
    public static long[] statements = new long[75];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$2e7b5bq3toqoksnzguyucfqfm7uvfqf77rl8xjfrao29npyxbcokh () {
    super("org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 74; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset.java");
      for (int i = 1; i <= 74; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
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

