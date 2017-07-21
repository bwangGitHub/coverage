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

import org.joda.convert.FromString;
import org.joda.time.base.BasePeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * Standard mutable time period implementation.
 * <p>
 * A time period is divided into a number of fields, such as hours and seconds.
 * Which fields are supported is defined by the PeriodType class.
 * The default is the standard period type, which supports years, months, weeks, days,
 * hours, minutes, seconds and millis.
 * <p>
 * When this time period is added to an instant, the effect is of adding each field in turn.
 * As a result, this takes into account daylight savings time.
 * Adding a time period of 1 day to the day before daylight savings starts will only add
 * 23 hours rather than 24 to ensure that the time remains the same.
 * If this is not the behaviour you want, then see {@link Duration}.
 * <p>
 * The definition of a period also affects the equals method. A period of 1
 * day is not equal to a period of 24 hours, nor 1 hour equal to 60 minutes.
 * This is because periods represent an abstracted definition of a time period
 * (eg. a day may not actually be 24 hours, it might be 23 or 25 at daylight
 * savings boundary). To compare the actual duration of two periods, convert
 * both to durations using toDuration, an operation that emphasises that the
 * result may differ according to the date you choose.
 * <p>
 * MutablePeriod is mutable and not thread-safe, unless concurrent threads
 * are not invoking mutator methods.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 * @see Period
 */
