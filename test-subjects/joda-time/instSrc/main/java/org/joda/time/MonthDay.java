/*
 *  Copyright 2001-2010 Stephen Colebourne
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

/**
 * MonthDay is an immutable partial supporting the monthOfYear and dayOfMonth fields.
 * <p>
 * NOTE: This class only supports the two fields listed above.
 * It is impossible to query any other fields, such as dayOfWeek or centuryOfEra.
 * <p>
 * Calculations on MonthDay are performed using a {@link Chronology}.
 * This chronology is set to be in the UTC time zone for all calculations.
 * <p>
 * One use case for this class is to store a birthday without the year (to avoid
 * storing the age of the person).
 * This class can be used as the gMonthDay type in XML Schema.
 * <p>
 * Each individual field can be queried in two ways:
 * <ul>
 * <li><code>getMonthOfYear()</code>
 * <li><code>monthOfYear().get()</code>
 * </ul>
 * The second technique also provides access to other useful methods on the
 * field:
 * <ul>
 * <li>numeric value - <code>monthOfYear().get()</code>
 * <li>text value - <code>monthOfYear().getAsText()</code>
 * <li>short text value - <code>monthOfYear().getAsShortText()</code>
 * <li>maximum/minimum values - <code>monthOfYear().getMaximumValue()</code>
 * <li>add/subtract - <code>monthOfYear().addToCopy()</code>
 * <li>set - <code>monthOfYear().setCopy()</code>
 * </ul>
 * <p>
 * MonthDay is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Chris Pheby
 * @since 2.0
 */
