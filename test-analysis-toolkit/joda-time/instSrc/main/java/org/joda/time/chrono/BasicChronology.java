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

import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.PreciseDateTimeField;
import org.joda.time.field.PreciseDurationField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.ZeroIsMaxDateTimeField;

/**
 * Abstract implementation for calendar systems that use a typical
 * day/month/year/leapYear model.
 * Most of the utility methods required by subclasses are package-private,
 * reflecting the intention that they be defined in the same package.
 * <p>
 * BasicChronology is thread-safe and immutable, and all subclasses must
 * be as well.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @author Guy Allard
 * @since 1.2, renamed from BaseGJChronology
 */
abstract class BasicChronology extends AssembledChronology {
  static {
    CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = 8283225332206808863L;
  static {
    CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[1]++;
  }

    private static final DurationField cMillisField;
    private static final DurationField cSecondsField;
    private static final DurationField cMinutesField;
    private static final DurationField cHoursField;
    private static final DurationField cHalfdaysField;
    private static final DurationField cDaysField;
    private static final DurationField cWeeksField;

    private static final DateTimeField cMillisOfSecondField;
    private static final DateTimeField cMillisOfDayField;
    private static final DateTimeField cSecondOfMinuteField;
    private static final DateTimeField cSecondOfDayField;
    private static final DateTimeField cMinuteOfHourField;
    private static final DateTimeField cMinuteOfDayField;
    private static final DateTimeField cHourOfDayField;
    private static final DateTimeField cHourOfHalfdayField;
    private static final DateTimeField cClockhourOfDayField;
    private static final DateTimeField cClockhourOfHalfdayField;
    private static final DateTimeField cHalfdayOfDayField;

    static {
        cMillisField = MillisDurationField.INSTANCE;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[2]++;
        cSecondsField = new PreciseDurationField
            (DurationFieldType.seconds(), DateTimeConstants.MILLIS_PER_SECOND);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[3]++;
        cMinutesField = new PreciseDurationField
            (DurationFieldType.minutes(), DateTimeConstants.MILLIS_PER_MINUTE);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[4]++;
        cHoursField = new PreciseDurationField
            (DurationFieldType.hours(), DateTimeConstants.MILLIS_PER_HOUR);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[5]++;
        cHalfdaysField = new PreciseDurationField
            (DurationFieldType.halfdays(), DateTimeConstants.MILLIS_PER_DAY / 2);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[6]++;
        cDaysField = new PreciseDurationField
            (DurationFieldType.days(), DateTimeConstants.MILLIS_PER_DAY);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[7]++;
        cWeeksField = new PreciseDurationField
            (DurationFieldType.weeks(), DateTimeConstants.MILLIS_PER_WEEK);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[8]++;

        cMillisOfSecondField = new PreciseDateTimeField
            (DateTimeFieldType.millisOfSecond(), cMillisField, cSecondsField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[9]++;

        cMillisOfDayField = new PreciseDateTimeField
            (DateTimeFieldType.millisOfDay(), cMillisField, cDaysField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[10]++;
             
        cSecondOfMinuteField = new PreciseDateTimeField
            (DateTimeFieldType.secondOfMinute(), cSecondsField, cMinutesField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[11]++;

        cSecondOfDayField = new PreciseDateTimeField
            (DateTimeFieldType.secondOfDay(), cSecondsField, cDaysField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[12]++;

        cMinuteOfHourField = new PreciseDateTimeField
            (DateTimeFieldType.minuteOfHour(), cMinutesField, cHoursField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[13]++;

        cMinuteOfDayField = new PreciseDateTimeField
            (DateTimeFieldType.minuteOfDay(), cMinutesField, cDaysField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[14]++;

        cHourOfDayField = new PreciseDateTimeField
            (DateTimeFieldType.hourOfDay(), cHoursField, cDaysField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[15]++;

        cHourOfHalfdayField = new PreciseDateTimeField
            (DateTimeFieldType.hourOfHalfday(), cHoursField, cHalfdaysField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[16]++;

        cClockhourOfDayField = new ZeroIsMaxDateTimeField
            (cHourOfDayField, DateTimeFieldType.clockhourOfDay());
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[17]++;

        cClockhourOfHalfdayField = new ZeroIsMaxDateTimeField
            (cHourOfHalfdayField, DateTimeFieldType.clockhourOfHalfday());
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[18]++;

        cHalfdayOfDayField = new HalfdayField();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[19]++;
    }

    private static final int CACHE_SIZE = 1 << 10;
  static {
    CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[20]++;
  }
    private static final int CACHE_MASK = CACHE_SIZE - 1;
  static {
    CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[21]++;
  }

    private transient final YearInfo[] iYearInfoCache = new YearInfo[CACHE_SIZE];
  {
    CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[22]++;
  }

    private final int iMinDaysInFirstWeek;

    BasicChronology(Chronology base, Object param, int minDaysInFirstWeek) {
        super(base, param);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[23]++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[24]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((minDaysInFirstWeek < 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((minDaysInFirstWeek > 7) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[1]++;
            throw new IllegalArgumentException
                ("Invalid min days in first week: " + minDaysInFirstWeek);

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[2]++;}

        iMinDaysInFirstWeek = minDaysInFirstWeek;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[25]++;
    }

    public DateTimeZone getZone() {
        Chronology base;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[26]++;
        if ((base = getBase()) != null) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[3]++;
            return base.getZone();

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[4]++;}
        return DateTimeZone.UTC;
    }

    public long getDateTimeMillis(
            int year, int monthOfYear, int dayOfMonth, int millisOfDay)
            throws IllegalArgumentException {
        Chronology base;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[27]++;
        if ((base = getBase()) != null) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[5]++;
            return base.getDateTimeMillis(year, monthOfYear, dayOfMonth, millisOfDay);

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[6]++;}

        FieldUtils.verifyValueBounds
            (DateTimeFieldType.millisOfDay(), millisOfDay, 0, DateTimeConstants.MILLIS_PER_DAY);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[28]++;
        return getDateMidnightMillis(year, monthOfYear, dayOfMonth) + millisOfDay;
    }

    public long getDateTimeMillis(
            int year, int monthOfYear, int dayOfMonth,
            int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond)
            throws IllegalArgumentException {
        Chronology base;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[29]++;
        if ((base = getBase()) != null) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[7]++;
            return base.getDateTimeMillis(year, monthOfYear, dayOfMonth,
                                          hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[8]++;}

        FieldUtils.verifyValueBounds(DateTimeFieldType.hourOfDay(), hourOfDay, 0, 23);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[30]++;
        FieldUtils.verifyValueBounds(DateTimeFieldType.minuteOfHour(), minuteOfHour, 0, 59);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[31]++;
        FieldUtils.verifyValueBounds(DateTimeFieldType.secondOfMinute(), secondOfMinute, 0, 59);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[32]++;
        FieldUtils.verifyValueBounds(DateTimeFieldType.millisOfSecond(), millisOfSecond, 0, 999);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[33]++;

        return getDateMidnightMillis(year, monthOfYear, dayOfMonth)
            + hourOfDay * DateTimeConstants.MILLIS_PER_HOUR
            + minuteOfHour * DateTimeConstants.MILLIS_PER_MINUTE
            + secondOfMinute * DateTimeConstants.MILLIS_PER_SECOND
            + millisOfSecond;
    }

    public int getMinimumDaysInFirstWeek() {
        return iMinDaysInFirstWeek;
    }

    /**
     * Checks if this chronology instance equals another.
     * 
     * @param obj  the object to compare to
     * @return true if equal
     * @since 1.6
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * A suitable hash code for the chronology.
     * 
     * @return the hash code
     * @since 1.6
     */
    public int hashCode() {
        return getClass().getName().hashCode() * 11 + getZone().hashCode() + getMinimumDaysInFirstWeek();
    }

    // Output
    //-----------------------------------------------------------------------
    /**
     * Gets a debugging toString.
     * 
     * @return a debugging string
     */
    public String toString() {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[34]++;
        StringBuffer sb = new StringBuffer(60);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[35]++;
        String name = getClass().getName();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[36]++;
        int index = name.lastIndexOf('.');
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[9]++;
            name = name.substring(index + 1);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[38]++;

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[10]++;}
        sb.append(name);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[39]++;
        sb.append('[');
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[40]++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[41]++;
        DateTimeZone zone = getZone();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[11]++;
            sb.append(zone.getID());
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[43]++;

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[12]++;}
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getMinimumDaysInFirstWeek() != 4) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[13]++;
            sb.append(",mdfw=");
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[45]++;
            sb.append(getMinimumDaysInFirstWeek());
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[46]++;

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[14]++;}
        sb.append(']');
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[47]++;
        return sb.toString();
    }

    protected void assemble(Fields fields) {
        // First copy fields that are the same for all Gregorian and Julian
        // chronologies.

        fields.millis = cMillisField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[48]++;
        fields.seconds = cSecondsField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[49]++;
        fields.minutes = cMinutesField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[50]++;
        fields.hours = cHoursField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[51]++;
        fields.halfdays = cHalfdaysField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[52]++;
        fields.days = cDaysField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[53]++;
        fields.weeks = cWeeksField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[54]++;

        fields.millisOfSecond = cMillisOfSecondField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[55]++;
        fields.millisOfDay = cMillisOfDayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[56]++;
        fields.secondOfMinute = cSecondOfMinuteField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[57]++;
        fields.secondOfDay = cSecondOfDayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[58]++;
        fields.minuteOfHour = cMinuteOfHourField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[59]++;
        fields.minuteOfDay = cMinuteOfDayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[60]++;
        fields.hourOfDay = cHourOfDayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[61]++;
        fields.hourOfHalfday = cHourOfHalfdayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[62]++;
        fields.clockhourOfDay = cClockhourOfDayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[63]++;
        fields.clockhourOfHalfday = cClockhourOfHalfdayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[64]++;
        fields.halfdayOfDay = cHalfdayOfDayField;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[65]++;

        // Now create fields that have unique behavior for Gregorian and Julian
        // chronologies.

        fields.year = new BasicYearDateTimeField(this);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[66]++;
        fields.yearOfEra = new GJYearOfEraDateTimeField(fields.year, this);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[67]++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[68]++;

        // Define one-based centuryOfEra and yearOfCentury.
        DateTimeField field = new OffsetDateTimeField(
            fields.yearOfEra, 99);
        fields.centuryOfEra = new DividedDateTimeField(
            field, DateTimeFieldType.centuryOfEra(), 100);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[69]++;
        
        field = new RemainderDateTimeField(
            (DividedDateTimeField) fields.centuryOfEra);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[70]++;
        fields.yearOfCentury = new OffsetDateTimeField(
            field, DateTimeFieldType.yearOfCentury(), 1);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[71]++;

        fields.era = new GJEraDateTimeField(this);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[72]++;
        fields.dayOfWeek = new GJDayOfWeekDateTimeField(this, fields.days);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[73]++;
        fields.dayOfMonth = new BasicDayOfMonthDateTimeField(this, fields.days);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[74]++;
        fields.dayOfYear = new BasicDayOfYearDateTimeField(this, fields.days);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[75]++;
        fields.monthOfYear = new GJMonthOfYearDateTimeField(this);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[76]++;
        fields.weekyear = new BasicWeekyearDateTimeField(this);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[77]++;
        fields.weekOfWeekyear = new BasicWeekOfWeekyearDateTimeField(this, fields.weeks);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[78]++;
        
        field = new RemainderDateTimeField(
            fields.weekyear, DateTimeFieldType.weekyearOfCentury(), 100);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[79]++;
        fields.weekyearOfCentury = new OffsetDateTimeField(
            field, DateTimeFieldType.weekyearOfCentury(), 1);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[80]++;
        
        // The remaining (imprecise) durations are available from the newly
        // created datetime fields.

        fields.years = fields.year.getDurationField();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[81]++;
        fields.centuries = fields.centuryOfEra.getDurationField();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[82]++;
        fields.months = fields.monthOfYear.getDurationField();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[83]++;
        fields.weekyears = fields.weekyear.getDurationField();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[84]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the number of days in the year.
     *
     * @return 366
     */
    int getDaysInYearMax() {
        return 366;
    }

    /**
     * Get the number of days in the year.
     *
     * @param year  the year to use
     * @return 366 if a leap year, otherwise 365
     */
    int getDaysInYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    /**
     * Get the number of weeks in the year.
     *
     * @param year  the year to use
     * @return number of weeks in the year
     */
    int getWeeksInYear(int year) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[85]++;
        long firstWeekMillis1 = getFirstWeekOfYearMillis(year);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[86]++;
        long firstWeekMillis2 = getFirstWeekOfYearMillis(year + 1);
        return (int) ((firstWeekMillis2 - firstWeekMillis1) / DateTimeConstants.MILLIS_PER_WEEK);
    }

    /**
     * Get the millis for the first week of a year.
     *
     * @param year  the year to use
     * @return millis
     */
    long getFirstWeekOfYearMillis(int year) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[87]++;
        long jan1millis = getYearMillis(year);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[88]++;
        int jan1dayOfWeek = getDayOfWeek(jan1millis);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[89]++;
int CodeCoverConditionCoverageHelper_C8;
        
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((jan1dayOfWeek > (8 - iMinDaysInFirstWeek)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[15]++;
            // First week is end of previous year because it doesn't have enough days.
            return jan1millis + (8 - jan1dayOfWeek)
                * (long)DateTimeConstants.MILLIS_PER_DAY;

        } else {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[16]++;
            // First week is start of this year because it has enough days.
            return jan1millis - (jan1dayOfWeek - 1)
                * (long)DateTimeConstants.MILLIS_PER_DAY;
        }
    }

    /**
     * Get the milliseconds for the start of a year.
     *
     * @param year The year to use.
     * @return millis from 1970-01-01T00:00:00Z
     */
    long getYearMillis(int year) {
        return getYearInfo(year).iFirstDayMillis;
    }

    /**
     * Get the milliseconds for the start of a month.
     *
     * @param year The year to use.
     * @param month The month to use
     * @return millis from 1970-01-01T00:00:00Z
     */
    long getYearMonthMillis(int year, int month) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[90]++;
        long millis = getYearMillis(year);
        millis += getTotalMillisByYearMonth(year, month);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[91]++;
        return millis;
    }

    /**
     * Get the milliseconds for a particular date.
     *
     * @param year The year to use.
     * @param month The month to use
     * @param dayOfMonth The day of the month to use
     * @return millis from 1970-01-01T00:00:00Z
     */
    long getYearMonthDayMillis(int year, int month, int dayOfMonth) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[92]++;
        long millis = getYearMillis(year);
        millis += getTotalMillisByYearMonth(year, month);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[93]++;
        return millis + (dayOfMonth - 1) * (long)DateTimeConstants.MILLIS_PER_DAY;
    }
    
    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     */
    int getYear(long instant) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[94]++;
        // Get an initial estimate of the year, and the millis value that
        // represents the start of that year. Then verify estimate and fix if
        // necessary.

        // Initial estimate uses values divided by two to avoid overflow.
        long unitMillis = getAverageMillisPerYearDividedByTwo();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[95]++;
        long i2 = (instant >> 1) + getApproxMillisAtEpochDividedByTwo();
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[96]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i2 < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[17]++;
            i2 = i2 - unitMillis + 1;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[97]++;

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[18]++;}
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[98]++;
        int year = (int) (i2 / unitMillis);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[99]++;

        long yearStart = getYearMillis(year);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[100]++;
        long diff = instant - yearStart;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[101]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((diff < 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[19]++;
            year--;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[102]++;

        } else {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[20]++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[103]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((diff >= DateTimeConstants.MILLIS_PER_DAY * 365L) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[21]++;
            // One year may need to be added to fix estimate.
            long oneYear;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[104]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isLeapYear(year)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[23]++;
                oneYear = DateTimeConstants.MILLIS_PER_DAY * 366L;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[105]++;

            } else {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[24]++;
                oneYear = DateTimeConstants.MILLIS_PER_DAY * 365L;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[106]++;
            }

            yearStart += oneYear;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[107]++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[108]++;
int CodeCoverConditionCoverageHelper_C13;

            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((yearStart <= instant) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[25]++;
                // Didn't go too far, so actually add one year.
                year++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[109]++;

            } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[22]++;}
}

        return year;
    }

    /**
     * @param millis from 1970-01-01T00:00:00Z
     */
    int getMonthOfYear(long millis) {
        return getMonthOfYear(millis, getYear(millis));
    }

    /**
     * @param millis from 1970-01-01T00:00:00Z
     * @param year precalculated year of millis
     */
    abstract int getMonthOfYear(long millis, int year);

    /**
     * @param millis from 1970-01-01T00:00:00Z
     */
    int getDayOfMonth(long millis) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[110]++;
        int year = getYear(millis);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[111]++;
        int month = getMonthOfYear(millis, year);
        return getDayOfMonth(millis, year, month);
    }

