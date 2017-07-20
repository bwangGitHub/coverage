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
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * DateMidnight defines a date where the time component is fixed at midnight.
 * The class uses a time zone, thus midnight is local unless a UTC time zone is used.
 * <p>
 * It is important to emphasise that this class represents the time of midnight on
 * any given day.
 * Note that midnight is defined as 00:00, which is at the very start of a day.
 * <p>
 * This class does not represent a day, but the millisecond instant at midnight.
 * If you need a class that represents the whole day, then an {@link Interval} or
 * a {@link LocalDate} may be more suitable.
 * <p>
 * This class uses a Chronology internally. The Chronology determines how the
 * millisecond instant value is converted into the date time fields.
 * The default Chronology is <code>ISOChronology</code> which is the agreed
 * international standard and compatable with the modern Gregorian calendar.
 *
 * <p>Each individual field can be queried in two ways:
 * <ul>
 * <li><code>getDayOfMonth()</code>
 * <li><code>dayOfMonth().get()</code>
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
 * DateMidnight is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class DateMidnight
        extends BaseDateTime
        implements ReadableDateTime, Serializable {
  static {
    CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.ping();
  }

    
    /** Serialization lock */
    private static final long serialVersionUID = 156371964018738L;
  static {
    CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[1]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains a {@code DateMidnight} set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * The constructed object will have a local time of midnight.
     * 
     * @return the current date, not null
     * @since 2.0
     */
    public static DateMidnight now() {
        return new DateMidnight();
    }

    /**
     * Obtains a {@code DateMidnight} set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * The constructed object will have a local time of midnight.
     *
     * @param zone  the time zone, not null
     * @return the current date, not null
     * @since 2.0
     */
    public static DateMidnight now(DateTimeZone zone) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[1]++;
            throw new NullPointerException("Zone must not be null");

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[2]++;}
        return new DateMidnight(zone);
    }

    /**
     * Obtains a {@code DateMidnight} set to the current system millisecond time
     * using the specified chronology.
     * The constructed object will have a local time of midnight.
     *
     * @param chronology  the chronology, not null
     * @return the current date, not null
     * @since 2.0
     */
    public static DateMidnight now(Chronology chronology) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronology == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[3]++;
            throw new NullPointerException("Chronology must not be null");

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[4]++;}
        return new DateMidnight(chronology);
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code DateMidnight} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#dateTimeParser()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static DateMidnight parse(String str) {
        return parse(str, ISODateTimeFormat.dateTimeParser().withOffsetParsed());
    }

    /**
     * Parses a {@code DateMidnight} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static DateMidnight parse(String str, DateTimeFormatter formatter) {
        return formatter.parseDateTime(str).toDateMidnight();
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * The constructed object will have a local time of midnight.
     * 
     * @see #now()
     */
    public DateMidnight() {
        super();
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[4]++;
    }

    /**
     * Constructs an instance set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * The constructed object will have a local time of midnight.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param zone  the time zone, null means default zone
     * @see #now(DateTimeZone)
     */
    public DateMidnight(DateTimeZone zone) {
        super(zone);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[5]++;
    }

    /**
     * Constructs an instance set to the current system millisecond time
     * using the specified chronology.
     * The constructed object will have a local time of midnight.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param chronology  the chronology, null means ISOChronology in default zone
     * @see #now(Chronology)
     */
    public DateMidnight(Chronology chronology) {
        super(chronology);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[6]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using <code>ISOChronology</code> in the default time zone.
     * The constructed object will have a local time of midnight.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public DateMidnight(long instant) {
        super(instant);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[7]++;
    }

    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using <code>ISOChronology</code> in the specified time zone.
     * The constructed object will have a local time of midnight.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param zone  the time zone, null means default zone
     */
    public DateMidnight(long instant, DateTimeZone zone) {
        super(instant, zone);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[8]++;
    }

    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using the specified chronology.
     * The constructed object will have a local time of midnight.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public DateMidnight(long instant, Chronology chronology) {
        super(instant, chronology);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[9]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from an Object that represents a datetime.
     * The constructed object will have a local time of midnight.
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
    public DateMidnight(Object instant) {
        super(instant, (Chronology) null);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[10]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime,
     * forcing the time zone to that specified.
     * The constructed object will have a local time of midnight.
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
    public DateMidnight(Object instant, DateTimeZone zone) {
        super(instant, zone);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[11]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime,
     * using the specified chronology.
     * The constructed object will have a local time of midnight.
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
     * @param chronology  the chronology, null means ISOChronology in default zone
     * @throws IllegalArgumentException if the instant is invalid
     */
    public DateMidnight(Object instant, Chronology chronology) {
        super(instant, DateTimeUtils.getChronology(chronology));
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[12]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the default time zone.
     * The constructed object will have a local time of midnight.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     */
    public DateMidnight(int year, int monthOfYear, int dayOfMonth) {
        super(year, monthOfYear, dayOfMonth, 0, 0, 0, 0);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[13]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the specified time zone.
     * The constructed object will have a local time of midnight.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param zone  the time zone, null means default time zone
     */
    public DateMidnight(int year, int monthOfYear, int dayOfMonth, DateTimeZone zone) {
        super(year, monthOfYear, dayOfMonth, 0, 0, 0, 0, zone);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[14]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using the specified chronology.
     * The constructed object will have a local time of midnight.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public DateMidnight(int year, int monthOfYear, int dayOfMonth, Chronology chronology) {
        super(year, monthOfYear, dayOfMonth, 0, 0, 0, 0, chronology);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[15]++;
    }

    /**
     * Rounds the specified instant to midnight.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
     * @param chronology  the chronology to use, not null
     * @return the updated instant, rounded to midnight
     */
    protected long checkInstant(long instant, Chronology chronology) {
        return chronology.dayOfMonth().roundFloor(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date with a different millisecond instant.
     * The returned object will have a local time of midnight.
     * <p>
     * Only the millis will change, the chronology and time zone are kept.
     * The returned object will be either be a new instance or <code>this</code>.
     *
     * @param newMillis  the new millis, from 1970-01-01T00:00:00Z
     * @return a copy of this instant with different millis
     */
    public DateMidnight withMillis(long newMillis) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[16]++;
        Chronology chrono = getChronology();
        newMillis = checkInstant(newMillis, chrono);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[17]++;
        return (newMillis == getMillis() ? this : new DateMidnight(newMillis, chrono));
    }

    /**
     * Returns a copy of this date with a different chronology, potentially
     * changing the day in unexpected ways.
     * <p>
     * This method creates a new DateMidnight using the midnight millisecond value
     * and the new chronology. If the same or similar chronology is specified, but
     * with a different time zone, the day may change. This occurs because the new
     * DateMidnight rounds down the millisecond value to get to midnight, and the
     * time zone change may result in a rounding down to a different day.
     * <p>
     * For example, changing time zone from London (+00:00) to Paris (+01:00) will
     * retain the same day, but changing from Paris to London will change the day.
     * (When its midnight in London its the same day in Paris, but when its midnight
     * in Paris its still the previous day in London)
     * <p>
     * To avoid these unusual effects, use {@link #withZoneRetainFields(DateTimeZone)}
     * to change time zones.
     *
     * @param newChronology  the new chronology
     * @return a copy of this instant with a different chronology
     */
    public DateMidnight withChronology(Chronology newChronology) {
        return (newChronology == getChronology() ? this : new DateMidnight(getMillis(), newChronology));
    }

    /**
     * Returns a copy of this date with a different time zone, preserving the day
     * The returned object will have a local time of midnight in the new zone on
     * the same day as the original instant.
     *
     * @param newZone  the new time zone, null means default
     * @return a copy of this instant with a different time zone
     */
    public DateMidnight withZoneRetainFields(DateTimeZone newZone) {
        newZone = DateTimeUtils.getZone(newZone);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[18]++;
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[19]++;
        DateTimeZone originalZone = DateTimeUtils.getZone(getZone());
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((newZone == originalZone) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[5]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[6]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[21]++;
        
        long millis = originalZone.getMillisKeepLocal(newZone, getMillis());
        return new DateMidnight(millis, getChronology().withZone(newZone));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date with the partial set of fields replacing those
     * from this instance.
     * <p>
     * For example, if the partial is a <code>LocalDate</code> then the date fields
     * would be changed in the returned instance.
     * If the partial is null, then <code>this</code> is returned.
     *
     * @param partial  the partial set of fields to apply to this datetime, null ignored
     * @return a copy of this datetime with a different set of fields
     * @throws IllegalArgumentException if any value is invalid
     */
    public DateMidnight withFields(ReadablePartial partial) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[7]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[8]++;}
        return withMillis(getChronology().set(partial, getMillis()));
    }

    /**
     * Returns a copy of this date with the specified field set to a new value.
     * <p>
     * For example, if the field type is <code>dayOfMonth</code> then the day of month
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
    public DateMidnight withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[9]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[10]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[24]++;
        long instant = fieldType.getField(getChronology()).set(getMillis(), value);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date with the value of the specified field increased.
     * <p>
     * If the addition is zero or the field is null, then <code>this</code> is returned.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * DateMidnight added = dt.withFieldAdded(DateTimeFieldType.year(), 6);
     * DateMidnight added = dt.plusYears(6);
     * DateMidnight added = dt.year().addToCopy(6);
     * </pre>
     * 
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this datetime with the field updated
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[11]++;
            throw new IllegalArgumentException("Field must not be null");

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[12]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[13]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[14]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[27]++;
        long instant = fieldType.getField(getChronology()).add(getMillis(), amount);
        return withMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date with the specified duration added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * 
     * @param durationToAdd  the duration to add to this one
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight withDurationAdded(long durationToAdd, int scalar) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((durationToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[15]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[16]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[29]++;
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date with the specified duration added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * 
     * @param durationToAdd  the duration to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight withDurationAdded(ReadableDuration durationToAdd, int scalar) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[30]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((durationToAdd == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[17]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[18]++;}
        return withDurationAdded(durationToAdd.getMillis(), scalar);
    }

    /**
     * Returns a copy of this date with the specified period added.
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
    public DateMidnight withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[19]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[20]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[32]++;
        long instant = getChronology().add(period, getMillis(), scalar);
        return withMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date with the specified duration added.
     * <p>
     * If the amount is zero, then <code>this</code> is returned.
     * 
     * @param duration  the duration, in millis, to add to this one
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight plus(long duration) {
        return withDurationAdded(duration, 1);
    }

    /**
     * Returns a copy of this date with the specified duration added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * 
     * @param duration  the duration to add to this one, null means zero
     * @return a copy of this datetime with the duration added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight plus(ReadableDuration duration) {
        return withDurationAdded(duration, 1);
    }

    /**
     * Returns a copy of this date with the specified period added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to add complex period instances.
     * Adding one field is best achieved using methods
     * like {@link #plusYears(int)}.
     * 
     * @param period  the duration to add to this one, null means zero
     * @return a copy of this datetime with the period added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date plus the specified number of years.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateMidnight added = dt.plusYears(6);
     * DateMidnight added = dt.plus(Period.years(6));
     * DateMidnight added = dt.withFieldAdded(DurationFieldType.years(), 6);
     * </pre>
     *
     * @param years  the amount of years to add, may be negative
     * @return the new datetime plus the increased years
     * @since 1.1
     */
    public DateMidnight plusYears(int years) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[21]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[22]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[34]++;
        long instant = getChronology().years().add(getMillis(), years);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date plus the specified number of months.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateMidnight added = dt.plusMonths(6);
     * DateMidnight added = dt.plus(Period.months(6));
     * DateMidnight added = dt.withFieldAdded(DurationFieldType.months(), 6);
     * </pre>
     *
     * @param months  the amount of months to add, may be negative
     * @return the new datetime plus the increased months
     * @since 1.1
     */
    public DateMidnight plusMonths(int months) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[35]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[23]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[24]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[36]++;
        long instant = getChronology().months().add(getMillis(), months);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date plus the specified number of weeks.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateMidnight added = dt.plusWeeks(6);
     * DateMidnight added = dt.plus(Period.weeks(6));
     * DateMidnight added = dt.withFieldAdded(DurationFieldType.weeks(), 6);
     * </pre>
     *
     * @param weeks  the amount of weeks to add, may be negative
     * @return the new datetime plus the increased weeks
     * @since 1.1
     */
    public DateMidnight plusWeeks(int weeks) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((weeks == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[25]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[26]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[38]++;
        long instant = getChronology().weeks().add(getMillis(), weeks);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date plus the specified number of days.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateMidnight added = dt.plusDays(6);
     * DateMidnight added = dt.plus(Period.days(6));
     * DateMidnight added = dt.withFieldAdded(DurationFieldType.days(), 6);
     * </pre>
     *
     * @param days  the amount of days to add, may be negative
     * @return the new datetime plus the increased days
     * @since 1.1
     */
    public DateMidnight plusDays(int days) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[27]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[28]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[40]++;
        long instant = getChronology().days().add(getMillis(), days);
        return withMillis(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date with the specified duration taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * 
     * @param duration  the duration, in millis, to reduce this instant by
     * @return a copy of this datetime with the duration taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight minus(long duration) {
        return withDurationAdded(duration, -1);
    }

    /**
     * Returns a copy of this date with the specified duration taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * 
     * @param duration  the duration to reduce this instant by
     * @return a copy of this datetime with the duration taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight minus(ReadableDuration duration) {
        return withDurationAdded(duration, -1);
    }

    /**
     * Returns a copy of this date with the specified period taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to subtract complex period instances.
     * Subtracting one field is best achieved using methods
     * like {@link #minusYears(int)}.
     * 
     * @param period  the period to reduce this instant by
     * @return a copy of this datetime with the period taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public DateMidnight minus(ReadablePeriod period) {
        return withPeriodAdded(period, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date minus the specified number of years.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateTime subtracted = dt.minusYears(6);
     * DateTime subtracted = dt.minus(Period.years(6));
     * DateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), -6);
     * </pre>
     *
     * @param years  the amount of years to subtract, may be negative
     * @return the new datetime minus the increased years
     * @since 1.1
     */
    public DateMidnight minusYears(int years) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[41]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[29]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[30]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[42]++;
        long instant = getChronology().years().subtract(getMillis(), years);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date minus the specified number of months.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateMidnight subtracted = dt.minusMonths(6);
     * DateMidnight subtracted = dt.minus(Period.months(6));
     * DateMidnight subtracted = dt.withFieldAdded(DurationFieldType.months(), -6);
     * </pre>
     *
     * @param months  the amount of months to subtract, may be negative
     * @return the new datetime minus the increased months
     * @since 1.1
     */
    public DateMidnight minusMonths(int months) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[43]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[31]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[32]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[44]++;
        long instant = getChronology().months().subtract(getMillis(), months);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date minus the specified number of weeks.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateMidnight subtracted = dt.minusWeeks(6);
     * DateMidnight subtracted = dt.minus(Period.weeks(6));
     * DateMidnight subtracted = dt.withFieldAdded(DurationFieldType.weeks(), -6);
     * </pre>
     *
     * @param weeks  the amount of weeks to subtract, may be negative
     * @return the new datetime minus the increased weeks
     * @since 1.1
     */
    public DateMidnight minusWeeks(int weeks) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[45]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((weeks == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[33]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[34]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[46]++;
        long instant = getChronology().weeks().subtract(getMillis(), weeks);
        return withMillis(instant);
    }

    /**
     * Returns a copy of this date minus the specified number of days.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * DateMidnight subtracted = dt.minusDays(6);
     * DateMidnight subtracted = dt.minus(Period.days(6));
     * DateMidnight subtracted = dt.withFieldAdded(DurationFieldType.days(), -6);
     * </pre>
     *
     * @param days  the amount of days to subtract, may be negative
     * @return the new datetime minus the increased days
     * @since 1.1
     */
    public DateMidnight minusDays(int days) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[47]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[35]++;
            return this;

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[36]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[48]++;
        long instant = getChronology().days().subtract(getMillis(), days);
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
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[49]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[37]++;
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[38]++;}
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[50]++;
        DateTimeField field = type.getField(getChronology());
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[51]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((field.isSupported() == false) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[39]++;
            throw new IllegalArgumentException("Field '" + type + "' is not supported");

        } else {
  CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.branches[40]++;}
        return new Property(this, field);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a <code>YearMonthDay</code> using the
     * same date and chronology.
     * 
     * @return a YearMonthDay using the same millis and chronology
     * @deprecated Use LocalDate instead of YearMonthDay
     */
    @Deprecated
    public YearMonthDay toYearMonthDay() {
        return new YearMonthDay(getMillis(), getChronology());
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
     * Converts this object to an <code>Interval</code> encompassing
     * the whole of this day.
     * <p>
     * The interval starts at midnight 00:00 and ends at 00:00 the following day,
     * (which is not included in the interval, as intervals are half-open).
     * 
     * @return an interval over the day
     */
    public Interval toInterval() {
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[52]++;
        Chronology chrono = getChronology();
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[53]++;
        long start = getMillis();
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[54]++;
        long end = DurationFieldType.days().getField(chrono).add(start, 1);
        return new Interval(start, end, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date with the era field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * era changed.
     *
     * @param era  the era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withEra(int era) {
        return withMillis(getChronology().era().set(getMillis(), era));
    }

    /**
     * Returns a copy of this date with the century of era field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * century of era changed.
     *
     * @param centuryOfEra  the centurey of era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withCenturyOfEra(int centuryOfEra) {
        return withMillis(getChronology().centuryOfEra().set(getMillis(), centuryOfEra));
    }

    /**
     * Returns a copy of this date with the year of era field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year of era changed.
     *
     * @param yearOfEra  the year of era to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withYearOfEra(int yearOfEra) {
        return withMillis(getChronology().yearOfEra().set(getMillis(), yearOfEra));
    }

    /**
     * Returns a copy of this date with the year of century field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year of century changed.
     *
     * @param yearOfCentury  the year of century to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withYearOfCentury(int yearOfCentury) {
        return withMillis(getChronology().yearOfCentury().set(getMillis(), yearOfCentury));
    }

    /**
     * Returns a copy of this date with the year field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year changed.
     *
     * @param year  the year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withYear(int year) {
        return withMillis(getChronology().year().set(getMillis(), year));
    }

    /**
     * Returns a copy of this date with the weekyear field updated.
     * <p>
     * The weekyear is the year that matches with the weekOfWeekyear field.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * The weekyear allows you to query the effective year for that day.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * weekyear changed.
     *
     * @param weekyear  the weekyear to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withWeekyear(int weekyear) {
        return withMillis(getChronology().weekyear().set(getMillis(), weekyear));
    }

    /**
     * Returns a copy of this date with the month of year field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * month of year changed.
     *
     * @param monthOfYear  the month of year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withMonthOfYear(int monthOfYear) {
        return withMillis(getChronology().monthOfYear().set(getMillis(), monthOfYear));
    }

    /**
     * Returns a copy of this date with the week of weekyear field updated.
     * <p>
     * This field is associated with the "weekyear" via {@link #withWeekyear(int)}.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * week of weekyear changed.
     *
     * @param weekOfWeekyear  the week of weekyear to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withWeekOfWeekyear(int weekOfWeekyear) {
        return withMillis(getChronology().weekOfWeekyear().set(getMillis(), weekOfWeekyear));
    }

    /**
     * Returns a copy of this date with the day of year field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of year changed.
     *
     * @param dayOfYear  the day of year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withDayOfYear(int dayOfYear) {
        return withMillis(getChronology().dayOfYear().set(getMillis(), dayOfYear));
    }

    /**
     * Returns a copy of this date with the day of month field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of month changed.
     *
     * @param dayOfMonth  the day of month to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withDayOfMonth(int dayOfMonth) {
        return withMillis(getChronology().dayOfMonth().set(getMillis(), dayOfMonth));
    }

    /**
     * Returns a copy of this date with the day of week field updated.
     * <p>
     * DateMidnight is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of week changed.
     *
     * @param dayOfWeek  the day of week to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public DateMidnight withDayOfWeek(int dayOfWeek) {
        return withMillis(getChronology().dayOfWeek().set(getMillis(), dayOfWeek));
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

    //-----------------------------------------------------------------------
    /**
     * DateMidnight.Property binds a DateMidnight to a DateTimeField allowing powerful
     * datetime functionality to be easily accessed.
     * <p>
     * The simplest use of this class is as an alternative get method, here used to
     * get the year '1972' (as an int) and the month 'December' (as a String).
     * <pre>
     * DateMidnight dt = new DateMidnight(1972, 12, 3);
     * int year = dt.year().get();
     * String monthStr = dt.monthOfYear().getAsText();
     * </pre>
     * <p>
     * Methods are also provided that allow date modification. These return new instances
     * of DateMidnight - they do not modify the original. The example below yields two
     * independent immutable date objects 20 years apart.
     * <pre>
     * DateMidnight dt = new DateMidnight(1972, 12, 3);
     * DateMidnight dt20 = dt.year().addToCopy(20);
     * </pre>
     * Serious modification of dates (ie. more than just changing one or two fields)
     * should use the {@link org.joda.time.MutableDateTime MutableDateTime} class.
     * <p>
     * DateMidnight.Property itself is thread-safe and immutable.
     *
     * @author Stephen Colebourne
     * @author Brian S O'Neill
     * @since 1.0
     */
    public static final class Property extends AbstractReadableInstantFieldProperty {
    
        /** Serialization lock */
        private static final long serialVersionUID = 257629620L;
        
        /** The instant this property is working against */
        private DateMidnight iInstant;
        /** The field this property is working against */
        private DateTimeField iField;
        
        /**
         * Constructor.
         * 
         * @param instant  the instant to set
         * @param field  the field to use
         */
        Property(DateMidnight instant, DateTimeField field) {
            super();
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[55]++;
            iInstant = instant;
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[56]++;
            iField = field;
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[57]++;
        }

        /**
         * Writes the property in a safe serialization format.
         */
        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.writeObject(iInstant);
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[58]++;
            oos.writeObject(iField.getType());
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[59]++;
        }

        /**
         * Reads the property from a safe serialization format.
         */
        private void readObject(ObjectInputStream oos) throws IOException, ClassNotFoundException {
            iInstant = (DateMidnight) oos.readObject();
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[60]++;
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[61]++;
            DateTimeFieldType type = (DateTimeFieldType) oos.readObject();
            iField = type.getField(iInstant.getChronology());
CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201.statements[62]++;
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
        public DateMidnight getDateMidnight() {
            return iInstant;
        }

        //-----------------------------------------------------------------------
        /**
         * Adds to this field in a copy of this DateMidnight.
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         * This operation is faster than converting a DateMidnight to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to add to the field in the copy
         * @return a copy of the DateMidnight with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateMidnight addToCopy(int value) {
            return iInstant.withMillis(iField.add(iInstant.getMillis(), value));
        }

        /**
         * Adds to this field in a copy of this DateMidnight.
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         * This operation is faster than converting a DateMidnight to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to add to the field in the copy
         * @return a copy of the DateMidnight with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateMidnight addToCopy(long value) {
            return iInstant.withMillis(iField.add(iInstant.getMillis(), value));
        }

        /**
         * Adds to this field, possibly wrapped, in a copy of this DateMidnight.
         * A wrapped operation only changes this field.
         * Thus 31st January addWrapField one day goes to the 1st January.
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         * This operation is faster than converting a DateMidnight to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to add to the field in the copy
         * @return a copy of the DateMidnight with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateMidnight addWrapFieldToCopy(int value) {
            return iInstant.withMillis(iField.addWrapField(iInstant.getMillis(), value));
        }

        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the DateMidnight.
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         * This operation is faster than converting a DateMidnight to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param value  the value to set the field in the copy to
         * @return a copy of the DateMidnight with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public DateMidnight setCopy(int value) {
            return iInstant.withMillis(iField.set(iInstant.getMillis(), value));
        }
    
        /**
         * Sets this field in a copy of the DateMidnight to a parsed text value.
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         * This operation is faster than converting a DateMidnight to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the DateMidnight with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public DateMidnight setCopy(String text, Locale locale) {
            return iInstant.withMillis(iField.set(iInstant.getMillis(), text, locale));
        }

        /**
         * Sets this field in a copy of the DateMidnight to a parsed text value.
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         * This operation is faster than converting a DateMidnight to a MutableDateTime
         * and back again when setting one field. When setting multiple fields,
         * it is generally quicker to make the conversion to MutableDateTime.
         * 
         * @param text  the text value to set
         * @return a copy of the DateMidnight with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public DateMidnight setCopy(String text) {
            return setCopy(text, null);
        }

        //-----------------------------------------------------------------------
        /**
         * Returns a new DateMidnight with this field set to the maximum value
         * for this field.
         * <p>
         * This operation is useful for obtaining a DateTime on the last day
         * of the month, as month lengths vary.
         * <pre>
         * DateMidnight lastDayOfMonth = dt.dayOfMonth().withMaximumValue();
         * </pre>
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         *
         * @return a copy of the DateMidnight with this field set to its maximum
         * @since 1.2
         */
        public DateMidnight withMaximumValue() {
            return setCopy(getMaximumValue());
        }
        
        /**
         * Returns a new DateMidnight with this field set to the minimum value
         * for this field.
         * <p>
         * The DateMidnight attached to this property is unchanged by this call.
         *
         * @return a copy of the DateMidnight with this field set to its minimum
         * @since 1.2
         */
        public DateMidnight withMinimumValue() {
            return setCopy(getMinimumValue());
        }
        
        //-----------------------------------------------------------------------
        /**
         * Rounds to the lowest whole unit of this field on a copy of this DateMidnight.
         *
         * @return a copy of the DateMidnight with the field value changed
         */
        public DateMidnight roundFloorCopy() {
            return iInstant.withMillis(iField.roundFloor(iInstant.getMillis()));
        }

        /**
         * Rounds to the highest whole unit of this field on a copy of this DateMidnight.
         *
         * @return a copy of the DateMidnight with the field value changed
         */
        public DateMidnight roundCeilingCopy() {
            return iInstant.withMillis(iField.roundCeiling(iInstant.getMillis()));
        }

        /**
         * Rounds to the nearest whole unit of this field on a copy of this DateMidnight,
         * favoring the floor if halfway.
         *
         * @return a copy of the DateMidnight with the field value changed
         */
        public DateMidnight roundHalfFloorCopy() {
            return iInstant.withMillis(iField.roundHalfFloor(iInstant.getMillis()));
        }

        /**
         * Rounds to the nearest whole unit of this field on a copy of this DateMidnight,
         * favoring the ceiling if halfway.
         *
         * @return a copy of the DateMidnight with the field value changed
         */
        public DateMidnight roundHalfCeilingCopy() {
            return iInstant.withMillis(iField.roundHalfCeiling(iInstant.getMillis()));
        }

        /**
         * Rounds to the nearest whole unit of this field on a copy of this DateMidnight.
         * If halfway, the ceiling is favored over the floor only if it makes this field's value even.
         *
         * @return a copy of the DateMidnight with the field value changed
         */
        public DateMidnight roundHalfEvenCopy() {
            return iInstant.withMillis(iField.roundHalfEven(iInstant.getMillis()));
        }

    }
}

class CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201 ());
  }
    public static long[] statements = new long[63];
    public static long[] branches = new long[41];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.joda.time.DateMidnight.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
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

  public CodeCoverCoverageCounter$ssd5eo91lnmra2ymn5fg8m1201 () {
    super("org.joda.time.DateMidnight.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 62; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 40; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.DateMidnight.java");
      for (int i = 1; i <= 62; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 40; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
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
