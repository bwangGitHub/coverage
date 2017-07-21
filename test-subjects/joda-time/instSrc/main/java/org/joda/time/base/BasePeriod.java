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
import org.joda.time.Duration;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PeriodConverter;
import org.joda.time.field.FieldUtils;

/**
 * BasePeriod is an abstract implementation of ReadablePeriod that stores
 * data in a <code>PeriodType</code> and an <code>int[]</code>.
 * <p>
 * This class should generally not be used directly by API users.
 * The {@link ReadablePeriod} interface should be used when different 
 * kinds of period objects are to be referenced.
 * <p>
 * BasePeriod subclasses may be mutable and not thread-safe.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class BasePeriod
        extends AbstractPeriod
        implements ReadablePeriod, Serializable {
  static {
    CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = -2110953284060001145L;
  static {
    CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[1]++;
  }
    /** Serialization version */
    private static final ReadablePeriod DUMMY_PERIOD = new AbstractPeriod() {
        public int getValue(int index) {
            return 0;
        }
        public PeriodType getPeriodType() {
            return PeriodType.time();
        }
    };
  static {
    CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[2]++;
  }

    /** The type of period */
    private final PeriodType iType;
    /** The values */
    private final int[] iValues;

    //-----------------------------------------------------------------------
    /**
     * Creates a period from a set of field values.
     *
     * @param years  amount of years in this period, which must be zero if unsupported
     * @param months  amount of months in this period, which must be zero if unsupported
     * @param weeks  amount of weeks in this period, which must be zero if unsupported
     * @param days  amount of days in this period, which must be zero if unsupported
     * @param hours  amount of hours in this period, which must be zero if unsupported
     * @param minutes  amount of minutes in this period, which must be zero if unsupported
     * @param seconds  amount of seconds in this period, which must be zero if unsupported
     * @param millis  amount of milliseconds in this period, which must be zero if unsupported
     * @param type  which set of fields this period supports
     * @throws IllegalArgumentException if period type is invalid
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected BasePeriod(int years, int months, int weeks, int days,
                         int hours, int minutes, int seconds, int millis,
                         PeriodType type) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[3]++;
        type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[4]++;
        iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[5]++;
        iValues = setPeriodInternal(years, months, weeks, days, hours, minutes, seconds, millis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[6]++; // internal method
    }

    /**
     * Creates a period from the given interval endpoints.
     *
     * @param startInstant  interval start, in milliseconds
     * @param endInstant  interval end, in milliseconds
     * @param type  which set of fields this period supports, null means standard
     * @param chrono  the chronology to use, null means ISO default
     * @throws IllegalArgumentException if period type is invalid
     */
    protected BasePeriod(long startInstant, long endInstant, PeriodType type, Chronology chrono) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[7]++;
        type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[8]++;
        chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[9]++;
        iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[10]++;
        iValues = chrono.get(this, startInstant, endInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[11]++;
    }

    /**
     * Creates a period from the given interval endpoints.
     *
     * @param startInstant  interval start, null means now
     * @param endInstant  interval end, null means now
     * @param type  which set of fields this period supports, null means standard
     * @throws IllegalArgumentException if period type is invalid
     */
    protected BasePeriod(ReadableInstant startInstant, ReadableInstant endInstant, PeriodType type) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[12]++;
        type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[13]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((startInstant == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((endInstant == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[1]++;
            iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[15]++;
            iValues = new int[size()];
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[16]++;

        } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[2]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[17]++;
            long startMillis = DateTimeUtils.getInstantMillis(startInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[18]++;
            long endMillis = DateTimeUtils.getInstantMillis(endInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[19]++;
            Chronology chrono = DateTimeUtils.getIntervalChronology(startInstant, endInstant);
            iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[20]++;
            iValues = chrono.get(this, startMillis, endMillis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[21]++;
        }
    }

    /**
     * Creates a period from the given duration and end point.
     * <p>
     * The two partials must contain the same fields, thus you can
     * specify two <code>LocalDate</code> objects, or two <code>LocalTime</code>
     * objects, but not one of each.
     * As these are Partial objects, time zones have no effect on the result.
     * <p>
     * The two partials must also both be contiguous - see
     * {@link DateTimeUtils#isContiguous(ReadablePartial)} for a
     * definition. Both <code>LocalDate</code> and <code>LocalTime</code> are contiguous.
     *
     * @param start  the start of the period, must not be null
     * @param end  the end of the period, must not be null
     * @param type  which set of fields this period supports, null means standard
     * @throws IllegalArgumentException if the partials are null or invalid
     * @since 1.1
     */
    protected BasePeriod(ReadablePartial start, ReadablePartial end, PeriodType type) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[22]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((start == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((end == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[3]++;
            throw new IllegalArgumentException("ReadablePartial objects must not be null");

        } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[4]++;}
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((start instanceof BaseLocal) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((end instanceof BaseLocal) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((start.getClass() == end.getClass()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[5]++;
            // for performance
            type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[25]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[26]++;
            long startMillis = ((BaseLocal) start).getLocalMillis();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[27]++;
            long endMillis = ((BaseLocal) end).getLocalMillis();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[28]++;
            Chronology chrono = start.getChronology();
            chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[29]++;
            iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[30]++;
            iValues = chrono.get(this, startMillis, endMillis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[31]++;

        } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[6]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((start.size() != end.size()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[7]++;
                throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");

            } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[8]++;}
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[33]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
            for (int i = 0, isize = start.size();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[1]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[2]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[3]++;
}
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((start.getFieldType(i) != end.getFieldType(i)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[9]++;
                    throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");

                } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[10]++;}
            }
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((DateTimeUtils.isContiguous(start) == false) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[11]++;
                throw new IllegalArgumentException("ReadablePartial objects must be contiguous");

            } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[12]++;}
            iType = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[36]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[37]++;
            Chronology chrono = DateTimeUtils.getChronology(start.getChronology()).withUTC();
            iValues = chrono.get(this, chrono.set(start, 0L), chrono.set(end, 0L));
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[38]++;
        }
    }

    /**
     * Creates a period from the given start point and duration.
     *
     * @param startInstant  the interval start, null means now
     * @param duration  the duration of the interval, null means zero-length
     * @param type  which set of fields this period supports, null means standard
     */
    protected BasePeriod(ReadableInstant startInstant, ReadableDuration duration, PeriodType type) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[39]++;
        type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[40]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[41]++;
        long startMillis = DateTimeUtils.getInstantMillis(startInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[42]++;
        long durationMillis = DateTimeUtils.getDurationMillis(duration);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[43]++;
        long endMillis = FieldUtils.safeAdd(startMillis, durationMillis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[44]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(startInstant);
        iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[45]++;
        iValues = chrono.get(this, startMillis, endMillis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[46]++;
    }

    /**
     * Creates a period from the given duration and end point.
     *
     * @param duration  the duration of the interval, null means zero-length
     * @param endInstant  the interval end, null means now
     * @param type  which set of fields this period supports, null means standard
     */
    protected BasePeriod(ReadableDuration duration, ReadableInstant endInstant, PeriodType type) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[47]++;
        type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[48]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[49]++;
        long durationMillis = DateTimeUtils.getDurationMillis(duration);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[50]++;
        long endMillis = DateTimeUtils.getInstantMillis(endInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[51]++;
        long startMillis = FieldUtils.safeSubtract(endMillis, durationMillis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[52]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(endInstant);
        iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[53]++;
        iValues = chrono.get(this, startMillis, endMillis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[54]++;
    }

    /**
     * Creates a period from the given millisecond duration with the standard period type
     * and ISO rules, ensuring that the calculation is performed with the time-only period type.
     * <p>
     * The calculation uses the hour, minute, second and millisecond fields.
     *
     * @param duration  the duration, in milliseconds
     */
    protected BasePeriod(long duration) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[55]++;
        // bug [3264409]
        // calculation uses period type from a period object (bad design)
        // thus we use a dummy period object with the time type
        iType = PeriodType.standard();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[56]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[57]++;
        int[] values = ISOChronology.getInstanceUTC().get(DUMMY_PERIOD, duration);
        iValues = new int[8];
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[58]++;
        System.arraycopy(values, 0, iValues, 4, 4);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[59]++;
    }

    /**
     * Creates a period from the given millisecond duration, which is only really
     * suitable for durations less than one day.
     * <p>
     * Only fields that are precise will be used.
     * Thus the largest precise field may have a large value.
     *
     * @param duration  the duration, in milliseconds
     * @param type  which set of fields this period supports, null means standard
     * @param chrono  the chronology to use, null means ISO default
     * @throws IllegalArgumentException if period type is invalid
     */
    protected BasePeriod(long duration, PeriodType type, Chronology chrono) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[60]++;
        type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[61]++;
        chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[62]++;
        iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[63]++;
        iValues = chrono.get(this, duration);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[64]++;
    }

    /**
     * Creates a new period based on another using the {@link ConverterManager}.
     *
     * @param period  the period to convert
     * @param type  which set of fields this period supports, null means use type from object
     * @param chrono  the chronology to use, null means ISO default
     * @throws IllegalArgumentException if period is invalid
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected BasePeriod(Object period, PeriodType type, Chronology chrono) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[65]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[66]++;
        PeriodConverter converter = ConverterManager.getInstance().getPeriodConverter(period);
        type = (type == null ? converter.getPeriodType(period) : type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[67]++;
        type = checkPeriodType(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[68]++;
        iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[69]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[70]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this instanceof ReadWritablePeriod) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[13]++;
            iValues = new int[size()];
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[71]++;
            chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[72]++;
            converter.setInto((ReadWritablePeriod) this, period, chrono);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[73]++;

        } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[14]++;
            iValues = new MutablePeriod(period, type, chrono).getValues();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[74]++;
        }
    }

    /**
     * Constructor used when we trust ourselves.
     * Do not expose publically.
     *
     * @param values  the values to use, not null, not cloned
     * @param type  which set of fields this period supports, not null
     */
    protected BasePeriod(int[] values, PeriodType type) {
        super();
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[75]++;
        iType = type;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[76]++;
        iValues = values;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[77]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Validates a period type, converting nulls to a default value and
     * checking the type is suitable for this instance.
     * 
     * @param type  the type to check, may be null
     * @return the validated type to use, not null
     * @throws IllegalArgumentException if the period type is invalid
     */
    protected PeriodType checkPeriodType(PeriodType type) {
        return DateTimeUtils.getPeriodType(type);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the period type.
     *
     * @return the period type
     */
    public PeriodType getPeriodType() {
        return iType;
    }

    /**
     * Gets the value at the specified index.
     *
     * @param index  the index to retrieve
     * @return the value of the field at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public int getValue(int index) {
        return iValues[index];
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the total millisecond duration of this period relative to a start instant.
     * <p>
     * This method adds the period to the specified instant in order to
     * calculate the duration.
     * <p>
     * An instant must be supplied as the duration of a period varies.
     * For example, a period of 1 month could vary between the equivalent of
     * 28 and 31 days in milliseconds due to different length months.
     * Similarly, a day can vary at Daylight Savings cutover, typically between
     * 23 and 25 hours.
     *
     * @param startInstant  the instant to add the period to, thus obtaining the duration
     * @return the total length of the period as a duration relative to the start instant
     * @throws ArithmeticException if the millis exceeds the capacity of the duration
     */
    public Duration toDurationFrom(ReadableInstant startInstant) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[78]++;
        long startMillis = DateTimeUtils.getInstantMillis(startInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[79]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(startInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[80]++;
        long endMillis = chrono.add(this, startMillis, 1);
        return new Duration(startMillis, endMillis);
    }

    /**
     * Gets the total millisecond duration of this period relative to an
     * end instant.
     * <p>
     * This method subtracts the period from the specified instant in order
     * to calculate the duration.
     * <p>
     * An instant must be supplied as the duration of a period varies.
     * For example, a period of 1 month could vary between the equivalent of
     * 28 and 31 days in milliseconds due to different length months.
     * Similarly, a day can vary at Daylight Savings cutover, typically between
     * 23 and 25 hours.
     *
     * @param endInstant  the instant to subtract the period from, thus obtaining the duration
     * @return the total length of the period as a duration relative to the end instant
     * @throws ArithmeticException if the millis exceeds the capacity of the duration
     */
    public Duration toDurationTo(ReadableInstant endInstant) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[81]++;
        long endMillis = DateTimeUtils.getInstantMillis(endInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[82]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(endInstant);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[83]++;
        long startMillis = chrono.add(this, endMillis, -1);
        return new Duration(startMillis, endMillis);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks whether a field type is supported, and if so adds the new value
     * to the relevant index in the specified array.
     * 
     * @param type  the field type
     * @param values  the array to update
     * @param newValue  the new value to store if successful
     */
    private void checkAndUpdate(DurationFieldType type, int[] values, int newValue) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[84]++;
        int index = indexOf(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[85]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[15]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[86]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((newValue != 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[17]++;
                throw new IllegalArgumentException(
                    "Period does not support field '" + type.getName() + "'");

            } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[18]++;}

        } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[16]++;
            values[index] = newValue;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[87]++;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Sets all the fields of this period from another.
     * 
     * @param period  the period to copy from, not null
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected void setPeriod(ReadablePeriod period) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[88]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[19]++;
            setValues(new int[size()]);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[89]++;

        } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[20]++;
            setPeriodInternal(period);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[90]++;
        }
    }

    /**
     * Private method called from constructor.
     */
    private void setPeriodInternal(ReadablePeriod period) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[91]++;
        int[] newValues = new int[size()];
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[92]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0, isize = period.size();(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[4]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[5]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[6]++;
}
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[93]++;
            DurationFieldType type = period.getFieldType(i);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[94]++;
            int value = period.getValue(i);
            checkAndUpdate(type, newValues, value);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[95]++;
        }
        setValues(newValues);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[96]++;
    }

    /**
     * Sets the eight standard the fields in one go.
     * 
     * @param years  amount of years in this period, which must be zero if unsupported
     * @param months  amount of months in this period, which must be zero if unsupported
     * @param weeks  amount of weeks in this period, which must be zero if unsupported
     * @param days  amount of days in this period, which must be zero if unsupported
     * @param hours  amount of hours in this period, which must be zero if unsupported
     * @param minutes  amount of minutes in this period, which must be zero if unsupported
     * @param seconds  amount of seconds in this period, which must be zero if unsupported
     * @param millis  amount of milliseconds in this period, which must be zero if unsupported
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected void setPeriod(int years, int months, int weeks, int days,
                             int hours, int minutes, int seconds, int millis) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[97]++;
        int[] newValues = setPeriodInternal(years, months, weeks, days, hours, minutes, seconds, millis);
        setValues(newValues);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[98]++;
    }

    /**
     * Private method called from constructor.
     */
    private int[] setPeriodInternal(int years, int months, int weeks, int days,
                                   int hours, int minutes, int seconds, int millis) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[99]++;
        int[] newValues = new int[size()];
        checkAndUpdate(DurationFieldType.years(), newValues, years);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[100]++;
        checkAndUpdate(DurationFieldType.months(), newValues, months);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[101]++;
        checkAndUpdate(DurationFieldType.weeks(), newValues, weeks);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[102]++;
        checkAndUpdate(DurationFieldType.days(), newValues, days);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[103]++;
        checkAndUpdate(DurationFieldType.hours(), newValues, hours);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[104]++;
        checkAndUpdate(DurationFieldType.minutes(), newValues, minutes);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[105]++;
        checkAndUpdate(DurationFieldType.seconds(), newValues, seconds);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[106]++;
        checkAndUpdate(DurationFieldType.millis(), newValues, millis);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[107]++;
        return newValues;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the value of a field in this period.
     * 
     * @param field  the field to set
     * @param value  the value to set
     * @throws IllegalArgumentException if field is is null or not supported.
     */
    protected void setField(DurationFieldType field, int value) {
        setFieldInto(iValues, field, value);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[108]++;
    }

    /**
     * Sets the value of a field in this period.
     * 
     * @param values  the array of values to update
     * @param field  the field to set
     * @param value  the value to set
     * @throws IllegalArgumentException if field is null or not supported.
     */
    protected void setFieldInto(int[] values, DurationFieldType field, int value) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[109]++;
        int index = indexOf(field);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[110]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[21]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[111]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((value != 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[23]++;
                throw new IllegalArgumentException(
                    "Period does not support field '" + field + "'");

            } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[24]++;}

        } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[22]++;
            values[index] = value;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[112]++;
        }
    }

    /**
     * Adds the value of a field in this period.
     * 
     * @param field  the field to set
     * @param value  the value to set
     * @throws IllegalArgumentException if field is is null or not supported.
     */
    protected void addField(DurationFieldType field, int value) {
        addFieldInto(iValues, field, value);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[113]++;
    }

    /**
     * Adds the value of a field in this period.
     * 
     * @param values  the array of values to update
     * @param field  the field to set
     * @param value  the value to set
     * @throws IllegalArgumentException if field is is null or not supported.
     */
    protected void addFieldInto(int[] values, DurationFieldType field, int value) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[114]++;
        int index = indexOf(field);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[115]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[25]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[116]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((value != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[27]++;
                throw new IllegalArgumentException(
                    "Period does not support field '" + field + "'");

            } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[28]++;}

        } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[26]++;
            values[index] = FieldUtils.safeAdd(values[index], value);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[117]++;
        }
    }

    /**
     * Merges the fields from another period.
     * 
     * @param period  the period to add from, not null
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected void mergePeriod(ReadablePeriod period) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[118]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((period != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[29]++;
            setValues(mergePeriodInto(getValues(), period));
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[119]++;

        } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[30]++;}
    }

    /**
     * Merges the fields from another period.
     * 
     * @param values  the array of values to update
     * @param period  the period to add from, not null
     * @return the updated values
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected int[] mergePeriodInto(int[] values, ReadablePeriod period) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[120]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;
         for (int i = 0, isize = period.size();(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[7]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[8]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[9]++;
}
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[121]++;
             DurationFieldType type = period.getFieldType(i);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[122]++;
             int value = period.getValue(i);
             checkAndUpdate(type, values, value);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[123]++;
         }
         return values;
    }

    /**
     * Adds the fields from another period.
     * 
     * @param period  the period to add from, not null
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected void addPeriod(ReadablePeriod period) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[124]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((period != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[31]++;
            setValues(addPeriodInto(getValues(), period));
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[125]++;

        } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[32]++;}
    }

    /**
     * Adds the fields from another period.
     * 
     * @param values  the array of values to update
     * @param period  the period to add from, not null
     * @return the updated values
     * @throws IllegalArgumentException if an unsupported field's value is non-zero
     */
    protected int[] addPeriodInto(int[] values, ReadablePeriod period) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[126]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[10]++;


int CodeCoverConditionCoverageHelper_C20;
         for (int i = 0, isize = period.size();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[10]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[11]--;
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.loops[12]++;
}
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[127]++;
             DurationFieldType type = period.getFieldType(i);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[128]++;
             int value = period.getValue(i);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[129]++;
int CodeCoverConditionCoverageHelper_C21;
             if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((value != 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[33]++;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[130]++;
                 int index = indexOf(type);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[131]++;
int CodeCoverConditionCoverageHelper_C22;
                 if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[35]++;
                     throw new IllegalArgumentException(
                         "Period does not support field '" + type.getName() + "'");

                 } else {
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[36]++;
                     values[index] = FieldUtils.safeAdd(getValue(index), value);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[132]++;
                 }

             } else {
  CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.branches[34]++;}
         }
         return values;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the value of the field at the specified index.
     * 
     * @param index  the index
     * @param value  the value to set
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    protected void setValue(int index, int value) {
        iValues[index] = value;
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[133]++;
    }

    /**
     * Sets the values of all fields.
     * <p>
     * In version 2.0 and later, this method copies the array into the original.
     * This is because the instance variable has been changed to be final to satisfy the Java Memory Model.
     * This only impacts subclasses that are mutable.
     * 
     * @param values  the array of values
     */
    protected void setValues(int[] values) {
        System.arraycopy(values, 0, iValues, 0, iValues.length);
CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh.statements[134]++;
    }

}

class CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh ());
  }
    public static long[] statements = new long[135];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.joda.time.base.BasePeriod.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,3,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 22; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$jw81qzysmo3ihax1b27gmsh () {
    super("org.joda.time.base.BasePeriod.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 134; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.base.BasePeriod.java");
      for (int i = 1; i <= 134; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

