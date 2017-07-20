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

import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;

/**
 * Abstract Chronology for implementing chronologies based on Gregorian/Julian formulae.
 * Most of the utility methods required by subclasses are package-private,
 * reflecting the intention that they be defined in the same package.
 * <p>
 * BasicGJChronology is thread-safe and immutable, and all subclasses must
 * be as well.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @author Guy Allard
 * @since 1.2, refactored from CommonGJChronology
 */
abstract class BasicGJChronology extends BasicChronology {
  static {
    CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = 538276888268L;
  static {
    CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[1]++;
  }

    // These arrays are NOT public. We trust ourselves not to alter the array.
    // They use zero-based array indexes so the that valid range of months is
    // automatically checked.
    private static final int[] MIN_DAYS_PER_MONTH_ARRAY = {
        31,28,31,30,31,30,31,31,30,31,30,31
    };
  static {
    CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[2]++;
  }
    private static final int[] MAX_DAYS_PER_MONTH_ARRAY = {
        31,29,31,30,31,30,31,31,30,31,30,31
    };
  static {
    CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[3]++;
  }
    private static final long[] MIN_TOTAL_MILLIS_BY_MONTH_ARRAY;
    private static final long[] MAX_TOTAL_MILLIS_BY_MONTH_ARRAY;
    private static final long FEB_29 = (31L + 29 - 1) * DateTimeConstants.MILLIS_PER_DAY;
  static {
    CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[4]++;
  }

    static {
        MIN_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[5]++;
        MAX_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[6]++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[7]++;

        long minSum = 0;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[8]++;
        long maxSum = 0;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < 11) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.loops[1]--;
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.loops[2]--;
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.loops[3]++;
}
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[10]++;
            long millis = MIN_DAYS_PER_MONTH_ARRAY[i]
                * (long)DateTimeConstants.MILLIS_PER_DAY;
            minSum += millis;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[11]++;
            MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[i + 1] = minSum;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[12]++;

