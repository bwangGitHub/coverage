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

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

/**
 * Precise datetime field, composed of two precise duration fields.
 * <p>
 * This DateTimeField is useful for defining DateTimeFields that are composed
 * of precise durations, like time of day fields. If either duration field is
 * imprecise, then an {@link ImpreciseDateTimeField} may be used instead.
 * <p>
 * PreciseDateTimeField is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 * @see ImpreciseDateTimeField
 */
public class PreciseDateTimeField extends PreciseDurationDateTimeField {
  static {
    CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.ping();
  }


    private static final long serialVersionUID = -5586801265774496376L;
  static {
    CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[1]++;
  }

    /** The maximum range in the correct units */
    private final int iRange;

    private final DurationField iRangeField;

    /**
     * Constructor.
     * 
     * @param type  the field type this field uses
     * @param unit  precise unit duration, like "seconds()".
     * @param range precise range duration, preferably a multiple of the unit,
     * like "minutes()".
     * @throws IllegalArgumentException if either duration field is imprecise
     * @throws IllegalArgumentException if unit milliseconds is less than one
     * or effective value range is less than two.
     */
    public PreciseDateTimeField(DateTimeFieldType type,
                                DurationField unit, DurationField range) {
        super(type, unit);
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[2]++;
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((range.isPrecise()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.branches[1]++;
            throw new IllegalArgumentException("Range duration field must be precise");

        } else {
  CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.branches[2]++;}
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[4]++;

        long rangeMillis = range.getUnitMillis();
        iRange = (int)(rangeMillis / getUnitMillis());
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[5]++;
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iRange < 2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.branches[3]++;
            throw new IllegalArgumentException("The effective range must be at least 2");

        } else {
  CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.branches[4]++;}

        iRangeField = range;
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[7]++;
    }

    /**
     * Get the amount of fractional units from the specified time instant.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @return the amount of fractional units extracted from the input.
     */
    public int get(long instant) {
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((instant >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.branches[5]++;
            return (int) ((instant / getUnitMillis()) % iRange);

        } else {
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.branches[6]++;
            return iRange - 1 + (int) (((instant + 1) / getUnitMillis()) % iRange);
        }
    }

    /**
     * Add to the component of the specified time instant, wrapping around
     * within that component if necessary.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to add to
     * @param amount  the amount of units to add (can be negative).
     * @return the updated time instant.
     */
    public long addWrapField(long instant, int amount) {
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[9]++;
        int thisValue = get(instant);
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[10]++;
        int wrappedValue = FieldUtils.getWrappedValue
            (thisValue, amount, getMinimumValue(), getMaximumValue());
        // copy code from set() to avoid repeat call to get()
        return instant + (wrappedValue - thisValue) * getUnitMillis();
    }

    /**
     * Set the specified amount of units to the specified time instant.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to set in
     * @param value  value of units to set.
     * @return the updated time instant.
     * @throws IllegalArgumentException if value is too large or too small.
     */
    public long set(long instant, int value) {
        FieldUtils.verifyValueBounds(this, value, getMinimumValue(), getMaximumValue());
CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5.statements[11]++;
        return instant + (value - get(instant)) * iUnitMillis;
    }

    /**
     * Returns the range duration of this field. For example, if this field
     * represents "minute of hour", then the range duration field is an hours.
     *
     * @return the range duration of this field, or null if field has no range
     */
    public DurationField getRangeDurationField() {
        return iRangeField;
    }

    /**
     * Get the maximum value for the field.
     * 
     * @return the maximum value
     */
    public int getMaximumValue() {
        return iRange - 1;
    }
    
    /**
     * Returns the range of the field in the field's units.
     * <p>
     * For example, 60 for seconds per minute. The field is allowed values
     * from 0 to range - 1.
     * 
     * @return unit range
     */
    public int getRange() {
        return iRange;
    }

}

class CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5 ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.field.PreciseDateTimeField.java";
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

  public CodeCoverCoverageCounter$3nujv9xzf0wshxxcheklh9c7gyuh0t0nmxhajj5 () {
    super("org.joda.time.field.PreciseDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
    log.startNamedSection("org.joda.time.field.PreciseDateTimeField.java");
      for (int i = 1; i <= 11; i++) {
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

