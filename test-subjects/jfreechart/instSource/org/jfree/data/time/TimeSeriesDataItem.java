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
 * -----------------------
 * TimeSeriesDataItem.java
 * -----------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 15-Nov-2001 : Updated Javadoc comments (DG);
 * 29-Nov-2001 : Added cloning (DG);
 * 24-Jun-2002 : Removed unnecessary import (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Mar-2003 : Renamed TimeSeriesDataPair --> TimeSeriesDataItem, moved to
 *               com.jrefinery.data.time package, implemented Serializable (DG)
 */

package org.jfree.data.time;

import java.io.Serializable;

/**
 * Represents one data item in a time series.
 * <P>
 * The time period can be any of the following:
 * <ul>
 * <li>{@link Year}</li>
 * <li>{@link Quarter}</li>
 * <li>{@link Month}</li>
 * <li>{@link Week}</li>
 * <li>{@link Day}</li>
 * <li>{@link Hour}</li>
 * <li>{@link Minute}</li>
 * <li>{@link Second}</li>
 * <li>{@link Millisecond}</li>
 * <li>{@link FixedMillisecond}</li>
 * </ul>
 *
 * The time period is an immutable property of the data item.  Data items will
 * often be sorted within a list, and allowing the time period to be changed
 * could destroy the sort order.
 * <P>
 * Implements the <code>Comparable</code> interface so that standard Java 
 * sorting can be used to keep the data items in order.
 *
 */
public class TimeSeriesDataItem implements Cloneable, Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2235346966016401302L;
  static {
    CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[1]++;
  }
    
    /** The time period. */
    private RegularTimePeriod period;

    /** The value associated with the time period. */
    private Number value;

    /**
     * Constructs a new data item that associates a value with a time period.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public TimeSeriesDataItem(RegularTimePeriod period, Number value) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[1]++;
            throw new IllegalArgumentException("Null 'period' argument.");
   
        } else {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[2]++;}
        this.period = period;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[3]++;
        this.value = value;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[4]++;
    }

    /**
     * Constructs a new data item that associates a value with a time period.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value associated with the time period.
     */
    public TimeSeriesDataItem(RegularTimePeriod period, double value) {
        this(period, new Double(value));
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[5]++;
    }

    /**
     * Returns the time period.
     *
     * @return The time period (never <code>null</code>).
     */
    public RegularTimePeriod getPeriod() {
        return this.period;
    }

    /**
     * Returns the value.
     *
     * @return The value (<code>null</code> possible).
     */
    public Number getValue() {
        return this.value;
    }

    /**
     * Sets the value for this data item.
     *
     * @param value  the value (<code>null</code> permitted).
     */
    public void setValue(Number value) {
        this.value = value;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[6]++;
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param o  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object o) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this == o) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[4]++;}
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((o instanceof TimeSeriesDataItem) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[6]++;}
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[9]++;
        TimeSeriesDataItem timeSeriesDataItem = (TimeSeriesDataItem) o;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.period != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[7]++;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.period.equals(timeSeriesDataItem.period)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[9]++;
                return false;

            } else {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[10]++;}

        }
        else {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[8]++;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[12]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((timeSeriesDataItem.period != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[11]++;
           return false;

        } else {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[12]++;}
}
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;
        
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.value != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[13]++;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[14]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.value.equals(timeSeriesDataItem.value)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[15]++;
                return false;

            } else {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[16]++;}

        }
        else {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[14]++;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[15]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((timeSeriesDataItem.value != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[18]++;}
}

        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = (this.period != null ? this.period.hashCode() : 0);
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[16]++;
        result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[17]++;
        return result;
    }

    /**
     * Returns an integer indicating the order of this data pair object
     * relative to another object.
     * <P>
     * For the order we consider only the timing:
     * negative == before, zero == same, positive == after.
     *
     * @param o1  The object being compared to.
     *
     * @return An integer indicating the order of the data item object 
     *         relative to another object.
     */
    public int compareTo(Object o1) {

        int result;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[18]++;
int CodeCoverConditionCoverageHelper_C10;

        // CASE 1 : Comparing to another TimeSeriesDataItem object
        // -------------------------------------------------------
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((o1 instanceof TimeSeriesDataItem) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[19]++;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[19]++;
            TimeSeriesDataItem datapair = (TimeSeriesDataItem) o1;
            result = getPeriod().compareTo(datapair.getPeriod());
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[20]++;

        }

        // CASE 2 : Comparing to a general object
        // ---------------------------------------------
        else {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[20]++;
            // consider time periods to be ordered after general objects
            result = 1;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[21]++;
        }

        return result;

    }

    /**
     * Clones the data item.  Note: there is no need to clone the period or 
     * value since they are immutable classes.
     *
     * @return A clone of the data item.
     */
    public Object clone() {
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[22]++;
        Object clone = null;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[23]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            clone = super.clone();
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[24]++;
        }
        catch (CloneNotSupportedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[22]++; // won't get here...
            e.printStackTrace();
CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.statements[25]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9.branches[21]++;
}
  }
        return clone;
    }

}

class CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9 ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimeSeriesDataItem.java";
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

  public CodeCoverCoverageCounter$2qhom7pni5xxfgmql3rhyjqe7h2pumg838m9 () {
    super("org.jfree.data.time.TimeSeriesDataItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
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
    log.startNamedSection("org.jfree.data.time.TimeSeriesDataItem.java");
      for (int i = 1; i <= 25; i++) {
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

