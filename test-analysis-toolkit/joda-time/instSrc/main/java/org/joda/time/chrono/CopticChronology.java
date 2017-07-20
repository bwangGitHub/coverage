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
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.field.SkipDateTimeField;

/**
 * Implements the Coptic calendar system, which defines every fourth year as
 * leap, much like the Julian calendar. The year is broken down into 12 months,
 * each 30 days in length. An extra period at the end of the year is either 5
 * or 6 days in length. In this implementation, it is considered a 13th month.
 * <p>
 * Year 1 in the Coptic calendar began on August 29, 284 CE (Julian), thus
 * Coptic years do not begin at the same time as Julian years. This chronology
 * is not proleptic, as it does not allow dates before the first Coptic year.
 * <p>
 * This implementation defines a day as midnight to midnight exactly as per
 * the ISO chronology. Some references indicate that a coptic day starts at
 * sunset on the previous ISO day, but this has not been confirmed and is not
 * implemented.
 * <p>
 * CopticChronology is thread-safe and immutable.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Coptic_calendar">Wikipedia</a>
 * @see JulianChronology
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class CopticChronology extends BasicFixedMonthChronology {
  static {
    CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -5972804258688333942L;
  static {
    CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[1]++;
  }

    /**
     * Constant value for 'Anno Martyrum' or 'Era of the Martyrs', equivalent
     * to the value returned for AD/CE.
     */
    public static final int AM = DateTimeConstants.CE;
  static {
    CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[2]++;
  }

    /** A singleton era field. */
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AM");
  static {
    CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[3]++;
  }

    /** The lowest year that can be fully supported. */
    private static final int MIN_YEAR = -292269337;
  static {
    CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[4]++;
  }

    /** The highest year that can be fully supported. */
    private static final int MAX_YEAR = 292272708;
  static {
    CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[5]++;
  }

    /** Cache of zone to chronology arrays */
    private static final Map<DateTimeZone, CopticChronology[]> cCache = new HashMap<DateTimeZone, CopticChronology[]>();
  static {
    CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[6]++;
  }

    /** Singleton instance of a UTC CopticChronology */
    private static final CopticChronology INSTANCE_UTC;
    static {
        // init after static fields
        INSTANCE_UTC = getInstance(DateTimeZone.UTC);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[7]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets an instance of the CopticChronology.
     * The time zone of the returned instance is UTC.
     * 
     * @return a singleton UTC instance of the chronology
     */
    public static CopticChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Gets an instance of the CopticChronology in the default time zone.
     * 
     * @return a chronology in the default time zone
     */
    public static CopticChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    /**
     * Gets an instance of the CopticChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @return a chronology in the specified time zone
     */
    public static CopticChronology getInstance(DateTimeZone zone) {
        return getInstance(zone, 4);
    }

    /**
     * Gets an instance of the CopticChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @param minDaysInFirstWeek  minimum number of days in first week of the year; default is 4
     * @return a chronology in the specified time zone
     */
    public static CopticChronology getInstance(DateTimeZone zone, int minDaysInFirstWeek) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[1]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[9]++;

        } else {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[2]++;}
        CopticChronology chrono;
        synchronized (cCache) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[10]++;
            CopticChronology[] chronos = cCache.get(zone);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronos == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[3]++;
                chronos = new CopticChronology[7];
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[12]++;
                cCache.put(zone, chronos);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[13]++;

            } else {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[4]++;}
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[14]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                chrono = chronos[minDaysInFirstWeek - 1];
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[15]++;
            } catch (ArrayIndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[6]++;
                throw new IllegalArgumentException
                    ("Invalid min days in first week: " + minDaysInFirstWeek);
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[5]++;
}
  }
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[7]++;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[9]++;
                    // First create without a lower limit.
                    chrono = new CopticChronology(null, null, minDaysInFirstWeek);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[18]++;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[19]++;
                    // Impose lower limit and make another CopticChronology.
                    DateTime lowerLimit = new DateTime(1, 1, 1, 0, 0, 0, 0, chrono);
                    chrono = new CopticChronology
                        (LimitChronology.getInstance(chrono, lowerLimit, null),
                         null, minDaysInFirstWeek);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[20]++;

                } else {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[10]++;
                    chrono = getInstance(DateTimeZone.UTC, minDaysInFirstWeek);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[21]++;
                    chrono = new CopticChronology
                        (ZonedChronology.getInstance(chrono, zone), null, minDaysInFirstWeek);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[22]++;
                }
                chronos[minDaysInFirstWeek - 1] = chrono;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[23]++;

            } else {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[8]++;}
        }
        return chrono;
    }

    // Constructors and instance variables
    //-----------------------------------------------------------------------
    /**
     * Restricted constructor.
     */
    CopticChronology(Chronology base, Object param, int minDaysInFirstWeek) {
        super(base, param, minDaysInFirstWeek);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[24]++;
    }

    /**
     * Serialization singleton.
     */
    private Object readResolve() {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[25]++;
        Chronology base = getBase();
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[26]++;
        int minDays = getMinimumDaysInFirstWeek();
        minDays = (minDays == 0 ? 4 : minDays);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[27]++;  // handle rename of BaseGJChronology
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
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[11]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[29]++;

        } else {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[12]++;}
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[13]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[14]++;}
        return getInstance(zone);
    }

    //-----------------------------------------------------------------------
    long calculateFirstDayOfYearMillis(int year) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[31]++;
        // Java epoch is 1970-01-01 Gregorian which is 1686-04-23 Coptic.
        // Calculate relative to the nearest leap year and account for the
        // difference later.

        int relativeYear = year - 1687;
        int leapYears;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((relativeYear <= 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[15]++;
            // Add 3 before shifting right since /4 and >>2 behave differently
            // on negative numbers.
            leapYears = (relativeYear + 3) >> 2;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[33]++;

        } else {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[16]++;
            leapYears = relativeYear >> 2;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[34]++;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
            // For post 1687 an adjustment is needed as jan1st is before leap day
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[17]++;
                leapYears++;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[36]++;

            } else {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[18]++;}
        }
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[37]++;
        
        long millis = (relativeYear * 365L + leapYears)
            * (long)DateTimeConstants.MILLIS_PER_DAY;

        // Adjust to account for difference between 1687-01-01 and 1686-04-23.

        return millis + (365L - 112) * DateTimeConstants.MILLIS_PER_DAY;
    }

    //-----------------------------------------------------------------------
    int getMinYear() {
        return MIN_YEAR;
    }

    //-----------------------------------------------------------------------
    int getMaxYear() {
        return MAX_YEAR;
    }

    //-----------------------------------------------------------------------
    long getApproxMillisAtEpochDividedByTwo() {
        return (1686L * MILLIS_PER_YEAR + 112L * DateTimeConstants.MILLIS_PER_DAY) / 2;
    }

    //-----------------------------------------------------------------------
    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getBase() == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[19]++;
            super.assemble(fields);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[39]++;

            // Coptic, like Julian, has no year zero.
            fields.year = new SkipDateTimeField(this, fields.year);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[40]++;
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[41]++;
            
            fields.era = ERA_FIELD;
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[42]++;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[43]++;
            fields.months = fields.monthOfYear.getDurationField();
CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.statements[44]++;

        } else {
  CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01.branches[20]++;}
    }

}

class CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01 ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.CopticChronology.java";
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

  public CodeCoverCoverageCounter$1k0k8pqhvrdcy6ct31rbmc7k8etxxmu01 () {
    super("org.joda.time.chrono.CopticChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
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
    log.startNamedSection("org.joda.time.chrono.CopticChronology.java");
      for (int i = 1; i <= 44; i++) {
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

