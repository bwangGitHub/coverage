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
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.field.SkipDateTimeField;

/**
 * Implements a pure proleptic Julian calendar system, which defines every
 * fourth year as leap. This implementation follows the leap year rule
 * strictly, even for dates before 8 CE, where leap years were actually
 * irregular. In the Julian calendar, year zero does not exist: 1 BCE is
 * followed by 1 CE.
 * <p>
 * Although the Julian calendar did not exist before 45 BCE, this chronology
 * assumes it did, thus it is proleptic. This implementation also fixes the
 * start of the year at January 1.
 * <p>
 * JulianChronology is thread-safe and immutable.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Julian_calendar">Wikipedia</a>
 * @see GregorianChronology
 * @see GJChronology
 *
 * @author Guy Allard
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class JulianChronology extends BasicGJChronology {
  static {
    CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -8731039522547897247L;
  static {
    CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[1]++;
  }

    private static final long MILLIS_PER_YEAR =
        (long) (365.25 * DateTimeConstants.MILLIS_PER_DAY);
  static {
    CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[2]++;
  }

    private static final long MILLIS_PER_MONTH =
        (long) (365.25 * DateTimeConstants.MILLIS_PER_DAY / 12);
  static {
    CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[3]++;
  }

    /** The lowest year that can be fully supported. */
    private static final int MIN_YEAR = -292269054;
  static {
    CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[4]++;
  }

    /** The highest year that can be fully supported. */
    private static final int MAX_YEAR = 292272992;
  static {
    CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[5]++;
  }

    /** Singleton instance of a UTC JulianChronology */
    private static final JulianChronology INSTANCE_UTC;

    /** Cache of zone to chronology arrays */
    private static final Map<DateTimeZone, JulianChronology[]> cCache = new HashMap<DateTimeZone, JulianChronology[]>();
  static {
    CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[6]++;
  }

    static {
        INSTANCE_UTC = getInstance(DateTimeZone.UTC);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[7]++;
    }

    static int adjustYearForSet(int year) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((year <= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[1]++;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((year == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[3]++;
                throw new IllegalFieldValueException
                    (DateTimeFieldType.year(), Integer.valueOf(year), null, null);

            } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[4]++;}
            year++;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[10]++;

        } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[2]++;}
        return year;
    }

    /**
     * Gets an instance of the JulianChronology.
     * The time zone of the returned instance is UTC.
     * 
     * @return a singleton UTC instance of the chronology
     */
    public static JulianChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Gets an instance of the JulianChronology in the default time zone.
     * 
     * @return a chronology in the default time zone
     */
    public static JulianChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    /**
     * Gets an instance of the JulianChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @return a chronology in the specified time zone
     */
    public static JulianChronology getInstance(DateTimeZone zone) {
        return getInstance(zone, 4);
    }

    /**
     * Gets an instance of the JulianChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @param minDaysInFirstWeek  minimum number of days in first week of the year; default is 4
     * @return a chronology in the specified time zone
     */
    public static JulianChronology getInstance(DateTimeZone zone, int minDaysInFirstWeek) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[5]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[12]++;

        } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[6]++;}
        JulianChronology chrono;
        synchronized (cCache) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[13]++;
            JulianChronology[] chronos = cCache.get(zone);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((chronos == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[7]++;
                chronos = new JulianChronology[7];
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[15]++;
                cCache.put(zone, chronos);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[16]++;

            } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[8]++;}
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[17]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                chrono = chronos[minDaysInFirstWeek - 1];
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[18]++;
            } catch (ArrayIndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[10]++;
                throw new IllegalArgumentException
                    ("Invalid min days in first week: " + minDaysInFirstWeek);
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[9]++;
}
  }
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[11]++;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[13]++;
                    chrono = new JulianChronology(null, null, minDaysInFirstWeek);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[21]++;

                } else {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[14]++;
                    chrono = getInstance(DateTimeZone.UTC, minDaysInFirstWeek);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[22]++;
                    chrono = new JulianChronology
                        (ZonedChronology.getInstance(chrono, zone), null, minDaysInFirstWeek);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[23]++;
                }
                chronos[minDaysInFirstWeek - 1] = chrono;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[24]++;

            } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[12]++;}
        }
        return chrono;
    }

    // Constructors and instance variables
    //-----------------------------------------------------------------------

    /**
     * Restricted constructor
     */
    JulianChronology(Chronology base, Object param, int minDaysInFirstWeek) {
        super(base, param, minDaysInFirstWeek);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[25]++;
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[26]++;
        Chronology base = getBase();
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[27]++;
        int minDays = getMinimumDaysInFirstWeek();
        minDays = (minDays == 0 ? 4 : minDays);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[28]++;  // handle rename of BaseGJChronology
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
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[15]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[30]++;

        } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[16]++;}
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[17]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[18]++;}
        return getInstance(zone);
    }

    long getDateMidnightMillis(int year, int monthOfYear, int dayOfMonth)
        throws IllegalArgumentException
    {
        return super.getDateMidnightMillis(adjustYearForSet(year), monthOfYear, dayOfMonth);
    }

    boolean isLeapYear(int year) {
        return (year & 3) == 0;
    }

    long calculateFirstDayOfYearMillis(int year) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[32]++;
        // Java epoch is 1970-01-01 Gregorian which is 1969-12-19 Julian.
        // Calculate relative to the nearest leap year and account for the
        // difference later.

        int relativeYear = year - 1968;
        int leapYears;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((relativeYear <= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[19]++;
            // Add 3 before shifting right since /4 and >>2 behave differently
            // on negative numbers.
            leapYears = (relativeYear + 3) >> 2;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[34]++;

        } else {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[20]++;
            leapYears = relativeYear >> 2;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[35]++;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
            // For post 1968 an adjustment is needed as jan1st is before leap day
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[21]++;
                leapYears++;
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[37]++;

            } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[22]++;}
        }
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[38]++;
        
        long millis = (relativeYear * 365L + leapYears) * (long)DateTimeConstants.MILLIS_PER_DAY;

        // Adjust to account for difference between 1968-01-01 and 1969-12-19.

        return millis - (366L + 352) * DateTimeConstants.MILLIS_PER_DAY;
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
        return (1969L * MILLIS_PER_YEAR + 352L * DateTimeConstants.MILLIS_PER_DAY) / 2;
    }

    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[39]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getBase() == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[23]++;
            super.assemble(fields);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[40]++;
            // Julian chronology has no year zero.
            fields.year = new SkipDateTimeField(this, fields.year);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[41]++;
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.statements[42]++;

        } else {
  CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h.branches[24]++;}
    }

}

class CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.JulianChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$1pul4m0rgj0pn06ornf2uyn3hqnxuak4h () {
    super("org.joda.time.chrono.JulianChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.JulianChronology.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

