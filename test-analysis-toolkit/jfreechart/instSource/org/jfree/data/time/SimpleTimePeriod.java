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
 * SimpleTimePeriod.java
 * ---------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Oct-2002 : Added Javadocs (DG);
 * 10-Jan-2003 : Renamed TimeAllocation --> SimpleTimePeriod (DG);
 * 13-Mar-2003 : Added equals() method, and Serializable interface (DG);
 * 21-Oct-2003 : Added hashCode() method (DG);
 * 27-Jan-2005 : Implemented Comparable, to enable this class to be used
 *               in the TimeTableXYDataset class (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Date;

/**
 * An arbitrary period of time, measured to millisecond precision using 
 * <code>java.util.Date</code>.
 * <p>
 * This class is intentionally immutable (that is, once constructed, you cannot 
 * alter the start and end attributes).
 */
public class SimpleTimePeriod implements TimePeriod, Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8684672361131829554L;
  static {
    CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[1]++;
  }
    
    /** The start date/time. */
    private Date start;

    /** The end date/time. */
    private Date end;

    /**
     * Creates a new time allocation.
     *
     * @param start  the start date/time in milliseconds.
     * @param end  the end date/time in milliseconds.
     */
    public SimpleTimePeriod(long start, long end) {
        this(new Date(start), new Date(end));
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[2]++;   
    }
    
    /**
     * Creates a new time allocation.
     *
     * @param start  the start date/time (<code>null</code> not permitted).
     * @param end  the end date/time (<code>null</code> not permitted).
     */
    public SimpleTimePeriod(Date start, Date end) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((start.getTime() > end.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[1]++;
            throw new IllegalArgumentException("Requires end >= start.");

        } else {
  CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[2]++;}
        this.start = start;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[4]++;
        this.end = end;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[5]++;
    }

    /**
     * Returns the start date/time.
     *
     * @return The start date/time (never <code>null</code>).
     */
    public Date getStart() {
        return this.start;
    }

    /**
     * Returns the end date/time.
     *
     * @return The end date/time (never <code>null</code>).
     */
    public Date getEnd() {
        return this.end;
    }

    /**
     * Tests this time period instance for equality with an arbitrary object.  
     * The object is considered equal if it is an instance of {@link TimePeriod}
     * and it has the same start and end dates.
     *
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[4]++;}
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof TimePeriod) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[6]++;}
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[8]++;
        TimePeriod that = (TimePeriod) obj;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.start.equals(that.getStart())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[8]++;}
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.end.equals(that.getEnd())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[9]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[10]++;}
        return true;
    }
    
    /**
     * Returns an integer that indicates the relative ordering of two
     * time periods.
     * 
     * @param obj  the object (<code>null</code> not permitted).
     * 
     * @return An integer.
     * 
     * @throws ClassCastException if <code>obj</code> is not an instance of
     *                            {@link TimePeriod}.
     */
    public int compareTo(Object obj) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[11]++;        
        TimePeriod that = (TimePeriod) obj;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[12]++;
        long t0 = getStart().getTime();
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[13]++;
        long t1 = getEnd().getTime();
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[14]++;
        long m0 = t0 + (t1 - t0) / 2L;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[15]++;
        long t2 = that.getStart().getTime();
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[16]++;
        long t3 = that.getEnd().getTime();
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[17]++;
        long m1 = t2 + (t3 - t2) / 2L;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((m0 < m1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[11]++;
            return -1;
   
        }
        else {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[12]++;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[19]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((m0 > m1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[13]++;
            return 1;
   
        }
        else {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[14]++;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((t0 < t2) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[15]++;
                return -1;

            }
            else {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[16]++;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[21]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((t0 > t2) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[17]++;
                return 1;
   
            }
            else {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[18]++;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[22]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((t1 < t3) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[19]++;
                    return -1;
   
                }
                else {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[20]++;
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[23]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((t1 > t3) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[21]++;
                    return 1;
   
                }
                else {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.branches[22]++;
                    return 0;   
                }
}
            }
}
        }
}
    }
    
    /**
     * Returns a hash code for this object instance.  The approach described by
     * Joshua Bloch in "Effective Java" has been used here - see:
     * <p>
     * <code>http://developer.java.sun.com/
     * developer/Books/effectivejava/Chapter3.pdf</code>
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[24]++;
        int result = 17;
        result = 37 * result + this.start.hashCode();
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[25]++;
        result = 37 * result + this.end.hashCode();
CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd.statements[26]++;
        return result;
    }

}

class CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd ());
  }
    public static long[] statements = new long[27];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.data.time.SimpleTimePeriod.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$1xabgt87zg21ahmbzazpny7mv16m8f1pd () {
    super("org.jfree.data.time.SimpleTimePeriod.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 26; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.SimpleTimePeriod.java");
      for (int i = 1; i <= 26; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

