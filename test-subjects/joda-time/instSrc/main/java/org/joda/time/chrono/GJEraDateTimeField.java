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
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDurationField;

/**
 * Provides time calculations for the era component of time.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
final class GJEraDateTimeField extends BaseDateTimeField {
  static {
    CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.ping();
  }

    
    /** Serialization version */
    private static final long serialVersionUID = 4240986525305515528L;
  static {
    CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[1]++;
  }

    private final BasicChronology iChronology;

    /**
     * Restricted constructor
     */
    GJEraDateTimeField(BasicChronology chronology) {
        super(DateTimeFieldType.era());
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[2]++;
        iChronology = chronology;
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[3]++;
    }

    public boolean isLenient() {
        return false;
    }

    /**
     * Get the Era component of the specified time instant.
     * 
     * @param instant  the time instant in millis to query.
     */
    public int get(long instant) {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((iChronology.getYear(instant) <= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[1]++;
            return DateTimeConstants.BCE;

        } else {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[2]++;
            return DateTimeConstants.CE;
        }
    }

    public String getAsText(int fieldValue, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).eraValueToText(fieldValue);
    }

    /**
     * Set the Era component of the specified time instant.
     * 
     * @param instant  the time instant in millis to update.
     * @param era  the era to update the time to.
     * @return the updated time instant.
     * @throws IllegalArgumentException  if era is invalid.
     */
    public long set(long instant, int era) {
        FieldUtils.verifyValueBounds(this, era, DateTimeConstants.BCE, DateTimeConstants.CE);
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[5]++;
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[6]++;
            
        int oldEra = get(instant);
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((oldEra != era) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[3]++;
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[8]++;
            int year = iChronology.getYear(instant);
            return iChronology.setYear(instant, -year);

        } else {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[4]++;
            return instant;
        }
    }

    public long set(long instant, String text, Locale locale) {
        return set(instant, GJLocaleSymbols.forLocale(locale).eraTextToValue(text));
    }

    public long roundFloor(long instant) {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((get(instant) == DateTimeConstants.CE) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[5]++;
            return iChronology.setYear(0, 1);

        } else {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[6]++;
            return Long.MIN_VALUE;
        }
    }

    public long roundCeiling(long instant) {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((get(instant) == DateTimeConstants.BCE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[7]++;
            return iChronology.setYear(0, 1);

        } else {
CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9.branches[8]++;
            return Long.MAX_VALUE;
        }
    }

    public long roundHalfFloor(long instant) {
        // In reality, the era is infinite, so there is no halfway point.
        return roundFloor(instant);
    }

    public long roundHalfCeiling(long instant) {
        // In reality, the era is infinite, so there is no halfway point.
        return roundFloor(instant);
    }

    public long roundHalfEven(long instant) {
        // In reality, the era is infinite, so there is no halfway point.
        return roundFloor(instant);
    }

    public DurationField getDurationField() {
        return UnsupportedDurationField.getInstance(DurationFieldType.eras());
    }

    public DurationField getRangeDurationField() {
        return null;
    }

    public int getMinimumValue() {
        return DateTimeConstants.BCE;
    }

    public int getMaximumValue() {
        return DateTimeConstants.CE;
    }

    public int getMaximumTextLength(Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getEraMaxTextLength();
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.era();
    }
}

class CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.GJEraDateTimeField.java";
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

  public CodeCoverCoverageCounter$2b6ifnyzfdxa9a3b7vib2pyhishjzjb71ie9 () {
    super("org.joda.time.chrono.GJEraDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.joda.time.chrono.GJEraDateTimeField.java");
      for (int i = 1; i <= 10; i++) {
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

