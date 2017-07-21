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
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;

/**
 * ReadableDurationConverter extracts milliseconds and chronology from a ReadableDuration.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
class ReadableDurationConverter extends AbstractConverter
        implements DurationConverter, PeriodConverter {
  static {
    CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.ping();
  }


    /**
     * Singleton instance.
     */
    static final ReadableDurationConverter INSTANCE = new ReadableDurationConverter();
  static {
    CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected ReadableDurationConverter() {
        super();
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts the millis from an object of this convertor's type.
     * 
     * @param object  the object to convert, must not be null
     * @return the millisecond value
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     * @throws IllegalArgumentException if the object is invalid
     */
    public long getDurationMillis(Object object) {
        return ((ReadableDuration) object).getMillis();
    }

    //-----------------------------------------------------------------------
    /**
     * Extracts duration values from an object of this converter's type, and
     * sets them into the given ReadWritableDuration.
     *
     * @param writablePeriod  period to get modified
     * @param object  the object to convert, must not be null
     * @param chrono  the chronology to use, must not be null
     * @throws NullPointerException if the duration or object is null
     * @throws ClassCastException if the object is an invalid type
     * @throws IllegalArgumentException if the object is invalid
     */
    public void setInto(ReadWritablePeriod writablePeriod, Object object, Chronology chrono) {
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[3]++;
        ReadableDuration dur = (ReadableDuration) object;
        chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[4]++;
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[5]++;
        long duration = dur.getMillis();
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[6]++;
        int[] values = chrono.get(writablePeriod, duration);
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.loops[1]--;
  CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.loops[2]--;
  CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.loops[3]++;
}
            writablePeriod.setValue(i, values[i]);
CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt.statements[8]++;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Returns ReadableDuration.class.
     * 
     * @return ReadableDuration.class
     */
    public Class<?> getSupportedType() {
        return ReadableDuration.class;
    }

}

class CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[0];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.joda.time.convert.ReadableDurationConverter.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$1gmrigkgi8ir72bu9ybvrti6e7vbvotel1gl1lgx0uwrwpt () {
    super("org.joda.time.convert.ReadableDurationConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.convert.ReadableDurationConverter.java");
      for (int i = 1; i <= 8; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
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

