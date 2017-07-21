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
 * LegendItemEntity.java
 * ---------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 05-Jun-2003 : Version 1 (DG);
 * 20-May-2004 : Added equals() method and implemented Cloneable and 
 *               Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-May-2007 : Added dataset and seriesKey fields (DG);
 * 
 */

package org.jfree.chart.entity;

import java.awt.Shape;
import java.io.Serializable;

import org.jfree.data.general.Dataset;
import org.jfree.util.ObjectUtilities;

/**
 * An entity that represents an item within a legend.
 */
public class LegendItemEntity extends ChartEntity 
                              implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7435683933545666702L;
  static {
    CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[1]++;
  }
    
    /**
     * The dataset.
     * 
     * @since 1.0.6
     */
    private Dataset dataset;
    
    /**
     * The series key.
     * 
     * @since 1.0.6
     */
    private Comparable seriesKey;
    
    /** The series index. */
    private int seriesIndex;

    /**
     * Creates a legend item entity.
     *
     * @param area  the area.
     */
    public LegendItemEntity(Shape area) {
        super(area);
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[2]++;
    }

    /**
     * Returns a reference to the dataset that this legend item is derived
     * from.
     * 
     * @return The dataset.
     * 
     * @since 1.0.6
     * 
     * @see #setDataset(Dataset)
     */
    public Dataset getDataset() {
        return this.dataset;
    }
    
    /**
     * Sets a reference to the dataset that this legend item is derived from.
     * 
     * @param dataset  the dataset.
     * 
     * @since 1.0.6
     */
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[3]++;
    }
    
    /**
     * Returns the series key that identifies the legend item.
     * 
     * @return The series key.
     * 
     * @since 1.0.6
     * 
     * @see #setSeriesKey(Comparable)
     */
    public Comparable getSeriesKey() {
        return this.seriesKey;
    }
    
    /**
     * Sets the key for the series.
     * 
     * @param key  the key.
     * 
     * @since 1.0.6
     * 
     * @see #getSeriesKey()
     */
    public void setSeriesKey(Comparable key) {
        this.seriesKey = key;
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[4]++;
    }
    
    /**
     * Returns the series index.
     *
     * @return The series index.
     * 
     * @see #setSeriesIndex(int)
     * 
     * @deprecated As of 1.0.6, use the {@link #getSeriesKey()} method.
     */
    public int getSeriesIndex() {
        return this.seriesIndex;
    }

    /**
     * Sets the series index.
     *
     * @param index  the series index.
     * 
     * @see #getSeriesIndex()
     * 
     * @deprecated As of 1.0.6, use the {@link #setSeriesKey(Comparable)} 
     *         method.
     */
    public void setSeriesIndex(int index) {
        this.seriesIndex = index;
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[5]++;
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[1]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[2]++;}
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof LegendItemEntity) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[4]++;}
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[8]++;
        LegendItemEntity that = (LegendItemEntity) obj;
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesKey, that.seriesKey)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[6]++;}
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.seriesIndex != that.seriesIndex) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[8]++;}
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.dataset, that.dataset)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9.branches[10]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a clone of the entity.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning the 
     *         object.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }
    
    /**
     * Returns a string representing this object (useful for debugging 
     * purposes).
     *
     * @return A string (never <code>null</code>).
     */
    public String toString() {
        return "LegendItemEntity: seriesKey=" + this.seriesKey 
                + ", dataset=" + this.dataset;
    }

}

class CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9 ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.LegendItemEntity.java";
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

  public CodeCoverCoverageCounter$1rgitq3ta991cs9vcu8twuj5q613q6hy9 () {
    super("org.jfree.chart.entity.LegendItemEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
    log.startNamedSection("org.jfree.chart.entity.LegendItemEntity.java");
      for (int i = 1; i <= 11; i++) {
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

