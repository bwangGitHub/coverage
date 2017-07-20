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
 * Hour.java
 * ---------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 18-Dec-2001 : Changed order of parameters in constructor (DG);
 * 19-Dec-2001 : Added a new constructor as suggested by Paul English (DG);
 * 14-Feb-2002 : Fixed bug in Hour(Date) constructor (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 15-Mar-2002 : Changed API (DG);
 * 16-Apr-2002 : Fixed small time zone bug in constructor (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package, and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method, and new constructor for 
 *               convenience (DG);
 * 30-Sep-2004 : Replaced getTime().getTime() with getTimeInMillis() (DG);
 * 04-Nov-2004 : Reverted change of 30-Sep-2004, because it won't work for 
 *               JDK 1.3 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Oct-2006 : Updated API docs (DG);
 * 06-Oct-2006 : Refactored to cache first and last millisecond values (DG);
 * 04-Apr-2007 : In Hour(Date, TimeZone), peg milliseconds using specified
 *               time zone (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Represents an hour in a specific day.  This class is immutable, which is a 
 * requirement for all {@link RegularTimePeriod} subclasses.
 */
public class Hour extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$7tzsqxwr2h14kh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -835471579831937652L;
  static {
    CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[1]++;
  }
    
    /** Useful constant for the first hour in the day. */
    public static final int FIRST_HOUR_IN_DAY = 0;
  static {
    CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[2]++;
  }

    /** Useful constant for the last hour in the day. */
    public static final int LAST_HOUR_IN_DAY = 23;
  static {
    CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[3]++;
  }

    /** The day. */
    private Day day;

    /** The hour. */
    private byte hour;

    /** The first millisecond. */
    private long firstMillisecond;
    
    /** The last millisecond. */
    private long lastMillisecond;

    /**
     * Constructs a new Hour, based on the system date/time.
     */
    public Hour() {
        this(new Date());
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[4]++;
    }

    /**
     * Constructs a new Hour.
     *
     * @param hour  the hour (in the range 0 to 23).
     * @param day  the day (<code>null</code> not permitted).
     */
    public Hour(int hour, Day day) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((day == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[1]++;
            throw new IllegalArgumentException("Null 'day' argument.");

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[2]++;}
        this.hour = (byte) hour;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[6]++;
        this.day = day;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[7]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[8]++;
    }

    /**
     * Creates a new hour.
     * 
     * @param hour  the hour (0-23).
     * @param day  the day (1-31).
     * @param month  the month (1-12).
     * @param year  the year (1900-9999).
     */
    public Hour(int hour, int day, int month, int year) {
        this(hour, new Day(day, month, year));
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[9]++;
    }
    
    /**
     * Constructs a new Hour, based on the supplied date/time.
     *
     * @param time  the date-time (<code>null</code> not permitted).
     */
    public Hour(Date time) {
        // defer argument checking...
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[10]++;
    }

    /**
     * Constructs a new Hour, based on the supplied date/time evaluated in the
     * specified time zone.
     *
     * @param time  the date-time (<code>null</code> not permitted).
     * @param zone  the time zone (<code>null</code> not permitted).
     */
    public Hour(Date time, TimeZone zone) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((time == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[3]++;
            throw new IllegalArgumentException("Null 'time' argument.");

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[4]++;}
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[5]++;
            throw new IllegalArgumentException("Null 'zone' argument.");

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[6]++;}
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[13]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[14]++;
        this.hour = (byte) calendar.get(Calendar.HOUR_OF_DAY);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[15]++;
        this.day = new Day(time, zone);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[16]++;
        peg(calendar);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[17]++;
    }

    /**
     * Returns the hour.
     *
     * @return The hour (0 <= hour <= 23).
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Returns the day in which this hour falls.
     *
     * @return The day.
     */
    public Day getDay() {
        return this.day;
    }

    /**
     * Returns the year in which this hour falls.
     *
     * @return The year.
     */
    public int getYear() {
        return this.day.getYear();
    }

    /**
     * Returns the month in which this hour falls.
     *
     * @return The month.
     */
    public int getMonth() {
        return this.day.getMonth();
    }

    /**
     * Returns the day-of-the-month in which this hour falls.
     *
     * @return The day-of-the-month.
     */
    public int getDayOfMonth() {
        return this.day.getDayOfMonth();
    }

    /**
     * Returns the first millisecond of the hour.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the hour.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the hour.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the hour.
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
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[18]++;
        this.lastMillisecond = getLastMillisecond(calendar);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[19]++;
    }

    /**
     * Returns the hour preceding this one.
     *
     * @return The hour preceding this one.
     */
    public RegularTimePeriod previous() {

        Hour result;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.hour != FIRST_HOUR_IN_DAY) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[7]++;
            result = new Hour(this.hour - 1, this.day);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[21]++;

        }
        else {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[8]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[22]++; // we are at the first hour in the day...
            Day prevDay = (Day) this.day.previous();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((prevDay != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[9]++;
                result = new Hour(LAST_HOUR_IN_DAY, prevDay);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[24]++;

            }
            else {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[10]++;
                result = null;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[25]++;
            }
        }
        return result;

    }

    /**
     * Returns the hour following this one.
     *
     * @return The hour following this one.
     */
    public RegularTimePeriod next() {

        Hour result;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.hour != LAST_HOUR_IN_DAY) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[11]++;
            result = new Hour(this.hour + 1, this.day);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[27]++;

        }
        else {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[12]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[28]++; // we are at the last hour in the day...
            Day nextDay = (Day) this.day.next();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nextDay != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[13]++;
                result = new Hour(FIRST_HOUR_IN_DAY, nextDay);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[30]++;

            }
            else {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[14]++;
                result = null;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[31]++;
            }
        }
        return result;

    }

    /**
     * Returns a serial index number for the hour.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.day.getSerialIndex() * 24L + this.hour;
    }

    /**
     * Returns the first millisecond of the hour.
     *
     * @param calendar  the calendar/timezone (<code>null</code> not permitted).
     *
     * @return The first millisecond.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[32]++;
        int year = this.day.getYear();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[33]++;
        int month = this.day.getMonth() - 1;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[34]++;
        int dom = this.day.getDayOfMonth();
        calendar.set(year, month, dom, this.hour, 0, 0);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[35]++;
        calendar.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[36]++;
        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the hour.
     *
     * @param calendar  the calendar/timezone (<code>null</code> not permitted).
     *
     * @return The last millisecond.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[37]++;
        int year = this.day.getYear();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[38]++;
        int month = this.day.getMonth() - 1;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[39]++;
        int dom = this.day.getDayOfMonth();
        calendar.set(year, month, dom, this.hour, 59, 59);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[40]++;
        calendar.set(Calendar.MILLISECOND, 999);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[41]++;
        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();
    }

    /**
     * Tests the equality of this object against an arbitrary Object.
     * <P>
     * This method will return true ONLY if the object is an Hour object
     * representing the same hour as this instance.
     *
     * @param obj  the object to compare (<code>null</code> permitted).
     *
     * @return <code>true</code> if the hour and day value of the object
     *      is the same as this.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[16]++;}
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof Hour) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[18]++;}
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[44]++;
        Hour that = (Hour) obj;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.hour != that.hour) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[20]++;}
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.day.equals(that.day)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[22]++;}
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
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[47]++;
        int result = 17;
        result = 37 * result + this.hour;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[48]++;
        result = 37 * result + this.day.hashCode();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[49]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Hour object relative to
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
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;

        // CASE 1 : Comparing to another Hour object
        // -----------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((o1 instanceof Hour) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[23]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[51]++;
            Hour h = (Hour) o1;
            result = getDay().compareTo(h.getDay());
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[52]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[53]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((result == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[25]++;
                result = this.hour - h.getHour();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[54]++;

            } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[26]++;}

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[24]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[55]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[27]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[56]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[28]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[57]++;
        }
}

        return result;

    }

    /**
     * Creates an Hour instance by parsing a string.  The string is assumed to
     * be in the format "YYYY-MM-DD HH", perhaps with leading or trailing
     * whitespace.
     *
     * @param s  the hour string to parse.
     *
     * @return <code>null</code> if the string is not parseable, the hour 
     *         otherwise.
     */
    public static Hour parseHour(String s) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[58]++;

        Hour result = null;
        s = s.trim();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[59]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[60]++;

        String daystr = s.substring(0, Math.min(10, s.length()));
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[61]++;
        Day day = Day.parseDay(daystr);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((day != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[29]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[63]++;
            String hourstr = s.substring(
                Math.min(daystr.length() + 1, s.length()), s.length()
            );
            hourstr = hourstr.trim();
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[64]++;
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[65]++;
            int hour = Integer.parseInt(hourstr);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[66]++;
int CodeCoverConditionCoverageHelper_C16;
            // if the hour is 0 - 23 then create an hour
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((hour >= FIRST_HOUR_IN_DAY) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((hour <= LAST_HOUR_IN_DAY) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$7tzsqxwr2h14kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[31]++;
                result = new Hour(hour, day);
CodeCoverCoverageCounter$7tzsqxwr2h14kh.statements[67]++;

            } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$7tzsqxwr2h14kh.branches[30]++;}

        return result;

    }

}

class CodeCoverCoverageCounter$7tzsqxwr2h14kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7tzsqxwr2h14kh ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Hour.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2};
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

  public CodeCoverCoverageCounter$7tzsqxwr2h14kh () {
    super("org.jfree.data.time.Hour.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
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
    log.startNamedSection("org.jfree.data.time.Hour.java");
      for (int i = 1; i <= 67; i++) {
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