    /**
     * @param millis from 1970-01-01T00:00:00Z
     * @param year precalculated year of millis
     */
    int getDayOfMonth(long millis, int year) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[112]++;
        int month = getMonthOfYear(millis, year);
        return getDayOfMonth(millis, year, month);
    }

    /**
     * @param millis from 1970-01-01T00:00:00Z
     * @param year precalculated year of millis
     * @param month precalculated month of millis
     */
    int getDayOfMonth(long millis, int year, int month) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[113]++;
        long dateMillis = getYearMillis(year);
        dateMillis += getTotalMillisByYearMonth(year, month);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[114]++;
        return (int) ((millis - dateMillis) / DateTimeConstants.MILLIS_PER_DAY) + 1;
    }

    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     */
    int getDayOfYear(long instant) {
        return getDayOfYear(instant, getYear(instant));
    }

    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     * @param year precalculated year of millis
     */
    int getDayOfYear(long instant, int year) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[115]++;
        long yearStart = getYearMillis(year);
        return (int) ((instant - yearStart) / DateTimeConstants.MILLIS_PER_DAY) + 1;
    }

    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     */
    int getWeekyear(long instant) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[116]++;
        int year = getYear(instant);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[117]++;
        int week = getWeekOfWeekyear(instant, year);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[118]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((week == 1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[27]++;
            return getYear(instant + DateTimeConstants.MILLIS_PER_WEEK);

        } else {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[28]++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[119]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((week > 51) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[29]++;
            return getYear(instant - (2 * DateTimeConstants.MILLIS_PER_WEEK));

        } else {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[30]++;
            return year;
        }
}
    }

    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     */
    int getWeekOfWeekyear(long instant) {
        return getWeekOfWeekyear(instant, getYear(instant));
    }

    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     * @param year precalculated year of millis
     */
    int getWeekOfWeekyear(long instant, int year) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[120]++;
        long firstWeekMillis1 = getFirstWeekOfYearMillis(year);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[121]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((instant < firstWeekMillis1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[31]++;
            return getWeeksInYear(year - 1);

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[32]++;}
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[122]++;
        long firstWeekMillis2 = getFirstWeekOfYearMillis(year + 1);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[123]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((instant >= firstWeekMillis2) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[33]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[34]++;}
        return (int) ((instant - firstWeekMillis1) / DateTimeConstants.MILLIS_PER_WEEK) + 1;
    }

    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     */
    int getDayOfWeek(long instant) {
        // 1970-01-01 is day of week 4, Thursday.

        long daysSince19700101;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[124]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((instant >= 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[35]++;
            daysSince19700101 = instant / DateTimeConstants.MILLIS_PER_DAY;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[125]++;

        } else {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[36]++;
            daysSince19700101 = (instant - (DateTimeConstants.MILLIS_PER_DAY - 1))
                / DateTimeConstants.MILLIS_PER_DAY;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[126]++;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[127]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((daysSince19700101 < -3) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[37]++;
                return 7 + (int) ((daysSince19700101 + 4) % 7);

            } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[38]++;}
        }

        return 1 + (int) ((daysSince19700101 + 3) % 7);
    }

    /**
     * @param instant millis from 1970-01-01T00:00:00Z
     */
    int getMillisOfDay(long instant) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[128]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((instant >= 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[39]++;
            return (int) (instant % DateTimeConstants.MILLIS_PER_DAY);

        } else {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[40]++;
            return (DateTimeConstants.MILLIS_PER_DAY - 1)
                + (int) ((instant + 1) % DateTimeConstants.MILLIS_PER_DAY);
        }
    }

    /**
     * Gets the maximum number of days in any month.
     * 
     * @return 31
     */
    int getDaysInMonthMax() {
        return 31;
    }

    /**
     * Gets the maximum number of days in the month specified by the instant.
     * 
     * @param instant  millis from 1970-01-01T00:00:00Z
     * @return the maximum number of days in the month
     */
    int getDaysInMonthMax(long instant) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[129]++;
        int thisYear = getYear(instant);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[130]++;
        int thisMonth = getMonthOfYear(instant, thisYear);
        return getDaysInYearMonth(thisYear, thisMonth);
    }

    /**
     * Gets the maximum number of days in the month specified by the instant.
     * The value represents what the user is trying to set, and can be
     * used to optimise this method.
     * 
     * @param instant  millis from 1970-01-01T00:00:00Z
     * @param value  the value being set
     * @return the maximum number of days in the month
     */
    int getDaysInMonthMaxForSet(long instant, int value) {
        return getDaysInMonthMax(instant);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the milliseconds for a date at midnight.
     * 
     * @param year  the year
     * @param monthOfYear  the month
     * @param dayOfMonth  the day
     * @return the milliseconds
     */
    long getDateMidnightMillis(int year, int monthOfYear, int dayOfMonth) {
        FieldUtils.verifyValueBounds(DateTimeFieldType.year(), year, getMinYear(), getMaxYear());
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[131]++;
        FieldUtils.verifyValueBounds(DateTimeFieldType.monthOfYear(), monthOfYear, 1, getMaxMonth(year));
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[132]++;
        FieldUtils.verifyValueBounds(DateTimeFieldType.dayOfMonth(), dayOfMonth, 1, getDaysInYearMonth(year, monthOfYear));
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[133]++;
        return getYearMonthDayMillis(year, monthOfYear, dayOfMonth);
    }

    /**
     * Gets the difference between the two instants in years.
     * 
     * @param minuendInstant  the first instant
     * @param subtrahendInstant  the second instant
     * @return the difference
     */
    abstract long getYearDifference(long minuendInstant, long subtrahendInstant);

    /**
     * Is the specified year a leap year?
     * 
     * @param year  the year to test
     * @return true if leap
     */
    abstract boolean isLeapYear(int year);

    /**
     * Gets the number of days in the specified month and year.
     * 
     * @param year  the year
     * @param month  the month
     * @return the number of days
     */
    abstract int getDaysInYearMonth(int year, int month);

    /**
     * Gets the maximum days in the specified month.
     * 
     * @param month  the month
     * @return the max days
     */
    abstract int getDaysInMonthMax(int month);

    /**
     * Gets the total number of millis elapsed in this year at the start
     * of the specified month, such as zero for month 1.
     * 
     * @param year  the year
     * @param month  the month
     * @return the elapsed millis at the start of the month
     */
    abstract long getTotalMillisByYearMonth(int year, int month);

    /**
     * Gets the millisecond value of the first day of the year.
     * 
     * @return the milliseconds for the first of the year
     */
    abstract long calculateFirstDayOfYearMillis(int year);

    /**
     * Gets the minimum supported year.
     * 
     * @return the year
     */
    abstract int getMinYear();

    /**
     * Gets the maximum supported year.
     * 
     * @return the year
     */
    abstract int getMaxYear();

    /**
     * Gets the maximum month for the specified year.
     * This implementation calls getMaxMonth().
     * 
     * @param year  the year
     * @return the maximum month value
     */
    int getMaxMonth(int year) {
        return getMaxMonth();
    }

    /**
     * Gets the maximum number of months.
     * 
     * @return 12
     */
    int getMaxMonth() {
        return 12;
    }

    /**
     * Gets an average value for the milliseconds per year.
     * 
     * @return the millis per year
     */
    abstract long getAverageMillisPerYear();

    /**
     * Gets an average value for the milliseconds per year, divided by two.
     * 
     * @return the millis per year divided by two
     */
    abstract long getAverageMillisPerYearDividedByTwo();

    /**
     * Gets an average value for the milliseconds per month.
     * 
     * @return the millis per month
     */
    abstract long getAverageMillisPerMonth();

    /**
     * Returns a constant representing the approximate number of milliseconds
     * elapsed from year 0 of this chronology, divided by two. This constant
     * <em>must</em> be defined as:
     * <pre>
     *    (yearAtEpoch * averageMillisPerYear + millisOfYearAtEpoch) / 2
     * </pre>
     * where epoch is 1970-01-01 (Gregorian).
     */
    abstract long getApproxMillisAtEpochDividedByTwo();

    /**
     * Sets the year from an instant and year.
     * 
     * @param instant  millis from 1970-01-01T00:00:00Z
     * @param year  the year to set
     * @return the updated millis
     */
    abstract long setYear(long instant, int year);

    //-----------------------------------------------------------------------
    // Although accessed by multiple threads, this method doesn't need to be synchronized.
    private YearInfo getYearInfo(int year) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[134]++;
        YearInfo info = iYearInfoCache[year & CACHE_MASK];
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[135]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((info.iYear != year) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[41]++;
            info = new YearInfo(year, calculateFirstDayOfYearMillis(year));
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[136]++;
            iYearInfoCache[year & CACHE_MASK] = info;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[137]++;

        } else {
  CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.branches[42]++;}
        return info;
    }

    private static class HalfdayField extends PreciseDateTimeField {
        private static final long serialVersionUID = 581601443656929254L;

        HalfdayField() {
            super(DateTimeFieldType.halfdayOfDay(), cHalfdaysField, cDaysField);
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[138]++;
        }

        public String getAsText(int fieldValue, Locale locale) {
            return GJLocaleSymbols.forLocale(locale).halfdayValueToText(fieldValue);
        }

        public long set(long millis, String text, Locale locale) {
            return set(millis, GJLocaleSymbols.forLocale(locale).halfdayTextToValue(text));
        }

        public int getMaximumTextLength(Locale locale) {
            return GJLocaleSymbols.forLocale(locale).getHalfdayMaxTextLength();
        }
    }

    private static class YearInfo {
        public final int iYear;
        public final long iFirstDayMillis;

        YearInfo(int year, long firstDayMillis) {
            iYear = year;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[139]++;
            iFirstDayMillis = firstDayMillis;
CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75.statements[140]++;
        }
    }

}

class CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75 ());
  }
    public static long[] statements = new long[141];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BasicChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$7r56qij6r301g7i67vvb7viadyxdi75 () {
    super("org.joda.time.chrono.BasicChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 140; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BasicChronology.java");
      for (int i = 1; i <= 140; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
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

