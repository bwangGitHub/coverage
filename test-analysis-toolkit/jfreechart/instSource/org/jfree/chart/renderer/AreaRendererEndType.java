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
 * ------------------------
 * AreaRendererEndType.java
 * ------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 29-April-2004 : Version 1 (DG);
 *
 */

package org.jfree.chart.renderer;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * An enumeration of the 'end types' for an area renderer.
 */
public final class AreaRendererEndType implements Serializable {
  static {
    CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1774146392916359839L;
  static {
    CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[1]++;
  }
    
    /** 
     * The area tapers from the first or last value down to zero. 
     */
    public static final AreaRendererEndType TAPER = new AreaRendererEndType(
        "AreaRendererEndType.TAPER"
    );
  static {
    CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[2]++;
  }

    /** 
     * The area is truncated at the first or last value. 
     */
    public static final AreaRendererEndType TRUNCATE = new AreaRendererEndType(
        "AreaRendererEndType.TRUNCATE"
    );
  static {
    CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[3]++;
  }
    
    /** 
     * The area is levelled at the first or last value. 
     */
    public static final AreaRendererEndType LEVEL = new AreaRendererEndType(
        "AreaRendererEndType.LEVEL"
    );
  static {
    CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[4]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private AreaRendererEndType(String name) {
        this.name = name;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[5]++;
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
     * @param o  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object o) {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == o) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[2]++;}
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((o instanceof AreaRendererEndType) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[4]++;}
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[8]++;

        AreaRendererEndType t = (AreaRendererEndType) o;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(t.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[6]++;}

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
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[10]++;
        Object result = null;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(AreaRendererEndType.LEVEL)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[7]++;
            result = AreaRendererEndType.LEVEL;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[12]++;

        }
        else {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[8]++;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[13]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(AreaRendererEndType.TAPER)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[9]++;
            result = AreaRendererEndType.TAPER;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[14]++;

        }
        else {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[10]++;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[15]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(AreaRendererEndType.TRUNCATE)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[11]++;
            result = AreaRendererEndType.TRUNCATE;
CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.statements[16]++;

        } else {
  CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx.branches[12]++;}
}
}
        return result;
    }

}

class CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.AreaRendererEndType.java";
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

  public CodeCoverCoverageCounter$f30xh130ie1nqj5qg25ykelwubjlfn9gxa1cx () {
    super("org.jfree.chart.renderer.AreaRendererEndType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
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
    log.startNamedSection("org.jfree.chart.renderer.AreaRendererEndType.java");
      for (int i = 1; i <= 16; i++) {
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

