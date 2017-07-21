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
 * ---------------------------
 * DefaultCategoryDataset.java
 * ---------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 21-Jan-2003 : Added standard header, and renamed DefaultCategoryDataset (DG);
 * 13-Mar-2003 : Inserted DefaultKeyedValues2DDataset into class hierarchy (DG);
 * 06-Oct-2003 : Added incrementValue() method (DG);
 * 05-Apr-2004 : Added clear() method (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.category (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 26-Feb-2007 : Updated API docs (DG);
 * 08-Mar-2007 : Implemented clone() (DG);
 *
 */

package org.jfree.data.category;

import java.io.Serializable;
import java.util.List;

import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.UnknownKeyException;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DatasetChangeEvent;

/**
 * A default implementation of the {@link CategoryDataset} interface.
 */
public class DefaultCategoryDataset extends AbstractDataset
                                    implements CategoryDataset, Serializable {
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8168173757291644622L;
  static {
    CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[1]++;
  }
    
    /** A storage structure for the data. */
    private DefaultKeyedValues2D data;

    /**
     * Creates a new (empty) dataset.
     */
    public DefaultCategoryDataset() {
        this.data = new DefaultKeyedValues2D();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[2]++;
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
     * Returns a value from the table.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @see #addValue(Number, Comparable, Comparable)
     * @see #removeValue(Comparable, Comparable)
     */
    public Number getValue(int row, int column) {
        return this.data.getValue(row, column);
    }

    /**
     * Returns the key for the specified row.
     *
     * @param row  the row index (zero-based).
     *
     * @return The row key.
     * 
     * @see #getRowIndex(Comparable)
     * @see #getRowKeys()
     * @see #getColumnKey(int)
     */
    public Comparable getRowKey(int row) {
        return this.data.getRowKey(row);
    }

    /**
     * Returns the row index for a given key.
     *
     * @param key  the row key (<code>null</code> not permitted).
     *
     * @return The row index.
     * 
     * @see #getRowKey(int)
     */
    public int getRowIndex(Comparable key) {
        // defer null argument check
        return this.data.getRowIndex(key);
    }

    /**
     * Returns the row keys.
     *
     * @return The keys.
     * 
     * @see #getRowKey(int)
     */
    public List getRowKeys() {
        return this.data.getRowKeys();
    }

    /**
     * Returns a column key.
     *
     * @param column  the column index (zero-based).
     *
     * @return The column key.
     * 
     * @see #getColumnIndex(Comparable)
     */
    public Comparable getColumnKey(int column) {
        return this.data.getColumnKey(column);
    }

    /**
     * Returns the column index for a given key.
     *
     * @param key  the column key (<code>null</code> not permitted).
     *
     * @return The column index.
     * 
     * @see #getColumnKey(int)
     */
    public int getColumnIndex(Comparable key) {
        // defer null argument check
        return this.data.getColumnIndex(key);
    }

    /**
     * Returns the column keys.
     *
     * @return The keys.
     * 
     * @see #getColumnKey(int)
     */
    public List getColumnKeys() {
        return this.data.getColumnKeys();
    }

    /**
     * Returns the value for a pair of keys.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @throws UnknownKeyException if either key is not defined in the dataset.
     * 
     * @see #addValue(Number, Comparable, Comparable)
     */
    public Number getValue(Comparable rowKey, Comparable columnKey) {
        return this.data.getValue(rowKey, columnKey);
    }

    /**
     * Adds a value to the table.  Performs the same function as setValue().
     *
     * @param value  the value.
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @see #getValue(Comparable, Comparable)
     * @see #removeValue(Comparable, Comparable)
     */
    public void addValue(Number value, Comparable rowKey, 
                         Comparable columnKey) {
        this.data.addValue(value, rowKey, columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[3]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[4]++;
    }

    /**
     * Adds a value to the table.
     *
     * @param value  the value.
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @see #getValue(Comparable, Comparable)
     */
    public void addValue(double value, Comparable rowKey, 
                         Comparable columnKey) {
        addValue(new Double(value), rowKey, columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[5]++;
    }

    /**
     * Adds or updates a value in the table and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param value  the value (<code>null</code> permitted).
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #getValue(Comparable, Comparable)
     */
    public void setValue(Number value, Comparable rowKey, 
                         Comparable columnKey) {
        this.data.setValue(value, rowKey, columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[6]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[7]++;
    }

    /**
     * Adds or updates a value in the table and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param value  the value.
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #getValue(Comparable, Comparable)
     */
    public void setValue(double value, Comparable rowKey, 
                         Comparable columnKey) {
        setValue(new Double(value), rowKey, columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[8]++;
    }

    /**
     * Adds the specified value to an existing value in the dataset (if the 
     * existing value is <code>null</code>, it is treated as if it were 0.0).
     * 
     * @param value  the value.
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @throws UnknownKeyException if either key is not defined in the dataset.
     */
    public void incrementValue(double value, 
                               Comparable rowKey, 
                               Comparable columnKey) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[9]++;
        double existing = 0.0;
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[10]++;
        Number n = getValue(rowKey, columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[1]++;
            existing = n.doubleValue();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[12]++;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[2]++;}
        setValue(existing + value, rowKey, columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[13]++;
    }
    
    /**
     * Removes a value from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @see #addValue(Number, Comparable, Comparable)
     */
    public void removeValue(Comparable rowKey, Comparable columnKey) {
        this.data.removeValue(rowKey, columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[14]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[15]++;
    }

    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowIndex  the row index.
     * 
     * @see #removeColumn(int)
     */
    public void removeRow(int rowIndex) {
        this.data.removeRow(rowIndex);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[16]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[17]++;
    }

    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey  the row key.
     * 
     * @see #removeColumn(Comparable)
     */
    public void removeRow(Comparable rowKey) {
        this.data.removeRow(rowKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[18]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[19]++;
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnIndex  the column index.
     * 
     * @see #removeRow(int)
     */
    public void removeColumn(int columnIndex) {
        this.data.removeColumn(columnIndex);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[20]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[21]++;
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #removeRow(Comparable)
     * 
     * @throws UnknownKeyException if <code>columnKey</code> is not defined
     *         in the dataset.
     */
    public void removeColumn(Comparable columnKey) {
        this.data.removeColumn(columnKey);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[22]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[23]++;
    }

    /**
     * Clears all data from the dataset and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     */
    public void clear() {
        this.data.clear();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[24]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[25]++;
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[4]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[6]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[28]++;
        CategoryDataset that = (CategoryDataset) obj;
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[29]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getRowKeys().equals(that.getRowKeys())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[8]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getColumnKeys().equals(that.getColumnKeys())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[10]++;}
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[31]++;
        int rowCount = getRowCount();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[32]++;
        int colCount = getColumnCount();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[33]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((r < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[1]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[2]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[3]++;
}
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c < colCount) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[4]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[5]--;
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.loops[6]++;
}
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[35]++;
                Number v1 = getValue(r, c);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[36]++;
                Number v2 = that.getValue(r, c);
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((v1 == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[11]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
                    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((v2 != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[13]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[14]++;}

                }
                else {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[12]++;
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[39]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((v1.equals(v2)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[15]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.branches[16]++;}
}
            }
        }
        return true;
    }

    /**
     * Returns a hash code for the dataset.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return this.data.hashCode();
    }

    /**
     * Returns a clone of the dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning the
     *         dataset.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[40]++;
        DefaultCategoryDataset clone = (DefaultCategoryDataset) super.clone();
        clone.data = (DefaultKeyedValues2D) this.data.clone();
CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41.statements[41]++;
        return clone;
    }
    
}

class CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41 ());
  }
    public static long[] statements = new long[42];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.category.DefaultCategoryDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$4dgk7qtxg7g0tqnfh2yq2op6hufg8g3pd8u3eonh41 () {
    super("org.jfree.data.category.DefaultCategoryDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 41; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.category.DefaultCategoryDataset.java");
      for (int i = 1; i <= 41; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

