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
 * -----------------------
 * RelativeDateFormat.java
 * -----------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 01-Nov-2006 : Version 1 (DG);
 * 23-Nov-2006 : Added argument checks, updated equals(), added clone() and 
 *               hashCode() (DG);
 *
 */
package org.jfree.chart.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A formatter that formats dates to show the elapsed time relative to some
 * base date.
 *
 * @since 1.0.3
 */
public class RelativeDateFormat extends DateFormat {
  static {
    CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.ping();
  }

    
    /** The base milliseconds for the elapsed time calculation. */
    private long baseMillis;
    
    /**
     * A flag that controls whether or not a zero day count is displayed.
     */
    private boolean showZeroDays;
    
    /** 
     * A formatter for the day count (most likely not critical until the
     * day count exceeds 999). 
     */
    private NumberFormat dayFormatter;
    
    /**
     * A string appended after the day count.
     */
    private String daySuffix;
    
    /**
     * A string appended after the hours.
     */
    private String hourSuffix;
    
    /**
     * A string appended after the minutes.
     */
    private String minuteSuffix;
    
    /**
     * A formatter for the seconds (and milliseconds).
     */
    private NumberFormat secondFormatter;
    
    /**
     * A string appended after the seconds.
     */
    private String secondSuffix;

    /**
     * A constant for the number of milliseconds in one hour.
     */
    private static long MILLISECONDS_IN_ONE_HOUR = 60 * 60 * 1000L;
  static {
    CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[1]++;
  }

    /**
     * A constant for the number of milliseconds in one day.
     */
    private static long MILLISECONDS_IN_ONE_DAY = 24 * MILLISECONDS_IN_ONE_HOUR;
  static {
    CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[2]++;
  }
    
    /**
     * Creates a new instance.
     */
    public RelativeDateFormat() {
        this(0L);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[3]++;  
    }
    
    /**
     * Creates a new instance.
     * 
     * @param time  the date/time (<code>null</code> not permitted).
     */
    public RelativeDateFormat(Date time) {
        this(time.getTime());
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[4]++;
    }
    
    /**
     * Creates a new instance.
     * 
     * @param baseMillis  the time zone (<code>null</code> not permitted).
     */
    public RelativeDateFormat(long baseMillis) {
        super();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[5]++;        
        this.baseMillis = baseMillis;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[6]++;
        this.showZeroDays = false;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[7]++;
        this.dayFormatter = NumberFormat.getInstance();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[8]++;
        this.daySuffix = "d";
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[9]++;
        this.hourSuffix = "h";
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[10]++;
        this.minuteSuffix = "m";
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[11]++;
        this.secondFormatter = NumberFormat.getNumberInstance();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[12]++;
        this.secondFormatter.setMaximumFractionDigits(3);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[13]++;
        this.secondFormatter.setMinimumFractionDigits(3);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[14]++;
        this.secondSuffix = "s";
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[15]++;

        // we don't use the calendar or numberFormat fields, but equals(Object) 
        // is failing without them being non-null
        this.calendar = new GregorianCalendar();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[16]++;
        this.numberFormat = new DecimalFormat("0");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[17]++;    
    }
    
    /**
     * Returns the base date/time used to calculate the elapsed time for 
     * display.
     * 
     * @return The base date/time in milliseconds since 1-Jan-1970.
     * 
     * @see #setBaseMillis(long)
     */
    public long getBaseMillis() {
        return this.baseMillis;
    }
    
    /**
     * Sets the base date/time used to calculate the elapsed time for display.  
     * This should be specified in milliseconds using the same encoding as
     * <code>java.util.Date</code>.
     * 
     * @param baseMillis  the base date/time in milliseconds.
     * 
     * @see #getBaseMillis()
     */
    public void setBaseMillis(long baseMillis) {
        this.baseMillis = baseMillis;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[18]++;
    }
    
    /**
     * Returns the flag that controls whether or not zero day counts are 
     * shown in the formatted output.
     * 
     * @return The flag.
     * 
     * @see #setShowZeroDays(boolean)
     */
    public boolean getShowZeroDays() {
        return this.showZeroDays;
    }
    
