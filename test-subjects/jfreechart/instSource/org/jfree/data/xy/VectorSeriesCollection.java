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
 * VectorSeriesCollection.java
 * ---------------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Added indexOf(), removeSeries() and removeAllSeries() 
 *               methods (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;
import java.util.List;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.util.ObjectUtilities;

/**
 * A collection of {@link VectorSeries} objects.
 * 
 * @since 1.0.6
 */
public class VectorSeriesCollection extends AbstractXYDataset
                                implements VectorXYDataset, Serializable {
  static {
    CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.ping();
  }


    /** Storage for the data series. */
    private List data;
    
    /** 
     * Creates a new instance of <code>VectorSeriesCollection</code>. 
     */
    public VectorSeriesCollection() {
        this.data = new java.util.ArrayList();
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[1]++;
    }

    /**
     * Adds a series to the collection and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     */
    public void addSeries(VectorSeries series) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[1]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[2]++;}
        this.data.add(series);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[3]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[4]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[5]++;
    }
    
    /**
     * Removes the specified series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param series  the series (<code>null</code> not permitted).
     * 
     * @return A boolean indicating whether the series has actually been 
     *         removed.
     */
    public boolean removeSeries(VectorSeries series) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[3]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[4]++;}
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[7]++;
        boolean removed = this.data.remove(series);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[5]++;
            series.removeChangeListener(this);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[9]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[10]++;
            
        } else {
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[6]++;}
        return removed;
    }
    
    /**
     * Removes all the series from the collection and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     */
    public void removeAllSeries() {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;

        // deregister the collection as a change listener to each series in the
        // collection
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < this.data.size()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.loops[1]--;
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.loops[2]--;
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.loops[3]++;
}
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[12]++;
            VectorSeries series = (VectorSeries) this.data.get(i);
            series.removeChangeListener(this);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[13]++;
        }

        // remove all the series from the collection and notify listeners.
        this.data.clear();
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[14]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[15]++;

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
     * Returns a series from the collection.
     *
     * @param series  the series index (zero-based).
     *
     * @return The series.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the
     *     range <code>0</code> to <code>getSeriesCount() - 1</code>.
     */
    public VectorSeries getSeries(int series) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((series >= getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[7]++;
            throw new IllegalArgumentException("Series index out of bounds");

        } else {
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[8]++;}
        return (VectorSeries) this.data.get(series);
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
     * Returns the index of the specified series, or -1 if that series is not
     * present in the dataset.
     * 
     * @param series  the series (<code>null</code> not permitted).
     * 
     * @return The series index.
     */
    public int indexOf(VectorSeries series) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[9]++;
            throw new IllegalArgumentException("Null 'series' argument.");

        } else {
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[10]++;}
        return this.data.indexOf(series);
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
     * Returns the x-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The x-value.
     */
    public double getXValue(int series, int item) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[18]++;
        VectorSeries s = (VectorSeries) this.data.get(series);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[19]++;
        VectorDataItem di = (VectorDataItem) s.getDataItem(item);
        return di.getXValue();
    }

    /**
     * Returns the x-value for an item within a series.  Note that this method
     * creates a new {@link Double} instance every time it is called---use
     * {@link #getXValue(int, int)} instead, if possible.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The x-value.
     */
    public Number getX(int series, int item) {
        return new Double(getXValue(series, item));
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The y-value.
     */
    public double getYValue(int series, int item) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[20]++;
        VectorSeries s = (VectorSeries) this.data.get(series);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[21]++;
        VectorDataItem di = (VectorDataItem) s.getDataItem(item);
        return di.getYValue();
    }

    /**
     * Returns the y-value for an item within a series.  Note that this method
     * creates a new {@link Double} instance every time it is called---use
     * {@link #getYValue(int, int)} instead, if possible.
     *
     * @param series  the series index.
     * @param item  the item index.
     *
     * @return The y-value.
     */
    public Number getY(int series, int item) {
        return new Double(getYValue(series, item));
    }

    /**
     * Returns the vector for an item in a series.  
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The vector (possibly <code>null</code>).
     */
    public Vector getVector(int series, int item) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[22]++;
        VectorSeries s = (VectorSeries) this.data.get(series);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[23]++;
        VectorDataItem di = (VectorDataItem) s.getDataItem(item);
        return di.getVector();
    }
    
    /**
     * Returns the x-component of the vector for an item in a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The x-component of the vector.
     */
    public double getVectorXValue(int series, int item) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[24]++;
        VectorSeries s = (VectorSeries) this.data.get(series);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[25]++;
        VectorDataItem di = (VectorDataItem) s.getDataItem(item);
        return di.getVectorX();
    }

    /**
     * Returns the y-component of the vector for an item in a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The y-component of the vector.
     */
    public double getVectorYValue(int series, int item) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[26]++;
        VectorSeries s = (VectorSeries) this.data.get(series);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[27]++;
        VectorDataItem di = (VectorDataItem) s.getDataItem(item);
        return di.getVectorY();
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean. 
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[12]++;}
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof VectorSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.branches[14]++;}
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[30]++;
        VectorSeriesCollection that = (VectorSeriesCollection) obj;
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
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[31]++;
        VectorSeriesCollection clone 
                = (VectorSeriesCollection) super.clone();
        clone.data = (List) ObjectUtilities.deepClone(this.data);
CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd.statements[32]++;
        return clone;
    }
  
}

class CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.VectorSeriesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$5iwdb76t3g4jdzvb5l52r8h3z3oaoqmh3jgnpy3otd () {
    super("org.jfree.data.xy.VectorSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.VectorSeriesCollection.java");
      for (int i = 1; i <= 32; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

