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
 * -------------------------------
 * CustomXYItemLabelGenerator.java
 * -------------------------------
 * (C) Copyright 2002-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 05-Aug-2002 : Version 1, contributed by Richard Atkinson (RA);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 21-Mar-2003 : Implemented Serializable (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 17-Nov-2003 : Implemented PublicCloneable (DG);
 * 25-Feb-2004 : Renamed XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.util.List;

import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

/**
 * A tool tip generator that stores custom tooltips. The dataset passed into 
 * the generateToolTip method is ignored.
 */
public class CustomXYToolTipGenerator implements XYToolTipGenerator, 
                                                 Cloneable, 
                                                 PublicCloneable,
                                                 Serializable {
  static {
    CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8636030004670141362L;
  static {
    CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[1]++;
  } 
    
    /** Storage for the tooltip lists. */
    private List toolTipSeries = new java.util.ArrayList();
  {
    CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[2]++;
  }

    /**
     * Default constructor.
     */
    public CustomXYToolTipGenerator() {
        super();
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[3]++;
    }

    /**
     * Returns the number of tool tip lists stored by the renderer.
     *
     * @return The list count.
     */
    public int getListCount() {
        return this.toolTipSeries.size();
    }

    /**
     * Returns the number of tool tips in a given list.
     *
     * @param list  the list index (zero based).
     *
     * @return The tooltip count.
     */
    public int getToolTipCount(int list) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[4]++;

        int result = 0;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[5]++;
        List tooltips = (List) this.toolTipSeries.get(list);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((tooltips != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[1]++;
            result = tooltips.size();
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[7]++;

        } else {
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[2]++;}
        return result;
    }

    /**
     * Returns the tool tip text for an item.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The tool tip text.
     */
    public String getToolTipText(int series, int item) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[8]++;

        String result = null;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series < getListCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[3]++;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[10]++;
            List tooltips = (List) this.toolTipSeries.get(series);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((tooltips != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[5]++;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((item < tooltips.size()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[7]++;
                    result = (String) tooltips.get(item);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[13]++;

                } else {
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[8]++;}

            } else {
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[4]++;}

        return result;
    }

    /**
     * Adds a list of tooltips for a series.
     *
     * @param toolTips  the list of tool tips.
     */
    public void addToolTipSeries(List toolTips) {
        this.toolTipSeries.add(toolTips);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[14]++;
    }

    /**
     * Generates a tool tip text item for a particular item within a series.
     *
     * @param data  the dataset (ignored in this implementation).
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The tooltip text.
     */
    public String generateToolTip(XYDataset data, int series, int item) {

        return getToolTipText(series, item);

    }

    /**
     * Returns an independent copy of the generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[15]++;
        
        CustomXYToolTipGenerator clone 
            = (CustomXYToolTipGenerator) super.clone();
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.toolTipSeries != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[9]++;
            clone.toolTipSeries = new java.util.ArrayList(this.toolTipSeries);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[17]++;

        } else {
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[10]++;}
        return clone;
        
    }
    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[12]++;}
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof CustomXYToolTipGenerator) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[13]++;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[20]++;
            CustomXYToolTipGenerator generator = (CustomXYToolTipGenerator) obj;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[21]++;
            boolean result = true;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[22]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((series < getListCount()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[1]--;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[2]--;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[3]++;
}
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((item < getToolTipCount(series)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[4]--;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[5]--;
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.loops[6]++;
}
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[24]++;
                    String t1 = getToolTipText(series, item);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[25]++;
                    String t2 = generator.getToolTipText(series, item);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((t1 != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[15]++;
                        result = result && t1.equals(t2);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[27]++;

                    }
                    else {
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[16]++;
                        result = result && (t2 == null);
CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.statements[28]++;
                    }
                }
            }
            return result;

        } else {
  CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap.branches[14]++;}

        return false;

    }

}

class CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.CustomXYToolTipGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$625b1krbv01332t3nni9hz58zk6460kadzc6wdrsflaap () {
    super("org.jfree.chart.labels.CustomXYToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.CustomXYToolTipGenerator.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

