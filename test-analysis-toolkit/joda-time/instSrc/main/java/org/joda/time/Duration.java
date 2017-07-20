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
import org.joda.time.base.BaseDuration;
import org.joda.time.field.FieldUtils;

/**
 * An immutable duration specifying a length of time in milliseconds.
 * <p>
 * A duration is defined by a fixed number of milliseconds.
 * There is no concept of fields, such as days or seconds, as these fields can vary in length.
 * A duration may be converted to a {@link Period} to obtain field values.
 * This conversion will typically cause a loss of precision however.
 * <p>
 * Duration is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class Duration
        extends BaseDuration
        implements ReadableDuration, Serializable {
  static {
    CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.ping();
  }


    /** Constant representing zero millisecond duration */
    public static final Duration ZERO = new Duration(0L);
  static {
    CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[1]++;
  }

    /** Serialization version */
    private static final long serialVersionUID = 2471658376918L;
  static {
    CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[2]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code Duration} from the specified string.
     * <p>
     * This parses the format {@code PTa.bS}, as per {@link #toString()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static Duration parse(String str) {
        return new Duration(str);
    }

    //-----------------------------------------------------------------------
    /**
     * Create a duration with the specified number of days assuming that
     * there are the standard number of milliseconds in a day.
     * <p>
     * This method assumes that there are 24 hours in a day,
     * 60 minutes in an hour, 60 seconds in a minute and 1000 milliseconds in
     * a second. This will be true for most days, however days with Daylight
     * Savings changes will not have 24 hours, so use this method with care.
     * <p>
     * A Duration is a representation of an amount of time. If you want to express
     * the concepts of 'days' you should consider using the {@link Days} class.
     *
     * @param days  the number of standard days in this duration
     * @return the duration, never null
     * @throws ArithmeticException if the days value is too large
     * @since 1.6
     */
    public static Duration standardDays(long days) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[1]++;
            return ZERO;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[2]++;}
        return new Duration(FieldUtils.safeMultiply(days, DateTimeConstants.MILLIS_PER_DAY));
    }

    /**
     * Create a duration with the specified number of hours assuming that
     * there are the standard number of milliseconds in an hour.
     * <p>
     * This method assumes that there are 60 minutes in an hour,
     * 60 seconds in a minute and 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * <p>
     * A Duration is a representation of an amount of time. If you want to express
     * the concepts of 'hours' you should consider using the {@link Hours} class.
     *
     * @param hours  the number of standard hours in this duration
     * @return the duration, never null
     * @throws ArithmeticException if the hours value is too large
     * @since 1.6
     */
    public static Duration standardHours(long hours) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[3]++;
            return ZERO;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[4]++;}
        return new Duration(FieldUtils.safeMultiply(hours, DateTimeConstants.MILLIS_PER_HOUR));
    }

    /**
     * Create a duration with the specified number of minutes assuming that
     * there are the standard number of milliseconds in a minute.
     * <p>
     * This method assumes that there are 60 seconds in a minute and
     * 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * <p>
     * A Duration is a representation of an amount of time. If you want to express
     * the concepts of 'minutes' you should consider using the {@link Minutes} class.
     *
     * @param minutes  the number of standard minutes in this duration
     * @return the duration, never null
     * @throws ArithmeticException if the minutes value is too large
     * @since 1.6
     */
    public static Duration standardMinutes(long minutes) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((minutes == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[5]++;
            return ZERO;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[6]++;}
        return new Duration(FieldUtils.safeMultiply(minutes, DateTimeConstants.MILLIS_PER_MINUTE));
    }

    /**
     * Create a duration with the specified number of seconds assuming that
     * there are the standard number of milliseconds in a second.
     * <p>
     * This method assumes that there are 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * <p>
     * A Duration is a representation of an amount of time. If you want to express
     * the concepts of 'seconds' you should consider using the {@link Seconds} class.
     *
     * @param seconds  the number of standard seconds in this duration
     * @return the duration, never null
     * @throws ArithmeticException if the seconds value is too large
     * @since 1.6
     */
    public static Duration standardSeconds(long seconds) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[6]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[7]++;
            return ZERO;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[8]++;}
        return new Duration(FieldUtils.safeMultiply(seconds, DateTimeConstants.MILLIS_PER_SECOND));
    }

    /**
     * Create a duration with the specified number of milliseconds.
     *
     * @param millis  the number of standard milliseconds in this duration
     * @return the duration, never null
     * @since 2.0
     */
    public static Duration millis(long millis) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[7]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((millis == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[9]++;
            return ZERO;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[10]++;}
        return new Duration(millis);
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a duration from the given millisecond duration.
     *
     * @param duration  the duration, in milliseconds
     */
    public Duration(long duration) {
        super(duration);
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[8]++;
    }

    /**
     * Creates a duration from the given interval endpoints.
     *
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     * @throws ArithmeticException if the duration exceeds a 64 bit long
     */
    public Duration(long startInstant, long endInstant) {
        super(startInstant, endInstant);
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[9]++;
    }

    /**
     * Creates a duration from the given interval endpoints.
     *
     * @param start  interval start, null means now
     * @param end  interval end, null means now
     * @throws ArithmeticException if the duration exceeds a 64 bit long
     */
    public Duration(ReadableInstant start, ReadableInstant end) {
        super(start, end);
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[10]++;
    }

    /**
     * Creates a duration from the specified object using the
     * {@link org.joda.time.convert.ConverterManager ConverterManager}.
     *
     * @param duration  duration to convert
     * @throws IllegalArgumentException if duration is invalid
     */
    public Duration(Object duration) {
        super(duration);
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[11]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the length of this duration in days assuming that there are the
     * standard number of milliseconds in a day.
     * <p>
     * This method assumes that there are 24 hours in a day,
     * 60 minutes in an hour, 60 seconds in a minute and 1000 milliseconds in
     * a second. This will be true for most days, however days with Daylight
     * Savings changes will not have 24 hours, so use this method with care.
     * <p>
     * This returns <code>getMillis() / MILLIS_PER_DAY</code>.
     * The result is an integer division, thus excess milliseconds are truncated.
     *
     * @return the length of the duration in standard seconds
     * @since 2.0
     */
    public long getStandardDays() {
        return getMillis() / DateTimeConstants.MILLIS_PER_DAY;
    }

    /**
     * Gets the length of this duration in hours assuming that there are the
     * standard number of milliseconds in an hour.
     * <p>
     * This method assumes that there are 60 minutes in an hour,
     * 60 seconds in a minute and 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * <p>
     * This returns <code>getMillis() / MILLIS_PER_HOUR</code>.
     * The result is an integer division, thus excess milliseconds are truncated.
     *
     * @return the length of the duration in standard seconds
     * @since 2.0
     */
    public long getStandardHours() {
        return getMillis() / DateTimeConstants.MILLIS_PER_HOUR;
    }

    /**
     * Gets the length of this duration in minutes assuming that there are the
     * standard number of milliseconds in a minute.
     * <p>
     * This method assumes that there are 60 seconds in a minute and
     * 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * <p>
     * This returns <code>getMillis() / 60000</code>.
     * The result is an integer division, thus excess milliseconds are truncated.
     *
     * @return the length of the duration in standard seconds
     * @since 2.0
     */
    public long getStandardMinutes() {
        return getMillis() / DateTimeConstants.MILLIS_PER_MINUTE;
    }

    /**
     * Gets the length of this duration in seconds assuming that there are the
     * standard number of milliseconds in a second.
     * <p>
     * This method assumes that there are 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * <p>
     * This returns <code>getMillis() / 1000</code>.
     * The result is an integer division, so 2999 millis returns 2 seconds.
     *
     * @return the length of the duration in standard seconds
     * @since 1.6
     */
    public long getStandardSeconds() {
        return getMillis() / DateTimeConstants.MILLIS_PER_SECOND;
    }

    //-----------------------------------------------------------------------
    /**
     * Get this duration as an immutable <code>Duration</code> object
     * by returning <code>this</code>.
     * 
     * @return <code>this</code>
     */
    public Duration toDuration() {
        return this;
    }

    /**
     * Converts this duration to a period in days assuming that there are the
     * standard number of milliseconds in a day.
     * <p>
     * This method assumes that there are 24 hours in a day,
     * 60 minutes in an hour, 60 seconds in a minute and 1000 milliseconds in
     * a second. This will be true for most days, however days with Daylight
     * Savings changes will not have 24 hours, so use this method with care.
     * 
     * @return a period representing the number of standard days in this period, never null
     * @throws ArithmeticException if the number of days is too large to be represented
     * @since 2.0
     */
    public Days toStandardDays() {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[12]++;
        long days = getStandardDays();
        return Days.days(FieldUtils.safeToInt(days));
    }

    /**
     * Converts this duration to a period in hours assuming that there are the
     * standard number of milliseconds in an hour.
     * <p>
     * This method assumes that there are 60 minutes in an hour,
     * 60 seconds in a minute and 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * 
     * @return a period representing the number of standard hours in this period, never null
     * @throws ArithmeticException if the number of hours is too large to be represented
     * @since 2.0
     */
    public Hours toStandardHours() {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[13]++;
        long hours = getStandardHours();
        return Hours.hours(FieldUtils.safeToInt(hours));
    }

    /**
     * Converts this duration to a period in minutes assuming that there are the
     * standard number of milliseconds in a minute.
     * <p>
     * This method assumes that there are 60 seconds in a minute and
     * 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * 
     * @return a period representing the number of standard minutes in this period, never null
     * @throws ArithmeticException if the number of minutes is too large to be represented
     * @since 2.0
     */
    public Minutes toStandardMinutes() {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[14]++;
        long minutes = getStandardMinutes();
        return Minutes.minutes(FieldUtils.safeToInt(minutes));
    }

    /**
     * Converts this duration to a period in seconds assuming that there are the
     * standard number of milliseconds in a second.
     * <p>
     * This method assumes that there are 1000 milliseconds in a second.
     * All currently supplied chronologies use this definition.
     * 
     * @return a period representing the number of standard seconds in this period, never null
     * @throws ArithmeticException if the number of seconds is too large to be represented
     * @since 1.6
     */
    public Seconds toStandardSeconds() {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[15]++;
        long seconds = getStandardSeconds();
        return Seconds.seconds(FieldUtils.safeToInt(seconds));
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new Duration instance with a different milisecond length.
     * 
     * @param duration  the new length of the duration
     * @return the new duration instance
     */
    public Duration withMillis(long duration) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((duration == getMillis()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[11]++;
            return this;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[12]++;}
        return new Duration(duration);
    }

    /**
     * Returns a new duration with this length plus that specified multiplied by the scalar.
     * This instance is immutable and is not altered.
     * <p>
     * If the addition is zero, this instance is returned.
     * 
     * @param durationToAdd  the duration to add to this one
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return the new duration instance
     */
    public Duration withDurationAdded(long durationToAdd, int scalar) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((durationToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[13]++;
            return this;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[14]++;}
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[18]++;
        long add = FieldUtils.safeMultiply(durationToAdd, scalar);
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[19]++;
        long duration = FieldUtils.safeAdd(getMillis(), add);
        return new Duration(duration);
    }

    /**
     * Returns a new duration with this length plus that specified multiplied by the scalar.
     * This instance is immutable and is not altered.
     * <p>
     * If the addition is zero, this instance is returned.
     * 
     * @param durationToAdd  the duration to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return the new duration instance
     */
    public Duration withDurationAdded(ReadableDuration durationToAdd, int scalar) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((durationToAdd == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[15]++;
            return this;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[16]++;}
        return withDurationAdded(durationToAdd.getMillis(), scalar);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new duration with this length plus that specified.
     * This instance is immutable and is not altered.
     * <p>
     * If the addition is zero, this instance is returned.
     * 
     * @param amount  the duration to add to this one
     * @return the new duration instance
     */
    public Duration plus(long amount) {
        return withDurationAdded(amount, 1);
    }

    /**
     * Returns a new duration with this length plus that specified.
     * This instance is immutable and is not altered.
     * <p>
     * If the amount is zero, this instance is returned.
     * 
     * @param amount  the duration to add to this one, null means zero
     * @return the new duration instance
     */
    public Duration plus(ReadableDuration amount) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((amount == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[17]++;
            return this;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[18]++;}
        return withDurationAdded(amount.getMillis(), 1);
    }

    /**
     * Returns a new duration with this length minus that specified.
     * This instance is immutable and is not altered.
     * <p>
     * If the addition is zero, this instance is returned.
     * 
     * @param amount  the duration to take away from this one
     * @return the new duration instance
     */
    public Duration minus(long amount) {
        return withDurationAdded(amount, -1);
    }

    /**
     * Returns a new duration with this length minus that specified.
     * This instance is immutable and is not altered.
     * <p>
     * If the amount is zero, this instance is returned.
     * 
     * @param amount  the duration to take away from this one, null means zero
     * @return the new duration instance
     */
    public Duration minus(ReadableDuration amount) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.statements[22]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((amount == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[19]++;
            return this;

        } else {
  CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x.branches[20]++;}
        return withDurationAdded(amount.getMillis(), -1);
    }

}

class CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.joda.time.Duration.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,2,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$eluogpi6akwx1j5qsr4x () {
    super("org.joda.time.Duration.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.Duration.java");
      for (int i = 1; i <= 22; i++) {
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
    for (int i = 1; i <= 10; i++) {
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

