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
package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

/**
 * Wraps another field such that a certain value is skipped.
 * <p>
 * This is most useful for years where you want to skip zero, so the
 * sequence runs ...,2,1,-1,-2,...
 * <p>
 * SkipDateTimeField is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class SkipDateTimeField extends DelegatedDateTimeField {
  static {
    CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.ping();
  }


    /** Serialization version. */
    private static final long serialVersionUID = -8869148464118507846L;
  static {
    CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[1]++;
  }

    /** The chronology to wrap. */
    private final Chronology iChronology;
    /** The value to skip. */
    private final int iSkip;
    /** The calculated minimum value. */
    private transient int iMinValue;

    /**
     * Constructor that skips zero.
     * 
     * @param chronology  the chronoogy to use
     * @param field  the field to skip zero on
     */
    public SkipDateTimeField(Chronology chronology, DateTimeField field) {
        this(chronology, field, 0);
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[2]++;
    }

    /**
     * Constructor.
     * 
     * @param chronology  the chronoogy to use
     * @param field  the field to skip zero on
     * @param skip  the value to skip
     */
    public SkipDateTimeField(Chronology chronology, DateTimeField field, int skip) {
        super(field);
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[3]++;
        iChronology = chronology;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[4]++;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[5]++;
        int min = super.getMinimumValue();
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((min < skip) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[1]++;
            iMinValue = min - 1;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[7]++;

        } else {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[2]++;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[8]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((min == skip) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[3]++;
            iMinValue = skip + 1;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[9]++;

        } else {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[4]++;
            iMinValue = min;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[10]++;
        }
}
        iSkip = skip;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[11]++;
    }

    //-----------------------------------------------------------------------
    public int get(long millis) {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[12]++;
        int value = super.get(millis);
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value <= iSkip) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[5]++;
            value--;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[14]++;

        } else {
  CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[6]++;}
        return value;
    }

    public long set(long millis, int value) {
        FieldUtils.verifyValueBounds(this, value, iMinValue, getMaximumValue());
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[15]++;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value <= iSkip) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[7]++;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value == iSkip) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[9]++;
                throw new IllegalFieldValueException
                    (DateTimeFieldType.year(), Integer.valueOf(value), null, null);

            } else {
  CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[10]++;}
            value++;
CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.statements[18]++;

        } else {
  CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx.branches[8]++;}
        return super.set(millis, value);
    }

    public int getMinimumValue() {
        return iMinValue;
    }

    private Object readResolve() {
        return getType().getField(iChronology);
    }

}

class CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.joda.time.field.SkipDateTimeField.java";
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

  public CodeCoverCoverageCounter$dor0vshags6izfrxsj93um04uukw5a9sgx () {
    super("org.joda.time.field.SkipDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.joda.time.field.SkipDateTimeField.java");
      for (int i = 1; i <= 18; i++) {
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

