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
 * LengthConstraintType.java
 * -------------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 08-Feb-2004 : Version 1 (DG);
 * 
 */

package org.jfree.chart.block;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Defines tokens used to indicate a length constraint type.
 */
public final class LengthConstraintType implements Serializable {
  static {
    CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1156658804028142978L;
  static {
    CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[1]++;
  }
    
    /** NONE. */
    public static final LengthConstraintType NONE 
        = new LengthConstraintType("LengthConstraintType.NONE");
  static {
    CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[2]++;
  }

    /** Range. */
    public static final LengthConstraintType RANGE 
        = new LengthConstraintType("RectangleConstraintType.RANGE");
  static {
    CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[3]++;
  }

    /** FIXED. */
    public static final LengthConstraintType FIXED 
        = new LengthConstraintType("LengthConstraintType.FIXED");
  static {
    CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[4]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private LengthConstraintType(String name) {
        this.name = name;
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[5]++;
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
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[2]++;}
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof LengthConstraintType) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[4]++;}
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[8]++;
        LengthConstraintType that = (LengthConstraintType) obj;
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(that.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[6]++;}
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
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(LengthConstraintType.NONE)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[7]++;
            return LengthConstraintType.NONE;

        }
        else {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[8]++;
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[11]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(LengthConstraintType.RANGE)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[9]++;
            return LengthConstraintType.RANGE;

        }
        else {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[10]++;
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.statements[12]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(LengthConstraintType.FIXED)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[11]++;
            return LengthConstraintType.FIXED;

        } else {
  CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl.branches[12]++;}
}
}
        return null;
    }
    
}

class CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.LengthConstraintType.java";
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

  public CodeCoverCoverageCounter$3h7jyn4aml0a6l0mzbpkvtbldgw0xwolxtnflnl () {
    super("org.jfree.chart.block.LengthConstraintType.java");
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
    log.startNamedSection("org.jfree.chart.block.LengthConstraintType.java");
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

