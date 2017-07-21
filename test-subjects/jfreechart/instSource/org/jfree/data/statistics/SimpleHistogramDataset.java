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
 * SimpleHistogramDataset.java
 * ---------------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sergei Ivanov;
 *
 * Changes
 * -------
 * 10-Jan-2005 : Version 1 (DG);
 * 21-May-2007 : Added clearObservations() and removeAllBins() (SI);
 * 10-Jul-2007 : Added null argument check to constructor (DG);
 * 
 */

package org.jfree.data.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A dataset used for creating simple histograms with custom defined bins.
 * 
 * @see HistogramDataset
 */
public class SimpleHistogramDataset extends AbstractIntervalXYDataset 
                                    implements IntervalXYDataset, 
                                               Cloneable, PublicCloneable, 
                                               Serializable {
  static {
    CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7997996479768018443L;
  static {
    CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[1]++;
  }
    
    /** The series key. */
    private Comparable key;
    
    /** The bins. */
    private List bins;
    
    /** 
     * A flag that controls whether or not the bin count is divided by the 
     * bin size. 
     */
    private boolean adjustForBinSize;
    
    /**
     * Creates a new histogram dataset.  Note that the 
     * <code>adjustForBinSize</code> flag defaults to <code>true</code>.
     * 
     * @param key  the series key (<code>null</code> not permitted).
     */
    public SimpleHistogramDataset(Comparable key) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[1]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[2]++;}
        this.key = key;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[3]++;
        this.bins = new ArrayList();
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[4]++;
        this.adjustForBinSize = true;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[5]++;
    }
    
    /**
     * Returns a flag that controls whether or not the bin count is divided by 
     * the bin size in the {@link #getXValue(int, int)} method.
     * 
     * @return A boolean.
     * 
     * @see #setAdjustForBinSize(boolean)
     */
    public boolean getAdjustForBinSize() {
        return this.adjustForBinSize;
    }
    
    /**
     * Sets the flag that controls whether or not the bin count is divided by 
     * the bin size in the {@link #getYValue(int, int)} method, and sends a
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @param adjust  the flag.
     * 
     * @see #getAdjustForBinSize()
     */
    public void setAdjustForBinSize(boolean adjust) {
        this.adjustForBinSize = adjust;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[6]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[7]++;
    }
    
    /**
     * Returns the number of series in the dataset (always 1 for this dataset).
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the key for a series.  Since this dataset only stores a single
     * series, the <code>series</code> argument is ignored.
     *
     * @param series  the series (zero-based index, ignored in this dataset).
     *
     * @return The key for the series.
     */
    public Comparable getSeriesKey(int series) {
        return this.key;    
    }
    
    /**
     * Returns the order of the domain (or X) values returned by the dataset.
     * 
     * @return The order (never <code>null</code>).
     */
    public DomainOrder getDomainOrder() {
        return DomainOrder.ASCENDING;
    }
    
    /**
     * Returns the number of items in a series.  Since this dataset only stores
     * a single series, the <code>series</code> argument is ignored.
     *
     * @param series  the series index (zero-based, ignored in this dataset).
     *
     * @return The item count.
     */
    public int getItemCount(int series) {
        return this.bins.size();
    }
    
    /**
     * Adds a bin to the dataset.  An exception is thrown if the bin overlaps 
     * with any existing bin in the dataset.
     * 
     * @param bin  the bin (<code>null</code> not permitted).
     * 
     * @see #removeAllBins()
     */
    public void addBin(SimpleHistogramBin bin) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[8]++;
        // check that the new bin doesn't overlap with any existing bin
        Iterator iterator = this.bins.iterator();
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[1]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[2]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[3]++;
}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[10]++;
            SimpleHistogramBin existingBin 
                    = (SimpleHistogramBin) iterator.next();
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((bin.overlapsWith(existingBin)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[3]++;
                throw new RuntimeException("Overlapping bin");

            } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[4]++;}
        }
        this.bins.add(bin);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[12]++;
        Collections.sort(this.bins);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[13]++;
    }
    
    /**
     * Adds an observation to the dataset (by incrementing the item count for 
     * the appropriate bin).  A runtime exception is thrown if the value does 
     * not fit into any bin.
     * 
     * @param value  the value.
     */
    public void addObservation(double value) {
        addObservation(value, true);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[14]++;
    }
    
    /**
     * Adds an observation to the dataset (by incrementing the item count for 
     * the appropriate bin).  A runtime exception is thrown if the value does 
     * not fit into any bin.
     * 
     * @param value  the value.
     * @param notify  send {@link DatasetChangeEvent} to listeners?
     */
    public void addObservation(double value, boolean notify) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[15]++;
        boolean placed = false;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[16]++;
        Iterator iterator = this.bins.iterator();
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
        while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((placed) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[4]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[5]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[6]++;
}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[18]++;
            SimpleHistogramBin bin = (SimpleHistogramBin) iterator.next();
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((bin.accepts(value)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[5]++;
                bin.setItemCount(bin.getItemCount() + 1);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[20]++;
                placed = true;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[21]++;

            } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[6]++;}
        }
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((placed) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[7]++;
            throw new RuntimeException("No bin.");

        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[8]++;}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[9]++;
            notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[24]++;
 
        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[10]++;}
    }
    
    /**
     * Adds a set of values to the dataset and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @param values  the values (<code>null</code> not permitted).
     * 
     * @see #clearObservations()
     */
    public void addObservations(double[] values) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[25]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[7]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[8]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[9]++;
}
            addObservation(values[i], false);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[26]++;
        }
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[27]++;
    }

    /**
     * Removes all current observation data and sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @since 1.0.6
     * 
     * @see #addObservations(double[])
     * @see #removeAllBins()
     */
    public void clearObservations() {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[28]++;
        Iterator iterator = this.bins.iterator();
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[29]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[10]++;


int CodeCoverConditionCoverageHelper_C9;
        while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[10]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[11]--;
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.loops[12]++;
}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[30]++;
            SimpleHistogramBin bin = (SimpleHistogramBin) iterator.next();
            bin.setItemCount(0);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[31]++;
        }
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[32]++;
    }
    
    /**
     * Removes all bins and sends a {@link DatasetChangeEvent} to all 
     * registered listeners.
     * 
     * @since 1.0.6
     * 
     * @see #addBin(SimpleHistogramBin)
     */
    public void removeAllBins() {
        this.bins = new ArrayList();
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[33]++;
        notifyListeners(new DatasetChangeEvent(this, this));
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[34]++;
    }
    
    /**
     * Returns the x-value for an item within a series.  The x-values may or 
     * may not be returned in ascending order, that is up to the class 
     * implementing the interface.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The x-value (never <code>null</code>).
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
     * @return The x-value.
     */
    public double getXValue(int series, int item) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[35]++;
        SimpleHistogramBin bin = (SimpleHistogramBin) this.bins.get(item);
        return (bin.getLowerBound() + bin.getUpperBound()) / 2.0;
    }
    
    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The y-value (possibly <code>null</code>).
     */
    public Number getY(int series, int item) {
        return new Double(getYValue(series, item));
    }

    /**
     * Returns the y-value (as a double primitive) for an item within a series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The y-value.
     * 
     * @see #getAdjustForBinSize()
     */
    public double getYValue(int series, int item) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[36]++;
        SimpleHistogramBin bin = (SimpleHistogramBin) this.bins.get(item);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.adjustForBinSize) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[11]++;
            return bin.getItemCount() 
                   / (bin.getUpperBound() - bin.getLowerBound());

        }
        else {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[12]++;
            return bin.getItemCount();
        }
    }
    
    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getStartX(int series, int item) {
        return new Double(getStartXValue(series, item));
    }

    /**
     * Returns the start x-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     * 
     * @return The start x-value.
     */
    public double getStartXValue(int series, int item) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[38]++;
        SimpleHistogramBin bin = (SimpleHistogramBin) this.bins.get(item);
        return bin.getLowerBound();
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getEndX(int series, int item) {
        return new Double(getEndXValue(series, item));
    }

    /**
     * Returns the end x-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The end x-value.
     */
    public double getEndXValue(int series, int item) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[39]++;
        SimpleHistogramBin bin = (SimpleHistogramBin) this.bins.get(item);
        return bin.getUpperBound();
    }

    /**
     * Returns the starting Y value for the specified series and item.
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
     * Returns the start y-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The start y-value.
     */
    public double getStartYValue(int series, int item) {
        return getYValue(series, item);
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return The value.
     */
    public Number getEndY(int series, int item) {
        return getY(series, item);
    }

    /**
     * Returns the end y-value (as a double primitive) for an item within a 
     * series.
     * 
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * 
     * @return The end y-value.
     */
    public double getEndYValue(int series, int item) {
        return getYValue(series, item);
    }

    /**
     * Compares the dataset for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[14]++;}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj instanceof SimpleHistogramDataset) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[16]++;}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[42]++;
        SimpleHistogramDataset that = (SimpleHistogramDataset) obj;
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.key.equals(that.key)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[18]++;}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.adjustForBinSize != that.adjustForBinSize) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[20]++;}
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[45]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.bins.equals(that.bins)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.branches[22]++;}
        return true;
    }
    
    /**
     * Returns a clone of the dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not thrown by this class, but maybe 
     *         by subclasses (if any).
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[46]++;
        SimpleHistogramDataset clone = (SimpleHistogramDataset) super.clone();
        clone.bins = (List) ObjectUtilities.deepClone(this.bins);
CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp.statements[47]++;
        return clone;
    }
    
}

class CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp ());
  }
    public static long[] statements = new long[48];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.SimpleHistogramDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$5c11ih66y47uw7wq8rfi9f6e4zrqw3f5mqgq0x8jmp () {
    super("org.jfree.data.statistics.SimpleHistogramDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 47; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.SimpleHistogramDataset.java");
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
    for (int i = 1; i <= 15; i++) {
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

