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
package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;

/**
 * Implements a pure proleptic Gregorian calendar system, which defines every
 * fourth year as leap, unless the year is divisible by 100 and not by 400.
 * This improves upon the Julian calendar leap year rule.
 * <p>
 * Although the Gregorian calendar did not exist before 1582 CE, this
 * chronology assumes it did, thus it is proleptic. This implementation also
 * fixes the start of the year at January 1, and defines the year zero.
 * <p>
 * GregorianChronology is thread-safe and immutable.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Gregorian_calendar">Wikipedia</a>
 * @see JulianChronology
 * @see GJChronology
 * 
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class GregorianChronology extends BasicGJChronology {
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -861407383323710522L;
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[1]++;
  }

    private static final long MILLIS_PER_YEAR =
        (long) (365.2425 * DateTimeConstants.MILLIS_PER_DAY);
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[2]++;
  }

    private static final long MILLIS_PER_MONTH =
        (long) (365.2425 * DateTimeConstants.MILLIS_PER_DAY / 12);
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[3]++;
  }

    private static final int DAYS_0000_TO_1970 = 719527;
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[4]++;
  }

    /** The lowest year that can be fully supported. */
    private static final int MIN_YEAR = -292275054;
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[5]++;
  }

    /** The highest year that can be fully supported. */
    private static final int MAX_YEAR = 292278993;
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[6]++;
  }

    /** Singleton instance of a UTC GregorianChronology */
    private static final GregorianChronology INSTANCE_UTC;

    /** Cache of zone to chronology arrays */
    private static final Map<DateTimeZone, GregorianChronology[]> cCache = new HashMap<DateTimeZone, GregorianChronology[]>();
  static {
    CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[7]++;
  }

    static {
        INSTANCE_UTC = getInstance(DateTimeZone.UTC);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[8]++;
    }

    /**
     * Gets an instance of the GregorianChronology.
     * The time zone of the returned instance is UTC.
     * 
     * @return a singleton UTC instance of the chronology
     */
    public static GregorianChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Gets an instance of the GregorianChronology in the default time zone.
     * 
     * @return a chronology in the default time zone
     */
    public static GregorianChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    /**
     * Gets an instance of the GregorianChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @return a chronology in the specified time zone
     */
    public static GregorianChronology getInstance(DateTimeZone zone) {
        return getInstance(zone, 4);
    }

    /**
     * Gets an instance of the GregorianChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @param minDaysInFirstWeek  minimum number of days in first week of the year; default is 4
     * @return a chronology in the specified time zone
     */
    public static GregorianChronology getInstance(DateTimeZone zone, int minDaysInFirstWeek) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[1]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[10]++;

        } else {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[2]++;}
        GregorianChronology chrono;
        synchronized (cCache) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[11]++;
            GregorianChronology[] chronos = cCache.get(zone);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronos == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[3]++;
                chronos = new GregorianChronology[7];
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[13]++;
                cCache.put(zone, chronos);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[14]++;

            } else {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[4]++;}
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[15]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                chrono = chronos[minDaysInFirstWeek - 1];
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[16]++;
            } catch (ArrayIndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[6]++;
                throw new IllegalArgumentException
                    ("Invalid min days in first week: " + minDaysInFirstWeek);
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[5]++;
}
  }
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[7]++;
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[9]++;
                    chrono = new GregorianChronology(null, null, minDaysInFirstWeek);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[19]++;

                } else {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[10]++;
                    chrono = getInstance(DateTimeZone.UTC, minDaysInFirstWeek);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[20]++;
                    chrono = new GregorianChronology
                        (ZonedChronology.getInstance(chrono, zone), null, minDaysInFirstWeek);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[21]++;
                }
                chronos[minDaysInFirstWeek - 1] = chrono;
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[22]++;

            } else {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[8]++;}
        }
        return chrono;
    }

    // Constructors and instance variables
    //-----------------------------------------------------------------------

    /**
     * Restricted constructor
     */
    private GregorianChronology(Chronology base, Object param, int minDaysInFirstWeek) {
        super(base, param, minDaysInFirstWeek);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[23]++;
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[24]++;
        Chronology base = getBase();
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[25]++;
        int minDays = getMinimumDaysInFirstWeek();
        minDays = (minDays == 0 ? 4 : minDays);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[26]++;  // handle rename of BaseGJChronology
        return base == null ?
                getInstance(DateTimeZone.UTC, minDays) :
                    getInstance(base.getZone(), minDays);
    }

    // Conversion
    //-----------------------------------------------------------------------
    /**
     * Gets the Chronology in the UTC time zone.
     * 
     * @return the chronology in UTC
     */
    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Gets the Chronology in a specific time zone.
     * 
     * @param zone  the zone to get the chronology in, null is default
     * @return the chronology
     */
    public Chronology withZone(DateTimeZone zone) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[11]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[28]++;

        } else {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[12]++;}
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[13]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[14]++;}
        return getInstance(zone);
    }

    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getBase() == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[15]++;
            super.assemble(fields);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[31]++;

        } else {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[16]++;}
    }

    boolean isLeapYear(int year) {
        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    long calculateFirstDayOfYearMillis(int year) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[32]++;
        // Initial value is just temporary.
        int leapYears = year / 100;
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((year < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[17]++;
            // Add 3 before shifting right since /4 and >>2 behave differently
            // on negative numbers. When the expression is written as
            // (year / 4) - (year / 100) + (year / 400),
            // it works for both positive and negative values, except this optimization
            // eliminates two divisions.
            leapYears = ((year + 3) >> 2) - leapYears + ((leapYears + 3) >> 2) - 1;
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[34]++;

        } else {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[18]++;
            leapYears = (year >> 2) - leapYears + (leapYears >> 2);
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[35]++;
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[19]++;
                leapYears--;
CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.statements[37]++;

            } else {
  CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35.branches[20]++;}
        }

        return (year * 365L + (leapYears - DAYS_0000_TO_1970)) * DateTimeConstants.MILLIS_PER_DAY;
    }

    int getMinYear() {
        return MIN_YEAR;
    }

    int getMaxYear() {
        return MAX_YEAR;
    }

    long getAverageMillisPerYear() {
        return MILLIS_PER_YEAR;
    }

    long getAverageMillisPerYearDividedByTwo() {
        return MILLIS_PER_YEAR / 2;
    }

    long getAverageMillisPerMonth() {
        return MILLIS_PER_MONTH;
    }

    long getApproxMillisAtEpochDividedByTwo() {
        return (1970L * MILLIS_PER_YEAR) / 2;
    }

}

class CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35 ());
  }
    public static long[] statements = new long[38];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.GregorianChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$ggt4irvc9x0n32lm90wz4jwm600592pfjcf35 () {
    super("org.joda.time.chrono.GregorianChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 37; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.GregorianChronology.java");
      for (int i = 1; i <= 37; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

