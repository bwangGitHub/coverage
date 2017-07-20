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

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

/**
 * Counterpart remainder datetime field to {@link DividedDateTimeField}. The
 * field's unit duration is unchanged, but the range duration is scaled
 * accordingly.
 * <p>
 * RemainderDateTimeField is thread-safe and immutable.
 *
 * @see DividedDateTimeField
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public class RemainderDateTimeField extends DecoratedDateTimeField {
  static {
    CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.ping();
  }


    private static final long serialVersionUID = 5708241235177666790L;
  static {
    CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[1]++;
  }

    // Shared with DividedDateTimeField.
    final int iDivisor;
    final DurationField iRangeField;

    /**
     * Constructor.
     * 
     * @param field  the field to wrap, like "year()".
     * @param type  the field type this field actually uses
     * @param divisor  divisor, such as 100 years in a century
     * @throws IllegalArgumentException if divisor is less than two
     */
    public RemainderDateTimeField(DateTimeField field,
                                  DateTimeFieldType type, int divisor) {
        super(field, type);
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[2]++;
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((divisor < 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[1]++;
            throw new IllegalArgumentException("The divisor must be at least 2");

        } else {
  CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[2]++;}
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[4]++;

        DurationField rangeField = field.getDurationField();
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((rangeField == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[3]++;
            iRangeField = null;
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[6]++;

        } else {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[4]++;
            iRangeField = new ScaledDurationField(
                rangeField, type.getRangeDurationType(), divisor);
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[7]++;
        }

        iDivisor = divisor;
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[8]++;
    }

    /**
     * Construct a RemainderDateTimeField that compliments the given
     * DividedDateTimeField.
     *
     * @param dividedField  complimentary divided field, like "century()".
     */
    public RemainderDateTimeField(DividedDateTimeField dividedField) {
        this(dividedField, dividedField.getType());
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[9]++;
    }

    /**
     * Construct a RemainderDateTimeField that compliments the given
     * DividedDateTimeField.
     *
     * @param dividedField  complimentary divided field, like "century()".
     * @param type  the field type this field actually uses
     */
    public RemainderDateTimeField(DividedDateTimeField dividedField, DateTimeFieldType type) {
        super(dividedField.getWrappedField(), type);
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[10]++;
        iDivisor = dividedField.iDivisor;
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[11]++;
        iRangeField = dividedField.iDurationField;
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[12]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the remainder from the specified time instant.
     * 
     * @param instant  the time instant in millis to query.
     * @return the remainder extracted from the input.
     */
    public int get(long instant) {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[13]++;
        int value = getWrappedField().get(instant);
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[5]++;
            return value % iDivisor;

        } else {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[6]++;
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }

    /**
     * Add the specified amount to the specified time instant, wrapping around
     * within the remainder range if necessary. The amount added may be
     * negative.
     * 
     * @param instant  the time instant in millis to update.
     * @param amount  the amount to add (can be negative).
     * @return the updated time instant.
     */
    public long addWrapField(long instant, int amount) {
        return set(instant, FieldUtils.getWrappedValue(get(instant), amount, 0, iDivisor - 1));
    }

    /**
     * Set the specified amount of remainder units to the specified time instant.
     * 
     * @param instant  the time instant in millis to update.
     * @param value  value of remainder units to set.
     * @return the updated time instant.
     * @throws IllegalArgumentException if value is too large or too small.
     */
    public long set(long instant, int value) {
        FieldUtils.verifyValueBounds(this, value, 0, iDivisor - 1);
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[15]++;
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[16]++;
        int divided = getDivided(getWrappedField().get(instant));
        return getWrappedField().set(instant, divided * iDivisor + value);
    }

    /**
     * Returns a scaled version of the wrapped field's unit duration field.
     */
    public DurationField getRangeDurationField() {
        return iRangeField;
    }

    /**
     * Get the minimum value for the field, which is always zero.
     * 
     * @return the minimum value of zero.
     */
    public int getMinimumValue() {
        return 0;
    }

    /**
     * Get the maximum value for the field, which is always one less than the
     * divisor.
     * 
     * @return the maximum value
     */
    public int getMaximumValue() {
        return iDivisor - 1;
    }

    public long roundFloor(long instant) {
        return getWrappedField().roundFloor(instant);
    }

    public long roundCeiling(long instant) {
        return getWrappedField().roundCeiling(instant);
    }

    public long roundHalfFloor(long instant) {
        return getWrappedField().roundHalfFloor(instant);
    }

    public long roundHalfCeiling(long instant) {
        return getWrappedField().roundHalfCeiling(instant);
    }

    public long roundHalfEven(long instant) {
        return getWrappedField().roundHalfEven(instant);
    }

    public long remainder(long instant) {
        return getWrappedField().remainder(instant);
    }

    /**
     * Returns the divisor applied, in the field's units.
     * 
     * @return the divisor
     */
    public int getDivisor() {
        return iDivisor;
    }

    private int getDivided(int value) {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[7]++;
            return value / iDivisor;

        } else {
CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5.branches[8]++;
            return ((value + 1) / iDivisor) - 1;
        }
    }

}

class CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5 ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.joda.time.field.RemainderDateTimeField.java";
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

  public CodeCoverCoverageCounter$59ov9mtvj37874cz9ldbkjb6dzf448ncmnlnyu2zj5 () {
    super("org.joda.time.field.RemainderDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("org.joda.time.field.RemainderDateTimeField.java");
      for (int i = 1; i <= 17; i++) {
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

