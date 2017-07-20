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
 * An immutable time period representing a number of days.
 * <p>
 * <code>Days</code> is an immutable period that can only store days.
 * It does not store years, months or hours for example. As such it is a
 * type-safe way of representing a number of days in an application.
 * <p>
 * The number of days is set in the constructor, and may be queried using
 * <code>getDays()</code>. Basic mathematical operations are provided -
 * <code>plus()</code>, <code>minus()</code>, <code>multipliedBy()</code> and
 * <code>dividedBy()</code>.
 * <p>
 * <code>Days</code> is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.4
 */
public final class Days extends BaseSingleFieldPeriod {
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.ping();
  }


    /** Constant representing zero days. */
    public static final Days ZERO = new Days(0);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[1]++;
  }
    /** Constant representing one day. */
    public static final Days ONE = new Days(1);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[2]++;
  }
    /** Constant representing two days. */
    public static final Days TWO = new Days(2);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[3]++;
  }
    /** Constant representing three days. */
    public static final Days THREE = new Days(3);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[4]++;
  }
    /** Constant representing four days. */
    public static final Days FOUR = new Days(4);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[5]++;
  }
    /** Constant representing five days. */
    public static final Days FIVE = new Days(5);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[6]++;
  }
    /** Constant representing six days. */
    public static final Days SIX = new Days(6);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[7]++;
  }
    /** Constant representing seven days. */
    public static final Days SEVEN = new Days(7);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[8]++;
  }
    /** Constant representing the maximum number of days that can be stored in this object. */
    public static final Days MAX_VALUE = new Days(Integer.MAX_VALUE);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[9]++;
  }
    /** Constant representing the minimum number of days that can be stored in this object. */
    public static final Days MIN_VALUE = new Days(Integer.MIN_VALUE);
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[10]++;
  }

    /** The paser to use for this class. */
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.days());
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[11]++;
  }
    /** Serialization version. */
    private static final long serialVersionUID = 87525275727380865L;
  static {
    CodeCoverCoverageCounter$7e7jktaat61dup.statements[12]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of <code>Days</code> that may be cached.
     * <code>Days</code> is immutable, so instances can be cached and shared.
     * This factory method provides access to shared instances.
     *
     * @param days  the number of days to obtain an instance for
     * @return the instance of Days
     */
    public static Days days(int days) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[13]++;
        switch (days) {
            case 0:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[1]++;
                return ZERO;
            case 1:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[2]++;
                return ONE;
            case 2:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[3]++;
                return TWO;
            case 3:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[4]++;
                return THREE;
            case 4:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[5]++;
                return FOUR;
            case 5:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[6]++;
                return FIVE;
            case 6:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[7]++;
                return SIX;
            case 7:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[8]++;
                return SEVEN;
            case Integer.MAX_VALUE:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[9]++;
                return MAX_VALUE;
            case Integer.MIN_VALUE:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[10]++;
                return MIN_VALUE;
            default:
CodeCoverCoverageCounter$7e7jktaat61dup.branches[11]++;
                return new Days(days);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a <code>Days</code> representing the number of whole days
     * between the two specified datetimes. This method corectly handles
     * any daylight savings time changes that may occur during the interval.
     *
     * @param start  the start instant, must not be null
     * @param end  the end instant, must not be null
     * @return the period in days
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    public static Days daysBetween(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[14]++;
        int amount = BaseSingleFieldPeriod.between(start, end, DurationFieldType.days());
        return Days.days(amount);
    }

    /**
     * Creates a <code>Days</code> representing the number of whole days
     * between the two specified partial datetimes.
     * <p>
     * The two partials must contain the same fields, for example you can specify
     * two <code>LocalDate</code> objects.
     *
     * @param start  the start partial date, must not be null
     * @param end  the end partial date, must not be null
     * @return the period in days
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Days daysBetween(ReadablePartial start, ReadablePartial end) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((start instanceof LocalDate) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end instanceof LocalDate) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))   {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[12]++;
CodeCoverCoverageCounter$7e7jktaat61dup.statements[16]++;
            Chronology chrono = DateTimeUtils.getChronology(start.getChronology());
CodeCoverCoverageCounter$7e7jktaat61dup.statements[17]++;
            int days = chrono.days().getDifference(
                    ((LocalDate) end).getLocalMillis(), ((LocalDate) start).getLocalMillis());
            return Days.days(days);

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[13]++;}
CodeCoverCoverageCounter$7e7jktaat61dup.statements[18]++;
        int amount = BaseSingleFieldPeriod.between(start, end, ZERO);
        return Days.days(amount);
    }

    /**
     * Creates a <code>Days</code> representing the number of whole days
     * in the specified interval. This method corectly handles any daylight
     * savings time changes that may occur during the interval.
     *
     * @param interval  the interval to extract days from, null returns zero
     * @return the period in days
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Days daysIn(ReadableInterval interval) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))   {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[14]++;
            return Days.ZERO;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[15]++;}
CodeCoverCoverageCounter$7e7jktaat61dup.statements[20]++;
        int amount = BaseSingleFieldPeriod.between(interval.getStart(), interval.getEnd(), DurationFieldType.days());
        return Days.days(amount);
    }

    /**
     * Creates a new <code>Days</code> representing the number of complete
     * standard length days in the specified period.
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
     * @return the period in days
     * @throws IllegalArgumentException if the period contains imprecise duration values
     */
    public static Days standardDaysIn(ReadablePeriod period) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[21]++;
        int amount = BaseSingleFieldPeriod.standardPeriodIn(period, DateTimeConstants.MILLIS_PER_DAY);
        return Days.days(amount);
    }

    /**
     * Creates a new <code>Days</code> by parsing a string in the ISO8601 format 'PnD'.
     * <p>
     * The parse will accept the full ISO syntax of PnYnMnWnDTnHnMnS however only the
     * days component may be non-zero. If any other component is non-zero, an exception
     * will be thrown.
     *
     * @param periodStr  the period string, null returns zero
     * @return the period in days
     * @throws IllegalArgumentException if the string format is invalid
     */
    @FromString
    public static Days parseDays(String periodStr) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((periodStr == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[16]++;
            return Days.ZERO;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[17]++;}
CodeCoverCoverageCounter$7e7jktaat61dup.statements[23]++;
        Period p = PARSER.parsePeriod(periodStr);
        return Days.days(p.getDays());
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new instance representing a number of days.
     * You should consider using the factory method {@link #days(int)}
     * instead of the constructor.
     *
     * @param days  the number of days to represent
     */
    private Days(int days) {
        super(days);
CodeCoverCoverageCounter$7e7jktaat61dup.statements[24]++;
    }

    /**
     * Resolves singletons.
     * 
     * @return the singleton instance
     */
    private Object readResolve() {
        return Days.days(getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the duration field type, which is <code>days</code>.
     *
     * @return the period type
     */
    public DurationFieldType getFieldType() {
        return DurationFieldType.days();
    }

    /**
     * Gets the period type, which is <code>days</code>.
     *
     * @return the period type
     */
    public PeriodType getPeriodType() {
        return PeriodType.days();
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this period in days to a period in weeks assuming a
     * 7 day week.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all weeks are
     * 7 days long.
     * This may not be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of weeks for this number of days
     */
    public Weeks toStandardWeeks() {
        return Weeks.weeks(getValue() / DateTimeConstants.DAYS_PER_WEEK);
    }

    /**
     * Converts this period in days to a period in hours assuming a
     * 24 hour day.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all days are 24 hours long.
     * This is not true when daylight savings is considered and may also not
     * be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of hours for this number of days
     * @throws ArithmeticException if the number of hours is too large to be represented
     */
    public Hours toStandardHours() {
        return Hours.hours(FieldUtils.safeMultiply(getValue(), DateTimeConstants.HOURS_PER_DAY));
    }

    /**
     * Converts this period in days to a period in minutes assuming a
     * 24 hour day and 60 minute hour.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all days are 24 hours
     * long and all hours are 60 minutes long.
     * This is not true when daylight savings is considered and may also not
     * be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of minutes for this number of days
     * @throws ArithmeticException if the number of minutes is too large to be represented
     */
    public Minutes toStandardMinutes() {
        return Minutes.minutes(FieldUtils.safeMultiply(getValue(), DateTimeConstants.MINUTES_PER_DAY));
    }

    /**
     * Converts this period in days to a period in seconds assuming a
     * 24 hour day, 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert between different types of period.
     * However to achieve this it makes the assumption that all days are 24 hours
     * long, all hours are 60 minutes long and all minutes are 60 seconds long.
     * This is not true when daylight savings is considered and may also not
     * be true for some unusual chronologies. However, it is included
     * as it is a useful operation for many applications and business rules.
     * 
     * @return a period representing the number of seconds for this number of days
     * @throws ArithmeticException if the number of seconds is too large to be represented
     */
    public Seconds toStandardSeconds() {
        return Seconds.seconds(FieldUtils.safeMultiply(getValue(), DateTimeConstants.SECONDS_PER_DAY));
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this period in days to a duration in milliseconds assuming a
     * 24 hour day, 60 minute hour and 60 second minute.
     * <p>
     * This method allows you to convert from a period to a duration.
     * However to achieve this it makes the assumption that all days are 24 hours
     * long, all hours are 60 minutes and all minutes are 60 seconds.
     * This is not true when daylight savings time is considered, and may also
     * not be true for some unusual chronologies. However, it is included as it
     * is a useful operation for many applications and business rules.
     * 
     * @return a duration equivalent to this number of days
     */
    public Duration toStandardDuration() {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[25]++;
        long days = getValue();  // assign to a long
        return new Duration(days * DateTimeConstants.MILLIS_PER_DAY);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of days that this period represents.
     *
     * @return the number of days in the period
     */
    public int getDays() {
        return getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of days added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param days  the amount of days to add, may be negative
     * @return the new period plus the specified number of days
     * @throws ArithmeticException if the result overflows an int
     */
    public Days plus(int days) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((days == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[18]++;
            return this;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[19]++;}
        return Days.days(FieldUtils.safeAdd(getValue(), days));
    }

    /**
     * Returns a new instance with the specified number of days added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param days  the amount of days to add, may be negative, null means zero
     * @return the new period plus the specified number of days
     * @throws ArithmeticException if the result overflows an int
     */
    public Days plus(Days days) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((days == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[20]++;
            return this;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[21]++;}
        return plus(days.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of days taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param days  the amount of days to take away, may be negative
     * @return the new period minus the specified number of days
     * @throws ArithmeticException if the result overflows an int
     */
    public Days minus(int days) {
        return plus(FieldUtils.safeNegate(days));
    }

    /**
     * Returns a new instance with the specified number of days taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param days  the amount of days to take away, may be negative, null means zero
     * @return the new period minus the specified number of days
     * @throws ArithmeticException if the result overflows an int
     */
    public Days minus(Days days) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((days == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[22]++;
            return this;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[23]++;}
        return minus(days.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the days multiplied by the specified scalar.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param scalar  the amount to multiply by, may be negative
     * @return the new period multiplied by the specified scalar
     * @throws ArithmeticException if the result overflows an int
     */
    public Days multipliedBy(int scalar) {
        return Days.days(FieldUtils.safeMultiply(getValue(), scalar));
    }

    /**
     * Returns a new instance with the days divided by the specified divisor.
     * The calculation uses integer division, thus 3 divided by 2 is 1.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param divisor  the amount to divide by, may be negative
     * @return the new period divided by the specified divisor
     * @throws ArithmeticException if the divisor is zero
     */
    public Days dividedBy(int divisor) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((divisor == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[24]++;
            return this;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[25]++;}
        return Days.days(getValue() / divisor);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the days value negated.
     *
     * @return the new period with a negated value
     * @throws ArithmeticException if the result overflows an int
     */
    public Days negated() {
        return Days.days(FieldUtils.safeNegate(getValue()));
    }

    //-----------------------------------------------------------------------
    /**
     * Is this days instance greater than the specified number of days.
     *
     * @param other  the other period, null means zero
     * @return true if this days instance is greater than the specified one
     */
    public boolean isGreaterThan(Days other) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[26]++;
            return getValue() > 0;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[27]++;}
        return getValue() > other.getValue();
    }

    /**
     * Is this days instance less than the specified number of days.
     *
     * @param other  the other period, null means zero
     * @return true if this days instance is less than the specified one
     */
    public boolean isLessThan(Days other) {
CodeCoverCoverageCounter$7e7jktaat61dup.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7e7jktaat61dup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7e7jktaat61dup.branches[28]++;
            return getValue() < 0;

        } else {
  CodeCoverCoverageCounter$7e7jktaat61dup.branches[29]++;}
        return getValue() < other.getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets this instance as a String in the ISO8601 duration format.
     * <p>
     * For example, "P4D" represents 4 days.
     *
     * @return the value as an ISO8601 string
     */
    @ToString
    public String toString() {
        return "P" + String.valueOf(getValue()) + "D";
    }

}

class CodeCoverCoverageCounter$7e7jktaat61dup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e7jktaat61dup ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[30];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.Days.java";
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

  public CodeCoverCoverageCounter$7e7jktaat61dup () {
    super("org.joda.time.Days.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 29; i++) {
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
    log.startNamedSection("org.joda.time.Days.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 29; i++) {
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

