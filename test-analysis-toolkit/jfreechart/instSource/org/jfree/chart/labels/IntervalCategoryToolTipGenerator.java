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
 * -------------------------------------
 * IntervalCategoryToolTipGenerator.java
 * -------------------------------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-May-2004 : Version 1, split from IntervalCategoryItemLabelGenerator (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.util.PublicCloneable;

/**
 * A tooltip generator for plots that use data from an 
 * {@link IntervalCategoryDataset}.
 */
public class IntervalCategoryToolTipGenerator 
    extends StandardCategoryToolTipGenerator
    implements CategoryToolTipGenerator, PublicCloneable, Cloneable,
               Serializable {
  static {
    CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3853824986520333437L;
  static {
    CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[1]++;
  }
    
    /** The default format string. */
    public static final String DEFAULT_TOOL_TIP_FORMAT_STRING 
        = "({0}, {1}) = {3} - {4}";
  static {
    CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[2]++;
  }
    
    /**
     * Creates a new generator with a default number formatter.
     */
    public IntervalCategoryToolTipGenerator() {
        super(DEFAULT_TOOL_TIP_FORMAT_STRING, NumberFormat.getInstance());
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[3]++;
    }

    /**
     * Creates a new generator with the specified number formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the number formatter (<code>null</code> not permitted).
     */
    public IntervalCategoryToolTipGenerator(String labelFormat, 
                                            NumberFormat formatter) {
        super(labelFormat, formatter);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[4]++;
    }
    
    /**
     * Creates a new generator with the specified date formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the date formatter (<code>null</code> not permitted).
     */
    public IntervalCategoryToolTipGenerator(String labelFormat, 
                                            DateFormat formatter) {
        super(labelFormat, formatter);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[5]++;
    }
    
    /**
     * Creates the array of items that can be passed to the 
     * <code>MessageFormat</code> class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(CategoryDataset dataset, 
                                       int row, int column) {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[6]++;
        Object[] result = new Object[5];
        result[0] = dataset.getRowKey(row).toString();
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[7]++;
        result[1] = dataset.getColumnKey(column).toString();
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[8]++;
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[9]++;
        Number value = dataset.getValue(row, column);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getNumberFormat() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[1]++;
            result[2] = getNumberFormat().format(value);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[11]++;
  
        }
        else {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[2]++;
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[12]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getDateFormat() != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[3]++;
            result[2] = getDateFormat().format(value);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[13]++;

        } else {
  CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[4]++;}
}
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[5]++;
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[15]++;
            IntervalCategoryDataset icd = (IntervalCategoryDataset) dataset;
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[16]++;
            Number start = icd.getStartValue(row, column);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[17]++;
            Number end = icd.getEndValue(row, column);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getNumberFormat() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[7]++;
                result[3] = getNumberFormat().format(start);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[19]++;  
                result[4] = getNumberFormat().format(end);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[20]++;
  
            }
            else {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[8]++;
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[21]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getDateFormat() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[9]++;
                result[3] = getDateFormat().format(start);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[22]++;
                result[4] = getDateFormat().format(end);
CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.statements[23]++;

            } else {
  CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[10]++;}
}

        } else {
  CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap.branches[6]++;}
        return result;
    }

}

class CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.IntervalCategoryToolTipGenerator.java";
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

  public CodeCoverCoverageCounter$pog73vcpwhq4patetfqafsu05ka8mqnlqgzkap9w1p9a1ppqve45sryap () {
    super("org.jfree.chart.labels.IntervalCategoryToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
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
    log.startNamedSection("org.jfree.chart.labels.IntervalCategoryToolTipGenerator.java");
      for (int i = 1; i <= 23; i++) {
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

