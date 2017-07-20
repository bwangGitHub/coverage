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
package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Wraps another Chronology to impose limits on the range of instants that
 * the fields within a Chronology may support. The limits are applied to both
 * DateTimeFields and DurationFields.
 * <p>
 * Methods in DateTimeField and DurationField throw an IllegalArgumentException
 * whenever given an input instant that is outside the limits or when an
 * attempt is made to move an instant outside the limits.
 * <p>
 * LimitChronology is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class LimitChronology extends AssembledChronology {
  static {
    CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = 7670866536893052522L;
  static {
    CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[1]++;
  }

    /**
     * Wraps another chronology, with datetime limits. When withUTC or
     * withZone is called, the returned LimitChronology instance has
     * the same limits, except they are time zone adjusted.
     *
     * @param base  base chronology to wrap
     * @param lowerLimit  inclusive lower limit, or null if none
     * @param upperLimit  exclusive upper limit, or null if none
     * @throws IllegalArgumentException if chronology is null or limits are invalid
     */
    public static LimitChronology getInstance(Chronology base,
                                              ReadableDateTime lowerLimit,
                                              ReadableDateTime upperLimit) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[1]++;
            throw new IllegalArgumentException("Must supply a chronology");

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[2]++;}

        lowerLimit = lowerLimit == null ? null : lowerLimit.toDateTime();
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[3]++;
        upperLimit = upperLimit == null ? null : upperLimit.toDateTime();
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[4]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((lowerLimit != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((upperLimit != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[3]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lowerLimit.isBefore(upperLimit)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[5]++;
                throw new IllegalArgumentException
                    ("The lower limit must be come before than the upper limit");

            } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[4]++;}

        return new LimitChronology(base, (DateTime)lowerLimit, (DateTime)upperLimit);
    }

    final DateTime iLowerLimit;
    final DateTime iUpperLimit;

    private transient LimitChronology iWithUTC;

    /**
     * Wraps another chronology, with datetime limits. When withUTC or
     * withZone is called, the returned LimitChronology instance has
     * the same limits, except they are time zone adjusted.
     *
     * @param lowerLimit  inclusive lower limit, or null if none
     * @param upperLimit  exclusive upper limit, or null if none
     */
    private LimitChronology(Chronology base,
                            DateTime lowerLimit, DateTime upperLimit) {
        super(base, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[7]++;
        // These can be set after assembly.
        iLowerLimit = lowerLimit;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[8]++;
        iUpperLimit = upperLimit;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[9]++;
    }

    /**
     * Returns the inclusive lower limit instant.
     * 
     * @return lower limit
     */
    public DateTime getLowerLimit() {
        return iLowerLimit;
    }

    /**
     * Returns the inclusive upper limit instant.
     * 
     * @return upper limit
     */
    public DateTime getUpperLimit() {
        return iUpperLimit;
    }

    /**
     * If this LimitChronology is already UTC, then this is
     * returned. Otherwise, a new instance is returned, with the limits
     * adjusted to the new time zone.
     */
    public Chronology withUTC() {
        return withZone(DateTimeZone.UTC);
    }

    /**
     * If this LimitChronology has the same time zone as the one given, then
     * this is returned. Otherwise, a new instance is returned, with the limits
     * adjusted to the new time zone.
     */
    public Chronology withZone(DateTimeZone zone) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[7]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[11]++;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[8]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[9]++;
            return this;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[10]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iWithUTC != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[11]++;
            return iWithUTC;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[12]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[14]++;

        DateTime lowerLimit = iLowerLimit;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lowerLimit != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[13]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[16]++;
            MutableDateTime mdt = lowerLimit.toMutableDateTime();
            mdt.setZoneRetainFields(zone);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[17]++;
            lowerLimit = mdt.toDateTime();
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[18]++;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[14]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[19]++;

        DateTime upperLimit = iUpperLimit;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((upperLimit != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[15]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[21]++;
            MutableDateTime mdt = upperLimit.toMutableDateTime();
            mdt.setZoneRetainFields(zone);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[22]++;
            upperLimit = mdt.toDateTime();
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[23]++;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[16]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[24]++;
        
        LimitChronology chrono = getInstance
            (getBase().withZone(zone), lowerLimit, upperLimit);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[17]++;
            iWithUTC = chrono;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[26]++;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[18]++;}

        return chrono;
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int millisOfDay)
        throws IllegalArgumentException
    {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[27]++;
        long instant = getBase().getDateTimeMillis(year, monthOfYear, dayOfMonth, millisOfDay);
        checkLimits(instant, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[28]++;
        return instant;
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[29]++;
        long instant = getBase().getDateTimeMillis
            (year, monthOfYear, dayOfMonth,
             hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
        checkLimits(instant, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[30]++;
        return instant;
    }

    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
        checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[31]++;
        instant = getBase().getDateTimeMillis
            (instant, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[32]++;
        checkLimits(instant, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[33]++;
        return instant;
    }

    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[34]++;
        // Keep a local cache of converted fields so as not to create redundant
        // objects.
        HashMap<Object, Object> converted = new HashMap<Object, Object>();

        // Convert duration fields...

        fields.eras = convertField(fields.eras, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[35]++;
        fields.centuries = convertField(fields.centuries, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[36]++;
        fields.years = convertField(fields.years, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[37]++;
        fields.months = convertField(fields.months, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[38]++;
        fields.weekyears = convertField(fields.weekyears, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[39]++;
        fields.weeks = convertField(fields.weeks, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[40]++;
        fields.days = convertField(fields.days, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[41]++;

        fields.halfdays = convertField(fields.halfdays, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[42]++;
        fields.hours = convertField(fields.hours, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[43]++;
        fields.minutes = convertField(fields.minutes, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[44]++;
        fields.seconds = convertField(fields.seconds, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[45]++;
        fields.millis = convertField(fields.millis, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[46]++;

        // Convert datetime fields...

        fields.year = convertField(fields.year, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[47]++;
        fields.yearOfEra = convertField(fields.yearOfEra, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[48]++;
        fields.yearOfCentury = convertField(fields.yearOfCentury, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[49]++;
        fields.centuryOfEra = convertField(fields.centuryOfEra, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[50]++;
        fields.era = convertField(fields.era, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[51]++;
        fields.dayOfWeek = convertField(fields.dayOfWeek, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[52]++;
        fields.dayOfMonth = convertField(fields.dayOfMonth, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[53]++;
        fields.dayOfYear = convertField(fields.dayOfYear, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[54]++;
        fields.monthOfYear = convertField(fields.monthOfYear, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[55]++;
        fields.weekOfWeekyear = convertField(fields.weekOfWeekyear, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[56]++;
        fields.weekyear = convertField(fields.weekyear, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[57]++;
        fields.weekyearOfCentury = convertField(fields.weekyearOfCentury, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[58]++;

        fields.millisOfSecond = convertField(fields.millisOfSecond, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[59]++;
        fields.millisOfDay = convertField(fields.millisOfDay, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[60]++;
        fields.secondOfMinute = convertField(fields.secondOfMinute, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[61]++;
        fields.secondOfDay = convertField(fields.secondOfDay, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[62]++;
        fields.minuteOfHour = convertField(fields.minuteOfHour, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[63]++;
        fields.minuteOfDay = convertField(fields.minuteOfDay, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[64]++;
        fields.hourOfDay = convertField(fields.hourOfDay, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[65]++;
        fields.hourOfHalfday = convertField(fields.hourOfHalfday, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[66]++;
        fields.clockhourOfDay = convertField(fields.clockhourOfDay, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[67]++;
        fields.clockhourOfHalfday = convertField(fields.clockhourOfHalfday, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[68]++;
        fields.halfdayOfDay = convertField(fields.halfdayOfDay, converted);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[69]++;
    }

    private DurationField convertField(DurationField field, HashMap<Object, Object> converted) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[70]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((field.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[19]++;
            return field;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[20]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[71]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((converted.containsKey(field)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[21]++;
            return (DurationField)converted.get(field);

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[22]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[72]++;
        LimitDurationField limitField = new LimitDurationField(field);
        converted.put(field, limitField);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[73]++;
        return limitField;
    }

    private DateTimeField convertField(DateTimeField field, HashMap<Object, Object> converted) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[74]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((field.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[23]++;
            return field;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[24]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[75]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((converted.containsKey(field)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[25]++;
            return (DateTimeField)converted.get(field);

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[26]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[76]++;
        LimitDateTimeField limitField =
            new LimitDateTimeField(field,
                                   convertField(field.getDurationField(), converted),
                                   convertField(field.getRangeDurationField(), converted),
                                   convertField(field.getLeapDurationField(), converted));
        converted.put(field, limitField);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[77]++;
        return limitField;
    }

    void checkLimits(long instant, String desc) {
        DateTime limit;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[78]++;
        if ((limit = iLowerLimit) != null && instant < limit.getMillis()) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[27]++;
            throw new LimitException(desc, true);

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[28]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[79]++;
        if ((limit = iUpperLimit) != null && instant >= limit.getMillis()) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[29]++;
            throw new LimitException(desc, false);

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[30]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * A limit chronology is only equal to a limit chronology with the
     * same base chronology and limits.
     * 
     * @param obj  the object to compare to
     * @return true if equal
     * @since 1.4
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[80]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[31]++;
            return true;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[32]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj instanceof LimitChronology == false) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[34]++;}
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[82]++;
        LimitChronology chrono = (LimitChronology) obj;
        return
            getBase().equals(chrono.getBase()) &&
            FieldUtils.equals(getLowerLimit(), chrono.getLowerLimit()) &&
            FieldUtils.equals(getUpperLimit(), chrono.getUpperLimit());
    }

    /**
     * A suitable hashcode for the chronology.
     * 
     * @return the hashcode
     * @since 1.4
     */
    public int hashCode() {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[83]++;
        int hash = 317351877;
        hash += (getLowerLimit() != null ? getLowerLimit().hashCode() : 0);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[84]++;
        hash += (getUpperLimit() != null ? getUpperLimit().hashCode() : 0);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[85]++;
        hash += getBase().hashCode() * 7;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[86]++;
        return hash;
    }

    /**
     * A debugging string for the chronology.
     * 
     * @return the debugging string
     */
    public String toString() {
        return "LimitChronology[" + getBase().toString() + ", " +
            (getLowerLimit() == null ? "NoLimit" : getLowerLimit().toString()) + ", " +
            (getUpperLimit() == null ? "NoLimit" : getUpperLimit().toString()) + ']';
    }

    //-----------------------------------------------------------------------
    /**
     * Extends IllegalArgumentException such that the exception message is not
     * generated unless it is actually requested.
     */
    private class LimitException extends IllegalArgumentException {
        private static final long serialVersionUID = -5924689995607498581L;

        private final boolean iIsLow;

        LimitException(String desc, boolean isLow) {
            super(desc);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[87]++;
            iIsLow = isLow;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[88]++;
        }

        public String getMessage() {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[89]++;
            StringBuffer buf = new StringBuffer(85);
            buf.append("The");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[90]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[91]++;
            String desc = super.getMessage();
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[92]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((desc != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[35]++;
                buf.append(' ');
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[93]++;
                buf.append(desc);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[94]++;

            } else {
  CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[36]++;}
            buf.append(" instant is ");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[95]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[96]++;

            DateTimeFormatter p = ISODateTimeFormat.dateTime();
            p = p.withChronology(getBase());
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[97]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[98]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((iIsLow) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[37]++;
                buf.append("below the supported minimum of ");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[99]++;
                p.printTo(buf, getLowerLimit().getMillis());
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[100]++;

            } else {
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.branches[38]++;
                buf.append("above the supported maximum of ");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[101]++;
                p.printTo(buf, getUpperLimit().getMillis());
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[102]++;
            }
            
            buf.append(" (");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[103]++;
            buf.append(getBase());
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[104]++;
            buf.append(')');
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[105]++;

            return buf.toString();
        }

        public String toString() {
            return "IllegalArgumentException: " + getMessage();
        }
    }

    private class LimitDurationField extends DecoratedDurationField {
        private static final long serialVersionUID = 8049297699408782284L;

        LimitDurationField(DurationField field) {
            super(field, field.getType());
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[106]++;
        }

        public int getValue(long duration, long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[107]++;
            return getWrappedField().getValue(duration, instant);
        }

        public long getValueAsLong(long duration, long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[108]++;
            return getWrappedField().getValueAsLong(duration, instant);
        }

        public long getMillis(int value, long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[109]++;
            return getWrappedField().getMillis(value, instant);
        }

        public long getMillis(long value, long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[110]++;
            return getWrappedField().getMillis(value, instant);
        }

        public long add(long instant, int amount) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[111]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[112]++;
            long result = getWrappedField().add(instant, amount);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[113]++;
            return result;
        }

        public long add(long instant, long amount) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[114]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[115]++;
            long result = getWrappedField().add(instant, amount);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[116]++;
            return result;
        }

        public int getDifference(long minuendInstant, long subtrahendInstant) {
            checkLimits(minuendInstant, "minuend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[117]++;
            checkLimits(subtrahendInstant, "subtrahend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[118]++;
            return getWrappedField().getDifference(minuendInstant, subtrahendInstant);
        }

        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            checkLimits(minuendInstant, "minuend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[119]++;
            checkLimits(subtrahendInstant, "subtrahend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[120]++;
            return getWrappedField().getDifferenceAsLong(minuendInstant, subtrahendInstant);
        }

    }

    private class LimitDateTimeField extends DecoratedDateTimeField {
        private static final long serialVersionUID = -2435306746995699312L;

        private final DurationField iDurationField;
        private final DurationField iRangeDurationField;
        private final DurationField iLeapDurationField;

        LimitDateTimeField(DateTimeField field,
                           DurationField durationField,
                           DurationField rangeDurationField,
                           DurationField leapDurationField) {
            super(field, field.getType());
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[121]++;
            iDurationField = durationField;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[122]++;
            iRangeDurationField = rangeDurationField;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[123]++;
            iLeapDurationField = leapDurationField;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[124]++;
        }

        public int get(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[125]++;
            return getWrappedField().get(instant);
        }
        
        public String getAsText(long instant, Locale locale) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[126]++;
            return getWrappedField().getAsText(instant, locale);
        }
        
        public String getAsShortText(long instant, Locale locale) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[127]++;
            return getWrappedField().getAsShortText(instant, locale);
        }
        
        public long add(long instant, int amount) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[128]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[129]++;
            long result = getWrappedField().add(instant, amount);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[130]++;
            return result;
        }

        public long add(long instant, long amount) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[131]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[132]++;
            long result = getWrappedField().add(instant, amount);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[133]++;
            return result;
        }

        public long addWrapField(long instant, int amount) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[134]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[135]++;
            long result = getWrappedField().addWrapField(instant, amount);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[136]++;
            return result;
        }
        
        public int getDifference(long minuendInstant, long subtrahendInstant) {
            checkLimits(minuendInstant, "minuend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[137]++;
            checkLimits(subtrahendInstant, "subtrahend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[138]++;
            return getWrappedField().getDifference(minuendInstant, subtrahendInstant);
        }
        
        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
            checkLimits(minuendInstant, "minuend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[139]++;
            checkLimits(subtrahendInstant, "subtrahend");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[140]++;
            return getWrappedField().getDifferenceAsLong(minuendInstant, subtrahendInstant);
        }
        
        public long set(long instant, int value) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[141]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[142]++;
            long result = getWrappedField().set(instant, value);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[143]++;
            return result;
        }
        
        public long set(long instant, String text, Locale locale) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[144]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[145]++;
            long result = getWrappedField().set(instant, text, locale);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[146]++;
            return result;
        }
        
        public final DurationField getDurationField() {
            return iDurationField;
        }

        public final DurationField getRangeDurationField() {
            return iRangeDurationField;
        }

        public boolean isLeap(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[147]++;
            return getWrappedField().isLeap(instant);
        }
        
        public int getLeapAmount(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[148]++;
            return getWrappedField().getLeapAmount(instant);
        }
        
        public final DurationField getLeapDurationField() {
            return iLeapDurationField;
        }
        
        public long roundFloor(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[149]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[150]++;
            long result = getWrappedField().roundFloor(instant);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[151]++;
            return result;
        }
        
        public long roundCeiling(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[152]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[153]++;
            long result = getWrappedField().roundCeiling(instant);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[154]++;
            return result;
        }
        
        public long roundHalfFloor(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[155]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[156]++;
            long result = getWrappedField().roundHalfFloor(instant);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[157]++;
            return result;
        }
        
        public long roundHalfCeiling(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[158]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[159]++;
            long result = getWrappedField().roundHalfCeiling(instant);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[160]++;
            return result;
        }
        
        public long roundHalfEven(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[161]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[162]++;
            long result = getWrappedField().roundHalfEven(instant);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[163]++;
            return result;
        }
        
        public long remainder(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[164]++;
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[165]++;
            long result = getWrappedField().remainder(instant);
            checkLimits(result, "resulting");
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[166]++;
            return result;
        }

        public int getMinimumValue(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[167]++;
            return getWrappedField().getMinimumValue(instant);
        }

        public int getMaximumValue(long instant) {
            checkLimits(instant, null);
CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1.statements[168]++;
            return getWrappedField().getMaximumValue(instant);
        }

        public int getMaximumTextLength(Locale locale) {
            return getWrappedField().getMaximumTextLength(locale);
        }

        public int getMaximumShortTextLength(Locale locale) {
            return getWrappedField().getMaximumShortTextLength(locale);
        }

    }

}

class CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1 ());
  }
    public static long[] statements = new long[169];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.LimitChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,2,1,1,1,2,1,2,1,0,0,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$8xbryl2a6hla7oovae9x0aiwsxfe8o1 () {
    super("org.joda.time.chrono.LimitChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 168; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.LimitChronology.java");
      for (int i = 1; i <= 168; i++) {
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
    for (int i = 1; i <= 19; i++) {
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

