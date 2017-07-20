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
 * --------------------
 * TimePeriodValue.java
 * --------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Apr-2003 : Version 1 (DG);
 * 03-Oct-2006 : Added null argument check to constructor (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;

/**
 * Represents a time period and an associated value.
 */
public class TimePeriodValue implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3390443360845711275L;
  static {
    CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[1]++;
  }
    
    /** The time period. */
    private TimePeriod period;

    /** The value associated with the time period. */
    private Number value;

    /**
     * Constructs a new data item.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value associated with the time period.
     * 
     * @throws IllegalArgumentException if <code>period</code> is 
     *     <code>null</code>.
     */
    public TimePeriodValue(TimePeriod period, Number value) {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[1]++;
            throw new IllegalArgumentException("Null 'period' argument.");

        } else {
  CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[2]++;}
        this.period = period;
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[3]++;
        this.value = value;
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[4]++;
    }

    /**
     * Constructs a new data item.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value associated with the time period.
     * 
     * @throws IllegalArgumentException if <code>period</code> is 
     *     <code>null</code>.
     */
    public TimePeriodValue(TimePeriod period, double value) {
        this(period, new Double(value));
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[5]++;
    }

    /**
     * Returns the time period.
     *
     * @return The time period (never <code>null</code>).
     */
    public TimePeriod getPeriod() {
        return this.period;
    }

    /**
     * Returns the value.
     *
     * @return The value (possibly <code>null</code>).
     * 
     * @see #setValue(Number)
     */
    public Number getValue() {
        return this.value;
    }

    /**
     * Sets the value for this data item.
     *
     * @param value  the new value (<code>null</code> permitted).
     * 
     * @see #getValue()
     */
    public void setValue(Number value) {
        this.value = value;
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[6]++;
    }

    /**
     * Tests this object for equality with the target object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[4]++;}
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof TimePeriodValue) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[6]++;}
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[9]++;

        TimePeriodValue timePeriodValue = (TimePeriodValue) obj;
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((this.period != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 ? !
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((this.period.equals(timePeriodValue.period)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 : 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((timePeriodValue.period != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[8]++;}
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((this.value != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 ? !
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((this.value.equals(timePeriodValue.value)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 : 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((timePeriodValue.value != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[10]++;}

        return true;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return The hashcode
     */
    public int hashCode() {
        int result;
        result = (this.period != null ? this.period.hashCode() : 0);
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[12]++;
        result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[13]++;
        return result;
    }

    /**
     * Clones the object.
     * <P>
     * Note: no need to clone the period or value since they are immutable 
     * classes.
     *
     * @return A clone.
     */
    public Object clone() {
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[14]++;
        Object clone = null;
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[15]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            clone = super.clone();
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[16]++;
        }
        catch (CloneNotSupportedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[12]++; // won't get here...
            e.printStackTrace();
CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.statements[17]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx.branches[11]++;
}
  }
        return clone;
    }

}

class CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimePeriodValue.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,3};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$9uyvhm57ks2xp0d76uc1vlbt41gsggx () {
    super("org.jfree.data.time.TimePeriodValue.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.TimePeriodValue.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

