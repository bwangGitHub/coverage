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
 * ---------------------------------
 * SymbolicXYItemLabelGenerator.java
 * ---------------------------------
 * (C) Copyright 2001-2007, by Anthony Boulestreau and Contributors.
 *
 * Original Author:  Anthony Boulestreau;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 * 
 * Changes
 * -------
 * 29-Mar-2002 : Version 1, contributed by Anthony Boulestreau (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 23-Mar-2003 : Implemented Serializable (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 17-Nov-2003 : Implemented PublicCloneable (DG);
 * 25-Feb-2004 : Renamed XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 19-Jan-2005 : Now accesses primitives only from dataset (DG);
 * 20-Apr-2005 : Renamed XYLabelGenerator --> XYItemLabelGenerator (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XisSymbolic;
import org.jfree.data.xy.YisSymbolic;
import org.jfree.util.PublicCloneable;

/**
 * A standard item label generator for plots that use data from an 
 * {@link XYDataset}.
 */
public class SymbolicXYItemLabelGenerator implements XYItemLabelGenerator, 
                                                     XYToolTipGenerator,
                                                     Cloneable, 
                                                     PublicCloneable,
                                                     Serializable {
  static {
    CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3963400354475494395L;
  static {
    CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[1]++;
  }
    
    /**
     * Generates a tool tip text item for a particular item within a series.
     *
     * @param data  the dataset.
     * @param series  the series number (zero-based index).
     * @param item  the item number (zero-based index).
     *
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String generateToolTip(XYDataset data, int series, int item) {

        String xStr, yStr;
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((data instanceof YisSymbolic) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[1]++;
            yStr = ((YisSymbolic) data).getYSymbolicValue(series, item);
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[3]++;

        }
        else {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[2]++;
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[4]++;
            double y = data.getYValue(series, item);
            yStr = Double.toString(round(y, 2));
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[5]++;
        }
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((data instanceof XisSymbolic) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[3]++;
            xStr = ((XisSymbolic) data).getXSymbolicValue(series, item);
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[7]++;

        }
        else {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[4]++;
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[8]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((data instanceof TimeSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[5]++;
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[9]++;
            RegularTimePeriod p
                = ((TimeSeriesCollection) data).getSeries(series)
                    .getTimePeriod(item);
            xStr = p.toString();
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[10]++;

        }
        else {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[6]++;
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[11]++;
            double x = data.getXValue(series, item);
            xStr = Double.toString(round(x, 2));
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[12]++;
        }
}
        return "X: " + xStr + ", Y: " + yStr;
    }

    /**
     * Generates a label for the specified item. The label is typically a 
     * formatted version of the data value, but any text can be used.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index (zero-based).
     * @param category  the category index (zero-based).
     *
     * @return The label (possibly <code>null</code>).
     */
    public String generateLabel(XYDataset dataset, int series, int category) {
        return null;  //TODO: implement this method properly
    }

    /**
    * Round a double value.
    *
    * @param value  the value.
    * @param nb  the exponent.
    *
    * @return The rounded value.
    */
    private static double round(double value, int nb) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((nb <= 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[7]++;
            return Math.floor(value + 0.5d);

        } else {
  CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[8]++;}
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[14]++;
        double p = Math.pow(10, nb);
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[15]++;
        double tempval = Math.floor(value * p + 0.5d);
        return tempval / p;
    }
    
    /**
     * Returns an independent copy of the generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException { 
        return super.clone();
    }    

    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[10]++;}
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof SymbolicXYItemLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d.branches[12]++;}
        return false;
    }
    
}

class CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.SymbolicXYItemLabelGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$eslyhiettu88a11en7qamyy58evxjd3rhd43fm7z9tpti1o8x9d () {
    super("org.jfree.chart.labels.SymbolicXYItemLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.SymbolicXYItemLabelGenerator.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

