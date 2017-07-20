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
 * GreyPalette.java
 * ----------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 26-Nov-2002 : Version 1 contributed by David M. O'Donnell (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 *
 */

package org.jfree.chart.plot;

import java.io.Serializable;

import org.jfree.chart.renderer.xy.XYBlockRenderer;


/**
 * A grey color palette.
 * 
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class GreyPalette extends ColorPalette implements Serializable {
  static {
    CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2120941170159987395L;
  static {
    CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[1]++;
  }
    
    /**
     * Creates a new palette.
     */
    public GreyPalette() {
        super();
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[2]++;
        initialize();
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[3]++;
    }

    /**
     * Intializes the palette's indices.
     */
    public void initialize() {

        setPaletteName("Grey");
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[4]++;

        this.r = new int[256];
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[5]++;
        this.g = new int[256];
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[6]++;
        this.b = new int[256];
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[7]++;

        this.r[0] = 255;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[8]++;
        this.g[0] = 255;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[9]++;
        this.b[0] = 255;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[10]++;
        this.r[1] = 0;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[11]++;
        this.g[1] = 0;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[12]++;
        this.b[1] = 0;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[13]++;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

        for (int i = 2;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < 256) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.loops[1]--;
  CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.loops[2]--;
  CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.loops[3]++;
}
            this.r[i] = i;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[15]++;
            this.g[i] = i;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[16]++;
            this.b[i] = i;
CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x.statements[17]++;
        }

    }

}

class CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[0];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.GreyPalette.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$489ut8pzr5jbp3m1tdg1t634x () {
    super("org.jfree.chart.plot.GreyPalette.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.GreyPalette.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
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