    /**
     * Sets the flag that controls whether or not zero day counts are shown
     * in the formatted output.
     * 
     * @param show  the flag.
     * 
     * @see #getShowZeroDays()
     */
    public void setShowZeroDays(boolean show) {
        this.showZeroDays = show;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[19]++;
    }
    
    /**
     * Returns the string that is appended to the day count.
     * 
     * @return The string.
     * 
     * @see #setDaySuffix(String)
     */
    public String getDaySuffix() {
        return this.daySuffix;
    }
    
    /**
     * Sets the string that is appended to the day count.
     * 
     * @param suffix  the suffix (<code>null</code> not permitted).
     * 
     * @see #getDaySuffix()
     */
    public void setDaySuffix(String suffix) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[20]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((suffix == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[1]++;
            throw new IllegalArgumentException("Null 'suffix' argument.");

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[2]++;}
        this.daySuffix = suffix;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[21]++;
    }

    /**
     * Returns the string that is appended to the hour count.
     * 
     * @return The string.
     * 
     * @see #setHourSuffix(String)
     */
    public String getHourSuffix() {
        return this.hourSuffix;
    }
    
    /**
     * Sets the string that is appended to the hour count.
     * 
     * @param suffix  the suffix (<code>null</code> not permitted).
     * 
     * @see #getHourSuffix()
     */
    public void setHourSuffix(String suffix) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((suffix == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[3]++;
            throw new IllegalArgumentException("Null 'suffix' argument.");

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[4]++;}
        this.hourSuffix = suffix;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[23]++;
    }

    /**
     * Returns the string that is appended to the minute count.
     * 
     * @return The string.
     * 
     * @see #setMinuteSuffix(String)
     */
    public String getMinuteSuffix() {
        return this.minuteSuffix;
    }
    
    /**
     * Sets the string that is appended to the minute count.
     * 
     * @param suffix  the suffix (<code>null</code> not permitted).
     * 
     * @see #getMinuteSuffix()
     */
    public void setMinuteSuffix(String suffix) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((suffix == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[5]++;
            throw new IllegalArgumentException("Null 'suffix' argument.");

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[6]++;}
        this.minuteSuffix = suffix;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[25]++;
    }

    /**
     * Returns the string that is appended to the second count.
     * 
     * @return The string.
     * 
     * @see #setSecondSuffix(String)
     */
    public String getSecondSuffix() {
        return this.secondSuffix;
    }
    
    /**
     * Sets the string that is appended to the second count.
     * 
     * @param suffix  the suffix (<code>null</code> not permitted).
     * 
     * @see #getSecondSuffix()
     */
    public void setSecondSuffix(String suffix) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((suffix == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[7]++;
            throw new IllegalArgumentException("Null 'suffix' argument.");

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[8]++;}
        this.secondSuffix = suffix;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[27]++;
    }
    
