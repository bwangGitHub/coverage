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
import java.util.Locale;

import org.joda.convert.FromString;
import org.joda.time.base.BaseDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * DateTime is the standard implementation of an unmodifiable datetime class.
 * <p>
 * <code>DateTime</code> is the most widely used implementation of
 * {@link ReadableInstant}. As with all instants, it represents an exact
 * point on the time-line, but limited to the precision of milliseconds.
 * A <code>DateTime</code> calculates its fields with respect to a
 * {@link DateTimeZone time zone}.
 * <p>
 * Internally, the class holds two pieces of data. Firstly, it holds the
 * datetime as milliseconds from the Java epoch of 1970-01-01T00:00:00Z.
 * Secondly, it holds a {@link Chronology} which determines how the
 * millisecond instant value is converted into the date time fields.
 * The default Chronology is {@link ISOChronology} which is the agreed
 * international standard and compatible with the modern Gregorian calendar.
 * <p>
 * Each individual field can be queried in two ways:
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
 * <p>
 * DateTime is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @author Kandarp Shah
 * @author Brian S O'Neill
 * @since 1.0
 * @see MutableDateTime
 */
public final class DateTime
        extends BaseDateTime
        implements ReadableDateTime, Serializable {
  static {
    CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -5171125899451703815L;
  static {
    CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[1]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains a {@code DateTime} set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * 
     * @return the current date-time, not null
     * @since 2.0
     */
    public static DateTime now() {
        return new DateTime();
    }

    /**
     * Obtains a {@code DateTime} set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     *
     * @param zone  the time zone, not null
     * @return the current date-time, not null
     * @since 2.0
     */
    public static DateTime now(DateTimeZone zone) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[1]++;
            throw new NullPointerException("Zone must not be null");

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[2]++;}
        return new DateTime(zone);
    }

    /**
     * Obtains a {@code DateTime} set to the current system millisecond time
     * using the specified chronology.
     *
     * @param chronology  the chronology, not null
     * @return the current date-time, not null
     * @since 2.0
     */
    public static DateTime now(Chronology chronology) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronology == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[3]++;
            throw new NullPointerException("Chronology must not be null");

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[4]++;}
        return new DateTime(chronology);
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code DateTime} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#dateTimeParser()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static DateTime parse(String str) {
        return parse(str, ISODateTimeFormat.dateTimeParser().withOffsetParsed());
    }

    /**
     * Parses a {@code DateTime} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static DateTime parse(String str, DateTimeFormatter formatter) {
        return formatter.parseDateTime(str);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * 
     * @see #now()
     */
    public DateTime() {
        super();
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[4]++;
    }

    /**
     * Constructs an instance set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param zone  the time zone, null means default zone
     * @see #now(DateTimeZone)
     */
    public DateTime(DateTimeZone zone) {
        super(zone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[5]++;
    }

    /**
     * Constructs an instance set to the current system millisecond time
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param chronology  the chronology, null means ISOChronology in default zone
     * @see #now(Chronology)
     */
    public DateTime(Chronology chronology) {
        super(chronology);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[6]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using <code>ISOChronology</code> in the default time zone.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public DateTime(long instant) {
        super(instant);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[7]++;
    }

    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param zone  the time zone, null means default zone
     */
    public DateTime(long instant, DateTimeZone zone) {
        super(instant, zone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[8]++;
    }

    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public DateTime(long instant, Chronology chronology) {
        super(instant, chronology);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[9]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from an Object that represents a datetime.
     * <p>
     * If the object implies a chronology (such as GregorianCalendar does),
     * then that chronology will be used. Otherwise, ISO default is used.
     * Thus if a GregorianCalendar is passed in, the chronology used will
     * be GJ, but if a Date is passed in the chronology will be ISO.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#dateTimeParser()}.
     *
     * @param instant  the datetime object, null means now
     * @throws IllegalArgumentException if the instant is invalid
     */
    public DateTime(Object instant) {
        super(instant, (Chronology) null);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[10]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime,
     * forcing the time zone to that specified.
     * <p>
     * If the object implies a chronology (such as GregorianCalendar does),
     * then that chronology will be used, but with the time zone adjusted.
     * Otherwise, ISO is used in the specified time zone.
     * If the specified time zone is null, the default zone is used.
     * Thus if a GregorianCalendar is passed in, the chronology used will
     * be GJ, but if a Date is passed in the chronology will be ISO.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#dateTimeParser()}.
     *
     * @param instant  the datetime object, null means now
     * @param zone  the time zone, null means default time zone
     * @throws IllegalArgumentException if the instant is invalid
     */
    public DateTime(Object instant, DateTimeZone zone) {
        super(instant, zone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[11]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime,
     * using the specified chronology.
     * <p>
     * If the chronology is null, ISO in the default time zone is used.
     * Any chronology implied by the object (such as GregorianCalendar does)
     * is ignored.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#dateTimeParser()}.
     *
     * @param instant  the datetime object, null means now
     * @param chronology  the chronology, null means ISO in default zone
     * @throws IllegalArgumentException if the instant is invalid
     */
    public DateTime(Object instant, Chronology chronology) {
        super(instant, DateTimeUtils.getChronology(chronology));
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[12]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the default time zone.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @since 2.0
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour) {
        super(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[13]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param zone  the time zone, null means default time zone
     * @since 2.0
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            DateTimeZone zone) {
        super(year, monthOfYear, dayOfMonth,
              hourOfDay, minuteOfHour, 0, 0, zone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[14]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param chronology  the chronology, null means ISOChronology in default zone
     * @since 2.0
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            Chronology chronology) {
        super(year, monthOfYear, dayOfMonth,
              hourOfDay, minuteOfHour, 0, 0, chronology);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[15]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the default time zone.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @since 2.0
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute) {
        super(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, 0);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[16]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param zone  the time zone, null means default time zone
     * @since 2.0
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            DateTimeZone zone) {
        super(year, monthOfYear, dayOfMonth,
              hourOfDay, minuteOfHour, secondOfMinute, 0, zone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[17]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param chronology  the chronology, null means ISOChronology in default zone
     * @since 2.0
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            Chronology chronology) {
        super(year, monthOfYear, dayOfMonth,
              hourOfDay, minuteOfHour, secondOfMinute, 0, chronology);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[18]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the default time zone.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {
        super(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[19]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @param zone  the time zone, null means default time zone
     */
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond,
            DateTimeZone zone) {
        super(year, monthOfYear, dayOfMonth,
              hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond, zone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[20]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
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
    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond,
            Chronology chronology) {
        super(year, monthOfYear, dayOfMonth,
              hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond, chronology);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[21]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Get this object as a DateTime by returning <code>this</code>.
     * 
     * @return <code>this</code>
     */
    public DateTime toDateTime() {
        return this;
    }

    /**
     * Get this object as a DateTime using ISOChronology in the default zone,
     * returning <code>this</code> if possible.
     * 
     * @return a DateTime using the same millis
     */
    public DateTime toDateTimeISO() {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((getChronology() == ISOChronology.getInstance()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[5]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[6]++;}
        return super.toDateTimeISO();
    }

    /**
     * Get this object as a DateTime, returning <code>this</code> if possible.
     * 
     * @param zone time zone to apply, or default if null
     * @return a DateTime using the same millis
     */
    public DateTime toDateTime(DateTimeZone zone) {
        zone = DateTimeUtils.getZone(zone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[23]++;
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getZone() == zone) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[7]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[8]++;}
        return super.toDateTime(zone);
    }

    /**
     * Get this object as a DateTime, returning <code>this</code> if possible.
     * 
     * @param chronology chronology to apply, or ISOChronology if null
     * @return a DateTime using the same millis
     */
    public DateTime toDateTime(Chronology chronology) {
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[25]++;
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getChronology() == chronology) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[9]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[10]++;}
        return super.toDateTime(chronology);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with different millis.
     * <p>
     * The returned object will be either be a new instance or <code>this</code>.
     * Only the millis will change, the chronology and time zone are kept.
     *
     * @param newMillis  the new millis, from 1970-01-01T00:00:00Z
     * @return a copy of this datetime with different millis
     */
    public DateTime withMillis(long newMillis) {
        return (newMillis == getMillis() ? this : new DateTime(newMillis, getChronology()));
    }

    /**
     * Returns a copy of this datetime with a different chronology.
     * <p>
     * The returned object will be either be a new instance or <code>this</code>.
     * Only the chronology will change, the millis are kept.
     *
     * @param newChronology  the new chronology, null means ISO default
     * @return a copy of this datetime with a different chronology
     */
    public DateTime withChronology(Chronology newChronology) {
        newChronology = DateTimeUtils.getChronology(newChronology);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[27]++;
        return (newChronology == getChronology() ? this : new DateTime(getMillis(), newChronology));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with a different time zone, preserving the
     * millisecond instant.
     * <p>
     * This method is useful for finding the local time in another timezone.
     * For example, if this instant holds 12:30 in Europe/London, the result
     * from this method with Europe/Paris would be 13:30.
     * <p>
     * The returned object will be a new instance of the same implementation type.
     * This method changes the time zone, and does not change the
     * millisecond instant, with the effect that the field values usually change.
     * The returned object will be either be a new instance or <code>this</code>.
     *
     * @param newZone  the new time zone
     * @return a copy of this datetime with a different time zone
     * @see #withZoneRetainFields
     */
    public DateTime withZone(DateTimeZone newZone) {
        return withChronology(getChronology().withZone(newZone));
    }

    /**
     * Returns a copy of this datetime with a different time zone, preserving the
     * field values.
     * <p>
     * This method is useful for finding the millisecond time in another timezone.
     * For example, if this instant holds 12:30 in Europe/London (ie. 12:30Z),
     * the result from this method with Europe/Paris would be 12:30 (ie. 11:30Z).
     * <p>
     * The returned object will be a new instance of the same implementation type.
     * This method changes the time zone and the millisecond instant to keep
     * the field values the same.
     * The returned object will be either be a new instance or <code>this</code>.
     *
     * @param newZone  the new time zone, null means default
     * @return a copy of this datetime with a different time zone
     * @see #withZone
     */
    public DateTime withZoneRetainFields(DateTimeZone newZone) {
        newZone = DateTimeUtils.getZone(newZone);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[28]++;
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[29]++;
        DateTimeZone originalZone = DateTimeUtils.getZone(getZone());
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((newZone == originalZone) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[11]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[12]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[31]++;
        
        long millis = originalZone.getMillisKeepLocal(newZone, getMillis());
        return new DateTime(millis, getChronology().withZone(newZone));
    }

    /**
     * Returns a copy of this ZonedDateTime changing the zone offset to the earlier
     * of the two valid offsets at a local time-line overlap.
     * <p>
     * This method only has any effect when the local time-line overlaps, such as at
     * an autumn daylight savings cutover. In this scenario, there are two valid offsets
     * for the local date-time. Calling this method will return a date-time with the
     * earlier of the two selected.
     * <p>
     * If this method is called when it is not an overlap, this is returned.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @return a copy of this datetime with the earliest valid offset for the local datetime
     */
    public DateTime withEarlierOffsetAtOverlap() {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[32]++;
        long newMillis = getZone().adjustOffset(getMillis(), false);
        return withMillis(newMillis);
    }

    /**
     * Returns a copy of this ZonedDateTime changing the zone offset to the later
     * of the two valid offsets at a local time-line overlap.
     * <p>
     * This method only has any effect when the local time-line overlaps, such as at
     * an autumn daylight savings cutover. In this scenario, there are two valid offsets
     * for the local date-time. Calling this method will return a date-time with the
     * later of the two selected.
     * <p>
     * If this method is called when it is not an overlap, this is returned.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @return a copy of this datetime with the latest valid offset for the local datetime
     */
    public DateTime withLaterOffsetAtOverlap() {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[33]++;
        long newMillis = getZone().adjustOffset(getMillis(), true);
        return withMillis(newMillis);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified date, retaining the time fields.
     * <p>
     * If the date is already the date passed in, then <code>this</code> is returned.
     * <p>
     * To set a single field use the properties, for example:
     * <pre>
     * DateTime set = monthOfYear().setCopy(6);
     * </pre>
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param year  the new year value
     * @param monthOfYear  the new monthOfYear value
     * @param dayOfMonth  the new dayOfMonth value
     * @return a copy of this datetime with a different date
     * @throws IllegalArgumentException if any value if invalid
     */
    public DateTime withDate(int year, int monthOfYear, int dayOfMonth) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[34]++;
        Chronology chrono = getChronology();
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[35]++;
        long instant = getMillis();
        instant = chrono.year().set(instant, year);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[36]++;
        instant = chrono.monthOfYear().set(instant, monthOfYear);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[37]++;
        instant = chrono.dayOfMonth().set(instant, dayOfMonth);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[38]++;
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime with the specified time, retaining the date fields.
     * <p>
     * If the time is already the time passed in, then <code>this</code> is returned.
     * <p>
     * To set a single field use the properties, for example:
     * <pre>
     * DateTime set = dt.hourOfDay().setCopy(6);
     * </pre>
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @return a copy of this datetime with a different time
     * @throws IllegalArgumentException if any value if invalid
     */
    public DateTime withTime(int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[39]++;
        Chronology chrono = getChronology();
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[40]++;
        long instant = getMillis();
        instant = chrono.hourOfDay().set(instant, hourOfDay);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[41]++;
        instant = chrono.minuteOfHour().set(instant, minuteOfHour);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[42]++;
        instant = chrono.secondOfMinute().set(instant, secondOfMinute);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[43]++;
        instant = chrono.millisOfSecond().set(instant, millisOfSecond);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[44]++;
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime with the time set to the start of the day.
     * <p>
     * The time will normally be midnight, as that is the earliest time on
     * any given day. However, in some time zones when Daylight Savings Time
     * starts, there is no midnight because time jumps from 11:59 to 01:00.
     * This method handles that situation by returning 01:00 on that date.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @return a copy of this datetime with the time set to the start of the day, not null
     */
    public DateTime withTimeAtStartOfDay() {
        return toLocalDate().toDateTimeAtStartOfDay(getZone());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the partial set of fields replacing those
     * from this instance.
     * <p>
     * For example, if the partial is a <code>TimeOfDay</code> then the time fields
     * would be changed in the returned instance.
     * If the partial is null, then <code>this</code> is returned.
     *
     * @param partial  the partial set of fields to apply to this datetime, null ignored
     * @return a copy of this datetime with a different set of fields
     * @throws IllegalArgumentException if any value is invalid
     */
    public DateTime withFields(ReadablePartial partial) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[45]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[13]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[14]++;}
        return withMillis(getChronology().set(partial, getMillis()));
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
     * DateTime updated = dt.withField(DateTimeFieldType.dayOfMonth(), 6);
     * DateTime updated = dt.dayOfMonth().setCopy(6);
     * DateTime updated = dt.property(DateTimeFieldType.dayOfMonth()).setCopy(6);
     * </pre>
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this datetime with the field set
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public DateTime withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[15]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[16]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[47]++;
        long instant = fieldType.getField(getChronology()).set(getMillis(), value);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime with the value of the specified field increased.
     * <p>
     * If the addition is zero or the field is null, then <code>this</code> is returned.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * DateTime added = dt.withFieldAdded(DurationFieldType.years(), 6);
     * DateTime added = dt.plusYears(6);
     * DateTime added = dt.plus(Period.years(6));
     * </pre>
     * 
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this datetime with the field updated
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[17]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[18]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[49]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[19]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[20]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[50]++;
        long instant = fieldType.getField(getChronology()).add(getMillis(), amount);
        return withMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified duration added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * 
     * @param durationToAdd  the duration to add to this one
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime withDurationAdded(long durationToAdd, int scalar) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((durationToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[21]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[22]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[52]++;
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime with the specified duration added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * 
     * @param durationToAdd  the duration to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime withDurationAdded(ReadableDuration durationToAdd, int scalar) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((durationToAdd == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[23]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[24]++;}
        return withDurationAdded(durationToAdd.getMillis(), scalar);
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
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[25]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[26]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[55]++;
        long instant = getChronology().add(period, getMillis(), scalar);
        return withMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified duration added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * This datetime instance is immutable and unaffected by this method call.
     * 
     * @param duration  the duration, in millis, to add to this one
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime plus(long duration) {
        return withDurationAdded(duration, 1);
    }

    /**
     * Returns a copy of this datetime with the specified duration added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * This datetime instance is immutable and unaffected by this method call.
     * 
     * @param duration  the duration to add to this one, null means zero
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime plus(ReadableDuration duration) {
        return withDurationAdded(duration, 1);
    }

    /**
     * Returns a copy of this datetime with the specified period added.
     * <p>
     * This method will add each element of the period one by one, from largest
     * to smallest, adjusting the datetime to be accurate between each.
     * <p>
     * Thus, adding a period of one month and one day to 2007-03-31 will
     * work as follows:
     * First add one month and adjust, resulting in 2007-04-30
     * Then add one day and adjust, resulting in 2007-05-01.
     * <p>
     * This method is typically used to add complex period instances.
     * Adding one field is best achieved using methods
     * like {@link #plusYears(int)}.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * This datetime instance is immutable and unaffected by this method call.
     * 
     * @param period  the duration to add to this one, null means zero
     * @return a copy of this datetime with the period added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime plus the specified number of years.
     * <p>
     * The calculation will do its best to only change the year field
     * retaining the same month of year.
     * However, in certain circumstances, it may be necessary to alter
     * smaller fields. For example, 2008-02-29 plus one year cannot result
     * in 2009-02-29, so the day of month is adjusted to 2009-02-28.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusYears(6);
     * DateTime added = dt.plus(Period.years(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.years(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param years  the amount of years to add, may be negative
     * @return the new datetime plus the increased years
     * @since 1.1
     */
    public DateTime plusYears(int years) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[27]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[28]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[57]++;
        long instant = getChronology().years().add(getMillis(), years);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of months.
     * <p>
     * The calculation will do its best to only change the month field
     * retaining the same day of month.
     * However, in certain circumstances, it may be necessary to alter
     * smaller fields. For example, 2007-03-31 plus one month cannot result
     * in 2007-04-31, so the day of month is adjusted to 2007-04-30.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusMonths(6);
     * DateTime added = dt.plus(Period.months(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.months(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param months  the amount of months to add, may be negative
     * @return the new datetime plus the increased months
     * @since 1.1
     */
    public DateTime plusMonths(int months) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[58]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[29]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[30]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[59]++;
        long instant = getChronology().months().add(getMillis(), months);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of weeks.
     * <p>
     * The calculation operates as if it were adding the equivalent in days.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusWeeks(6);
     * DateTime added = dt.plus(Period.weeks(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.weeks(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param weeks  the amount of weeks to add, may be negative
     * @return the new datetime plus the increased weeks
     * @since 1.1
     */
    public DateTime plusWeeks(int weeks) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((weeks == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[31]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[32]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[61]++;
        long instant = getChronology().weeks().add(getMillis(), weeks);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of days.
     * <p>
     * The calculation will do its best to only change the day field
     * retaining the same time of day.
     * However, in certain circumstances, typically daylight savings cutover,
     * it may be necessary to alter the time fields.
     * <p>
     * In spring an hour is typically removed. If adding one day results in
     * the time being within the cutover then the time is adjusted to be
     * within summer time. For example, if the cutover is from 01:59 to 03:00
     * and the result of this method would have been 02:30, then the result
     * will be adjusted to 03:30.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusDays(6);
     * DateTime added = dt.plus(Period.days(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.days(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param days  the amount of days to add, may be negative
     * @return the new datetime plus the increased days
     * @since 1.1
     */
    public DateTime plusDays(int days) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[33]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[34]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[63]++;
        long instant = getChronology().days().add(getMillis(), days);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of hours.
     * <p>
     * The calculation will add a duration equivalent to the number of hours
     * expressed in milliseconds.
     * <p>
     * For example, if a spring daylight savings cutover is from 01:59 to 03:00
     * then adding one hour to 01:30 will result in 03:30. This is a duration
     * of one hour later, even though the hour field value changed from 1 to 3.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusHours(6);
     * DateTime added = dt.plus(Period.hours(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.hours(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param hours  the amount of hours to add, may be negative
     * @return the new datetime plus the increased hours
     * @since 1.1
     */
    public DateTime plusHours(int hours) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[64]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[35]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[36]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[65]++;
        long instant = getChronology().hours().add(getMillis(), hours);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of minutes.
     * <p>
     * The calculation will add a duration equivalent to the number of minutes
     * expressed in milliseconds.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusMinutes(6);
     * DateTime added = dt.plus(Period.minutes(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.minutes(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param minutes  the amount of minutes to add, may be negative
     * @return the new datetime plus the increased minutes
     * @since 1.1
     */
    public DateTime plusMinutes(int minutes) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[66]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((minutes == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[37]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[38]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[67]++;
        long instant = getChronology().minutes().add(getMillis(), minutes);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of seconds.
     * <p>
     * The calculation will add a duration equivalent to the number of seconds
     * expressed in milliseconds.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusSeconds(6);
     * DateTime added = dt.plus(Period.seconds(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.seconds(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param seconds  the amount of seconds to add, may be negative
     * @return the new datetime plus the increased seconds
     * @since 1.1
     */
    public DateTime plusSeconds(int seconds) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[68]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[39]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[40]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[69]++;
        long instant = getChronology().seconds().add(getMillis(), seconds);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime plus the specified number of millis.
     * <p>
     * The calculation will add a duration equivalent to the number of milliseconds.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime added = dt.plusMillis(6);
     * DateTime added = dt.plus(Period.millis(6));
     * DateTime added = dt.withFieldAdded(DurationFieldType.millis(), 6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param millis  the amount of millis to add, may be negative
     * @return the new datetime plus the increased millis
     * @since 1.1
     */
    public DateTime plusMillis(int millis) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[70]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((millis == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[41]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[42]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[71]++;
        long instant = getChronology().millis().add(getMillis(), millis);
        return withMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the specified duration taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * This datetime instance is immutable and unaffected by this method call.
     * 
     * @param duration  the duration, in millis, to reduce this instant by
     * @return a copy of this datetime with the duration taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime minus(long duration) {
        return withDurationAdded(duration, -1);
    }

    /**
     * Returns a copy of this datetime with the specified duration taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * This datetime instance is immutable and unaffected by this method call.
     * 
     * @param duration  the duration to reduce this instant by
     * @return a copy of this datetime with the duration taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime minus(ReadableDuration duration) {
        return withDurationAdded(duration, -1);
    }

    /**
     * Returns a copy of this datetime with the specified period taken away.
     * <p>
     * This method will subtract each element of the period one by one, from
     * largest to smallest, adjusting the datetime to be accurate between each.
     * <p>
     * Thus, subtracting a period of one month and one day from 2007-05-31 will
     * work as follows:
     * First subtract one month and adjust, resulting in 2007-04-30
     * Then subtract one day and adjust, resulting in 2007-04-29.
     * Note that the day has been adjusted by two.
     * <p>
     * This method is typically used to subtract complex period instances.
     * Subtracting one field is best achieved using methods
     * like {@link #minusYears(int)}.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * This datetime instance is immutable and unaffected by this method call.
     * 
     * @param period  the period to reduce this instant by
     * @return a copy of this datetime with the period taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateTime minus(ReadablePeriod period) {
        return withPeriodAdded(period, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime minus the specified number of years.
     * <p>
     * The calculation will do its best to only change the year field
     * retaining the same month of year.
     * However, in certain circumstances, it may be necessary to alter
     * smaller fields. For example, 2008-02-29 minus one year cannot result
     * in 2007-02-29, so the day of month is adjusted to 2007-02-28.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusYears(6);
     * DateTime subtracted = dt.minus(Period.years(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param years  the amount of years to subtract, may be negative
     * @return the new datetime minus the increased years
     * @since 1.1
     */
    public DateTime minusYears(int years) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[72]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[43]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[44]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[73]++;
        long instant = getChronology().years().subtract(getMillis(), years);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of months.
     * <p>
     * The calculation will do its best to only change the month field
     * retaining the same day of month.
     * However, in certain circumstances, it may be necessary to alter
     * smaller fields. For example, 2007-05-31 minus one month cannot result
     * in 2007-04-31, so the day of month is adjusted to 2007-04-30.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusMonths(6);
     * DateTime subtracted = dt.minus(Period.months(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.months(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param months  the amount of months to subtract, may be negative
     * @return the new datetime minus the increased months
     * @since 1.1
     */
    public DateTime minusMonths(int months) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[74]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[45]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[46]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[75]++;
        long instant = getChronology().months().subtract(getMillis(), months);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of weeks.
     * <p>
     * The calculation operates as if it were subtracting the equivalent in days.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusWeeks(6);
     * DateTime subtracted = dt.minus(Period.weeks(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.weeks(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param weeks  the amount of weeks to subtract, may be negative
     * @return the new datetime minus the increased weeks
     * @since 1.1
     */
    public DateTime minusWeeks(int weeks) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[76]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((weeks == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[47]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[48]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[77]++;
        long instant = getChronology().weeks().subtract(getMillis(), weeks);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of days.
     * <p>
     * The calculation will do its best to only change the day field
     * retaining the same time of day.
     * However, in certain circumstances, typically daylight savings cutover,
     * it may be necessary to alter the time fields.
     * <p>
     * In spring an hour is typically removed. If subtracting one day results
     * in the time being within the cutover then the time is adjusted to be
     * within summer time. For example, if the cutover is from 01:59 to 03:00
     * and the result of this method would have been 02:30, then the result
     * will be adjusted to 03:30.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusDays(6);
     * DateTime subtracted = dt.minus(Period.days(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.days(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param days  the amount of days to subtract, may be negative
     * @return the new datetime minus the increased days
     * @since 1.1
     */
    public DateTime minusDays(int days) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[78]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[49]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[50]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[79]++;
        long instant = getChronology().days().subtract(getMillis(), days);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of hours.
     * <p>
     * The calculation will subtract a duration equivalent to the number of
     * hours expressed in milliseconds.
     * <p>
     * For example, if a spring daylight savings cutover is from 01:59 to 03:00
     * then subtracting one hour from 03:30 will result in 01:30. This is a
     * duration of one hour earlier, even though the hour field value changed
     * from 3 to 1.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusHours(6);
     * DateTime subtracted = dt.minus(Period.hours(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.hours(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param hours  the amount of hours to subtract, may be negative
     * @return the new datetime minus the increased hours
     * @since 1.1
     */
    public DateTime minusHours(int hours) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[80]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[51]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[52]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[81]++;
        long instant = getChronology().hours().subtract(getMillis(), hours);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of minutes.
     * <p>
     * The calculation will subtract a duration equivalent to the number of
     * minutes expressed in milliseconds.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusMinutes(6);
     * DateTime subtracted = dt.minus(Period.minutes(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.minutes(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param minutes  the amount of minutes to subtract, may be negative
     * @return the new datetime minus the increased minutes
     * @since 1.1
     */
    public DateTime minusMinutes(int minutes) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[82]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((minutes == 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[53]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[54]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[83]++;
        long instant = getChronology().minutes().subtract(getMillis(), minutes);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of seconds.
     * <p>
     * The calculation will subtract a duration equivalent to the number of
     * seconds expressed in milliseconds.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusSeconds(6);
     * DateTime subtracted = dt.minus(Period.seconds(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.seconds(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param seconds  the amount of seconds to subtract, may be negative
     * @return the new datetime minus the increased seconds
     * @since 1.1
     */
    public DateTime minusSeconds(int seconds) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[84]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[55]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[56]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[85]++;
        long instant = getChronology().seconds().subtract(getMillis(), seconds);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this datetime minus the specified number of millis.
     * <p>
     * The calculation will subtract a duration equivalent to the number of
     * milliseconds.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusMillis(6);
     * DateTime subtracted = dt.minus(Period.millis(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.millis(), -6);
     * </pre>
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     *
     * @param millis  the amount of millis to subtract, may be negative
     * @return the new datetime minus the increased millis
     * @since 1.1
     */
    public DateTime minusMillis(int millis) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[86]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((millis == 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[57]++;
            return this;

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[58]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[87]++;
        long instant = getChronology().millis().subtract(getMillis(), millis);
        return withMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the property object for the specified type, which contains many useful methods.
     *
     * @param type  the field type to get the chronology for
     * @return the property object
     * @throws IllegalArgumentException if the field is null or unsupported
     */
    public Property property(DateTimeFieldType type) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[88]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[59]++;
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[60]++;}
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[89]++;
        DateTimeField field = type.getField(getChronology());
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[90]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((field.isSupported() == false) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[61]++;
            throw new IllegalArgumentException("Field '" + type + "' is not supported");

        } else {
  CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.branches[62]++;}
        return new Property(this, field);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a <code>DateMidnight</code> using the
     * same millis and chronology.
     * 
     * @return a DateMidnight using the same millis and chronology
     */
    public DateMidnight toDateMidnight() {
        return new DateMidnight(getMillis(), getChronology());
    }

    /**
     * Converts this object to a <code>YearMonthDay</code> using the
     * same millis and chronology.
     * 
     * @return a YearMonthDay using the same millis and chronology
     * @deprecated Use LocalDate instead of YearMonthDay
     */
    @Deprecated
    public YearMonthDay toYearMonthDay() {
        return new YearMonthDay(getMillis(), getChronology());
    }

    /**
     * Converts this object to a <code>TimeOfDay</code> using the
     * same millis and chronology.
     * 
     * @return a TimeOfDay using the same millis and chronology
     * @deprecated Use LocalTime instead of TimeOfDay
     */
    @Deprecated
    public TimeOfDay toTimeOfDay() {
        return new TimeOfDay(getMillis(), getChronology());
    }

    /**
     * Converts this object to a <code>LocalDateTime</code> with
     * the same datetime and chronology.
     *
     * @return a LocalDateTime with the same datetime and chronology
     * @since 1.3
     */
    public LocalDateTime toLocalDateTime() {
        return new LocalDateTime(getMillis(), getChronology());
    }

    /**
     * Converts this object to a <code>LocalDate</code> with the
     * same date and chronology.
     *
     * @return a LocalDate with the same date and chronology
     * @since 1.3
     */
    public LocalDate toLocalDate() {
        return new LocalDate(getMillis(), getChronology());
    }

    /**
     * Converts this object to a <code>LocalTime</code> with the
     * same time and chronology.
     *
     * @return a LocalTime with the same time and chronology
     * @since 1.3
     */
    public LocalTime toLocalTime() {
        return new LocalTime(getMillis(), getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the era field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * era changed.
     *
     * @param era  the era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withEra(int era) {
        return withMillis(getChronology().era().set(getMillis(), era));
    }

    /**
     * Returns a copy of this datetime with the century of era field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * century of era changed.
     *
     * @param centuryOfEra  the centurey of era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withCenturyOfEra(int centuryOfEra) {
        return withMillis(getChronology().centuryOfEra().set(getMillis(), centuryOfEra));
    }

    /**
     * Returns a copy of this datetime with the year of era field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year of era changed.
     *
     * @param yearOfEra  the year of era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withYearOfEra(int yearOfEra) {
        return withMillis(getChronology().yearOfEra().set(getMillis(), yearOfEra));
    }

    /**
     * Returns a copy of this datetime with the year of century field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year of century changed.
     *
     * @param yearOfCentury  the year of century to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withYearOfCentury(int yearOfCentury) {
        return withMillis(getChronology().yearOfCentury().set(getMillis(), yearOfCentury));
    }

    /**
     * Returns a copy of this datetime with the year field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year changed.
     *
     * @param year  the year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withYear(int year) {
        return withMillis(getChronology().year().set(getMillis(), year));
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
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * weekyear changed.
     *
     * @param weekyear  the weekyear to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withWeekyear(int weekyear) {
        return withMillis(getChronology().weekyear().set(getMillis(), weekyear));
    }

    /**
     * Returns a copy of this datetime with the month of year field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * month of year changed.
     *
     * @param monthOfYear  the month of year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withMonthOfYear(int monthOfYear) {
        return withMillis(getChronology().monthOfYear().set(getMillis(), monthOfYear));
    }

    /**
     * Returns a copy of this datetime with the week of weekyear field updated.
     * <p>
     * This field is associated with the "weekyear" via {@link #withWeekyear(int)}.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * week of weekyear changed.
     *
     * @param weekOfWeekyear  the week of weekyear to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withWeekOfWeekyear(int weekOfWeekyear) {
        return withMillis(getChronology().weekOfWeekyear().set(getMillis(), weekOfWeekyear));
    }

    /**
     * Returns a copy of this datetime with the day of year field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of year changed.
     *
     * @param dayOfYear  the day of year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withDayOfYear(int dayOfYear) {
        return withMillis(getChronology().dayOfYear().set(getMillis(), dayOfYear));
    }

    /**
     * Returns a copy of this datetime with the day of month field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of month changed.
     *
     * @param dayOfMonth  the day of month to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withDayOfMonth(int dayOfMonth) {
        return withMillis(getChronology().dayOfMonth().set(getMillis(), dayOfMonth));
    }

    /**
     * Returns a copy of this datetime with the day of week field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of week changed.
     *
     * @param dayOfWeek  the day of week to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withDayOfWeek(int dayOfWeek) {
        return withMillis(getChronology().dayOfWeek().set(getMillis(), dayOfWeek));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this datetime with the hour of day field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * hour of day changed.
     *
     * @param hour  the hour of day to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withHourOfDay(int hour) {
        return withMillis(getChronology().hourOfDay().set(getMillis(), hour));
    }

    /**
     * Returns a copy of this datetime with the minute of hour updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * minute of hour changed.
     *
     * @param minute  the minute of hour to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withMinuteOfHour(int minute) {
        return withMillis(getChronology().minuteOfHour().set(getMillis(), minute));
    }

    /**
     * Returns a copy of this datetime with the second of minute field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * second of minute changed.
     *
     * @param second  the second of minute to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withSecondOfMinute(int second) {
        return withMillis(getChronology().secondOfMinute().set(getMillis(), second));
    }

    /**
     * Returns a copy of this datetime with the millis of second field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * millis of second changed.
     *
     * @param millis  the millis of second to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withMillisOfSecond(int millis) {
        return withMillis(getChronology().millisOfSecond().set(getMillis(), millis));
    }

    /**
     * Returns a copy of this datetime with the millis of day field updated.
     * <p>
     * DateTime is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * millis of day changed.
     *
     * @param millis  the millis of day to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateTime withMillisOfDay(int millis) {
        return withMillis(getChronology().millisOfDay().set(getMillis(), millis));
    }

    // Date properties
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
     * Get the year of a week based year property which provides access to advanced functionality.
     * 
     * @return the year of a week based year property
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

    // Time properties
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
     * Get the minute of day property which provides access to advanced functionality.
     * 
     * @return the minute of day property
     */
    public Property minuteOfDay() {
        return new Property(this, getChronology().minuteOfDay());
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
     * Get the second of day property which provides access to advanced functionality.
     * 
     * @return the second of day property
     */
    public Property secondOfDay() {
        return new Property(this, getChronology().secondOfDay());
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
     * Get the millis of day property which provides access to advanced functionality.
     * 
     * @return the millis of day property
     */
    public Property millisOfDay() {
        return new Property(this, getChronology().millisOfDay());
    }

    /**
     * Get the millis of second property which provides access to advanced functionality.
     * 
     * @return the millis of second property
     */
    public Property millisOfSecond() {
        return new Property(this, getChronology().millisOfSecond());
    }

    //-----------------------------------------------------------------------
    /**
     * DateTime.Property binds a DateTime to a DateTimeField allowing powerful
     * datetime functionality to be easily accessed.
     * <p>
     * The simplest use of this class is as an alternative get method, here used to
     * get the year '1972' (as an int) and the month 'December' (as a String).
     * <pre>
     * DateTime dt = new DateTime(1972, 12, 3, 0, 0, 0, 0);
     * int year = dt.year().get();
     * String monthStr = dt.month().getAsText();
     * </pre>
     * <p>
     * Methods are also provided that allow date modification. These return new instances
     * of DateTime - they do not modify the original. The example below yields two
     * independent immutable date objects 20 years apart.
     * <pre>
     * DateTime dt = new DateTime(1972, 12, 3, 0, 0, 0, 0);
     * DateTime dt20 = dt.year().addToCopy(20);
     * </pre>
     * Serious modification of dates (ie. more than just changing one or two fields)
     * should use the {@link org.joda.time.MutableDateTime MutableDateTime} class.
     * <p>
     * DateTime.Propery itself is thread-safe and immutable, as well as the
     * DateTime being operated on.
     *
     * @author Stephen Colebourne
     * @author Brian S O'Neill
     * @since 1.0
     */
    public static final class Property extends AbstractReadableInstantFieldProperty {
        
        /** Serialization version */
        private static final long serialVersionUID = -6983323811635733510L;
        
        /** The instant this property is working against */
        private DateTime iInstant;
        /** The field this property is working against */
        private DateTimeField iField;
        
        /**
         * Constructor.
         * 
         * @param instant  the instant to set
         * @param field  the field to use
         */
        Property(DateTime instant, DateTimeField field) {
            super();
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[91]++;
            iInstant = instant;
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[92]++;
            iField = field;
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[93]++;
        }
        
        /**
         * Writes the property in a safe serialization format.
         */
        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.writeObject(iInstant);
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[94]++;
            oos.writeObject(iField.getType());
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[95]++;
        }

        /**
         * Reads the property from a safe serialization format.
         */
        private void readObject(ObjectInputStream oos) throws IOException, ClassNotFoundException {
            iInstant = (DateTime) oos.readObject();
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[96]++;
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[97]++;
            DateTimeFieldType type = (DateTimeFieldType) oos.readObject();
            iField = type.getField(iInstant.getChronology());
CodeCoverCoverageCounter$el931ee1pozkdxl0at7l.statements[98]++;
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
            return iInstant.getMillis();
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
         * Gets the datetime being used.
         * 
         * @return the datetime
         */
        public DateTime getDateTime() {
            return iInstant;
        }
        
        //-----------------------------------------------------------------------
        /**
         * Adds to this field in a copy of this DateTime.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         * This operation is faster than converting a DateTime to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to add to the field in the copy
         * @return a copy of the DateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateTime addToCopy(int value) {
            return iInstant.withMillis(iField.add(iInstant.getMillis(), value));
        }
        
        /**
         * Adds to this field in a copy of this DateTime.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         * This operation is faster than converting a DateTime to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to add to the field in the copy
         * @return a copy of the DateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateTime addToCopy(long value) {
            return iInstant.withMillis(iField.add(iInstant.getMillis(), value));
        }
        
        /**
         * Adds to this field, possibly wrapped, in a copy of this DateTime.
         * A wrapped operation only changes this field.
         * Thus 31st January addWrapField one day goes to the 1st January.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         * This operation is faster than converting a DateTime to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to add to the field in the copy
         * @return a copy of the DateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateTime addWrapFieldToCopy(int value) {
            return iInstant.withMillis(iField.addWrapField(iInstant.getMillis(), value));
        }
        
        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the DateTime.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         * This operation is faster than converting a DateTime to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to set the field in the copy to
         * @return a copy of the DateTime with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateTime setCopy(int value) {
            return iInstant.withMillis(iField.set(iInstant.getMillis(), value));
        }
        
        /**
         * Sets this field in a copy of the DateTime to a parsed text value.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         * This operation is faster than converting a DateTime to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the DateTime with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public DateTime setCopy(String text, Locale locale) {
            return iInstant.withMillis(iField.set(iInstant.getMillis(), text, locale));
        }
        
        /**
         * Sets this field in a copy of the DateTime to a parsed text value.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         * This operation is faster than converting a DateTime to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param text  the text value to set
         * @return a copy of the DateTime with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public DateTime setCopy(String text) {
            return setCopy(text, null);
        }
        
        //-----------------------------------------------------------------------
        /**
         * Returns a new DateTime with this field set to the maximum value
         * for this field.
         * <p>
         * This operation is useful for obtaining a DateTime on the last day
         * of the month, as month lengths vary.
         * <pre>
         * DateTime lastDayOfMonth = dt.dayOfMonth().withMaximumValue();
         * </pre>
         * <p>
         * Where possible, the offset from UTC will be retained, thus applications
         * may need to call {@link DateTime#withLaterOffsetAtOverlap()} on the result
         * to force the later time during a DST overlap if desired.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         *
         * @return a copy of the DateTime with this field set to its maximum
         * @since 1.2
         */
        public DateTime withMaximumValue() {
            return setCopy(getMaximumValue());
        }
        
        /**
         * Returns a new DateTime with this field set to the minimum value
         * for this field.
         * <p>
         * Where possible, the offset from UTC will be retained, thus applications
         * may need to call {@link DateTime#withEarlierOffsetAtOverlap()} on the result
         * to force the earlier time during a DST overlap if desired.
         * <p>
         * The DateTime attached to this property is unchanged by this call.
         *
         * @return a copy of the DateTime with this field set to its minimum
         * @since 1.2
         */
        public DateTime withMinimumValue() {
            return setCopy(getMinimumValue());
        }
        
        //-----------------------------------------------------------------------
        /**
         * Rounds to the lowest whole unit of this field on a copy of this DateTime.
         *
         * @return a copy of the DateTime with the field value changed
         */
        public DateTime roundFloorCopy() {
            return iInstant.withMillis(iField.roundFloor(iInstant.getMillis()));
        }
        
        /**
         * Rounds to the highest whole unit of this field on a copy of this DateTime.
         *
         * @return a copy of the DateTime with the field value changed
         */
        public DateTime roundCeilingCopy() {
            return iInstant.withMillis(iField.roundCeiling(iInstant.getMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this DateTime,
         * favoring the floor if halfway.
         *
         * @return a copy of the DateTime with the field value changed
         */
        public DateTime roundHalfFloorCopy() {
            return iInstant.withMillis(iField.roundHalfFloor(iInstant.getMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this DateTime,
         * favoring the ceiling if halfway.
         *
         * @return a copy of the DateTime with the field value changed
         */
        public DateTime roundHalfCeilingCopy() {
            return iInstant.withMillis(iField.roundHalfCeiling(iInstant.getMillis()));
        }
        
        /**
         * Rounds to the nearest whole unit of this field on a copy of this
         * DateTime.  If halfway, the ceiling is favored over the floor only if
         * it makes this field's value even.
         *
         * @return a copy of the DateTime with the field value changed
         */
        public DateTime roundHalfEvenCopy() {
            return iInstant.withMillis(iField.roundHalfEven(iInstant.getMillis()));
        }
    }

}

class CodeCoverCoverageCounter$el931ee1pozkdxl0at7l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el931ee1pozkdxl0at7l ());
  }
    public static long[] statements = new long[99];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "org.joda.time.DateTime.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
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

  public CodeCoverCoverageCounter$el931ee1pozkdxl0at7l () {
    super("org.joda.time.DateTime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 98; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.DateTime.java");
      for (int i = 1; i <= 98; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
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

