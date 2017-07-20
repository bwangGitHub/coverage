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
 * -------------
 * XYSeries.java
 * -------------
 * (C) Copyright 2001-2007, Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Aaron Metzger;
 *                   Jonathan Gabbai;
 *                   Richard Atkinson;
 *                   Michel Santos;
 *
 * Changes
 * -------
 * 15-Nov-2001 : Version 1 (DG);
 * 03-Apr-2002 : Added an add(double, double) method (DG);
 * 29-Apr-2002 : Added a clear() method (ARM);
 * 06-Jun-2002 : Updated Javadoc comments (DG);
 * 29-Aug-2002 : Modified to give user control over whether or not duplicate 
 *               x-values are allowed (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 11-Nov-2002 : Added maximum item count, code contributed by Jonathan 
 *               Gabbai (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 04-Aug-2003 : Added getItems() method (DG);
 * 15-Aug-2003 : Changed 'data' from private to protected, added new add() 
 *               methods with a 'notify' argument (DG);
 * 22-Sep-2003 : Added getAllowDuplicateXValues() method (RA);
 * 29-Jan-2004 : Added autoSort attribute, based on a contribution by 
 *               Michel Santos - see patch 886740 (DG);
 * 03-Feb-2004 : Added indexOf() method (DG);
 * 16-Feb-2004 : Added remove() method (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 21-Feb-2005 : Added update(Number, Number) and addOrUpdate(Number, Number) 
 *               methods (DG);
 * 03-May-2005 : Added a new constructor, fixed the setMaximumItemCount() 
 *               method to remove items (and notify listeners) if necessary, 
 *               fixed the add() and addOrUpdate() methods to handle unsorted 
 *               series (DG);
 * ------------- JFreeChart 1.0.x ---------------------------------------------
 * 11-Jan-2005 : Renamed update(int, Number) --> updateByIndex() (DG);
 * 15-Jan-2007 : Added toArray() method (DG);
 * 31-Oct-2007 : Implemented faster hashCode() (DG);
 * 22-Nov-2007 : Reimplemented clone() (DG);
 * 
 */

package org.jfree.data.xy;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.jfree.data.general.Series;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesException;
import org.jfree.util.ObjectUtilities;

/**
 * Represents a sequence of zero or more data items in the form (x, y).  By 
 * default, items in the series will be sorted into ascending order by x-value,
 * and duplicate x-values are permitted.  Both the sorting and duplicate 
 * defaults can be changed in the constructor.  Y-values can be 
 * <code>null</code> to represent missing values.
 */
public class XYSeries extends Series implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -5908509288197150436L;
  static {
    CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[1]++;
  }
    
    // In version 0.9.12, in response to several developer requests, I changed 
    // the 'data' attribute from 'private' to 'protected', so that others can 
    // make subclasses that work directly with the underlying data structure.

    /** Storage for the data items in the series. */
    protected List data;

    /** The maximum number of items for the series. */
    private int maximumItemCount = Integer.MAX_VALUE;
  {
    CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[2]++;
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
    public XYSeries(Comparable key) {
        this(key, true, true);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[3]++;
    }

    /**
     * Constructs a new empty series, with the auto-sort flag set as requested,
     * and duplicate values allowed.  
     * 
     * @param key  the series key (<code>null</code> not permitted).
     * @param autoSort  a flag that controls whether or not the items in the 
     *                  series are sorted.
     */
    public XYSeries(Comparable key, boolean autoSort) {
        this(key, autoSort, true);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[4]++;
    }

    /**
     * Constructs a new xy-series that contains no data.  You can specify 
     * whether or not duplicate x-values are allowed for the series.
     *
     * @param key  the series key (<code>null</code> not permitted).
     * @param autoSort  a flag that controls whether or not the items in the 
     *                  series are sorted.
     * @param allowDuplicateXValues  a flag that controls whether duplicate 
     *                               x-values are allowed.
     */
    public XYSeries(Comparable key, 
                    boolean autoSort, 
                    boolean allowDuplicateXValues) {
        super(key);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[5]++;
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[6]++;
        this.autoSort = autoSort;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[7]++;
        this.allowDuplicateXValues = allowDuplicateXValues;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[8]++;
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
     * Returns the list of data items for the series (the list contains 
     * {@link XYDataItem} objects and is unmodifiable).
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
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[9]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[10]++;
        boolean dataRemoved = false;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.data.size() > maximum) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[1]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[2]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[3]++;
}
            this.data.remove(0);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[12]++;   
            dataRemoved = true;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[13]++;
        }
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataRemoved) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[1]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[15]++;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[2]++;}
    }

    /**
     * Adds a data item to the series and sends a {@link SeriesChangeEvent} to 
     * all registered listeners.
     *
     * @param item  the (x, y) item (<code>null</code> not permitted).
     */
    public void add(XYDataItem item) {
        // argument checking delegated...
        add(item, true);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[16]++;
    }
    
    /**
     * Adds a data item to the series and sends a {@link SeriesChangeEvent} to 
     * all registered listeners.
     *
     * @param x  the x value.
     * @param y  the y value.
     */
    public void add(double x, double y) {
        add(new Double(x), new Double(y), true);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[17]++;
    }

    /**
     * Adds a data item to the series and, if requested, sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param x  the x value.
     * @param y  the y value.
     * @param notify  a flag that controls whether or not a 
     *                {@link SeriesChangeEvent} is sent to all registered 
     *                listeners.
     */
    public void add(double x, double y, boolean notify) {
        add(new Double(x), new Double(y), notify);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[18]++;
    }

    /**
     * Adds a data item to the series and sends a {@link SeriesChangeEvent} to 
     * all registered listeners.  The unusual pairing of parameter types is to 
     * make it easier to add <code>null</code> y-values.
     *
     * @param x  the x value.
     * @param y  the y value (<code>null</code> permitted).
     */
    public void add(double x, Number y) {
        add(new Double(x), y);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[19]++;
    }

    /**
     * Adds a data item to the series and, if requested, sends a 
     * {@link SeriesChangeEvent} to all registered listeners.  The unusual 
     * pairing of parameter types is to make it easier to add null y-values.
     *
     * @param x  the x value.
     * @param y  the y value (<code>null</code> permitted).
     * @param notify  a flag that controls whether or not a 
     *                {@link SeriesChangeEvent} is sent to all registered 
     *                listeners.
     */
    public void add(double x, Number y, boolean notify) {
        add(new Double(x), y, notify);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[20]++;
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
    public void add(Number x, Number y) {
        // argument checking delegated...
        add(x, y, true);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[21]++;
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
    public void add(Number x, Number y, boolean notify) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[22]++;
        // delegate argument checking to XYDataItem...
        XYDataItem item = new XYDataItem(x, y);
        add(item, notify);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[23]++;
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
    public void add(XYDataItem item, boolean notify) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((item == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[3]++;
            throw new IllegalArgumentException("Null 'item' argument.");

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[4]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.autoSort) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[5]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[26]++;
            int index = Collections.binarySearch(this.data, item);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[7]++;
                this.data.add(-index - 1, item);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[28]++;

            }
            else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[8]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.allowDuplicateXValues) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[9]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[30]++;
                    // need to make sure we are adding *after* any duplicates
                    int size = this.data.size();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[31]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
                    while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((index < size) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((item.compareTo(this.data.get(index)) == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[4]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[5]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[6]++;
}
                        index++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[32]++;
                    }
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((index < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[11]++;
                        this.data.add(index, item);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[34]++;

                    }
                    else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[12]++;
                        this.data.add(item);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[35]++;
                    }

                }
                else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[10]++;
                    throw new SeriesException("X-value already exists.");
                }
            }

        }
        else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[6]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.allowDuplicateXValues) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[13]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[37]++;
                // can't allow duplicate values, so we need to check whether
                // there is an item with the given x-value already
                int index = indexOf(item.getX());
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[15]++;
                    throw new SeriesException("X-value already exists.");
      
                } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[16]++;}

            } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[14]++;}
            this.data.add(item);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[39]++;
        }
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getItemCount() > this.maximumItemCount) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[17]++;
            this.data.remove(0);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[41]++;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[18]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[42]++;
