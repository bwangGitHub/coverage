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
 * --------------
 * AxisState.java
 * --------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2003 : Added standard header (DG);
 * 07-Nov-2003 : Added 'max' attribute (DG);
 *
 */

package org.jfree.chart.axis;

import java.util.List;

import org.jfree.ui.RectangleEdge;

/**
 * Instances of this class are used to carry state information for an axis 
 * during the drawing process.  By retaining this information in a separate 
 * object, it is possible for multiple threads to draw the same axis to 
 * different output targets (each drawing will maintain separate state 
 * information).
 */
public class AxisState {
  static {
    CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.ping();
  }


    /** The cursor position. */
    private double cursor;
    
    /** The axis ticks. */
    private List ticks;
    
    /** The maximum width/height. */
    private double max;
    
    /**
     * Creates a new axis state.
     */
    public AxisState() {
        this(0.0);
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[1]++;
    }
    
    /**
     * Creates a new axis state.
     * 
     * @param cursor  the cursor.
     */
    public AxisState(double cursor) {
        this.cursor = cursor;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[2]++;
        this.ticks = new java.util.ArrayList();
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[3]++;
    }
    
    /**
     * Returns the cursor position.
     * 
     * @return The cursor position.
     */
    public double getCursor() {
        return this.cursor;
    }

    /**
     * Sets the cursor position.
     * 
     * @param cursor  the cursor position.
     */
    public void setCursor(double cursor) {
        this.cursor = cursor;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[4]++;
    }
    
    /**
     * Moves the cursor outwards by the specified number of units.
     * 
     * @param units  the units.
     * @param edge  the edge.
     */
    public void moveCursor(double units, RectangleEdge edge) {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[1]++;
            cursorUp(units);
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[6]++;
   
        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[2]++;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[7]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[3]++;
            cursorDown(units);
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[8]++;
   
        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[4]++;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[9]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[5]++;
            cursorLeft(units);
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[10]++;
   
        }
        else {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[6]++;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[7]++;
            cursorRight(units);
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[12]++;
   
        } else {
  CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.branches[8]++;}
}
}
}
    }
    
    /**
     * Moves the cursor up by the specified number of Java 2D units.
     * 
     * @param units  the units.
     */
    public void cursorUp(double units) {
        this.cursor = this.cursor - units;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[13]++;
    }
    
    /**
     * Moves the cursor down by the specified number of Java 2D units.
     * 
     * @param units  the units.
     */
    public void cursorDown(double units) {
        this.cursor = this.cursor + units;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[14]++;
    }
    
    /**
     * Moves the cursor left by the specified number of Java 2D units.
     * 
     * @param units  the units.
     */
    public void cursorLeft(double units) {
        this.cursor = this.cursor - units;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[15]++;
    }
    
    /**
     * Moves the cursor right by the specified number of Java 2D units.
     * 
     * @param units  the units.
     */
    public void cursorRight(double units) {
        this.cursor = this.cursor + units;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[16]++;
    }
    
    /**
     * Returns the list of ticks.
     * 
     * @return The list of ticks.
     */
    public List getTicks() {
        return this.ticks;
    }
    
    /**
     * Sets the list of ticks.
     * 
     * @param ticks  the ticks.
     */
    public void setTicks(List ticks) {
        this.ticks = ticks;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[17]++;
    }
    
    /**
     * Returns the maximum width/height.
     * 
     * @return The maximum width/height.
     */
    public double getMax() {
        return this.max;
    }
    
    /**
     * Sets the maximum width/height.
     * 
     * @param max the maximum width/height.
     */
    public void setMax(double max) {
        this.max = max;
CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach.statements[18]++;
    }
}

class CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.AxisState.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$2rc5pe8j9ulwkfe3xvsach () {
    super("org.jfree.chart.axis.AxisState.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.AxisState.java");
      for (int i = 1; i <= 18; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

