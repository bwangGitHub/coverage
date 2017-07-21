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
 * MatrixSeriesCollection.java
 * ---------------------------
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh;;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 10-Jul-2003 : Version 1 contributed by Barak Naveh (DG);
 * 05-May-2004 : Now extends AbstractXYZDataset (DG);
 * 15-Jul-2004 : Switched getZ() and getZValue() methods (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 27-Nov-2006 : Added clone() override (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */
 
package org.jfree.data.xy;

import java.io.Serializable;
import java.util.List;

import org.jfree.util.ObjectUtilities;

/**
 * Represents a collection of {@link MatrixSeries} that can be used as a 
 * dataset.
 *
 * @see org.jfree.data.xy.MatrixSeries
 */
public class MatrixSeriesCollection extends AbstractXYZDataset
                                    implements XYZDataset, Serializable {
  static {
    CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -3197705779242543945L;
  static {
    CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[1]++;
  }
    
    /** The series that are included in the collection. */
    private List seriesList;

    /**
     * Constructs an empty dataset.
     */
    public MatrixSeriesCollection() {
        this(null);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[2]++;
    }


    /**
     * Constructs a dataset and populates it with a single matrix series.
     *
     * @param series the time series.
     */
    public MatrixSeriesCollection(MatrixSeries series) {
        this.seriesList = new java.util.ArrayList();
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[3]++;
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((series != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[1]++;
            this.seriesList.add(series);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[5]++;
            series.addChangeListener(this);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[6]++;

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[2]++;}
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param seriesIndex zero-based series index.
     *
     * @return The number of items in the specified series.
     */
    public int getItemCount(int seriesIndex) {
        return getSeries(seriesIndex).getItemCount();
    }


    /**
     * Returns the series having the specified index.
     *
     * @param seriesIndex zero-based series index.
     *
     * @return The series.
     *
     * @throws IllegalArgumentException
     */
    public MatrixSeries getSeries(int seriesIndex) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((seriesIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((seriesIndex > getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[3]++;
            throw new IllegalArgumentException("Index outside valid range.");

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[4]++;}
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[8]++;

        MatrixSeries series = (MatrixSeries) this.seriesList.get(seriesIndex);

        return series;
    }


    /**
     * Returns the number of series in the collection.
     *
     * @return The number of series in the collection.
     */
    public int getSeriesCount() {
        return this.seriesList.size();
    }


    /**
     * Returns the key for a series.
     *
     * @param seriesIndex zero-based series index.
     *
     * @return The key for a series.
     */
    public Comparable getSeriesKey(int seriesIndex) {
        return getSeries(seriesIndex).getKey();
    }


    /**
     * Returns the j index value of the specified Mij matrix item in the
     * specified matrix series.
     *
     * @param seriesIndex zero-based series index.
     * @param itemIndex zero-based item index.
     *
     * @return The j index value for the specified matrix item.
     *
     * @see org.jfree.data.xy.XYDataset#getXValue(int, int)
     */
    public Number getX(int seriesIndex, int itemIndex) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[9]++;
        MatrixSeries series = (MatrixSeries) this.seriesList.get(seriesIndex);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[10]++;
        int x = series.getItemColumn(itemIndex);

        return new Integer(x); // I know it's bad to create object. better idea?
    }


    /**
     * Returns the i index value of the specified Mij matrix item in the
     * specified matrix series.
     *
     * @param seriesIndex zero-based series index.
     * @param itemIndex zero-based item index.
     *
     * @return The i index value for the specified matrix item.
     *
     * @see org.jfree.data.xy.XYDataset#getYValue(int, int)
     */
    public Number getY(int seriesIndex, int itemIndex) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[11]++;
        MatrixSeries series = (MatrixSeries) this.seriesList.get(seriesIndex);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[12]++;
        int y = series.getItemRow(itemIndex);

        return new Integer(y); // I know it's bad to create object. better idea?
    }


    /**
     * Returns the Mij item value of the specified Mij matrix item in the
     * specified matrix series.
     *
     * @param seriesIndex the series (zero-based index).
     * @param itemIndex zero-based item index.
     *
     * @return The Mij item value for the specified matrix item.
     *
     * @see org.jfree.data.xy.XYZDataset#getZValue(int, int)
     */
    public Number getZ(int seriesIndex, int itemIndex) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[13]++;
        MatrixSeries series = (MatrixSeries) this.seriesList.get(seriesIndex);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[14]++;
        Number z = series.getItem(itemIndex);
        return z;
    }


    /**
     * Adds a series to the collection.
     * <P>
     * Notifies all registered listeners that the dataset has changed.
     * </p>
     *
     * @param series the series.
     *
     * @throws IllegalArgumentException
     */
    public void addSeries(MatrixSeries series) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[5]++;
            throw new IllegalArgumentException("Cannot add null series.");

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[6]++;}
        // FIXME: Check that there isn't already a series with the same key
        
        // add the series...
        this.seriesList.add(series);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[16]++;
        series.addChangeListener(this);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[17]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[18]++;
    }


    /**
     * Tests this collection for equality with an arbitrary object.
     *
     * @param obj the object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[8]++;}
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[10]++;}
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof MatrixSeriesCollection) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[11]++;
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[22]++;
            MatrixSeriesCollection c = (MatrixSeriesCollection) obj;

            return ObjectUtilities.equal(this.seriesList, c.seriesList);

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[12]++;}

        return false;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return (this.seriesList != null ? this.seriesList.hashCode() : 0);
    }
    
    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[23]++;
        MatrixSeriesCollection clone = (MatrixSeriesCollection) super.clone();
        clone.seriesList = (List) ObjectUtilities.deepClone(this.seriesList);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[24]++;
        return clone;
    }

    /**
     * Removes all the series from the collection.
     * <P>
     * Notifies all registered listeners that the dataset has changed.
     * </p>
     */
    public void removeAllSeries() {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[25]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
        // Unregister the collection as a change listener to each series in 
        // the collection.
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < this.seriesList.size()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.loops[1]--;
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.loops[2]--;
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.loops[3]++;
}
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[26]++;
            MatrixSeries series = (MatrixSeries) this.seriesList.get(i);
            series.removeChangeListener(this);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[27]++;
        }

        // Remove all the series from the collection and notify listeners.
        this.seriesList.clear();
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[28]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[29]++;
    }


    /**
     * Removes a series from the collection.
     * <P>
     * Notifies all registered listeners that the dataset has changed.
     * </p>
     *
     * @param series the series.
     *
     * @throws IllegalArgumentException
     */
    public void removeSeries(MatrixSeries series) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((series == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[13]++;
            throw new IllegalArgumentException("Cannot remove null series.");

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[14]++;}
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;

        // remove the series...
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.seriesList.contains(series)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[15]++;
            series.removeChangeListener(this);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[32]++;
            this.seriesList.remove(series);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[33]++;
            fireDatasetChanged();
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[34]++;

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[16]++;}
    }


    /**
     * Removes a series from the collection.
     * <P>
     * Notifies all registered listeners that the dataset has changed.
     *
     * @param seriesIndex the series (zero based index).
     *
     * @throws IllegalArgumentException
     */
    public void removeSeries(int seriesIndex) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((seriesIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((seriesIndex > getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[17]++;
            throw new IllegalArgumentException("Index outside valid range.");

        } else {
  CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.branches[18]++;}
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[36]++;

        // fetch the series, remove the change listener, then remove the series.
        MatrixSeries series = (MatrixSeries) this.seriesList.get(seriesIndex);
        series.removeChangeListener(this);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[37]++;
        this.seriesList.remove(seriesIndex);
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[38]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69.statements[39]++;
    }
    
}

class CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69 ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.MatrixSeriesCollection.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$4y56umvtopacm3pe10za6axnrsb8q8rf6u9ninrs69 () {
    super("org.jfree.data.xy.MatrixSeriesCollection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.MatrixSeriesCollection.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

