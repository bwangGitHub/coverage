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
 * -----------------
 * KeyedObjects.java
 * -----------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 31-Oct-2002 : Version 1 (DG);
 * 11-Jan-2005 : Minor tidy up (DG);
 * 28-Sep-2007 : Clean up equals() method (DG);
 * 03-Oct-2007 : Make method behaviour consistent with DefaultKeyedValues (DG);
 *
 */

package org.jfree.data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.util.PublicCloneable;

/**
 * A collection of (key, object) pairs.
 */
public class KeyedObjects implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1321582394193530984L;
  static {
    CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[1]++;
  }
    
    /** Storage for the data. */
    private List data;

    /**
     * Creates a new collection (initially empty).
     */
    public KeyedObjects() {
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[2]++;
    }

    /**
     * Returns the number of items (values) in the collection.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.data.size();
    }

    /**
     * Returns an object from the list.
     *
     * @param item  the item index (zero-based).
     *
     * @return The object (possibly <code>null</code>).
     * 
     * @throws IndexOutOfBoundsException if <code>item</code> is out of bounds.
     */
    public Object getObject(int item) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[3]++;
        Object result = null;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[4]++;
        KeyedObject kobj = (KeyedObject) this.data.get(item);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((kobj != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[1]++;
            result = kobj.getObject();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[6]++;

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[2]++;}
        return result;
    }

    /**
     * Returns the key at the specified position in the list.
     *
     * @param index  the item index (zero-based).
     *
     * @return The row key.
     *
     * @throws IndexOutOfBoundsException if <code>item</code> is out of bounds.
     * 
     * @see #getIndex(Comparable)
     */
    public Comparable getKey(int index) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[7]++;
        Comparable result = null;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[8]++;
        KeyedObject item = (KeyedObject) this.data.get(index);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[3]++;
            result = item.getKey();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[10]++;

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[4]++;}
        return result;
    }

    /**
     * Returns the index for a given key, or <code>-1</code>.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The index, or <code>-1</code> if the key is unrecognised.
     * 
     * @see #getKey(int)
     */
    public int getIndex(Comparable key) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[5]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[6]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[12]++;
        int i = 0;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[13]++;
        Iterator iterator = this.data.iterator();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[1]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[2]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[3]++;
}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[15]++;
            KeyedObject ko = (KeyedObject) iterator.next();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ko.getKey().equals(key)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[7]++;
                return i;

            } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[8]++;}
            i++;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[17]++;
        }
        return -1;
    }

    /**
     * Returns a list containing all the keys in the list.
     *
     * @return The keys (never <code>null</code>).
     */
    public List getKeys() {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[18]++;
        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[19]++;
        Iterator iterator = this.data.iterator();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[20]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[4]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[5]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[6]++;
}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[21]++;
            KeyedObject ko = (KeyedObject) iterator.next();
            result.add(ko.getKey());
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[22]++;
        }
        return result;
    }

    /**
     * Returns the object for a given key. If the key is not recognised, the 
     * method should return <code>null</code>.
     *
     * @param key  the key.
     *
     * @return The object (possibly <code>null</code>).
     * 
     * @see #addObject(Comparable, Object)
     */
    public Object getObject(Comparable key) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[23]++;
        int index = getIndex(key);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[9]++;
            throw new UnknownKeyException("The key (" + key 
                    + ") is not recognised.");

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[10]++;}
        return getObject(index);
    }

    /**
     * Adds a new object to the collection, or overwrites an existing object.  
     * This is the same as the {@link #setObject(Comparable, Object)} method.
     *
     * @param key  the key.
     * @param object  the object.
     * 
     * @see #getObject(Comparable)
     */
    public void addObject(Comparable key, Object object) {
        setObject(key, object);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[25]++;
    }

    /**
     * Replaces an existing object, or adds a new object to the collection.
     * This is the same as the {@link #addObject(Comparable, Object)} 
     * method.
     *
     * @param key  the key (<code>null</code> not permitted).
     * @param object  the object.
     * 
     * @see #getObject(Comparable)
     */
    public void setObject(Comparable key, Object object) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[26]++;
        int keyIndex = getIndex(key);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((keyIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[11]++;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[28]++;
            KeyedObject ko = (KeyedObject) this.data.get(keyIndex);
            ko.setObject(object);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[29]++;

        }
        else {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[12]++;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[30]++;
            KeyedObject ko = new KeyedObject(key, object);
            this.data.add(ko);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[31]++;
        }
    }

    /**
     * Inserts a new value at the specified position in the dataset or, if
     * there is an existing item with the specified key, updates the value 
     * for that item and moves it to the specified position.
     * 
     * @param position  the position (in the range <code>0</code> to 
     *                  <code>getItemCount()</code>).
     * @param key  the key (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     * 
     * @since 1.0.7
     */
    public void insertValue(int position, Comparable key, Object value) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((position < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((position > this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[13]++;
            throw new IllegalArgumentException("'position' out of bounds.");

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[14]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[15]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[16]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[34]++;
        int pos = getIndex(key);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[35]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((pos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[17]++;
            this.data.remove(pos);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[36]++;

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[18]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[37]++;
        KeyedObject item = new KeyedObject(key, value);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((position <= this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[19]++;
            this.data.add(position, item);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[39]++;

        }
        else {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[20]++;
            this.data.add(item);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[40]++;
        }
    }

    /**
     * Removes a value from the collection.
     *
     * @param index  the index of the item to remove.
     * 
     * @see #removeValue(Comparable)
     */
    public void removeValue(int index) {
        this.data.remove(index);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[41]++;
    }

    /**
     * Removes a value from the collection.
     *
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @see #removeValue(int)
     * 
     * @throws UnknownKeyException if the key is not recognised.
     */
    public void removeValue(Comparable key) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[42]++;
        // defer argument checking 
        int index = getIndex(key);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[21]++;
            throw new UnknownKeyException("The key (" + key.toString() 
                    + ") is not recognised.");

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[22]++;}
        removeValue(index);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[44]++;
    }
    
    /**
     * Clears all values from the collection.
     * 
     * @since 1.0.7
     */
    public void clear() {
        this.data.clear();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[45]++;
    }

    /**
     * Returns a clone of this object.  Keys in the list should be immutable
     * and are not cloned.  Objects in the list are cloned only if they
     * implement {@link PublicCloneable}.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[46]++;
        KeyedObjects clone = (KeyedObjects) super.clone();
        clone.data = new java.util.ArrayList();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[47]++;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[48]++;
        Iterator iterator = this.data.iterator();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[49]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;
        while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[7]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[8]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[9]++;
}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[50]++;
            KeyedObject ko = (KeyedObject) iterator.next();
            clone.data.add(ko.clone());
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[51]++;
        }
        return clone;      
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[24]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[53]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj instanceof KeyedObjects) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[26]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[54]++;
        KeyedObjects that = (KeyedObjects) obj;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[55]++;
        int count = getItemCount();
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[56]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((count != that.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[28]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[57]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[10]++;


int CodeCoverConditionCoverageHelper_C18;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[10]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[11]--;
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.loops[12]++;
}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[58]++;
            Comparable k1 = getKey(i);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[59]++;
            Comparable k2 = that.getKey(i);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[60]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((k1.equals(k2)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[29]++;
                return false;

            } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[30]++;}
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[61]++;
            Object o1 = getObject(i);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[62]++;
            Object o2 = that.getObject(i);
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[63]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((o1 == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[31]++;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[64]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((o2 != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[33]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[34]++;}

            }
            else {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[32]++;
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.statements[65]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((o1.equals(o2)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[35]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t.branches[36]++;}
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
        return (this.data != null ? this.data.hashCode() : 0);
    }
  
}

class CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t ());
  }
    public static long[] statements = new long[66];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.jfree.data.KeyedObjects.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 22; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$vqozen8n6aath42i1j61t3689t () {
    super("org.jfree.data.KeyedObjects.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 65; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.KeyedObjects.java");
      for (int i = 1; i <= 65; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

