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
 * ---------------
 * TimeSeries.java
 * ---------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Bryan Scott;
 *                   Nick Guenther;
 *
 * Changes
 * -------
 * 11-Oct-2001 : Version 1 (DG);
 * 14-Nov-2001 : Added listener mechanism (DG);
 * 15-Nov-2001 : Updated argument checking and exceptions in add() method (DG);
 * 29-Nov-2001 : Added properties to describe the domain and range (DG);
 * 07-Dec-2001 : Renamed TimeSeries --> BasicTimeSeries (DG);
 * 01-Mar-2002 : Updated import statements (DG);
 * 28-Mar-2002 : Added a method add(TimePeriod, double) (DG);
 * 27-Aug-2002 : Changed return type of delete method to void (DG);
 * 04-Oct-2002 : Added itemCount and historyCount attributes, fixed errors 
 *               reported by Checkstyle (DG);
 * 29-Oct-2002 : Added series change notification to addOrUpdate() method (DG);
 * 28-Jan-2003 : Changed name back to TimeSeries (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package and implemented 
 *               Serializable (DG);
 * 01-May-2003 : Updated equals() method (see bug report 727575) (DG);
 * 14-Aug-2003 : Added ageHistoryCountItems method (copied existing code for 
 *               contents) made a method and added to addOrUpdate.  Made a 
 *               public method to enable ageing against a specified time 
 *               (eg now) as opposed to lastest time in series (BS);
 * 15-Oct-2003 : Added fix for setItemCount method - see bug report 804425.  
 *               Modified exception message in add() method to be more 
 *               informative (DG);
 * 13-Apr-2004 : Added clear() method (DG);
 * 21-May-2004 : Added an extra addOrUpdate() method (DG);
 * 15-Jun-2004 : Fixed NullPointerException in equals() method (DG);
 * 29-Nov-2004 : Fixed bug 1075255 (DG);
 * 17-Nov-2005 : Renamed historyCount --> maximumItemAge (DG);
 * 28-Nov-2005 : Changed maximumItemAge from int to long (DG);
 * 01-Dec-2005 : New add methods accept notify flag (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 24-May-2006 : Improved error handling in createCopy() methods (DG);
 * 01-Sep-2006 : Fixed bugs in removeAgedItems() methods - see bug report 
 *               1550045 (DG);
 * 22-Mar-2007 : Simplified getDataItem(RegularTimePeriod) - see patch 1685500 
 *               by Nick Guenther (DG);
 * 31-Oct-2007 : Implemented faster hashCode() (DG);
 * 21-Nov-2007 : Fixed clone() method (bug 1832432) (DG);
 * 
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.jfree.data.general.Series;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesException;
import org.jfree.util.ObjectUtilities;

/**
 * Represents a sequence of zero or more data items in the form (period, value).
 */
public class TimeSeries extends Series implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5032960206869675528L;
  static {
    CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[1]++;
  }
    
    /** Default value for the domain description. */
    protected static final String DEFAULT_DOMAIN_DESCRIPTION = "Time";
  static {
    CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[2]++;
  }

    /** Default value for the range description. */
    protected static final String DEFAULT_RANGE_DESCRIPTION = "Value";
  static {
    CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[3]++;
  }

    /** A description of the domain. */
    private String domain;

    /** A description of the range. */
    private String range;

    /** The type of period for the data. */
    protected Class timePeriodClass;

    /** The list of data items in the series. */
    protected List data;

    /** The maximum number of items for the series. */
    private int maximumItemCount;

    /** 
     * The maximum age of items for the series, specified as a number of
     * time periods. 
     */
    private long maximumItemAge;
    
    /**
     * Creates a new (empty) time series.  By default, a daily time series is 
     * created.  Use one of the other constructors if you require a different 
     * time period.
     *
     * @param name  the series name (<code>null</code> not permitted).
     */
    public TimeSeries(Comparable name) {
        this(name, DEFAULT_DOMAIN_DESCRIPTION, DEFAULT_RANGE_DESCRIPTION, 
                Day.class);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[4]++;
    }

    /**
     * Creates a new (empty) time series with the specified name and class
     * of {@link RegularTimePeriod}.
     *
     * @param name  the series name (<code>null</code> not permitted).
     * @param timePeriodClass  the type of time period (<code>null</code> not 
     *                         permitted).
     */
    public TimeSeries(Comparable name, Class timePeriodClass) {
        this(name, DEFAULT_DOMAIN_DESCRIPTION, DEFAULT_RANGE_DESCRIPTION, 
                timePeriodClass);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[5]++;
    }

    /**
     * Creates a new time series that contains no data.
     * <P>
     * Descriptions can be specified for the domain and range.  One situation
     * where this is helpful is when generating a chart for the time series -
     * axis labels can be taken from the domain and range description.
     *
     * @param name  the name of the series (<code>null</code> not permitted).
     * @param domain  the domain description (<code>null</code> permitted).
     * @param range  the range description (<code>null</code> permitted).
     * @param timePeriodClass  the type of time period (<code>null</code> not 
     *                         permitted).
     */
    public TimeSeries(Comparable name, String domain, String range, 
                      Class timePeriodClass) {
        super(name);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[6]++;
        this.domain = domain;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[7]++;
        this.range = range;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[8]++;
        this.timePeriodClass = timePeriodClass;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[9]++;
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[10]++;
        this.maximumItemCount = Integer.MAX_VALUE;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[11]++;
        this.maximumItemAge = Long.MAX_VALUE;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[12]++;
    }

    /**
     * Returns the domain description.
     *
     * @return The domain description (possibly <code>null</code>).
     * 
     * @see #setDomainDescription(String)
     */
    public String getDomainDescription() {
        return this.domain;
    }

    /**
     * Sets the domain description and sends a <code>PropertyChangeEvent</code> 
     * (with the property name <code>Domain</code>) to all registered
     * property change listeners.
     *
     * @param description  the description (<code>null</code> permitted).
     * 
     * @see #getDomainDescription()
     */
    public void setDomainDescription(String description) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[13]++;
        String old = this.domain;
        this.domain = description;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[14]++;
        firePropertyChange("Domain", old, description);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[15]++;
    }

    /**
     * Returns the range description.
     *
     * @return The range description (possibly <code>null</code>).
     * 
     * @see #setRangeDescription(String)
     */
    public String getRangeDescription() {
        return this.range;
    }

    /**
     * Sets the range description and sends a <code>PropertyChangeEvent</code> 
     * (with the property name <code>Range</code>) to all registered listeners.
     *
     * @param description  the description (<code>null</code> permitted).
     * 
     * @see #getRangeDescription()
     */
    public void setRangeDescription(String description) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[16]++;
        String old = this.range;
        this.range = description;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[17]++;
        firePropertyChange("Range", old, description);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[18]++;
    }

    /**
     * Returns the number of items in the series.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.data.size();
    }

    /**
     * Returns the list of data items for the series (the list contains 
     * {@link TimeSeriesDataItem} objects and is unmodifiable).
     *
     * @return The list of data items.
     */
    public List getItems() {
        return Collections.unmodifiableList(this.data);
    }

    /**
     * Returns the maximum number of items that will be retained in the series.
     * The default value is <code>Integer.MAX_VALUE</code>.
     *
     * @return The maximum item count.
     * 
     * @see #setMaximumItemCount(int)
     */
    public int getMaximumItemCount() {
        return this.maximumItemCount;
    }

    /**
     * Sets the maximum number of items that will be retained in the series.  
     * If you add a new item to the series such that the number of items will 
     * exceed the maximum item count, then the FIRST element in the series is 
     * automatically removed, ensuring that the maximum item count is not 
     * exceeded.
     *
     * @param maximum  the maximum (requires >= 0).
     * 
     * @see #getMaximumItemCount()
     */
    public void setMaximumItemCount(int maximum) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((maximum < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[1]++;
            throw new IllegalArgumentException("Negative 'maximum' argument.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[2]++;}
        this.maximumItemCount = maximum;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[20]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[21]++;
        int count = this.data.size();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((count > maximum) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[3]++;
            delete(0, count - maximum - 1);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[23]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[4]++;}
    }

    /**
     * Returns the maximum item age (in time periods) for the series.
     *
     * @return The maximum item age.
     * 
     * @see #setMaximumItemAge(long)
     */
    public long getMaximumItemAge() {
        return this.maximumItemAge;
    }

    /**
     * Sets the number of time units in the 'history' for the series.  This 
     * provides one mechanism for automatically dropping old data from the
     * time series. For example, if a series contains daily data, you might set
     * the history count to 30.  Then, when you add a new data item, all data
     * items more than 30 days older than the latest value are automatically 
     * dropped from the series.
     *
     * @param periods  the number of time periods.
     * 
     * @see #getMaximumItemAge()
     */
    public void setMaximumItemAge(long periods) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((periods < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[5]++;
            throw new IllegalArgumentException("Negative 'periods' argument.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[6]++;}
        this.maximumItemAge = periods;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[25]++;
        removeAgedItems(true);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[26]++;  // remove old items and notify if necessary
    }

    /**
     * Returns the time period class for this series.
     * <p>
     * Only one time period class can be used within a single series (enforced).
     * If you add a data item with a {@link Year} for the time period, then all
     * subsequent data items must also have a {@link Year} for the time period.
     *
     * @return The time period class (never <code>null</code>).
     */
    public Class getTimePeriodClass() {
        return this.timePeriodClass;
    }

    /**
     * Returns a data item for the series.
     *
     * @param index  the item index (zero-based).
     *
     * @return The data item.
     * 
     * @see #getDataItem(RegularTimePeriod)
     */
    public TimeSeriesDataItem getDataItem(int index) {
        return (TimeSeriesDataItem) this.data.get(index);
    }

    /**
     * Returns the data item for a specific period.
     *
     * @param period  the period of interest (<code>null</code> not allowed).
     *
     * @return The data item matching the specified period (or 
     *         <code>null</code> if there is no match).
     *
     * @see #getDataItem(int)
     */
    public TimeSeriesDataItem getDataItem(RegularTimePeriod period) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[27]++;
        int index = getIndex(period);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[7]++;
            return (TimeSeriesDataItem) this.data.get(index);

        }
        else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[8]++;
            return null;
        }
    }

    /**
     * Returns the time period at the specified index.
     *
     * @param index  the index of the data item.
     *
     * @return The time period.
     */
    public RegularTimePeriod getTimePeriod(int index) {
        return getDataItem(index).getPeriod();
    }

    /**
     * Returns a time period that would be the next in sequence on the end of
     * the time series.
     *
     * @return The next time period.
     */
    public RegularTimePeriod getNextTimePeriod() {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[29]++;
        RegularTimePeriod last = getTimePeriod(getItemCount() - 1);
        return last.next();
    }

    /**
     * Returns a collection of all the time periods in the time series.
     *
     * @return A collection of all the time periods.
     */
    public Collection getTimePeriods() {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[30]++;
        Collection result = new java.util.ArrayList();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[1]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[2]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[3]++;
}
            result.add(getTimePeriod(i));
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[32]++;
        }
        return result;
    }

    /**
     * Returns a collection of time periods in the specified series, but not in
     * this series, and therefore unique to the specified series.
     *
     * @param series  the series to check against this one.
     *
     * @return The unique time periods.
     */
    public Collection getTimePeriodsUniqueToOtherSeries(TimeSeries series) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[33]++;

        Collection result = new java.util.ArrayList();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < series.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[4]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[5]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[6]++;
}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[35]++;
            RegularTimePeriod period = series.getTimePeriod(i);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[36]++;
            int index = getIndex(period);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[9]++;
                result.add(period);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[38]++;

            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[10]++;}
        }
        return result;

    }

    /**
     * Returns the index for the item (if any) that corresponds to a time 
     * period.
     *
     * @param period  the time period (<code>null</code> not permitted).
     *
     * @return The index.
     */
    public int getIndex(RegularTimePeriod period) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[11]++;
            throw new IllegalArgumentException("Null 'period' argument.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[12]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[40]++; 
        TimeSeriesDataItem dummy = new TimeSeriesDataItem(
              period, Integer.MIN_VALUE);
        return Collections.binarySearch(this.data, dummy);
    }

    /**
     * Returns the value at the specified index.
     *
     * @param index  index of a value.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue(int index) {
        return getDataItem(index).getValue();
    }

    /**
     * Returns the value for a time period.  If there is no data item with the 
     * specified period, this method will return <code>null</code>.
     *
     * @param period  time period (<code>null</code> not permitted).
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue(RegularTimePeriod period) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[41]++;

        int index = getIndex(period);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[13]++;
            return getValue(index);

        }
        else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[14]++;
            return null;
        }

    }

    /**
     * Adds a data item to the series and sends a 
     * {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param item  the (timeperiod, value) pair (<code>null</code> not 
     *              permitted).
     */
    public void add(TimeSeriesDataItem item) {
        add(item, true);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[43]++;
    }
        
    /**
     * Adds a data item to the series and sends a 
     * {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param item  the (timeperiod, value) pair (<code>null</code> not 
     *              permitted).
     * @param notify  notify listeners?
     */
    public void add(TimeSeriesDataItem item, boolean notify) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[15]++;
            throw new IllegalArgumentException("Null 'item' argument.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[16]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((item.getPeriod().getClass().equals(this.timePeriodClass)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[17]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[46]++;
            StringBuffer b = new StringBuffer();
            b.append("You are trying to add data where the time period class ");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[47]++;
            b.append("is ");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[48]++;
            b.append(item.getPeriod().getClass().getName());
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[49]++;
            b.append(", but the TimeSeries is expecting an instance of ");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[50]++;
            b.append(this.timePeriodClass.getName());
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[51]++;
            b.append(".");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[52]++;
            throw new SeriesException(b.toString());

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[18]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[53]++;

        // make the change (if it's not a duplicate time period)...
        boolean added = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[54]++;
        int count = getItemCount();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[55]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[19]++;
            this.data.add(item);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[56]++;
            added = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[57]++;

        }
        else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[20]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[58]++;
            RegularTimePeriod last = getTimePeriod(getItemCount() - 1);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[59]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((item.getPeriod().compareTo(last) > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[21]++;
                this.data.add(item);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[60]++;
                added = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[61]++;

            }
            else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[22]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[62]++;
                int index = Collections.binarySearch(this.data, item);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[23]++;
                    this.data.add(-index - 1, item);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[64]++;
                    added = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[65]++;

                }
                else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[24]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[66]++;
                    StringBuffer b = new StringBuffer();
                    b.append("You are attempting to add an observation for ");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[67]++;
                    b.append("the time period ");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[68]++;
                    b.append(item.getPeriod().toString());
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[69]++;
                    b.append(" but the series already contains an observation");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[70]++;
                    b.append(" for that time period. Duplicates are not ");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[71]++;
                    b.append("permitted.  Try using the addOrUpdate() method.");
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[72]++;
                    throw new SeriesException(b.toString());
                }
            }
        }
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[73]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((added) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[25]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;
            // check if this addition will exceed the maximum item count...
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getItemCount() > this.maximumItemCount) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[27]++;
                this.data.remove(0);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[75]++;

            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[28]++;}

            removeAgedItems(false);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[76]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[77]++;
