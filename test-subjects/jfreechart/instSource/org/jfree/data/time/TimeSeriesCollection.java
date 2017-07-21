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
 * TimeSeriesCollection.java
 * -------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 18-Oct-2001 : Added implementation of IntervalXYDataSource so that bar plots
 *               (using numerical axes) can be plotted from time series 
 *               data (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 15-Nov-2001 : Added getSeries() method.  Changed name from TimeSeriesDataset
 *               to TimeSeriesCollection (DG);
 * 07-Dec-2001 : TimeSeries --> BasicTimeSeries (DG);
 * 01-Mar-2002 : Added a time zone offset attribute, to enable fast calculation
 *               of the time period start and end values (DG);
 * 29-Mar-2002 : The collection now registers itself with all the time series 
 *               objects as a SeriesChangeListener.  Removed redundant 
 *               calculateZoneOffset method (DG);
 * 06-Jun-2002 : Added a setting to control whether the x-value supplied in the
 *               getXValue() method comes from the START, MIDDLE, or END of the
 *               time period.  This is a workaround for JFreeChart, where the 
 *               current date axis always labels the start of a time 
 *               period (DG);
 * 24-Jun-2002 : Removed unnecessary import (DG);
 * 24-Aug-2002 : Implemented DomainInfo interface, and added the 
 *               DomainIsPointsInTime flag (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 16-Oct-2002 : Added remove methods (DG);
 * 10-Jan-2003 : Changed method names in RegularTimePeriod class (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package and implemented 
 *               Serializable (DG);
 * 04-Sep-2003 : Added getSeries(String) method (DG);
 * 15-Sep-2003 : Added a removeAllSeries() method to match 
 *               XYSeriesCollection (DG);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 06-Oct-2004 : Updated for changed in DomainInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 28-Mar-2005 : Fixed bug in getSeries(int) method (1170825) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Dec-2005 : Deprecated the 'domainIsPointsInTime' flag as it is 
 *               redundant.  Fixes bug 1243050 (DG);
 * 04-May-2007 : Override getDomainOrder() to indicate that items are sorted
 *               by x-value (ascending) (DG);
 * 08-May-2007 : Added indexOf(TimeSeries) method (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.jfree.data.DomainInfo;
import org.jfree.data.DomainOrder;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.ObjectUtilities;

/**
 * A collection of time series objects.  This class implements the 
 * {@link org.jfree.data.xy.XYDataset} interface, as well as the extended 
 * {@link IntervalXYDataset} interface.  This makes it a convenient dataset for
 * use with the {@link org.jfree.chart.plot.XYPlot} class.
 */
public class TimeSeriesCollection extends AbstractIntervalXYDataset
                                  implements XYDataset,
                                             IntervalXYDataset,
                                             DomainInfo,
                                             Serializable {
  static {
    CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 834149929022371137L;
  static {
    CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[1]++;
  }
    
    /** Storage for the time series. */
    private List data;

    /** A working calendar (to recycle) */
    private Calendar workingCalendar;
    
    /** 
     * The point within each time period that is used for the X value when this
     * collection is used as an {@link org.jfree.data.xy.XYDataset}.  This can 
     * be the start, middle or end of the time period.   
     */
    private TimePeriodAnchor xPosition;

    /**
     * A flag that indicates that the domain is 'points in time'.  If this
     * flag is true, only the x-value is used to determine the range of values
     * in the domain, the start and end x-values are ignored.
     * 
     * @deprecated No longer used (as of 1.0.1).
     */
    private boolean domainIsPointsInTime;

    /**
     * Constructs an empty dataset, tied to the default timezone.
     */
    public TimeSeriesCollection() {
        this(null, TimeZone.getDefault());
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[2]++;
    }

    /**
     * Constructs an empty dataset, tied to a specific timezone.
     *
     * @param zone  the timezone (<code>null</code> permitted, will use 
     *              <code>TimeZone.getDefault()</code> in that case).
     */
    public TimeSeriesCollection(TimeZone zone) {
        this(null, zone);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[3]++;
    }

    /**
     * Constructs a dataset containing a single series (more can be added),
     * tied to the default timezone.
     *
     * @param series the series (<code>null</code> permitted).
     */
    public TimeSeriesCollection(TimeSeries series) {
        this(series, TimeZone.getDefault());
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[4]++;
    }

    /**
     * Constructs a dataset containing a single series (more can be added),
     * tied to a specific timezone.
     *
     * @param series  a series to add to the collection (<code>null</code> 
     *                permitted).
     * @param zone  the timezone (<code>null</code> permitted, will use 
     *              <code>TimeZone.getDefault()</code> in that case).
     */
    public TimeSeriesCollection(TimeSeries series, TimeZone zone) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[1]++;
            zone = TimeZone.getDefault();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[6]++;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[2]++;}
        this.workingCalendar = Calendar.getInstance(zone);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[7]++;
        this.data = new ArrayList();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[8]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[3]++;
            this.data.add(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[10]++;
            series.addChangeListener(this);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[11]++;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[4]++;}
        this.xPosition = TimePeriodAnchor.START;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[12]++;
        this.domainIsPointsInTime = true;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[13]++;

    }
    
    /**
     * Returns a flag that controls whether the domain is treated as 'points in
     * time'.  This flag is used when determining the max and min values for 
     * the domain.  If <code>true</code>, then only the x-values are considered
     * for the max and min values.  If <code>false</code>, then the start and
     * end x-values will also be taken into consideration.
     *
     * @return The flag.
     * 
     * @deprecated This flag is no longer used (as of 1.0.1).
     */
    public boolean getDomainIsPointsInTime() {
        return this.domainIsPointsInTime;
    }

    /**
     * Sets a flag that controls whether the domain is treated as 'points in 
     * time', or time periods.
     *
     * @param flag  the flag.
     * 
     * @deprecated This flag is no longer used, as of 1.0.1.  The 
     *             <code>includeInterval</code> flag in methods such as 
     *             {@link #getDomainBounds(boolean)} makes this unnecessary.
     */
    public void setDomainIsPointsInTime(boolean flag) {
        this.domainIsPointsInTime = flag;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[14]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[15]++;    
    }
    
    /**
     * Returns the order of the domain values in this dataset.
     *
     * @return {@link DomainOrder#ASCENDING}
     */
    public DomainOrder getDomainOrder() {
        return DomainOrder.ASCENDING;
    }
    
    /**
     * Returns the position within each time period that is used for the X 
     * value when the collection is used as an 
     * {@link org.jfree.data.xy.XYDataset}.
     * 
     * @return The anchor position (never <code>null</code>).
     */
    public TimePeriodAnchor getXPosition() {
        return this.xPosition;
    }

    /**
     * Sets the position within each time period that is used for the X values 
     * when the collection is used as an {@link XYDataset}, then sends a 
     * {@link DatasetChangeEvent} is sent to all registered listeners.
     * 
     * @param anchor  the anchor position (<code>null</code> not permitted).
     */
    public void setXPosition(TimePeriodAnchor anchor) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[5]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[6]++;}
        this.xPosition = anchor;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[17]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[18]++;    
    }
    
    /**
     * Returns a list of all the series in the collection.  
     * 
     * @return The list (which is unmodifiable).
     */
    public List getSeries() {
        return Collections.unmodifiableList(this.data);
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
     * Returns the index of the specified series, or -1 if that series is not
     * present in the dataset.
     * 
     * @param series  the series (<code>null</code> not permitted).
     * 
     * @return The series index.
     * 
     * @since 1.0.6
     */
    public int indexOf(TimeSeries series) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[7]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[8]++;}
        return this.data.indexOf(series);
    }

    /**
     * Returns a series.
     *
     * @param series  the index of the series (zero-based).
     *
     * @return The series.
     */
    public TimeSeries getSeries(int series) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[9]++;
            throw new IllegalArgumentException(
                "The 'series' argument is out of bounds (" + series + ").");

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[10]++;}
        return (TimeSeries) this.data.get(series);
    }
    
    /**
     * Returns the series with the specified key, or <code>null</code> if 
     * there is no such series.
     * 
     * @param key  the series key (<code>null</code> permitted).
     * 
     * @return The series with the given key.
     */
    public TimeSeries getSeries(String key) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[21]++;
        TimeSeries result = null;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[22]++;
        Iterator iterator = this.data.iterator();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[1]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[2]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[3]++;
}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[24]++;
            TimeSeries series = (TimeSeries) iterator.next();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[25]++;
            Comparable k = series.getKey();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((k != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((k.equals(key)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[11]++;
                result = series;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[27]++;

            } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[12]++;}
        }
        return result;   
    }

    /**
     * Returns the key for a series.  
     *
     * @param series  the index of the series (zero-based).
     *
     * @return The key for a series.
     */
    public Comparable getSeriesKey(int series) {
        // check arguments...delegated
        // fetch the series name...
        return getSeries(series).getKey();
    }

    /**
     * Adds a series to the collection and sends a {@link DatasetChangeEvent} to
     * all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void addSeries(TimeSeries series) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[13]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[14]++;}
        this.data.add(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[29]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[30]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[31]++;
    }

    /**
     * Removes the specified series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void removeSeries(TimeSeries series) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[15]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[16]++;}
        this.data.remove(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[33]++;
        series.removeChangeListener(this);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[34]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[35]++;
    }

    /**
     * Removes a series from the collection.
     *
     * @param index  the series index (zero-based).
     */
    public void removeSeries(int index) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[36]++;
        TimeSeries series = getSeries(index);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((series != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[17]++;
            removeSeries(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[38]++;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[18]++;}
    }

    /**
     * Removes all the series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     */
    public void removeAllSeries() {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[39]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;

        // deregister the collection as a change listener to each series in the
        // collection
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[4]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[5]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[6]++;
}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[40]++;
            TimeSeries series = (TimeSeries) this.data.get(i);
            series.removeChangeListener(this);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[41]++;
        }

        // remove all the series from the collection and notify listeners.
        this.data.clear();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[42]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[43]++;

    }

    /**
     * Returns the number of items in the specified series.  This method is 
     * provided for convenience.
     *
     * @param series  the series index (zero-based).
     *
     * @return The item count.
     */
    public int getItemCount(int series) {
        return getSeries(series).getItemCount();
    }
    
    /**
     * Returns the x-value (as a double primitive) for an item within a series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The x-value.
     */
    public double getXValue(int series, int item) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[44]++;
        TimeSeries s = (TimeSeries) this.data.get(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[45]++;
        TimeSeriesDataItem i = s.getDataItem(item);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[46]++;
        RegularTimePeriod period = i.getPeriod();
        return getX(period);
    }

    /**
     * Returns the x-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The value.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[47]++;
        TimeSeries ts = (TimeSeries) this.data.get(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[48]++;
        TimeSeriesDataItem dp = ts.getDataItem(item);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[49]++;
        RegularTimePeriod period = dp.getPeriod();
        return new Long(getX(period));
    }
    
    /**
     * Returns the x-value for a time period.
     *
     * @param period  the time period (<code>null</code> not permitted).
     *
     * @return The x-value.
     */
    protected synchronized long getX(RegularTimePeriod period) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[50]++;
        long result = 0L;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[51]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.START) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[19]++;
            result = period.getFirstMillisecond(this.workingCalendar);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[52]++;

        }
        else {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[20]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[53]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[21]++;
            result = period.getMiddleMillisecond(this.workingCalendar);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[54]++;

        }
        else {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[22]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[55]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.xPosition == TimePeriodAnchor.END) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[23]++;
            result = period.getLastMillisecond(this.workingCalendar);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[56]++;
 
        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[24]++;}
}
}
        return result;
    }

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The value.
     */
    public synchronized Number getStartX(int series, int item) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[57]++;
        TimeSeries ts = (TimeSeries) this.data.get(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[58]++;
        TimeSeriesDataItem dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getFirstMillisecond(
                this.workingCalendar));
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series The series (zero-based index).
     * @param item  The item (zero-based index).
     *
     * @return The value.
     */
    public synchronized Number getEndX(int series, int item) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[59]++;
        TimeSeries ts = (TimeSeries) this.data.get(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[60]++;
        TimeSeriesDataItem dp = ts.getDataItem(item);
        return new Long(dp.getPeriod().getLastMillisecond(
                this.workingCalendar));
    }

    /**
     * Returns the y-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getY(int series, int item) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[61]++;
        TimeSeries ts = (TimeSeries) this.data.get(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[62]++;
        TimeSeriesDataItem dp = ts.getDataItem(item);
        return dp.getValue();
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getStartY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  te series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }


    /**
     * Returns the indices of the two data items surrounding a particular 
     * millisecond value.  
     * 
     * @param series  the series index.
     * @param milliseconds  the time.
     * 
     * @return An array containing the (two) indices of the items surrounding 
     *         the time.
     */
    public int[] getSurroundingItems(int series, long milliseconds) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[63]++;
        int[] result = new int[] {-1, -1};
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[64]++;
        TimeSeries timeSeries = getSeries(series);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[65]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[7]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < timeSeries.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[7]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[8]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[9]++;
}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[66]++;
            Number x = getX(series, i);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[67]++;
            long m = x.longValue();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[68]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((m <= milliseconds) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[25]++;
                result[0] = i;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[69]++;

            } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[26]++;}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((m >= milliseconds) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[27]++;
                result[1] = i;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[71]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[72]++;
                break;

            } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[28]++;}
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
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[73]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[74]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[75]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[29]++;
            result = r.getLowerBound();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[76]++;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[30]++;}
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
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[77]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[78]++;
        Range r = getDomainBounds(includeInterval);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[79]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[31]++;
            result = r.getUpperBound();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[80]++;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[32]++;}
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
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[81]++;
        Range result = null;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[82]++;
        Iterator iterator = this.data.iterator();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[83]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[10]++;


