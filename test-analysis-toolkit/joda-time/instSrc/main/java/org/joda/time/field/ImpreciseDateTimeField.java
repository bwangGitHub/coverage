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
import org.joda.time.DurationFieldType;

/**
 * Abstract datetime field class that defines its own DurationField, which
 * delegates back into this ImpreciseDateTimeField.
 * <p>
 * This DateTimeField is useful for defining DateTimeFields that are composed
 * of imprecise durations. If both duration fields are precise, then a
 * {@link PreciseDateTimeField} should be used instead.
 * <p>
 * When defining imprecise DateTimeFields where a matching DurationField is
 * already available, just extend BaseDateTimeField directly so as not to
 * create redundant DurationField instances.
 * <p>
 * ImpreciseDateTimeField is thread-safe and immutable, and its subclasses must
 * be as well.
 *
 * @author Brian S O'Neill
 * @see PreciseDateTimeField
 * @since 1.0
 */
public abstract class ImpreciseDateTimeField extends BaseDateTimeField {
  static {
    CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.ping();
  }


    private static final long serialVersionUID = 7190739608550251860L;
  static {
    CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[1]++;
  }

    final long iUnitMillis;
    private final DurationField iDurationField;

    /**
     * Constructor.
     * 
     * @param type  the field type
     * @param unitMillis  the average duration unit milliseconds
     */
    public ImpreciseDateTimeField(DateTimeFieldType type, long unitMillis) {
        super(type);
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[2]++;
        iUnitMillis = unitMillis;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[3]++;
        iDurationField = new LinkedDurationField(type.getDurationType());
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[4]++;
    }

    public abstract int get(long instant);

    public abstract long set(long instant, int value);

    public abstract long add(long instant, int value);

    public abstract long add(long instant, long value);

    /**
     * Computes the difference between two instants, as measured in the units
     * of this field. Any fractional units are dropped from the result. Calling
     * getDifference reverses the effect of calling add. In the following code:
     *
     * <pre>
     * long instant = ...
     * int v = ...
     * int age = getDifference(add(instant, v), instant);
     * </pre>
     *
     * The value 'age' is the same as the value 'v'.
     * <p>
     * The default implementation call getDifferenceAsLong and converts the
     * return value to an int.
     *
     * @param minuendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract from
     * @param subtrahendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract off the minuend
     * @return the difference in the units of this field
     */
    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return FieldUtils.safeToInt(getDifferenceAsLong(minuendInstant, subtrahendInstant));
    }

    /**
     * Computes the difference between two instants, as measured in the units
     * of this field. Any fractional units are dropped from the result. Calling
     * getDifference reverses the effect of calling add. In the following code:
     *
     * <pre>
     * long instant = ...
     * long v = ...
     * long age = getDifferenceAsLong(add(instant, v), instant);
     * </pre>
     *
     * The value 'age' is the same as the value 'v'.
     * <p>
     * The default implementation performs a guess-and-check algorithm using
     * getDurationField().getUnitMillis() and the add() method. Subclasses are
     * encouraged to provide a more efficient implementation.
     *
     * @param minuendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract from
     * @param subtrahendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract off the minuend
     * @return the difference in the units of this field
     */
    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((minuendInstant < subtrahendInstant) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.branches[1]++;
            return -getDifferenceAsLong(subtrahendInstant, minuendInstant);

        } else {
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.branches[2]++;}
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[6]++;
        
        long difference = (minuendInstant - subtrahendInstant) / iUnitMillis;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((add(subtrahendInstant, difference) < minuendInstant) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.branches[3]++;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
            do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[1]--;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[2]--;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[3]++;
}
                difference++;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[9]++;
            } while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((add(subtrahendInstant, difference) <= minuendInstant) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false));
            difference--;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[10]++;

        } else {
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.branches[4]++;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((add(subtrahendInstant, difference) > minuendInstant) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.branches[5]++;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[12]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
            do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[4]--;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[5]--;
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.loops[6]++;
}
                difference--;
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[13]++;
            } while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((add(subtrahendInstant, difference) > minuendInstant) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false));

        } else {
  CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.branches[6]++;}
}
        return difference;
    }

    public final DurationField getDurationField() {
        return iDurationField;
    }

    public abstract DurationField getRangeDurationField();

    public abstract long roundFloor(long instant);

    protected final long getDurationUnitMillis() {
        return iUnitMillis;
    }

    private final class LinkedDurationField extends BaseDurationField {
        private static final long serialVersionUID = -203813474600094134L;

        LinkedDurationField(DurationFieldType type) {
            super(type);
CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl.statements[14]++;
        }
    
        public boolean isPrecise() {
            return false;
        }
    
        public long getUnitMillis() {
            return iUnitMillis;
        }

        public int getValue(long duration, long instant) {
            return ImpreciseDateTimeField.this
                .getDifference(instant + duration, instant);
        }

        public long getValueAsLong(long duration, long instant) {
            return ImpreciseDateTimeField.this
                .getDifferenceAsLong(instant + duration, instant);
        }
        
        public long getMillis(int value, long instant) {
            return ImpreciseDateTimeField.this.add(instant, value) - instant;
        }

        public long getMillis(long value, long instant) {
            return ImpreciseDateTimeField.this.add(instant, value) - instant;
        }

        public long add(long instant, int value) {
            return ImpreciseDateTimeField.this.add(instant, value);
        }
        
        public long add(long instant, long value) {
            return ImpreciseDateTimeField.this.add(instant, value);
        }
        
        public int getDifference(long minuendInstant, long subtrahendInstant) {
            return ImpreciseDateTimeField.this
                .getDifference(minuendInstant, subtrahendInstant);
        }
        
        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            return ImpreciseDateTimeField.this
                .getDifferenceAsLong(minuendInstant, subtrahendInstant);
        }
    }

}

class CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.joda.time.field.ImpreciseDateTimeField.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$4p1k140rscprzpltfgfbkwkoaieoetxwa3590j0zfl () {
    super("org.joda.time.field.ImpreciseDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.field.ImpreciseDateTimeField.java");
      for (int i = 1; i <= 14; i++) {
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
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

