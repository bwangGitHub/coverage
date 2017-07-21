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
 * ----------------------
 * RegularTimePeriod.java
 * ----------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 26-Feb-2002 : Changed getStart(), getMiddle() and getEnd() methods to 
 *               evaluate with reference to a particular time zone (DG);
 * 29-May-2002 : Implemented MonthConstants interface, so that these constants 
 *               are conveniently available (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 10-Jan-2003 : Renamed TimePeriod --> RegularTimePeriod (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package (DG);
 * 29-Apr-2004 : Changed getMiddleMillisecond() methods to fix bug 943985 (DG);
 * 25-Nov-2004 : Added utility methods (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Oct-2006 : Deprecated the WORKING_CALENDAR field and several methods,
 *               added new peg() method (DG);
 *
 */

package org.jfree.data.time;

import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.date.MonthConstants;

/**
 * An abstract class representing a unit of time.  Convenient methods are 
 * provided for calculating the next and previous time periods.  Conversion 
 * methods are defined that return the first and last milliseconds of the time 
 * period.  The results from these methods are timezone dependent.
 * <P>
 * This class is immutable, and all subclasses should be immutable also.
 */
public abstract class RegularTimePeriod implements TimePeriod, Comparable, 
                                                   MonthConstants {
  static {
    CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.ping();
  }


    /**
     * Creates a time period that includes the specified millisecond, assuming 
     * the given time zone.
     * 
     * @param c  the time period class.
     * @param millisecond  the time.
     * @param zone  the time zone.
     * 
     * @return The time period.
     */
    public static RegularTimePeriod createInstance(Class c, Date millisecond, 
                                                   TimeZone zone) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[1]++;
        RegularTimePeriod result = null;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[2]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[3]++;
            Constructor constructor = c.getDeclaredConstructor(
                    new Class[] {Date.class, TimeZone.class});
            result = (RegularTimePeriod) constructor.newInstance(
                    new Object[] {millisecond, zone});
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[4]++;
        }
        catch (Exception e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[2]++;
            // do nothing, so null is returned            
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[1]++;
}
  }
        return result;  
    }
    
    /**
     * Returns a subclass of {@link RegularTimePeriod} that is smaller than
     * the specified class.
     * 
     * @param c  a subclass of {@link RegularTimePeriod}.
     * 
     * @return A class.
     */
    public static Class downsize(Class c) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((c.equals(Year.class)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[3]++;
            return Quarter.class;

        }
        else {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[4]++;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[6]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((c.equals(Quarter.class)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[5]++;
            return Month.class;

        }
        else {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[6]++;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[7]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c.equals(Month.class)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[7]++;
            return Day.class;

        }
        else {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[8]++;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[8]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((c.equals(Day.class)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[9]++;
            return Hour.class;

        }
        else {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[10]++;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[9]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c.equals(Hour.class)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[11]++;
            return Minute.class;

        }
        else {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[12]++;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[10]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c.equals(Minute.class)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[13]++;
            return Second.class;

        }
        else {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[14]++;
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[11]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c.equals(Second.class)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[15]++;
            return Millisecond.class;

        }
        else {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.branches[16]++;
            return Millisecond.class;
        }
}
}
}
}
}
}
    }
    
    /**
     * Returns the time period preceding this one, or <code>null</code> if some
     * lower limit has been reached.
     *
     * @return The previous time period (possibly <code>null</code>).
     */
    public abstract RegularTimePeriod previous();

    /**
     * Returns the time period following this one, or <code>null</code> if some
     * limit has been reached.
     *
     * @return The next time period (possibly <code>null</code>).
     */
    public abstract RegularTimePeriod next();

    /**
     * Returns a serial index number for the time unit.
     *
     * @return The serial index number.
     */
    public abstract long getSerialIndex();

    //////////////////////////////////////////////////////////////////////////

    /** 
     * The default time zone. 
     */
    public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();
  static {
    CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[12]++;
  }

    /** 
     * A working calendar (recycle to avoid unnecessary object creation). 
     * 
     * @deprecated This was a bad idea, don't use it!
     */
    public static final Calendar WORKING_CALENDAR 
        = Calendar.getInstance(DEFAULT_TIME_ZONE);
  static {
    CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[13]++;
  }

    /** 
     * Recalculates the start date/time and end date/time for this time period 
     * relative to the supplied calendar (which incorporates a time zone).
     * 
     * @param calendar  the calendar (<code>null</code> not permitted).
     * 
     * @since 1.0.3
     */
    public abstract void peg(Calendar calendar);
    
    /**
     * Returns the date/time that marks the start of the time period.  This 
     * method returns a new <code>Date</code> instance every time it is called.
     *
     * @return The start date/time.
     * 
     * @see #getFirstMillisecond()
     */
    public Date getStart() {
        return new Date(getFirstMillisecond());
    }

    /**
     * Returns the date/time that marks the end of the time period.  This 
     * method returns a new <code>Date</code> instance every time it is called.
     *
     * @return The end date/time.
     * 
     * @see #getLastMillisecond()
     */
    public Date getEnd() {
        return new Date(getLastMillisecond());
    }

    /**
     * Returns the first millisecond of the time period.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The first millisecond of the time period.
     * 
     * @see #getLastMillisecond()
     */
    public abstract long getFirstMillisecond();

    /**
     * Returns the first millisecond of the time period, evaluated within a 
     * specific time zone.
     *
     * @param zone  the time zone (<code>null</code> not permitted).
     *
     * @return The first millisecond of the time period.
     * 
     * @deprecated As of 1.0.3, you should avoid using this method (it creates
     *     a new Calendar instance every time it is called).  You are advised
     *     to call {@link #getFirstMillisecond(Calendar)} instead.
     *     
     * @see #getLastMillisecond(TimeZone)
     */
    public long getFirstMillisecond(TimeZone zone) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[14]++;
        Calendar calendar = Calendar.getInstance(zone);
        return getFirstMillisecond(calendar);
    }

    /**
     * Returns the first millisecond of the time period, evaluated using the 
     * supplied calendar (which incorporates a timezone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The first millisecond of the time period.
     * 
     * @throws NullPointerException if <code>calendar,/code> is 
     *     </code>null</code>.
     *     
     * @see #getLastMillisecond(Calendar)
     */
    public abstract long getFirstMillisecond(Calendar calendar);

    /**
     * Returns the last millisecond of the time period.  This will be 
     * determined relative to the time zone specified in the constructor, or
     * in the calendar instance passed in the most recent call to the 
     * {@link #peg(Calendar)} method.
     *
     * @return The last millisecond of the time period.
     * 
     * @see #getFirstMillisecond()
     */
    public abstract long getLastMillisecond();

    /**
     * Returns the last millisecond of the time period, evaluated within a 
     * specific time zone.
     *
     * @param zone  the time zone (<code>null</code> not permitted).
     *
     * @return The last millisecond of the time period.
     * 
     * @deprecated As of 1.0.3, you should avoid using this method (it creates
     *     a new Calendar instance every time it is called).  You are advised
     *     to call {@link #getLastMillisecond(Calendar)} instead.
     *     
     * @see #getFirstMillisecond(TimeZone)
     */
    public long getLastMillisecond(TimeZone zone) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[15]++;
        Calendar calendar = Calendar.getInstance(zone);
        return getLastMillisecond(calendar);
    }

    /**
     * Returns the last millisecond of the time period, evaluated using the 
     * supplied calendar (which incorporates a timezone).
     *
     * @param calendar  the calendar (<code>null</code> not permitted).
     *
     * @return The last millisecond of the time period.
     * 
     * @see #getFirstMillisecond(Calendar)
     */
    public abstract long getLastMillisecond(Calendar calendar);

    /**
     * Returns the millisecond closest to the middle of the time period.
     *
     * @return The middle millisecond.
     */
    public long getMiddleMillisecond() {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[16]++;
        long m1 = getFirstMillisecond();
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[17]++;
        long m2 = getLastMillisecond();
        return m1 + (m2 - m1) / 2;
    }

    /**
     * Returns the millisecond closest to the middle of the time period,
     * evaluated within a specific time zone.
     *
     * @param zone  the time zone (<code>null</code> not permitted).
     *
     * @return The middle millisecond.
     * 
     * @deprecated As of 1.0.3, you should avoid using this method (it creates
     *     a new Calendar instance every time it is called).  You are advised
     *     to call {@link #getMiddleMillisecond(Calendar)} instead.
     */
    public long getMiddleMillisecond(TimeZone zone) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[18]++;
        Calendar calendar = Calendar.getInstance(zone);
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[19]++;
        long m1 = getFirstMillisecond(calendar);
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[20]++;
        long m2 = getLastMillisecond(calendar);
        return m1 + (m2 - m1) / 2;
    }

    /**
     * Returns the millisecond closest to the middle of the time period,
     * evaluated using the supplied calendar (which incorporates a timezone).
     *
     * @param calendar  the calendar.
     *
     * @return The middle millisecond.
     */
    public long getMiddleMillisecond(Calendar calendar) {
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[21]++;
        long m1 = getFirstMillisecond(calendar);
CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9.statements[22]++;
        long m2 = getLastMillisecond(calendar);
        return m1 + (m2 - m1) / 2;
    }

    /**
     * Returns a string representation of the time period.
     *
     * @return The string.
     */
    public String toString() {
        return String.valueOf(getStart());
    }

}

class CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9 ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.time.RegularTimePeriod.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$dipdw6o0ov4jvbitpw5ldl8ifxyv014rq9 () {
    super("org.jfree.data.time.RegularTimePeriod.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
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
    log.startNamedSection("org.jfree.data.time.RegularTimePeriod.java");
      for (int i = 1; i <= 22; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
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

