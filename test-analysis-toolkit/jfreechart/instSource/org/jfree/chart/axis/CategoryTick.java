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
 * CategoryTick.java
 * -----------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 13-May-2004 : Added equals() method (DG);
 *
 */

package org.jfree.chart.axis;

import org.jfree.text.TextBlock;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;

/**
 * A tick for a {@link CategoryAxis}.
 */
public class CategoryTick extends Tick {
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.ping();
  }


    /** The category. */
    private Comparable category;
    
    /** The label. */
    private TextBlock label;
    
    /** The label anchor. */
    private TextBlockAnchor labelAnchor;
    
    /**
     * Creates a new tick.
     * 
     * @param category  the category.
     * @param label  the label.
     * @param labelAnchor  the label anchor.
     * @param rotationAnchor  the rotation anchor.
     * @param angle  the rotation angle (in radians).
     */
    public CategoryTick(Comparable category,
                        TextBlock label,
                        TextBlockAnchor labelAnchor,
                        TextAnchor rotationAnchor,
                        double angle) {
                            
        super("", TextAnchor.CENTER, rotationAnchor, angle);
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[1]++;
        this.category = category;
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[2]++;
        this.label = label;
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[3]++;
        this.labelAnchor = labelAnchor;
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[4]++;
        
    }
    
    /**
     * Returns the category.
     * 
     * @return The category.
     */
    public Comparable getCategory() {
        return this.category;
    }
    
    /**
     * Returns the label.
     * 
     * @return The label.
     */
    public TextBlock getLabel() {
        return this.label;
    }
    
    /**
     * Returns the label anchor.
     * 
     * @return The label anchor.
     */
    public TextBlockAnchor getLabelAnchor() {
        return this.labelAnchor;
    }
    
    /**
     * Tests this category tick for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[1]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[2]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((obj instanceof CategoryTick) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[3]++;
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[7]++;
            CategoryTick that = (CategoryTick) obj;
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;   
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.category, that.category)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[5]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[6]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.label, that.label)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[7]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[8]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelAnchor, that.labelAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[9]++;
                return false;
   
           } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[10]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.branches[4]++;}
        return false;
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[11]++;
        int result = 41;
        result = 37 * result + this.category.hashCode();
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[12]++;
        result = 37 * result + this.label.hashCode();
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[13]++;
        result = 37 * result + this.labelAnchor.hashCode();
CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x.statements[14]++;
        return result;
    }
}

class CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CategoryTick.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1};
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

  public CodeCoverCoverageCounter$sd7jgrcliqd2g5isyejrt6as0x () {
    super("org.jfree.chart.axis.CategoryTick.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.CategoryTick.java");
      for (int i = 1; i <= 14; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
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

