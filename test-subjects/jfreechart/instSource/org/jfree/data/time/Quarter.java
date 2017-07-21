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
 * ------------
 * Quarter.java
 * ------------
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
 * 29-Jan-2002 : Added a new method parseQuarter(String) (DG);
 * 14-Feb-2002 : Fixed bug in Quarter(Date) constructor (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 19-Mar-2002 : Changed API for TimePeriod classes (DG);
 * 24-Jun-2002 : Removed main method (just test code) (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package, and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * 10-Dec-2005 : Fixed argument checking bug (1377239) in constructor (DG);
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

import org.jfree.date.MonthConstants;
import org.jfree.date.SerialDate;

/**
 * Defines a quarter (in a given year).  The range supported is Q1 1900 to 
 * Q4 9999.  This class is immutable, which is a requirement for all 
 * {@link RegularTimePeriod} subclasses.
 */
public class Quarter extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3810061714380888671L;
  static {
    CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[1]++;
  }
    
    /** Constant for quarter 1. */
    public static final int FIRST_QUARTER = 1;
  static {
    CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[2]++;
  }

    /** Constant for quarter 4. */
    public static final int LAST_QUARTER = 4;
  static {
    CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[3]++;
  }

    /** The first month in each quarter. */
    public static final int[] FIRST_MONTH_IN_QUARTER = {
        0, MonthConstants.JANUARY, MonthConstants.APRIL, MonthConstants.JULY, 
        MonthConstants.OCTOBER
    };
  static {
    CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[4]++;
  }

    /** The last month in each quarter. */
    public static final int[] LAST_MONTH_IN_QUARTER = {
        0, MonthConstants.MARCH, MonthConstants.JUNE, MonthConstants.SEPTEMBER, 
        MonthConstants.DECEMBER
    };
  static {
    CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[5]++;
  }

    /** The year in which the quarter falls. */
    private short year;

    /** The quarter (1-4). */
    private byte quarter;

    /** The first millisecond. */
    private long firstMillisecond;
    
    /** The last millisecond. */
    private long lastMillisecond;

    /**
     * Constructs a new Quarter, based on the current system date/time.
     */
    public Quarter() {
        this(new Date());
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[6]++;
    }

    /**
     * Constructs a new quarter.
     *
     * @param year  the year (1900 to 9999).
     * @param quarter  the quarter (1 to 4).
     */
    public Quarter(int quarter, int year) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((quarter < FIRST_QUARTER) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((quarter > LAST_QUARTER) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[1]++;
            throw new IllegalArgumentException("Quarter outside valid range.");

        } else {
  CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[2]++;}
        this.year = (short) year;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[8]++;
        this.quarter = (byte) quarter;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[9]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[10]++;
    }

    /**
     * Constructs a new quarter.
     *
     * @param quarter  the quarter (1 to 4).
     * @param year  the year (1900 to 9999).
     */
    public Quarter(int quarter, Year year) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((quarter < FIRST_QUARTER) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((quarter > LAST_QUARTER) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[3]++;
            throw new IllegalArgumentException("Quarter outside valid range.");

        } else {
  CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[4]++;}
        this.year = (short) year.getYear();
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[12]++;
        this.quarter = (byte) quarter;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[13]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[14]++;
    }

    /**
     * Constructs a new Quarter, based on a date/time and the default time zone.
     *
     * @param time  the date/time.
     */
    public Quarter(Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[15]++;
    }

    /**
     * Constructs a Quarter, based on a date/time and time zone.
     *
     * @param time  the date/time.
     * @param zone  the zone (<code>null</code> not permitted).
     */
    public Quarter(Date time, TimeZone zone) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[16]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[17]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[18]++;
        int month = calendar.get(Calendar.MONTH) + 1;
        this.quarter = (byte) SerialDate.monthCodeToQuarter(month);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[19]++;
        this.year = (short) calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[20]++;
        peg(calendar);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[21]++;
    }

    /**
     * Returns the quarter.
     *
     * @return The quarter.
     */
    public int getQuarter() {
        return this.quarter;
    }

    /**
     * Returns the year.
     *
     * @return The year.
     */
    public Year getYear() {
        return new Year(this.year);
    }
    
    /**
     * Returns the year.
     * 
     * @return The year.
     * 
     * @since 1.0.3
     */
    public int getYearValue() {
        return this.year;
    }

    /**
     * Returns the first millisecond of the quarter.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the quarter.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the quarter.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the quarter.
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
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[22]++;
        this.lastMillisecond = getLastMillisecond(calendar);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[23]++;
    }

    /**
     * Returns the quarter preceding this one.
     *
     * @return The quarter preceding this one (or <code>null</code> if this is 
     *     Q1 1900).
     */
    public RegularTimePeriod previous() {
        Quarter result;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.quarter > FIRST_QUARTER) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[5]++;
            result = new Quarter(this.quarter - 1, this.year);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[25]++;

        }
        else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[6]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.year > 1900) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[7]++;
                result = new Quarter(LAST_QUARTER, this.year - 1);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[27]++;

            }
            else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[8]++;
                result = null;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[28]++;
            }
        }
        return result;
    }

    /**
     * Returns the quarter following this one.
     *
     * @return The quarter following this one (or null if this is Q4 9999).
     */
    public RegularTimePeriod next() {
        Quarter result;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.quarter < LAST_QUARTER) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[9]++;
            result = new Quarter(this.quarter + 1, this.year);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[30]++;

        }
        else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[10]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.year < 9999) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[11]++;
                result = new Quarter(FIRST_QUARTER, this.year + 1);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[32]++;

            }
            else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[12]++;
                result = null;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[33]++;
            }
        }
        return result;
    }

    /**
     * Returns a serial index number for the quarter.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.year * 4L + this.quarter;
    }

    /**
     * Tests the equality of this Quarter object to an arbitrary object.
     * Returns <code>true</code> if the target is a Quarter instance 
     * representing the same quarter as this object.  In all other cases, 
     * returns <code>false</code>.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> if quarter and year of this and the object are
     *         the same.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[13]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof Quarter) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[15]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[36]++;
                Quarter target = (Quarter) obj;
                return (this.quarter == target.getQuarter()
                        && (this.year == target.getYearValue()));

            }
            else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[16]++;
                return false;
            }

        }
        else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[14]++;
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
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[37]++;
        int result = 17;
        result = 37 * result + this.quarter;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[38]++;
        result = 37 * result + this.year;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[39]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this Quarter object relative
     * to the specified object:
     *
     * negative == before, zero == same, positive == after.
     *
     * @param o1  the object to compare
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;

        // CASE 1 : Comparing to another Quarter object
        // --------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((o1 instanceof Quarter) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[17]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[41]++;
            Quarter q = (Quarter) o1;
            result = this.year - q.getYearValue();
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[42]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[43]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((result == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[19]++;
                result = this.quarter - q.getQuarter();
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[44]++;

            } else {
  CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[20]++;}

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[18]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[45]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[21]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[46]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[22]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[47]++;
        }
}

        return result;

    }

    /**
     * Returns a string representing the quarter (e.g. "Q1/2002").
     *
     * @return A string representing the quarter.
     */
    public String toString() {
        return "Q" + this.quarter + "/" + this.year;
    }

    /**
     * Returns the first millisecond in the Quarter, evaluated using the
     * supplied calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The first millisecond in the Quarter.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[48]++;
        int month = Quarter.FIRST_MONTH_IN_QUARTER[this.quarter];
        calendar.set(this.year, month - 1, 1, 0, 0, 0);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[49]++;
        calendar.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[50]++;
        // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the Quarter, evaluated using the
     * supplied calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The last millisecond of the Quarter.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[51]++;
        int month = Quarter.LAST_MONTH_IN_QUARTER[this.quarter];
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[52]++;
        int eom = SerialDate.lastDayOfMonth(month, this.year);
        calendar.set(this.year, month - 1, eom, 23, 59, 59);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[53]++;
        calendar.set(Calendar.MILLISECOND, 999);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[54]++;
        // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }

    /**
     * Parses the string argument as a quarter.
     * <P>
     * This method should accept the following formats: "YYYY-QN" and "QN-YYYY",
     * where the "-" can be a space, a forward-slash (/), comma or a dash (-).
     * @param s A string representing the quarter.
     *
     * @return The quarter.
     */
    public static Quarter parseQuarter(String s) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[55]++;

        // find the Q and the integer following it (remove both from the
        // string)...
        int i = s.indexOf("Q");
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i == -1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[23]++;
            throw new TimePeriodFormatException("Missing Q.");

        } else {
  CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[24]++;}
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i == s.length() - 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[25]++;
            throw new TimePeriodFormatException("Q found at end of string.");

        } else {
  CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.branches[26]++;}
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[58]++;

        String qstr = s.substring(i + 1, i + 2);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[59]++;
        int quarter = Integer.parseInt(qstr);
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[60]++;
        String remaining = s.substring(0, i) + s.substring(i + 2, s.length());

        // replace any / , or - with a space
        remaining = remaining.replace('/', ' ');
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[61]++;
        remaining = remaining.replace(',', ' ');
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[62]++;
        remaining = remaining.replace('-', ' ');
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[63]++;
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[64]++;

        // parse the string...
        Year year = Year.parseYear(remaining.trim());
CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp.statements[65]++;
        Quarter result = new Quarter(quarter, year);
        return result;

    }

}

class CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp ());
  }
    public static long[] statements = new long[66];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Quarter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$2fzn47lyuuz5s0glmqp () {
    super("org.jfree.data.time.Quarter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 65; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.Quarter.java");
      for (int i = 1; i <= 65; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

