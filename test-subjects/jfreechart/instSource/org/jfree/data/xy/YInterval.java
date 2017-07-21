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
 * --------------
 * YInterval.java
 * --------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;

/**
 * A y-interval.  This class is used internally by the 
 * {@link YIntervalDataItem} class.
 *
 * @since 1.0.3
 */
public class YInterval implements Serializable {
  static {
    CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.ping();
  }

    
    /** The y-value. */
    private double y;
    
    /** The lower bound of the y-interval. */
    private double yLow;
    
    /** The upper bound of the y-interval. */
    private double yHigh;
    
    /** 
     * Creates a new instance of <code>YInterval</code>.
     *
     * @param y  the y-value.
     * @param yLow  the lower bound of the y-interval.
     * @param yHigh  the upper bound of the y-interval.  
     */
    public YInterval(double y, double yLow, double yHigh) {
        this.y = y;
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[1]++;
        this.yLow = yLow;
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[2]++;
        this.yHigh = yHigh;
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[3]++;
    }
    
    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Returns the lower bound of the y-interval.
     *
     * @return The lower bound of the y-interval.
     */
    public double getYLow() {
        return this.yLow;
    }
    
    /**
     * Returns the upper bound of the y-interval.
     *
     * @return The upper bound of the y-interval.
     */
    public double getYHigh() {
        return this.yHigh;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[2]++;}
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof YInterval) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[4]++;}
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[6]++;
        YInterval that = (YInterval) obj;
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.y != that.y) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[6]++;}
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.yLow != that.yLow) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[8]++;}
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.statements[9]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.yHigh != that.yHigh) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap.branches[10]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.YInterval.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$3rh2q38lkyim3kw9190uap () {
    super("org.jfree.data.xy.YInterval.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.YInterval.java");
      for (int i = 1; i <= 9; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