int CodeCoverConditionCoverageHelper_C17;  // remove old items if necessary, but
                                     // don't notify anyone, because that
                                     // happens next anyway...
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[29]++;
                fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[78]++;

            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[30]++;}

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[26]++;}

    }

    /**
     * Adds a new data item to the series and sends a {@link SeriesChangeEvent}
     * to all registered listeners.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value.
     */
    public void add(RegularTimePeriod period, double value) {
        // defer argument checking...
        add(period, value, true);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[79]++;
    }

    /**
     * Adds a new data item to the series and sends a {@link SeriesChangeEvent}
     * to all registered listeners.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value.
     * @param notify  notify listeners?
     */
    public void add(RegularTimePeriod period, double value, boolean notify) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[80]++;
        // defer argument checking...
        TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
        add(item, notify);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[81]++;
    }

    /**
     * Adds a new data item to the series and sends 
     * a {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public void add(RegularTimePeriod period, Number value) {
        // defer argument checking...
        add(period, value, true);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[82]++;
    }

    /**
     * Adds a new data item to the series and sends 
     * a {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     * @param notify  notify listeners?
     */
    public void add(RegularTimePeriod period, Number value, boolean notify) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[83]++;
        // defer argument checking...
        TimeSeriesDataItem item = new TimeSeriesDataItem(period, value);
        add(item, notify);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[84]++;
    }

    /**
     * Updates (changes) the value for a time period.  Throws a 
     * {@link SeriesException} if the period does not exist.
     *
     * @param period  the period (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public void update(RegularTimePeriod period, Number value) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[85]++;
        TimeSeriesDataItem temp = new TimeSeriesDataItem(period, value);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[86]++;
        int index = Collections.binarySearch(this.data, temp);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[87]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[31]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[88]++;
            TimeSeriesDataItem pair = (TimeSeriesDataItem) this.data.get(index);
            pair.setValue(value);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[89]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[90]++;

        }
        else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[32]++;
            throw new SeriesException(
                "TimeSeries.update(TimePeriod, Number):  period does not exist."
            );
        }

    }

    /**
     * Updates (changes) the value of a data item.
     *
     * @param index  the index of the data item.
     * @param value  the new value (<code>null</code> permitted).
     */
    public void update(int index, Number value) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[91]++;
        TimeSeriesDataItem item = getDataItem(index);
        item.setValue(value);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[92]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[93]++;
    }

    /**
     * Adds or updates data from one series to another.  Returns another series
     * containing the values that were overwritten.
     *
     * @param series  the series to merge with this.
     *
     * @return A series containing the values that were overwritten.
     */
    public TimeSeries addAndOrUpdate(TimeSeries series) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[94]++;
        TimeSeries overwritten = new TimeSeries("Overwritten values from: " 
                + getKey(), series.getTimePeriodClass());
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[95]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[7]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < series.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[7]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[8]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[9]++;
}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[96]++;
            TimeSeriesDataItem item = series.getDataItem(i);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[97]++;
            TimeSeriesDataItem oldItem = addOrUpdate(item.getPeriod(), 
                    item.getValue());
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((oldItem != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[33]++;
                overwritten.add(oldItem);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[99]++;

            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[34]++;}
        }
        return overwritten;
    }

    /**
     * Adds or updates an item in the times series and sends a 
     * {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param period  the time period to add/update (<code>null</code> not 
     *                permitted).
     * @param value  the new value.
     *
     * @return A copy of the overwritten data item, or <code>null</code> if no 
     *         item was overwritten.
     */
    public TimeSeriesDataItem addOrUpdate(RegularTimePeriod period, 
                                          double value) {
        return this.addOrUpdate(period, new Double(value));    
    }
    
    /**
     * Adds or updates an item in the times series and sends a 
     * {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param period  the time period to add/update (<code>null</code> not 
     *                permitted).
     * @param value  the new value (<code>null</code> permitted).
     *
     * @return A copy of the overwritten data item, or <code>null</code> if no 
     *         item was overwritten.
     */
    public TimeSeriesDataItem addOrUpdate(RegularTimePeriod period, 
                                          Number value) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[100]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[35]++;
            throw new IllegalArgumentException("Null 'period' argument.");
   
        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[36]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[101]++;
        TimeSeriesDataItem overwritten = null;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[102]++;

        TimeSeriesDataItem key = new TimeSeriesDataItem(period, value);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[103]++;
        int index = Collections.binarySearch(this.data, key);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[104]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[37]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[105]++;
            TimeSeriesDataItem existing 
                = (TimeSeriesDataItem) this.data.get(index);
            overwritten = (TimeSeriesDataItem) existing.clone();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[106]++;
            existing.setValue(value);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[107]++;
            removeAgedItems(false);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[108]++;  // remove old items if necessary, but
                                     // don't notify anyone, because that
                                     // happens next anyway...
            fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[109]++;

        }
        else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[38]++;
            this.data.add(-index - 1, new TimeSeriesDataItem(period, value));
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[110]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[111]++;
int CodeCoverConditionCoverageHelper_C23;

            // check if this addition will exceed the maximum item count...
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getItemCount() > this.maximumItemCount) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[39]++;
                this.data.remove(0);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[112]++;

            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[40]++;}

            removeAgedItems(false);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[113]++;  // remove old items if necessary, but
                                     // don't notify anyone, because that
                                     // happens next anyway...
            fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[114]++;
        }
        return overwritten;

    }

    /**
     * Age items in the series.  Ensure that the timespan from the youngest to 
     * the oldest record in the series does not exceed maximumItemAge time 
     * periods.  Oldest items will be removed if required.
     * 
     * @param notify  controls whether or not a {@link SeriesChangeEvent} is 
     *                sent to registered listeners IF any items are removed.
     */
    public void removeAgedItems(boolean notify) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[115]++;
