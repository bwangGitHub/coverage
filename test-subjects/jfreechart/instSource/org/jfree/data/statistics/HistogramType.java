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
 * ------------------
 * HistogramType.java
 * ------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Mar-2004 : Version 1 (DG);
 * 
 */

package org.jfree.data.statistics;

import java.io.ObjectStreamException;
import java.io.Serializable;
    
/**
 * A class for creating constants to represent the histogram type.  See Bloch's
 * enum tip in 'Effective Java'.
 */
public class HistogramType implements Serializable {
  static {
    CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.ping();
  }
 
        
    /** For serialization. */
    private static final long serialVersionUID = 2618927186251997727L;
  static {
    CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[1]++;
  }
    
    /** Frequency histogram. */
    public static final HistogramType FREQUENCY 
        = new HistogramType("FREQUENCY");
  static {
    CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[2]++;
  }
    
    /** Relative frequency. */
    public static final HistogramType RELATIVE_FREQUENCY 
        = new HistogramType("RELATIVE_FREQUENCY");
  static {
    CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[3]++;
  }
    
    /** Scale area to one. */
    public static final HistogramType SCALE_AREA_TO_1 
        = new HistogramType("SCALE_AREA_TO_1");
  static {
    CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[4]++;
  }

    /** The type name. */
    private String name;
        
    /** 
     * Creates a new type.
     * 
     * @param name  the name.
     */
    private HistogramType(String name) {
        this.name = name;
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[5]++;
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
     * Tests this type for equality with an arbitrary object.
     *
     * @param obj  the object to test against.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[1]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[2]++;}
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[4]++;}
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof HistogramType) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[6]++;}
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[9]++;
        
        HistogramType t = (HistogramType) obj;
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.name.equals(t.name)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[8]++;}
        
        return true;
        
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return The hashcode
     */
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return The object.
     * 
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(HistogramType.FREQUENCY)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[9]++;
            return HistogramType.FREQUENCY;

        }
        else {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[10]++;
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[12]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.equals(HistogramType.RELATIVE_FREQUENCY)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[11]++;
            return HistogramType.RELATIVE_FREQUENCY;

        }
        else {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[12]++;
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.statements[13]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.equals(HistogramType.SCALE_AREA_TO_1)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[13]++;
            return HistogramType.SCALE_AREA_TO_1;

        } else {
  CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl.branches[14]++;}
}
}
        return null;
    }

}

class CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.HistogramType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$60s69gh6a4rvy208l5giah7dzgbl () {
    super("org.jfree.data.statistics.HistogramType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.HistogramType.java");
      for (int i = 1; i <= 13; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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


