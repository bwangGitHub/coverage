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
 * -------------------------------
 * TimePeriodValuesCollection.java
 * -------------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Apr-2003 : Version 1 (DG);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 06-Oct-2004 : Updated for changes in DomainInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-Oct-2006 : Deprecated get/setDomainIsPointsInTime() (DG);
 * 11-Jun-2007 : Fixed bug in getDomainBounds() method, and changed default
 *               value for domainIsPointsInTime to false (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A collection of {@link TimePeriodValues} objects.
 * <P>
 * This class implements the {@link org.jfree.data.xy.XYDataset} interface, as 
 * well as the extended {@link IntervalXYDataset} interface.  This makes it a 
 * convenient dataset for use with the {@link org.jfree.chart.plot.XYPlot} 
 * class.
 */
public class TimePeriodValuesCollection extends AbstractIntervalXYDataset
        implements IntervalXYDataset, DomainInfo, Serializable {
  static {
    CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3077934065236454199L;
  static {
    CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[1]++;
  }
    
    /** Storage for the time series. */
    private List data;

    /** 
     * The position within a time period to return as the x-value (START, 
     * MIDDLE or END). 
     */
    private TimePeriodAnchor xPosition;
    
    /**
     * A flag that indicates that the domain is 'points in time'.  If this 
     * flag is true, only the x-value is used to determine the range of values 
     * in the domain, the start and end x-values are ignored.
     */
    private boolean domainIsPointsInTime;

    /**
     * Constructs an empty dataset.
     */
    public TimePeriodValuesCollection() {
        this((TimePeriodValues) null);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[2]++;
    }

    /**
     * Constructs a dataset containing a single series.  Additional series can 
     * be added.
     *
     * @param series  the series (<code>null</code> ignored).
     */
    public TimePeriodValuesCollection(TimePeriodValues series) {
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[3]++;
        this.xPosition = TimePeriodAnchor.MIDDLE;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[4]++;
        this.domainIsPointsInTime = false;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[5]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[1]++;
            this.data.add(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[7]++;
            series.addChangeListener(this);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[8]++;

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[2]++;}
    }

    /**
     * Returns the position of the X value within each time period.
     * 
     * @return The position (never <code>null</code>).
     * 
     * @see #setXPosition(TimePeriodAnchor)
     */
    public TimePeriodAnchor getXPosition() {
        return this.xPosition;
    }

    /**
     * Sets the position of the x axis within each time period.
     * 
     * @param position  the position (<code>null</code> not permitted).
     * 
     * @see #getXPosition()
     */
    public void setXPosition(TimePeriodAnchor position) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[3]++;
            throw new IllegalArgumentException("Null 'position' argument.");

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[4]++;}
        this.xPosition = position;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[10]++;
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
     * Returns a series.
     *
     * @param series  the index of the series (zero-based).
     *
     * @return The series.
     */
    public TimePeriodValues getSeries(int series) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[5]++;
            throw new IllegalArgumentException("Index 'series' out of range.");

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[6]++;}
        return (TimePeriodValues) this.data.get(series);
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the index of the series (zero-based).
     *
     * @return The key for a series.
     */
    public Comparable getSeriesKey(int series) {
        // defer argument checking
        return getSeries(series).getKey();
    }

    /**
     * Adds a series to the collection.  A 
     * {@link org.jfree.data.general.DatasetChangeEvent} is sent to all 
     * registered listeners.
     *
     * @param series  the time series.
     */
    public void addSeries(TimePeriodValues series) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[7]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[8]++;}

        this.data.add(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[13]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[14]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[15]++;

    }

    /**
     * Removes the specified series from the collection.
     *
     * @param series  the series to remove (<code>null</code> not permitted).
     */
    public void removeSeries(TimePeriodValues series) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[9]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[10]++;}
        this.data.remove(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[17]++;
        series.removeChangeListener(this);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[18]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[19]++;

    }

    /**
     * Removes a series from the collection.
     *
     * @param index  the series index (zero-based).
     */
    public void removeSeries(int index) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[20]++;
        TimePeriodValues series = getSeries(index);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((series != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[11]++;
            removeSeries(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[22]++;

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[12]++;}
    }

    /**
     * Returns the number of items in the specified series.
     * <P>
     * This method is provided for convenience.
     *
     * @param series  the index of the series of interest (zero-based).
     *
     * @return The number of items in the specified series.
     */
    public int getItemCount(int series) {
        return getSeries(series).getItemCount();
    }

    /**
     * Returns the x-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value for the specified series and item.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[23]++;
        TimePeriodValues ts = (TimePeriodValues) this.data.get(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[24]++;
        TimePeriodValue dp = ts.getDataItem(item);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[25]++;
        TimePeriod period = dp.getPeriod();
        return new Long(getX(period));
    }

    /**
     * Returns the x-value for a time period.
     *
     * @param period  the time period.
     *
     * @return The x-value.
     */
    private long getX(TimePeriod period) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.START) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[13]++;
            return period.getStart().getTime();

        }
        else {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[14]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[27]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[15]++;
            return period.getStart().getTime() 
                / 2 + period.getEnd().getTime() / 2;

        }
        else {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[16]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[28]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.END) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[17]++;
            return period.getEnd().getTime();

        }
        else {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[18]++;
            throw new IllegalStateException("TimePeriodAnchor unknown.");
        }
}
}

    }

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The starting X value for the specified series and item.
     */
    public Number getStartX(int series, int item) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[29]++;
        TimePeriodValues ts = (TimePeriodValues) this.data.get(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[30]++;
        TimePeriodValue dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getStart().getTime());
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The ending X value for the specified series and item.
     */
    public Number getEndX(int series, int item) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[31]++;
        TimePeriodValues ts = (TimePeriodValues) this.data.get(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[32]++;
        TimePeriodValue dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getEnd().getTime());
    }

    /**
     * Returns the y-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The y-value for the specified series and item.
     */
    public Number getY(int series, int item) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[33]++;
        TimePeriodValues ts = (TimePeriodValues) this.data.get(series);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[34]++;
        TimePeriodValue dp = ts.getDataItem(item);
        return dp.getValue();
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The starting Y value for the specified series and item.
     */
    public Number getStartY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The ending Y value for the specified series and item.
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
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[35]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[36]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[19]++;
            result = r.getLowerBound();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[38]++;

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[20]++;}
        return result;
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
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[39]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[40]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[41]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[21]++;
            result = r.getUpperBound();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[42]++;

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[22]++;}
        return result;
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
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[43]++;
        boolean interval = includeInterval || this.domainIsPointsInTime;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[44]++;
        Range result = null;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[45]++;
        Range temp = null;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[46]++;
        Iterator iterator = this.data.iterator();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[47]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
        while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.loops[1]--;
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.loops[2]--;
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.loops[3]++;
}
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[48]++;
            TimePeriodValues series = (TimePeriodValues) iterator.next();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[49]++;
            int count = series.getItemCount();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[50]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[23]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[51]++;
                TimePeriod start = series.getTimePeriod(
                        series.getMinStartIndex());
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[52]++;
                TimePeriod end = series.getTimePeriod(series.getMaxEndIndex());
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((interval) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[25]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.START) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[27]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[55]++;
                        TimePeriod maxStart = series.getTimePeriod(
                                series.getMaxStartIndex());
                        temp = new Range(start.getStart().getTime(), 
                                maxStart.getStart().getTime());
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[56]++;

                    }
                    else {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[28]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[57]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[29]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[58]++;
                        TimePeriod minMiddle = series.getTimePeriod(
                                series.getMinMiddleIndex());
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[59]++;
                        long s1 = minMiddle.getStart().getTime();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[60]++;
                        long e1 = minMiddle.getEnd().getTime();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[61]++;
                        TimePeriod maxMiddle = series.getTimePeriod(
                                series.getMaxMiddleIndex());
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[62]++;
                        long s2 = maxMiddle.getStart().getTime();
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[63]++;
                        long e2 = maxMiddle.getEnd().getTime();
                        temp = new Range(s1 + (e1 - s1) / 2, 
                                s2 + (e2 - s2) / 2);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[64]++;

                    }
                    else {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[30]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[65]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.END) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[31]++;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[66]++;
                        TimePeriod minEnd = series.getTimePeriod(
                                series.getMinEndIndex());
                        temp = new Range(minEnd.getEnd().getTime(), 
                                end.getEnd().getTime());
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[67]++;

                    } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[32]++;}
}
}

                }
                else {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[26]++;
                    temp = new Range(start.getStart().getTime(), 
                            end.getEnd().getTime());
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[68]++;
                }
                result = Range.combine(result, temp);
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[69]++;

            } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[24]++;}
        }
        return result;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[70]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[33]++;
            return true;

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[34]++;}
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj instanceof TimePeriodValuesCollection) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[36]++;}
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[72]++;
        TimePeriodValuesCollection that = (TimePeriodValuesCollection) obj;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[73]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.domainIsPointsInTime != that.domainIsPointsInTime) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[37]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[38]++;}
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[74]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.xPosition != that.xPosition) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[39]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[40]++;}
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[75]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.branches[42]++;}
        return true;   
    }

    // --- DEPRECATED METHODS -------------------------------------------------
    
    /**
     * Returns a flag that controls whether the domain is treated as 'points 
     * in time'.  This flag is used when determining the max and min values for 
     * the domain.  If true, then only the x-values are considered for the max 
     * and min values.  If false, then the start and end x-values will also be 
     * taken into consideration
     *
     * @return The flag.
     * 
     * @deprecated This flag is no longer used by JFreeChart (as of version 
     *     1.0.3).
     */
    public boolean getDomainIsPointsInTime() {
        return this.domainIsPointsInTime;
    }

    /**
     * Sets a flag that controls whether the domain is treated as 'points in 
     * time', or time periods.
     *
     * @param flag  the new value of the flag.
     * 
     * @deprecated This flag is no longer used by JFreeChart (as of version 
     *     1.0.3).
     */
    public void setDomainIsPointsInTime(boolean flag) {
        this.domainIsPointsInTime = flag;
CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t.statements[76]++;
    }

}

class CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t ());
  }
    public static long[] statements = new long[77];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimePeriodValuesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 22; i++) {
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

  public CodeCoverCoverageCounter$anfiki1e4i0dp74i3p9a5os1mupppxhaceboy1sr855x8p5t () {
    super("org.jfree.data.time.TimePeriodValuesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 76; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.TimePeriodValuesCollection.java");
      for (int i = 1; i <= 76; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
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

