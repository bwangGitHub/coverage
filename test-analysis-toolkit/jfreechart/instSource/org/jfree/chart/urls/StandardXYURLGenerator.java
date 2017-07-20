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
 * ---------------------------
 * StandardXYURLGenerator.java
 * ---------------------------
 * (C) Copyright 2002-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributors:     David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 05-Aug-2002 : Version 1, contributed by Richard Atkinson;
 * 29-Aug-2002 : New constructor and member variables to customise series and 
 *               item parameter names (RA);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 23-Mar-2003 : Implemented Serializable (DG);
 * 01-Mar-2004 : Added equals() method (DG);
 * 13-Jan-2005 : Modified for XHTML 1.0 compliance (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.urls;

import java.io.Serializable;

import org.jfree.data.xy.XYDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A URL generator.
 */
public class StandardXYURLGenerator implements XYURLGenerator, Serializable {
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -1771624523496595382L;
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[1]++;
  }
    
    /** The default prefix. */
    public static final String DEFAULT_PREFIX = "index.html";
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[2]++;
  }
    
    /** The default series parameter. */
    public static final String DEFAULT_SERIES_PARAMETER = "series";
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[3]++;
  }
    
    /** The default item parameter. */
    public static final String DEFAULT_ITEM_PARAMETER = "item";
  static {
    CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[4]++;
  }
    
    /** Prefix to the URL */
    private String prefix;

    /** Series parameter name to go in each URL */
    private String seriesParameterName;

    /** Item parameter name to go in each URL */
    private String itemParameterName;

    /**
     * Creates a new default generator.  This constructor is equivalent to
     * calling <code>StandardXYURLGenerator("index.html", "series", "item");
     * </code>.
     */
    public StandardXYURLGenerator() {
        this(DEFAULT_PREFIX, DEFAULT_SERIES_PARAMETER, DEFAULT_ITEM_PARAMETER);
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[5]++;
    }

    /**
     * Creates a new generator with the specified prefix.  This constructor 
     * is equivalent to calling 
     * <code>StandardXYURLGenerator(prefix, "series", "item");</code>.
     *
     * @param prefix  the prefix to the URL (<code>null</code> not permitted).
     */
    public StandardXYURLGenerator(String prefix) {
        this(prefix, DEFAULT_SERIES_PARAMETER, DEFAULT_ITEM_PARAMETER);
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[6]++;
    }

    /**
     * Constructor that overrides all the defaults
     *
     * @param prefix  the prefix to the URL (<code>null</code> not permitted).
     * @param seriesParameterName  the name of the series parameter to go in 
     *                             each URL (<code>null</code> not permitted).
     * @param itemParameterName  the name of the item parameter to go in each
     *                           URL (<code>null</code> not permitted).
     */
    public StandardXYURLGenerator(String prefix,
                                  String seriesParameterName,
                                  String itemParameterName) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[1]++;
            throw new IllegalArgumentException("Null 'prefix' argument.");

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[2]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((seriesParameterName == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[3]++;
            throw new IllegalArgumentException(
                    "Null 'seriesParameterName' argument.");

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[4]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((itemParameterName == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'itemParameterName' argument.");

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[6]++;}
        this.prefix = prefix;
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[10]++;
        this.seriesParameterName = seriesParameterName;
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[11]++;
        this.itemParameterName = itemParameterName;
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[12]++;
    }

    /**
     * Generates a URL for a particular item within a series.
     *
     * @param dataset  the dataset.
     * @param series  the series number (zero-based index).
     * @param item  the item number (zero-based index).
     *
     * @return The generated URL.
     */
    public String generateURL(XYDataset dataset, int series, int item) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[13]++;
        String url = this.prefix;
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[14]++;
        boolean firstParameter = url.indexOf("?") == -1;
        url += firstParameter ? "?" : "&amp;";
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[15]++;
        url += this.seriesParameterName + "=" + series
                + "&amp;" + this.itemParameterName + "=" + item;
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[16]++;
        return url;
    }

    /**
     * Tests this generator for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[7]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[8]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof StandardXYURLGenerator) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[10]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[19]++;
        StandardXYURLGenerator that = (StandardXYURLGenerator) obj;
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(that.prefix, this.prefix)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[12]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(that.seriesParameterName, 
                this.seriesParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[14]++;}
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(that.itemParameterName, 
                this.itemParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5.branches[16]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5 ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.chart.urls.StandardXYURLGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$5c4l6388boie9i9z2h4uoyathobb259hcmr4s4jof5 () {
    super("org.jfree.chart.urls.StandardXYURLGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.urls.StandardXYURLGenerator.java");
      for (int i = 1; i <= 22; i++) {
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
    for (int i = 1; i <= 8; i++) {
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

