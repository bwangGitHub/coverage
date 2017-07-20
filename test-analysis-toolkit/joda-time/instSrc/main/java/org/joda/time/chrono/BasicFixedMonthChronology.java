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

import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;

/**
 * Abstract implementation of a calendar system based around fixed length months.
 * <p>
 * As the month length is fixed various calculations can be optimised.
 * This implementation assumes any additional days after twelve
 * months fall into a thirteenth month.
 * <p>
 * BasicFixedMonthChronology is thread-safe and immutable, and all
 * subclasses must be as well.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.2, refactored from CopticChronology
 */
abstract class BasicFixedMonthChronology extends BasicChronology {
  static {
    CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = 261387371998L;
  static {
    CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[1]++;
  }

    /** The length of the month. */
    static final int MONTH_LENGTH = 30;
  static {
    CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[2]++;
  }

    /** The typical millis per year. */
    static final long MILLIS_PER_YEAR =
        (long) (365.25 * DateTimeConstants.MILLIS_PER_DAY);
  static {
    CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[3]++;
  }

    /** The length of the month in millis. */
    static final long MILLIS_PER_MONTH = ((long) MONTH_LENGTH) * DateTimeConstants.MILLIS_PER_DAY;
  static {
    CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[4]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Restricted constructor.
     *
     * @param base  the base chronology
     * @param param  the init parameter
     * @param minDaysInFirstWeek  the minimum days in the first week
     */
    BasicFixedMonthChronology(Chronology base, Object param, int minDaysInFirstWeek) {
        super(base, param, minDaysInFirstWeek);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[5]++;
    }

    //-----------------------------------------------------------------------
    long setYear(long instant, int year) {
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[6]++;
        // optimsed implementation of set, due to fixed months
        int thisYear = getYear(instant);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[7]++;
        int dayOfYear = getDayOfYear(instant, thisYear);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[8]++;
        int millisOfDay = getMillisOfDay(instant);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dayOfYear > 365) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.branches[1]++;
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
            // Current year is leap, and day is leap.
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.branches[3]++;
                // Moving to a non-leap year, leap day doesn't exist.
                dayOfYear--;
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[11]++;

            } else {
  CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.branches[2]++;}

        instant = getYearMonthDayMillis(year, 1, dayOfYear);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[12]++;
        instant += millisOfDay;
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[13]++;
        return instant;
    }

    //-----------------------------------------------------------------------
    long getYearDifference(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[14]++;
        // optimsed implementation of getDifference, due to fixed months
        int minuendYear = getYear(minuendInstant);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[15]++;
        int subtrahendYear = getYear(subtrahendInstant);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[16]++;

        // Inlined remainder method to avoid duplicate calls to get.
        long minuendRem = minuendInstant - getYearMillis(minuendYear);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[17]++;
        long subtrahendRem = subtrahendInstant - getYearMillis(subtrahendYear);
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[18]++;

        int difference = minuendYear - subtrahendYear;
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((minuendRem < subtrahendRem) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.branches[5]++;
            difference--;
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[20]++;

        } else {
  CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.branches[6]++;}
        return difference;
    }

    //-----------------------------------------------------------------------
    long getTotalMillisByYearMonth(int year, int month) {
        return ((month - 1) * MILLIS_PER_MONTH);
    }

    //-----------------------------------------------------------------------
    int getDayOfMonth(long millis) {
        // optimised for fixed months
        return (getDayOfYear(millis) - 1) % MONTH_LENGTH + 1;
    }

    //-----------------------------------------------------------------------
    boolean isLeapYear(int year) {
        return (year & 3) == 3;
    }

    //-----------------------------------------------------------------------
    int getDaysInYearMonth(int year, int month) {
        return (month != 13) ? MONTH_LENGTH : (isLeapYear(year) ? 6 : 5);
    }

    //-----------------------------------------------------------------------
    int getDaysInMonthMax() {
        return MONTH_LENGTH;
    }

    //-----------------------------------------------------------------------
    int getDaysInMonthMax(int month) {
        return (month != 13 ? MONTH_LENGTH : 6);
    }

    //-----------------------------------------------------------------------
    int getMonthOfYear(long millis) {
        return (getDayOfYear(millis) - 1) / MONTH_LENGTH + 1;
    }

    //-----------------------------------------------------------------------
    int getMonthOfYear(long millis, int year) {
CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt.statements[21]++;
        long monthZeroBased = (millis - getYearMillis(year)) / MILLIS_PER_MONTH;
        return ((int) monthZeroBased) + 1;
    }

    //-----------------------------------------------------------------------
    int getMaxMonth() {
        return 13;
    }

    //-----------------------------------------------------------------------
    long getAverageMillisPerYear() {
        return MILLIS_PER_YEAR;
    }

    //-----------------------------------------------------------------------
    long getAverageMillisPerYearDividedByTwo() {
        return MILLIS_PER_YEAR / 2;
    }

    //-----------------------------------------------------------------------
    long getAverageMillisPerMonth() {
        return MILLIS_PER_MONTH;
    }

}

class CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicFixedMonthChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
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

  public CodeCoverCoverageCounter$16eh7llr4wzwp25wlavn7rbtfabp89qjo82pezvwpzsrwpt () {
    super("org.joda.time.chrono.BasicFixedMonthChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
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
    log.startNamedSection("org.joda.time.chrono.BasicFixedMonthChronology.java");
      for (int i = 1; i <= 21; i++) {
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

