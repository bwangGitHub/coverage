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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;

/**
 * Implements the Islamic, or Hijri, calendar system using arithmetic rules.
 * <p>
 * This calendar is a lunar calendar with a shorter year than ISO.
 * Year 1 in the Islamic calendar began on July 16, 622 CE (Julian), thus
 * Islamic years do not begin at the same time as Julian years. This chronology
 * is not proleptic, as it does not allow dates before the first Islamic year.
 * <p>
 * There are two basic forms of the Islamic calendar, the tabular and the
 * observed. The observed form cannot easily be used by computers as it
 * relies on human observation of the new moon.
 * The tabular calendar, implemented here, is an arithmetical approximation
 * of the observed form that follows relatively simple rules.
 * <p>
 * The tabular form of the calendar defines 12 months of alternately
 * 30 and 29 days. The last month is extended to 30 days in a leap year.
 * Leap years occur according to a 30 year cycle. There are four recognised
 * patterns of leap years in the 30 year cycle:
 * <pre>
 * Years 2, 5, 7, 10, 13, 15, 18, 21, 24, 26 & 29 - 15-based, used by Microsoft
 * Years 2, 5, 7, 10, 13, 16, 18, 21, 24, 26 & 29 - 16-based, most commonly used
 * Years 2, 5, 8, 10, 13, 16, 19, 21, 24, 27 & 29 - Indian
 * Years 2, 5, 8, 11, 13, 16, 19, 21, 24, 27 & 30 - Habash al-Hasib
 * </pre>
 * You can select which pattern to use via the factory methods, or use the
 * default (16-based).
 * <p>
 * This implementation defines a day as midnight to midnight exactly as per
 * the ISO chronology. This correct start of day is at sunset on the previous
 * day, however this cannot readily be modelled and has been ignored.
 * <p>
 * IslamicChronology is thread-safe and immutable.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Islamic_calendar">Wikipedia</a>
 *
 * @author Stephen Colebourne
 * @since 1.2
 */
public final class IslamicChronology extends BasicChronology {
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -3663823829888L;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[1]++;
  }

    /**
     * Constant value for 'Anno Hegirae', equivalent
     * to the value returned for AD/CE.
     */
    public static final int AH = DateTimeConstants.CE;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[2]++;
  }

    /** A singleton era field. */
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AH");
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[3]++;
  }

    /** Leap year 15-based pattern. */
    public static final LeapYearPatternType LEAP_YEAR_15_BASED = new LeapYearPatternType(0, 623158436);
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[4]++;
  }
    /** Leap year 16-based pattern. */
    public static final LeapYearPatternType LEAP_YEAR_16_BASED = new LeapYearPatternType(1, 623191204);
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[5]++;
  }
    /** Leap year Indian pattern. */
    public static final LeapYearPatternType LEAP_YEAR_INDIAN = new LeapYearPatternType(2, 690562340);
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[6]++;
  }
    /** Leap year Habash al-Hasib pattern. */
    public static final LeapYearPatternType LEAP_YEAR_HABASH_AL_HASIB = new LeapYearPatternType(3, 153692453);
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[7]++;
  }

    /** The lowest year that can be fully supported. */
    private static final int MIN_YEAR = -292269337;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[8]++;
  }

    /**
     * The highest year that can be fully supported.
     * Although calculateFirstDayOfYearMillis can go higher without
     * overflowing, the getYear method overflows when it adds the
     * approximate millis at the epoch.
     */
    private static final int MAX_YEAR = 292271022;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[9]++;
  }

    /** The days in a pair of months. */
    private static final int MONTH_PAIR_LENGTH = 59;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[10]++;
  }

    /** The length of the long month. */
    private static final int LONG_MONTH_LENGTH = 30;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[11]++;
  }

    /** The length of the short month. */
    private static final int SHORT_MONTH_LENGTH = 29;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[12]++;
  }

    /** The length of the long month in millis. */
    private static final long MILLIS_PER_MONTH_PAIR = 59L * DateTimeConstants.MILLIS_PER_DAY;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[13]++;
  }

    /** The length of the long month in millis. */
    private static final long MILLIS_PER_MONTH = (long) (29.53056 * DateTimeConstants.MILLIS_PER_DAY);
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[14]++;
  }

    /** The length of the long month in millis. */
    private static final long MILLIS_PER_LONG_MONTH = 30L * DateTimeConstants.MILLIS_PER_DAY;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[15]++;
  }

    /** The typical millis per year. */
    private static final long MILLIS_PER_YEAR = (long) (354.36667 * DateTimeConstants.MILLIS_PER_DAY);
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[16]++;
  }

    /** The typical millis per year. */
    private static final long MILLIS_PER_SHORT_YEAR = 354L * DateTimeConstants.MILLIS_PER_DAY;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[17]++;
  }

    /** The typical millis per year. */
    private static final long MILLIS_PER_LONG_YEAR = 355L * DateTimeConstants.MILLIS_PER_DAY;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[18]++;
  }

    /** The millis of 0001-01-01. */
    private static final long MILLIS_YEAR_1 = -42521587200000L;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[19]++;
  }
                                    //        -42520809600000L;
