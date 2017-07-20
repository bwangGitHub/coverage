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
 * Generic offset adjusting datetime field.
 * <p>
 * OffsetDateTimeField is thread-safe and immutable.
 * 
 * @author Brian S O'Neill
 * @since 1.0
 */
public class OffsetDateTimeField extends DecoratedDateTimeField {
  static {
    CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.ping();
  }

    private static final long serialVersionUID = 3145790132623583142L;
  static {
    CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[1]++;
  }

    private final int iOffset;

    private final int iMin;
    private final int iMax;

    /**
     * Constructor.
     * 
     * @param field  the field to wrap, like "year()".
     * @param offset  offset to add to field values
     * @throws IllegalArgumentException if offset is zero
     */
    public OffsetDateTimeField(DateTimeField field, int offset) {
        this(field, (field == null ? null : field.getType()), offset, Integer.MIN_VALUE, Integer.MAX_VALUE);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[2]++;
    }

    /**
     * Constructor.
     * 
     * @param field  the field to wrap, like "year()".
     * @param type  the field type this field actually uses
     * @param offset  offset to add to field values
     * @throws IllegalArgumentException if offset is zero
     */
    public OffsetDateTimeField(DateTimeField field, DateTimeFieldType type, int offset) {
        this(field, type, offset, Integer.MIN_VALUE, Integer.MAX_VALUE);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[3]++;
    }

    /**
     * Constructor.
     * 
     * @param field  the field to wrap, like "year()".
     * @param type  the field type this field actually uses
     * @param offset  offset to add to field values
     * @param minValue  minimum allowed value
     * @param maxValue  maximum allowed value
     * @throws IllegalArgumentException if offset is zero
     */
    public OffsetDateTimeField(DateTimeField field, DateTimeFieldType type, int offset,
                               int minValue, int maxValue) {
        super(field, type);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[4]++;
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
                
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((offset == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.branches[1]++;
            throw new IllegalArgumentException("The offset cannot be zero");

        } else {
  CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.branches[2]++;}

        iOffset = offset;
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[6]++;
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((minValue < (field.getMinimumValue() + offset)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.branches[3]++;
            iMin = field.getMinimumValue() + offset;
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[8]++;

        } else {
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.branches[4]++;
            iMin = minValue;
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[9]++;
        }
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((maxValue > (field.getMaximumValue() + offset)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.branches[5]++;
            iMax = field.getMaximumValue() + offset;
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[11]++;

        } else {
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.branches[6]++;
            iMax = maxValue;
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[12]++;
        }
    }

    /**
     * Get the amount of offset units from the specified time instant.
     * 
     * @param instant  the time instant in millis to query.
     * @return the amount of units extracted from the input.
     */
    public int get(long instant) {
        return super.get(instant) + iOffset;
    }

    /**
     * Add the specified amount of offset units to the specified time
     * instant. The amount added may be negative.
     * 
     * @param instant  the time instant in millis to update.
     * @param amount  the amount of units to add (can be negative).
     * @return the updated time instant.
     */
    public long add(long instant, int amount) {
        instant = super.add(instant, amount);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[13]++;
        FieldUtils.verifyValueBounds(this, get(instant), iMin, iMax);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[14]++;
        return instant;
    }

    /**
     * Add the specified amount of offset units to the specified time
     * instant. The amount added may be negative.
     * 
     * @param instant  the time instant in millis to update.
     * @param amount  the amount of units to add (can be negative).
     * @return the updated time instant.
     */
    public long add(long instant, long amount) {
        instant = super.add(instant, amount);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[15]++;
        FieldUtils.verifyValueBounds(this, get(instant), iMin, iMax);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[16]++;
        return instant;
    }

    /**
     * Add to the offset component of the specified time instant,
     * wrapping around within that component if necessary.
     * 
     * @param instant  the time instant in millis to update.
     * @param amount  the amount of units to add (can be negative).
     * @return the updated time instant.
     */
    public long addWrapField(long instant, int amount) {
        return set(instant, FieldUtils.getWrappedValue(get(instant), amount, iMin, iMax));
    }

    /**
     * Set the specified amount of offset units to the specified time instant.
     * 
     * @param instant  the time instant in millis to update.
     * @param value  value of units to set.
     * @return the updated time instant.
     * @throws IllegalArgumentException if value is too large or too small.
     */
    public long set(long instant, int value) {
        FieldUtils.verifyValueBounds(this, value, iMin, iMax);
CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d.statements[17]++;
        return super.set(instant, value - iOffset);
    }

    public boolean isLeap(long instant) {
        return getWrappedField().isLeap(instant);
    }

    public int getLeapAmount(long instant) {
        return getWrappedField().getLeapAmount(instant);
    }

    public DurationField getLeapDurationField() {
        return getWrappedField().getLeapDurationField();
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
     * Returns the offset added to the field values.
     * 
     * @return the offset
     */
    public int getOffset() {
        return iOffset;
    }
}

class CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.field.OffsetDateTimeField.java";
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

  public CodeCoverCoverageCounter$iasq142myz7yi52yli8qaciam7691ve49kj1d () {
    super("org.joda.time.field.OffsetDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("org.joda.time.field.OffsetDateTimeField.java");
      for (int i = 1; i <= 17; i++) {
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

