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
 * <code>DecoratedDateTimeField</code> extends {@link BaseDateTimeField},
 * implementing only the minimum required set of methods. These implemented
 * methods delegate to a wrapped field.
 * <p>
 * This design allows new DateTimeField types to be defined that piggyback on
 * top of another, inheriting all the safe method implementations from
 * BaseDateTimeField. Should any method require pure delegation to the
 * wrapped field, simply override and use the provided getWrappedField method.
 * <p>
 * DecoratedDateTimeField is thread-safe and immutable, and its subclasses must
 * be as well.
 *
 * @author Brian S O'Neill
 * @since 1.0
 * @see DelegatedDateTimeField
 */
public abstract class DecoratedDateTimeField extends BaseDateTimeField {
  static {
    CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 203115783733757597L;
  static {
    CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.statements[1]++;
  }

    /** The DateTimeField being wrapped */
    private final DateTimeField iField;

    /**
     * Constructor.
     * 
     * @param field  the field being decorated
     * @param type  allow type to be overridden
     */
    protected DecoratedDateTimeField(DateTimeField field, DateTimeFieldType type) {
        super(type);
CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.statements[2]++;
CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.branches[1]++;
            throw new IllegalArgumentException("The field must not be null");

        } else {
  CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.branches[2]++;}
CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((field.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.branches[3]++;
            throw new IllegalArgumentException("The field must be supported");

        } else {
  CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.branches[4]++;}
        iField = field;
CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh.statements[5]++;
    }

    /**
     * Gets the wrapped date time field.
     * 
     * @return the wrapped DateTimeField
     */
    public final DateTimeField getWrappedField() {
        return iField;
    }

    public boolean isLenient() {
        return iField.isLenient();
    }

    public int get(long instant) {
        return iField.get(instant);
    }

    public long set(long instant, int value) {
        return iField.set(instant, value);
    }

    public DurationField getDurationField() {
        return iField.getDurationField();
    }

    public DurationField getRangeDurationField() {
        return iField.getRangeDurationField();
    }

    public int getMinimumValue() {
        return iField.getMinimumValue();
    }

    public int getMaximumValue() {
        return iField.getMaximumValue();
    }

    public long roundFloor(long instant) {
        return iField.roundFloor(instant);
    }

}

class CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.field.DecoratedDateTimeField.java";
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

  public CodeCoverCoverageCounter$4dgk2x1qn4j48jsb3by2ycwhgq3dkg8ejage6x9tgh () {
    super("org.joda.time.field.DecoratedDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("org.joda.time.field.DecoratedDateTimeField.java");
      for (int i = 1; i <= 5; i++) {
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

