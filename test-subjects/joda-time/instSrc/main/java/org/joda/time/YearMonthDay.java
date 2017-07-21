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

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISODateTimeFormat;

/**
 * YearMonthDay is an immutable partial supporting the year, monthOfYear
 * and dayOfMonth fields.
 * <p>
 * NOTE: This class only supports the three fields listed above. Thus, you
 * cannot query the dayOfWeek or centuryOfEra fields for example.
 * The new <code>LocalDate</code> class removes this restriction.
 * <p>
 * Calculations on YearMonthDay are performed using a {@link Chronology}.
 * This chronology is set to be in the UTC time zone for all calculations.
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
 * YearMonthDay is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.0
 * @deprecated Use LocalDate which has a much better internal implementation and
 *  has been available since 1.3
 */
@Deprecated
public final class YearMonthDay
        extends BasePartial
        implements ReadablePartial, Serializable {
  static {
    CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 797544782896179L;
  static {
    CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[1]++;
  }
    /** The singleton set of field types */
    private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[] {
        DateTimeFieldType.year(),
        DateTimeFieldType.monthOfYear(),
        DateTimeFieldType.dayOfMonth(),
    };
  static {
    CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[2]++;
  }

    /** The index of the year field in the field array */
    public static final int YEAR = 0;
  static {
    CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[3]++;
  }
    /** The index of the monthOfYear field in the field array */
    public static final int MONTH_OF_YEAR = 1;
  static {
    CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[4]++;
  }
    /** The index of the dayOfMonth field in the field array */
    public static final int DAY_OF_MONTH = 2;
  static {
    CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[5]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Constructs a YearMonthDay from a <code>java.util.Calendar</code>
     * using exactly the same field values avoiding any time zone effects.
     * <p>
     * Each field is queried from the Calendar and assigned to the YearMonthDay.
     * This is useful if you have been using the Calendar as a local date,
     * ignoing the zone.
     * <p>
     * This factory method ignores the type of the calendar and always
     * creates a YearMonthDay with ISO chronology. It is expected that you
     * will only pass in instances of <code>GregorianCalendar</code> however
     * this is not validated.
     *
     * @param calendar  the Calendar to extract fields from
     * @return the created YearMonthDay
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the date is invalid for the ISO chronology
     * @since 1.2
     */
    public static YearMonthDay fromCalendarFields(Calendar calendar) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((calendar == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[1]++;
            throw new IllegalArgumentException("The calendar must not be null");

        } else {
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[2]++;}
        return new YearMonthDay(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        );
    }

    /**
     * Constructs a YearMonthDay from a <code>java.util.Date</code>
     * using exactly the same field values avoiding any time zone effects.
     * <p>
     * Each field is queried from the Date and assigned to the YearMonthDay.
     * This is useful if you have been using the Date as a local date,
     * ignoing the zone.
     * <p>
     * This factory method always creates a YearMonthDay with ISO chronology.
     *
     * @param date  the Date to extract fields from
     * @return the created YearMonthDay
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the date is invalid for the ISO chronology
     * @since 1.2
     */
    public static YearMonthDay fromDateFields(Date date) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[3]++;
            throw new IllegalArgumentException("The date must not be null");

        } else {
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[4]++;}
        return new YearMonthDay(
            date.getYear() + 1900,
            date.getMonth() + 1,
            date.getDate()
        );
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a YearMonthDay with the current date, using ISOChronology in
     * the default zone to extract the fields.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     */
    public YearMonthDay() {
        super();
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[8]++;
    }

    /**
     * Constructs a YearMonthDay with the current date, using ISOChronology in
     * the specified zone to extract the fields.
     * <p>
     * The constructor uses the specified time zone to obtain the current date.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     * 
     * @param zone  the zone to use, null means default zone
     * @since 1.1
     */
    public YearMonthDay(DateTimeZone zone) {
        super(ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[9]++;
    }

    /**
     * Constructs a YearMonthDay with the current date, using the specified chronology
     * and zone to extract the fields.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public YearMonthDay(Chronology chronology) {
        super(chronology);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[10]++;
    }

    /**
     * Constructs a YearMonthDay extracting the partial fields from the specified
     * milliseconds using the ISOChronology in the default zone.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public YearMonthDay(long instant) {
        super(instant);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[11]++;
    }

    /**
     * Constructs a YearMonthDay extracting the partial fields from the specified
     * milliseconds using the chronology provided.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public YearMonthDay(long instant, Chronology chronology) {
        super(instant, chronology);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[12]++;
    }

    /**
     * Constructs a YearMonthDay from an Object that represents a time.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#dateOptionalTimeParser()}.
     * <p>
     * The chronology used will be derived from the object, defaulting to ISO.
     * <p>
     * NOTE: Prior to v1.3 the string format was described by
     * {@link ISODateTimeFormat#dateTimeParser()}. Time ony strings are now rejected.
     *
     * @param instant  the datetime object, null means now
     * @throws IllegalArgumentException if the instant is invalid
     */
    public YearMonthDay(Object instant) {
        super(instant, null, ISODateTimeFormat.dateOptionalTimeParser());
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[13]++;
    }

    /**
     * Constructs a YearMonthDay from an Object that represents a time, using the
     * specified chronology.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * The String formats are described by {@link ISODateTimeFormat#dateOptionalTimeParser()}.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     * The specified chronology overrides that of the object.
     * <p>
     * NOTE: Prior to v1.3 the string format was described by
     * {@link ISODateTimeFormat#dateTimeParser()}. Time only strings are now rejected.
     *
     * @param instant  the datetime object, null means now
     * @param chronology  the chronology, null means ISO default
     * @throws IllegalArgumentException if the instant is invalid
     */
    public YearMonthDay(Object instant, Chronology chronology) {
        super(instant, DateTimeUtils.getChronology(chronology), ISODateTimeFormat.dateOptionalTimeParser());
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[14]++;
    }

    /**
     * Constructs a YearMonthDay with specified time field values
     * using <code>ISOChronology</code> in the default zone.
     * <p>
     * The constructor uses the no time zone initialising the fields as provided.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     */
    public YearMonthDay(int year, int monthOfYear, int dayOfMonth) {
        this(year, monthOfYear, dayOfMonth, null);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[15]++;
    }

    /**
     * Constructs a YearMonthDay with specified time field values.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public YearMonthDay(int year, int monthOfYear, int dayOfMonth, Chronology chronology) {
        super(new int[] {year, monthOfYear, dayOfMonth}, chronology);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[16]++;
    }

    /**
     * Constructs a YearMonthDay with chronology from this instance and new values.
     *
     * @param partial  the partial to base this new instance on
     * @param values  the new set of values
     */
    YearMonthDay(YearMonthDay partial, int[] values) {
        super(partial, values);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[17]++;
    }

    /**
     * Constructs a YearMonthDay with values from this instance and a new chronology.
     *
     * @param partial  the partial to base this new instance on
     * @param chrono  the new chronology
     */
    YearMonthDay(YearMonthDay partial, Chronology chrono) {
        super(partial, chrono);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[18]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields in this partial.
     * 
     * @return the field count
     */
    public int size() {
        return 3;
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
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[19]++;
        switch (index) {
            case YEAR:
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[5]++;
                return chrono.year();
            case MONTH_OF_YEAR:
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[6]++;
                return chrono.monthOfYear();
            case DAY_OF_MONTH:
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[7]++;
                return chrono.dayOfMonth();
            default:
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[8]++;
                throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    /**
     * Gets the field type at the specified index.
     *
     * @param index  the index to retrieve
     * @return the field at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public DateTimeFieldType getFieldType(int index) {
        return FIELD_TYPES[index];
    }

    /**
     * Gets an array of the field type of each of the fields that this partial supports.
     * <p>
     * The fields are returned largest to smallest, Year, Month, Day
     *
     * @return the array of field types (cloned), largest to smallest
     */
    public DateTimeFieldType[] getFieldTypes() {
        return (DateTimeFieldType[]) FIELD_TYPES.clone();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date with the specified chronology.
     * This instance is immutable and unaffected by this method call.
     * <p>
     * This method retains the values of the fields, thus the result will
     * typically refer to a different instant.
     * <p>
     * The time zone of the specified chronology is ignored, as YearMonthDay
     * operates without a time zone.
     *
     * @param newChronology  the new chronology, null means ISO
     * @return a copy of this datetime with a different chronology
     * @throws IllegalArgumentException if the values are invalid for the new chronology
     */
    public YearMonthDay withChronologyRetainFields(Chronology newChronology) {
        newChronology = DateTimeUtils.getChronology(newChronology);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[20]++;
        newChronology = newChronology.withUTC();
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[21]++;
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((newChronology == getChronology()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[9]++;
            return this;

        } else {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[10]++;
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[23]++;
            YearMonthDay newYearMonthDay = new YearMonthDay(this, newChronology);
            newChronology.validate(newYearMonthDay, getValues());
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[24]++;
            return newYearMonthDay;
        }
    }

    /**
     * Returns a copy of this date with the specified field set to a new value.
     * <p>
     * For example, if the field type is <code>dayOfMonth</code> then the day
     * would be changed in the returned instance.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * YearMonthDay updated = ymd.withField(DateTimeFieldType.dayOfMonth(), 6);
     * YearMonthDay updated = ymd.dayOfMonth().setCopy(6);
     * YearMonthDay updated = ymd.property(DateTimeFieldType.dayOfMonth()).setCopy(6);
     * </pre>
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this instance with the field set
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public YearMonthDay withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[25]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value == getValue(index)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[11]++;
            return this;

        } else {
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[12]++;}
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[27]++;
        int[] newValues = getValues();
        newValues = getField(index).set(this, index, newValues, value);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[28]++;
        return new YearMonthDay(this, newValues);
    }

    /**
     * Returns a copy of this date with the value of the specified field increased.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * YearMonthDay added = ymd.withFieldAdded(DurationFieldType.days(), 6);
     * YearMonthDay added = ymd.plusDays(6);
     * YearMonthDay added = ymd.dayOfMonth().addToCopy(6);
     * </pre>
     * 
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this instance with the field updated
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the new datetime exceeds the capacity
     */
    public YearMonthDay withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[29]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[13]++;
            return this;

        } else {
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[14]++;}
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[31]++;
        int[] newValues = getValues();
        newValues = getField(index).add(this, index, newValues, amount);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[32]++;
        return new YearMonthDay(this, newValues);
    }

    /**
     * Returns a copy of this date with the specified period added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * Fields in the period that aren't present in the partial are ignored.
     * <p>
     * This method is typically used to add multiple copies of complex
     * period instances. Adding one field is best achieved using methods
     * like {@link #withFieldAdded(DurationFieldType, int)}
     * or {@link #plusYears(int)}.
     * 
     * @param period  the period to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this instance with the period added
     * @throws ArithmeticException if the new datetime exceeds the capacity
     */
    public YearMonthDay withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[15]++;
            return this;

        } else {
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[16]++;}
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[34]++;
        int[] newValues = getValues();
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[35]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < period.size()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.loops[1]--;
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.loops[2]--;
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.loops[3]++;
}
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[36]++;
            DurationFieldType fieldType = period.getFieldType(i);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[37]++;
            int index = indexOf(fieldType);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[17]++;
                newValues = getField(index).add(this, index, newValues,
                        FieldUtils.safeMultiply(period.getValue(i), scalar));
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[39]++;

            } else {
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[18]++;}
        }
        return new YearMonthDay(this, newValues);
    }

    //-----------------------------------------------------------------------
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
     * @return a copy of this instance with the period added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public YearMonthDay plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this date plus the specified number of years.
     * <p>
     * This date instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonthDay added = dt.plusYears(6);
     * YearMonthDay added = dt.plus(Period.years(6));
     * YearMonthDay added = dt.withFieldAdded(DurationFieldType.years(), 6);
     * </pre>
     *
     * @param years  the amount of years to add, may be negative
     * @return the new date plus the increased years
     * @since 1.1
     */
    public YearMonthDay plusYears(int years) {
        return withFieldAdded(DurationFieldType.years(), years);
    }

    /**
     * Returns a copy of this date plus the specified number of months.
     * <p>
     * This date instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonthDay added = dt.plusMonths(6);
     * YearMonthDay added = dt.plus(Period.months(6));
     * YearMonthDay added = dt.withFieldAdded(DurationFieldType.months(), 6);
     * </pre>
     *
     * @param months  the amount of months to add, may be negative
     * @return the new date plus the increased months
     * @since 1.1
     */
    public YearMonthDay plusMonths(int months) {
        return withFieldAdded(DurationFieldType.months(), months);
    }

    /**
     * Returns a copy of this date plus the specified number of days.
     * <p>
     * This date instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonthDay added = dt.plusDays(6);
     * YearMonthDay added = dt.plus(Period.days(6));
     * YearMonthDay added = dt.withFieldAdded(DurationFieldType.days(), 6);
     * </pre>
     *
     * @param days  the amount of days to add, may be negative
     * @return the new date plus the increased days
     * @since 1.1
     */
    public YearMonthDay plusDays(int days) {
        return withFieldAdded(DurationFieldType.days(), days);
    }

    //-----------------------------------------------------------------------
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
     * @return a copy of this instance with the period taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public YearMonthDay minus(ReadablePeriod period) {
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
     * YearMonthDay subtracted = dt.minusYears(6);
     * YearMonthDay subtracted = dt.minus(Period.years(6));
     * YearMonthDay subtracted = dt.withFieldAdded(DurationFieldType.years(), -6);
     * </pre>
     *
     * @param years  the amount of years to subtract, may be negative
     * @return the new datetime minus the increased years
     * @since 1.1
     */
    public YearMonthDay minusYears(int years) {
        return withFieldAdded(DurationFieldType.years(), FieldUtils.safeNegate(years));
    }

    /**
     * Returns a copy of this date minus the specified number of months.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonthDay subtracted = dt.minusMonths(6);
     * YearMonthDay subtracted = dt.minus(Period.months(6));
     * YearMonthDay subtracted = dt.withFieldAdded(DurationFieldType.months(), -6);
     * </pre>
     *
     * @param months  the amount of months to subtract, may be negative
     * @return the new datetime minus the increased months
     * @since 1.1
     */
    public YearMonthDay minusMonths(int months) {
        return withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(months));
    }

    /**
     * Returns a copy of this date minus the specified number of days.
     * <p>
     * This datetime instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonthDay subtracted = dt.minusDays(6);
     * YearMonthDay subtracted = dt.minus(Period.days(6));
     * YearMonthDay subtracted = dt.withFieldAdded(DurationFieldType.days(), -6);
     * </pre>
     *
     * @param days  the amount of days to subtract, may be negative
     * @return the new datetime minus the increased days
     * @since 1.1
     */
    public YearMonthDay minusDays(int days) {
        return withFieldAdded(DurationFieldType.days(), FieldUtils.safeNegate(days));
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
     * Converts this object to a LocalDate with the same date and chronology.
     *
     * @return a LocalDate with the same date and chronology
     * @since 1.3
     */
    public LocalDate toLocalDate() {
        return new LocalDate(getYear(), getMonthOfYear(), getDayOfMonth(), getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this YearMonthDay to a full datetime at midnight using the
     * default time zone.
     *
     * @return this date as a datetime at midnight
     */
    public DateTime toDateTimeAtMidnight() {
        return toDateTimeAtMidnight(null);
    }

    /**
     * Converts this YearMonthDay to a full datetime at midnight using the
     * specified time zone.
     * <p>
     * This method uses the chronology from this instance plus the time zone
     * specified.
     *
     * @param zone  the zone to use, null means default
     * @return this date as a datetime at midnight
     */
    public DateTime toDateTimeAtMidnight(DateTimeZone zone) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[40]++;
        Chronology chrono = getChronology().withZone(zone);
        return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), 0, 0, 0, 0, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this partial to a full datetime using the default time zone
     * setting the date fields from this instance and the time fields from
     * the current time.
     *
     * @return this date as a datetime with the time as the current time
     */
    public DateTime toDateTimeAtCurrentTime() {
        return toDateTimeAtCurrentTime(null);
    }

    /**
     * Converts this partial to a full datetime using the specified time zone
     * setting the date fields from this instance and the time fields from
     * the current time.
     * <p>
     * This method uses the chronology from this instance plus the time zone
     * specified.
     *
     * @param zone  the zone to use, null means default
     * @return this date as a datetime with the time as the current time
     */
    public DateTime toDateTimeAtCurrentTime(DateTimeZone zone) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[41]++;
        Chronology chrono = getChronology().withZone(zone);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[42]++;
        long instantMillis = DateTimeUtils.currentTimeMillis();
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[43]++;
        long resolved = chrono.set(this, instantMillis);
        return new DateTime(resolved, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a DateMidnight in the default time zone.
     *
     * @return the DateMidnight instance in the default zone
     */
    public DateMidnight toDateMidnight() {
        return toDateMidnight(null);
    }

    /**
     * Converts this object to a DateMidnight.
     *
     * @param zone  the zone to get the DateMidnight in, null means default
     * @return the DateMidnight instance
     */
    public DateMidnight toDateMidnight(DateTimeZone zone) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[44]++;
        Chronology chrono = getChronology().withZone(zone);
        return new DateMidnight(getYear(), getMonthOfYear(), getDayOfMonth(), chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a DateTime using a TimeOfDay to fill in the
     * missing fields and using the default time zone.
     * This instance is immutable and unaffected by this method call.
     * <p>
     * The resulting chronology is determined by the chronology of this
     * YearMonthDay plus the time zone.
     * The chronology of the time is ignored - only the field values are used.
     *
     * @param time  the time of day to use, null means current time
     * @return the DateTime instance
     */
    public DateTime toDateTime(TimeOfDay time) {
        return toDateTime(time, null);
    }

    /**
     * Converts this object to a DateTime using a TimeOfDay to fill in the
     * missing fields.
     * This instance is immutable and unaffected by this method call.
     * <p>
     * The resulting chronology is determined by the chronology of this
     * YearMonthDay plus the time zone.
     * The chronology of the time is ignored - only the field values are used.
     *
     * @param time  the time of day to use, null means current time
     * @param zone  the zone to get the DateTime in, null means default
     * @return the DateTime instance
     */
    public DateTime toDateTime(TimeOfDay time, DateTimeZone zone) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[45]++;
        Chronology chrono = getChronology().withZone(zone);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[46]++;
        long instant = DateTimeUtils.currentTimeMillis();
        instant = chrono.set(this, instant);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[47]++;
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((time != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[19]++;
            instant = chrono.set(time, instant);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[49]++;

        } else {
  CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.branches[20]++;}
        return new DateTime(instant, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to an Interval representing the whole day
     * in the default time zone.
     *
     * @return a interval over the day
     */
    public Interval toInterval() {
        return toInterval(null);
    }

    /**
     * Converts this object to an Interval representing the whole day.
     *
     * @param zone  the zone to get the Interval in, null means default
     * @return a interval over the day
     */
    public Interval toInterval(DateTimeZone zone) {
        zone = DateTimeUtils.getZone(zone);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[50]++;
        return toDateMidnight(zone).toInterval();
    }

    //-----------------------------------------------------------------------
    /**
     * Get the year field value.
     *
     * @return the year
     */
    public int getYear() {
        return getValue(YEAR);
    }

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
     * Returns a copy of this date with the year field updated.
     * <p>
     * YearMonthDay is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year changed.
     *
     * @param year  the year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public YearMonthDay withYear(int year) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[51]++;
        int[] newValues = getValues();
        newValues = getChronology().year().set(this, YEAR, newValues, year);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[52]++;
        return new YearMonthDay(this, newValues);
    }

    /**
     * Returns a copy of this date with the month of year field updated.
     * <p>
     * YearMonthDay is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * month of year changed.
     *
     * @param monthOfYear  the month of year to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public YearMonthDay withMonthOfYear(int monthOfYear) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[53]++;
        int[] newValues = getValues();
        newValues = getChronology().monthOfYear().set(this, MONTH_OF_YEAR, newValues, monthOfYear);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[54]++;
        return new YearMonthDay(this, newValues);
    }

    /**
     * Returns a copy of this date with the day of month field updated.
     * <p>
     * YearMonthDay is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * day of month changed.
     *
     * @param dayOfMonth  the day of month to set
     * @return a copy of this object with the field set
     * @throws IllegalArgumentException if the value is invalid
     * @since 1.3
     */
    public YearMonthDay withDayOfMonth(int dayOfMonth) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[55]++;
        int[] newValues = getValues();
        newValues = getChronology().dayOfMonth().set(this, DAY_OF_MONTH, newValues, dayOfMonth);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[56]++;
        return new YearMonthDay(this, newValues);
    }

    //-----------------------------------------------------------------------
    /**
     * Get the year field property which provides access to advanced functionality.
     * 
     * @return the year property
     */
    public Property year() {
        return new Property(this, YEAR);
    }

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
     * Output the date in the ISO8601 format YYYY-MM-DD.
     * 
     * @return ISO8601 formatted string
     */
    public String toString() {
        return ISODateTimeFormat.yearMonthDay().print(this);
    }

    //-----------------------------------------------------------------------
    /**
     * The property class for <code>YearMonthDay</code>.
     * <p>
     * This class binds a <code>YearMonthDay</code> to a <code>DateTimeField</code>.
     * 
     * @author Stephen Colebourne
     * @since 1.0
     * @deprecated Use LocalDate which has a much better internal implementation
     */
    @Deprecated
    public static class Property extends AbstractPartialFieldProperty implements Serializable {

        /** Serialization version */
        private static final long serialVersionUID = 5727734012190224363L;

        /** The partial */
        private final YearMonthDay iYearMonthDay;
        /** The field index */
        private final int iFieldIndex;

        /**
         * Constructs a property.
         * 
         * @param partial  the partial instance
         * @param fieldIndex  the index in the partial
         */
        Property(YearMonthDay partial, int fieldIndex) {
            super();
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[57]++;
            iYearMonthDay = partial;
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[58]++;
            iFieldIndex = fieldIndex;
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[59]++;
        }

        /**
         * Gets the field that this property uses.
         * 
         * @return the field
         */
        public DateTimeField getField() {
            return iYearMonthDay.getField(iFieldIndex);
        }

        /**
         * Gets the partial that this property belongs to.
         * 
         * @return the partial
         */
        protected ReadablePartial getReadablePartial() {
            return iYearMonthDay;
        }

        /**
         * Gets the partial that this property belongs to.
         * 
         * @return the partial
         */
        public YearMonthDay getYearMonthDay() {
            return iYearMonthDay;
        }

        /**
         * Gets the value of this field.
         * 
         * @return the field value
         */
        public int get() {
            return iYearMonthDay.getValue(iFieldIndex);
        }

        //-----------------------------------------------------------------------
        /**
         * Adds to the value of this field in a copy of this YearMonthDay.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it will affect larger fields.
         * Smaller fields are unaffected.
         * <p>
         * If the result would be too large, beyond the maximum year, then an
         * IllegalArgumentException is thrown.
         * <p>
         * The YearMonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the YearMonthDay with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public YearMonthDay addToCopy(int valueToAdd) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[60]++;
            int[] newValues = iYearMonthDay.getValues();
            newValues = getField().add(iYearMonthDay, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[61]++;
            return new YearMonthDay(iYearMonthDay, newValues);
        }

        /**
         * Adds to the value of this field in a copy of this YearMonthDay wrapping
         * within this field if the maximum value is reached.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it wraps within this field.
         * Other fields are unaffected.
         * <p>
         * For example,
         * <code>2004-12-20</code> addWrapField one month returns <code>2004-01-20</code>.
         * <p>
         * The YearMonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the YearMonthDay with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public YearMonthDay addWrapFieldToCopy(int valueToAdd) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[62]++;
            int[] newValues = iYearMonthDay.getValues();
            newValues = getField().addWrapField(iYearMonthDay, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[63]++;
            return new YearMonthDay(iYearMonthDay, newValues);
        }

        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the YearMonthDay.
         * <p>
         * The YearMonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param value  the value to set the field in the copy to
         * @return a copy of the YearMonthDay with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public YearMonthDay setCopy(int value) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[64]++;
            int[] newValues = iYearMonthDay.getValues();
            newValues = getField().set(iYearMonthDay, iFieldIndex, newValues, value);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[65]++;
            return new YearMonthDay(iYearMonthDay, newValues);
        }

        /**
         * Sets this field in a copy of the YearMonthDay to a parsed text value.
         * <p>
         * The YearMonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the YearMonthDay with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public YearMonthDay setCopy(String text, Locale locale) {
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[66]++;
            int[] newValues = iYearMonthDay.getValues();
            newValues = getField().set(iYearMonthDay, iFieldIndex, newValues, text, locale);
CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d.statements[67]++;
            return new YearMonthDay(iYearMonthDay, newValues);
        }

        /**
         * Sets this field in a copy of the YearMonthDay to a parsed text value.
         * <p>
         * The YearMonthDay attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @return a copy of the YearMonthDay with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public YearMonthDay setCopy(String text) {
            return setCopy(text, null);
        }

        //-----------------------------------------------------------------------
        /**
         * Returns a new YearMonthDay with this field set to the maximum value
         * for this field.
         * <p>
         * This operation is useful for obtaining a DateTime on the last day
         * of the month, as month lengths vary.
         * <pre>
         * YearMonthDay lastDayOfMonth = dt.dayOfMonth().withMaximumValue();
         * </pre>
         * <p>
         * The YearMonthDay attached to this property is unchanged by this call.
         *
         * @return a copy of the YearMonthDay with this field set to its maximum
         * @since 1.2
         */
        public YearMonthDay withMaximumValue() {
            return setCopy(getMaximumValue());
        }

        /**
         * Returns a new YearMonthDay with this field set to the minimum value
         * for this field.
         * <p>
         * The YearMonthDay attached to this property is unchanged by this call.
         *
         * @return a copy of the YearMonthDay with this field set to its minimum
         * @since 1.2
         */
        public YearMonthDay withMinimumValue() {
            return setCopy(getMinimumValue());
        }
    }

}

class CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.YearMonthDay.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$11mvbf3cumm3in7tquqs8i5bn1d () {
    super("org.joda.time.YearMonthDay.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.YearMonthDay.java");
      for (int i = 1; i <= 67; i++) {
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

