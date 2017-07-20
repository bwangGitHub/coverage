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
 * ---------
 * Week.java
 * ---------
 * (C) Copyright 2001-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Aimin Han;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 18-Dec-2001 : Changed order of parameters in constructor (DG);
 * 19-Dec-2001 : Added a new constructor as suggested by Paul English (DG);
 * 29-Jan-2002 : Worked on the parseWeek() method (DG);
 * 13-Feb-2002 : Fixed bug in Week(Date) constructor (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 05-Apr-2002 : Reinstated this class to the JCommon library (DG);
 * 24-Jun-2002 : Removed unnecessary main method (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 06-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 18-Oct-2002 : Changed to observe 52 or 53 weeks per year, consistent with 
 *               GregorianCalendar. Thanks to Aimin Han for the code (DG);
 * 02-Jan-2003 : Removed debug code (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package, and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * 24-May-2004 : Modified getFirstMillisecond() and getLastMillisecond() to 
 *               take account of firstDayOfWeek setting in Java's Calendar 
 *               class (DG);
 * 30-Sep-2004 : Replaced getTime().getTime() with getTimeInMillis() (DG);
 * 04-Nov-2004 : Reverted change of 30-Sep-2004, because it won't work for 
 *               JDK 1.3 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Mar-2006 : Fix for bug 1448828, incorrect calculation of week and year
 *               for the first few days of some years (DG);
 * 05-Oct-2006 : Updated API docs (DG);
 * 06-Oct-2006 : Refactored to cache first and last millisecond values (DG);
 * 09-Jan-2007 : Fixed bug in next() (DG);
 * 28-Aug-2007 : Added new constructor to avoid problem in creating new 
 *               instances (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A calendar week.  All years are considered to have 53 weeks, numbered from 1 
 * to 53, although in many cases the 53rd week is empty.  Most of the time, the
 * 1st week of the year *begins* in the previous calendar year, but it always 
 * finishes in the current year (this behaviour matches the workings of the 
 * <code>GregorianCalendar</code> class).
 * <P>
 * This class is immutable, which is a requirement for all 
 * {@link RegularTimePeriod} subclasses.
 */
public class Week extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$9g8j9vdln219wh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1856387786939865061L;
  static {
    CodeCoverCoverageCounter$9g8j9vdln219wh.statements[1]++;
  }
    
    /** Constant for the first week in the year. */
    public static final int FIRST_WEEK_IN_YEAR = 1;
  static {
    CodeCoverCoverageCounter$9g8j9vdln219wh.statements[2]++;
  }

    /** Constant for the last week in the year. */
    public static final int LAST_WEEK_IN_YEAR = 53;
  static {
    CodeCoverCoverageCounter$9g8j9vdln219wh.statements[3]++;
  }

    /** The year in which the week falls. */
    private short year;

    /** The week (1-53). */
    private byte week;

    /** The first millisecond. */
    private long firstMillisecond;
    
    /** The last millisecond. */
    private long lastMillisecond;

    /**
     * Creates a new time period for the week in which the current system 
     * date/time falls.
     */
    public Week() {
        this(new Date());
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[4]++;
    }

    /**
     * Creates a time period representing the week in the specified year.
     *
     * @param week  the week (1 to 53).
     * @param year  the year (1900 to 9999).
     */
    public Week(int week, int year) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((week < FIRST_WEEK_IN_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((week > LAST_WEEK_IN_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[1]++;
            throw new IllegalArgumentException(
                    "The 'week' argument must be in the range 1 - 53.");

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[2]++;}
        this.week = (byte) week;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[6]++;
        this.year = (short) year;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[7]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[8]++;
    }

    /**
     * Creates a time period representing the week in the specified year.
     *
     * @param week  the week (1 to 53).
     * @param year  the year (1900 to 9999).
     */
    public Week(int week, Year year) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((week < FIRST_WEEK_IN_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((week > LAST_WEEK_IN_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[3]++;
            throw new IllegalArgumentException(
                    "The 'week' argument must be in the range 1 - 53.");

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[4]++;}
        this.week = (byte) week;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[10]++;
        this.year = (short) year.getYear();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[11]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[12]++;
   }

    /**
     * Creates a time period for the week in which the specified date/time 
     * falls.
     *
     * @param time  the time (<code>null</code> not permitted).
     */
    public Week(Date time) {
        // defer argument checking...
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE, Locale.getDefault());
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[13]++;
    }

    /**
     * Creates a time period for the week in which the specified date/time 
     * falls, calculated relative to the specified time zone.
     *
     * @param time  the date/time (<code>null</code> not permitted).
     * @param zone  the time zone (<code>null</code> not permitted).
     * 
     * @deprecated As of 1.0.7, use {@link #Week(Date, TimeZone, Locale)}.
     */
    public Week(Date time, TimeZone zone) {
        // defer argument checking...
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE, Locale.getDefault());
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[14]++;
    }
    
    /**
     * Creates a time period for the week in which the specified date/time 
     * falls, calculated relative to the specified time zone.
     *
     * @param time  the date/time (<code>null</code> not permitted).
     * @param zone  the time zone (<code>null</code> not permitted).
     * @param locale  the locale (<code>null</code> not permitted).
     * 
     * @since 1.0.7
     */
    public Week(Date time, TimeZone zone, Locale locale) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((time == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[5]++;
            throw new IllegalArgumentException("Null 'time' argument.");
   
        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[6]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[7]++;
            throw new IllegalArgumentException("Null 'zone' argument.");
   
        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[8]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[9]++;
            throw new IllegalArgumentException("Null 'locale' argument.");

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[10]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[18]++;
        Calendar calendar = Calendar.getInstance(zone, locale);
        calendar.setTime(time);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[19]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[20]++;

        // sometimes the last few days of the year are considered to fall in 
        // the *first* week of the following year.  Refer to the Javadocs for 
        // GregorianCalendar.
        int tempWeek = calendar.get(Calendar.WEEK_OF_YEAR);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((tempWeek == 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((calendar.get(Calendar.MONTH) == Calendar.DECEMBER) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[11]++;
            this.week = 1;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[22]++;
            this.year = (short) (calendar.get(Calendar.YEAR) + 1);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[23]++;

        }
        else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[12]++;
            this.week = (byte) Math.min(tempWeek, LAST_WEEK_IN_YEAR);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[24]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[25]++;
            int yyyy = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
            // alternatively, sometimes the first few days of the year are
            // considered to fall in the *last* week of the previous year...
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((calendar.get(Calendar.MONTH) == Calendar.JANUARY) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.week >= 52) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[13]++;
                yyyy--;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[27]++;
 
            } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[14]++;}
            this.year = (short) yyyy;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[28]++;
        }
        peg(calendar);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[29]++;
    }

    /**
     * Returns the year in which the week falls.
     *
     * @return The year (never <code>null</code>).
     */
    public Year getYear() {
        return new Year(this.year);
    }

    /**
     * Returns the year in which the week falls, as an integer value.
     *
     * @return The year.
     */
    public int getYearValue() {
        return this.year;
    }

    /**
     * Returns the week.
     *
     * @return The week.
     */
    public int getWeek() {
        return this.week;
    }

    /**
     * Returns the first millisecond of the week.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the week.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the week.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the week.
     * 
     * @see #getFirstMillisecond()
     */
    public long getLastMillisecond() {
        return this.lastMillisecond;
    }
    
    /** 
     * Recalculates the start date/time and end date/time for this time period 
     * relative to the supplied calendar (which incorporates a time zone).
     * 
     * @param calendar  the calendar (<code>null</code> not permitted).
     * 
     * @since 1.0.3
     */
    public void peg(Calendar calendar) {
        this.firstMillisecond = getFirstMillisecond(calendar);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[30]++;
        this.lastMillisecond = getLastMillisecond(calendar);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[31]++;
    }

    /**
     * Returns the week preceding this one.  This method will return 
     * <code>null</code> for some lower limit on the range of weeks (currently 
     * week 1, 1900).  For week 1 of any year, the previous week is always week 
     * 53, but week 53 may not contain any days (you should check for this).
     *
     * @return The preceding week (possibly <code>null</code>).
     */
    public RegularTimePeriod previous() {

        Week result;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.week != FIRST_WEEK_IN_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[15]++;
            result = new Week(this.week - 1, this.year);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[33]++;

        }
        else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[16]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
            // we need to work out if the previous year has 52 or 53 weeks...
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.year > 1900) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[17]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[35]++;
                int yy = this.year - 1;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[36]++;
                Calendar prevYearCalendar = Calendar.getInstance();
                prevYearCalendar.set(yy, Calendar.DECEMBER, 31);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[37]++;
                result = new Week(prevYearCalendar.getActualMaximum(
                        Calendar.WEEK_OF_YEAR), yy);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[38]++;

            }
            else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[18]++;
                result = null;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[39]++;
            }
        }
        return result;

    }

    /**
     * Returns the week following this one.  This method will return 
     * <code>null</code> for some upper limit on the range of weeks (currently 
     * week 53, 9999).  For week 52 of any year, the following week is always 
     * week 53, but week 53 may not contain any days (you should check for 
     * this).
     *
     * @return The following week (possibly <code>null</code>).
     */
    public RegularTimePeriod next() {

        Week result;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[40]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.week < 52) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[19]++;
            result = new Week(this.week + 1, this.year);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[41]++;

        }
        else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[20]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[42]++;
            Calendar calendar = Calendar.getInstance();
            calendar.set(this.year, Calendar.DECEMBER, 31);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[43]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[44]++;
            int actualMaxWeek 
                = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.week < actualMaxWeek) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[21]++;
                result = new Week(this.week + 1, this.year);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[46]++;

            }
            else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[22]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.year < 9999) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[23]++;
                    result = new Week(FIRST_WEEK_IN_YEAR, this.year + 1);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[48]++;

                }
                else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[24]++;
                    result = null;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[49]++;
                }
            }
        }
        return result;

    }

    /**
     * Returns a serial index number for the week.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.year * 53L + this.week;
    }

    /**
     * Returns the first millisecond of the week, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The first millisecond of the week.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[50]++;
        Calendar c = (Calendar) calendar.clone();
        c.clear();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[51]++;
        c.set(Calendar.YEAR, this.year);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[52]++;
        c.set(Calendar.WEEK_OF_YEAR, this.week);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[53]++;
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[54]++;
        c.set(Calendar.HOUR, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[55]++;
        c.set(Calendar.MINUTE, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[56]++;
        c.set(Calendar.SECOND, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[57]++;
        c.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[58]++;
        //return c.getTimeInMillis();  // this won't work for JDK 1.3
        return c.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the week, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The last millisecond of the week.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[59]++;
        Calendar c = (Calendar) calendar.clone();
        c.clear();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[60]++;
        c.set(Calendar.YEAR, this.year);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[61]++;
        c.set(Calendar.WEEK_OF_YEAR, this.week + 1);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[62]++;
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[63]++;
        c.set(Calendar.HOUR, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[64]++;
        c.set(Calendar.MINUTE, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[65]++;
        c.set(Calendar.SECOND, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[66]++;
        c.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[67]++;
        //return c.getTimeInMillis();  // this won't work for JDK 1.3
        return c.getTime().getTime() - 1;
    }

    /**
     * Returns a string representing the week (e.g. "Week 9, 2002").
     *
     * TODO: look at internationalisation.
     *
     * @return A string representing the week.
     */
    public String toString() {
        return "Week " + this.week + ", " + this.year;
    }

    /**
     * Tests the equality of this Week object to an arbitrary object.  Returns
     * true if the target is a Week instance representing the same week as this
     * object.  In all other cases, returns false.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> if week and year of this and object are the 
     *         same.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[68]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[25]++;
            return true;

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[26]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[69]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((obj instanceof Week) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[28]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[70]++;
        Week that = (Week) obj;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[71]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.week != that.week) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[30]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[72]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.year != that.year) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[32]++;}
        return true;

    }

    /**
     * Returns a hash code for this object instance.  The approach described by
     * Joshua Bloch in "Effective Java" has been used here:
     * <p>
     * <code>http://developer.java.sun.com/developer/Books/effectivejava
     * /Chapter3.pdf</code>
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[73]++;
        int result = 17;
        result = 37 * result + this.week;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[74]++;
        result = 37 * result + this.year;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[75]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Week object relative to
     * the specified object:
     *
     * negative == before, zero == same, positive == after.
     *
     * @param o1  the object to compare.
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[76]++;
int CodeCoverConditionCoverageHelper_C17;

        // CASE 1 : Comparing to another Week object
        // --------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((o1 instanceof Week) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[33]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[77]++;
            Week w = (Week) o1;
            result = this.year - w.getYear().getYear();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[78]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[79]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((result == 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[35]++;
                result = this.week - w.getWeek();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[80]++;

            } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[36]++;}

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[34]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[81]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[37]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[82]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[38]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[83]++;
        }
}

        return result;

    }

    /**
     * Parses the string argument as a week.
     * <P>
     * This method is required to accept the format "YYYY-Wnn".  It will also
     * accept "Wnn-YYYY". Anything else, at the moment, is a bonus.
     *
     * @param s  string to parse.
     *
     * @return <code>null</code> if the string is not parseable, the week 
     *         otherwise.
     */
    public static Week parseWeek(String s) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[84]++;

        Week result = null;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[85]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[39]++;

            // trim whitespace from either end of the string
            s = s.trim();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[86]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[87]++;

            int i = Week.findSeparator(s);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[88]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i != -1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[41]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[89]++;
                String s1 = s.substring(0, i).trim();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[90]++;
                String s2 = s.substring(i + 1, s.length()).trim();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[91]++;

                Year y = Week.evaluateAsYear(s1);
                int w;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[92]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((y != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[43]++;
                    w = Week.stringToWeek(s2);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[93]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[94]++;
int CodeCoverConditionCoverageHelper_C23;
                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((w == -1) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[45]++;
                        throw new TimePeriodFormatException(
                                "Can't evaluate the week.");

                    } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[46]++;}
                    result = new Week(w, y);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[95]++;

                }
                else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[44]++;
                    y = Week.evaluateAsYear(s2);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[96]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[97]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((y != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[47]++;
                        w = Week.stringToWeek(s1);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[98]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[99]++;
int CodeCoverConditionCoverageHelper_C25;
                        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((w == -1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[49]++;
                            throw new TimePeriodFormatException(
                                    "Can't evaluate the week.");

                        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[50]++;}
                        result = new Week(w, y);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[100]++;

                    }
                    else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[48]++;
                        throw new TimePeriodFormatException(
                                "Can't evaluate the year.");
                    }
                }


            }
            else {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[42]++;
                throw new TimePeriodFormatException(
                        "Could not find separator.");
            }


        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[40]++;}
        return result;

    }

    /**
     * Finds the first occurrence of ' ', '-', ',' or '.'
     *
     * @param s  the string to parse.
     *
     * @return <code>-1</code> if none of the characters was found, the
     *      index of the first occurrence otherwise.
     */
    private static int findSeparator(String s) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[101]++;

        int result = s.indexOf('-');
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[102]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((result == -1) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[51]++;
            result = s.indexOf(',');
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[103]++;

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[52]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[104]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((result == -1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[53]++;
            result = s.indexOf(' ');
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[105]++;

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[54]++;}
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[106]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((result == -1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[55]++;
            result = s.indexOf('.');
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[107]++;

        } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[56]++;}
        return result;
    }

    /**
     * Creates a year from a string, or returns null (format exceptions
     * suppressed).
     *
     * @param s  string to parse.
     *
     * @return <code>null</code> if the string is not parseable, the year 
     *         otherwise.
     */
    private static Year evaluateAsYear(String s) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[108]++;

        Year result = null;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[109]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            result = Year.parseYear(s);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[110]++;
        }
        catch (TimePeriodFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[58]++;
            // suppress
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[57]++;
}
  }
        return result;

    }

    /**
     * Converts a string to a week.
     *
     * @param s  the string to parse.
     * @return <code>-1</code> if the string does not contain a week number,
     *         the number of the week otherwise.
     */
    private static int stringToWeek(String s) {
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[111]++;

        int result = -1;
        s = s.replace('W', ' ');
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[112]++;
        s = s.trim();
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[113]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[114]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            result = Integer.parseInt(s);
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[115]++;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[116]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((result < 1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((result > LAST_WEEK_IN_YEAR) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$9g8j9vdln219wh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[60]++;
                result = -1;
CodeCoverCoverageCounter$9g8j9vdln219wh.statements[117]++;

            } else {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[61]++;}
        }
        catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$9g8j9vdln219wh.branches[62]++;
            // suppress
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$9g8j9vdln219wh.branches[59]++;
}
  }
        return result;

    }
    
}

class CodeCoverCoverageCounter$9g8j9vdln219wh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9g8j9vdln219wh ());
  }
    public static long[] statements = new long[118];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[30];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Week.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 29; i++) {
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

  public CodeCoverCoverageCounter$9g8j9vdln219wh () {
    super("org.jfree.data.time.Week.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 117; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 29; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.Week.java");
      for (int i = 1; i <= 117; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 29; i++) {
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

