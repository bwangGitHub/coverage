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
 * ------------------------------
 * YIntervalSeriesCollection.java
 * ------------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 * 27-Nov-2006 : Added clone() override (DG);
 * 20-Feb-2007 : Added getYValue(), getStartYValue() and getEndYValue() 
 *               methods (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;
import java.util.List;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.util.ObjectUtilities;

/**
 * A collection of {@link YIntervalSeries} objects.
 *
 * @since 1.0.3
 *
 * @see YIntervalSeries
 */
public class YIntervalSeriesCollection extends AbstractIntervalXYDataset
                                implements IntervalXYDataset, Serializable {
  static {
    CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.ping();
  }


    /** Storage for the data series. */
    private List data;
    
    /** 
     * Creates a new instance of <code>YIntervalSeriesCollection</code>. 
     */
    public YIntervalSeriesCollection() {
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[1]++;
    }

    /**
     * Adds a series to the collection and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void addSeries(YIntervalSeries series) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[1]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[2]++;}
        this.data.add(series);
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[3]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[4]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[5]++;
    }

    /**
     * Returns the number of series in the collection.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.data.size();
    }

    /**
     * Returns a series from the collection.
     *
     * @param series  the series index (zero-based).
     *
     * @return The series.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the
     *     range <code>0</code> to <code>getSeriesCount() - 1</code>.
     */
    public YIntervalSeries getSeries(int series) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[3]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[4]++;}
        return (YIntervalSeries) this.data.get(series);
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     *
     * @return The key for a series.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the
     *     specified range.
     */
    public Comparable getSeriesKey(int series) {
        // defer argument checking
        return getSeries(series).getKey();
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The item count.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the
     *     range <code>0</code> to <code>getSeriesCount() - 1</code>.
     */
    public int getItemCount(int series) {
        // defer argument checking
        return getSeries(series).getItemCount();
    }

    /**
     * Returns the x-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The x-value.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[7]++;
        YIntervalSeries s = (YIntervalSeries) this.data.get(series);
        return s.getX(item);
    }

    /**
     * Returns the y-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getYValue(int series, int item) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[8]++;
        YIntervalSeries s = (YIntervalSeries) this.data.get(series);
        return s.getYValue(item);
    }

    /**
     * Returns the start y-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getStartYValue(int series, int item) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[9]++;
        YIntervalSeries s = (YIntervalSeries) this.data.get(series);
        return s.getYLowValue(item);
    }

    /**
     * Returns the end y-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The value.
     */
    public double getEndYValue(int series, int item) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[10]++;
        YIntervalSeries s = (YIntervalSeries) this.data.get(series);
        return s.getYHighValue(item);
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The y-value.
     */
    public Number getY(int series, int item) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[11]++;
        YIntervalSeries s = (YIntervalSeries) this.data.get(series);
        return new Double(s.getYValue(item));
    }

    /**
     * Returns the start x-value for an item within a series.  This method
     * maps directly to {@link #getX(int, int)}.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The x-value.
     */
    public Number getStartX(int series, int item) {
        return getX(series, item);
    }

    /**
     * Returns the end x-value for an item within a series.  This method
     * maps directly to {@link #getX(int, int)}.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The x-value.
     */
    public Number getEndX(int series, int item) {
        return getX(series, item);
    }

    /**
     * Returns the start y-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The start y-value.
     */
    public Number getStartY(int series, int item) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[12]++;
        YIntervalSeries s = (YIntervalSeries) this.data.get(series);
        return new Double(s.getYLowValue(item));
    }

    /**
     * Returns the end y-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The end y-value.
     */
    public Number getEndY(int series, int item) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[13]++;
        YIntervalSeries s = (YIntervalSeries) this.data.get(series);
        return new Double(s.getYHighValue(item));
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean. 
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[6]++;}
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof YIntervalSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.branches[8]++;}
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[16]++;
        YIntervalSeriesCollection that = (YIntervalSeriesCollection) obj;
        return ObjectUtilities.equal(this.data, that.data);
    }
    
    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[17]++;
        YIntervalSeriesCollection clone 
                = (YIntervalSeriesCollection) super.clone();
        clone.data = (List) ObjectUtilities.deepClone(this.data);
CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9.statements[18]++;
        return clone;
    }
    
}

class CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9 ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.YIntervalSeriesCollection.java";
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

  public CodeCoverCoverageCounter$1l182ulxcm7h21o32bzvtvsfg48cyy8bxsv40fc44c565i9 () {
    super("org.jfree.data.xy.YIntervalSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.jfree.data.xy.YIntervalSeriesCollection.java");
      for (int i = 1; i <= 18; i++) {
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