int CodeCoverConditionCoverageHelper_C24;
        // check if there are any values earlier than specified by the history 
        // count...
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((getItemCount() > 1) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[41]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[116]++;
            long latest = getTimePeriod(getItemCount() - 1).getSerialIndex();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[117]++;
            boolean removed = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[118]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[10]++;


int CodeCoverConditionCoverageHelper_C25;
            while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 (((latest - getTimePeriod(0).getSerialIndex()) 
                    > this.maximumItemAge) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[10]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[11]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[12]++;
}
                this.data.remove(0);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[119]++;
                removed = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[120]++;
            }
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[121]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[43]++;
                fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[122]++;

            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[42]++;}
    }

    /**
     * Age items in the series.  Ensure that the timespan from the supplied 
     * time to the oldest record in the series does not exceed history count.  
     * oldest items will be removed if required.
     *
     * @param latest  the time to be compared against when aging data 
     *     (specified in milliseconds).
     * @param notify  controls whether or not a {@link SeriesChangeEvent} is 
     *                sent to registered listeners IF any items are removed.
     */
    public void removeAgedItems(long latest, boolean notify) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[123]++;
        
        // find the serial index of the period specified by 'latest'
        long index = Long.MAX_VALUE;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[124]++;
boolean CodeCoverTryBranchHelper_Try1 = false; 
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[125]++;
            Method m = RegularTimePeriod.class.getDeclaredMethod(
                    "createInstance", new Class[] {Class.class, Date.class, 
                    TimeZone.class});
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[126]++;
            RegularTimePeriod newest = (RegularTimePeriod) m.invoke(
                    this.timePeriodClass, new Object[] {this.timePeriodClass,
                            new Date(latest), TimeZone.getDefault()});
            index = newest.getSerialIndex();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[127]++;
        }
        catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[46]++;
            e.printStackTrace();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[128]++;
        }
        catch (IllegalAccessException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[47]++;
            e.printStackTrace();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[129]++;
        }
        catch (InvocationTargetException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[48]++;
            e.printStackTrace();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[130]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[45]++;
}
  }
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[131]++;
        
        // check if there are any values earlier than specified by the history 
        // count...
        boolean removed = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[132]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
        while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((getItemCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 (((index 
                - getTimePeriod(0).getSerialIndex()) > this.maximumItemAge) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[13]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[14]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[15]++;
}
            this.data.remove(0);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[133]++;
            removed = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[134]++;
        }
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[135]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[49]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[136]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[50]++;}
    }

    /**
     * Removes all data items from the series and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     */
    public void clear() {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[137]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.data.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[51]++;
            this.data.clear();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[138]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[139]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[52]++;}
    }

    /**
     * Deletes the data item for the given time period and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.  If there is no
     * item with the specified time period, this method does nothing.
     *
     * @param period  the period of the item to delete (<code>null</code> not 
     *                permitted).
     */
    public void delete(RegularTimePeriod period) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[140]++;
        int index = getIndex(period);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[141]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[53]++;
            this.data.remove(index);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[142]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[143]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[54]++;}
    }

    /**
     * Deletes data from start until end index (end inclusive).
     *
     * @param start  the index of the first period to delete.
     * @param end  the index of the last period to delete.
     */
    public void delete(int start, int end) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[144]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((end < start) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[55]++;
            throw new IllegalArgumentException("Requires start <= end.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[56]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[145]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[16]++;


int CodeCoverConditionCoverageHelper_C32;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i <= (end - start)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[16]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[17]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[18]++;
}
            this.data.remove(start);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[146]++;
        }
        fireSeriesChanged();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[147]++;
    }

    /**
     * Returns a clone of the time series.
     * <P>
     * Notes:
     * <ul>
     *   <li>no need to clone the domain and range descriptions, since String 
     *     object is immutable;</li>
     *   <li>we pass over to the more general method clone(start, end).</li>
     * </ul>
     *
     * @return A clone of the time series.
     * 
     * @throws CloneNotSupportedException not thrown by this class, but 
     *         subclasses may differ.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[148]++;
        TimeSeries clone = (TimeSeries) super.clone();
        clone.data = (List) ObjectUtilities.deepClone(this.data);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[149]++;
        return clone;
    }

    /**
     * Creates a new timeseries by copying a subset of the data in this time
     * series.
     *
     * @param start  the index of the first time period to copy.
     * @param end  the index of the last time period to copy.
     *
     * @return A series containing a copy of this times series from start until
     *         end.
     * 
     * @throws CloneNotSupportedException if there is a cloning problem.
     */
    public TimeSeries createCopy(int start, int end) 
        throws CloneNotSupportedException {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[150]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[57]++;
            throw new IllegalArgumentException("Requires start >= 0.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[58]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[151]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((end < start) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[59]++;
            throw new IllegalArgumentException("Requires start <= end.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[60]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[152]++;
        TimeSeries copy = (TimeSeries) super.clone();

        copy.data = new java.util.ArrayList();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[153]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[154]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.data.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[61]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[155]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[19]++;


int CodeCoverConditionCoverageHelper_C36;
            for (int index = start;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((index <= end) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[19]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[20]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[21]++;
}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[156]++;
                TimeSeriesDataItem item 
                    = (TimeSeriesDataItem) this.data.get(index);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[157]++;
                TimeSeriesDataItem clone = (TimeSeriesDataItem) item.clone();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[158]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    copy.add(clone);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[159]++;
                }
                catch (SeriesException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[64]++;
                    e.printStackTrace();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[160]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[63]++;
}
  }
            }

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[62]++;}
        return copy;
    }

    /**
     * Creates a new timeseries by copying a subset of the data in this time 
     * series.
     *
     * @param start  the first time period to copy.
     * @param end  the last time period to copy.
     *
     * @return A time series containing a copy of this time series from start 
     *         until end.
     * 
     * @throws CloneNotSupportedException if there is a cloning problem.
     */
    public TimeSeries createCopy(RegularTimePeriod start, RegularTimePeriod end)
        throws CloneNotSupportedException {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[161]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((start == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[65]++;
            throw new IllegalArgumentException("Null 'start' argument.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[66]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[162]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((end == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[67]++;
            throw new IllegalArgumentException("Null 'end' argument.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[68]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[163]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((start.compareTo(end) > 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[69]++;
            throw new IllegalArgumentException(
                    "Requires start on or before end.");

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[70]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[164]++;
        boolean emptyRange = false;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[165]++;
        int startIndex = getIndex(start);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[166]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((startIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[71]++;
            startIndex = -(startIndex + 1);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[167]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[168]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((startIndex == this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[73]++;
                emptyRange = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[169]++;
  // start is after last data item
            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[74]++;}

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[72]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[170]++;
        int endIndex = getIndex(end);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[171]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((endIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[75]++;             // end period is not in original series
            endIndex = -(endIndex + 1);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[172]++; // this is first item AFTER end period
            endIndex = endIndex - 1;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[173]++;
    // so this is last item BEFORE end 
        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[76]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[174]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((endIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[77]++;
            emptyRange = true;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[175]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[78]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[176]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((emptyRange) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[79]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[177]++;
            TimeSeries copy = (TimeSeries) super.clone();
            copy.data = new java.util.ArrayList();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[178]++;
            return copy;

        }
        else {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[80]++;
            return createCopy(startIndex, endIndex);
        }

    }

    /**
     * Tests the series for equality with an arbitrary object.
     *
     * @param object  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[179]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((object == this) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[81]++;
            return true;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[82]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[180]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((object instanceof TimeSeries) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((super.equals(object)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[84]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[181]++;
        TimeSeries s = (TimeSeries) object;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[182]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            getDomainDescription(), s.getDomainDescription()
        )) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[86]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[183]++;
int CodeCoverConditionCoverageHelper_C48;

        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            getRangeDescription(), s.getRangeDescription()
        )) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[87]++;
            return false;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[88]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[184]++;
int CodeCoverConditionCoverageHelper_C49;

        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((getClass().equals(s.getClass())) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[90]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[185]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((getMaximumItemAge() != s.getMaximumItemAge()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[92]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[186]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((getMaximumItemCount() != s.getMaximumItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[93]++;
            return false;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[94]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[187]++;

        int count = getItemCount();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[188]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((count != s.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[95]++;
            return false;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[96]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[189]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[22]++;


int CodeCoverConditionCoverageHelper_C53;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[22]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[23]--;
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.loops[24]++;
}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[190]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((getDataItem(i).equals(s.getDataItem(i))) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[97]++;
                return false;

            } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[98]++;}
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return The hashcode
     */
    public int hashCode() {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[191]++;
        int result = super.hashCode();
        result = 29 * result + (this.domain != null ? this.domain.hashCode() 
                : 0);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[192]++;
        result = 29 * result + (this.range != null ? this.range.hashCode() : 0);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[193]++;
        result = 29 * result + (this.timePeriodClass != null 
                ? this.timePeriodClass.hashCode() : 0);
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[194]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[195]++;
        // it is too slow to look at every data item, so let's just look at
        // the first, middle and last items...
        int count = getItemCount();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[196]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[99]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[197]++;
            TimeSeriesDataItem item = getDataItem(0);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[198]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[100]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[199]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[101]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[200]++;
            TimeSeriesDataItem item = getDataItem(count - 1);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[201]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[102]++;}
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[202]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((count > 2) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[103]++;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[203]++;
            TimeSeriesDataItem item = getDataItem(count / 2);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[204]++;

        } else {
  CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.branches[104]++;}
        result = 29 * result + this.maximumItemCount;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[205]++;
        result = 29 * result + (int) this.maximumItemAge;
CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox.statements[206]++;
        return result;
    }

}

class CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox ());
  }
    public static long[] statements = new long[207];
    public static long[] branches = new long[105];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[58];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimeSeries.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 57; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$paru73xuwkh7ledzoo2j2ox () {
    super("org.jfree.data.time.TimeSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 206; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 104; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 57; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.TimeSeries.java");
      for (int i = 1; i <= 206; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 104; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 57; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

