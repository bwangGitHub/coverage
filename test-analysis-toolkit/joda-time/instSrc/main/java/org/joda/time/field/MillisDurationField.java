/*
 *  Copyright 2001-2009 Stephen Colebourne
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

import java.io.Serializable;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/**
 * Duration field class representing a field with a fixed unit length of one
 * millisecond.
 * <p>
 * MillisDurationField is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class MillisDurationField extends DurationField implements Serializable {
  static {
    CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.ping();
  }


    /** Serialization lock. */
    private static final long serialVersionUID = 2656707858124633367L;
  static {
    CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[1]++;
  }

    /** Singleton instance. */
    public static final DurationField INSTANCE = new MillisDurationField();
  static {
    CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[2]++;
  }

    /**
     * Restricted constructor.
     */
    private MillisDurationField() {
        super();
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[3]++;
    }
    
    //------------------------------------------------------------------------
    public DurationFieldType getType() {
        return DurationFieldType.millis();
    }

    public String getName() {
        return "millis";
    }

    /**
     * Returns true as this field is supported.
     * 
     * @return true always
     */
    public boolean isSupported() {
        return true;
    }

    /**
     * Returns true as this field is precise.
     * 
     * @return true always
     */
    public final boolean isPrecise() {
        return true;
    }

    /**
     * Returns the amount of milliseconds per unit value of this field.
     *
     * @return one always
     */
    public final long getUnitMillis() {
        return 1;
    }

    //------------------------------------------------------------------------
    public int getValue(long duration) {
        return FieldUtils.safeToInt(duration);
    }

    public long getValueAsLong(long duration) {
        return duration;
    }

    public int getValue(long duration, long instant) {
        return FieldUtils.safeToInt(duration);
    }

    public long getValueAsLong(long duration, long instant) {
        return duration;
    }

    public long getMillis(int value) {
        return value;
    }

    public long getMillis(long value) {
        return value;
    }

    public long getMillis(int value, long instant) {
        return value;
    }

    public long getMillis(long value, long instant) {
        return value;
    }

    public long add(long instant, int value) {
        return FieldUtils.safeAdd(instant, value);
    }

    public long add(long instant, long value) {
        return FieldUtils.safeAdd(instant, value);
    }

    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return FieldUtils.safeToInt(FieldUtils.safeSubtract(minuendInstant, subtrahendInstant));
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        return FieldUtils.safeSubtract(minuendInstant, subtrahendInstant);
    }

    //------------------------------------------------------------------------
    public int compareTo(DurationField otherField) {
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[4]++;
        long otherMillis = otherField.getUnitMillis();
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[5]++;
        long thisMillis = getUnitMillis();
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        // cannot do (thisMillis - otherMillis) as can overflow
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((thisMillis == otherMillis) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.branches[1]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.branches[2]++;}
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thisMillis < otherMillis) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.branches[3]++;
            return -1;

        } else {
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.branches[4]++;
            return 1;
        }
    }

    public boolean equals(Object obj) {
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof MillisDurationField) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.branches[5]++;
            return getUnitMillis() == ((MillisDurationField) obj).getUnitMillis();

        } else {
  CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld.branches[6]++;}
        return false;
    }

    public int hashCode() {
        return (int) getUnitMillis();
    }

    /**
     * Get a suitable debug string.
     * 
     * @return debug string
     */
    public String toString() {
        return "DurationField[millis]";
    }

    /**
     * Deserialize to the singleton.
     */
    private Object readResolve() {
        return INSTANCE;
    }

}

class CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.field.MillisDurationField.java";
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

  public CodeCoverCoverageCounter$huauocr81b50tz04w4db6czbjl17vha3brqld () {
    super("org.joda.time.field.MillisDurationField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("org.joda.time.field.MillisDurationField.java");
      for (int i = 1; i <= 8; i++) {
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

