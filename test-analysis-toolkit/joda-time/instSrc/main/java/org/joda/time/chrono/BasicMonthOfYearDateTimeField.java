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

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

/**
 * Provides time calculations for the month of the year component of time.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.2, refactored from GJMonthOfYearDateTimeField
 */
class BasicMonthOfYearDateTimeField extends ImpreciseDateTimeField {
  static {
    CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = -8258715387168736L;
  static {
    CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[1]++;
  }

    private static final int MIN = DateTimeConstants.JANUARY;
  static {
    CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[2]++;
  }

    private final BasicChronology iChronology;
    private final int iMax;
    private final int iLeapMonth;

    /**
     * Restricted constructor.
     * 
     * @param leapMonth the month of year that leaps
     */
    BasicMonthOfYearDateTimeField(BasicChronology chronology, int leapMonth) {
        super(DateTimeFieldType.monthOfYear(), chronology.getAverageMillisPerMonth());
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[3]++;
        iChronology = chronology;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[4]++;
        iMax = iChronology.getMaxMonth();
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[5]++;
        iLeapMonth = leapMonth;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[6]++;
    }

    //-----------------------------------------------------------------------
    public boolean isLenient() {
        return false;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the Month component of the specified time instant.
     *
     * @see org.joda.time.DateTimeField#get(long)
     * @see org.joda.time.ReadableDateTime#getMonthOfYear()
     * @param instant  the time instant in millis to query.
     * @return the month extracted from the input.
     */
    public int get(long instant) {
        return iChronology.getMonthOfYear(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Add the specified month to the specified time instant.
     * The amount added may be negative.<p>
     * If the new month has less total days than the specified
     * day of the month, this value is coerced to the nearest
     * sane value. e.g.<p>
     * 07-31 - (1 month) = 06-30<p>
     * 03-31 - (1 month) = 02-28 or 02-29 depending<p>
     * 
     * @see org.joda.time.DateTimeField#add
     * @see org.joda.time.ReadWritableDateTime#addMonths(int)
     * @param instant  the time instant in millis to update.
     * @param months  the months to add (can be negative).
     * @return the updated time instant.
     */
    public long add(long instant, int months) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((months == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[1]++;
            return instant;
 // the easy case
        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[2]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[8]++;
        //
        // Save time part first.
        //
        long timePart = iChronology.getMillisOfDay(instant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[9]++;
        //
        //
        // Get this year and month.
        //
        int thisYear = iChronology.getYear(instant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[10]++;
        int thisMonth = iChronology.getMonthOfYear(instant, thisYear);
        // ----------------------------------------------------------
        //
        // Do not refactor without careful consideration.
        // Order of calculation is important.
        //
        int yearToUse;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[11]++;
        // Initially, monthToUse is zero-based
        int monthToUse = thisMonth - 1 + months;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((monthToUse >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[3]++;
            yearToUse = thisYear + (monthToUse / iMax);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[13]++;
            monthToUse = (monthToUse % iMax) + 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[14]++;

        } else {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[4]++;
            yearToUse = thisYear + (monthToUse / iMax) - 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[15]++;
            monthToUse = Math.abs(monthToUse);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[16]++;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[17]++;
            int remMonthToUse = monthToUse % iMax;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
            // Take care of the boundary condition
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((remMonthToUse == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[5]++;
                remMonthToUse = iMax;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[19]++;

            } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[6]++;}
            monthToUse = iMax - remMonthToUse + 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[20]++;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
            // Take care of the boundary condition
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((monthToUse == 1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[7]++;
                yearToUse += 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[22]++;

            } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[8]++;}
        }
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[23]++;
        // End of do not refactor.
        // ----------------------------------------------------------

        //
        // Quietly force DOM to nearest sane value.
        //
        int dayToUse = iChronology.getDayOfMonth(instant, thisYear, thisMonth);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[24]++;
        int maxDay = iChronology.getDaysInYearMonth(yearToUse, monthToUse);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((dayToUse > maxDay) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[9]++;
            dayToUse = maxDay;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[26]++;

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[10]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[27]++;
        //
        // get proper date part, and return result
        //
        long datePart =
            iChronology.getYearMonthDayMillis(yearToUse, monthToUse, dayToUse);
        return datePart + timePart;
    }

    //-----------------------------------------------------------------------
    public long add(long instant, long months) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[28]++;
        int i_months = (int)months;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i_months == months) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[11]++;
            return add(instant, i_months);

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[12]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[30]++;

        // Copied from add(long, int) and modified slightly:

        long timePart = iChronology.getMillisOfDay(instant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[31]++;

        int thisYear = iChronology.getYear(instant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[32]++;
        int thisMonth = iChronology.getMonthOfYear(instant, thisYear);

        long yearToUse;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[33]++;
        long monthToUse = thisMonth - 1 + months;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((monthToUse >= 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[13]++;
            yearToUse = thisYear + (monthToUse / iMax);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[35]++;
            monthToUse = (monthToUse % iMax) + 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[36]++;

        } else {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[14]++;
            yearToUse = thisYear + (monthToUse / iMax) - 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[37]++;
            monthToUse = Math.abs(monthToUse);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[38]++;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[39]++;
            int remMonthToUse = (int)(monthToUse % iMax);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((remMonthToUse == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[15]++;
                remMonthToUse = iMax;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[41]++;

            } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[16]++;}
            monthToUse = iMax - remMonthToUse + 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[42]++;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((monthToUse == 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[17]++;
                yearToUse += 1;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[44]++;

            } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[18]++;}
        }
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((yearToUse < iChronology.getMinYear()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((yearToUse > iChronology.getMaxYear()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[19]++;

            throw new IllegalArgumentException
                ("Magnitude of add amount is too large: " + months);

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[20]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[46]++;

        int i_yearToUse = (int)yearToUse;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[47]++;
        int i_monthToUse = (int)monthToUse;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[48]++;

        int dayToUse = iChronology.getDayOfMonth(instant, thisYear, thisMonth);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[49]++;
        int maxDay = iChronology.getDaysInYearMonth(i_yearToUse, i_monthToUse);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((dayToUse > maxDay) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[21]++;
            dayToUse = maxDay;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[51]++;

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[22]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[52]++;

        long datePart =
            iChronology.getYearMonthDayMillis(i_yearToUse, i_monthToUse, dayToUse);
        return datePart + timePart;
    }

    //-----------------------------------------------------------------------
    public int[] add(ReadablePartial partial, int fieldIndex, int[] values, int valueToAdd) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
        // overridden as superclass algorithm can't handle
        // 2004-02-29 + 48 months -> 2008-02-29 type dates
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((valueToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[23]++;
            return values;

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[24]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((DateTimeUtils.isContiguous(partial)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[25]++;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[55]++;
            long instant = 0L;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[56]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.loops[1]++;


int CodeCoverConditionCoverageHelper_C14;
            for (int i = 0, isize = partial.size();(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.loops[1]--;
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.loops[2]--;
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.loops[3]++;
}
                instant = partial.getFieldType(i).getField(iChronology).set(instant, values[i]);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[57]++;
            }
            instant = add(instant, valueToAdd);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[58]++;
            return iChronology.get(partial, instant);

        } else {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[26]++;
            return super.add(partial, fieldIndex, values, valueToAdd);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Add to the Month component of the specified time instant
     * wrapping around within that component if necessary.
     * 
     * @see org.joda.time.DateTimeField#addWrapField
     * @param instant  the time instant in millis to update.
     * @param months  the months to add (can be negative).
     * @return the updated time instant.
     */
    public long addWrapField(long instant, int months) {
        return set(instant, FieldUtils.getWrappedValue(get(instant), months, MIN, iMax));
    }

    //-----------------------------------------------------------------------
    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((minuendInstant < subtrahendInstant) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[27]++;
            return -getDifference(subtrahendInstant, minuendInstant);

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[28]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[60]++;

        int minuendYear = iChronology.getYear(minuendInstant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[61]++;
        int minuendMonth = iChronology.getMonthOfYear(minuendInstant, minuendYear);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[62]++;
        int subtrahendYear = iChronology.getYear(subtrahendInstant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[63]++;
        int subtrahendMonth = iChronology.getMonthOfYear(subtrahendInstant, subtrahendYear);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[64]++;

        long difference = (minuendYear - subtrahendYear) * ((long) iMax) + minuendMonth - subtrahendMonth;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[65]++;

        // Before adjusting for remainder, account for special case of add
        // where the day-of-month is forced to the nearest sane value.
        int minuendDom = iChronology.getDayOfMonth
            (minuendInstant, minuendYear, minuendMonth);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[66]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((minuendDom == iChronology.getDaysInYearMonth(minuendYear, minuendMonth)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[29]++;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[67]++;
            // Last day of the minuend month...
            int subtrahendDom = iChronology.getDayOfMonth
                (subtrahendInstant, subtrahendYear, subtrahendMonth);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[68]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((subtrahendDom > minuendDom) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[31]++;
                // ...and day of subtrahend month is larger.
                // Note: This works fine, but it ideally shouldn't invoke other
                // fields from within a field.
                subtrahendInstant = iChronology.dayOfMonth().set(subtrahendInstant, minuendDom);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[69]++;

            } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[30]++;}
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[70]++;

        // Inlined remainder method to avoid duplicate calls.
        long minuendRem = minuendInstant
            - iChronology.getYearMonthMillis(minuendYear, minuendMonth);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[71]++;
        long subtrahendRem = subtrahendInstant
            - iChronology.getYearMonthMillis(subtrahendYear, subtrahendMonth);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((minuendRem < subtrahendRem) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[33]++;
            difference--;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[73]++;

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[34]++;}

        return difference;
    }

    //-----------------------------------------------------------------------
    /**
     * Set the Month component of the specified time instant.<p>
     * If the new month has less total days than the specified
     * day of the month, this value is coerced to the nearest
     * sane value. e.g.<p>
     * 07-31 to month 6 = 06-30<p>
     * 03-31 to month 2 = 02-28 or 02-29 depending<p>
     * 
     * @param instant  the time instant in millis to update.
     * @param month  the month (1,12) to update the time to.
     * @return the updated time instant.
     * @throws IllegalArgumentException  if month is invalid
     */
    public long set(long instant, int month) {
        FieldUtils.verifyValueBounds(this, month, MIN, iMax);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[74]++;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[75]++;
        //
        int thisYear = iChronology.getYear(instant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[76]++;
        //
        int thisDom = iChronology.getDayOfMonth(instant, thisYear);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[77]++;
        int maxDom = iChronology.getDaysInYearMonth(thisYear, month);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[78]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((thisDom > maxDom) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[35]++;
            // Quietly force DOM to nearest sane value.
            thisDom = maxDom;
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[79]++;

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[36]++;}
        // Return newly calculated millis value
        return iChronology.getYearMonthDayMillis(thisYear, month, thisDom) +
            iChronology.getMillisOfDay(instant);
    }

    //-----------------------------------------------------------------------
    public DurationField getRangeDurationField() {
        return iChronology.years();
    }

    //-----------------------------------------------------------------------
    public boolean isLeap(long instant) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[80]++;
        int thisYear = iChronology.getYear(instant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[81]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((iChronology.isLeapYear(thisYear)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[37]++;
            return (iChronology.getMonthOfYear(instant, thisYear) == iLeapMonth);

        } else {
  CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.branches[38]++;}
        return false;
    }

    //-----------------------------------------------------------------------
    public int getLeapAmount(long instant) {
        return isLeap(instant) ? 1 : 0;
    }

    //-----------------------------------------------------------------------
    public DurationField getLeapDurationField() {
        return iChronology.days();
    }

    //-----------------------------------------------------------------------
    public int getMinimumValue() {
        return MIN;
    }

    //-----------------------------------------------------------------------
    public int getMaximumValue() {
        return iMax;
    }

    //-----------------------------------------------------------------------
    public long roundFloor(long instant) {
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[82]++;
        int year = iChronology.getYear(instant);
CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h.statements[83]++;
        int month = iChronology.getMonthOfYear(instant, year);
        return iChronology.getYearMonthMillis(year, month);
    }

    //-----------------------------------------------------------------------
    public long remainder(long instant) {
        return instant - roundFloor(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.monthOfYear();
    }
}

class CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h ());
  }
    public static long[] statements = new long[84];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicMonthOfYearDateTimeField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$2bnv4ehm3ee7lgptormg6y6ntkpy9wm764vwmt9vxo5sspq8asc4h () {
    super("org.joda.time.chrono.BasicMonthOfYearDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 83; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BasicMonthOfYearDateTimeField.java");
      for (int i = 1; i <= 83; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

