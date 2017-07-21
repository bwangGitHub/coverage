/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

/**
 * Provides time calculations for the day of the month component of time.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.1, refactored from GJDayOfMonthDateTimeField
 */
final class BasicDayOfMonthDateTimeField extends PreciseDurationDateTimeField {
  static {
    CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.ping();
  }


    private static final long serialVersionUID = -4677223814028011723L;
  static {
    CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[1]++;
  }

    private final BasicChronology iChronology;

    /**
     * Restricted constructor.
     */
    BasicDayOfMonthDateTimeField(BasicChronology chronology, DurationField days) {
        super(DateTimeFieldType.dayOfMonth(), days);
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[2]++;
        iChronology = chronology;
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[3]++;
    }

    //-----------------------------------------------------------------------
    public int get(long instant) {
        return iChronology.getDayOfMonth(instant);
    }

    public DurationField getRangeDurationField() {
        return iChronology.months();
    }

    public int getMinimumValue() {
        return 1;
    }

    public int getMaximumValue() {
        return iChronology.getDaysInMonthMax();
    }

    public int getMaximumValue(long instant) {
        return iChronology.getDaysInMonthMax(instant);
    }

    public int getMaximumValue(ReadablePartial partial) {
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((partial.isSupported(DateTimeFieldType.monthOfYear())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[1]++;
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[5]++;
            int month = partial.get(DateTimeFieldType.monthOfYear());
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((partial.isSupported(DateTimeFieldType.year())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[3]++;
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[7]++;
                int year = partial.get(DateTimeFieldType.year());
                return iChronology.getDaysInYearMonth(year, month);

            } else {
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[4]++;}
            return iChronology.getDaysInMonthMax(month);

        } else {
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[2]++;}
        return getMaximumValue();
    }

    public int getMaximumValue(ReadablePartial partial, int[] values) {
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[8]++;
        int size = partial.size();
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[1]--;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[2]--;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[3]++;
}
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((partial.getFieldType(i) == DateTimeFieldType.monthOfYear()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[5]++;
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[11]++;
                int month = values[i];
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[12]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((j < size) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[4]--;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[5]--;
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.loops[6]++;
}
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((partial.getFieldType(j) == DateTimeFieldType.year()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[7]++;
CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.statements[14]++;
                        int year = values[j];
                        return iChronology.getDaysInYearMonth(year, month);

                    } else {
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[8]++;}
                }
                return iChronology.getDaysInMonthMax(month);

            } else {
  CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1.branches[6]++;}
        }
        return getMaximumValue();
    }

    protected int getMaximumValueForSet(long instant, int value) {
        return iChronology.getDaysInMonthMaxForSet(instant, value);
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.dayOfMonth();
    }
}

class CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1 ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicDayOfMonthDateTimeField.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$brjkb1cnzle38v4w90hy7jqjpsbm2ysqtejr5pgw96ztjd8rmg1 () {
    super("org.joda.time.chrono.BasicDayOfMonthDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BasicDayOfMonthDateTimeField.java");
      for (int i = 1; i <= 14; i++) {
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
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

