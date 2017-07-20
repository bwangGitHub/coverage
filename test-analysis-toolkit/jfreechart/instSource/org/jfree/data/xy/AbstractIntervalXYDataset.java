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
 * ------------------------------
 * AbstractIntervalXYDataset.java
 * ------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited).
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-May-2004 : Version 1 (DG);
 * 15-Jul-2004 : Switched getStartX() and getStartXValue() methods and 
 *               others (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 
 */

package org.jfree.data.xy;


/**
 * An base class that you can use to create new implementations of the 
 * {@link IntervalXYDataset} interface.
 */
public abstract class AbstractIntervalXYDataset extends AbstractXYDataset 
                                                implements IntervalXYDataset {
  static {
    CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.ping();
  }


    /**
     * Returns the start x-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getStartXValue(int series, int item) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[1]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[2]++;
        Number x = getStartX(series, item);
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[1]++;
            result = x.doubleValue();
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[4]++;
   
        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[2]++;}
        return result;   
    }

    /**
     * Returns the end x-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getEndXValue(int series, int item) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[5]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[6]++;
        Number x = getEndX(series, item);
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[3]++;
            result = x.doubleValue();
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[8]++;
   
        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[4]++;}
        return result;   
    }

    /**
     * Returns the start y-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getStartYValue(int series, int item) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[9]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[10]++;
        Number y = getStartY(series, item);
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((y != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[5]++;
            result = y.doubleValue();
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[12]++;
   
        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[6]++;}
        return result;   
    }

    /**
     * Returns the end y-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The value.
     */
    public double getEndYValue(int series, int item) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[13]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[14]++;
        Number y = getEndY(series, item);
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((y != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[7]++;
            result = y.doubleValue();
CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.statements[16]++;
   
        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t.branches[8]++;}
        return result;   
    }

}

class CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.AbstractIntervalXYDataset.java";
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

  public CodeCoverCoverageCounter$15rklg6ahh1zpgkli3xxo0c0u1xgq0gncfrhcbwtmnw5l5t () {
    super("org.jfree.data.xy.AbstractIntervalXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
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
    log.startNamedSection("org.jfree.data.xy.AbstractIntervalXYDataset.java");
      for (int i = 1; i <= 16; i++) {
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

