/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * --------------------
 * MonthDateFormat.java
 * --------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 10-May-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.axis;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.jfree.data.time.Month;

/**
 * A formatter that formats dates to show the initial letter(s) of the month
 * name and, as an option, the year for the first or last month of each year.
 */
public class MonthDateFormat extends DateFormat {
  static {
    CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.ping();
  }

    
    /** The symbols used for the months. */
    private String[] months;
    
    /** Flags that control which months will have the year appended. */
    private boolean[] showYear;
    
    /** The year formatter. */
    private DateFormat yearFormatter;
    
    /**
     * Creates a new instance for the default time zone.
     */
    public MonthDateFormat() {
        this(TimeZone.getDefault());
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[1]++;  
    }
    
    /**
     * Creates a new instance for the specified time zone.
     * 
     * @param zone  the time zone (<code>null</code> not permitted).
     */
    public MonthDateFormat(TimeZone zone) {
        this(zone, Locale.getDefault(), 1, true, false);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[2]++;
    }
    
    /**
     * Creates a new instance for the specified time zone.
     * 
     * @param locale  the locale used to obtain the month 
     *                names (<code>null</code> not permitted).
     */
    public MonthDateFormat(Locale locale) {
        this(TimeZone.getDefault(), locale, 1, true, false);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[3]++;
    }
    
    /**
     * Creates a new instance for the specified time zone.
     * 
     * @param zone  the time zone (<code>null</code> not permitted).
     * @param chars  the maximum number of characters to use from the month
     *               names (that are obtained from the date symbols of the
     *               default locale).  If this value is <= 0, the entire 
     *               month name is used in each case.
     */
    public MonthDateFormat(TimeZone zone, int chars) {
        this(zone, Locale.getDefault(), chars, true, false);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[4]++;
    }
    
    /**
     * Creates a new instance for the specified time zone.
     * 
     * @param locale  the locale (<code>null</code> not permitted).
     * @param chars  the maximum number of characters to use from the month
     *               names (that are obtained from the date symbols of the
     *               default locale).  If this value is <= 0, the entire 
     *               month name is used in each case.
     */
    public MonthDateFormat(Locale locale, int chars) {
        this(TimeZone.getDefault(), locale, chars, true, false);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[5]++;
    }

    /**
     * Creates a new formatter.
     * 
     * @param zone  the time zone used to extract the month and year from dates
     *              passed to this formatter (<code>null</code> not permitted).
     * @param locale  the locale used to determine the month names 
     *                (<code>null</code> not permitted).
     * @param chars  the maximum number of characters to use from the month 
     *               names, or zero to indicate that the entire month name 
     *               should be used.
     * @param showYearForJan  a flag that controls whether or not the year is
     *                        appended to the symbol for the first month of
     *                        each year.
     * @param showYearForDec  a flag that controls whether or not the year is
     *                        appended to the symbol for the last month of
     *                        each year.
     */
    public MonthDateFormat(TimeZone zone, Locale locale, int chars, 
                           boolean showYearForJan, boolean showYearForDec) {
        this(zone, locale, chars, new boolean[] {showYearForJan, false, false, 
            false, false, false, false, false, false, false, false, false,
            showYearForDec}, new SimpleDateFormat("yy"));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[6]++;       
    }
    
