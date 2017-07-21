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
 * ---------------------------
 * CategoryLabelWidthType.java
 * ---------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 24-Mar-2004 : Version 1 (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Represents the width types for a category label.
 */
public final class CategoryLabelWidthType implements Serializable {
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6976024792582949656L;
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[1]++;
  }
    
    /** Percentage of category. */
    public static final CategoryLabelWidthType 
        CATEGORY = new CategoryLabelWidthType(
            "CategoryLabelWidthType.CATEGORY"
        );
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[2]++;
  }

    /** Percentage of range. */
    public static final CategoryLabelWidthType 
        RANGE = new CategoryLabelWidthType("CategoryLabelWidthType.RANGE");
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[3]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name (<code>null</code> not permitted).
     */
    private CategoryLabelWidthType(String name) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[1]++;
            throw new IllegalArgumentException("Null 'name' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[2]++;}
        this.name = name;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[5]++;
    }

    /**
     * Returns a string representing the object.
     *
     * @return The string (never </code>null</code>).
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified 
     * object, and <code>false</code> otherwise.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[4]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryLabelWidthType) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[6]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[8]++;
        CategoryLabelWidthType t = (CategoryLabelWidthType) obj;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.name.equals(t.toString())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[8]++;}
        return true;

    }
    
    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(CategoryLabelWidthType.CATEGORY)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[9]++;
            return CategoryLabelWidthType.CATEGORY;

        }
        else {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[10]++;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.statements[11]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(CategoryLabelWidthType.RANGE)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[11]++;
            return CategoryLabelWidthType.RANGE;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01.branches[12]++;}
}    
        return null;
    }

}

class CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01 ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CategoryLabelWidthType.java";
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

  public CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbfdsu93p5srfl9s8r12fa01 () {
    super("org.jfree.chart.axis.CategoryLabelWidthType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.CategoryLabelWidthType.java");
      for (int i = 1; i <= 11; i++) {
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