            millis = MAX_DAYS_PER_MONTH_ARRAY[i]
                * (long)DateTimeConstants.MILLIS_PER_DAY;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[13]++;
            maxSum += millis;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[14]++;
            MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[i + 1] = maxSum;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[15]++;
        }
    }

    /**
     * Constructor.
     */
    BasicGJChronology(Chronology base, Object param, int minDaysInFirstWeek) {
        super(base, param, minDaysInFirstWeek);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[16]++;
    }

    //-----------------------------------------------------------------------
    int getMonthOfYear(long millis, int year) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[17]++;
        // Perform a binary search to get the month. To make it go even faster,
        // compare using ints instead of longs. The number of milliseconds per
        // year exceeds the limit of a 32-bit int's capacity, so divide by
        // 1024. No precision is lost (except time of day) since the number of
        // milliseconds per day contains 1024 as a factor. After the division,
        // the instant isn't measured in milliseconds, but in units of
        // (128/125)seconds.

        int i = (int)((millis - getYearMillis(year)) >> 10);

        // There are 86400000 milliseconds per day, but divided by 1024 is
        // 84375. There are 84375 (128/125)seconds per day.

        return
            (isLeapYear(year))
            ? ((i < 182 * 84375)
               ? ((i < 91 * 84375)
                  ? ((i < 31 * 84375) ? 1 : (i < 60 * 84375) ? 2 : 3)
                  : ((i < 121 * 84375) ? 4 : (i < 152 * 84375) ? 5 : 6))
               : ((i < 274 * 84375)
                  ? ((i < 213 * 84375) ? 7 : (i < 244 * 84375) ? 8 : 9)
                  : ((i < 305 * 84375) ? 10 : (i < 335 * 84375) ? 11 : 12)))
            : ((i < 181 * 84375)
               ? ((i < 90 * 84375)
                  ? ((i < 31 * 84375) ? 1 : (i < 59 * 84375) ? 2 : 3)
                  : ((i < 120 * 84375) ? 4 : (i < 151 * 84375) ? 5 : 6))
               : ((i < 273 * 84375)
                  ? ((i < 212 * 84375) ? 7 : (i < 243 * 84375) ? 8 : 9)
                  : ((i < 304 * 84375) ? 10 : (i < 334 * 84375) ? 11 : 12)));
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of days in the specified month and year.
     * 
     * @param year  the year
     * @param month  the month
     * @return the number of days
     */
    int getDaysInYearMonth(int year, int month) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[1]++;
            return MAX_DAYS_PER_MONTH_ARRAY[month - 1];

        } else {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[2]++;
            return MIN_DAYS_PER_MONTH_ARRAY[month - 1];
        }
    }

    //-----------------------------------------------------------------------
    int getDaysInMonthMax(int month) {
        return MAX_DAYS_PER_MONTH_ARRAY[month - 1];
    }

    //-----------------------------------------------------------------------
    int getDaysInMonthMaxForSet(long instant, int value) {
        return (value > 28 ? getDaysInMonthMax(instant) : 28);
    }

    //-----------------------------------------------------------------------
    long getTotalMillisByYearMonth(int year, int month) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[3]++;
            return MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[month - 1];

        } else {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[4]++;
            return MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[month - 1];
        }
    }

    //-----------------------------------------------------------------------
    long getYearDifference(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[20]++;
        int minuendYear = getYear(minuendInstant);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[21]++;
        int subtrahendYear = getYear(subtrahendInstant);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[22]++;
    
        // Inlined remainder method to avoid duplicate calls to get.
        long minuendRem = minuendInstant - getYearMillis(minuendYear);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[23]++;
        long subtrahendRem = subtrahendInstant - getYearMillis(subtrahendYear);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
    
        // Balance leap year differences on remainders.
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((subtrahendRem >= FEB_29) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[5]++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isLeapYear(subtrahendYear)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[7]++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isLeapYear(minuendYear)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[9]++;
                    subtrahendRem -= DateTimeConstants.MILLIS_PER_DAY;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[27]++;

                } else {
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[10]++;}

            } else {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[8]++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[28]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((minuendRem >= FEB_29) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isLeapYear(minuendYear)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[11]++;
                minuendRem -= DateTimeConstants.MILLIS_PER_DAY;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[29]++;

            } else {
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[12]++;}
}

        } else {
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[6]++;}
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[30]++;
    
        int difference = minuendYear - subtrahendYear;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((minuendRem < subtrahendRem) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[13]++;
            difference--;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[32]++;

        } else {
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[14]++;}
        return difference;
    }

    //-----------------------------------------------------------------------
    long setYear(long instant, int year) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[33]++;
        int thisYear = getYear(instant);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[34]++;
        int dayOfYear = getDayOfYear(instant, thisYear);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[35]++;
        int millisOfDay = getMillisOfDay(instant);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dayOfYear > (31 + 28)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[15]++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[37]++;
int CodeCoverConditionCoverageHelper_C10; // after Feb 28
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isLeapYear(thisYear)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[17]++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[38]++;
int CodeCoverConditionCoverageHelper_C11;
                // Current date is Feb 29 or later.
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[19]++;
                    // Moving to a non-leap year, Feb 29 does not exist.
                    dayOfYear--;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[39]++;

                } else {
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[20]++;}

            } else {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[18]++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[40]++;
int CodeCoverConditionCoverageHelper_C12;
                // Current date is Mar 01 or later.
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[21]++;
                    // Moving to a leap year, account for Feb 29.
                    dayOfYear++;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[41]++;

                } else {
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[22]++;}
            }

        } else {
  CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.branches[16]++;}

        instant = getYearMonthDayMillis(year, 1, dayOfYear);
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[42]++;
        instant += millisOfDay;
CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx.statements[43]++;

        return instant;
    }

}

class CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx ());
  }
    public static long[] statements = new long[44];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicGJChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$aw3qavgqbj1v95nnrj47e9h28j694r40gx () {
    super("org.joda.time.chrono.BasicGJChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 43; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BasicGJChronology.java");
      for (int i = 1; i <= 43; i++) {
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
    for (int i = 1; i <= 12; i++) {
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

