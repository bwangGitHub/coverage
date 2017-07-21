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
 * -----------------------
 * DefaultKeyedValues.java
 * -----------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Thomas Morgner;
 *
 * Changes:
 * --------
 * 31-Oct-2002 : Version 1 (DG);
 * 11-Feb-2003 : Fixed bug in getValue(key) method for unrecognised key (DG);
 * 05-Mar-2003 : Added methods to sort stored data 'by key' or 'by value' (DG);
 * 13-Mar-2003 : Implemented Serializable (DG);
 * 08-Apr-2003 : Modified removeValue(Comparable) method to fix bug 717049 (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 27-Aug-2003 : Moved SortOrder from org.jfree.data --> org.jfree.util (DG);
 * 09-Feb-2004 : Modified getIndex() method - see bug report 893256 (DG);
 * 15-Sep-2004 : Updated clone() method and added PublicCloneable 
 *               interface (DG);
 * 25-Nov-2004 : Small update to the clone() implementation (DG);
 * 24-Feb-2005 : Added methods addValue(Comparable, double) and 
 *               setValue(Comparable, double) for convenience (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jul-2006 : Added a clear() method (DG);
 * 01-Aug-2006 : Added argument check to getIndex() method (DG);
 * 30-Apr-2007 : Added insertValue() methods (DG);
 * 31-Oct-2007 : Performance improvements by using separate lists for keys and 
 *               values (TM);
 * 21-Nov-2007 : Fixed bug in removeValue() method from previous patch (DG);
 *               
 */

package org.jfree.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.jfree.util.PublicCloneable;
import org.jfree.util.SortOrder;

/**
 * An ordered list of (key, value) items.  This class provides a default 
 * implementation of the {@link KeyedValues} interface.
 */
public class DefaultKeyedValues implements KeyedValues, 
                                           Cloneable, PublicCloneable, 
                                           Serializable {
  static {
    CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8468154364608194797L;
  static {
    CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[1]++;
  }
    
    /** Storage for the keys. */
    private ArrayList keys;
    
    /** Storage for the values. */
    private ArrayList values;
    
    /** 
     * Contains (key, Integer) mappings, where the Integer is the index for
     * the key in the list. 
     */
    private HashMap indexMap; 

  /**
     * Creates a new collection (initially empty).
     */
    public DefaultKeyedValues() {
        this.keys = new ArrayList();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[2]++;
        this.values = new ArrayList();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[3]++;
        this.indexMap = new HashMap();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[4]++;
    }

    /**
     * Returns the number of items (values) in the collection.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.indexMap.size();
    }

    /**
     * Returns a value.
     *
     * @param item  the item of interest (zero-based index).
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @throws IndexOutOfBoundsException if <code>item</code> is out of bounds.
     */
    public Number getValue(int item) {
        return (Number) this.values.get(item);
    }

    /**
     * Returns a key.
     *
     * @param index  the item index (zero-based).
     *
     * @return The row key.
     * 
     * @throws IndexOutOfBoundsException if <code>item</code> is out of bounds.
     */
    public Comparable getKey(int index) {
        return (Comparable) this.keys.get(index);
    }

    /**
     * Returns the index for a given key.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The index, or <code>-1</code> if the key is not recognised.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     */
    public int getIndex(Comparable key) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[1]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[2]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[6]++;
        final Integer i = (Integer) this.indexMap.get(key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[3]++;
            return -1;
  // key not found
        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[4]++;}
        return i.intValue();
    }

    /**
     * Returns the keys for the values in the collection.
     *
     * @return The keys (never <code>null</code>).
     */
    public List getKeys() {
        return (List) this.keys.clone();
    }

    /**
     * Returns the value for a given key.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @throws UnknownKeyException if the key is not recognised.
     * 
     * @see #getValue(int)
     */
    public Number getValue(Comparable key) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[8]++;
        int index = getIndex(key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[5]++;
            throw new UnknownKeyException("Key not found: " + key);

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[6]++;}
        return getValue(index);
    }

    /**
     * Updates an existing value, or adds a new value to the collection.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value.
     * 
     * @see #addValue(Comparable, Number)
     */
    public void addValue(Comparable key, double value) {
        addValue(key, new Double(value));
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[10]++; 
    }
    
    /**
     * Adds a new value to the collection, or updates an existing value.
     * This method passes control directly to the 
     * {@link #setValue(Comparable, Number)} method.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public void addValue(Comparable key, Number value) {
        setValue(key, value);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[11]++;
    }

    /**
     * Updates an existing value, or adds a new value to the collection.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value.
     */
    public void setValue(Comparable key, double value) {
        setValue(key, new Double(value));
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[12]++;   
    }
    
    /**
     * Updates an existing value, or adds a new value to the collection.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public void setValue(Comparable key, Number value) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[7]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[8]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[14]++;
        int keyIndex = getIndex(key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((keyIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[9]++;
            this.keys.set(keyIndex, key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[16]++;
            this.values.set(keyIndex, value);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[17]++;

        }
        else {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[10]++;
            this.keys.add(key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[18]++;
            this.values.add(value);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[19]++;
            this.indexMap.put(key, new Integer(this.keys.size() - 1));
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[20]++;
        }
    }
    
    /**
     * Inserts a new value at the specified position in the dataset or, if
     * there is an existing item with the specified key, updates the value 
     * for that item and moves it to the specified position.
     * 
     * @param position  the position (in the range 0 to getItemCount()).
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value.
     * 
     * @since 1.0.6
     */
    public void insertValue(int position, Comparable key, double value) {
        insertValue(position, key, new Double(value));
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[21]++;
    }

    /**
     * Inserts a new value at the specified position in the dataset or, if
     * there is an existing item with the specified key, updates the value 
     * for that item and moves it to the specified position.
     * 
     * @param position  the position (in the range 0 to getItemCount()).
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     * 
     * @since 1.0.6
     */
    public void insertValue(int position, Comparable key, Number value) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((position < 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((position > getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[11]++;
            throw new IllegalArgumentException("'position' out of bounds.");

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[12]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[13]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[14]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[24]++;
        int pos = getIndex(key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((pos == position) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[15]++;
            this.keys.set(pos, key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[26]++;
            this.values.set(pos, value);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[27]++;

        }
        else {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[16]++;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((pos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[17]++;
                this.keys.remove(pos);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[29]++;
                this.values.remove(pos);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[30]++;

            } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[18]++;}
          
            this.keys.add(position, key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[31]++;
            this.values.add(position, value);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[32]++;
            rebuildIndex();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[33]++;
        }
    }

    /**
     * Rebuilds the key to indexed-position mapping after an positioned insert
     * or a remove operation.
     */
    private void rebuildIndex () {
        this.indexMap.clear();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[34]++;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[35]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < this.keys.size()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[1]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[2]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[3]++;
}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[36]++;
            final Object key = this.keys.get(i);
            this.indexMap.put(key, new Integer(i));
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[37]++;
        }
    }

    /**
     * Removes a value from the collection.
     *
     * @param index  the index of the item to remove (in the range 
     *     <code>0</code> to <code>getItemCount() - 1</code>).
     *     
     * @throws IndexOutOfBoundsException if <code>index</code> is not within
     *     the specified range.
     */
    public void removeValue(int index) {
        this.keys.remove(index);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[38]++;
        this.values.remove(index);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[39]++;
        rebuildIndex();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[40]++;
    }

    /**
     * Removes a value from the collection.
     *
     * @param key  the item key (<code>null</code> not permitted).
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     * @throws UnknownKeyException if <code>key</code> is not recognised.
     */
    public void removeValue(Comparable key) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[41]++;
        int index = getIndex(key);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[19]++;
            throw new UnknownKeyException("The key (" + key 
                    + ") is not recognised.");

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[20]++;}
        removeValue(index);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[43]++;
    }
    
    /**
     * Clears all values from the collection.
     * 
     * @since 1.0.2
     */
    public void clear() {
        this.keys.clear();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[44]++;
        this.values.clear();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[45]++;
        this.indexMap.clear();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[46]++;
    }

    /**
     * Sorts the items in the list by key.
     *
     * @param order  the sort order (<code>null</code> not permitted).
     */
    public void sortByKeys(SortOrder order) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[47]++;
        final int size = this.keys.size();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[48]++;
        final DefaultKeyedValue[] data = new DefaultKeyedValue[size];
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[49]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[4]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[5]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[6]++;
}
            data[i] = new DefaultKeyedValue((Comparable) this.keys.get(i), 
                    (Number) this.values.get(i));
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[50]++;
        }
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[51]++;

        Comparator comparator = new KeyedValueComparator(
                KeyedValueComparatorType.BY_KEY, order);
        Arrays.sort(data, comparator);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[52]++;
        clear();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[53]++;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[54]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < data.length) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[7]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[8]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[9]++;
}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[55]++;
            final DefaultKeyedValue value = data[i];
            addValue(value.getKey(), value.getValue());
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[56]++;
        }
    }

    /**
     * Sorts the items in the list by value.  If the list contains 
     * <code>null</code> values, they will sort to the end of the list, 
     * irrespective of the sort order.
     *
     * @param order  the sort order (<code>null</code> not permitted).
     */
    public void sortByValues(SortOrder order) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[57]++;
        final int size = this.keys.size();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[58]++;
        final DefaultKeyedValue[] data = new DefaultKeyedValue[size];
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[59]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[10]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[10]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[11]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[12]++;
}
            data[i] = new DefaultKeyedValue((Comparable) this.keys.get(i), 
                    (Number) this.values.get(i));
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[60]++;
        }
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[61]++;

        Comparator comparator = new KeyedValueComparator(
                KeyedValueComparatorType.BY_VALUE, order);
        Arrays.sort(data, comparator);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[62]++;

        clear();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[63]++;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[64]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < data.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[13]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[14]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[15]++;
}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[65]++;
            final DefaultKeyedValue value = data[i];
            addValue(value.getKey(), value.getValue());
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[66]++;
        }
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[21]++;
            return true;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[22]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[68]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj instanceof KeyedValues) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[24]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[69]++;

        KeyedValues that = (KeyedValues) obj;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[70]++;
        int count = getItemCount();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[71]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((count != that.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[26]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[72]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[16]++;


int CodeCoverConditionCoverageHelper_C19;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[16]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[17]--;
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.loops[18]++;
}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[73]++;
            Comparable k1 = getKey(i);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[74]++;
            Comparable k2 = that.getKey(i);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[75]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((k1.equals(k2)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[27]++;
                return false;

            } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[28]++;}
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[76]++;
            Number v1 = getValue(i);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[77]++;
            Number v2 = that.getValue(i);
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[78]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((v1 == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[29]++;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[79]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((v2 != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[31]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[32]++;}

            }
            else {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[30]++;
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[80]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((v1.equals(v2)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[33]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.branches[34]++;}
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
        return (this.keys != null ? this.keys.hashCode() : 0);
    }

    /**
     * Returns a clone.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[81]++;
        DefaultKeyedValues clone = (DefaultKeyedValues) super.clone();
        clone.keys = (ArrayList) this.keys.clone();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[82]++;
        clone.values = (ArrayList) this.values.clone();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[83]++;
        clone.indexMap = (HashMap) this.indexMap.clone();
CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx.statements[84]++;
        return clone;
    }
    
}

class CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx ());
  }
    public static long[] statements = new long[85];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.data.DefaultKeyedValues.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$27sy6oy8nww16gdg8rdy3yo67j3ykyvj7rkx () {
    super("org.jfree.data.DefaultKeyedValues.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 84; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.DefaultKeyedValues.java");
      for (int i = 1; i <= 84; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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

