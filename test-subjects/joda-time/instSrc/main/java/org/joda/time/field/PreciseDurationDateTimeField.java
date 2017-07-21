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
 * Precise datetime field, which has a precise unit duration field.
 * <p>
 * PreciseDurationDateTimeField is thread-safe and immutable, and its
 * subclasses must be as well.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class PreciseDurationDateTimeField extends BaseDateTimeField {
  static {
    CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.ping();
  }


    private static final long serialVersionUID = 5004523158306266035L;
  static {
    CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[1]++;
  }

    /** The fractional unit in millis */
    final long iUnitMillis;

    private final DurationField iUnitField;

    /**
     * Constructor.
     * 
     * @param type  the field type
     * @param unit  precise unit duration, like "days()".
     * @throws IllegalArgumentException if duration field is imprecise
     * @throws IllegalArgumentException if unit milliseconds is less than one
     */
    public PreciseDurationDateTimeField(DateTimeFieldType type, DurationField unit) {
        super(type);
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[2]++;
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((unit.isPrecise()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[1]++;
            throw new IllegalArgumentException("Unit duration field must be precise");

        } else {
  CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[2]++;}

        iUnitMillis = unit.getUnitMillis();
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[4]++;
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iUnitMillis < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[3]++;
            throw new IllegalArgumentException("The unit milliseconds must be at least 1");

        } else {
  CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[4]++;}

        iUnitField = unit;
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[6]++;
    }

    /**
     * Returns false by default.
     */
    public boolean isLenient() {
        return false;
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
        FieldUtils.verifyValueBounds(this, value, getMinimumValue(),
                                     getMaximumValueForSet(instant, value));
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[7]++;
        return instant + (value - get(instant)) * iUnitMillis;
    }

    /**
     * This method assumes that this field is properly rounded on
     * 1970-01-01T00:00:00. If the rounding alignment differs, override this
     * method as follows:
     * <pre>
     * return super.roundFloor(instant + ALIGNMENT_MILLIS) - ALIGNMENT_MILLIS;
     * </pre>
     */
    public long roundFloor(long instant) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((instant >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[5]++;
            return instant - instant % iUnitMillis;

        } else {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[6]++;
            instant += 1;
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[9]++;
            return instant - instant % iUnitMillis - iUnitMillis;
        }
    }

    /**
     * This method assumes that this field is properly rounded on
     * 1970-01-01T00:00:00. If the rounding alignment differs, override this
     * method as follows:
     * <pre>
     * return super.roundCeiling(instant + ALIGNMENT_MILLIS) - ALIGNMENT_MILLIS;
     * </pre>
     */
    public long roundCeiling(long instant) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((instant > 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[7]++;
            instant -= 1;
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[11]++;
            return instant - instant % iUnitMillis + iUnitMillis;

        } else {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[8]++;
            return instant - instant % iUnitMillis;
        }
    }

    /**
     * This method assumes that this field is properly rounded on
     * 1970-01-01T00:00:00. If the rounding alignment differs, override this
     * method as follows:
     * <pre>
     * return super.remainder(instant + ALIGNMENT_MILLIS);
     * </pre>
     */
    public long remainder(long instant) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((instant >= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[9]++;
            return instant % iUnitMillis;

        } else {
CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl.branches[10]++;
            return (instant + 1) % iUnitMillis + iUnitMillis - 1;
        }
    }

    /**
     * Returns the duration per unit value of this field. For example, if this
     * field represents "minute of hour", then the duration field is minutes.
     *
     * @return the duration of this field, or UnsupportedDurationField if field
     * has no duration
     */
    public DurationField getDurationField() {
        return iUnitField;
    }

    /**
     * Get the minimum value for the field.
     * 
     * @return the minimum value
     */
    public int getMinimumValue() {
        return 0;
    }

    public final long getUnitMillis() {
        return iUnitMillis;
    }

    /**
     * Called by the set method to get the maximum allowed value. By default,
     * returns getMaximumValue(instant). Override to provide a faster
     * implementation.
     */
    protected int getMaximumValueForSet(long instant, int value) {
        return getMaximumValue(instant);
    }

}

class CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.joda.time.field.PreciseDurationDateTimeField.java";
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

  public CodeCoverCoverageCounter$e9ajw1h1wgbne3e5jng9yv4ka46wcrlx0ql813vhjla7e9yugrl () {
    super("org.joda.time.field.PreciseDurationDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("org.joda.time.field.PreciseDurationDateTimeField.java");
      for (int i = 1; i <= 12; i++) {
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

