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
 * XYBarDataset.java
 * -----------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 02-Mar-2004 : Version 1 (DG);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 25-Jan-2007 : Added some accessor methods, plus new equals() and clone()
 *               overrides (DG);
 * 30-Jan-2007 : Added method overrides to prevent unnecessary object 
 *               creation (DG);
 *
 */

package org.jfree.data.xy;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.util.PublicCloneable;

/**
 * A dataset wrapper class that converts a standard {@link XYDataset} into an
 * {@link IntervalXYDataset} suitable for use in creating XY bar charts.
 */
public class XYBarDataset extends AbstractIntervalXYDataset
                          implements IntervalXYDataset, DatasetChangeListener {
  static {
    CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.ping();
  }

    
    /** The underlying dataset. */
    private XYDataset underlying;
    
    /** The bar width. */
    private double barWidth;
    
    /**
     * Creates a new dataset.
     * 
     * @param underlying  the underlying dataset (<code>null</code> not 
     *     permitted).
     * @param barWidth  the width of the bars.
     */
    public XYBarDataset(XYDataset underlying, double barWidth) {
        this.underlying = underlying;
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[1]++;   
        this.underlying.addChangeListener(this);
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[2]++;
        this.barWidth = barWidth;
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[3]++;
    }
    
    /**
     * Returns the underlying dataset that was specified via the constructor.
     * 
     * @return The underlying dataset (never <code>null</code>).
     * 
     * @since 1.0.4
     */
    public XYDataset getUnderlyingDataset() {
        return this.underlying;
    }

    /**
     * Returns the bar width.
     * 
     * @return The bar width.
     * 
     * @see #setBarWidth(double)
     * @since 1.0.4
     */
    public double getBarWidth() {
        return this.barWidth;
    }
    
    /**
     * Sets the bar width and sends a {@link DatasetChangeEvent} to all 
     * registered listeners.
     * 
     * @param barWidth  the bar width.
     * 
     * @see #getBarWidth()
     * @since 1.0.4
     */
    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[4]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[5]++;
    }
    
    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.underlying.getSeriesCount();   
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     *
     * @return The series key.
     */
    public Comparable getSeriesKey(int series) {
        return this.underlying.getSeriesKey(series);   
    }
    
    /**
     * Returns the number of items in a series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The item count.
     */
    public int getItemCount(int series) {
        return this.underlying.getItemCount(series);   
    }

    /**
     * Returns the x-value for an item within a series. 
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The x-value.
     * 
     * @see #getXValue(int, int)
     */
    public Number getX(int series, int item) {
        return this.underlying.getX(series, item);   
    }

    /**
     * Returns the x-value (as a double primitive) for an item within a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     * 
     * @see #getX(int, int)
     */
    public double getXValue(int series, int item) {
        return this.underlying.getXValue(series, item);   
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The y-value (possibly <code>null</code>).
     * 
     * @see #getYValue(int, int)
     */
    public Number getY(int series, int item) {
        return this.underlying.getY(series, item);   
    }

    /**
     * Returns the y-value (as a double primitive) for an item within a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     * 
     * @see #getY(int, int)
     */
    public double getYValue(int series, int item) {
        return this.underlying.getYValue(series, item);  
    }
    
    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getStartX(int series, int item) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[6]++;
        Number result = null;
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[7]++;
        Number xnum = this.underlying.getX(series, item);
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((xnum != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[1]++;
             result = new Double(xnum.doubleValue() - this.barWidth / 2.0);
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[9]++;
   
        } else {
  CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[2]++;}
        return result;   
    }

    /**
     * Returns the starting x-value (as a double primitive) for an item within 
     * a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     * 
     * @see #getXValue(int, int)
     */
    public double getStartXValue(int series, int item) {
        return getXValue(series, item) - this.barWidth / 2.0;   
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getEndX(int series, int item) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[10]++;
        Number result = null;
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[11]++;
        Number xnum = this.underlying.getX(series, item);
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xnum != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[3]++;
             result = new Double(xnum.doubleValue() + this.barWidth / 2.0);
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[13]++;
   
        } else {
  CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[4]++;}
        return result;   
    }

    /**
     * Returns the ending x-value (as a double primitive) for an item within 
     * a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     * 
     * @see #getXValue(int, int)
     */
    public double getEndXValue(int series, int item) {
        return getXValue(series, item) + this.barWidth / 2.0;   
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getStartY(int series, int item) {
        return this.underlying.getY(series, item);   
    }
    
    /**
     * Returns the starting y-value (as a double primitive) for an item within 
     * a series.  
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     * 
     * @see #getYValue(int, int)
     */
    public double getStartYValue(int series, int item) {
        return getYValue(series, item);   
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getEndY(int series, int item) {
        return this.underlying.getY(series, item);   
    }

    /**
     * Returns the ending y-value (as a double primitive) for an item within 
     * a series.  
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     * 
     * @see #getYValue(int, int)
     */
    public double getEndYValue(int series, int item) {
        return getYValue(series, item);   
    }

    /**
     * Receives notification of an dataset change event.
     *
     * @param event  information about the event.
     */
    public void datasetChanged(DatasetChangeEvent event) {
        this.notifyListeners(event);
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[14]++;
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[6]++;}
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof XYBarDataset) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[8]++;}
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[17]++;
        XYBarDataset that = (XYBarDataset) obj;
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.underlying.equals(that.underlying)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[10]++;}
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.barWidth != that.barWidth) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[12]++;}
        return true;
    }
    
    /**
     * Returns an independent copy of the dataset.  Note that:
     * <ul>
     * <li>the underlying dataset is only cloned if it implements the 
     * {@link PublicCloneable} interface;</li>
     * <li>the listeners registered with this dataset are not carried over to
     * the cloned dataset.</li>
     * </ul>
     * 
     * @return An independent copy of the dataset.
     * 
     * @throws CloneNotSupportedException if the dataset cannot be cloned for 
     *         any reason.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[20]++;
        XYBarDataset clone = (XYBarDataset) super.clone();
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.underlying instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[13]++;
            clone.underlying 
                    = (XYDataset) ((PublicCloneable) this.underlying).clone();
CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.statements[22]++;

        } else {
  CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h.branches[14]++;}
        return clone;
    }

}

class CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.XYBarDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$116zvg7hz3wuwo2z9d0xr8q1h0h () {
    super("org.jfree.data.xy.XYBarDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
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
    log.startNamedSection("org.jfree.data.xy.XYBarDataset.java");
      for (int i = 1; i <= 22; i++) {
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

