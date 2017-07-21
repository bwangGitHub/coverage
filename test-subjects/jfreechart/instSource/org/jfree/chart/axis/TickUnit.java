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
 * -------------
 * TickUnit.java
 * -------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Dec-2001 : Added standard header (DG);
 * 01-May-2002 : Changed the unit size from Number to double (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 05-Sep-2005 : Implemented hashCode(), thanks to Thomas Morgner (DG);
 * 02-Aug-2007 : Added minorTickCount attribute (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;

/**
 * Base class representing a tick unit.  This determines the spacing of the
 * tick marks on an axis.
 * <P>
 * This class (and any subclasses) should be immutable, the reason being that
 * ORDERED collections of tick units are maintained and if one instance can be
 * changed, it may destroy the order of the collection that it belongs to.
 * In addition, if the implementations are immutable, they can belong to
 * multiple collections.
 *
 * @see ValueAxis
 */
public abstract class TickUnit implements Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 510179855057013974L;
  static {
    CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[1]++;
  }
    
    /** The size of the tick unit. */
    private double size;

    /**
     * The number of minor ticks.
     * 
     * @since 1.0.7
     */
    private int minorTickCount;
    
    /**
     * Constructs a new tick unit.
     *
     * @param size  the tick unit size.
     */
    public TickUnit(double size) {
        this.size = size;
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[2]++;
    }
    
    /**
     * Constructs a new tick unit.
     *
     * @param size  the tick unit size.
     * @param minorTickCount  the minor tick count.
     * 
     * @since 1.0.7
     */
    public TickUnit(double size, int minorTickCount) {
        this.size = size;
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[3]++;
        this.minorTickCount = minorTickCount;
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[4]++;
    }    

    /**
     * Returns the size of the tick unit.
     *
     * @return The size of the tick unit.
     */
    public double getSize() {
        return this.size;
    }
    
    /**
     * Returns the minor tick count.
     * 
     * @return The minor tick count.
     * 
     * @since 1.0.7
     */
    public int getMinorTickCount() {
        return this.minorTickCount;
    }

    /**
     * Converts the supplied value to a string.
     * <P>
     * Subclasses may implement special formatting by overriding this method.
     *
     * @param value  the data value.
     *
     * @return Value as string.
     */
    public String valueToString(double value) {
        return String.valueOf(value);
    }

    /**
     * Compares this tick unit to an arbitrary object.
     *
     * @param object  the object to compare against.
     *
     * @return <code>1</code> if the size of the other object is less than this,
     *      <code>0</code> if both have the same size and <code>-1</code> this
     *      size is less than the others.
     */
    public int compareTo(Object object) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((object instanceof TickUnit) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[1]++;
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[6]++;
            TickUnit other = (TickUnit) object;
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.size > other.getSize()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[3]++;
                return 1;

            }
            else {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[4]++;
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[8]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.size < other.getSize()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[5]++;
                return -1;

            }
            else {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[6]++;
                return 0;
            }
}

        }
        else {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[2]++;
            return -1;
        }

    }

    /**
     * Tests this unit for equality with another object.
     *
     * @param obj  the object.
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[8]++;}
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof TickUnit) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[10]++;}
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[11]++;
        TickUnit that = (TickUnit) obj;
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[12]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.size != that.size) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[12]++;}
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.minorTickCount != that.minorTickCount) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.branches[14]++;}
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx.statements[14]++;
        long temp = this.size != +0.0d ? Double.doubleToLongBits(this.size) 
                : 0L;
        return (int) (temp ^ (temp >>> 32));
    }

}

class CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.TickUnit.java";
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

  public CodeCoverCoverageCounter$i0e2xjlnl3ykk1ez4swx () {
    super("org.jfree.chart.axis.TickUnit.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.TickUnit.java");
      for (int i = 1; i <= 14; i++) {
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

