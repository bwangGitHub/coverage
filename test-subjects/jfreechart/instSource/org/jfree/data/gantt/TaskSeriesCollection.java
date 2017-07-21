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
 * TaskSeriesCollection.java
 * -------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Thomas Schuster;
 *
 * Changes
 * -------
 * 06-Jun-2002 : Version 1 (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 10-Jan-2003 : Renamed GanttSeriesCollection --> TaskSeriesCollection (DG);
 * 04-Sep-2003 : Fixed bug 800324 (DG);
 * 16-Sep-2003 : Implemented GanttCategoryDataset (DG);
 * 12-Jan-2005 : Fixed bug 1099331 (DG);
 * 18-Jan-2006 : Added new methods getSeries(int) and 
 *               getSeries(Comparable) (DG);
 *
 */

package org.jfree.data.gantt;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.time.TimePeriod;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A collection of {@link TaskSeries} objects.  This class provides one 
 * implementation of the {@link GanttCategoryDataset} interface.
 */
public class TaskSeriesCollection extends AbstractSeriesDataset
                                  implements GanttCategoryDataset,
                                             Cloneable, PublicCloneable, 
                                             Serializable {
  static {
    CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2065799050738449903L;
  static {
    CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[1]++;
  }

    /** 
     * Storage for aggregate task keys (the task description is used as the 
     * key). 
     */
    private List keys;

    /** Storage for the series. */
    private List data;

    /**
     * Default constructor.
     */
    public TaskSeriesCollection() {
        this.keys = new java.util.ArrayList();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[2]++;
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[3]++;
    }
    
    /**
     * Returns a series from the collection.
     *
     * @param key  the series key (<code>null</code> not permitted).
     *
     * @return The series.
     * 
     * @since 1.0.1
     */
    public TaskSeries getSeries(Comparable key) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[1]++;
            throw new NullPointerException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[2]++;}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[5]++;
        TaskSeries result = null;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[6]++;
        int index = getRowIndex(key);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[3]++;
            result = getSeries(index);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[8]++;

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[4]++;}
        return result;
    }

    /**
     * Returns a series from the collection.
     *
     * @param series  the series index (zero-based).
     *
     * @return The series.
     * 
     * @since 1.0.1
     */
    public TaskSeries getSeries(int series) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[5]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[6]++;}
        return (TaskSeries) this.data.get(series);
    }
    
    /**
     * Returns the number of series in the collection.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return getRowCount();
    }

    /**
     * Returns the name of a series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The name of a series.
     */
    public Comparable getSeriesKey(int series) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[10]++;
        TaskSeries ts = (TaskSeries) this.data.get(series);
        return ts.getKey();
    }

    /**
     * Returns the number of rows (series) in the collection.
     *
     * @return The series count.
     */
    public int getRowCount() {
        return this.data.size();
    }

    /**
     * Returns the row keys.  In this case, each series is a key.
     *
     * @return The row keys.
     */
    public List getRowKeys() {
        return this.data;
    }

    /**
     * Returns the number of column in the dataset.
     *
     * @return The column count.
     */
    public int getColumnCount() {
        return this.keys.size();
    }

    /**
     * Returns a list of the column keys in the dataset.
     *
     * @return The category list.
     */
    public List getColumnKeys() {
        return this.keys;
    }

    /**
     * Returns a column key.
     *
     * @param index  the column index.
     *
     * @return The column key.
     */
    public Comparable getColumnKey(int index) {
        return (Comparable) this.keys.get(index);
    }

    /**
     * Returns the column index for a column key.
     *
     * @param columnKey  the column key (<code>null</code> not permitted).
     *
     * @return The column index.
     */
    public int getColumnIndex(Comparable columnKey) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((columnKey == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[7]++;
            throw new IllegalArgumentException("Null 'columnKey' argument.");

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[8]++;}
        return this.keys.indexOf(columnKey);
    }

    /**
     * Returns the row index for the given row key.
     *
     * @param rowKey  the row key.
     *
     * @return The index.
     */
    public int getRowIndex(Comparable rowKey) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[12]++;
        int result = -1;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[13]++;
        int count = this.data.size();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[1]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[2]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[3]++;
}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[15]++;
            TaskSeries s = (TaskSeries) this.data.get(i);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((s.getKey().equals(rowKey)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[9]++;
                result = i;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[17]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[18]++;
                break;

            } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[10]++;}
        }
        return result;
    }

    /**
     * Returns the key for a row.
     *
     * @param index  the row index (zero-based).
     *
     * @return The key.
     */
    public Comparable getRowKey(int index) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[19]++;
        TaskSeries series = (TaskSeries) this.data.get(index);
        return series.getKey();
    }

    /**
     * Adds a series to the dataset and sends a 
     * {@link org.jfree.data.general.DatasetChangeEvent} to all registered 
     * listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void add(TaskSeries series) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[11]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[12]++;}
        this.data.add(series);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[21]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[22]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[23]++;

        // look for any keys that we don't already know about...
        Iterator iterator = series.getTasks().iterator();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[24]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[4]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[5]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[6]++;
}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[25]++;
            Task task = (Task) iterator.next();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[26]++;
            String key = task.getDescription();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[27]++;
            int index = this.keys.indexOf(key);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[13]++;
                this.keys.add(key);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[29]++;

            } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[14]++;}
        }
        fireDatasetChanged();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[30]++;
    }

    /**
     * Removes a series from the collection and sends 
     * a {@link org.jfree.data.general.DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param series  the series.
     */
    public void remove(TaskSeries series) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[15]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[16]++;}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.data.contains(series)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[17]++;
            series.removeChangeListener(this);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[33]++;
            this.data.remove(series);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[34]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[35]++;

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[18]++;}
    }

    /**
     * Removes a series from the collection and sends 
     * a {@link org.jfree.data.general.DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param series  the series (zero based index).
     */
    public void remove(int series) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[36]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[19]++;
            throw new IllegalArgumentException(
                "TaskSeriesCollection.remove(): index outside valid range.");

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[20]++;}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[37]++;

        // fetch the series, remove the change listener, then remove the series.
        TaskSeries ts = (TaskSeries) this.data.get(series);
        ts.removeChangeListener(this);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[38]++;
        this.data.remove(series);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[39]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[40]++;

    }

    /**
     * Removes all the series from the collection and sends 
     * a {@link org.jfree.data.general.DatasetChangeEvent}
     * to all registered listeners.
     */
    public void removeAll() {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[41]++;

        // deregister the collection as a change listener to each series in 
        // the collection.
        Iterator iterator = this.data.iterator();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[42]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;
        while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[7]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[8]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[9]++;
}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[43]++;
            TaskSeries series = (TaskSeries) iterator.next();
            series.removeChangeListener(this);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[44]++;
        }

        // remove all the series from the collection and notify listeners.
        this.data.clear();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[45]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[46]++;

    }

    /**
     * Returns the value for an item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     *
     * @return The item value.
     */
    public Number getValue(Comparable rowKey, Comparable columnKey) {
        return getStartValue(rowKey, columnKey);
    }

    /**
     * Returns the value for a task.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The start value.
     */
    public Number getValue(int row, int column) {
        return getStartValue(row, column);
    }

    /**
     * Returns the start value for a task.  This is a date/time value, measured
     * in milliseconds since 1-Jan-1970.
     *
     * @param rowKey  the series.
     * @param columnKey  the category.
     *
     * @return The start value (possibly <code>null</code>).
     */
    public Number getStartValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[47]++;
        Number result = null;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[48]++;
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[49]++;
        TaskSeries series = (TaskSeries) this.data.get(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[50]++;
        Task task = series.get(columnKey.toString());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((task != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[21]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[52]++;
            TimePeriod duration = task.getDuration();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((duration != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[23]++;
                result = new Long(duration.getStart().getTime());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[54]++;

            } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[22]++;}
        return result;
    }

    /**
     * Returns the start value for a task.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The start value.
     */
    public Number getStartValue(int row, int column) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[55]++;
        Comparable rowKey = getRowKey(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[56]++;
        Comparable columnKey = getColumnKey(column);
        return getStartValue(rowKey, columnKey);
    }

    /**
     * Returns the end value for a task.  This is a date/time value, measured
     * in milliseconds since 1-Jan-1970.
     *
     * @param rowKey  the series.
     * @param columnKey  the category.
     *
     * @return The end value (possibly <code>null</code>).
     */
    public Number getEndValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[57]++;
        Number result = null;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[58]++;
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[59]++;
        TaskSeries series = (TaskSeries) this.data.get(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[60]++;
        Task task = series.get(columnKey.toString());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((task != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[25]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[62]++;
            TimePeriod duration = task.getDuration();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((duration != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[27]++;
                result = new Long(duration.getEnd().getTime());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[64]++;

            } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[28]++;}

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[26]++;}
        return result;
    }

    /**
     * Returns the end value for a task.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The end value.
     */
    public Number getEndValue(int row, int column) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[65]++;
        Comparable rowKey = getRowKey(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[66]++;
        Comparable columnKey = getColumnKey(column);
        return getEndValue(rowKey, columnKey);
    }

    /**
     * Returns the percent complete for a given item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The percent complete (possibly <code>null</code>).
     */
    public Number getPercentComplete(int row, int column) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[67]++;
        Comparable rowKey = getRowKey(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[68]++;
        Comparable columnKey = getColumnKey(column);
        return getPercentComplete(rowKey, columnKey);
    }

    /**
     * Returns the percent complete for a given item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     *
     * @return The percent complete.
     */
    public Number getPercentComplete(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[69]++;
        Number result = null;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[70]++;
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[71]++;
        TaskSeries series = (TaskSeries) this.data.get(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[72]++;
        Task task = series.get(columnKey.toString());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((task != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[29]++;
            result = task.getPercentComplete();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[74]++;

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[30]++;}
        return result;
    }

    /**
     * Returns the number of sub-intervals for a given item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The sub-interval count.
     */
    public int getSubIntervalCount(int row, int column) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[75]++;
        Comparable rowKey = getRowKey(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[76]++;
        Comparable columnKey = getColumnKey(column);
        return getSubIntervalCount(rowKey, columnKey);
    }

    /**
     * Returns the number of sub-intervals for a given item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     *
     * @return The sub-interval count.
     */
    public int getSubIntervalCount(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[77]++;
        int result = 0;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[78]++;
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[79]++;
        TaskSeries series = (TaskSeries) this.data.get(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[80]++;
        Task task = series.get(columnKey.toString());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((task != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[31]++;
            result = task.getSubtaskCount();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[82]++;

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[32]++;}
        return result;
    }

    /**
     * Returns the start value of a sub-interval for a given item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param subinterval  the sub-interval index (zero-based).
     *
     * @return The start value (possibly <code>null</code>).
     */
    public Number getStartValue(int row, int column, int subinterval) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[83]++;
        Comparable rowKey = getRowKey(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[84]++;
        Comparable columnKey = getColumnKey(column);
        return getStartValue(rowKey, columnKey, subinterval);
    }

    /**
     * Returns the start value of a sub-interval for a given item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * @param subinterval  the subinterval.
     *
     * @return The start value (possibly <code>null</code>).
     */
    public Number getStartValue(Comparable rowKey, Comparable columnKey, 
                                int subinterval) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[85]++;
        Number result = null;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[86]++;
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[87]++;
        TaskSeries series = (TaskSeries) this.data.get(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[88]++;
        Task task = series.get(columnKey.toString());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[89]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((task != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[33]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[90]++;
            Task sub = task.getSubtask(subinterval);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[91]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((sub != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[35]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[92]++;
                TimePeriod duration = sub.getDuration();
                result = new Long(duration.getStart().getTime());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[93]++;

            } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[34]++;}
        return result;
    }

    /**
     * Returns the end value of a sub-interval for a given item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param subinterval  the subinterval.
     *
     * @return The end value (possibly <code>null</code>).
     */
    public Number getEndValue(int row, int column, int subinterval) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[94]++;
        Comparable rowKey = getRowKey(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[95]++;
        Comparable columnKey = getColumnKey(column);
        return getEndValue(rowKey, columnKey, subinterval);
    }

    /**
     * Returns the end value of a sub-interval for a given item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * @param subinterval  the subinterval.
     *
     * @return The end value (possibly <code>null</code>).
     */
    public Number getEndValue(Comparable rowKey, Comparable columnKey, 
                              int subinterval) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[96]++;
        Number result = null;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[97]++;
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[98]++;
        TaskSeries series = (TaskSeries) this.data.get(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[99]++;
        Task task = series.get(columnKey.toString());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[100]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((task != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[37]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[101]++;
            Task sub = task.getSubtask(subinterval);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[102]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((sub != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[39]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[103]++;
                TimePeriod duration = sub.getDuration();
                result = new Long(duration.getEnd().getTime());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[104]++;

            } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[40]++;}

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[38]++;}
        return result;
    }

    /**
     * Returns the percentage complete value of a sub-interval for a given item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param subinterval  the sub-interval.
     *
     * @return The percent complete value (possibly <code>null</code>).
     */
    public Number getPercentComplete(int row, int column, int subinterval) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[105]++;
        Comparable rowKey = getRowKey(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[106]++;
        Comparable columnKey = getColumnKey(column);
        return getPercentComplete(rowKey, columnKey, subinterval);
    }

    /**
     * Returns the percentage complete value of a sub-interval for a given item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * @param subinterval  the sub-interval.
     *
     * @return The precent complete value (possibly <code>null</code>).
     */
    public Number getPercentComplete(Comparable rowKey, Comparable columnKey, 
                                     int subinterval) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[107]++;
        Number result = null;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[108]++;
        int row = getRowIndex(rowKey);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[109]++;
        TaskSeries series = (TaskSeries) this.data.get(row);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[110]++;
        Task task = series.get(columnKey.toString());
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[111]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((task != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[41]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[112]++;
            Task sub = task.getSubtask(subinterval);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[113]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((sub != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[43]++;
                result = sub.getPercentComplete();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[114]++;

            } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[42]++;}
        return result;
    }

    /**
     * Called when a series belonging to the dataset changes.
     *
     * @param event  information about the change.
     */
    public void seriesChanged(SeriesChangeEvent event) {
        refreshKeys();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[115]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[116]++;
    }

    /**
     * Refreshes the keys.
     */
    private void refreshKeys() {

        this.keys.clear();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[117]++;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[118]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[10]++;


int CodeCoverConditionCoverageHelper_C26;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[10]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[11]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[12]++;
}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[119]++;
            TaskSeries series = (TaskSeries) this.data.get(i);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[120]++;
            // look for any keys that we don't already know about...
            Iterator iterator = series.getTasks().iterator();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[121]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
            while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[13]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[14]--;
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.loops[15]++;
}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[122]++;
                Task task = (Task) iterator.next();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[123]++;
                String key = task.getDescription();
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[124]++;
                int index = this.keys.indexOf(key);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[125]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[45]++;
                    this.keys.add(key);
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[126]++;

                } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[46]++;}
            }
        }

    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[127]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[47]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[48]++;}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[128]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((obj instanceof TaskSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[50]++;}
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[129]++;
        TaskSeriesCollection that = (TaskSeriesCollection) obj;
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.statements[130]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt.branches[52]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt ());
  }
    public static long[] statements = new long[131];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "org.jfree.data.gantt.TaskSeriesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$3uanltm2xjxqphwu5mad21yajh2iajwhc893rdt () {
    super("org.jfree.data.gantt.TaskSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 130; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.gantt.TaskSeriesCollection.java");
      for (int i = 1; i <= 130; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

