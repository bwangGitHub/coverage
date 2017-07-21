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
 * ---------------------
 * FixedMillisecond.java
 * ---------------------
 * (C) Copyright 2002-2007 by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Mar-2002 : Version 1, based on original Millisecond implementation (DG);
 * 24-Jun-2002 : Removed unnecessary imports (DG);
 * 10-Sep-2002 : Added getSerialIndex() method (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package and implemented 
 *               Serializable (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Oct-2006 : Added peg() method (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Wrapper for a <code>java.util.Date</code> object that allows it to be used 
 * as a {@link RegularTimePeriod}.  This class is immutable, which is a 
 * requirement for all {@link RegularTimePeriod} subclasses.
 */
public class FixedMillisecond extends RegularTimePeriod 
                              implements Serializable {
  static {
    CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7867521484545646931L;
  static {
    CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[1]++;
  }
    
    /** The millisecond. */
    private Date time;

    /**
     * Constructs a millisecond based on the current system time.
     */
    public FixedMillisecond() {
        this(new Date());
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[2]++;
    }

    /**
     * Constructs a millisecond.
     *
     * @param millisecond  the millisecond (same encoding as java.util.Date).
     */
    public FixedMillisecond(long millisecond) {
        this(new Date(millisecond));
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[3]++;
    }

    /**
     * Constructs a millisecond.
     *
     * @param time  the time.
     */
    public FixedMillisecond(Date time) {
        this.time = time;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[4]++;
    }

    /**
     * Returns the date/time.
     *
     * @return The date/time.
     */
    public Date getTime() {
        return this.time;
    }

    /**
     * This method is overridden to do nothing.
     * 
     * @param calendar  ignored
     * 
     * @since 1.0.3
     */
    public void peg(Calendar calendar) {
        // nothing to do        
    }

    /**
     * Returns the millisecond preceding this one.
     *
     * @return The millisecond preceding this one.
     */
    public RegularTimePeriod previous() {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[5]++;
        RegularTimePeriod result = null;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[6]++;
        long t = this.time.getTime();
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((t != Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[1]++;
            result = new FixedMillisecond(t - 1);
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[8]++;

        } else {
  CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[2]++;}
        return result;
    }

    /**
     * Returns the millisecond following this one.
     *
     * @return The millisecond following this one.
     */
    public RegularTimePeriod next() {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[9]++;
        RegularTimePeriod result = null;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[10]++;
        long t = this.time.getTime();
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((t != Long.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[3]++;
            result = new FixedMillisecond(t + 1);
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[12]++;

        } else {
  CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[4]++;}
        return result;
    }

    /**
     * Tests the equality of this object against an arbitrary Object.
     *
     * @param object  the object to compare
     *
     * @return A boolean.
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((object instanceof FixedMillisecond) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[5]++;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[14]++;
            FixedMillisecond m = (FixedMillisecond) object;
            return this.time.equals(m.getTime());

        }
        else {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[6]++;
            return false;
        }

    }

    /**
     * Returns a hash code for this object instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return this.time.hashCode();
    }

    /**
     * Returns an integer indicating the order of this Millisecond object
     * relative to the specified
     * object: negative == before, zero == same, positive == after.
     *
     * @param o1    the object to compare.
     *
     * @return negative == before, zero == same, positive == after.
     */
    public int compareTo(Object o1) {

        int result;
        long difference;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;

        // CASE 1 : Comparing to another Second object
        // -------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((o1 instanceof FixedMillisecond) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[7]++;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[16]++;
            FixedMillisecond t1 = (FixedMillisecond) o1;
            difference = this.time.getTime() - t1.time.getTime();
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[17]++;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((difference > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[9]++;
                result = 1;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[19]++;

            }
            else {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[10]++;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((difference < 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[11]++;
                   result = -1;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[21]++;

                }
                else {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[12]++;
                    result = 0;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[22]++;
                }
            }

        }

        // CASE 2 : Comparing to another TimePeriod object
        // -----------------------------------------------
        else {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[8]++;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[23]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((o1 instanceof RegularTimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[13]++;
            // more difficult case - evaluate later...
            result = 0;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[24]++;

        }

        // CASE 3 : Comparing to a non-TimePeriod object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.branches[14]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh.statements[25]++;
        }
}

        return result;

    }

    /**
     * Returns the first millisecond of the time period.
     *
     * @return The first millisecond of the time period.
     */
    public long getFirstMillisecond() {
        return this.time.getTime();
    }


    /**
     * Returns the first millisecond of the time period.
     *
     * @param calendar  the calendar.
     *
     * @return The first millisecond of the time period.
     */
    public long getFirstMillisecond(Calendar calendar) {
        return this.time.getTime();
    }

    /**
     * Returns the last millisecond of the time period.
     *
     * @return The last millisecond of the time period.
     */
    public long getLastMillisecond() {
        return this.time.getTime();
    }

    /**
     * Returns the last millisecond of the time period.
     *
     * @param calendar  the calendar.
     *
     * @return The last millisecond of the time period.
     */
    public long getLastMillisecond(Calendar calendar) {
        return this.time.getTime();
    }

    /**
     * Returns the millisecond closest to the middle of the time period.
     *
     * @return The millisecond closest to the middle of the time period.
     */
    public long getMiddleMillisecond() {
        return this.time.getTime();
    }

    /**
     * Returns the millisecond closest to the middle of the time period.
     *
     * @param calendar  the calendar.
     *
     * @return The millisecond closest to the middle of the time period.
     */
    public long getMiddleMillisecond(Calendar calendar) {
        return this.time.getTime();
    }

    /**
     * Returns a serial index number for the millisecond.
     *
     * @return The serial index number.
     */
    public long getSerialIndex() {
        return this.time.getTime();
    }

}

class CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.time.FixedMillisecond.java";
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

  public CodeCoverCoverageCounter$1mhkqi0xr5ry6tol8w2528h1r5d3ymlwh () {
    super("org.jfree.data.time.FixedMillisecond.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
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
    log.startNamedSection("org.jfree.data.time.FixedMillisecond.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
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

