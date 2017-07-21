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
 * CategoryAnchor.java
 * -------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 04-Jul-2003 : Version 1 (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Used to indicate one of three positions within a category: 
 * <code>START</code>, <code>MIDDLE</code> and <code>END</code>.
 */
public final class CategoryAnchor implements Serializable {
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2604142742210173810L;
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[1]++;
  }
    
    /** Start of period. */
    public static final CategoryAnchor START 
        = new CategoryAnchor("CategoryAnchor.START");
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[2]++;
  }

    /** Middle of period. */
    public static final CategoryAnchor MIDDLE 
        = new CategoryAnchor("CategoryAnchor.MIDDLE");
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[3]++;
  }

    /** End of period. */
    public static final CategoryAnchor END 
        = new CategoryAnchor("CategoryAnchor.END");
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[4]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private CategoryAnchor(String name) {
        this.name = name;
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[5]++;
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
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[2]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryAnchor) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[4]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[8]++;
        CategoryAnchor position = (CategoryAnchor) obj;
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(position.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[6]++;}
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
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(CategoryAnchor.START)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[7]++;
            return CategoryAnchor.START;

        }
        else {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[8]++;
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[11]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(CategoryAnchor.MIDDLE)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[9]++;
            return CategoryAnchor.MIDDLE;

        }    
        else {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[10]++;
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.statements[12]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(CategoryAnchor.END)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[11]++;
            return CategoryAnchor.END;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201.branches[12]++;}
}
}    
        return null;
    }

}

class CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201 ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CategoryAnchor.java";
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

  public CodeCoverCoverageCounter$13ugf3fy51kl4apxt2pk78a37h5201 () {
    super("org.jfree.chart.axis.CategoryAnchor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.CategoryAnchor.java");
      for (int i = 1; i <= 12; i++) {
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

