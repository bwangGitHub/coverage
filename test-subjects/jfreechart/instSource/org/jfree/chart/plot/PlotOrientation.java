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
 * --------------------
 * PlotOrientation.java
 * --------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-May-2003 : Version 1 (DG);
 * 17-Jul-2003 : Added readResolve() method (DG);
 * 21-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.plot;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Used to indicate the orientation (horizontal or vertical) of a 2D plot.
 */
public final class PlotOrientation implements Serializable {
  static {
    CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2508771828190337782L;
  static {
    CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[1]++;
  }
    
    /** For a plot where the range axis is horizontal. */
    public static final PlotOrientation HORIZONTAL
            = new PlotOrientation("PlotOrientation.HORIZONTAL");
  static {
    CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[2]++;
  }

    /** For a plot where the range axis is vertical. */
    public static final PlotOrientation VERTICAL
            = new PlotOrientation("PlotOrientation.VERTICAL");
  static {
    CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[3]++;
  }

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private PlotOrientation(String name) {
        this.name = name;
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[4]++;
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
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[2]++;}
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof PlotOrientation) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[4]++;}
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[7]++;
        PlotOrientation orientation = (PlotOrientation) obj;
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.name.equals(orientation.toString())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[6]++;}
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
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[9]++;
        Object result = null;
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.equals(PlotOrientation.HORIZONTAL)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[7]++;
            result = PlotOrientation.HORIZONTAL;
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[11]++;

        }
        else {
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[8]++;
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[12]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.equals(PlotOrientation.VERTICAL)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[9]++;
            result = PlotOrientation.VERTICAL;
CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.statements[13]++;

        } else {
  CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l.branches[10]++;}
}
        return result;
    }

}

class CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.PlotOrientation.java";
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

  public CodeCoverCoverageCounter$9e73rfkt53wrnz2nf53fh5d4dr0sl7l () {
    super("org.jfree.chart.plot.PlotOrientation.java");
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
    log.startNamedSection("org.jfree.chart.plot.PlotOrientation.java");
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

