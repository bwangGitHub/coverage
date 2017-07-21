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
 * ---------------------------
 * StandardTickUnitSource.java
 * ---------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Sep-2003 : Version 1 (DG);
 * 25-Oct-2007 : Implemented Serializable and equals() method (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * A source that can used by the {@link NumberAxis} class to obtain a
 * suitable {@link TickUnit}.  Instances of this class are {@link Serializable}
 * from version 1.0.7 onwards.  Cloning is not supported, because instances
 * are immutable.
 */
public class StandardTickUnitSource implements TickUnitSource, Serializable {
  static {
    CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.ping();
  }


    /** Constant for log(10.0). */
    private static final double LOG_10_VALUE = Math.log(10.0);
  static {
    CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[1]++;
  }
    
    /**
     * Default constructor.
     */
    public StandardTickUnitSource() {
        super();
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[2]++;
    }
    
    /**
     * Returns a tick unit that is larger than the supplied unit.
     *
     * @param unit  the unit (<code>null</code> not permitted).
     *
     * @return A tick unit that is larger than the supplied unit.
     */
    public TickUnit getLargerTickUnit(TickUnit unit) {
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[3]++;
        double x = unit.getSize();
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[4]++;
        double log = Math.log(x) / LOG_10_VALUE;
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[5]++;
        double higher = Math.ceil(log);
        return new NumberTickUnit(Math.pow(10, higher), 
                new DecimalFormat("0.0E0"));
    }

    /**
     * Returns the tick unit in the collection that is greater than or equal
     * to (in size) the specified unit.
     *
     * @param unit  the unit (<code>null</code> not permitted).
     *
     * @return A unit from the collection.
     */
    public TickUnit getCeilingTickUnit(TickUnit unit) {
        return getLargerTickUnit(unit);
    }

    /**
     * Returns the tick unit in the collection that is greater than or equal
     * to the specified size.
     *
     * @param size  the size.
     *
     * @return A unit from the collection.
     */
    public TickUnit getCeilingTickUnit(double size) {
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[6]++;
        double log = Math.log(size) / LOG_10_VALUE;
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[7]++;
        double higher = Math.ceil(log);
        return new NumberTickUnit(Math.pow(10, higher), 
                new DecimalFormat("0.0E0"));
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh.branches[2]++;}
        return (obj instanceof StandardTickUnitSource);
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return 0;
    }

}

class CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.StandardTickUnitSource.java";
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

  public CodeCoverCoverageCounter$5c4l6388boie9fy5jhv9262uyp5yjdsh46q5u81ysh () {
    super("org.jfree.chart.axis.StandardTickUnitSource.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.StandardTickUnitSource.java");
      for (int i = 1; i <= 8; i++) {
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

