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
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

/**
 * A year field suitable for many calendars.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.1, refactored from GJYearDateTimeField
 */
class BasicYearDateTimeField extends ImpreciseDateTimeField {
  static {
    CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.ping();
  }


    /** Serialization version. */
    private static final long serialVersionUID = -98628754872287L;
  static {
    CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[1]++;
  }

    /** The underlying basic chronology. */
    protected final BasicChronology iChronology;

    /**
     * Restricted constructor.
     * 
     * @param chronology  the chronology this field belogs to
     */
    BasicYearDateTimeField(BasicChronology chronology) {
        super(DateTimeFieldType.year(), chronology.getAverageMillisPerYear());
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[2]++;
        iChronology = chronology;
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[3]++;
    }

    public boolean isLenient() {
        return false;
    }

    public int get(long instant) {
        return iChronology.getYear(instant);
    }

    public long add(long instant, int years) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[1]++;
            return instant;

        } else {
  CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[2]++;}
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[5]++;
        int thisYear = get(instant);
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[6]++;
        int newYear = FieldUtils.safeAdd(thisYear, years);
        return set(instant, newYear);
    }

    public long add(long instant, long years) {
        return add(instant, FieldUtils.safeToInt(years));
    }

    public long addWrapField(long instant, int years) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[3]++;
            return instant;

        } else {
  CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[4]++;}
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[8]++;
        // Return newly calculated millis value
        int thisYear = iChronology.getYear(instant);
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[9]++;
        int wrappedYear = FieldUtils.getWrappedValue
            (thisYear, years, iChronology.getMinYear(), iChronology.getMaxYear());
        return set(instant, wrappedYear);
    }

    public long set(long instant, int year) {
        FieldUtils.verifyValueBounds
            (this, year, iChronology.getMinYear(), iChronology.getMaxYear());
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[10]++;
        return iChronology.setYear(instant, year);
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((minuendInstant < subtrahendInstant) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[5]++;
            return -iChronology.getYearDifference(subtrahendInstant, minuendInstant);

        } else {
  CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[6]++;}
        return iChronology.getYearDifference(minuendInstant, subtrahendInstant);
    }

    public DurationField getRangeDurationField() {
        return null;
    }

    public boolean isLeap(long instant) {
        return iChronology.isLeapYear(get(instant));
    }

    public int getLeapAmount(long instant) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((iChronology.isLeapYear(get(instant))) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[7]++;
            return 1;

        } else {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[8]++;
            return 0;
        }
    }

    public DurationField getLeapDurationField() {
        return iChronology.days();
    }

    public int getMinimumValue() {
        return iChronology.getMinYear();
    }

    public int getMaximumValue() {
        return iChronology.getMaxYear();
    }

    public long roundFloor(long instant) {
        return iChronology.getYearMillis(get(instant));
    }

    public long roundCeiling(long instant) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[13]++;
        int year = get(instant);
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[14]++;
        long yearStartMillis = iChronology.getYearMillis(year);
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((instant != yearStartMillis) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[9]++;
            // Bump up to start of next year.
            instant = iChronology.getYearMillis(year + 1);
CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.statements[16]++;

        } else {
  CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t.branches[10]++;}
        return instant;
    }

    public long remainder(long instant) {
        return instant - roundFloor(instant);
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.year();
    }
}

class CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicYearDateTimeField.java";
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

  public CodeCoverCoverageCounter$48tixwb2c3gk7aqw5eg91hhey4thy38tn9bfvfrd5t () {
    super("org.joda.time.chrono.BasicYearDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
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
    log.startNamedSection("org.joda.time.chrono.BasicYearDateTimeField.java");
      for (int i = 1; i <= 16; i++) {
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

