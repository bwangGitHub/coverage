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
 * KeyToGroupMap.java
 * ------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 29-Apr-2004 : Version 1 (DG);
 * 07-Jul-2004 : Added a group list to ensure group index is consistent, fixed 
 *               cloning problem (DG);
 * 18-Aug-2005 : Added casts in clone() method to suppress 1.5 compiler 
 *               warnings - see patch 1260587 (DG);
 * 
 */

package org.jfree.data;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A class that maps keys (instances of <code>Comparable</code>) to groups.
 */
public class KeyToGroupMap implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -2228169345475318082L;
  static {
    CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[1]++;
  }
    
    /** The default group. */
    private Comparable defaultGroup;
    
    /** The groups. */
    private List groups;
    
    /** A mapping between keys and groups. */
    private Map keyToGroupMap;
    
    /**
     * Creates a new map with a default group named 'Default Group'.
     */
    public KeyToGroupMap() {
        this("Default Group");
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[2]++;
    }
    
    /**
     * Creates a new map with the specified default group.
     * 
     * @param defaultGroup  the default group (<code>null</code> not permitted).
     */
    public KeyToGroupMap(Comparable defaultGroup) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((defaultGroup == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[1]++;
            throw new IllegalArgumentException("Null 'defaultGroup' argument.");

        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[2]++;}
        this.defaultGroup = defaultGroup;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[4]++;
        this.groups = new ArrayList();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[5]++;
        this.keyToGroupMap = new HashMap();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[6]++;
    }
    
    /**
     * Returns the number of groups in the map.
     * 
     * @return The number of groups in the map.
     */
    public int getGroupCount() {
        return this.groups.size() + 1;
    }
    
    /**
     * Returns a list of the groups (always including the default group) in the 
     * map.  The returned list is independent of the map, so altering the list 
     * will have no effect.
     * 
     * @return The groups (never <code>null</code>).
     */
    public List getGroups() {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[7]++;
        List result = new ArrayList();
        result.add(this.defaultGroup);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[8]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[9]++;
        Iterator iterator = this.groups.iterator();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[1]--;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[2]--;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[3]++;
}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[11]++;
            Comparable group = (Comparable) iterator.next();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((result.contains(group)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[3]++;
                result.add(group);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[13]++;
   
            } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[4]++;}
        } 
        return result;
    }
    
    /**
     * Returns the index for the group.
     * 
     * @param group  the group.
     * 
     * @return The group index (or -1 if the group is not represented within 
     *         the map).
     */
    public int getGroupIndex(Comparable group) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[14]++;
        int result = this.groups.indexOf(group);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[5]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.defaultGroup.equals(group)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[7]++;
                result = 0;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[17]++;

            } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[8]++;}

        }
        else {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[6]++;
            result = result + 1;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[18]++;   
        }
        return result;   
    }
    
    /**
     * Returns the group that a key is mapped to.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @return The group (never <code>null</code>, returns the default group if
     *         there is no mapping for the specified key).
     */
    public Comparable getGroup(Comparable key) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[9]++;
            throw new IllegalArgumentException("Null 'key' argument.");
   
        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[10]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[20]++;
        Comparable result = this.defaultGroup;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[21]++;
        Comparable group = (Comparable) this.keyToGroupMap.get(key);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((group != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[11]++;
            result = group;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[23]++;
   
        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[12]++;}
        return result;
    }
    
    /**
     * Maps a key to a group.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * @param group  the group (<code>null</code> permitted, clears any 
     *               existing mapping).
     */
    public void mapKeyToGroup(Comparable key, Comparable group) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[13]++;
            throw new IllegalArgumentException("Null 'key' argument.");
   
        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[14]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[25]++;
        Comparable currentGroup = getGroup(key);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((currentGroup.equals(this.defaultGroup)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[15]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((currentGroup.equals(group)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[17]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[28]++;
                int count = getKeyCount(currentGroup);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[29]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((count == 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[19]++;
                    this.groups.remove(currentGroup);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[30]++;
   
                } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[20]++;}

            } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[16]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[31]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((group == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[21]++;
            this.keyToGroupMap.remove(key);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[32]++;
 
        }
        else {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[22]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.groups.contains(group)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[23]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[34]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.defaultGroup.equals(group)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[25]++;
                    this.groups.add(group);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[35]++;

                } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[26]++;}

            } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[24]++;}
            this.keyToGroupMap.put(key, group);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[36]++;
        }
    }
    
    /**
     * Returns the number of keys mapped to the specified group.  This method 
     * won't always return an accurate result for the default group, since 
     * explicit mappings are not required for this group.
     * 
     * @param group  the group (<code>null</code> not permitted).
     * 
     * @return The key count.
     */
    public int getKeyCount(Comparable group) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[37]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((group == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[27]++;
            throw new IllegalArgumentException("Null 'group' argument.");
   
        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[28]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[38]++;
        int result = 0;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[39]++;
        Iterator iterator = this.keyToGroupMap.values().iterator();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[40]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;
        while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[4]--;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[5]--;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[6]++;
}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[41]++;
            Comparable g = (Comparable) iterator.next();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[42]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((group.equals(g)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[29]++;
                result++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[43]++;

            } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[30]++;}
        }
        return result;
    }
    
    /**
     * Tests the map for equality against an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[44]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[31]++;
            return true;
      
        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[32]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[45]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj instanceof KeyToGroupMap) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[34]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[46]++;
        KeyToGroupMap that = (KeyToGroupMap) obj;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[47]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.defaultGroup, that.defaultGroup)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[36]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[48]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.keyToGroupMap.equals(that.keyToGroupMap)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[38]++;}
        return true;
    }
    
    /**
     * Returns a clone of the map.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if there is a problem cloning the
     *                                     map.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[49]++;
        KeyToGroupMap result = (KeyToGroupMap) super.clone();
        result.defaultGroup 
            = (Comparable) KeyToGroupMap.clone(this.defaultGroup);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[50]++;
        result.groups = (List) KeyToGroupMap.clone(this.groups);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[51]++;
        result.keyToGroupMap = (Map) KeyToGroupMap.clone(this.keyToGroupMap);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[52]++;
        return result;
    }
    
    /**
     * Attempts to clone the specified object using reflection.
     * 
     * @param object  the object (<code>null</code> permitted).
     * 
     * @return The cloned object, or the original object if cloning failed.
     */
    private static Object clone(Object object) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[53]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[39]++;
            return null;
   
        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[40]++;}
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[54]++;
        Class c = object.getClass();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[55]++;
        Object result = null;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[56]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[57]++;
            Method m = c.getMethod("clone", (Class[]) null);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[58]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((Modifier.isPublic(m.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[42]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[59]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    result = m.invoke(object, (Object[]) null);
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[60]++;
                }
                catch (Exception e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[45]++;
                    e.printStackTrace();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[61]++;  
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[44]++;
}
  }

            } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[43]++;}
        }
        catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[46]++;
            result = object;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[62]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[41]++;
}
  }
        return result;
    }
    
    /**
     * Returns a clone of the list.
     * 
     * @param list  the list.
     * 
     * @return A clone of the list.
     * 
     * @throws CloneNotSupportedException if the list could not be cloned.
     */
    private static Collection clone(Collection list) 
        throws CloneNotSupportedException {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[63]++;
        Collection result = null;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[64]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((list != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[47]++;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[65]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
            try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[66]++;
                List clone = (List) list.getClass().newInstance();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[67]++;
                Iterator iterator = list.iterator();
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[68]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[7]++;


int CodeCoverConditionCoverageHelper_C25;
                while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[7]--;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[8]--;
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.loops[9]++;
}
                    clone.add(KeyToGroupMap.clone(iterator.next()));
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[69]++;
                }
                result = clone;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.statements[70]++;
            }
            catch (Exception e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[50]++;
                throw new CloneNotSupportedException("Exception.");
            } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[49]++;
}
  }

        } else {
  CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl.branches[48]++;}
        return result;
    }

}

class CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl ());
  }
    public static long[] statements = new long[71];
    public static long[] branches = new long[51];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "org.jfree.data.KeyToGroupMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 25; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$69ptnn2aukuvyfniiqrptp5wipnl () {
    super("org.jfree.data.KeyToGroupMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 70; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 50; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.KeyToGroupMap.java");
      for (int i = 1; i <= 70; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 50; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

