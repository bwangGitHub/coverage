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
 * ------------------
 * KeyedObject2D.java
 * ------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Feb-2003 : Version 1 (DG);
 * 01-Mar-2004 : Added equals() and clone() methods and implemented 
 *               Serializable (DG);
 * 03-Oct-2007 : Updated getObject() to handle modified behaviour in 
 *               KeyedObjects class, added clear() method (DG);
 *
 */

package org.jfree.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A data structure that stores zero, one or many objects, where each object is
 * associated with two keys (a 'row' key and a 'column' key).
 */
public class KeyedObjects2D implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1015873563138522374L;
  static {
    CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[1]++;
  }
    
    /** The row keys. */
    private List rowKeys;

    /** The column keys. */
    private List columnKeys;

    /** The row data. */
    private List rows;

    /**
     * Creates a new instance (initially empty).
     */
    public KeyedObjects2D() {
        this.rowKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[2]++;
        this.columnKeys = new java.util.ArrayList();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[3]++;
        this.rows = new java.util.ArrayList();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[4]++;
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
     * Returns the object for a given row and column.
     *
     * @param row  the row index (in the range 0 to getRowCount() - 1).
     * @param column  the column index (in the range 0 to getColumnCount() - 1).
     *
     * @return The object (possibly <code>null</code>).
     * 
     * @see #getObject(Comparable, Comparable)
     */
    public Object getObject(int row, int column) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[5]++;
        Object result = null;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[6]++;
        KeyedObjects rowData = (KeyedObjects) this.rows.get(row);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((rowData != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[1]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[8]++;
            Comparable columnKey = (Comparable) this.columnKeys.get(column);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((columnKey != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[3]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[10]++;
                int index = rowData.getIndex(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[5]++;
                    result = rowData.getObject(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[12]++;

                } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[6]++;}

            } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[2]++;}
        return result;
    }

    /**
     * Returns the key for a given row.
     *
     * @param row  the row index (zero based).
     *
     * @return The row index.
     * 
     * @see #getRowIndex(Comparable)
     */
    public Comparable getRowKey(int row) {
        return (Comparable) this.rowKeys.get(row);
    }

    /**
     * Returns the row index for a given key, or <code>-1</code> if the key
     * is not recognised.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The row index.
     * 
     * @see #getRowKey(int)
     */
    public int getRowIndex(Comparable key) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[7]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[8]++;}
        return this.rowKeys.indexOf(key);
    }

    /**
     * Returns the row keys.
     *
     * @return The row keys (never <code>null</code>).
     * 
     * @see #getRowKeys()
     */
    public List getRowKeys() {
        return Collections.unmodifiableList(this.rowKeys);
    }

    /**
     * Returns the key for a given column.
     *
     * @param column  the column.
     *
     * @return The key.
     * 
     * @see #getColumnIndex(Comparable)
     */
    public Comparable getColumnKey(int column) {
        return (Comparable) this.columnKeys.get(column);
    }

    /**
     * Returns the column index for a given key, or <code>-1</code> if the key
     * is not recognised.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The column index.
     * 
     * @see #getColumnKey(int)
     */
    public int getColumnIndex(Comparable key) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[9]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[10]++;}
        return this.columnKeys.indexOf(key);
    }

    /**
     * Returns the column keys.
     *
     * @return The column keys (never <code>null</code>).
     * 
     * @see #getRowKeys()
     */
    public List getColumnKeys() {
        return Collections.unmodifiableList(this.columnKeys);
    }

    /**
     * Returns the object for the given row and column keys.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     *
     * @return The object (possibly <code>null</code>).
     * 
     * @throws IllegalArgumentException if <code>rowKey<code> or 
     *         <code>columnKey</code> is <code>null</code>.
     * @throws UnknownKeyException if <code>rowKey</code> or 
     *         <code>columnKey</code> is not recognised.
     */
    public Object getObject(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rowKey == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[11]++;
            throw new IllegalArgumentException("Null 'rowKey' argument.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[12]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[16]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((columnKey == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[13]++;
            throw new IllegalArgumentException("Null 'columnKey' argument.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[14]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[17]++;
        int row = this.rowKeys.indexOf(rowKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[18]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((row < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[15]++;
            throw new UnknownKeyException("Row key (" + rowKey 
                    + ") not recognised.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[16]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[19]++;
        int column = this.columnKeys.indexOf(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[20]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((column < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[17]++;
            throw new UnknownKeyException("Column key (" + columnKey 
                    + ") not recognised.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[18]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[21]++;
        KeyedObjects rowData = (KeyedObjects) this.rows.get(row);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[22]++;
        int index = rowData.getIndex(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[19]++;
            return rowData.getObject(index);

        }
        else {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[20]++;
            return null;
        }
    }

    /**
     * Adds an object to the table.  Performs the same function as setObject().
     *
     * @param object  the object.
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     */
    public void addObject(Object object, Comparable rowKey, 
            Comparable columnKey) {
        setObject(object, rowKey, columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[24]++;
    }

    /**
     * Adds or updates an object.
     *
     * @param object  the object.
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     */
    public void setObject(Object object, Comparable rowKey, 
            Comparable columnKey) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[25]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((rowKey == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[21]++;
            throw new IllegalArgumentException("Null 'rowKey' argument.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[22]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[26]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((columnKey == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[23]++;
            throw new IllegalArgumentException("Null 'columnKey' argument.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[24]++;}
        KeyedObjects row;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[27]++;
        int rowIndex = this.rowKeys.indexOf(rowKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[28]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((rowIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[25]++;
            row = (KeyedObjects) this.rows.get(rowIndex);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[29]++;

        }
        else {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[26]++;
            this.rowKeys.add(rowKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[30]++;
            row = new KeyedObjects();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[31]++;
            this.rows.add(row);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[32]++;
        }
        row.setObject(columnKey, object);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[33]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[34]++;
        int columnIndex = this.columnKeys.indexOf(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((columnIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[27]++;
            this.columnKeys.add(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[36]++;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[28]++;}

    }

    /**
     * Removes an object from the table by setting it to <code>null</code>.  If
     * all the objects in the specified row and/or column are now 
     * <code>null</code>, the row and/or column is removed from the table.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #addObject(Object, Comparable, Comparable)
     */
    public void removeObject(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[37]++;
        int rowIndex = getRowIndex(rowKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((rowIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[29]++;
            throw new UnknownKeyException("Row key (" + rowKey 
                    + ") not recognised.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[30]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[39]++;
        int columnIndex = getColumnIndex(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[40]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((columnIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[31]++;
            throw new UnknownKeyException("Column key (" + columnKey 
                    + ") not recognised.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[32]++;}
        setObject(null, rowKey, columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[41]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[42]++;
        
        // 1. check whether the row is now empty.
        boolean allNull = true;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[43]++;
        KeyedObjects row = (KeyedObjects) this.rows.get(rowIndex);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[44]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[1]++;


int CodeCoverConditionCoverageHelper_C17;

        for (int item = 0, itemCount = row.getItemCount();(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); 
             item++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[1]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[2]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[3]++;
}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[45]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((row.getObject(item) != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[33]++;
                allNull = false;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[46]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[47]++;
                break;

            } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[34]++;}
        }
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[48]++;
int CodeCoverConditionCoverageHelper_C19;
        
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((allNull) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[35]++;
            this.rowKeys.remove(rowIndex);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[49]++;
            this.rows.remove(rowIndex);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[50]++;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[36]++;}
        
        // 2. check whether the column is now empty.
        allNull = true;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[51]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[52]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
        
        for (int item = 0, itemCount = this.rows.size();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); 
             item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[4]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[5]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[6]++;
}
            row = (KeyedObjects) this.rows.get(item);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[53]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[54]++;
            int colIndex = row.getIndex(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[55]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((colIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((row.getObject(colIndex) != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[37]++;
                allNull = false;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[56]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[57]++;
                break;

            } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[38]++;}
        }
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[58]++;
int CodeCoverConditionCoverageHelper_C22;
        
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((allNull) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[39]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[59]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[7]++;


int CodeCoverConditionCoverageHelper_C23;
            for (int item = 0, itemCount = this.rows.size();(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); 
                 item++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[7]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[8]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[9]++;
}
                row = (KeyedObjects) this.rows.get(item);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[60]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[61]++;
                int colIndex = row.getIndex(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[62]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((colIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[41]++;
                    row.removeValue(colIndex);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[63]++;

                } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[42]++;}
            }
            this.columnKeys.remove(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[64]++;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[40]++;}
    }

    /**
     * Removes an entire row from the table.
     *
     * @param rowIndex  the row index.
     * 
     * @see #removeColumn(int)
     */
    public void removeRow(int rowIndex) {
        this.rowKeys.remove(rowIndex);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[65]++;
        this.rows.remove(rowIndex);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[66]++;
    }

    /**
     * Removes an entire row from the table.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * 
     * @throws UnknownKeyException if <code>rowKey</code> is not recognised.
     * 
     * @see #removeColumn(Comparable)
     */
    public void removeRow(Comparable rowKey) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[67]++;
        int index = getRowIndex(rowKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[68]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[43]++;
            throw new UnknownKeyException("Row key (" + rowKey 
                    + ") not recognised.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[44]++;}
        removeRow(index);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[69]++;
    }

    /**
     * Removes an entire column from the table.
     *
     * @param columnIndex  the column index.
     * 
     * @see #removeRow(int)
     */
    public void removeColumn(int columnIndex) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[70]++;
        Comparable columnKey = getColumnKey(columnIndex);
        removeColumn(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[71]++;
    }

    /**
     * Removes an entire column from the table.
     *
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @throws UnknownKeyException if <code>rowKey</code> is not recognised.
     * 
     * @see #removeRow(Comparable)
     */
    public void removeColumn(Comparable columnKey) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[72]++;
        int index = getColumnIndex(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[73]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[45]++;
            throw new UnknownKeyException("Column key (" + columnKey 
                    + ") not recognised.");

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[46]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[74]++;
        Iterator iterator = this.rows.iterator();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[75]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[10]++;


int CodeCoverConditionCoverageHelper_C27;
        while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[10]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[11]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[12]++;
}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[76]++;
            KeyedObjects rowData = (KeyedObjects) iterator.next();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[77]++;
            int i = rowData.getIndex(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[78]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[47]++;
                rowData.removeValue(i);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[79]++;

            } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[48]++;}
        }
        this.columnKeys.remove(columnKey);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[80]++;
    }

    /**
     * Clears all the data and associated keys.
     * 
     * @since 1.0.7
     */
    public void clear() {
        this.rowKeys.clear();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[81]++;
        this.columnKeys.clear();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[82]++;
        this.rows.clear();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[83]++;
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object to test (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[84]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[49]++;
            return true;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[50]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[85]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((obj instanceof KeyedObjects2D) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[52]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[86]++;
        
        KeyedObjects2D that = (KeyedObjects2D) obj;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[87]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((getRowKeys().equals(that.getRowKeys())) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[54]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[88]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((getColumnKeys().equals(that.getColumnKeys())) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[56]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[89]++;
        int rowCount = getRowCount();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[90]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((rowCount != that.getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[58]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[91]++;
        int colCount = getColumnCount();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[92]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((colCount != that.getColumnCount()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[60]++;}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[93]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[13]++;


int CodeCoverConditionCoverageHelper_C35;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((r < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[13]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[14]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[15]++;
}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[94]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[16]++;


int CodeCoverConditionCoverageHelper_C36;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((c < colCount) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[16]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[17]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[18]++;
}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[95]++;
                Object v1 = getObject(r, c);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[96]++;
                Object v2 = that.getObject(r, c);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[97]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((v1 == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[61]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[98]++;
int CodeCoverConditionCoverageHelper_C38;
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((v2 != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[63]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[64]++;}

                }
                else {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[62]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[99]++;
int CodeCoverConditionCoverageHelper_C39;
                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((v1.equals(v2)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[65]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.branches[66]++;}
                }
            }
        }
        return true;
    }

    /**
     * Returns a hashcode for this object.
     * 
     * @return A hashcode.
     */
    public int hashCode() {
        int result;
        result = this.rowKeys.hashCode();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[100]++;
        result = 29 * result + this.columnKeys.hashCode();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[101]++;
        result = 29 * result + this.rows.hashCode();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[102]++;
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
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[103]++;
        KeyedObjects2D clone = (KeyedObjects2D) super.clone();
        clone.columnKeys = new java.util.ArrayList(this.columnKeys);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[104]++;
        clone.rowKeys = new java.util.ArrayList(this.rowKeys);
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[105]++;
        clone.rows = new java.util.ArrayList(this.rows.size());
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[106]++;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[107]++;
        Iterator iterator = this.rows.iterator();
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[108]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[19]++;


int CodeCoverConditionCoverageHelper_C40;
        while ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[19]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[20]--;
  CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.loops[21]++;
}
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[109]++;
            KeyedObjects row = (KeyedObjects) iterator.next();
            clone.rows.add(row.clone());
CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x.statements[110]++;
        }
        return clone;
    }

}

class CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x ());
  }
    public static long[] statements = new long[111];
    public static long[] branches = new long[67];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[41];
  static {
    final String SECTION_NAME = "org.jfree.data.KeyedObjects2D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 40; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$18l3mx45fbg3jiy9c99i12axrrc40x () {
    super("org.jfree.data.KeyedObjects2D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 110; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 66; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.KeyedObjects2D.java");
      for (int i = 1; i <= 110; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 66; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

