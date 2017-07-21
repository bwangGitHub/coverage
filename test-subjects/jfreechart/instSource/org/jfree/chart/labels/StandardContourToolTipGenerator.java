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
 * ------------------------------------
 * StandardContourToolTipGenerator.java
 * ------------------------------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 23-Jan-2003 : Added standard header (DG);
 * 21-Mar-2003 : Implemented Serializable (DG);
 * 15-Jul-2004 : Switched the getZ() and getZValue() methods (DG);
 * 19-Jan-2005 : Now accesses primitives only from dataset (DG);
 * 
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.contour.ContourDataset;

/**
 * A standard tooltip generator for plots that use data from an 
 * {@link ContourDataset}.
 *
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class StandardContourToolTipGenerator implements ContourToolTipGenerator,
                                                        Serializable {
  static {
    CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1881659351247502711L;
  static {
    CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[1]++;
  }
    
    /** The number formatter. */
    private DecimalFormat valueForm = new DecimalFormat("##.###");
  {
    CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[2]++;
  }

    /**
     * Generates a tooltip text item for a particular item within a series.
     *
     * @param data  the dataset.
     * @param item  the item index (zero-based).
     *
     * @return The tooltip text.
     */
    public String generateToolTip(ContourDataset data, int item) {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[3]++;

        double x = data.getXValue(0, item);
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[4]++;
        double y = data.getYValue(0, item);
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[5]++;
        double z = data.getZValue(0, item);
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[6]++;
        String xString = null;
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((data.isDateAxis(0)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[1]++;
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[8]++;
            SimpleDateFormat formatter 
                = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[9]++;
            StringBuffer strbuf = new StringBuffer();
            strbuf = formatter.format(
                new Date((long) x), strbuf, new java.text.FieldPosition(0)
            );
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[10]++;
            xString = strbuf.toString();
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[11]++;

        }
        else {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[2]++;
            xString = this.valueForm.format(x);
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[12]++;
        }
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Double.isNaN(z)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[3]++;
            return "X: " + xString
                   + ", Y: " + this.valueForm.format(y)
                   + ", Z: " + this.valueForm.format(z);

        }
        else {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[4]++;
            return "X: " + xString
                 + ", Y: " + this.valueForm.format(y)
                 + ", Z: no data";
        }

    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[6]++;}
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof StandardContourToolTipGenerator) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[8]++;}
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[16]++;
        StandardContourToolTipGenerator that 
            = (StandardContourToolTipGenerator) obj;
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.valueForm != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[9]++;
            return this.valueForm.equals(that.valueForm);

        } else {
  CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt.branches[10]++;}
        return false;

    }

}

class CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.StandardContourToolTipGenerator.java";
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

  public CodeCoverCoverageCounter$43qu38mjk3wrv2qxmki05wi2kqn7pre5i3rv7rgc2tatdzljs42qlvtt () {
    super("org.jfree.chart.labels.StandardContourToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("org.jfree.chart.labels.StandardContourToolTipGenerator.java");
      for (int i = 1; i <= 17; i++) {
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

