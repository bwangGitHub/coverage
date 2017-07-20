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
 * -------------------------
 * DefaultKeyedValues2D.java
 * -------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andreas Schroeder;
 *
 * Changes
 * -------
 * 28-Oct-2002 : Version 1 (DG);
 * 21-Jan-2003 : Updated Javadocs (DG);
 * 13-Mar-2003 : Implemented Serializable (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 31-Mar-2004 : Made the rows optionally sortable by a flag (AS);
 * 01-Apr-2004 : Implemented remove method (AS);
 * 05-Apr-2004 : Added clear() method (DG);
 * 15-Sep-2004 : Fixed clone() method (DG);
 * 12-Jan-2005 : Fixed bug in getValue() method (DG);
 * 23-Mar-2005 : Implemented PublicCloneable (DG);
 * 09-Jun-2005 : Modified getValue() method to throw exception for unknown
 *               keys (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-Jan-2007 : Fixed bug in getValue() method (DG);
 * 30-Mar-2007 : Fixed bug 1690654, problem with removeValue() (DG);
 * 21-Nov-2007 : Fixed bug (1835955) in removeColumn(Comparable) method (DG);
 * 23-Nov-2007 : Added argument checks to removeRow(Comparable) to make it
 *               consistent with the removeRow(Comparable) method (DG);
 *
 */

package org.jfree.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A data structure that stores zero, one or many values, where each value 
 * is associated with two keys (a 'row' key and a 'column' key).  The keys 
 * should be (a) instances of {@link Comparable} and (b) immutable.  
 */
public class DefaultKeyedValues2D implements KeyedValues2D, 
                                             PublicCloneable, Cloneable, 
                                             Serializable {
  static {
    CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5514169970951994748L;
  static {
    CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[1]++;
  }
    
    /** The row keys. */
    private List rowKeys;

    /** The column keys. */
    private List columnKeys;

    /** The row data. */
    private List rows;
    
    /** If the row keys should be sorted by their comparable order. */
    private boolean sortRowKeys;

    /**
     * Creates a new instance (initially empty).
     */
    public DefaultKeyedValues2D() {
        this(false);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[2]++;
    }

    /**
     * Creates a new instance (initially empty).
     * 
     * @param sortRowKeys  if the row keys should be sorted.
     */
    public DefaultKeyedValues2D(boolean sortRowKeys) {
        this.rowKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[3]++;
        this.columnKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[4]++;
        this.rows = new java.util.ArrayList();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[5]++;
        this.sortRowKeys = sortRowKeys;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[6]++;
    }

    /**
     * Returns the row count.
     *
     * @return The row count.
     * 
     * @see #getColumnCount()
     */
    public int getRowCount() {
        return this.rowKeys.size();
    }

    /**
     * Returns the column count.
     *
     * @return The column count.
     * 
     * @see #getRowCount()
     */
    public int getColumnCount() {
        return this.columnKeys.size();
    }

    /**
     * Returns the value for a given row and column.
     *
     * @param row  the row index.
     * @param column  the column index.
     *
     * @return The value.
     * 
     * @see #getValue(Comparable, Comparable)
     */
    public Number getValue(int row, int column) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[7]++;
        Number result = null;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[8]++;
        DefaultKeyedValues rowData = (DefaultKeyedValues) this.rows.get(row);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((rowData != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[1]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[10]++;
            Comparable columnKey = (Comparable) this.columnKeys.get(column);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[11]++;
            // the row may not have an entry for this key, in which case the 
            // return value is null
            int index = rowData.getIndex(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[3]++;
                result = rowData.getValue(index);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[13]++;

            } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[2]++;}
        return result;
    }

    /**
     * Returns the key for a given row.
     *
     * @param row  the row index (in the range 0 to {@link #getRowCount()} - 1).
     *
     * @return The row key.
     * 
     * @see #getRowIndex(Comparable)
     * @see #getColumnKey(int)
     */
    public Comparable getRowKey(int row) {
        return (Comparable) this.rowKeys.get(row);
    }

    /**
     * Returns the row index for a given key.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The row index.
     * 
     * @see #getRowKey(int)
     * @see #getColumnIndex(Comparable)
     */
    public int getRowIndex(Comparable key) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[5]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[6]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.sortRowKeys) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[7]++;
            return Collections.binarySearch(this.rowKeys, key);

        }
        else {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[8]++;
            return this.rowKeys.indexOf(key);
        }
    }

    /**
     * Returns the row keys in an unmodifiable list.
     *
     * @return The row keys.
     * 
     * @see #getColumnKeys()
     */
    public List getRowKeys() {
        return Collections.unmodifiableList(this.rowKeys);
    }

    /**
     * Returns the key for a given column.
     *
     * @param column  the column (in the range 0 to {@link #getColumnCount()} 
     *     - 1).
     *
     * @return The key.
     * 
     * @see #getColumnIndex(Comparable)
     * @see #getRowKey(int)
     */
    public Comparable getColumnKey(int column) {
        return (Comparable) this.columnKeys.get(column);
    }

    /**
     * Returns the column index for a given key.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The column index.
     * 
     * @see #getColumnKey(int)
     * @see #getRowIndex(Comparable)
     */
    public int getColumnIndex(Comparable key) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[9]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[10]++;}
        return this.columnKeys.indexOf(key);
    }

    /**
     * Returns the column keys in an unmodifiable list.
     *
     * @return The column keys.
     * 
     * @see #getRowKeys()
     */
    public List getColumnKeys() {
        return Collections.unmodifiableList(this.columnKeys);
    }

    /**
     * Returns the value for the given row and column keys.  This method will
     * throw an {@link UnknownKeyException} if either key is not defined in the
     * data structure.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @see #addValue(Number, Comparable, Comparable)
     * @see #removeValue(Comparable, Comparable)
     */
    public Number getValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rowKey == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[11]++;
            throw new IllegalArgumentException("Null 'rowKey' argument.");

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[12]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((columnKey == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[13]++;
            throw new IllegalArgumentException("Null 'columnKey' argument.");

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[14]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
        
        // check that the column key is defined in the 2D structure
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.columnKeys.contains(columnKey)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[15]++;
            throw new UnknownKeyException("Unrecognised columnKey: " 
                    + columnKey);

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[16]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[20]++;
        
        // now fetch the row data - need to bear in mind that the row
        // structure may not have an entry for the column key, but that we
        // have already checked that the key is valid for the 2D structure
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((row >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[17]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[22]++;
            DefaultKeyedValues rowData 
                = (DefaultKeyedValues) this.rows.get(row);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[23]++;
            int col = rowData.getIndex(columnKey);
            return (col >= 0 ? rowData.getValue(col) : null);

        }
        else {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[18]++;
            throw new UnknownKeyException("Unrecognised rowKey: " + rowKey);
        }
    }

    /**
     * Adds a value to the table.  Performs the same function as 
     * #setValue(Number, Comparable, Comparable).
     *
     * @param value  the value (<code>null</code> permitted).
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #setValue(Number, Comparable, Comparable)
     * @see #removeValue(Comparable, Comparable)
     */
    public void addValue(Number value, Comparable rowKey, 
                         Comparable columnKey) {
        // defer argument checking
        setValue(value, rowKey, columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[24]++;
    }

    /**
     * Adds or updates a value.
     *
     * @param value  the value (<code>null</code> permitted).
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #addValue(Number, Comparable, Comparable)
     * @see #removeValue(Comparable, Comparable)
     */
    public void setValue(Number value, Comparable rowKey, 
                         Comparable columnKey) {

        DefaultKeyedValues row;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[25]++;
        int rowIndex = getRowIndex(rowKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
        
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((rowIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[19]++;
            row = (DefaultKeyedValues) this.rows.get(rowIndex);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[27]++;

        }
        else {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[20]++;
            row = new DefaultKeyedValues();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[28]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[29]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.sortRowKeys) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[21]++;
                rowIndex = -rowIndex - 1;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[30]++;
                this.rowKeys.add(rowIndex, rowKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[31]++;
                this.rows.add(rowIndex, row);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[32]++;

            }
            else {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[22]++;
                this.rowKeys.add(rowKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[33]++;
                this.rows.add(row);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[34]++;
            }
        }
        row.setValue(columnKey, value);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[35]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[36]++;
        
        int columnIndex = this.columnKeys.indexOf(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((columnIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[23]++;
            this.columnKeys.add(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[38]++;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[24]++;}
    }

    /**
     * Removes a value from the table by setting it to <code>null</code>.  If
     * all the values in the specified row and/or column are now 
     * <code>null</code>, the row and/or column is removed from the table.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #addValue(Number, Comparable, Comparable)
     */
    public void removeValue(Comparable rowKey, Comparable columnKey) {
        setValue(null, rowKey, columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[39]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[40]++;
        
        // 1. check whether the row is now empty.
        boolean allNull = true;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[41]++;
        int rowIndex = getRowIndex(rowKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[42]++;
        DefaultKeyedValues row = (DefaultKeyedValues) this.rows.get(rowIndex);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[43]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;

        for (int item = 0, itemCount = row.getItemCount();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); 
             item++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[1]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[2]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[3]++;
}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((row.getValue(item) != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[25]++;
                allNull = false;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[45]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[46]++;
                break;

            } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[26]++;}
        }
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[47]++;
int CodeCoverConditionCoverageHelper_C15;
        
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((allNull) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[27]++;
            this.rowKeys.remove(rowIndex);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[48]++;
            this.rows.remove(rowIndex);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[49]++;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[28]++;}
        
        // 2. check whether the column is now empty.
        allNull = true;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[50]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[51]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;
        //int columnIndex = getColumnIndex(columnKey);
        
        for (int item = 0, itemCount = this.rows.size();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); 
             item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[4]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[5]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[6]++;
}
            row = (DefaultKeyedValues) this.rows.get(item);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[52]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[53]++;
            int columnIndex = row.getIndex(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[54]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((columnIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((row.getValue(columnIndex) != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[29]++;
                allNull = false;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[55]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[56]++;
                break;

            } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[30]++;}
        }
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[57]++;
int CodeCoverConditionCoverageHelper_C18;
        
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((allNull) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[31]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[58]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[7]++;


int CodeCoverConditionCoverageHelper_C19;
            for (int item = 0, itemCount = this.rows.size();(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); 
                 item++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[7]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[8]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[9]++;
}
                row = (DefaultKeyedValues) this.rows.get(item);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[59]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[60]++;
                int columnIndex = row.getIndex(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[61]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((columnIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[33]++;
                    row.removeValue(columnIndex);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[62]++;

                } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[34]++;}
            }
            this.columnKeys.remove(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[63]++;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[32]++;}
    }

    /**
     * Removes a row.
     *
     * @param rowIndex  the row index.
     * 
     * @see #removeRow(Comparable)
     * @see #removeColumn(int)
     */
    public void removeRow(int rowIndex) {
        this.rowKeys.remove(rowIndex);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[64]++;
        this.rows.remove(rowIndex);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[65]++;
    }

    /**
     * Removes a row from the table.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * 
     * @see #removeRow(int)
     * @see #removeColumn(Comparable)
     *
     * @throws UnknownKeyException if <code>rowKey</code> is not defined in the
     *         table.
     */
    public void removeRow(Comparable rowKey) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[66]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((rowKey == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[35]++;
            throw new IllegalArgumentException("Null 'rowKey' argument.");

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[36]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[67]++;
        int index = getRowIndex(rowKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[68]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[37]++;
            removeRow(index);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[69]++;

        }
        else {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[38]++;
            throw new UnknownKeyException("Unknown key: " + rowKey);
        }
    }

    /**
     * Removes a column.
     *
     * @param columnIndex  the column index.
     * 
     * @see #removeColumn(Comparable)
     * @see #removeRow(int)
     */
    public void removeColumn(int columnIndex) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[70]++;
        Comparable columnKey = getColumnKey(columnIndex);
        removeColumn(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[71]++;
    }

    /**
     * Removes a column from the table.
     *
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @throws UnknownKeyException if the table does not contain a column with
     *     the specified key.
     * @throws IllegalArgumentException if <code>columnKey</code> is 
     *     <code>null</code>.
     * 
     * @see #removeColumn(int)
     * @see #removeRow(Comparable)
     */
    public void removeColumn(Comparable columnKey) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[72]++;
int CodeCoverConditionCoverageHelper_C23;
    	if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((columnKey == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[39]++;
    		throw new IllegalArgumentException("Null 'columnKey' argument.");

    	} else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[40]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[73]++;
int CodeCoverConditionCoverageHelper_C24;
    	if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.columnKeys.contains(columnKey)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[41]++;
    		throw new UnknownKeyException("Unknown key: " + columnKey);

    	} else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[42]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[74]++;
        Iterator iterator = this.rows.iterator();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[75]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[10]++;


int CodeCoverConditionCoverageHelper_C25;
        while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[10]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[11]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[12]++;
}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[76]++;
            DefaultKeyedValues rowData = (DefaultKeyedValues) iterator.next();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[77]++;
            int index = rowData.getIndex(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[78]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[43]++;
                rowData.removeValue(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[79]++;

            } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[44]++;}
        }
        this.columnKeys.remove(columnKey);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[80]++;
    }

    /**
     * Clears all the data and associated keys.
     */
    public void clear() {
        this.rowKeys.clear();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[81]++;
        this.columnKeys.clear();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[82]++;
        this.rows.clear();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[83]++;
    }
    
    /**
     * Tests if this object is equal to another.
     *
     * @param o  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object o) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[84]++;
int CodeCoverConditionCoverageHelper_C27;

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((o == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[46]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[85]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((o == this) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[47]++;
            return true;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[48]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[86]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((o instanceof KeyedValues2D) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[50]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[87]++;
        KeyedValues2D kv2D = (KeyedValues2D) o;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[88]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((getRowKeys().equals(kv2D.getRowKeys())) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[52]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[89]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((getColumnKeys().equals(kv2D.getColumnKeys())) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[54]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[90]++;
        int rowCount = getRowCount();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[91]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((rowCount != kv2D.getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[56]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[92]++;

        int colCount = getColumnCount();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[93]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((colCount != kv2D.getColumnCount()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[58]++;}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[94]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[13]++;


int CodeCoverConditionCoverageHelper_C34;

        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((r < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[13]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[14]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[15]++;
}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[95]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[16]++;


int CodeCoverConditionCoverageHelper_C35;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((c < colCount) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[16]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[17]--;
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.loops[18]++;
}
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[96]++;
                Number v1 = getValue(r, c);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[97]++;
                Number v2 = kv2D.getValue(r, c);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[98]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((v1 == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[59]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[99]++;
int CodeCoverConditionCoverageHelper_C37;
                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((v2 != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[61]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[62]++;}

                }
                else {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[60]++;
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[100]++;
int CodeCoverConditionCoverageHelper_C38;
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((v1.equals(v2)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[63]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.branches[64]++;}
                }
            }
        }
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = this.rowKeys.hashCode();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[101]++;
        result = 29 * result + this.columnKeys.hashCode();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[102]++;
        result = 29 * result + this.rows.hashCode();
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[103]++;
        return result;
    }

    /**
     * Returns a clone.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[104]++;
        DefaultKeyedValues2D clone = (DefaultKeyedValues2D) super.clone();
        // for the keys, a shallow copy should be fine because keys
        // should be immutable...
        clone.columnKeys = new java.util.ArrayList(this.columnKeys);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[105]++;
        clone.rowKeys = new java.util.ArrayList(this.rowKeys);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[106]++;
        
        // but the row data requires a deep copy
        clone.rows = (List) ObjectUtilities.deepClone(this.rows);
CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p.statements[107]++;
        return clone;
    }

}

class CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p ());
  }
    public static long[] statements = new long[108];
    public static long[] branches = new long[65];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "org.jfree.data.DefaultKeyedValues2D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 38; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$343j15ke58te1uunb64hanilgnhgqe4p9wk6f6p () {
    super("org.jfree.data.DefaultKeyedValues2D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 107; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 64; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.DefaultKeyedValues2D.java");
      for (int i = 1; i <= 107; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 64; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

