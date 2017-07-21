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
package org.joda.time.format;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTimeFieldType;

/**
 * Factory that creates instances of DateTimeFormatter for the ISO8601 standard.
 * <p>
 * Datetime formatting is performed by the {@link DateTimeFormatter} class.
 * Three classes provide factory methods to create formatters, and this is one.
 * The others are {@link DateTimeFormat} and {@link DateTimeFormatterBuilder}.
 * <p>
 * ISO8601 is the international standard for data interchange. It defines a
 * framework, rather than an absolute standard. As a result this provider has a
 * number of methods that represent common uses of the framework. The most common
 * formats are {@link #date() date}, {@link #time() time}, and {@link #dateTime() dateTime}.
 * <p>
 * For example, to format a date time in ISO format:
 * <pre>
 * DateTime dt = new DateTime();
 * DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
 * String str = fmt.print(dt);
 * </pre>
 * <p>
 * It is important to understand that these formatters are not linked to
 * the <code>ISOChronology</code>. These formatters may be used with any
 * chronology, however there may be certain side effects with more unusual
 * chronologies. For example, the ISO formatters rely on dayOfWeek being
 * single digit, dayOfMonth being two digit and dayOfYear being three digit.
 * A chronology with a ten day week would thus cause issues. However, in
 * general, it is safe to use these formatters with other chronologies.
 * <p>
 * ISODateTimeFormat is thread-safe and immutable, and the formatters it
 * returns are as well.
 *
 * @author Brian S O'Neill
 * @since 1.0
 * @see DateTimeFormat
 * @see DateTimeFormatterBuilder
 */
public class ISODateTimeFormat {
  static {
    CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.ping();
  }


    //-----------------------------------------------------------------------
    private static DateTimeFormatter
        ye,  // year element (yyyy)
        mye, // monthOfYear element (-MM)
        dme, // dayOfMonth element (-dd)
        we,  // weekyear element (xxxx)
        wwe, // weekOfWeekyear element (-ww)
        dwe, // dayOfWeek element (-ee)
        dye, // dayOfYear element (-DDD)
        hde, // hourOfDay element (HH)
        mhe, // minuteOfHour element (:mm)
        sme, // secondOfMinute element (:ss)
        fse, // fractionOfSecond element (.SSSSSSSSS)
        ze,  // zone offset element
        lte, // literal 'T' element
        
        //y,   // year (same as year element)
        ym,  // year month
        ymd, // year month day

        //w,   // weekyear (same as weekyear element)
        ww,  // weekyear week
        wwd, // weekyear week day

        //h,    // hour (same as hour element)
        hm,   // hour minute
        hms,  // hour minute second
        hmsl, // hour minute second millis
        hmsf, // hour minute second fraction

        dh,    // date hour
        dhm,   // date hour minute
        dhms,  // date hour minute second
        dhmsl, // date hour minute second millis
        dhmsf, // date hour minute second fraction

        //d,  // date (same as ymd)
        t,  // time
        tx,  // time no millis
        tt,  // Ttime
        ttx,  // Ttime no millis
        dt, // date time
        dtx, // date time no millis

        //wd,  // week date (same as wwd)
        wdt, // week date time
        wdtx, // week date time no millis

        od,  // ordinal date (same as yd)
        odt, // ordinal date time
        odtx, // ordinal date time no millis

        bd,  // basic date
        bt,  // basic time
        btx,  // basic time no millis
        btt, // basic Ttime
        bttx, // basic Ttime no millis
        bdt, // basic date time
        bdtx, // basic date time no millis

        bod,  // basic ordinal date
        bodt, // basic ordinal date time
        bodtx, // basic ordinal date time no millis

        bwd,  // basic week date
        bwdt, // basic week date time
        bwdtx, // basic week date time no millis

        dpe, // date parser element
        tpe, // time parser element
        dp,  // date parser
        ldp, // local date parser
        tp,  // time parser
        ltp, // local time parser
        dtp, // date time parser
        dotp, // date optional time parser
        ldotp; // local date optional time parser

