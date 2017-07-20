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
 * Provides time calculations for the day of the year component of time.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.1, refactored from GJDayOfYearDateTimeField
 */
final class BasicDayOfYearDateTimeField extends PreciseDurationDateTimeField {
  static {
    CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.ping();
  }


    private static final long serialVersionUID = -6821236822336841037L;
  static {
    CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[1]++;
  }

    private final BasicChronology iChronology;

    /**
     * Restricted constructor
     */
    BasicDayOfYearDateTimeField(BasicChronology chronology, DurationField days) {
        super(DateTimeFieldType.dayOfYear(), days);
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[2]++;
        iChronology = chronology;
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[3]++;
    }

    /**
     * Get the day of the year component of the specified time instant.
     * 
     * @param instant  the time instant in millis to query.
     * @return the day of the year extracted from the input.
     */
    public int get(long instant) {
        return iChronology.getDayOfYear(instant);
    }

    public DurationField getRangeDurationField() {
        return iChronology.years();
    }

    public int getMinimumValue() {
        return 1;
    }

    public int getMaximumValue() {
        return iChronology.getDaysInYearMax();
    }

    public int getMaximumValue(long instant) {
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[4]++;
        int year = iChronology.getYear(instant);
        return iChronology.getDaysInYear(year);
    }

    public int getMaximumValue(ReadablePartial partial) {
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((partial.isSupported(DateTimeFieldType.year())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.branches[1]++;
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[6]++;
            int year = partial.get(DateTimeFieldType.year());
            return iChronology.getDaysInYear(year);

        } else {
  CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.branches[2]++;}
        return iChronology.getDaysInYearMax();
    }

    public int getMaximumValue(ReadablePartial partial, int[] values) {
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[7]++;
        int size = partial.size();
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.loops[1]--;
  CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.loops[2]--;
  CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.loops[3]++;
}
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((partial.getFieldType(i) == DateTimeFieldType.year()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.branches[3]++;
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[10]++;
                int year = values[i];
                return iChronology.getDaysInYear(year);

            } else {
  CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.branches[4]++;}
        }
        return iChronology.getDaysInYearMax();
    }

    protected int getMaximumValueForSet(long instant, int value) {
CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep.statements[11]++;
        int maxLessOne = iChronology.getDaysInYearMax() - 1;
        return (value > maxLessOne || value < 1) ? getMaximumValue(instant) : maxLessOne;
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.dayOfYear();
    }
}

class CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicDayOfYearDateTimeField.java";
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

  public CodeCoverCoverageCounter$1nk6sjvldfy1kxmml8a7ci66i0ai8ftv4awpjo4o9weal1v9ep () {
    super("org.joda.time.chrono.BasicDayOfYearDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
    log.startNamedSection("org.joda.time.chrono.BasicDayOfYearDateTimeField.java");
      for (int i = 1; i <= 11; i++) {
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

