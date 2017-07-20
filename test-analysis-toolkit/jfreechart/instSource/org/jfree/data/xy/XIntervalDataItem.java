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
 * ----------------------
 * XIntervalDataItem.java
 * ----------------------
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

import org.jfree.data.ComparableObjectItem;

/**
 * An item representing data in the form (x, x-low, x-high, y).
 *
 * @since 1.0.3
 */
public class XIntervalDataItem extends ComparableObjectItem {
  static {
    CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.ping();
  }


    /** 
     * Creates a new instance of <code>XIntervalDataItem</code>.
     *
     * @param x  the x-value.
     * @param xLow  the lower bound of the x-interval.
     * @param xHigh  the upper bound of the x-interval.
     * @param y  the y-value.
     */
    public XIntervalDataItem(double x, double xLow, double xHigh, double y) {
        super(new Double(x), new YWithXInterval(y, xLow, xHigh));
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.statements[1]++;
    }
    
    /**
     * Returns the x-value.
     *
     * @return The x-value (never <code>null</code>).
     */
    public Number getX() {
        return (Number) getComparable();
    }
    
    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.statements[2]++;
        YWithXInterval interval = (YWithXInterval) getObject();
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((interval != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.branches[1]++;
            return interval.getY();

        }
        else {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.branches[2]++;
            return Double.NaN;
        }
    }
    
    /**
     * Returns the lower bound of the x-interval.
     *
     * @return The lower bound of the x-interval.
     */
    public double getXLowValue() {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.statements[4]++;
        YWithXInterval interval = (YWithXInterval) getObject();
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((interval != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.branches[3]++;
            return interval.getXLow();

        }
        else {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.branches[4]++;
            return Double.NaN;
        }
    }
    
    /**
     * Returns the upper bound of the x-interval.
     *
     * @return The upper bound of the x-interval.
     */
    public double getXHighValue() {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.statements[6]++;
        YWithXInterval interval = (YWithXInterval) getObject();
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((interval != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.branches[5]++;
            return interval.getXHigh();

        }
        else {
CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x.branches[6]++;
            return Double.NaN;
        }
    }
    
}

class CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.XIntervalDataItem.java";
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

  public CodeCoverCoverageCounter$ehi1f9hcj47gzxjmktupzq0w7juwz50k0x () {
    super("org.jfree.data.xy.XIntervalDataItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
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
    log.startNamedSection("org.jfree.data.xy.XIntervalDataItem.java");
      for (int i = 1; i <= 7; i++) {
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

