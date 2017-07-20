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
 * --------------------------
 * DefaultHighLowDataset.java
 * --------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 21-Mar-2002 : Version 1 (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 06-May-2004 : Now extends AbstractXYDataset and added new methods from 
 *               HighLowDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 28-Nov-2006 : Added equals() method override (DG);
 *
 */

package org.jfree.data.xy;

import java.util.Arrays;
import java.util.Date;

/**
 * A simple implementation of the {@link OHLCDataset} interface.  See also
 * the {@link DefaultOHLCDataset} class, which provides another implementation
 * that is very similar.
 */
public class DefaultHighLowDataset extends AbstractXYDataset 
                                   implements OHLCDataset {
  static {
    CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.ping();
  }


    /** The series key. */
    private Comparable seriesKey;

    /** Storage for the dates. */
    private Date[] date;

    /** Storage for the high values. */
    private Number[] high;

    /** Storage for the low values. */
    private Number[] low;

    /** Storage for the open values. */
    private Number[] open;

    /** Storage for the close values. */
    private Number[] close;

    /** Storage for the volume values. */
    private Number[] volume;

    /**
     * Constructs a new high/low/open/close dataset.
     * <p>
     * The current implementation allows only one series in the dataset.
     * This may be extended in a future version.
     *
     * @param seriesKey  the key for the series (<code>null</code> not 
     *     permitted).
     * @param date  the dates (<code>null</code> not permitted).
     * @param high  the high values (<code>null</code> not permitted).
     * @param low  the low values (<code>null</code> not permitted).
     * @param open  the open values (<code>null</code> not permitted).
     * @param close  the close values (<code>null</code> not permitted).
     * @param volume  the volume values (<code>null</code> not permitted).
     */
    public DefaultHighLowDataset(Comparable seriesKey, Date[] date, 
            double[] high, double[] low, double[] open, double[] close,
            double[] volume) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((seriesKey == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[1]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[2]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[3]++;
            throw new IllegalArgumentException("Null 'date' argument.");

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[4]++;}
        this.seriesKey = seriesKey;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[3]++;
        this.date = date;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[4]++;
        this.high = createNumberArray(high);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[5]++;
        this.low = createNumberArray(low);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[6]++;
        this.open = createNumberArray(open);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[7]++;
        this.close = createNumberArray(close);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[8]++;
        this.volume = createNumberArray(volume);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[9]++;

    }

    /**
     * Returns the key for the series stored in this dataset.
     *
     * @param series  the index of the series (ignored, this dataset supports 
     *     only one series and this method always returns the key for series 0).
     * 
     * @return The series key (never <code>null</code>).
     */
    public Comparable getSeriesKey(int series) {
        return this.seriesKey;
    }
    
    /**
     * Returns the x-value for one item in a series.  The value returned is a 
     * <code>Long</code> instance generated from the underlying 
     * <code>Date</code> object.  To avoid generating a new object instance,
     * you might prefer to call {@link #getXValue(int, int)}.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value.
     * 
     * @see #getXValue(int, int)
     * @see #getXDate(int, int)
     */
    public Number getX(int series, int item) {
        return new Long(this.date[item].getTime());
    }

    /**
     * Returns the x-value for one item in a series, as a Date.
     * <p>
     * This method is provided for convenience only.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value as a Date.
     * 
     * @see #getX(int, int)
     */
    public Date getXDate(int series, int item) {
        return this.date[item];
    }

    /**
     * Returns the y-value for one item in a series.
     * <p>
     * This method (from the {@link XYDataset} interface) is mapped to the 
     * {@link #getCloseValue(int, int)} method.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The y-value.
     * 
     * @see #getYValue(int, int)
     */
    public Number getY(int series, int item) {
        return getClose(series, item);
    }

    /**
     * Returns the high-value for one item in a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The high-value.
     * 
     * @see #getHighValue(int, int)
     */
    public Number getHigh(int series, int item) {
        return this.high[item];
    }

    /**
     * Returns the high-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The high-value.
     * 
     * @see #getHigh(int, int)
     */
    public double getHighValue(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[10]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[11]++;
        Number high = getHigh(series, item);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((high != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[5]++;
            result = high.doubleValue();
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[13]++;
   
        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[6]++;}
        return result;   
    }

    /**
     * Returns the low-value for one item in a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The low-value.
     * 
     * @see #getLowValue(int, int)
     */
    public Number getLow(int series, int item) {
        return this.low[item];
    }

    /**
     * Returns the low-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The low-value.
     * 
     * @see #getLow(int, int)
     */
    public double getLowValue(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[14]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[15]++;
        Number low = getLow(series, item);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((low != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[7]++;
            result = low.doubleValue();
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[17]++;
   
        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[8]++;}
        return result;   
    }

    /**
     * Returns the open-value for one item in a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The open-value.
     * 
     * @see #getOpenValue(int, int)
     */
    public Number getOpen(int series, int item) {
        return this.open[item];
    }

    /**
     * Returns the open-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The open-value.
     * 
     * @see #getOpen(int, int)
     */
    public double getOpenValue(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[18]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[19]++;
        Number open = getOpen(series, item);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((open != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[9]++;
            result = open.doubleValue();
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[21]++;
   
        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[10]++;}
        return result;   
    }

    /**
     * Returns the close-value for one item in a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The close-value.
     * 
     * @see #getCloseValue(int, int)
     */
    public Number getClose(int series, int item) {
        return this.close[item];
    }

    /**
     * Returns the close-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The close-value.
     * 
     * @see #getClose(int, int)
     */
    public double getCloseValue(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[22]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[23]++;
        Number close = getClose(series, item);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((close != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[11]++;
            result = close.doubleValue();
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[25]++;
   
        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[12]++;}
        return result;   
    }

    /**
     * Returns the volume-value for one item in a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The volume-value.
     * 
     * @see #getVolumeValue(int, int)
     */
    public Number getVolume(int series, int item) {
        return this.volume[item];
    }

    /**
     * Returns the volume-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The volume-value.
     * 
     * @see #getVolume(int, int)
     */
    public double getVolumeValue(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[26]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[27]++;
        Number volume = getVolume(series, item);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((volume != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[13]++;
            result = volume.doubleValue();
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[29]++;
   
        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[14]++;}
        return result;   
    }

    /**
     * Returns the number of series in the dataset.
     * <p>
     * This implementation only allows one series.
     *
     * @return The number of series.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param series  the index (zero-based) of the series.
     *
     * @return The number of items in the specified series.
     */
    public int getItemCount(int series) {
        return this.date.length;
    }

    /**
     * Tests this dataset for equality with an arbitrary instance.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[16]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultHighLowDataset) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[18]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[32]++;
        DefaultHighLowDataset that = (DefaultHighLowDataset) obj;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.seriesKey.equals(that.seriesKey)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[20]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[34]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.date, that.date)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[22]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[35]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.open, that.open)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[24]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.high, that.high)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[26]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[37]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.low, that.low)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[28]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.close, that.close)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[30]++;}
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[39]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.volume, that.volume)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.branches[32]++;}
        return true;    
    }
    
    /**
     * Constructs an array of Number objects from an array of doubles.
     *
     * @param data  the double values to convert (<code>null</code> not 
     *     permitted).
     *
     * @return The data as an array of Number objects.
     */
    public static Number[] createNumberArray(double[] data) {
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[40]++;
        Number[] result = new Number[data.length];
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[41]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.loops[1]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < data.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.loops[1]--;
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.loops[2]--;
  CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.loops[3]++;
}
            result[i] = new Double(data[i]);
CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh.statements[42]++;
        }
        return result;
    }

}

class CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.DefaultHighLowDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$m553c7l0laoy5nyhb9qtjueu0g1yatgmzcdad4kh () {
    super("org.jfree.data.xy.DefaultHighLowDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.DefaultHighLowDataset.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

