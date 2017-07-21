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
 * -----------------------
 * AbstractXYZDataset.java
 * -----------------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited).
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-May-2004 : Version 1 (DG);
 * 15-Jul-2004 : Switched getZ() and getZValue() (DG);
 *
 */

package org.jfree.data.xy;

/**
 * An base class that you can use to create new implementations of the 
 * {@link XYZDataset} interface.
 */
public abstract class AbstractXYZDataset extends AbstractXYDataset 
                                         implements XYZDataset {
  static {
    CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.ping();
  }


    /**
     * Returns the z-value (as a double primitive) for an item within a series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The z-value.
     */
    public double getZValue(int series, int item) {
CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.statements[1]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.statements[2]++;
        Number z = getZ(series, item);
CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((z != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.branches[1]++;
            result = z.doubleValue();
CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.statements[4]++;
   
        } else {
  CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5.branches[2]++;}
        return result;   
    }

}

class CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5 ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.AbstractXYZDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$24ag9rz5j0rq6jb1a3a5bp8dp6iw1wqgeqn5 () {
    super("org.jfree.data.xy.AbstractXYZDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.AbstractXYZDataset.java");
      for (int i = 1; i <= 4; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

