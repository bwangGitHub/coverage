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
 * DatasetGroup.java
 * -----------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Oct-2002 : Version 1 (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 20-Aug-2003 : Implemented Cloneable (DG);
 *
 */

package org.jfree.data.general;

import java.io.Serializable;

/**
 * A class that is used to group datasets (currently not used for any specific
 * purpose).
 */
public class DatasetGroup implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -3640642179674185688L;
  static {
    CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[1]++;
  }
    
    /** The group id. */
    private String id;
    
    /**
     * Constructs a new group.
     */
    public DatasetGroup() {
        super();
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[2]++;
        this.id = "NOID";
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[3]++;
    }

    /**
     * Creates a new group with the specified id.
     * 
     * @param id  the identification for the group.
     */
    public DatasetGroup(String id) {
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((id == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[1]++;
            throw new IllegalArgumentException("Null 'id' argument.");
   
        } else {
  CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[2]++;}
        this.id = id;
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[5]++;   
    }
    
    /**
     * Returns the identification string for this group.
     * 
     * @return The identification string.
     */
    public String getID() {
        return this.id;   
    }
    
    /**
     * Clones the group.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not by this class.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[3]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[4]++;}
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof DatasetGroup) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[5]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[6]++;}
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[8]++;
        DatasetGroup that = (DatasetGroup) obj;
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.id.equals(that.id)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1.branches[8]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1 ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.general.DatasetGroup.java";
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

  public CodeCoverCoverageCounter$ssd5eienq2r69r94uo9m2v8ig1 () {
    super("org.jfree.data.general.DatasetGroup.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("org.jfree.data.general.DatasetGroup.java");
      for (int i = 1; i <= 9; i++) {
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

