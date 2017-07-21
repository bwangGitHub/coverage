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
import java.util.Locale;
import java.util.TimeZone;

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
 * LocalDateTime is an unmodifiable datetime class representing a
 * datetime without a time zone.
 * <p>
 * LocalDateTime implements the {@link ReadablePartial} interface.
 * To do this, certain methods focus on key fields Year, MonthOfYear,
 * DayOfYear and MillisOfDay.
 * However, <b>all</b> fields may in fact be queried.
 * <p>
 * Internally, LocalDateTime uses a single millisecond-based value to
 * represent the local datetime. This value is only used internally and
 * is not exposed to applications.
 * <p>
 * Calculations on LocalDateTime are performed using a {@link Chronology}.
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
 * LocalDateTime is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.3
 */
public final class LocalDateTime
        extends BaseLocal
        implements ReadablePartial, Serializable {
  static {
    CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -268716875315837168L;
  static {
    CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[1]++;
  }

    /** The index of the year field in the field array */
    private static final int YEAR = 0;
  static {
    CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[2]++;
  }
    /** The index of the monthOfYear field in the field array */
    private static final int MONTH_OF_YEAR = 1;
  static {
    CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[3]++;
  }
    /** The index of the dayOfMonth field in the field array */
    private static final int DAY_OF_MONTH = 2;
  static {
    CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[4]++;
  }
    /** The index of the millis field in the field array */
    private static final int MILLIS_OF_DAY = 3;
  static {
    CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[5]++;
  }

    /** The local millis from 1970-01-01T00:00:00 */
    private final long iLocalMillis;
    /** The chronology to use in UTC */
    private final Chronology iChronology;

    //-----------------------------------------------------------------------
    /**
     * Obtains a {@code LocalDateTime} set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * The resulting object does not use the zone.
     * 
     * @return the current date, not null
     * @since 2.0
     */
    public static LocalDateTime now() {
        return new LocalDateTime();
    }

    /**
     * Obtains a {@code LocalDateTime} set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * The resulting object does not use the zone.
     *
     * @param zone  the time zone, not null
     * @return the current date, not null
     * @since 2.0
     */
    public static LocalDateTime now(DateTimeZone zone) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[1]++;
            throw new NullPointerException("Zone must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[2]++;}
        return new LocalDateTime(zone);
    }

    /**
     * Obtains a {@code LocalDateTime} set to the current system millisecond time
     * using the specified chronology.
     * The resulting object does not use the zone.
     *
     * @param chronology  the chronology, not null
     * @return the current date, not null
     * @since 2.0
     */
    public static LocalDateTime now(Chronology chronology) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronology == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[3]++;
            throw new NullPointerException("Chronology must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[4]++;}
        return new LocalDateTime(chronology);
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code LocalDateTime} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#localDateOptionalTimeParser()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static LocalDateTime parse(String str) {
        return parse(str, ISODateTimeFormat.localDateOptionalTimeParser());
    }

    /**
     * Parses a {@code LocalDateTime} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static LocalDateTime parse(String str, DateTimeFormatter formatter) {
        return formatter.parseLocalDateTime(str);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a LocalDateTime from a <code>java.util.Calendar</code>
     * using exactly the same field values.
     * <p>
     * Each field is queried from the Calendar and assigned to the LocalDateTime.
     * This is useful if you have been using the Calendar as a local date,
     * ignoring the zone.
     * <p>
     * One advantage of this method is that this method is unaffected if the
     * version of the time zone data differs between the JDK and Joda-Time.
     * That is because the local field values are transferred, calculated using
     * the JDK time zone data and without using the Joda-Time time zone data.
     * <p>
     * This factory method ignores the type of the calendar and always
     * creates a LocalDateTime with ISO chronology. It is expected that you
     * will only pass in instances of <code>GregorianCalendar</code> however
     * this is not validated.
     *
     * @param calendar  the Calendar to extract fields from
     * @return the created LocalDateTime
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the date is invalid for the ISO chronology
     */
    public static LocalDateTime fromCalendarFields(Calendar calendar) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((calendar == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[5]++;
            throw new IllegalArgumentException("The calendar must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[6]++;}
        return new LocalDateTime(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND),
            calendar.get(Calendar.MILLISECOND)
        );
    }

    /**
     * Constructs a LocalDateTime from a <code>java.util.Date</code>
     * using exactly the same field values.
     * <p>
     * Each field is queried from the Date and assigned to the LocalDateTime.
     * This is useful if you have been using the Date as a local date,
     * ignoring the zone.
     * <p>
     * One advantage of this method is that this method is unaffected if the
     * version of the time zone data differs between the JDK and Joda-Time.
     * That is because the local field values are transferred, calculated using
     * the JDK time zone data and without using the Joda-Time time zone data.
     * <p>
     * This factory method always creates a LocalDateTime with ISO chronology.
     *
     * @param date  the Date to extract fields from
     * @return the created LocalDateTime
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the date is invalid for the ISO chronology
     */
    @SuppressWarnings("deprecation")
    public static LocalDateTime fromDateFields(Date date) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[7]++;
            throw new IllegalArgumentException("The date must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[8]++;}
        return new LocalDateTime(
            date.getYear() + 1900,
            date.getMonth() + 1,
            date.getDate(),
            date.getHours(),
            date.getMinutes(),
            date.getSeconds(),
            (int) (date.getTime() % 1000)
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
    public LocalDateTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[10]++;
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
    public LocalDateTime(DateTimeZone zone) {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[11]++;
    }

    /**
     * Constructs an instance set to the current local time evaluated using
     * specified chronology.
     * <p>
     * If the chronology is null, ISO chronology in the default time zone is used.
     * Once the constructor is completed, the zone is no longer used.
     *
     * @param chronology  the chronology, null means ISOChronology in default zone
     * @see #now(Chronology)
     */
    public LocalDateTime(Chronology chronology) {
        this(DateTimeUtils.currentTimeMillis(), chronology);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[12]++;
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
    public LocalDateTime(long instant) {
        this(instant, ISOChronology.getInstance());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[13]++;
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
    public LocalDateTime(long instant, DateTimeZone zone) {
        this(instant, ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[14]++;
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
    public LocalDateTime(long instant, Chronology chronology) {
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[15]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[16]++;
        
        long localMillis = chronology.getZone().getMillisKeepLocal(DateTimeZone.UTC, instant);
        iLocalMillis = localMillis;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[17]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[18]++;
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
     * The String formats are described by {@link ISODateTimeFormat#localDateOptionalTimeParser()}.
     * The default String converter ignores the zone and only parses the field values.
     *
     * @param instant  the datetime object
     * @throws IllegalArgumentException if the instant is invalid
     */
    public LocalDateTime(Object instant) {
        this(instant, (Chronology) null);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[19]++;
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
     * The String formats are described by {@link ISODateTimeFormat#localDateOptionalTimeParser()}.
     * The default String converter ignores the zone and only parses the field values.
     *
     * @param instant  the datetime object
     * @param zone  the time zone
     * @throws IllegalArgumentException if the instant is invalid
     */
    public LocalDateTime(Object instant, DateTimeZone zone) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[20]++;
        PartialConverter converter = ConverterManager.getInstance().getPartialConverter(instant);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[21]++;
        Chronology chronology = converter.getChronology(instant, zone);
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[22]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[23]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[24]++;
        int[] values = converter.getPartialValues(this, instant, chronology, ISODateTimeFormat.localDateOptionalTimeParser());
        iLocalMillis = iChronology.getDateTimeMillis(values[0], values[1], values[2], values[3]);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[25]++;
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
     * The String formats are described by {@link ISODateTimeFormat#localDateOptionalTimeParser()}.
     * The default String converter ignores the zone and only parses the field values.
     *
     * @param instant  the datetime object
     * @param chronology  the chronology
     * @throws IllegalArgumentException if the instant is invalid
     */
    public LocalDateTime(Object instant, Chronology chronology) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[26]++;
        PartialConverter converter = ConverterManager.getInstance().getPartialConverter(instant);
        chronology = converter.getChronology(instant, chronology);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[27]++;
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[28]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[29]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[30]++;
        int[] values = converter.getPartialValues(this, instant, chronology, ISODateTimeFormat.localDateOptionalTimeParser());
        iLocalMillis = iChronology.getDateTimeMillis(values[0], values[1], values[2], values[3]);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[31]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the specified date and time
     * using <code>ISOChronology</code>.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     */
    public LocalDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour) {
        this(year, monthOfYear, dayOfMonth, hourOfDay,
            minuteOfHour, 0, 0, ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[32]++;
    }

    /**
     * Constructs an instance set to the specified date and time
     * using <code>ISOChronology</code>.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     */
    public LocalDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute) {
        this(year, monthOfYear, dayOfMonth, hourOfDay,
            minuteOfHour, secondOfMinute, 0, ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[33]++;
    }

    /**
     * Constructs an instance set to the specified date and time
     * using <code>ISOChronology</code>.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     */
    public LocalDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {
        this(year, monthOfYear, dayOfMonth, hourOfDay,
            minuteOfHour, secondOfMinute, millisOfSecond, ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[34]++;
    }

    /**
     * Constructs an instance set to the specified date and time
     * using the specified chronology, whose zone is ignored.
     * <p>
     * If the chronology is null, <code>ISOChronology</code> is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public LocalDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond,
            Chronology chronology) {
        super();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[35]++;
        chronology = DateTimeUtils.getChronology(chronology).withUTC();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[36]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[37]++;
        long instant = chronology.getDateTimeMillis(year, monthOfYear, dayOfMonth,
            hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
        iChronology = chronology;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[38]++;
        iLocalMillis = instant;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[39]++;
    }

    /**
     * Handle broken serialization from other tools.
     * @return the resolved object, not null
     */
    private Object readResolve() {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[40]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((DateTimeZone.UTC.equals(iChronology.getZone()) == false) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[9]++;
            return new LocalDateTime(iLocalMillis, iChronology.withUTC());

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[10]++;}
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields in this partial, which is four.
     * The supported fields are Year, MonthOfDay, DayOfMonth and MillisOfDay.
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
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[41]++;
        switch (index) {
            case YEAR:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[11]++;
                return chrono.year();
            case MONTH_OF_YEAR:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[12]++;
                return chrono.monthOfYear();
            case DAY_OF_MONTH:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[13]++;
                return chrono.dayOfMonth();
            case MILLIS_OF_DAY:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[14]++;
                return chrono.millisOfDay();
            default:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[15]++;
                throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    /**
     * Gets the value of the field at the specifed index.
     * <p>
     * This method is required to support the <code>ReadablePartial</code>
     * interface. The supported fields are Year, MonthOfDay, DayOfMonth and MillisOfDay.
     *
     * @param index  the index, zero to two
     * @return the value
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public int getValue(int index) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[42]++;
        switch (index) {
            case YEAR:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[16]++;
                return getChronology().year().get(getLocalMillis());
            case MONTH_OF_YEAR:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[17]++;
                return getChronology().monthOfYear().get(getLocalMillis());
            case DAY_OF_MONTH:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[18]++;
                return getChronology().dayOfMonth().get(getLocalMillis());
            case MILLIS_OF_DAY:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[19]++;
                return getChronology().millisOfDay().get(getLocalMillis());
            default:
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[20]++;
                throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Get the value of one of the fields of a datetime.
     * <p>
     * This method gets the value of the specified field.
     * For example:
     * <pre>
     * DateTime dt = new DateTime();
     * int year = dt.get(DateTimeFieldType.year());
     * </pre>
     *
     * @param type  a field type, usually obtained from DateTimeFieldType, not null
     * @return the value of that field
     * @throws IllegalArgumentException if the field type is null
     */
    public int get(DateTimeFieldType type) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[43]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[21]++;
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[22]++;}
        return type.getField(getChronology()).get(getLocalMillis());
    }

    /**
     * Checks if the field type specified is supported by this
     * local datetime and chronology.
     * This can be used to avoid exceptions in {@link #get(DateTimeFieldType)}.
     *
     * @param type  a field type, usually obtained from DateTimeFieldType
     * @return true if the field type is supported
     */
    public boolean isSupported(DateTimeFieldType type) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[24]++;}
        return type.getField(getChronology()).isSupported();
    }

    /**
     * Checks if the duration type specified is supported by this
     * local datetime and chronology.
     *
     * @param type  a duration type, usually obtained from DurationFieldType
     * @return true if the field type is supported
     */
    public boolean isSupported(DurationFieldType type) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[26]++;}
        return type.getField(getChronology()).isSupported();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the milliseconds of the datetime instant from the Java epoch
     * of 1970-01-01T00:00:00 (not fixed to any specific time zone).
     *
     * @return the number of milliseconds since 1970-01-01T00:00:00
     * @since 1.5 (previously private)
     */
    protected long getLocalMillis() {
        return iLocalMillis;
    }

    /**
     * Gets the chronology of the datetime.
     *
     * @return the Chronology that the datetime is using
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
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        // override to perform faster
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this == partial) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[27]++;
            return true;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[28]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((partial instanceof LocalDateTime) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[29]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[48]++;
            LocalDateTime other = (LocalDateTime) partial;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((iChronology.equals(other.iChronology)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[31]++;
                return iLocalMillis == other.iLocalMillis;

            } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[30]++;}
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
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
        // override to perform faster
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this == partial) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[33]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[34]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((partial instanceof LocalDateTime) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[35]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[52]++;
            LocalDateTime other = (LocalDateTime) partial;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((iChronology.equals(other.iChronology)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[37]++;
                return (iLocalMillis < other.iLocalMillis ? -1 :
                            (iLocalMillis == other.iLocalMillis ? 0 : 1));


            } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[38]++;}

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[36]++;}
        return super.compareTo(partial);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a DateTime using the default zone.
     * <p>
     * This method will throw an exception if the datetime that would be
     * created does not exist when the time zone is taken into account.
     * 
     * @return <code>this</code>
     */
    public DateTime toDateTime() {
        return toDateTime((DateTimeZone) null);
    }

    /**
     * Converts this object to a DateTime using the specified zone.
     * <p>
     * This method will throw an exception if the datetime that would be
     * created does not exist when the time zone is taken into account.
     * 
     * @param zone time zone to apply, or default if null
     * @return a DateTime using the same millis
     */
    public DateTime toDateTime(DateTimeZone zone) {
        zone = DateTimeUtils.getZone(zone);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[54]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[55]++;
        Chronology chrono = iChronology.withZone(zone);
        return new DateTime(
                getYear(), getMonthOfYear(), getDayOfMonth(),
                getHourOfDay(), getMinuteOfHour(),
                getSecondOfMinute(), getMillisOfSecond(), chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a LocalDate with the same date and chronology.
     *
     * @return a LocalDate with the same date and chronology
     */
    public LocalDate toLocalDate() {
        return new LocalDate(getLocalMillis(), getChronology());
    }

    /**
     * Converts this object to a LocalTime with the same time and chronology.
     *
     * @return a LocalTime with the same time and chronology
     */
    public LocalTime toLocalTime() {
        return new LocalTime(getLocalMillis(), getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Get the date time as a <code>java.util.Date</code>.
     * <p>
     * The <code>Date</code> object created has exactly the same fields as this
     * date-time, except when the time would be invalid due to a daylight savings
     * gap. In that case, the time will be set to the earliest valid time after the gap.
     * <p>
     * In the case of a daylight savings overlap, the earlier instant is selected.
     * <p>
     * Converting to a JDK Date is full of complications as the JDK Date constructor
     * doesn't behave as you might expect around DST transitions. This method works
     * by taking a first guess and then adjusting. This also handles the situation
     * where the JDK time zone data differs from the Joda-Time time zone data.
     *
     * @return a Date initialised with this date-time, never null
     * @since 2.0
     */
    @SuppressWarnings("deprecation")
    public Date toDate() {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[56]++;
        int dom = getDayOfMonth();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[57]++;
        Date date = new Date(getYear() - 1900, getMonthOfYear() - 1, dom,
                        getHourOfDay(), getMinuteOfHour(), getSecondOfMinute());
        date.setTime(date.getTime() + getMillisOfSecond());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[58]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[59]++;
        LocalDateTime check = LocalDateTime.fromDateFields(date);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[60]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((check.isBefore(this)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[39]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[61]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[1]++;


int CodeCoverConditionCoverageHelper_C16;
            // DST gap
            // move forward in units of one minute until equal/after
            while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((check.isBefore(this)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[1]--;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[2]--;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[3]++;
}
                date.setTime(date.getTime() + 60000);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[62]++;
                check = LocalDateTime.fromDateFields(date);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[63]++;
            }
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[64]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;
            // move back in units of one second until date wrong
            while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((check.isBefore(this) == false) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[4]--;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[5]--;
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.loops[6]++;
}
                date.setTime(date.getTime() - 1000);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[65]++;
                check = LocalDateTime.fromDateFields(date);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[66]++;
            }
            date.setTime(date.getTime() + 1000);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[67]++;

        } else {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[40]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[68]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((check.equals(this)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[41]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[69]++;
            // check for DST overlap
            Date earlier = new Date(date.getTime() - TimeZone.getDefault().getDSTSavings());
            check = LocalDateTime.fromDateFields(earlier);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[70]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((check.equals(this)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[43]++;
                date = earlier;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[72]++;

            } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[42]++;}
}
        return date;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with different local millis.
     * <p>
     * The returned object will be a new instance of the same type.
     * Only the millis will change, the chronology is kept.
     * The returned object will be either be a new instance or <code>this</code>.
     *
     * @param newMillis  the new millis, from 1970-01-01T00:00:00
     * @return a copy of this datetime with different millis
     */
    LocalDateTime withLocalMillis(long newMillis) {
        return (newMillis == getLocalMillis() ? this : new LocalDateTime(newMillis, getChronology()));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified date,
     * retaining the time fields.
     * <p>
     * If the date is already the date passed in, then <code>this</code> is returned.
     * <p>
     * To set a single field use the properties, for example:
     * <pre>
     * DateTime set = dt.monthOfYear().setCopy(6);
     * </pre>
     *
     * @param year  the new year value
     * @param monthOfYear  the new monthOfYear value
     * @param dayOfMonth  the new dayOfMonth value
     * @return a copy of this datetime with a different date
     * @throws IllegalArgumentException if any value if invalid
     */
    public LocalDateTime withDate(int year, int monthOfYear, int dayOfMonth) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[73]++;
        Chronology chrono = getChronology();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[74]++;
        long instant = getLocalMillis();
        instant = chrono.year().set(instant, year);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[75]++;
        instant = chrono.monthOfYear().set(instant, monthOfYear);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[76]++;
        instant = chrono.dayOfMonth().set(instant, dayOfMonth);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[77]++;
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime with the specified time,
     * retaining the date fields.
     * <p>
     * If the time is already the time passed in, then <code>this</code> is returned.
     * <p>
     * To set a single field use the properties, for example:
     * <pre>
     * LocalDateTime set = dt.hourOfDay().setCopy(6);
     * </pre>
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @return a copy of this datetime with a different time
     * @throws IllegalArgumentException if any value if invalid
     */
    public LocalDateTime withTime(int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[78]++;
        Chronology chrono = getChronology();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[79]++;
        long instant = getLocalMillis();
        instant = chrono.hourOfDay().set(instant, hourOfDay);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[80]++;
        instant = chrono.minuteOfHour().set(instant, minuteOfHour);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[81]++;
        instant = chrono.secondOfMinute().set(instant, secondOfMinute);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[82]++;
        instant = chrono.millisOfSecond().set(instant, millisOfSecond);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[83]++;
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the partial set of fields
     * replacing those from this instance.
     * <p>
     * For example, if the partial is a <code>TimeOfDay</code> then the time fields
     * would be changed in the returned instance.
     * If the partial is null, then <code>this</code> is returned.
     *
     * @param partial  the partial set of fields to apply to this datetime, null ignored
     * @return a copy of this datetime with a different set of fields
     * @throws IllegalArgumentException if any value is invalid
     */
    public LocalDateTime withFields(ReadablePartial partial) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[84]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[45]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[46]++;}
        return withLocalMillis(getChronology().set(partial, getLocalMillis()));
    }

    /**
     * Returns a copy of this datetime with the specified field set to a new value.
     * <p>
     * For example, if the field type is <code>hourOfDay</code> then the hour of day
     * field would be changed in the returned instance.
     * If the field type is null, then <code>this</code> is returned.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * LocalDateTime updated = dt.withField(DateTimeFieldType.dayOfMonth(), 6);
     * LocalDateTime updated = dt.dayOfMonth().setCopy(6);
     * LocalDateTime updated = dt.property(DateTimeFieldType.dayOfMonth()).setCopy(6);
     * </pre>
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this datetime with the field set
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public LocalDateTime withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[85]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[47]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[48]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[86]++;
        long instant = fieldType.getField(getChronology()).set(getLocalMillis(), value);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime with the value of the specified
     * field increased.
     * <p>
     * If the addition is zero or the field is null, then <code>this</code> is returned.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.years(), 6);
     * LocalDateTime added = dt.plusYears(6);
     * LocalDateTime added = dt.plus(Period.years(6));
     * </pre>
     *
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this datetime with the field updated
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalDateTime withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[87]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[49]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[50]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[88]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[51]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[52]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[89]++;
        long instant = fieldType.getField(getChronology()).add(getLocalMillis(), amount);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified duration added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     *
     * @param durationToAdd  the duration to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalDateTime withDurationAdded(ReadableDuration durationToAdd, int scalar) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[90]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((durationToAdd == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[53]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[54]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[91]++;
        long instant = getChronology().add(getLocalMillis(), durationToAdd.getMillis(), scalar);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime with the specified period added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * <p>
     * This method is typically used to add multiple copies of complex
     * period instances. Adding one field is best achieved using methods
     * like {@link #withFieldAdded(DurationFieldType, int)}
     * or {@link #plusYears(int)}.
     *
     * @param period  the period to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this datetime with the period added
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalDateTime withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[92]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[55]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[56]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[93]++;
        long instant = getChronology().add(period, getLocalMillis(), scalar);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified duration added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     *
     * @param duration  the duration to add to this one, null means zero
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalDateTime plus(ReadableDuration duration) {
        return withDurationAdded(duration, 1);
    }

    /**
     * Returns a copy of this datetime with the specified period added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to add complex period instances.
     * Adding one field is best achieved using methods
     * like {@link #plusYears(int)}.
     *
     * @param period  the period to add to this one, null means zero
     * @return a copy of this datetime with the period added
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalDateTime plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime plus the specified number of years.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusYears(6);
     * LocalDateTime added = dt.plus(Period.years(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.years(), 6);
     * </pre>
     *
     * @param years  the amount of years to add, may be negative
     * @return the new LocalDateTime plus the increased years
     */
    public LocalDateTime plusYears(int years) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[94]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[57]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[58]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[95]++;
        long instant = getChronology().years().add(getLocalMillis(), years);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of months.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusMonths(6);
     * LocalDateTime added = dt.plus(Period.months(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.months(), 6);
     * </pre>
     *
     * @param months  the amount of months to add, may be negative
     * @return the new LocalDateTime plus the increased months
     */
    public LocalDateTime plusMonths(int months) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[96]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[59]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[60]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[97]++;
        long instant = getChronology().months().add(getLocalMillis(), months);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of weeks.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusWeeks(6);
     * LocalDateTime added = dt.plus(Period.weeks(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.weeks(), 6);
     * </pre>
     *
     * @param weeks  the amount of weeks to add, may be negative
     * @return the new LocalDateTime plus the increased weeks
     */
    public LocalDateTime plusWeeks(int weeks) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[98]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((weeks == 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[61]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[62]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[99]++;
        long instant = getChronology().weeks().add(getLocalMillis(), weeks);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of days.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusDays(6);
     * LocalDateTime added = dt.plus(Period.days(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.days(), 6);
     * </pre>
     *
     * @param days  the amount of days to add, may be negative
     * @return the new LocalDateTime plus the increased days
     */
    public LocalDateTime plusDays(int days) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[100]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[63]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[64]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[101]++;
        long instant = getChronology().days().add(getLocalMillis(), days);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime plus the specified number of hours.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusHours(6);
     * LocalDateTime added = dt.plus(Period.hours(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.hours(), 6);
     * </pre>
     *
     * @param hours  the amount of hours to add, may be negative
     * @return the new LocalDateTime plus the increased hours
     */
    public LocalDateTime plusHours(int hours) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[102]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[65]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[66]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[103]++;
        long instant = getChronology().hours().add(getLocalMillis(), hours);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of minutes.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusMinutes(6);
     * LocalDateTime added = dt.plus(Period.minutes(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.minutes(), 6);
     * </pre>
     *
     * @param minutes  the amount of minutes to add, may be negative
     * @return the new LocalDateTime plus the increased minutes
     */
    public LocalDateTime plusMinutes(int minutes) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[104]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((minutes == 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[67]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[68]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[105]++;
        long instant = getChronology().minutes().add(getLocalMillis(), minutes);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of seconds.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusSeconds(6);
     * LocalDateTime added = dt.plus(Period.seconds(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.seconds(), 6);
     * </pre>
     *
     * @param seconds  the amount of seconds to add, may be negative
     * @return the new LocalDateTime plus the increased seconds
     */
    public LocalDateTime plusSeconds(int seconds) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[106]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[69]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[70]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[107]++;
        long instant = getChronology().seconds().add(getLocalMillis(), seconds);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of millis.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime added = dt.plusMillis(6);
     * LocalDateTime added = dt.plus(Period.millis(6));
     * LocalDateTime added = dt.withFieldAdded(DurationFieldType.millis(), 6);
     * </pre>
     *
     * @param millis  the amount of millis to add, may be negative
     * @return the new LocalDateTime plus the increased millis
     */
    public LocalDateTime plusMillis(int millis) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[108]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((millis == 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[71]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[72]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[109]++;
        long instant = getChronology().millis().add(getLocalMillis(), millis);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified duration taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     *
     * @param duration  the duration to reduce this instant by
     * @return a copy of this datetime with the duration taken away
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalDateTime minus(ReadableDuration duration) {
        return withDurationAdded(duration, -1);
    }

    /**
     * Returns a copy of this datetime with the specified period taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to subtract complex period instances.
     * Subtracting one field is best achieved using methods
     * like {@link #minusYears(int)}.
     *
     * @param period  the period to reduce this instant by
     * @return a copy of this datetime with the period taken away
     * @throws ArithmeticException if the result exceeds the internal capacity
     */
    public LocalDateTime minus(ReadablePeriod period) {
        return withPeriodAdded(period, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime minus the specified number of years.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusYears(6);
     * LocalDateTime subtracted = dt.minus(Period.years(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), -6);
     * </pre>
     *
     * @param years  the amount of years to subtract, may be negative
     * @return the new LocalDateTime minus the increased years
     */
    public LocalDateTime minusYears(int years) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[110]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[73]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[74]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[111]++;
        long instant = getChronology().years().subtract(getLocalMillis(), years);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of months.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusMonths(6);
     * LocalDateTime subtracted = dt.minus(Period.months(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.months(), -6);
     * </pre>
     *
     * @param months  the amount of months to subtract, may be negative
     * @return the new LocalDateTime minus the increased months
     */
    public LocalDateTime minusMonths(int months) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[112]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[75]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[76]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[113]++;
        long instant = getChronology().months().subtract(getLocalMillis(), months);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of weeks.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusWeeks(6);
     * LocalDateTime subtracted = dt.minus(Period.weeks(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.weeks(), -6);
     * </pre>
     *
     * @param weeks  the amount of weeks to subtract, may be negative
     * @return the new LocalDateTime minus the increased weeks
     */
    public LocalDateTime minusWeeks(int weeks) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[114]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((weeks == 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[77]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[78]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[115]++;
        long instant = getChronology().weeks().subtract(getLocalMillis(), weeks);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of days.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusDays(6);
     * LocalDateTime subtracted = dt.minus(Period.days(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.days(), -6);
     * </pre>
     *
     * @param days  the amount of days to subtract, may be negative
     * @return the new LocalDateTime minus the increased days
     */
    public LocalDateTime minusDays(int days) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[116]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[79]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[80]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[117]++;
        long instant = getChronology().days().subtract(getLocalMillis(), days);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime minus the specified number of hours.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusHours(6);
     * LocalDateTime subtracted = dt.minus(Period.hours(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.hours(), -6);
     * </pre>
     *
     * @param hours  the amount of hours to subtract, may be negative
     * @return the new LocalDateTime minus the increased hours
     */
    public LocalDateTime minusHours(int hours) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[118]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[81]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[82]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[119]++;
        long instant = getChronology().hours().subtract(getLocalMillis(), hours);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of minutes.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusMinutes(6);
     * LocalDateTime subtracted = dt.minus(Period.minutes(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.minutes(), -6);
     * </pre>
     *
     * @param minutes  the amount of minutes to subtract, may be negative
     * @return the new LocalDateTime minus the increased minutes
     */
    public LocalDateTime minusMinutes(int minutes) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[120]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((minutes == 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[83]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[84]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[121]++;
        long instant = getChronology().minutes().subtract(getLocalMillis(), minutes);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of seconds.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusSeconds(6);
     * LocalDateTime subtracted = dt.minus(Period.seconds(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.seconds(), -6);
     * </pre>
     *
     * @param seconds  the amount of seconds to subtract, may be negative
     * @return the new LocalDateTime minus the increased seconds
     */
    public LocalDateTime minusSeconds(int seconds) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[122]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[85]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[86]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[123]++;
        long instant = getChronology().seconds().subtract(getLocalMillis(), seconds);
        return withLocalMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of millis.
     * <p>
     * This LocalDateTime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * LocalDateTime subtracted = dt.minusMillis(6);
     * LocalDateTime subtracted = dt.minus(Period.millis(6));
     * LocalDateTime subtracted = dt.withFieldAdded(DurationFieldType.millis(), -6);
     * </pre>
     *
     * @param millis  the amount of millis to subtract, may be negative
     * @return the new LocalDateTime minus the increased millis
     */
    public LocalDateTime minusMillis(int millis) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[124]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((millis == 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[87]++;
            return this;

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[88]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[125]++;
        long instant = getChronology().millis().subtract(getLocalMillis(), millis);
        return withLocalMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the property object for the specified type, which contains many
     * useful methods.
     *
     * @param fieldType  the field type to get the chronology for
     * @return the property object
     * @throws IllegalArgumentException if the field is null or unsupported
     */
    public Property property(DateTimeFieldType fieldType) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[126]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[89]++;
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[90]++;}
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[127]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((isSupported(fieldType) == false) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[91]++;
            throw new IllegalArgumentException("Field '" + fieldType + "' is not supported");

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[92]++;}
        return new Property(this, fieldType.getField(getChronology()));
    }

    //-----------------------------------------------------------------------
    /**
     * Get the era field value.
     *
     * @return the era
     */
    public int getEra() {
        return getChronology().era().get(getLocalMillis());
    }

    /**
     * Get the year of era field value.
     *
     * @return the year of era
     */
    public int getCenturyOfEra() {
        return getChronology().centuryOfEra().get(getLocalMillis());
    }

    /**
     * Get the year of era field value.
     *
     * @return the year of era
     */
    public int getYearOfEra() {
        return getChronology().yearOfEra().get(getLocalMillis());
    }

    /**
     * Get the year of century field value.
     *
     * @return the year of century
     */
    public int getYearOfCentury() {
        return getChronology().yearOfCentury().get(getLocalMillis());
    }

    /**
     * Get the year field value.
     *
     * @return the year
     */
    public int getYear() {
        return getChronology().year().get(getLocalMillis());
    }

    /**
     * Get the weekyear field value.
     * <p>
     * The weekyear is the year that matches with the weekOfWeekyear field.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * The weekyear allows you to query the effective year for that day.
     *
     * @return the weekyear
     */
    public int getWeekyear() {
        return getChronology().weekyear().get(getLocalMillis());
    }

    /**
     * Get the month of year field value.
     *
     * @return the month of year
     */
    public int getMonthOfYear() {
        return getChronology().monthOfYear().get(getLocalMillis());
    }

    /**
     * Get the week of weekyear field value.
     * <p>
     * This field is associated with the "weekyear" via {@link #getWeekyear()}.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     *
     * @return the week of a week based year
     */
    public int getWeekOfWeekyear() {
        return getChronology().weekOfWeekyear().get(getLocalMillis());
    }

    /**
     * Get the day of year field value.
     *
     * @return the day of year
     */
    public int getDayOfYear() {
        return getChronology().dayOfYear().get(getLocalMillis());
    }

    /**
     * Get the day of month field value.
     * <p>
     * The values for the day of month are defined in {@link org.joda.time.DateTimeConstants}.
     *
     * @return the day of month
     */
    public int getDayOfMonth() {
        return getChronology().dayOfMonth().get(getLocalMillis());
    }

    /**
     * Get the day of week field value.
     * <p>
     * The values for the day of week are defined in {@link org.joda.time.DateTimeConstants}.
     *
     * @return the day of week
     */
    public int getDayOfWeek() {
        return getChronology().dayOfWeek().get(getLocalMillis());
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
     * Returns a copy of this datetime with the era field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * era changed.
     *
     * @param era  the era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withEra(int era) {
        return withLocalMillis(getChronology().era().set(getLocalMillis(), era));
    }

    /**
     * Returns a copy of this datetime with the century of era field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * century of era changed.
     *
     * @param centuryOfEra  the centurey of era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withCenturyOfEra(int centuryOfEra) {
        return withLocalMillis(getChronology().centuryOfEra().set(getLocalMillis(), centuryOfEra));
    }

    /**
     * Returns a copy of this datetime with the year of era field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year of era changed.
     *
     * @param yearOfEra  the year of era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withYearOfEra(int yearOfEra) {
        return withLocalMillis(getChronology().yearOfEra().set(getLocalMillis(), yearOfEra));
    }

    /**
     * Returns a copy of this datetime with the year of century field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year of century changed.
     *
     * @param yearOfCentury  the year of century to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withYearOfCentury(int yearOfCentury) {
        return withLocalMillis(getChronology().yearOfCentury().set(getLocalMillis(), yearOfCentury));
    }

    /**
     * Returns a copy of this datetime with the year field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year changed.
     *
     * @param year  the year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withYear(int year) {
        return withLocalMillis(getChronology().year().set(getLocalMillis(), year));
    }

    /**
     * Returns a copy of this datetime with the weekyear field updated.
     * <p>
     * The weekyear is the year that matches with the weekOfWeekyear field.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * The weekyear allows you to query the effective year for that day.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * weekyear changed.
     *
     * @param weekyear  the weekyear to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withWeekyear(int weekyear) {
        return withLocalMillis(getChronology().weekyear().set(getLocalMillis(), weekyear));
    }

    /**
     * Returns a copy of this datetime with the month of year field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * month of year changed.
     *
     * @param monthOfYear  the month of year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withMonthOfYear(int monthOfYear) {
        return withLocalMillis(getChronology().monthOfYear().set(getLocalMillis(), monthOfYear));
    }

    /**
     * Returns a copy of this datetime with the week of weekyear field updated.
     * <p>
     * This field is associated with the "weekyear" via {@link #withWeekyear(int)}.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * week of weekyear changed.
     *
     * @param weekOfWeekyear  the week of weekyear to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withWeekOfWeekyear(int weekOfWeekyear) {
        return withLocalMillis(getChronology().weekOfWeekyear().set(getLocalMillis(), weekOfWeekyear));
    }

    /**
     * Returns a copy of this datetime with the day of year field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of year changed.
     *
     * @param dayOfYear  the day of year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withDayOfYear(int dayOfYear) {
        return withLocalMillis(getChronology().dayOfYear().set(getLocalMillis(), dayOfYear));
    }

    /**
     * Returns a copy of this datetime with the day of month field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of month changed.
     *
     * @param dayOfMonth  the day of month to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withDayOfMonth(int dayOfMonth) {
        return withLocalMillis(getChronology().dayOfMonth().set(getLocalMillis(), dayOfMonth));
    }

    /**
     * Returns a copy of this datetime with the day of week field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of week changed.
     *
     * @param dayOfWeek  the day of week to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withDayOfWeek(int dayOfWeek) {
        return withLocalMillis(getChronology().dayOfWeek().set(getLocalMillis(), dayOfWeek));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the hour of day field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * hour of day changed.
     *
     * @param hour  the hour of day to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withHourOfDay(int hour) {
        return withLocalMillis(getChronology().hourOfDay().set(getLocalMillis(), hour));
    }

    /**
     * Returns a copy of this datetime with the minute of hour field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * minute of hour changed.
     *
     * @param minute  the minute of hour to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withMinuteOfHour(int minute) {
        return withLocalMillis(getChronology().minuteOfHour().set(getLocalMillis(), minute));
    }

    /**
     * Returns a copy of this datetime with the second of minute field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * second of minute changed.
     *
     * @param second  the second of minute to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withSecondOfMinute(int second) {
        return withLocalMillis(getChronology().secondOfMinute().set(getLocalMillis(), second));
    }

    /**
     * Returns a copy of this datetime with the millis of second field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * millis of second changed.
     *
     * @param millis  the millis of second to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withMillisOfSecond(int millis) {
        return withLocalMillis(getChronology().millisOfSecond().set(getLocalMillis(), millis));
    }

    /**
     * Returns a copy of this datetime with the millis of day field updated.
     * <p>
     * LocalDateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * millis of day changed.
     *
     * @param millis  the millis of day to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     */
    public LocalDateTime withMillisOfDay(int millis) {
        return withLocalMillis(getChronology().millisOfDay().set(getLocalMillis(), millis));
    }

    //-----------------------------------------------------------------------
    /**
     * Get the era property which provides access to advanced functionality.
     *
     * @return the era property
     */
    public Property era() {
        return new Property(this, getChronology().era());
    }

    /**
     * Get the century of era property which provides access to advanced functionality.
     *
     * @return the year of era property
     */
    public Property centuryOfEra() {
        return new Property(this, getChronology().centuryOfEra());
    }

    /**
     * Get the year of century property which provides access to advanced functionality.
     *
     * @return the year of era property
     */
    public Property yearOfCentury() {
        return new Property(this, getChronology().yearOfCentury());
    }

    /**
     * Get the year of era property which provides access to advanced functionality.
     *
     * @return the year of era property
     */
    public Property yearOfEra() {
        return new Property(this, getChronology().yearOfEra());
    }

    /**
     * Get the year property which provides access to advanced functionality.
     *
     * @return the year property
     */
    public Property year() {
        return new Property(this, getChronology().year());
    }

    /**
     * Get the weekyear property which provides access to advanced functionality.
     *
     * @return the weekyear property
     */
    public Property weekyear() {
        return new Property(this, getChronology().weekyear());
    }

    /**
     * Get the month of year property which provides access to advanced functionality.
     *
     * @return the month of year property
     */
    public Property monthOfYear() {
        return new Property(this, getChronology().monthOfYear());
    }

    /**
     * Get the week of a week based year property which provides access to advanced functionality.
     *
     * @return the week of a week based year property
     */
    public Property weekOfWeekyear() {
        return new Property(this, getChronology().weekOfWeekyear());
    }

    /**
     * Get the day of year property which provides access to advanced functionality.
     *
     * @return the day of year property
     */
    public Property dayOfYear() {
        return new Property(this, getChronology().dayOfYear());
    }

    /**
     * Get the day of month property which provides access to advanced functionality.
     *
     * @return the day of month property
     */
    public Property dayOfMonth() {
        return new Property(this, getChronology().dayOfMonth());
    }

    /**
     * Get the day of week property which provides access to advanced functionality.
     *
     * @return the day of week property
     */
    public Property dayOfWeek() {
        return new Property(this, getChronology().dayOfWeek());
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
     * Output the date time in ISO8601 format (yyyy-MM-ddTHH:mm:ss.SSS).
     * 
     * @return ISO8601 time formatted string.
     */
    @ToString
    public String toString() {
        return ISODateTimeFormat.dateTime().print(this);
    }

    /**
     * Output the date using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[128]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[93]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[94]++;}
        return DateTimeFormat.forPattern(pattern).print(this);
    }

    /**
     * Output the date using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @param locale  Locale to use, null means default
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern, Locale locale) throws IllegalArgumentException {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[129]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[95]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.branches[96]++;}
        return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
    }

    //-----------------------------------------------------------------------
    /**
     * LocalDateTime.Property binds a LocalDateTime to a DateTimeField allowing
     * powerful datetime functionality to be easily accessed.
     * <p>
     * The simplest use of this class is as an alternative get method, here used to
     * get the year '1972' (as an int) and the month 'December' (as a String).
     * <pre>
     * LocalDateTime dt = new LocalDateTime(1972, 12, 3, 0, 0);
     * int year = dt.year().get();
     * String monthStr = dt.month().getAsText();
     * </pre>
     * <p>
     * Methods are also provided that allow date modification. These return
     * new instances of LocalDateTime - they do not modify the original.
     * The example below yields two independent immutable date objects
     * 20 years apart.
     * <pre>
     * LocalDateTime dt = new LocalDateTime(1972, 12, 3, 0, 0);
     * LocalDateTime dt1920 = dt.year().setCopy(1920);
     * </pre>
     * <p>
     * LocalDateTime.Property itself is thread-safe and immutable, as well as the
     * LocalDateTime being operated on.
     *
     * @author Stephen Colebourne
     * @author Brian S O'Neill
     * @since 1.3
     */
    public static final class Property extends AbstractReadableInstantFieldProperty {
        
        /** Serialization version */
        private static final long serialVersionUID = -358138762846288L;
        
        /** The instant this property is working against */
        private transient LocalDateTime iInstant;
        /** The field this property is working against */
        private transient DateTimeField iField;
        
        /**
         * Constructor.
         * 
         * @param instant  the instant to set
         * @param field  the field to use
         */
        Property(LocalDateTime instant, DateTimeField field) {
            super();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[130]++;
            iInstant = instant;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[131]++;
            iField = field;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[132]++;
        }
        
        /**
         * Writes the property in a safe serialization format.
         */
        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.writeObject(iInstant);
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[133]++;
            oos.writeObject(iField.getType());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[134]++;
        }

        /**
         * Reads the property from a safe serialization format.
         */
        private void readObject(ObjectInputStream oos) throws IOException, ClassNotFoundException {
            iInstant = (LocalDateTime) oos.readObject();
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[135]++;
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[136]++;
            DateTimeFieldType type = (DateTimeFieldType) oos.readObject();
            iField = type.getField(iInstant.getChronology());
CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt.statements[137]++;
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
         * Gets the milliseconds of the datetime that this property is linked to.
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
         * Gets the LocalDateTime object linked to this property.
         * 
         * @return the linked LocalDateTime
         */
        public LocalDateTime getLocalDateTime() {
            return iInstant;
        }
        
        //-----------------------------------------------------------------------
        /**
         * Adds to this field in a copy of this LocalDateTime.
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @param value  the value to add to the field in the copy
         * @return a copy of the LocalDateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public LocalDateTime addToCopy(int value) {
            return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), value));
        }
        
        /**
         * Adds to this field in a copy of this LocalDateTime.
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @param value  the value to add to the field in the copy
         * @return a copy of the LocalDateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public LocalDateTime addToCopy(long value) {
            return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), value));
        }
        
        /**
         * Adds to this field, possibly wrapped, in a copy of this LocalDateTime.
         * A field wrapped operation only changes this field.
         * Thus 31st January addWrapField one day goes to the 1st January.
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @param value  the value to add to the field in the copy
         * @return a copy of the LocalDateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public LocalDateTime addWrapFieldToCopy(int value) {
            return iInstant.withLocalMillis(iField.addWrapField(iInstant.getLocalMillis(), value));
        }
        
        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the LocalDateTime.
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @param value  the value to set the field in the copy to
         * @return a copy of the LocalDateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public LocalDateTime setCopy(int value) {
            return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), value));
        }
        
        /**
         * Sets this field in a copy of the LocalDateTime to a parsed text value.
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the LocalDateTime with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public LocalDateTime setCopy(String text, Locale locale) {
            return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), text, locale));
        }
        
        /**
         * Sets this field in a copy of the LocalDateTime to a parsed text value.
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @param text  the text value to set
         * @return a copy of the LocalDateTime with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public LocalDateTime setCopy(String text) {
            return setCopy(text, null);
        }
        
        //-----------------------------------------------------------------------
        /**
         * Returns a new LocalDateTime with this field set to the maximum value
         * for this field.
         * <p>
         * This operation is useful for obtaining a LocalDateTime on the last day
         * of the month, as month lengths vary.
         * <pre>
         * LocalDateTime lastDayOfMonth = dt.dayOfMonth().withMaximumValue();
         * </pre>
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @return a copy of the LocalDateTime with this field set to its maximum
         */
        public LocalDateTime withMaximumValue() {
            return setCopy(getMaximumValue());
        }
        
        /**
         * Returns a new LocalDateTime with this field set to the minimum value
         * for this field.
         * <p>
         * The LocalDateTime attached to this property is unchanged by this call.
         *
         * @return a copy of the LocalDateTime with this field set to its minimum
         */
        public LocalDateTime withMinimumValue() {
            return setCopy(getMinimumValue());
        }
        
        //-----------------------------------------------------------------------
        /**
         * Rounds to the lowest whole unit of this field on a copy of this
         * LocalDateTime.
         * <p>
         * For example, rounding floor on the hourOfDay field of a LocalDateTime
         * where the time is 10:30 would result in new LocalDateTime with the
         * time of 10:00.
         *
         * @return a copy of the LocalDateTime with the field value changed
         */
        public LocalDateTime roundFloorCopy() {
            return iInstant.withLocalMillis(iField.roundFloor(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the highest whole unit of this field on a copy of this
         * LocalDateTime.
         * <p>
         * For example, rounding floor on the hourOfDay field of a LocalDateTime
         * where the time is 10:30 would result in new LocalDateTime with the
         * time of 11:00.
         *
         * @return a copy of the LocalDateTime with the field value changed
         */
        public LocalDateTime roundCeilingCopy() {
            return iInstant.withLocalMillis(iField.roundCeiling(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this
         * LocalDateTime, favoring the floor if halfway.
         *
         * @return a copy of the LocalDateTime with the field value changed
         */
        public LocalDateTime roundHalfFloorCopy() {
            return iInstant.withLocalMillis(iField.roundHalfFloor(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this
         * LocalDateTime, favoring the ceiling if halfway.
         *
         * @return a copy of the LocalDateTime with the field value changed
         */
        public LocalDateTime roundHalfCeilingCopy() {
            return iInstant.withLocalMillis(iField.roundHalfCeiling(iInstant.getLocalMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this
         * LocalDateTime.  If halfway, the ceiling is favored over the floor
         * only if it makes this field's value even.
         *
         * @return a copy of the LocalDateTime with the field value changed
         */
        public LocalDateTime roundHalfEvenCopy() {
            return iInstant.withLocalMillis(iField.roundHalfEven(iInstant.getLocalMillis()));
        }
    }

}

class CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt ());
  }
    public static long[] statements = new long[138];
    public static long[] branches = new long[97];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[46];
  static {
    final String SECTION_NAME = "org.joda.time.LocalDateTime.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 45; i++) {
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

  public CodeCoverCoverageCounter$6ctrszlfam7x6ohj4q06t9eisllt () {
    super("org.joda.time.LocalDateTime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 137; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 96; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.LocalDateTime.java");
      for (int i = 1; i <= 137; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 96; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 45; i++) {
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

