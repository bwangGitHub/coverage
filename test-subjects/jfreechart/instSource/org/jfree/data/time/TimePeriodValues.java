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
 * ---------------------
 * TimePeriodValues.java
 * ---------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Apr-2003 : Version 1 (DG);
 * 30-Jul-2003 : Added clone and equals methods while testing (DG);
 * 11-Mar-2005 : Fixed bug in bounds recalculation - see bug report 
 *               1161329 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 03-Oct-2006 : Fixed NullPointerException in equals(), fire change event in 
 *               add() method, updated API docs (DG);
 *
 */

package org.jfree.data.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.general.Series;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesException;
import org.jfree.util.ObjectUtilities;

/**
 * A structure containing zero, one or many {@link TimePeriodValue} instances.  
 * The time periods can overlap, and are maintained in the order that they are 
 * added to the collection.
 * <p>
 * This is similar to the {@link TimeSeries} class, except that the time 
 * periods can have irregular lengths.
 */
public class TimePeriodValues extends Series implements Serializable {
  static {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -2210593619794989709L;
  static {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[1]++;
  }
    
    /** Default value for the domain description. */
    protected static final String DEFAULT_DOMAIN_DESCRIPTION = "Time";
  static {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[2]++;
  }

    /** Default value for the range description. */
    protected static final String DEFAULT_RANGE_DESCRIPTION = "Value";
  static {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[3]++;
  }

    /** A description of the domain. */
    private String domain;

    /** A description of the range. */
    private String range;

    /** The list of data pairs in the series. */
    private List data;

    /** Index of the time period with the minimum start milliseconds. */
    private int minStartIndex = -1;
  {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[4]++;
  }
    
    /** Index of the time period with the maximum start milliseconds. */
    private int maxStartIndex = -1;
  {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[5]++;
  }
    
    /** Index of the time period with the minimum middle milliseconds. */
    private int minMiddleIndex = -1;
  {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[6]++;
  }
    
    /** Index of the time period with the maximum middle milliseconds. */
    private int maxMiddleIndex = -1;
  {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[7]++;
  }
    
    /** Index of the time period with the minimum end milliseconds. */
    private int minEndIndex = -1;
  {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[8]++;
  }
    
    /** Index of the time period with the maximum end milliseconds. */
    private int maxEndIndex = -1;
  {
    CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[9]++;
  }

    /**
     * Creates a new (empty) collection of time period values.
     *
     * @param name  the name of the series (<code>null</code> not permitted).
     */
    public TimePeriodValues(String name) {
        this(name, DEFAULT_DOMAIN_DESCRIPTION, DEFAULT_RANGE_DESCRIPTION);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[10]++;
    }

    /**
     * Creates a new time series that contains no data.
     * <P>
     * Descriptions can be specified for the domain and range.  One situation
     * where this is helpful is when generating a chart for the time series -
     * axis labels can be taken from the domain and range description.
     *
     * @param name  the name of the series (<code>null</code> not permitted).
     * @param domain  the domain description.
     * @param range  the range description.
     */
    public TimePeriodValues(String name, String domain, String range) {
        super(name);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[11]++;
        this.domain = domain;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[12]++;
        this.range = range;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[13]++;
        this.data = new ArrayList();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[14]++;
    }

    /**
     * Returns the domain description.
     *
     * @return The domain description (possibly <code>null</code>).
     * 
     * @see #getRangeDescription()
     * @see #setDomainDescription(String)
     */
    public String getDomainDescription() {
        return this.domain;
    }

    /**
     * Sets the domain description and fires a property change event (with the
     * property name <code>Domain</code> if the description changes).
     *
     * @param description  the new description (<code>null</code> permitted).
     * 
     * @see #getDomainDescription()
     */
    public void setDomainDescription(String description) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[15]++;
        String old = this.domain;
        this.domain = description;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[16]++;
        firePropertyChange("Domain", old, description);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[17]++;
    }

