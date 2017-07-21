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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Instant;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Implements the Gregorian/Julian calendar system which is the calendar system
 * used in most of the world. Wherever possible, it is recommended to use the
 * {@link ISOChronology} instead.
 * <p>
 * The Gregorian calendar replaced the Julian calendar, and the point in time
 * when this chronology switches can be controlled using the second parameter
 * of the getInstance method. By default this cutover is set to the date the
 * Gregorian calendar was first instituted, October 15, 1582.
 * <p>
 * Before this date, this chronology uses the proleptic Julian calendar
 * (proleptic means extending indefinitely). The Julian calendar has leap years
 * every four years, whereas the Gregorian has special rules for 100 and 400
 * years. A meaningful result will thus be obtained for all input values.
 * However before 8 CE, Julian leap years were irregular, and before 45 BCE
 * there was no Julian calendar.
 * <p>
 * This chronology differs from
 * {@link java.util.GregorianCalendar GregorianCalendar} in that years
 * in BCE are returned correctly. Thus year 1 BCE is returned as -1 instead of 1.
 * The yearOfEra field produces results compatible with GregorianCalendar.
 * <p>
 * The Julian calendar does not have a year zero, and so year -1 is followed by
 * year 1. If the Gregorian cutover date is specified at or before year -1
 * (Julian), year zero is defined. In other words, the proleptic Gregorian
 * chronology used by this class has a year zero.
 * <p>
 * To create a pure proleptic Julian chronology, use {@link JulianChronology},
 * and to create a pure proleptic Gregorian chronology, use
 * {@link GregorianChronology}.
 * <p>
 * GJChronology is thread-safe and immutable.
 * 
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class GJChronology extends AssembledChronology {
  static {
    CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -2545574827706931671L;
  static {
    CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[1]++;
  }

    /**
     * Convert a datetime from one chronology to another.
     */
    private static long convertByYear(long instant, Chronology from, Chronology to) {
        return to.getDateTimeMillis
            (from.year().get(instant),
             from.monthOfYear().get(instant),
             from.dayOfMonth().get(instant),
             from.millisOfDay().get(instant));
    }

    /**
     * Convert a datetime from one chronology to another.
     */
    private static long convertByWeekyear(final long instant, Chronology from, Chronology to) {
        long newInstant;
        newInstant = to.weekyear().set(0, from.weekyear().get(instant));
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[2]++;
        newInstant = to.weekOfWeekyear().set(newInstant, from.weekOfWeekyear().get(instant));
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[3]++;
        newInstant = to.dayOfWeek().set(newInstant, from.dayOfWeek().get(instant));
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[4]++;
        newInstant = to.millisOfDay().set(newInstant, from.millisOfDay().get(instant));
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[5]++;
        return newInstant;
    }

    /**
     * The default GregorianJulian cutover point.
     */
    static final Instant DEFAULT_CUTOVER = new Instant(-12219292800000L);
  static {
    CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[6]++;
  }

    /** Cache of zone to chronology list */
    private static final Map<DateTimeZone, ArrayList<GJChronology>> cCache = new HashMap<DateTimeZone, ArrayList<GJChronology>>();
  static {
    CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[7]++;
  }

    /**
     * Factory method returns instances of the default GJ cutover
     * chronology. This uses a cutover date of October 15, 1582 (Gregorian)
     * 00:00:00 UTC. For this value, October 4, 1582 (Julian) is followed by
     * October 15, 1582 (Gregorian).
     *
     * <p>The first day of the week is designated to be
     * {@link org.joda.time.DateTimeConstants#MONDAY Monday},
     * and the minimum days in the first week of the year is 4.
     *
     * <p>The time zone of the returned instance is UTC.
     */
    public static GJChronology getInstanceUTC() {
        return getInstance(DateTimeZone.UTC, DEFAULT_CUTOVER, 4);
    }

    /**
     * Factory method returns instances of the default GJ cutover
     * chronology. This uses a cutover date of October 15, 1582 (Gregorian)
     * 00:00:00 UTC. For this value, October 4, 1582 (Julian) is followed by
     * October 15, 1582 (Gregorian).
     *
     * <p>The first day of the week is designated to be
     * {@link org.joda.time.DateTimeConstants#MONDAY Monday},
     * and the minimum days in the first week of the year is 4.
     *
     * <p>The returned chronology is in the default time zone.
     */
    public static GJChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), DEFAULT_CUTOVER, 4);
    }

    /**
     * Factory method returns instances of the GJ cutover chronology. This uses
     * a cutover date of October 15, 1582 (Gregorian) 00:00:00 UTC. For this
     * value, October 4, 1582 (Julian) is followed by October 15, 1582
     * (Gregorian).
     *
     * <p>The first day of the week is designated to be
     * {@link org.joda.time.DateTimeConstants#MONDAY Monday},
     * and the minimum days in the first week of the year is 4.
     *
     * @param zone  the time zone to use, null is default
     */
    public static GJChronology getInstance(DateTimeZone zone) {
        return getInstance(zone, DEFAULT_CUTOVER, 4);
    }

    /**
     * Factory method returns instances of the GJ cutover chronology. Any
     * cutover date may be specified.
     *
     * <p>The first day of the week is designated to be
     * {@link org.joda.time.DateTimeConstants#MONDAY Monday},
     * and the minimum days in the first week of the year is 4.
     *
     * @param zone  the time zone to use, null is default
     * @param gregorianCutover  the cutover to use, null means default
     */
    public static GJChronology getInstance(
            DateTimeZone zone,
            ReadableInstant gregorianCutover) {
        
        return getInstance(zone, gregorianCutover, 4);
    }
    
    /**
     * Factory method returns instances of the GJ cutover chronology. Any
     * cutover date may be specified.
     *
     * @param zone  the time zone to use, null is default
     * @param gregorianCutover  the cutover to use, null means default
     * @param minDaysInFirstWeek  minimum number of days in first week of the year; default is 4
     */
    public static synchronized GJChronology getInstance(
            DateTimeZone zone,
            ReadableInstant gregorianCutover,
            int minDaysInFirstWeek) {
        
        zone = DateTimeUtils.getZone(zone);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[8]++;
        Instant cutoverInstant;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((gregorianCutover == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[1]++;
            cutoverInstant = DEFAULT_CUTOVER;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[10]++;

        } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[2]++;
            cutoverInstant = gregorianCutover.toInstant();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[11]++;
        }

        GJChronology chrono;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[12]++;

        ArrayList<GJChronology> chronos = cCache.get(zone);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronos == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[3]++;
            chronos = new ArrayList<GJChronology>(2);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[14]++;
            cCache.put(zone, chronos);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[15]++;

        } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[4]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
            for (int i=chronos.size();(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((--i>=0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); ) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[1]--;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[2]--;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[3]++;
}
                chrono = chronos.get(i);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[17]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((minDaysInFirstWeek == chrono.getMinimumDaysInFirstWeek()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cutoverInstant.equals(chrono.getGregorianCutover())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[5]++;
                    
                    return chrono;

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[6]++;}
            }
        }
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[7]++;
            chrono = new GJChronology
                (JulianChronology.getInstance(zone, minDaysInFirstWeek),
                 GregorianChronology.getInstance(zone, minDaysInFirstWeek),
                 cutoverInstant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[20]++;

        } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[8]++;
            chrono = getInstance(DateTimeZone.UTC, cutoverInstant, minDaysInFirstWeek);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[21]++;
            chrono = new GJChronology
                (ZonedChronology.getInstance(chrono, zone),
                 chrono.iJulianChronology,
                 chrono.iGregorianChronology,
                 chrono.iCutoverInstant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[22]++;
        }

        chronos.add(chrono);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[23]++;

        return chrono;
    }

    /**
     * Factory method returns instances of the GJ cutover chronology. Any
     * cutover date may be specified.
     *
     * @param zone  the time zone to use, null is default
     * @param gregorianCutover  the cutover to use
     * @param minDaysInFirstWeek  minimum number of days in first week of the year; default is 4
     */
    public static GJChronology getInstance(
            DateTimeZone zone,
            long gregorianCutover,
            int minDaysInFirstWeek) {
        
        Instant cutoverInstant;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((gregorianCutover == DEFAULT_CUTOVER.getMillis()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[9]++;
            cutoverInstant = null;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[25]++;

        } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[10]++;
            cutoverInstant = new Instant(gregorianCutover);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[26]++;
        }
        return getInstance(zone, cutoverInstant, minDaysInFirstWeek);
    }

    //-----------------------------------------------------------------------
    private JulianChronology iJulianChronology;
    private GregorianChronology iGregorianChronology;
    private Instant iCutoverInstant;

    private long iCutoverMillis;
    private long iGapDuration;

    /**
     * @param julian chronology used before the cutover instant
     * @param gregorian chronology used at and after the cutover instant
     * @param cutoverInstant instant when the gregorian chronology began
     */
    private GJChronology(JulianChronology julian,
                         GregorianChronology gregorian,
                         Instant cutoverInstant) {
        super(null, new Object[] {julian, gregorian, cutoverInstant});
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[27]++;
    }

    /**
     * Called when applying a time zone.
     */
    private GJChronology(Chronology base,
                         JulianChronology julian,
                         GregorianChronology gregorian,
                         Instant cutoverInstant) {
        super(base, new Object[] {julian, gregorian, cutoverInstant});
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[28]++;
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return getInstance(getZone(), iCutoverInstant, getMinimumDaysInFirstWeek());
    }

    public DateTimeZone getZone() {
        Chronology base;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[29]++;
        if ((base = getBase()) != null) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[11]++;
            return base.getZone();

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[12]++;}
        return DateTimeZone.UTC;
    }

    // Conversion
    //-----------------------------------------------------------------------
    /**
     * Gets the Chronology in the UTC time zone.
     * 
     * @return the chronology in UTC
     */
    public Chronology withUTC() {
        return withZone(DateTimeZone.UTC);
    }

    /**
     * Gets the Chronology in a specific time zone.
     * 
     * @param zone  the zone to get the chronology in, null is default
     * @return the chronology
     */
    public Chronology withZone(DateTimeZone zone) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[13]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[31]++;

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[14]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[15]++;
            return this;

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[16]++;}
        return getInstance(zone, iCutoverInstant, getMinimumDaysInFirstWeek());
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int millisOfDay)
        throws IllegalArgumentException
    {
        Chronology base;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[33]++;
        if ((base = getBase()) != null) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[17]++;
            return base.getDateTimeMillis(year, monthOfYear, dayOfMonth, millisOfDay);

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[18]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[34]++;

        // Assume date is Gregorian.
        long instant = iGregorianChronology.getDateTimeMillis
            (year, monthOfYear, dayOfMonth, millisOfDay);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[35]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((instant < iCutoverMillis) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[19]++;
            // Maybe it's Julian.
            instant = iJulianChronology.getDateTimeMillis
                (year, monthOfYear, dayOfMonth, millisOfDay);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[36]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((instant >= iCutoverMillis) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[21]++;
                // Okay, it's in the illegal cutover gap.
                throw new IllegalArgumentException("Specified date does not exist");

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[22]++;}

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[20]++;}
        return instant;
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
        Chronology base;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[38]++;
        if ((base = getBase()) != null) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[23]++;
            return base.getDateTimeMillis
                (year, monthOfYear, dayOfMonth,
                 hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[24]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[39]++;

        // Assume date is Gregorian.
        long instant = iGregorianChronology.getDateTimeMillis
            (year, monthOfYear, dayOfMonth,
             hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[40]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((instant < iCutoverMillis) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[25]++;
            // Maybe it's Julian.
            instant = iJulianChronology.getDateTimeMillis
                (year, monthOfYear, dayOfMonth,
                 hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[41]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((instant >= iCutoverMillis) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[27]++;
                // Okay, it's in the illegal cutover gap.
                throw new IllegalArgumentException("Specified date does not exist");

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[28]++;}

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[26]++;}
        return instant;
    }

    /**
     * Gets the cutover instant between Gregorian and Julian chronologies.
     * @return the cutover instant
     */
    public Instant getGregorianCutover() {
        return iCutoverInstant;
    }

    /**
     * Gets the minimum days needed for a week to be the first week in a year.
     * 
     * @return the minimum days
     */
    public int getMinimumDaysInFirstWeek() {
        return iGregorianChronology.getMinimumDaysInFirstWeek();
    }

    /**
     * Checks if this chronology instance equals another.
     * 
     * @param obj  the object to compare to
     * @return true if equal
     * @since 1.6
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * A suitable hash code for the chronology.
     * 
     * @return the hash code
     * @since 1.6
     */
    public int hashCode() {
        return "GJ".hashCode() * 11 + iJulianChronology.hashCode() +
            iGregorianChronology.hashCode() + iCutoverInstant.hashCode();
    }

    // Output
    //-----------------------------------------------------------------------
    /**
     * Gets a debugging toString.
     * 
     * @return a debugging string
     */
    public String toString() {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[43]++;
        StringBuffer sb = new StringBuffer(60);
        sb.append("GJChronology");
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[44]++;
        sb.append('[');
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[45]++;
        sb.append(getZone().getID());
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[46]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[47]++;
int CodeCoverConditionCoverageHelper_C16;
        
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((iCutoverMillis != DEFAULT_CUTOVER.getMillis()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[29]++;
            sb.append(",cutover=");
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[48]++;
            DateTimeFormatter printer;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[49]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((withUTC().dayOfYear().remainder(iCutoverMillis) == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[31]++;
                printer = ISODateTimeFormat.date();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[50]++;

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[32]++;
                printer = ISODateTimeFormat.dateTime();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[51]++;
            }
            printer.withChronology(withUTC()).printTo(sb, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[52]++;

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[30]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[53]++;
int CodeCoverConditionCoverageHelper_C18;
        
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((getMinimumDaysInFirstWeek() != 4) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[33]++;
            sb.append(",mdfw=");
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[54]++;
            sb.append(getMinimumDaysInFirstWeek());
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[55]++;

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[34]++;}
        sb.append(']');
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[56]++;
        
        return sb.toString();
    }

    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[57]++;
        Object[] params = (Object[])getParam();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[58]++;

        JulianChronology julian = (JulianChronology)params[0];
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[59]++;
        GregorianChronology gregorian = (GregorianChronology)params[1];
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[60]++;
        Instant cutoverInstant = (Instant)params[2];
        iCutoverMillis = cutoverInstant.getMillis();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[61]++;

        iJulianChronology = julian;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[62]++;
        iGregorianChronology = gregorian;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[63]++;
        iCutoverInstant = cutoverInstant;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[64]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[65]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getBase() != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[35]++;
            return;

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[36]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[66]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((julian.getMinimumDaysInFirstWeek() != gregorian.getMinimumDaysInFirstWeek()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[37]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[38]++;}

        // Compute difference between the chronologies at the cutover instant
        iGapDuration = iCutoverMillis - julianToGregorianByYear(iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[67]++;

        // Begin field definitions.

        // First just copy all the Gregorian fields and then override those
        // that need special attention.
        fields.copyFieldsFrom(gregorian);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[68]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[69]++;
int CodeCoverConditionCoverageHelper_C21;
        
        // Assuming cutover is at midnight, all time of day fields can be
        // gregorian since they are unaffected by cutover.

        // Verify assumption.
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((gregorian.millisOfDay().get(iCutoverMillis) == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[39]++;
            // Cutover is sometime in the day, so cutover fields are required
            // for time of day.

            fields.millisOfSecond = new CutoverField(julian.millisOfSecond(), fields.millisOfSecond, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[70]++;
            fields.millisOfDay = new CutoverField(julian.millisOfDay(), fields.millisOfDay, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[71]++;
            fields.secondOfMinute = new CutoverField(julian.secondOfMinute(), fields.secondOfMinute, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[72]++;
            fields.secondOfDay = new CutoverField(julian.secondOfDay(), fields.secondOfDay, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[73]++;
            fields.minuteOfHour = new CutoverField(julian.minuteOfHour(), fields.minuteOfHour, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[74]++;
            fields.minuteOfDay = new CutoverField(julian.minuteOfDay(), fields.minuteOfDay, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[75]++;
            fields.hourOfDay = new CutoverField(julian.hourOfDay(), fields.hourOfDay, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[76]++;
            fields.hourOfHalfday = new CutoverField(julian.hourOfHalfday(), fields.hourOfHalfday, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[77]++;
            fields.clockhourOfDay = new CutoverField(julian.clockhourOfDay(), fields.clockhourOfDay, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[78]++;
            fields.clockhourOfHalfday = new CutoverField(julian.clockhourOfHalfday(),
                                                         fields.clockhourOfHalfday, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[79]++;
            fields.halfdayOfDay = new CutoverField(julian.halfdayOfDay(), fields.halfdayOfDay, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[80]++;

        } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[40]++;}

        // These fields just require basic cutover support.
        {
            fields.era = new CutoverField(julian.era(), fields.era, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[81]++;
        }

        // DayOfYear and weekOfWeekyear require special handling since cutover
        // year has fewer days and weeks. Extend the cutover to the start of
        // the next year or weekyear. This keeps the sequence unbroken during
        // the cutover year.

        {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[82]++;
            long cutover = gregorian.year().roundCeiling(iCutoverMillis);
            fields.dayOfYear = new CutoverField(
                julian.dayOfYear(), fields.dayOfYear, cutover);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[83]++;
        }

        {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[84]++;
            long cutover = gregorian.weekyear().roundCeiling(iCutoverMillis);
            fields.weekOfWeekyear = new CutoverField(
                julian.weekOfWeekyear(), fields.weekOfWeekyear, cutover, true);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[85]++;
        }

        // These fields are special because they have imprecise durations. The
        // family of addition methods need special attention. Override affected
        // duration fields as well.
        {
            fields.year = new ImpreciseCutoverField(
                julian.year(), fields.year, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[86]++;
            fields.years = fields.year.getDurationField();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[87]++;
            fields.yearOfEra = new ImpreciseCutoverField(
                julian.yearOfEra(), fields.yearOfEra, fields.years, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[88]++;
            fields.yearOfCentury = new ImpreciseCutoverField(
                julian.yearOfCentury(), fields.yearOfCentury, fields.years, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[89]++;
            
            fields.centuryOfEra = new ImpreciseCutoverField(
                julian.centuryOfEra(), fields.centuryOfEra, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[90]++;
            fields.centuries = fields.centuryOfEra.getDurationField();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[91]++;
            
            fields.monthOfYear = new ImpreciseCutoverField(
                julian.monthOfYear(), fields.monthOfYear, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[92]++;
            fields.months = fields.monthOfYear.getDurationField();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[93]++;
            
            fields.weekyear = new ImpreciseCutoverField(
                julian.weekyear(), fields.weekyear, null, iCutoverMillis, true);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[94]++;
            fields.weekyearOfCentury = new ImpreciseCutoverField(
                julian.weekyearOfCentury(), fields.weekyearOfCentury, fields.weekyears, iCutoverMillis);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[95]++;
            fields.weekyears = fields.weekyear.getDurationField();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[96]++;
        }

        // These fields require basic cutover support, except they must link to
        // imprecise durations.
        {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[97]++;
            CutoverField cf = new CutoverField
                (julian.dayOfMonth(), fields.dayOfMonth, iCutoverMillis);
            cf.iRangeDurationField = fields.months;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[98]++;
            fields.dayOfMonth = cf;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[99]++;
        }
    }

    long julianToGregorianByYear(long instant) {
        return convertByYear(instant, iJulianChronology, iGregorianChronology);
    }

    long gregorianToJulianByYear(long instant) {
        return convertByYear(instant, iGregorianChronology, iJulianChronology);
    }

    long julianToGregorianByWeekyear(long instant) {
        return convertByWeekyear(instant, iJulianChronology, iGregorianChronology);
    }

    long gregorianToJulianByWeekyear(long instant) {
        return convertByWeekyear(instant, iGregorianChronology, iJulianChronology);
    }

    //-----------------------------------------------------------------------
    /**
     * This basic cutover field adjusts calls to 'get' and 'set' methods, and
     * assumes that calls to add and addWrapField are unaffected by the cutover.
     */
    private class CutoverField extends BaseDateTimeField {
        private static final long serialVersionUID = 3528501219481026402L;

        final DateTimeField iJulianField;
        final DateTimeField iGregorianField;
        final long iCutover;
        final boolean iConvertByWeekyear;

        protected DurationField iDurationField;
        protected DurationField iRangeDurationField;

        /**
         * @param julianField field from the chronology used before the cutover instant
         * @param gregorianField field from the chronology used at and after the cutover
         * @param cutoverMillis  the millis of the cutover
         */
        CutoverField(DateTimeField julianField, DateTimeField gregorianField, long cutoverMillis) {
            this(julianField, gregorianField, cutoverMillis, false);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[100]++;
        }

        /**
         * @param julianField field from the chronology used before the cutover instant
         * @param gregorianField field from the chronology used at and after the cutover
         * @param cutoverMillis  the millis of the cutover
         * @param convertByWeekyear
         */
        CutoverField(DateTimeField julianField, DateTimeField gregorianField,
                     long cutoverMillis, boolean convertByWeekyear) {
            super(gregorianField.getType());
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[101]++;
            iJulianField = julianField;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[102]++;
            iGregorianField = gregorianField;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[103]++;
            iCutover = cutoverMillis;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[104]++;
            iConvertByWeekyear = convertByWeekyear;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[105]++;
            // Although average length of Julian and Gregorian years differ,
            // use the Gregorian duration field because it is more accurate.
            iDurationField = gregorianField.getDurationField();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[106]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[107]++;

            DurationField rangeField = gregorianField.getRangeDurationField();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[108]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((rangeField == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[41]++;
                rangeField = julianField.getRangeDurationField();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[109]++;

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[42]++;}
            iRangeDurationField = rangeField;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[110]++;
        }

        public boolean isLenient() {
            return false;
        }

        public int get(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[111]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[43]++;
                return iGregorianField.get(instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[44]++;
                return iJulianField.get(instant);
            }
        }

        public String getAsText(long instant, Locale locale) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[112]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[45]++;
                return iGregorianField.getAsText(instant, locale);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[46]++;
                return iJulianField.getAsText(instant, locale);
            }
        }

        public String getAsText(int fieldValue, Locale locale) {
            return iGregorianField.getAsText(fieldValue, locale);
        }

        public String getAsShortText(long instant, Locale locale) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[113]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[47]++;
                return iGregorianField.getAsShortText(instant, locale);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[48]++;
                return iJulianField.getAsShortText(instant, locale);
            }
        }

        public String getAsShortText(int fieldValue, Locale locale) {
            return iGregorianField.getAsShortText(fieldValue, locale);
        }

        public long add(long instant, int value) {
            return iGregorianField.add(instant, value);
        }

        public long add(long instant, long value) {
            return iGregorianField.add(instant, value);
        }

        public int[] add(ReadablePartial partial, int fieldIndex, int[] values, int valueToAdd) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[114]++;
int CodeCoverConditionCoverageHelper_C26;
            // overridden as superclass algorithm can't handle
            // 2004-02-29 + 48 months -> 2008-02-29 type dates
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((valueToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[49]++;
                return values;

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[50]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[115]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((DateTimeUtils.isContiguous(partial)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[51]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[116]++;
                long instant = 0L;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[117]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[4]++;


int CodeCoverConditionCoverageHelper_C28;
                for (int i = 0, isize = partial.size();(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[4]--;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[5]--;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[6]++;
}
                    instant = partial.getFieldType(i).getField(GJChronology.this).set(instant, values[i]);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[118]++;
                }
                instant = add(instant, valueToAdd);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[119]++;
                return GJChronology.this.get(partial, instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[52]++;
                return super.add(partial, fieldIndex, values, valueToAdd);
            }
        }

        public int getDifference(long minuendInstant, long subtrahendInstant) {
            return iGregorianField.getDifference(minuendInstant, subtrahendInstant);
        }

        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            return iGregorianField.getDifferenceAsLong(minuendInstant, subtrahendInstant);
        }

        public long set(long instant, int value) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[120]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[53]++;
                instant = iGregorianField.set(instant, value);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[121]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[122]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((instant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[55]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[123]++;
int CodeCoverConditionCoverageHelper_C31;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((instant + iGapDuration < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[57]++;
                        instant = gregorianToJulian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[124]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[58]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[125]++;
int CodeCoverConditionCoverageHelper_C32;
                    // Verify that new value stuck.
                    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((get(instant) != value) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[59]++;
                        throw new IllegalFieldValueException
                            (iGregorianField.getType(), Integer.valueOf(value), null, null);

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[60]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[56]++;}

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[54]++;
                instant = iJulianField.set(instant, value);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[126]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[127]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[61]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[128]++;
int CodeCoverConditionCoverageHelper_C34;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((instant - iGapDuration >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[63]++;
                        instant = julianToGregorian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[129]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[64]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[130]++;
int CodeCoverConditionCoverageHelper_C35;
                    // Verify that new value stuck.
                    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((get(instant) != value) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[65]++;
                       throw new IllegalFieldValueException
                            (iJulianField.getType(), Integer.valueOf(value), null, null);

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[66]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[62]++;}
            }
            return instant;
        }

        public long set(long instant, String text, Locale locale) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[131]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[67]++;
                instant = iGregorianField.set(instant, text, locale);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[132]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[133]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((instant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[69]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[134]++;
int CodeCoverConditionCoverageHelper_C38;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((instant + iGapDuration < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[71]++;
                        instant = gregorianToJulian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[135]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[72]++;}

                    // Cannot verify that new value stuck because set may be lenient.
                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[70]++;}

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[68]++;
                instant = iJulianField.set(instant, text, locale);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[136]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[137]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[73]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[138]++;
int CodeCoverConditionCoverageHelper_C40;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((instant - iGapDuration >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[75]++;
                        instant = julianToGregorian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[139]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[76]++;}

                    // Cannot verify that new value stuck because set may be lenient.
                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[74]++;}
            }
            return instant;
        }

        public DurationField getDurationField() {
            return iDurationField;
        }

        public DurationField getRangeDurationField() {
            return iRangeDurationField;
        }

        public boolean isLeap(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[140]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[77]++;
                return iGregorianField.isLeap(instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[78]++;
                return iJulianField.isLeap(instant);
            }
        }

        public int getLeapAmount(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[141]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[79]++;
                return iGregorianField.getLeapAmount(instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[80]++;
                return iJulianField.getLeapAmount(instant);
            }
        }

        public DurationField getLeapDurationField() {
            return iGregorianField.getLeapDurationField();
        }


        public int getMinimumValue() {
            // For all precise fields, the Julian and Gregorian limits are
            // identical. Choose Julian to tighten up the year limits.
            return iJulianField.getMinimumValue();
        }

        public int getMinimumValue(ReadablePartial partial) {
            return iJulianField.getMinimumValue(partial);
        }

        public int getMinimumValue(ReadablePartial partial, int[] values) {
            return iJulianField.getMinimumValue(partial, values);
        }

        public int getMinimumValue(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[142]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((instant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[81]++;
                return iJulianField.getMinimumValue(instant);

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[82]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[143]++;

            int min = iGregorianField.getMinimumValue(instant);

            // Because the cutover may reduce the length of this field, verify
            // the minimum by setting it.
            instant = iGregorianField.set(instant, min);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[144]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[145]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((instant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[83]++;
                min = iGregorianField.get(iCutover);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[146]++;

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[84]++;}

            return min;
        }

        public int getMaximumValue() {
            // For all precise fields, the Julian and Gregorian limits are
            // identical.
            return iGregorianField.getMaximumValue();
        }

        public int getMaximumValue(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[147]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[85]++;
                return iGregorianField.getMaximumValue(instant);

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[86]++;}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[148]++;

            int max = iJulianField.getMaximumValue(instant);

            // Because the cutover may reduce the length of this field, verify
            // the maximum by setting it.
            instant = iJulianField.set(instant, max);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[149]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[150]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[87]++;
                max = iJulianField.get(iJulianField.add(iCutover, -1));
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[151]++;

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[88]++;}

            return max;
        }

        public int getMaximumValue(ReadablePartial partial) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[152]++;
            long instant = GJChronology.getInstanceUTC().set(partial, 0L);
            return getMaximumValue(instant);
        }

        public int getMaximumValue(ReadablePartial partial, int[] values) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[153]++;
            Chronology chrono = GJChronology.getInstanceUTC();
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[154]++;
            long instant = 0L;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[155]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[7]++;


int CodeCoverConditionCoverageHelper_C47;
            for (int i = 0, isize = partial.size();(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[7]--;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[8]--;
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.loops[9]++;
}
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[156]++;
                DateTimeField field = partial.getFieldType(i).getField(chrono);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[157]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((values[i] <= field.getMaximumValue(instant)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[89]++;
                    instant = field.set(instant, values[i]);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[158]++;

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[90]++;}
            }
            return getMaximumValue(instant);
        }

        public long roundFloor(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[159]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[91]++;
                instant = iGregorianField.roundFloor(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[160]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[161]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((instant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[93]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[162]++;
int CodeCoverConditionCoverageHelper_C51;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((instant + iGapDuration < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[95]++;
                        instant = gregorianToJulian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[163]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[96]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[94]++;}

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[92]++;
                instant = iJulianField.roundFloor(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[164]++;
            }
            return instant;
        }

        public long roundCeiling(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[165]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[97]++;
                instant = iGregorianField.roundCeiling(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[166]++;

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[98]++;
                instant = iJulianField.roundCeiling(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[167]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[168]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[99]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[169]++;
int CodeCoverConditionCoverageHelper_C54;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((instant - iGapDuration >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[101]++;
                        instant = julianToGregorian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[170]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[102]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[100]++;}
            }
            return instant;
        }

        public int getMaximumTextLength(Locale locale) {
            return Math.max(iJulianField.getMaximumTextLength(locale),
                            iGregorianField.getMaximumTextLength(locale));
        }

        public int getMaximumShortTextLength(Locale locale) {
            return Math.max(iJulianField.getMaximumShortTextLength(locale),
                            iGregorianField.getMaximumShortTextLength(locale));
        }

        protected long julianToGregorian(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[171]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((iConvertByWeekyear) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[103]++;
                return julianToGregorianByWeekyear(instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[104]++;
                return julianToGregorianByYear(instant);
            }
        }

        protected long gregorianToJulian(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[172]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((iConvertByWeekyear) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[105]++;
                return gregorianToJulianByWeekyear(instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[106]++;
                return gregorianToJulianByYear(instant);
            }
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Cutover field for variable length fields. These fields internally call
     * set whenever add is called. As a result, the same correction applied to
     * set must be applied to add and addWrapField. Knowing when to use this
     * field requires specific knowledge of how the GJ fields are implemented.
     */
    private final class ImpreciseCutoverField extends CutoverField {
        private static final long serialVersionUID = 3410248757173576441L;

        /**
         * Creates a duration field that links back to this.
         */
        ImpreciseCutoverField(DateTimeField julianField, DateTimeField gregorianField, long cutoverMillis) {
            this(julianField, gregorianField, null, cutoverMillis, false);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[173]++;
        }

        /**
         * Uses a shared duration field rather than creating a new one.
         *
         * @param durationField shared duration field
         */
        ImpreciseCutoverField(DateTimeField julianField, DateTimeField gregorianField,
                              DurationField durationField, long cutoverMillis)
        {
            this(julianField, gregorianField, durationField, cutoverMillis, false);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[174]++;
        }

        /**
         * Uses a shared duration field rather than creating a new one.
         *
         * @param durationField shared duration field
         */
        ImpreciseCutoverField(DateTimeField julianField, DateTimeField gregorianField,
                              DurationField durationField,
                              long cutoverMillis, boolean convertByWeekyear)
        {
            super(julianField, gregorianField, cutoverMillis, convertByWeekyear);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[175]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[176]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((durationField == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[107]++;
                durationField = new LinkedDurationField(iDurationField, this);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[177]++;

            } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[108]++;}
            iDurationField = durationField;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[178]++;
        }

        public long add(long instant, int value) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[179]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[109]++;
                instant = iGregorianField.add(instant, value);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[180]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[181]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((instant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[111]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[182]++;
int CodeCoverConditionCoverageHelper_C60;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((instant + iGapDuration < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[113]++;
                        instant = gregorianToJulian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[183]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[114]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[112]++;}

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[110]++;
                instant = iJulianField.add(instant, value);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[184]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[185]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[115]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[186]++;
int CodeCoverConditionCoverageHelper_C62;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((instant - iGapDuration >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[117]++;
                        instant = julianToGregorian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[187]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[118]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[116]++;}
            }
            return instant;
        }
        
        public long add(long instant, long value) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[188]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[119]++;
                instant = iGregorianField.add(instant, value);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[189]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[190]++;
int CodeCoverConditionCoverageHelper_C64;
                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((instant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[121]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[191]++;
int CodeCoverConditionCoverageHelper_C65;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((instant + iGapDuration < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[123]++;
                        instant = gregorianToJulian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[192]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[124]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[122]++;}

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[120]++;
                instant = iJulianField.add(instant, value);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[193]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[194]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[125]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[195]++;
int CodeCoverConditionCoverageHelper_C67;
                    // Only adjust if gap fully crossed.
                    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((instant - iGapDuration >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[127]++;
                        instant = julianToGregorian(instant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[196]++;

                    } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[128]++;}

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[126]++;}
            }
            return instant;
        }

        public int getDifference(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[197]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((minuendInstant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[129]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[198]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((subtrahendInstant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[131]++;
                    return iGregorianField.getDifference(minuendInstant, subtrahendInstant);

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[132]++;}
                // Remember, the add is being reversed. Since subtrahend is
                // Julian, convert minuend to Julian to match.
                minuendInstant = gregorianToJulian(minuendInstant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[199]++;
                return iJulianField.getDifference(minuendInstant, subtrahendInstant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[130]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[200]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((subtrahendInstant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[133]++;
                    return iJulianField.getDifference(minuendInstant, subtrahendInstant);

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[134]++;}
                // Remember, the add is being reversed. Since subtrahend is
                // Gregorian, convert minuend to Gregorian to match.
                minuendInstant = julianToGregorian(minuendInstant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[201]++;
                return iGregorianField.getDifference(minuendInstant, subtrahendInstant);
            }
        }

        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[202]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((minuendInstant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[135]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[203]++;
int CodeCoverConditionCoverageHelper_C72;
                if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((subtrahendInstant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[137]++;
                    return iGregorianField.getDifferenceAsLong(minuendInstant, subtrahendInstant);

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[138]++;}
                // Remember, the add is being reversed. Since subtrahend is
                // Julian, convert minuend to Julian to match.
                minuendInstant = gregorianToJulian(minuendInstant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[204]++;
                return iJulianField.getDifferenceAsLong(minuendInstant, subtrahendInstant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[136]++;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[205]++;
int CodeCoverConditionCoverageHelper_C73;
                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((subtrahendInstant < iCutover) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[139]++;
                    return iJulianField.getDifferenceAsLong(minuendInstant, subtrahendInstant);

                } else {
  CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[140]++;}
                // Remember, the add is being reversed. Since subtrahend is
                // Gregorian, convert minuend to Gregorian to match.
                minuendInstant = julianToGregorian(minuendInstant);
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[206]++;
                return iGregorianField.getDifferenceAsLong(minuendInstant, subtrahendInstant);
            }
        }

        // Since the imprecise fields have durations longer than the gap
        // duration, keep these methods simple. The inherited implementations
        // produce incorrect results.
        //
        // Degenerate case: If this field is a month, and the cutover is set
        // far into the future, then the gap duration may be so large as to
        // reduce the number of months in a year. If the missing month(s) are
        // at the beginning or end of the year, then the minimum and maximum
        // values are not 1 and 12. I don't expect this case to ever occur.

        public int getMinimumValue(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[207]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[141]++;
                return iGregorianField.getMinimumValue(instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[142]++;
                return iJulianField.getMinimumValue(instant);
            }
        }

        public int getMaximumValue(long instant) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[208]++;
int CodeCoverConditionCoverageHelper_C75;
            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((instant >= iCutover) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[143]++;
                return iGregorianField.getMaximumValue(instant);

            } else {
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.branches[144]++;
                return iJulianField.getMaximumValue(instant);
            }
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Links the duration back to a ImpreciseCutoverField.
     */
    private static class LinkedDurationField extends DecoratedDurationField {
        private static final long serialVersionUID = 4097975388007713084L;

        private final ImpreciseCutoverField iField;

        LinkedDurationField(DurationField durationField, ImpreciseCutoverField dateTimeField) {
            super(durationField, durationField.getType());
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[209]++;
            iField = dateTimeField;
CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1.statements[210]++;
        }

        public long add(long instant, int value) {
            return iField.add(instant, value);
        }

        public long add(long instant, long value) {
            return iField.add(instant, value);
        }

        public int getDifference(long minuendInstant, long subtrahendInstant) {
            return iField.getDifference(minuendInstant, subtrahendInstant);
        }

        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            return iField.getDifferenceAsLong(minuendInstant, subtrahendInstant);
        }
    }

}

class CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1 ());
  }
    public static long[] statements = new long[211];
    public static long[] branches = new long[145];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[76];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.GJChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 75; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$u0gjugmc9sgb4m6m0b06yqxnc1 () {
    super("org.joda.time.chrono.GJChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 210; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 144; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 75; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.GJChronology.java");
      for (int i = 1; i <= 210; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 144; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 75; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

