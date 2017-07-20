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
package org.joda.time.chrono;

import java.io.Serializable;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.UnsupportedDateTimeField;
import org.joda.time.field.UnsupportedDurationField;

/**
 * BaseChronology provides a skeleton implementation for chronology
 * classes. Many utility methods are defined, but all fields are unsupported.
 * <p>
 * BaseChronology is thread-safe and immutable, and all subclasses must be
 * as well.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class BaseChronology
        extends Chronology
        implements Serializable {
  static {
    CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.ping();
  }


    /** Serialization version. */
    private static final long serialVersionUID = -7310865996721419676L;
  static {
    CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected BaseChronology() {
        super();
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[2]++;
    }

    /**
     * Returns the DateTimeZone that this Chronology operates in, or null if
     * unspecified.
     *
     * @return DateTimeZone null if unspecified
     */
    public abstract DateTimeZone getZone();

    /**
     * Returns an instance of this Chronology that operates in the UTC time
     * zone. Chronologies that do not operate in a time zone or are already
     * UTC must return themself.
     *
     * @return a version of this chronology that ignores time zones
     */
    public abstract Chronology withUTC();
    
    /**
     * Returns an instance of this Chronology that operates in any time zone.
     *
     * @return a version of this chronology with a specific time zone
     * @param zone to use, or default if null
     * @see org.joda.time.chrono.ZonedChronology
     */
    public abstract Chronology withZone(DateTimeZone zone);

    /**
     * Returns a datetime millisecond instant, formed from the given year,
     * month, day, and millisecond values. The set of given values must refer
     * to a valid datetime, or else an IllegalArgumentException is thrown.
     * <p>
     * The default implementation calls upon separate DateTimeFields to
     * determine the result. Subclasses are encouraged to provide a more
     * efficient implementation.
     *
     * @param year year to use
     * @param monthOfYear month to use
     * @param dayOfMonth day of month to use
     * @param millisOfDay millisecond to use
     * @return millisecond instant from 1970-01-01T00:00:00Z
     */
    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int millisOfDay)
        throws IllegalArgumentException
    {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[3]++;
        long instant = year().set(0, year);
        instant = monthOfYear().set(instant, monthOfYear);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[4]++;
        instant = dayOfMonth().set(instant, dayOfMonth);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[5]++;
        return millisOfDay().set(instant, millisOfDay);
    }

    /**
     * Returns a datetime millisecond instant, formed from the given year,
     * month, day, hour, minute, second, and millisecond values. The set of
     * given values must refer to a valid datetime, or else an
     * IllegalArgumentException is thrown.
     * <p>
     * The default implementation calls upon separate DateTimeFields to
     * determine the result. Subclasses are encouraged to provide a more
     * efficient implementation.
     *
     * @param year year to use
     * @param monthOfYear month to use
     * @param dayOfMonth day of month to use
     * @param hourOfDay hour to use
     * @param minuteOfHour minute to use
     * @param secondOfMinute second to use
     * @param millisOfSecond millisecond to use
     * @return millisecond instant from 1970-01-01T00:00:00Z
     */
    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[6]++;
        long instant = year().set(0, year);
        instant = monthOfYear().set(instant, monthOfYear);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[7]++;
        instant = dayOfMonth().set(instant, dayOfMonth);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[8]++;
        instant = hourOfDay().set(instant, hourOfDay);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[9]++;
        instant = minuteOfHour().set(instant, minuteOfHour);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[10]++;
        instant = secondOfMinute().set(instant, secondOfMinute);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[11]++;
        return millisOfSecond().set(instant, millisOfSecond);
    }

    /**
     * Returns a datetime millisecond instant, from from the given instant,
     * hour, minute, second, and millisecond values. The set of given values
     * must refer to a valid datetime, or else an IllegalArgumentException is
     * thrown.
     * <p>
     * The default implementation calls upon separate DateTimeFields to
     * determine the result. Subclasses are encouraged to provide a more
     * efficient implementation.
     *
     * @param instant instant to start from
     * @param hourOfDay hour to use
     * @param minuteOfHour minute to use
     * @param secondOfMinute second to use
     * @param millisOfSecond millisecond to use
     * @return millisecond instant from 1970-01-01T00:00:00Z
     */
    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
        instant = hourOfDay().set(instant, hourOfDay);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[12]++;
        instant = minuteOfHour().set(instant, minuteOfHour);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[13]++;
        instant = secondOfMinute().set(instant, secondOfMinute);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[14]++;
        return millisOfSecond().set(instant, millisOfSecond);
    }

    //-----------------------------------------------------------------------
    /**
     * Validates whether the fields stored in a partial instant are valid.
     * <p>
     * This implementation uses {@link DateTimeField#getMinimumValue(ReadablePartial, int[])}
     * and {@link DateTimeField#getMaximumValue(ReadablePartial, int[])}.
     *
     * @param partial  the partial instant to validate
     * @param values  the values to validate, not null
     * @throws IllegalArgumentException if the instant is invalid
     */
    public void validate(ReadablePartial partial, int[] values) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[15]++;
        // check values in standard range, catching really stupid cases like -1
        // this means that the second check will not hit trouble
        int size = partial.size();
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[1]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[2]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[3]++;
}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[17]++;
            int value = values[i];
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[18]++;
            DateTimeField field = partial.getField(i);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value < field.getMinimumValue()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[1]++;
                throw new IllegalFieldValueException
                    (field.getType(), Integer.valueOf(value),
                     Integer.valueOf(field.getMinimumValue()), null);

            } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[2]++;}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value > field.getMaximumValue()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[3]++;
                throw new IllegalFieldValueException
                    (field.getType(), Integer.valueOf(value),
                     null, Integer.valueOf(field.getMaximumValue()));

            } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[4]++;}
        }
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
        // check values in specific range, catching really odd cases like 30th Feb
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[4]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[5]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[6]++;
}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[22]++;
            int value = values[i];
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[23]++;
            DateTimeField field = partial.getField(i);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value < field.getMinimumValue(partial, values)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[5]++;
                throw new IllegalFieldValueException
                    (field.getType(), Integer.valueOf(value),
                     Integer.valueOf(field.getMinimumValue(partial, values)), null);

            } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[6]++;}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value > field.getMaximumValue(partial, values)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[7]++;
                throw new IllegalFieldValueException
                    (field.getType(), Integer.valueOf(value),
                     null, Integer.valueOf(field.getMaximumValue(partial, values)));

            } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[8]++;}
        }
    }

    /**
     * Gets the values of a partial from an instant.
     *
     * @param partial  the partial instant to use
     * @param instant  the instant to query
     * @return the values of the partial extracted from the instant
     */
    public int[] get(ReadablePartial partial, long instant) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[26]++;
        int size = partial.size();
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[27]++;
        int[] values = new int[size];
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[28]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[7]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[8]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[9]++;
}
            values[i] = partial.getFieldType(i).getField(this).get(instant);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[29]++;
        }
        return values;
    }

    /**
     * Sets the partial into the instant.
     *
     * @param partial  the partial instant to use
     * @param instant  the instant to update
     * @return the updated instant
     */
    public long set(ReadablePartial partial, long instant) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[30]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[10]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i = 0, isize = partial.size();(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[10]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[11]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[12]++;
}
            instant = partial.getFieldType(i).getField(this).set(instant, partial.getValue(i));
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[31]++;
        }
        return instant;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the values of a period from an interval.
     *
     * @param period  the period instant to use
     * @param startInstant  the start instant of an interval to query
     * @param endInstant  the start instant of an interval to query
     * @return the values of the period extracted from the interval
     */
    public int[] get(ReadablePeriod period, long startInstant, long endInstant) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[32]++;
        int size = period.size();
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[33]++;
        int[] values = new int[size];
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((startInstant != endInstant) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[9]++;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[35]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[13]++;


int CodeCoverConditionCoverageHelper_C10;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[13]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[14]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[15]++;
}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[36]++;
                DurationField field = period.getFieldType(i).getField(this);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[37]++;
                int value = field.getDifference(endInstant, startInstant);
                startInstant = field.add(startInstant, value);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[38]++;
                values[i] = value;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[39]++;
            }

        } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[10]++;}
        return values;
    }

    /**
     * Gets the values of a period from an interval.
     *
     * @param period  the period instant to use
     * @param duration  the duration to query
     * @return the values of the period extracted from the duration
     */
    public int[] get(ReadablePeriod period, long duration) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[40]++;
        int size = period.size();
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[41]++;
        int[] values = new int[size];
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((duration != 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[11]++;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[43]++;
            long current = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[44]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[16]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[17]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[18]++;
}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[45]++;
                DurationField field = period.getFieldType(i).getField(this);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[46]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((field.isPrecise()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[13]++;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[47]++;
                    int value = field.getDifference(duration, current);
                    current = field.add(current, value);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[48]++;
                    values[i] = value;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[49]++;

                } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[14]++;}
            }

        } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[12]++;}
        return values;
    }

    /**
     * Adds the period to the instant, specifying the number of times to add.
     *
     * @param period  the period to add, null means add nothing
     * @param instant  the instant to add to
     * @param scalar  the number of times to add
     * @return the updated instant
     */
    public long add(ReadablePeriod period, long instant, int scalar) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[50]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((scalar != 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((period != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[15]++;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[51]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[19]++;


int CodeCoverConditionCoverageHelper_C15;
            for (int i = 0, isize = period.size();(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[19]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[20]--;
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.loops[21]++;
}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[52]++;
                long value = period.getValue(i);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[53]++;
int CodeCoverConditionCoverageHelper_C16; // use long to allow for multiplication (fits OK)
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((value != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[17]++;
                    instant = period.getFieldType(i).getField(this).add(instant, value * scalar);
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[54]++;

                } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[18]++;}
            }

        } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[16]++;}
        return instant;
    }

    //-----------------------------------------------------------------------
    /**
     * Adds the duration to the instant, specifying the number of times to add.
     *
     * @param instant  the instant to add to
     * @param duration  the duration to add
     * @param scalar  the number of times to add
     * @return the updated instant
     */
    public long add(long instant, long duration, int scalar) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((duration == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[19]++;
            return instant;

        } else {
  CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.branches[20]++;}
CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd.statements[56]++;
        long add = FieldUtils.safeMultiply(duration, scalar);
        return FieldUtils.safeAdd(instant, add);
    }

    // Millis
    //-----------------------------------------------------------------------
    /**
     * Get the millis duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField millis() {
        return UnsupportedDurationField.getInstance(DurationFieldType.millis());
    }

    /**
     * Get the millis of second field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField millisOfSecond() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfSecond(), millis());
    }

    /**
     * Get the millis of day field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField millisOfDay() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfDay(), millis());
    }

    // Second
    //-----------------------------------------------------------------------
    /**
     * Get the seconds duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField seconds() {
        return UnsupportedDurationField.getInstance(DurationFieldType.seconds());
    }

    /**
     * Get the second of minute field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField secondOfMinute() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfMinute(), seconds());
    }

    /**
     * Get the second of day field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField secondOfDay() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.secondOfDay(), seconds());
    }

    // Minute
    //-----------------------------------------------------------------------
    /**
     * Get the minutes duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField minutes() {
        return UnsupportedDurationField.getInstance(DurationFieldType.minutes());
    }

    /**
     * Get the minute of hour field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField minuteOfHour() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfHour(), minutes());
    }

    /**
     * Get the minute of day field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField minuteOfDay() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.minuteOfDay(), minutes());
    }

    // Hour
    //-----------------------------------------------------------------------
    /**
     * Get the hours duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField hours() {
        return UnsupportedDurationField.getInstance(DurationFieldType.hours());
    }

    /**
     * Get the hour of day (0-23) field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField hourOfDay() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfDay(), hours());
    }

    /**
     * Get the hour of day (offset to 1-24) field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField clockhourOfDay() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfDay(), hours());
    }

    // Halfday
    //-----------------------------------------------------------------------
    /**
     * Get the halfdays duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField halfdays() {
        return UnsupportedDurationField.getInstance(DurationFieldType.halfdays());
    }

    /**
     * Get the hour of am/pm (0-11) field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField hourOfHalfday() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.hourOfHalfday(), hours());
    }

    /**
     * Get the hour of am/pm (offset to 1-12) field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField clockhourOfHalfday() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.clockhourOfHalfday(), hours());
    }

    /**
     * Get the AM(0) PM(1) field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField halfdayOfDay() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.halfdayOfDay(), halfdays());
    }

    // Day
    //-----------------------------------------------------------------------
    /**
     * Get the days duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField days() {
        return UnsupportedDurationField.getInstance(DurationFieldType.days());
    }

    /**
     * Get the day of week field for this chronology.
     *
     * <p>DayOfWeek values are defined in
     * {@link org.joda.time.DateTimeConstants DateTimeConstants}.
     * They use the ISO definitions, where 1 is Monday and 7 is Sunday.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField dayOfWeek() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfWeek(), days());
    }

    /**
     * Get the day of month field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField dayOfMonth() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfMonth(), days());
    }

    /**
     * Get the day of year field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField dayOfYear() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.dayOfYear(), days());
    }

    // Week
    //-----------------------------------------------------------------------
    /**
     * Get the weeks duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField weeks() {
        return UnsupportedDurationField.getInstance(DurationFieldType.weeks());
    }

    /**
     * Get the week of a week based year field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField weekOfWeekyear() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekOfWeekyear(), weeks());
    }

    // Weekyear
    //-----------------------------------------------------------------------
    /**
     * Get the weekyears duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField weekyears() {
        return UnsupportedDurationField.getInstance(DurationFieldType.weekyears());
    }

    /**
     * Get the year of a week based year field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField weekyear() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyear(), weekyears());
    }

    /**
     * Get the year of a week based year in a century field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField weekyearOfCentury() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.weekyearOfCentury(), weekyears());
    }

    // Month
    //-----------------------------------------------------------------------
    /**
     * Get the months duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField months() {
        return UnsupportedDurationField.getInstance(DurationFieldType.months());
    }

    /**
     * Get the month of year field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField monthOfYear() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.monthOfYear(), months());
    }

    // Year
    //-----------------------------------------------------------------------
    /**
     * Get the years duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField years() {
        return UnsupportedDurationField.getInstance(DurationFieldType.years());
    }

    /**
     * Get the year field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField year() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.year(), years());
    }

    /**
     * Get the year of era field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField yearOfEra() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfEra(), years());
    }

    /**
     * Get the year of century field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField yearOfCentury() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.yearOfCentury(), years());
    }

    // Century
    //-----------------------------------------------------------------------
    /**
     * Get the centuries duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField centuries() {
        return UnsupportedDurationField.getInstance(DurationFieldType.centuries());
    }

    /**
     * Get the century of era field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField centuryOfEra() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.centuryOfEra(), centuries());
    }

    // Era
    //-----------------------------------------------------------------------
    /**
     * Get the eras duration field for this chronology.
     * 
     * @return DurationField or UnsupportedDurationField if unsupported
     */
    public DurationField eras() {
        return UnsupportedDurationField.getInstance(DurationFieldType.eras());
    }

    /**
     * Get the era field for this chronology.
     * 
     * @return DateTimeField or UnsupportedDateTimeField if unsupported
     */
    public DateTimeField era() {
        return UnsupportedDateTimeField.getInstance(DateTimeFieldType.era(), eras());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a debugging toString.
     * 
     * @return a debugging string
     */
    public abstract String toString();

}

class CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd ());
  }
    public static long[] statements = new long[57];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BaseChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$13960chj6mt9q3uh5jybayractzcdd () {
    super("org.joda.time.chrono.BaseChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 56; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BaseChronology.java");
      for (int i = 1; i <= 56; i++) {
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
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

