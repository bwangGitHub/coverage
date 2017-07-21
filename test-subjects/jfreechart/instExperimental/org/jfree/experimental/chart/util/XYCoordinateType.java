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
 * ---------------------
 * XYCoordinateType.java
 * ---------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 02-Feb-2007 : Version 1 (DG);
 * 
 */

package org.jfree.experimental.chart.util;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Represents several possible interpretations for an (x, y) coordinate.
 */
public final class XYCoordinateType implements Serializable {
  static {
    CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.ping();
  }

    
    /** The (x, y) coordinates represent a point in the data space. */
    public static final XYCoordinateType DATA 
            = new XYCoordinateType("XYCoordinateType.DATA");
  static {
    CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[1]++;
  }

    /** 
     * The (x, y) coordinates represent a relative position in the data space. 
     * In this case, the values should be in the range (0.0 to 1.0).
     */
    public static final XYCoordinateType RELATIVE 
            = new XYCoordinateType("XYCoordinateType.RELATIVE");
  static {
    CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[2]++;
  }

    /** 
     * The (x, y) coordinates represent indices in a dataset. 
     * In this case, the values should be in the range (0.0 to 1.0).
     */
    public static final XYCoordinateType INDEX 
            = new XYCoordinateType("XYCoordinateType.INDEX");
  static {
    CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[3]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private XYCoordinateType(String name) {
        this.name = name;
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[4]++;
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
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[2]++;}
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof XYCoordinateType) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[4]++;}
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[7]++;
        XYCoordinateType order = (XYCoordinateType) obj;
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(order.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[6]++;}
        return true;
    }
   
    /**
     * Ensures that serialization returns the unique instances.
     *
     * @return The object.
     *
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(XYCoordinateType.DATA)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[7]++;
            return XYCoordinateType.DATA;

        }
        else {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[8]++;
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[10]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(XYCoordinateType.RELATIVE)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[9]++;
            return XYCoordinateType.RELATIVE;

        }     
        else {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[10]++;
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.statements[11]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(XYCoordinateType.INDEX)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[11]++;
            return XYCoordinateType.INDEX;

        } else {
  CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x.branches[12]++;}
}
}     
        return null;
    }

}

class CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.experimental.chart.util.XYCoordinateType.java";
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

  public CodeCoverCoverageCounter$21dy5gqxz3cu6ptqn5jsozigv1go0rv4x () {
    super("org.jfree.experimental.chart.util.XYCoordinateType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
    log.startNamedSection("org.jfree.experimental.chart.util.XYCoordinateType.java");
      for (int i = 1; i <= 11; i++) {
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

