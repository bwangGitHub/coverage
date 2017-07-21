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
 * KeyedValueComparatorType.java
 * -----------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 05-Mar-2003 : Version 1 (DG);
 *
 */

package org.jfree.data;


/**
 * Used to indicate the type of a {@link KeyedValueComparator} : 'by key' or 
 * 'by value'.
 */
public final class KeyedValueComparatorType {
  static {
    CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.ping();
  }


    /** An object representing 'by key' sorting. */
    public static final KeyedValueComparatorType BY_KEY
        = new KeyedValueComparatorType("KeyedValueComparatorType.BY_KEY");
  static {
    CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.statements[1]++;
  }

    /** An object representing 'by value' sorting. */
    public static final KeyedValueComparatorType BY_VALUE
        = new KeyedValueComparatorType("KeyedValueComparatorType.BY_VALUE");
  static {
    CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.statements[2]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private KeyedValueComparatorType(String name) {
        this.name = name;
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.statements[3]++;
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
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == o) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.branches[2]++;}
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((o instanceof KeyedValueComparatorType) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.branches[4]++;}
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.statements[6]++;

        KeyedValueComparatorType type = (KeyedValueComparatorType) o;
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(type.name)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l.branches[6]++;}

        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return this.name.hashCode();
    }
}

class CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.data.KeyedValueComparatorType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$6rtdeangzn12oj72ljjmfl25toiuyhtdr8o55pxngsl7l () {
    super("org.jfree.data.KeyedValueComparatorType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.KeyedValueComparatorType.java");
      for (int i = 1; i <= 7; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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


