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
 * --------
 * Day.java
 * --------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 15-Nov-2001 : Updated Javadoc comments (DG);
 * 04-Dec-2001 : Added static method to parse a string into a Day object (DG);
 * 19-Dec-2001 : Added new constructor as suggested by Paul English (DG);
 * 29-Jan-2002 : Changed getDay() method to getSerialDate() (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 19-Mar-2002 : Changed the API for the TimePeriod classes (DG);
 * 29-May-2002 : Fixed bug in equals method (DG);
 * 24-Jun-2002 : Removed unnecessary imports (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package, and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * 30-Sep-2004 : Replaced getTime().getTime() with getTimeInMillis() (DG);
 * 04-Nov-2004 : Reverted change of 30-Sep-2004, because it won't work for 
 *               JDK 1.3 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Oct-2006 : Updated API docs (DG);
 * 06-Oct-2006 : Refactored to cache first and last millisecond values (DG);
 * 
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.date.SerialDate;

/**
 * Represents a single day in the range 1-Jan-1900 to 31-Dec-9999.  This class 
 * is immutable, which is a requirement for all {@link RegularTimePeriod} 
 * subclasses.
 */
public class Day extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$11foorckwfhi9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7082667380758962755L;
  static {
    CodeCoverCoverageCounter$11foorckwfhi9.statements[1]++;
  }
    
    /** A standard date formatter. */
    protected static final DateFormat DATE_FORMAT 
        = new SimpleDateFormat("yyyy-MM-dd");
  static {
    CodeCoverCoverageCounter$11foorckwfhi9.statements[2]++;
  }

    /** A date formatter for the default locale. */
    protected static final DateFormat
        DATE_FORMAT_SHORT = DateFormat.getDateInstance(DateFormat.SHORT);
  static {
    CodeCoverCoverageCounter$11foorckwfhi9.statements[3]++;
  }

    /** A date formatter for the default locale. */
    protected static final DateFormat
        DATE_FORMAT_MEDIUM = DateFormat.getDateInstance(DateFormat.MEDIUM);
  static {
    CodeCoverCoverageCounter$11foorckwfhi9.statements[4]++;
  }

    /** A date formatter for the default locale. */
    protected static final DateFormat
        DATE_FORMAT_LONG = DateFormat.getDateInstance(DateFormat.LONG);
  static {
    CodeCoverCoverageCounter$11foorckwfhi9.statements[5]++;
  }

    /** The day (uses SerialDate for convenience). */
    private SerialDate serialDate;

    /** The first millisecond. */
    private long firstMillisecond;
    
    /** The last millisecond. */
    private long lastMillisecond;

    /**
     * Creates a new instance, derived from the system date/time (and assuming 
     * the default timezone).
     */
    public Day() {
        this(new Date());
CodeCoverCoverageCounter$11foorckwfhi9.statements[6]++;
    }

    /**
     * Constructs a new one day time period.
     *
     * @param day  the day-of-the-month.
     * @param month  the month (1 to 12).
     * @param year  the year (1900 <= year <= 9999).
     */
    public Day(int day, int month, int year) {
        this.serialDate = SerialDate.createInstance(day, month, year);
CodeCoverCoverageCounter$11foorckwfhi9.statements[7]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$11foorckwfhi9.statements[8]++;
    }

    /**
     * Constructs a new one day time period.
     *
     * @param serialDate  the day (<code>null</code> not permitted).
     */
    public Day(SerialDate serialDate) {
CodeCoverCoverageCounter$11foorckwfhi9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((serialDate == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[1]++;
            throw new IllegalArgumentException("Null 'serialDate' argument.");

        } else {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[2]++;}
        this.serialDate = serialDate;
CodeCoverCoverageCounter$11foorckwfhi9.statements[10]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$11foorckwfhi9.statements[11]++;
    }

    /**
     * Constructs a new instance, based on a particular date/time and the 
     * default time zone.
     *
     * @param time  the time (<code>null</code> not permitted).
     */
    public Day(Date time) {
        // defer argument checking...
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$11foorckwfhi9.statements[12]++;
    }

    /**
     * Constructs a new instance, based on a particular date/time and time zone.
     *
     * @param time  the date/time.
     * @param zone  the time zone.
     */
    public Day(Date time, TimeZone zone) {
CodeCoverCoverageCounter$11foorckwfhi9.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((time == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[3]++;
            throw new IllegalArgumentException("Null 'time' argument.");

        } else {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[4]++;}
CodeCoverCoverageCounter$11foorckwfhi9.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[5]++;
            throw new IllegalArgumentException("Null 'zone' argument.");

        } else {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[6]++;}
CodeCoverCoverageCounter$11foorckwfhi9.statements[15]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$11foorckwfhi9.statements[16]++;
CodeCoverCoverageCounter$11foorckwfhi9.statements[17]++;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
CodeCoverCoverageCounter$11foorckwfhi9.statements[18]++;
        int m = calendar.get(Calendar.MONTH) + 1;
CodeCoverCoverageCounter$11foorckwfhi9.statements[19]++;
        int y = calendar.get(Calendar.YEAR);
        this.serialDate = SerialDate.createInstance(d, m, y);
CodeCoverCoverageCounter$11foorckwfhi9.statements[20]++;
        peg(calendar);
CodeCoverCoverageCounter$11foorckwfhi9.statements[21]++;
    }

    /**
     * Returns the day as a {@link SerialDate}.  Note: the reference that is 
     * returned should be an instance of an immutable {@link SerialDate} 
     * (otherwise the caller could use the reference to alter the state of 
     * this <code>Day</code> instance, and <code>Day</code> is supposed
     * to be immutable).
     *
     * @return The day as a {@link SerialDate}.
     */
    public SerialDate getSerialDate() {
        return this.serialDate;
    }

    /**
     * Returns the year.
     *
     * @return The year.
     */
    public int getYear() {
        return this.serialDate.getYYYY();
    }

    /**
     * Returns the month.
     *
     * @return The month.
     */
    public int getMonth() {
        return this.serialDate.getMonth();
    }

    /**
     * Returns the day of the month.
     *
     * @return The day of the month.
     */
    public int getDayOfMonth() {
        return this.serialDate.getDayOfMonth();
    }

    /**
     * Returns the first millisecond of the day.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the day.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the day.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the day.
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
CodeCoverCoverageCounter$11foorckwfhi9.statements[22]++;
        this.lastMillisecond = getLastMillisecond(calendar);
CodeCoverCoverageCounter$11foorckwfhi9.statements[23]++;
    }

    /**
     * Returns the day preceding this one.
     *
     * @return The day preceding this one.
     */
    public RegularTimePeriod previous() {

        Day result;
CodeCoverCoverageCounter$11foorckwfhi9.statements[24]++;
        int serial = this.serialDate.toSerial();
CodeCoverCoverageCounter$11foorckwfhi9.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((serial > SerialDate.SERIAL_LOWER_BOUND) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[7]++;
CodeCoverCoverageCounter$11foorckwfhi9.statements[26]++;
            SerialDate yesterday = SerialDate.createInstance(serial - 1);
            return new Day(yesterday);

        }
        else {
CodeCoverCoverageCounter$11foorckwfhi9.branches[8]++;
            result = null;
CodeCoverCoverageCounter$11foorckwfhi9.statements[27]++;
        }
        return result;

    }

    /**
     * Returns the day following this one, or <code>null</code> if some limit 
     * has been reached.
     *
     * @return The day following this one, or <code>null</code> if some limit 
     *         has been reached.
     */
    public RegularTimePeriod next() {

        Day result;
CodeCoverCoverageCounter$11foorckwfhi9.statements[28]++;
        int serial = this.serialDate.toSerial();
CodeCoverCoverageCounter$11foorckwfhi9.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((serial < SerialDate.SERIAL_UPPER_BOUND) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[9]++;
CodeCoverCoverageCounter$11foorckwfhi9.statements[30]++;
            SerialDate tomorrow = SerialDate.createInstance(serial + 1);
            return new Day(tomorrow);

        }
        else {
CodeCoverCoverageCounter$11foorckwfhi9.branches[10]++;
            result = null;
CodeCoverCoverageCounter$11foorckwfhi9.statements[31]++;
        }
        return result;

    }

    /**
     * Returns a serial index number for the day.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.serialDate.toSerial();
    }

    /**
     * Returns the first millisecond of the day, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  calendar to use (<code>null</code> not permitted).
     *
     * @return The start of the day as milliseconds since 01-01-1970.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$11foorckwfhi9.statements[32]++;
        int year = this.serialDate.getYYYY();
CodeCoverCoverageCounter$11foorckwfhi9.statements[33]++;
        int month = this.serialDate.getMonth();
CodeCoverCoverageCounter$11foorckwfhi9.statements[34]++;
        int day = this.serialDate.getDayOfMonth();
        calendar.clear();
CodeCoverCoverageCounter$11foorckwfhi9.statements[35]++;
        calendar.set(year, month - 1, day, 0, 0, 0);
CodeCoverCoverageCounter$11foorckwfhi9.statements[36]++;
        calendar.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$11foorckwfhi9.statements[37]++;
        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the day, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  calendar to use (<code>null</code> not permitted).
     *
     * @return The end of the day as milliseconds since 01-01-1970.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$11foorckwfhi9.statements[38]++;
        int year = this.serialDate.getYYYY();
CodeCoverCoverageCounter$11foorckwfhi9.statements[39]++;
        int month = this.serialDate.getMonth();
CodeCoverCoverageCounter$11foorckwfhi9.statements[40]++;
        int day = this.serialDate.getDayOfMonth();
        calendar.clear();
CodeCoverCoverageCounter$11foorckwfhi9.statements[41]++;
        calendar.set(year, month - 1, day, 23, 59, 59);
CodeCoverCoverageCounter$11foorckwfhi9.statements[42]++;
        calendar.set(Calendar.MILLISECOND, 999);
CodeCoverCoverageCounter$11foorckwfhi9.statements[43]++;
        //return calendar.getTimeInMillis();  // this won't work for JDK 1.3
        return calendar.getTime().getTime();
    }

    /**
     * Tests the equality of this Day object to an arbitrary object.  Returns
     * true if the target is a Day instance or a SerialDate instance
     * representing the same day as this object. In all other cases,
     * returns false.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A flag indicating whether or not an object is equal to this day.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$11foorckwfhi9.statements[44]++;
int CodeCoverConditionCoverageHelper_C6;
        
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[12]++;}
CodeCoverCoverageCounter$11foorckwfhi9.statements[45]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof Day) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[14]++;}
CodeCoverCoverageCounter$11foorckwfhi9.statements[46]++;
        Day that = (Day) obj;
CodeCoverCoverageCounter$11foorckwfhi9.statements[47]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.serialDate.equals(that.getSerialDate())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[16]++;}
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
        return this.serialDate.hashCode();
    }

    /**
     * Returns an integer indicating the order of this Day object relative to
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
CodeCoverCoverageCounter$11foorckwfhi9.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;

        // CASE 1 : Comparing to another Day object
        // ----------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((o1 instanceof Day) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[17]++;
CodeCoverCoverageCounter$11foorckwfhi9.statements[49]++;
            Day d = (Day) o1;
            result = -d.getSerialDate().compare(this.serialDate);
CodeCoverCoverageCounter$11foorckwfhi9.statements[50]++;

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$11foorckwfhi9.branches[18]++;
CodeCoverCoverageCounter$11foorckwfhi9.statements[51]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$11foorckwfhi9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$11foorckwfhi9.branches[19]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$11foorckwfhi9.statements[52]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$11foorckwfhi9.branches[20]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$11foorckwfhi9.statements[53]++;
        }
}

        return result;

    }

    /**
     * Returns a string representing the day.
     *
     * @return A string representing the day.
     */
    public String toString() {
        return this.serialDate.toString();
    }

    /**
     * Parses the string argument as a day.
     * <P>
     * This method is required to recognise YYYY-MM-DD as a valid format.
     * Anything else, for now, is a bonus.
     *
     * @param s  the date string to parse.
     *
     * @return <code>null</code> if the string does not contain any parseable
     *      string, the day otherwise.
     */
    public static Day parseDay(String s) {
CodeCoverCoverageCounter$11foorckwfhi9.statements[54]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

        try {
CodeCoverTryBranchHelper_Try1 = true;
            return new Day (Day.DATE_FORMAT.parse(s));
        }
        catch (ParseException e1) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$11foorckwfhi9.branches[22]++;
CodeCoverCoverageCounter$11foorckwfhi9.statements[55]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                return new Day (Day.DATE_FORMAT_SHORT.parse(s));
            }
            catch (ParseException e2) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$11foorckwfhi9.branches[24]++;
              // ignore
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[23]++;
}
  }
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$11foorckwfhi9.branches[21]++;
}
  }
        return null;

    }

}

class CodeCoverCoverageCounter$11foorckwfhi9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11foorckwfhi9 ());
  }
    public static long[] statements = new long[56];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Day.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$11foorckwfhi9 () {
    super("org.jfree.data.time.Day.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 55; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.Day.java");
      for (int i = 1; i <= 55; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
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

