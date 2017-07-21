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

import org.joda.time.DurationFieldType;

/**
 * Duration field class representing a field with a fixed unit length.
 * <p>
 * PreciseDurationField is thread-safe and immutable.
 * 
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public class PreciseDurationField extends BaseDurationField {
  static {
    CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.ping();
  }

    
    private static final long serialVersionUID = -8346152187724495365L;
  static {
    CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[1]++;
  }

    /** The size of the unit */
    private final long iUnitMillis;

    /**
     * Constructor.
     * 
     * @param type  the field type
     * @param unitMillis  the unit milliseconds
     */    
    public PreciseDurationField(DurationFieldType type, long unitMillis) {
        super(type);
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[2]++;
        iUnitMillis = unitMillis;
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[3]++;
    }
    
    //------------------------------------------------------------------------
    /**
     * This field is precise.
     * 
     * @return true always
     */
    public final boolean isPrecise() {
        return true;
    }
    
    /**
     * Returns the amount of milliseconds per unit value of this field.
     *
     * @return the unit size of this field, in milliseconds
     */
    public final long getUnitMillis() {
        return iUnitMillis;
    }

    //------------------------------------------------------------------------
    /**
     * Get the value of this field from the milliseconds.
     * 
     * @param duration  the milliseconds to query, which may be negative
     * @param instant  ignored
     * @return the value of the field, in the units of the field, which may be
     * negative
     */
    public long getValueAsLong(long duration, long instant) {
        return duration / iUnitMillis;  // safe
    }

    /**
     * Get the millisecond duration of this field from its value.
     * 
     * @param value  the value of the field, which may be negative
     * @param instant  ignored
     * @return the milliseconds that the field represents, which may be
     * negative
     */
    public long getMillis(int value, long instant) {
        return value * iUnitMillis;  // safe
    }

    /**
     * Get the millisecond duration of this field from its value.
     * 
     * @param value  the value of the field, which may be negative
     * @param instant  ignored
     * @return the milliseconds that the field represents, which may be
     * negative
     */
    public long getMillis(long value, long instant) {
        return FieldUtils.safeMultiply(value, iUnitMillis);
    }

    public long add(long instant, int value) {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[4]++;
        long addition = value * iUnitMillis;  // safe
        return FieldUtils.safeAdd(instant, addition);
    }

    public long add(long instant, long value) {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[5]++;
        long addition = FieldUtils.safeMultiply(value, iUnitMillis);
        return FieldUtils.safeAdd(instant, addition);
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[6]++;
        long difference = FieldUtils.safeSubtract(minuendInstant, subtrahendInstant);
        return difference / iUnitMillis;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this duration field to another.
     * Two fields are equal if of the same type and duration.
     * 
     * @param obj  the object to compare to
     * @return if equal
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.branches[1]++;
            return true;

        } else {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.branches[2]++;
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[8]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof PreciseDurationField) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.branches[3]++;
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[9]++;
            PreciseDurationField other = (PreciseDurationField) obj;
            return (getType() == other.getType()) && (iUnitMillis == other.iUnitMillis);

        } else {
  CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.branches[4]++;}
}
        return false;
    }

    /**
     * Gets a hash code for this instance.
     * 
     * @return a suitable hashcode
     */
    public int hashCode() {
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[10]++;
        long millis = iUnitMillis;
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[11]++;
        int hash = (int) (millis ^ (millis >>> 32));
        hash += getType().hashCode();
CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch.statements[12]++;
        return hash;
    }

}

class CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.field.PreciseDurationField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$3nujv9xzf0wsi6ccdmtvyvhfdsd6317k07lt6ch () {
    super("org.joda.time.field.PreciseDurationField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.field.PreciseDurationField.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

