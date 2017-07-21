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
 * <code>DelegatedDurationField</code> delegates each method call to the
 * duration field it wraps.
 * <p>
 * DelegatedDurationField is thread-safe and immutable, and its subclasses must
 * be as well.
 *
 * @author Brian S O'Neill
 * @see DecoratedDurationField
 * @since 1.0
 */
public class DelegatedDurationField extends DurationField implements Serializable {
  static {
    CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.ping();
  }


    /** Serialization lock. */
    private static final long serialVersionUID = -5576443481242007829L;
  static {
    CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.statements[1]++;
  }

    /** The DurationField being wrapped */
    private final DurationField iField;
    /** The field type */
    private final DurationFieldType iType;

    /**
     * Constructor.
     * 
     * @param field  the base field
     */
    protected DelegatedDurationField(DurationField field) {
        this(field, null);
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.statements[2]++;
    }

    /**
     * Constructor.
     * 
     * @param field  the base field
     * @param type  the field type to use
     */
    protected DelegatedDurationField(DurationField field, DurationFieldType type) {
        super();
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.statements[3]++;
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.branches[1]++;
            throw new IllegalArgumentException("The field must not be null");

        } else {
  CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.branches[2]++;}
        iField = field;
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.statements[5]++;
        iType = (type == null ? field.getType() : type);
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.statements[6]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the wrapped duration field.
     * 
     * @return the wrapped DurationField
     */
    public final DurationField getWrappedField() {
        return iField;
    }

    public DurationFieldType getType() {
        return iType;
    }

    public String getName() {
        return iType.getName();
    }

    /**
     * Returns true if this field is supported.
     */
    public boolean isSupported() {
        return iField.isSupported();
    }

    public boolean isPrecise() {
        return iField.isPrecise();
    }
    
    public int getValue(long duration) {
        return iField.getValue(duration);
    }

    public long getValueAsLong(long duration) {
        return iField.getValueAsLong(duration);
    }

    public int getValue(long duration, long instant) {
        return iField.getValue(duration, instant);
    }

    public long getValueAsLong(long duration, long instant) {
        return iField.getValueAsLong(duration, instant);
    }

    public long getMillis(int value) {
        return iField.getMillis(value);
    }

    public long getMillis(long value) {
        return iField.getMillis(value);
    }

    public long getMillis(int value, long instant) {
        return iField.getMillis(value, instant);
    }

    public long getMillis(long value, long instant) {
        return iField.getMillis(value, instant);
    }

    public long add(long instant, int value) {
        return iField.add(instant, value);
    }

    public long add(long instant, long value) {
        return iField.add(instant, value);
    }

    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return iField.getDifference(minuendInstant, subtrahendInstant);
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        return iField.getDifferenceAsLong(minuendInstant, subtrahendInstant);
    }

    public long getUnitMillis() {
        return iField.getUnitMillis();
    }

    public int compareTo(DurationField durationField) {
        return iField.compareTo(durationField);
    }

    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof DelegatedDurationField) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.branches[3]++;
            return iField.equals(((DelegatedDurationField) obj).iField);

        } else {
  CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75.branches[4]++;}
        return false;
    }

    public int hashCode() {
        return iField.hashCode() ^ iType.hashCode();
    }

    public String toString() {
        return (iType == null) ? iField.toString() :
            ("DurationField[" + iType + ']');
    }

}

class CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75 ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.field.DelegatedDurationField.java";
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

  public CodeCoverCoverageCounter$4dgkhlr5yflock54da4ashd7ipid4xmi3tkrdzzq75 () {
    super("org.joda.time.field.DelegatedDurationField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
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
    log.startNamedSection("org.joda.time.field.DelegatedDurationField.java");
      for (int i = 1; i <= 7; i++) {
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