public class MutablePeriod
        extends BasePeriod
        implements ReadWritablePeriod, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 3436451121567212165L;
  static {
    CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[1]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code MutablePeriod} from the specified string.
     * <p>
     * This uses {@link ISOPeriodFormat#standard()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static MutablePeriod parse(String str) {
        return parse(str, ISOPeriodFormat.standard());
    }

    /**
     * Parses a {@code MutablePeriod} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static MutablePeriod parse(String str, PeriodFormatter formatter) {
        return formatter.parsePeriod(str).toMutablePeriod();
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a zero-length period using the standard period type.
     */
    public MutablePeriod() {
        super(0L, null, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[2]++;
    }

    /**
     * Creates a zero-length period using the specified period type.
     *
     * @param type  which set of fields this period supports
     */
    public MutablePeriod(PeriodType type) {
        super(0L, type, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[3]++;
    }

    /**
     * Create a period from a set of field values using the standard set of fields.
     *
     * @param hours  amount of hours in this period
     * @param minutes  amount of minutes in this period
     * @param seconds  amount of seconds in this period
     * @param millis  amount of milliseconds in this period
     */
    public MutablePeriod(int hours, int minutes, int seconds, int millis) {
        super(0, 0, 0, 0, hours, minutes, seconds, millis, PeriodType.standard());
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[4]++;
    }

    /**
     * Create a period from a set of field values using the standard set of fields.
     *
     * @param years  amount of years in this period
     * @param months  amount of months in this period
     * @param weeks  amount of weeks in this period
     * @param days  amount of days in this period
     * @param hours  amount of hours in this period
     * @param minutes  amount of minutes in this period
     * @param seconds  amount of seconds in this period
     * @param millis  amount of milliseconds in this period
     */
    public MutablePeriod(int years, int months, int weeks, int days,
                  int hours, int minutes, int seconds, int millis) {
        super(years, months, weeks, days, hours, minutes, seconds, millis, PeriodType.standard());
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[5]++;
    }

    /**
     * Create a period from a set of field values.
     *
     * @param years  amount of years in this period, which must be zero if unsupported
     * @param months  amount of months in this period, which must be zero if unsupported
     * @param weeks  amount of weeks in this period, which must be zero if unsupported
     * @param days  amount of days in this period, which must be zero if unsupported
     * @param hours  amount of hours in this period, which must be zero if unsupported
     * @param minutes  amount of minutes in this period, which must be zero if unsupported
     * @param seconds  amount of seconds in this period, which must be zero if unsupported
     * @param millis  amount of milliseconds in this period, which must be zero if unsupported
     * @param type  which set of fields this period supports, null means AllType
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    public MutablePeriod(int years, int months, int weeks, int days,
                    int hours, int minutes, int seconds, int millis, PeriodType type) {
        super(years, months, weeks, days, hours, minutes, seconds, millis, type);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[6]++;
    }

    /**
     * Creates a period from the given millisecond duration using the standard
     * set of fields.
     * <p>
     * Only precise fields in the period type will be used.
     * For the standard period type this is the time fields only.
     * Thus the year, month, week and day fields will not be populated.
     * <p>
     * If the duration is small, less than one day, then this method will perform
     * as you might expect and split the fields evenly.
     * <p>
     * If the duration is larger than one day then all the remaining duration will
     * be stored in the largest available precise field, hours in this case.
     * <p>
     * For example, a duration equal to (365 + 60 + 5) days will be converted to
     * ((365 + 60 + 5) * 24) hours by this constructor.
     * <p>
     * For more control over the conversion process, you have two options:
     * <ul>
     * <li>convert the duration to an {@link Interval}, and from there obtain the period
     * <li>specify a period type that contains precise definitions of the day and larger
     * fields, such as the UTC or precise types.
     * </ul>
     *
     * @param duration  the duration, in milliseconds
     */
    public MutablePeriod(long duration) {
        super(duration);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[7]++;
    }

    /**
     * Creates a period from the given millisecond duration.
     * <p>
     * Only precise fields in the period type will be used.
     * Imprecise fields will not be populated.
     * <p>
     * If the duration is small then this method will perform
     * as you might expect and split the fields evenly.
     * <p>
     * If the duration is large then all the remaining duration will
     * be stored in the largest available precise field.
     * For details as to which fields are precise, review the period type javadoc.
     *
     * @param duration  the duration, in milliseconds
     * @param type  which set of fields this period supports, null means standard
     */
    public MutablePeriod(long duration, PeriodType type) {
        super(duration, type, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[8]++;
    }

    /**
     * Creates a period from the given millisecond duration using the standard
     * set of fields.
     * <p>
     * Only precise fields in the period type will be used.
     * Imprecise fields will not be populated.
     * <p>
     * If the duration is small then this method will perform
     * as you might expect and split the fields evenly.
     * <p>
     * If the duration is large then all the remaining duration will
     * be stored in the largest available precise field.
     * For details as to which fields are precise, review the period type javadoc.
     *
     * @param duration  the duration, in milliseconds
     * @param chronology  the chronology to use to split the duration, null means ISO default
     */
    public MutablePeriod(long duration, Chronology chronology) {
        super(duration, null, chronology);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[9]++;
    }

    /**
     * Creates a period from the given millisecond duration.
     * <p>
     * Only precise fields in the period type will be used.
     * Imprecise fields will not be populated.
     * <p>
     * If the duration is small then this method will perform
     * as you might expect and split the fields evenly.
     * <p>
     * If the duration is large then all the remaining duration will
     * be stored in the largest available precise field.
     * For details as to which fields are precise, review the period type javadoc.
     *
     * @param duration  the duration, in milliseconds
     * @param type  which set of fields this period supports, null means standard
     * @param chronology  the chronology to use to split the duration, null means ISO default
     */
    public MutablePeriod(long duration, PeriodType type, Chronology chronology) {
        super(duration, type, chronology);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[10]++;
    }

    /**
     * Creates a period from the given interval endpoints using the standard
     * set of fields.
     *
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     */
    public MutablePeriod(long startInstant, long endInstant) {
        super(startInstant, endInstant, null, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[11]++;
    }

    /**
     * Creates a period from the given interval endpoints.
     *
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     * @param type  which set of fields this period supports, null means standard
     */
    public MutablePeriod(long startInstant, long endInstant, PeriodType type) {
        super(startInstant, endInstant, type, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[12]++;
    }

    /**
     * Creates a period from the given interval endpoints using the standard
     * set of fields.
     *
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     * @param chrono  the chronology to use, null means ISO in default zone
     */
    public MutablePeriod(long startInstant, long endInstant, Chronology chrono) {
        super(startInstant, endInstant, null, chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[13]++;
    }

    /**
     * Creates a period from the given interval endpoints.
     *
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     * @param type  which set of fields this period supports, null means standard
     * @param chrono  the chronology to use, null means ISO in default zone
     */
    public MutablePeriod(long startInstant, long endInstant, PeriodType type, Chronology chrono) {
        super(startInstant, endInstant, type, chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[14]++;
    }

    /**
     * Creates a period from the given interval endpoints using the standard
     * set of fields.
     * <p>
     * The chronology of the start instant is used, unless that is null when the
     * chronology of the end instant is used instead.
     *
     * @param startInstant  interval start, null means now
     * @param endInstant  interval end, null means now
     */
    public MutablePeriod(ReadableInstant startInstant, ReadableInstant endInstant) {
        super(startInstant, endInstant, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[15]++;
    }

    /**
     * Creates a period from the given interval endpoints.
     * <p>
     * The chronology of the start instant is used, unless that is null when the
     * chronology of the end instant is used instead.
     *
     * @param startInstant  interval start, null means now
     * @param endInstant  interval end, null means now
     * @param type  which set of fields this period supports, null means AllType
     */
    public MutablePeriod(ReadableInstant startInstant, ReadableInstant endInstant, PeriodType type) {
        super(startInstant, endInstant, type);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[16]++;
    }

    /**
     * Creates a period from the given start point and the duration.
     *
     * @param startInstant  the interval start, null means now
     * @param duration  the duration of the interval, null means zero-length
     */
    public MutablePeriod(ReadableInstant startInstant, ReadableDuration duration) {
        super(startInstant, duration, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[17]++;
    }

    /**
     * Creates a period from the given start point and the duration.
     *
     * @param startInstant  the interval start, null means now
     * @param duration  the duration of the interval, null means zero-length
     * @param type  which set of fields this period supports, null means standard
     */
    public MutablePeriod(ReadableInstant startInstant, ReadableDuration duration, PeriodType type) {
        super(startInstant, duration, type);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[18]++;
    }

    /**
     * Creates a period from the given duration and end point.
     *
     * @param duration  the duration of the interval, null means zero-length
     * @param endInstant  the interval end, null means now
     */
    public MutablePeriod(ReadableDuration duration, ReadableInstant endInstant) {
        super(duration, endInstant, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[19]++;
    }

    /**
     * Creates a period from the given duration and end point.
     *
     * @param duration  the duration of the interval, null means zero-length
     * @param endInstant  the interval end, null means now
     * @param type  which set of fields this period supports, null means standard
     */
    public MutablePeriod(ReadableDuration duration, ReadableInstant endInstant, PeriodType type) {
        super(duration, endInstant, type);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[20]++;
    }

    /**
     * Creates a period by converting or copying from another object.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadablePeriod, ReadableInterval and String.
     * The String formats are described by {@link ISOPeriodFormat#standard()}.
     *
     * @param period  period to convert
     * @throws IllegalArgumentException if period is invalid
     * @throws UnsupportedOperationException if an unsupported field's value is non-zero
     */
    public MutablePeriod(Object period) {
        super(period, null, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[21]++;
    }

    /**
     * Creates a period by converting or copying from another object.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadablePeriod, ReadableInterval and String.
     * The String formats are described by {@link ISOPeriodFormat#standard()}.
     *
     * @param period  period to convert
     * @param type  which set of fields this period supports, null means use converter
     * @throws IllegalArgumentException if period is invalid
     * @throws UnsupportedOperationException if an unsupported field's value is non-zero
     */
    public MutablePeriod(Object period, PeriodType type) {
        super(period, type, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[22]++;
    }

    /**
     * Creates a period by converting or copying from another object.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadablePeriod, ReadableInterval and String.
     * The String formats are described by {@link ISOPeriodFormat#standard()}.
     *
     * @param period  period to convert
     * @param chrono  the chronology to use, null means ISO in default zone
     * @throws IllegalArgumentException if period is invalid
     * @throws UnsupportedOperationException if an unsupported field's value is non-zero
     */
    public MutablePeriod(Object period, Chronology chrono) {
        super(period, null, chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[23]++;
    }

    /**
     * Creates a period by converting or copying from another object.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadablePeriod, ReadableInterval and String.
     * The String formats are described by {@link ISOPeriodFormat#standard()}.
     *
     * @param period  period to convert
     * @param type  which set of fields this period supports, null means use converter
     * @param chrono  the chronology to use, null means ISO in default zone
     * @throws IllegalArgumentException if period is invalid
     * @throws UnsupportedOperationException if an unsupported field's value is non-zero
     */
    public MutablePeriod(Object period, PeriodType type, Chronology chrono) {
        super(period, type, chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[24]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Clears the period, setting all values back to zero.
     */
    public void clear() {
        super.setValues(new int[size()]);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[25]++;
    }

    /**
     * Sets the value of one of the fields by index.
     *
     * @param index  the field index
     * @param value  the new value for the field
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void setValue(int index, int value) {
        super.setValue(index, value);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[26]++;
    }

    /**
     * Sets the value of one of the fields.
     * <p>
     * The field type specified must be one of those that is supported by the period.
     *
     * @param field  a DurationFieldType instance that is supported by this period, not null
     * @param value  the new value for the field
     * @throws IllegalArgumentException if the field is null or not supported
     */
    public void set(DurationFieldType field, int value) {
        super.setField(field, value);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[27]++;
    }

    /**
     * Sets all the fields in one go from another ReadablePeriod.
     * 
     * @param period  the period to set, null means zero length period
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    public void setPeriod(ReadablePeriod period) {
        super.setPeriod(period);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[28]++;
    }

    /**
     * Sets all the fields in one go.
     * 
     * @param years  amount of years in this period, which must be zero if unsupported
     * @param months  amount of months in this period, which must be zero if unsupported
     * @param weeks  amount of weeks in this period, which must be zero if unsupported
     * @param days  amount of days in this period, which must be zero if unsupported
     * @param hours  amount of hours in this period, which must be zero if unsupported
     * @param minutes  amount of minutes in this period, which must be zero if unsupported
     * @param seconds  amount of seconds in this period, which must be zero if unsupported
     * @param millis  amount of milliseconds in this period, which must be zero if unsupported
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    public void setPeriod(int years, int months, int weeks, int days,
                          int hours, int minutes, int seconds, int millis) {
        super.setPeriod(years, months, weeks, days, hours, minutes, seconds, millis);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[29]++;
    }

    /**
     * Sets all the fields in one go from an interval using the ISO chronology
     * and dividing the fields using the period type.
     * 
     * @param interval  the interval to set, null means zero length
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(ReadableInterval interval) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[30]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[1]++;
            setPeriod(0L);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[31]++;

        } else {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[2]++;
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[32]++;
            Chronology chrono = DateTimeUtils.getChronology(interval.getChronology());
            setPeriod(interval.getStartMillis(), interval.getEndMillis(), chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[33]++;
        }
    }

    /**
     * Sets all the fields in one go from two instants representing an interval.
     * <p>
     * The chronology of the start instant is used, unless that is null when the
     * chronology of the end instant is used instead.
     * 
     * @param start  the start instant, null means now
     * @param end  the end instant, null means now
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[34]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((start == end) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[3]++;
            setPeriod(0L);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[35]++;

        } else {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[4]++;
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[36]++;
            long startMillis = DateTimeUtils.getInstantMillis(start);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[37]++;
            long endMillis = DateTimeUtils.getInstantMillis(end);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[38]++;
            Chronology chrono = DateTimeUtils.getIntervalChronology(start, end);
            setPeriod(startMillis, endMillis, chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[39]++;
        }
    }

    /**
     * Sets all the fields in one go from a millisecond interval using ISOChronology
     * and dividing the fields using the period type.
     * 
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(long startInstant, long endInstant) {
        setPeriod(startInstant, endInstant, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[40]++;
    }

    /**
     * Sets all the fields in one go from a millisecond interval.
     * 
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     * @param chrono  the chronology to use, not null
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(long startInstant, long endInstant, Chronology chrono) {
        chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[41]++;
        setValues(chrono.get(this, startInstant, endInstant));
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[42]++;
    }

    /**
     * Sets all the fields in one go from a duration dividing the
     * fields using the period type.
     * <p>
     * When dividing the duration, only precise fields in the period type will be used.
     * For large durations, all the remaining duration will be stored in the largest
     * available precise field.
     * 
     * @param duration  the duration to set, null means zero length
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(ReadableDuration duration) {
        setPeriod(duration, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[43]++;
    }

    /**
     * Sets all the fields in one go from a duration dividing the
     * fields using the period type.
     * <p>
     * When dividing the duration, only precise fields in the period type will be used.
     * For large durations, all the remaining duration will be stored in the largest
     * available precise field.
     * 
     * @param duration  the duration to set, null means zero length
     * @param chrono  the chronology to use, null means ISO default
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(ReadableDuration duration, Chronology chrono) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[44]++;
        long durationMillis = DateTimeUtils.getDurationMillis(duration);
        setPeriod(durationMillis, chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[45]++;
    }

    /**
     * Sets all the fields in one go from a millisecond duration dividing the
     * fields using the period type.
     * <p>
     * When dividing the duration, only precise fields in the period type will be used.
     * For large durations, all the remaining duration will be stored in the largest
     * available precise field.
     * 
     * @param duration  the duration, in milliseconds
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(long duration) {
        setPeriod(duration, null);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[46]++;
    }

    /**
     * Sets all the fields in one go from a millisecond duration.
     * <p>
     * When dividing the duration, only precise fields in the period type will be used.
     * For large durations, all the remaining duration will be stored in the largest
     * available precise field.
     * 
     * @param duration  the duration, in milliseconds
     * @param chrono  the chronology to use, not null
     * @throws ArithmeticException if the set exceeds the capacity of the period
     */
    public void setPeriod(long duration, Chronology chrono) {
        chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[47]++;
        setValues(chrono.get(this, duration));
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[48]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Adds to the value of one of the fields.
     * <p>
     * The field type specified must be one of those that is supported by the period.
     *
     * @param field  a DurationFieldType instance that is supported by this period, not null
     * @param value  the value to add to the field
     * @throws IllegalArgumentException if the field is null or not supported
     */
    public void add(DurationFieldType field, int value) {
        super.addField(field, value);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[49]++;
    }

    /**
     * Adds a period to this one by adding each field in turn.
     * 
     * @param period  the period to add, null means add nothing
     * @throws IllegalArgumentException if the period being added contains a field
     * not supported by this period
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void add(ReadablePeriod period) {
        super.addPeriod(period);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[50]++;
    }

    /**
     * Adds to each field of this period.
     * 
     * @param years  amount of years to add to this period, which must be zero if unsupported
     * @param months  amount of months to add to this period, which must be zero if unsupported
     * @param weeks  amount of weeks to add to this period, which must be zero if unsupported
     * @param days  amount of days to add to this period, which must be zero if unsupported
     * @param hours  amount of hours to add to this period, which must be zero if unsupported
     * @param minutes  amount of minutes to add to this period, which must be zero if unsupported
     * @param seconds  amount of seconds to add to this period, which must be zero if unsupported
     * @param millis  amount of milliseconds to add to this period, which must be zero if unsupported
     * @throws IllegalArgumentException if the period being added contains a field
     * not supported by this period
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void add(int years, int months, int weeks, int days,
                       int hours, int minutes, int seconds, int millis) {
        setPeriod(
            FieldUtils.safeAdd(getYears(), years),
            FieldUtils.safeAdd(getMonths(), months),
            FieldUtils.safeAdd(getWeeks(), weeks),
            FieldUtils.safeAdd(getDays(), days),
            FieldUtils.safeAdd(getHours(), hours),
            FieldUtils.safeAdd(getMinutes(), minutes),
            FieldUtils.safeAdd(getSeconds(), seconds),
            FieldUtils.safeAdd(getMillis(), millis)
        );
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[51]++;
    }

    /**
     * Adds an interval to this one by dividing the interval into
     * fields and calling {@link #add(ReadablePeriod)}.
     * 
     * @param interval  the interval to add, null means add nothing
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void add(ReadableInterval interval) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[52]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((interval != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[5]++;
            add(interval.toPeriod(getPeriodType()));
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[53]++;

        } else {
  CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[6]++;}
    }

    /**
     * Adds a duration to this one by dividing the duration into
     * fields and calling {@link #add(ReadablePeriod)}.
     * 
     * @param duration  the duration to add, null means add nothing
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void add(ReadableDuration duration) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[54]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((duration != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[7]++;
            add(new Period(duration.getMillis(), getPeriodType()));
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[55]++;

        } else {
  CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[8]++;}
    }

    /**
     * Adds a millisecond duration to this one by dividing the duration into
     * fields and calling {@link #add(ReadablePeriod)}.
     * <p>
     * When dividing the duration, only precise fields in the period type will be used.
     * For large durations, all the remaining duration will be stored in the largest
     * available precise field.
     * 
     * @param duration  the duration, in milliseconds
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void add(long duration) {
        add(new Period(duration, getPeriodType()));
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[56]++;
    }

    /**
     * Adds a millisecond duration to this one by dividing the duration into
     * fields and calling {@link #add(ReadablePeriod)}.
     * <p>
     * When dividing the duration, only precise fields in the period type will be used.
     * For large durations, all the remaining duration will be stored in the largest
     * available precise field.
     * 
     * @param duration  the duration, in milliseconds
     * @param chrono  the chronology to use, null means ISO default
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void add(long duration, Chronology chrono) {
        add(new Period(duration, getPeriodType(), chrono));
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[57]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Merges all the fields from the specified period into this one.
     * <p>
     * Fields that are not present in the specified period are left unaltered.
     * 
     * @param period  the period to set, null ignored
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    public void mergePeriod(ReadablePeriod period) {
        super.mergePeriod(period);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[58]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the years field part of the period.
     * 
     * @return the number of years in the period, zero if unsupported
     */
    public int getYears() {
        return getPeriodType().getIndexedField(this, PeriodType.YEAR_INDEX);
    }

    /**
     * Gets the months field part of the period.
     * 
     * @return the number of months in the period, zero if unsupported
     */
    public int getMonths() {
        return getPeriodType().getIndexedField(this, PeriodType.MONTH_INDEX);
    }

    /**
     * Gets the weeks field part of the period.
     * 
     * @return the number of weeks in the period, zero if unsupported
     */
    public int getWeeks() {
        return getPeriodType().getIndexedField(this, PeriodType.WEEK_INDEX);
    }

    /**
     * Gets the days field part of the period.
     * 
     * @return the number of days in the period, zero if unsupported
     */
    public int getDays() {
        return getPeriodType().getIndexedField(this, PeriodType.DAY_INDEX);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the hours field part of the period.
     * 
     * @return the number of hours in the period, zero if unsupported
     */
    public int getHours() {
        return getPeriodType().getIndexedField(this, PeriodType.HOUR_INDEX);
    }

    /**
     * Gets the minutes field part of the period.
     * 
     * @return the number of minutes in the period, zero if unsupported
     */
    public int getMinutes() {
        return getPeriodType().getIndexedField(this, PeriodType.MINUTE_INDEX);
    }

    /**
     * Gets the seconds field part of the period.
     * 
     * @return the number of seconds in the period, zero if unsupported
     */
    public int getSeconds() {
        return getPeriodType().getIndexedField(this, PeriodType.SECOND_INDEX);
    }

    /**
     * Gets the millis field part of the period.
     * 
     * @return the number of millis in the period, zero if unsupported
     */
    public int getMillis() {
        return getPeriodType().getIndexedField(this, PeriodType.MILLI_INDEX);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of years of the period.
     * 
     * @param years  the number of years
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setYears(int years) {
        super.setField(DurationFieldType.years(), years);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[59]++;
    }

    /**
     * Adds the specified years to the number of years in the period.
     * 
     * @param years  the number of years
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addYears(int years) {
        super.addField(DurationFieldType.years(), years);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[60]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of months of the period.
     * 
     * @param months  the number of months
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setMonths(int months) {
        super.setField(DurationFieldType.months(), months);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[61]++;
    }

    /**
     * Adds the specified months to the number of months in the period.
     * 
     * @param months  the number of months
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addMonths(int months) {
        super.addField(DurationFieldType.months(), months);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[62]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of weeks of the period.
     * 
     * @param weeks  the number of weeks
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setWeeks(int weeks) {
        super.setField(DurationFieldType.weeks(), weeks);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[63]++;
    }

    /**
     * Adds the specified weeks to the number of weeks in the period.
     * 
     * @param weeks  the number of weeks
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addWeeks(int weeks) {
        super.addField(DurationFieldType.weeks(), weeks);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[64]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of days of the period.
     * 
     * @param days  the number of days
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setDays(int days) {
        super.setField(DurationFieldType.days(), days);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[65]++;
    }

    /**
     * Adds the specified days to the number of days in the period.
     * 
     * @param days  the number of days
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addDays(int days) {
        super.addField(DurationFieldType.days(), days);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[66]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of hours of the period.
     * 
     * @param hours  the number of hours
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setHours(int hours) {
        super.setField(DurationFieldType.hours(), hours);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[67]++;
    }

    /**
     * Adds the specified hours to the number of hours in the period.
     * 
     * @param hours  the number of hours
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addHours(int hours) {
        super.addField(DurationFieldType.hours(), hours);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[68]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of minutes of the period.
     * 
     * @param minutes  the number of minutes
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setMinutes(int minutes) {
        super.setField(DurationFieldType.minutes(), minutes);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[69]++;
    }

    /**
     * Adds the specified minutes to the number of minutes in the period.
     * 
     * @param minutes  the number of minutes
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addMinutes(int minutes) {
        super.addField(DurationFieldType.minutes(), minutes);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[70]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of seconds of the period.
     * 
     * @param seconds  the number of seconds
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setSeconds(int seconds) {
        super.setField(DurationFieldType.seconds(), seconds);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[71]++;
    }

    /**
     * Adds the specified seconds to the number of seconds in the period.
     * 
     * @param seconds  the number of seconds
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addSeconds(int seconds) {
        super.addField(DurationFieldType.seconds(), seconds);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[72]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the number of millis of the period.
     * 
     * @param millis  the number of millis
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     */
    public void setMillis(int millis) {
        super.setField(DurationFieldType.millis(), millis);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[73]++;
    }

    /**
     * Adds the specified millis to the number of millis in the period.
     * 
     * @param millis  the number of millis
     * @throws IllegalArgumentException if field is not supported and the value is non-zero
     * @throws ArithmeticException if the addition exceeds the capacity of the period
     */
    public void addMillis(int millis) {
        super.addField(DurationFieldType.millis(), millis);
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[74]++;
    }

    // Misc
    //-----------------------------------------------------------------------
    /**
     * Clone this object without having to cast the returned object.
     *
     * @return a clone of the this object.
     */
    public MutablePeriod copy() {
        return (MutablePeriod) clone();
    }

    /**
     * Clone this object.
     *
     * @return a clone of this object.
     */
    public Object clone() {
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.statements[75]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return super.clone();
        } catch (CloneNotSupportedException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[10]++;
            throw new InternalError("Clone error");
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd.branches[9]++;
}
  }
    }

}

class CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd ());
  }
    public static long[] statements = new long[76];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.joda.time.MutablePeriod.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$6fw3mws3k3fp9uyu92qrs9mbrxpd () {
    super("org.joda.time.MutablePeriod.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 75; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.MutablePeriod.java");
      for (int i = 1; i <= 75; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

