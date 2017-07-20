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
 * ----------
 * Month.java
 * ----------
 * (C) Copyright 2001-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Chris Boek;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 14-Nov-2001 : Added method to get year as primitive (DG);
 *               Override for toString() method (DG);
 * 18-Dec-2001 : Changed order of parameters in constructor (DG);
 * 19-Dec-2001 : Added a new constructor as suggested by Paul English (DG);
 * 29-Jan-2002 : Worked on the parseMonth() method (DG);
 * 14-Feb-2002 : Fixed bugs in the Month constructors (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 19-Mar-2002 : Changed API for TimePeriod classes (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package, and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * 01-Nov-2005 : Fixed bug 1345383 (argument check in constructor) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Oct-2006 : Updated API docs (DG);
 * 06-Oct-2006 : Refactored to cache first and last millisecond values (DG);
 * 04-Apr-2007 : Fixed bug in Month(Date, TimeZone) constructor (CB);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.date.MonthConstants;
import org.jfree.date.SerialDate;

/**
 * Represents a single month.  This class is immutable, which is a requirement
 * for all {@link RegularTimePeriod} subclasses.
 */
public class Month extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$1njpk8lco1amjvxd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5090216912548722570L;
  static {
    CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[1]++;
  }

    /** The month (1-12). */
    private int month;

    /** The year in which the month falls. */
    private int year;

    /** The first millisecond. */
    private long firstMillisecond;
    
    /** The last millisecond. */
    private long lastMillisecond;

    /**
     * Constructs a new Month, based on the current system time.
     */
    public Month() {
        this(new Date());
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[2]++;
    }

    /**
     * Constructs a new month instance.
     *
     * @param month  the month (in the range 1 to 12).
     * @param year  the year.
     */
    public Month(int month, int year) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((month < 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((month > 12) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[1]++;
            throw new IllegalArgumentException("Month outside valid range.");

        } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[2]++;}
        this.month = month;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[4]++;
        this.year = year;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[5]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[6]++;
    }

    /**
     * Constructs a new month instance.
     *
     * @param month  the month (in the range 1 to 12).
     * @param year  the year.
     */
    public Month(int month, Year year) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((month < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((month > 12) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[3]++;
            throw new IllegalArgumentException("Month outside valid range.");

        } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[4]++;}
        this.month = month;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[8]++;
        this.year = year.getYear();
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[9]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[10]++;
    }

    /**
     * Constructs a new <code>Month</code> instance, based on a date/time and 
     * the default time zone.
     *
     * @param time  the date/time.
     */
    public Month(Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[11]++;
    }

    /**
     * Constructs a new <code>Month</code> instance, based on a date/time and 
     * a time zone.  The first and last millisecond values are initially
     * pegged to the given time zone also.
     *
     * @param time  the date/time.
     * @param zone  the time zone (<code>null</code> not permitted).
     */
    public Month(Date time, TimeZone zone) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[12]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[13]++;
        this.month = calendar.get(Calendar.MONTH) + 1;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[14]++;
        this.year = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[15]++;
        peg(calendar);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[16]++;
    }

    /**
     * Returns the year in which the month falls.
     *
     * @return The year in which the month falls (as a Year object).
     */
    public Year getYear() {
        return new Year(this.year);
    }

    /**
     * Returns the year in which the month falls.
     *
     * @return The year in which the month falls (as an int).
     */
    public int getYearValue() {
        return this.year;
    }

    /**
     * Returns the month.  Note that 1=JAN, 2=FEB, ...
     *
     * @return The month.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the first millisecond of the month.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the month.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the month.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the month.
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
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[17]++;
        this.lastMillisecond = getLastMillisecond(calendar);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[18]++;
    }

    /**
     * Returns the month preceding this one.
     *
     * @return The month preceding this one.
     */
    public RegularTimePeriod previous() {
        Month result;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.month != MonthConstants.JANUARY) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[5]++;
            result = new Month(this.month - 1, this.year);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[20]++;

        }
        else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[6]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.year > 1900) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[7]++;
                result = new Month(MonthConstants.DECEMBER, this.year - 1);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[22]++;

            }
            else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[8]++;
                result = null;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[23]++;
            }
        }
        return result;
    }

    /**
     * Returns the month following this one.
     *
     * @return The month following this one.
     */
    public RegularTimePeriod next() {
        Month result;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.month != MonthConstants.DECEMBER) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[9]++;
            result = new Month(this.month + 1, this.year);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[25]++;

        }
        else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[10]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.year < 9999) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[11]++;
                result = new Month(MonthConstants.JANUARY, this.year + 1);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[27]++;

            }
            else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[12]++;
                result = null;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[28]++;
            }
        }
        return result;
    }

    /**
     * Returns a serial index number for the month.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.year * 12L + this.month;
    }

    /**
     * Returns a string representing the month (e.g. "January 2002").
     * <P>
     * To do: look at internationalisation.
     *
     * @return A string representing the month.
     */
    public String toString() {
        return SerialDate.monthCodeToString(this.month) + " " + this.year;
    }

    /**
     * Tests the equality of this Month object to an arbitrary object.
     * Returns true if the target is a Month instance representing the same
     * month as this object.  In all other cases, returns false.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> if month and year of this and object are the 
     *         same.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[13]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof Month) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[15]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[31]++;
                Month target = (Month) obj;
                return (this.month == target.getMonth() 
                        && (this.year == target.getYearValue()));

            }
            else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[16]++;
                return false;
            }

        }
        else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[14]++;
            return false;
        }

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
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[32]++;
        int result = 17;
        result = 37 * result + this.month;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[33]++;
        result = 37 * result + this.year;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[34]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Month object relative to
     * the specified
     * object: negative == before, zero == same, positive == after.
     *
     * @param o1  the object to compare.
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;

        // CASE 1 : Comparing to another Month object
        // --------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((o1 instanceof Month) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[17]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[36]++;
            Month m = (Month) o1;
            result = this.year - m.getYearValue();
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[37]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((result == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[19]++;
                result = this.month - m.getMonth();
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[39]++;

            } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[20]++;}

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[18]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[40]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[21]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[41]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[22]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[42]++;
        }
}

        return result;

    }

    /**
     * Returns the first millisecond of the month, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The first millisecond of the month.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
        calendar.set(this.year, this.month - 1, 1, 0, 0, 0);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[43]++;
        calendar.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[44]++;
        // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the month, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The last millisecond of the month.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[45]++;
        int eom = SerialDate.lastDayOfMonth(this.month, this.year);
        calendar.set(this.year, this.month - 1, eom, 23, 59, 59);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[46]++;
        calendar.set(Calendar.MILLISECOND, 999);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[47]++;
        // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }

    /**
     * Parses the string argument as a month.
     * <P>
     * This method is required to accept the format "YYYY-MM".  It will also
     * accept "MM-YYYY". Anything else, at the moment, is a bonus.
     *
     * @param s  the string to parse.
     *
     * @return <code>null</code> if the string is not parseable, the month 
     *         otherwise.
     */
    public static Month parseMonth(String s) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[48]++;

        Month result = null;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[23]++;

            // trim whitespace from either end of the string
            s = s.trim();
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[50]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[51]++;

            int i = Month.findSeparator(s);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i != -1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[25]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[53]++;
                String s1 = s.substring(0, i).trim();
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[54]++;
                String s2 = s.substring(i + 1, s.length()).trim();
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[55]++;

                Year year = Month.evaluateAsYear(s1);
                int month;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((year != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[27]++;
                    month = SerialDate.stringToMonthCode(s2);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[57]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[58]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((month == -1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[29]++;
                        throw new TimePeriodFormatException(
                            "Can't evaluate the month."
                        );

                    } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[30]++;}
                    result = new Month(month, year);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[59]++;

                }
                else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[28]++;
                    year = Month.evaluateAsYear(s2);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[60]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((year != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[31]++;
                        month = SerialDate.stringToMonthCode(s1);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[62]++;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
                        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((month == -1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[33]++;
                            throw new TimePeriodFormatException(
                                "Can't evaluate the month."
                            );

                        } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[34]++;}
                        result = new Month(month, year);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[64]++;

                    }
                    else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[32]++;
                        throw new TimePeriodFormatException(
                            "Can't evaluate the year."
                        );
                    }
                }


            }
            else {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[26]++;
                throw new TimePeriodFormatException(
                    "Could not find separator."
                );
            }


        } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[24]++;}
        return result;

    }

    /**
     * Finds the first occurrence of ' ', '-', ',' or '.'
     *
     * @param s  the string to parse.
     * 
     * @return <code>-1</code> if none of the characters where found, the
     *      position of the first occurence otherwise.
     */
    private static int findSeparator(String s) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[65]++;

        int result = s.indexOf('-');
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[66]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((result == -1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[35]++;
            result = s.indexOf(',');
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[67]++;

        } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[36]++;}
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((result == -1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[37]++;
            result = s.indexOf(' ');
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[69]++;

        } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[38]++;}
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((result == -1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1njpk8lco1amjvxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[39]++;
            result = s.indexOf('.');
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[71]++;

        } else {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[40]++;}
        return result;
    }

    /**
     * Creates a year from a string, or returns null (format exceptions 
     * suppressed).
     *
     * @param s  the string to parse.
     *
     * @return <code>null</code> if the string is not parseable, the year 
     *         otherwise.
     */
    private static Year evaluateAsYear(String s) {
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[72]++;

        Year result = null;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[73]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            result = Year.parseYear(s);
CodeCoverCoverageCounter$1njpk8lco1amjvxd.statements[74]++;
        }
        catch (TimePeriodFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[42]++;
            // suppress
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1njpk8lco1amjvxd.branches[41]++;
}
  }
        return result;

    }

}

class CodeCoverCoverageCounter$1njpk8lco1amjvxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1njpk8lco1amjvxd ());
  }
    public static long[] statements = new long[75];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Month.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
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

  public CodeCoverCoverageCounter$1njpk8lco1amjvxd () {
    super("org.jfree.data.time.Month.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 74; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.Month.java");
      for (int i = 1; i <= 74; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
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