    /**
     * Constructor.
     *
     * @since 1.1 (previously private)
     */
    protected ISODateTimeFormat() {
        super();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[1]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a formatter that outputs only those fields specified.
     * <p>
     * This method examines the fields provided and returns an ISO-style
     * formatter that best fits. This can be useful for outputting
     * less-common ISO styles, such as YearMonth (YYYY-MM) or MonthDay (--MM-DD).
     * <p>
     * The list provided may have overlapping fields, such as dayOfWeek and
     * dayOfMonth. In this case, the style is chosen based on the following
     * list, thus in the example, the calendar style is chosen as dayOfMonth
     * is higher in priority than dayOfWeek:
     * <ul>
     * <li>monthOfYear - calendar date style
     * <li>dayOfYear - ordinal date style
     * <li>weekOfWeekYear - week date style
     * <li>dayOfMonth - calendar date style
     * <li>dayOfWeek - week date style
     * <li>year
     * <li>weekyear
     * </ul>
     * The supported formats are:
     * <pre>
     * Extended      Basic       Fields
     * 2005-03-25    20050325    year/monthOfYear/dayOfMonth
     * 2005-03       2005-03     year/monthOfYear
     * 2005--25      2005--25    year/dayOfMonth *
     * 2005          2005        year
     * --03-25       --0325      monthOfYear/dayOfMonth
     * --03          --03        monthOfYear
     * ---03         ---03       dayOfMonth
     * 2005-084      2005084     year/dayOfYear
     * -084          -084        dayOfYear
     * 2005-W12-5    2005W125    weekyear/weekOfWeekyear/dayOfWeek
     * 2005-W-5      2005W-5     weekyear/dayOfWeek *
     * 2005-W12      2005W12     weekyear/weekOfWeekyear
     * -W12-5        -W125       weekOfWeekyear/dayOfWeek
     * -W12          -W12        weekOfWeekyear
     * -W-5          -W-5        dayOfWeek
     * 10:20:30.040  102030.040  hour/minute/second/milli
     * 10:20:30      102030      hour/minute/second
     * 10:20         1020        hour/minute
     * 10            10          hour
     * -20:30.040    -2030.040   minute/second/milli
     * -20:30        -2030       minute/second
     * -20           -20         minute
     * --30.040      --30.040    second/milli
     * --30          --30        second
     * ---.040       ---.040     milli *
     * 10-30.040     10-30.040   hour/second/milli *
     * 10:20-.040    1020-.040   hour/minute/milli *
     * 10-30         10-30       hour/second *
     * 10--.040      10--.040    hour/milli *
     * -20-.040      -20-.040    minute/milli *
     *   plus datetime formats like {date}T{time}
     * </pre>
     * * indiates that this is not an official ISO format and can be excluded
     * by passing in <code>strictISO</code> as <code>true</code>.
     * <p>
     * This method can side effect the input collection of fields.
     * If the input collection is modifiable, then each field that was added to
     * the formatter will be removed from the collection, including any duplicates.
     * If the input collection is unmodifiable then no side effect occurs.
     * <p>
     * This side effect processing is useful if you need to know whether all
     * the fields were converted into the formatter or not. To achieve this,
     * pass in a modifiable list, and check that it is empty on exit.
     *
     * @param fields  the fields to get a formatter for, not null,
     *  updated by the method call unless unmodifiable,
     *  removing those fields built in the formatter
     * @param extended  true to use the extended format (with separators)
     * @param strictISO  true to stick exactly to ISO8601, false to include additional formats
     * @return a suitable formatter
     * @throws IllegalArgumentException if there is no format for the fields
     * @since 1.1
     */
    public static DateTimeFormatter forFields(
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((fields == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((fields.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[1]++;
            throw new IllegalArgumentException("The fields must not be null or empty");

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[2]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[3]++;
        Set<DateTimeFieldType> workingFields = new HashSet<DateTimeFieldType>(fields);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[4]++;
        int inputSize = workingFields.size();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[5]++;
        boolean reducedPrec = false;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[6]++;
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        // date
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((workingFields.contains(DateTimeFieldType.monthOfYear())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[3]++;
            reducedPrec = dateByMonth(bld, workingFields, extended, strictISO);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[8]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[4]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[9]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((workingFields.contains(DateTimeFieldType.dayOfYear())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[5]++;
            reducedPrec = dateByOrdinal(bld, workingFields, extended, strictISO);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[10]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[6]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((workingFields.contains(DateTimeFieldType.weekOfWeekyear())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[7]++;
            reducedPrec = dateByWeek(bld, workingFields, extended, strictISO);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[12]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[8]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[13]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((workingFields.contains(DateTimeFieldType.dayOfMonth())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[9]++;
            reducedPrec = dateByMonth(bld, workingFields, extended, strictISO);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[14]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[10]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[15]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((workingFields.contains(DateTimeFieldType.dayOfWeek())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[11]++;
            reducedPrec = dateByWeek(bld, workingFields, extended, strictISO);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[16]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[12]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[17]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((workingFields.remove(DateTimeFieldType.year())) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[13]++;
            bld.append(yearElement());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[18]++;
            reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[19]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[14]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[20]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((workingFields.remove(DateTimeFieldType.weekyear())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[15]++;
            bld.append(weekyearElement());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[21]++;
            reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[22]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[16]++;}
}
}
}
}
}
}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[23]++;
        boolean datePresent = (workingFields.size() < inputSize);
        
        // time
        time(bld, workingFields, extended, strictISO, reducedPrec, datePresent);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[24]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;
        
        // result
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((bld.canBuildFormatter() == false) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[17]++;
            throw new IllegalArgumentException("No valid format for fields: " + fields);

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[18]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[26]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        
        // side effect the input collection to indicate the processed fields
        // handling unmodifiable collections with no side effect
        try {
CodeCoverTryBranchHelper_Try1 = true;
            fields.retainAll(workingFields);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[27]++;
        } catch (UnsupportedOperationException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[20]++;
            // ignore, so we can handle unmodifiable collections
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[19]++;
}
  }
        return bld.toFormatter();
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a date using the calendar date format.
     * Specification reference: 5.2.1.
     *
     * @param bld  the builder
     * @param fields  the fields
     * @param extended  true to use extended format
     * @param strictISO  true to only allow ISO formats
     * @return true if reduced precision
     * @since 1.1
     */
    private static boolean dateByMonth(
        DateTimeFormatterBuilder bld,
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[28]++;
        
        boolean reducedPrec = false;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.year())) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[21]++;
            bld.append(yearElement());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[30]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.monthOfYear())) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[23]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfMonth())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[25]++;
                    // YYYY-MM-DD/YYYYMMDD
                    appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[33]++;
                    bld.appendMonthOfYear(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[34]++;
                    appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[35]++;
                    bld.appendDayOfMonth(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[36]++;

                } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[26]++;
                    // YYYY-MM/YYYY-MM
                    bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[37]++;
                    bld.appendMonthOfYear(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[38]++;
                    reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[39]++;
                }

            } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[24]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[40]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfMonth())) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[27]++;
                    // YYYY--DD/YYYY--DD (non-iso)
                    checkNotStrictISO(fields, strictISO);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[41]++;
                    bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[42]++;
                    bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[43]++;
                    bld.appendDayOfMonth(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[44]++;

                } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[28]++;
                    // YYYY/YYYY
                    reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[45]++;
                }
            }

            
        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[22]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[46]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.monthOfYear())) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[29]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[47]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[48]++;
            bld.appendMonthOfYear(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[49]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[50]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfMonth())) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[31]++;
                // --MM-DD/--MMDD
                appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[51]++;
                bld.appendDayOfMonth(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[52]++;

            } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[32]++;
                // --MM/--MM
                reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[53]++;
            }

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[30]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[54]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfMonth())) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[33]++;
            // ---DD/---DD
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[55]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[56]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[57]++;
            bld.appendDayOfMonth(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[58]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[34]++;}
}
}
        return reducedPrec;
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a date using the ordinal date format.
     * Specification reference: 5.2.2.
     *
     * @param bld  the builder
     * @param fields  the fields
     * @param extended  true to use extended format
     * @param strictISO  true to only allow ISO formats
     * @since 1.1
     */
    private static boolean dateByOrdinal(
        DateTimeFormatterBuilder bld,
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[59]++;
        
        boolean reducedPrec = false;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[60]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.year())) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[35]++;
            bld.append(yearElement());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[61]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[62]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfYear())) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[37]++;
                // YYYY-DDD/YYYYDDD
                appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[63]++;
                bld.appendDayOfYear(3);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[64]++;

            } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[38]++;
                // YYYY/YYYY
                reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[65]++;
            }

            
        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[36]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[66]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfYear())) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[39]++;
            // -DDD/-DDD
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[67]++;
            bld.appendDayOfYear(3);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[68]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[40]++;}
}
        return reducedPrec;
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a date using the calendar date format.
     * Specification reference: 5.2.3.
     *
     * @param bld  the builder
     * @param fields  the fields
     * @param extended  true to use extended format
     * @param strictISO  true to only allow ISO formats
     * @since 1.1
     */
    private static boolean dateByWeek(
        DateTimeFormatterBuilder bld,
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[69]++;
        
        boolean reducedPrec = false;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.weekyear())) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[41]++;
            bld.append(weekyearElement());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[71]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.weekOfWeekyear())) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[43]++;
                appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[73]++;
                bld.appendLiteral('W');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[74]++;
                bld.appendWeekOfWeekyear(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[75]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[76]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfWeek())) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[45]++;
                    // YYYY-WWW-D/YYYYWWWD
                    appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[77]++;
                    bld.appendDayOfWeek(1);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[78]++;

                } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[46]++;
                    // YYYY-WWW/YYYY-WWW
                    reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[79]++;
                }

            } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[44]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[80]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfWeek())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[47]++;
                    // YYYY-W-D/YYYYW-D (non-iso)
                    checkNotStrictISO(fields, strictISO);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[81]++;
                    appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[82]++;
                    bld.appendLiteral('W');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[83]++;
                    bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[84]++;
                    bld.appendDayOfWeek(1);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[85]++;

                } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[48]++;
                    // YYYY/YYYY
                    reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[86]++;
                }
            }

            
        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[42]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[87]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.weekOfWeekyear())) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[49]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[88]++;
            bld.appendLiteral('W');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[89]++;
            bld.appendWeekOfWeekyear(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[90]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[91]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfWeek())) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[51]++;
                // -WWW-D/-WWWD
                appendSeparator(bld, extended);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[92]++;
                bld.appendDayOfWeek(1);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[93]++;

            } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[52]++;
                // -WWW/-WWW
                reducedPrec = true;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[94]++;
            }

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[50]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[95]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((fields.remove(DateTimeFieldType.dayOfWeek())) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[53]++;
            // -W-D/-W-D
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[96]++;
            bld.appendLiteral('W');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[97]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[98]++;
            bld.appendDayOfWeek(1);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[99]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[54]++;}
}
}
        return reducedPrec;
    }

    //-----------------------------------------------------------------------
    /**
     * Adds the time fields to the builder.
     * Specification reference: 5.3.1.
     * 
     * @param bld  the builder
     * @param fields  the fields
     * @param extended  whether to use the extended format
     * @param strictISO  whether to be strict
     * @param reducedPrec  whether the date was reduced precision
     * @param datePresent  whether there was a date
     * @since 1.1
     */
    private static void time(
        DateTimeFormatterBuilder bld,
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO,
        boolean reducedPrec,
        boolean datePresent) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[100]++;
        
        boolean hour = fields.remove(DateTimeFieldType.hourOfDay());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[101]++;
        boolean minute = fields.remove(DateTimeFieldType.minuteOfHour());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[102]++;
        boolean second = fields.remove(DateTimeFieldType.secondOfMinute());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[103]++;
        boolean milli = fields.remove(DateTimeFieldType.millisOfSecond());
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[104]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (128)) == 0 || true) &&
 ((hour) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C27 |= (32)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[55]++;
            return;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[56]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[105]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (128)) == 0 || true) &&
 ((hour) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (32)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 4) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 4) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[57]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[106]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((strictISO) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((reducedPrec) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[59]++;
                throw new IllegalArgumentException("No valid ISO8601 format for fields because Date was reduced precision: " + fields);

            } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[60]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[107]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((datePresent) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[61]++;
                bld.appendLiteral('T');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[108]++;

            } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[62]++;}

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[58]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[109]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2048)) == 0 || true) &&
 ((hour) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (512)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (128)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (64)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((hour) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 6) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 6) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[63]++;

            // OK - HMSm/HMS/HM/H - valid in combination with date
        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[64]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[110]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((strictISO) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((datePresent) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[65]++;
                throw new IllegalArgumentException("No valid ISO8601 format for fields because Time was truncated: " + fields);

            } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[66]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[111]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2048)) == 0 || true) &&
 ((hour) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1024)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C33 |= (512)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (128)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (64)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C33 |= (32)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 6) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 6) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[67]++;

                // OK - MSm/MS/M/Sm/S - valid ISO formats
            } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[68]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[112]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((strictISO) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[69]++;
                    throw new IllegalArgumentException("No valid ISO8601 format for fields: " + fields);

                } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[70]++;}
            }
        }
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[113]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((hour) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[71]++;
            bld.appendHourOfDay(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[114]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[72]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[115]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (32)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[73]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[116]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[74]++;}
}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[117]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((extended) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((hour) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[75]++;
            bld.appendLiteral(':');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[118]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[76]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[119]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[77]++;
            bld.appendMinuteOfHour(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[120]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[78]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[121]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[79]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[122]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[80]++;}
}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[123]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((extended) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((minute) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[81]++;
            bld.appendLiteral(':');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[124]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[82]++;}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[125]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((second) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[83]++;
            bld.appendSecondOfMinute(2);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[126]++;

        } else {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[84]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[127]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[85]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[128]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[86]++;}
}
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[129]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((milli) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[87]++;
            bld.appendLiteral('.');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[130]++;
            bld.appendMillisOfSecond(3);
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[131]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[88]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Checks that the iso only flag is not set, throwing an exception if it is.
     * 
     * @param fields  the fields
     * @param strictISO  true if only ISO formats allowed
     * @since 1.1
     */
    private static void checkNotStrictISO(Collection<DateTimeFieldType> fields, boolean strictISO) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[132]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((strictISO) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[89]++;
            throw new IllegalArgumentException("No valid ISO8601 format for fields: " + fields);

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[90]++;}
    }

    /**
     * Appends the separator if necessary.
     *
     * @param bld  the builder
     * @param extended  whether to append the separator
     * @param sep  the separator
     * @since 1.1
     */
    private static void appendSeparator(DateTimeFormatterBuilder bld, boolean extended) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[133]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((extended) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[91]++;
            bld.appendLiteral('-');
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[134]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[92]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a generic ISO date parser for parsing dates with a possible zone.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * date              = date-element ['T' offset]
     * date-element      = std-date-element | ord-date-element | week-date-element
     * std-date-element  = yyyy ['-' MM ['-' dd]]
     * ord-date-element  = yyyy ['-' DDD]
     * week-date-element = xxxx '-W' ww ['-' e]
     * offset            = 'Z' | (('+' | '-') HH [':' mm [':' ss [('.' | ',') SSS]]])
     * </pre>
     */
    public static DateTimeFormatter dateParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[135]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((dp == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[93]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[136]++;
            DateTimeParser tOffset = new DateTimeFormatterBuilder()
                .appendLiteral('T')
                .append(offsetElement()).toParser();
            dp = new DateTimeFormatterBuilder()
                .append(dateElementParser())
                .appendOptional(tOffset)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[137]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[94]++;}
        return dp;
    }

    /**
     * Returns a generic ISO date parser for parsing local dates.
     * This parser is initialised with the local (UTC) time zone.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * date-element      = std-date-element | ord-date-element | week-date-element
     * std-date-element  = yyyy ['-' MM ['-' dd]]
     * ord-date-element  = yyyy ['-' DDD]
     * week-date-element = xxxx '-W' ww ['-' e]
     * </pre>
     * @since 1.3
     */
    public static DateTimeFormatter localDateParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[138]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((ldp == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[95]++;
            ldp = dateElementParser().withZoneUTC();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[139]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[96]++;}
        return ldp;
    }

    /**
     * Returns a generic ISO date parser for parsing dates.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * date-element      = std-date-element | ord-date-element | week-date-element
     * std-date-element  = yyyy ['-' MM ['-' dd]]
     * ord-date-element  = yyyy ['-' DDD]
     * week-date-element = xxxx '-W' ww ['-' e]
     * </pre>
     */
    public static DateTimeFormatter dateElementParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[140]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((dpe == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[97]++;
            dpe = new DateTimeFormatterBuilder()
                .append(null, new DateTimeParser[] {
                    new DateTimeFormatterBuilder()
                    .append(yearElement())
                    .appendOptional
                    (new DateTimeFormatterBuilder()
                     .append(monthElement())
                     .appendOptional(dayOfMonthElement().getParser())
                     .toParser())
                    .toParser(),
                    new DateTimeFormatterBuilder()
                    .append(weekyearElement())
                    .append(weekElement())
                    .appendOptional(dayOfWeekElement().getParser())
                    .toParser(),
                    new DateTimeFormatterBuilder()
                    .append(yearElement())
                    .append(dayOfYearElement())
                    .toParser()
                })
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[141]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[98]++;}
        return dpe;
    }

    /**
     * Returns a generic ISO time parser for parsing times with a possible zone.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * time           = ['T'] time-element [offset]
     * time-element   = HH [minute-element] | [fraction]
     * minute-element = ':' mm [second-element] | [fraction]
     * second-element = ':' ss [fraction]
     * fraction       = ('.' | ',') digit+
     * offset         = 'Z' | (('+' | '-') HH [':' mm [':' ss [('.' | ',') SSS]]])
     * </pre>
     */
    public static DateTimeFormatter timeParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[142]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((tp == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[99]++;
            tp = new DateTimeFormatterBuilder()
                .appendOptional(literalTElement().getParser())
                .append(timeElementParser())
                .appendOptional(offsetElement().getParser())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[143]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[100]++;}
        return tp;
    }

    /**
     * Returns a generic ISO time parser for parsing local times.
     * This parser is initialised with the local (UTC) time zone.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * time           = ['T'] time-element
     * time-element   = HH [minute-element] | [fraction]
     * minute-element = ':' mm [second-element] | [fraction]
     * second-element = ':' ss [fraction]
     * fraction       = ('.' | ',') digit+
     * </pre>
     * @since 1.3
     */
    public static DateTimeFormatter localTimeParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[144]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ltp == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[101]++;
            ltp = new DateTimeFormatterBuilder()
                .appendOptional(literalTElement().getParser())
                .append(timeElementParser())
                .toFormatter().withZoneUTC();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[145]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[102]++;}
        return ltp;
    }

    /**
     * Returns a generic ISO time parser.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * time-element   = HH [minute-element] | [fraction]
     * minute-element = ':' mm [second-element] | [fraction]
     * second-element = ':' ss [fraction]
     * fraction       = ('.' | ',') digit+
     * </pre>
     */
    public static DateTimeFormatter timeElementParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[146]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((tpe == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[103]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[147]++;
            // Decimal point can be either '.' or ','
            DateTimeParser decimalPoint = new DateTimeFormatterBuilder()
                .append(null, new DateTimeParser[] {
                    new DateTimeFormatterBuilder()
                    .appendLiteral('.')
                    .toParser(),
                    new DateTimeFormatterBuilder()
                    .appendLiteral(',')
                    .toParser()
                })
                .toParser();

            tpe = new DateTimeFormatterBuilder()
                // time-element
                .append(hourElement())
                .append
                (null, new DateTimeParser[] {
                    new DateTimeFormatterBuilder()
                    // minute-element
                    .append(minuteElement())
                    .append
                    (null, new DateTimeParser[] {
                        new DateTimeFormatterBuilder()
                        // second-element
                        .append(secondElement())
                        // second fraction
                        .appendOptional(new DateTimeFormatterBuilder()
                                        .append(decimalPoint)
                                        .appendFractionOfSecond(1, 9)
                                        .toParser())
                        .toParser(),
                        // minute fraction
                        new DateTimeFormatterBuilder()
                        .append(decimalPoint)
                        .appendFractionOfMinute(1, 9)
                        .toParser(),
                        null
                    })
                    .toParser(),
                    // hour fraction
                    new DateTimeFormatterBuilder()
                    .append(decimalPoint)
                    .appendFractionOfHour(1, 9)
                    .toParser(),
                    null
                })
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[148]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[104]++;}
        return tpe;
    }

    /**
     * Returns a generic ISO datetime parser which parses either a date or
     * a time or both. The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * datetime          = time | date-opt-time
     * time              = 'T' time-element [offset]
     * date-opt-time     = date-element ['T' [time-element] [offset]]
     * date-element      = std-date-element | ord-date-element | week-date-element
     * std-date-element  = yyyy ['-' MM ['-' dd]]
     * ord-date-element  = yyyy ['-' DDD]
     * week-date-element = xxxx '-W' ww ['-' e]
     * time-element      = HH [minute-element] | [fraction]
     * minute-element    = ':' mm [second-element] | [fraction]
     * second-element    = ':' ss [fraction]
     * fraction          = ('.' | ',') digit+
     * offset            = 'Z' | (('+' | '-') HH [':' mm [':' ss [('.' | ',') SSS]]])
     * </pre>
     */
    public static DateTimeFormatter dateTimeParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[149]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((dtp == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[105]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[150]++;
            // This is different from the general time parser in that the 'T'
            // is required.
            DateTimeParser time = new DateTimeFormatterBuilder()
                .appendLiteral('T')
                .append(timeElementParser())
                .appendOptional(offsetElement().getParser())
                .toParser();
            dtp = new DateTimeFormatterBuilder()
                .append(null, new DateTimeParser[] {time, dateOptionalTimeParser().getParser()})
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[151]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[106]++;}
        return dtp;
    }

    /**
     * Returns a generic ISO datetime parser where the date is mandatory and
     * the time is optional. This parser can parse zoned datetimes.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * date-opt-time     = date-element ['T' [time-element] [offset]]
     * date-element      = std-date-element | ord-date-element | week-date-element
     * std-date-element  = yyyy ['-' MM ['-' dd]]
     * ord-date-element  = yyyy ['-' DDD]
     * week-date-element = xxxx '-W' ww ['-' e]
     * time-element      = HH [minute-element] | [fraction]
     * minute-element    = ':' mm [second-element] | [fraction]
     * second-element    = ':' ss [fraction]
     * fraction          = ('.' | ',') digit+
     * </pre>
     * @since 1.3
     */
    public static DateTimeFormatter dateOptionalTimeParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[152]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((dotp == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[107]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[153]++;
            DateTimeParser timeOrOffset = new DateTimeFormatterBuilder()
                .appendLiteral('T')
                .appendOptional(timeElementParser().getParser())
                .appendOptional(offsetElement().getParser())
                .toParser();
            dotp = new DateTimeFormatterBuilder()
                .append(dateElementParser())
                .appendOptional(timeOrOffset)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[154]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[108]++;}
        return dotp;
    }

    /**
     * Returns a generic ISO datetime parser where the date is mandatory and
     * the time is optional. This parser only parses local datetimes.
     * This parser is initialised with the local (UTC) time zone.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * <p>
     * It accepts formats described by the following syntax:
     * <pre>
     * datetime          = date-element ['T' time-element]
     * date-element      = std-date-element | ord-date-element | week-date-element
     * std-date-element  = yyyy ['-' MM ['-' dd]]
     * ord-date-element  = yyyy ['-' DDD]
     * week-date-element = xxxx '-W' ww ['-' e]
     * time-element      = HH [minute-element] | [fraction]
     * minute-element    = ':' mm [second-element] | [fraction]
     * second-element    = ':' ss [fraction]
     * fraction          = ('.' | ',') digit+
     * </pre>
     * @since 1.3
     */
    public static DateTimeFormatter localDateOptionalTimeParser() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[155]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ldotp == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[109]++;
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[156]++;
            DateTimeParser time = new DateTimeFormatterBuilder()
                .appendLiteral('T')
                .append(timeElementParser())
                .toParser();
            ldotp = new DateTimeFormatterBuilder()
                .append(dateElementParser())
                .appendOptional(time)
                .toFormatter().withZoneUTC();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[157]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[110]++;}
        return ldotp;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a formatter for a full date as four digit year, two digit month
     * of year, and two digit day of month (yyyy-MM-dd).
     * 
     * @return a formatter for yyyy-MM-dd
     */
    public static DateTimeFormatter date() {
        return yearMonthDay();
    }

    /**
     * Returns a formatter for a two digit hour of day, two digit minute of
     * hour, two digit second of minute, three digit fraction of second, and
     * time zone offset (HH:mm:ss.SSSZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for HH:mm:ss.SSSZZ
     */
    public static DateTimeFormatter time() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[158]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((t == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[111]++;
            t = new DateTimeFormatterBuilder()
                .append(hourMinuteSecondFraction())
                .append(offsetElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[159]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[112]++;}
        return t;
    }

    /**
     * Returns a formatter for a two digit hour of day, two digit minute of
     * hour, two digit second of minute, and time zone offset (HH:mm:ssZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for HH:mm:ssZZ
     */
    public static DateTimeFormatter timeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[160]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((tx == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[113]++;
            tx = new DateTimeFormatterBuilder()
                .append(hourMinuteSecond())
                .append(offsetElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[161]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[114]++;}
        return tx;
    }

    /**
     * Returns a formatter for a two digit hour of day, two digit minute of
     * hour, two digit second of minute, three digit fraction of second, and
     * time zone offset prefixed by 'T' ('T'HH:mm:ss.SSSZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for 'T'HH:mm:ss.SSSZZ
     */
    public static DateTimeFormatter tTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[162]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((tt == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[115]++;
            tt = new DateTimeFormatterBuilder()
                .append(literalTElement())
                .append(time())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[163]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[116]++;}
        return tt;
    }

    /**
     * Returns a formatter for a two digit hour of day, two digit minute of
     * hour, two digit second of minute, and time zone offset prefixed
     * by 'T' ('T'HH:mm:ssZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for 'T'HH:mm:ssZZ
     */
    public static DateTimeFormatter tTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[164]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((ttx == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[117]++;
            ttx = new DateTimeFormatterBuilder()
                .append(literalTElement())
                .append(timeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[165]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[118]++;}
        return ttx;
    }

    /**
     * Returns a formatter that combines a full date and time, separated by a 'T'
     * (yyyy-MM-dd'T'HH:mm:ss.SSSZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyy-MM-dd'T'HH:mm:ss.SSSZZ
     */
    public static DateTimeFormatter dateTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[166]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((dt == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[119]++;
            dt = new DateTimeFormatterBuilder()
                .append(date())
                .append(tTime())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[167]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[120]++;}
        return dt;
    }

    /**
     * Returns a formatter that combines a full date and time without millis,
     * separated by a 'T' (yyyy-MM-dd'T'HH:mm:ssZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyy-MM-dd'T'HH:mm:ssZZ
     */
    public static DateTimeFormatter dateTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[168]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((dtx == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[121]++;
            dtx = new DateTimeFormatterBuilder()
                .append(date())
                .append(tTimeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[169]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[122]++;}
        return dtx;
    }

    /**
     * Returns a formatter for a full ordinal date, using a four
     * digit year and three digit dayOfYear (yyyy-DDD).
     * 
     * @return a formatter for yyyy-DDD
     * @since 1.1
     */
    public static DateTimeFormatter ordinalDate() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[170]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((od == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[123]++;
            od = new DateTimeFormatterBuilder()
                .append(yearElement())
                .append(dayOfYearElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[171]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[124]++;}
        return od;
    }

    /**
     * Returns a formatter for a full ordinal date and time, using a four
     * digit year and three digit dayOfYear (yyyy-DDD'T'HH:mm:ss.SSSZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyy-DDD'T'HH:mm:ss.SSSZZ
     * @since 1.1
     */
    public static DateTimeFormatter ordinalDateTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[172]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((odt == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[125]++;
            odt = new DateTimeFormatterBuilder()
                .append(ordinalDate())
                .append(tTime())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[173]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[126]++;}
        return odt;
    }

    /**
     * Returns a formatter for a full ordinal date and time without millis,
     * using a four digit year and three digit dayOfYear (yyyy-DDD'T'HH:mm:ssZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyy-DDD'T'HH:mm:ssZZ
     * @since 1.1
     */
    public static DateTimeFormatter ordinalDateTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[174]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((odtx == null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[127]++;
            odtx = new DateTimeFormatterBuilder()
                .append(ordinalDate())
                .append(tTimeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[175]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[128]++;}
        return odtx;
    }

    /**
     * Returns a formatter for a full date as four digit weekyear, two digit
     * week of weekyear, and one digit day of week (xxxx-'W'ww-e).
     * 
     * @return a formatter for xxxx-'W'ww-e
     */
    public static DateTimeFormatter weekDate() {
        return weekyearWeekDay();
    }

    /**
     * Returns a formatter that combines a full weekyear date and time,
     * separated by a 'T' (xxxx-'W'ww-e'T'HH:mm:ss.SSSZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for xxxx-'W'ww-e'T'HH:mm:ss.SSSZZ
     */
    public static DateTimeFormatter weekDateTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[176]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((wdt == null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[129]++;
            wdt = new DateTimeFormatterBuilder()
                .append(weekDate())
                .append(tTime())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[177]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[130]++;}
        return wdt;
    }

    /**
     * Returns a formatter that combines a full weekyear date and time without millis,
     * separated by a 'T' (xxxx-'W'ww-e'T'HH:mm:ssZZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HH:mm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for xxxx-'W'ww-e'T'HH:mm:ssZZ
     */
    public static DateTimeFormatter weekDateTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[178]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((wdtx == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[131]++;
            wdtx = new DateTimeFormatterBuilder()
                .append(weekDate())
                .append(tTimeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[179]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[132]++;}
        return wdtx;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a basic formatter for a full date as four digit year, two digit
     * month of year, and two digit day of month (yyyyMMdd).
     * 
     * @return a formatter for yyyyMMdd
     */
    public static DateTimeFormatter basicDate() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[180]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((bd == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[133]++;
            bd = new DateTimeFormatterBuilder()
                .appendYear(4, 4)
                .appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2)
                .appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[181]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[134]++;}
        return bd;
    }

    /**
     * Returns a basic formatter for a two digit hour of day, two digit minute
     * of hour, two digit second of minute, three digit millis, and time zone
     * offset (HHmmss.SSSZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for HHmmss.SSSZ
     */
    public static DateTimeFormatter basicTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[182]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((bt == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[135]++;
            bt = new DateTimeFormatterBuilder()
                .appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2)
                .appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2)
                .appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2)
                .appendLiteral('.')
                .appendFractionOfSecond(3, 9)
                .appendTimeZoneOffset("Z", false, 2, 2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[183]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[136]++;}
        return bt;
    }

    /**
     * Returns a basic formatter for a two digit hour of day, two digit minute
     * of hour, two digit second of minute, and time zone offset (HHmmssZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for HHmmssZ
     */
    public static DateTimeFormatter basicTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[184]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((btx == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[137]++;
            btx = new DateTimeFormatterBuilder()
                .appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2)
                .appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2)
                .appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2)
                .appendTimeZoneOffset("Z", false, 2, 2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[185]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[138]++;}
        return btx;
    }

    /**
     * Returns a basic formatter for a two digit hour of day, two digit minute
     * of hour, two digit second of minute, three digit millis, and time zone
     * offset prefixed by 'T' ('T'HHmmss.SSSZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for 'T'HHmmss.SSSZ
     */
    public static DateTimeFormatter basicTTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[186]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((btt == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[139]++;
            btt = new DateTimeFormatterBuilder()
                .append(literalTElement())
                .append(basicTime())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[187]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[140]++;}
        return btt;
    }

    /**
     * Returns a basic formatter for a two digit hour of day, two digit minute
     * of hour, two digit second of minute, and time zone offset prefixed by 'T'
     * ('T'HHmmssZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for 'T'HHmmssZ
     */
    public static DateTimeFormatter basicTTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[188]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((bttx == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[141]++;
            bttx = new DateTimeFormatterBuilder()
                .append(literalTElement())
                .append(basicTimeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[189]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[142]++;}
        return bttx;
    }

    /**
     * Returns a basic formatter that combines a basic date and time, separated
     * by a 'T' (yyyyMMdd'T'HHmmss.SSSZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyyMMdd'T'HHmmss.SSSZ
     */
    public static DateTimeFormatter basicDateTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[190]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((bdt == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[143]++;
            bdt = new DateTimeFormatterBuilder()
                .append(basicDate())
                .append(basicTTime())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[191]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[144]++;}
        return bdt;
    }

    /**
     * Returns a basic formatter that combines a basic date and time without millis,
     * separated by a 'T' (yyyyMMdd'T'HHmmssZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyyMMdd'T'HHmmssZ
     */
    public static DateTimeFormatter basicDateTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[192]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((bdtx == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[145]++;
            bdtx = new DateTimeFormatterBuilder()
                .append(basicDate())
                .append(basicTTimeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[193]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[146]++;}
        return bdtx;
    }

    /**
     * Returns a formatter for a full ordinal date, using a four
     * digit year and three digit dayOfYear (yyyyDDD).
     * 
     * @return a formatter for yyyyDDD
     * @since 1.1
     */
    public static DateTimeFormatter basicOrdinalDate() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[194]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((bod == null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[147]++;
            bod = new DateTimeFormatterBuilder()
                .appendYear(4, 4)
                .appendFixedDecimal(DateTimeFieldType.dayOfYear(), 3)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[195]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[148]++;}
        return bod;
    }

    /**
     * Returns a formatter for a full ordinal date and time, using a four
     * digit year and three digit dayOfYear (yyyyDDD'T'HHmmss.SSSZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyyDDD'T'HHmmss.SSSZ
     * @since 1.1
     */
    public static DateTimeFormatter basicOrdinalDateTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[196]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((bodt == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[149]++;
            bodt = new DateTimeFormatterBuilder()
                .append(basicOrdinalDate())
                .append(basicTTime())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[197]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[150]++;}
        return bodt;
    }

    /**
     * Returns a formatter for a full ordinal date and time without millis,
     * using a four digit year and three digit dayOfYear (yyyyDDD'T'HHmmssZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for yyyyDDD'T'HHmmssZ
     * @since 1.1
     */
    public static DateTimeFormatter basicOrdinalDateTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[198]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((bodtx == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[151]++;
            bodtx = new DateTimeFormatterBuilder()
                .append(basicOrdinalDate())
                .append(basicTTimeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[199]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[152]++;}
        return bodtx;
    }

    /**
     * Returns a basic formatter for a full date as four digit weekyear, two
     * digit week of weekyear, and one digit day of week (xxxx'W'wwe).
     * 
     * @return a formatter for xxxx'W'wwe
     */
    public static DateTimeFormatter basicWeekDate() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[200]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((bwd == null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[153]++;
            bwd = new DateTimeFormatterBuilder()
                .appendWeekyear(4, 4)
                .appendLiteral('W')
                .appendFixedDecimal(DateTimeFieldType.weekOfWeekyear(), 2)
                .appendFixedDecimal(DateTimeFieldType.dayOfWeek(), 1)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[201]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[154]++;}
        return bwd;
    }

    /**
     * Returns a basic formatter that combines a basic weekyear date and time,
     * separated by a 'T' (xxxx'W'wwe'T'HHmmss.SSSZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for xxxx'W'wwe'T'HHmmss.SSSZ
     */
    public static DateTimeFormatter basicWeekDateTime() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[202]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((bwdt == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[155]++;
            bwdt = new DateTimeFormatterBuilder()
                .append(basicWeekDate())
                .append(basicTTime())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[203]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[156]++;}
        return bwdt;
    }

    /**
     * Returns a basic formatter that combines a basic weekyear date and time
     * without millis, separated by a 'T' (xxxx'W'wwe'T'HHmmssZ).
     * The time zone offset is 'Z' for zero, and of the form '\u00b1HHmm' for non-zero.
     * The parser is strict by default, thus time string {@code 24:00} cannot be parsed.
     * 
     * @return a formatter for xxxx'W'wwe'T'HHmmssZ
     */
    public static DateTimeFormatter basicWeekDateTimeNoMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[204]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((bwdtx == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[157]++;
            bwdtx = new DateTimeFormatterBuilder()
                .append(basicWeekDate())
                .append(basicTTimeNoMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[205]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[158]++;}
        return bwdtx;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a formatter for a four digit year. (yyyy)
     * 
     * @return a formatter for yyyy
     */
    public static DateTimeFormatter year() {
        return yearElement();
    }

    /**
     * Returns a formatter for a four digit year and two digit month of
     * year. (yyyy-MM)
     * 
     * @return a formatter for yyyy-MM
     */
    public static DateTimeFormatter yearMonth() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[206]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((ym == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[159]++;
            ym = new DateTimeFormatterBuilder()
                .append(yearElement())
                .append(monthElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[207]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[160]++;}
        return ym;
    }

    /**
     * Returns a formatter for a four digit year, two digit month of year, and
     * two digit day of month. (yyyy-MM-dd)
     * 
     * @return a formatter for yyyy-MM-dd
     */
    public static DateTimeFormatter yearMonthDay() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[208]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((ymd == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[161]++;
            ymd = new DateTimeFormatterBuilder()
                .append(yearElement())
                .append(monthElement())
                .append(dayOfMonthElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[209]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[162]++;}
        return ymd;
    }

    /**
     * Returns a formatter for a four digit weekyear. (xxxx)
     * 
     * @return a formatter for xxxx
     */
    public static DateTimeFormatter weekyear() {
        return weekyearElement();
    }

    /**
     * Returns a formatter for a four digit weekyear and two digit week of
     * weekyear. (xxxx-'W'ww)
     * 
     * @return a formatter for xxxx-'W'ww
     */
    public static DateTimeFormatter weekyearWeek() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[210]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((ww == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[163]++;
            ww = new DateTimeFormatterBuilder()
                .append(weekyearElement())
                .append(weekElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[211]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[164]++;}
        return ww;
    }

    /**
     * Returns a formatter for a four digit weekyear, two digit week of
     * weekyear, and one digit day of week. (xxxx-'W'ww-e)
     * 
     * @return a formatter for xxxx-'W'ww-e
     */
    public static DateTimeFormatter weekyearWeekDay() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[212]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((wwd == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[165]++;
            wwd = new DateTimeFormatterBuilder()
                .append(weekyearElement())
                .append(weekElement())
                .append(dayOfWeekElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[213]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[166]++;}
        return wwd;
    }

    /**
     * Returns a formatter for a two digit hour of day. (HH)
     * 
     * @return a formatter for HH
     */
    public static DateTimeFormatter hour() {
        return hourElement();
    }

    /**
     * Returns a formatter for a two digit hour of day and two digit minute of
     * hour. (HH:mm)
     * 
     * @return a formatter for HH:mm
     */
    public static DateTimeFormatter hourMinute() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[214]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((hm == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[167]++;
            hm = new DateTimeFormatterBuilder()
                .append(hourElement())
                .append(minuteElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[215]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[168]++;}
        return hm;
    }

    /**
     * Returns a formatter for a two digit hour of day, two digit minute of
     * hour, and two digit second of minute. (HH:mm:ss)
     * 
     * @return a formatter for HH:mm:ss
     */
    public static DateTimeFormatter hourMinuteSecond() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[216]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((hms == null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[169]++;
            hms = new DateTimeFormatterBuilder()
                .append(hourElement())
                .append(minuteElement())
                .append(secondElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[217]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[170]++;}
        return hms;
    }

    /**
     * Returns a formatter for a two digit hour of day, two digit minute of
     * hour, two digit second of minute, and three digit fraction of
     * second (HH:mm:ss.SSS). Parsing will parse up to 3 fractional second
     * digits.
     * 
     * @return a formatter for HH:mm:ss.SSS
     */
    public static DateTimeFormatter hourMinuteSecondMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[218]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((hmsl == null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[171]++;
            hmsl = new DateTimeFormatterBuilder()
                .append(hourElement())
                .append(minuteElement())
                .append(secondElement())
                .appendLiteral('.')
                .appendFractionOfSecond(3, 3)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[219]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[172]++;}
        return hmsl;
    }

    /**
     * Returns a formatter for a two digit hour of day, two digit minute of
     * hour, two digit second of minute, and three digit fraction of
     * second (HH:mm:ss.SSS). Parsing will parse up to 9 fractional second
     * digits, throwing away all except the first three.
     * 
     * @return a formatter for HH:mm:ss.SSS
     */
    public static DateTimeFormatter hourMinuteSecondFraction() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[220]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((hmsf == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[173]++;
            hmsf = new DateTimeFormatterBuilder()
                .append(hourElement())
                .append(minuteElement())
                .append(secondElement())
                .append(fractionElement())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[221]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[174]++;}
        return hmsf;
    }

    /**
     * Returns a formatter that combines a full date and two digit hour of
     * day. (yyyy-MM-dd'T'HH)
     * 
     * @return a formatter for yyyy-MM-dd'T'HH
     */
    public static DateTimeFormatter dateHour() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[222]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((dh == null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[175]++;
            dh = new DateTimeFormatterBuilder()
                .append(date())
                .append(literalTElement())
                .append(hour())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[223]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[176]++;}
        return dh;
    }

    /**
     * Returns a formatter that combines a full date, two digit hour of day,
     * and two digit minute of hour. (yyyy-MM-dd'T'HH:mm)
     * 
     * @return a formatter for yyyy-MM-dd'T'HH:mm
     */
    public static DateTimeFormatter dateHourMinute() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[224]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((dhm == null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[177]++;
            dhm = new DateTimeFormatterBuilder()
                .append(date())
                .append(literalTElement())
                .append(hourMinute())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[225]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[178]++;}
        return dhm;
    }

    /**
     * Returns a formatter that combines a full date, two digit hour of day,
     * two digit minute of hour, and two digit second of
     * minute. (yyyy-MM-dd'T'HH:mm:ss)
     * 
     * @return a formatter for yyyy-MM-dd'T'HH:mm:ss
     */
    public static DateTimeFormatter dateHourMinuteSecond() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[226]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((dhms == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[179]++;
            dhms = new DateTimeFormatterBuilder()
                .append(date())
                .append(literalTElement())
                .append(hourMinuteSecond())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[227]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[180]++;}
        return dhms;
    }

    /**
     * Returns a formatter that combines a full date, two digit hour of day,
     * two digit minute of hour, two digit second of minute, and three digit
     * fraction of second (yyyy-MM-dd'T'HH:mm:ss.SSS). Parsing will parse up
     * to 3 fractional second digits.
     * 
     * @return a formatter for yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    public static DateTimeFormatter dateHourMinuteSecondMillis() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[228]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((dhmsl == null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[181]++;
            dhmsl = new DateTimeFormatterBuilder()
                .append(date())
                .append(literalTElement())
                .append(hourMinuteSecondMillis())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[229]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[182]++;}
        return dhmsl;
    }

    /**
     * Returns a formatter that combines a full date, two digit hour of day,
     * two digit minute of hour, two digit second of minute, and three digit
     * fraction of second (yyyy-MM-dd'T'HH:mm:ss.SSS). Parsing will parse up
     * to 9 fractional second digits, throwing away all except the first three.
     * 
     * @return a formatter for yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    public static DateTimeFormatter dateHourMinuteSecondFraction() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[230]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((dhmsf == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[183]++;
            dhmsf = new DateTimeFormatterBuilder()
                .append(date())
                .append(literalTElement())
                .append(hourMinuteSecondFraction())
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[231]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[184]++;}
        return dhmsf;
    }

    //-----------------------------------------------------------------------
    private static DateTimeFormatter yearElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[232]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((ye == null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[185]++;
            ye = new DateTimeFormatterBuilder()
                .appendYear(4, 9)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[233]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[186]++;}
        return ye;
    }

    private static DateTimeFormatter monthElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[234]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((mye == null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[187]++;
            mye = new DateTimeFormatterBuilder()
                .appendLiteral('-')
                .appendMonthOfYear(2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[235]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[188]++;}
        return mye;
    }

    private static DateTimeFormatter dayOfMonthElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[236]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((dme == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[189]++;
            dme = new DateTimeFormatterBuilder()
                .appendLiteral('-')
                .appendDayOfMonth(2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[237]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[190]++;}
        return dme;
    }

    private static DateTimeFormatter weekyearElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[238]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((we == null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[191]++;
            we = new DateTimeFormatterBuilder()
                .appendWeekyear(4, 9)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[239]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[192]++;}
        return we;
    }

    private static DateTimeFormatter weekElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[240]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((wwe == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[193]++;
            wwe = new DateTimeFormatterBuilder()
                .appendLiteral("-W")
                .appendWeekOfWeekyear(2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[241]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[194]++;}
        return wwe;
    }

    private static DateTimeFormatter dayOfWeekElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[242]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((dwe == null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[195]++;
            dwe = new DateTimeFormatterBuilder()
                .appendLiteral('-')
                .appendDayOfWeek(1)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[243]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[196]++;}
        return dwe;
    }

    private static DateTimeFormatter dayOfYearElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[244]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((dye == null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[197]++;
            dye = new DateTimeFormatterBuilder()
                .appendLiteral('-')
                .appendDayOfYear(3)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[245]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[198]++;}
        return dye;
    }
    
    private static DateTimeFormatter literalTElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[246]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((lte == null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[199]++;
            lte = new DateTimeFormatterBuilder()
                .appendLiteral('T')
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[247]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[200]++;}
        return lte;
    }

    private static DateTimeFormatter hourElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[248]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((hde == null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[201]++;
            hde = new DateTimeFormatterBuilder()
                .appendHourOfDay(2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[249]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[202]++;}
        return hde;
    }

    private static DateTimeFormatter minuteElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[250]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((mhe == null) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[203]++;
            mhe = new DateTimeFormatterBuilder()
                .appendLiteral(':')
                .appendMinuteOfHour(2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[251]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[204]++;}
        return mhe;
    }

    private static DateTimeFormatter secondElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[252]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((sme == null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[205]++;
            sme = new DateTimeFormatterBuilder()
                .appendLiteral(':')
                .appendSecondOfMinute(2)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[253]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[206]++;}
        return sme;
    }

    private static DateTimeFormatter fractionElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[254]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((fse == null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[207]++;
            fse = new DateTimeFormatterBuilder()
                .appendLiteral('.')
                // Support parsing up to nanosecond precision even though
                // those extra digits will be dropped.
                .appendFractionOfSecond(3, 9)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[255]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[208]++;}
        return fse;
    }

    private static DateTimeFormatter offsetElement() {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[256]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((ze == null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[209]++;
            ze = new DateTimeFormatterBuilder()
                .appendTimeZoneOffset("Z", true, 2, 4)
                .toFormatter();
CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.statements[257]++;

        } else {
  CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx.branches[210]++;}
        return ze;
    }

}

class CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx ());
  }
    public static long[] statements = new long[258];
    public static long[] branches = new long[211];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[105];
  static {
    final String SECTION_NAME = "org.joda.time.format.ISODateTimeFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,2,1,3,2,3,1,1,3,3,1,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 104; i++) {
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

  public CodeCoverCoverageCounter$c14is2jfwv0dq5os4ma6bpsi5dmzsl53kx () {
    super("org.joda.time.format.ISODateTimeFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 257; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 210; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 104; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.ISODateTimeFormat.java");
      for (int i = 1; i <= 257; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 210; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 104; i++) {
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

