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
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Instant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.BaseDurationField;
import org.joda.time.format.DateTimeFormat;

/**
 * Wraps another Chronology to add support for time zones.
 * <p>
 * ZonedChronology is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class ZonedChronology extends AssembledChronology {
  static {
    CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -1079258847191166848L;
  static {
    CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[1]++;
  }

    /**
     * Create a ZonedChronology for any chronology, overriding any time zone it
     * may already have.
     *
     * @param base base chronology to wrap
     * @param zone the time zone
     * @throws IllegalArgumentException if chronology or time zone is null
     */
    public static ZonedChronology getInstance(Chronology base, DateTimeZone zone) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[1]++;
            throw new IllegalArgumentException("Must supply a chronology");

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[2]++;}
        base = base.withUTC();
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[3]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[3]++;
            throw new IllegalArgumentException("UTC chronology must not be null");

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[4]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[5]++;
            throw new IllegalArgumentException("DateTimeZone must not be null");

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[6]++;}
        return new ZonedChronology(base, zone);
    }

    static boolean useTimeArithmetic(DurationField field) {
        // Use time of day arithmetic rules for unit durations less than
        // typical time zone offsets.
        return field != null && field.getUnitMillis() < DateTimeConstants.MILLIS_PER_HOUR * 12;
    }

    /**
     * Restricted constructor
     *
     * @param base base chronology to wrap
     * @param zone the time zone
     */
    private ZonedChronology(Chronology base, DateTimeZone zone) {
        super(base, zone);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[6]++;
    }

    public DateTimeZone getZone() {
        return (DateTimeZone)getParam();
    }

    public Chronology withUTC() {
        return getBase();
    }

    public Chronology withZone(DateTimeZone zone) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[7]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[8]++;

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[8]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[9]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == getParam()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[9]++;
            return this;

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[10]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[10]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[11]++;
            return getBase();

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[12]++;}
        return new ZonedChronology(getBase(), zone);
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int millisOfDay)
        throws IllegalArgumentException
    {
        return localToUTC(getBase().getDateTimeMillis
                          (year, monthOfYear, dayOfMonth, millisOfDay));
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
        return localToUTC(getBase().getDateTimeMillis
                          (year, monthOfYear, dayOfMonth, 
                           hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond));
    }

    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
        return localToUTC(getBase().getDateTimeMillis
                          (instant + getZone().getOffset(instant),
                           hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond));
    }

    /**
     * @param instant instant from 1970-01-01T00:00:00 local time
     * @return instant from 1970-01-01T00:00:00Z
     */
    private long localToUTC(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[11]++;
        DateTimeZone zone = getZone();
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[12]++;
        int offset = zone.getOffsetFromLocal(instant);
        instant -= offset;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[13]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((offset != zone.getOffset(instant)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[13]++;
            throw new IllegalArgumentException
                ("Illegal instant due to time zone offset transition: " +
                    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(instant)));

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[14]++;}
        return instant;
    }

    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[15]++;
        // Keep a local cache of converted fields so as not to create redundant
        // objects.
        HashMap<Object, Object> converted = new HashMap<Object, Object>();

        // Convert duration fields...

        fields.eras = convertField(fields.eras, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[16]++;
        fields.centuries = convertField(fields.centuries, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[17]++;
        fields.years = convertField(fields.years, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[18]++;
        fields.months = convertField(fields.months, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[19]++;
        fields.weekyears = convertField(fields.weekyears, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[20]++;
        fields.weeks = convertField(fields.weeks, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[21]++;
        fields.days = convertField(fields.days, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[22]++;

        fields.halfdays = convertField(fields.halfdays, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[23]++;
        fields.hours = convertField(fields.hours, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[24]++;
        fields.minutes = convertField(fields.minutes, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[25]++;
        fields.seconds = convertField(fields.seconds, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[26]++;
        fields.millis = convertField(fields.millis, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[27]++;

        // Convert datetime fields...

        fields.year = convertField(fields.year, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[28]++;
        fields.yearOfEra = convertField(fields.yearOfEra, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[29]++;
        fields.yearOfCentury = convertField(fields.yearOfCentury, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[30]++;
        fields.centuryOfEra = convertField(fields.centuryOfEra, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[31]++;
        fields.era = convertField(fields.era, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[32]++;
        fields.dayOfWeek = convertField(fields.dayOfWeek, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[33]++;
        fields.dayOfMonth = convertField(fields.dayOfMonth, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[34]++;
        fields.dayOfYear = convertField(fields.dayOfYear, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[35]++;
        fields.monthOfYear = convertField(fields.monthOfYear, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[36]++;
        fields.weekOfWeekyear = convertField(fields.weekOfWeekyear, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[37]++;
        fields.weekyear = convertField(fields.weekyear, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[38]++;
        fields.weekyearOfCentury = convertField(fields.weekyearOfCentury, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[39]++;

        fields.millisOfSecond = convertField(fields.millisOfSecond, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[40]++;
        fields.millisOfDay = convertField(fields.millisOfDay, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[41]++;
        fields.secondOfMinute = convertField(fields.secondOfMinute, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[42]++;
        fields.secondOfDay = convertField(fields.secondOfDay, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[43]++;
        fields.minuteOfHour = convertField(fields.minuteOfHour, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[44]++;
        fields.minuteOfDay = convertField(fields.minuteOfDay, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[45]++;
        fields.hourOfDay = convertField(fields.hourOfDay, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[46]++;
        fields.hourOfHalfday = convertField(fields.hourOfHalfday, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[47]++;
        fields.clockhourOfDay = convertField(fields.clockhourOfDay, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[48]++;
        fields.clockhourOfHalfday = convertField(fields.clockhourOfHalfday, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[49]++;
        fields.halfdayOfDay = convertField(fields.halfdayOfDay, converted);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[50]++;
    }

    private DurationField convertField(DurationField field, HashMap<Object, Object> converted) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[51]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((field.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[15]++;
            return field;

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[16]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[52]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((converted.containsKey(field)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[17]++;
            return (DurationField)converted.get(field);

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[18]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[53]++;
        ZonedDurationField zonedField = new ZonedDurationField(field, getZone());
        converted.put(field, zonedField);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[54]++;
        return zonedField;
    }

    private DateTimeField convertField(DateTimeField field, HashMap<Object, Object> converted) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((field.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[19]++;
            return field;

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[20]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((converted.containsKey(field)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[21]++;
            return (DateTimeField)converted.get(field);

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[22]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[57]++;
        ZonedDateTimeField zonedField =
            new ZonedDateTimeField(field, getZone(),
                                   convertField(field.getDurationField(), converted),
                                   convertField(field.getRangeDurationField(), converted),
                                   convertField(field.getLeapDurationField(), converted));
        converted.put(field, zonedField);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[58]++;
        return zonedField;
    }

    //-----------------------------------------------------------------------
    /**
     * A zoned chronology is only equal to a zoned chronology with the
     * same base chronology and zone.
     * 
     * @param obj  the object to compare to
     * @return true if equal
     * @since 1.4
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[59]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[24]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[60]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((obj instanceof ZonedChronology == false) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[26]++;}
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[61]++;
        ZonedChronology chrono = (ZonedChronology) obj;
        return
            getBase().equals(chrono.getBase()) &&
            getZone().equals(chrono.getZone());
    }

    /**
     * A suitable hashcode for the chronology.
     * 
     * @return the hashcode
     * @since 1.4
     */
    public int hashCode() {
        return 326565 + getZone().hashCode() * 11 + getBase().hashCode() * 7;
    }

    /**
     * A debugging string for the chronology.
     * 
     * @return the debugging string
     */
    public String toString() {
        return "ZonedChronology[" + getBase() + ", " + getZone().getID() + ']';
    }

    //-----------------------------------------------------------------------
    /*
     * Because time durations are typically smaller than time zone offsets, the
     * arithmetic methods subtract the original offset. This produces a more
     * expected behavior when crossing time zone offset transitions. For dates,
     * the new offset is subtracted off. This behavior, if applied to time
     * fields, can nullify or reverse an add when crossing a transition.
     */
    static class ZonedDurationField extends BaseDurationField {
        private static final long serialVersionUID = -485345310999208286L;

        final DurationField iField;
        final boolean iTimeField;
        final DateTimeZone iZone;

        ZonedDurationField(DurationField field, DateTimeZone zone) {
            super(field.getType());
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[62]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((field.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[27]++;
                throw new IllegalArgumentException();

            } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[28]++;}
            iField = field;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[64]++;
            iTimeField = useTimeArithmetic(field);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[65]++;
            iZone = zone;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[66]++;
        }

        public boolean isPrecise() {
            return iTimeField ? iField.isPrecise() : iField.isPrecise() && this.iZone.isFixed();
        }

        public long getUnitMillis() {
            return iField.getUnitMillis();
        }

        public int getValue(long duration, long instant) {
            return iField.getValue(duration, addOffset(instant));
        }

        public long getValueAsLong(long duration, long instant) {
            return iField.getValueAsLong(duration, addOffset(instant));
        }

        public long getMillis(int value, long instant) {
            return iField.getMillis(value, addOffset(instant));
        }

        public long getMillis(long value, long instant) {
            return iField.getMillis(value, addOffset(instant));
        }

        public long add(long instant, int value) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[67]++;
            int offset = getOffsetToAdd(instant);
            instant = iField.add(instant + offset, value);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[68]++;
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }

        public long add(long instant, long value) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[69]++;
            int offset = getOffsetToAdd(instant);
            instant = iField.add(instant + offset, value);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[70]++;
            return instant - (iTimeField ? offset : getOffsetFromLocalToSubtract(instant));
        }

        public int getDifference(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[71]++;
            int offset = getOffsetToAdd(subtrahendInstant);
            return iField.getDifference
                (minuendInstant + (iTimeField ? offset : getOffsetToAdd(minuendInstant)),
                 subtrahendInstant + offset);
        }

        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[72]++;
            int offset = getOffsetToAdd(subtrahendInstant);
            return iField.getDifferenceAsLong
                (minuendInstant + (iTimeField ? offset : getOffsetToAdd(minuendInstant)),
                 subtrahendInstant + offset);
        }

        private int getOffsetToAdd(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[73]++;
            int offset = this.iZone.getOffset(instant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[74]++;
            long sum = instant + offset;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[75]++;
int CodeCoverConditionCoverageHelper_C15;
            // If there is a sign change, but the two values have the same sign...
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 (((instant ^ sum) < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (((instant ^ offset) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[29]++;
                throw new ArithmeticException("Adding time zone offset caused overflow");

            } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[30]++;}
            return offset;
        }

        private int getOffsetFromLocalToSubtract(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[76]++;
            int offset = this.iZone.getOffsetFromLocal(instant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[77]++;
            long diff = instant - offset;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[78]++;
int CodeCoverConditionCoverageHelper_C16;
            // If there is a sign change, but the two values have different signs...
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 (((instant ^ diff) < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (((instant ^ offset) < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[31]++;
                throw new ArithmeticException("Subtracting time zone offset caused overflow");

            } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[32]++;}
            return offset;
        }

        private long addOffset(long instant) {
            return iZone.convertUTCToLocal(instant);
        }
    }

    /**
     * A DateTimeField that decorates another to add timezone behaviour.
     * <p>
     * This class converts passed in instants to local wall time, and vice
     * versa on output.
     */
    static final class ZonedDateTimeField extends BaseDateTimeField {
        private static final long serialVersionUID = -3968986277775529794L;

        final DateTimeField iField;
        final DateTimeZone iZone;
        final DurationField iDurationField;
        final boolean iTimeField;
        final DurationField iRangeDurationField;
        final DurationField iLeapDurationField;

        ZonedDateTimeField(DateTimeField field,
                           DateTimeZone zone,
                           DurationField durationField,
                           DurationField rangeDurationField,
                           DurationField leapDurationField) {
            super(field.getType());
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[79]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[80]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((field.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[33]++;
                throw new IllegalArgumentException();

            } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[34]++;}
            iField = field;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[81]++;
            iZone = zone;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[82]++;
            iDurationField = durationField;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[83]++;
            iTimeField = useTimeArithmetic(durationField);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[84]++;
            iRangeDurationField = rangeDurationField;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[85]++;
            iLeapDurationField = leapDurationField;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[86]++;
        }

        public boolean isLenient() {
            return iField.isLenient();
        }

        public int get(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[87]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.get(localInstant);
        }

        public String getAsText(long instant, Locale locale) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[88]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.getAsText(localInstant, locale);
        }

        public String getAsShortText(long instant, Locale locale) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[89]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.getAsShortText(localInstant, locale);
        }

        public String getAsText(int fieldValue, Locale locale) {
            return iField.getAsText(fieldValue, locale);
        }

        public String getAsShortText(int fieldValue, Locale locale) {
            return iField.getAsShortText(fieldValue, locale);
        }

        public long add(long instant, int value) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[90]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((iTimeField) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[35]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[91]++;
                int offset = getOffsetToAdd(instant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[92]++;
                long localInstant = iField.add(instant + offset, value);
                return localInstant - offset;

            } else {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[36]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[93]++;
               long localInstant = iZone.convertUTCToLocal(instant);
               localInstant = iField.add(localInstant, value);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[94]++;
               return iZone.convertLocalToUTC(localInstant, false, instant);
            }
        }

        public long add(long instant, long value) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[95]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((iTimeField) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[37]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[96]++;
                int offset = getOffsetToAdd(instant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[97]++;
                long localInstant = iField.add(instant + offset, value);
                return localInstant - offset;

            } else {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[38]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[98]++;
               long localInstant = iZone.convertUTCToLocal(instant);
               localInstant = iField.add(localInstant, value);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[99]++;
               return iZone.convertLocalToUTC(localInstant, false, instant);
            }
        }

        public long addWrapField(long instant, int value) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[100]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((iTimeField) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[39]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[101]++;
                int offset = getOffsetToAdd(instant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[102]++;
                long localInstant = iField.addWrapField(instant + offset, value);
                return localInstant - offset;

            } else {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[40]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[103]++;
                long localInstant = iZone.convertUTCToLocal(instant);
                localInstant = iField.addWrapField(localInstant, value);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[104]++;
                return iZone.convertLocalToUTC(localInstant, false, instant);
            }
        }

        public long set(long instant, int value) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[105]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            localInstant = iField.set(localInstant, value);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[106]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[107]++;
            long result = iZone.convertLocalToUTC(localInstant, false, instant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[108]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((get(result) != value) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[41]++;
                throw new IllegalFieldValueException(iField.getType(), Integer.valueOf(value),
                    "Illegal instant due to time zone offset transition: " +
                    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(localInstant)) +
                    " (" + iZone.getID() + ")");

            } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[42]++;}
            return result;
        }

        public long set(long instant, String text, Locale locale) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[109]++;
            // cannot verify that new value stuck because set may be lenient
            long localInstant = iZone.convertUTCToLocal(instant);
            localInstant = iField.set(localInstant, text, locale);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[110]++;
            return iZone.convertLocalToUTC(localInstant, false, instant);
        }

        public int getDifference(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[111]++;
            int offset = getOffsetToAdd(subtrahendInstant);
            return iField.getDifference
                (minuendInstant + (iTimeField ? offset : getOffsetToAdd(minuendInstant)),
                 subtrahendInstant + offset);
        }

        public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[112]++;
            int offset = getOffsetToAdd(subtrahendInstant);
            return iField.getDifferenceAsLong
                (minuendInstant + (iTimeField ? offset : getOffsetToAdd(minuendInstant)),
                 subtrahendInstant + offset);
        }

        public final DurationField getDurationField() {
            return iDurationField;
        }

        public final DurationField getRangeDurationField() {
            return iRangeDurationField;
        }

        public boolean isLeap(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[113]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.isLeap(localInstant);
        }

        public int getLeapAmount(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[114]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.getLeapAmount(localInstant);
        }

        public final DurationField getLeapDurationField() {
            return iLeapDurationField;
        }

        public long roundFloor(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[115]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((iTimeField) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[43]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[116]++;
                int offset = getOffsetToAdd(instant);
                instant = iField.roundFloor(instant + offset);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[117]++;
                return instant - offset;

            } else {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[44]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[118]++;
                long localInstant = iZone.convertUTCToLocal(instant);
                localInstant = iField.roundFloor(localInstant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[119]++;
                return iZone.convertLocalToUTC(localInstant, false, instant);
            }
        }

        public long roundCeiling(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[120]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((iTimeField) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[45]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[121]++;
                int offset = getOffsetToAdd(instant);
                instant = iField.roundCeiling(instant + offset);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[122]++;
                return instant - offset;

            } else {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[46]++;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[123]++;
                long localInstant = iZone.convertUTCToLocal(instant);
                localInstant = iField.roundCeiling(localInstant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[124]++;
                return iZone.convertLocalToUTC(localInstant, false, instant);
            }
        }

        public long remainder(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[125]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.remainder(localInstant);
        }

        public int getMinimumValue() {
            return iField.getMinimumValue();
        }

        public int getMinimumValue(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[126]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.getMinimumValue(localInstant);
        }

        public int getMinimumValue(ReadablePartial instant) {
            return iField.getMinimumValue(instant);
        }

        public int getMinimumValue(ReadablePartial instant, int[] values) {
            return iField.getMinimumValue(instant, values);
        }

        public int getMaximumValue() {
            return iField.getMaximumValue();
        }

        public int getMaximumValue(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[127]++;
            long localInstant = iZone.convertUTCToLocal(instant);
            return iField.getMaximumValue(localInstant);
        }

        public int getMaximumValue(ReadablePartial instant) {
            return iField.getMaximumValue(instant);
        }

        public int getMaximumValue(ReadablePartial instant, int[] values) {
            return iField.getMaximumValue(instant, values);
        }

        public int getMaximumTextLength(Locale locale) {
            return iField.getMaximumTextLength(locale);
        }

        public int getMaximumShortTextLength(Locale locale) {
            return iField.getMaximumShortTextLength(locale);
        }

        private int getOffsetToAdd(long instant) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[128]++;
            int offset = this.iZone.getOffset(instant);
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[129]++;
            long sum = instant + offset;
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.statements[130]++;
int CodeCoverConditionCoverageHelper_C24;
            // If there is a sign change, but the two values have the same sign...
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 (((instant ^ sum) < 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 (((instant ^ offset) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[47]++;
                throw new ArithmeticException("Adding time zone offset caused overflow");

            } else {
  CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt.branches[48]++;}
            return offset;
        }
    }

}

class CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt ());
  }
    public static long[] statements = new long[131];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.ZonedChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 24; i++) {
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

  public CodeCoverCoverageCounter$akaqyb1a73mjpbdmf3ehes9wq3kbplt () {
    super("org.joda.time.chrono.ZonedChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 130; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.ZonedChronology.java");
      for (int i = 1; i <= 130; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
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

