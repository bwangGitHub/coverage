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
 * BoxAndWhiskerToolTipGenerator.java
 * ------------------------------------
 * (C) Copyright 2004-2007, by David Browning and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 02-Jun-2004 : Version 1 (DG);
 * 23-Mar-2005 : Implemented PublicCloneable (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.NumberFormat;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.util.PublicCloneable;

/**
 * An item label generator for plots that use data from a 
 * {@link BoxAndWhiskerCategoryDataset}.
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
public class BoxAndWhiskerToolTipGenerator 
        extends StandardCategoryToolTipGenerator
        implements CategoryToolTipGenerator, Cloneable, PublicCloneable,
                   Serializable {
  static {
    CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -6076837753823076334L;
  static {
    CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[1]++;
  }
    
    /** The default tooltip format string. */
    public static final String DEFAULT_TOOL_TIP_FORMAT 
            = "X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ";
  static {
    CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[2]++;
  }
    
    /**
     * Creates a default tool tip generator.
     */
    public BoxAndWhiskerToolTipGenerator() {
        super(DEFAULT_TOOL_TIP_FORMAT, NumberFormat.getInstance());
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[3]++;
    }
    
    /**
     * Creates a tool tip formatter.
     * 
     * @param format  the tool tip format string.
     * @param formatter  the formatter.
     */
    public BoxAndWhiskerToolTipGenerator(String format, 
                                         NumberFormat formatter) {
        super(format, formatter);
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[4]++;   
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
    protected Object[] createItemArray(CategoryDataset dataset, int series, 
                                       int item) {
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[5]++;
        Object[] result = new Object[8];
        result[0] = dataset.getRowKey(series);
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[6]++;
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[7]++;
        Number y = dataset.getValue(series, item);
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[8]++;
        NumberFormat formatter = getNumberFormat();
        result[1] = formatter.format(y);
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[9]++;
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset instanceof BoxAndWhiskerCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.branches[1]++;
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[11]++;
            BoxAndWhiskerCategoryDataset d 
                = (BoxAndWhiskerCategoryDataset) dataset;
            result[2] = formatter.format(d.getMeanValue(series, item));
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[12]++;
            result[3] = formatter.format(d.getMedianValue(series, item));
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[13]++;
            result[4] = formatter.format(d.getMinRegularValue(series, item));
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[14]++;
            result[5] = formatter.format(d.getMaxRegularValue(series, item));
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[15]++;
            result[6] = formatter.format(d.getQ1Value(series, item));
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[16]++;
            result[7] = formatter.format(d.getQ3Value(series, item));
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[17]++;

        } else {
  CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.branches[2]++;}
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
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.branches[4]++;}
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof BoxAndWhiskerToolTipGenerator) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.branches[5]++;
            return super.equals(obj);

        } else {
  CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h.branches[6]++;}
        return false;
    }
    
}

class CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$2bqckj6m0teael3m4kyoxuypb3pmqha8zl92ascwl0xyuz7lg838h () {
    super("org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator.java");
      for (int i = 1; i <= 19; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

