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
 * Divides a DateTimeField such that the retrieved values are reduced by a
 * fixed divisor. The field's unit duration is scaled accordingly, but the
 * range duration is unchanged.
 * <p>
 * DividedDateTimeField is thread-safe and immutable.
 *
 * @see RemainderDateTimeField
 * 
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public class DividedDateTimeField extends DecoratedDateTimeField {
  static {
    CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.ping();
  }


    private static final long serialVersionUID = 8318475124230605365L;
  static {
    CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[1]++;
  }

    // Shared with RemainderDateTimeField.
    final int iDivisor;
    final DurationField iDurationField;

    private final int iMin;
    private final int iMax;

    /**
     * Constructor.
     * 
     * @param field  the field to wrap, like "year()".
     * @param type  the field type this field will actually use
     * @param divisor  divisor, such as 100 years in a century
     * @throws IllegalArgumentException if divisor is less than two
     */
    public DividedDateTimeField(DateTimeField field,
                                DateTimeFieldType type, int divisor) {
        super(field, type);
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[2]++;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
                
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((divisor < 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[1]++;
            throw new IllegalArgumentException("The divisor must be at least 2");

        } else {
  CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[2]++;}
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[4]++;

        DurationField unitField = field.getDurationField();
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((unitField == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[3]++;
            iDurationField = null;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[6]++;

        } else {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[4]++;
            iDurationField = new ScaledDurationField(
                unitField, type.getDurationType(), divisor);
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[7]++;
        }

        iDivisor = divisor;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[8]++;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[9]++;

        int i = field.getMinimumValue();
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[10]++;
        int min = (i >= 0) ? i / divisor : ((i + 1) / divisor - 1);
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[11]++;

        int j = field.getMaximumValue();
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[12]++;
        int max = (j >= 0) ? j / divisor : ((j + 1) / divisor - 1);

        iMin = min;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[13]++;
        iMax = max;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[14]++;
    }

    /**
     * Construct a DividedDateTimeField that compliments the given
     * RemainderDateTimeField.
     *
     * @param remainderField  complimentary remainder field, like "yearOfCentury()".
     * @param type  the field type this field will actually use
     */
    public DividedDateTimeField(RemainderDateTimeField remainderField, DateTimeFieldType type) {
        super(remainderField.getWrappedField(), type);
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[15]++;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[16]++;
        int divisor = iDivisor = remainderField.iDivisor;
        iDurationField = remainderField.iRangeField;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[17]++;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[18]++;

        DateTimeField field = getWrappedField();
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[19]++;
        int i = field.getMinimumValue();
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[20]++;
        int min = (i >= 0) ? i / divisor : ((i + 1) / divisor - 1);
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[21]++;

        int j = field.getMaximumValue();
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[22]++;
        int max = (j >= 0) ? j / divisor : ((j + 1) / divisor - 1);

        iMin = min;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[23]++;
        iMax = max;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[24]++;
    }

    /**
     * Get the amount of scaled units from the specified time instant.
     * 
     * @param instant  the time instant in millis to query.
     * @return the amount of scaled units extracted from the input.
     */
    public int get(long instant) {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[25]++;
        int value = getWrappedField().get(instant);
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[5]++;
            return value / iDivisor;

        } else {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[6]++;
            return ((value + 1) / iDivisor) - 1;
        }
    }

    /**
     * Add the specified amount of scaled units to the specified time
     * instant. The amount added may be negative.
     * 
     * @param instant  the time instant in millis to update.
     * @param amount  the amount of scaled units to add (can be negative).
     * @return the updated time instant.
     */
    public long add(long instant, int amount) {
        return getWrappedField().add(instant, amount * iDivisor);
    }

    /**
     * Add the specified amount of scaled units to the specified time
     * instant. The amount added may be negative.
     * 
     * @param instant  the time instant in millis to update.
     * @param amount  the amount of scaled units to add (can be negative).
     * @return the updated time instant.
     */
    public long add(long instant, long amount) {
        return getWrappedField().add(instant, amount * iDivisor);
    }

    /**
     * Add to the scaled component of the specified time instant,
     * wrapping around within that component if necessary.
     * 
     * @param instant  the time instant in millis to update.
     * @param amount  the amount of scaled units to add (can be negative).
     * @return the updated time instant.
     */
    public long addWrapField(long instant, int amount) {
        return set(instant, FieldUtils.getWrappedValue(get(instant), amount, iMin, iMax));
    }

    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return getWrappedField().getDifference(minuendInstant, subtrahendInstant) / iDivisor;
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        return getWrappedField().getDifferenceAsLong(minuendInstant, subtrahendInstant) / iDivisor;
    }

    /**
     * Set the specified amount of scaled units to the specified time instant.
     * 
     * @param instant  the time instant in millis to update.
     * @param value  value of scaled units to set.
     * @return the updated time instant.
     * @throws IllegalArgumentException if value is too large or too small.
     */
    public long set(long instant, int value) {
        FieldUtils.verifyValueBounds(this, value, iMin, iMax);
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[27]++;
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[28]++;
        int remainder = getRemainder(getWrappedField().get(instant));
        return getWrappedField().set(instant, value * iDivisor + remainder);
    }

    /**
     * Returns a scaled version of the wrapped field's unit duration field.
     */
    public DurationField getDurationField() {
        return iDurationField;
    }

    /**
     * Get the minimum value for the field.
     * 
     * @return the minimum value
     */
    public int getMinimumValue() {
        return iMin;
    }

    /**
     * Get the maximum value for the field.
     * 
     * @return the maximum value
     */
    public int getMaximumValue() {
        return iMax;
    }

    public long roundFloor(long instant) {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[29]++;
        DateTimeField field = getWrappedField();
        return field.roundFloor(field.set(instant, get(instant) * iDivisor));
    }

    public long remainder(long instant) {
        return set(instant, get(getWrappedField().remainder(instant)));
    }

    /**
     * Returns the divisor applied, in the field's units.
     * 
     * @return the divisor
     */
    public int getDivisor() {
        return iDivisor;
    }

    private int getRemainder(int value) {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[7]++;
            return value % iDivisor;

        } else {
CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt.branches[8]++;
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }

}

class CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.joda.time.field.DividedDateTimeField.java";
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

  public CodeCoverCoverageCounter$344gqnmyre57tuqfd7oaaagh6c3nyvcgbpiybtt () {
    super("org.joda.time.field.DividedDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
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
    log.startNamedSection("org.joda.time.field.DividedDateTimeField.java");
      for (int i = 1; i <= 30; i++) {
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

