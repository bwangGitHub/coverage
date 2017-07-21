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
 * XYSeriesCollection.java
 * -----------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Aaron Metzger;
 *
 * Changes
 * -------
 * 15-Nov-2001 : Version 1 (DG);
 * 03-Apr-2002 : Added change listener code (DG);
 * 29-Apr-2002 : Added removeSeries, removeAllSeries methods (ARM);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 04-Aug-2003 : Added getSeries() method (DG);
 * 31-Mar-2004 : Modified to use an XYIntervalDelegate.
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 17-Nov-2004 : Updated for changes to DomainInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 28-Mar-2005 : Fixed bug in getSeries(int) method (1170825) (DG);
 * 05-Oct-2005 : Made the interval delegate a dataset listener (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 27-Nov-2006 : Added clone() override (DG);
 * 08-May-2007 : Added indexOf(XYSeries) method (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.util.ObjectUtilities;

/**
 * Represents a collection of {@link XYSeries} objects that can be used as a 
 * dataset.
 */
public class XYSeriesCollection extends AbstractIntervalXYDataset
                                implements IntervalXYDataset, DomainInfo, 
                                           Serializable {
  static {
    CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7590013825931496766L;
  static {
    CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[1]++;
  }
    
    /** The series that are included in the collection. */
    private List data;
    
    /** The interval delegate (used to calculate the start and end x-values). */
    private IntervalXYDelegate intervalDelegate;
    
    /**
     * Constructs an empty dataset.
     */
    public XYSeriesCollection() {
        this(null);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[2]++;
    }

    /**
     * Constructs a dataset and populates it with a single series.
     *
     * @param series  the series (<code>null</code> ignored).
     */
    public XYSeriesCollection(XYSeries series) {
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[3]++;
        this.intervalDelegate = new IntervalXYDelegate(this, false);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[4]++;
        addChangeListener(this.intervalDelegate);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[5]++;
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[1]++;
            this.data.add(series);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[7]++;
            series.addChangeListener(this);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[8]++;

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[2]++;}
    }
    
    /**
     * Adds a series to the collection and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void addSeries(XYSeries series) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[3]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[4]++;}
        this.data.add(series);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[10]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[11]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[12]++;

    }

    /**
     * Removes a series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     */
    public void removeSeries(int series) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[5]++;
            throw new IllegalArgumentException("Series index out of bounds.");

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[6]++;}
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[14]++;

        // fetch the series, remove the change listener, then remove the series.
        XYSeries ts = (XYSeries) this.data.get(series);
        ts.removeChangeListener(this);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[15]++;
        this.data.remove(series);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[16]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[17]++;

    }

    /**
     * Removes a series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void removeSeries(XYSeries series) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[7]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[8]++;}
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.data.contains(series)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[9]++;
            series.removeChangeListener(this);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[20]++;
            this.data.remove(series);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[21]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[22]++;

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[10]++;}

    }
    
    /**
     * Removes all the series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     */
    public void removeAllSeries() {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        // Unregister the collection as a change listener to each series in 
        // the collection.
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.loops[1]--;
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.loops[2]--;
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.loops[3]++;
}
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[24]++;
          XYSeries series = (XYSeries) this.data.get(i);
          series.removeChangeListener(this);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[25]++;
        }

        // Remove all the series from the collection and notify listeners.
        this.data.clear();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[26]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[27]++;
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
     * Returns a list of all the series in the collection.  
     * 
     * @return The list (which is unmodifiable).
     */
    public List getSeries() {
        return Collections.unmodifiableList(this.data);
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
    public int indexOf(XYSeries series) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[11]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[12]++;}
        return this.data.indexOf(series);
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
    public XYSeries getSeries(int series) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[13]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[14]++;}
        return (XYSeries) this.data.get(series);
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
     * Returns the x-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The value.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[30]++;
        XYSeries ts = (XYSeries) this.data.get(series);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[31]++;
        XYDataItem xyItem = ts.getDataItem(item);
        return xyItem.getX();
    }

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The starting X value.
     */
    public Number getStartX(int series, int item) {
        return this.intervalDelegate.getStartX(series, item);
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The ending X value.
     */
    public Number getEndX(int series, int item) {
        return this.intervalDelegate.getEndX(series, item);
    }

    /**
     * Returns the y-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param index  the index of the item of interest (zero-based).
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getY(int series, int index) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[32]++;

        XYSeries ts = (XYSeries) this.data.get(series);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[33]++;
        XYDataItem xyItem = ts.getDataItem(index);
        return xyItem.getY();

    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The starting Y value.
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
     * @return The ending Y value.
     */
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Tests this collection for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
        /* 
         * XXX
         *  
         * what about  the interval delegate...?
         * The interval width etc wasn't considered
         * before, hence i did not add it here (AS)
         * 
         */

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[16]++;}
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj instanceof XYSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[18]++;}
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[36]++;
        XYSeriesCollection that = (XYSeriesCollection) obj;
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
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[37]++;
        XYSeriesCollection clone = (XYSeriesCollection) super.clone();
        clone.data = (List) ObjectUtilities.deepClone(this.data);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[38]++;
        clone.intervalDelegate 
                = (IntervalXYDelegate) this.intervalDelegate.clone();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[39]++;
        return clone;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        // Same question as for equals (AS)
        return (this.data != null ? this.data.hashCode() : 0);
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
        return this.intervalDelegate.getDomainLowerBound(includeInterval);
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
        return this.intervalDelegate.getDomainUpperBound(includeInterval);
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
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[19]++;
            return this.intervalDelegate.getDomainBounds(includeInterval);

        }
        else {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[20]++;
            return DatasetUtilities.iterateDomainBounds(this, includeInterval);
        }
            
    }
    
    /**
     * Returns the interval width. This is used to calculate the start and end 
     * x-values, if/when the dataset is used as an {@link IntervalXYDataset}.  
     * 
     * @return The interval width.
     */
    public double getIntervalWidth() {
        return this.intervalDelegate.getIntervalWidth();
    }
    
    /**
     * Sets the interval width and sends a {@link DatasetChangeEvent} to all 
     * registered listeners.
     * 
     * @param width  the width (negative values not permitted).
     */
    public void setIntervalWidth(double width) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((width < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[21]++;
            throw new IllegalArgumentException("Negative 'width' argument.");

        } else {
  CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.branches[22]++;}
        this.intervalDelegate.setFixedIntervalWidth(width);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[42]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[43]++;
    }

    /**
     * Returns the interval position factor.  
     * 
     * @return The interval position factor.
     */
    public double getIntervalPositionFactor() {
        return this.intervalDelegate.getIntervalPositionFactor();
    }
    
    /**
     * Sets the interval position factor. This controls where the x-value is in
     * relation to the interval surrounding the x-value (0.0 means the x-value 
     * will be positioned at the start, 0.5 in the middle, and 1.0 at the end).
     * 
     * @param factor  the factor.
     */
    public void setIntervalPositionFactor(double factor) {
        this.intervalDelegate.setIntervalPositionFactor(factor);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[44]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[45]++;
    }
    
    /**
     * Returns whether the interval width is automatically calculated or not.
     * 
     * @return Whether the width is automatically calculated or not.
     */
    public boolean isAutoWidth() {
        return this.intervalDelegate.isAutoWidth();
    }

    /**
     * Sets the flag that indicates wether the interval width is automatically
     * calculated or not. 
     * 
     * @param b  a boolean.
     */
    public void setAutoWidth(boolean b) {
        this.intervalDelegate.setAutoWidth(b);
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[46]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d.statements[47]++;
    }
    
}

class CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d ());
  }
    public static long[] statements = new long[48];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.XYSeriesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,2,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$2v3260rnomu8ujyp060qvap3rtuktv0w1m5d () {
    super("org.jfree.data.xy.XYSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 47; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.XYSeriesCollection.java");
      for (int i = 1; i <= 47; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

