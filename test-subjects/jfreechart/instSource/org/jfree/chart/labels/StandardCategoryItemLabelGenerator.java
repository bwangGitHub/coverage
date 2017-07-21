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
 * ---------------------------------------
 * StandardCategoryItemLabelGenerator.java
 * ---------------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-May-2004 : Version 1 (DG);
 * 20-Apr-2005 : Renamed StandardCategoryLabelGenerator 
 *               --> StandardCategoryItemLabelGenerator (DG);
 * ------------- JFREECHART 1.0.0 ---------------------------------------------
 * 03-May-2005 : Added equals() implementation, to fix bug 1481087 (DG);
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;

import org.jfree.data.category.CategoryDataset;
import org.jfree.util.PublicCloneable;

/**
 * A standard label generator that can be used with a 
 * {@link org.jfree.chart.renderer.category.CategoryItemRenderer}.
 */
public class StandardCategoryItemLabelGenerator 
    extends AbstractCategoryItemLabelGenerator 
    implements CategoryItemLabelGenerator, Cloneable, PublicCloneable,
               Serializable {
  static {
    CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3499701401211412882L;
  static {
    CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[1]++;
  }
    
    /** The default format string. */
    public static final String DEFAULT_LABEL_FORMAT_STRING = "{2}";
  static {
    CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[2]++;
  }
    
    /**
     * Creates a new generator with a default number formatter.
     */
    public StandardCategoryItemLabelGenerator() {
        super(DEFAULT_LABEL_FORMAT_STRING, NumberFormat.getInstance());
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[3]++;
    }

    /**
     * Creates a new generator with the specified number formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the number formatter (<code>null</code> not permitted).
     */
    public StandardCategoryItemLabelGenerator(String labelFormat, 
                                              NumberFormat formatter) {
        super(labelFormat, formatter);
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[4]++;
    }
    
    /**
     * Creates a new generator with the specified number formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the number formatter (<code>null</code> not permitted).
     * @param percentFormatter  the percent formatter (<code>null</code> not
     *     permitted).
     * 
     * @since 1.0.2
     */
    public StandardCategoryItemLabelGenerator(String labelFormat, 
            NumberFormat formatter, NumberFormat percentFormatter) {
        super(labelFormat, formatter, percentFormatter);
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[5]++;
    }
    
    /**
     * Creates a new generator with the specified date formatter.
     *
     * @param labelFormat  the label format string (<code>null</code> not 
     *                     permitted).
     * @param formatter  the date formatter (<code>null</code> not permitted).
     */
    public StandardCategoryItemLabelGenerator(String labelFormat, 
                                              DateFormat formatter) {
        super(labelFormat, formatter);
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[6]++;
    }
    
    /**
     * Generates the label for an item in a dataset.  Note: in the current 
     * dataset implementation, each row is a series, and each column contains 
     * values for a particular category.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The label (possibly <code>null</code>).
     */
    public String generateLabel(CategoryDataset dataset, int row, int column) {
        return generateLabelString(dataset, row, column);
    }
    
    /**
     * Tests this generator for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> if this generator is equal to 
     *     <code>obj</code>, and <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.branches[2]++;}
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof StandardCategoryItemLabelGenerator) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td.branches[4]++;}
        return super.equals(obj);
    }
}

class CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.StandardCategoryItemLabelGenerator.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$14zsd9gnetsbl10u5sv4wks7b80730jf26zblopuq5o9mkyskcrn4gyg544td () {
    super("org.jfree.chart.labels.StandardCategoryItemLabelGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.labels.StandardCategoryItemLabelGenerator.java");
      for (int i = 1; i <= 8; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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

