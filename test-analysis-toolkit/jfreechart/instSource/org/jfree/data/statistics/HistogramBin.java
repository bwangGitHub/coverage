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
 * HistogramBin.java
 * -----------------
 * (C) Copyright 2003-2007, by Jelai Wang and Contributors.
 *
 * Original Author:  Jelai Wang (jelaiw AT mindspring.com);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 06-Jul-2003 : Version 1, contributed by Jelai Wang (DG);
 * 07-Jul-2003 : Changed package and added Javadocs (DG);
 * 01-Mar-2004 : Moved from org.jfree.data --> org.jfree.data.statistics (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 
 */

package org.jfree.data.statistics;

import java.io.Serializable;

/**
 * A bin for the {@link HistogramDataset} class.
 */
public class HistogramBin implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 7614685080015589931L;
  static {
    CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[1]++;
  }
    
    /** The number of items in the bin. */
    private int count;
    
    /** The start boundary. */
    private double startBoundary;
    
    /** The end boundary. */
    private double endBoundary;

    /**
     * Creates a new bin.
     * 
     * @param startBoundary  the start boundary.
     * @param endBoundary  the end boundary.
     */
    public HistogramBin(double startBoundary, double endBoundary) {
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((startBoundary > endBoundary) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[1]++;
            throw new IllegalArgumentException(
                    "HistogramBin():  startBoundary > endBoundary.");

        } else {
  CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[2]++;}
        this.count = 0;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[3]++;
        this.startBoundary = startBoundary;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[4]++;
        this.endBoundary = endBoundary;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[5]++;
    }

    /**
     * Returns the number of items in the bin.
     * 
     * @return The item count.
     */
    public int getCount() {
        return this.count;
    }
    
    /**
     * Increments the item count.
     */
    public void incrementCount() {
        this.count++;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[6]++;
    }
    
    /**
     * Returns the start boundary.
     * 
     * @return The start boundary.
     */
    public double getStartBoundary() {
        return this.startBoundary;
    }
    
    /**
     * Returns the end boundary.
     * 
     * @return The end boundary.
     */
    public double getEndBoundary() {
        return this.endBoundary;
    }
    
    /**
     * Returns the bin width.
     * 
     * @return The bin width.
     */
    public double getBinWidth() {
        return this.endBoundary - this.startBoundary;
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object to test against.
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[3]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[4]++;}
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[5]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[6]++;}
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof HistogramBin) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[7]++;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[10]++;
            HistogramBin bin = (HistogramBin) obj;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[11]++;
            boolean b0 = bin.startBoundary == this.startBoundary;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[12]++;
            boolean b1 = bin.endBoundary == this.endBoundary;
CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.statements[13]++;
            boolean b2 = bin.count == this.count;
            return b0 && b1 && b2;

        } else {
  CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp.branches[8]++;}
        return false;
    }
    
    /**
     * Returns a clone of the bin.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not thrown by this class.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }
    
}

class CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.HistogramBin.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$uhgmpdny1so6h5dthkhreosqqp () {
    super("org.jfree.data.statistics.HistogramBin.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.HistogramBin.java");
      for (int i = 1; i <= 13; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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

