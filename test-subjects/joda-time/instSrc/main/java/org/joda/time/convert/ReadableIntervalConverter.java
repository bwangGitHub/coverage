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
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableInterval;

/**
 * Converts intervals into durations of any requested period type.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
class ReadableIntervalConverter extends AbstractConverter
        implements IntervalConverter, DurationConverter, PeriodConverter {
  static {
    CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.ping();
  }


    /**
     * Singleton instance.
     */
    static final ReadableIntervalConverter INSTANCE = new ReadableIntervalConverter();
  static {
    CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected ReadableIntervalConverter() {
        super();
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the millisecond length of the interval.
     * 
     * @param object  the interval
     */
    public long getDurationMillis(Object object) {
        return (((ReadableInterval) object)).toDurationMillis();
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the values of the mutable duration from the specified interval.
     * 
     * @param writablePeriod  the period to modify
     * @param object  the interval to set from
     * @param chrono  the chronology to use
     */
    public void setInto(ReadWritablePeriod writablePeriod, Object object, Chronology chrono) {
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[3]++;
        ReadableInterval interval = (ReadableInterval) object;
        chrono = (chrono != null ? chrono : DateTimeUtils.getIntervalChronology(interval));
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[4]++;
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[5]++;
        long start = interval.getStartMillis();
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[6]++;
        long end = interval.getEndMillis();
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[7]++;
        int[] values = chrono.get(writablePeriod, start, end);
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.loops[1]--;
  CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.loops[2]--;
  CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.loops[3]++;
}
            writablePeriod.setValue(i, values[i]);
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[9]++;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the input is a ReadableInterval.
     * <p>
     * If it is, then the calling code should cast and copy the fields directly.
     *
     * @param object  the object to convert, must not be null
     * @param chrono  the chronology to use, may be null
     * @return true if the input is a ReadableInterval
     * @throws ClassCastException if the object is invalid
     */
    public boolean isReadableInterval(Object object, Chronology chrono) {
        return true;
    }

    /**
     * Extracts interval endpoint values from an object of this converter's
     * type, and sets them into the given ReadWritableInterval.
     *
     * @param writableInterval interval to get modified, not null
     * @param object  the object to convert, must not be null
     * @param chrono  the chronology to use, may be null
     * @throws ClassCastException if the object is invalid
     */
    public void setInto(ReadWritableInterval writableInterval, Object object, Chronology chrono) {
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[10]++;
        ReadableInterval input = (ReadableInterval) object;
        writableInterval.setInterval(input);
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[11]++;
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chrono != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.branches[1]++;
            writableInterval.setChronology(chrono);
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[13]++;

        } else {
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.branches[2]++;
            writableInterval.setChronology(input.getChronology());
CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p.statements[14]++;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Returns ReadableInterval.class.
     */
    public Class<?> getSupportedType() {
        return ReadableInterval.class;
    }

}

class CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.convert.ReadableIntervalConverter.java";
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

  public CodeCoverCoverageCounter$1gmrigkgi8ir7357pjjcz6n179c2bo5uexnr4bv9bm6hs2p () {
    super("org.joda.time.convert.ReadableIntervalConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
    log.startNamedSection("org.joda.time.convert.ReadableIntervalConverter.java");
      for (int i = 1; i <= 14; i++) {
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

