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
 * ---------------------
 * PieSectionEntity.java
 * ---------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 23-May-2002 : Version 1 (DG);
 * 12-Jun-2002 : Added Javadoc comments (DG);
 * 26-Jun-2002 : Added method to generate AREA tag for image map 
 *               generation (DG);
 * 05-Aug-2002 : Added new constructor to populate URLText
 *               Moved getImageMapAreaTag() to ChartEntity (superclass) (RA);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 07-Mar-2003 : Added pie index attribute, since the PiePlot class can create
 *               multiple pie plots within one chart.  Also renamed 'category' 
 *               --> 'sectionKey' and changed the class from Object --> 
 *               Comparable (DG);
 * 30-Jul-2003 : Added PieDataset reference (CZ);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 13-Nov-2007 : Implemented equals() and hashCode() (DG);
 *
 */

package org.jfree.chart.entity;

import java.awt.Shape;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.data.general.PieDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A chart entity that represents one section within a pie plot.
 */
public class PieSectionEntity extends ChartEntity
                              implements Serializable {
  static {
    CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 9199892576531984162L;
  static {
    CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[1]++;
  }
    
    /** The dataset. */
    private PieDataset dataset;
    
    /** The pie index. */
    private int pieIndex;

    /** The section index. */
    private int sectionIndex;

    /** The section key. */
    private Comparable sectionKey;

    /**
     * Creates a new pie section entity.
     *
     * @param area  the area.
     * @param dataset  the pie dataset.
     * @param pieIndex  the pie index (zero-based).
     * @param sectionIndex  the section index (zero-based).
     * @param sectionKey  the section key.
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text for HTML image maps.
     */
    public PieSectionEntity(Shape area, 
                            PieDataset dataset,
                            int pieIndex, int sectionIndex, 
                            Comparable sectionKey,
                            String toolTipText, String urlText) {

        super(area, toolTipText, urlText);
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[2]++;
        this.dataset = dataset;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[3]++;
        this.pieIndex = pieIndex;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[4]++;
        this.sectionIndex = sectionIndex;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[5]++;
        this.sectionKey = sectionKey;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[6]++;

    }

    /**
     * Returns the dataset this entity refers to.
     *
     * @return The dataset.
     * 
     * @see #setDataset(PieDataset)
     */
    public PieDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset this entity refers to.
     *
     * @param dataset  the dataset.
     * 
     * @see #getDataset()
     */
    public void setDataset(PieDataset dataset) {
        this.dataset = dataset;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[7]++;
    }

    /**
     * Returns the pie index.  For a regular pie chart, the section index is 0. 
     * For a pie chart containing multiple pie plots, the pie index is the row 
     * or column index from which the pie data is extracted.
     *
     * @return The pie index.
     * 
     * @see #setPieIndex(int)
     */
    public int getPieIndex() {
        return this.pieIndex;
    }

    /**
     * Sets the pie index.
     *
     * @param index  the new index value.
     * 
     * @see #getPieIndex()
     */
    public void setPieIndex(int index) {
        this.pieIndex = index;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[8]++;
    }

    /**
     * Returns the section index.
     *
     * @return The section index.
     * 
     * @see #setSectionIndex(int)
     */
    public int getSectionIndex() {
        return this.sectionIndex;
    }

    /**
     * Sets the section index.
     *
     * @param index  the section index.
     * 
     * @see #getSectionIndex()
     */
    public void setSectionIndex(int index) {
        this.sectionIndex = index;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[9]++;
    }

    /**
     * Returns the section key.
     *
     * @return The section key.
     * 
     * @see #setSectionKey(Comparable)
     */
    public Comparable getSectionKey() {
        return this.sectionKey;
    }

    /**
     * Sets the section key.
     *
     * @param key  the section key.
     * 
     * @see #getSectionKey()
     */
    public void setSectionKey(Comparable key) {
        this.sectionKey = key;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[10]++;
    }

    /**
     * Tests this entity for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[2]++;}
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof PieSectionEntity) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[4]++;}
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[13]++;
        PieSectionEntity that = (PieSectionEntity) obj;
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.dataset, that.dataset)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[6]++;}
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.pieIndex != that.pieIndex) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[8]++;}
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.sectionIndex != that.sectionIndex) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[10]++;}
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.sectionKey, that.sectionKey)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.branches[12]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[18]++;
        int result = super.hashCode();
        result = HashUtilities.hashCode(result, this.pieIndex);
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[19]++;
        result = HashUtilities.hashCode(result, this.sectionIndex);
CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69.statements[20]++;
        return result;
    }
    
    /**
     * Returns a string representing the entity.
     *
     * @return A string representing the entity.
     */
    public String toString() {
        return "PieSection: " + this.pieIndex + ", " + this.sectionIndex + "("
                              + this.sectionKey.toString() + ")";
    }

}

class CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69 ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.PieSectionEntity.java";
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

  public CodeCoverCoverageCounter$1uslqlmltd3k1f5uijy53augfkdntug69 () {
    super("org.jfree.chart.entity.PieSectionEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
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
    log.startNamedSection("org.jfree.chart.entity.PieSectionEntity.java");
      for (int i = 1; i <= 20; i++) {
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

