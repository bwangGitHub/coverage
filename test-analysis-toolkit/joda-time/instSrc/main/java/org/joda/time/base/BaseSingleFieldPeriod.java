/*
 *  Copyright 2001-2011 Stephen Colebourne
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

import java.io.Serializable;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.FieldUtils;

/**
 * BaseSingleFieldPeriod is an abstract implementation of ReadablePeriod that
 * manages a single duration field, such as days or minutes.
 * <p>
 * This class should generally not be used directly by API users.
 * The {@link ReadablePeriod} interface should be used when different 
 * kinds of period objects are to be referenced.
 * <p>
 * BaseSingleFieldPeriod subclasses may be mutable and not thread-safe.
 *
 * @author Stephen Colebourne
 * @since 1.4
 */
public abstract class BaseSingleFieldPeriod
        implements ReadablePeriod, Comparable<BaseSingleFieldPeriod>, Serializable {
  static {
    CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.ping();
  }


    /** Serialization version. */
    private static final long serialVersionUID = 9386874258972L;
  static {
    CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[1]++;
  }

    /** The period in the units of this period. */
    private volatile int iPeriod;

    //-----------------------------------------------------------------------
    /**
     * Calculates the number of whole units between the two specified datetimes.
     *
     * @param start  the start instant, validated to not be null
     * @param end  the end instant, validated to not be null
     * @param field  the field type to use, must not be null
     * @return the period
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    protected static int between(ReadableInstant start, ReadableInstant end, DurationFieldType field) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((start == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[1]++;
            throw new IllegalArgumentException("ReadableInstant objects must not be null");

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[2]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[3]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(start);
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[4]++;
        int amount = field.getField(chrono).getDifference(end.getMillis(), start.getMillis());
        return amount;
    }

    //-----------------------------------------------------------------------
    /**
     * Calculates the number of whole units between the two specified partial datetimes.
     * <p>
     * The two partials must contain the same fields, for example you can specify
     * two <code>LocalDate</code> objects.
     *
     * @param start  the start partial date, validated to not be null
     * @param end  the end partial date, validated to not be null
     * @param zeroInstance  the zero instance constant, must not be null
     * @return the period
     * @throws IllegalArgumentException if the partials are null or invalid
     */
    protected static int between(ReadablePartial start, ReadablePartial end, ReadablePeriod zeroInstance) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((start == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((end == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[3]++;
            throw new IllegalArgumentException("ReadablePartial objects must not be null");

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[4]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((start.size() != end.size()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[5]++;
            throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[6]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int i = 0, isize = start.size();(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[1]--;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[2]--;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[3]++;
}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[8]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((start.getFieldType(i) != end.getFieldType(i)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[7]++;
                throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");

            } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[8]++;}
        }
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[9]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((DateTimeUtils.isContiguous(start) == false) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[9]++;
            throw new IllegalArgumentException("ReadablePartial objects must be contiguous");

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[10]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[10]++;
        Chronology chrono = DateTimeUtils.getChronology(start.getChronology()).withUTC();
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[11]++;
        int[] values = chrono.get(zeroInstance, chrono.set(start, 0L), chrono.set(end, 0L));
        return values[0];
    }

    /**
     * Creates a new instance representing the number of complete standard length units
     * in the specified period.
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
     * @param period  the period to get the number of hours from, must not be null
     * @param millisPerUnit  the number of milliseconds in one standard unit of this period
     * @throws IllegalArgumentException if the period contains imprecise duration values
     */
    protected static int standardPeriodIn(ReadablePeriod period, long millisPerUnit) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[12]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[11]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[12]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[13]++;
        Chronology iso = ISOChronology.getInstanceUTC();
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[14]++;
        long duration = 0L;
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < period.size()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[4]--;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[5]--;
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.loops[6]++;
}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[16]++;
            int value = period.getValue(i);
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[17]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value != 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[13]++;
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[18]++;
                DurationField field = period.getFieldType(i).getField(iso);
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[19]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((field.isPrecise() == false) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[15]++;
                    throw new IllegalArgumentException(
                            "Cannot convert period to duration as " + field.getName() +
                            " is not precise in the period " + period);

                } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[16]++;}
                duration = FieldUtils.safeAdd(duration, FieldUtils.safeMultiply(field.getUnitMillis(), value));
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[20]++;

            } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[14]++;}
        }
        return FieldUtils.safeToInt(duration / millisPerUnit);
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new instance representing the specified period.
     *
     * @param period  the period to represent
     */
    protected BaseSingleFieldPeriod(int period) {
        super();
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[21]++;
        iPeriod = period;
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[22]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the amount of this period.
     *
     * @return the period value
     */
    protected int getValue() {
        return iPeriod;
    }

    /**
     * Sets the amount of this period.
     * To make a subclass immutable you must declare it final, or block this method.
     *
     * @param value  the period value
     */
    protected void setValue(int value) {
        iPeriod = value;
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[23]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the single duration field type.
     *
     * @return the duration field type, not null
     */
    public abstract DurationFieldType getFieldType();

    /**
     * Gets the period type which matches the duration field type.
     *
     * @return the period type, not null
     */
    public abstract PeriodType getPeriodType();

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields that this period supports, which is one.
     *
     * @return the number of fields supported, which is one
     */
    public int size() {
        return 1;
    }

    /**
     * Gets the field type at the specified index.
     * <p>
     * The only index supported by this period is zero which returns the
     * field type of this class.
     *
     * @param index  the index to retrieve, which must be zero
     * @return the field at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public DurationFieldType getFieldType(int index) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[24]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((index != 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[17]++;
            throw new IndexOutOfBoundsException(String.valueOf(index));

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[18]++;}
        return getFieldType();
    }

    /**
     * Gets the value at the specified index.
     * <p>
     * The only index supported by this period is zero.
     *
     * @param index  the index to retrieve, which must be zero
     * @return the value of the field at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public int getValue(int index) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((index != 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[19]++;
            throw new IndexOutOfBoundsException(String.valueOf(index));

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[20]++;}
        return getValue();
    }

    /**
     * Gets the value of a duration field represented by this period.
     * <p>
     * If the field type specified does not match the type used by this class
     * then zero is returned.
     *
     * @param type  the field type to query, null returns zero
     * @return the value of that field, zero if field not supported
     */
    public int get(DurationFieldType type) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[26]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((type == getFieldType()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[21]++;
            return getValue();

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[22]++;}
        return 0;
    }

    /**
     * Checks whether the duration field specified is supported by this period.
     *
     * @param type  the type to check, may be null which returns false
     * @return true if the field is supported
     */
    public boolean isSupported(DurationFieldType type) {
        return (type == getFieldType());
    }

    //-----------------------------------------------------------------------
    /**
     * Get this period as an immutable <code>Period</code> object.
     * The period will use <code>PeriodType.standard()</code>.
     *
     * @return a <code>Period</code> representing the same number of days
     */
    public Period toPeriod() {
        return Period.ZERO.withFields(this);
    }

    /**
     * Get this object as a <code>MutablePeriod</code>.
     * <p>
     * This will always return a new <code>MutablePeriod</code> with the same fields.
     * The period will use <code>PeriodType.standard()</code>.
     * 
     * @return a MutablePeriod using the same field set and values
     */
    public MutablePeriod toMutablePeriod() {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[27]++;
        MutablePeriod period = new MutablePeriod();
        period.add(this);
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[28]++;
        return period;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this object with the specified object for equality based on the
     * value of each field. All ReadablePeriod instances are accepted, but only
     * those with a matching <code>PeriodType</code> can return true.
     *
     * @param period  a readable period to check against
     * @return true if all the field values are equal, false if
     *  not or the period is null or of an incorrect type
     */
    public boolean equals(Object period) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[29]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this == period) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[24]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[30]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((period instanceof ReadablePeriod == false) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[26]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[31]++;
        ReadablePeriod other = (ReadablePeriod) period;
        return (other.getPeriodType() == getPeriodType() && other.getValue(0) == getValue());
    }

    /**
     * Gets a hash code for the period as defined by ReadablePeriod.
     *
     * @return a hash code
     */
    public int hashCode() {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[32]++;
        int total = 17;
        total = 27 * total + getValue();
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[33]++;
        total = 27 * total + getFieldType().hashCode();
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[34]++;
        return total;
    }

    /**
     * Compares this period to another object of the same class.
     *
     * @param other  the other period, must not be null
     * @return zero if equal, positive if greater, negative if less
     * @throws NullPointerException if the other period is null
     * @throws ClassCastException if the other period is of a different type
     */
    public int compareTo(BaseSingleFieldPeriod other) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[35]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((other.getClass() != getClass()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[27]++;
            throw new ClassCastException(getClass() + " cannot be compared to " + other.getClass());

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[28]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[36]++;
        int otherValue = other.getValue();
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[37]++;
        int thisValue = getValue();
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[38]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((thisValue > otherValue) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[29]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[30]++;}
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.statements[39]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((thisValue < otherValue) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[31]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p.branches[32]++;}
        return 0;
    }

}

class CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.joda.time.base.BaseSingleFieldPeriod.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 18; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$lhnh4z2drwqtpu8wdja3wlzmw4mpxk6ee00d9j6p () {
    super("org.joda.time.base.BaseSingleFieldPeriod.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.base.BaseSingleFieldPeriod.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

