/* ======================================
 * JFreeChart : a free Java chart library
 * ======================================
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
 * --------------------------
 * CustomPieURLGenerator.java
 * --------------------------
 * (C) Copyright 2004-2007, by David Basten and Contributors.
 *
 * Original Author:  David Basten;
 * Contributors:     -;
 *
 * Changes:
 * --------
 * 04-Feb-2004 : Version 1, contributed by David Basten based on 
 *               CustomXYURLGenerator by Richard Atkinson (added to main source
 *               tree on 25-May-2004);
 *
 */
package org.jfree.chart.urls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.util.PublicCloneable;

/**
 * A custom URL generator for pie charts.
 */
public class CustomPieURLGenerator implements PieURLGenerator, 
                                              Cloneable, 
                                              PublicCloneable, 
                                              Serializable {
  static {
    CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7100607670144900503L;
  static {
    CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[1]++;
  }

    /** Storage for the URLs. */
    private ArrayList urls;

    /**
     * Creates a new <code>CustomPieURLGenerator</code> instance, initially
     * empty.  Call {@link #addURLs(Map)} to specify the URL fragments to be
     * used.
     */
    public CustomPieURLGenerator() {
        this.urls = new ArrayList();
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[2]++;
    }

    /**
     * Generates a URL fragment.
     *
     * @param dataset  the dataset (ignored).
     * @param key  the item key.
     * @param pieIndex  the pie index.
     *
     * @return A string containing the generated URL.
     * 
     * @see #getURL(Comparable, int)
     */
    public String generateURL(PieDataset dataset, Comparable key, 
                              int pieIndex) {
        return getURL(key, pieIndex);
    }

    /**
     * Returns the number of URL maps stored by the renderer.
     * 
     * @return The list count.
     * 
     * @see #addURLs(Map)
     */
    public int getListCount() {
        return this.urls.size();
    }
    
    /**
     * Returns the number of URLs in a given map (specified by its position 
     * in the map list).
     * 
     * @param list  the list index (zero based).
     * 
     * @return The URL count.
     * 
     * @see #getListCount()
     */
    public int getURLCount(int list) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[3]++;
        int result = 0;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[4]++;
        Map urlMap = (Map) this.urls.get(list);
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((urlMap != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[1]++;
            result = urlMap.size();
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[6]++;

        } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[2]++;}
        return result;
    }

    /**
     * Returns the URL for a section in the specified map.
     * 
     * @param key  the key.
     * @param mapIndex  the map index.
     * 
     * @return The URL.
     */    
    public String getURL(Comparable key, int mapIndex) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[7]++;
        String result = null;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((mapIndex < getListCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[3]++;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[9]++;
            Map urlMap = (Map) this.urls.get(mapIndex);
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((urlMap != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[5]++;
                result = (String) urlMap.get(key);
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[11]++;

            } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[4]++;}
        return result;
    }

    /**
     * Adds a map containing <code>(key, URL)</code> mappings where each
     * <code>key</code> is an instance of <code>Comparable</code> 
     * (corresponding to the key for an item in a pie dataset) and each 
     * <code>URL</code> is a <code>String</code> representing a URL fragment.
     * <br><br>
     * The map is appended to an internal list...you can add multiple maps
     * if you are working with, say, a {@link MultiplePiePlot}.
     * 
     * @param urlMap  the URLs (<code>null</code> permitted).
     */
    public void addURLs(Map urlMap) {
        this.urls.add(urlMap);
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[12]++;
    }
    
    /**
     * Tests if this object is equal to another.
     * 
     * @param o  the other object.
     * 
     * @return A boolean.
     */
    public boolean equals(Object o) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
    
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((o == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[8]++;}
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((o instanceof CustomPieURLGenerator) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[9]++;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[15]++;
            CustomPieURLGenerator generator = (CustomPieURLGenerator) o;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((getListCount() != generator.getListCount()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[11]++;
                return false;

            } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[12]++;}
            Set keySet;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[17]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
            for (int pieItem = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((pieItem < getListCount()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); pieItem++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[1]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[2]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[3]++;
}
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[18]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getURLCount(pieItem) != generator.getURLCount(pieItem)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[13]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[14]++;}
                keySet = ((HashMap) this.urls.get(pieItem)).keySet();
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[19]++;
                String key;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[20]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;
                for (Iterator i = keySet.iterator();(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[4]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[5]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[6]++;
}
                key = (String) i.next();
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[21]++;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[22]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((getURL(key, pieItem).equals(
                            generator.getURL(key, pieItem))) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[15]++;
                        return false;

                    } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[16]++;}
                }
            }
            return true;

        } else {
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.branches[10]++;}
        return false;
    }

    /**
     * Returns a clone of the generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[23]++;
        CustomPieURLGenerator urlGen = new CustomPieURLGenerator();
        Map map;
        Map newMap;
        String key;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[24]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[7]++;


int CodeCoverConditionCoverageHelper_C11;

        for (Iterator i = this.urls.iterator();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[7]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[8]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[9]++;
}
            map = (Map) i.next();
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[25]++;

            newMap = new HashMap();
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[26]++;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[27]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[10]++;


int CodeCoverConditionCoverageHelper_C12;
            for (Iterator j = map.keySet().iterator();(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((j.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false);) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[10]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[11]--;
  CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.loops[12]++;
}
                key = (String) j.next();
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[28]++;
                newMap.put(key, map.get(key));
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[29]++;
            }

            urlGen.addURLs(newMap);
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[30]++;
            newMap = null;
CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd.statements[31]++;
        }

        return urlGen;
    }

}

class CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.chart.urls.CustomPieURLGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$lu7tsl1evicjdhs85hotjb235g5ijd3kl2yajotd () {
    super("org.jfree.chart.urls.CustomPieURLGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.urls.CustomPieURLGenerator.java");
      for (int i = 1; i <= 31; i++) {
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
    for (int i = 1; i <= 12; i++) {
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

