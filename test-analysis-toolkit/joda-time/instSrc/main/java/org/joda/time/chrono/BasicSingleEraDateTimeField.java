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

import java.util.Locale;

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDurationField;

/**
 * Provides time calculations for the coptic era component of time.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.2, refactored from CopticEraDateTimeField
 */
final class BasicSingleEraDateTimeField extends BaseDateTimeField {
  static {
    CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.ping();
  }


    /**
     * Value of the era, which will be the same as DateTimeConstants.CE.
     */
    private static final int ERA_VALUE = DateTimeConstants.CE;
  static {
    CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.statements[1]++;
  }
    /**
     * Text value of the era.
     */
    private final String iEraText;

    /**
     * Restricted constructor.
     */
    BasicSingleEraDateTimeField(String text) {
        super(DateTimeFieldType.era());
CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.statements[2]++;
        iEraText = text;
CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.statements[3]++;
    }

    /** @inheritDoc */
    public boolean isLenient() {
        return false;
    }

    /** @inheritDoc */
    public int get(long instant) {
        return ERA_VALUE;
    }

    /** @inheritDoc */
    public long set(long instant, int era) {
        FieldUtils.verifyValueBounds(this, era, ERA_VALUE, ERA_VALUE);
CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.statements[4]++;
        return instant;
    }

    /** @inheritDoc */
    public long set(long instant, String text, Locale locale) {
CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((iEraText.equals(text) == false) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (("1".equals(text) == false) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.branches[1]++;
            throw new IllegalFieldValueException(DateTimeFieldType.era(), text);

        } else {
  CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt.branches[2]++;}
        return instant;
    }

    /** @inheritDoc */
    public long roundFloor(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public long roundCeiling(long instant) {
        return Long.MAX_VALUE;
    }

    /** @inheritDoc */
    public long roundHalfFloor(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public long roundHalfCeiling(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public long roundHalfEven(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public DurationField getDurationField() {
        return UnsupportedDurationField.getInstance(DurationFieldType.eras());
    }

    /** @inheritDoc */
    public DurationField getRangeDurationField() {
        return null;
    }

    /** @inheritDoc */
    public int getMinimumValue() {
        return ERA_VALUE;
    }

    /** @inheritDoc */
    public int getMaximumValue() {
        return ERA_VALUE;
    }

    /** @inheritDoc */
    public String getAsText(int fieldValue, Locale locale) {
        return iEraText;
    }

    /** @inheritDoc */
    public int getMaximumTextLength(Locale locale) {
        return iEraText.length();
    }

}

class CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicSingleEraDateTimeField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$1nk6sjvleejsoiebsu952l03ktaowqck569oxx3na6463oz7tt () {
    super("org.joda.time.chrono.BasicSingleEraDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BasicSingleEraDateTimeField.java");
      for (int i = 1; i <= 5; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

