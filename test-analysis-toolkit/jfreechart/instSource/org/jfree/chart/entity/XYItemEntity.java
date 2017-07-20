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
 * -----------------
 * XYItemEntity.java
 * -----------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 23-May-2002 : Version 1 (DG);
 * 12-Jun-2002 : Added accessor methods and Javadoc comments (DG);
 * 26-Jun-2002 : Added getImageMapAreaTag() method (DG);
 * 05-Aug-2002 : Added new constructor to populate URLText
 *               Moved getImageMapAreaTag() to ChartEntity (superclass) (RA);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 30-Jun-2003 : Added XYDataset reference (CZ);
 * 20-May-2004 : Added equals() and clone() methods and implemented 
 *               Serializable (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 *
 */

package org.jfree.chart.entity;

import java.awt.Shape;

import org.jfree.data.xy.XYDataset;

/**
 * A chart entity that represents one item within an 
 * {@link org.jfree.chart.plot.XYPlot}.
 */
public class XYItemEntity extends ChartEntity {
  static {
    CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3870862224880283771L;
  static {
    CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[1]++;
  }
    
    /** The dataset. */
    private transient XYDataset dataset;
    
    /** The series. */
    private int series;

    /** The item. */
    private int item;

    /**
     * Creates a new entity.
     *
     * @param area  the area.
     * @param dataset  the dataset.
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text for HTML image maps.
     */
    public XYItemEntity(Shape area, 
                        XYDataset dataset, int series, int item,
                        String toolTipText, String urlText) {
        super(area, toolTipText, urlText);
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[2]++;
        this.dataset = dataset;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[3]++;
        this.series = series;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[4]++;
        this.item = item;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[5]++;
    }

    /**
     * Returns the dataset this entity refers to.
     *
     * @return The dataset.
     */
    public XYDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset this entity refers to.
     *
     * @param dataset  the dataset.
     */
    public void setDataset(XYDataset dataset) {
        this.dataset = dataset;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[6]++;
    }

    /**
     * Returns the series index.
     *
     * @return The series index.
     */
    public int getSeriesIndex() {
        return this.series;
    }

    /**
     * Sets the series index.
     *
     * @param series the series index (zero-based).
     */
    public void setSeriesIndex(int series) {
        this.series = series;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[7]++;
    }

    /**
     * Returns the item index.
     *
     * @return The item index.
     */
    public int getItem() {
        return this.item;
    }

    /**
     * Sets the item index.
     *
     * @param item the item index (zero-based).
     */
    public void setItem(int item) {
        this.item = item;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[8]++;
    }

    /**
     * Tests the entity for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[1]++;
            return true;
      
        } else {
  CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[2]++;}
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((obj instanceof XYItemEntity) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[3]++;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[11]++;
            XYItemEntity ie = (XYItemEntity) obj;
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.series != ie.series) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[5]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[6]++;}
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.item != ie.item) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[7]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[8]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9.branches[4]++;}
        return false;
    }

    /**
     * Returns a string representation of this instance, useful for debugging
     * purposes.
     * 
     * @return A string.
     */
    public String toString() {
        return "XYItemEntity: series = " + getSeriesIndex() + ", item = " 
            + getItem() + ", dataset = " + getDataset();   
    }
    
}

class CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9 ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.XYItemEntity.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1};
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

  public CodeCoverCoverageCounter$116zxkj8orpvd087wwm5xu4iue9 () {
    super("org.jfree.chart.entity.XYItemEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
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
    log.startNamedSection("org.jfree.chart.entity.XYItemEntity.java");
      for (int i = 1; i <= 13; i++) {
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

