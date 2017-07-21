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
 * -------------------
 * NumberTickUnit.java
 * -------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Dec-2001 : Added standard header (DG);
 * 01-May-2002 : Updated for changed to TickUnit class (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 09-Jan-2002 : Added a new constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 05-Jul-2005 : Added equals() implementation (DG);
 * 05-Sep-2005 : Implemented hashCode(), thanks to Thomas Morgner (DG);
 * 02-Aug-2007 : Added new constructor with minorTickCount (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * A numerical tick unit.
 */
public class NumberTickUnit extends TickUnit implements Serializable {
  static {
    CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3849459506627654442L;
  static {
    CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[1]++;
  }
    
    /** A formatter for the tick unit. */
    private NumberFormat formatter;

    /**
     * Creates a new number tick unit.
     *
     * @param size  the size of the tick unit.
     */
    public NumberTickUnit(double size) {
        this(size, NumberFormat.getNumberInstance());
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[2]++;
    }

    /**
     * Creates a new number tick unit.
     *
     * @param size  the size of the tick unit.
     * @param formatter  a number formatter for the tick unit (<code>null</code>
     *                   not permitted).
     */
    public NumberTickUnit(double size, NumberFormat formatter) {
        super(size);
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[3]++;
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[1]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[2]++;}
        this.formatter = formatter;
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[5]++;
    }

    /**
     * Creates a new number tick unit.
     *
     * @param size  the size of the tick unit.
     * @param formatter  a number formatter for the tick unit (<code>null</code>
     *                   not permitted).
     * @param minorTickCount  the number of minor ticks.
     *
     * @since 1.0.7
     */
    public NumberTickUnit(double size, NumberFormat formatter,
            int minorTickCount) {
        super(size, minorTickCount);
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[6]++;
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[3]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[4]++;}
        this.formatter = formatter;
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[8]++;
    }

    /**
     * Converts a value to a string.
     *
     * @param value  the value.
     *
     * @return The formatted string.
     */
    public String valueToString(double value) {
        return this.formatter.format(value);
    }
    
    /**
     * Tests this formatter for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[6]++;}
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof NumberTickUnit) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[8]++;}
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[10]++;}
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[12]++;
        NumberTickUnit that = (NumberTickUnit) obj;
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.formatter.equals(that.formatter)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.branches[12]++;}
        return true;
    }
    
    /**
     * Returns a string representing this unit.
     * 
     * @return A string.
     */
    public String toString() {
        return "[size=" + this.valueToString(this.getSize()) + "]";
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[14]++;
        int result = super.hashCode();
        result = 29 * result + (this.formatter != null 
                ? this.formatter.hashCode() : 0);
CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h.statements[15]++;
        return result;
    }

}

class CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.NumberTickUnit.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$1aeal8oi2eu3z51dkogxs5absn184h () {
    super("org.jfree.chart.axis.NumberTickUnit.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.NumberTickUnit.java");
      for (int i = 1; i <= 15; i++) {
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
    for (int i = 1; i <= 6; i++) {
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

