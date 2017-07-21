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
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.ISOChronology;

/**
 * ReadableInstantConverter extracts milliseconds and chronology from a ReadableInstant.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
class ReadableInstantConverter extends AbstractConverter
        implements InstantConverter, PartialConverter {
  static {
    CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.ping();
  }


    /**
     * Singleton instance.
     */
    static final ReadableInstantConverter INSTANCE = new ReadableInstantConverter();
  static {
    CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected ReadableInstantConverter() {
        super();
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology, which is taken from the ReadableInstant.
     * If the chronology on the instant is null, the ISOChronology in the
     * specified time zone is used.
     * If the chronology on the instant is not in the specified zone, it is
     * adapted.
     * 
     * @param object  the ReadableInstant to convert, must not be null
     * @param zone  the specified zone to use, null means default zone
     * @return the chronology, never null
     */
    public Chronology getChronology(Object object, DateTimeZone zone) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[3]++;
        Chronology chrono = ((ReadableInstant) object).getChronology();
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[1]++;
            return ISOChronology.getInstance(zone);

        } else {
  CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[2]++;}
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[5]++;
        DateTimeZone chronoZone = chrono.getZone();
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronoZone != zone) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[3]++;
            chrono = chrono.withZone(zone);
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[7]++;
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[5]++;
                return ISOChronology.getInstance(zone);

            } else {
  CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[4]++;}
        return chrono;
    }

    /**
     * Gets the chronology, which is taken from the ReadableInstant.
     * <p>
     * If the passed in chronology is non-null, it is used.
     * Otherwise the chronology from the instant is used.
     * 
     * @param object  the ReadableInstant to convert, must not be null
     * @param chrono  the chronology to use, null means use that from object
     * @return the chronology, never null
     */
    public Chronology getChronology(Object object, Chronology chrono) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[7]++;
            chrono = ((ReadableInstant) object).getChronology();
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[10]++;
            chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.statements[11]++;

        } else {
  CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl.branches[8]++;}
        return chrono;
    }

    /**
     * Extracts the millis from an object of this convertor's type.
     * 
     * @param object  the ReadableInstant to convert, must not be null
     * @param chrono  the non-null result of getChronology
     * @return the millisecond value
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     */
    public long getInstantMillis(Object object, Chronology chrono) {
        return ((ReadableInstant) object).getMillis();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns ReadableInstant.class.
     * 
     * @return ReadableInstant.class
     */
    public Class<?> getSupportedType() {
        return ReadableInstant.class;
    }

}

class CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.joda.time.convert.ReadableInstantConverter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$7eg8s7dkkbmy6mo59i42kk3nh4d8vxfxassd7zekb2yjl () {
    super("org.joda.time.convert.ReadableInstantConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.convert.ReadableInstantConverter.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

