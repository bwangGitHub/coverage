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
 * PieLabelRecord.java
 * -------------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 08-Mar-2004 : Version 1 (DG);
 * 14-Jun-2007 : Implemented Serializable, updated API docs (DG);
 * 21-Nov-2007 : Implemented equals() to shut up FindBugs (DG);
 *
 */

package org.jfree.chart.plot;

import java.io.Serializable;

import org.jfree.text.TextBox;

/**
 * A structure that retains information about the label for a section in a pie 
 * chart.
 */
public class PieLabelRecord implements Comparable, Serializable {
  static {
    CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.ping();
  }

    
    /** The section key. */
    private Comparable key;
    
    /** The angle of the centre of the section (in radians). */
    private double angle;
    
    /** The base y-coordinate. */
    private double baseY;
    
    /** The allocated y-coordinate. */
    private double allocatedY;

    /** The label. */
    private TextBox label;
    
    /** The label height. */
    private double labelHeight;
    
    /** The gap. */
    private double gap;
    
    /** The link percent. */
    private double linkPercent;
    
    /**
     * Creates a new record.
     * 
     * @param key  the section key.
     * @param angle  the angle to the middle of the section (in radians).
     * @param baseY  the base y-coordinate.
     * @param label  the section label.
     * @param labelHeight  the label height (in Java2D units).
     * @param gap  the offset to the left.
     * @param linkPercent  the link percent.
     */
    public PieLabelRecord(Comparable key, double angle, double baseY, 
                          TextBox label, double labelHeight, double gap, 
                          double linkPercent) {
        this.key = key;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[1]++;
        this.angle = angle;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[2]++;
        this.baseY = baseY;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[3]++;
        this.allocatedY = baseY;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[4]++;
        this.label = label;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[5]++;
        this.labelHeight = labelHeight;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[6]++;
        this.gap = gap;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[7]++;
        this.linkPercent = linkPercent;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[8]++;
    }
    
    /**
     * Returns the base y-coordinate.  This is where the label will appear if 
     * there is no overlapping of labels.
     * 
     * @return The base y-coordinate.
     */
    public double getBaseY() {
        return this.baseY;   
    }
    
    /**
     * Sets the base y-coordinate.
     * 
     * @param base  the base y-coordinate.
     */
    public void setBaseY(double base) {
        this.baseY = base;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[9]++;   
    }
    
    /**
     * Returns the lower bound of the label.
     * 
     * @return The lower bound.
     */
    public double getLowerY() {
        return this.allocatedY - this.labelHeight / 2.0;   
    }
    
    /**
     * Returns the upper bound of the label.
     * 
     * @return The upper bound.
     */
    public double getUpperY() {
        return this.allocatedY + this.labelHeight / 2.0;   
    }
    
    /**
     * Returns the angle of the middle of the section, in radians.
     * 
     * @return The angle, in radians.
     */
    public double getAngle() {
        return this.angle;   
    }
    
    /**
     * Returns the key for the section that the label applies to.
     * 
     * @return The key.
     */
    public Comparable getKey() {
        return this.key;   
    }
    
    /**
     * Returns the label.
     * 
     * @return The label.
     */
    public TextBox getLabel() {
        return this.label;   
    }
    
    /**
     * Returns the label height (you could derive this from the label itself,
     * but we cache the value so it can be retrieved quickly).
     * 
     * @return The label height (in Java2D units).
     */
    public double getLabelHeight() {
        return this.labelHeight;   
    }
    
    /**
     * Returns the allocated y-coordinate.
     * 
     * @return The allocated y-coordinate.
     */
    public double getAllocatedY() {
        return this.allocatedY;   
    }
    
    /**
     * Sets the allocated y-coordinate.
     * 
     * @param y  the y-coordinate.
     */
    public void setAllocatedY(double y) {
        this.allocatedY = y;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[10]++;   
    }
    
    /**
     * Returns the gap.
     * 
     * @return The gap.
     */
    public double getGap() {
        return this.gap;   
    }
    
    /**
     * Returns the link percent.
     * 
     * @return The link percent.
     */
    public double getLinkPercent() {
        return this.linkPercent;   
    }
    
    /**
     * Compares this object to an arbitrary object.
     * 
     * @param obj  the object to compare against.
     * 
     * @return An integer that specifies the relative order of the two objects.
     */
    public int compareTo(Object obj) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[11]++;
        int result = 0;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj instanceof PieLabelRecord) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[1]++;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[13]++;
            PieLabelRecord plr = (PieLabelRecord) obj;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.baseY < plr.baseY) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[3]++;
                result = -1;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[15]++;
   
            }
            else {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[4]++;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[16]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.baseY > plr.baseY) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[5]++;
                result = 1;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[17]++;
   
            } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[6]++;}
}

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[2]++;}
        return result;
    }
    
    /**
     * Tests this record for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[8]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof PieLabelRecord) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[10]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[20]++;
        PieLabelRecord that = (PieLabelRecord) obj;
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.key.equals(that.key)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[12]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.angle != that.angle) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[14]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.gap != that.gap) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[16]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.allocatedY != that.allocatedY) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[18]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.baseY != that.baseY) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[20]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[26]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.labelHeight != that.labelHeight) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[22]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[27]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.linkPercent != that.linkPercent) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[24]++;}
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.statements[28]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.label.equals(that.label)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d.branches[26]++;}
        return true;
    }
    
    /**
     * Returns a string describing the object.  This is used for debugging only.
     * 
     * @return A string.
     */
    public String toString() {
        return this.baseY + ", " + this.key.toString();   
    }
}

class CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.PieLabelRecord.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$1bjvd71bes01v58tvw0rfat6xyip9d () {
    super("org.jfree.chart.plot.PieLabelRecord.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.PieLabelRecord.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

