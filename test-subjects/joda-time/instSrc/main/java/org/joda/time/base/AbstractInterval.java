/*
 *  Copyright 2001-2006 Stephen Colebourne
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
package org.joda.time.base;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.MutableInterval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * AbstractInterval provides the common behaviour for time intervals.
 * <p>
 * This class should generally not be used directly by API users. The 
 * {@link ReadableInterval} interface should be used when different 
 * kinds of intervals are to be referenced.
 * <p>
 * AbstractInterval subclasses may be mutable and not thread-safe.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class AbstractInterval implements ReadableInterval {
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.ping();
  }


    /**
     * Constructor.
     */
    protected AbstractInterval() {
        super();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[1]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Validates an interval.
     * 
     * @param start  the start instant in milliseconds
     * @param end  the end instant in milliseconds
     * @throws IllegalArgumentException if the interval is invalid
     */
    protected void checkInterval(long start, long end) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end < start) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[1]++;
            throw new IllegalArgumentException("The end instant must be greater or equal to the start");

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[2]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the start of this time interval, which is inclusive, as a DateTime.
     *
     * @return the start of the time interval
     */
    public DateTime getStart() {
        return new DateTime(getStartMillis(), getChronology());
    }

    /** 
     * Gets the end of this time interval, which is exclusive, as a DateTime.
     *
     * @return the end of the time interval
     */
    public DateTime getEnd() {
        return new DateTime(getEndMillis(), getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Does this time interval contain the specified millisecond instant.
     * <p>
     * Non-zero duration intervals are inclusive of the start instant and
     * exclusive of the end. A zero duration interval cannot contain anything.
     *
     * @param millisInstant  the instant to compare to,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @return true if this time interval contains the millisecond
     */
    public boolean contains(long millisInstant) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[3]++;
        long thisStart = getStartMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[4]++;
        long thisEnd = getEndMillis();
        return (millisInstant >= thisStart && millisInstant < thisEnd);
    }

    /**
     * Does this time interval contain the current instant.
     * <p>
     * Non-zero duration intervals are inclusive of the start instant and
     * exclusive of the end. A zero duration interval cannot contain anything.
     *
     * @return true if this time interval contains the current instant
     */
    public boolean containsNow() {
        return contains(DateTimeUtils.currentTimeMillis());
    }

    /**
     * Does this time interval contain the specified instant.
     * <p>
     * Non-zero duration intervals are inclusive of the start instant and
     * exclusive of the end. A zero duration interval cannot contain anything.
     * <p>
     * For example:
     * <pre>
     * [09:00 to 10:00) contains 08:59  = false (before start)
     * [09:00 to 10:00) contains 09:00  = true
     * [09:00 to 10:00) contains 09:59  = true
     * [09:00 to 10:00) contains 10:00  = false (equals end)
     * [09:00 to 10:00) contains 10:01  = false (after end)
     * 
     * [14:00 to 14:00) contains 14:00  = false (zero duration contains nothing)
     * </pre>
     * Passing in a <code>null</code> parameter will have the same effect as
     * calling {@link #containsNow()}.
     *
     * @param instant  the instant, null means now
     * @return true if this time interval contains the instant
     */
    public boolean contains(ReadableInstant instant) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[3]++;
            return containsNow();

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[4]++;}
        return contains(instant.getMillis());
    }

    /**
     * Does this time interval contain the specified time interval.
     * <p>
     * Non-zero duration intervals are inclusive of the start instant and
     * exclusive of the end. The other interval is contained if this interval
     * wholly contains, starts, finishes or equals it.
     * A zero duration interval cannot contain anything.
     * <p>
     * When two intervals are compared the result is one of three states:
     * (a) they abut, (b) there is a gap between them, (c) they overlap.
     * The <code>contains</code> method is not related to these states.
     * In particular, a zero duration interval is contained at the start of
     * a larger interval, but does not overlap (it abuts instead).
     * <p>
     * For example:
     * <pre>
     * [09:00 to 10:00) contains [09:00 to 10:00)  = true
     * [09:00 to 10:00) contains [09:00 to 09:30)  = true
     * [09:00 to 10:00) contains [09:30 to 10:00)  = true
     * [09:00 to 10:00) contains [09:15 to 09:45)  = true
     * [09:00 to 10:00) contains [09:00 to 09:00)  = true
     * 
     * [09:00 to 10:00) contains [08:59 to 10:00)  = false (otherStart before thisStart)
     * [09:00 to 10:00) contains [09:00 to 10:01)  = false (otherEnd after thisEnd)
     * [09:00 to 10:00) contains [10:00 to 10:00)  = false (otherStart equals thisEnd)
     * 
     * [14:00 to 14:00) contains [14:00 to 14:00)  = false (zero duration contains nothing)
     * </pre>
     * Passing in a <code>null</code> parameter will have the same effect as
     * calling {@link #containsNow()}.
     *
     * @param interval  the time interval to compare to, null means a zero duration interval now
     * @return true if this time interval contains the time interval
     */
    public boolean contains(ReadableInterval interval) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[5]++;
            return containsNow();

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[6]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[7]++;
        long otherStart = interval.getStartMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[8]++;
        long otherEnd = interval.getEndMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[9]++;
        long thisStart = getStartMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[10]++;
        long thisEnd = getEndMillis();
        return (thisStart <= otherStart && otherStart < thisEnd && otherEnd <= thisEnd);
    }

    /**
     * Does this time interval overlap the specified time interval.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * An interval overlaps another if it shares some common part of the
     * datetime continuum. 
     * <p>
     * When two intervals are compared the result is one of three states:
     * (a) they abut, (b) there is a gap between them, (c) they overlap.
     * The abuts state takes precedence over the other two, thus a zero duration
     * interval at the start of a larger interval abuts and does not overlap.
     * <p>
     * For example:
     * <pre>
     * [09:00 to 10:00) overlaps [08:00 to 08:30)  = false (completely before)
     * [09:00 to 10:00) overlaps [08:00 to 09:00)  = false (abuts before)
     * [09:00 to 10:00) overlaps [08:00 to 09:30)  = true
     * [09:00 to 10:00) overlaps [08:00 to 10:00)  = true
     * [09:00 to 10:00) overlaps [08:00 to 11:00)  = true
     * 
     * [09:00 to 10:00) overlaps [09:00 to 09:00)  = false (abuts before)
     * [09:00 to 10:00) overlaps [09:00 to 09:30)  = true
     * [09:00 to 10:00) overlaps [09:00 to 10:00)  = true
     * [09:00 to 10:00) overlaps [09:00 to 11:00)  = true
     * 
     * [09:00 to 10:00) overlaps [09:30 to 09:30)  = true
     * [09:00 to 10:00) overlaps [09:30 to 10:00)  = true
     * [09:00 to 10:00) overlaps [09:30 to 11:00)  = true
     * 
     * [09:00 to 10:00) overlaps [10:00 to 10:00)  = false (abuts after)
     * [09:00 to 10:00) overlaps [10:00 to 11:00)  = false (abuts after)
     * 
     * [09:00 to 10:00) overlaps [10:30 to 11:00)  = false (completely after)
     * 
     * [14:00 to 14:00) overlaps [14:00 to 14:00)  = false (abuts before and after)
     * [14:00 to 14:00) overlaps [13:00 to 15:00)  = true
     * </pre>
     *
     * @param interval  the time interval to compare to, null means a zero length interval now
     * @return true if the time intervals overlap
     */
    public boolean overlaps(ReadableInterval interval) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[11]++;
        long thisStart = getStartMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[12]++;
        long thisEnd = getEndMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[7]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[14]++;
            long now = DateTimeUtils.currentTimeMillis();
            return (thisStart < now && now < thisEnd);

        }  else {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[8]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[15]++;
            long otherStart = interval.getStartMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[16]++;
            long otherEnd = interval.getEndMillis();
            return (thisStart < otherEnd && otherStart < thisEnd);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Is this time interval before the specified millisecond instant.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * 
     * @param millisInstant  the instant to compare to,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @return true if this time interval is before the instant
     */
    public boolean isBefore(long millisInstant) {
        return (getEndMillis() <= millisInstant);
    }

    /**
     * Is this time interval before the current instant.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * 
     * @return true if this time interval is before the current instant
     */
    public boolean isBeforeNow() {
        return isBefore(DateTimeUtils.currentTimeMillis());
    }

    /**
     * Is this time interval before the specified instant.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * 
     * @param instant  the instant to compare to, null means now
     * @return true if this time interval is before the instant
     */
    public boolean isBefore(ReadableInstant instant) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[9]++;
            return isBeforeNow();

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[10]++;}
        return isBefore(instant.getMillis());
    }

    /**
     * Is this time interval entirely before the specified instant.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * 
     * @param interval  the interval to compare to, null means now
     * @return true if this time interval is before the interval specified
     */
    public boolean isBefore(ReadableInterval interval) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[11]++;
            return isBeforeNow();

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[12]++;}
        return isBefore(interval.getStartMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Is this time interval after the specified millisecond instant.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * 
     * @param millisInstant  the instant to compare to,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @return true if this time interval is after the instant
     */
    public boolean isAfter(long millisInstant) {
        return (getStartMillis() > millisInstant);
    }

    /**
     * Is this time interval after the current instant.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * 
     * @return true if this time interval is after the current instant
     */
    public boolean isAfterNow() {
        return isAfter(DateTimeUtils.currentTimeMillis());
    }

    /**
     * Is this time interval after the specified instant.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * 
     * @param instant  the instant to compare to, null means now
     * @return true if this time interval is after the instant
     */
    public boolean isAfter(ReadableInstant instant) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[13]++;
            return isAfterNow();

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[14]++;}
        return isAfter(instant.getMillis());
    }

    /**
     * Is this time interval entirely after the specified interval.
     * <p>
     * Intervals are inclusive of the start instant and exclusive of the end.
     * Only the end time of the specified interval is used in the comparison.
     * 
     * @param interval  the interval to compare to, null means now
     * @return true if this time interval is after the interval specified
     */
    public boolean isAfter(ReadableInterval interval) {
        long endMillis;
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[15]++;
            endMillis = DateTimeUtils.currentTimeMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[21]++;

        } else {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[16]++;
            endMillis = interval.getEndMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[22]++;
        }
        return (getStartMillis() >= endMillis);
    }

    //-----------------------------------------------------------------------
    /**
     * Get this interval as an immutable <code>Interval</code> object.
     *
     * @return the interval as an Interval object
     */
    public Interval toInterval() {
        return new Interval(getStartMillis(), getEndMillis(), getChronology());
    }

    /**
     * Get this time interval as a <code>MutableInterval</code>.
     * <p>
     * This will always return a new <code>MutableInterval</code> with the same interval.
     *
     * @return the time interval as a MutableInterval object
     */
    public MutableInterval toMutableInterval() {
        return new MutableInterval(getStartMillis(), getEndMillis(), getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the duration of this time interval in milliseconds.
     * <p>
     * The duration is equal to the end millis minus the start millis.
     *
     * @return the duration of the time interval in milliseconds
     * @throws ArithmeticException if the duration exceeds the capacity of a long
     */
    public long toDurationMillis() {
        return FieldUtils.safeAdd(getEndMillis(), -getStartMillis());
    }

    /**
     * Gets the duration of this time interval.
     * <p>
     * The duration is equal to the end millis minus the start millis.
     *
     * @return the duration of the time interval
     * @throws ArithmeticException if the duration exceeds the capacity of a long
     */
    public Duration toDuration() {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[23]++;
        long durMillis = toDurationMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((durMillis == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[17]++;
            return Duration.ZERO;

        } else {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[18]++;
            return new Duration(durMillis);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Converts the duration of the interval to a <code>Period</code> using the
     * All period type.
     * <p>
     * This method should be used to exract the field values describing the
     * difference between the start and end instants.
     *
     * @return a time period derived from the interval
     */
    public Period toPeriod() {
        return new Period(getStartMillis(), getEndMillis(), getChronology());
    }

    /**
     * Converts the duration of the interval to a <code>Period</code> using the
     * specified period type.
     * <p>
     * This method should be used to exract the field values describing the
     * difference between the start and end instants.
     *
     * @param type  the requested type of the duration, null means AllType
     * @return a time period derived from the interval
     */
    public Period toPeriod(PeriodType type) {
        return new Period(getStartMillis(), getEndMillis(), type, getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this object with the specified object for equality based
     * on start and end millis plus the chronology.
     * All ReadableInterval instances are accepted.
     * <p>
     * To compare the duration of two time intervals, use {@link #toDuration()}
     * to get the durations and compare those.
     *
     * @param readableInterval  a readable interval to check against
     * @return true if the start and end millis are equal
     */
    public boolean equals(Object readableInterval) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this == readableInterval) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[19]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[20]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[26]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((readableInterval instanceof ReadableInterval == false) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.branches[22]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[27]++;
        ReadableInterval other = (ReadableInterval) readableInterval;
        return 
            getStartMillis() == other.getStartMillis() &&
            getEndMillis() == other.getEndMillis() &&
            FieldUtils.equals(getChronology(), other.getChronology());
    }

    /**
     * Hashcode compatible with equals method.
     *
     * @return suitable hashcode
     */
    public int hashCode() {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[28]++;
        long start = getStartMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[29]++;
        long end = getEndMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[30]++;
        int result = 97;
        result = 31 * result + ((int) (start ^ (start >>> 32)));
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[31]++;
        result = 31 * result + ((int) (end ^ (end >>> 32)));
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[32]++;
        result = 31 * result + getChronology().hashCode();
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[33]++;
        return result;
    }

    /**
     * Output a string in ISO8601 interval format.
     *
     * @return re-parsable string
     */
    public String toString() {
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[34]++;
        DateTimeFormatter printer = ISODateTimeFormat.dateHourMinuteSecondFraction();
        printer = printer.withChronology(getChronology());
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[35]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[36]++;
        StringBuffer buf = new StringBuffer(48);
        printer.printTo(buf, getStartMillis());
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[37]++;
        buf.append('/');
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[38]++;
        printer.printTo(buf, getEndMillis());
CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l.statements[39]++;
        return buf.toString();
    }

}

class CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.joda.time.base.AbstractInterval.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$1ib8k6g9t1mz1yofrpsdr7xlpbrhwxq3l () {
    super("org.joda.time.base.AbstractInterval.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.base.AbstractInterval.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

