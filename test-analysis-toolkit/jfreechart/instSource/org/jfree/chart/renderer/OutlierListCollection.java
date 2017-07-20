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
 * --------------------------
 * OutlierListCollection.java
 * --------------------------
 * (C) Copyright 2003, 2004, 2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 01-Sep-2003 : Made storage internal rather than extending ArrayList (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A collection of outlier lists for a box and whisker plot. Each collection is
 * associated with a single box and whisker entity.
 *
 * Outliers are grouped in lists for each entity. Lists contain
 * one or more outliers, determined by whether overlaps have
 * occurred. Overlapping outliers are grouped in the same list.
 *
 * @see org.jfree.chart.renderer.OutlierList
 */
public class OutlierListCollection {
  static {
    CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.ping();
  }


    /** Storage for the outlier lists. */
    private List outlierLists;
    
    /** 
     * Unbelievably, outliers which are more than 2 * interquartile range are
     * called far outs...  See Tukey EDA  (a classic one of a kind...)
     */
    private boolean highFarOut = false;
  {
    CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[1]++;
  }

    /**
     * A flag that indicates whether or not the collection contains low far 
     * out values.
     */
    private boolean lowFarOut = false;
  {
    CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[2]++;
  }
    
    /**
     * Creates a new empty collection.
     */
    public OutlierListCollection() {
        this.outlierLists = new ArrayList();
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[3]++;
    }
    
    /**
     * A flag to indicate the presence of one or more far out values at the 
     * top end of the range.
     *
     * @return A <code>boolean</code>.
     */
    public boolean isHighFarOut() {
        return this.highFarOut;
    }

    /**
     * Sets the flag that indicates the presence of one or more far out values 
     * at the top end of the range.
     *
     * @param farOut  the flag.
     */
    public void setHighFarOut(boolean farOut) {
        this.highFarOut = farOut;
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[4]++;
    }

    /**
     * A flag to indicate the presence of one or more far out values at the 
     * bottom end of the range.
     *
     * @return A <code>boolean</code>.
     */
    public boolean isLowFarOut() {
        return this.lowFarOut;
    }

    /**
     * Sets the flag that indicates the presence of one or more far out values 
     * at the bottom end of the range.
     *
     * @param farOut  the flag.
     */
    public void setLowFarOut(boolean farOut) {
        this.lowFarOut = farOut;
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[5]++;
    }
    /**
     * Appends the specified element as a new <code>OutlierList</code> to the
     * end of this list if it does not overlap an outlier in an existing list.
     *
     * If it does overlap, it is appended to the outlier list which it overlaps
     * and that list is updated.
     *
     * @param outlier  element to be appended to this list.
     * 
     * @return <tt>true</tt> (as per the general contract of Collection.add).
     */
    public boolean add(Outlier outlier) {
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.outlierLists.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.branches[1]++;
            return this.outlierLists.add(new OutlierList(outlier));

        } 
        else {
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.branches[2]++;
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[7]++;
            boolean updated = false;
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
            for (Iterator iterator = this.outlierLists.iterator();(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.loops[1]--;
  CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.loops[2]--;
  CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.loops[3]++;
}
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[9]++;
                OutlierList list = (OutlierList) iterator.next();
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((list.isOverlapped(outlier)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.branches[3]++;
                    updated = updateOutlierList(list, outlier);
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[11]++;

                } else {
  CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.branches[4]++;}
            }
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((updated) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.branches[5]++;
                //System.err.print(" creating new outlier list ");
                updated = this.outlierLists.add(new OutlierList(outlier));
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[13]++;

            } else {
  CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.branches[6]++;}
            return updated;
        }

    }

    /**
     * Returns an iterator for the outlier lists.
     * 
     * @return An iterator.
     */
    public Iterator iterator() {
        return this.outlierLists.iterator();    
    }
    
    
    /** 
     * Updates the outlier list by adding the outlier to the end of the list and
     * setting the averaged outlier to the average x and y coordinnate values 
     * of the outliers in the list.
     *
     * @param list  the outlier list to be updated.
     * @param outlier  the outlier to be added
     *
     * @return <tt>true</tt> (as per the general contract of Collection.add).
     */
    private boolean updateOutlierList(OutlierList list, Outlier outlier) {
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[14]++;
        boolean result = false;
        result = list.add(outlier);
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[15]++;
        list.updateAveragedOutlier();
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[16]++;
        list.setMultiple(true);
CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl.statements[17]++;
        return result;
    }

}

class CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.OutlierListCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$pq2p5ypyzdp9r8a89svkbhesgs01awrod3fy01nl () {
    super("org.jfree.chart.renderer.OutlierListCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.OutlierListCollection.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

