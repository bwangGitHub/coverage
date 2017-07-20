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
 * -----------------------
 * TimeTableXYDataset.java
 * -----------------------
 * (C) Copyright 2004, 2005, 2007, by Andreas Schroeder and Contributors.
 *
 * Original Author:  Andreas Schroeder;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Rob Eden;
 *
 * Changes
 * -------
 * 01-Apr-2004 : Version 1 (AS);
 * 05-May-2004 : Now implements AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 15-Sep-2004 : Added getXPosition(), setXPosition(), equals() and 
 *               clone() (DG);
 * 17-Nov-2004 : Updated methods for changes in DomainInfo interface (DG);
 * 25-Nov-2004 : Added getTimePeriod(int) method (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 27-Jan-2005 : Modified to use TimePeriod rather than RegularTimePeriod (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 25-Jul-2007 : Added clear() method by Rob Eden, see patch 1752205 (DG);
 *
 */

package org.jfree.data.time;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.util.PublicCloneable;

/**
 * A dataset for regular time periods that implements the 
 * {@link TableXYDataset} interface.
 * 
 * @see org.jfree.data.xy.TableXYDataset
 */
public class TimeTableXYDataset extends AbstractIntervalXYDataset
                                implements Cloneable, PublicCloneable,
                                           IntervalXYDataset, 
                                           DomainInfo, 
                                           TableXYDataset {
  static {
    CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.ping();
  }

    
    /**
     * The data structure to store the values.  Each column represents
     * a series (elsewhere in JFreeChart rows are typically used for series,
     * but it doesn't matter that much since this data structure is private 
     * and symmetrical anyway), each row contains values for the same 
     * {@link RegularTimePeriod} (the rows are sorted into ascending order).
     */
    private DefaultKeyedValues2D values;
    
    /**
     * A flag that indicates that the domain is 'points in time'.  If this flag
     * is true, only the x-value (and not the x-interval) is used to determine 
     * the range of values in the domain.
     */
    private boolean domainIsPointsInTime;
    
    /** 
     * The point within each time period that is used for the X value when this
     * collection is used as an {@link org.jfree.data.xy.XYDataset}.  This can 
     * be the start, middle or end of the time period.   
     */
    private TimePeriodAnchor xPosition;

    /** A working calendar (to recycle) */
    private Calendar workingCalendar;

    /**
     * Creates a new dataset.
     */
    public TimeTableXYDataset() {
        // defer argument checking
        this(TimeZone.getDefault(), Locale.getDefault());
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[1]++;
    }
    
    /**
     * Creates a new dataset with the given time zone.
     * 
     * @param zone  the time zone to use (<code>null</code> not permitted).
     */
    public TimeTableXYDataset(TimeZone zone) {
        // defer argument checking
        this(zone, Locale.getDefault());
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[2]++;
    }

    /**
     * Creates a new dataset with the given time zone and locale.
     * 
     * @param zone  the time zone to use (<code>null</code> not permitted).
     * @param locale  the locale to use (<code>null</code> not permitted).
     */
    public TimeTableXYDataset(TimeZone zone, Locale locale) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[1]++;
            throw new IllegalArgumentException("Null 'zone' argument.");

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[2]++;}
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[3]++;
            throw new IllegalArgumentException("Null 'locale' argument.");

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[4]++;}
        this.values = new DefaultKeyedValues2D(true);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[5]++;
        this.workingCalendar = Calendar.getInstance(zone, locale);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[6]++;
        this.xPosition = TimePeriodAnchor.START;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[7]++;
    }
    
    /**
     * Returns a flag that controls whether the domain is treated as 'points in
     * time'.
     * <P>
     * This flag is used when determining the max and min values for the domain.
     * If true, then only the x-values are considered for the max and min 
     * values.  If false, then the start and end x-values will also be taken 
     * into consideration.
     *
     * @return The flag.
     */
    public boolean getDomainIsPointsInTime() {
        return this.domainIsPointsInTime;
    }

    /**
     * Sets a flag that controls whether the domain is treated as 'points in 
     * time', or time periods.  A {@link DatasetChangeEvent} is sent to all
     * registered listeners.
     *
     * @param flag  the new value of the flag.
     */
    public void setDomainIsPointsInTime(boolean flag) {
        this.domainIsPointsInTime = flag;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[8]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[9]++;
    }
    
    /**
     * Returns the position within each time period that is used for the X 
     * value.
     * 
     * @return The anchor position (never <code>null</code>).
     */
    public TimePeriodAnchor getXPosition() {
        return this.xPosition;
    }

    /**
     * Sets the position within each time period that is used for the X values,
     * then sends a {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @param anchor  the anchor position (<code>null</code> not permitted).
     */
    public void setXPosition(TimePeriodAnchor anchor) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[5]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[6]++;}
        this.xPosition = anchor;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[11]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[12]++;    
    }
        
    /**
     * Adds a new data item to the dataset and sends a 
     * {@link org.jfree.data.general.DatasetChangeEvent} to all registered
     * listeners.
     * 
     * @param period  the time period.
     * @param y  the value for this period.
     * @param seriesName  the name of the series to add the value.
     */
    public void add(TimePeriod period, double y, String seriesName) {
        add(period, new Double(y), seriesName, true);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[13]++;
    }
    
    /**
     * Adds a new data item to the dataset.
     * 
     * @param period  the time period (<code>null</code> not permitted).
     * @param y  the value for this period (<code>null</code> permitted).
     * @param seriesName  the name of the series to add the value 
     *                    (<code>null</code> not permitted).
     * @param notify  whether dataset listener are notified or not.
     */
    public void add(TimePeriod period, Number y, String seriesName, 
                    boolean notify) {
        this.values.addValue(y, period, seriesName);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[14]++;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[7]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[16]++;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[8]++;}
    }

    /**
     * Removes an existing data item from the dataset.
     * 
     * @param period  the (existing!) time period of the value to remove 
     *                (<code>null</code> not permitted).
     * @param seriesName  the (existing!) series name to remove the value 
     *                    (<code>null</code> not permitted).
     */
    public void remove(TimePeriod period, String seriesName) {
        remove(period, seriesName, true);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[17]++;
    }
    
    /**
     * Removes an existing data item from the dataset.
     * 
     * @param period  the (existing!) time period of the value to remove 
     *                (<code>null</code> not permitted).
     * @param seriesName  the (existing!) series name to remove the value 
     *                    (<code>null</code> not permitted).
     * @param notify  whether dataset listener are notified or not.
     */
    public void remove(TimePeriod period, String seriesName, boolean notify) {
        this.values.removeValue(period, seriesName);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[18]++;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[9]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[20]++;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[10]++;}
    }

    /**
     * Removes all data items from the dataset and sends a
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @since 1.0.7
     */
    public void clear() {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.values.getRowCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[11]++;
            this.values.clear();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[22]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[23]++;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[12]++;}
    }
    
    /**
     * Returns the time period for the specified item.  Bear in mind that all
     * series share the same set of time periods.
     * 
     * @param item  the item index (0 <= i <= {@link #getItemCount()}).
     * 
     * @return The time period.
     */
    public TimePeriod getTimePeriod(int item) {
        return (TimePeriod) this.values.getRowKey(item);    
    }
    
    /**
     * Returns the number of items in ALL series.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.values.getRowCount();
    }

    /**
     * Returns the number of items in a series.  This is the same value
     * that is returned by {@link #getItemCount()} since all series
     * share the same x-values (time periods).
     *
     * @param series  the series (zero-based index, ignored).
     *
     * @return The number of items within the series.
     */
    public int getItemCount(int series) {
        return getItemCount();
    }
    
    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.values.getColumnCount();
    }

    /**
     * Returns the key for a series.
     *
     * @param series  the series (zero-based index).
     *
     * @return The key for the series.
     */
    public Comparable getSeriesKey(int series) {
        return this.values.getColumnKey(series);
    }
    
    /**
     * Returns the x-value for an item within a series.  The x-values may or 
     * may not be returned in ascending order, that is up to the class 
     * implementing the interface.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The x-value.
     */
    public Number getX(int series, int item) {
        return new Double(getXValue(series, item));
    }
    
    /**
     * Returns the x-value (as a double primitive) for an item within a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getXValue(int series, int item) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[24]++;
        TimePeriod period = (TimePeriod) this.values.getRowKey(item);
        return getXValue(period);
    }

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return The starting X value for the specified series and item.
     */
    public Number getStartX(int series, int item) {
        return new Double(getStartXValue(series, item));
    }

    /**
     * Returns the start x-value (as a double primitive) for an item within 
     * a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getStartXValue(int series, int item) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[25]++;
        TimePeriod period = (TimePeriod) this.values.getRowKey(item);
        return period.getStart().getTime();
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return The ending X value for the specified series and item.
     */
    public Number getEndX(int series, int item) {
        return new Double(getEndXValue(series, item));
    }

    /**
     * Returns the end x-value (as a double primitive) for an item within 
     * a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The value.
     */
    public double getEndXValue(int series, int item) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[26]++;
        TimePeriod period = (TimePeriod) this.values.getRowKey(item);
        return period.getEnd().getTime();
    }
 
    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The y-value (possibly <code>null</code>).
     */
    public Number getY(int series, int item) {
        return this.values.getValue(item, series);
    }
    
    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
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
     * @param item  the item within a series (zero-based index).
     *
     * @return The ending Y value for the specified series and item.
     */
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }
    
    /**
     * Returns the x-value for a time period.
     *
     * @param period  the time period.
     *
     * @return The x-value.
     */
    private long getXValue(TimePeriod period) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[27]++;
        long result = 0L;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.START) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[13]++;
            result = period.getStart().getTime();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[29]++;

        }
        else {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[14]++;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[30]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[15]++;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[31]++;
            long t0 = period.getStart().getTime();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[32]++;
            long t1 = period.getEnd().getTime();
            result = t0 + (t1 - t0) / 2L;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[33]++;

        }
        else {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[16]++;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[34]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.END) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[17]++;
            result = period.getEnd().getTime();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[35]++;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[18]++;}
}
}
        return result;
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
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[36]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[37]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[19]++;
            result = r.getLowerBound();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[39]++;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[20]++;}
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
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[40]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[41]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[21]++;
            result = r.getUpperBound();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[43]++;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[22]++;}
        return result;
    }

    /**
     * Returns the range of the values in this dataset's domain.
     * 
     * @param includeInterval  a flag that controls whether or not the
     *                         x-intervals are taken into account.
     *
     * @return The range.
     */
    public Range getDomainBounds(boolean includeInterval) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[44]++;
        List keys = this.values.getRowKeys();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((keys.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[23]++;
            return null;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[24]++;}
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[46]++;
        
        TimePeriod first = (TimePeriod) keys.get(0);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[47]++;
        TimePeriod last = (TimePeriod) keys.get(keys.size() - 1);
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
        
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.domainIsPointsInTime) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[25]++;
            return new Range(getXValue(first), getXValue(last));

        }
        else {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[26]++;
            return new Range(first.getStart().getTime(), 
                    last.getEnd().getTime());
        }
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[49]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[27]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[28]++;}
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[50]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj instanceof TimeTableXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[30]++;}
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[51]++;
        TimeTableXYDataset that = (TimeTableXYDataset) obj;
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.domainIsPointsInTime != that.domainIsPointsInTime) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[32]++;}
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[53]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.xPosition != that.xPosition) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[34]++;}
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[54]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.workingCalendar.getTimeZone().equals(
            that.workingCalendar.getTimeZone())) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)
        ) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[36]++;}
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[55]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.values.equals(that.values)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.branches[38]++;}
        return true;
    }
    
    /**
     * Returns a clone of this dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the dataset cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[56]++;
        TimeTableXYDataset clone = (TimeTableXYDataset) super.clone();
        clone.values = (DefaultKeyedValues2D) this.values.clone();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[57]++;
        clone.workingCalendar = (Calendar) this.workingCalendar.clone();
CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl.statements[58]++;
        return clone;
    }

}

class CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl ());
  }
    public static long[] statements = new long[59];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimeTableXYDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$2qhom7po346fch4vorldo2qs7rfqnz2jbnfl () {
    super("org.jfree.data.time.TimeTableXYDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 58; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.TimeTableXYDataset.java");
      for (int i = 1; i <= 58; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

