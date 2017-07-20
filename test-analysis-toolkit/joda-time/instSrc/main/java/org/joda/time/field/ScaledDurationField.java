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

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

/**
 * Scales a DurationField such that it's unit millis becomes larger in
 * magnitude.
 * <p>
 * ScaledDurationField is thread-safe and immutable.
 *
 * @see PreciseDurationField
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public class ScaledDurationField extends DecoratedDurationField {
  static {
    CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.ping();
  }


    private static final long serialVersionUID = -3205227092378684157L;
  static {
    CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[1]++;
  }

    private final int iScalar;

    /**
     * Constructor
     * 
     * @param field  the field to wrap, like "year()".
     * @param type  the type this field will actually use
     * @param scalar  scalar, such as 100 years in a century
     * @throws IllegalArgumentException if scalar is zero or one.
     */
    public ScaledDurationField(DurationField field, DurationFieldType type, int scalar) {
        super(field, type);
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[2]++;
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scalar == 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.branches[1]++;
            throw new IllegalArgumentException("The scalar must not be 0 or 1");

        } else {
  CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.branches[2]++;}
        iScalar = scalar;
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[4]++;
    }

    public int getValue(long duration) {
        return getWrappedField().getValue(duration) / iScalar;
    }

    public long getValueAsLong(long duration) {
        return getWrappedField().getValueAsLong(duration) / iScalar;
    }

    public int getValue(long duration, long instant) {
        return getWrappedField().getValue(duration, instant) / iScalar;
    }

    public long getValueAsLong(long duration, long instant) {
        return getWrappedField().getValueAsLong(duration, instant) / iScalar;
    }

    public long getMillis(int value) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[5]++;
        long scaled = ((long) value) * ((long) iScalar);
        return getWrappedField().getMillis(scaled);
    }

    public long getMillis(long value) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[6]++;
        long scaled = FieldUtils.safeMultiply(value, iScalar);
        return getWrappedField().getMillis(scaled);
    }

    public long getMillis(int value, long instant) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[7]++;
        long scaled = ((long) value) * ((long) iScalar);
        return getWrappedField().getMillis(scaled, instant);
    }

    public long getMillis(long value, long instant) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[8]++;
        long scaled = FieldUtils.safeMultiply(value, iScalar);
        return getWrappedField().getMillis(scaled, instant);
    }

    public long add(long instant, int value) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[9]++;
        long scaled = ((long) value) * ((long) iScalar);
        return getWrappedField().add(instant, scaled);
    }

    public long add(long instant, long value) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[10]++;
        long scaled = FieldUtils.safeMultiply(value, iScalar);
        return getWrappedField().add(instant, scaled);
    }

    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return getWrappedField().getDifference(minuendInstant, subtrahendInstant) / iScalar;
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        return getWrappedField().getDifferenceAsLong(minuendInstant, subtrahendInstant) / iScalar;
    }

    public long getUnitMillis() {
        return getWrappedField().getUnitMillis() * iScalar;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the scalar applied, in the field's units.
     * 
     * @return the scalar
     */
    public int getScalar() {
        return iScalar;
    }

    /**
     * Compares this duration field to another.
     * Two fields are equal if of the same type and duration.
     * 
     * @param obj  the object to compare to
     * @return if equal
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.branches[3]++;
            return true;

        } else {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.branches[4]++;
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[12]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof ScaledDurationField) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.branches[5]++;
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[13]++;
            ScaledDurationField other = (ScaledDurationField) obj;
            return (getWrappedField().equals(other.getWrappedField())) &&
                   (getType() == other.getType()) &&
                   (iScalar == other.iScalar);

        } else {
  CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.branches[6]++;}
}
        return false;
    }

    /**
     * Gets a hash code for this instance.
     * 
     * @return a suitable hashcode
     */
    public int hashCode() {
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[14]++;
        long scalar = iScalar;
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[15]++;
        int hash = (int) (scalar ^ (scalar >>> 32));
        hash += getType().hashCode();
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[16]++;
        hash += getWrappedField().hashCode();
CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp.statements[17]++;
        return hash;
    }

}

class CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.field.ScaledDurationField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1};
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

  public CodeCoverCoverageCounter$j7vzvznp8eop7o0v0wznbzwh5knxk9w7myyqp () {
    super("org.joda.time.field.ScaledDurationField.java");
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
    log.startNamedSection("org.joda.time.field.ScaledDurationField.java");
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

