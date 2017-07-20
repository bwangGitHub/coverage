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
 * --------------------------
 * SeriesRenderingOrder.java
 * --------------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  Eric Thomas (www.isti.com);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 21-Apr-2005 : Version 1 contributed by Eric Thomas (ET);
 * 21-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.plot;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Defines the tokens that indicate the rendering order for series in a
 * {@link org.jfree.chart.plot.XYPlot}.
 */
public final class SeriesRenderingOrder implements Serializable {
  static {
    CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 209336477448807735L;
  static {
    CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[1]++;
  }
    
    /**
     * Render series in the order 0, 1, 2, ..., N-1, where N is the number
     * of series.
     */
    public static final SeriesRenderingOrder FORWARD
            = new SeriesRenderingOrder("SeriesRenderingOrder.FORWARD");
  static {
    CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[2]++;
  }

    /**
     * Render series in the order N-1, N-2, ..., 2, 1, 0, where N is the
     * number of series.
     */
    public static final SeriesRenderingOrder REVERSE
            = new SeriesRenderingOrder("SeriesRenderingOrder.REVERSE");
  static {
    CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[3]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private SeriesRenderingOrder(String name) {
        this.name = name;
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[4]++;
    }

    /**
     * Returns a string representing the object.
     *
     * @return The string (never <code>null</code>).
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified
     * object, and <code>false</code> otherwise.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[2]++;}
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof SeriesRenderingOrder) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[4]++;}
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[7]++;
        SeriesRenderingOrder order = (SeriesRenderingOrder) obj;
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(order.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[6]++;}
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
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
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(SeriesRenderingOrder.FORWARD)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[7]++;
            return SeriesRenderingOrder.FORWARD;

        }
        else {
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[8]++;
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.statements[10]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(SeriesRenderingOrder.REVERSE)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[9]++;
            return SeriesRenderingOrder.REVERSE;

        } else {
  CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l.branches[10]++;}
}
        return null;
    }

}

class CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.SeriesRenderingOrder.java";
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

  public CodeCoverCoverageCounter$3sokoff6xb7xqmgnjkza8h9t85lo0ere8vh1a3l () {
    super("org.jfree.chart.plot.SeriesRenderingOrder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.SeriesRenderingOrder.java");
      for (int i = 1; i <= 10; i++) {
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

