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
 * Second.java
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
 * 14-Feb-2002 : Fixed bug in Second(Date) constructor, and changed start of 
 *               range to zero from one (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 13-Mar-2002 : Added parseSecond() method (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 05-Mar-2003 : Fixed bug in getLastMillisecond() picked up in JUnit 
 *               tests (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Oct-2006 : Updated API docs (DG);
 * 06-Oct-2006 : Refactored to cache first and last millisecond values (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Represents a second in a particular day.  This class is immutable, which is 
 * a requirement for all {@link RegularTimePeriod} subclasses.
 */
public class Second extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6536564190712383466L;
  static {
    CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[1]++;
  }
    
    /** Useful constant for the first second in a minute. */
    public static final int FIRST_SECOND_IN_MINUTE = 0;
  static {
    CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[2]++;
  }

    /** Useful constant for the last second in a minute. */
    public static final int LAST_SECOND_IN_MINUTE = 59;
  static {
    CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[3]++;
  }

    /** The day. */
    private Day day;
    
    /** The hour of the day. */
    private byte hour;
    
    /** The minute. */
    private byte minute;

    /** The second. */
    private byte second;

    /** 
     * The first millisecond.  We don't store the last millisecond, because it
     * is always firstMillisecond + 999L.
     */
    private long firstMillisecond;
  
    /**
     * Constructs a new Second, based on the system date/time.
     */
    public Second() {
        this(new Date());
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[4]++;
    }

    /**
     * Constructs a new Second.
     *
     * @param second  the second (0 to 24*60*60-1).
     * @param minute  the minute (<code>null</code> not permitted).
     */
    public Second(int second, Minute minute) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((minute == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[1]++;
            throw new IllegalArgumentException("Null 'minute' argument.");
   
        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[2]++;}
        this.day = minute.getDay();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[6]++;
        this.hour = (byte) minute.getHourValue();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[7]++;
        this.minute = (byte) minute.getMinute();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[8]++;
        this.second = (byte) second;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[9]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[10]++;
    }

    /**
     * Creates a new second.
     * 
     * @param second  the second (0-59).
     * @param minute  the minute (0-59).
     * @param hour  the hour (0-23).
     * @param day  the day (1-31).
     * @param month  the month (1-12).
     * @param year  the year (1900-9999).
     */
    public Second(int second, int minute, int hour, 
                  int day, int month, int year) {
        this(second, new Minute(minute, hour, day, month, year));
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[11]++;    
    }
    
    /**
     * Constructs a second.
     *
     * @param time  the time.
     */
    public Second(Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[12]++;
    }

    /**
     * Creates a new second based on the supplied time and time zone.
     *
     * @param time  the instant in time.
     * @param zone  the time zone.
     */
    public Second(Date time, final TimeZone zone) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[13]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[14]++;
        this.second = (byte) calendar.get(Calendar.SECOND);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[15]++;
        this.minute = (byte) calendar.get(Calendar.MINUTE);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[16]++;
        this.hour = (byte) calendar.get(Calendar.HOUR_OF_DAY);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[17]++;
        this.day = new Day(time, zone);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[18]++;
        peg(calendar);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[19]++;
    }

    /**
     * Returns the second within the minute.
     *
     * @return The second (0 - 59).
     */
    public int getSecond() {
        return this.second;
    }

    /**
     * Returns the minute.
     *
     * @return The minute (never <code>null</code>).
     */
    public Minute getMinute() {
        return new Minute(this.minute, new Hour(this.hour, this.day));
    }

    /**
     * Returns the first millisecond of the second.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the second.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the second.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the second.
     * 
     * @see #getFirstMillisecond()
     */
    public long getLastMillisecond() {
        return this.firstMillisecond + 999L;
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
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[20]++;
    }

    /**
     * Returns the second preceding this one.
     *
     * @return The second preceding this one.
     */
    public RegularTimePeriod previous() {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[21]++;
        
        Second result = null;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.second != FIRST_SECOND_IN_MINUTE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[3]++;
            result = new Second(this.second - 1, getMinute());
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[23]++;

        }
        else {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[4]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[24]++;
            Minute previous = (Minute) getMinute().previous();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((previous != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[5]++;
                result = new Second(LAST_SECOND_IN_MINUTE, previous);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[26]++;

            } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[6]++;}
        }
        return result;
        
    }

    /**
     * Returns the second following this one.
     *
     * @return The second following this one.
     */
    public RegularTimePeriod next() {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[27]++;
        
        Second result = null;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.second != LAST_SECOND_IN_MINUTE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[7]++;
            result = new Second(this.second + 1, getMinute());
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[29]++;

        }
        else {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[8]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[30]++;
            Minute next = (Minute) getMinute().next();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[9]++;
                result = new Second(FIRST_SECOND_IN_MINUTE, next);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[32]++;

            } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[10]++;}
        }
        return result;

    }

    /**
     * Returns a serial index number for the minute.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[33]++;
        long hourIndex = this.day.getSerialIndex() * 24L + this.hour;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[34]++;
        long minuteIndex = hourIndex * 60L + this.minute;
        return minuteIndex * 60L + this.second;
    }

    /**
     * Returns the first millisecond of the minute.
     *
     * @param calendar  the calendar/timezone (<code>null</code> not permitted).
     *
     * @return The first millisecond.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[35]++;
        int year = this.day.getYear();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[36]++;
        int month = this.day.getMonth() - 1;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[37]++;
        int day = this.day.getDayOfMonth();
        calendar.clear();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[38]++;
        calendar.set(year, month, day, this.hour, this.minute, this.second);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[39]++;
        calendar.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[40]++;
        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the second.
     *
     * @param calendar  the calendar/timezone (<code>null</code> not permitted).
     *
     * @return The last millisecond.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
        return getFirstMillisecond(calendar) + 999L;
    }

    /**
     * Tests the equality of this object against an arbitrary Object.
     * <P>
     * This method will return true ONLY if the object is a Second object
     * representing the same second as this instance.
     *
     * @param obj  the object to compare (<code>null</code> permitted).
     *
     * @return <code>true</code> if second and minute of this and the object 
     *         are the same.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[12]++;}
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof Second) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[14]++;}
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[43]++;
        Second that = (Second) obj;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.second != that.second) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[16]++;}
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.minute != that.minute) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[18]++;}
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.hour != that.hour) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[20]++;}
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.day.equals(that.day)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[22]++;}
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
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[48]++;
        int result = 17;
        result = 37 * result + this.second;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[49]++;
        result = 37 * result + this.minute;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[50]++;
        result = 37 * result + this.hour;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[51]++;
        result = 37 * result + this.day.hashCode();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[52]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Second object relative
     * to the specified
     * object: negative == before, zero == same, positive == after.
     *
     * @param o1  the object to compare.
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;

        // CASE 1 : Comparing to another Second object
        // -------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((o1 instanceof Second) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[23]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[54]++;
            Second s = (Second) o1;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.firstMillisecond < s.firstMillisecond) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[25]++;
                return -1;

            }
            else {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[26]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[56]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.firstMillisecond > s.firstMillisecond) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[27]++;
                return 1;

            }
            else {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[28]++;
                return 0;
            }
}

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[24]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[57]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[29]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[58]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[30]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[59]++;
        }
}

        return result;

    }

    /**
     * Creates a new instance by parsing a string.  The string is assumed to
     * be in the format "YYYY-MM-DD HH:MM:SS", perhaps with leading or trailing
     * whitespace.
     *
     * @param s  the string to parse.
     *
     * @return The second, or <code>null</code> if the string is not parseable.
     */
    public static Second parseSecond(String s) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[60]++;

        Second result = null;
        s = s.trim();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[61]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[62]++;

        String daystr = s.substring(0, Math.min(10, s.length()));
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[63]++;
        Day day = Day.parseDay(daystr);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((day != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[31]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[65]++;
            String hmsstr = s.substring(
                Math.min(daystr.length() + 1, s.length()), s.length()
            );
            hmsstr = hmsstr.trim();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[66]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[67]++;

            int l = hmsstr.length();
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[68]++;
            String hourstr = hmsstr.substring(0, Math.min(2, l));
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[69]++;
            String minstr = hmsstr.substring(Math.min(3, l), Math.min(5, l));
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[70]++;
            String secstr = hmsstr.substring(Math.min(6, l), Math.min(8, l));
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[71]++;
            int hour = Integer.parseInt(hourstr);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[72]++;
int CodeCoverConditionCoverageHelper_C17;

            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((hour >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((hour <= 23) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[33]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[73]++;

                int minute = Integer.parseInt(minstr);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((minute >= 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((minute <= 59) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[35]++;
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[75]++;

                    Minute m = new Minute(minute, new Hour(hour, day));
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[76]++;
                    int second = Integer.parseInt(secstr);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[77]++;
int CodeCoverConditionCoverageHelper_C19;
                    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((second >= 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((second <= 59) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[37]++;
                        result = new Second(second, m);
CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.statements[78]++;

                    } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[38]++;}

                } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[36]++;}

            } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[34]++;}

        } else {
  CodeCoverCoverageCounter$co1m7f6kljp0jjy3l.branches[32]++;}

        return result;

    }

}

class CodeCoverCoverageCounter$co1m7f6kljp0jjy3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$co1m7f6kljp0jjy3l ());
  }
    public static long[] statements = new long[79];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Second.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2};
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

  public CodeCoverCoverageCounter$co1m7f6kljp0jjy3l () {
    super("org.jfree.data.time.Second.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 78; i++) {
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
    log.startNamedSection("org.jfree.data.time.Second.java");
      for (int i = 1; i <= 78; i++) {
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

