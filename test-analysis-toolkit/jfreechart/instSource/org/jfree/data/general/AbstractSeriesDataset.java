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
 * AbstractSeriesDataset.java
 * --------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 17-Nov-2001 : Version 1 (DG);
 * 28-Mar-2002 : Implemented SeriesChangeListener interface (DG);
 * 04-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 04-Feb-2003 : Removed redundant methods (DG);
 * 27-Mar-2003 : Implemented Serializable (DG);
 *
 */

package org.jfree.data.general;

import java.io.Serializable;

/**
 * An abstract implementation of the {@link SeriesDataset} interface, 
 * containing a mechanism for registering change listeners.
 */
public abstract class AbstractSeriesDataset extends AbstractDataset
                                            implements SeriesDataset,
                                                       SeriesChangeListener,
                                                       Serializable {
  static {
    CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6074996219705033171L;
  static {
    CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.statements[1]++;
  }
    
    /**
     * Creates a new dataset.
     */
    protected AbstractSeriesDataset() {
        super();
CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.statements[2]++;
    }

    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    public abstract int getSeriesCount();

    /**
     * Returns the key for a series.  
     * <p>
     * If <code>series</code> is not within the specified range, the 
     * implementing method should throw an {@link IndexOutOfBoundsException} 
     * (preferred) or an {@link IllegalArgumentException}.
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     *
     * @return The series key.
     */
    public abstract Comparable getSeriesKey(int series);
    
    /**
     * Returns the index of the named series, or -1.
     * 
     * @param seriesKey  the series key (<code>null</code> permitted).
     * 
     * @return The index.
     */
    public int indexOf(Comparable seriesKey) {
CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.statements[3]++;
        int seriesCount = getSeriesCount();
CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((s < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.loops[1]--;
  CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.loops[2]--;
  CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.loops[3]++;
}
CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
           if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getSeriesKey(s).equals(seriesKey)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.branches[1]++;
               return s;

           } else {
  CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.branches[2]++;}
        }
        return -1;
    }
    
    /**
     * Called when a series belonging to the dataset changes.
     *
     * @param event  information about the change.
     */
    public void seriesChanged(SeriesChangeEvent event) {
        fireDatasetChanged();
CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9.statements[6]++;
    }

}

class CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9 ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.data.general.AbstractSeriesDataset.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$l61jk3p2cd44bnj93zieh0jgl2r1eqy039hiteu9 () {
    super("org.jfree.data.general.AbstractSeriesDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.AbstractSeriesDataset.java");
      for (int i = 1; i <= 6; i++) {
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
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

