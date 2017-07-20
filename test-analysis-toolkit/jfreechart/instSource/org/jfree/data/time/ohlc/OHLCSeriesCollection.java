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
 * -------------------------
 * OHLCSeriesCollection.java
 * -------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 04-Dec-2006 : Version 1 (DG);
 *
 */

package org.jfree.data.time.ohlc;

import java.io.Serializable;
import java.util.List;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A collection of {@link OHLCSeries} objects.
 *
 * @since 1.0.4
 *
 * @see OHLCSeries
 */
public class OHLCSeriesCollection extends AbstractXYDataset
                                implements OHLCDataset, Serializable {
  static {
    CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.ping();
  }


    /** Storage for the data series. */
    private List data;
    
    private TimePeriodAnchor xPosition = TimePeriodAnchor.MIDDLE;
  {
    CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[1]++;
  }
    
    /** 
     * Creates a new instance of <code>OHLCSeriesCollection</code>. 
     */
    public OHLCSeriesCollection() {
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[2]++;
    }

    /**
     * Adds a series to the collection and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void addSeries(OHLCSeries series) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[1]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[2]++;}
        this.data.add(series);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[4]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[5]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[6]++;
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
    public OHLCSeries getSeries(int series) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[3]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[4]++;}
        return (OHLCSeries) this.data.get(series);
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
     * Returns the x-value for a time period.
     *
     * @param period  the time period (<code>null</code> not permitted).
     *
     * @return The x-value.
     */
    protected synchronized long getX(RegularTimePeriod period) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[8]++;
        long result = 0L;
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.START) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[5]++;
            result = period.getFirstMillisecond();
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[10]++;

        }
        else {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[6]++;
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[7]++;
            result = period.getMiddleMillisecond();
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[12]++;

        }
        else {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[8]++;
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[13]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.END) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[9]++;
            result = period.getLastMillisecond();
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[14]++;
 
        } else {
  CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[10]++;}
}
}
        return result;
    }

    /**
     * Returns the x-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The x-value.
     */
    public double getXValue(int series, int item) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[15]++;
        OHLCSeries s = (OHLCSeries) this.data.get(series);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[16]++;
        OHLCItem di = (OHLCItem) s.getDataItem(item);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[17]++;
        RegularTimePeriod period = di.getPeriod();
        return getX(period);
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
        return new Double(getXValue(series, item));
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
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[18]++;
        OHLCSeries s = (OHLCSeries) this.data.get(series);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[19]++;
        OHLCItem di = (OHLCItem) s.getDataItem(item);
        return new Double(di.getYValue());
    }

    /**
     * Returns the open-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The open-value.
     */
    public double getOpenValue(int series, int item) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[20]++;
        OHLCSeries s = (OHLCSeries) this.data.get(series);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[21]++;
        OHLCItem di = (OHLCItem) s.getDataItem(item);
        return di.getOpenValue();
    }
    
    /**
     * Returns the open-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The open-value.
     */
    public Number getOpen(int series, int item) {
        return new Double(getOpenValue(series, item));
    }
    
    /**
     * Returns the close-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The close-value.
     */
    public double getCloseValue(int series, int item) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[22]++;
        OHLCSeries s = (OHLCSeries) this.data.get(series);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[23]++;
        OHLCItem di = (OHLCItem) s.getDataItem(item);
        return di.getCloseValue();
    }
    
    /**
     * Returns the close-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The close-value.
     */
    public Number getClose(int series, int item) {
        return new Double(getCloseValue(series, item));
    }
    
    /**
     * Returns the high-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The high-value.
     */
    public double getHighValue(int series, int item) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[24]++;
        OHLCSeries s = (OHLCSeries) this.data.get(series);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[25]++;
        OHLCItem di = (OHLCItem) s.getDataItem(item);
        return di.getHighValue();
    }
    
    /**
     * Returns the high-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The high-value.
     */
    public Number getHigh(int series, int item) {
        return new Double(getHighValue(series, item));
    }
    
    /**
     * Returns the low-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The low-value.
     */
    public double getLowValue(int series, int item) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[26]++;
        OHLCSeries s = (OHLCSeries) this.data.get(series);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[27]++;
        OHLCItem di = (OHLCItem) s.getDataItem(item);
        return di.getLowValue();
    }
    
    /**
     * Returns the low-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The low-value.
     */
    public Number getLow(int series, int item) {
        return new Double(getLowValue(series, item));
    }
    
    public Number getVolume(int series, int item) {
        return null;
    }
    
    public double getVolumeValue(int series, int item) {
        return Double.NaN;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean. 
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[12]++;}
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof OHLCSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.branches[14]++;}
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[30]++;
        OHLCSeriesCollection that = (OHLCSeriesCollection) obj;
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
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[31]++;
        OHLCSeriesCollection clone 
                = (OHLCSeriesCollection) super.clone();
        clone.data = (List) ObjectUtilities.deepClone(this.data);
CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d.statements[32]++;
        return clone;
    }
    
}

class CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.time.ohlc.OHLCSeriesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$3lxuhkopk9gh0qai56e274q3flm49eg6b3pj59d () {
    super("org.jfree.data.time.ohlc.OHLCSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.ohlc.OHLCSeriesCollection.java");
      for (int i = 1; i <= 32; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

