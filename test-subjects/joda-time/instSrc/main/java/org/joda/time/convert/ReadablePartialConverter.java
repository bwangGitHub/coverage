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
package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

/**
 * ReadablePartialConverter extracts partial fields and chronology from a ReadablePartial.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
class ReadablePartialConverter extends AbstractConverter
        implements PartialConverter {
  static {
    CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.ping();
  }


    /**
     * Singleton instance.
     */
    static final ReadablePartialConverter INSTANCE = new ReadablePartialConverter();
  static {
    CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected ReadablePartialConverter() {
        super();
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology, which is taken from the ReadablePartial.
     * 
     * @param object  the ReadablePartial to convert, must not be null
     * @param zone  the specified zone to use, null means default zone
     * @return the chronology, never null
     */
    public Chronology getChronology(Object object, DateTimeZone zone) {
        return getChronology(object, (Chronology) null).withZone(zone);
    }

    /**
     * Gets the chronology, which is taken from the ReadableInstant.
     * <p>
     * If the passed in chronology is non-null, it is used.
     * Otherwise the chronology from the instant is used.
     * 
     * @param object  the ReadablePartial to convert, must not be null
     * @param chrono  the chronology to use, null means use that from object
     * @return the chronology, never null
     */
    public Chronology getChronology(Object object, Chronology chrono) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.branches[1]++;
            chrono = ((ReadablePartial) object).getChronology();
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[4]++;
            chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[5]++;

        } else {
  CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.branches[2]++;}
        return chrono;
    }

    /**
     * Extracts the values of the partial from an object of this converter's type.
     * The chrono parameter is a hint to the converter, should it require a
     * chronology to aid in conversion.
     * 
     * @param fieldSource  a partial that provides access to the fields.
     *  This partial may be incomplete and only getFieldType(int) should be used
     * @param object  the object to convert
     * @param chrono  the chronology to use, which is the non-null result of getChronology()
     * @return the array of field values that match the fieldSource, must be non-null valid
     * @throws ClassCastException if the object is invalid
     */
    public int[] getPartialValues(ReadablePartial fieldSource, Object object, Chronology chrono) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[6]++;
        ReadablePartial input = (ReadablePartial) object;
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[7]++;
        int size = fieldSource.size();
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[8]++;
        int[] values = new int[size];
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.loops[1]--;
  CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.loops[2]--;
  CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.loops[3]++;
}
            values[i] = input.get(fieldSource.getFieldType(i));
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[10]++;
        }
        chrono.validate(fieldSource, values);
CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup.statements[11]++;
        return values;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns ReadableInstant.class.
     * 
     * @return ReadableInstant.class
     */
    public Class<?> getSupportedType() {
        return ReadablePartial.class;
    }

}

class CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.convert.ReadablePartialConverter.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$7eg8s7dkkbmy6sfyauau530afhzn77oijo8bvowtm8hup () {
    super("org.joda.time.convert.ReadablePartialConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.convert.ReadablePartialConverter.java");
      for (int i = 1; i <= 11; i++) {
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
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

