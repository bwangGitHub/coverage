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
 * ValueTick.java
 * --------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 02-Aug-2007 : Added tick type attribute (DG);
 * 
 */

package org.jfree.chart.axis;

import org.jfree.ui.TextAnchor;

/**
 * A value tick.
 */
public abstract class ValueTick extends Tick {
  static {
    CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.ping();
  }


    /** The value. */
    private double value;
    
    /** 
     * The tick type (major or minor). 
     *
     * @since 1.0.7
     */
    private TickType tickType;
    
    /**
     * Creates a new value tick.
     * 
     * @param value  the value.
     * @param label  the label.
     * @param textAnchor  the part of the label that is aligned to the anchor 
     *                    point.
     * @param rotationAnchor  defines the rotation point relative to the label.
     * @param angle  the rotation angle (in radians).
     */
    public ValueTick(double value, String label, 
                     TextAnchor textAnchor, TextAnchor rotationAnchor, 
                     double angle) {
                          
        this(TickType.MAJOR, value, label, textAnchor, rotationAnchor, angle);
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[1]++;
        this.value = value;
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[2]++;
        
    }

    /**
     * Creates a new value tick.
     * 
     * @param tickType  the tick type (major or minor).
     * @param value  the value.
     * @param label  the label.
     * @param textAnchor  the part of the label that is aligned to the anchor 
     *                    point.
     * @param rotationAnchor  defines the rotation point relative to the label.
     * @param angle  the rotation angle (in radians).
     * 
     * @since 1.0.7
     */
    public ValueTick(TickType tickType, double value, String label, 
                     TextAnchor textAnchor, TextAnchor rotationAnchor, 
                     double angle) {
                          
        super(label, textAnchor, rotationAnchor, angle);
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[3]++;
        this.value = value;
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[4]++;
        this.tickType = tickType;
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[5]++;   
    }    
    
    /**
     * Returns the value.
     * 
     * @return The value.
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * Returns the tick type (major or minor).
     * 
     * @return The tick type.
     *
     * @since 1.0.7
     */
    public TickType getTickType() {
        return this.tickType;
    }
    
    /**
     * Tests this tick for equality with an arbitrary object.
     * 
     * @param obj  the object to test (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[1]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[2]++;}
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof ValueTick) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[4]++;}
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[8]++;
        ValueTick that = (ValueTick) obj;
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.value != that.value) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[5]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[6]++;}
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.tickType.equals(that.tickType)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl.branches[8]++;}
        return super.equals(obj);
    }
    
}

class CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.ValueTick.java";
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

  public CodeCoverCoverageCounter$3n2bs2hl0hntiwe69uw8rl () {
    super("org.jfree.chart.axis.ValueTick.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.ValueTick.java");
      for (int i = 1; i <= 10; i++) {
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

