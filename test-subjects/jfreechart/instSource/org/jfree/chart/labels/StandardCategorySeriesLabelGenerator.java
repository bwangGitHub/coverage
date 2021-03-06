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
 * -----------------------------------------
 * StandardCategorySeriesLabelGenerator.java
 * -----------------------------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Apr-2005 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-May-2006 : Fixed equals() method (bug 1481102) (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.MessageFormat;

import org.jfree.data.category.CategoryDataset;
import org.jfree.util.PublicCloneable;

/**
 * A standard series label generator for plots that use data from 
 * a {@link org.jfree.data.category.CategoryDataset}.
 */
public class StandardCategorySeriesLabelGenerator implements 
    CategorySeriesLabelGenerator, Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 4630760091523940820L;
  static {
    CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[1]++;
  }
    
    /** The default item label format. */
    public static final String DEFAULT_LABEL_FORMAT = "{0}";
  static {
    CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[2]++;
  }
    
    /** The format pattern. */
    private String formatPattern;

    /**
     * Creates a default series label generator (uses 
     * {@link #DEFAULT_LABEL_FORMAT}).
     */
    public StandardCategorySeriesLabelGenerator() {
        this(DEFAULT_LABEL_FORMAT);
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[3]++;
    }
    
    /**
     * Creates a new series label generator.
     * 
     * @param format  the format pattern (<code>null</code> not permitted).
     */
    public StandardCategorySeriesLabelGenerator(String format) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((format == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[1]++;
            throw new IllegalArgumentException("Null 'format' argument.");

        } else {
  CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[2]++;}
        this.formatPattern = format;
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[5]++;
    }

    /**
     * Generates a label for the specified series.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series.
     * 
     * @return A series label.
     */
    public String generateLabel(CategoryDataset dataset, int series) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[3]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[4]++;}
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[7]++;
        String label = MessageFormat.format(this.formatPattern, 
                createItemArray(dataset, series));
        return label;
    }

    /**
     * Creates the array of items that can be passed to the 
     * {@link MessageFormat} class for creating labels.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series (zero-based index).
     *
     * @return The items (never <code>null</code>).
     */
    protected Object[] createItemArray(CategoryDataset dataset, int series) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[8]++;
        Object[] result = new Object[1];
        result[0] = dataset.getRowKey(series).toString();
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[9]++;
        return result;
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
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[6]++;}
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof StandardCategorySeriesLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[8]++;}
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[12]++;
        StandardCategorySeriesLabelGenerator that 
                = (StandardCategorySeriesLabelGenerator) obj;
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.formatPattern.equals(that.formatPattern)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh.branches[10]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.StandardCategorySeriesLabelGenerator.java";
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

  public CodeCoverCoverageCounter$1lkzi349z00h2892t6puxt57272030e2zmqz1bi0wg3efyensnh2ea1cwgum7lgh () {
    super("org.jfree.chart.labels.StandardCategorySeriesLabelGenerator.java");
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
    log.startNamedSection("org.jfree.chart.labels.StandardCategorySeriesLabelGenerator.java");
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