//    long start = 0L - 278L * DateTimeConstants.MILLIS_PER_DAY;
//    long cy = 46L * MILLIS_PER_CYCLE;  // 1381-01-01
//    long rem = 5L * MILLIS_PER_SHORT_YEAR +
//            3L * MILLIS_PER_LONG_YEAR;  // 1389-01-01

    /** The length of the cycle of leap years. */
    private static final int CYCLE = 30;
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[20]++;
  }

    /** The millis of a 30 year cycle. */
    private static final long MILLIS_PER_CYCLE = ((19L * 354L + 11L * 355L) * DateTimeConstants.MILLIS_PER_DAY);
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[21]++;
  }

    /** Cache of zone to chronology arrays */
    private static final Map<DateTimeZone, IslamicChronology[]> cCache = new HashMap<DateTimeZone, IslamicChronology[]>();
  static {
    CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[22]++;
  }

    /** Singleton instance of a UTC IslamicChronology */
    private static final IslamicChronology INSTANCE_UTC;
    static {
        // init after static fields
        INSTANCE_UTC = getInstance(DateTimeZone.UTC);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[23]++;
    }

    /** The leap years to use. */
    private final LeapYearPatternType iLeapYears;

    //-----------------------------------------------------------------------
    /**
     * Gets an instance of the IslamicChronology.
     * The time zone of the returned instance is UTC.
     * 
     * @return a singleton UTC instance of the chronology
     */
    public static IslamicChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Gets an instance of the IslamicChronology in the default time zone.
     * 
     * @return a chronology in the default time zone
     */
    public static IslamicChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), LEAP_YEAR_16_BASED);
    }

    /**
     * Gets an instance of the IslamicChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @return a chronology in the specified time zone
     */
    public static IslamicChronology getInstance(DateTimeZone zone) {
        return getInstance(zone, LEAP_YEAR_16_BASED);
    }

    /**
     * Gets an instance of the IslamicChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @param leapYears  the type defining the leap year pattern
     * @return a chronology in the specified time zone
     */
    public static IslamicChronology getInstance(DateTimeZone zone, LeapYearPatternType leapYears) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[24]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[1]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[25]++;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[2]++;}
        IslamicChronology chrono;
        synchronized (cCache) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[26]++;
            IslamicChronology[] chronos = cCache.get(zone);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[27]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronos == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[3]++;
                chronos = new IslamicChronology[4];
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[28]++;
                cCache.put(zone, chronos);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[29]++;

            } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[4]++;}
            chrono = chronos[leapYears.index];
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[30]++;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[31]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[5]++;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[7]++;
                    // First create without a lower limit.
                    chrono = new IslamicChronology(null, null, leapYears);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[33]++;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[34]++;
                    // Impose lower limit and make another IslamicChronology.
                    DateTime lowerLimit = new DateTime(1, 1, 1, 0, 0, 0, 0, chrono);
                    chrono = new IslamicChronology(
                        LimitChronology.getInstance(chrono, lowerLimit, null),
                         null, leapYears);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[35]++;

                } else {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[8]++;
                    chrono = getInstance(DateTimeZone.UTC, leapYears);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[36]++;
                    chrono = new IslamicChronology
                        (ZonedChronology.getInstance(chrono, zone), null, leapYears);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[37]++;
                }
                chronos[leapYears.index] = chrono;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[38]++;

            } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[6]++;}
        }
        return chrono;
    }

    // Constructors and instance variables
    //-----------------------------------------------------------------------
    /**
     * Restricted constructor.
     */
    IslamicChronology(Chronology base, Object param, LeapYearPatternType leapYears) {
        super(base, param, 4);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[39]++;
        this.iLeapYears = leapYears;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[40]++;
    }

    /**
     * Serialization singleton.
     */
    private Object readResolve() {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[41]++;
        Chronology base = getBase();
        return base == null ? getInstanceUTC() : getInstance(base.getZone());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the leap year pattern type.
     *
     * @return the pattern type
     */
    public LeapYearPatternType getLeapYearPatternType() {
        return iLeapYears;
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
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[42]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[9]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[43]++;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[10]++;}
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[44]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[11]++;
            return this;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[12]++;}
        return getInstance(zone);
    }

    /**
     * A suitable hash code for the chronology.
     * 
     * @return the hash code
     * @since 1.6
     */
    public int hashCode() {
        return super.hashCode() * 13 + getLeapYearPatternType().hashCode();
    }

    //-----------------------------------------------------------------------
    int getYear(long instant) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[45]++;
        long millisIslamic = instant - MILLIS_YEAR_1;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[46]++;
        long cycles = millisIslamic / MILLIS_PER_CYCLE;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[47]++;
        long cycleRemainder = millisIslamic % MILLIS_PER_CYCLE;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[48]++;
        
        int year = (int) ((cycles * CYCLE) + 1L);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[49]++;
        long yearMillis = (isLeapYear(year) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[50]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
        while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((cycleRemainder >= yearMillis) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[1]--;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[2]--;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[3]++;
}
            cycleRemainder -= yearMillis;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[51]++;
            yearMillis = (isLeapYear(++year) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[52]++;
        }
        return year;
    }

    long setYear(long instant, int year) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[53]++;
        // optimsed implementation of set, due to fixed months
        int thisYear = getYear(instant);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[54]++;
        int dayOfYear = getDayOfYear(instant, thisYear);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[55]++;
        int millisOfDay = getMillisOfDay(instant);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[56]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((dayOfYear > 354) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[13]++;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[57]++;
int CodeCoverConditionCoverageHelper_C9;
            // Current year is leap, and day is leap.
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[15]++;
                // Moving to a non-leap year, leap day doesn't exist.
                dayOfYear--;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[58]++;

            } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[16]++;}

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[14]++;}

        instant = getYearMonthDayMillis(year, 1, dayOfYear);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[59]++;
        instant += millisOfDay;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[60]++;
        return instant;
    }

    //-----------------------------------------------------------------------
    long getYearDifference(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[61]++;
        // optimsed implementation of getDifference, due to fixed months
        int minuendYear = getYear(minuendInstant);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[62]++;
        int subtrahendYear = getYear(subtrahendInstant);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[63]++;

        // Inlined remainder method to avoid duplicate calls to get.
        long minuendRem = minuendInstant - getYearMillis(minuendYear);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[64]++;
        long subtrahendRem = subtrahendInstant - getYearMillis(subtrahendYear);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[65]++;

        int difference = minuendYear - subtrahendYear;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[66]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((minuendRem < subtrahendRem) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[17]++;
            difference--;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[67]++;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[18]++;}
        return difference;
    }

    //-----------------------------------------------------------------------
    long getTotalMillisByYearMonth(int year, int month) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[68]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((--month % 2 == 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[19]++;
            month /= 2;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[69]++;
            return month * MILLIS_PER_MONTH_PAIR + MILLIS_PER_LONG_MONTH;

        } else {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[20]++;
            month /= 2;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[70]++;
            return month * MILLIS_PER_MONTH_PAIR;
        }
    }

    //-----------------------------------------------------------------------
    int getDayOfMonth(long millis) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[71]++;
        // optimised for simple months
        int doy = getDayOfYear(millis) - 1;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[72]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((doy == 354) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[21]++;
            return 30;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[22]++;}
        return (doy % MONTH_PAIR_LENGTH) % LONG_MONTH_LENGTH + 1;
    }

    //-----------------------------------------------------------------------
    boolean isLeapYear(int year) {
        return iLeapYears.isLeapYear(year);
    }

    //-----------------------------------------------------------------------
    int getDaysInYearMax() {
        return 355;
    }

    //-----------------------------------------------------------------------
    int getDaysInYear(int year) {
        return isLeapYear(year) ? 355 : 354;
    }

    //-----------------------------------------------------------------------
    int getDaysInYearMonth(int year, int month) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[73]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((month == 12) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[23]++;
            return LONG_MONTH_LENGTH;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[24]++;}
        return (--month % 2 == 0 ? LONG_MONTH_LENGTH : SHORT_MONTH_LENGTH);
    }

    //-----------------------------------------------------------------------
    int getDaysInMonthMax() {
        return LONG_MONTH_LENGTH;
    }

    //-----------------------------------------------------------------------
    int getDaysInMonthMax(int month) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[74]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((month == 12) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[25]++;
            return LONG_MONTH_LENGTH;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[26]++;}
        return (--month % 2 == 0 ? LONG_MONTH_LENGTH : SHORT_MONTH_LENGTH);
    }

    //-----------------------------------------------------------------------
    int getMonthOfYear(long millis, int year) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[75]++;
        int doyZeroBased = (int) ((millis - getYearMillis(year)) / DateTimeConstants.MILLIS_PER_DAY);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[76]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((doyZeroBased == 354) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[27]++;
            return 12;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[28]++;}
        return ((doyZeroBased * 2) / MONTH_PAIR_LENGTH) + 1;
