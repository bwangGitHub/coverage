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
 * This field is not publicy exposed by ISOChronology, but rather it is used to
 * build the yearOfCentury and centuryOfEra fields. It merely drops the sign of
 * the year.
 *
 * @author Brian S O'Neill
 * @see GJYearOfEraDateTimeField
 * @since 1.0
 */
class ISOYearOfEraDateTimeField extends DecoratedDateTimeField {
  static {
    CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.ping();
  }


    private static final long serialVersionUID = 7037524068969447317L;
  static {
    CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.statements[1]++;
  }

    /**
     * Singleton instance
     */
    static final DateTimeField INSTANCE = new ISOYearOfEraDateTimeField();
  static {
    CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.statements[2]++;
  }

    /**
     * Restricted constructor.
     */
    private ISOYearOfEraDateTimeField() {
        super(GregorianChronology.getInstanceUTC().year(), DateTimeFieldType.yearOfEra());
CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.statements[3]++;
    }

    public int get(long instant) {
CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.statements[4]++;
        int year = getWrappedField().get(instant);
        return year < 0 ? -year : year;
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

    public long set(long instant, int year) {
        FieldUtils.verifyValueBounds(this, year, 0, getMaximumValue());
CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.statements[5]++;
CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getWrappedField().get(instant) < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.branches[1]++;
            year = -year;
CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.statements[7]++;

        } else {
  CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x.branches[2]++;}
        return super.set(instant, year);
    }

    public int getMinimumValue() {
        return 0;
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
        return INSTANCE;
    }
}

class CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.ISOYearOfEraDateTimeField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$1au6fh9ccrpght8ddyfjc5het42nvq0uvgbr7v9h2f2cy8x () {
    super("org.joda.time.chrono.ISOYearOfEraDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.ISOYearOfEraDateTimeField.java");
      for (int i = 1; i <= 7; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