    /**
     * Returns the range description.
     *
     * @return The range description (possibly <code>null</code>).
     * 
     * @see #getDomainDescription()
     * @see #setRangeDescription(String)
     */
    public String getRangeDescription() {
        return this.range;
    }

    /**
     * Sets the range description and fires a property change event with the
     * name <code>Range</code>.
     *
     * @param description  the new description (<code>null</code> permitted).
     * 
     * @see #getRangeDescription()
     */
    public void setRangeDescription(String description) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[18]++;
        String old = this.range;
        this.range = description;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[19]++;
        firePropertyChange("Range", old, description);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[20]++;
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
     * Returns one data item for the series.
     *
     * @param index  the item index (in the range <code>0</code> to 
     *     <code>getItemCount() - 1</code>).
     *
     * @return One data item for the series.
     */
    public TimePeriodValue getDataItem(int index) {
        return (TimePeriodValue) this.data.get(index);
    }

    /**
     * Returns the time period at the specified index.
     *
     * @param index  the item index (in the range <code>0</code> to 
     *     <code>getItemCount() - 1</code>).
     *
     * @return The time period at the specified index.
     * 
     * @see #getDataItem(int)
     */
    public TimePeriod getTimePeriod(int index) {
        return getDataItem(index).getPeriod();
    }

    /**
     * Returns the value at the specified index.
     *
     * @param index  the item index (in the range <code>0</code> to 
     *     <code>getItemCount() - 1</code>).
     *
     * @return The value at the specified index (possibly <code>null</code>).
     * 
     * @see #getDataItem(int)
     */
    public Number getValue(int index) {
        return getDataItem(index).getValue();
    }

