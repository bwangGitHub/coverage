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
package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.ReadablePartial;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.FieldUtils;

/**
 * Provides time calculations for the year of era component of time.
 * 
 * @author Brian S O'Neill
 * @since 1.0
 */
final class GJYearOfEraDateTimeField extends DecoratedDateTimeField {
  static {
    CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.ping();
  }


    private static final long serialVersionUID = -5961050944769862059L;
  static {
    CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[1]++;
  }

    private final BasicChronology iChronology;

    /**
     * Restricted constructor.
     */
    GJYearOfEraDateTimeField(DateTimeField yearField, BasicChronology chronology) {
        super(yearField, DateTimeFieldType.yearOfEra());
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[2]++;
        iChronology = chronology;
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[3]++;
    }

    public int get(long instant) {
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[4]++;
        int year = getWrappedField().get(instant);
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((year <= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.branches[1]++;
            year = 1 - year;
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[6]++;

        } else {
  CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.branches[2]++;}
        return year;
    }

    public long add(long instant, int years) {
        return getWrappedField().add(instant, years);
    }

    public long add(long instant, long years) {
        return getWrappedField().add(instant, years);
    }

    public long addWrapField(long instant, int years) {
        return getWrappedField().addWrapField(instant, years);
    }

    public int[] addWrapField(ReadablePartial instant, int fieldIndex, int[] values, int years) {
        return getWrappedField().addWrapField(instant, fieldIndex, values, years);
    }

    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return getWrappedField().getDifference(minuendInstant, subtrahendInstant);
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        return getWrappedField().getDifferenceAsLong(minuendInstant, subtrahendInstant);
    }

    /**
     * Set the year component of the specified time instant.
     * 
     * @param instant  the time instant in millis to update.
     * @param year  the year (0,292278994) to update the time to.
     * @return the updated time instant.
     * @throws IllegalArgumentException  if year is invalid.
     */
    public long set(long instant, int year) {
        FieldUtils.verifyValueBounds(this, year, 1, getMaximumValue());
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[7]++;
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iChronology.getYear(instant) <= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.branches[3]++;
            year = 1 - year;
CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.statements[9]++;

        } else {
  CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9.branches[4]++;}
        return super.set(instant, year);
    }

    public int getMinimumValue() {
        return 1;
    }

    public int getMaximumValue() {
        return getWrappedField().getMaximumValue();
    }

    public long roundFloor(long instant) {
        return getWrappedField().roundFloor(instant);
    }

    public long roundCeiling(long instant) {
        return getWrappedField().roundCeiling(instant);
    }

    public long remainder(long instant) {
        return getWrappedField().remainder(instant);
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.yearOfEra();
    }
}

class CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9 ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.GJYearOfEraDateTimeField.java";
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

  public CodeCoverCoverageCounter$6ejddkyvz3moybbitdmsvkzr8hcmg9q2l9rx5ygypbvq9 () {
    super("org.joda.time.chrono.GJYearOfEraDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("org.joda.time.chrono.GJYearOfEraDateTimeField.java");
      for (int i = 1; i <= 9; i++) {
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

