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
 * An immutable time period representing a number of months.
 * <p>
 * <code>Months</code> is an immutable period that can only store months.
 * It does not store years, days or hours for example. As such it is a
 * type-safe way of representing a number of months in an application.
 * <p>
 * The number of months is set in the constructor, and may be queried using
 * <code>getMonths()</code>. Basic mathematical operations are provided -
 * <code>plus()</code>, <code>minus()</code>, <code>multipliedBy()</code> and
 * <code>dividedBy()</code>.
 * <p>
 * <code>Months</code> is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.4
 */
public final class Months extends BaseSingleFieldPeriod {
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.ping();
  }


    /** Constant representing zero months. */
    public static final Months ZERO = new Months(0);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[1]++;
  }
    /** Constant representing one month. */
    public static final Months ONE = new Months(1);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[2]++;
  }
    /** Constant representing two months. */
    public static final Months TWO = new Months(2);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[3]++;
  }
    /** Constant representing three months. */
    public static final Months THREE = new Months(3);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[4]++;
  }
    /** Constant representing four months. */
    public static final Months FOUR = new Months(4);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[5]++;
  }
    /** Constant representing five months. */
    public static final Months FIVE = new Months(5);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[6]++;
  }
    /** Constant representing six months. */
    public static final Months SIX = new Months(6);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[7]++;
  }
    /** Constant representing seven months. */
    public static final Months SEVEN = new Months(7);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[8]++;
  }
    /** Constant representing eight months. */
    public static final Months EIGHT = new Months(8);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[9]++;
  }
    /** Constant representing nine months. */
    public static final Months NINE = new Months(9);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[10]++;
  }
    /** Constant representing ten months. */
    public static final Months TEN = new Months(10);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[11]++;
  }
    /** Constant representing eleven months. */
    public static final Months ELEVEN = new Months(11);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[12]++;
  }
    /** Constant representing twelve months. */
    public static final Months TWELVE = new Months(12);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[13]++;
  }
    /** Constant representing the maximum number of months that can be stored in this object. */
    public static final Months MAX_VALUE = new Months(Integer.MAX_VALUE);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[14]++;
  }
    /** Constant representing the minimum number of months that can be stored in this object. */
    public static final Months MIN_VALUE = new Months(Integer.MIN_VALUE);
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[15]++;
  }

    /** The parser to use for this class. */
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.months());
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[16]++;
  }
    /** Serialization version. */
    private static final long serialVersionUID = 87525275727380867L;
  static {
    CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[17]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of <code>Months</code> that may be cached.
     * <code>Months</code> is immutable, so instances can be cached and shared.
     * This factory method provides access to shared instances.
     *
     * @param months  the number of months to obtain an instance for
     * @return the instance of Months
     */
    public static Months months(int months) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[18]++;
        switch (months) {
            case 0:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[1]++;
                return ZERO;
            case 1:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[2]++;
                return ONE;
            case 2:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[3]++;
                return TWO;
            case 3:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[4]++;
                return THREE;
            case 4:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[5]++;
                return FOUR;
            case 5:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[6]++;
                return FIVE;
            case 6:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[7]++;
                return SIX;
            case 7:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[8]++;
                return SEVEN;
            case 8:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[9]++;
                return EIGHT;
            case 9:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[10]++;
                return NINE;
            case 10:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[11]++;
                return TEN;
            case 11:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[12]++;
                return ELEVEN;
            case 12:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[13]++;
                return TWELVE;
            case Integer.MAX_VALUE:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[14]++;
                return MAX_VALUE;
            case Integer.MIN_VALUE:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[15]++;
                return MIN_VALUE;
            default:
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[16]++;
                return new Months(months);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a <code>Months</code> representing the number of whole months
     * between the two specified datetimes. This method corectly handles
     * any daylight savings time changes that may occur during the interval.
     *
     * @param start  the start instant, must not be null
     * @param end  the end instant, must not be null
     * @return the period in months
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    public static Months monthsBetween(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[19]++;
        int amount = BaseSingleFieldPeriod.between(start, end, DurationFieldType.months());
        return Months.months(amount);
    }

    /**
     * Creates a <code>Months</code> representing the number of whole months
     * between the two specified partial datetimes.
     * <p>
     * The two partials must contain the same fields, for example you can specify
     * two <code>LocalDate</code> objects.
     *
     * @param start  the start partial date, must not be null
     * @param end  the end partial date, must not be null
     * @return the period in months
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Months monthsBetween(ReadablePartial start, ReadablePartial end) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[20]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((start instanceof LocalDate) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end instanceof LocalDate) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))   {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[17]++;
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[21]++;
            Chronology chrono = DateTimeUtils.getChronology(start.getChronology());
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[22]++;
            int months = chrono.months().getDifference(
                    ((LocalDate) end).getLocalMillis(), ((LocalDate) start).getLocalMillis());
            return Months.months(months);

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[18]++;}
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[23]++;
        int amount = BaseSingleFieldPeriod.between(start, end, ZERO);
        return Months.months(amount);
    }

    /**
     * Creates a <code>Months</code> representing the number of whole months
     * in the specified interval. This method corectly handles any daylight
     * savings time changes that may occur during the interval.
     *
     * @param interval  the interval to extract months from, null returns zero
     * @return the period in months
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Months monthsIn(ReadableInterval interval) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))   {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[19]++;
            return Months.ZERO;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[20]++;}
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[25]++;
        int amount = BaseSingleFieldPeriod.between(interval.getStart(), interval.getEnd(), DurationFieldType.months());
        return Months.months(amount);
    }

    /**
     * Creates a new <code>Months</code> by parsing a string in the ISO8601 format 'PnM'.
     * <p>
     * The parse will accept the full ISO syntax of PnYnMnWnDTnHnMnS however only the
     * months component may be non-zero. If any other component is non-zero, an exception
     * will be thrown.
     *
     * @param periodStr  the period string, null returns zero
     * @return the period in months
     * @throws IllegalArgumentException if the string format is invalid
     */
    @FromString
    public static Months parseMonths(String periodStr) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((periodStr == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[21]++;
            return Months.ZERO;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[22]++;}
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[27]++;
        Period p = PARSER.parsePeriod(periodStr);
        return Months.months(p.getMonths());
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new instance representing a number of months.
     * You should consider using the factory method {@link #months(int)}
     * instead of the constructor.
     *
     * @param months  the number of months to represent
     */
    private Months(int months) {
        super(months);
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[28]++;
    }

    /**
     * Resolves singletons.
     * 
     * @return the singleton instance
     */
    private Object readResolve() {
        return Months.months(getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the duration field type, which is <code>months</code>.
     *
     * @return the period type
     */
    public DurationFieldType getFieldType() {
        return DurationFieldType.months();
    }

    /**
     * Gets the period type, which is <code>months</code>.
     *
     * @return the period type
     */
    public PeriodType getPeriodType() {
        return PeriodType.months();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of months that this period represents.
     *
     * @return the number of months in the period
     */
    public int getMonths() {
        return getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of months added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param months  the amount of months to add, may be negative
     * @return the new period plus the specified number of months
     * @throws ArithmeticException if the result overflows an int
     */
    public Months plus(int months) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[29]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[23]++;
            return this;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[24]++;}
        return Months.months(FieldUtils.safeAdd(getValue(), months));
    }

    /**
     * Returns a new instance with the specified number of months added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param months  the amount of months to add, may be negative, null means zero
     * @return the new period plus the specified number of months
     * @throws ArithmeticException if the result overflows an int
     */
    public Months plus(Months months) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((months == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[25]++;
            return this;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[26]++;}
        return plus(months.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of months taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param months  the amount of months to take away, may be negative
     * @return the new period minus the specified number of months
     * @throws ArithmeticException if the result overflows an int
     */
    public Months minus(int months) {
        return plus(FieldUtils.safeNegate(months));
    }

    /**
     * Returns a new instance with the specified number of months taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param months  the amount of months to take away, may be negative, null means zero
     * @return the new period minus the specified number of months
     * @throws ArithmeticException if the result overflows an int
     */
    public Months minus(Months months) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((months == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[27]++;
            return this;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[28]++;}
        return minus(months.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the months multiplied by the specified scalar.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param scalar  the amount to multiply by, may be negative
     * @return the new period multiplied by the specified scalar
     * @throws ArithmeticException if the result overflows an int
     */
    public Months multipliedBy(int scalar) {
        return Months.months(FieldUtils.safeMultiply(getValue(), scalar));
    }

    /**
     * Returns a new instance with the months divided by the specified divisor.
     * The calculation uses integer division, thus 3 divided by 2 is 1.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param divisor  the amount to divide by, may be negative
     * @return the new period divided by the specified divisor
     * @throws ArithmeticException if the divisor is zero
     */
    public Months dividedBy(int divisor) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((divisor == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[29]++;
            return this;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[30]++;}
        return Months.months(getValue() / divisor);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the months value negated.
     *
     * @return the new period with a negated value
     * @throws ArithmeticException if the result overflows an int
     */
    public Months negated() {
        return Months.months(FieldUtils.safeNegate(getValue()));
    }

    //-----------------------------------------------------------------------
    /**
     * Is this months instance greater than the specified number of months.
     *
     * @param other  the other period, null means zero
     * @return true if this months instance is greater than the specified one
     */
    public boolean isGreaterThan(Months other) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[31]++;
            return getValue() > 0;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[32]++;}
        return getValue() > other.getValue();
    }

    /**
     * Is this months instance less than the specified number of months.
     *
     * @param other  the other period, null means zero
     * @return true if this months instance is less than the specified one
     */
    public boolean isLessThan(Months other) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[33]++;
            return getValue() < 0;

        } else {
  CodeCoverCoverageCounter$brg5rx3utq1qxbxb5.branches[34]++;}
        return getValue() < other.getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets this instance as a String in the ISO8601 duration format.
     * <p>
     * For example, "P4M" represents 4 months.
     *
     * @return the value as an ISO8601 string
     */
    @ToString
    public String toString() {
        return "P" + String.valueOf(getValue()) + "M";
    }

}

class CodeCoverCoverageCounter$brg5rx3utq1qxbxb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$brg5rx3utq1qxbxb5 ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.Months.java";
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

  public CodeCoverCoverageCounter$brg5rx3utq1qxbxb5 () {
    super("org.joda.time.Months.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
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
    log.startNamedSection("org.joda.time.Months.java");
      for (int i = 1; i <= 34; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
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

