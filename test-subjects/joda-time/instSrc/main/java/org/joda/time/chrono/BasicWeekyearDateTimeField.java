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
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

/**
 * Provides time calculations for the week of the weekyear component of time.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.1, refactored from GJWeekyearDateTimeField
 */
final class BasicWeekyearDateTimeField extends ImpreciseDateTimeField {
  static {
    CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.ping();
  }

    
    private static final long serialVersionUID = 6215066916806820644L;
  static {
    CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[1]++;
  }

    private static final long WEEK_53 = (53L - 1) * DateTimeConstants.MILLIS_PER_WEEK;
  static {
    CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[2]++;
  }

    private final BasicChronology iChronology;

    /**
     * Restricted constructor
     */
    BasicWeekyearDateTimeField(BasicChronology chronology) {
        super(DateTimeFieldType.weekyear(), chronology.getAverageMillisPerYear());
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[3]++;
        iChronology = chronology;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[4]++;
    }

    public boolean isLenient() {
        return false;
    }

    /**
     * Get the Year of a week based year component of the specified time instant.
     * 
     * @see org.joda.time.DateTimeField#get
     * @param instant  the time instant in millis to query.
     * @return the year extracted from the input.
     */
    public int get(long instant) {
        return iChronology.getWeekyear(instant);
    }

    /**
     * Add the specified years to the specified time instant.
     * 
     * @see org.joda.time.DateTimeField#add
     * @param instant  the time instant in millis to update.
     * @param years  the years to add (can be negative).
     * @return the updated time instant.
     */
    public long add(long instant, int years) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((years == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[1]++;
            return instant;

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[2]++;}
        return set(instant, get(instant) + years);
    }

    public long add(long instant, long value) {
        return add(instant, FieldUtils.safeToInt(value));
    }

    /**
     * Add to the year component of the specified time instant
     * wrapping around within that component if necessary.
     * 
     * @see org.joda.time.DateTimeField#addWrapField
     * @param instant  the time instant in millis to update.
     * @param years  the years to add (can be negative).
     * @return the updated time instant.
     */
    public long addWrapField(long instant, int years) {
        return add(instant, years);
    }

    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((minuendInstant < subtrahendInstant) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[3]++;
            return -getDifference(subtrahendInstant, minuendInstant);

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[4]++;}
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[7]++;

