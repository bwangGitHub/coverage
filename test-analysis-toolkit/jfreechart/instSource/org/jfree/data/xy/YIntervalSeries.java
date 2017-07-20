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
 * --------------------
 * YIntervalSeries.java
 * --------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 * 20-Feb-2007 : Added getYHighValue() and getYLowValue() methods (DG);
 *
 */

package org.jfree.data.xy;

import org.jfree.data.ComparableObjectItem;
import org.jfree.data.ComparableObjectSeries;

/**
 * A list of (x, y, y-low, y-high) data items.
 *
 * @since 1.0.3
 *
 * @see YIntervalSeriesCollection
 */
public class YIntervalSeries extends ComparableObjectSeries {
  static {
    CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.ping();
  }

    
    /**
     * Creates a new empty series.  By default, items added to the series will 
     * be sorted into ascending order by x-value, and duplicate x-values will 
     * be allowed (these defaults can be modified with another constructor.
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    public YIntervalSeries(Comparable key) {
        this(key, true, true);
CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.statements[1]++;
    }
    
    /**
     * Constructs a new xy-series that contains no data.  You can specify 
     * whether or not duplicate x-values are allowed for the series.
     *
     * @param key  the series key (<code>null</code> not permitted).
     * @param autoSort  a flag that controls whether or not the items in the 
     *                  series are sorted.
     * @param allowDuplicateXValues  a flag that controls whether duplicate 
     *                               x-values are allowed.
     */
    public YIntervalSeries(Comparable key, boolean autoSort, 
            boolean allowDuplicateXValues) {
        super(key, autoSort, allowDuplicateXValues);
CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.statements[2]++;
    }
    
    /**
     * Adds a data item to the series.
     *
     * @param x  the x-value.
     * @param y  the y-value.
     * @param yLow  the lower bound of the y-interval.
     * @param yHigh  the upper bound of the y-interval.
     */
    public void add(double x, double y, double yLow, double yHigh) {
        super.add(new YIntervalDataItem(x, y, yLow, yHigh), true);
CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.statements[3]++;
    }
    
    /**
     * Returns the x-value for the specified item.
     *
     * @param index  the item index.
     *
     * @return The x-value (never <code>null</code>).
     */
    public Number getX(int index) {
CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.statements[4]++;
        YIntervalDataItem item = (YIntervalDataItem) getDataItem(index);
        return item.getX();
    }
    
    /**
     * Returns the y-value for the specified item.
     *
     * @param index  the item index.
     *
     * @return The y-value.
     */
    public double getYValue(int index) {
CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.statements[5]++;
        YIntervalDataItem item = (YIntervalDataItem) getDataItem(index);
        return item.getYValue();
    }
    
    /**
     * Returns the lower bound of the Y-interval for the specified item in the
     * series.
     * 
     * @param index  the item index.
     * 
     * @return The lower bound of the Y-interval.
     * 
     * @since 1.0.5
     */
    public double getYLowValue(int index) {
CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.statements[6]++;
        YIntervalDataItem item = (YIntervalDataItem) getDataItem(index);
        return item.getYLowValue();
    }
    
    /**
     * Returns the upper bound of the y-interval for the specified item in the
     * series.
     * 
     * @param index  the item index.
     * 
     * @return The upper bound of the y-interval.
     * 
     * @since 1.0.5
     */
    public double getYHighValue(int index) {
CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh.statements[7]++;
        YIntervalDataItem item = (YIntervalDataItem) getDataItem(index);
        return item.getYHighValue();
    }

    /**
     * Returns the data item at the specified index.
     * 
     * @param index  the item index.
     * 
     * @return The data item.
     */
    public ComparableObjectItem getDataItem(int index) {
        return super.getDataItem(index);
    }

}

class CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$afgw1h1w3lz14q8zry0uiyzc7ojgfoh () {
    super("org.jfree.data.xy.YIntervalSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.YIntervalSeries.java");
      for (int i = 1; i <= 7; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