    /**
     * Adds a data item to the series and sends a {@link SeriesChangeEvent} to
     * all registered listeners.
     *
     * @param item  the item (<code>null</code> not permitted).
     */
    public void add(TimePeriodValue item) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[21]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[1]++;
            throw new IllegalArgumentException("Null item not allowed.");

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[2]++;}
        this.data.add(item);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[22]++;
        updateBounds(item.getPeriod(), this.data.size() - 1);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[23]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[24]++;
    }
    
    /**
     * Update the index values for the maximum and minimum bounds.
     * 
     * @param period  the time period.
     * @param index  the index of the time period.
     */
    private void updateBounds(TimePeriod period, int index) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[25]++;
        
        long start = period.getStart().getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[26]++;
        long end = period.getEnd().getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[27]++;
        long middle = start + ((end - start) / 2);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[28]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.minStartIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[3]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[29]++;
            long minStart = getDataItem(this.minStartIndex).getPeriod()
                .getStart().getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((start < minStart) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[5]++;
                this.minStartIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[31]++;
           
            } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[6]++;}

        }
        else {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[4]++;
            this.minStartIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[32]++;
        }
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.maxStartIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[7]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[34]++;
            long maxStart = getDataItem(this.maxStartIndex).getPeriod()
                .getStart().getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((start > maxStart) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[9]++;
                this.maxStartIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[36]++;
           
            } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[10]++;}

        }
        else {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[8]++;
            this.maxStartIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[37]++;
        }
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.minMiddleIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[11]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[39]++;
            long s = getDataItem(this.minMiddleIndex).getPeriod().getStart()
                .getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[40]++;
            long e = getDataItem(this.minMiddleIndex).getPeriod().getEnd()
                .getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[41]++;
            long minMiddle = s + (e - s) / 2;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((middle < minMiddle) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[13]++;
                this.minMiddleIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[43]++;
           
            } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[14]++;}

        }
        else {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[12]++;
            this.minMiddleIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[44]++;
        }
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.maxMiddleIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[15]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[46]++;
            long s = getDataItem(this.minMiddleIndex).getPeriod().getStart()
                .getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[47]++;
            long e = getDataItem(this.minMiddleIndex).getPeriod().getEnd()
                .getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[48]++;
            long maxMiddle = s + (e - s) / 2;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((middle > maxMiddle) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[17]++;
                this.maxMiddleIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[50]++;
           
            } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[18]++;}

        }
        else {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[16]++;
            this.maxMiddleIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[51]++;
        }
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[52]++;
int CodeCoverConditionCoverageHelper_C10;
        
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.minEndIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[19]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[53]++;
            long minEnd = getDataItem(this.minEndIndex).getPeriod().getEnd()
                .getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[54]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((end < minEnd) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[21]++;
                this.minEndIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[55]++;
           
            } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[22]++;}

        }
        else {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[20]++;
            this.minEndIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[56]++;
        }
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[57]++;
int CodeCoverConditionCoverageHelper_C12;
       
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.maxEndIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[23]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[58]++;
            long maxEnd = getDataItem(this.maxEndIndex).getPeriod().getEnd()
                .getTime();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[59]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((end > maxEnd) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[25]++;
                this.maxEndIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[60]++;
           
            } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[26]++;}

        }
        else {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[24]++;
            this.maxEndIndex = index;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[61]++;
        }
        
    }
    
    /**
     * Recalculates the bounds for the collection of items.
     */
    private void recalculateBounds() {
        this.minStartIndex = -1;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[62]++;
        this.minMiddleIndex = -1;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[63]++;
        this.minEndIndex = -1;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[64]++;
        this.maxStartIndex = -1;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[65]++;
        this.maxMiddleIndex = -1;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[66]++;
        this.maxEndIndex = -1;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[67]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[68]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[1]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[1]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[2]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[3]++;
}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[69]++;
            TimePeriodValue tpv = (TimePeriodValue) this.data.get(i);
            updateBounds(tpv.getPeriod(), i);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[70]++;
        }
    }

    /**
     * Adds a new data item to the series and sends a {@link SeriesChangeEvent}
     * to all registered listeners.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value.
     * 
     * @see #add(TimePeriod, Number)
     */
    public void add(TimePeriod period, double value) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[71]++;
        TimePeriodValue item = new TimePeriodValue(period, value);
        add(item);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[72]++;
    }

    /**
     * Adds a new data item to the series and sends a {@link SeriesChangeEvent}
     * to all registered listeners.
     *
     * @param period  the time period (<code>null</code> not permitted).
     * @param value  the value (<code>null</code> permitted).
     */
    public void add(TimePeriod period, Number value) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[73]++;
        TimePeriodValue item = new TimePeriodValue(period, value);
        add(item);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[74]++;
    }

    /**
     * Updates (changes) the value of a data item and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param index  the index of the data item to update.
     * @param value  the new value (<code>null</code> not permitted).
     */
    public void update(int index, Number value) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[75]++;
        TimePeriodValue item = getDataItem(index);
        item.setValue(value);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[76]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[77]++;
    }

    /**
     * Deletes data from start until end index (end inclusive) and sends a
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param start  the index of the first period to delete.
     * @param end  the index of the last period to delete.
     */
    public void delete(int start, int end) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[78]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[4]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i <= (end - start)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[4]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[5]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[6]++;
}
            this.data.remove(start);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[79]++;
        }
        recalculateBounds();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[80]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[81]++;
    }
    
    /**
     * Tests the series for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[82]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[27]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[28]++;}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[83]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj instanceof TimePeriodValues) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[30]++;}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[84]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[32]++;}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[85]++;
        TimePeriodValues that = (TimePeriodValues) obj;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[86]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.getDomainDescription(), 
                that.getDomainDescription())) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[34]++;}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[87]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.getRangeDescription(), 
                that.getRangeDescription())) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[36]++;}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[88]++;
        int count = getItemCount();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[89]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((count != that.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[38]++;}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[90]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[7]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[8]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[9]++;
}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[91]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getDataItem(i).equals(that.getDataItem(i))) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[39]++;
                return false;

            } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[40]++;}
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return The hashcode
     */
    public int hashCode() {
        int result;
        result = (this.domain != null ? this.domain.hashCode() : 0);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[92]++;
        result = 29 * result + (this.range != null ? this.range.hashCode() : 0);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[93]++;
        result = 29 * result + this.data.hashCode();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[94]++;
        result = 29 * result + this.minStartIndex;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[95]++;
        result = 29 * result + this.maxStartIndex;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[96]++;
        result = 29 * result + this.minMiddleIndex;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[97]++;
        result = 29 * result + this.maxMiddleIndex;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[98]++;
        result = 29 * result + this.minEndIndex;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[99]++;
        result = 29 * result + this.maxEndIndex;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[100]++;
        return result;
    }

    /**
     * Returns a clone of the collection.
     * <P>
     * Notes:
     * <ul>
     *   <li>no need to clone the domain and range descriptions, since String 
     *       object is immutable;</li>
     *   <li>we pass over to the more general method createCopy(start, end).
     *   </li>
     * </ul>
     *
     * @return A clone of the time series.
     * 
     * @throws CloneNotSupportedException if there is a cloning problem.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[101]++;
        Object clone = createCopy(0, getItemCount() - 1);
        return clone;
    }

    /**
     * Creates a new instance by copying a subset of the data in this 
     * collection.
     *
     * @param start  the index of the first item to copy.
     * @param end  the index of the last item to copy.
     *
     * @return A copy of a subset of the items.
     * 
     * @throws CloneNotSupportedException if there is a cloning problem.
     */
    public TimePeriodValues createCopy(int start, int end) 
        throws CloneNotSupportedException {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[102]++;

        TimePeriodValues copy = (TimePeriodValues) super.clone();

        copy.data = new ArrayList();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[103]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[104]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.data.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[41]++;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[105]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[10]++;


int CodeCoverConditionCoverageHelper_C25;
            for (int index = start;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((index <= end) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[10]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[11]--;
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.loops[12]++;
}
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[106]++;
                TimePeriodValue item = (TimePeriodValue) this.data.get(index);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[107]++;
                TimePeriodValue clone = (TimePeriodValue) item.clone();
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[108]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    copy.add(clone);
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[109]++;
                }
                catch (SeriesException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[44]++;
                    System.err.println("Failed to add cloned item.");
CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.statements[110]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[43]++;
}
  }
            }

        } else {
  CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd.branches[42]++;}
        return copy;

    }
    
    /**
     * Returns the index of the time period with the minimum start milliseconds.
     * 
     * @return The index.
     */
    public int getMinStartIndex() {
        return this.minStartIndex;
    }
    
    /**
     * Returns the index of the time period with the maximum start milliseconds.
     * 
     * @return The index.
     */
    public int getMaxStartIndex() {
        return this.maxStartIndex;
    }

    /**
     * Returns the index of the time period with the minimum middle 
     * milliseconds.
     * 
     * @return The index.
     */
    public int getMinMiddleIndex() {
        return this.minMiddleIndex;
    }
    
    /**
     * Returns the index of the time period with the maximum middle 
     * milliseconds.
     * 
     * @return The index.
     */
    public int getMaxMiddleIndex() {
        return this.maxMiddleIndex;
    }

    /**
     * Returns the index of the time period with the minimum end milliseconds.
     * 
     * @return The index.
     */
    public int getMinEndIndex() {
        return this.minEndIndex;
    }
    
    /**
     * Returns the index of the time period with the maximum end milliseconds.
     * 
     * @return The index.
     */
    public int getMaxEndIndex() {
        return this.maxEndIndex;
    }

}

class CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd ());
  }
    public static long[] statements = new long[111];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "org.jfree.data.time.TimePeriodValues.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$1y47zx9h1vrovlulv4nppcnoptklya0dd () {
    super("org.jfree.data.time.TimePeriodValues.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 110; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.TimePeriodValues.java");
      for (int i = 1; i <= 110; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