        int minuendWeekyear = get(minuendInstant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[8]++;
        int subtrahendWeekyear = get(subtrahendInstant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[9]++;

        long minuendRem = remainder(minuendInstant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[10]++;
        long subtrahendRem = remainder(subtrahendInstant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;

        // Balance leap weekyear differences on remainders.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((subtrahendRem >= WEEK_53) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((iChronology.getWeeksInYear(minuendWeekyear) <= 52) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[5]++;
            subtrahendRem -= DateTimeConstants.MILLIS_PER_WEEK;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[12]++;

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[6]++;}
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[13]++;

        int difference = minuendWeekyear - subtrahendWeekyear;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((minuendRem < subtrahendRem) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[7]++;
            difference--;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[15]++;

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[8]++;}
        return difference;
    }

    /**
     * Set the Year of a week based year component of the specified time instant.
     *
     * @see org.joda.time.DateTimeField#set
     * @param instant  the time instant in millis to update.
     * @param year  the year (-9999,9999) to set the date to.
     * @return the updated DateTime.
     * @throws IllegalArgumentException  if year is invalid.
     */
    public long set(long instant, int year) {
        FieldUtils.verifyValueBounds(this, Math.abs(year),
                                     iChronology.getMinYear(), iChronology.getMaxYear());
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[16]++;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[17]++;
        //
        // Do nothing if no real change is requested.
        //
        int thisWeekyear = get( instant );
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thisWeekyear == year) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false) ) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[9]++;
            return instant;

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[10]++;}
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[19]++;
        //
        // Calculate the DayOfWeek (to be preserved).
        //
        int thisDow = iChronology.getDayOfWeek(instant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[20]++;
        //
        // Calculate the maximum weeks in the target year.
        //
        int weeksInFromYear = iChronology.getWeeksInYear( thisWeekyear );
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[21]++;
        int weeksInToYear = iChronology.getWeeksInYear( year );
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[22]++;
        int maxOutWeeks = (weeksInToYear < weeksInFromYear) ?
            weeksInToYear : weeksInFromYear;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[23]++;
        //
        // Get the current week of the year. This will be preserved in
        // the output unless it is greater than the maximum possible
        // for the target weekyear.  In that case it is adjusted
        // to the maximum possible.
        //
        int setToWeek = iChronology.getWeekOfWeekyear(instant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((setToWeek > maxOutWeeks) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false) ) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[11]++;
            setToWeek = maxOutWeeks;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[25]++;

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[12]++;}
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[26]++;
        //
        // Get a wroking copy of the current date-time.
        // This can be a convenience for debugging.
        //
        long workInstant = instant; // Get a copy
        //
        // Attempt to get close to the proper weekyear.
        // Note - we cannot currently call ourself, so we just call
        // set for the year.  This at least gets us close.
        //
        workInstant = iChronology.setYear( workInstant, year );
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[27]++;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[28]++;
        //
        // Calculate the weekyear number for the get close to value
        // (which might not be equal to the year just set).
        //
        int workWoyYear = get( workInstant );
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;

        //
        // At most we are off by one year, which can be "fixed" by
        // adding/subtracting a week.
        //
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((workWoyYear < year) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false) ) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[13]++;
            workInstant += DateTimeConstants.MILLIS_PER_WEEK;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[30]++;

        } else {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[14]++;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[31]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((workWoyYear > year) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false) ) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[15]++;
            workInstant -= DateTimeConstants.MILLIS_PER_WEEK;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[32]++;

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[16]++;}
}
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[33]++;
        //
        // Set the proper week in the current weekyear.
        //

        // BEGIN: possible set WeekOfWeekyear logic.
        int currentWoyWeek = iChronology.getWeekOfWeekyear(workInstant);
        // No range check required (we already know it is OK).
        workInstant = workInstant + (setToWeek - currentWoyWeek)
            * (long)DateTimeConstants.MILLIS_PER_WEEK;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[34]++;
        // END: possible set WeekOfWeekyear logic.

        //
        // Reset DayOfWeek to previous value.
        //
        // Note: This works fine, but it ideally shouldn't invoke other
        // fields from within a field.
        workInstant = iChronology.dayOfWeek().set( workInstant, thisDow );
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[35]++;
        //
        // Return result.
        //
        return workInstant;
    }

    public DurationField getRangeDurationField() {
        return null;
    }

    public boolean isLeap(long instant) {
        return iChronology.getWeeksInYear(iChronology.getWeekyear(instant)) > 52;
    }

    public int getLeapAmount(long instant) {
        return iChronology.getWeeksInYear(iChronology.getWeekyear(instant)) - 52;
    }

    public DurationField getLeapDurationField() {
        return iChronology.weeks();
    }

    public int getMinimumValue() {
        return iChronology.getMinYear();
    }

    public int getMaximumValue() {
        return iChronology.getMaxYear();
    }

    public long roundFloor(long instant) {
        // Note: This works fine, but it ideally shouldn't invoke other
        // fields from within a field.
        instant = iChronology.weekOfWeekyear().roundFloor(instant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[36]++;
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[37]++;
        int wow = iChronology.getWeekOfWeekyear(instant);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((wow > 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[17]++;
            instant -= ((long) DateTimeConstants.MILLIS_PER_WEEK) * (wow - 1);
CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.statements[39]++;

        } else {
  CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d.branches[18]++;}
        return instant;
    }

    public long remainder(long instant) {
        return instant - roundFloor(instant);
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
        return iChronology.weekyear();
    }
}

class CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicWeekyearDateTimeField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$8diye1mp4gle8zmhmy87ktse15d3yvjnonrwlbbqzyuo371d () {
    super("org.joda.time.chrono.BasicWeekyearDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.joda.time.chrono.BasicWeekyearDateTimeField.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
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

