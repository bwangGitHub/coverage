/*
 *  Copyright 2001-2009 Stephen Colebourne
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.field.FieldUtils;

/**
 * Controls a period implementation by specifying which duration fields are to be used.
 * <p>
 * The following implementations are provided:
 * <ul>
 * <li>Standard - years, months, weeks, days, hours, minutes, seconds, millis
 * <li>YearMonthDayTime - years, months, days, hours, minutes, seconds, millis
 * <li>YearMonthDay - years, months, days
 * <li>YearWeekDayTime - years, weeks, days, hours, minutes, seconds, millis
 * <li>YearWeekDay - years, weeks, days
 * <li>YearDayTime - years, days, hours, minutes, seconds, millis
 * <li>YearDay - years, days, hours
 * <li>DayTime - days, hours, minutes, seconds, millis
 * <li>Time - hours, minutes, seconds, millis
 * <li>plus one for each single type
 * </ul>
 *
 * <p>
 * PeriodType is thread-safe and immutable, and all subclasses must be as well.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public class PeriodType implements Serializable {
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.ping();
  }

    /** Serialization version */
    private static final long serialVersionUID = 2274324892792009998L;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[1]++;
  }

    /** Cache of all the known types. */
    private static final Map<PeriodType, Object> cTypes = new HashMap<PeriodType, Object>(32);
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[2]++;
  }

    static int YEAR_INDEX = 0;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[3]++;
  }
    static int MONTH_INDEX = 1;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[4]++;
  }
    static int WEEK_INDEX = 2;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[5]++;
  }
    static int DAY_INDEX = 3;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[6]++;
  }
    static int HOUR_INDEX = 4;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[7]++;
  }
    static int MINUTE_INDEX = 5;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[8]++;
  }
    static int SECOND_INDEX = 6;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[9]++;
  }
    static int MILLI_INDEX = 7;
  static {
    CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[10]++;
  }
    
    private static PeriodType cStandard;
    private static PeriodType cYMDTime;
    private static PeriodType cYMD;
    private static PeriodType cYWDTime;
    private static PeriodType cYWD;
    private static PeriodType cYDTime;
    private static PeriodType cYD;
    private static PeriodType cDTime;
    private static PeriodType cTime;
    
    private static PeriodType cYears;
    private static PeriodType cMonths;
    private static PeriodType cWeeks;
    private static PeriodType cDays;
    private static PeriodType cHours;
    private static PeriodType cMinutes;
    private static PeriodType cSeconds;
    private static PeriodType cMillis;

    /**
     * Gets a type that defines all standard fields.
     * <ul>
     * <li>years
     * <li>months
     * <li>weeks
     * <li>days
     * <li>hours
     * <li>minutes
     * <li>seconds
     * <li>milliseconds
     * </ul>
     *
     * @return the period type
     */
    public static PeriodType standard() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[11]++;
        PeriodType type = cStandard;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[1]++;
            type = new PeriodType(
                "Standard",
                new DurationFieldType[] {
                    DurationFieldType.years(), DurationFieldType.months(),
                    DurationFieldType.weeks(), DurationFieldType.days(),
                    DurationFieldType.hours(), DurationFieldType.minutes(),
                    DurationFieldType.seconds(), DurationFieldType.millis(),
                },
                new int[] { 0, 1, 2, 3, 4, 5, 6, 7, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[13]++;
            cStandard = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[14]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[2]++;}
        return type;
    }

    /**
     * Gets a type that defines all standard fields except weeks.
     * <ul>
     * <li>years
     * <li>months
     * <li>days
     * <li>hours
     * <li>minutes
     * <li>seconds
     * <li>milliseconds
     * </ul>
     *
     * @return the period type
     */
    public static PeriodType yearMonthDayTime() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[15]++;
        PeriodType type = cYMDTime;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[3]++;
            type = new PeriodType(
                "YearMonthDayTime",
                new DurationFieldType[] {
                    DurationFieldType.years(), DurationFieldType.months(),
                    DurationFieldType.days(),
                    DurationFieldType.hours(), DurationFieldType.minutes(),
                    DurationFieldType.seconds(), DurationFieldType.millis(),
                },
                new int[] { 0, 1, -1, 2, 3, 4, 5, 6, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[17]++;
            cYMDTime = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[18]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[4]++;}
        return type;
    }

    /**
     * Gets a type that defines the year, month and day fields.
     * <ul>
     * <li>years
     * <li>months
     * <li>days
     * </ul>
     *
     * @return the period type
     * @since 1.1
     */
    public static PeriodType yearMonthDay() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[19]++;
        PeriodType type = cYMD;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[5]++;
            type = new PeriodType(
                "YearMonthDay",
                new DurationFieldType[] {
                    DurationFieldType.years(), DurationFieldType.months(),
                    DurationFieldType.days(),
                },
                new int[] { 0, 1, -1, 2, -1, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[21]++;
            cYMD = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[22]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[6]++;}
        return type;
    }

    /**
     * Gets a type that defines all standard fields except months.
     * <ul>
     * <li>years
     * <li>weeks
     * <li>days
     * <li>hours
     * <li>minutes
     * <li>seconds
     * <li>milliseconds
     * </ul>
     *
     * @return the period type
     */
    public static PeriodType yearWeekDayTime() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[23]++;
        PeriodType type = cYWDTime;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[7]++;
            type = new PeriodType(
                "YearWeekDayTime",
                new DurationFieldType[] {
                    DurationFieldType.years(),
                    DurationFieldType.weeks(), DurationFieldType.days(),
                    DurationFieldType.hours(), DurationFieldType.minutes(),
                    DurationFieldType.seconds(), DurationFieldType.millis(),
                },
                new int[] { 0, -1, 1, 2, 3, 4, 5, 6, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[25]++;
            cYWDTime = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[26]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[8]++;}
        return type;
    }

    /**
     * Gets a type that defines year, week and day fields.
     * <ul>
     * <li>years
     * <li>weeks
     * <li>days
     * </ul>
     *
     * @return the period type
     * @since 1.1
     */
    public static PeriodType yearWeekDay() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[27]++;
        PeriodType type = cYWD;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[9]++;
            type = new PeriodType(
                "YearWeekDay",
                new DurationFieldType[] {
                    DurationFieldType.years(),
                    DurationFieldType.weeks(), DurationFieldType.days(),
                },
                new int[] { 0, -1, 1, 2, -1, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[29]++;
            cYWD = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[30]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[10]++;}
        return type;
    }

    /**
     * Gets a type that defines all standard fields except months and weeks.
     * <ul>
     * <li>years
     * <li>days
     * <li>hours
     * <li>minutes
     * <li>seconds
     * <li>milliseconds
     * </ul>
     *
     * @return the period type
     */
    public static PeriodType yearDayTime() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[31]++;
        PeriodType type = cYDTime;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[11]++;
            type = new PeriodType(
                "YearDayTime",
                new DurationFieldType[] {
                    DurationFieldType.years(), DurationFieldType.days(),
                    DurationFieldType.hours(), DurationFieldType.minutes(),
                    DurationFieldType.seconds(), DurationFieldType.millis(),
                },
                new int[] { 0, -1, -1, 1, 2, 3, 4, 5, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[33]++;
            cYDTime = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[34]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[12]++;}
        return type;
    }

    /**
     * Gets a type that defines the year and day fields.
     * <ul>
     * <li>years
     * <li>days
     * </ul>
     *
     * @return the period type
     * @since 1.1
     */
    public static PeriodType yearDay() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[35]++;
        PeriodType type = cYD;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[13]++;
            type = new PeriodType(
                "YearDay",
                new DurationFieldType[] {
                    DurationFieldType.years(), DurationFieldType.days(),
                },
                new int[] { 0, -1, -1, 1, -1, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[37]++;
            cYD = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[38]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[14]++;}
        return type;
    }

    /**
     * Gets a type that defines all standard fields from days downwards.
     * <ul>
     * <li>days
     * <li>hours
     * <li>minutes
     * <li>seconds
     * <li>milliseconds
     * </ul>
     *
     * @return the period type
     */
    public static PeriodType dayTime() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[39]++;
        PeriodType type = cDTime;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[15]++;
            type = new PeriodType(
                "DayTime",
                new DurationFieldType[] {
                    DurationFieldType.days(),
                    DurationFieldType.hours(), DurationFieldType.minutes(),
                    DurationFieldType.seconds(), DurationFieldType.millis(),
                },
                new int[] { -1, -1, -1, 0, 1, 2, 3, 4, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[41]++;
            cDTime = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[42]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[16]++;}
        return type;
    }

    /**
     * Gets a type that defines all standard time fields.
     * <ul>
     * <li>hours
     * <li>minutes
     * <li>seconds
     * <li>milliseconds
     * </ul>
     *
     * @return the period type
     */
    public static PeriodType time() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[43]++;
        PeriodType type = cTime;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[17]++;
            type = new PeriodType(
                "Time",
                new DurationFieldType[] {
                    DurationFieldType.hours(), DurationFieldType.minutes(),
                    DurationFieldType.seconds(), DurationFieldType.millis(),
                },
                new int[] { -1, -1, -1, -1, 0, 1, 2, 3, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[45]++;
            cTime = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[46]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[18]++;}
        return type;
    }

    /**
     * Gets a type that defines just the years field.
     *
     * @return the period type
     */
    public static PeriodType years() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[47]++;
        PeriodType type = cYears;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[19]++;
            type = new PeriodType(
                "Years",
                new DurationFieldType[] { DurationFieldType.years() },
                new int[] { 0, -1, -1, -1, -1, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[49]++;
            cYears = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[50]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[20]++;}
        return type;
    }

    /**
     * Gets a type that defines just the months field.
     *
     * @return the period type
     */
    public static PeriodType months() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[51]++;
        PeriodType type = cMonths;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[21]++;
            type = new PeriodType(
                "Months",
                new DurationFieldType[] { DurationFieldType.months() },
                new int[] { -1, 0, -1, -1, -1, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[53]++;
            cMonths = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[54]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[22]++;}
        return type;
    }

    /**
     * Gets a type that defines just the weeks field.
     *
     * @return the period type
     */
    public static PeriodType weeks() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[55]++;
        PeriodType type = cWeeks;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[23]++;
            type = new PeriodType(
                "Weeks",
                new DurationFieldType[] { DurationFieldType.weeks() },
                new int[] { -1, -1, 0, -1, -1, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[57]++;
            cWeeks = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[58]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[24]++;}
        return type;
    }

    /**
     * Gets a type that defines just the days field.
     *
     * @return the period type
     */
    public static PeriodType days() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[59]++;
        PeriodType type = cDays;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[60]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[25]++;
            type = new PeriodType(
                "Days",
                new DurationFieldType[] { DurationFieldType.days() },
                new int[] { -1, -1, -1, 0, -1, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[61]++;
            cDays = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[62]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[26]++;}
        return type;
    }

    /**
     * Gets a type that defines just the hours field.
     *
     * @return the period type
     */
    public static PeriodType hours() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[63]++;
        PeriodType type = cHours;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[27]++;
            type = new PeriodType(
                "Hours",
                new DurationFieldType[] { DurationFieldType.hours() },
                new int[] { -1, -1, -1, -1, 0, -1, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[65]++;
            cHours = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[66]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[28]++;}
        return type;
    }

    /**
     * Gets a type that defines just the minutes field.
     *
     * @return the period type
     */
    public static PeriodType minutes() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[67]++;
        PeriodType type = cMinutes;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[68]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[29]++;
            type = new PeriodType(
                "Minutes",
                new DurationFieldType[] { DurationFieldType.minutes() },
                new int[] { -1, -1, -1, -1, -1, 0, -1, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[69]++;
            cMinutes = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[70]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[30]++;}
        return type;
    }

    /**
     * Gets a type that defines just the seconds field.
     *
     * @return the period type
     */
    public static PeriodType seconds() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[71]++;
        PeriodType type = cSeconds;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[72]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[31]++;
            type = new PeriodType(
                "Seconds",
                new DurationFieldType[] { DurationFieldType.seconds() },
                new int[] { -1, -1, -1, -1, -1, -1, 0, -1, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[73]++;
            cSeconds = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[74]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[32]++;}
        return type;
    }

    /**
     * Gets a type that defines just the millis field.
     *
     * @return the period type
     */
    public static PeriodType millis() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[75]++;
        PeriodType type = cMillis;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[76]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[33]++;
            type = new PeriodType(
                "Millis",
                new DurationFieldType[] { DurationFieldType.millis() },
                new int[] { -1, -1, -1, -1, -1, -1, -1, 0, }
            );
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[77]++;
            cMillis = type;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[78]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[34]++;}
        return type;
    }

    /**
     * Gets a period type that contains the duration types of the array.
     * <p>
     * Only the 8 standard duration field types are supported.
     *
     * @param types  the types to include in the array.
     * @return the period type
     * @since 1.1
     */
    public static synchronized PeriodType forFields(DurationFieldType[] types) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[79]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((types == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((types.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[35]++;
            throw new IllegalArgumentException("Types array must not be null or empty");

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[36]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[80]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[1]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < types.length) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[1]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[2]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[3]++;
}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[81]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((types[i] == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[37]++;
                throw new IllegalArgumentException("Types array must not contain null");

            } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[38]++;}
        }
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[82]++;
        Map<PeriodType, Object> cache = cTypes;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[83]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((cache.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[39]++;
            cache.put(standard(), standard());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[84]++;
            cache.put(yearMonthDayTime(), yearMonthDayTime());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[85]++;
            cache.put(yearMonthDay(), yearMonthDay());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[86]++;
            cache.put(yearWeekDayTime(), yearWeekDayTime());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[87]++;
            cache.put(yearWeekDay(), yearWeekDay());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[88]++;
            cache.put(yearDayTime(), yearDayTime());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[89]++;
            cache.put(yearDay(), yearDay());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[90]++;
            cache.put(dayTime(), dayTime());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[91]++;
            cache.put(time(), time());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[92]++;
            cache.put(years(), years());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[93]++;
            cache.put(months(), months());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[94]++;
            cache.put(weeks(), weeks());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[95]++;
            cache.put(days(), days());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[96]++;
            cache.put(hours(), hours());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[97]++;
            cache.put(minutes(), minutes());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[98]++;
            cache.put(seconds(), seconds());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[99]++;
            cache.put(millis(), millis());
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[100]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[40]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[101]++;
        PeriodType inPartType = new PeriodType(null, types, null);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[102]++;
        Object cached = cache.get(inPartType);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[103]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((cached instanceof PeriodType) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[41]++;
            return (PeriodType) cached;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[42]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[104]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((cached != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[43]++;
            throw new IllegalArgumentException("PeriodType does not support fields: " + cached);

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[44]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[105]++;
        PeriodType type = standard();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[106]++;
        List<DurationFieldType> list = new ArrayList<DurationFieldType>(Arrays.asList(types));
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[107]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.years()) == false) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[45]++;
            type = type.withYearsRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[108]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[46]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[109]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.months()) == false) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[47]++;
            type = type.withMonthsRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[110]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[48]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[111]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.weeks()) == false) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[49]++;
            type = type.withWeeksRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[112]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[50]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[113]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.days()) == false) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[51]++;
            type = type.withDaysRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[114]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[52]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[115]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.hours()) == false) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[53]++;
            type = type.withHoursRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[116]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[54]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[117]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.minutes()) == false) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[55]++;
            type = type.withMinutesRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[118]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[56]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[119]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.seconds()) == false) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[57]++;
            type = type.withSecondsRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[120]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[58]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[121]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((list.remove(DurationFieldType.millis()) == false) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[59]++;
            type = type.withMillisRemoved();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[122]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[60]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[123]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((list.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[61]++;
            cache.put(inPartType, list);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[124]++;
            throw new IllegalArgumentException("PeriodType does not support fields: " + list);

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[62]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[125]++;
        // recheck cache in case initial array order was wrong
        PeriodType checkPartType = new PeriodType(null, type.iTypes, null);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[126]++;
        PeriodType checkedType = (PeriodType) cache.get(checkPartType);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[127]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((checkedType != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[63]++;
            cache.put(checkPartType, checkedType);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[128]++;
            return checkedType;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[64]++;}
	cache.put(checkPartType, type);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[129]++;
        return type;
    }

    //-----------------------------------------------------------------------    
    /** The name of the type */
    private final String iName;
    /** The array of types */
    private final DurationFieldType[] iTypes;
    /** The array of indices */
    private final int[] iIndices;

    /**
     * Constructor.
     *
     * @param name  the name
     * @param types  the types
     * @param indices  the indices
     */
    protected PeriodType(String name, DurationFieldType[] types, int[] indices) {
        super();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[130]++;
        iName = name;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[131]++;
        iTypes = types;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[132]++;
        iIndices = indices;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[133]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the name of the period type.
     * 
     * @return the name
     */
    public String getName() {
        return iName;
    }

    /**
     * Gets the number of fields in the period type.
     * 
     * @return the number of fields
     */
    public int size() {
        return iTypes.length;
    }

    /**
     * Gets the field type by index.
     * 
     * @param index  the index to retrieve
     * @return the field type
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public DurationFieldType getFieldType(int index) {
        return iTypes[index];
    }

    /**
     * Checks whether the field specified is supported by this period.
     *
     * @param type  the type to check, may be null which returns false
     * @return true if the field is supported
     */
    public boolean isSupported(DurationFieldType type) {
        return (indexOf(type) >= 0);
    }

    /**
     * Gets the index of the field in this period.
     *
     * @param type  the type to check, may be null which returns -1
     * @return the index of -1 if not supported
     */
    public int indexOf(DurationFieldType type) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[134]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[4]++;


int CodeCoverConditionCoverageHelper_C34;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[4]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[5]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[6]++;
}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[135]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((iTypes[i] == type) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[65]++;
                return i;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[66]++;}
        }
        return -1;
    }

    /**
     * Gets a debugging to string.
     * 
     * @return a string
     */
    public String toString() {
        return "PeriodType[" + getName() + "]";
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the indexed field part of the period.
     * 
     * @param period  the period to query
     * @param index  the index to use
     * @return the value of the field, zero if unsupported
     */
    int getIndexedField(ReadablePeriod period, int index) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[136]++;
        int realIndex = iIndices[index];
        return (realIndex == -1 ? 0 : period.getValue(realIndex));
    }

    /**
     * Sets the indexed field part of the period.
     * 
     * @param period  the period to query
     * @param index  the index to use
     * @param values  the array to populate
     * @param newValue  the value to set
     * @throws UnsupportedOperationException if not supported
     */
    boolean setIndexedField(ReadablePeriod period, int index, int[] values, int newValue) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[137]++;
        int realIndex = iIndices[index];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[138]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((realIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[67]++;
            throw new UnsupportedOperationException("Field is not supported");

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[68]++;}
        values[realIndex] = newValue;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[139]++;
        return true;
    }

    /**
     * Adds to the indexed field part of the period.
     * 
     * @param period  the period to query
     * @param index  the index to use
     * @param values  the array to populate
     * @param valueToAdd  the value to add
     * @return true if the array is updated
     * @throws UnsupportedOperationException if not supported
     */
    boolean addIndexedField(ReadablePeriod period, int index, int[] values, int valueToAdd) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[140]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((valueToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[70]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[141]++;
        int realIndex = iIndices[index];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[142]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((realIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[71]++;
            throw new UnsupportedOperationException("Field is not supported");

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[72]++;}
        values[realIndex] = FieldUtils.safeAdd(values[realIndex], valueToAdd);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[143]++;
        return true;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a version of this PeriodType instance that does not support years.
     * 
     * @return a new period type that supports the original set of fields except years
     */
    public PeriodType withYearsRemoved() {
        return withFieldRemoved(0, "NoYears");
    }

    /**
     * Returns a version of this PeriodType instance that does not support months.
     * 
     * @return a new period type that supports the original set of fields except months
     */
    public PeriodType withMonthsRemoved() {
        return withFieldRemoved(1, "NoMonths");
    }

    /**
     * Returns a version of this PeriodType instance that does not support weeks.
     * 
     * @return a new period type that supports the original set of fields except weeks
     */
    public PeriodType withWeeksRemoved() {
        return withFieldRemoved(2, "NoWeeks");
    }

    /**
     * Returns a version of this PeriodType instance that does not support days.
     * 
     * @return a new period type that supports the original set of fields except days
     */
    public PeriodType withDaysRemoved() {
        return withFieldRemoved(3, "NoDays");
    }

    /**
     * Returns a version of this PeriodType instance that does not support hours.
     * 
     * @return a new period type that supports the original set of fields except hours
     */
    public PeriodType withHoursRemoved() {
        return withFieldRemoved(4, "NoHours");
    }

    /**
     * Returns a version of this PeriodType instance that does not support minutes.
     * 
     * @return a new period type that supports the original set of fields except minutes
     */
    public PeriodType withMinutesRemoved() {
        return withFieldRemoved(5, "NoMinutes");
    }

    /**
     * Returns a version of this PeriodType instance that does not support seconds.
     * 
     * @return a new period type that supports the original set of fields except seconds
     */
    public PeriodType withSecondsRemoved() {
        return withFieldRemoved(6, "NoSeconds");
    }

    /**
     * Returns a version of this PeriodType instance that does not support milliseconds.
     * 
     * @return a new period type that supports the original set of fields except milliseconds
     */
    public PeriodType withMillisRemoved() {
        return withFieldRemoved(7, "NoMillis");
    }

    /**
     * Removes the field specified by indices index.
     * 
     * @param indicesIndex  the index to remove
     * @param name  the name addition
     * @return the new type
     */
    private PeriodType withFieldRemoved(int indicesIndex, String name) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[144]++;
        int fieldIndex = iIndices[indicesIndex];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[145]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((fieldIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[73]++;
            return this;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[74]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[146]++;
        
        DurationFieldType[] types = new DurationFieldType[size() - 1];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[147]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[7]++;


int CodeCoverConditionCoverageHelper_C40;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i < iTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[7]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[8]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[9]++;
}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[148]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i < fieldIndex) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[75]++;
                types[i] = iTypes[i];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[149]++;

            } else {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[76]++;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[150]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((i > fieldIndex) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[77]++;
                types[i - 1] = iTypes[i];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[151]++;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[78]++;}
}
        }
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[152]++;
        
        int[] indices = new int[8];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[153]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[10]++;


int CodeCoverConditionCoverageHelper_C43;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((i < indices.length) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[10]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[11]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[12]++;
}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[154]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i < indicesIndex) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[79]++;
                indices[i] = iIndices[i];
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[155]++;

            } else {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[80]++;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[156]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((i > indicesIndex) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[81]++;
                indices[i] = (iIndices[i] == -1 ? -1 : iIndices[i] - 1);
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[157]++;

            } else {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[82]++;
                indices[i] = -1;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[158]++;
            }
}
        }
        return new PeriodType(getName() + name, types, indices);
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this type to another object.
     * To be equal, the object must be a PeriodType with the same set of fields.
     * 
     * @param obj  the object to compare to
     * @return true if equal
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[159]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[83]++;
            return true;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[84]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[160]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((obj instanceof PeriodType == false) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.branches[86]++;}
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[161]++;
        PeriodType other = (PeriodType) obj;
        return (Arrays.equals(iTypes, other.iTypes));
    }

    /**
     * Returns a hashcode based on the field types.
     * 
     * @return a suitable hashcode
     */
    public int hashCode() {
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[162]++;
        int hash = 0;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[163]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[13]++;


int CodeCoverConditionCoverageHelper_C48;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((i < iTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[13]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[14]--;
  CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.loops[15]++;
}
            hash += iTypes[i].hashCode();
CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h.statements[164]++;
        }
        return hash;
    }

}

class CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h ());
  }
    public static long[] statements = new long[165];
    public static long[] branches = new long[87];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[49];
  static {
    final String SECTION_NAME = "org.joda.time.PeriodType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 48; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$o3g3a0goeky16xgmh70d84h () {
    super("org.joda.time.PeriodType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 164; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 86; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 48; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.PeriodType.java");
      for (int i = 1; i <= 164; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 86; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 48; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

