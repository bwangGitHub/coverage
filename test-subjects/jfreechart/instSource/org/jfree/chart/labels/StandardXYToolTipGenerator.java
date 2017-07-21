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
 * StandardXYToolTipGenerator.java
 * -------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 12-May-2004 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 25-Jan-2007 : Added new constructor - see bug 1624067 (DG);
 *
 */

package org.jfree.chart.labels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;

import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

/**
 * A standard tool tip generator for use with an 
 * {@link org.jfree.chart.renderer.xy.XYItemRenderer}.
 */
public class StandardXYToolTipGenerator extends AbstractXYItemLabelGenerator  
        implements XYToolTipGenerator, Cloneable, PublicCloneable,
                   Serializable {
  static {
    CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3564164459039540784L;
  static {
    CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[1]++;
  }    
    
    /** The default tooltip format. */
    public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2})";
  static {
    CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[2]++;
  }

    /**
     * Returns a tool tip generator that formats the x-values as dates and the 
     * y-values as numbers.
     * 
     * @return A tool tip generator (never <code>null</code>).
     */
    public static StandardXYToolTipGenerator getTimeSeriesInstance() {
        return new StandardXYToolTipGenerator(DEFAULT_TOOL_TIP_FORMAT, 
                DateFormat.getInstance(), NumberFormat.getInstance());
    }
    
    /**
     * Creates a tool tip generator using default number formatters.
     */
    public StandardXYToolTipGenerator() {
        this(DEFAULT_TOOL_TIP_FORMAT, NumberFormat.getNumberInstance(), 
                NumberFormat.getNumberInstance());
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[3]++;
    }

    /**
     * Creates a tool tip generator using the specified number formatters.
     *
     * @param formatString  the item label format string (<code>null</code> not
     *                      permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 not permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     */
    public StandardXYToolTipGenerator(String formatString, 
            NumberFormat xFormat, NumberFormat yFormat) {
        
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[4]++;
    
    }

    /**
     * Creates a tool tip generator using the specified number formatters.
     *
     * @param formatString  the label format string (<code>null</code> not 
     *                      permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 not permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     */
    public StandardXYToolTipGenerator(String formatString, DateFormat xFormat, 
            NumberFormat yFormat) {
        
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[5]++;
    
    }

    /**
     * Creates a tool tip generator using the specified formatters (a 
     * number formatter for the x-values and a date formatter for the 
     * y-values).
     *
     * @param formatString  the item label format string (<code>null</code> 
     *                      not permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     *                 
     * @since 1.0.4
     */
    public StandardXYToolTipGenerator(String formatString,
            NumberFormat xFormat, DateFormat yFormat) {
        
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[6]++;
    }
    /**
     * Creates a tool tip generator using the specified date formatters.
     *
     * @param formatString  the label format string (<code>null</code> not 
     *                      permitted).
     * @param xFormat  the format object for the x values (<code>null</code> 
     *                 not permitted).
     * @param yFormat  the format object for the y values (<code>null</code> 
     *                 not permitted).
     */
    public StandardXYToolTipGenerator(String formatString,
            DateFormat xFormat, DateFormat yFormat) {
        
        super(formatString, xFormat, yFormat);
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[7]++;
    
    }

    /**
     * Generates the tool tip text for an item in a dataset.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The tooltip text (possibly <code>null</code>).
     */
    public String generateToolTip(XYDataset dataset, int series, int item) {
        return generateLabelString(dataset, series, item);
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.branches[2]++;}
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof StandardXYToolTipGenerator) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9.branches[4]++;}
        return super.equals(obj);
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
    
}

class CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9 ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.labels.StandardXYToolTipGenerator.java";
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

  public CodeCoverCoverageCounter$aj2zinee9jmup5j8mkd0pwgv14mdbwxu6wggy4ymk8gmmau9 () {
    super("org.jfree.chart.labels.StandardXYToolTipGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("org.jfree.chart.labels.StandardXYToolTipGenerator.java");
      for (int i = 1; i <= 9; i++) {
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

