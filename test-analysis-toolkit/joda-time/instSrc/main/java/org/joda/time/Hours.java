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
 * An immutable time period representing a number of hours.
 * <p>
 * <code>Hours</code> is an immutable period that can only store hours.
 * It does not store years, months or minutes for example. As such it is a
 * type-safe way of representing a number of hours in an application.
 * <p>
 * The number of hours is set in the constructor, and may be queried using
 * <code>getHours()</code>. Basic mathematical operations are provided -
 * <code>plus()</code>, <code>minus()</code>, <code>multipliedBy()</code> and
 * <code>dividedBy()</code>.
 * <p>
 * <code>Hours</code> is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.4
 */
public final class Hours extends BaseSingleFieldPeriod {
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.ping();
  }


    /** Constant representing zero hours. */
    public static final Hours ZERO = new Hours(0);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[1]++;
  }
    /** Constant representing one hour. */
    public static final Hours ONE = new Hours(1);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[2]++;
  }
    /** Constant representing two hours. */
    public static final Hours TWO = new Hours(2);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[3]++;
  }
    /** Constant representing three hours. */
    public static final Hours THREE = new Hours(3);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[4]++;
  }
    /** Constant representing four hours. */
    public static final Hours FOUR = new Hours(4);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[5]++;
  }
    /** Constant representing five hours. */
    public static final Hours FIVE = new Hours(5);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[6]++;
  }
    /** Constant representing six hours. */
    public static final Hours SIX = new Hours(6);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[7]++;
  }
    /** Constant representing seven hours. */
    public static final Hours SEVEN = new Hours(7);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[8]++;
  }
    /** Constant representing eight hours. */
    public static final Hours EIGHT = new Hours(8);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[9]++;
  }
    /** Constant representing the maximum number of hours that can be stored in this object. */
    public static final Hours MAX_VALUE = new Hours(Integer.MAX_VALUE);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[10]++;
  }
    /** Constant representing the minimum number of hours that can be stored in this object. */
    public static final Hours MIN_VALUE = new Hours(Integer.MIN_VALUE);
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[11]++;
  }

    /** The paser to use for this class. */
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.hours());
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[12]++;
  }
    /** Serialization version. */
    private static final long serialVersionUID = 87525275727380864L;
  static {
    CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[13]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of <code>Hours</code> that may be cached.
     * <code>Hours</code> is immutable, so instances can be cached and shared.
     * This factory method provides access to shared instances.
     *
     * @param hours  the number of hours to obtain an instance for
     * @return the instance of Hours
     */
    public static Hours hours(int hours) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[14]++;
        switch (hours) {
            case 0:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[1]++;
                return ZERO;
            case 1:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[2]++;
                return ONE;
            case 2:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[3]++;
                return TWO;
            case 3:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[4]++;
                return THREE;
            case 4:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[5]++;
                return FOUR;
            case 5:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[6]++;
                return FIVE;
            case 6:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[7]++;
                return SIX;
            case 7:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[8]++;
                return SEVEN;
            case 8:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[9]++;
                return EIGHT;
            case Integer.MAX_VALUE:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[10]++;
                return MAX_VALUE;
            case Integer.MIN_VALUE:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[11]++;
                return MIN_VALUE;
            default:
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[12]++;
                return new Hours(hours);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a <code>Hours</code> representing the number of whole hours
     * between the two specified datetimes.
     *
     * @param start  the start instant, must not be null
     * @param end  the end instant, must not be null
     * @return the period in hours
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    public static Hours hoursBetween(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[15]++;
        int amount = BaseSingleFieldPeriod.between(start, end, DurationFieldType.hours());
        return Hours.hours(amount);
    }

    /**
     * Creates a <code>Hours</code> representing the number of whole hours
     * between the two specified partial datetimes.
     * <p>
     * The two partials must contain the same fields, for example you can specify
     * two <code>LocalTime</code> objects.
     *
     * @param start  the start partial date, must not be null
     * @param end  the end partial date, must not be null
     * @return the period in hours
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Hours hoursBetween(ReadablePartial start, ReadablePartial end) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((start instanceof LocalTime) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end instanceof LocalTime) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))   {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[13]++;
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[17]++;
            Chronology chrono = DateTimeUtils.getChronology(start.getChronology());
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[18]++;
            int hours = chrono.hours().getDifference(
                    ((LocalTime) end).getLocalMillis(), ((LocalTime) start).getLocalMillis());
            return Hours.hours(hours);

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[14]++;}
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[19]++;
        int amount = BaseSingleFieldPeriod.between(start, end, ZERO);
        return Hours.hours(amount);
    }

    /**
     * Creates a <code>Hours</code> representing the number of whole hours
     * in the specified interval.
     *
     * @param interval  the interval to extract hours from, null returns zero
     * @return the period in hours
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Hours hoursIn(ReadableInterval interval) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))   {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[15]++;
            return Hours.ZERO;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[16]++;}
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[21]++;
        int amount = BaseSingleFieldPeriod.between(interval.getStart(), interval.getEnd(), DurationFieldType.hours());
        return Hours.hours(amount);
    }

    /**
     * Creates a new <code>Hours</code> representing the number of complete
     * standard length hours in the specified period.
     * <p>
     * This factory method converts all fields from the period to hours using standardised
     * durations for each field. Only those fields which have a precise duration in
     * the ISO UTC chronology can be converted.
     * <ul>
     * <li>One week consists of 7 days.
     * <li>One day consists of 24 hours.
     * <li>One hour consists of 60 minutes.
     * <li>One minute consists of 60 seconds.
     * <li>One second consists of 1000 milliseconds.
     * </ul>
     * Months and Years are imprecise and periods containing these values cannot be converted.
     *
     * @param period  the period to get the number of hours from, null returns zero
     * @return the period in hours
     * @throws IllegalArgumentException if the period contains imprecise duration values
     */
    public static Hours standardHoursIn(ReadablePeriod period) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[22]++;
        int amount = BaseSingleFieldPeriod.standardPeriodIn(period, DateTimeConstants.MILLIS_PER_HOUR);
        return Hours.hours(amount);
    }

    /**
     * Creates a new <code>Hours</code> by parsing a string in the ISO8601 format 'PTnH'.
     * <p>
     * The parse will accept the full ISO syntax of PnYnMnWnDTnHnMnS however only the
     * hours component may be non-zero. If any other component is non-zero, an exception
     * will be thrown.
     *
     * @param periodStr  the period string, null returns zero
     * @return the period in hours
     * @throws IllegalArgumentException if the string format is invalid
     */
    @FromString
    public static Hours parseHours(String periodStr) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((periodStr == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[17]++;
            return Hours.ZERO;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[18]++;}
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[24]++;
        Period p = PARSER.parsePeriod(periodStr);
        return Hours.hours(p.getHours());
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new instance representing a number of hours.
     * You should consider using the factory method {@link #hours(int)}
     * instead of the constructor.
     *
     * @param hours  the number of hours to represent
     */
    private Hours(int hours) {
        super(hours);
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[25]++;
    }

    /**
     * Resolves singletons.
     * 
     * @return the singleton instance
     */
    private Object readResolve() {
        return Hours.hours(getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the duration field type, which is <code>hours</code>.
     *
     * @return the period type
     */
    public DurationFieldType getFieldType() {
        return DurationFieldType.hours();
    }

    /**
     * Gets the period type, which is <code>hours</code>.
     *
     * @return the period type
     */
    public PeriodType getPeriodType() {
        return PeriodType.hours();
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this period in hours to a period in weeks assuming a
     * 7 day week and 24 hour day.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all weeks are 7 days
     * long and all days are 24 hours long.
     * This is not true when daylight savings time is considered, and may also
     * not be true for some unusual chronologies. However, it is included as it
     * is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of whole weeks for this number of hours
     */
    public Weeks toStandardWeeks() {
        return Weeks.weeks(getValue() / DateTimeConstants.HOURS_PER_WEEK);
    }

    /**
     * Converts this period in hours to a period in days assuming a
     * 24 hour day.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all days are 24 hours long.
     * This is not true when daylight savings time is considered, and may also
     * not be true for some unusual chronologies. However, it is included as it
     * is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of whole days for this number of hours
     */
    public Days toStandardDays() {
        return Days.days(getValue() / DateTimeConstants.HOURS_PER_DAY);
    }

    /**
     * Converts this period in hours to a period in minutes assuming a
     * 60 minute hour.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all hours are 60 minutes long.
     * This may not be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of minutes for this number of hours
     * @throws ArithmeticException if the number of minutes is too large to be represented
     */
    public Minutes toStandardMinutes() {
        return Minutes.minutes(FieldUtils.safeMultiply(getValue(), DateTimeConstants.MINUTES_PER_HOUR));
    }

    /**
     * Converts this period in hours to a period in seconds assuming a
     * 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all hours are
     * 60 minutes long and all minutes are 60 seconds long.
     * This may not be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of seconds for this number of hours
     * @throws ArithmeticException if the number of seconds is too large to be represented
     */
    public Seconds toStandardSeconds() {
        return Seconds.seconds(FieldUtils.safeMultiply(getValue(), DateTimeConstants.SECONDS_PER_HOUR));
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this period in hours to a duration in milliseconds assuming a
     * 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert from a period to a duration.
     * However to achieve this it makes the assumption that all hours are
     * 60 minutes and all minutes are 60 seconds. This might not be true for an
     * unusual chronology, for example one that takes leap seconds into account.
     * However, the method is included as it is a useful operation for many
     * applications and business rules.
     *
     * @return a duration equivalent to this number of hours
     */
    public Duration toStandardDuration() {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[26]++;
        long hours = getValue();  // assign to a long
        return new Duration(hours * DateTimeConstants.MILLIS_PER_HOUR);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of hours that this period represents.
     *
     * @return the number of hours in the period
     */
    public int getHours() {
        return getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of hours added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param hours  the amount of hours to add, may be negative
     * @return the new period plus the specified number of hours
     * @throws ArithmeticException if the result overflows an int
     */
    public Hours plus(int hours) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((hours == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[19]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[20]++;}
        return Hours.hours(FieldUtils.safeAdd(getValue(), hours));
    }

    /**
     * Returns a new instance with the specified number of hours added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param hours  the amount of hours to add, may be negative, null means zero
     * @return the new period plus the specified number of hours
     * @throws ArithmeticException if the result overflows an int
     */
    public Hours plus(Hours hours) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((hours == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[21]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[22]++;}
        return plus(hours.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of hours taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param hours  the amount of hours to take away, may be negative
     * @return the new period minus the specified number of hours
     * @throws ArithmeticException if the result overflows an int
     */
    public Hours minus(int hours) {
        return plus(FieldUtils.safeNegate(hours));
    }

    /**
     * Returns a new instance with the specified number of hours taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param hours  the amount of hours to take away, may be negative, null means zero
     * @return the new period minus the specified number of hours
     * @throws ArithmeticException if the result overflows an int
     */
    public Hours minus(Hours hours) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((hours == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[23]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[24]++;}
        return minus(hours.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the hours multiplied by the specified scalar.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param scalar  the amount to multiply by, may be negative
     * @return the new period multiplied by the specified scalar
     * @throws ArithmeticException if the result overflows an int
     */
    public Hours multipliedBy(int scalar) {
        return Hours.hours(FieldUtils.safeMultiply(getValue(), scalar));
    }

    /**
     * Returns a new instance with the hours divided by the specified divisor.
     * The calculation uses integer division, thus 3 divided by 2 is 1.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param divisor  the amount to divide by, may be negative
     * @return the new period divided by the specified divisor
     * @throws ArithmeticException if the divisor is zero
     */
    public Hours dividedBy(int divisor) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((divisor == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[25]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[26]++;}
        return Hours.hours(getValue() / divisor);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the hours value negated.
     *
     * @return the new period with a negated value
     * @throws ArithmeticException if the result overflows an int
     */
    public Hours negated() {
        return Hours.hours(FieldUtils.safeNegate(getValue()));
    }

    //-----------------------------------------------------------------------
    /**
     * Is this hours instance greater than the specified number of hours.
     *
     * @param other  the other period, null means zero
     * @return true if this hours instance is greater than the specified one
     */
    public boolean isGreaterThan(Hours other) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[27]++;
            return getValue() > 0;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[28]++;}
        return getValue() > other.getValue();
    }

    /**
     * Is this hours instance less than the specified number of hours.
     *
     * @param other  the other period, null means zero
     * @return true if this hours instance is less than the specified one
     */
    public boolean isLessThan(Hours other) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1jpakflvpbrnxdnl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[29]++;
            return getValue() < 0;

        } else {
  CodeCoverCoverageCounter$1jpakflvpbrnxdnl.branches[30]++;}
        return getValue() < other.getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets this instance as a String in the ISO8601 duration format.
     * <p>
     * For example, "PT4H" represents 4 hours.
     *
     * @return the value as an ISO8601 string
     */
    @ToString
    public String toString() {
        return "PT" + String.valueOf(getValue()) + "H";
    }

}

class CodeCoverCoverageCounter$1jpakflvpbrnxdnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1jpakflvpbrnxdnl ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.Hours.java";
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

  public CodeCoverCoverageCounter$1jpakflvpbrnxdnl () {
    super("org.joda.time.Hours.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
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
    log.startNamedSection("org.joda.time.Hours.java");
      for (int i = 1; i <= 32; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
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

