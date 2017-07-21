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
 * -----------
 * Minute.java
 * -----------
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
 * 14-Feb-2002 : Fixed bug in Minute(Date) constructor, and changed the range 
 *               to start from zero instead of one (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 13-Mar-2002 : Added parseMinute() method (DG);
 * 19-Mar-2002 : Changed API, the minute is now defined in relation to an 
 *               Hour (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method, and new constructor for 
 *               convenience (DG);
 * 30-Sep-2004 : Replaced getTime().getTime() with getTimeInMillis() (DG);
 * 04-Nov-2004 : Reverted change of 30-Sep-2004, because it won't work for 
 *               JDK 1.3 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Oct-2006 : Updated API docs (DG);
 * 06-Oct-2006 : Refactored to cache first and last millisecond values (DG);
 * 11-Dec-2006 : Fix for previous() - bug 1611872 (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Represents a minute.  This class is immutable, which is a requirement for 
 * all {@link RegularTimePeriod} subclasses.
 */
public class Minute extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$brbjo7pxilw39apup.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2144572840034842871L;
  static {
    CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[1]++;
  }
    
    /** Useful constant for the first minute in a day. */
    public static final int FIRST_MINUTE_IN_HOUR = 0;
  static {
    CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[2]++;
  }

    /** Useful constant for the last minute in a day. */
    public static final int LAST_MINUTE_IN_HOUR = 59;
  static {
    CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[3]++;
  }

    /** The day. */
    private Day day;
    
    /** The hour in which the minute falls. */
    private byte hour;

    /** The minute. */
    private byte minute;

    /** The first millisecond. */
    private long firstMillisecond;
    
    /** The last millisecond. */
    private long lastMillisecond;

    /**
     * Constructs a new Minute, based on the system date/time.
     */
    public Minute() {
        this(new Date());
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[4]++;
    }

    /**
     * Constructs a new Minute.
     *
     * @param minute  the minute (0 to 59).
     * @param hour  the hour (<code>null</code> not permitted).
     */
    public Minute(int minute, Hour hour) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((hour == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[1]++;
            throw new IllegalArgumentException("Null 'hour' argument.");

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[2]++;}
        this.minute = (byte) minute;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[6]++;
        this.hour = (byte) hour.getHour();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[7]++;
        this.day = hour.getDay();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[8]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[9]++;
    }

    /**
     * Constructs a new Minute, based on the supplied date/time.
     *
     * @param time  the time (<code>null</code> not permitted).
     */
    public Minute(Date time) {
        // defer argument checking
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[10]++;
    }

    /**
     * Constructs a new Minute, based on the supplied date/time and timezone.
     *
     * @param time  the time (<code>null</code> not permitted).
     * @param zone  the time zone (<code>null</code> not permitted).
     */
    public Minute(Date time, TimeZone zone) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((time == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[3]++;
            throw new IllegalArgumentException("Null 'time' argument.");

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[4]++;}
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[5]++;
            throw new IllegalArgumentException("Null 'zone' argument.");

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[6]++;}
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[13]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[14]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[15]++;
        int min = calendar.get(Calendar.MINUTE);
        this.minute = (byte) min;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[16]++;
        this.hour = (byte) calendar.get(Calendar.HOUR_OF_DAY);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[17]++;
        this.day = new Day(time, zone);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[18]++;
        peg(calendar);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[19]++;
    }
    
    /**
     * Creates a new minute.
     * 
     * @param minute  the minute (0-59).
     * @param hour  the hour (0-23).
     * @param day  the day (1-31).
     * @param month  the month (1-12).
     * @param year  the year (1900-9999).
     */
    public Minute(int minute, 
                  int hour, 
                  int day, 
                  int month, 
                  int year) {
        this(minute, new Hour(hour, new Day(day, month, year)));
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[20]++;
    }

    /**
     * Returns the day.
     * 
     * @return The day.
     * 
     * @since 1.0.3
     */
    public Day getDay() {
        return this.day;
    }
    
    /**
     * Returns the hour.
     *
     * @return The hour (never <code>null</code>).
     */
    public Hour getHour() {
        return new Hour(this.hour, this.day);
    }
    
    /**
     * Returns the hour.
     * 
     * @return The hour.
     * 
     * @since 1.0.3
     */
    public int getHourValue() {
        return this.hour;
    }

    /**
     * Returns the minute.
     *
     * @return The minute.
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * Returns the first millisecond of the minute.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the minute.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the minute.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the minute.
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
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[21]++;
        this.lastMillisecond = getLastMillisecond(calendar);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[22]++;
    }

    /**
     * Returns the minute preceding this one.
     *
     * @return The minute preceding this one.
     */
    public RegularTimePeriod previous() {
        Minute result;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.minute != FIRST_MINUTE_IN_HOUR) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[7]++;
            result = new Minute(this.minute - 1, getHour());
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[24]++;

        }
        else {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[8]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[25]++;
            Hour h = (Hour) getHour().previous();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((h != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[9]++;
                result = new Minute(LAST_MINUTE_IN_HOUR, h);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[27]++;

            }
            else {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[10]++;
                result = null;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[28]++;
            }
        }
        return result;
    }

    /**
     * Returns the minute following this one.
     *
     * @return The minute following this one.
     */
    public RegularTimePeriod next() {

        Minute result;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.minute != LAST_MINUTE_IN_HOUR) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[11]++;
            result = new Minute(this.minute + 1, getHour());
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[30]++;

        }
        else {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[12]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[31]++; // we are at the last minute in the hour...
            Hour nextHour = (Hour) getHour().next();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nextHour != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[13]++;
                result = new Minute(FIRST_MINUTE_IN_HOUR, nextHour);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[33]++;

            }
            else {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[14]++;
                result = null;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[34]++;
            }
        }
        return result;

    }

    /**
     * Returns a serial index number for the minute.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[35]++;
        long hourIndex = this.day.getSerialIndex() * 24L + this.hour;
        return hourIndex * 60L + this.minute;
    }

    /**
     * Returns the first millisecond of the minute.
     *
     * @param calendar  the calendar which defines the timezone 
     *     (<code>null</code> not permitted).
     *
     * @return The first millisecond.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[36]++;

        int year = this.day.getYear();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[37]++;
        int month = this.day.getMonth() - 1;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[38]++;
        int day = this.day.getDayOfMonth();

        calendar.clear();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[39]++;
        calendar.set(year, month, day, this.hour, this.minute, 0);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[40]++;
        calendar.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[41]++;

        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();

    }

    /**
     * Returns the last millisecond of the minute.
     *
     * @param calendar  the calendar / timezone (<code>null</code> not 
     *     permitted).
     *
     * @return The last millisecond.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[42]++;

        int year = this.day.getYear();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[43]++;
        int month = this.day.getMonth() - 1;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[44]++;
        int day = this.day.getDayOfMonth();

        calendar.clear();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[45]++;
        calendar.set(year, month, day, this.hour, this.minute, 59);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[46]++;
        calendar.set(Calendar.MILLISECOND, 999);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[47]++;

        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();

    }

    /**
     * Tests the equality of this object against an arbitrary Object.
     * <P>
     * This method will return true ONLY if the object is a Minute object
     * representing the same minute as this instance.
     *
     * @param obj  the object to compare (<code>null</code> permitted).
     *
     * @return <code>true</code> if the minute and hour value of this and the
     *      object are the same.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[16]++;}
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof Minute) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[18]++;}
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[50]++;
        Minute that = (Minute) obj;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[51]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.minute != that.minute) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[20]++;}
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.hour != that.hour) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[22]++;}
        return true;
    }

    /**
     * Returns a hash code for this object instance.  The approach described 
     * by Joshua Bloch in "Effective Java" has been used here:
     * <p>
     * <code>http://developer.java.sun.com/developer/Books/effectivejava
     * /Chapter3.pdf</code>
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[53]++;
        int result = 17;
        result = 37 * result + this.minute;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[54]++;
        result = 37 * result + this.hour;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[55]++;
        result = 37 * result + this.day.hashCode();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[56]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Minute object relative
     * to the specified object:
     *
     * negative == before, zero == same, positive == after.
     *
     * @param o1  object to compare.
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[57]++;
int CodeCoverConditionCoverageHelper_C12;

        // CASE 1 : Comparing to another Minute object
        // -------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((o1 instanceof Minute) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[23]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[58]++;
            Minute m = (Minute) o1;
            result = getHour().compareTo(m.getHour());
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[59]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[60]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((result == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[25]++;
                result = this.minute - m.getMinute();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[61]++;

            } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[26]++;}

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[24]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[62]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[27]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[63]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[28]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[64]++;
        }
}

        return result;

    }

    /**
     * Creates a Minute instance by parsing a string.  The string is assumed to
     * be in the format "YYYY-MM-DD HH:MM", perhaps with leading or trailing
     * whitespace.
     *
     * @param s  the minute string to parse.
     *
     * @return <code>null</code>, if the string is not parseable, the minute
     *      otherwise.
     */
    public static Minute parseMinute(String s) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[65]++;

        Minute result = null;
        s = s.trim();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[66]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[67]++;

        String daystr = s.substring(0, Math.min(10, s.length()));
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[68]++;
        Day day = Day.parseDay(daystr);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[69]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((day != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[29]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[70]++;
            String hmstr = s.substring(
                Math.min(daystr.length() + 1, s.length()), s.length()
            );
            hmstr = hmstr.trim();
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[71]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[72]++;

            String hourstr = hmstr.substring(0, Math.min(2, hmstr.length()));
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[73]++;
            int hour = Integer.parseInt(hourstr);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;

            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((hour >= 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((hour <= 23) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[31]++;
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[75]++;
                String minstr = hmstr.substring(
                    Math.min(hourstr.length() + 1, hmstr.length()), 
                    hmstr.length()
                );
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[76]++;
                int minute = Integer.parseInt(minstr);
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[77]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((minute >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((minute <= 59) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$brbjo7pxilw39apup.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[33]++;
                    result = new Minute(minute, new Hour(hour, day));
CodeCoverCoverageCounter$brbjo7pxilw39apup.statements[78]++;

                } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[34]++;}

            } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$brbjo7pxilw39apup.branches[30]++;}

        return result;

    }

}

class CodeCoverCoverageCounter$brbjo7pxilw39apup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$brbjo7pxilw39apup ());
  }
    public static long[] statements = new long[79];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Minute.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$brbjo7pxilw39apup () {
    super("org.jfree.data.time.Minute.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 78; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.Minute.java");
      for (int i = 1; i <= 78; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
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

