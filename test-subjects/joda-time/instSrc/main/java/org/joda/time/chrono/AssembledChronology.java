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

import java.io.IOException;
import java.io.ObjectInputStream;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;

/**
 * Abstract Chronology that enables chronologies to be assembled from
 * a container of fields.
 * <p>
 * AssembledChronology is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class AssembledChronology extends BaseChronology {
  static {
    CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.ping();
  }


    private static final long serialVersionUID = -6728465968995518215L;
  static {
    CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[1]++;
  }

    private final Chronology iBase;
    private final Object iParam;

    private transient DurationField iMillis;
    private transient DurationField iSeconds;
    private transient DurationField iMinutes;
    private transient DurationField iHours;
    private transient DurationField iHalfdays;

    private transient DurationField iDays;
    private transient DurationField iWeeks;
    private transient DurationField iWeekyears;
    private transient DurationField iMonths;
    private transient DurationField iYears;
    private transient DurationField iCenturies;
    private transient DurationField iEras;

    private transient DateTimeField iMillisOfSecond;
    private transient DateTimeField iMillisOfDay;
    private transient DateTimeField iSecondOfMinute;
    private transient DateTimeField iSecondOfDay;
    private transient DateTimeField iMinuteOfHour;
    private transient DateTimeField iMinuteOfDay;
    private transient DateTimeField iHourOfDay;
    private transient DateTimeField iClockhourOfDay;
    private transient DateTimeField iHourOfHalfday;
    private transient DateTimeField iClockhourOfHalfday;
    private transient DateTimeField iHalfdayOfDay;

    private transient DateTimeField iDayOfWeek;
    private transient DateTimeField iDayOfMonth;
    private transient DateTimeField iDayOfYear;
    private transient DateTimeField iWeekOfWeekyear;
    private transient DateTimeField iWeekyear;
    private transient DateTimeField iWeekyearOfCentury;
    private transient DateTimeField iMonthOfYear;
    private transient DateTimeField iYear;
    private transient DateTimeField iYearOfEra;
    private transient DateTimeField iYearOfCentury;
    private transient DateTimeField iCenturyOfEra;
    private transient DateTimeField iEra;

    // Bit set determines which base fields are used
    // bit 1 set: hourOfDay, minuteOfHour, secondOfMinute, and millisOfSecond fields
    // bit 2 set: millisOfDayField
    // bit 3 set: year, monthOfYear, and dayOfMonth fields
    private transient int iBaseFlags;

    /**
     * Constructor calls the assemble method, enabling subclasses to define its
     * supported fields. If a base chronology is supplied, the field set
     * initially contains references to each base chronology field.
     * <p>
     * Other methods in this class will delegate to the base chronology, if it
     * can be determined that the base chronology will produce the same results
     * as AbstractChronology.
     *
     * @param base optional base chronology to copy initial fields from
     * @param param optional param object avalable for assemble method
     */
    protected AssembledChronology(Chronology base, Object param) {
        iBase = base;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[2]++;
        iParam = param;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[3]++;
        setFields();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[4]++;
    }

    public DateTimeZone getZone() {
        Chronology base;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[5]++;
        if ((base = iBase) != null) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[1]++;
            return base.getZone();

        } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[2]++;}
        return null;
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int millisOfDay)
        throws IllegalArgumentException
    {
        Chronology base;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[6]++;
        if ((base = iBase) != null && (iBaseFlags & 6) == 6) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[3]++;
            // Only call specialized implementation if applicable fields are the same.
            return base.getDateTimeMillis(year, monthOfYear, dayOfMonth, millisOfDay);

        } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[4]++;}
        return super.getDateTimeMillis(year, monthOfYear, dayOfMonth, millisOfDay);
    }

    public long getDateTimeMillis(int year, int monthOfYear, int dayOfMonth,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
        Chronology base;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[7]++;
        if ((base = iBase) != null && (iBaseFlags & 5) == 5) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[5]++;
            // Only call specialized implementation if applicable fields are the same.
            return base.getDateTimeMillis(year, monthOfYear, dayOfMonth,
                                          hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);

        } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[6]++;}
        return super.getDateTimeMillis(year, monthOfYear, dayOfMonth,
                                       hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
    }

    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
        Chronology base;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[8]++;
        if ((base = iBase) != null && (iBaseFlags & 1) == 1) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[7]++;
            // Only call specialized implementation if applicable fields are the same.
            return base.getDateTimeMillis
                (instant, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);

        } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[8]++;}
        return super.getDateTimeMillis
            (instant, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
    }

    public final DurationField millis() {
        return iMillis;
    }

    public final DateTimeField millisOfSecond() {
        return iMillisOfSecond;
    }

    public final DateTimeField millisOfDay() {
        return iMillisOfDay;
    }

    public final DurationField seconds() {
        return iSeconds;
    }

    public final DateTimeField secondOfMinute() {
        return iSecondOfMinute;
    }

    public final DateTimeField secondOfDay() {
        return iSecondOfDay;
    }

    public final DurationField minutes() {
        return iMinutes;
    }

    public final DateTimeField minuteOfHour() {
        return iMinuteOfHour;
    }

    public final DateTimeField minuteOfDay() {
        return iMinuteOfDay;
    }

    public final DurationField hours() {
        return iHours;
    }

    public final DateTimeField hourOfDay() {
        return iHourOfDay;
    }

    public final DateTimeField clockhourOfDay() {
        return iClockhourOfDay;
    }

    public final DurationField halfdays() {
        return iHalfdays;
    }

    public final DateTimeField hourOfHalfday() {
        return iHourOfHalfday;
    }

    public final DateTimeField clockhourOfHalfday() {
        return iClockhourOfHalfday;
    }

    public final DateTimeField halfdayOfDay() {
        return iHalfdayOfDay;
    }

    public final DurationField days() {
        return iDays;
    }

    public final DateTimeField dayOfWeek() {
        return iDayOfWeek;
    }

    public final DateTimeField dayOfMonth() {
        return iDayOfMonth;
    }

    public final DateTimeField dayOfYear() {
        return iDayOfYear;
    }

    public final DurationField weeks() {
        return iWeeks;
    }

    public final DateTimeField weekOfWeekyear() {
        return iWeekOfWeekyear;
    }

    public final DurationField weekyears() {
        return iWeekyears;
    }

    public final DateTimeField weekyear() {
        return iWeekyear;
    }

    public final DateTimeField weekyearOfCentury() {
        return iWeekyearOfCentury;
    }

    public final DurationField months() {
        return iMonths;
    }

    public final DateTimeField monthOfYear() {
        return iMonthOfYear;
    }

    public final DurationField years() {
        return iYears;
    }

    public final DateTimeField year() {
        return iYear;
    }

    public final DateTimeField yearOfEra() {
        return iYearOfEra;
    }

    public final DateTimeField yearOfCentury() {
        return iYearOfCentury;
    }

    public final DurationField centuries() {
        return iCenturies;
    }

    public final DateTimeField centuryOfEra() {
        return iCenturyOfEra;
    }

    public final DurationField eras() {
        return iEras;
    }

    public final DateTimeField era() {
        return iEra;
    }

    /**
     * Invoked by the constructor and after deserialization to allow subclasses
     * to define all of its supported fields. All unset fields default to
     * unsupported instances.
     *
     * @param fields container of fields
     */
    protected abstract void assemble(Fields fields);

    /**
     * Returns the same base chronology as passed into the constructor.
     */
    protected final Chronology getBase() {
        return iBase;
    }

    /**
     * Returns the same param object as passed into the constructor.
     */
    protected final Object getParam() {
        return iParam;
    }

    private void setFields() {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[9]++;
        Fields fields = new Fields();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((iBase != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[9]++;
            fields.copyFieldsFrom(iBase);
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[11]++;

        } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[10]++;}
        assemble(fields);
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[12]++;

        {
            DurationField f;
            iMillis    = (f = fields.millis)    != null ? f : super.millis();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[13]++;
            iSeconds   = (f = fields.seconds)   != null ? f : super.seconds();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[14]++;
            iMinutes   = (f = fields.minutes)   != null ? f : super.minutes();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[15]++;
            iHours     = (f = fields.hours)     != null ? f : super.hours();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[16]++;
            iHalfdays  = (f = fields.halfdays)  != null ? f : super.halfdays();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[17]++;
            iDays      = (f = fields.days)      != null ? f : super.days();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[18]++;
            iWeeks     = (f = fields.weeks)     != null ? f : super.weeks();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[19]++;
            iWeekyears = (f = fields.weekyears) != null ? f : super.weekyears();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[20]++;
            iMonths    = (f = fields.months)    != null ? f : super.months();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[21]++;
            iYears     = (f = fields.years)     != null ? f : super.years();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[22]++;
            iCenturies = (f = fields.centuries) != null ? f : super.centuries();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[23]++;
            iEras      = (f = fields.eras)      != null ? f : super.eras();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[24]++;
        }

        {
            DateTimeField f;
            iMillisOfSecond     = (f = fields.millisOfSecond)     != null ? f : super.millisOfSecond();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[25]++;
            iMillisOfDay        = (f = fields.millisOfDay)        != null ? f : super.millisOfDay();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[26]++;
            iSecondOfMinute     = (f = fields.secondOfMinute)     != null ? f : super.secondOfMinute();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[27]++;
            iSecondOfDay        = (f = fields.secondOfDay)        != null ? f : super.secondOfDay();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[28]++;
            iMinuteOfHour       = (f = fields.minuteOfHour)       != null ? f : super.minuteOfHour();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[29]++;
            iMinuteOfDay        = (f = fields.minuteOfDay)        != null ? f : super.minuteOfDay();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[30]++;
            iHourOfDay          = (f = fields.hourOfDay)          != null ? f : super.hourOfDay();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[31]++;
            iClockhourOfDay     = (f = fields.clockhourOfDay)     != null ? f : super.clockhourOfDay();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[32]++;
            iHourOfHalfday      = (f = fields.hourOfHalfday)      != null ? f : super.hourOfHalfday();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[33]++;
            iClockhourOfHalfday = (f = fields.clockhourOfHalfday) != null ? f : super.clockhourOfHalfday();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[34]++;
            iHalfdayOfDay       = (f = fields.halfdayOfDay)       != null ? f : super.halfdayOfDay();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[35]++;
            iDayOfWeek          = (f = fields.dayOfWeek)          != null ? f : super.dayOfWeek();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[36]++;
            iDayOfMonth         = (f = fields.dayOfMonth)         != null ? f : super.dayOfMonth();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[37]++;
            iDayOfYear          = (f = fields.dayOfYear)          != null ? f : super.dayOfYear();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[38]++;
            iWeekOfWeekyear     = (f = fields.weekOfWeekyear)     != null ? f : super.weekOfWeekyear();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[39]++;
            iWeekyear           = (f = fields.weekyear)           != null ? f : super.weekyear();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[40]++;
            iWeekyearOfCentury  = (f = fields.weekyearOfCentury)  != null ? f : super.weekyearOfCentury();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[41]++;
            iMonthOfYear        = (f = fields.monthOfYear)        != null ? f : super.monthOfYear();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[42]++;
            iYear               = (f = fields.year)               != null ? f : super.year();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[43]++;
            iYearOfEra          = (f = fields.yearOfEra)          != null ? f : super.yearOfEra();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[44]++;
            iYearOfCentury      = (f = fields.yearOfCentury)      != null ? f : super.yearOfCentury();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[45]++;
            iCenturyOfEra       = (f = fields.centuryOfEra)       != null ? f : super.centuryOfEra();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[46]++;
            iEra                = (f = fields.era)                != null ? f : super.era();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[47]++;
        }

        int flags;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[48]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iBase == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[11]++;
            flags = 0;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[49]++;

        } else {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[12]++;
            flags = 
                ((iHourOfDay      == iBase.hourOfDay()      &&
                  iMinuteOfHour   == iBase.minuteOfHour()   &&
                  iSecondOfMinute == iBase.secondOfMinute() &&
                  iMillisOfSecond == iBase.millisOfSecond()   ) ? 1 : 0) |

                ((iMillisOfDay == iBase.millisOfDay()) ? 2 : 0) |

                ((iYear        == iBase.year()        &&
                  iMonthOfYear == iBase.monthOfYear() &&
                  iDayOfMonth  == iBase.dayOfMonth()    ) ? 4 : 0);
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[50]++;
        }

        iBaseFlags = flags;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[51]++;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[52]++;
        setFields();
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[53]++;
    }

    /**
     * A container of fields used for assembling a chronology.
     */
    public static final class Fields {
        public DurationField millis;
        public DurationField seconds;
        public DurationField minutes;
        public DurationField hours;
        public DurationField halfdays;
    
        public DurationField days;
        public DurationField weeks;
        public DurationField weekyears;
        public DurationField months;
        public DurationField years;
        public DurationField centuries;
        public DurationField eras;
    
        public DateTimeField millisOfSecond;
        public DateTimeField millisOfDay;
        public DateTimeField secondOfMinute;
        public DateTimeField secondOfDay;
        public DateTimeField minuteOfHour;
        public DateTimeField minuteOfDay;
        public DateTimeField hourOfDay;
        public DateTimeField clockhourOfDay;
        public DateTimeField hourOfHalfday;
        public DateTimeField clockhourOfHalfday;
        public DateTimeField halfdayOfDay;
    
        public DateTimeField dayOfWeek;
        public DateTimeField dayOfMonth;
        public DateTimeField dayOfYear;
        public DateTimeField weekOfWeekyear;
        public DateTimeField weekyear;
        public DateTimeField weekyearOfCentury;
        public DateTimeField monthOfYear;
        public DateTimeField year;
        public DateTimeField yearOfEra;
        public DateTimeField yearOfCentury;
        public DateTimeField centuryOfEra;
        public DateTimeField era;

        Fields() {
        }

        /**
         * Copy the supported fields from a chronology into this container.
         */
        public void copyFieldsFrom(Chronology chrono) {
            {
                DurationField f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[54]++;
                if (isSupported(f = chrono.millis())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[13]++;
                    millis = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[55]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[14]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[56]++;
                if (isSupported(f = chrono.seconds())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[15]++;
                    seconds = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[57]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[16]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[58]++;
                if (isSupported(f = chrono.minutes())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[17]++;
                    minutes = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[59]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[18]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[60]++;
                if (isSupported(f = chrono.hours())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[19]++;
                    hours = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[61]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[20]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[62]++;
                if (isSupported(f = chrono.halfdays())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[21]++;
                    halfdays = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[63]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[22]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[64]++;
                if (isSupported(f = chrono.days())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[23]++;
                    days = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[65]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[24]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[66]++;
                if (isSupported(f = chrono.weeks())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[25]++;
                    weeks = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[67]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[26]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[68]++;
                if (isSupported(f = chrono.weekyears())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[27]++;
                    weekyears = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[69]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[28]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[70]++;
                if (isSupported(f = chrono.months())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[29]++;
                    months = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[71]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[30]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[72]++;
                if (isSupported(f = chrono.years())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[31]++;
                    years = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[73]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[32]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[74]++;
                if (isSupported(f = chrono.centuries())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[33]++;
                    centuries = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[75]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[34]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[76]++;
                if (isSupported(f = chrono.eras())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[35]++;
                    eras = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[77]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[36]++;}
            }

            {
                DateTimeField f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[78]++;
                if (isSupported(f = chrono.millisOfSecond())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[37]++;
                    millisOfSecond = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[79]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[38]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[80]++;
                if (isSupported(f = chrono.millisOfDay())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[39]++;
                    millisOfDay = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[81]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[40]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[82]++;
                if (isSupported(f = chrono.secondOfMinute())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[41]++;
                    secondOfMinute = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[83]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[42]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[84]++;
                if (isSupported(f = chrono.secondOfDay())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[43]++;
                    secondOfDay = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[85]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[44]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[86]++;
                if (isSupported(f = chrono.minuteOfHour())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[45]++;
                    minuteOfHour = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[87]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[46]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[88]++;
                if (isSupported(f = chrono.minuteOfDay())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[47]++;
                    minuteOfDay = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[89]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[48]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[90]++;
                if (isSupported(f = chrono.hourOfDay())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[49]++;
                    hourOfDay = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[91]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[50]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[92]++;
                if (isSupported(f = chrono.clockhourOfDay())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[51]++;
                    clockhourOfDay = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[93]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[52]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[94]++;
                if (isSupported(f = chrono.hourOfHalfday())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[53]++;
                    hourOfHalfday = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[95]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[54]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[96]++;
                if (isSupported(f = chrono.clockhourOfHalfday())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[55]++;
                    clockhourOfHalfday = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[97]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[56]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[98]++;
                if (isSupported(f = chrono.halfdayOfDay())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[57]++;
                    halfdayOfDay = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[99]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[58]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[100]++;
                if (isSupported(f = chrono.dayOfWeek())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[59]++;
                    dayOfWeek = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[101]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[60]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[102]++;
                if (isSupported(f = chrono.dayOfMonth())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[61]++;
                    dayOfMonth = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[103]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[62]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[104]++;
                if (isSupported(f = chrono.dayOfYear())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[63]++;
                    dayOfYear = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[105]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[64]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[106]++;
                if (isSupported(f = chrono.weekOfWeekyear())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[65]++;
                    weekOfWeekyear = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[107]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[66]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[108]++;
                if (isSupported(f = chrono.weekyear())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[67]++;
                    weekyear = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[109]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[68]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[110]++;
                if (isSupported(f = chrono.weekyearOfCentury())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[69]++;
                    weekyearOfCentury = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[111]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[70]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[112]++;
                if (isSupported(f = chrono.monthOfYear())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[71]++;
                    monthOfYear = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[113]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[72]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[114]++;
                if (isSupported(f = chrono.year())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[73]++;
                    year = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[115]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[74]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[116]++;
                if (isSupported(f = chrono.yearOfEra())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[75]++;
                    yearOfEra = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[117]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[76]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[118]++;
                if (isSupported(f = chrono.yearOfCentury())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[77]++;
                    yearOfCentury = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[119]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[78]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[120]++;
                if (isSupported(f = chrono.centuryOfEra())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[79]++;
                    centuryOfEra = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[121]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[80]++;}
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[122]++;
                if (isSupported(f = chrono.era())) {
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[81]++;
                    era = f;
CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.statements[123]++;

                } else {
  CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx.branches[82]++;}
            }
        }

        private static boolean isSupported(DurationField field) {
            return field == null ? false : field.isSupported();
        }

        private static boolean isSupported(DateTimeField field) {
            return field == null ? false : field.isSupported();
        }
    }
}

class CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx ());
  }
    public static long[] statements = new long[124];
    public static long[] branches = new long[83];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.AssembledChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,0,0,0,0,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$f325rzigl6dpje6ncx9crgvvob9bt0ela87kx () {
    super("org.joda.time.chrono.AssembledChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 123; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 82; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.AssembledChronology.java");
      for (int i = 1; i <= 123; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 82; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

