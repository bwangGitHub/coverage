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
 * DefaultValueDataset.java
 * ------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 27-Mar-2003 : Version 1 (DG);
 * 18-Aug-2003 : Implemented Cloneable (DG);
 * 03-Mar-2005 : Implemented PublicCloneable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 30-Jan-2007 : Added explicit super() call in constructor (for clarity) (DG);
 * 
 */

package org.jfree.data.general;

import java.io.Serializable;

import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A dataset that stores a single value (that is possibly <code>null</code>).  
 * This class provides a default implementation of the {@link ValueDataset} 
 * interface.
 */
public class DefaultValueDataset extends AbstractDataset
                                 implements ValueDataset, 
                                            Cloneable, PublicCloneable,
                                            Serializable {
  static {
    CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8137521217249294891L;
  static {
    CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[1]++;
  }
    
    /** The value. */
    private Number value;

    /**
     * Constructs a new dataset, initially empty.
     */
    public DefaultValueDataset() {
        this(null);
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[2]++;
    }
    
    /**
     * Creates a new dataset with the specified value.
     * 
     * @param value  the value.
     */
    public DefaultValueDataset(double value) {
        this(new Double(value));
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[3]++;
    }

    /**
     * Creates a new dataset with the specified value.
     *
     * @param value  the initial value (<code>null</code> permitted).
     */
    public DefaultValueDataset(Number value) {
        super();
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[4]++;
        this.value = value;
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[5]++;
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
     * Sets the value and sends a {@link DatasetChangeEvent} to all registered 
     * listeners.
     *
     * @param value  the new value (<code>null</code> permitted).
     */
    public void setValue(Number value) {
        this.value = value;
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[6]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[7]++;
    }

    /**
     * Tests this dataset for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.branches[2]++;}
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof ValueDataset) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.branches[3]++;
CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.statements[10]++;
            ValueDataset vd = (ValueDataset) obj;
            return ObjectUtilities.equal(this.value, vd.getValue());

        } else {
  CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep.branches[4]++;}
        return false;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return (this.value != null ? this.value.hashCode() : 0);
    }
    
}

class CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.data.general.DefaultValueDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$frhv3lfhm1vx0di8fb6uu424f8ub62m0cs5ep () {
    super("org.jfree.data.general.DefaultValueDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.DefaultValueDataset.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

