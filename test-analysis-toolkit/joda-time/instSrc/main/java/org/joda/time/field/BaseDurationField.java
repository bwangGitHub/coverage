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
 * BaseDurationField provides the common behaviour for DurationField
 * implementations.
 * <p>
 * This class should generally not be used directly by API users. The
 * DurationField class should be used when different kinds of DurationField
 * objects are to be referenced.
 * <p>
 * BaseDurationField is thread-safe and immutable, and its subclasses must
 * be as well.
 *
 * @author Brian S O'Neill
 * @see DecoratedDurationField
 * @since 1.0
 */
public abstract class BaseDurationField extends DurationField implements Serializable {
  static {
    CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.ping();
  }


    /** Serialization lock. */
    private static final long serialVersionUID = -2554245107589433218L;
  static {
    CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[1]++;
  }

    /** A desriptive name for the field. */
    private final DurationFieldType iType;

    protected BaseDurationField(DurationFieldType type) {
        super();
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[2]++;
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.branches[1]++;
            throw new IllegalArgumentException("The type must not be null");

        } else {
  CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.branches[2]++;}
        iType = type;
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[4]++;
    }

    public final DurationFieldType getType() {
        return iType;
    }

    public final String getName() {
        return iType.getName();
    }

    /**
     * @return true always
     */
    public final boolean isSupported() {
        return true;
    }

    //------------------------------------------------------------------------
    /**
     * Get the value of this field from the milliseconds, which is approximate
     * if this field is imprecise.
     *
     * @param duration  the milliseconds to query, which may be negative
     * @return the value of the field, in the units of the field, which may be
     * negative
     */
    public int getValue(long duration) {
        return FieldUtils.safeToInt(getValueAsLong(duration));
    }

    /**
     * Get the value of this field from the milliseconds, which is approximate
     * if this field is imprecise.
     *
     * @param duration  the milliseconds to query, which may be negative
     * @return the value of the field, in the units of the field, which may be
     * negative
     */
    public long getValueAsLong(long duration) {
        return duration / getUnitMillis();
    }

    /**
     * Get the value of this field from the milliseconds relative to an
     * instant.
     *
     * <p>If the milliseconds is positive, then the instant is treated as a
     * "start instant". If negative, the instant is treated as an "end
     * instant".
     *
     * <p>The default implementation returns
     * <code>Utils.safeToInt(getAsLong(millisDuration, instant))</code>.
     * 
     * @param duration  the milliseconds to query, which may be negative
     * @param instant  the start instant to calculate relative to
     * @return the value of the field, in the units of the field, which may be
     * negative
     */
    public int getValue(long duration, long instant) {
        return FieldUtils.safeToInt(getValueAsLong(duration, instant));
    }

    /**
     * Get the millisecond duration of this field from its value, which is
     * approximate if this field is imprecise.
     * 
     * @param value  the value of the field, which may be negative
     * @return the milliseconds that the field represents, which may be
     * negative
     */
    public long getMillis(int value) {
        return value * getUnitMillis();  // safe
    }

    /**
     * Get the millisecond duration of this field from its value, which is
     * approximate if this field is imprecise.
     * 
     * @param value  the value of the field, which may be negative
     * @return the milliseconds that the field represents, which may be
     * negative
     */
    public long getMillis(long value) {
        return FieldUtils.safeMultiply(value, getUnitMillis());
    }

    // Calculation API
    //------------------------------------------------------------------------
    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return FieldUtils.safeToInt(getDifferenceAsLong(minuendInstant, subtrahendInstant));
    }

    //------------------------------------------------------------------------
    public int compareTo(DurationField otherField) {
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[5]++;
        long otherMillis = otherField.getUnitMillis();
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[6]++;
        long thisMillis = getUnitMillis();
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        // cannot do (thisMillis - otherMillis) as can overflow
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thisMillis == otherMillis) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.branches[3]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.branches[4]++;}
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((thisMillis < otherMillis) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.branches[5]++;
            return -1;

        } else {
CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35.branches[6]++;
            return 1;
        }
    }

    /**
     * Get a suitable debug string.
     * 
     * @return debug string
     */
    public String toString() {
        return "DurationField[" + getName() + ']';
    }

}

class CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35 ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.field.BaseDurationField.java";
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

  public CodeCoverCoverageCounter$aw3qat10gmucpiw6sxmnnpgijhi4z54f35 () {
    super("org.joda.time.field.BaseDurationField.java");
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
    log.startNamedSection("org.joda.time.field.BaseDurationField.java");
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

