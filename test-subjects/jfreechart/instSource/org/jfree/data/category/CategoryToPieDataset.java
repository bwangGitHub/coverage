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
 * CategoryToPieDataset.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1 (DG);
 * 30-Jul-2003 : Pass through DatasetChangeEvent (CZ);
 * 29-Jan-2004 : Replaced 'extract' int with TableOrder (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * ------------- JFREECHART 1.0.0 RELEASED ------------------------------------
 * 26-Jul-2006 : Added serialVersionUID, changed constructor to allow null
 *               for source, and added getSource(), getExtractType() and
 *               getExtractIndex() methods - see feature request 1477915 (DG);
 * 
 */

package org.jfree.data.category;

import java.util.Collections;
import java.util.List;

import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.PieDataset;
import org.jfree.util.TableOrder;

/**
 * A {@link PieDataset} implementation that obtains its data from one row or 
 * column of a {@link CategoryDataset}.
 */
public class CategoryToPieDataset extends AbstractDataset 
                                  implements PieDataset, DatasetChangeListener {
  static {
    CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = 5516396319762189617L;
  static {
    CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[1]++;
  }

    /** The source. */
    private CategoryDataset source;

    /** The extract type. */
    private TableOrder extract;

    /** The row or column index. */
    private int index;

    /**
     * An adaptor class that converts any {@link CategoryDataset} into a 
     * {@link PieDataset}, by taking the values from a single row or column.
     * <p>
     * If <code>source</code> is <code>null</code>, the created dataset will 
     * be empty.
     *
     * @param source  the source dataset (<code>null</code> permitted).
     * @param extract  extract data from rows or columns? (<code>null</code> 
     *                 not permitted).
     * @param index  the row or column index.
     */
    public CategoryToPieDataset(CategoryDataset source, 
                                TableOrder extract, 
                                int index) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((extract == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[1]++;
            throw new IllegalArgumentException("Null 'extract' argument.");

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[2]++;}
        this.source = source;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[3]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.source != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[3]++;
            this.source.addChangeListener(this);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[5]++;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[4]++;}
        this.extract = extract;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[6]++;
        this.index = index;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[7]++;
    }
    
    /**
     * Returns the underlying dataset.
     * 
     * @return The underlying dataset (possibly <code>null</code>).
     * 
     * @since 1.0.2
     */
    public CategoryDataset getUnderlyingDataset() {
        return this.source;
    }
    
    /**
     * Returns the extract type, which determines whether data is read from
     * one row or one column of the underlying dataset.
     * 
     * @return The extract type.
     * 
     * @since 1.0.2
     */
    public TableOrder getExtractType() {
        return this.extract;
    }
    
    /**
     * Returns the index of the row or column from which to extract the data.
     * 
     * @return The extract index.
     * 
     * @since 1.0.2
     */
    public int getExtractIndex() {
        return this.index;
    }

    /**
     * Returns the number of items (values) in the collection.  If the 
     * underlying dataset is <code>null</code>, this method returns zero.
     *
     * @return The item count.
     */
    public int getItemCount() {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[8]++;
        int result = 0;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.source != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[5]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[7]++;
                result = this.source.getColumnCount();
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[11]++;

            }
            else {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[8]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[12]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[9]++;
                result = this.source.getRowCount();
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[13]++;

            } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[10]++;}
}

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[6]++;}
        return result;
    }

    /**
     * Returns a value from the dataset.
     *
     * @param item  the item index (zero-based).
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @throws IndexOutOfBoundsException if <code>item</code> is not in the 
     *     range <code>0</code> to <code>getItemCount() - 1</code>.
     */
    public Number getValue(int item) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[14]++;
        Number result = null;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((item < 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((item >= getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[11]++;
            // this will include the case where the underlying dataset is null
            throw new IndexOutOfBoundsException(
                    "The 'item' index is out of bounds.");

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[12]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[16]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[13]++;
            result = this.source.getValue(this.index, item);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[17]++;

        }
        else {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[14]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[18]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[15]++;
            result = this.source.getValue(item, this.index);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[19]++;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[16]++;}
}
        return result;
    }

    /**
     * Returns the key at the specified index.
     *
     * @param index  the item index (in the range <code>0</code> to 
     *     <code>getItemCount() - 1</code>).
     *
     * @return The key.
     * 
     * @throws IndexOutOfBoundsException if <code>index</code> is not in the 
     *     specified range.
     */
    public Comparable getKey(int index) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[20]++;
        Comparable result = null;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index >= getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[17]++;
            // this includes the case where the underlying dataset is null
            throw new IndexOutOfBoundsException("Invalid 'index': " + index);

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[18]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[22]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[19]++;
            result = this.source.getColumnKey(index);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[23]++;

        }
        else {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[20]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[24]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[21]++;
            result = this.source.getRowKey(index);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[25]++;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[22]++;}
}
        return result;
    }

    /**
     * Returns the index for a given key, or <code>-1</code> if there is no
     * such key.
     *
     * @param key  the key.
     *
     * @return The index for the key, or <code>-1</code>.
     */
    public int getIndex(Comparable key) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[26]++;
        int result = -1;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[27]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.source != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[23]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[28]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[25]++;
                result = this.source.getColumnIndex(key);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[29]++;

            }
            else {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[26]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[30]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[27]++;
                result = this.source.getRowIndex(key);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[31]++;

            } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[28]++;}
}

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[24]++;}
        return result;
    }

    /**
     * Returns the keys for the dataset.
     * <p>
     * If the underlying dataset is <code>null</code>, this method returns an
     * empty list.
     *
     * @return The keys.
     */
    public List getKeys() {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[32]++;
        List result = Collections.EMPTY_LIST;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[33]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.source != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[29]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[34]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[31]++;
                result = this.source.getColumnKeys();
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[35]++;

            }
            else {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[32]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[36]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[33]++;
                result = this.source.getRowKeys();
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[37]++;

            } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[34]++;}
}

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[30]++;}
        return result;
    }

    /**
     * Returns the value for a given key.  If the key is not recognised, the 
     * method should return <code>null</code> (but note that <code>null</code> 
     * can be associated with a valid key also).
     *
     * @param key  the key.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue(Comparable key) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[38]++;
        Number result = null;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[39]++;
        int keyIndex = getIndex(key);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[40]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((keyIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[35]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[41]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[37]++;
                result = this.source.getValue(this.index, keyIndex);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[42]++;

            }
            else {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[38]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[43]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.extract == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[39]++;
                result = this.source.getValue(keyIndex, this.index);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[44]++;

            } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[40]++;}
}

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[36]++;}
        return result;
    }
    
    /**
     * Sends a {@link DatasetChangeEvent} to all registered listeners, with
     * this (not the underlying) dataset as the source.
     * 
     * @param event  the event (ignored, a new event with this dataset as the
     *     source is sent to the listeners).
     */
    public void datasetChanged(DatasetChangeEvent event) {
        fireDatasetChanged();
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[45]++;
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object, returning
     * <code>true</code> if <code>obj</code> is a dataset containing the same
     * keys and values in the same order as this dataset.
     * 
     * @param obj  the object to test (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[46]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[41]++;
            return true;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[42]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[47]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((obj instanceof PieDataset) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[44]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[48]++;
        PieDataset that = (PieDataset) obj;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[49]++;
        int count = getItemCount();
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[50]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((that.getItemCount() != count) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[46]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[51]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.loops[1]++;


int CodeCoverConditionCoverageHelper_C24;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.loops[1]--;
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.loops[2]--;
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.loops[3]++;
}
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[52]++;
            Comparable k1 = getKey(i);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[53]++;
            Comparable k2 = that.getKey(i);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[54]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((k1.equals(k2)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[47]++;
                return false;

            } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[48]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[55]++;

            Number v1 = getValue(i);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[56]++;
            Number v2 = that.getValue(i);
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[57]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((v1 == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[49]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[58]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((v2 != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[51]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[52]++;}

            }
            else {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[50]++;
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.statements[59]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((v1.equals(v2)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[53]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld.branches[54]++;}
            }
        }
        return true;
    }
     
}

class CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld ());
  }
    public static long[] statements = new long[60];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "org.jfree.data.category.CategoryToPieDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 28; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$32fm7p0jmh74c79cusve0ahxr7otadgj0nm8mld () {
    super("org.jfree.data.category.CategoryToPieDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 59; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 54; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.category.CategoryToPieDataset.java");
      for (int i = 1; i <= 59; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 54; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

