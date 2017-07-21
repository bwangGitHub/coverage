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
 * ---------------------------
 * ComparableObjectSeries.java
 * ---------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Oct-2006 : New class (DG);
 * 31-Oct-2007 : Implemented faster hashCode() (DG);
 *
 */

package org.jfree.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.jfree.data.general.Series;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesException;
import org.jfree.util.ObjectUtilities;

/**
 * A (possibly ordered) list of (Comparable, Object) data items.
 *
 * @since 1.0.3
 */
public class ComparableObjectSeries extends Series 
        implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.ping();
  }

    
    /** Storage for the data items in the series. */
    protected List data;

    /** The maximum number of items for the series. */
    private int maximumItemCount = Integer.MAX_VALUE;
  {
    CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[1]++;
  }

    /** A flag that controls whether the items are automatically sorted. */
    private boolean autoSort;
    
    /** A flag that controls whether or not duplicate x-values are allowed. */
    private boolean allowDuplicateXValues;

    /**
     * Creates a new empty series.  By default, items added to the series will 
     * be sorted into ascending order by x-value, and duplicate x-values will 
     * be allowed (these defaults can be modified with another constructor.
     *
     * @param key  the series key (<code>null</code> not permitted).
     */
    public ComparableObjectSeries(Comparable key) {
        this(key, true, true);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[2]++;
    }
    
    /**
     * Constructs a new series that contains no data.  You can specify 
     * whether or not duplicate x-values are allowed for the series.
     *
     * @param key  the series key (<code>null</code> not permitted).
     * @param autoSort  a flag that controls whether or not the items in the 
     *                  series are sorted.
     * @param allowDuplicateXValues  a flag that controls whether duplicate 
     *                               x-values are allowed.
     */
    public ComparableObjectSeries(Comparable key, boolean autoSort, 
            boolean allowDuplicateXValues) {
        super(key);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[3]++;
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[4]++;
        this.autoSort = autoSort;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[5]++;
        this.allowDuplicateXValues = allowDuplicateXValues;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[6]++;
    }

    /**
     * Returns the flag that controls whether the items in the series are 
     * automatically sorted.  There is no setter for this flag, it must be 
     * defined in the series constructor.
     * 
     * @return A boolean.
     */
    public boolean getAutoSort() {
        return this.autoSort;
    }
    
    /**
     * Returns a flag that controls whether duplicate x-values are allowed.  
     * This flag can only be set in the constructor.
     *
     * @return A boolean.
     */
    public boolean getAllowDuplicateXValues() {
        return this.allowDuplicateXValues;
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
     * Returns the maximum number of items that will be retained in the series.
     * The default value is <code>Integer.MAX_VALUE</code>.
     *
     * @return The maximum item count.
     * @see #setMaximumItemCount(int)
     */
    public int getMaximumItemCount() {
        return this.maximumItemCount;
    }

    /**
     * Sets the maximum number of items that will be retained in the series.  
     * If you add a new item to the series such that the number of items will 
     * exceed the maximum item count, then the first element in the series is 
     * automatically removed, ensuring that the maximum item count is not 
     * exceeded.
     * <p>
     * Typically this value is set before the series is populated with data,
     * but if it is applied later, it may cause some items to be removed from
     * the series (in which case a {@link SeriesChangeEvent} will be sent to
     * all registered listeners.
     *
     * @param maximum  the maximum number of items for the series.
     */
    public void setMaximumItemCount(int maximum) {
        this.maximumItemCount = maximum;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[7]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[8]++;
        boolean dataRemoved = false;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.data.size() > maximum) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[1]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[2]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[3]++;
}
            this.data.remove(0);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[10]++;   
            dataRemoved = true;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[11]++;
        }
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataRemoved) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[1]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[13]++;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[2]++;}
    }
    
    /**
     * Adds new data to the series and sends a {@link SeriesChangeEvent} to 
     * all registered listeners.
     * <P>
     * Throws an exception if the x-value is a duplicate AND the 
     * allowDuplicateXValues flag is false.
     *
     * @param x  the x-value (<code>null</code> not permitted).
     * @param y  the y-value (<code>null</code> permitted).
     */
    protected void add(Comparable x, Object y) {
        // argument checking delegated...
        add(x, y, true);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[14]++;
    }
    
    /**
     * Adds new data to the series and, if requested, sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * <P>
     * Throws an exception if the x-value is a duplicate AND the 
     * allowDuplicateXValues flag is false.
     *
     * @param x  the x-value (<code>null</code> not permitted).
     * @param y  the y-value (<code>null</code> permitted).
     * @param notify  a flag the controls whether or not a 
     *                {@link SeriesChangeEvent} is sent to all registered 
     *                listeners.
     */
    protected void add(Comparable x, Object y, boolean notify) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[15]++;
        // delegate argument checking to XYDataItem...
        ComparableObjectItem item = new ComparableObjectItem(x, y);
        add(item, notify);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[16]++;
    }

    /**
     * Adds a data item to the series and, if requested, sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param item  the (x, y) item (<code>null</code> not permitted).
     * @param notify  a flag that controls whether or not a 
     *                {@link SeriesChangeEvent} is sent to all registered 
     *                listeners.
     */
    protected void add(ComparableObjectItem item, boolean notify) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[3]++;
            throw new IllegalArgumentException("Null 'item' argument.");

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[4]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.autoSort) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[5]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[19]++;
            int index = Collections.binarySearch(this.data, item);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[7]++;
                this.data.add(-index - 1, item);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[21]++;

            }
            else {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[8]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.allowDuplicateXValues) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[9]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[23]++;
                    // need to make sure we are adding *after* any duplicates
                    int size = this.data.size();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[24]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
                    while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((index < size) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((item.compareTo(this.data.get(index)) == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[4]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[5]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[6]++;
}
                        index++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[25]++;
                    }
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((index < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[11]++;
                        this.data.add(index, item);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[27]++;

                    }
                    else {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[12]++;
                        this.data.add(item);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[28]++;
                    }

                }
                else {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[10]++;
                    throw new SeriesException("X-value already exists.");
                }
            }

        }
        else {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[6]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.allowDuplicateXValues) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[13]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[30]++;
                // can't allow duplicate values, so we need to check whether
                // there is an item with the given x-value already
                int index = indexOf(item.getComparable());
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[15]++;
                    throw new SeriesException("X-value already exists.");
      
                } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[16]++;}

            } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[14]++;}
            this.data.add(item);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[32]++;
        }
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getItemCount() > this.maximumItemCount) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[17]++;
            this.data.remove(0);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[34]++;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[18]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[35]++;