int CodeCoverConditionCoverageHelper_C12;                    
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[19]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[43]++;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[20]++;}
    }

    /**
     * Deletes a range of items from the series and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param start  the start index (zero-based).
     * @param end  the end index (zero-based).
     */
    public void delete(int start, int end) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[44]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = start;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i <= end) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[7]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[8]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[9]++;
}
            this.data.remove(start);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[45]++;
        }
        fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[46]++;
    }

    /**
     * Removes the item at the specified index and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * 
     * @param index  the index.
     * 
     * @return The item removed.
     */
    public XYDataItem remove(int index) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[47]++;
        XYDataItem result = (XYDataItem) this.data.remove(index);
        fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[48]++;
        return result;
    }
    
    /**
     * Removes the item with the specified x-value and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * 
     * @param x  the x-value.

     * @return The item removed.
     */
    public XYDataItem remove(Number x) {
        return remove(indexOf(x));
    }
    
    /**
     * Removes all data items from the series.
     */
    public void clear() {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[49]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.data.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[21]++;
            this.data.clear();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[50]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[51]++;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[22]++;}
    }

    /**
     * Return the data item with the specified index.
     *
     * @param index  the index.
     *
     * @return The data item with the specified index.
     */
    public XYDataItem getDataItem(int index) {
        return (XYDataItem) this.data.get(index);
    }

    /**
     * Returns the x-value at the specified index.
     *
     * @param index  the index (zero-based).
     *
     * @return The x-value (never <code>null</code>).
     */
    public Number getX(int index) {
        return getDataItem(index).getX();
    }

    /**
     * Returns the y-value at the specified index.
     *
     * @param index  the index (zero-based).
     *
     * @return The y-value (possibly <code>null</code>).
     */
    public Number getY(int index) {
        return getDataItem(index).getY();
    }
    
    /**
     * Updates the value of an item in the series and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     *
     * @param index  the item (zero based index).
     * @param y  the new value (<code>null</code> permitted).
     * 
     * @deprecated Renamed {@link #updateByIndex(int, Number)} to avoid 
     *         confusion with the {@link #update(Number, Number)} method.
     */
    public void update(int index, Number y) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[52]++;
        XYDataItem item = getDataItem(index);
        item.setY(y);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[53]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[54]++;
    }
    
    /**
     * Updates the value of an item in the series and sends a 
     * {@link SeriesChangeEvent} to all registered listeners.
     * 
     * @param index  the item (zero based index).
     * @param y  the new value (<code>null</code> permitted).
     * 
     * @since 1.0.1
     */
    public void updateByIndex(int index, Number y) {
        update(index, y);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[55]++;
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
    public void update(Number x, Number y) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[56]++;
        int index = indexOf(x);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[23]++;
            throw new SeriesException("No observation for x = " + x);

        }
        else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[24]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[58]++;
            XYDataItem item = getDataItem(index);
            item.setY(y);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[59]++;
            fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[60]++;
        }
    }
    
    /**
     * Adds or updates an item in the series and sends a 
     * {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param x  the x-value (<code>null</code> not permitted).
     * @param y  the y-value (<code>null</code> permitted).
     *
     * @return A copy of the overwritten data item, or <code>null</code> if no 
     *         item was overwritten.
     */
    public XYDataItem addOrUpdate(Number x, Number y) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[25]++;
            throw new IllegalArgumentException("Null 'x' argument.");
   
        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[26]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[62]++;
        XYDataItem overwritten = null;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[63]++;
        int index = indexOf(x);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[64]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[27]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[65]++;
            XYDataItem existing = (XYDataItem) this.data.get(index);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[66]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                overwritten = (XYDataItem) existing.clone();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[67]++;
            }
            catch (CloneNotSupportedException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[30]++;
                throw new SeriesException("Couldn't clone XYDataItem!");   
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[29]++;
}
  }
            existing.setY(y);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[68]++;

        }
        else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[28]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;
            // if the series is sorted, the negative index is a result from
            // Collections.binarySearch() and tells us where to insert the
            // new item...otherwise it will be just -1 and we should just
            // append the value to the list...
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.autoSort) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[31]++;
                this.data.add(-index - 1, new XYDataItem(x, y));
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[70]++;

            }
            else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[32]++;
                this.data.add(new XYDataItem(x, y));
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[71]++;
            }
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[72]++;
int CodeCoverConditionCoverageHelper_C19;
            // check if this addition will exceed the maximum item count...
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getItemCount() > this.maximumItemCount) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[33]++;
                this.data.remove(0);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[73]++;

            } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[34]++;}
        }            
        fireSeriesChanged();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[74]++;
        return overwritten;
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
    public int indexOf(Number x) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[75]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.autoSort) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[35]++;
            return Collections.binarySearch(this.data, new XYDataItem(x, null));

        }
        else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[36]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[76]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[10]++;


