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
 * ----------------------------
 * StandardPieURLGenerator.java
 * ----------------------------
 * (C) Copyright 2002-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributors:     David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 05-Aug-2002 : Version 1, contributed by Richard Atkinson;
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 07-Mar-2003 : Modified to use KeyedValuesDataset and added pieIndex 
 *               parameter (DG);
 * 21-Mar-2003 : Implemented Serializable (DG);
 * 24-Apr-2003 : Switched around PieDataset and KeyedValuesDataset (DG);
 * 31-Mar-2004 : Added an optional 'pieIndex' parameter (DG);
 * 13-Jan-2005 : Fixed for compliance with XHTML 1.0 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 24-Nov-2006 : Fixed equals() method and added argument checks (DG);
 * 17-Apr-2007 : Encode section key in generateURL() (DG);
 *
 */
 
package org.jfree.chart.urls;

import java.io.Serializable;

import org.jfree.data.general.PieDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A URL generator for pie charts.  Instances of this class are immutable.
 */
public class StandardPieURLGenerator implements PieURLGenerator, Serializable {
  static {
    CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1626966402065883419L;
  static {
    CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[1]++;
  }
    
    /** The prefix. */
    private String prefix = "index.html";
  {
    CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[2]++;
  }

    /** The category parameter name. */
    private String categoryParameterName = "category";
  {
    CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[3]++;
  }
    
    /** The pie index parameter name. */
    private String indexParameterName = "pieIndex";
  {
    CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[4]++;
  }

    /**
     * Default constructor.
     */
    public StandardPieURLGenerator() {
        this("index.html");
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[5]++;
    }

    /**
     * Creates a new generator.
     *
     * @param prefix  the prefix (<code>null</code> not permitted).
     */
    public StandardPieURLGenerator(String prefix) {
        this(prefix, "category");
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[6]++;
    }

    /**
     * Creates a new generator.
     *
     * @param prefix  the prefix (<code>null</code> not permitted).
     * @param categoryParameterName  the category parameter name 
     *     (<code>null</code> not permitted).
     */
    public StandardPieURLGenerator(String prefix, 
                                   String categoryParameterName) {
        this(prefix, categoryParameterName, "pieIndex");
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[7]++;
    }

    /**
     * Creates a new generator.
     *
     * @param prefix  the prefix (<code>null</code> not permitted).
     * @param categoryParameterName  the category parameter name 
     *     (<code>null</code> not permitted).
     * @param indexParameterName  the index parameter name (<code>null</code> 
     *     permitted).
     */
    public StandardPieURLGenerator(String prefix, 
                                   String categoryParameterName, 
                                   String indexParameterName) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[1]++;
            throw new IllegalArgumentException("Null 'prefix' argument.");

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[2]++;}
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((categoryParameterName == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[3]++;
            throw new IllegalArgumentException(
                    "Null 'categoryParameterName' argument.");

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[4]++;}
        this.prefix = prefix;
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[10]++;
        this.categoryParameterName = categoryParameterName;
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[11]++;
        this.indexParameterName = indexParameterName;
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[12]++;
    }

    /**
     * Generates a URL.
     *
     * @param dataset  the dataset (ignored).
     * @param key  the item key (<code>null</code> not permitted).
     * @param pieIndex  the pie index.
     *
     * @return A string containing the generated URL.
     */
    public String generateURL(PieDataset dataset, Comparable key, 
            int pieIndex) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[13]++;
        String url = this.prefix;
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((url.indexOf("?") > -1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[5]++;
            url += "&amp;" + this.categoryParameterName + "=" 
                    + URLUtilities.encode(key.toString(), "UTF-8");
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[15]++;

        }
        else {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[6]++;
            url += "?" + this.categoryParameterName + "=" 
                    + URLUtilities.encode(key.toString(), "UTF-8");
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[16]++;
        }
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.indexParameterName != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[7]++;
            url += "&amp;" + this.indexParameterName + "=" 
                   + String.valueOf(pieIndex);
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[18]++;

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[8]++;}
        return url;
    }

    /**
     * Tests if this object is equal to another.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[10]++;}
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof StandardPieURLGenerator) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[12]++;}
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[21]++;
        StandardPieURLGenerator that = (StandardPieURLGenerator) obj;
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.prefix.equals(that.prefix)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[14]++;}
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.categoryParameterName.equals(that.categoryParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[16]++;}
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.indexParameterName, 
                that.indexParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx.branches[18]++;}
        return true;
    }
}

class CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.chart.urls.StandardPieURLGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$11y8mjayj72ateolbstxk9ob9dvq16q5vyhp7vpohsgx () {
    super("org.jfree.chart.urls.StandardPieURLGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.urls.StandardPieURLGenerator.java");
      for (int i = 1; i <= 24; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

