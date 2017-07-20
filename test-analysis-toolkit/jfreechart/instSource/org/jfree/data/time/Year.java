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
 * Year.java
 * ---------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 14-Nov-2001 : Override for toString() method (DG);
 * 19-Dec-2001 : Added a new constructor as suggested by Paul English (DG);
 * 29-Jan-2002 : Worked on parseYear() method (DG);
 * 14-Feb-2002 : Fixed bug in Year(Date) constructor (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 19-Mar-2002 : Changed API for TimePeriod classes (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Jan-2003 : Changed base class and method names (DG);
 * 05-Mar-2003 : Fixed bug in getFirstMillisecond() picked up in JUnit 
 *               tests (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package, and implemented 
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

import org.jfree.date.SerialDate;

/**
 * Represents a year in the range 1900 to 9999.  This class is immutable, which
 * is a requirement for all {@link RegularTimePeriod} subclasses.
 */
public class Year extends RegularTimePeriod implements Serializable {
  static {
    CodeCoverCoverageCounter$9o0tqx2egqsrxd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7659990929736074836L;
  static {
    CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[1]++;
  }
    
    /** The year. */
    private short year;

    /** The first millisecond. */
    private long firstMillisecond;
    
    /** The last millisecond. */
    private long lastMillisecond;
    
    /**
     * Creates a new <code>Year</code>, based on the current system date/time.
     */
    public Year() {
        this(new Date());
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[2]++;
    }

    /**
     * Creates a time period representing a single year.
     *
     * @param year  the year.
     */
    public Year(int year) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((year < SerialDate.MINIMUM_YEAR_SUPPORTED) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((year > SerialDate.MAXIMUM_YEAR_SUPPORTED) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[1]++;

            throw new IllegalArgumentException(
                "Year constructor: year (" + year + ") outside valid range.");

        } else {
  CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[2]++;}
        this.year = (short) year;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[4]++;
        peg(Calendar.getInstance());
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[5]++;
    }

    /**
     * Creates a new <code>Year</code>, based on a particular instant in time, 
     * using the default time zone.
     *
     * @param time  the time (<code>null</code> not permitted).
     */
    public Year(Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[6]++;
    }

    /**
     * Constructs a year, based on a particular instant in time and a time zone.
     *
     * @param time  the time.
     * @param zone  the time zone.
     */
    public Year(Date time, TimeZone zone) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[7]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[8]++;
        this.year = (short) calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[9]++;
        peg(calendar);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[10]++;
    }

    /**
     * Returns the year.
     *
     * @return The year.
     */
    public int getYear() {
        return this.year;
    }
    
    /**
     * Returns the first millisecond of the year.  This will be determined 
     * relative to the time zone specified in the constructor, or in the 
     * calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the year.
     * 
     * @see #getLastMillisecond()
     */
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }

    /**
     * Returns the last millisecond of the year.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the year.
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
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[11]++;
        this.lastMillisecond = getLastMillisecond(calendar);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[12]++;
    }
    
    /**
     * Returns the year preceding this one.
     *
     * @return The year preceding this one (or <code>null</code> if the 
     *         current year is 1900).
     */
    public RegularTimePeriod previous() {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.year > SerialDate.MINIMUM_YEAR_SUPPORTED) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[3]++;
            return new Year(this.year - 1);

        }
        else {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[4]++;
            return null;
        }
    }

    /**
     * Returns the year following this one.
     *
     * @return The year following this one (or <code>null</code> if the current
     *         year is 9999).
     */
    public RegularTimePeriod next() {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.year < SerialDate.MAXIMUM_YEAR_SUPPORTED) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[5]++;
            return new Year(this.year + 1);

        }
        else {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[6]++;
            return null;
        }
    }

    /**
     * Returns a serial index number for the year.
     * <P>
     * The implementation simply returns the year number (e.g. 2002).
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.year;
    }

    /**
     * Returns the first millisecond of the year, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The first millisecond of the year.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getFirstMillisecond(Calendar calendar) {
        calendar.set(this.year, Calendar.JANUARY, 1, 0, 0, 0);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[15]++;
        calendar.set(Calendar.MILLISECOND, 0);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[16]++;
        // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }

    /**
     * Returns the last millisecond of the year, evaluated using the supplied
     * calendar (which determines the time zone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The last millisecond of the year.
     *
     * @throws NullPointerException if <code>calendar</code> is 
     *     <code>null</code>.
     */
    public long getLastMillisecond(Calendar calendar) {
        calendar.set(this.year, Calendar.DECEMBER, 31, 23, 59, 59);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[17]++;
        calendar.set(Calendar.MILLISECOND, 999);
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[18]++;
        // in the following line, we'd rather call calendar.getTimeInMillis()
        // to avoid object creation, but that isn't supported in Java 1.3.1
        return calendar.getTime().getTime();
    }
    
    /**
     * Tests the equality of this <code>Year</code> object to an arbitrary 
     * object.  Returns <code>true</code> if the target is a <code>Year</code>
     * instance representing the same year as this object.  In all other cases,
     * returns <code>false</code>.
     *
     * @param object  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> if the year of this and the object are the 
     *         same.
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((object != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[7]++;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((object instanceof Year) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[9]++;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[21]++;
                Year target = (Year) object;
                return (this.year == target.getYear());

            }
            else {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[10]++;
                return false;
            }

        }
        else {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[8]++;
            return false;
        }
    }
    
    /**
     * Returns a hash code for this object instance.  The approach described by
     * Joshua Bloch in "Effective Java" has been used here:
     * <p>
     * <code>http://developer.java.sun.com/developer/Books/effectivejava
     *     /Chapter3.pdf</code>
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[22]++;
        int result = 17;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[23]++;
        int c = this.year;
        result = 37 * result + c;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[24]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this <code>Year</code> object
     * relative to the specified object:
     *
     * negative == before, zero == same, positive == after.
     *
     * @param o1  the object to compare.
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;

        // CASE 1 : Comparing to another Year object
        // -----------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((o1 instanceof Year) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[11]++;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[26]++;
            Year y = (Year) o1;
            result = this.year - y.getYear();
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[27]++;

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[12]++;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[28]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9o0tqx2egqsrxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[13]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[29]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[14]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[30]++;
        }
}

        return result;

    }

    /**
     * Returns a string representing the year..
     *
     * @return A string representing the year.
     */
    public String toString() {
        return Integer.toString(this.year);
    }

    /**
     * Parses the string argument as a year.
     * <P>
     * The string format is YYYY.
     *
     * @param s  a string representing the year.
     *
     * @return <code>null</code> if the string is not parseable, the year 
     *         otherwise.
     */
    public static Year parseYear(String s) {

        // parse the string...
        int y;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[31]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            y = Integer.parseInt(s.trim());
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[32]++;
        }
        catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[16]++;
            throw new TimePeriodFormatException("Cannot parse string.");
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[15]++;
}
  }
CodeCoverCoverageCounter$9o0tqx2egqsrxd.statements[33]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

        // create the year...
        try {
CodeCoverTryBranchHelper_Try2 = true;
            return new Year(y);
        }
        catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[18]++;
            throw new TimePeriodFormatException("Year outside valid range.");
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$9o0tqx2egqsrxd.branches[17]++;
}
  }
    }

}

class CodeCoverCoverageCounter$9o0tqx2egqsrxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9o0tqx2egqsrxd ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.time.Year.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$9o0tqx2egqsrxd () {
    super("org.jfree.data.time.Year.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.Year.java");
      for (int i = 1; i <= 33; i++) {
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
    for (int i = 1; i <= 7; i++) {
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