//        return (int) (doyZeroBased / 29.9f) + 1;
//        
//        int monthPairZeroBased = doyZeroBased / MONTH_PAIR_LENGTH;
//        int monthPairRemainder = doyZeroBased % MONTH_PAIR_LENGTH;
//        return (monthPairZeroBased * 2) + 1 + (monthPairRemainder >= LONG_MONTH_LENGTH ? 1 : 0);
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

    //-----------------------------------------------------------------------
    long calculateFirstDayOfYearMillis(int year) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((year > MAX_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[29]++;
            throw new ArithmeticException("Year is too large: " + year + " > " + MAX_YEAR);

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[30]++;}
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[78]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((year < MIN_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[31]++;
            throw new ArithmeticException("Year is too small: " + year + " < " + MIN_YEAR);

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[32]++;}

        // Java epoch is 1970-01-01 Gregorian which is 0622-07-16 Islamic.
        // 0001-01-01 Islamic is -42520809600000L
        // would prefer to calculate against year zero, but leap year
        // can be in that year so it doesn't work
        year--;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[79]++;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[80]++;
        long cycle = year / CYCLE;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[81]++;
        long millis = MILLIS_YEAR_1 + cycle * MILLIS_PER_CYCLE;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[82]++;
        int cycleRemainder = (year % CYCLE) + 1;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[83]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;
        
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < cycleRemainder) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[4]--;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[5]--;
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.loops[6]++;
}
            millis += (isLeapYear(i) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[84]++;
        }
        
        return millis;
    }

    //-----------------------------------------------------------------------
    int getMinYear() {
        return 1; //MIN_YEAR;
    }

    //-----------------------------------------------------------------------
    int getMaxYear() {
        return MAX_YEAR;
    }

    //-----------------------------------------------------------------------
    long getApproxMillisAtEpochDividedByTwo() {
        // Epoch 1970-01-01 ISO = 1389-10-22 Islamic
        return (-MILLIS_YEAR_1) / 2;
    }

    //-----------------------------------------------------------------------
    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[85]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getBase() == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[33]++;
            super.assemble(fields);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[86]++;

            fields.era = ERA_FIELD;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[87]++;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 12);
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[88]++;
            fields.months = fields.monthOfYear.getDurationField();
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[89]++;

        } else {
  CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[34]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Opaque object describing a leap year pattern for the Islamic Chronology.
     *
     * @since 1.2
     */
    public static class LeapYearPatternType implements Serializable {
        /** Serialization lock */
        private static final long serialVersionUID = 26581275372698L;
//        /** Leap year raw data encoded into bits. */
//        private static final int[][] LEAP_YEARS = {
//            {2, 5, 7, 10, 13, 15, 18, 21, 24, 26, 29},  // 623158436
//            {2, 5, 7, 10, 13, 16, 18, 21, 24, 26, 29},  // 623191204
//            {2, 5, 8, 10, 13, 16, 19, 21, 24, 27, 29},  // 690562340
//            {0, 2, 5, 8, 11, 13, 16, 19, 21, 24, 27},   // 153692453
//        };
        
        /** The index. */
        final byte index;
        /** The leap year pattern, a bit-based 1=true pattern. */
        final int pattern;
        
        /**
         * Constructor.
         * This constructor takes a bit pattern where bits 0-29 correspond
         * to years 0-29 in the 30 year Islamic cycle of years. This allows
         * a highly efficient lookup by bit-matching.
         *
         * @param index  the index
         * @param pattern  the bit pattern
         */
        LeapYearPatternType(int index, int pattern) {
            super();
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[90]++;
            this.index = (byte) index;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[91]++;
            this.pattern = pattern;
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[92]++;
        }
        
        /**
         * Is the year a leap year.
         * @param year  the year to query
         * @return true if leap
         */
        boolean isLeapYear(int year) {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[93]++;
            int key = 1 << (year % 30);
            return ((pattern & key) > 0);
        }
        
        /**
         * Ensure a singleton is returned if possible.
         * @return the singleton instance
         */
        private Object readResolve() {
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.statements[94]++;
            switch (index) {
                case 0:
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[35]++;
                    return LEAP_YEAR_15_BASED;
                case 1:
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[36]++;
                    return LEAP_YEAR_16_BASED;
                case 2:
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[37]++;
                    return LEAP_YEAR_INDIAN;
                case 3:
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[38]++;
                    return LEAP_YEAR_HABASH_AL_HASIB;
                default:
CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1.branches[39]++;
                    return this;
            }
        }
    }
}

class CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1 ());
  }
    public static long[] statements = new long[95];
    public static long[] branches = new long[40];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.IslamicChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$c1v73ics8tyh6a8fwe6ofgetluof36jfc1 () {
    super("org.joda.time.chrono.IslamicChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 94; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 39; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.IslamicChronology.java");
      for (int i = 1; i <= 94; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 39; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

