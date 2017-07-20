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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * YearMonth is an immutable partial supporting the year and monthOfYear fields.
 * <p>
 * NOTE: This class only supports the two fields listed above.
 * It is impossible to query any other fields, such as dayOfWeek or centuryOfEra.
 * <p>
 * Calculations on YearMonth are performed using a {@link Chronology}.
 * This chronology is set to be in the UTC time zone for all calculations.
 * <p>
 * One use case for this class is to store a credit card expiry date, as that only
 * references the year and month.
 * This class can be used as the gYearMonth type in XML Schema.
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
 * YearMonth is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 2.0
 */
public final class YearMonth
        extends BasePartial
        implements ReadablePartial, Serializable {
  static {
    CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 797544782896179L;
  static {
    CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[1]++;
  }
    /** The singleton set of field types */
    private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[] {
        DateTimeFieldType.year(),
        DateTimeFieldType.monthOfYear(),
    };
  static {
    CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[2]++;
  }

    /** The index of the year field in the field array */
    public static final int YEAR = 0;
  static {
    CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[3]++;
  }
    /** The index of the monthOfYear field in the field array */
    public static final int MONTH_OF_YEAR = 1;
  static {
    CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[4]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains a {@code YearMonth} set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     * The resulting object does not use the zone.
     * 
     * @return the current year-month, not null
     * @since 2.0
     */
    public static YearMonth now() {
        return new YearMonth();
    }

    /**
     * Obtains a {@code YearMonth} set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * The resulting object does not use the zone.
     *
     * @param zone  the time zone, not null
     * @return the current year-month, not null
     * @since 2.0
     */
    public static YearMonth now(DateTimeZone zone) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[1]++;
            throw new NullPointerException("Zone must not be null");

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[2]++;}
        return new YearMonth(zone);
    }

    /**
     * Obtains a {@code YearMonth} set to the current system millisecond time
     * using the specified chronology.
     * The resulting object does not use the zone.
     *
     * @param chronology  the chronology, not null
     * @return the current year-month, not null
     * @since 2.0
     */
    public static YearMonth now(Chronology chronology) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chronology == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[3]++;
            throw new NullPointerException("Chronology must not be null");

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[4]++;}
        return new YearMonth(chronology);
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code YearMonth} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#localDateParser()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static YearMonth parse(String str) {
        return parse(str, ISODateTimeFormat.localDateParser());
    }

    /**
     * Parses a {@code YearMonth} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static YearMonth parse(String str, DateTimeFormatter formatter) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[7]++;
        LocalDate date = formatter.parseLocalDate(str);
        return new YearMonth(date.getYear(), date.getMonthOfYear());
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a YearMonth from a <code>java.util.Calendar</code>
     * using exactly the same field values avoiding any time zone effects.
     * <p>
     * Each field is queried from the Calendar and assigned to the YearMonth.
     * <p>
     * This factory method ignores the type of the calendar and always
     * creates a YearMonth with ISO chronology. It is expected that you
     * will only pass in instances of <code>GregorianCalendar</code> however
     * this is not validated.
     *
     * @param calendar  the Calendar to extract fields from
     * @return the created YearMonth, never null
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the year or month is invalid for the ISO chronology
     */
    public static YearMonth fromCalendarFields(Calendar calendar) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((calendar == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[5]++;
            throw new IllegalArgumentException("The calendar must not be null");

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[6]++;}
        return new YearMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * Constructs a YearMonth from a <code>java.util.Date</code>
     * using exactly the same field values avoiding any time zone effects.
     * <p>
     * Each field is queried from the Date and assigned to the YearMonth.
     * <p>
     * This factory method always creates a YearMonth with ISO chronology.
     *
     * @param date  the Date to extract fields from
     * @return the created YearMonth, never null
     * @throws IllegalArgumentException if the calendar is null
     * @throws IllegalArgumentException if the year or month is invalid for the ISO chronology
     */
    @SuppressWarnings("deprecation")
    public static YearMonth fromDateFields(Date date) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[7]++;
            throw new IllegalArgumentException("The date must not be null");

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[8]++;}
        return new YearMonth(date.getYear() + 1900, date.getMonth() + 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a YearMonth with the current year-month, using ISOChronology in
     * the default zone to extract the fields.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     * 
     * @see #now()
     */
    public YearMonth() {
        super();
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[10]++;
    }

    /**
     * Constructs a YearMonth with the current year-month, using ISOChronology in
     * the specified zone to extract the fields.
     * <p>
     * The constructor uses the specified time zone to obtain the current year-month.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     * 
     * @param zone  the zone to use, null means default zone
     * @see #now(DateTimeZone)
     */
    public YearMonth(DateTimeZone zone) {
        super(ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[11]++;
    }

    /**
     * Constructs a YearMonth with the current year-month, using the specified chronology
     * and zone to extract the fields.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a time-zone (by switching to UTC).
     *
     * @param chronology  the chronology, null means ISOChronology in the default zone
     * @see #now(Chronology)
     */
    public YearMonth(Chronology chronology) {
        super(chronology);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[12]++;
    }

    /**
     * Constructs a YearMonth extracting the partial fields from the specified
     * milliseconds using the ISOChronology in the default zone.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public YearMonth(long instant) {
        super(instant);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[13]++;
    }

    /**
     * Constructs a YearMonth extracting the partial fields from the specified
     * milliseconds using the chronology provided.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a time-zone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public YearMonth(long instant, Chronology chronology) {
        super(instant, chronology);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[14]++;
    }

    /**
     * Constructs a YearMonth from an Object that represents some form of time.
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
    public YearMonth(Object instant) {
        super(instant, null, ISODateTimeFormat.localDateParser());
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[15]++;
    }

    /**
     * Constructs a YearMonth from an Object that represents some form of time,
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
    public YearMonth(Object instant, Chronology chronology) {
        super(instant, DateTimeUtils.getChronology(chronology), ISODateTimeFormat.localDateParser());
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[16]++;
    }

    /**
     * Constructs a YearMonth with specified year and month
     * using <code>ISOChronology</code>.
     * <p>
     * The constructor uses the no time zone initialising the fields as provided.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a time-zone (by switching to UTC).
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     */
    public YearMonth(int year, int monthOfYear) {
        this(year, monthOfYear, null);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[17]++;
    }

    /**
     * Constructs an instance set to the specified year and month
     * using the specified chronology, whose zone is ignored.
     * <p>
     * If the chronology is null, <code>ISOChronology</code> is used.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a time-zone (by switching to UTC).
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public YearMonth(int year, int monthOfYear, Chronology chronology) {
        super(new int[] {year, monthOfYear}, chronology);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[18]++;
    }

    /**
     * Constructs a YearMonth with chronology from this instance and new values.
     *
     * @param partial  the partial to base this new instance on
     * @param values  the new set of values
     */
    YearMonth(YearMonth partial, int[] values) {
        super(partial, values);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[19]++;
    }

    /**
     * Constructs a YearMonth with values from this instance and a new chronology.
     *
     * @param partial  the partial to base this new instance on
     * @param chrono  the new chronology
     */
    YearMonth(YearMonth partial, Chronology chrono) {
        super(partial, chrono);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[20]++;
    }

    /**
     * Handle broken serialization from other tools.
     * @return the resolved object, not null
     */
    private Object readResolve() {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((DateTimeZone.UTC.equals(getChronology().getZone()) == false) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[9]++;
            return new YearMonth(this, getChronology().withUTC());

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[10]++;}
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields in this partial, which is two.
     * The supported fields are Year and MonthOfYear.
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
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[22]++;
        switch (index) {
            case YEAR:
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[11]++;
                return chrono.year();
            case MONTH_OF_YEAR:
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[12]++;
                return chrono.monthOfYear();
            default:
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[13]++;
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
     * The fields are returned largest to smallest, Year, Month.
     *
     * @return the array of field types (cloned), largest to smallest, never null
     */
    public DateTimeFieldType[] getFieldTypes() {
        return (DateTimeFieldType[]) FIELD_TYPES.clone();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this year-month with the specified chronology.
     * This instance is immutable and unaffected by this method call.
     * <p>
     * This method retains the values of the fields, thus the result will
     * typically refer to a different instant.
     * <p>
     * The time zone of the specified chronology is ignored, as YearMonth
     * operates without a time zone.
     *
     * @param newChronology  the new chronology, null means ISO
     * @return a copy of this year-month with a different chronology, never null
     * @throws IllegalArgumentException if the values are invalid for the new chronology
     */
    public YearMonth withChronologyRetainFields(Chronology newChronology) {
        newChronology = DateTimeUtils.getChronology(newChronology);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[23]++;
        newChronology = newChronology.withUTC();
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[24]++;
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((newChronology == getChronology()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[14]++;
            return this;

        } else {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[15]++;
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[26]++;
            YearMonth newYearMonth = new YearMonth(this, newChronology);
            newChronology.validate(newYearMonth, getValues());
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[27]++;
            return newYearMonth;
        }
    }

    /**
     * Returns a copy of this year-month with the specified field set to a new value.
     * <p>
     * For example, if the field type is <code>monthOfYear</code> then the month
     * would be changed in the returned instance.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * YearMonth updated = ym.withField(DateTimeFieldType.monthOfYear(), 6);
     * YearMonth updated = ym.monthOfYear().setCopy(6);
     * YearMonth updated = ym.property(DateTimeFieldType.monthOfYear()).setCopy(6);
     * </pre>
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this instance with the field set, never null
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public YearMonth withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[28]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value == getValue(index)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[16]++;
            return this;

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[17]++;}
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[30]++;
        int[] newValues = getValues();
        newValues = getField(index).set(this, index, newValues, value);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[31]++;
        return new YearMonth(this, newValues);
    }

    /**
     * Returns a copy of this year-month with the value of the specified field increased.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * <p>
     * These three lines are equivalent:
     * <pre>
     * YearMonth added = ym.withFieldAdded(DurationFieldType.months(), 6);
     * YearMonth added = ym.plusMonths(6);
     * YearMonth added = ym.monthOfYear().addToCopy(6);
     * </pre>
     * 
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this instance with the field updated, never null
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the new date-time exceeds the capacity
     */
    public YearMonth withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[32]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[18]++;
            return this;

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[19]++;}
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[34]++;
        int[] newValues = getValues();
        newValues = getField(index).add(this, index, newValues, amount);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[35]++;
        return new YearMonth(this, newValues);
    }

    /**
     * Returns a copy of this year-month with the specified period added.
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
     * @return a copy of this instance with the period added, never null
     * @throws ArithmeticException if the new date-time exceeds the capacity
     */
    public YearMonth withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[20]++;
            return this;

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[21]++;}
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[37]++;
        int[] newValues = getValues();
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[38]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < period.size()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.loops[1]--;
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.loops[2]--;
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.loops[3]++;
}
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[39]++;
            DurationFieldType fieldType = period.getFieldType(i);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[40]++;
            int index = indexOf(fieldType);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[41]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[22]++;
                newValues = getField(index).add(this, index, newValues,
                        FieldUtils.safeMultiply(period.getValue(i), scalar));
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[42]++;

            } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[23]++;}
        }
        return new YearMonth(this, newValues);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this year-month with the specified period added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to add complex period instances.
     * Adding one field is best achieved using methods
     * like {@link #plusYears(int)}.
     * 
     * @param period  the duration to add to this one, null means zero
     * @return a copy of this instance with the period added, never null
     * @throws ArithmeticException if the new year-month exceeds the capacity
     */
    public YearMonth plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this year-month plus the specified number of years.
     * <p>
     * This year-month instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonth added = ym.plusYears(6);
     * YearMonth added = ym.plus(Period.years(6));
     * YearMonth added = ym.withFieldAdded(DurationFieldType.years(), 6);
     * </pre>
     *
     * @param years  the amount of years to add, may be negative
     * @return the new year-month plus the increased years, never null
     */
    public YearMonth plusYears(int years) {
        return withFieldAdded(DurationFieldType.years(), years);
    }

    /**
     * Returns a copy of this year-month plus the specified number of months.
     * <p>
     * This year-month instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonth added = ym.plusMonths(6);
     * YearMonth added = ym.plus(Period.months(6));
     * YearMonth added = ym.withFieldAdded(DurationFieldType.months(), 6);
     * </pre>
     *
     * @param months  the amount of months to add, may be negative
     * @return the new year-month plus the increased months, never null
     */
    public YearMonth plusMonths(int months) {
        return withFieldAdded(DurationFieldType.months(), months);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this year-month with the specified period taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * <p>
     * This method is typically used to subtract complex period instances.
     * Subtracting one field is best achieved using methods
     * like {@link #minusYears(int)}.
     * 
     * @param period  the period to reduce this instant by
     * @return a copy of this instance with the period taken away, never null
     * @throws ArithmeticException if the new year-month exceeds the capacity
     */
    public YearMonth minus(ReadablePeriod period) {
        return withPeriodAdded(period, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this year-month minus the specified number of years.
     * <p>
     * This year-month instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonth subtracted = ym.minusYears(6);
     * YearMonth subtracted = ym.minus(Period.years(6));
     * YearMonth subtracted = ym.withFieldAdded(DurationFieldType.years(), -6);
     * </pre>
     *
     * @param years  the amount of years to subtract, may be negative
     * @return the new year-month minus the increased years, never null
     */
    public YearMonth minusYears(int years) {
        return withFieldAdded(DurationFieldType.years(), FieldUtils.safeNegate(years));
    }

    /**
     * Returns a copy of this year-month minus the specified number of months.
     * <p>
     * This year-month instance is immutable and unaffected by this method call.
     * <p>
     * The following three lines are identical in effect:
     * <pre>
     * YearMonth subtracted = ym.minusMonths(6);
     * YearMonth subtracted = ym.minus(Period.months(6));
     * YearMonth subtracted = ym.withFieldAdded(DurationFieldType.months(), -6);
     * </pre>
     *
     * @param months  the amount of months to subtract, may be negative
     * @return the new year-month minus the increased months, never null
     */
    public YearMonth minusMonths(int months) {
        return withFieldAdded(DurationFieldType.months(), FieldUtils.safeNegate(months));
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to a LocalDate with the same year-month and chronology.
     *
     * @param dayOfMonth the day of month to use, valid for chronology, such as 1-31 for ISO
     * @return a LocalDate with the same year-month and chronology, never null
     */
    public LocalDate toLocalDate(int dayOfMonth) {
        return new LocalDate(getYear(), getMonthOfYear(), dayOfMonth, getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this object to an Interval representing the whole month.
     * <p>
     * The interval will use the chronology of the year-month in the default zone.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @return an interval over the month, never null
     */
    public Interval toInterval() {
        return toInterval(null);
    }

    /**
     * Converts this object to an Interval representing the whole month.
     * <p>
     * The interval will use the chronology of the year-month in the specified zone.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param zone  the zone to get the Interval in, null means default
     * @return an interval over the month, never null
     */
    public Interval toInterval(DateTimeZone zone) {
        zone = DateTimeUtils.getZone(zone);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[43]++;
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[44]++;
        DateTime start = toLocalDate(1).toDateTimeAtStartOfDay(zone);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[45]++;
        DateTime end = plusMonths(1).toLocalDate(1).toDateTimeAtStartOfDay(zone);
        return new Interval(start, end);
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

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of this year-month with the year field updated.
     * <p>
     * YearMonth is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * year changed.
     *
     * @param year  the year to set
     * @return a copy of this object with the field set, never null
     * @throws IllegalArgumentException if the value is invalid
     */
    public YearMonth withYear(int year) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[46]++;
        int[] newValues = getValues();
        newValues = getChronology().year().set(this, YEAR, newValues, year);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[47]++;
        return new YearMonth(this, newValues);
    }

    /**
     * Returns a copy of this year-month with the month of year field updated.
     * <p>
     * YearMonth is immutable, so there are no set methods.
     * Instead, this method returns a new instance with the value of
     * month of year changed.
     *
     * @param monthOfYear  the month of year to set
     * @return a copy of this object with the field set, never null
     * @throws IllegalArgumentException if the value is invalid
     */
    public YearMonth withMonthOfYear(int monthOfYear) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[48]++;
        int[] newValues = getValues();
        newValues = getChronology().monthOfYear().set(this, MONTH_OF_YEAR, newValues, monthOfYear);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[49]++;
        return new YearMonth(this, newValues);
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

    //-----------------------------------------------------------------------
    /**
     * Output the year-month in ISO8601 format (yyyy-MM).
     *
     * @return ISO8601 time formatted string.
     */
    @ToString
    public String toString() {
        return ISODateTimeFormat.yearMonth().print(this);
    }

    /**
     * Output the year-month using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[24]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[25]++;}
        return DateTimeFormat.forPattern(pattern).print(this);
    }

    /**
     * Output the year-month using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @param locale  Locale to use, null means default
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern, Locale locale) throws IllegalArgumentException {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[26]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.branches[27]++;}
        return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
    }

    //-----------------------------------------------------------------------
    /**
     * The property class for <code>YearMonth</code>.
     * <p>
     * This class binds a <code>YearMonth</code> to a <code>DateTimeField</code>.
     * 
     * @author Stephen Colebourne
     * @since 2.0
     */
    public static class Property extends AbstractPartialFieldProperty implements Serializable {

        /** Serialization version */
        private static final long serialVersionUID = 5727734012190224363L;

        /** The partial */
        private final YearMonth iBase;
        /** The field index */
        private final int iFieldIndex;

        /**
         * Constructs a property.
         * 
         * @param partial  the partial instance
         * @param fieldIndex  the index in the partial
         */
        Property(YearMonth partial, int fieldIndex) {
            super();
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[52]++;
            iBase = partial;
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[53]++;
            iFieldIndex = fieldIndex;
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[54]++;
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
        public YearMonth getYearMonth() {
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
         * Adds to the value of this field in a copy of this YearMonth.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it will affect larger fields.
         * Smaller fields are unaffected.
         * <p>
         * If the result would be too large, beyond the maximum year, then an
         * IllegalArgumentException is thrown.
         * <p>
         * The YearMonth attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the YearMonth with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public YearMonth addToCopy(int valueToAdd) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[55]++;
            int[] newValues = iBase.getValues();
            newValues = getField().add(iBase, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[56]++;
            return new YearMonth(iBase, newValues);
        }

        /**
         * Adds to the value of this field in a copy of this YearMonth wrapping
         * within this field if the maximum value is reached.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it wraps within this field.
         * Other fields are unaffected.
         * <p>
         * For example,
         * <code>2004-12</code> addWrapField one month returns <code>2004-01</code>.
         * <p>
         * The YearMonth attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the YearMonth with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public YearMonth addWrapFieldToCopy(int valueToAdd) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[57]++;
            int[] newValues = iBase.getValues();
            newValues = getField().addWrapField(iBase, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[58]++;
            return new YearMonth(iBase, newValues);
        }

        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the YearMonth.
         * <p>
         * The YearMonth attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param value  the value to set the field in the copy to
         * @return a copy of the YearMonth with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public YearMonth setCopy(int value) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[59]++;
            int[] newValues = iBase.getValues();
            newValues = getField().set(iBase, iFieldIndex, newValues, value);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[60]++;
            return new YearMonth(iBase, newValues);
        }

        /**
         * Sets this field in a copy of the YearMonth to a parsed text value.
         * <p>
         * The YearMonth attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the YearMonth with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public YearMonth setCopy(String text, Locale locale) {
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[61]++;
            int[] newValues = iBase.getValues();
            newValues = getField().set(iBase, iFieldIndex, newValues, text, locale);
CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl.statements[62]++;
            return new YearMonth(iBase, newValues);
        }

        /**
         * Sets this field in a copy of the YearMonth to a parsed text value.
         * <p>
         * The YearMonth attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @return a copy of the YearMonth with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public YearMonth setCopy(String text) {
            return setCopy(text, null);
        }
    }

}

class CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl ());
  }
    public static long[] statements = new long[63];
    public static long[] branches = new long[28];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.joda.time.YearMonth.java";
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

  public CodeCoverCoverageCounter$3rn1esb4cqm9fu8ixcrlnl () {
    super("org.joda.time.YearMonth.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 62; i++) {
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
    log.startNamedSection("org.joda.time.YearMonth.java");
      for (int i = 1; i <= 62; i++) {
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

