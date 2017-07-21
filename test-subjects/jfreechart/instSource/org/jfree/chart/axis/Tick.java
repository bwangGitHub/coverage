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
 * ---------
 * Tick.java
 * ---------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *
 * Changes
 * -------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 12-Sep-2003 : Implemented Cloneable (NB);
 * 07-Nov-2003 : Added subclasses for particular types of ticks (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;

import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;

/**
 * The base class used to represent labelled ticks along an axis.
 */
public abstract class Tick implements Serializable, Cloneable {
  static {
    CodeCoverCoverageCounter$94m9s9vqc5a6w1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 6668230383875149773L;
  static {
    CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[1]++;
  }
    
    /** A text version of the tick value. */
    private String text;

    /** The text anchor for the tick label. */
    private TextAnchor textAnchor;
    
    /** The rotation anchor for the tick label. */
    private TextAnchor rotationAnchor;
        
    /** The rotation angle. */
    private double angle;
    
    /**
     * Creates a new tick.
     *
     * @param text  the formatted version of the tick value.
     * @param textAnchor  the text anchor (<code>null</code> not permitted).
     * @param rotationAnchor  the rotation anchor (<code>null</code> not 
     *                        permitted).
     * @param angle  the angle. 
     */
    public Tick(String text, TextAnchor textAnchor, TextAnchor rotationAnchor, 
                double angle) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((textAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[1]++;
            throw new IllegalArgumentException("Null 'textAnchor' argument.");

        } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[2]++;}
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((rotationAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[3]++;
            throw new IllegalArgumentException(
                "Null 'rotationAnchor' argument."
            );
   
        } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[4]++;}
        this.text = text;
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[4]++;
        this.textAnchor = textAnchor;
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[5]++;
        this.rotationAnchor = rotationAnchor;
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[6]++;
        this.angle = angle;
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[7]++;
    }

    /**
     * Returns the text version of the tick value.
     *
     * @return A string (possibly <code>null</code>;
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns the text anchor.
     * 
     * @return The text anchor (never <code>null</code>).
     */
    public TextAnchor getTextAnchor() {
        return this.textAnchor;
    }

    /**
     * Returns the text anchor that defines the point around which the label is
     * rotated.
     * 
     * @return A text anchor (never <code>null</code>).
     */    
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }
    
    /**
     * Returns the angle.
     * 
     * @return The angle.
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Tests this tick for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[5]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[6]++;}
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof Tick) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[7]++;
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[10]++;
            Tick t = (Tick) obj;
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;   
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.text, t.text)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[9]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[10]++;}
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[12]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.textAnchor, t.textAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[11]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[12]++;}
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rotationAnchor, t.rotationAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[13]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[14]++;}
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[14]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.angle == t.angle) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$94m9s9vqc5a6w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[15]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[16]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$94m9s9vqc5a6w1.branches[8]++;}
        return false;
    }

    /** 
     * Returns a clone of the tick.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$94m9s9vqc5a6w1.statements[15]++;
        Tick clone = (Tick) super.clone();
        return clone;
    }

    /**
     * Returns a string representation of the tick.
     * 
     * @return A string.
     */
    public String toString() {
        return this.text;
    }
}

class CodeCoverCoverageCounter$94m9s9vqc5a6w1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$94m9s9vqc5a6w1 ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.Tick.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$94m9s9vqc5a6w1 () {
    super("org.jfree.chart.axis.Tick.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.Tick.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

