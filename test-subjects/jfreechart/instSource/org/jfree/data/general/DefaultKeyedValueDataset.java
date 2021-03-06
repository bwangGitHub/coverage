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
 * -----------------------------
 * DefaultKeyedValueDataset.java
 * -----------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 27-Mar-2003 : Version 1 (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 *
 */

package org.jfree.data.general;

import java.io.Serializable;

import org.jfree.data.DefaultKeyedValue;
import org.jfree.data.KeyedValue;
import org.jfree.util.ObjectUtilities;

/**
 * A default implementation of the {@link KeyedValueDataset} interface.
 */
public class DefaultKeyedValueDataset extends AbstractDataset
                                      implements KeyedValueDataset, 
                                                 Serializable {
  static {
    CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8149484339560406750L;
  static {
    CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[1]++;
  }
    
    /** Storage for the data. */
    private KeyedValue data;

    /**
     * Constructs a new dataset, initially empty.
     */
    public DefaultKeyedValueDataset() {
        this(null);
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[2]++;
    }

    /**
     * Creates a new dataset with the specified initial value.
     *
     * @param key  the key.
     * @param value  the value (<code>null</code> permitted).
     */
    public DefaultKeyedValueDataset(Comparable key, Number value) {
        this(new DefaultKeyedValue(key, value));
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[3]++;
    }

    /**
     * Creates a new dataset that uses the data from a {@link KeyedValue} 
     * instance.
     *
     * @param data  the data (<code>null</code> permitted).
     */
    public DefaultKeyedValueDataset(KeyedValue data) {
        this.data = data;
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[4]++;
    }

    /**
     * Returns the key associated with the value, or <code>null</code> if the 
     * dataset has no data item.
     *
     * @return The key.
     */
    public Comparable getKey() {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[5]++;
        Comparable result = null;
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.data != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[1]++;
            result = this.data.getKey();
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[7]++;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[2]++;}
        return result;
    }

    /**
     * Returns the value.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue() {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[8]++;
        Number result = null;
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.data != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[3]++;
            result = this.data.getValue();
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[10]++;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[4]++;}
        return result;
    }

    /**
     * Updates the value.
     *
     * @param value  the new value (<code>null</code> permitted).
     */
    public void updateValue(Number value) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.data == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[5]++;
            throw new RuntimeException("updateValue: can't update null.");

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[6]++;}
        setValue(this.data.getKey(), value);
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[12]++;
    }

    /**
     * Sets the value for the dataset and sends a {@link DatasetChangeEvent} to 
     * all registered listeners.
     *
     * @param key  the key.
     * @param value  the value (<code>null</code> permitted).
     */
    public void setValue(Comparable key, Number value) {
        this.data = new DefaultKeyedValue(key, value);
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[13]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[14]++;
    }

    /**
     * Tests this dataset for equality with an arbitrary object.
     *
     * @param obj  the object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[8]++;}
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof KeyedValueDataset) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[10]++;}
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[17]++;
        KeyedValueDataset that = (KeyedValueDataset) obj;
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.data == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[11]++;
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((that.getKey() != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((that.getValue() != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[13]++;
                return false;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[14]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[12]++;}
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data.getKey(), that.getKey())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[16]++;}
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data.getValue(), that.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.branches[18]++;}
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return (this.data != null ? this.data.hashCode() : 0);
    }

    /**
     * Creates a clone of the dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException This class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201.statements[22]++;
        DefaultKeyedValueDataset clone 
            = (DefaultKeyedValueDataset) super.clone();
        return clone;    
    }
    
}

class CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201 ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.data.general.DefaultKeyedValueDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1};
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

  public CodeCoverCoverageCounter$656f9cjx0e7mo8fg8b14alx12fotx6lplc55vz2e1l201 () {
    super("org.jfree.data.general.DefaultKeyedValueDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
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
    log.startNamedSection("org.jfree.data.general.DefaultKeyedValueDataset.java");
      for (int i = 1; i <= 22; i++) {
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

