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
 * AxisCollection.java
 * -------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2003 : Added standard header (DG);
 *
 */

package org.jfree.chart.axis;

import java.util.List;

import org.jfree.ui.RectangleEdge;

/**
 * A collection of axes that have been assigned to the TOP, BOTTOM, LEFT or 
 * RIGHT of a chart.  This class is used internally by JFreeChart, you won't
 * normally need to use it yourself.
 */
public class AxisCollection {
  static {
    CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.ping();
  }

    
    /** The axes that need to be drawn at the top of the plot area. */
    private List axesAtTop;
    
    /** The axes that need to be drawn at the bottom of the plot area. */
    private List axesAtBottom;
    
    /** The axes that need to be drawn at the left of the plot area. */
    private List axesAtLeft;
    
    /** The axes that need to be drawn at the right of the plot area. */
    private List axesAtRight;
   
    /**
     * Creates a new empty collection.
     */ 
    public AxisCollection() {
        this.axesAtTop = new java.util.ArrayList();
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[1]++;
        this.axesAtBottom = new java.util.ArrayList();
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[2]++;
        this.axesAtLeft = new java.util.ArrayList();
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[3]++;
        this.axesAtRight = new java.util.ArrayList();
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[4]++;
    }
    
    /**
     * Returns a list of the axes (if any) that need to be drawn at the top of 
     * the plot area.
     * 
     * @return A list of axes.
     */
    public List getAxesAtTop() {
        return this.axesAtTop;
    }
    
   /**
    * Returns a list of the axes (if any) that need to be drawn at the bottom 
    * of the plot area.
    * 
    * @return A list of axes.
    */
   public List getAxesAtBottom() {
        return this.axesAtBottom;
    }
    
    /**
     * Returns a list of the axes (if any) that need to be drawn at the left 
     * of the plot area.
     * 
     * @return A list of axes.
     */
    public List getAxesAtLeft() {
        return this.axesAtLeft;
    }
    
    /**
    * Returns a list of the axes (if any) that need to be drawn at the right 
    * of the plot area.
    * 
    * @return A list of axes.
    */
    public List getAxesAtRight() {
        return this.axesAtRight;    
    }
    
    /**
     * Adds an axis to the collection.
     * 
     * @param axis  the axis (<code>null</code> not permitted).
     * @param edge  the edge of the plot that the axis should be drawn on 
     *              (<code>null</code> not permitted).
     */
    public void add(Axis axis, RectangleEdge edge) {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[1]++;
            throw new IllegalArgumentException("Null 'axis' argument.");
   
        } else {
  CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[2]++;}
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((edge == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[3]++;
            throw new IllegalArgumentException("Null 'edge' argument.");
   
        } else {
  CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[4]++;}
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[5]++;
            this.axesAtTop.add(axis);
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[8]++;

        }
        else {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[6]++;
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[9]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[7]++;
            this.axesAtBottom.add(axis);
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[10]++;

        }
        else {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[8]++;
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[11]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[9]++;
            this.axesAtLeft.add(axis);
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[12]++;

        }
        else {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[10]++;
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[13]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[11]++;
            this.axesAtRight.add(axis);
CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.statements[14]++;

        } else {
  CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29.branches[12]++;}
}
}
}
    }

}

class CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29 ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.AxisCollection.java";
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

  public CodeCoverCoverageCounter$12pscnfj8gsixs99cluzv38oshzl29 () {
    super("org.jfree.chart.axis.AxisCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.AxisCollection.java");
      for (int i = 1; i <= 14; i++) {
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

