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
 * --------------------------------
 * DynamicTimeSeriesCollection.java
 * --------------------------------
 * (C) Copyright 2002-2007, by I. H. Thomae and Contributors.
 *
 * Original Author:  I. H. Thomae (ithomae@ists.dartmouth.edu);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 22-Nov-2002 : Initial version completed
 *    Jan 2003 : Optimized advanceTime(), added implemnt'n of RangeInfo intfc
 *               (using cached values for min, max, and range); also added
 *               getOldestIndex() and getNewestIndex() ftns so client classes
 *               can use this class as the master "index authority".
 * 22-Jan-2003 : Made this class stand on its own, rather than extending
 *               class FastTimeSeriesCollection
 * 31-Jan-2003 : Changed TimePeriod --> RegularTimePeriod (DG);
 * 13-Mar-2003 : Moved to com.jrefinery.data.time package (DG);
 * 29-Apr-2003 : Added small change to appendData method, from Irv Thomae (DG);
 * 19-Sep-2003 : Added new appendData method, from Irv Thomae (DG);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset.  This also required a
 *               change to the return type of the getY() method - I'm slightly
 *               unsure of the implications of this, so it might require some
 *               further amendment (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 11-Jan-2004 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 
 */

package org.jfree.data.time;

import java.util.Calendar;
import java.util.TimeZone;

import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;

/**
 * A dynamic dataset.
 * <p>
 * Like FastTimeSeriesCollection, this class is a functional replacement
 * for JFreeChart's TimeSeriesCollection _and_ TimeSeries classes.
 * FastTimeSeriesCollection is appropriate for a fixed time range; for
 * real-time applications this subclass adds the ability to append new
 * data and discard the oldest.
 * In this class, the arrays used in FastTimeSeriesCollection become FIFO's.
 * NOTE:As presented here, all data is assumed >= 0, an assumption which is
 * embodied only in methods associated with interface RangeInfo.
 */
public class DynamicTimeSeriesCollection extends AbstractIntervalXYDataset
                                         implements IntervalXYDataset,
                                                    DomainInfo,
                                                    RangeInfo {
  static {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.ping();
  }


    /** 
     * Useful constant for controlling the x-value returned for a time 
     * period. 
     */
    public static final int START = 0;
  static {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[1]++;
  }

    /** 
     * Useful constant for controlling the x-value returned for a time period. 
     */
    public static final int MIDDLE = 1;
  static {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[2]++;
  }

    /** 
     * Useful constant for controlling the x-value returned for a time period. 
     */
    public static final int END = 2;
  static {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[3]++;
  }

    /** The maximum number of items for each series (can be overridden). */
    private int maximumItemCount = 2000;
  {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[4]++;
  }  // an arbitrary safe default value

    /** The history count. */
    protected int historyCount;

    /** Storage for the series keys. */
    private Comparable[] seriesKeys;

    /** The time period class - barely used, and could be removed (DG). */
    private Class timePeriodClass = Minute.class;
  {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[5]++;
  }   // default value;

    /** Storage for the x-values. */
    protected RegularTimePeriod[] pointsInTime;

    /** The number of series. */
    private int seriesCount;

    /**
     * A wrapper for a fixed array of float values.
     */
    protected class ValueSequence {

        /** Storage for the float values. */
        float[] dataPoints;

        /**
         * Default constructor:
         */
        public ValueSequence() {
            this(DynamicTimeSeriesCollection.this.maximumItemCount);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[6]++;
        }

        /**
         * Creates a sequence with the specified length.
         *
         * @param length  the length.
         */
        public ValueSequence(int length) {
            this.dataPoints = new float[length];
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[7]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[1]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[2]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[3]++;
}
                this.dataPoints[i] = 0.0f;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[9]++;
            }
        }

        /**
         * Enters data into the storage array.
         *
         * @param index  the index.
         * @param value  the value.
         */
        public void enterData(int index, float value) {
            this.dataPoints[index] = value;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[10]++;
        }

        /**
         * Returns a value from the storage array.
         *
         * @param index  the index.
         *
         * @return The value.
         */
        public float getData(int index) {
            return this.dataPoints[index];
        }
    }

    /** An array for storing the objects that represent each series. */
    protected ValueSequence[] valueHistory;

    /** A working calendar (to recycle) */
    protected Calendar workingCalendar;

    /** 
     * The position within a time period to return as the x-value (START, 
     * MIDDLE or END). 
     */
    private int position;

    /**
     * A flag that indicates that the domain is 'points in time'.  If this flag 
     * is true, only the x-value is used to determine the range of values in 
     * the domain, the start and end x-values are ignored.
     */
    private boolean domainIsPointsInTime;

    /** index for mapping: points to the oldest valid time & data. */
    private int oldestAt;  // as a class variable, initializes == 0

    /** Index of the newest data item. */
    private int newestAt;

    // cached values used for interface DomainInfo:

    /** the # of msec by which time advances. */
    private long deltaTime;

    /** Cached domain start (for use by DomainInfo). */
    private Long domainStart;

    /** Cached domain end (for use by DomainInfo). */
    private Long domainEnd;

    /** Cached domain range (for use by DomainInfo). */
    private Range domainRange;

    // Cached values used for interface RangeInfo: (note minValue pinned at 0)
    //   A single set of extrema covers the entire SeriesCollection

    /** The minimum value. */
    private Float minValue = new Float(0.0f);
  {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[11]++;
  }

    /** The maximum value. */
    private Float maxValue = null;
  {
    CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[12]++;
  }

    /** The value range. */
    private Range valueRange;  // autoinit's to null.

    /**
     * Constructs a dataset with capacity for N series, tied to default 
     * timezone.
     *
     * @param nSeries the number of series to be accommodated.
     * @param nMoments the number of TimePeriods to be spanned.
     */
    public DynamicTimeSeriesCollection(int nSeries, int nMoments) {

        this(nSeries, nMoments, new Millisecond(), TimeZone.getDefault());
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[13]++;
        this.newestAt = nMoments - 1;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[14]++;

    }

    /**
     * Constructs an empty dataset, tied to a specific timezone.
     *
     * @param nSeries the number of series to be accommodated
     * @param nMoments the number of TimePeriods to be spanned
     * @param zone the timezone.
     */
    public DynamicTimeSeriesCollection(int nSeries, int nMoments, 
                                       TimeZone zone) {
        this(nSeries, nMoments, new Millisecond(), zone);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[15]++;
        this.newestAt = nMoments - 1;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[16]++;
    }

    /**
     * Creates a new dataset.
     *
     * @param nSeries  the number of series.
     * @param nMoments  the number of items per series.
     * @param timeSample  a time period sample.
     */
    public DynamicTimeSeriesCollection(int nSeries,
                                       int nMoments,
                                       RegularTimePeriod timeSample) {
        this(nSeries, nMoments, timeSample, TimeZone.getDefault());
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[17]++;
    }

    /**
     * Creates a new dataset.
     *
     * @param nSeries  the number of series.
     * @param nMoments  the number of items per series.
     * @param timeSample  a time period sample.
     * @param zone  the time zone.
     */
    public DynamicTimeSeriesCollection(int nSeries,
                                       int nMoments,
                                       RegularTimePeriod timeSample,
                                       TimeZone zone) {

        // the first initialization must precede creation of the ValueSet array:
        this.maximumItemCount = nMoments;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[18]++;  // establishes length of each array
        this.historyCount = nMoments;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[19]++;
        this.seriesKeys = new Comparable[nSeries];
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[20]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        // initialize the members of "seriesNames" array so they won't be null:
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < nSeries) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[4]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[5]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[6]++;
}
            this.seriesKeys[i] = "";
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[22]++;
        }
        this.newestAt = nMoments - 1;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[23]++;
        this.valueHistory = new ValueSequence[nSeries];
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[24]++;
        this.timePeriodClass = timeSample.getClass();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[25]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;

        /// Expand the following for all defined TimePeriods:
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.timePeriodClass == Second.class) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[1]++;
            this.pointsInTime = new Second[nMoments];
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[27]++;

        }
        else {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[2]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[28]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.timePeriodClass == Minute.class) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[3]++;
            this.pointsInTime = new Minute[nMoments];
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[29]++;

        }
        else {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[4]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[30]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.timePeriodClass == Hour.class) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[5]++;
            this.pointsInTime = new Hour[nMoments];
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[31]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[6]++;}
}
}
        ///  .. etc....
        this.workingCalendar = Calendar.getInstance(zone);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[32]++;
        this.position = START;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[33]++;
        this.domainIsPointsInTime = true;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[34]++;
    }

    /**
     * Fill the pointsInTime with times using TimePeriod.next():
     * Will silently return if the time array was already populated.
     *
     * Also computes the data cached for later use by
     * methods implementing the DomainInfo interface:
     *
     * @param start  the start.
     *
     * @return ??.
     */
    public synchronized long setTimeBase(RegularTimePeriod start) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[35]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.pointsInTime[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[7]++;
            this.pointsInTime[0] = start;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[36]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[37]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
            for (int i = 1;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < this.historyCount) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[7]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[8]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[9]++;
}
                this.pointsInTime[i] = this.pointsInTime[i - 1].next();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[38]++;
            }

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[8]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[39]++;
        long oldestL = this.pointsInTime[0].getFirstMillisecond(
            this.workingCalendar
        );
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[40]++;
        long nextL = this.pointsInTime[1].getFirstMillisecond(
            this.workingCalendar
        );
        this.deltaTime = nextL - oldestL;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[41]++;
        this.oldestAt = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[42]++;
        this.newestAt = this.historyCount - 1;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[43]++;
        findDomainLimits();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[44]++;
        return this.deltaTime;

    }

    /**
     * Finds the domain limits.  Note: this doesn't need to be synchronized 
     * because it's called from within another method that already is.
     */
    protected void findDomainLimits() {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[45]++;

        long startL = getOldestTime().getFirstMillisecond(this.workingCalendar);
        long endL;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.domainIsPointsInTime) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[9]++;
            endL = getNewestTime().getFirstMillisecond(this.workingCalendar);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[47]++;

        }
        else {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[10]++;
            endL = getNewestTime().getLastMillisecond(this.workingCalendar);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[48]++;
        }
        this.domainStart = new Long(startL);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[49]++;
        this.domainEnd = new Long(endL);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[50]++;
        this.domainRange = new Range(startL, endL);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[51]++;

    }

    /**
     * Returns the x position type (START, MIDDLE or END).
     *
     * @return The x position type.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Sets the x position type (START, MIDDLE or END).
     *
     * @param position The x position type.
     */
    public void setPosition(int position) {
        this.position = position;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[52]++;
    }

    /**
     * Adds a series to the dataset.  Only the y-values are supplied, the 
     * x-values are specified elsewhere.
     *
     * @param values  the y-values.
     * @param seriesNumber  the series index (zero-based).
     * @param seriesKey  the series key.
     *
     * Use this as-is during setup only, or add the synchronized keyword around 
     * the copy loop.
     */
    public void addSeries(float[] values,
                          int seriesNumber, Comparable seriesKey) {

        invalidateRangeInfo();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[53]++;
        int i;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[11]++;
            throw new IllegalArgumentException("TimeSeriesDataset.addSeries(): "
                + "cannot add null array of values.");

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[12]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((seriesNumber >= this.valueHistory.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[13]++;
            throw new IllegalArgumentException("TimeSeriesDataset.addSeries(): "
                + "cannot add more series than specified in c'tor");

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[14]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.valueHistory[seriesNumber] == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[15]++;
            this.valueHistory[seriesNumber] 
                = new ValueSequence(this.historyCount);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[57]++;
            this.seriesCount++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[58]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[16]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[59]++;   
        // But if that series array already exists, just overwrite its contents

        // Avoid IndexOutOfBoundsException:
        int srcLength = values.length;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[60]++;
        int copyLength = this.historyCount;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[61]++;
        boolean fillNeeded = false;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[62]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((srcLength < this.historyCount) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[17]++;
            fillNeeded = true;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[63]++;
            copyLength = srcLength;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[64]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[18]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[65]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;
        //{
        for (i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < copyLength) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[10]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[11]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[12]++;
} // deep copy from values[], caller 
                                           // can safely discard that array
            this.valueHistory[seriesNumber].enterData(i, values[i]);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[66]++;
        }
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((fillNeeded) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[19]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[68]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
            for (i = copyLength;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < this.historyCount) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[13]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[14]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[15]++;
}
                this.valueHistory[seriesNumber].enterData(i, 0.0f);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[69]++;
            }

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[20]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[70]++;
int CodeCoverConditionCoverageHelper_C16;
      //}
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((seriesKey != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[21]++;
            this.seriesKeys[seriesNumber] = seriesKey;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[71]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[22]++;}
        fireSeriesChanged();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[72]++;

    }

    /**
     * Sets the name of a series.  If planning to add values individually.
     *
     * @param seriesNumber  the series.
     * @param key  the new key.
     */
    public void setSeriesKey(int seriesNumber, Comparable key) {
        this.seriesKeys[seriesNumber] = key;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[73]++;
    }

    /**
     * Adds a value to a series.
     *
     * @param seriesNumber  the series index.
     * @param index  ??.
     * @param value  the value.
     */
    public void addValue(int seriesNumber, int index, float value) {

        invalidateRangeInfo();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[74]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[75]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((seriesNumber >= this.valueHistory.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[23]++;
            throw new IllegalArgumentException(
                "TimeSeriesDataset.addValue(): series #"
                + seriesNumber + "unspecified in c'tor"
            );

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[24]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[76]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.valueHistory[seriesNumber] == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[25]++;
            this.valueHistory[seriesNumber] 
                = new ValueSequence(this.historyCount);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[77]++;
            this.seriesCount++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[78]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[26]++;}  
        // But if that series array already exists, just overwrite its contents
        //synchronized(this)
        //{
            this.valueHistory[seriesNumber].enterData(index, value);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[79]++;
        //}
        fireSeriesChanged();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[80]++;
    }

    /**
     * Returns the number of series in the collection.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.seriesCount;
    }

    /**
     * Returns the number of items in a series.
     * <p>
     * For this implementation, all series have the same number of items.
     *
     * @param series  the series index (zero-based).
     *
     * @return The item count.
     */
    public int getItemCount(int series) {  // all arrays equal length, 
                                           // so ignore argument:
        return this.historyCount;
    }

    // Methods for managing the FIFO's:

    /**
     * Re-map an index, for use in retrieving data.
     *
     * @param toFetch  the index.
     *
     * @return The translated index.
     */
    protected int translateGet(int toFetch) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.oldestAt == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[27]++;
            return toFetch;
  // no translation needed
        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[28]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[82]++;
        // else  [implicit here]
        int newIndex = toFetch + this.oldestAt;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[83]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((newIndex >= this.historyCount) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[29]++;
            newIndex -= this.historyCount;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[84]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[30]++;}
        return newIndex;
    }

    /**
     * Returns the actual index to a time offset by "delta" from newestAt.
     *
     * @param delta  the delta.
     *
     * @return The offset.
     */
    public int offsetFromNewest(int delta) {
        return wrapOffset(this.newestAt + delta);
    }

    /**
     * ??
     *
     * @param delta ??
     *
     * @return The offset.
     */
    public int offsetFromOldest(int delta) {
        return wrapOffset(this.oldestAt + delta);
    }

    /**
     * ??
     *
     * @param protoIndex  the index.
     *
     * @return The offset.
     */
    protected int wrapOffset(int protoIndex) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[85]++;
        int tmp = protoIndex;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[86]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((tmp >= this.historyCount) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[31]++;
            tmp -= this.historyCount;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[87]++;

        }
        else {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[32]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[88]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((tmp < 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[33]++;
            tmp += this.historyCount;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[89]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[34]++;}
}
        return tmp;
    }

    /**
     * Adjust the array offset as needed when a new time-period is added:
     * Increments the indices "oldestAt" and "newestAt", mod(array length),
     * zeroes the series values at newestAt, returns the new TimePeriod.
     *
     * @return The new time period.
     */
    public synchronized RegularTimePeriod advanceTime() {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[90]++;
        RegularTimePeriod nextInstant = this.pointsInTime[this.newestAt].next();
        this.newestAt = this.oldestAt;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[91]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[92]++;  // newestAt takes value previously held 
                                        // by oldestAT
        /*** 
         * The next 10 lines or so should be expanded if data can be negative 
         ***/
        // if the oldest data contained a maximum Y-value, invalidate the stored
        //   Y-max and Y-range data:
        boolean extremaChanged = false;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[93]++;
        float oldMax = 0.0f;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[94]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.maxValue != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[35]++;
            oldMax = this.maxValue.floatValue();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[95]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[36]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[96]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[16]++;


int CodeCoverConditionCoverageHelper_C24;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((s < getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[16]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[17]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[18]++;
}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[97]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.valueHistory[s].getData(this.oldestAt) == oldMax) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[37]++;
                extremaChanged = true;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[98]++;

            } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[38]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[99]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((extremaChanged) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[39]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[100]++;
                break;

            } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[40]++;}
        }
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[101]++;
int CodeCoverConditionCoverageHelper_C27;  /*** If data can be < 0, add code here to check the minimum    **/
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((extremaChanged) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[41]++;
            invalidateRangeInfo();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[102]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[42]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[103]++;
        //  wipe the next (about to be used) set of data slots
        float wiper = (float) 0.0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[104]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[19]++;


int CodeCoverConditionCoverageHelper_C28;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((s < getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[19]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[20]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[21]++;
}
            this.valueHistory[s].enterData(this.newestAt, wiper);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[105]++;
        }
        // Update the array of TimePeriods:
        this.pointsInTime[this.newestAt] = nextInstant;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[106]++;
        // Now advance "oldestAt", wrapping at end of the array
        this.oldestAt++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[107]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[108]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.oldestAt >= this.historyCount) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[43]++;
            this.oldestAt = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[109]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[44]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[110]++;
        // Update the domain limits:
        long startL = this.domainStart.longValue();  //(time is kept in msec)
        this.domainStart = new Long(startL + this.deltaTime);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[111]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[112]++;
        long endL = this.domainEnd.longValue();
        this.domainEnd = new Long(endL + this.deltaTime);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[113]++;
        this.domainRange = new Range(startL, endL);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[114]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[115]++;
        return nextInstant;
    }

    //  If data can be < 0, the next 2 methods should be modified

    /**
     * Invalidates the range info.
     */
    public void invalidateRangeInfo() {
        this.maxValue = null;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[116]++;
        this.valueRange = null;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[117]++;
    }

    /**
     * Returns the maximum value.
     *
     * @return The maximum value.
     */
    protected double findMaxValue() {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[118]++;
        double max = 0.0f;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[119]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[22]++;


int CodeCoverConditionCoverageHelper_C30;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((s < getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[22]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[23]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[24]++;
}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[120]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[25]++;


int CodeCoverConditionCoverageHelper_C31;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((i < this.historyCount) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[25]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[26]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[27]++;
}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[121]++;
                double tmp = getYValue(s, i);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[122]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((tmp > max) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[45]++;
                    max = tmp;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[123]++;

                } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[46]++;}
            }
        }
        return max;
    }

    /** End, positive-data-only code  **/

    /**
     * Returns the index of the oldest data item.
     *
     * @return The index.
     */
    public int getOldestIndex() {
        return this.oldestAt;
    }

    /**
     * Returns the index of the newest data item.
     *
     * @return The index.
     */
    public int getNewestIndex() {
        return this.newestAt;
    }

    // appendData() writes new data at the index position given by newestAt/
    // When adding new data dynamically, use advanceTime(), followed by this:
    /**
     * Appends new data.
     *
     * @param newData  the data.
     */
    public void appendData(float[] newData) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[124]++;
        int nDataPoints = newData.length;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[125]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((nDataPoints > this.valueHistory.length) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[47]++;
            throw new IllegalArgumentException(
               "More data than series to put them in"
            );

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[48]++;}
        int s;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[126]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[28]++;


