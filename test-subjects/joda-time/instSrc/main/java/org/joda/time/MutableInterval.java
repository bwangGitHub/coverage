/*
 *  Copyright 2001-2005 Stephen Colebourne
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

import org.joda.time.base.BaseInterval;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;

/**
 * MutableInterval is the standard implementation of a mutable time interval.
 * <p>
 * A time interval represents a period of time between two instants.
 * Intervals are inclusive of the start instant and exclusive of the end.
 * The end instant is always greater than or equal to the start instant.
 * <p>
 * Intervals have a fixed millisecond duration.
 * This is the difference between the start and end instants.
 * The duration is represented separately by {@link ReadableDuration}.
 * As a result, intervals are not comparable.
 * To compare the length of two intervals, you should compare their durations.
 * <p>
 * An interval can also be converted to a {@link ReadablePeriod}.
 * This represents the difference between the start and end points in terms of fields
 * such as years and days.
 * <p>
 * If performing significant calculations on an interval, it may be faster to
 * convert an Interval object to a MutableInterval one.
 * <p>
 * MutableInterval is mutable and not thread-safe, unless concurrent threads
 * are not invoking mutator methods.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public class MutableInterval
        extends BaseInterval
        implements ReadWritableInterval, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = -5982824024992428470L;
  static {
    CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[1]++;
  }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code MutableInterval} from the specified string.
     * <p>
     * The String formats are described by {@link ISODateTimeFormat#dateTimeParser()}
     * and {@link ISOPeriodFormat#standard()}, and may be 'datetime/datetime',
     * 'datetime/period' or 'period/datetime'.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    public static MutableInterval parse(String str) {
        return new MutableInterval(str);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs a zero length time interval from 1970-01-01 to 1970-01-01.
     */
    public MutableInterval() {
        super(0L, 0L, null);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[2]++;
    }

    /**
     * Constructs an interval from a start and end instant with the ISO default chronology.
     * 
     * @param startInstant  start of this interval, as milliseconds from 1970-01-01T00:00:00Z.
     * @param endInstant  end of this interval, as milliseconds from 1970-01-01T00:00:00Z.
     * @throws IllegalArgumentException if the end is before the start
     */
    public MutableInterval(long startInstant, long endInstant) {
        super(startInstant, endInstant, null);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[3]++;
    }

    /**
     * Constructs an interval from a start and end instant with a chronology.
     * 
     * @param chronology  the chronology to use, null is ISO default
     * @param startInstant  start of this interval, as milliseconds from 1970-01-01T00:00:00Z.
     * @param endInstant  end of this interval, as milliseconds from 1970-01-01T00:00:00Z.
     * @throws IllegalArgumentException if the end is before the start
     */
    public MutableInterval(long startInstant, long endInstant, Chronology chronology) {
        super(startInstant, endInstant, chronology);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[4]++;
    }

    /**
     * Constructs an interval from a start and end instant.
     * <p>
     * The chronology used is that of the start instant.
     * 
     * @param start  start of this interval, null means now
     * @param end  end of this interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     */
    public MutableInterval(ReadableInstant start, ReadableInstant end) {
        super(start, end);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[5]++;
    }

    /**
     * Constructs an interval from a start instant and a duration.
     * 
     * @param start  start of this interval, null means now
     * @param duration  the duration of this interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the end instant exceeds the capacity of a long
     */
    public MutableInterval(ReadableInstant start, ReadableDuration duration) {
        super(start, duration);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[6]++;
    }

    /**
     * Constructs an interval from a millisecond duration and an end instant.
     * 
     * @param duration  the duration of this interval, null means zero length
     * @param end  end of this interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the start instant exceeds the capacity of a long
     */
    public MutableInterval(ReadableDuration duration, ReadableInstant end) {
        super(duration, end);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[7]++;
    }

    /**
     * Constructs an interval from a start instant and a time period.
     * <p>
     * When forming the interval, the chronology from the instant is used
     * if present, otherwise the chronology of the period is used.
     * 
     * @param start  start of this interval, null means now
     * @param period  the period of this interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the end instant exceeds the capacity of a long
     */
    public MutableInterval(ReadableInstant start, ReadablePeriod period) {
        super(start, period);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[8]++;
    }

    /**
     * Constructs an interval from a time period and an end instant.
     * <p>
     * When forming the interval, the chronology from the instant is used
     * if present, otherwise the chronology of the period is used.
     * 
     * @param period  the period of this interval, null means zero length
     * @param end  end of this interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the start instant exceeds the capacity of a long
     */
    public MutableInterval(ReadablePeriod period, ReadableInstant end) {
        super(period, end);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[9]++;
    }

    /**
     * Constructs a time interval by converting or copying from another object.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInterval and String.
     * The String formats are described by {@link ISODateTimeFormat#dateTimeParser()}
     * and {@link ISOPeriodFormat#standard()}, and may be 'datetime/datetime',
     * 'datetime/period' or 'period/datetime'.
     * 
     * @param interval  the time interval to copy
     * @throws IllegalArgumentException if the interval is invalid
     */
    public MutableInterval(Object interval) {
        super(interval, null);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[10]++;
    }

    /**
     * Constructs a time interval by converting or copying from another object,
     * overriding the chronology.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInterval and String.
     * The String formats are described by {@link ISODateTimeFormat#dateTimeParser()}
     * and {@link ISOPeriodFormat#standard()}, and may be 'datetime/datetime',
     * 'datetime/period' or 'period/datetime'.
     * 
     * @param interval  the time interval to copy
     * @param chronology  the chronology to use, null means ISO default
     * @throws IllegalArgumentException if the interval is invalid
     */
    public MutableInterval(Object interval, Chronology chronology) {
        super(interval, chronology);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[11]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets this interval from two millisecond instants retaining the chronology.
     *
     * @param startInstant  the start of the time interval
     * @param endInstant  the start of the time interval
     * @throws IllegalArgumentException if the end is before the start
     */
    public void setInterval(long startInstant, long endInstant) {
        super.setInterval(startInstant, endInstant, getChronology());
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[12]++;
    }

    /**
     * Sets this interval to be the same as another.
     *
     * @param interval  the interval to copy
     * @throws IllegalArgumentException if the interval is null
     */
    public void setInterval(ReadableInterval interval) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[1]++;
            throw new IllegalArgumentException("Interval must not be null");

        } else {
  CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[2]++;}
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[14]++;
        long startMillis = interval.getStartMillis();
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[15]++;
        long endMillis = interval.getEndMillis();
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[16]++;
        Chronology chrono = interval.getChronology();
        super.setInterval(startMillis, endMillis, chrono);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[17]++;
    }

    /**
     * Sets this interval from two instants, replacing the chronology with
     * that from the start instant.
     *
     * @param start  the start of the time interval
     * @param end  the start of the time interval
     * @throws IllegalArgumentException if the end is before the start
     */
    public void setInterval(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((start == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((end == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[3]++;
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[19]++;
            long now = DateTimeUtils.currentTimeMillis();
            setInterval(now, now);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[20]++;

        } else {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[4]++;
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[21]++;
            long startMillis = DateTimeUtils.getInstantMillis(start);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[22]++;
            long endMillis = DateTimeUtils.getInstantMillis(end);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[23]++;
            Chronology chrono = DateTimeUtils.getInstantChronology(start);
            super.setInterval(startMillis, endMillis, chrono);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[24]++;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the chronology of this time interval.
     *
     * @param chrono  the chronology to use, null means ISO default
     */
    public void setChronology(Chronology chrono) {
        super.setInterval(getStartMillis(), getEndMillis(), chrono);
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[25]++;
    }

    /**
     * Sets the start of this time interval.
     *
     * @param startInstant  the start of the time interval,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @throws IllegalArgumentException if the end is before the start
     */
    public void setStartMillis(long startInstant) {
        super.setInterval(startInstant, getEndMillis(), getChronology());
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[26]++;
    }

    /**
     * Sets the start of this time interval as an Instant.
     *
     * @param start  the start of the time interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     */
    public void setStart(ReadableInstant start) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[27]++;
        long startMillis = DateTimeUtils.getInstantMillis(start);
        super.setInterval(startMillis, getEndMillis(), getChronology());
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[28]++;
    }

    /** 
     * Sets the end of this time interval.
     *
     * @param endInstant  the end of the time interval,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @throws IllegalArgumentException if the end is before the start
     */
    public void setEndMillis(long endInstant) {
        super.setInterval(getStartMillis(), endInstant, getChronology());
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[29]++;
    }

    /** 
     * Sets the end of this time interval as an Instant.
     *
     * @param end  the end of the time interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     */
    public void setEnd(ReadableInstant end) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[30]++;
        long endMillis = DateTimeUtils.getInstantMillis(end);
        super.setInterval(getStartMillis(), endMillis, getChronology());
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[31]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the duration of this time interval, preserving the start instant.
     *
     * @param duration  new duration for interval
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the end instant exceeds the capacity of a long
     */
    public void setDurationAfterStart(long duration) {
        setEndMillis(FieldUtils.safeAdd(getStartMillis(), duration));
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[32]++;
    }

    /**
     * Sets the duration of this time interval, preserving the end instant.
     *
     * @param duration  new duration for interval
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the start instant exceeds the capacity of a long
     */
    public void setDurationBeforeEnd(long duration) {
        setStartMillis(FieldUtils.safeAdd(getEndMillis(), -duration));
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[33]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the duration of this time interval, preserving the start instant.
     *
     * @param duration  new duration for interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the end instant exceeds the capacity of a long
     */
    public void setDurationAfterStart(ReadableDuration duration) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[34]++;
        long durationMillis = DateTimeUtils.getDurationMillis(duration);
        setEndMillis(FieldUtils.safeAdd(getStartMillis(), durationMillis));
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[35]++;
    }

    /**
     * Sets the duration of this time interval, preserving the end instant.
     *
     * @param duration  new duration for interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the start instant exceeds the capacity of a long
     */
    public void setDurationBeforeEnd(ReadableDuration duration) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[36]++;
        long durationMillis = DateTimeUtils.getDurationMillis(duration);
        setStartMillis(FieldUtils.safeAdd(getEndMillis(), -durationMillis));
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[37]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the period of this time interval, preserving the start instant
     * and using the ISOChronology in the default zone for calculations.
     *
     * @param period  new period for interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the end instant exceeds the capacity of a long
     */
    public void setPeriodAfterStart(ReadablePeriod period) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[38]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[5]++;
            setEndMillis(getStartMillis());
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[39]++;

        } else {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[6]++;
            setEndMillis(getChronology().add(period, getStartMillis(), 1));
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[40]++;
        }
    }

    /**
     * Sets the period of this time interval, preserving the end instant
     * and using the ISOChronology in the default zone for calculations.
     *
     * @param period  new period for interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the start instant exceeds the capacity of a long
     */
    public void setPeriodBeforeEnd(ReadablePeriod period) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[41]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[7]++;
            setStartMillis(getEndMillis());
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[42]++;

        } else {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[8]++;
            setStartMillis(getChronology().add(period, getEndMillis(), -1));
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[43]++;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Clone this object without having to cast the returned object.
     *
     * @return a clone of the this object.
     */
    public MutableInterval copy() {
        return (MutableInterval) clone();
    }

    /**
     * Clone this object.
     *
     * @return a clone of this object.
     */
    public Object clone() {
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.statements[44]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return super.clone();
        } catch (CloneNotSupportedException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[10]++;
            throw new InternalError("Clone error");
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35.branches[9]++;
}
  }
    }

}

class CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35 ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.joda.time.MutableInterval.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1};
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

  public CodeCoverCoverageCounter$91q9y3vf4dilv7rj0iiz4q7deqf1r35 () {
    super("org.joda.time.MutableInterval.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
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
    log.startNamedSection("org.joda.time.MutableInterval.java");
      for (int i = 1; i <= 44; i++) {
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

