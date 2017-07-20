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
 * LegendItemCollection.java
 * -------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Feb-2002 : Version 1 (DG);
 * 24-Sep-2002 : Added get(int) and getItemCount() methods (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 18-Apr-2005 : Added equals() method and implemented Cloneable and 
 *               Serializable (DG);
 *
 */

package org.jfree.chart;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * A collection of legend items.
 */
public class LegendItemCollection implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1365215565589815953L;
  static {
    CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[1]++;
  }
    
    /** Storage for the legend items. */
    private List items;

    /**
     * Constructs a new legend item collection, initially empty.
     */
    public LegendItemCollection() {
        this.items = new java.util.ArrayList();
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[2]++;
    }

    /**
     * Adds a legend item to the collection.
     *
     * @param item  the item to add.
     */
    public void add(LegendItem item) {
        this.items.add(item);
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[3]++;
    }

    /**
     * Adds the legend items from another collection to this collection.
     *
     * @param collection  the other collection.
     */
    public void addAll(LegendItemCollection collection) {
        this.items.addAll(collection.items);
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[4]++;
    }

    /**
     * Returns a legend item from the collection.
     *
     * @param index  the legend item index (zero-based).
     *
     * @return The legend item.
     */
    public LegendItem get(int index) {
        return (LegendItem) this.items.get(index);
    }

    /**
     * Returns the number of legend items in the collection.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.items.size();
    }

    /**
     * Returns an iterator that provides access to all the legend items.
     *
     * @return An iterator.
     */
    public Iterator iterator() {
        return this.items.iterator();
    }
    
    /**
     * Tests this collection for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.branches[1]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.branches[2]++;}
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof LegendItemCollection) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.branches[3]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.branches[4]++;}
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[7]++;
        LegendItemCollection that = (LegendItemCollection) obj;
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.items.equals(that.items)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.branches[5]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl.branches[6]++;}
        return true;
    }

    /**
     * Returns a clone of the collection.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if an item in the collection is not
     *         cloneable.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }
    
}

class CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.chart.LegendItemCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3h7jqgr6q6kn4qnix24wpgq1m47psgxit2mpbvl () {
    super("org.jfree.chart.LegendItemCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.LegendItemCollection.java");
      for (int i = 1; i <= 8; i++) {
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
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

