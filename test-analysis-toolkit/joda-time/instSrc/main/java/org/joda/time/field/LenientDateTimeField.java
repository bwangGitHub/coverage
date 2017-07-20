/*
 *  Copyright 2001-2007 Stephen Colebourne
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
package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;

/**
 * Converts a strict DateTimeField into a lenient one. By being lenient, the
 * set method accepts out of bounds values, performing an addition instead.
 * <p>
 * LenientDateTimeField is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @see org.joda.time.chrono.LenientChronology
 * @see StrictDateTimeField
 * @since 1.0
 */
public class LenientDateTimeField extends DelegatedDateTimeField {
  static {
    CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.ping();
  }


    private static final long serialVersionUID = 8714085824173290599L;
  static {
    CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[1]++;
  }

    private final Chronology iBase;

    /**
     * Returns a lenient version of the given field. If it is already lenient,
     * then it is returned as-is. Otherwise, a new LenientDateTimeField is
     * returned.
     */
    public static DateTimeField getInstance(DateTimeField field, Chronology base) {
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.branches[2]++;}
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((field instanceof StrictDateTimeField) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.branches[3]++;
            field = ((StrictDateTimeField)field).getWrappedField();
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[4]++;

        } else {
  CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.branches[4]++;}
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((field.isLenient()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.branches[5]++;
            return field;

        } else {
  CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.branches[6]++;}
        return new LenientDateTimeField(field, base);
    }

    protected LenientDateTimeField(DateTimeField field, Chronology base) {
        super(field);
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[6]++;
        iBase = base;
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[7]++;
    }

    public final boolean isLenient() {
        return true;
    }

    /**
     * Set values which may be out of bounds by adding the difference between
     * the new value and the current value.
     */
    public long set(long instant, int value) {
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[8]++;
        // lenient needs to handle time zone chronologies
        // so we do the calculation using local milliseconds
        long localInstant = iBase.getZone().convertUTCToLocal(instant);
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[9]++;
        long difference = FieldUtils.safeSubtract(value, get(instant));
        localInstant = getType().getField(iBase.withUTC()).add(localInstant, difference);
CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9.statements[10]++;
        return iBase.getZone().convertLocalToUTC(localInstant, false, instant);
    }
}

class CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.field.LenientDateTimeField.java";
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

  public CodeCoverCoverageCounter$3h7jynfrhf8xu7vx7hg11y9zvtpzo72k2xsg9y9 () {
    super("org.joda.time.field.LenientDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.joda.time.field.LenientDateTimeField.java");
      for (int i = 1; i <= 10; i++) {
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

