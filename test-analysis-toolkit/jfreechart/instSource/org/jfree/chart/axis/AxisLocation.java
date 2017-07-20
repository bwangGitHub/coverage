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
 * -----------------
 * AxisLocation.java
 * -----------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nick Guenther;
 *
 * Changes:
 * --------
 * 02-May-2003 : Version 1 (DG);
 * 03-Jul-2003 : Added isTopOrBottom() and isLeftOrRight() methods (DG);
 * 13-Aug-2003 : Fixed readResolve() bug (id=788202) (DG);
 * 24-Mar-2004 : Added static getOpposite() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 22-Mar-2007 : Added getOpposite() method, suggested by Nick Guenther (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Used to indicate the location of an axis on a 2D plot, prior to knowing the 
 * orientation of the plot.
 */
public final class AxisLocation implements Serializable {
  static {
    CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3276922179323563410L;
  static {
    CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[1]++;
  }
    
    /** Axis at the top or left. */
    public static final AxisLocation TOP_OR_LEFT = new AxisLocation(
            "AxisLocation.TOP_OR_LEFT");
  static {
    CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[2]++;
  }

    /** Axis at the top or right. */
    public static final AxisLocation TOP_OR_RIGHT = new AxisLocation(
            "AxisLocation.TOP_OR_RIGHT");
  static {
    CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[3]++;
  }
    
    /** Axis at the bottom or left. */
    public static final AxisLocation BOTTOM_OR_LEFT = new AxisLocation(
            "AxisLocation.BOTTOM_OR_LEFT");
  static {
    CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[4]++;
  }
        
    /** Axis at the bottom or right. */
    public static final AxisLocation BOTTOM_OR_RIGHT = new AxisLocation(
            "AxisLocation.BOTTOM_OR_RIGHT");
  static {
    CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[5]++;
  }
    
    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private AxisLocation(String name) {
        this.name = name;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[6]++;
    }

    /**
     * Returns the location that is opposite to this location.
     *
     * @return The opposite location.
     * 
     * @since 1.0.5
     */
    public AxisLocation getOpposite() {
        return getOpposite(this);
    }
    
    /**
     * Returns a string representing the object.
     *
     * @return The string.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified 
     * object, and <code>false</code> otherwise.
     *
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[2]++;}
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof AxisLocation) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[4]++;}
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[9]++;
        AxisLocation location = (AxisLocation) obj;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(location.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[6]++;}
        return true;

    }
    
    /**
     * Returns the location that is opposite to the supplied location.
     * 
     * @param location  the location (<code>null</code> not permitted).
     * 
     * @return The opposite location.
     */
    public static AxisLocation getOpposite(AxisLocation location) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[7]++;
            throw new IllegalArgumentException("Null 'location' argument.");
   
        } else {
  CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[8]++;}
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[12]++;
        AxisLocation result = null;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((location == AxisLocation.TOP_OR_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[9]++;
            result = AxisLocation.BOTTOM_OR_RIGHT;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[14]++;
   
        }
        else {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[10]++;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[15]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((location == AxisLocation.TOP_OR_RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[11]++;
            result = AxisLocation.BOTTOM_OR_LEFT;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[16]++;
   
        }
        else {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[12]++;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[17]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((location == AxisLocation.BOTTOM_OR_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[13]++;
            result = AxisLocation.TOP_OR_RIGHT;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[18]++;
   
        }
        else {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[14]++;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[19]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((location == AxisLocation.BOTTOM_OR_RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[15]++;
            result = AxisLocation.TOP_OR_LEFT;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[20]++;
   
        }
        else {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[16]++;
            throw new IllegalStateException("AxisLocation not recognised.");   
        }
}
}
}
        return result;
    }
        
    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.equals(AxisLocation.TOP_OR_RIGHT)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[17]++;
            return AxisLocation.TOP_OR_RIGHT;

        }
        else {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[18]++;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[22]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.equals(AxisLocation.BOTTOM_OR_RIGHT)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[19]++;
            return AxisLocation.BOTTOM_OR_RIGHT;

        }    
        else {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[20]++;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[23]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.equals(AxisLocation.TOP_OR_LEFT)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[21]++;
            return AxisLocation.TOP_OR_LEFT;

        }    
        else {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[22]++;
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.statements[24]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.equals(AxisLocation.BOTTOM_OR_LEFT)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[23]++;
            return AxisLocation.BOTTOM_OR_LEFT;

        } else {
  CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t.branches[24]++;}
}
}
}
        return null;
    }
    
}

class CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.AxisLocation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$rk99089j5jpki7v1ahq4ohvt5t () {
    super("org.jfree.chart.axis.AxisLocation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.AxisLocation.java");
      for (int i = 1; i <= 24; i++) {
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
    for (int i = 1; i <= 12; i++) {
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

