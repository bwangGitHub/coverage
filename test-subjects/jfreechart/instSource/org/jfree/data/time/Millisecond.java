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
 * ----------------
 * Millisecond.java
 * ----------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 19-Dec-2001 : Added new constructors as suggested by Paul English (DG);
 * 26-Feb-2002 : Added new getStart() and getEnd() methods (DG);
 * 29-Mar-2002 : Fixed bug in getStart(), getEnd() and compareTo() methods (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Oct-2006 : Updated API docs (DG);
 * 06-Oct-2006 : Refactored to cache first and last millisecond values (DG);
 * 04-Apr-2007 : In Millisecond(Date, TimeZone), peg milliseconds to the
 *               specified zone (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Represents a millisecond.  This class is immutable, which is a requirement 
 * for all {@link RegularTimePeriod} subclasses.
 */
public class Millisecond extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -5316836467277638485L;
  static {
    CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[1]++;
  }
    
    /** A constant for the first millisecond in a second. */
    public static final int FIRST_MILLISECOND_IN_SECOND = 0;
  static {
    CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[2]++;
  }

    /** A constant for the last millisecond in a second. */
    public static final int LAST_MILLISECOND_IN_SECOND = 999;
  static {
    CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[3]++;
  }

    /** The day. */
    private Day day;
    
    /** The hour in the day. */
    private byte hour;
    
    /** The minute. */
    private byte minute;

    /** The second. */
    private byte second;

    /** The millisecond. */
    private int millisecond;

    /**
     * The pegged millisecond. 
     */
    private long firstMillisecond;
    
    /**
     * Constructs a millisecond based on the current system time.
     */
    public Millisecond() {
        this(new Date());
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[4]++;
    }

    /**
     * Constructs a millisecond.
     *
     * @param millisecond  the millisecond (0-999).
     * @param second  the second.
     */
    public Millisecond(int millisecond, Second second) {
        this.millisecond = millisecond;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[5]++;
        this.second = (byte) second.getSecond();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[6]++;
        this.minute = (byte) second.getMinute().getMinute();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[7]++;
        this.hour = (byte) second.getMinute().getHourValue();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[8]++;
        this.day = second.getMinute().getDay();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[9]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[10]++;
    }

    /**
     * Creates a new millisecond.
     * 
     * @param millisecond  the millisecond (0-999).
     * @param second  the second (0-59).
     * @param minute  the minute (0-59).
     * @param hour  the hour (0-23).
     * @param day  the day (1-31).
     * @param month  the month (1-12).
     * @param year  the year (1900-9999).
     */    
    public Millisecond(int millisecond, int second, int minute, int hour,
                       int day, int month, int year) {
                           
        this(millisecond, new Second(second, minute, hour, day, month, year));
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[11]++;
    
    }

    /**
     * Constructs a millisecond.
     *
     * @param time  the time.
     */
    public Millisecond(Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[12]++;
    }

    /**
     * Creates a millisecond.
     *
     * @param time  the instant in time.
     * @param zone  the time zone.
     */
    public Millisecond(Date time, TimeZone zone) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[13]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[14]++;
        this.millisecond = calendar.get(Calendar.MILLISECOND);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[15]++;
        this.second = (byte) calendar.get(Calendar.SECOND);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[16]++;
        this.minute = (byte) calendar.get(Calendar.MINUTE);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[17]++;
        this.hour = (byte) calendar.get(Calendar.HOUR_OF_DAY);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[18]++;
        this.day = new Day(time, zone);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[19]++;
        peg(calendar);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[20]++;
    }

    /**
     * Returns the second.
     *
     * @return The second.
     */
    public Second getSecond() {
        return new Second(this.second, this.minute, this.hour, 
                this.day.getDayOfMonth(), this.day.getMonth(), 
                this.day.getYear());
    }

    /**
     * Returns the millisecond.
     *
     * @return The millisecond.
     */
    public long getMillisecond() {
        return this.millisecond;
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
        return this.firstMillisecond;
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
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[21]++;
    }

    /**
     * Returns the millisecond preceding this one.
     *
     * @return The millisecond preceding this one.
     */
    public RegularTimePeriod previous() {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[22]++;

        RegularTimePeriod result = null;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[23]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.millisecond != FIRST_MILLISECOND_IN_SECOND) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[1]++;
            result = new Millisecond(this.millisecond - 1, getSecond());
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[24]++;

        }
        else {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[2]++;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[25]++;
            Second previous = (Second) getSecond().previous();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((previous != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[3]++;
                result = new Millisecond(LAST_MILLISECOND_IN_SECOND, previous);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[27]++;

            } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[4]++;}
        }
        return result;

    }

    /**
     * Returns the millisecond following this one.
     *
     * @return The millisecond following this one.
     */
    public RegularTimePeriod next() {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[28]++;

        RegularTimePeriod result = null;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[29]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.millisecond != LAST_MILLISECOND_IN_SECOND) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[5]++;
            result = new Millisecond(this.millisecond + 1, getSecond());
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[30]++;

        }
        else {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[6]++;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[31]++;
            Second next = (Second) getSecond().next();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[7]++;
                result = new Millisecond(FIRST_MILLISECOND_IN_SECOND, next);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[33]++;

            } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[8]++;}
        }
        return result;

    }

    /**
     * Returns a serial index number for the millisecond.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[34]++;
        long hourIndex = this.day.getSerialIndex() * 24L + this.hour;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[35]++;
        long minuteIndex = hourIndex * 60L + this.minute;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[36]++;
        long secondIndex = minuteIndex * 60L + this.second;
        return secondIndex * 1000L + this.millisecond;
    }

    /**
     * Tests the equality of this object against an arbitrary Object.
     * <P>
     * This method will return true ONLY if the object is a Millisecond object
     * representing the same millisecond as this instance.
     *
     * @param obj  the object to compare
     *
     * @return <code>true</code> if milliseconds and seconds of this and object
     *      are the same.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[10]++;}
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof Millisecond) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[12]++;}
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[39]++;
        Millisecond that = (Millisecond) obj;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[40]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.millisecond != that.millisecond) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[14]++;}
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.second != that.second) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[16]++;}
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.minute != that.minute) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[18]++;}
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[43]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.hour != that.hour) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[20]++;}
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.day.equals(that.day)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[22]++;}
        return true;
    }

    /**
     * Returns a hash code for this object instance.  The approach described by 
     * Joshua Bloch in "Effective Java" has been used here:
     * <p>
     * <code>http://developer.java.sun.com/developer/Books/effectivejava
     * /Chapter3.pdf</code>
     * 
     * @return A hashcode.
     */
    public int hashCode() {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[45]++;
        int result = 17;
        result = 37 * result + this.millisecond;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[46]++;
        result = 37 * result + getSecond().hashCode();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[47]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Millisecond object
     * relative to the specified object:
     *
     * negative == before, zero == same, positive == after.
     *
     * @param obj  the object to compare
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object obj) {

        int result;
        long difference;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;

        // CASE 1 : Comparing to another Second object
        // -------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj instanceof Millisecond) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[23]++;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[49]++;
            Millisecond ms = (Millisecond) obj;
            difference = getFirstMillisecond() - ms.getFirstMillisecond();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[50]++;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((difference > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[25]++;
                result = 1;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[52]++;

            }
            else {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[26]++;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((difference < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[27]++;
                    result = -1;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[54]++;

                }
                else {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[28]++;
                    result = 0;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[55]++;
                }
            }

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[24]++;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[56]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[29]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[57]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.branches[30]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[58]++;
        }
}

        return result;

    }

    /**
     * Returns the first millisecond of the time period.
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The first millisecond of the time period.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[59]++;
        int year = this.day.getYear();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[60]++;
        int month = this.day.getMonth() - 1;
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[61]++;
        int day = this.day.getDayOfMonth();
        calendar.clear();
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[62]++;
        calendar.set(year, month, day, this.hour, this.minute, this.second);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[63]++;
        calendar.set(Calendar.MILLISECOND, this.millisecond);
CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d.statements[64]++;
        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the time period.
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The last millisecond of the time period.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
        return getFirstMillisecond(calendar);
    }

}

class CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d ());
  }
    public static long[] statements = new long[65];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Millisecond.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$4kzivxpvudpb06v0dstz5gz1d () {
    super("org.jfree.data.time.Millisecond.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 64; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.Millisecond.java");
      for (int i = 1; i <= 64; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

