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
 * DefaultPieDataset.java
 * ----------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sam (oldman);
 *
 * Changes
 * -------
 * 17-Nov-2001 : Version 1 (DG);
 * 22-Jan-2002 : Removed legend methods from dataset implementations (DG);
 * 07-Apr-2002 : Modified implementation to guarantee data sequence to remain 
 *               in the order categories are added (oldman);
 * 23-Oct-2002 : Added getCategory(int) method and getItemCount() method, in 
 *               line with changes to the PieDataset interface (DG);
 * 04-Feb-2003 : Changed underlying data storage to DefaultKeyedValues (DG);
 * 04-Mar-2003 : Inserted DefaultKeyedValuesDataset class into hierarchy (DG);
 * 24-Apr-2003 : Switched places with DefaultKeyedValuesDataset (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 03-Mar-2005 : Implemented PublicCloneable (DG);
 * 29-Jun-2005 : Added remove() method (DG);
 * ------------- JFREECHART 1.0.0 ---------------------------------------------
 * 31-Jul-2006 : Added a clear() method to clear all values from the 
 *               dataset (DG);
 * 28-Sep-2006 : Added sortByKeys() and sortByValues() methods (DG);
 * 30-Apr-2007 : Added new insertValues() methods (DG);
 * 
 */

package org.jfree.data.general;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.UnknownKeyException;
import org.jfree.util.PublicCloneable;
import org.jfree.util.SortOrder;

/**
 * A default implementation of the {@link PieDataset} interface.
 */
public class DefaultPieDataset extends AbstractDataset
                               implements PieDataset, 
                                          Cloneable, PublicCloneable, 
                                          Serializable {
  static {
    CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2904745139106540618L;
  static {
    CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[1]++;
  }
    
    /** Storage for the data. */
    private DefaultKeyedValues data;

    /**
     * Constructs a new dataset, initially empty.
     */
    public DefaultPieDataset() {
        this.data = new DefaultKeyedValues();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[2]++;
    }

    /**
     * Creates a new dataset by copying data from a {@link KeyedValues} 
     * instance.
     *
     * @param data  the data (<code>null</code> not permitted).
     */
    public DefaultPieDataset(KeyedValues data) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[1]++;
            throw new IllegalArgumentException("Null 'data' argument.");
   
        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[2]++;}
        this.data = new DefaultKeyedValues();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[4]++;
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < data.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[1]--;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[2]--;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[3]++;
}
            this.data.addValue(data.getKey(i), data.getValue(i));
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[6]++;
        }
    }

    /**
     * Returns the number of items in the dataset.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.data.getItemCount();
    }

    /**
     * Returns the categories in the dataset.  The returned list is 
     * unmodifiable.
     *
     * @return The categories in the dataset.
     */
    public List getKeys() {
        return Collections.unmodifiableList(this.data.getKeys());
    }

    /**
     * Returns the key for the specified item, or <code>null</code>. 
     *
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount() - 1</code>).
     *
     * @return The key, or <code>null</code>.
     * 
     * @throws IndexOutOfBoundsException if <code>item</code> is not in the
     *     specified range.
     */
    public Comparable getKey(int item) {
        return this.data.getKey(item);
    }

    /**
     * Returns the index for a key, or -1 if the key is not recognised.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The index, or <code>-1</code> if the key is unrecognised.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     */
    public int getIndex(Comparable key) {
        return this.data.getIndex(key);
    }

    /**
     * Returns a value.
     *
     * @param item  the value index.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue(int item) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[7]++;

        Number result = null;
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((getItemCount() > item) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[3]++;
            result = this.data.getValue(item);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[9]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[4]++;}
        return result;

    }

    /**
     * Returns the data value associated with a key.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @throws UnknownKeyException if the key is not recognised.
     */
    public Number getValue(Comparable key) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[5]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[6]++;}
        return this.data.getValue(key);
    }

    /**
     * Sets the data value for a key and sends a {@link DatasetChangeEvent} to
     * all registered listeners.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     */
    public void setValue(Comparable key, Number value) {
        this.data.setValue(key, value);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[11]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[12]++;
    }

    /**
     * Sets the data value for a key and sends a {@link DatasetChangeEvent} to
     * all registered listeners.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     */
    public void setValue(Comparable key, double value) {
        setValue(key, new Double(value));
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[13]++;
    }
    
    /**
     * Inserts a new value at the specified position in the dataset or, if
     * there is an existing item with the specified key, updates the value 
     * for that item and moves it to the specified position.  After the change
     * is made, this methods sends a {@link DatasetChangeEvent} to all 
     * registered listeners.
     * 
     * @param position  the position (in the range 0 to getItemCount()).
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     * 
     * @since 1.0.6
     */
    public void insertValue(int position, Comparable key, double value) {
        insertValue(position, key, new Double(value));
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[14]++;
    }

    /**
     * Inserts a new value at the specified position in the dataset or, if
     * there is an existing item with the specified key, updates the value 
     * for that item and moves it to the specified position.  After the change
     * is made, this methods sends a {@link DatasetChangeEvent} to all 
     * registered listeners.
     * 
     * @param position  the position (in the range 0 to getItemCount()).
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     * 
     * @since 1.0.6
     */
    public void insertValue(int position, Comparable key, Number value) {
        this.data.insertValue(position, key, value);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[15]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[16]++;
    }

    /**
     * Removes an item from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     */
    public void remove(Comparable key) {
        this.data.removeValue(key);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[17]++;   
        fireDatasetChanged();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[18]++;
    }
    
    /**
     * Clears all data from this dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners (unless the dataset was already empty).
     * 
     * @since 1.0.2
     */
    public void clear() {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getItemCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[7]++;
            this.data.clear();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[20]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[21]++;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[8]++;}
    }

    /**
     * Sorts the dataset's items by key and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     * 
     * @param order  the sort order (<code>null</code> not permitted).
     * 
     * @since 1.0.3
     */
    public void sortByKeys(SortOrder order) {
        this.data.sortByKeys(order);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[22]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[23]++;
    }
    
    /**
     * Sorts the dataset's items by value and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     * 
     * @param order  the sort order (<code>null</code> not permitted).
     * 
     * @since 1.0.3
     */
    public void sortByValues(SortOrder order) {
        this.data.sortByValues(order);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[24]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[25]++;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[10]++;}
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof PieDataset) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[12]++;}
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[28]++;
        PieDataset that = (PieDataset) obj;
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[29]++;
        int count = getItemCount();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((that.getItemCount() != count) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[14]++;}
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[31]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[4]--;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[5]--;
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.loops[6]++;
}
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[32]++;
            Comparable k1 = getKey(i);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[33]++;
            Comparable k2 = that.getKey(i);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[34]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((k1.equals(k2)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[15]++;
                return false;

            } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[16]++;}
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[35]++;

            Number v1 = getValue(i);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[36]++;
            Number v2 = that.getValue(i);
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((v1 == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[17]++;
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((v2 != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[19]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[20]++;}

            }
            else {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[18]++;
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[39]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((v1.equals(v2)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[21]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.branches[22]++;}
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
        return this.data.hashCode();
    }

    /**
     * Returns a clone of the dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException This class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[40]++;
        DefaultPieDataset clone = (DefaultPieDataset) super.clone();
        clone.data = (DefaultKeyedValues) this.data.clone();
CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9.statements[41]++;
        return clone;    
    }
    
}

class CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9 ());
  }
    public static long[] statements = new long[42];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.data.general.DefaultPieDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$b80b2dtcd297h1h5zie9c0ferrtrqunvq9 () {
    super("org.jfree.data.general.DefaultPieDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 41; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.DefaultPieDataset.java");
      for (int i = 1; i <= 41; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

