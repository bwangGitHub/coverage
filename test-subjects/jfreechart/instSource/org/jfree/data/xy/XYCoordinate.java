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
 * XYCoordinate.java
 * -----------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;

/**
 * Represents an (x, y) coordinate.
 * 
 * @since 1.0.6
 */
public class XYCoordinate implements Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.ping();
  }


    /** The x-coordinate. */
    private double x;
    
    /** The y-coordinate. */
    private double y;
    
    /**
     * Creates a new coordinate for the point (0.0, 0.0).
     */
    public XYCoordinate() {
        this(0.0, 0.0);
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[1]++;
    }
    
    /**
     * Creates a new coordinate for the point (x, y).
     * 
     * @param x  the x-coordinate.
     * @param y  the y-coordinate.
     */
    public XYCoordinate(double x, double y) {
        this.x = x;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[2]++;
        this.y = y;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[3]++;
    }
    
    /**
     * Returns the x-coordinate.
     * 
     * @return The x-coordinate.
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Returns the y-coordinate.
     * 
     * @return The y-coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Tests this coordinate for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[2]++;}
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof XYCoordinate) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[4]++;}
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[6]++;
        XYCoordinate that = (XYCoordinate) obj;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.x != that.x) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[6]++;}
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.y != that.y) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[8]++;}
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[9]++;
        int result = 193;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[10]++;
        long temp = Double.doubleToLongBits(this.x);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[11]++;
        temp = Double.doubleToLongBits(this.y);
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[12]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[13]++;
        return result;
    }

    /**
     * Returns a string representation of this instance, primarily for 
     * debugging purposes.
     * 
     * @return A string.
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Compares this instance against an arbitrary object. 
     * 
     * @param obj  the object (<code>null</code> not permitted).
     * 
     * @return An integer indicating the relative order of the items.
     */
    public int compareTo(Object obj) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof XYCoordinate) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[9]++;
            throw new IllegalArgumentException("Incomparable object.");

        } else {
  CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[10]++;}
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[15]++;
        XYCoordinate that = (XYCoordinate) obj;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.x > that.x) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[11]++;
            return 1;

        }
        else {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[12]++;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[17]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.x < that.x) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[13]++;
            return -1;

        }
        else {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[14]++;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[18]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.y > that.y) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[15]++;
                return 1;

            }
            else {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[16]++;
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.statements[19]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.y < that.y) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[17]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75.branches[18]++;}
}
        }
}
        return 0;
    }
    
}

class CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75 ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.XYCoordinate.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$116zvrl5gpj0coymv8d4qevsm75 () {
    super("org.jfree.data.xy.XYCoordinate.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.XYCoordinate.java");
      for (int i = 1; i <= 19; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

