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

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

/**
 * Provides time calculations for the week of a week based year component of time.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.1, refactored from GJWeekOfWeekyearDateTimeField
 */
final class BasicWeekOfWeekyearDateTimeField extends PreciseDurationDateTimeField {
  static {
    CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.ping();
  }


    private static final long serialVersionUID = -1587436826395135328L;
  static {
    CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[1]++;
  }

    private final BasicChronology iChronology;

    /**
     * Restricted constructor
     */
    BasicWeekOfWeekyearDateTimeField(BasicChronology chronology, DurationField weeks) {
        super(DateTimeFieldType.weekOfWeekyear(), weeks);
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[2]++;
        iChronology = chronology;
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[3]++;
    }

    /**
     * Get the week of a week based year component of the specified time instant.
     * 
     * @see org.joda.time.DateTimeField#get(long)
     * @param instant  the time instant in millis to query.
     * @return the week of the year extracted from the input.
     */
    public int get(long instant) {
        return iChronology.getWeekOfWeekyear(instant);
    }

    public DurationField getRangeDurationField() {
        return iChronology.weekyears();
    }

    // 1970-01-01 is day of week 4, Thursday. The rounding methods need to
    // apply a corrective alignment since weeks begin on day of week 1, Monday.

    public long roundFloor(long instant) {
        return super.roundFloor(instant + 3 * DateTimeConstants.MILLIS_PER_DAY)
            - 3 * DateTimeConstants.MILLIS_PER_DAY;
    }

    public long roundCeiling(long instant) {
        return super.roundCeiling(instant + 3 * DateTimeConstants.MILLIS_PER_DAY)
            - 3 * DateTimeConstants.MILLIS_PER_DAY;
    }

    public long remainder(long instant) {
        return super.remainder(instant + 3 * DateTimeConstants.MILLIS_PER_DAY);
    }

    public int getMinimumValue() {
        return 1;
    }

    public int getMaximumValue() {
        return 53;
    }

    public int getMaximumValue(long instant) {
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[4]++;
        int weekyear = iChronology.getWeekyear(instant);
        return iChronology.getWeeksInYear(weekyear);
    }

    public int getMaximumValue(ReadablePartial partial) {
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((partial.isSupported(DateTimeFieldType.weekyear())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.branches[1]++;
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[6]++;
            int weekyear = partial.get(DateTimeFieldType.weekyear());
            return iChronology.getWeeksInYear(weekyear);

        } else {
  CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.branches[2]++;}
        return 53;
    }

    public int getMaximumValue(ReadablePartial partial, int[] values) {
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[7]++;
        int size = partial.size();
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.loops[1]--;
  CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.loops[2]--;
  CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.loops[3]++;
}
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((partial.getFieldType(i) == DateTimeFieldType.weekyear()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.branches[3]++;
CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.statements[10]++;
                int weekyear = values[i];
                return iChronology.getWeeksInYear(weekyear);

            } else {
  CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75.branches[4]++;}
        }
        return 53;
    }

    protected int getMaximumValueForSet(long instant, int value) {
        return value > 52 ? getMaximumValue(instant) : 52;
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.weekOfWeekyear();
    }
}

class CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicWeekOfWeekyearDateTimeField.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$n7ooaquwicyg1dydie1wfhvale69g82s1hdla24dq7vxp60lwl3f2pi75 () {
    super("org.joda.time.chrono.BasicWeekOfWeekyearDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BasicWeekOfWeekyearDateTimeField.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

