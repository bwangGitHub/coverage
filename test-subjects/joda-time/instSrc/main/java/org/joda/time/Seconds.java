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

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * An immutable time period representing a number of seconds.
 * <p>
 * <code>Seconds</code> is an immutable period that can only store seconds.
 * It does not store years, months or hours for example. As such it is a
 * type-safe way of representing a number of seconds in an application.
 * <p>
 * The number of seconds is set in the constructor, and may be queried using
 * <code>getSeconds()</code>. Basic mathematical operations are provided -
 * <code>plus()</code>, <code>minus()</code>, <code>multipliedBy()</code> and
 * <code>dividedBy()</code>.
 * <p>
 * <code>Seconds</code> is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.4
 */
public final class Seconds extends BaseSingleFieldPeriod {
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.ping();
  }


    /** Constant representing zero seconds. */
    public static final Seconds ZERO = new Seconds(0);
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[1]++;
  }
    /** Constant representing one second. */
    public static final Seconds ONE = new Seconds(1);
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[2]++;
  }
    /** Constant representing two seconds. */
    public static final Seconds TWO = new Seconds(2);
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[3]++;
  }
    /** Constant representing three seconds. */
    public static final Seconds THREE = new Seconds(3);
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[4]++;
  }
    /** Constant representing the maximum number of seconds that can be stored in this object. */
    public static final Seconds MAX_VALUE = new Seconds(Integer.MAX_VALUE);
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[5]++;
  }
    /** Constant representing the minimum number of seconds that can be stored in this object. */
    public static final Seconds MIN_VALUE = new Seconds(Integer.MIN_VALUE);
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[6]++;
  }

    /** The paser to use for this class. */
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.seconds());
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[7]++;
  }
    /** Serialization version. */
    private static final long serialVersionUID = 87525275727380862L;
  static {
    CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[8]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of <code>Seconds</code> that may be cached.
     * <code>Seconds</code> is immutable, so instances can be cached and shared.
     * This factory method provides access to shared instances.
     *
     * @param seconds  the number of seconds to obtain an instance for
     * @return the instance of Seconds
     */
    public static Seconds seconds(int seconds) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[9]++;
        switch (seconds) {
            case 0:
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[1]++;
                return ZERO;
            case 1:
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[2]++;
                return ONE;
            case 2:
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[3]++;
                return TWO;
            case 3:
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[4]++;
                return THREE;
            case Integer.MAX_VALUE:
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[5]++;
                return MAX_VALUE;
            case Integer.MIN_VALUE:
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[6]++;
                return MIN_VALUE;
            default:
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[7]++;
                return new Seconds(seconds);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a <code>Seconds</code> representing the number of whole seconds
     * between the two specified datetimes.
     *
     * @param start  the start instant, must not be null
     * @param end  the end instant, must not be null
     * @return the period in seconds
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    public static Seconds secondsBetween(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[10]++;
        int amount = BaseSingleFieldPeriod.between(start, end, DurationFieldType.seconds());
        return Seconds.seconds(amount);
    }

    /**
     * Creates a <code>Seconds</code> representing the number of whole seconds
     * between the two specified partial datetimes.
     * <p>
     * The two partials must contain the same fields, for example you can specify
     * two <code>LocalTime</code> objects.
     *
     * @param start  the start partial date, must not be null
     * @param end  the end partial date, must not be null
     * @return the period in seconds
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Seconds secondsBetween(ReadablePartial start, ReadablePartial end) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((start instanceof LocalTime) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end instanceof LocalTime) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))   {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[8]++;
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[12]++;
            Chronology chrono = DateTimeUtils.getChronology(start.getChronology());
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[13]++;
            int seconds = chrono.seconds().getDifference(
                    ((LocalTime) end).getLocalMillis(), ((LocalTime) start).getLocalMillis());
            return Seconds.seconds(seconds);

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[9]++;}
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[14]++;
        int amount = BaseSingleFieldPeriod.between(start, end, ZERO);
        return Seconds.seconds(amount);
    }

    /**
     * Creates a <code>Seconds</code> representing the number of whole seconds
     * in the specified interval.
     *
     * @param interval  the interval to extract seconds from, null returns zero
     * @return the period in seconds
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Seconds secondsIn(ReadableInterval interval) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))   {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[10]++;
            return Seconds.ZERO;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[11]++;}
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[16]++;
        int amount = BaseSingleFieldPeriod.between(interval.getStart(), interval.getEnd(), DurationFieldType.seconds());
        return Seconds.seconds(amount);
    }

    /**
     * Creates a new <code>Seconds</code> representing the number of complete
     * standard length seconds in the specified period.
     * <p>
     * This factory method converts all fields from the period to hours using standardised
     * durations for each field. Only those fields which have a precise duration in
     * the ISO UTC chronology can be converted.
     * <ul>
     * <li>One week consists of 7 seconds.
     * <li>One day consists of 24 hours.
     * <li>One hour consists of 60 minutes.
     * <li>One minute consists of 60 seconds.
     * <li>One second consists of 1000 milliseconds.
     * </ul>
     * Months and Years are imprecise and periods containing these values cannot be converted.
     *
     * @param period  the period to get the number of hours from, null returns zero
     * @return the period in seconds
     * @throws IllegalArgumentException if the period contains imprecise duration values
     */
    public static Seconds standardSecondsIn(ReadablePeriod period) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[17]++;
        int amount = BaseSingleFieldPeriod.standardPeriodIn(period, DateTimeConstants.MILLIS_PER_SECOND);
        return Seconds.seconds(amount);
    }

    /**
     * Creates a new <code>Seconds</code> by parsing a string in the ISO8601 format 'PTnS'.
     * <p>
     * The parse will accept the full ISO syntax of PnYnMnWnDTnHnMnS however only the
     * seconds component may be non-zero. If any other component is non-zero, an exception
     * will be thrown.
     *
     * @param periodStr  the period string, null returns zero
     * @return the period in seconds
     * @throws IllegalArgumentException if the string format is invalid
     */
    @FromString
    public static Seconds parseSeconds(String periodStr) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((periodStr == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[12]++;
            return Seconds.ZERO;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[13]++;}
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[19]++;
        Period p = PARSER.parsePeriod(periodStr);
        return Seconds.seconds(p.getSeconds());
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new instance representing a number of seconds.
     * You should consider using the factory method {@link #seconds(int)}
     * instead of the constructor.
     *
     * @param seconds  the number of seconds to represent
     */
    private Seconds(int seconds) {
        super(seconds);
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[20]++;
    }

    /**
     * Resolves singletons.
     * 
     * @return the singleton instance
     */
    private Object readResolve() {
        return Seconds.seconds(getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the duration field type, which is <code>seconds</code>.
     *
     * @return the period type
     */
    public DurationFieldType getFieldType() {
        return DurationFieldType.seconds();
    }

    /**
     * Gets the period type, which is <code>seconds</code>.
     *
     * @return the period type
     */
    public PeriodType getPeriodType() {
        return PeriodType.seconds();
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this period in seconds to a period in weeks assuming a
     * 7 day week, 24 hour day, 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all weeks are 7 days
     * long, all days are 24 hours long, all hours are 60 minutes long and
     * all minutes are 60 seconds long.
     * This is not true when daylight savings time is considered, and may also
     * not be true for some unusual chronologies. However, it is included as it
     * is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of whole weeks for this number of seconds
     */
    public Weeks toStandardWeeks() {
        return Weeks.weeks(getValue() / DateTimeConstants.SECONDS_PER_WEEK);
    }

    /**
     * Converts this period in seconds to a period in days assuming a
     * 24 hour day, 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all days are 24 hours
     * long, all hours are 60 minutes long and all minutes are 60 seconds long.
     * This is not true when daylight savings is considered and may also not
     * be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of days for this number of seconds
     */
    public Days toStandardDays() {
        return Days.days(getValue() / DateTimeConstants.SECONDS_PER_DAY);
    }

    /**
     * Converts this period in seconds to a period in hours assuming a
     * 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all hours are
     * 60 minutes long and all minutes are 60 seconds long.
     * This may not be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of hours for this number of seconds
     */
    public Hours toStandardHours() {
        return Hours.hours(getValue() / DateTimeConstants.SECONDS_PER_HOUR);
    }

    /**
     * Converts this period in seconds to a period in minutes assuming a
     * 60 second minute.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all minutes are
     * 60 seconds long.
     * This may not be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of minutes for this number of seconds
     */
    public Minutes toStandardMinutes() {
        return Minutes.minutes(getValue() / DateTimeConstants.SECONDS_PER_MINUTE);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this period in seconds to a duration in milliseconds assuming a
     * 24 hour day, 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert from a period to a duration.
     * However to achieve this it makes the assumption that all seconds are 24 hours
     * long, all hours are 60 minutes and all minutes are 60 seconds.
     * This is not true when daylight savings time is considered, and may also
     * not be true for some unusual chronologies. However, it is included as it
     * is a useful operation for many applications and business rules.
     * 
     * @return a duration equivalent to this number of seconds
     */
    public Duration toStandardDuration() {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[21]++;
        long seconds = getValue();  // assign to a long
        return new Duration(seconds * DateTimeConstants.MILLIS_PER_SECOND);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of seconds that this period represents.
     *
     * @return the number of seconds in the period
     */
    public int getSeconds() {
        return getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of seconds added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param seconds  the amount of seconds to add, may be negative
     * @return the new period plus the specified number of seconds
     * @throws ArithmeticException if the result overflows an int
     */
    public Seconds plus(int seconds) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((seconds == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[14]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[15]++;}
        return Seconds.seconds(FieldUtils.safeAdd(getValue(), seconds));
    }

    /**
     * Returns a new instance with the specified number of seconds added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param seconds  the amount of seconds to add, may be negative, null means zero
     * @return the new period plus the specified number of seconds
     * @throws ArithmeticException if the result overflows an int
     */
    public Seconds plus(Seconds seconds) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((seconds == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[16]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[17]++;}
        return plus(seconds.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of seconds taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param seconds  the amount of seconds to take away, may be negative
     * @return the new period minus the specified number of seconds
     * @throws ArithmeticException if the result overflows an int
     */
    public Seconds minus(int seconds) {
        return plus(FieldUtils.safeNegate(seconds));
    }

    /**
     * Returns a new instance with the specified number of seconds taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param seconds  the amount of seconds to take away, may be negative, null means zero
     * @return the new period minus the specified number of seconds
     * @throws ArithmeticException if the result overflows an int
     */
    public Seconds minus(Seconds seconds) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((seconds == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[18]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[19]++;}
        return minus(seconds.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the seconds multiplied by the specified scalar.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param scalar  the amount to multiply by, may be negative
     * @return the new period multiplied by the specified scalar
     * @throws ArithmeticException if the result overflows an int
     */
    public Seconds multipliedBy(int scalar) {
        return Seconds.seconds(FieldUtils.safeMultiply(getValue(), scalar));
    }

    /**
     * Returns a new instance with the seconds divided by the specified divisor.
     * The calculation uses integer division, thus 3 divided by 2 is 1.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param divisor  the amount to divide by, may be negative
     * @return the new period divided by the specified divisor
     * @throws ArithmeticException if the divisor is zero
     */
    public Seconds dividedBy(int divisor) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((divisor == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[20]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[21]++;}
        return Seconds.seconds(getValue() / divisor);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the seconds value negated.
     *
     * @return the new period with a negated value
     * @throws ArithmeticException if the result overflows an int
     */
    public Seconds negated() {
        return Seconds.seconds(FieldUtils.safeNegate(getValue()));
    }

    //-----------------------------------------------------------------------
    /**
     * Is this seconds instance greater than the specified number of seconds.
     *
     * @param other  the other period, null means zero
     * @return true if this seconds instance is greater than the specified one
     */
    public boolean isGreaterThan(Seconds other) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[22]++;
            return getValue() > 0;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[23]++;}
        return getValue() > other.getValue();
    }

    /**
     * Is this seconds instance less than the specified number of seconds.
     *
     * @param other  the other period, null means zero
     * @return true if this seconds instance is less than the specified one
     */
    public boolean isLessThan(Seconds other) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[24]++;
            return getValue() < 0;

        } else {
  CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1.branches[25]++;}
        return getValue() < other.getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets this instance as a String in the ISO8601 duration format.
     * <p>
     * For example, "PT4S" represents 4 seconds.
     *
     * @return the value as an ISO8601 string
     */
    @ToString
    public String toString() {
        return "PT" + String.valueOf(getValue()) + "S";
    }

}

class CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1 ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[26];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.Seconds.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2i2zhwryqhyuq1lrdk1 () {
    super("org.joda.time.Seconds.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 25; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.Seconds.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 25; i++) {
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