int CodeCoverConditionCoverageHelper_C21;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[10]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[11]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[12]++;
}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[77]++;
                XYDataItem item = (XYDataItem) this.data.get(i);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[78]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((item.getX().equals(x)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[37]++;
                    return i;
   
                } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[38]++;}
            }
            return -1;
        }
    }
    
    /**
     * Returns a new array containing the x and y values from this series.
     * 
     * @return A new array containing the x and y values from this series.
     * 
     * @since 1.0.4
     */
    public double[][] toArray() {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[79]++;
        int itemCount = getItemCount();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[80]++;
        double[][] result = new double[2][itemCount];
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[81]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[13]++;


int CodeCoverConditionCoverageHelper_C23;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[13]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[14]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[15]++;
}
            result[0][i] = this.getX(i).doubleValue();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[82]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[83]++;
            Number y = getY(i);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[84]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((y != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[39]++;
                result[1][i] = y.doubleValue();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[85]++;

            }
            else {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[40]++;
                result[1][i] = Double.NaN;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[86]++;
            }
        }
        return result;
    }
    
    /**
     * Returns a clone of the series.
     *
     * @return A clone of the series.
     * 
     * @throws CloneNotSupportedException if there is a cloning problem.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[87]++;
        XYSeries clone = (XYSeries) super.clone();
        clone.data = (List) ObjectUtilities.deepClone(this.data);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[88]++;
        return clone;
    }

    /**
     * Creates a new series by copying a subset of the data in this time series.
     *
     * @param start  the index of the first item to copy.
     * @param end  the index of the last item to copy.
     *
     * @return A series containing a copy of this series from start until end.
     * 
     * @throws CloneNotSupportedException if there is a cloning problem.
     */
    public XYSeries createCopy(int start, int end) 
        throws CloneNotSupportedException {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[89]++;

        XYSeries copy = (XYSeries) super.clone();
        copy.data = new java.util.ArrayList();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[90]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[91]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.data.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[41]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[92]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[16]++;


int CodeCoverConditionCoverageHelper_C26;
            for (int index = start;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((index <= end) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[16]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[17]--;
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.loops[18]++;
}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[93]++;
                XYDataItem item = (XYDataItem) this.data.get(index);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[94]++;
                XYDataItem clone = (XYDataItem) item.clone();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[95]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    copy.add(clone);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[96]++;
                }
                catch (SeriesException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[44]++;
                    System.err.println("Unable to add cloned data item.");
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[97]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[43]++;
}
  }
            }

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[42]++;}
        return copy;

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
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[98]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[45]++;
            return true;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[46]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[99]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((obj instanceof XYSeries) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[48]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[100]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[50]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[101]++;
        XYSeries that = (XYSeries) obj;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[102]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.maximumItemCount != that.maximumItemCount) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[52]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[103]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.autoSort != that.autoSort) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[54]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[104]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.allowDuplicateXValues != that.allowDuplicateXValues) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[56]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[105]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[58]++;}
        return true;
    }
    
    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[106]++;
        int result = super.hashCode();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[107]++;
        // it is too slow to look at every data item, so let's just look at
        // the first, middle and last items...
        int count = getItemCount();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[108]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[59]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[109]++;
            XYDataItem item = getDataItem(0);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[110]++;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[60]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[111]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[61]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[112]++;
            XYDataItem item = getDataItem(count - 1);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[113]++;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[62]++;}
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[114]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((count > 2) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[63]++;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[115]++;
            XYDataItem item = getDataItem(count / 2);
            result = 29 * result + item.hashCode();
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[116]++;

        } else {
  CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.branches[64]++;}
        result = 29 * result + this.maximumItemCount;
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[117]++;
        result = 29 * result + (this.autoSort ? 1 : 0);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[118]++;
        result = 29 * result + (this.allowDuplicateXValues ? 1 : 0);
CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9.statements[119]++;
        return result;
    }

}

class CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9 ());
  }
    public static long[] statements = new long[120];
    public static long[] branches = new long[65];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[37];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.XYSeries.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 36; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$iumu5o8oi3gr3zo3mue9 () {
    super("org.jfree.data.xy.XYSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 119; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 64; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 36; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.XYSeries.java");
      for (int i = 1; i <= 119; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 64; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 36; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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


