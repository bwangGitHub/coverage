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
 * CategoryTableXYDataset.java
 * ---------------------------
 * (C) Copyright 2004, 2005, 2007, by Andreas Schroeder and Contributors.
 *
 * Original Author:  Andreas Schroeder;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 31-Mar-2004 : Version 1 (AS);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched interval access method names (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 17-Nov-2004 : Updates required by changes to DomainInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 05-Oct-2005 : Made the interval delegate a dataset change listener (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.data.xy;

import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;

/**
 * An implementation variant of the {@link TableXYDataset} where every series 
 * shares the same x-values (required for generating stacked area charts). 
 * This implementation uses a {@link DefaultKeyedValues2D} Object as backend 
 * implementation and is hence more "category oriented" than the {@link 
 * DefaultTableXYDataset} implementation.
 * <p>
 * This implementation provides no means to remove data items yet.
 * This is due to the lack of such facility in the DefaultKeyedValues2D class.
 * <p>
 * This class also implements the {@link IntervalXYDataset} interface, but this
 * implementation is provisional. 
 */
public class CategoryTableXYDataset extends AbstractIntervalXYDataset
                                    implements TableXYDataset, 
                                               IntervalXYDataset, 
                                               DomainInfo {
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.ping();
  }

    
    /**
     * The backing data structure.
     */
    private DefaultKeyedValues2D values;
    
    /** A delegate for controlling the interval width. */
    private IntervalXYDelegate intervalDelegate;

    /**
     * Creates a new empty CategoryTableXYDataset.
     */
    public CategoryTableXYDataset() {
        this.values = new DefaultKeyedValues2D(true);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[1]++;
        this.intervalDelegate = new IntervalXYDelegate(this);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[2]++;
        addChangeListener(this.intervalDelegate);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[3]++;
    }

    /**
     * Adds a data item to this dataset and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     * 
     * @param x  the x value.
     * @param y  the y value.
     * @param seriesName  the name of the series to add the data item.
     */
    public void add(double x, double y, String seriesName) {
        add(new Double(x), new Double(y), seriesName, true);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[4]++;
    }
    
    /**
     * Adds a data item to this dataset and, if requested, sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @param x  the x value.
     * @param y  the y value.
     * @param seriesName  the name of the series to add the data item.
     * @param notify  notify listeners?
     */
    public void add(Number x, Number y, String seriesName, boolean notify) {
        this.values.addValue(y, (Comparable) x, seriesName);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[5]++;
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[1]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[7]++;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[2]++;}
    }

    /**
     * Removes a value from the dataset.
     * 
     * @param x  the x-value.
     * @param seriesName  the series name.
     */
    public void remove(double x, String seriesName) {
        remove(new Double(x), seriesName, true);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[8]++;
    }
    
    /**
     * Removes an item from the dataset.
     * 
     * @param x  the x-value.
     * @param seriesName  the series name.
     * @param notify  notify listeners?
     */
    public void remove(Number x, String seriesName, boolean notify) {
        this.values.removeValue((Comparable) x, seriesName);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[9]++;
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[3]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[11]++;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[4]++;}
    }


    /**
     * Returns the number of series in the collection.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.values.getColumnCount();
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The key for a series.
     */
    public Comparable getSeriesKey(int series) {
        return this.values.getColumnKey(series);
    }

    /**
     * Returns the number of x values in the dataset.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.values.getRowCount();
    }

    /**
     * Returns the number of items in the specified series.
     * Returns the same as {@link CategoryTableXYDataset#getItemCount()}.
     *
     * @param series  the series index (zero-based).
     *
     * @return The item count.
     */
    public int getItemCount(int series) {
        return getItemCount();  // all series have the same number of items in 
                                // this dataset
    }

    /**
     * Returns the x-value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getX(int series, int item) {
        return (Number) this.values.getRowKey(item);
    }

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The starting X value.
     */
    public Number getStartX(int series, int item) {
        return this.intervalDelegate.getStartX(series, item);
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The ending X value.
     */
    public Number getEndX(int series, int item) {
        return this.intervalDelegate.getEndX(series, item);
    }

    /**
     * Returns the y-value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The y value (possibly <code>null</code>).
     */
    public Number getY(int series, int item) {
        return this.values.getValue(item, series);
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The starting Y value.
     */
    public Number getStartY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The ending Y value.
     */
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }
    
    /**
     * Returns the minimum x-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     * 
     * @return The minimum value.
     */
    public double getDomainLowerBound(boolean includeInterval) {
        return this.intervalDelegate.getDomainLowerBound(includeInterval);
    }

    /**
     * Returns the maximum x-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     * 
     * @return The maximum value.
     */
    public double getDomainUpperBound(boolean includeInterval) {
        return this.intervalDelegate.getDomainUpperBound(includeInterval);
    }

    /**
     * Returns the range of the values in this dataset's domain.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     * 
     * @return The range.
     */
    public Range getDomainBounds(boolean includeInterval) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[5]++;
            return this.intervalDelegate.getDomainBounds(includeInterval);

        }
        else {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[6]++;
            return DatasetUtilities.iterateDomainBounds(this, includeInterval);
        }
    }
    
    /**
     * Returns the interval position factor. 
     * 
     * @return The interval position factor.
     */
    public double getIntervalPositionFactor() {
        return this.intervalDelegate.getIntervalPositionFactor();
    }

    /**
     * Sets the interval position factor. Must be between 0.0 and 1.0 inclusive.
     * If the factor is 0.5, the gap is in the middle of the x values. If it
     * is lesser than 0.5, the gap is farther to the left and if greater than
     * 0.5 it gets farther to the right.
     *  
     * @param d  the new interval position factor.
     */
    public void setIntervalPositionFactor(double d) {
        this.intervalDelegate.setIntervalPositionFactor(d);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[13]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[14]++;
    }

    /**
     * Returns the full interval width. 
     * 
     * @return The interval width to use.
     */
    public double getIntervalWidth() {
        return this.intervalDelegate.getIntervalWidth();
    }

    /**
     * Sets the interval width to a fixed value, and sends a 
     * {@link DatasetChangeEvent} to all registered listeners. 
     * 
     * @param d  the new interval width (must be > 0).
     */
    public void setIntervalWidth(double d) {
        this.intervalDelegate.setFixedIntervalWidth(d);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[15]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[16]++;
    }

    /**
     * Returns whether the interval width is automatically calculated or not.
     * 
     * @return whether the width is automatically calculated or not.
     */
    public boolean isAutoWidth() {
        return this.intervalDelegate.isAutoWidth();
    }

    /**
     * Sets the flag that indicates whether the interval width is automatically
     * calculated or not. 
     * 
     * @param b  the flag.
     */
    public void setAutoWidth(boolean b) {
        this.intervalDelegate.setAutoWidth(b);
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[17]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[18]++;
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryTableXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[8]++;}
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[20]++;
        CategoryTableXYDataset that = (CategoryTableXYDataset) obj;
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.intervalDelegate.equals(that.intervalDelegate)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[10]++;}
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.values.equals(that.values)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh.branches[12]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.CategoryTableXYDataset.java";
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

  public CodeCoverCoverageCounter$4b4elyw2od3fmiraktg8537rzega9watqymj3gulwh () {
    super("org.jfree.data.xy.CategoryTableXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
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
    log.startNamedSection("org.jfree.data.xy.CategoryTableXYDataset.java");
      for (int i = 1; i <= 22; i++) {
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