    /**
     * Creates a new formatter.
     * 
     * @param zone  the time zone used to extract the month and year from dates
     *              passed to this formatter (<code>null</code> not permitted).
     * @param locale  the locale used to determine the month names 
     *                (<code>null</code> not permitted).
     * @param chars  the maximum number of characters to use from the month 
     *               names, or zero to indicate that the entire month name 
     *               should be used.
     * @param showYear  an array of flags that control whether or not the
     *                  year is displayed for a particular month.
     * @param yearFormatter  the year formatter.
     */
    public MonthDateFormat(TimeZone zone, Locale locale, int chars, 
                           boolean[] showYear, DateFormat yearFormatter) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[1]++;
            throw new IllegalArgumentException("Null 'locale' argument.");

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[2]++;}
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[8]++;
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[9]++;
        String[] monthsFromLocale = dfs.getMonths();
        this.months = new String[12];
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[10]++;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < 12) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.loops[1]--;
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.loops[2]--;
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.loops[3]++;
}
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chars > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[3]++;
                this.months[i] = monthsFromLocale[i].substring(0, 
                        Math.min(chars, monthsFromLocale[i].length()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[13]++;

            }
            else {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[4]++;
                this.months[i] = monthsFromLocale[i];
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[14]++;
            }
        }
        this.calendar = new GregorianCalendar(zone);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[15]++;
        this.showYear = showYear;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[16]++;
        this.yearFormatter = yearFormatter;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[17]++; 
        
        // the following is never used, but it seems that DateFormat requires
        // it to be non-null.  It isn't well covered in the spec, refer to 
        // bug parade 5061189 for more info.
        this.numberFormat = NumberFormat.getNumberInstance();
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[18]++;
    }

    /**
     * Formats the given date.
     * 
     * @param date  the date.
     * @param toAppendTo  the string buffer.
     * @param fieldPosition  the field position.
     * 
     * @return The formatted date.
     */
    public StringBuffer format(Date date, StringBuffer toAppendTo,
                               FieldPosition fieldPosition) {
        this.calendar.setTime(date);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[19]++;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[20]++;
        int month = this.calendar.get(Calendar.MONTH);
        toAppendTo.append(this.months[month]);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[21]++;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.showYear[month]) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[5]++;
            toAppendTo.append(this.yearFormatter.format(date));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[23]++;

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[6]++;}
        return toAppendTo;   
    }

    /**
     * Parses the given string (not implemented).
     * 
     * @param source  the date string.
     * @param pos  the parse position.
     * 
     * @return <code>null</code>, as this method has not been implemented.
     */
    public Date parse(String source, ParsePosition pos) {
        return null;   
    }

    /**
     * Tests this formatter for equality with an arbitrary object.
     * 
     * @param obj  the object.
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[8]++;}
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof MonthDateFormat) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[10]++;}
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[12]++;}
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[27]++;
        MonthDateFormat that = (MonthDateFormat) obj;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.months, that.months)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[14]++;}
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.showYear, that.showYear)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[16]++;}
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.yearFormatter.equals(that.yearFormatter)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.branches[18]++;}
        return true;
    }

    /**
     * Some test code.
     * 
     * @param args  ignored.
     */
    public static void main(String[] args) {
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[31]++;
        MonthDateFormat mdf = new MonthDateFormat(Locale.UK, 2);
        System.out.println("UK:");
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[32]++;
        System.out.println(mdf.format(new Month(1, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[33]++;      
        System.out.println(mdf.format(new Month(2, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[34]++;      
        System.out.println(mdf.format(new Month(3, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[35]++;      
        System.out.println(mdf.format(new Month(4, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[36]++;      
        System.out.println(mdf.format(new Month(5, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[37]++;      
        System.out.println(mdf.format(new Month(6, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[38]++;      
        System.out.println(mdf.format(new Month(7, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[39]++;      
        System.out.println(mdf.format(new Month(8, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[40]++;      
        System.out.println(mdf.format(new Month(9, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[41]++;      
        System.out.println(mdf.format(new Month(10, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[42]++;     
        System.out.println(mdf.format(new Month(11, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[43]++;     
        System.out.println(mdf.format(new Month(12, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[44]++;  
        System.out.println();
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[45]++;

        mdf = new MonthDateFormat(Locale.GERMANY, 2);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[46]++;
        System.out.println("GERMANY:");
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[47]++;
        System.out.println(mdf.format(new Month(1, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[48]++;      
        System.out.println(mdf.format(new Month(2, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[49]++;      
        System.out.println(mdf.format(new Month(3, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[50]++;      
        System.out.println(mdf.format(new Month(4, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[51]++;      
        System.out.println(mdf.format(new Month(5, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[52]++;      
        System.out.println(mdf.format(new Month(6, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[53]++;      
        System.out.println(mdf.format(new Month(7, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[54]++;      
        System.out.println(mdf.format(new Month(8, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[55]++;      
        System.out.println(mdf.format(new Month(9, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[56]++;      
        System.out.println(mdf.format(new Month(10, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[57]++;     
        System.out.println(mdf.format(new Month(11, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[58]++;     
        System.out.println(mdf.format(new Month(12, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[59]++;  
        System.out.println();
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[60]++;
        
        mdf = new MonthDateFormat(Locale.FRANCE, 2);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[61]++;
        System.out.println("FRANCE:");
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[62]++;
        System.out.println(mdf.format(new Month(1, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[63]++;      
        System.out.println(mdf.format(new Month(2, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[64]++;      
        System.out.println(mdf.format(new Month(3, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[65]++;      
        System.out.println(mdf.format(new Month(4, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[66]++;      
        System.out.println(mdf.format(new Month(5, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[67]++;      
        System.out.println(mdf.format(new Month(6, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[68]++;      
        System.out.println(mdf.format(new Month(7, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[69]++;      
        System.out.println(mdf.format(new Month(8, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[70]++;      
        System.out.println(mdf.format(new Month(9, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[71]++;      
        System.out.println(mdf.format(new Month(10, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[72]++;     
        System.out.println(mdf.format(new Month(11, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[73]++;     
        System.out.println(mdf.format(new Month(12, 2005).getStart()));
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[74]++;  
        System.out.println();
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[75]++;
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[76]++;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        sdf.setNumberFormat(null);
CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81.statements[77]++;
    }
}

class CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81 ());
  }
    public static long[] statements = new long[78];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.MonthDateFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$91mppz9mx647k7enkaal5rrgtxgyc81 () {
    super("org.jfree.chart.axis.MonthDateFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 77; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.MonthDateFormat.java");
      for (int i = 1; i <= 77; i++) {
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
    for (int i = 1; i <= 10; i++) {
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