int CodeCoverConditionCoverageHelper_C20;
        while ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[10]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[11]--;
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.loops[12]++;
}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[84]++;
            TimeSeries series = (TimeSeries) iterator.next();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[85]++;
            int count = series.getItemCount();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[86]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[33]++;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[87]++;
                RegularTimePeriod start = series.getTimePeriod(0);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[88]++;
                RegularTimePeriod end = series.getTimePeriod(count - 1);
                Range temp;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[89]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[35]++;
                    temp = new Range(getX(start), getX(end));
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[90]++;

                }
                else {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[36]++;
                    temp = new Range(
                            start.getFirstMillisecond(this.workingCalendar),
                            end.getLastMillisecond(this.workingCalendar));
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[91]++;
                }
                result = Range.combine(result, temp);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[92]++;

            } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[34]++;}
        }
        return result;
    }
    
    /**
     * Tests this time series collection for equality with another object.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[93]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[37]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[38]++;}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[94]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((obj instanceof TimeSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[40]++;}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[95]++;
        TimeSeriesCollection that = (TimeSeriesCollection) obj;
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[96]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.xPosition != that.xPosition) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[42]++;}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[97]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.domainIsPointsInTime != that.domainIsPointsInTime) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[44]++;}
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[98]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.branches[46]++;}
        return true;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return The hashcode
     */
    public int hashCode() {
        int result;
        result = this.data.hashCode();
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[99]++;
        result = 29 * result + (this.workingCalendar != null 
                ? this.workingCalendar.hashCode() : 0);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[100]++;
        result = 29 * result + (this.xPosition != null 
                ? this.xPosition.hashCode() : 0);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[101]++;
        result = 29 * result + (this.domainIsPointsInTime ? 1 : 0);
CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01.statements[102]++;
        return result;
    }
    
}

class CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01 ());
  }
    public static long[] statements = new long[103];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimeSeriesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$3uchsdzsboshx4joxwkfjc9bps5zxabc68qdi01 () {
    super("org.jfree.data.time.TimeSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 102; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.TimeSeriesCollection.java");
      for (int i = 1; i <= 102; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

