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
 * --------------------------------------
 * DefaultStatisticalCategoryDataset.java
 * --------------------------------------
 * (C) Copyright 2002-2007, by Pascal Collet and Contributors.
 *
 * Original Author:  Pascal Collet;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 21-Aug-2002 : Version 1, contributed by Pascal Collet (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 05-Feb-2003 : Revised implementation to use KeyedObjects2D (DG);
 * 28-Aug-2003 : Moved from org.jfree.data --> org.jfree.data.statistics (DG);
 * 06-Oct-2003 : Removed incorrect Javadoc text (DG);
 * 18-Nov-2004 : Updated for changes in RangeInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 01-Feb-2005 : Changed minimumRangeValue and maximumRangeValue from Double
 *               to double (DG);
 * 05-Feb-2005 : Implemented equals() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 08-Aug-2006 : Reworked implementation of RangeInfo methods (DG);
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 28-Sep-2007 : Fixed cloning bug (DG);
 * 02-Oct-2007 : Fixed bug updating cached range values (DG);
 * 
 */

package org.jfree.data.statistics;

import java.util.List;

import org.jfree.data.KeyedObjects2D;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.util.PublicCloneable;

/**
 * A convenience class that provides a default implementation of the
 * {@link StatisticalCategoryDataset} interface.
 */
public class DefaultStatisticalCategoryDataset extends AbstractDataset
        implements StatisticalCategoryDataset, RangeInfo, PublicCloneable {
  static {
    CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.ping();
  }


    /** Storage for the data. */
    private KeyedObjects2D data;

    /** The minimum range value. */
    private double minimumRangeValue;

    /** The row index for the minimum range value. */
    private int minimumRangeValueRow;
    
    /** The column index for the minimum range value. */
    private int minimumRangeValueColumn;
    
    /** The minimum range value including the standard deviation. */
    private double minimumRangeValueIncStdDev;
    
    /** 
     * The row index for the minimum range value (including the standard 
     * deviation). 
     */
    private int minimumRangeValueIncStdDevRow;
    
    /** 
     * The column index for the minimum range value (including the standard 
     * deviation). 
     */
    private int minimumRangeValueIncStdDevColumn;
    
    /** The maximum range value. */
    private double maximumRangeValue;
    
    /** The row index for the maximum range value. */
    private int maximumRangeValueRow;
    
    /** The column index for the maximum range value. */
    private int maximumRangeValueColumn;

    /** The maximum range value including the standard deviation. */
    private double maximumRangeValueIncStdDev;

    /** 
     * The row index for the maximum range value (including the standard 
     * deviation). 
     */
    private int maximumRangeValueIncStdDevRow;
    
    /** 
     * The column index for the maximum range value (including the standard 
     * deviation). 
     */
    private int maximumRangeValueIncStdDevColumn;
    
    /**
     * Creates a new dataset.
     */
    public DefaultStatisticalCategoryDataset() {
        this.data = new KeyedObjects2D();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[1]++;
        this.minimumRangeValue = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[2]++;
        this.minimumRangeValueRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[3]++;
        this.minimumRangeValueColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[4]++;
        this.maximumRangeValue = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[5]++;
        this.maximumRangeValueRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[6]++;
        this.maximumRangeValueColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[7]++;
        this.minimumRangeValueIncStdDev = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[8]++;
        this.minimumRangeValueIncStdDevRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[9]++;
        this.minimumRangeValueIncStdDevColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[10]++;
        this.maximumRangeValueIncStdDev = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[11]++;
        this.maximumRangeValueIncStdDevRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[12]++;
        this.maximumRangeValueIncStdDevColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[13]++;
    }

    /**
     * Returns the mean value for an item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The mean value (possibly <code>null</code>).
     */
    public Number getMeanValue(int row, int column) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[14]++;
        Number result = null;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[15]++;
        MeanAndStandardDeviation masd = (MeanAndStandardDeviation) 
                this.data.getObject(row, column);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((masd != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[1]++;
            result = masd.getMean();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[17]++;

        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[2]++;}
        return result;
    }

    /**
     * Returns the value for an item (for this dataset, the mean value is
     * returned).
     *
     * @param row  the row index.
     * @param column  the column index.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue(int row, int column) {
        return getMeanValue(row, column);
    }

    /**
     * Returns the value for an item (for this dataset, the mean value is
     * returned).
     *
     * @param rowKey  the row key.
     * @param columnKey  the columnKey.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue(Comparable rowKey, Comparable columnKey) {
        return getMeanValue(rowKey, columnKey);
    }

    /**
     * Returns the mean value for an item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the columnKey.
     *
     * @return The mean value (possibly <code>null</code>).
     */
    public Number getMeanValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[18]++;
        Number result = null;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[19]++;
        MeanAndStandardDeviation masd = (MeanAndStandardDeviation) 
                this.data.getObject(rowKey, columnKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((masd != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[3]++;
            result = masd.getMean();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[21]++;

        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[4]++;}
        return result;
    }

    /**
     * Returns the standard deviation value for an item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The standard deviation (possibly <code>null</code>).
     */
    public Number getStdDevValue(int row, int column) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[22]++;
        Number result = null;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[23]++;
        MeanAndStandardDeviation masd = (MeanAndStandardDeviation) 
                this.data.getObject(row, column);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((masd != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[5]++;
            result = masd.getStandardDeviation();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[25]++;

        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[6]++;}
        return result;
    }

    /**
     * Returns the standard deviation value for an item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the columnKey.
     *
     * @return The standard deviation (possibly <code>null</code>).
     */
    public Number getStdDevValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[26]++;
        Number result = null;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[27]++;
        MeanAndStandardDeviation masd = (MeanAndStandardDeviation) 
                this.data.getObject(rowKey, columnKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((masd != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[7]++;
            result = masd.getStandardDeviation();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[29]++;

        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[8]++;}
        return result;
    }

    /**
     * Returns the column index for a given key.
     *
     * @param key  the column key (<code>null</code> not permitted).
     *
     * @return The column index.
     */
    public int getColumnIndex(Comparable key) {
        // defer null argument check
        return this.data.getColumnIndex(key);
    }

    /**
     * Returns a column key.
     *
     * @param column  the column index (zero-based).
     *
     * @return The column key.
     */
    public Comparable getColumnKey(int column) {
        return this.data.getColumnKey(column);
    }

    /**
     * Returns the column keys.
     *
     * @return The keys.
     */
    public List getColumnKeys() {
        return this.data.getColumnKeys();
    }

    /**
     * Returns the row index for a given key.
     *
     * @param key  the row key (<code>null</code> not permitted).
     *
     * @return The row index.
     */
    public int getRowIndex(Comparable key) {
        // defer null argument check
        return this.data.getRowIndex(key);
    }

    /**
     * Returns a row key.
     *
     * @param row  the row index (zero-based).
     *
     * @return The row key.
     */
    public Comparable getRowKey(int row) {
        return this.data.getRowKey(row);
    }

    /**
     * Returns the row keys.
     *
     * @return The keys.
     */
    public List getRowKeys() {
        return this.data.getRowKeys();
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return The row count.
     * 
     * @see #getColumnCount()
     */
    public int getRowCount() {
        return this.data.getRowCount();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return The column count.
     * 
     * @see #getRowCount()
     */
    public int getColumnCount() {
        return this.data.getColumnCount();
    }

    /**
     * Adds a mean and standard deviation to the table.
     *
     * @param mean  the mean.
     * @param standardDeviation  the standard deviation.
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     */
    public void add(double mean, double standardDeviation,
                    Comparable rowKey, Comparable columnKey) {
        add(new Double(mean), new Double(standardDeviation), rowKey, columnKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[30]++;
    }

    /**
     * Adds a mean and standard deviation to the table.
     *
     * @param mean  the mean.
     * @param standardDeviation  the standard deviation.
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     */
    public void add(Number mean, Number standardDeviation,
                    Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[31]++;
        MeanAndStandardDeviation item = new MeanAndStandardDeviation(
                mean, standardDeviation);
        this.data.addObject(item, rowKey, columnKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[32]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[33]++;
        
        double m = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[34]++;
        double sd = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((mean != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[9]++;
            m = mean.doubleValue();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[36]++;

        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[10]++;}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[37]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((standardDeviation != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[11]++;
            sd = standardDeviation.doubleValue();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[38]++;
   
        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[12]++;}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[39]++;
        
        // update cached range values
        int r = this.data.getColumnIndex(columnKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[40]++;
        int c = this.data.getRowIndex(rowKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C7 |= (32768)) == 0 || true) &&
 ((r == this.maximumRangeValueRow) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16384)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (8192)) == 0 || true) &&
 ((c 
                == this.maximumRangeValueColumn) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4096)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C7 |= (2048)) == 0 || true) &&
 ((r 
                == this.maximumRangeValueIncStdDevRow) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (512)) == 0 || true) &&
 ((c 
                == this.maximumRangeValueIncStdDevColumn) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (256)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C7 |= (128)) == 0 || true) &&
 ((r 
                == this.minimumRangeValueRow) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((c 
                == this.minimumRangeValueColumn) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((r 
                == this.minimumRangeValueIncStdDevRow) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c 
                == this.minimumRangeValueIncStdDevColumn) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 8) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 8) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[13]++;
            
            // iterate over all data items and update mins and maxes
            updateBounds();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[42]++;

        } 
        else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[14]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Double.isNaN(m)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[15]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((Double.isNaN(this.maximumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((m > this.maximumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[17]++;
                    this.maximumRangeValue = m;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[45]++;
                    this.maximumRangeValueRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[46]++;
                    this.maximumRangeValueColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[47]++;

                } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[18]++;}

            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[16]++;}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
        
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((Double.isNaN(m + sd)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[19]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((Double.isNaN(this.maximumRangeValueIncStdDev)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 (((m + sd) > this.maximumRangeValueIncStdDev) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[21]++;
                    this.maximumRangeValueIncStdDev = m + sd;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[50]++;
                    this.maximumRangeValueIncStdDevRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[51]++;
                    this.maximumRangeValueIncStdDevColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[52]++;

                } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[22]++;}

            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[20]++;}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;

            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((Double.isNaN(m)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[23]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((Double.isNaN(this.minimumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((m < this.minimumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[25]++;
                    this.minimumRangeValue = m;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[55]++;
                    this.minimumRangeValueRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[56]++;
                    this.minimumRangeValueColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[57]++;

                } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[26]++;}

            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[24]++;}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[58]++;
int CodeCoverConditionCoverageHelper_C14;

            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Double.isNaN(m - sd)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[27]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((Double.isNaN(this.minimumRangeValueIncStdDev)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (((m - sd) < this.minimumRangeValueIncStdDev) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[29]++;
                    this.minimumRangeValueIncStdDev = m - sd;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[60]++;
                    this.minimumRangeValueIncStdDevRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[61]++;
                    this.minimumRangeValueIncStdDevColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[62]++;

                } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[30]++;}

            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[28]++;}
        }
        fireDatasetChanged();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[63]++;
    }
    
    /**
     * Removes an item from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #add(double, double, Comparable, Comparable)
     * 
     * @since 1.0.7
     */
    public void remove(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[64]++;
        // defer null argument checks
        int r = getRowIndex(rowKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[65]++;
        int c = getColumnIndex(columnKey);
        this.data.removeObject(rowKey, columnKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[66]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
        
        // if this cell held a maximum and/or minimum value, we'll need to
        // update the cached bounds...
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C16 |= (32768)) == 0 || true) &&
 ((r == this.maximumRangeValueRow) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16384)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (8192)) == 0 || true) &&
 ((c 
                == this.maximumRangeValueColumn) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4096)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C16 |= (2048)) == 0 || true) &&
 ((r 
                == this.maximumRangeValueIncStdDevRow) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (512)) == 0 || true) &&
 ((c 
                == this.maximumRangeValueIncStdDevColumn) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (256)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C16 |= (128)) == 0 || true) &&
 ((r 
                == this.minimumRangeValueRow) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (32)) == 0 || true) &&
 ((c 
                == this.minimumRangeValueColumn) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((r 
                == this.minimumRangeValueIncStdDevRow) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c 
                == this.minimumRangeValueIncStdDevColumn) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 8) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 8) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[31]++;
            
            // iterate over all data items and update mins and maxes
            updateBounds();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[68]++;

        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[32]++;} 
        
        fireDatasetChanged();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[69]++;
    }    


    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowIndex  the row index.
     * 
     * @see #removeColumn(int)
     * 
     * @since 1.0.7
     */
    public void removeRow(int rowIndex) {
        this.data.removeRow(rowIndex);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[70]++;
        updateBounds();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[71]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[72]++;
    }

    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * 
     * @see #removeColumn(Comparable)
     * 
     * @since 1.0.7
     */
    public void removeRow(Comparable rowKey) {
        this.data.removeRow(rowKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[73]++;
        updateBounds();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[74]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[75]++;
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnIndex  the column index.
     * 
     * @see #removeRow(int)
     * 
     * @since 1.0.7
     */
    public void removeColumn(int columnIndex) {
        this.data.removeColumn(columnIndex);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[76]++;
        updateBounds();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[77]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[78]++;
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #removeRow(Comparable)
     * 
     * @since 1.0.7
     */
    public void removeColumn(Comparable columnKey) {
        this.data.removeColumn(columnKey);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[79]++;
        updateBounds();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[80]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[81]++;
    }

    /**
     * Clears all data from the dataset and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     * 
     * @since 1.0.7
     */
    public void clear() {
        this.data.clear();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[82]++;
        updateBounds();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[83]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[84]++;
    }
    
    /**
     * Iterate over all the data items and update the cached bound values.
     */
    private void updateBounds() {
        this.maximumRangeValue = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[85]++;
        this.maximumRangeValueRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[86]++;
        this.maximumRangeValueColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[87]++;
        this.minimumRangeValue = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[88]++;
        this.minimumRangeValueRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[89]++;
        this.minimumRangeValueColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[90]++;
        this.maximumRangeValueIncStdDev = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[91]++;
        this.maximumRangeValueIncStdDevRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[92]++;
        this.maximumRangeValueIncStdDevColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[93]++;
        this.minimumRangeValueIncStdDev = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[94]++;
        this.minimumRangeValueIncStdDevRow = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[95]++;
        this.minimumRangeValueIncStdDevColumn = -1;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[96]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[97]++;
        
        int rowCount = this.data.getRowCount();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[98]++;
        int columnCount = this.data.getColumnCount();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[99]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[1]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((r < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[1]--;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[2]--;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[3]++;
}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[100]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[4]--;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[5]--;
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.loops[6]++;
}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[101]++;
                double m = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[102]++;
                double sd = Double.NaN;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[103]++;
                MeanAndStandardDeviation masd = (MeanAndStandardDeviation) 
                        this.data.getObject(r, c);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[104]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((masd == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[33]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[105]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[34]++;}
                m = masd.getMeanValue();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[106]++;
                sd = masd.getStandardDeviationValue();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[107]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[108]++;
int CodeCoverConditionCoverageHelper_C20;
                
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((Double.isNaN(m)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[35]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[109]++;
int CodeCoverConditionCoverageHelper_C21;
                    
                    // update the max value
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.maximumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[37]++;
                        this.maximumRangeValue = m;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[110]++;
                        this.maximumRangeValueRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[111]++;
                        this.maximumRangeValueColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[112]++;

                    }
                    else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[38]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[113]++;
int CodeCoverConditionCoverageHelper_C22;
                        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((m > this.maximumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[39]++;
                            this.maximumRangeValue = m;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[114]++;
                            this.maximumRangeValueRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[115]++;
                            this.maximumRangeValueColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[116]++;

                        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[40]++;}
                    }
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[117]++;
int CodeCoverConditionCoverageHelper_C23;
                    
                    // update the min value
                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.minimumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[41]++;
                        this.minimumRangeValue = m;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[118]++;
                        this.minimumRangeValueRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[119]++;
                        this.minimumRangeValueColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[120]++;

                    }
                    else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[42]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[121]++;
int CodeCoverConditionCoverageHelper_C24;
                        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((m < this.minimumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[43]++;
                            this.minimumRangeValue = m;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[122]++;
                            this.minimumRangeValueRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[123]++;
                            this.minimumRangeValueColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[124]++;

                        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[44]++;}
                    }
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[125]++;
int CodeCoverConditionCoverageHelper_C25;
                    
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((Double.isNaN(sd)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[45]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[126]++;
int CodeCoverConditionCoverageHelper_C26;
                        // update the max value
                        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.maximumRangeValueIncStdDev)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[47]++;
                            this.maximumRangeValueIncStdDev = m + sd;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[127]++;
                            this.maximumRangeValueIncStdDevRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[128]++;
                            this.maximumRangeValueIncStdDevColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[129]++;

                        }
                        else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[48]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[130]++;
int CodeCoverConditionCoverageHelper_C27;
                            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((m + sd > this.maximumRangeValueIncStdDev) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[49]++;
                                this.maximumRangeValueIncStdDev = m + sd;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[131]++;
                                this.maximumRangeValueIncStdDevRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[132]++;
                                this.maximumRangeValueIncStdDevColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[133]++;

                            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[50]++;}
                        }
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[134]++;
int CodeCoverConditionCoverageHelper_C28;
                        
                        // update the min value
                        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.minimumRangeValueIncStdDev)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[51]++;
                            this.minimumRangeValueIncStdDev = m - sd;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[135]++;
                            this.minimumRangeValueIncStdDevRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[136]++;
                            this.minimumRangeValueIncStdDevColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[137]++;

                        }
                        else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[52]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[138]++;
int CodeCoverConditionCoverageHelper_C29;
                            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((m - sd < this.minimumRangeValueIncStdDev) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[53]++;
                                this.minimumRangeValueIncStdDev = m - sd;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[139]++;
                                this.minimumRangeValueIncStdDevRow = r;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[140]++;
                                this.minimumRangeValueIncStdDevColumn = c;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[141]++;

                            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[54]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[46]++;}

                } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[36]++;}
            }
        }
    }
    
    /**
     * Returns the minimum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The minimum value.
     * 
     * @see #getRangeUpperBound(boolean)
     */
    public double getRangeLowerBound(boolean includeInterval) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[142]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[55]++;
            return this.minimumRangeValueIncStdDev;

        }
        else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[56]++;
            return this.minimumRangeValue;     
        }  
    }

    /**
     * Returns the maximum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The maximum value.
     * 
     * @see #getRangeLowerBound(boolean)
     */
    public double getRangeUpperBound(boolean includeInterval) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[143]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[57]++;
            return this.maximumRangeValueIncStdDev;

        }
        else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[58]++;
            return this.maximumRangeValue;     
        }  
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
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[144]++;
        Range result = null;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[145]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[59]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[146]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((Double.isNaN(this.minimumRangeValueIncStdDev)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.maximumRangeValueIncStdDev)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[61]++;
                result = new Range(this.minimumRangeValueIncStdDev, 
                        this.maximumRangeValueIncStdDev);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[147]++;

            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[62]++;}

        }
        else {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[60]++;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[148]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((Double.isNaN(this.minimumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.maximumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[63]++;
                result = new Range(this.minimumRangeValue, 
                        this.maximumRangeValue);
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[149]++;

            } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[64]++;}
        }
        return result;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[150]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[65]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[66]++;}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[151]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultStatisticalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[67]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[68]++;}
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[152]++;
        DefaultStatisticalCategoryDataset that 
                = (DefaultStatisticalCategoryDataset) obj;
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[153]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.data.equals(that.data)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[69]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.branches[70]++;}
        return true;
    }
    
    /**
     * Returns a clone of this dataset.
     * 
     * @return A clone of this dataset.
     * 
     * @throws CloneNotSupportedException if cloning cannot be completed.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[154]++;
        DefaultStatisticalCategoryDataset clone 
                = (DefaultStatisticalCategoryDataset) super.clone();
        clone.data = (KeyedObjects2D) this.data.clone();
CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl.statements[155]++;
        return clone;
    }
}

class CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl ());
  }
    public static long[] statements = new long[156];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[38];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.DefaultStatisticalCategoryDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,3,1,2,1,2,1,2,1,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1};
    for (int i = 1; i <= 37; i++) {
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

  public CodeCoverCoverageCounter$4q32wcsedargjy0jd10jgso34gyfk7fjrs5t29k9e4bdf22az7hifuyg1nl () {
    super("org.jfree.data.statistics.DefaultStatisticalCategoryDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 155; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.DefaultStatisticalCategoryDataset.java");
      for (int i = 1; i <= 155; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 70; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 37; i++) {
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

