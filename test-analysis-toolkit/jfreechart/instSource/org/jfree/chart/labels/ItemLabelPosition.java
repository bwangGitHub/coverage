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
 * ----------------------
 * ItemLabelPosition.java
 * ----------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 27-Oct-2003 : Version 1 (DG);
 * 19-Feb-2004 : Moved to org.jfree.chart.labels, updated Javadocs and argument 
 *               checking (DG);
 * 26-Feb-2004 : Added new constructor (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;

import org.jfree.ui.TextAnchor;

/**
 * The attributes that control the position of the label for each data item on 
 * a chart.  Instances of this class are immutable.
 */
public class ItemLabelPosition implements Serializable {
  static {
    CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5845390630157034499L;
  static {
    CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[1]++;
  }
    
    /** The item label anchor point. */
    private ItemLabelAnchor itemLabelAnchor;
    
    /** The text anchor. */
    private TextAnchor textAnchor;
    
    /** The rotation anchor. */
    private TextAnchor rotationAnchor;

    /** The rotation angle. */    
    private double angle;
    
    /**
     * Creates a new position record with default settings.
     */
    public ItemLabelPosition() {
        this(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, 
                TextAnchor.CENTER, 0.0);
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[2]++;
    }
    
    /**
     * Creates a new position record (with zero rotation).
     * 
     * @param itemLabelAnchor  the item label anchor (<code>null</code> not 
     *                         permitted).
     * @param textAnchor  the text anchor (<code>null</code> not permitted).
     */
    public ItemLabelPosition(ItemLabelAnchor itemLabelAnchor, 
                             TextAnchor textAnchor) {
        this(itemLabelAnchor, textAnchor, TextAnchor.CENTER, 0.0);
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[3]++;    
    }
    
    /**
     * Creates a new position record.  The item label anchor is a point 
     * relative to the data item (dot, bar or other visual item) on a chart.  
     * The item label is aligned by aligning the text anchor with the 
     * item label anchor.
     * 
     * @param itemLabelAnchor  the item label anchor (<code>null</code> not 
     *                         permitted).
     * @param textAnchor  the text anchor (<code>null</code> not permitted).
     * @param rotationAnchor  the rotation anchor (<code>null</code> not 
     *                        permitted).
     * @param angle  the rotation angle (in radians).
     */
    public ItemLabelPosition(ItemLabelAnchor itemLabelAnchor, 
                             TextAnchor textAnchor,
                             TextAnchor rotationAnchor,
                             double angle) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
              
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((itemLabelAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[1]++;
            throw new IllegalArgumentException(
                    "Null 'itemLabelAnchor' argument.");

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[2]++;}
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((textAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[3]++;
            throw new IllegalArgumentException("Null 'textAnchor' argument.");

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[4]++;}
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((rotationAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'rotationAnchor' argument.");

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[6]++;}
        
        this.itemLabelAnchor = itemLabelAnchor;
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[7]++;
        this.textAnchor = textAnchor;
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[8]++;
        this.rotationAnchor = rotationAnchor;
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[9]++;
        this.angle = angle;
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[10]++;
    
    }
    
    /**
     * Returns the item label anchor.
     * 
     * @return The item label anchor (never <code>null</code>).
     */
    public ItemLabelAnchor getItemLabelAnchor() {
        return this.itemLabelAnchor;
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
     * Returns the rotation anchor point.
     * 
     * @return The rotation anchor point (never <code>null</code>).
     */
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }
    
    /**
     * Returns the angle of rotation for the label.
     * 
     * @return The angle (in radians).
     */
    public double getAngle() {
        return this.angle;
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;  
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[8]++;}
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;    
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof ItemLabelPosition) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[10]++;}
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[13]++;
        ItemLabelPosition that = (ItemLabelPosition) obj;
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.itemLabelAnchor.equals(that.itemLabelAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[12]++;}
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.textAnchor.equals(that.textAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[14]++;}
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.rotationAnchor.equals(that.rotationAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[16]++;}
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.statements[17]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.angle != that.angle) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x.branches[18]++;}     
        return true;
    }

}

class CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.ItemLabelPosition.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$c1w06ssvg8evo37zw3r7jbq3qe360xyj4x () {
    super("org.jfree.chart.labels.ItemLabelPosition.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.ItemLabelPosition.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