int CodeCoverConditionCoverageHelper_C34;   // index to select the "series"
        for (s = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((s < nDataPoints) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[28]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[29]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[30]++;
}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[127]++;
int CodeCoverConditionCoverageHelper_C35;
            // check whether the "valueHistory" array member exists; if not, 
            // create them:
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.valueHistory[s] == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[49]++;
                this.valueHistory[s] = new ValueSequence(this.historyCount);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[128]++;

            } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[50]++;}
            this.valueHistory[s].enterData(this.newestAt, newData[s]);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[129]++;
        }
        fireSeriesChanged();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[130]++;
    }

    /**
     * Appends data at specified index, for loading up with data from file(s).
     *
     * @param  newData  the data
     * @param  insertionIndex  the index value at which to put it
     * @param  refresh  value of n in "refresh the display on every nth call"
     *                 (ignored if <= 0 )
     */
     public void appendData(float[] newData, int insertionIndex, int refresh) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[131]++;
         int nDataPoints = newData.length;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[132]++;
int CodeCoverConditionCoverageHelper_C36;
         if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((nDataPoints > this.valueHistory.length) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[51]++;
             throw new IllegalArgumentException(
                 "More data than series to put them " + "in"
             );

         } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[52]++;}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[133]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[31]++;


int CodeCoverConditionCoverageHelper_C37;
         for (int s = 0;(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((s < nDataPoints) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[31]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[32]--;
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.loops[33]++;
}
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[134]++;
int CodeCoverConditionCoverageHelper_C38;
             if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.valueHistory[s] == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[53]++;
                this.valueHistory[s] = new ValueSequence(this.historyCount);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[135]++;

             } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[54]++;}
             this.valueHistory[s].enterData(insertionIndex, newData[s]);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[136]++;
         }
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[137]++;
int CodeCoverConditionCoverageHelper_C39;
         if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((refresh > 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[55]++;
             insertionIndex++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[138]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[139]++;
int CodeCoverConditionCoverageHelper_C40;
             if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((insertionIndex % refresh == 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[57]++;
                 fireSeriesChanged();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[140]++;

             } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[58]++;}

         } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[56]++;}
    }

    /**
     * Returns the newest time.
     *
     * @return The newest time.
     */
    public RegularTimePeriod getNewestTime() {
        return this.pointsInTime[this.newestAt];
    }

    /**
     * Returns the oldest time.
     *
     * @return The oldest time.
     */
    public RegularTimePeriod getOldestTime() {
        return this.pointsInTime[this.oldestAt];
    }

    /**
     * Returns the x-value.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    // getXxx() ftns can ignore the "series" argument:
    // Don't synchronize this!! Instead, synchronize the loop that calls it.
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[141]++;
        RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
        return new Long(getX(tp));
    }

    /**
     * Returns the y-value.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public double getYValue(int series, int item) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[142]++;  
        // Don't synchronize this!!
        // Instead, synchronize the loop that calls it.
        ValueSequence values = this.valueHistory[series];
        return values.getData(translateGet(item)); 
    }

    /**
     * Returns the y-value.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getY(int series, int item) {
        return new Float(getYValue(series, item));
    }

    /**
     * Returns the start x-value.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getStartX(int series, int item) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[143]++;
        RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
        return new Long(tp.getFirstMillisecond(this.workingCalendar));
    }

    /**
     * Returns the end x-value.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getEndX(int series, int item) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[144]++;
        RegularTimePeriod tp = this.pointsInTime[translateGet(item)];
        return new Long(tp.getLastMillisecond(this.workingCalendar));
    }

    /**
     * Returns the start y-value.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getStartY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Returns the end y-value.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }

    /* // "Extras" found useful when analyzing/verifying class behavior:
    public Number getUntranslatedXValue(int series, int item)
    {
      return super.getXValue(series, item);
    }

    public float getUntranslatedY(int series, int item)
    {
      return super.getY(series, item);
    }  */

    /**
     * Returns the key for a series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The key.
     */
    public Comparable getSeriesKey(int series) {
        return this.seriesKeys[series];
    }

    /**
     * Sends a {@link SeriesChangeEvent} to all registered listeners.
     */
    protected void fireSeriesChanged() {
        seriesChanged(new SeriesChangeEvent(this));
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[145]++;
    }

    // The next 3 functions override the base-class implementation of
    // the DomainInfo interface.  Using saved limits (updated by
    // each updateTime() call), improves performance.
    //

    /**
     * Returns the minimum x-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     * 
     * @return The minimum value.
     */
    public double getDomainLowerBound(boolean includeInterval) {
        return this.domainStart.doubleValue();  
        // a Long kept updated by advanceTime()        
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
        return this.domainEnd.doubleValue();  
        // a Long kept updated by advanceTime()
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
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[146]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.domainRange == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[59]++;
            findDomainLimits();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[147]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[60]++;}
        return this.domainRange;
    }
    
    /**
     * Returns the x-value for a time period.
     *
     * @param period  the period.
     *
     * @return The x-value.
     */
    private long getX(RegularTimePeriod period) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[148]++;
        switch (this.position) {
            case (START) :
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[61]++; 
                return period.getFirstMillisecond(this.workingCalendar);
            case (MIDDLE) :
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[62]++; 
                return period.getMiddleMillisecond(this.workingCalendar);
            case (END) :
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[63]++; 
                return period.getLastMillisecond(this.workingCalendar);
            default:
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[64]++; 
                return period.getMiddleMillisecond(this.workingCalendar);
        }
     }

    // The next 3 functions implement the RangeInfo interface.
    // Using saved limits (updated by each updateTime() call) significantly
    // improves performance.  WARNING: this code makes the simplifying 
    // assumption that data is never negative.  Expand as needed for the 
    // general case.

    /**
     * Returns the minimum range value.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The minimum range value.
     */
    public double getRangeLowerBound(boolean includeInterval) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[149]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[150]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.minValue != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[65]++;
            result = this.minValue.doubleValue();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[151]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[66]++;}
        return result;
    }

    /**
     * Returns the maximum range value.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The maximum range value.
     */
    public double getRangeUpperBound(boolean includeInterval) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[152]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[153]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.maxValue != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[67]++;
            result = this.maxValue.doubleValue();
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[154]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[68]++;}
        return result;
    }

    /**
     * Returns the value range.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The range.
     */
    public Range getRangeBounds(boolean includeInterval) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[155]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.valueRange == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[69]++;
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[156]++;
            double max = getRangeUpperBound(includeInterval);
            this.valueRange = new Range(0.0, max);
CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.statements[157]++;

        } else {
  CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t.branches[70]++;}
        return this.valueRange;
    }
    
}

class CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t ());
  }
    public static long[] statements = new long[158];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[45];
  static {
    final String SECTION_NAME = "org.jfree.data.time.DynamicTimeSeriesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 44; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$1pftf71yr14hq7o09smz4eramcq6moh8gwrkcr0lwg6q4ypu1t () {
    super("org.jfree.data.time.DynamicTimeSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 157; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.time.DynamicTimeSeriesCollection.java");
      for (int i = 1; i <= 157; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 70; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 11; i++) {
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

