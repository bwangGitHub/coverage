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
 * DefaultKeyedValue.java
 * ----------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 31-Oct-2002 : Version 1 (DG);
 * 13-Mar-2003 : Added equals() method, and implemented Serializable (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.base (DG);
 * 15-Sep-2004 : Added PublicCloneable interface (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 11-Jun-2007 : Added toString() method to help with debugging (DG);
 *
 */

package org.jfree.data;

import java.io.Serializable;

import org.jfree.util.PublicCloneable;

/**
 * A (key, value) pair.  This class provides a default implementation 
 * of the {@link KeyedValue} interface.
 */
public class DefaultKeyedValue implements KeyedValue, 
                                          Cloneable, PublicCloneable, 
                                          Serializable {
  static {
    CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7388924517460437712L;
  static {
    CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[1]++;
  }
    
    /** The key. */
    private Comparable key;

    /** The value. */
    private Number value;

    /**
     * Creates a new (key, value) item.
     *
     * @param key  the key (should be immutable).
     * @param value  the value (<code>null</code> permitted).
     */
    public DefaultKeyedValue(Comparable key, Number value) {
        this.key = key;
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[2]++;
        this.value = value;
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[3]++;
    }

    /**
     * Returns the key.
     *
     * @return The key.
     */
    public Comparable getKey() {
        return this.key;
    }

    /**
     * Returns the value.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     *
     * @param value  the value (<code>null</code> permitted).
     */
    public synchronized void setValue(Number value) {
        this.value = value;
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[4]++;
    }

    /**
     * Tests this key-value pair for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[2]++;}
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultKeyedValue) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[4]++;}
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[7]++;
        // TODO: modify this so that we check for equality with any KeyedValue
        // rather than specifically a DefaultKeyedValue
        DefaultKeyedValue that = (DefaultKeyedValue) obj;
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        
        // TODO: the following checks for null should be handled in a utility 
        // method
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((this.key != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 ? !
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((this.key.equals(that.key)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 : 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((that.key != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[6]++;}
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((this.value != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 ? !
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((this.value.equals(that.value)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 : 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((that.value != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.branches[8]++;}
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = (this.key != null ? this.key.hashCode() : 0);
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[10]++;
        result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[11]++;
        return result;
    }

    /**
     * Returns a clone.  It is assumed that both the key and value are 
     * immutable objects, so only the references are cloned, not the objects 
     * themselves.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException Not thrown by this class, but 
     *         subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt.statements[12]++;
        DefaultKeyedValue clone = (DefaultKeyedValue) super.clone();
        return clone;
    }
    
    /** 
     * Returns a string representing this instance, primarily useful for 
     * debugging.
     * 
     * @return A string.
     */
    public String toString() {
        return "(" + this.key.toString() + ", " + this.value.toString() + ")";
    }

}

class CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.DefaultKeyedValue.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,3};
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

  public CodeCoverCoverageCounter$b80b2dtcd295yxe3hcncxk9vfme155rgpt () {
    super("org.jfree.data.DefaultKeyedValue.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("org.jfree.data.DefaultKeyedValue.java");
      for (int i = 1; i <= 12; i++) {
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

