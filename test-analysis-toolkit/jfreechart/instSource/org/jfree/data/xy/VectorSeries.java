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
 * VectorSeries.java
 * -----------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Renamed getDeltaXValue() --> getVectorXValue(), and likewise
 *               for getDeltaYValue() (DG);
 * 25-May-2007 : Added remove(int) and clear() methods, and moved from the
 *               experimental to the main source tree (DG);
 *               
 */

package org.jfree.data.xy;

import org.jfree.data.ComparableObjectItem;
import org.jfree.data.ComparableObjectSeries;
import org.jfree.data.general.SeriesChangeEvent;

/**
 * A list of (x,y, deltaX, deltaY) data items.
 *
 * @since 1.0.6
 * 
 * @see VectorSeriesCollection
 */
public class VectorSeries extends ComparableObjectSeries {
  static {
    CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.ping();
  }

    
    /**
     * Creates a new empty series.
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    public VectorSeries(Comparable key) {
        this(key, false, true);
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[1]++;
    }
    
    /**
     * Constructs a new series that contains no data.  You can specify 
     * whether or not duplicate x-values are allowed for the series.
     *
     * @param key  the series key (<code>null</code> not permitted).
     * @param autoSort  a flag that controls whether or not the items in the 
     *                  series are sorted.
     * @param allowDuplicateXValues  a flag that controls whether duplicate 
     *                               x-values are allowed.
     */
    public VectorSeries(Comparable key, boolean autoSort, 
            boolean allowDuplicateXValues) {
        super(key, autoSort, allowDuplicateXValues);
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[2]++;
    }
    
    /**
     * Adds a data item to the series.
     *
     * @param x  the x-value.
     * @param y  the y-value.
     * @param deltaX  the vector x.
     * @param deltaY  the vector y.
     */
    public void add(double x, double y, double deltaX, double deltaY) {
        super.add(new VectorDataItem(x, y, deltaX, deltaY), true);
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[3]++;
    }
    
    /**
     * Removes the item at the specified index and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * 
     * @param index  the index.
     * 
     * @return The item removed.
     */
    public ComparableObjectItem remove(int index) {
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[4]++;
        VectorDataItem result = (VectorDataItem) this.data.remove(index);
        fireSeriesChanged();
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[5]++;
        return result;
    }
    
    /**
     * Removes all data items from the series and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     */
    public void clear() {
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.data.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.branches[1]++;
            this.data.clear();
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[7]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[8]++;

        } else {
  CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.branches[2]++;}
    }

    /**
     * Returns the x-value for the specified item.
     *
     * @param index  the item index.
     *
     * @return The x-value.
     */
    public double getXValue(int index) {
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[9]++;
        VectorDataItem item = (VectorDataItem) this.getDataItem(index);
        return item.getXValue();
    }
    
    /**
     * Returns the y-value for the specified item.
     *
     * @param index  the item index.
     *
     * @return The y-value.
     */
    public double getYValue(int index) {
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[10]++;
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getYValue();
    }
    
    /**
     * Returns the x-component of the vector for an item in the series.
     * 
     * @param index  the item index.
     * 
     * @return The x-component of the vector.
     */
    public double getVectorXValue(int index) {
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[11]++;
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getVectorX();        
    }
    
    /**
     * Returns the y-component of the vector for an item in the series.
     * 
     * @param index  the item index.
     * 
     * @return The y-component of the vector.
     */
    public double getVectorYValue(int index) {
CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29.statements[12]++;
        VectorDataItem item = (VectorDataItem) getDataItem(index);
        return item.getVectorY();        
    }
    
    /**
     * Returns the data item at the specified index.
     * 
     * @param index  the item index.
     * 
     * @return The data item.
     */
    public ComparableObjectItem getDataItem(int index) {
        // overridden to make public
        return super.getDataItem(index);
    }
    
}

class CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29 ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.VectorSeries.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$10dei70a3rg2yqt0n9fjcg7ip29 () {
    super("org.jfree.data.xy.VectorSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.VectorSeries.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

