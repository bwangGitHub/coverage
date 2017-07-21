/*
 *  Copyright 2001-2011 Stephen Colebourne
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
package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PartialConverter;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * LocalTime is an immutable time class representing a time
 * without a time zone.
 * <p>
 * LocalTime implements the {@link ReadablePartial} interface.
 * To do this, the interface methods focus on the key fields -
 * HourOfDay, MinuteOfHour, SecondOfMinute and MillisOfSecond.
 * However, <b>all</b> time fields may in fact be queried.
 * <p>
 * Calculations on LocalTime are performed using a {@link Chronology}.
 * This chronology will be set internally to be in the UTC time zone
 * for all calculations.
 *
 * <p>Each individual field can be queried in two ways:
 * <ul>
 * <li><code>getHourOfDay()</code>
 * <li><code>hourOfDay().get()</code>
 * </ul>
 * The second technique also provides access to other useful methods on the
 * field:
 * <ul>
 * <li>numeric value
 * <li>text value
 * <li>short text value
 * <li>maximum/minimum values
 * <li>add/subtract
 * <li>set
 * <li>rounding
 * </ul>
 *
 * <p>
 * LocalTime is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.3
 */
public final class LocalTime
        extends BaseLocal
        implements ReadablePartial, Serializable {
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -12873158713873L;
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[1]++;
  }

    /** Constant for midnight. */
    public static final LocalTime MIDNIGHT = new LocalTime(0, 0, 0, 0);
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[2]++;
  }

    /** The index of the hourOfDay field in the field array */
    private static final int HOUR_OF_DAY = 0;
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[3]++;
  }
    /** The index of the minuteOfHour field in the field array */
    private static final int MINUTE_OF_HOUR = 1;
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[4]++;
  }
    /** The index of the secondOfMinute field in the field array */
    private static final int SECOND_OF_MINUTE = 2;
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[5]++;
  }
    /** The index of the millisOfSecond field in the field array */
    private static final int MILLIS_OF_SECOND = 3;
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[6]++;
  }
    /** Set of known duration types. */
    private static final Set<DurationFieldType> TIME_DURATION_TYPES = new HashSet<DurationFieldType>();
  static {
    CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[7]++;
  }
    static {
        TIME_DURATION_TYPES.add(DurationFieldType.millis());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[8]++;
        TIME_DURATION_TYPES.add(DurationFieldType.seconds());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[9]++;
        TIME_DURATION_TYPES.add(DurationFieldType.minutes());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[10]++;
        TIME_DURATION_TYPES.add(DurationFieldType.hours());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[11]++;
    }

    /** The local millis from 1970-01-01T00:00:00 */
    private final long iLocalMillis;
    /** The chronology to use, in UTC */
    private final Chronology iChronology;

    //-----------------------------------------------------------------------
    /**
     * Obtains a {@code LocalTime} set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * The resulting object does not use the zone.
     * 
     * @return the current time, not null
     * @since 2.0
     */
    public static LocalTime now() {
        return new LocalTime();
    }

    /**
     * Obtains a {@code LocalTime} set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * The resulting object does not use the zone.
     *
     * @param zone  the time zone, not null
     * @return the current time, not null
     * @since 2.0
     */
    public static LocalTime now(DateTimeZone zone) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[1]++;
            throw new NullPointerException("Zone must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[2]++;}
        return new LocalTime(zone);
    }

    /**
     * Obtains a {@code LocalTime} set to the current system millisecond time
     * using the specified chronology.
     * The resulting object does not use the zone.
     *
     * @param chronology  the chronology, not null
     * @return the current time, not null
     * @since 2.0
     */
    public static LocalTime now(Chronology chronology) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronology == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[3]++;
            throw new NullPointerException("Chronology must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[4]++;}
        return new LocalTime(chronology);
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code LocalTime} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#localTimeParser()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static LocalTime parse(String str) {
        return parse(str, ISODateTimeFormat.localTimeParser());
    }

    /**
     * Parses a {@code LocalTime} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static LocalTime parse(String str, DateTimeFormatter formatter) {
        return formatter.parseLocalTime(str);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a LocalTime from the specified millis of day using the
     * ISO chronology.
     * <p>
     * The millisOfDay value may exceed the number of millis in one day,
     * but additional days will be ignored.
     * This method uses the UTC time zone internally.
     *
     * @param millisOfDay  the number of milliseconds into a day to convert
     */
    public static LocalTime fromMillisOfDay(long millisOfDay) {
        return fromMillisOfDay(millisOfDay, null);
    }

    /**
     * Constructs a LocalTime from the specified millis of day using the
     * specified chronology.
     * <p>
     * The millisOfDay value may exceed the number of millis in one day,
     * but additional days will be ignored.
     * This method uses the UTC time zone internally.
     *
     * @param millisOfDay  the number of milliseconds into a day to convert
     * @param chrono  the chronology, null means ISO chronology
     */
    public static LocalTime fromMillisOfDay(long millisOfDay, Chronology chrono) {
        chrono = DateTimeUtils.getChronology(chrono).withUTC();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[14]++;
        return new LocalTime(millisOfDay, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a LocalTime from a <code>java.util.Calendar</code>
     * using exactly the same field values.
     * <p>
     * Each field is queried from the Calendar and assigned to the LocalTime.
     * This is useful if you have been using the Calendar as a local time,
     * ignoring the zone.
     * <p>
     * One advantage of this method is that this method is unaffected if the
     * version of the time zone data differs between the JDK and Joda-Time.
     * That is because the local field values are transferred, calculated using
     * the JDK time zone data and without using the Joda-Time time zone data.
     * <p>
     * This factory method ignores the type of the calendar and always
     * creates a LocalTime with ISO chronology. It is expected that you
     * will only pass in instances of <code>GregorianCalendar</code> however
     * this is not validated.
     *
     * @param calendar  the Calendar to extract fields from
     * @return the created LocalTime
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the date is invalid for the ISO chronology
     */
    public static LocalTime fromCalendarFields(Calendar calendar) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((calendar == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[5]++;
            throw new IllegalArgumentException("The calendar must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[6]++;}
        return new LocalTime(
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND),
            calendar.get(Calendar.MILLISECOND)
        );
    }

    /**
     * Constructs a LocalTime from a <code>java.util.Date</code>
     * using exactly the same field values.
     * <p>
     * Each field is queried from the Date and assigned to the LocalTime.
     * This is useful if you have been using the Date as a local time,
     * ignoring the zone.
     * <p>
     * One advantage of this method is that this method is unaffected if the
     * version of the time zone data differs between the JDK and Joda-Time.
     * That is because the local field values are transferred, calculated using
     * the JDK time zone data and without using the Joda-Time time zone data.
     * <p>
     * This factory method always creates a LocalTime with ISO chronology.
     *
     * @param date  the Date to extract fields from
     * @return the created LocalTime
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the date is invalid for the ISO chronology
     */
    @SuppressWarnings("deprecation")
    public static LocalTime fromDateFields(Date date) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[7]++;
            throw new IllegalArgumentException("The date must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[8]++;}
        return new LocalTime(
            date.getHours(),
            date.getMinutes(),
            date.getSeconds(),
            (((int) (date.getTime() % 1000)) + 1000) % 1000
        );
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the current local time evaluated using
     * ISO chronology in the default zone.
     * <p>
     * Once the constructor is completed, the zone is no longer used.
     * 
     * @see #now()
     */
    public LocalTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[17]++;
    }

    /**
     * Constructs an instance set to the current local time evaluated using
     * ISO chronology in the specified zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     * Once the constructor is completed, the zone is no longer used.
     *
     * @param zone  the time zone, null means default zone
     * @see #now(DateTimeZone)
     */
    public LocalTime(DateTimeZone zone) {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[18]++;
    }

    /**
     * Constructs an instance set to the current local time evaluated using
     * specified chronology and zone.
     * <p>
     * If the chronology is null, ISO chronology in the default time zone is used.
     * Once the constructor is completed, the zone is no longer used.
     *
     * @param chronology  the chronology, null means ISOChronology in default zone
     * @see #now(Chronology)
     */
    public LocalTime(Chronology chronology) {
        this(DateTimeUtils.currentTimeMillis(), chronology);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[19]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the local time defined by the specified
     * instant evaluated using ISO chronology in the default zone.
     * <p>
     * Once the constructor is completed, the zone is no longer used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public LocalTime(long instant) {
        this(instant, ISOChronology.getInstance());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[20]++;
    }

    /**
     * Constructs an instance set to the local time defined by the specified
     * instant evaluated using ISO chronology in the specified zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     * Once the constructor is completed, the zone is no longer used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param zone  the time zone, null means default zone
     */
    public LocalTime(long instant, DateTimeZone zone) {
        this(instant, ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[21]++;
    }

    /**
     * Constructs an instance set to the local time defined by the specified
     * instant evaluated using the specified chronology.
     * <p>
     * If the chronology is null, ISO chronology in the default zone is used.
     * Once the constructor is completed, the zone is no longer used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public LocalTime(long instant, Chronology chronology) {
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[22]++;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[23]++;
        
        long localMillis = chronology.getZone().getMillisKeepLocal(DateTimeZone.UTC, instant);
        chronology = chronology.withUTC();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[24]++;
        iLocalMillis = chronology.millisOfDay().get(localMillis);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[25]++;
        iChronology = chronology;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[26]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from an Object that represents a datetime.
     * <p>
     * If the object contains no chronology, <code>ISOChronology</code> is used.
     * If the object contains no time zone, the default zone is used.
     * Once the constructor is completed, the zone is no longer used.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadablePartial, ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#localTimeParser()}.
     * The default String converter ignores the zone and only parses the field values.
     *
     * @param instant  the datetime object
     * @throws IllegalArgumentException if the instant is invalid
     */
    public LocalTime(Object instant) {
        this(instant, (Chronology) null);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[27]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime,
     * forcing the time zone to that specified.
     * <p>
     * If the object contains no chronology, <code>ISOChronology</code> is used.
     * If the specified time zone is null, the default zone is used.
     * Once the constructor is completed, the zone is no longer used.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadablePartial, ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#localTimeParser()}.
     * The default String converter ignores the zone and only parses the field values.
     *
     * @param instant  the datetime object
     * @param zone  the time zone
     * @throws IllegalArgumentException if the instant is invalid
     */
    public LocalTime(Object instant, DateTimeZone zone) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[28]++;
        PartialConverter converter = ConverterManager.getInstance().getPartialConverter(instant);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[29]++;
        Chronology chronology = converter.getChronology(instant, zone);
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[30]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[31]++;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[32]++;
        int[] values = converter.getPartialValues(this, instant, chronology, ISODateTimeFormat.localTimeParser());
        iLocalMillis = iChronology.getDateTimeMillis(0L, values[0], values[1], values[2], values[3]);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[33]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime,
     * using the specified chronology.
     * <p>
     * If the chronology is null, ISO in the default time zone is used.
     * Once the constructor is completed, the zone is no longer used.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadablePartial, ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#localTimeParser()}.
     * The default String converter ignores the zone and only parses the field values.
     *
     * @param instant  the datetime object
     * @param chronology  the chronology
     * @throws IllegalArgumentException if the instant is invalid
     */
    public LocalTime(Object instant, Chronology chronology) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[34]++;
        PartialConverter converter = ConverterManager.getInstance().getPartialConverter(instant);
        chronology = converter.getChronology(instant, chronology);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[35]++;
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[36]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[37]++;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[38]++;
        int[] values = converter.getPartialValues(this, instant, chronology, ISODateTimeFormat.localTimeParser());
        iLocalMillis = iChronology.getDateTimeMillis(0L, values[0], values[1], values[2], values[3]);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[39]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the specified time
     * using <code>ISOChronology</code>.
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     */
    public LocalTime(
            int hourOfDay,
            int minuteOfHour) {
        this(hourOfDay, minuteOfHour, 0, 0, ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[40]++;
    }

    /**
     * Constructs an instance set to the specified time
     * using <code>ISOChronology</code>.
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     */
    public LocalTime(
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute) {
        this(hourOfDay, minuteOfHour, secondOfMinute, 0, ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[41]++;
    }

    /**
     * Constructs an instance set to the specified time
     * using <code>ISOChronology</code>.
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     */
    public LocalTime(
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {
        this(hourOfDay, minuteOfHour, secondOfMinute,
                millisOfSecond, ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[42]++;
    }

    /**
     * Constructs an instance set to the specified time
     * using the specified chronology, whose zone is ignored.
     * <p>
     * If the chronology is null, <code>ISOChronology</code> is used.
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public LocalTime(
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond,
            Chronology chronology) {
        super();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[43]++;
        chronology = DateTimeUtils.getChronology(chronology).withUTC();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[44]++;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[45]++;
        long instant = chronology.getDateTimeMillis(
            0L, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
        iChronology = chronology;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[46]++;
        iLocalMillis = instant;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[47]++;
    }

    /**
     * Handle broken serialization from other tools.
     * @return the resolved object, not null
     */
    private Object readResolve() {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[48]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((DateTimeZone.UTC.equals(iChronology.getZone()) == false) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[9]++;
            return new LocalTime(iLocalMillis, iChronology.withUTC());

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[10]++;}
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields in this partial, which is four.
     * The supported fields are HourOfDay, MinuteOfHour, SecondOfMinute
     * and MillisOfSecond.
     *
     * @return the field count, four
     */
    public int size() {
        return 4;
    }

    /**
     * Gets the field for a specific index in the chronology specified.
     * <p>
     * This method must not use any instance variables.
     *
     * @param index  the index to retrieve
     * @param chrono  the chronology to use
     * @return the field
     */
    protected DateTimeField getField(int index, Chronology chrono) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[49]++;
        switch (index) {
            case HOUR_OF_DAY:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[11]++;
                return chrono.hourOfDay();
            case MINUTE_OF_HOUR:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[12]++;
                return chrono.minuteOfHour();
            case SECOND_OF_MINUTE:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[13]++;
                return chrono.secondOfMinute();
            case MILLIS_OF_SECOND:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[14]++;
                return chrono.millisOfSecond();
            default:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[15]++;
                throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    /**
     * Gets the value of the field at the specifed index.
     * <p>
     * This method is required to support the <code>ReadablePartial</code>
     * interface. The supported fields are HourOfDay, MinuteOfHour,
     * SecondOfMinute and MillisOfSecond.
     *
     * @param index  the index, zero to three
     * @return the value
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public int getValue(int index) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[50]++;
        switch (index) {
            case HOUR_OF_DAY:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[16]++;
                return getChronology().hourOfDay().get(getLocalMillis());
            case MINUTE_OF_HOUR:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[17]++;
                return getChronology().minuteOfHour().get(getLocalMillis());
            case SECOND_OF_MINUTE:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[18]++;
                return getChronology().secondOfMinute().get(getLocalMillis());
            case MILLIS_OF_SECOND:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[19]++;
                return getChronology().millisOfSecond().get(getLocalMillis());
            default:
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[20]++;
                throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Get the value of one of the fields of time.
     * <p>
     * This method gets the value of the specified field.
     * For example:
     * <pre>
     * DateTime dt = new DateTime();
     * int hourOfDay = dt.get(DateTimeFieldType.hourOfDay());
     * </pre>
     *
     * @param fieldType  a field type, usually obtained from DateTimeFieldType, not null
     * @return the value of that field
     * @throws IllegalArgumentException if the field type is null
     */
    public int get(DateTimeFieldType fieldType) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[51]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[21]++;
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[22]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[52]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isSupported(fieldType) == false) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[23]++;
            throw new IllegalArgumentException("Field '" + fieldType + "' is not supported");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[24]++;}
        return fieldType.getField(getChronology()).get(getLocalMillis());
    }

    /**
     * Checks if the field type specified is supported by this
     * local time and chronology.
     * This can be used to avoid exceptions in {@link #get(DateTimeFieldType)}.
     *
     * @param type  a field type, usually obtained from DateTimeFieldType
     * @return true if the field type is supported
     */
    public boolean isSupported(DateTimeFieldType type) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[53]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[26]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isSupported(type.getDurationType()) == false) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[28]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[55]++;
        DurationFieldType range = type.getRangeDurationType();
        return (isSupported(range) || range == DurationFieldType.days());
    }

    /**
     * Checks if the duration type specified is supported by this
     * local time and chronology.
     *
     * @param type  a duration type, usually obtained from DurationFieldType
     * @return true if the field type is supported
     */
    public boolean isSupported(DurationFieldType type) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[56]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[30]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[57]++;
        DurationField field = type.getField(getChronology());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[58]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((TIME_DURATION_TYPES.contains(type)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((field.getUnitMillis() < getChronology().days().getUnitMillis()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[31]++;
            return field.isSupported();

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[32]++;}
        return false;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the local milliseconds from the Java epoch
     * of 1970-01-01T00:00:00 (not fixed to any specific time zone).
     * 
     * @return the number of milliseconds since 1970-01-01T00:00:00
     * @since 1.5 (previously private)
     */
    protected long getLocalMillis() {
        return iLocalMillis;
    }

    /**
     * Gets the chronology of the time.
     * 
     * @return the Chronology that the time is using
     */
    public Chronology getChronology() {
        return iChronology;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this ReadablePartial with another returning true if the chronology,
     * field types and values are equal.
     *
     * @param partial  an object to check against
     * @return true if fields and values are equal
     */
    public boolean equals(Object partial) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[59]++;
int CodeCoverConditionCoverageHelper_C12;
        // override to perform faster
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this == partial) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[33]++;
            return true;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[34]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[60]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((partial instanceof LocalTime) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[35]++;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[61]++;
            LocalTime other = (LocalTime) partial;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[62]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((iChronology.equals(other.iChronology)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[37]++;
                return iLocalMillis == other.iLocalMillis;

            } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[38]++;}

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[36]++;}
        return super.equals(partial);
    }

    /**
     * Compares this partial with another returning an integer
     * indicating the order.
     * <p>
     * The fields are compared in order, from largest to smallest.
     * The first field that is non-equal is used to determine the result.
     * <p>
     * The specified object must be a partial instance whose field types
     * match those of this partial.
     *
     * @param partial  an object to check against
     * @return negative if this is less, zero if equal, positive if greater
     * @throws ClassCastException if the partial is the wrong class
     *  or if it has field types that don't match
     * @throws NullPointerException if the partial is null
     */
    public int compareTo(ReadablePartial partial) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
        // override to perform faster
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this == partial) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[39]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[40]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((partial instanceof LocalTime) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[41]++;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[65]++;
            LocalTime other = (LocalTime) partial;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((iChronology.equals(other.iChronology)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[43]++;
                return (iLocalMillis < other.iLocalMillis ? -1 :
                            (iLocalMillis == other.iLocalMillis ? 0 : 1));


            } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[42]++;}
        return super.compareTo(partial);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time with different local millis.
     * <p>
     * The returned object will be a new instance of the same type.
     * Only the millis will change, the chronology is kept.
     * The returned object will be either be a new instance or <code>this</code>.
     *
     * @param newMillis  the new millis, from 1970-01-01T00:00:00
     * @return a copy of this time with different millis
     */
    LocalTime withLocalMillis(long newMillis) {
        return (newMillis == getLocalMillis() ? this : new LocalTime(newMillis, getChronology()));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time with the partial set of fields replacing
     * those from this instance.
     * <p>
     * For example, if the partial contains an hour and minute then those two
     * fields will be changed in the returned instance.
     * Unsupported fields are ignored.
     * If the partial is null, then <code>this</code> is returned.
     *
     * @param partial  the partial set of fields to apply to this time, null ignored
     * @return a copy of this time with a different set of fields
     * @throws IllegalArgumentException if any value is invalid
     */
    public LocalTime withFields(ReadablePartial partial) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[45]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[46]++;}
        return withLocalMillis(getChronology().set(partial, getLocalMillis()));
    }

    /**
     * Returns a copy of this time with the specified field set
     * to a new value.
     * <p>
     * For example, if the field type is <code>hourOfDay</code> then the hour of day
     * field would be changed in the returned instance.
     * If the field type is null, then <code>this</code> is returned.
     * <p>
     * These lines are equivalent:
     * <pre>
     * LocalTime updated = dt.withHourOfDay(6);
     * LocalTime updated = dt.withField(DateTimeFieldType.hourOfDay(), 6);
     * </pre>
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this time with the field set
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public LocalTime withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[47]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[48]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[69]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isSupported(fieldType) == false) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[49]++;
            throw new IllegalArgumentException("Field '" + fieldType + "' is not supported");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[50]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[70]++;
        long instant = fieldType.getField(getChronology()).set(getLocalMillis(), value);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this time with the value of the specified
     * field increased.
     * <p>
     * If the addition is zero or the field is null, then <code>this</code>
     * is returned.
     * <p>
     * If the addition causes the maximum value of the field to be exceeded,
     * then the value will wrap. Thus 23:59 plus two minutes yields 00:01.
     * <p>
     * These lines are equivalent:
     * <pre>
     * LocalTime added = dt.plusHours(6);
     * LocalTime added = dt.withFieldAdded(DurationFieldType.hours(), 6);
     * </pre>
     *
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this time with the field updated
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalTime withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[71]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[51]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[52]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[72]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isSupported(fieldType) == false) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[53]++;
            throw new IllegalArgumentException("Field '" + fieldType + "' is not supported");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[54]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[73]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[55]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[56]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[74]++;
        long instant = fieldType.getField(getChronology()).add(getLocalMillis(), amount);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time with the specified period added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * <p>
     * This method is typically used to add multiple copies of complex
     * period instances. Adding one field is best achieved using methods
     * like {@link #withFieldAdded(DurationFieldType, int)}
     * or {@link #plusHours(int)}.
     *
     * @param period  the period to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this time with the period added
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalTime withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[75]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[57]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[58]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[76]++;
        long instant = getChronology().add(period, getLocalMillis(), scalar);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time with the specified period added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to add complex period instances.
     * Adding one field is best achieved using methods
     * like {@link #plusHours(int)}.
     * 
     * @param period  the period to add to this one, null means zero
     * @return a copy of this time with the period added
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalTime plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time plus the specified number of hours.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime added = dt.plusHours(6);
     * LocalTime added = dt.plus(Period.hours(6));
     * LocalTime added = dt.withFieldAdded(DurationFieldType.hours(), 6);
     * </pre>
     *
     * @param hours  the amount of hours to add, may be negative
     * @return the new LocalTime plus the increased hours
     */
    public LocalTime plusHours(int hours) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[77]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[59]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[60]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[78]++;
        long instant = getChronology().hours().add(getLocalMillis(), hours);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this time plus the specified number of minutes.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime added = dt.plusMinutes(6);
     * LocalTime added = dt.plus(Period.minutes(6));
     * LocalTime added = dt.withFieldAdded(DurationFieldType.minutes(), 6);
     * </pre>
     *
     * @param minutes  the amount of minutes to add, may be negative
     * @return the new LocalTime plus the increased minutes
     */
    public LocalTime plusMinutes(int minutes) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[79]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((minutes == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[61]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[62]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[80]++;
        long instant = getChronology().minutes().add(getLocalMillis(), minutes);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this time plus the specified number of seconds.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime added = dt.plusSeconds(6);
     * LocalTime added = dt.plus(Period.seconds(6));
     * LocalTime added = dt.withFieldAdded(DurationFieldType.seconds(), 6);
     * </pre>
     *
     * @param seconds  the amount of seconds to add, may be negative
     * @return the new LocalTime plus the increased seconds
     */
    public LocalTime plusSeconds(int seconds) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[81]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[63]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[64]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[82]++;
        long instant = getChronology().seconds().add(getLocalMillis(), seconds);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this time plus the specified number of millis.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime added = dt.plusMillis(6);
     * LocalTime added = dt.plus(Period.millis(6));
     * LocalTime added = dt.withFieldAdded(DurationFieldType.millis(), 6);
     * </pre>
     *
     * @param millis  the amount of millis to add, may be negative
     * @return the new LocalTime plus the increased millis
     */
    public LocalTime plusMillis(int millis) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[83]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((millis == 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[65]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[66]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[84]++;
        long instant = getChronology().millis().add(getLocalMillis(), millis);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time with the specified period taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to subtract complex period instances.
     * Subtracting one field is best achieved using methods
     * like {@link #minusHours(int)}.
     * 
     * @param period  the period to reduce this instant by
     * @return a copy of this time with the period taken away
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalTime minus(ReadablePeriod period) {
        return withPeriodAdded(period, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time minus the specified number of hours.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime subtracted = dt.minusHours(6);
     * LocalTime subtracted = dt.minus(Period.hours(6));
     * LocalTime subtracted = dt.withFieldAdded(DurationFieldType.hours(), -6);
     * </pre>
     *
     * @param hours  the amount of hours to subtract, may be negative
     * @return the new LocalTime minus the increased hours
     */
    public LocalTime minusHours(int hours) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[85]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[67]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[68]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[86]++;
        long instant = getChronology().hours().subtract(getLocalMillis(), hours);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this time minus the specified number of minutes.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime subtracted = dt.minusMinutes(6);
     * LocalTime subtracted = dt.minus(Period.minutes(6));
     * LocalTime subtracted = dt.withFieldAdded(DurationFieldType.minutes(), -6);
     * </pre>
     *
     * @param minutes  the amount of minutes to subtract, may be negative
     * @return the new LocalTime minus the increased minutes
     */
    public LocalTime minusMinutes(int minutes) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[87]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((minutes == 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[69]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[70]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[88]++;
        long instant = getChronology().minutes().subtract(getLocalMillis(), minutes);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this time minus the specified number of seconds.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime subtracted = dt.minusSeconds(6);
     * LocalTime subtracted = dt.minus(Period.seconds(6));
     * LocalTime subtracted = dt.withFieldAdded(DurationFieldType.seconds(), -6);
     * </pre>
     *
     * @param seconds  the amount of seconds to subtract, may be negative
     * @return the new LocalTime minus the increased seconds
     */
    public LocalTime minusSeconds(int seconds) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[89]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[71]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[72]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[90]++;
        long instant = getChronology().seconds().subtract(getLocalMillis(), seconds);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this time minus the specified number of millis.
     * <p>
     * This LocalTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalTime subtracted = dt.minusMillis(6);
     * LocalTime subtracted = dt.minus(Period.millis(6));
     * LocalTime subtracted = dt.withFieldAdded(DurationFieldType.millis(), -6);
     * </pre>
     *
     * @param millis  the amount of millis to subtract, may be negative
     * @return the new LocalTime minus the increased millis
     */
    public LocalTime minusMillis(int millis) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[91]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((millis == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[73]++;
            return this;

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[74]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[92]++;
        long instant = getChronology().millis().subtract(getLocalMillis(), millis);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the property object for the specified type, which contains
     * many useful methods.
     *
     * @param fieldType  the field type to get the chronology for
     * @return the property object
     * @throws IllegalArgumentException if the field is null or unsupported
     */
    public Property property(DateTimeFieldType fieldType) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[93]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[75]++;
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[76]++;}
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[94]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isSupported(fieldType) == false) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[77]++;
            throw new IllegalArgumentException("Field '" + fieldType + "' is not supported");

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[78]++;}
        return new Property(this, fieldType.getField(getChronology()));
    }

    //-----------------------------------------------------------------------
    /**
     * Get the hour of day field value.
     *
     * @return the hour of day
     */
    public int getHourOfDay() {
        return getChronology().hourOfDay().get(getLocalMillis());
    }

    /**
     * Get the minute of hour field value.
     *
     * @return the minute of hour
     */
    public int getMinuteOfHour() {
        return getChronology().minuteOfHour().get(getLocalMillis());
    }

    /**
     * Get the second of minute field value.
     *
     * @return the second of minute
     */
    public int getSecondOfMinute() {
        return getChronology().secondOfMinute().get(getLocalMillis());
    }

    /**
     * Get the millis of second field value.
     *
     * @return the millis of second
     */
    public int getMillisOfSecond() {
        return getChronology().millisOfSecond().get(getLocalMillis());
    }

    /**
     * Get the millis of day field value.
     *
     * @return the millis of day
     */
    public int getMillisOfDay() {
        return getChronology().millisOfDay().get(getLocalMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this time with the hour of day field updated.
     * <p>
     * LocalTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * hour of day changed.
     *
     * @param hour  the hour of day to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalTime withHourOfDay(int hour) {
        return withLocalMillis(getChronology().hourOfDay().set(getLocalMillis(), hour));
    }

    /**
     * Returns a copy of this time with the minute of hour field updated.
     * <p>
     * LocalTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * minute of hour changed.
     *
     * @param minute  the minute of hour to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalTime withMinuteOfHour(int minute) {
        return withLocalMillis(getChronology().minuteOfHour().set(getLocalMillis(), minute));
    }

    /**
     * Returns a copy of this time with the second of minute field updated.
     * <p>
     * LocalTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * second of minute changed.
     *
     * @param second  the second of minute to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalTime withSecondOfMinute(int second) {
        return withLocalMillis(getChronology().secondOfMinute().set(getLocalMillis(), second));
    }

    /**
     * Returns a copy of this time with the millis of second field updated.
     * <p>
     * LocalTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * millis of second changed.
     *
     * @param millis  the millis of second to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalTime withMillisOfSecond(int millis) {
        return withLocalMillis(getChronology().millisOfSecond().set(getLocalMillis(), millis));
    }

    /**
     * Returns a copy of this time with the millis of day field updated.
     * <p>
     * LocalTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * millis of day changed.
     *
     * @param millis  the millis of day to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalTime withMillisOfDay(int millis) {
        return withLocalMillis(getChronology().millisOfDay().set(getLocalMillis(), millis));
    }

    //-----------------------------------------------------------------------
    /**
     * Get the hour of day field property which provides access to advanced functionality.
     * 
     * @return the hour of day property
     */
    public Property hourOfDay() {
        return new Property(this, getChronology().hourOfDay());
    }

    /**
     * Get the minute of hour field property which provides access to advanced functionality.
     * 
     * @return the minute of hour property
     */
    public Property minuteOfHour() {
        return new Property(this, getChronology().minuteOfHour());
    }

    /**
     * Get the second of minute field property which provides access to advanced functionality.
     * 
     * @return the second of minute property
     */
    public Property secondOfMinute() {
        return new Property(this, getChronology().secondOfMinute());
    }

    /**
     * Get the millis of second property which provides access to advanced functionality.
     * 
     * @return the millis of second property
     */
    public Property millisOfSecond() {
        return new Property(this, getChronology().millisOfSecond());
    }

    /**
     * Get the millis of day property which provides access to advanced functionality.
     * 
     * @return the millis of day property
     */
    public Property millisOfDay() {
        return new Property(this, getChronology().millisOfDay());
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this LocalTime to a full datetime using the default time zone
     * setting the time fields from this instance and the date fields from
     * the current date.
     *
     * @return this time as a datetime using todays date
     */
    public DateTime toDateTimeToday() {
        return toDateTimeToday(null);
    }

    /**
     * Converts this LocalTime to a full datetime using the specified time zone
     * setting the time fields from this instance and the date fields from
     * the current time.
     * <p>
     * This method uses the chronology from this instance plus the time zone
     * specified.
     *
     * @param zone  the zone to use, null means default
     * @return this time as a datetime using todays date
     */
    public DateTime toDateTimeToday(DateTimeZone zone) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[95]++;
        Chronology chrono = getChronology().withZone(zone);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[96]++;
        long instantMillis = DateTimeUtils.currentTimeMillis();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[97]++;
        long resolved = chrono.set(this, instantMillis);
        return new DateTime(resolved, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Output the time in ISO8601 format (HH:mm:ss.SSSZZ).
     * 
     * @return ISO8601 time formatted string.
     */
    @ToString
    public String toString() {
        return ISODateTimeFormat.time().print(this);
    }

    /**
     * Output the time using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[98]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[79]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[80]++;}
        return DateTimeFormat.forPattern(pattern).print(this);
    }

    /**
     * Output the time using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @param locale  Locale to use, null means default
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern, Locale locale) throws IllegalArgumentException {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[99]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[81]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[82]++;}
        return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
    }

    //-----------------------------------------------------------------------
    /**
     * LocalTime.Property binds a LocalTime to a DateTimeField allowing
     * powerful datetime functionality to be easily accessed.
     * <p>
     * The simplest use of this class is as an alternative get method, here used to
     * get the minute '30'.
     * <pre>
     * LocalTime dt = new LocalTime(12, 30);
     * int year = dt.minuteOfHour().get();
     * </pre>
     * <p>
     * Methods are also provided that allow time modification. These return
     * new instances of LocalTime - they do not modify the original. The example
     * below yields two independent immutable date objects 2 hours apart.
     * <pre>
     * LocalTime dt1230 = new LocalTime(12, 30);
     * LocalTime dt1430 = dt1230.hourOfDay().setCopy(14);
     * </pre>
     * <p>
     * LocalTime.Property itself is thread-safe and immutable, as well as the
     * LocalTime being operated on.
     *
     * @author Stephen Colebourne
     * @author Brian S O'Neill
     * @since 1.3
     */
    public static final class Property extends AbstractReadableInstantFieldProperty {
        
        /** Serialization version */
        private static final long serialVersionUID = -325842547277223L;
        
        /** The instant this property is working against */
        private transient LocalTime iInstant;
        /** The field this property is working against */
        private transient DateTimeField iField;
        
        /**
         * Constructor.
         * 
         * @param instant  the instant to set
         * @param field  the field to use
         */
        Property(LocalTime instant, DateTimeField field) {
            super();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[100]++;
            iInstant = instant;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[101]++;
            iField = field;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[102]++;
        }
        
        /**
         * Writes the property in a safe serialization format.
         */
        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.writeObject(iInstant);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[103]++;
            oos.writeObject(iField.getType());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[104]++;
        }
        
        /**
         * Reads the property from a safe serialization format.
         */
        private void readObject(ObjectInputStream oos) throws IOException, ClassNotFoundException {
            iInstant = (LocalTime) oos.readObject();
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[105]++;
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[106]++;
            DateTimeFieldType type = (DateTimeFieldType) oos.readObject();
            iField = type.getField(iInstant.getChronology());
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[107]++;
        }
        
        //-----------------------------------------------------------------------
        /**
         * Gets the field being used.
         * 
         * @return the field
         */
        public DateTimeField getField() {
            return iField;
        }
        
        /**
         * Gets the milliseconds of the time that this property is linked to.
         * 
         * @return the milliseconds
         */
        protected long getMillis() {
            return iInstant.getLocalMillis();
        }
        
        /**
         * Gets the chronology of the datetime that this property is linked to.
         * 
         * @return the chronology
         * @since 1.4
         */
        protected Chronology getChronology() {
            return iInstant.getChronology();
        }
        
        /**
         * Gets the LocalTime object linked to this property.
         * 
         * @return the linked LocalTime
         */
        public LocalTime getLocalTime() {
            return iInstant;
        }
        
        //-----------------------------------------------------------------------
        /**
         * Adds to this field in a copy of this LocalTime.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @param value  the value to add to the field in the copy
         * @return a copy of the LocalTime with the field value changed
         */
        public LocalTime addCopy(int value) {
            return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), value));
        }
        
        /**
         * Adds to this field in a copy of this LocalTime.
         * If the addition exceeds the maximum value (eg. 23:59) it will
         * wrap to the minimum value (eg. 00:00).
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @param value  the value to add to the field in the copy
         * @return a copy of the LocalTime with the field value changed
         */
        public LocalTime addCopy(long value) {
            return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), value));
        }
        
        /**
         * Adds to this field in a copy of this LocalTime.
         * If the addition exceeds the maximum value (eg. 23:59) then
         * an exception will be thrown.
         * Contrast this behaviour to {@link #addCopy(int)}.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @param value  the value to add to the field in the copy
         * @return a copy of the LocalTime with the field value changed
         * @throws IllegalArgumentException if the result is invalid
         */
        public LocalTime addNoWrapToCopy(int value) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[108]++;
            long millis = iField.add(iInstant.getLocalMillis(), value);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[109]++;
            long rounded = iInstant.getChronology().millisOfDay().get(millis);
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.statements[110]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((rounded != millis) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[83]++;
                throw new IllegalArgumentException("The addition exceeded the boundaries of LocalTime");

            } else {
  CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p.branches[84]++;}
            return iInstant.withLocalMillis(millis);
        }
        
        /**
         * Adds to this field, possibly wrapped, in a copy of this LocalTime.
         * A field wrapped operation only changes this field.
         * Thus 10:59 plusWrapField one minute goes to 10:00.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @param value  the value to add to the field in the copy
         * @return a copy of the LocalTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public LocalTime addWrapFieldToCopy(int value) {
            return iInstant.withLocalMillis(iField.addWrapField(iInstant.getLocalMillis(), value));
        }
        
        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the LocalTime.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @param value  the value to set the field in the copy to
         * @return a copy of the LocalTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public LocalTime setCopy(int value) {
            return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), value));
        }
        
        /**
         * Sets this field in a copy of the LocalTime to a parsed text value.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the LocalTime with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public LocalTime setCopy(String text, Locale locale) {
            return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), text, locale));
        }
        
        /**
         * Sets this field in a copy of the LocalTime to a parsed text value.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @param text  the text value to set
         * @return a copy of the LocalTime with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public LocalTime setCopy(String text) {
            return setCopy(text, null);
        }
        
        //-----------------------------------------------------------------------
        /**
         * Returns a new LocalTime with this field set to the maximum value
         * for this field.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @return a copy of the LocalTime with this field set to its maximum
         */
        public LocalTime withMaximumValue() {
            return setCopy(getMaximumValue());
        }
        
        /**
         * Returns a new LocalTime with this field set to the minimum value
         * for this field.
         * <p>
         * The LocalTime attached to this property is unchanged by this call.
         *
         * @return a copy of the LocalTime with this field set to its minimum
         */
        public LocalTime withMinimumValue() {
            return setCopy(getMinimumValue());
        }
        
        //-----------------------------------------------------------------------
        /**
         * Rounds to the lowest whole unit of this field on a copy of this
         * LocalTime.
         * <p>
         * For example, rounding floor on the hourOfDay field of a LocalTime
         * where the time is 10:30 would result in new LocalTime with the
         * time of 10:00.
         *
         * @return a copy of the LocalTime with the field value changed
         */
        public LocalTime roundFloorCopy() {
            return iInstant.withLocalMillis(iField.roundFloor(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the highest whole unit of this field on a copy of this
         * LocalTime.
         * <p>
         * For example, rounding floor on the hourOfDay field of a LocalTime
         * where the time is 10:30 would result in new LocalTime with the
         * time of 11:00.
         *
         * @return a copy of the LocalTime with the field value changed
         */
        public LocalTime roundCeilingCopy() {
            return iInstant.withLocalMillis(iField.roundCeiling(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this
         * LocalTime, favoring the floor if halfway.
         *
         * @return a copy of the LocalTime with the field value changed
         */
        public LocalTime roundHalfFloorCopy() {
            return iInstant.withLocalMillis(iField.roundHalfFloor(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this
         * LocalTime, favoring the ceiling if halfway.
         *
         * @return a copy of the LocalTime with the field value changed
         */
        public LocalTime roundHalfCeilingCopy() {
            return iInstant.withLocalMillis(iField.roundHalfCeiling(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this
         * LocalTime.  If halfway, the ceiling is favored over the floor
         * only if it makes this field's value even.
         *
         * @return a copy of the LocalTime with the field value changed
         */
        public LocalTime roundHalfEvenCopy() {
            return iInstant.withLocalMillis(iField.roundHalfEven(iInstant.getLocalMillis()));
        }
    }

}

class CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p ());
  }
    public static long[] statements = new long[111];
    public static long[] branches = new long[85];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[38];
  static {
    final String SECTION_NAME = "org.joda.time.LocalTime.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 37; i++) {
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

  public CodeCoverCoverageCounter$37z2xa9tkcye3erncbq82p () {
    super("org.joda.time.LocalTime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 110; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 84; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.LocalTime.java");
      for (int i = 1; i <= 110; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 84; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 37; i++) {
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