    /**
     * Sets the formatter for the seconds and milliseconds.
     * 
     * @param formatter  the formatter (<code>null</code> not permitted).
     */
    public void setSecondFormatter(NumberFormat formatter) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[9]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[10]++;}
        this.secondFormatter = formatter;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[29]++;
    }

    /**
     * Formats the given date as the amount of elapsed time (relative to the
     * base date specified in the constructor).
     * 
     * @param date  the date.
     * @param toAppendTo  the string buffer.
     * @param fieldPosition  the field position.
     * 
     * @return The formatted date.
     */
    public StringBuffer format(Date date, StringBuffer toAppendTo,
                               FieldPosition fieldPosition) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[30]++;
        long currentMillis = date.getTime();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[31]++;
        long elapsed = currentMillis - this.baseMillis;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[32]++;
        
        long days = elapsed / MILLISECONDS_IN_ONE_DAY;
        elapsed = elapsed - (days * MILLISECONDS_IN_ONE_DAY);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[33]++;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[34]++;
        long hours = elapsed / MILLISECONDS_IN_ONE_HOUR;
        elapsed = elapsed - (hours * MILLISECONDS_IN_ONE_HOUR);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[35]++;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[36]++;
        long minutes = elapsed / 60000L;
        elapsed = elapsed - (minutes * 60000L);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[37]++;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[38]++;
        double seconds = elapsed / 1000.0;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((days != 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.showZeroDays) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[11]++;
            toAppendTo.append(this.dayFormatter.format(days) + getDaySuffix());
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[40]++;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[12]++;}
        toAppendTo.append(String.valueOf(hours) + getHourSuffix());
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[41]++;
        toAppendTo.append(String.valueOf(minutes) + getMinuteSuffix());
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[42]++;
        toAppendTo.append(this.secondFormatter.format(seconds) 
                + getSecondSuffix());
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[43]++;
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
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[14]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof RelativeDateFormat) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[16]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[18]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[47]++;
        RelativeDateFormat that = (RelativeDateFormat) obj;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.baseMillis != that.baseMillis) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[20]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.showZeroDays != that.showZeroDays) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[22]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.daySuffix.equals(that.daySuffix)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[24]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.hourSuffix.equals(that.hourSuffix)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[26]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.minuteSuffix.equals(that.minuteSuffix)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[28]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.secondSuffix.equals(that.secondSuffix)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[30]++;}
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[54]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.secondFormatter.equals(that.secondFormatter)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.branches[32]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[55]++;
        int result = 193;
        result = 37 * result 
                + (int) (this.baseMillis ^ (this.baseMillis >>> 32));
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[56]++;
        result = 37 * result + this.daySuffix.hashCode();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[57]++;
        result = 37 * result + this.hourSuffix.hashCode();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[58]++;
        result = 37 * result + this.minuteSuffix.hashCode();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[59]++;
        result = 37 * result + this.secondSuffix.hashCode();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[60]++;
        result = 37 * result + this.secondFormatter.hashCode();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[61]++;
        return result;
    }

    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     */
    public Object clone() {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[62]++;
        RelativeDateFormat clone = (RelativeDateFormat) super.clone();
        clone.dayFormatter = (NumberFormat) this.dayFormatter.clone();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[63]++;
        clone.secondFormatter = (NumberFormat) this.secondFormatter.clone();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[64]++;
        return clone;
    }
    
    /**
     * Some test code.
     * 
     * @param args  ignored.
     */
    public static void main(String[] args) {
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[65]++;
        GregorianCalendar c0 = new GregorianCalendar(2006, 10, 1, 0, 0, 0);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[66]++;
        GregorianCalendar c1 = new GregorianCalendar(2006, 10, 1, 11, 37, 43);
        c1.set(Calendar.MILLISECOND, 123);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[67]++;
        
        System.out.println("Default: ");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[68]++;
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[69]++;
        RelativeDateFormat rdf = new RelativeDateFormat(c0.getTimeInMillis());
        System.out.println(rdf.format(c1.getTime()));
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[70]++;
        System.out.println();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[71]++;
        
        System.out.println("Hide milliseconds: ");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[72]++;
        rdf.setSecondFormatter(new DecimalFormat("0"));
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[73]++;
        System.out.println(rdf.format(c1.getTime()));
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[74]++;        
        System.out.println();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[75]++;

        System.out.println("Show zero day output: ");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[76]++;
        rdf.setShowZeroDays(true);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[77]++;
        System.out.println(rdf.format(c1.getTime()));
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[78]++;
        System.out.println();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[79]++;
        
        System.out.println("Alternative suffixes: ");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[80]++;
        rdf.setShowZeroDays(false);
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[81]++;
        rdf.setDaySuffix(":");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[82]++;
        rdf.setHourSuffix(":");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[83]++;
        rdf.setMinuteSuffix(":");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[84]++;
        rdf.setSecondSuffix("");
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[85]++;
        System.out.println(rdf.format(c1.getTime()));
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[86]++;
        System.out.println();
CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh.statements[87]++;
    }
}

class CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.jfree.chart.util.RelativeDateFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 16; i++) {
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

  public CodeCoverCoverageCounter$2o50mw23huup5r0wxvkei4e5x8zix5ym60kh () {
    super("org.jfree.chart.util.RelativeDateFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.util.RelativeDateFormat.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
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

