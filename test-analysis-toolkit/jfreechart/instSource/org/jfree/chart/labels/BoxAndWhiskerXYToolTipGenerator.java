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
 * BoxAndWhiskerXYToolTipGenerator.java
 * ------------------------------------
 * (C) Copyright 2003-2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 28-Aug-2003 : Updated for changes in dataset API (DG);
 * 25-Feb-2004 : Renamed XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 27-Feb-2004 : Renamed BoxAndWhiskerItemLabelGenerator --> 
 *               BoxAndWhiskerXYItemLabelGenerator, and modified to use 
 *               MessageFormat (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 * An item label generator for plots that use data from a 
 * {@link BoxAndWhiskerXYDataset}.
 * <P>
 * The tooltip text and item label text are composed using a 
 * {@link java.text.MessageFormat} object, that can aggregate some or all of 
 * the following string values into a message.
 * <table>
 * <tr><td>0</td><td>Series Name</td></tr>
 * <tr><td>1</td><td>X (value or date)</td></tr>
 * <tr><td>2</td><td>Mean</td></tr>
 * <tr><td>3</td><td>Median</td></tr>
 * <tr><td>4</td><td>Minimum</td></tr>
 * <tr><td>5</td><td>Maximum</td></tr>
 * <tr><td>6</td><td>Quartile 1</td></tr>
 * <tr><td>7</td><td>Quartile 3</td></tr>
 * </table>
 */
public class BoxAndWhiskerXYToolTipGenerator extends StandardXYToolTipGenerator
        implements XYToolTipGenerator, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2648775791161459710L;
  static {
    CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[1]++;
  }
    
    /** The default tooltip format string. */
    public static final String DEFAULT_TOOL_TIP_FORMAT 
            = "X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ";
  static {
    CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[2]++;
  }
    
    /**
     * Creates a default item label generator.
     */
    public BoxAndWhiskerXYToolTipGenerator() {
        super(DEFAULT_TOOL_TIP_FORMAT, NumberFormat.getInstance(), 
                NumberFormat.getInstance());
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[3]++;
    }

    /**
     * Creates a new item label generator.  If the date formatter is not 
     * <code>null</code>, the x-values will be formatted as dates.
     * 
     * @param toolTipFormat  the tool tip format string (<code>null</code> not 
     *                       permitted).
     * @param numberFormat  the number formatter (<code>null</code> not 
     *                      permitted).
     * @param dateFormat  the date formatter (<code>null</code> permitted).
     */
    public BoxAndWhiskerXYToolTipGenerator(String toolTipFormat, 
                                           DateFormat dateFormat, 
                                           NumberFormat numberFormat) {
        
        super(toolTipFormat, dateFormat, numberFormat);
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[4]++;
    
    }
    
    /**
     * Creates the array of items that can be passed to the 
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(XYDataset dataset, int series, 
                                       int item) {
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[5]++;
        Object[] result = new Object[8];
        result[0] = dataset.getSeriesKey(series).toString();
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[6]++;
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[7]++;
        Number x = dataset.getX(series, item);
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getXDateFormat() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[1]++;
            result[1] = getXDateFormat().format(new Date(x.longValue()));
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[9]++;
   
        }
        else {
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[2]++;
            result[1] = getXFormat().format(x);
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[10]++;
        }
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[11]++;
        NumberFormat formatter = getYFormat();
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset instanceof BoxAndWhiskerXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[3]++;
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[13]++;
            BoxAndWhiskerXYDataset d = (BoxAndWhiskerXYDataset) dataset;
            result[2] = formatter.format(d.getMeanValue(series, item));
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[14]++;
            result[3] = formatter.format(d.getMedianValue(series, item));
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[15]++;
            result[4] = formatter.format(d.getMinRegularValue(series, item));
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[16]++;
            result[5] = formatter.format(d.getMaxRegularValue(series, item));
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[17]++;
            result[6] = formatter.format(d.getQ1Value(series, item));
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[18]++;
            result[7] = formatter.format(d.getQ3Value(series, item));
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[19]++;

        } else {
  CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[4]++;}
        return result;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[6]++;}
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof BoxAndWhiskerXYToolTipGenerator) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh.branches[8]++;}
        return super.equals(obj);
    }
    
}

class CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.BoxAndWhiskerXYToolTipGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$39m5b7mysb2jge1849hv8fgua022qg0bd51o656dilgdvvz1kymyupgh () {
    super("org.jfree.chart.labels.BoxAndWhiskerXYToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.BoxAndWhiskerXYToolTipGenerator.java");
      for (int i = 1; i <= 21; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

