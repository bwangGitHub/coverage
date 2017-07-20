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
 * ----------------
 * KeyedObject.java
 * ----------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 05-Feb-2003 : Version 1 (DG);
 * 27-Jan-2003 : Implemented Cloneable and Serializable, and added an equals()
 *               method (DG);
 *
 */

package org.jfree.data;

import java.io.Serializable;

import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A (key, object) pair.
 */
public class KeyedObject implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2677930479256885863L;
  static {
    CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[1]++;
  }
    
    /** The key. */
    private Comparable key;

    /** The object. */
    private Object object;

    /**
     * Creates a new (key, object) pair.
     *
     * @param key  the key.
     * @param object  the object (<code>null</code> permitted).
     */
    public KeyedObject(Comparable key, Object object) {
        this.key = key;
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[2]++;
        this.object = object;
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[3]++;
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
     * Returns the object.
     *
     * @return The object (possibly <code>null</code>).
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * Sets the object.
     *
     * @param object  the object (<code>null</code> permitted).
     */
    public void setObject(Object object) {
        this.object = object;
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[4]++;
    }
    
    /**
     * Returns a clone of this object.  It is assumed that the key is an 
     * immutable object, so it is not deep-cloned.  The object is deep-cloned 
     * if it implements {@link PublicCloneable}, otherwise a shallow clone is 
     * made.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[5]++;
        KeyedObject clone = (KeyedObject) super.clone();
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.object instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[1]++;
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[7]++;
            PublicCloneable pc = (PublicCloneable) this.object;
            clone.object = pc.clone();
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[8]++;

        } else {
  CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[2]++;}
        return clone;      
    }
    
    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[4]++;}
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof KeyedObject) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[6]++;}
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[11]++;
        KeyedObject that = (KeyedObject) obj;
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.key, that.key)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[8]++;}
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.object, that.object)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01.branches[10]++;}

        return true;
    }
    
}

class CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01 ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.data.KeyedObject.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$4govz8w5ibkn9pvunqhlvuu01 () {
    super("org.jfree.data.KeyedObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
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
    log.startNamedSection("org.jfree.data.KeyedObject.java");
      for (int i = 1; i <= 13; i++) {
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

