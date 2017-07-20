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
 * An immutable time period representing a number of years.
 * <p>
 * <code>Years</code> is an immutable period that can only store years.
 * It does not store years, days or hours for example. As such it is a
 * type-safe way of representing a number of years in an application.
 * <p>
 * The number of years is set in the constructor, and may be queried using
 * <code>getYears()</code>. Basic mathematical operations are provided -
 * <code>plus()</code>, <code>minus()</code>, <code>multipliedBy()</code> and
 * <code>dividedBy()</code>.
 * <p>
 * <code>Years</code> is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.4
 */
public final class Years extends BaseSingleFieldPeriod {
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.ping();
  }


    /** Constant representing zero years. */
    public static final Years ZERO = new Years(0);
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[1]++;
  }
    /** Constant representing one year. */
    public static final Years ONE = new Years(1);
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[2]++;
  }
    /** Constant representing two years. */
    public static final Years TWO = new Years(2);
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[3]++;
  }
    /** Constant representing three years. */
    public static final Years THREE = new Years(3);
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[4]++;
  }
    /** Constant representing the maximum number of years that can be stored in this object. */
    public static final Years MAX_VALUE = new Years(Integer.MAX_VALUE);
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[5]++;
  }
    /** Constant representing the minimum number of years that can be stored in this object. */
    public static final Years MIN_VALUE = new Years(Integer.MIN_VALUE);
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[6]++;
  }

    /** The paser to use for this class. */
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.years());
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[7]++;
  }
    /** Serialization version. */
    private static final long serialVersionUID = 87525275727380868L;
  static {
    CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[8]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of <code>Years</code> that may be cached.
     * <code>Years</code> is immutable, so instances can be cached and shared.
     * This factory method provides access to shared instances.
     *
     * @param years  the number of years to obtain an instance for
     * @return the instance of Years
     */
    public static Years years(int years) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[9]++;
        switch (years) {
            case 0:
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[1]++;
                return ZERO;
            case 1:
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[2]++;
                return ONE;
            case 2:
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[3]++;
                return TWO;
            case 3:
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[4]++;
                return THREE;
            case Integer.MAX_VALUE:
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[5]++;
                return MAX_VALUE;
            case Integer.MIN_VALUE:
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[6]++;
                return MIN_VALUE;
            default:
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[7]++;
                return new Years(years);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a <code>Years</code> representing the number of whole years
     * between the two specified datetimes. This method corectly handles
     * any daylight savings time changes that may occur during the interval.
     *
     * @param start  the start instant, must not be null
     * @param end  the end instant, must not be null
     * @return the period in years
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    public static Years yearsBetween(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[10]++;
        int amount = BaseSingleFieldPeriod.between(start, end, DurationFieldType.years());
        return Years.years(amount);
    }

    /**
     * Creates a <code>Years</code> representing the number of whole years
     * between the two specified partial datetimes.
     * <p>
     * The two partials must contain the same fields, for example you can specify
     * two <code>LocalDate</code> objects.
     *
     * @param start  the start partial date, must not be null
     * @param end  the end partial date, must not be null
     * @return the period in years
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Years yearsBetween(ReadablePartial start, ReadablePartial end) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((start instanceof LocalDate) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end instanceof LocalDate) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false))   {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[8]++;
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[12]++;
            Chronology chrono = DateTimeUtils.getChronology(start.getChronology());
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[13]++;
            int years = chrono.years().getDifference(
                    ((LocalDate) end).getLocalMillis(), ((LocalDate) start).getLocalMillis());
            return Years.years(years);

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[9]++;}
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[14]++;
        int amount = BaseSingleFieldPeriod.between(start, end, ZERO);
        return Years.years(amount);
    }

    /**
     * Creates a <code>Years</code> representing the number of whole years
     * in the specified interval. This method corectly handles any daylight
     * savings time changes that may occur during the interval.
     *
     * @param interval  the interval to extract years from, null returns zero
     * @return the period in years
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    public static Years yearsIn(ReadableInterval interval) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))   {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[10]++;
            return Years.ZERO;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[11]++;}
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[16]++;
        int amount = BaseSingleFieldPeriod.between(interval.getStart(), interval.getEnd(), DurationFieldType.years());
        return Years.years(amount);
    }

    /**
     * Creates a new <code>Years</code> by parsing a string in the ISO8601 format 'PnY'.
     * <p>
     * The parse will accept the full ISO syntax of PnYnMnWnDTnHnMnS however only the
     * years component may be non-zero. If any other component is non-zero, an exception
     * will be thrown.
     *
     * @param periodStr  the period string, null returns zero
     * @return the period in years
     * @throws IllegalArgumentException if the string format is invalid
     */
    @FromString
    public static Years parseYears(String periodStr) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((periodStr == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[12]++;
            return Years.ZERO;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[13]++;}
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[18]++;
        Period p = PARSER.parsePeriod(periodStr);
        return Years.years(p.getYears());
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new instance representing a number of years.
     * You should consider using the factory method {@link #years(int)}
     * instead of the constructor.
     *
     * @param years  the number of years to represent
     */
    private Years(int years) {
        super(years);
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[19]++;
    }

    /**
     * Resolves singletons.
     * 
     * @return the singleton instance
     */
    private Object readResolve() {
        return Years.years(getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the duration field type, which is <code>years</code>.
     *
     * @return the period type
     */
    public DurationFieldType getFieldType() {
        return DurationFieldType.years();
    }

    /**
     * Gets the period type, which is <code>years</code>.
     *
     * @return the period type
     */
    public PeriodType getPeriodType() {
        return PeriodType.years();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of years that this period represents.
     *
     * @return the number of years in the period
     */
    public int getYears() {
        return getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of years added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param years  the amount of years to add, may be negative
     * @return the new period plus the specified number of years
     * @throws ArithmeticException if the result overflows an int
     */
    public Years plus(int years) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[14]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[15]++;}
        return Years.years(FieldUtils.safeAdd(getValue(), years));
    }

    /**
     * Returns a new instance with the specified number of years added.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param years  the amount of years to add, may be negative, null means zero
     * @return the new period plus the specified number of years
     * @throws ArithmeticException if the result overflows an int
     */
    public Years plus(Years years) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((years == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[16]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[17]++;}
        return plus(years.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the specified number of years taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param years  the amount of years to take away, may be negative
     * @return the new period minus the specified number of years
     * @throws ArithmeticException if the result overflows an int
     */
    public Years minus(int years) {
        return plus(FieldUtils.safeNegate(years));
    }

    /**
     * Returns a new instance with the specified number of years taken away.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param years  the amount of years to take away, may be negative, null means zero
     * @return the new period minus the specified number of years
     * @throws ArithmeticException if the result overflows an int
     */
    public Years minus(Years years) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((years == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[18]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[19]++;}
        return minus(years.getValue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the years multiplied by the specified scalar.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param scalar  the amount to multiply by, may be negative
     * @return the new period multiplied by the specified scalar
     * @throws ArithmeticException if the result overflows an int
     */
    public Years multipliedBy(int scalar) {
        return Years.years(FieldUtils.safeMultiply(getValue(), scalar));
    }

    /**
     * Returns a new instance with the years divided by the specified divisor.
     * The calculation uses integer division, thus 3 divided by 2 is 1.
     * <p>
     * This instance is immutable and unaffected by this method call.
     *
     * @param divisor  the amount to divide by, may be negative
     * @return the new period divided by the specified divisor
     * @throws ArithmeticException if the divisor is zero
     */
    public Years dividedBy(int divisor) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((divisor == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[20]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[21]++;}
        return Years.years(getValue() / divisor);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a new instance with the years value negated.
     *
     * @return the new period with a negated value
     * @throws ArithmeticException if the result overflows an int
     */
    public Years negated() {
        return Years.years(FieldUtils.safeNegate(getValue()));
    }

    //-----------------------------------------------------------------------
    /**
     * Is this years instance greater than the specified number of years.
     *
     * @param other  the other period, null means zero
     * @return true if this years instance is greater than the specified one
     */
    public boolean isGreaterThan(Years other) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[22]++;
            return getValue() > 0;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[23]++;}
        return getValue() > other.getValue();
    }

    /**
     * Is this years instance less than the specified number of years.
     *
     * @param other  the other period, null means zero
     * @return true if this years instance is less than the specified one
     */
    public boolean isLessThan(Years other) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[24]++;
            return getValue() < 0;

        } else {
  CodeCoverCoverageCounter$1wqtvjfvvp94jhb5.branches[25]++;}
        return getValue() < other.getValue();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets this instance as a String in the ISO8601 duration format.
     * <p>
     * For example, "P4Y" represents 4 years.
     *
     * @return the value as an ISO8601 string
     */
    @ToString
    public String toString() {
        return "P" + String.valueOf(getValue()) + "Y";
    }

}

class CodeCoverCoverageCounter$1wqtvjfvvp94jhb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wqtvjfvvp94jhb5 ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[26];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.Years.java";
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

  public CodeCoverCoverageCounter$1wqtvjfvvp94jhb5 () {
    super("org.joda.time.Years.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
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
    log.startNamedSection("org.joda.time.Years.java");
      for (int i = 1; i <= 25; i++) {
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

