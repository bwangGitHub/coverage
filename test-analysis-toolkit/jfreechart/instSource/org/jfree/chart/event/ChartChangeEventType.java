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
 * ChartChangeEventType.java
 * -------------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 18-Feb-2005 : Version 1 (DG);
 * 
 */

package org.jfree.chart.event;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Defines tokens used to indicate an event type.
 */
public final class ChartChangeEventType implements Serializable {
  static {
    CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5481917022435735602L;
  static {
    CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[1]++;
  }
    
    /** GENERAL. */
    public static final ChartChangeEventType GENERAL 
            = new ChartChangeEventType("ChartChangeEventType.GENERAL");
  static {
    CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[2]++;
  }

    /** NEW_DATASET. */
    public static final ChartChangeEventType NEW_DATASET 
            = new ChartChangeEventType("ChartChangeEventType.NEW_DATASET");
  static {
    CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[3]++;
  }

    /** DATASET_UPDATED. */
    public static final ChartChangeEventType DATASET_UPDATED
            = new ChartChangeEventType("ChartChangeEventType.DATASET_UPDATED");
  static {
    CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[4]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private ChartChangeEventType(String name) {
        this.name = name;
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[5]++;
    }

    /**
     * Returns a string representing the object.
     *
     * @return The string.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified 
     * object, and <code>false</code> otherwise.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[2]++;}
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof ChartChangeEventType) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[4]++;}
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[8]++;
        ChartChangeEventType that = (ChartChangeEventType) obj;
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(that.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[6]++;}
        return true;
    }
    
    /**
     * Returns a hash code value for the object.
     *
     * @return The hashcode
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(ChartChangeEventType.GENERAL)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[7]++;
            return ChartChangeEventType.GENERAL;

        }
        else {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[8]++;
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[11]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(ChartChangeEventType.NEW_DATASET)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[9]++;
            return ChartChangeEventType.NEW_DATASET;

        }
        else {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[10]++;
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.statements[12]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(ChartChangeEventType.DATASET_UPDATED)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[11]++;
            return ChartChangeEventType.DATASET_UPDATED;

        } else {
  CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1.branches[12]++;}
}
}
        return null;
    }
    
}

class CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1 ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.event.ChartChangeEventType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$32h7ohad3mxgdxuumg8nmpeoczrfjao7hdx7tk1 () {
    super("org.jfree.chart.event.ChartChangeEventType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.event.ChartChangeEventType.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