public final class MonthDay
        extends BasePartial
        implements ReadablePartial, Serializable {
  static {
    CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 2954560699050434609L;
  static {
    CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[1]++;
  }

    /** The singleton set of field types */
    private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[] {
        DateTimeFieldType.monthOfYear(),
        DateTimeFieldType.dayOfMonth(), };
  static {
    CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[2]++;
  }

    /** The singleton set of field types */
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder()
        .appendOptional(ISODateTimeFormat.localDateParser().getParser())
        .appendOptional(DateTimeFormat.forPattern("--MM-dd").getParser()).toFormatter();
  static {
    CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[3]++;
  }

    /** The index of the monthOfYear field in the field array */
    public static final int MONTH_OF_YEAR = 0;
  static {
    CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[4]++;
  }
    /** The index of the day field in the field array */
    public static final int DAY_OF_MONTH = 1;
  static {
    CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[5]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains a {@code MonthDay} set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * The resulting object does not use the zone.
     * 
     * @return the current month-day, not null
     * @since 2.0
     */
    public static MonthDay now() {
        return new MonthDay();
    }

    /**
     * Obtains a {@code MonthDay} set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * The resulting object does not use the zone.
     *
     * @param zone  the time zone, not null
     * @return the current month-day, not null
     * @since 2.0
     */
    public static MonthDay now(DateTimeZone zone) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[1]++;
            throw new NullPointerException("Zone must not be null");

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[2]++;}
        return new MonthDay(zone);
    }

    /**
     * Obtains a {@code MonthDay} set to the current system millisecond time
     * using the specified chronology.
     * The resulting object does not use the zone.
     *
     * @param chronology  the chronology, not null
     * @return the current month-day, not null
     * @since 2.0
     */
    public static MonthDay now(Chronology chronology) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronology == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[3]++;
            throw new NullPointerException("Chronology must not be null");

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[4]++;}
        return new MonthDay(chronology);
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code MonthDay} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#localDateParser()} or the format {@code --MM-dd}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static MonthDay parse(String str) {
        return parse(str, PARSER);
    }

    /**
     * Parses a {@code MonthDay} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static MonthDay parse(String str, DateTimeFormatter formatter) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[8]++;
        LocalDate date = formatter.parseLocalDate(str);
        return new MonthDay(date.getMonthOfYear(), date.getDayOfMonth());
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a MonthDay from a <code>java.util.Calendar</code>
     * using exactly the same field values avoiding any time zone effects.
     * <p>
     * Each field is queried from the Calendar and assigned to the MonthDay.
     * <p>
     * This factory method ignores the type of the calendar and always
     * creates a MonthDay with ISO chronology. It is expected that you
     * will only pass in instances of <code>GregorianCalendar</code> however
     * this is not validated.
     *
     * @param calendar  the Calendar to extract fields from
     * @return the created MonthDay, never null
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the monthOfYear or dayOfMonth is invalid for the ISO chronology
     */
    public static MonthDay fromCalendarFields(Calendar calendar) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((calendar == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[5]++;
            throw new IllegalArgumentException("The calendar must not be null");

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[6]++;}
        return new MonthDay(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Constructs a MonthDay from a <code>java.util.Date</code>
     * using exactly the same field values avoiding any time zone effects.
     * <p>
     * Each field is queried from the Date and assigned to the MonthDay.
     * <p>
     * This factory method always creates a MonthDay with ISO chronology.
     *
     * @param date  the Date to extract fields from
     * @return the created MonthDay, never null
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the monthOfYear or dayOfMonth is invalid for the ISO chronology
     */
    @SuppressWarnings("deprecation")
    public static MonthDay fromDateFields(Date date) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[7]++;
            throw new IllegalArgumentException("The date must not be null");

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[8]++;}
        return new MonthDay(date.getMonth() + 1, date.getDate());
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a MonthDay with the current monthOfYear, using ISOChronology in
     * the default zone to extract the fields.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     * 
     * @see #now()
     */
    public MonthDay() {
        super();
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[11]++;
    }

    /**
     * Constructs a MonthDay with the current month-day, using ISOChronology in
     * the specified zone to extract the fields.
     * <p>
     * The constructor uses the specified time zone to obtain the current month-day.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     * 
     * @param zone  the zone to use, null means default zone
     * @see #now(DateTimeZone)
     */
    public MonthDay(DateTimeZone zone) {
        super(ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[12]++;
    }

    /**
     * Constructs a MonthDay with the current month-day, using the specified chronology
     * and zone to extract the fields.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a time-zone (by switching to UTC).
     *
     * @param chronology  the chronology, null means ISOChronology in the default zone
     * @see #now(Chronology)
     */
    public MonthDay(Chronology chronology) {
        super(chronology);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[13]++;
    }

    /**
     * Constructs a MonthDay extracting the partial fields from the specified
     * milliseconds using the ISOChronology in the default zone.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public MonthDay(long instant) {
        super(instant);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[14]++;
    }

    /**
     * Constructs a MonthDay extracting the partial fields from the specified
     * milliseconds using the chronology provided.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a time-zone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public MonthDay(long instant, Chronology chronology) {
        super(instant, chronology);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[15]++;
    }

    /**
     * Constructs a MonthDay from an Object that represents some form of time.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#localDateParser()}.
     * <p>
     * The chronology used will be derived from the object, defaulting to ISO.
     *
     * @param instant  the date-time object, null means now
     * @throws IllegalArgumentException if the instant is invalid
     */
    public MonthDay(Object instant) {
        super(instant, null, ISODateTimeFormat.localDateParser());
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[16]++;
    }

    /**
     * Constructs a MonthDay from an Object that represents some form of time,
     * using the specified chronology.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#localDateParser()}.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a time-zone (by switching to UTC).
     * The specified chronology overrides that of the object.
     *
     * @param instant  the date-time object, null means now
     * @param chronology  the chronology, null means ISO default
     * @throws IllegalArgumentException if the instant is invalid
     */
    public MonthDay(Object instant, Chronology chronology) {
        super(instant, DateTimeUtils.getChronology(chronology), ISODateTimeFormat.localDateParser());
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[17]++;
    }

    /**
     * Constructs a MonthDay with specified year and month
     * using <code>ISOChronology</code>.
     * <p>
     * The constructor uses the no time zone initialising the fields as provided.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     *
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     */
    public MonthDay(int monthOfYear, int dayOfMonth) {
        this(monthOfYear, dayOfMonth, null);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[18]++;
    }

    /**
     * Constructs an instance set to the specified monthOfYear and dayOfMonth
     * using the specified chronology, whose zone is ignored.
     * <p>
     * If the chronology is null, <code>ISOChronology</code> is used.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a time-zone (by switching to UTC).
     *
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public MonthDay(int monthOfYear, int dayOfMonth, Chronology chronology) {
        super(new int[] {monthOfYear, dayOfMonth}, chronology);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[19]++;
    }

    /**
     * Constructs a MonthDay with chronology from this instance and new values.
     *
     * @param partial  the partial to base this new instance on
     * @param values  the new set of values
     */
    MonthDay(MonthDay partial, int[] values) {
        super(partial, values);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[20]++;
    }

    /**
     * Constructs a MonthDay with values from this instance and a new chronology.
     *
     * @param partial  the partial to base this new instance on
     * @param chrono  the new chronology
     */
    MonthDay(MonthDay partial, Chronology chrono) {
        super(partial, chrono);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[21]++;
    }

    /**
     * Handle broken serialization from other tools.
     * @return the resolved object, not null
     */
    private Object readResolve() {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((DateTimeZone.UTC.equals(getChronology().getZone()) == false) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[9]++;
            return new MonthDay(this, getChronology().withUTC());

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[10]++;}
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields in this partial, which is two.
     * The supported fields are MonthOfYear and DayOfMonth.
     * Note that only these fields may be queried.
     *
     * @return the field count, two
     */
    public int size() {
        return 2;
    }

    /**
     * Gets the field for a specific index in the chronology specified.
     * <p>
     * This method must not use any instance variables.
     * 
     * @param index  the index to retrieve
     * @param chrono  the chronology to use
     * @return the field, never null
     */
    protected DateTimeField getField(int index, Chronology chrono) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[23]++;
        switch (index) {
        case MONTH_OF_YEAR:
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[11]++;
            return chrono.monthOfYear();
        case DAY_OF_MONTH:
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[12]++;
            return chrono.dayOfMonth();
        default:
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[13]++;
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    /**
     * Gets the field type at the specified index.
     *
     * @param index  the index to retrieve
     * @return the field at the specified index, never null
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public DateTimeFieldType getFieldType(int index) {
        return FIELD_TYPES[index];
    }

    /**
     * Gets an array of the field type of each of the fields that this partial supports.
     * <p>
     * The fields are returned largest to smallest, Month, Day.
     *
     * @return the array of field types (cloned), largest to smallest, never null
     */
    public DateTimeFieldType[] getFieldTypes() {
        return (DateTimeFieldType[]) FIELD_TYPES.clone();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this month-day with the specified chronology.
     * This instance is immutable and unaffected by this method call.
     * <p>
     * This method retains the values of the fields, thus the result will
     * typically refer to a different instant.
     * <p>
     * The time zone of the specified chronology is ignored, as MonthDay
     * operates without a time zone.
     *
     * @param newChronology  the new chronology, null means ISO
     * @return a copy of this month-day with a different chronology, never null
     * @throws IllegalArgumentException if the values are invalid for the new chronology
     */
    public MonthDay withChronologyRetainFields(Chronology newChronology) {
        newChronology = DateTimeUtils.getChronology(newChronology);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[24]++;
        newChronology = newChronology.withUTC();
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[25]++;
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((newChronology == getChronology()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[14]++;
            return this;

        } else {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[15]++;
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[27]++;
            MonthDay newMonthDay = new MonthDay(this, newChronology);
            newChronology.validate(newMonthDay, getValues());
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[28]++;
            return newMonthDay;
        }
    }

    /**
     * Returns a copy of this month-day with the specified field set to a new value.
     * <p>
     * For example, if the field type is <code>dayOfMonth</code> then the day
     * would be changed in the returned instance.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * MonthDay updated = md.withField(DateTimeFieldType.dayOfMonth(), 6);
     * MonthDay updated = md.dayOfMonth().setCopy(6);
     * MonthDay updated = md.property(DateTimeFieldType.dayOfMonth()).setCopy(6);
     * </pre>
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this instance with the field set, never null
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public MonthDay withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[29]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value == getValue(index)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[16]++;
            return this;

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[17]++;}
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[31]++;
        int[] newValues = getValues();
        newValues = getField(index).set(this, index, newValues, value);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[32]++;
        return new MonthDay(this, newValues);
    }

    /**
     * Returns a copy of this month-day with the value of the specified field increased.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * MonthDay added = md.withFieldAdded(DurationFieldType.days(), 6);
     * MonthDay added = md.plusDays(6);
     * MonthDay added = md.dayOfMonth().addToCopy(6);
     * </pre>
     * 
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this instance with the field updated, never null
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the new date-time exceeds the capacity
     */
    public MonthDay withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[33]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[18]++;
            return this;

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[19]++;}
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[35]++;
        int[] newValues = getValues();
        newValues = getField(index).add(this, index, newValues, amount);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[36]++;
        return new MonthDay(this, newValues);
    }

    /**
     * Returns a copy of this month-day with the specified period added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * Fields in the period that aren't present in the partial are ignored.
     * <p>
     * This method is typically used to add multiple copies of complex
     * period instances. Adding one field is best achieved using methods
     * like {@link #withFieldAdded(DurationFieldType, int)}
     * or {@link #plusMonths(int)}.
     * 
     * @param period  the period to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this instance with the period added, never null
     * @throws ArithmeticException if the new date-time exceeds the capacity
     */
    public MonthDay withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[20]++;
            return this;

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[21]++;}
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[38]++;
        int[] newValues = getValues();
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[39]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < period.size()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.loops[1]--;
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.loops[2]--;
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.loops[3]++;
}
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[40]++;
            DurationFieldType fieldType = period.getFieldType(i);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[41]++;
            int index = indexOf(fieldType);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[22]++;
                newValues = getField(index).add(this, index, newValues,
                        FieldUtils.safeMultiply(period.getValue(i), scalar));
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[43]++;

            } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[23]++;}
        }
        return new MonthDay(this, newValues);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this month-day with the specified period added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to add complex period instances.
     * Adding one field is best achieved using methods
     * like {@link #plusMonths(int)}.
     * 
     * @param period  the duration to add to this one, null means zero
     * @return a copy of this instance with the period added, never null
     * @throws ArithmeticException if the new month-day exceeds the capacity
     */
    public MonthDay plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this month-day plus the specified number of months.
     * <p>
     * This month-day instance is immutable and unaffected by this method call.
     * The month will wrap at the end of the year from December to January.
     * The day will be adjusted to the last valid value if necessary.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * MonthDay added = md.plusMonths(6);
     * MonthDay added = md.plus(Period.months(6));
     * MonthDay added = md.withFieldAdded(DurationFieldType.months(), 6);
     * </pre>
     *
     * @param months  the amount of months to add, may be negative
     * @return the new month-day plus the increased months, never null
     */
    public MonthDay plusMonths(int months) {
        return withFieldAdded(DurationFieldType.months(), months);
    }

    /**
     * Returns a copy of this month-day plus the specified number of days.
     * <p>
     * This month-day instance is immutable and unaffected by this method call.
     * The month will wrap at the end of the year from December to January.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * MonthDay added = md.plusDays(6);
     * MonthDay added = md.plus(Period.days(6));
     * MonthDay added = md.withFieldAdded(DurationFieldType.days(), 6);
     * </pre>
     *
     * @param days  the amount of days to add, may be negative
     * @return the new month-day plus the increased days, never null
     */
    public MonthDay plusDays(int days) {
        return withFieldAdded(DurationFieldType.days(), days);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this month-day with the specified period taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to subtract complex period instances.
     * Subtracting one field is best achieved using methods
     * like {@link #minusMonths(int)}.
     * 
     * @param period  the period to reduce this instant by
     * @return a copy of this instance with the period taken away, never null
     * @throws ArithmeticException if the new month-day exceeds the capacity
     */
    public MonthDay minus(ReadablePeriod period) {
        return withPeriodAdded(period, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this month-day minus the specified number of months.
     * <p>
     * This MonthDay instance is immutable and unaffected by this method call.
     * The month will wrap at the end of the year from January to December.
     * The day will be adjusted to the last valid value if necessary.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * MonthDay subtracted = md.minusMonths(6);
     * MonthDay subtracted = md.minus(Period.months(6));
     * MonthDay subtracted = md.withFieldAdded(DurationFieldType.months(), -6);
     * </pre>
     *
     * @param months  the amount of months to subtract, may be negative
     * @return the new month-day minus the increased months, never null
     */
    public MonthDay minusMonths(int months) {
        return withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(months));
    }

    /**
     * Returns a copy of this month-day minus the specified number of months.
     * <p>
     * This month-day instance is immutable and unaffected by this method call.
     * The month will wrap at the end of the year from January to December.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * MonthDay subtracted = md.minusDays(6);
     * MonthDay subtracted = md.minus(Period.days(6));
     * MonthDay subtracted = md.withFieldAdded(DurationFieldType.days(), -6);
     * </pre>
     *
     * @param days  the amount of days to subtract, may be negative
     * @return the new month-day minus the increased days, never null
     */
    public MonthDay minusDays(int days) {
        return withFieldAdded(DurationFieldType.days(), FieldUtils.safeNegate(days));
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a LocalDate with the same month-day and chronology.
     *
     * @param year  the year to use, valid for chronology
     * @return a LocalDate with the same month-day and chronology, never null
     */
    public LocalDate toLocalDate(int year) {
        return new LocalDate(year, getMonthOfYear(), getDayOfMonth(), getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Get the month of year field value.
     *
     * @return the month of year
     */
    public int getMonthOfYear() {
        return getValue(MONTH_OF_YEAR);
    }

    /**
     * Get the day of month field value.
     *
     * @return the day of month
     */
    public int getDayOfMonth() {
        return getValue(DAY_OF_MONTH);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this month-day with the month of year field updated.
     * <p>
     * MonthDay is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * month of year changed.
     *
     * @param monthOfYear  the month of year to set
     * @return a copy of this object with the field set, never null
     * @throws IllegalArgumentException if the value is invalid
     */
    public MonthDay withMonthOfYear(int monthOfYear) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[44]++;
        int[] newValues = getValues();
        newValues = getChronology().monthOfYear().set(this, MONTH_OF_YEAR, newValues, monthOfYear);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[45]++;
        return new MonthDay(this, newValues);
    }

    /**
     * Returns a copy of this month-day with the day of month field updated.
     * <p>
     * MonthDay is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of month changed.
     *
     * @param dayOfMonth  the day of month to set
     * @return a copy of this object with the field set, never null
     * @throws IllegalArgumentException if the value is invalid
     */
    public MonthDay withDayOfMonth(int dayOfMonth) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[46]++;
        int[] newValues = getValues();
        newValues = getChronology().dayOfMonth().set(this, DAY_OF_MONTH, newValues, dayOfMonth);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[47]++;
        return new MonthDay(this, newValues);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the property object for the specified type, which contains
     * many useful methods.
     *
     * @param type  the field type to get the property for
     * @return the property object
     * @throws IllegalArgumentException if the field is null or unsupported
     */
    public Property property(DateTimeFieldType type) {
        return new Property(this, indexOfSupported(type));
    }

    //-----------------------------------------------------------------------
    /**
     * Get the month of year field property which provides access to advanced functionality.
     * 
     * @return the month of year property
     */
    public Property monthOfYear() {
        return new Property(this, MONTH_OF_YEAR);
    }

    /**
     * Get the day of month field property which provides access to advanced functionality.
     * 
     * @return the day of month property
     */
    public Property dayOfMonth() {
        return new Property(this, DAY_OF_MONTH);
    }

    //-----------------------------------------------------------------------
    /**
     * Output the month-day in ISO8601 format (--MM-dd).
     *
     * @return ISO8601 time formatted string.
     */
    @ToString
    public String toString() {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[48]++;
        List<DateTimeFieldType> fields = new ArrayList<DateTimeFieldType>();
        fields.add(DateTimeFieldType.monthOfYear());
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[49]++;
        fields.add(DateTimeFieldType.dayOfMonth());
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[50]++;
        return ISODateTimeFormat.forFields(fields, true, true).print(this);
    }

    /**
     * Output the month-day using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[51]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[24]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[25]++;}
        return DateTimeFormat.forPattern(pattern).print(this);
    }

    /**
     * Output the month-day using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @param locale  Locale to use, null means default
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern, Locale locale) throws IllegalArgumentException {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[26]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.branches[27]++;}
        return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
    }

    //-----------------------------------------------------------------------
    /**
     * The property class for <code>MonthDay</code>.
     * <p>
     * This class binds a <code>YearMonth</code> to a <code>DateTimeField</code>.
     * 
     * @author Chris Pheby
     * @since 2.0
     */
    public static class Property extends AbstractPartialFieldProperty implements Serializable {

        /** Serialization version */
        private static final long serialVersionUID = 5727734012190224363L;

        /** The partial */
        private final MonthDay iBase;
        /** The field index */
        private final int iFieldIndex;

        /**
         * Constructs a property.
         * 
         * @param partial  the partial instance
         * @param fieldIndex  the index in the partial
         */
        Property(MonthDay partial, int fieldIndex) {
            super();
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[53]++;
            iBase = partial;
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[54]++;
            iFieldIndex = fieldIndex;
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[55]++;
        }

        /**
         * Gets the field that this property uses.
         * 
         * @return the field
         */
        public DateTimeField getField() {
            return iBase.getField(iFieldIndex);
        }

        /**
         * Gets the partial that this property belongs to.
         * 
         * @return the partial
         */
        protected ReadablePartial getReadablePartial() {
            return iBase;
        }

        /**
         * Gets the partial that this property belongs to.
         * 
         * @return the partial
         */
        public MonthDay getMonthDay() {
            return iBase;
        }

        /**
         * Gets the value of this field.
         * 
         * @return the field value
         */
        public int get() {
            return iBase.getValue(iFieldIndex);
        }

        //-----------------------------------------------------------------------
        /**
         * Adds to the value of this field in a copy of this MonthDay.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it will affect larger fields.
         * Smaller fields are unaffected.
         * <p>
         * The MonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the MonthDay with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public MonthDay addToCopy(int valueToAdd) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[56]++;
            int[] newValues = iBase.getValues();
            newValues = getField().add(iBase, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[57]++;
            return new MonthDay(iBase, newValues);
        }

        /**
         * Adds to the value of this field in a copy of this MonthDay wrapping
         * within this field if the maximum value is reached.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it wraps within this field.
         * Other fields are unaffected.
         * <p>
         * For example,
         * <code>--12-30</code> addWrapField one month returns <code>--01-30</code>.
         * <p>
         * The MonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the MonthDay with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public MonthDay addWrapFieldToCopy(int valueToAdd) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[58]++;
            int[] newValues = iBase.getValues();
            newValues = getField().addWrapField(iBase, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[59]++;
            return new MonthDay(iBase, newValues);
        }

        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the MonthDay.
         * <p>
         * The MonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param value  the value to set the field in the copy to
         * @return a copy of the MonthDay with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public MonthDay setCopy(int value) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[60]++;
            int[] newValues = iBase.getValues();
            newValues = getField().set(iBase, iFieldIndex, newValues, value);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[61]++;
            return new MonthDay(iBase, newValues);
        }

        /**
         * Sets this field in a copy of the MonthDay to a parsed text value.
         * <p>
         * The MonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the MonthDay with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public MonthDay setCopy(String text, Locale locale) {
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[62]++;
            int[] newValues = iBase.getValues();
            newValues = getField().set(iBase, iFieldIndex, newValues, text, locale);
CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5.statements[63]++;
            return new MonthDay(iBase, newValues);
        }

        /**
         * Sets this field in a copy of the MonthDay to a parsed text value.
         * <p>
         * The MonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @return a copy of the MonthDay with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public MonthDay setCopy(String text) {
            return setCopy(text, null);
        }
    }

}

class CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5 ());
  }
    public static long[] statements = new long[64];
    public static long[] branches = new long[28];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.joda.time.MonthDay.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$gisx7624yi0qt3agyhr5 () {
    super("org.joda.time.MonthDay.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 63; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 27; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.MonthDay.java");
      for (int i = 1; i <= 63; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 27; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