int CodeCoverConditionCoverageHelper_C12;                    
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[19]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[36]++;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[20]++;}
    }
    
    /**
     * Returns the index of the item with the specified x-value, or a negative 
     * index if the series does not contain an item with that x-value.  Be 
     * aware that for an unsorted series, the index is found by iterating 
     * through all items in the series.
     * 
     * @param x  the x-value (<code>null</code> not permitted).
     * 
     * @return The index.
     */
    public int indexOf(Comparable x) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.autoSort) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[21]++;
            return Collections.binarySearch(this.data, new ComparableObjectItem(
                    x, null));
   
        }
        else {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[22]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[7]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[8]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[9]++;
}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[39]++;
                ComparableObjectItem item = (ComparableObjectItem) 
                        this.data.get(i);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((item.getComparable().equals(x)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[23]++;
                    return i;
   
                } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[24]++;}
            }
            return -1;
        }
    } 

    /**
     * Updates an item in the series.
     * 
     * @param x  the x-value (<code>null</code> not permitted).
     * @param y  the y-value (<code>null</code> permitted).
     * 
     * @throws SeriesException if there is no existing item with the specified
     *         x-value.
     */
    protected void update(Comparable x, Object y) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[41]++;
        int index = indexOf(x);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[42]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[25]++;
            throw new SeriesException("No observation for x = " + x);

        }
        else {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[26]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[43]++;
            ComparableObjectItem item = getDataItem(index);
            item.setObject(y);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[44]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[45]++;
        }
    }

    /**
     * Updates the value of an item in the series and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * 
     * @param index  the item (zero based index).
     * @param y  the new value (<code>null</code> permitted).
     */
    protected void updateByIndex(int index, Object y) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[46]++;
        ComparableObjectItem item = getDataItem(index);
        item.setObject(y);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[47]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[48]++;
    }
    
    /**
     * Return the data item with the specified index.
     *
     * @param index  the index.
     *
     * @return The data item with the specified index.
     */
    protected ComparableObjectItem getDataItem(int index) {
        return (ComparableObjectItem) this.data.get(index);
    }

    /**
     * Deletes a range of items from the series and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param start  the start index (zero-based).
     * @param end  the end index (zero-based).
     */
    protected void delete(int start, int end) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[49]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[10]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int i = start;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i <= end) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[10]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[11]--;
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.loops[12]++;
}
            this.data.remove(start);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[50]++;
        }
        fireSeriesChanged();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[51]++;
    }
    
    /**
     * Removes all data items from the series.
     */
    protected void clear() {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[52]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.data.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[27]++;
            this.data.clear();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[53]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[54]++;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[28]++;}
    }

    /**
     * Removes the item at the specified index and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * 
     * @param index  the index.
     * 
     * @return The item removed.
     */
    protected ComparableObjectItem remove(int index) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[55]++;
        ComparableObjectItem result = (ComparableObjectItem) this.data.remove(
                index);
        fireSeriesChanged();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[56]++;
        return result;
    }
    
    /**
     * Removes the item with the specified x-value and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * 
     * @param x  the x-value.

     * @return The item removed.
     */
    public ComparableObjectItem remove(Comparable x) {
        return remove(indexOf(x));
    }
    
    /**
     * Tests this series for equality with an arbitrary object.
     *
     * @param obj  the object to test against for equality 
     *             (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[57]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[29]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[30]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[58]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj instanceof ComparableObjectSeries) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[32]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[59]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[34]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[60]++;
        ComparableObjectSeries that = (ComparableObjectSeries) obj;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[61]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.maximumItemCount != that.maximumItemCount) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[36]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[62]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.autoSort != that.autoSort) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[38]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[63]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.allowDuplicateXValues != that.allowDuplicateXValues) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[40]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[64]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[42]++;}
        return true;
    }
    
    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[65]++;
        int result = super.hashCode();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[66]++;
        // it is too slow to look at every data item, so let's just look at
        // the first, middle and last items...
        int count = getItemCount();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[67]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[43]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[68]++;
            ComparableObjectItem item = getDataItem(0);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[69]++;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[44]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[70]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[45]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[71]++;
            ComparableObjectItem item = getDataItem(count - 1);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[72]++;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[46]++;}
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[73]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((count > 2) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[47]++;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[74]++;
            ComparableObjectItem item = getDataItem(count / 2);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[75]++;

        } else {
  CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.branches[48]++;}
        result = 29 * result + this.maximumItemCount;
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[76]++;
        result = 29 * result + (this.autoSort ? 1 : 0);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[77]++;
        result = 29 * result + (this.allowDuplicateXValues ? 1 : 0);
CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5.statements[78]++;
        return result;
    }
    
}

class CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5 ());
  }
    public static long[] statements = new long[79];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "org.jfree.data.ComparableObjectSeries.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 28; i++) {
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

  public CodeCoverCoverageCounter$4b8xgkqso8ulo1g41o4u57kt6hioz2578en4vnbfj5 () {
    super("org.jfree.data.ComparableObjectSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 78; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.ComparableObjectSeries.java");
      for (int i = 1; i <= 78; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
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

