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
 * StandardCategoryURLGenerator.java
 * ---------------------------------
 * (C) Copyright 2002-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributors:     David Gilbert (for Object Refinery Limited);
 *                   Cleland Early;
 *
 * Changes:
 * --------
 * 05-Aug-2002 : Version 1, contributed by Richard Atkinson;
 * 29-Aug-2002 : Reversed seriesParameterName and itemParameterName in 
 *               constructor.  Never should have been the other way round.  
 *               Also updated JavaDoc (RA);
 * 09-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 05-Nov-2002 : Base dataset is now TableDataset not CategoryDataset (DG);
 * 23-Mar-2003 : Implemented Serializable (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 23-Dec-2003 : Added fix for bug 861282 (DG);
 * 21-May-2004 : Added URL encoding - see patch 947854 (DG);
 * 13-Jan-2004 : Fixed for compliance with XHTML 1.0 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 17-Apr-2007 : Use new URLUtilities class to encode URLs (DG);
 *
 */

package org.jfree.chart.urls;

import java.io.Serializable;

import org.jfree.data.category.CategoryDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A URL generator that can be assigned to a 
 * {@link org.jfree.chart.renderer.category.CategoryItemRenderer}.
 */
public class StandardCategoryURLGenerator implements CategoryURLGenerator, 
                                                     Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2276668053074881909L;
  static {
    CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[1]++;
  }
    
    /** Prefix to the URL */
    private String prefix = "index.html";
  {
    CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[2]++;
  }

    /** Series parameter name to go in each URL */
    private String seriesParameterName = "series";
  {
    CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[3]++;
  }

    /** Category parameter name to go in each URL */
    private String categoryParameterName = "category";
  {
    CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[4]++;
  }

    /**
     * Creates a new generator with default settings.
     */
    public StandardCategoryURLGenerator() {
        super();
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[5]++;
    }

    /**
     * Constructor that overrides default prefix to the URL.
     *
     * @param prefix  the prefix to the URL (<code>null</code> not permitted).
     */
    public StandardCategoryURLGenerator(String prefix) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[1]++;
            throw new IllegalArgumentException("Null 'prefix' argument.");
   
        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[2]++;}
        this.prefix = prefix;
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[7]++;
    }

    /**
     * Constructor that overrides all the defaults.
     *
     * @param prefix  the prefix to the URL (<code>null</code> not permitted).
     * @param seriesParameterName  the name of the series parameter to go in 
     *                             each URL (<code>null</code> not permitted).
     * @param categoryParameterName  the name of the category parameter to go in
     *                               each URL (<code>null</code> not permitted).
     */
    public StandardCategoryURLGenerator(String prefix,
                                        String seriesParameterName,
                                        String categoryParameterName) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((prefix == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[3]++;
            throw new IllegalArgumentException("Null 'prefix' argument.");
   
        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[4]++;}
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((seriesParameterName == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[5]++;
            throw new IllegalArgumentException(
                "Null 'seriesParameterName' argument."
            );
   
        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[6]++;}
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((categoryParameterName == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[7]++;
            throw new IllegalArgumentException(
                "Null 'categoryParameterName' argument."
            );
   
        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[8]++;}
        this.prefix = prefix;
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[11]++;
        this.seriesParameterName = seriesParameterName;
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[12]++;
        this.categoryParameterName = categoryParameterName;
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[13]++;

    }

    /**
     * Generates a URL for a particular item within a series.
     *
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param category  the category index (zero-based).
     *
     * @return The generated URL.
     */
    public String generateURL(CategoryDataset dataset, int series, 
                              int category) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[14]++;
        String url = this.prefix;
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[15]++;
        Comparable seriesKey = dataset.getRowKey(series);
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[16]++;
        Comparable categoryKey = dataset.getColumnKey(category);
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[17]++;
        boolean firstParameter = url.indexOf("?") == -1;
        url += firstParameter ? "?" : "&amp;";
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[18]++;
        url += this.seriesParameterName + "=" + URLUtilities.encode(
                seriesKey.toString(), "UTF-8");
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[19]++;
        url += "&amp;" + this.categoryParameterName + "=" 
                + URLUtilities.encode(categoryKey.toString(), "UTF-8");
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[20]++;
        return url;
    }

    /**
     * Returns an independent copy of the URL generator.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not thrown by this class, but 
     *         subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
    
        // all attributes are immutable, so we can just return the super.clone()
        return super.clone();
        
    }
    
    /**
     * Tests the generator for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[10]++;}
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof StandardCategoryURLGenerator) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[12]++;}
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[23]++;
        StandardCategoryURLGenerator that = (StandardCategoryURLGenerator) obj;
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.prefix, that.prefix)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[14]++;}
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesParameterName, 
                that.seriesParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[16]++;}
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.categoryParameterName, 
                that.categoryParameterName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.branches[18]++;}
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        result = (this.prefix != null ? this.prefix.hashCode() : 0);
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[27]++;
        result = 29 * result 
            + (this.seriesParameterName != null 
                    ? this.seriesParameterName.hashCode() : 0);
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[28]++;
        result = 29 * result 
            + (this.categoryParameterName != null 
                    ? this.categoryParameterName.hashCode() : 0);
CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd.statements[29]++;
        return result;
    }
    
}

class CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.chart.urls.StandardCategoryURLGenerator.java";
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

  public CodeCoverCoverageCounter$eshfgyna7kww16xss8nad089a7ra3zth13spgwoqi1lgfx2mnhd () {
    super("org.jfree.chart.urls.StandardCategoryURLGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
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
    log.startNamedSection("org.jfree.chart.urls.StandardCategoryURLGenerator.java");
      for (int i = 1; i <= 29; i++) {
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

