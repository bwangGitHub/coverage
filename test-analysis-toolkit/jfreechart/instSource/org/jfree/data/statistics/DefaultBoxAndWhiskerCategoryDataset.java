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
 * ----------------------------------------
 * DefaultBoxAndWhiskerCategoryDataset.java
 * ----------------------------------------
 * (C) Copyright 2003-2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 27-Aug-2003 : Moved from org.jfree.data --> org.jfree.data.statistics (DG);
 * 12-Nov-2003 : Changed 'data' from private to protected and added a new 'add' 
 *               method as proposed by Tim Bardzil.  Also removed old code (DG);
 * 01-Mar-2004 : Added equals() method (DG);
 * 18-Nov-2004 : Updates for changes in RangeInfo interface (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 17-Apr-2007 : Fixed bug 1701822 (DG);
 * 13-Jun-2007 : Fixed error in previous patch (DG);
 * 28-Sep-2007 : Fixed cloning bug (DG);
 * 02-Oct-2007 : Fixed bug in updating cached bounds (DG);
 * 03-Oct-2007 : Fixed another bug in updating cached bounds, added removal
 *               methods (DG);
 *
 */

package org.jfree.data.statistics;

import java.util.List;

import org.jfree.data.KeyedObjects2D;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A convenience class that provides a default implementation of the
 * {@link BoxAndWhiskerCategoryDataset} interface.
 */
public class DefaultBoxAndWhiskerCategoryDataset extends AbstractDataset
        implements BoxAndWhiskerCategoryDataset, RangeInfo, PublicCloneable {
  static {
    CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.ping();
  }


    /** Storage for the data. */
    protected KeyedObjects2D data;

    /** The minimum range value. */
    private double minimumRangeValue;
    
    /** The row index for the cell that the minimum range value comes from. */
    private int minimumRangeValueRow;
    
    /** 
     * The column index for the cell that the minimum range value comes from. 
     */
    private int minimumRangeValueColumn;

    /** The maximum range value. */
    private double maximumRangeValue;

    /** The row index for the cell that the maximum range value comes from. */
    private int maximumRangeValueRow;
    
    /** 
     * The column index for the cell that the maximum range value comes from. 
     */
    private int maximumRangeValueColumn;
    
    /**
     * Creates a new dataset.
     */
    public DefaultBoxAndWhiskerCategoryDataset() {
        this.data = new KeyedObjects2D();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[1]++;
        this.minimumRangeValue = Double.NaN;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[2]++;
        this.minimumRangeValueRow = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[3]++;
        this.minimumRangeValueColumn = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[4]++;
        this.maximumRangeValue = Double.NaN;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[5]++;
        this.maximumRangeValueRow = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[6]++;
        this.maximumRangeValueColumn = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[7]++;
    }

    /**
     * Adds a list of values relating to one box-and-whisker entity to the 
     * table.  The various median values are calculated.
     *
     * @param list  a collection of values from which the various medians will 
     *              be calculated.
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #add(BoxAndWhiskerItem, Comparable, Comparable)
     */
    public void add(List list, Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[8]++;
        BoxAndWhiskerItem item = BoxAndWhiskerCalculator
                .calculateBoxAndWhiskerStatistics(list);
        add(item, rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[9]++;
    }
    
    /**
     * Adds a list of values relating to one Box and Whisker entity to the 
     * table.  The various median values are calculated.
     *
     * @param item  a box and whisker item (<code>null</code> not permitted).
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #add(List, Comparable, Comparable)
     */
    public void add(BoxAndWhiskerItem item, Comparable rowKey, 
            Comparable columnKey) {

        this.data.addObject(item, rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[10]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[11]++;
        
        // update cached min and max values
        int r = this.data.getRowIndex(rowKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[12]++;
        int c = this.data.getColumnIndex(columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((this.maximumRangeValueRow == r) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((this.maximumRangeValueColumn 
                == c) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((this.minimumRangeValueRow == r) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.minimumRangeValueColumn == c) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false))  {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[1]++;
            updateBounds();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[14]++;

        }
        else {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[2]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[15]++;
        
            double minval = Double.NaN;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((item.getMinOutlier() != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[3]++;
                minval = item.getMinOutlier().doubleValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[17]++;

            } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[4]++;}
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[18]++;
            double maxval = Double.NaN;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((item.getMaxOutlier() != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[5]++;
                maxval = item.getMaxOutlier().doubleValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[20]++;

            } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[6]++;}
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.maximumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[7]++;
                this.maximumRangeValue = maxval;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[22]++;
                this.maximumRangeValueRow = r;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[23]++;
                this.maximumRangeValueColumn = c;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[24]++;

            }
            else {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[8]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[25]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((maxval > this.maximumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[9]++;
                this.maximumRangeValue = maxval;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[26]++;
                this.maximumRangeValueRow = r;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[27]++;
                this.maximumRangeValueColumn = c;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[28]++;

            } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[10]++;}
}
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((Double.isNaN(this.minimumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[11]++;
                this.minimumRangeValue = minval;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[30]++;
                this.minimumRangeValueRow = r;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[31]++;
                this.minimumRangeValueColumn = c;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[32]++;

            }
            else {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[12]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[33]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((minval < this.minimumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[13]++;
                this.minimumRangeValue = minval;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[34]++;
                this.minimumRangeValueRow = r;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[35]++;
                this.minimumRangeValueColumn = c;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[36]++;

            } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[14]++;}
}
        }
        
        fireDatasetChanged();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[37]++;

    }
    
    /**
     * Removes an item from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param columnKey  the column key (<code>null</code> not permitted).
     * 
     * @see #add(BoxAndWhiskerItem, Comparable, Comparable)
     * 
     * @since 1.0.7
     */
    public void remove(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[38]++;
        // defer null argument checks
        int r = getRowIndex(rowKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[39]++;
        int c = getColumnIndex(columnKey);
        this.data.removeObject(rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[40]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
        
        // if this cell held a maximum and/or minimum value, we'll need to
        // update the cached bounds...
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C8 |= (128)) == 0 || true) &&
 ((this.maximumRangeValueRow == r) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((this.maximumRangeValueColumn 
                == c) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((this.minimumRangeValueRow == r) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.minimumRangeValueColumn == c) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 4) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 4) && false))  {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[15]++;
            updateBounds();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[42]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[16]++;}
        
        fireDatasetChanged();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[43]++;
    }

    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowIndex  the row index.
     * 
     * @see #removeColumn(int)
     * 
     * @since 1.0.7
     */
    public void removeRow(int rowIndex) {
        this.data.removeRow(rowIndex);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[44]++;
        updateBounds();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[45]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[46]++;
    }

    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey  the row key.
     * 
     * @see #removeColumn(Comparable)
     * 
     * @since 1.0.7
     */
    public void removeRow(Comparable rowKey) {
        this.data.removeRow(rowKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[47]++;
        updateBounds();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[48]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[49]++;
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnIndex  the column index.
     * 
     * @see #removeRow(int)
     * 
     * @since 1.0.7
     */
    public void removeColumn(int columnIndex) {
        this.data.removeColumn(columnIndex);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[50]++;
        updateBounds();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[51]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[52]++;
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnKey  the column key.
     * 
     * @see #removeRow(Comparable)
     * 
     * @since 1.0.7
     */
    public void removeColumn(Comparable columnKey) {
        this.data.removeColumn(columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[53]++;
        updateBounds();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[54]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[55]++;
    }

    /**
     * Clears all data from the dataset and sends a {@link DatasetChangeEvent} 
     * to all registered listeners.
     * 
     * @since 1.0.7
     */
    public void clear() {
        this.data.clear();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[56]++;
        updateBounds();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[57]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[58]++;
    }

    /**
     * Return an item from within the dataset.
     * 
     * @param row  the row index.
     * @param column  the column index.
     * 
     * @return The item.
     */
    public BoxAndWhiskerItem getItem(int row, int column) {
        return (BoxAndWhiskerItem) this.data.getObject(row, column);  
    }

    /**
     * Returns the value for an item.
     *
     * @param row  the row index.
     * @param column  the column index.
     *
     * @return The value.
     * 
     * @see #getMedianValue(int, int)
     * @see #getValue(Comparable, Comparable)
     */
    public Number getValue(int row, int column) {
        return getMedianValue(row, column);
    }

    /**
     * Returns the value for an item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the columnKey.
     *
     * @return The value.
     * 
     * @see #getMedianValue(Comparable, Comparable)
     * @see #getValue(int, int)
     */
    public Number getValue(Comparable rowKey, Comparable columnKey) {
        return getMedianValue(rowKey, columnKey);
    }

    /**
     * Returns the mean value for an item.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The mean value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMeanValue(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[59]++;

        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[60]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(row, 
                column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[61]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[17]++;
            result = item.getMean();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[62]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[18]++;}
        return result;

    }

    /**
     * Returns the mean value for an item.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return The mean value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMeanValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[63]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[64]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[65]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[19]++;
            result = item.getMean();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[66]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[20]++;}
        return result;
    }

    /**
     * Returns the median value for an item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The median value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMedianValue(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[67]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[68]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(row, 
                column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[69]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[21]++;
            result = item.getMedian();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[70]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[22]++;}
        return result;
    }

    /**
     * Returns the median value for an item.
     *
     * @param rowKey  the row key.
     * @param columnKey  the columnKey.
     *
     * @return The median value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMedianValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[71]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[72]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[73]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[23]++;
            result = item.getMedian();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[74]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[24]++;}
        return result;
    }

    /**
     * Returns the first quartile value.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The first quartile value.
     * 
     * @see #getItem(int, int)
     */
    public Number getQ1Value(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[75]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[76]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                row, column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[77]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[25]++;
            result = item.getQ1();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[78]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[26]++;}
        return result;
    }

    /**
     * Returns the first quartile value.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return The first quartile value.
     * 
     * @see #getItem(int, int)
     */
    public Number getQ1Value(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[79]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[80]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[81]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[27]++;
            result = item.getQ1();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[82]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[28]++;}
        return result;
    }

    /**
     * Returns the third quartile value.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The third quartile value.
     * 
     * @see #getItem(int, int)
     */
    public Number getQ3Value(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[83]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[84]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                row, column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[85]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[29]++;
            result = item.getQ3();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[86]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[30]++;}
        return result;
    }

    /**
     * Returns the third quartile value.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return The third quartile value.
     * 
     * @see #getItem(int, int)
     */
    public Number getQ3Value(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[87]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[88]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[89]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[31]++;
            result = item.getQ3();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[90]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[32]++;}
        return result;
    }

    /**
     * Returns the column index for a given key.
     *
     * @param key  the column key (<code>null</code> not permitted).
     *
     * @return The column index.
     * 
     * @see #getColumnKey(int)
     */
    public int getColumnIndex(Comparable key) {
        return this.data.getColumnIndex(key);
    }

    /**
     * Returns a column key.
     *
     * @param column  the column index (zero-based).
     *
     * @return The column key.
     * 
     * @see #getColumnIndex(Comparable)
     */
    public Comparable getColumnKey(int column) {
        return this.data.getColumnKey(column);
    }

    /**
     * Returns the column keys.
     *
     * @return The keys.
     * 
     * @see #getRowKeys()
     */
    public List getColumnKeys() {
        return this.data.getColumnKeys();
    }

    /**
     * Returns the row index for a given key.
     *
     * @param key  the row key (<code>null</code> not permitted).
     *
     * @return The row index.
     * 
     * @see #getRowKey(int)
     */
    public int getRowIndex(Comparable key) {
        // defer null argument check
        return this.data.getRowIndex(key);
    }

    /**
     * Returns a row key.
     *
     * @param row  the row index (zero-based).
     *
     * @return The row key.
     * 
     * @see #getRowIndex(Comparable)
     */
    public Comparable getRowKey(int row) {
        return this.data.getRowKey(row);
    }

    /**
     * Returns the row keys.
     *
     * @return The keys.
     * 
     * @see #getColumnKeys()
     */
    public List getRowKeys() {
        return this.data.getRowKeys();
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return The row count.
     * 
     * @see #getColumnCount()
     */
    public int getRowCount() {
        return this.data.getRowCount();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return The column count.
     * 
     * @see #getRowCount()
     */
    public int getColumnCount() {
        return this.data.getColumnCount();
    }

    /**
     * Returns the minimum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The minimum value.
     * 
     * @see #getRangeUpperBound(boolean)
     */
    public double getRangeLowerBound(boolean includeInterval) {
        return this.minimumRangeValue;
    }

    /**
     * Returns the maximum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The maximum value.
     * 
     * @see #getRangeLowerBound(boolean)
     */
    public double getRangeUpperBound(boolean includeInterval) {
        return this.maximumRangeValue;
    }

    /**
     * Returns the range of the values in this dataset's range.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The range.
     */
    public Range getRangeBounds(boolean includeInterval) {
        return new Range(this.minimumRangeValue, this.maximumRangeValue);
    }
    
    /**
     * Returns the minimum regular (non outlier) value for an item.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The minimum regular value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMinRegularValue(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[91]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[92]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                row, column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[93]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[33]++;
            result = item.getMinRegularValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[94]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[34]++;}
        return result;
    }

    /**
     * Returns the minimum regular (non outlier) value for an item.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return The minimum regular value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMinRegularValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[95]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[96]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[97]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[35]++;
            result = item.getMinRegularValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[98]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[36]++;}
        return result;
    }

    /**
     * Returns the maximum regular (non outlier) value for an item.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The maximum regular value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMaxRegularValue(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[99]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[100]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                row, column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[101]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[37]++;
            result = item.getMaxRegularValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[102]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[38]++;}
        return result;
    }

    /**
     * Returns the maximum regular (non outlier) value for an item.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return The maximum regular value.
     * 
     * @see #getItem(int, int)
     */
    public Number getMaxRegularValue(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[103]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[104]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[105]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[39]++;
            result = item.getMaxRegularValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[106]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[40]++;}
        return result;
    }

    /**
     * Returns the minimum outlier (non farout) value for an item.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The minimum outlier.
     * 
     * @see #getItem(int, int)
     */
    public Number getMinOutlier(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[107]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[108]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                row, column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[109]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[41]++;
            result = item.getMinOutlier();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[110]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[42]++;}
        return result;
    }

    /**
     * Returns the minimum outlier (non farout) value for an item.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return The minimum outlier.
     * 
     * @see #getItem(int, int)
     */
    public Number getMinOutlier(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[111]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[112]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[113]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[43]++;
            result = item.getMinOutlier();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[114]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[44]++;}
        return result;
    }

    /**
     * Returns the maximum outlier (non farout) value for an item.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return The maximum outlier.
     * 
     * @see #getItem(int, int)
     */
    public Number getMaxOutlier(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[115]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[116]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                row, column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[117]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[45]++;
            result = item.getMaxOutlier();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[118]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[46]++;}
        return result;
    }

    /**
     * Returns the maximum outlier (non farout) value for an item.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return The maximum outlier.
     * 
     * @see #getItem(int, int)
     */
    public Number getMaxOutlier(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[119]++;
        Number result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[120]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[121]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[47]++;
            result = item.getMaxOutlier();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[122]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[48]++;}
        return result;
    }

    /**
     * Returns a list of outlier values for an item.
     * 
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * 
     * @return A list of outlier values.
     * 
     * @see #getItem(int, int)
     */
    public List getOutliers(int row, int column) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[123]++;
        List result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[124]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                row, column);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[125]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[49]++;
            result = item.getOutliers();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[126]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[50]++;}
        return result;
    }

    /**
     * Returns a list of outlier values for an item.
     * 
     * @param rowKey  the row key.
     * @param columnKey  the column key.
     * 
     * @return A list of outlier values.
     * 
     * @see #getItem(int, int)
     */
    public List getOutliers(Comparable rowKey, Comparable columnKey) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[127]++;
        List result = null;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[128]++;
        BoxAndWhiskerItem item = (BoxAndWhiskerItem) this.data.getObject(
                rowKey, columnKey);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[129]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[51]++;
            result = item.getOutliers();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[130]++;

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[52]++;}
        return result;
    }
    
    /**
     * Resets the cached bounds, by iterating over the entire dataset to find
     * the current bounds.
     */
    private void updateBounds() {
        this.minimumRangeValue = Double.NaN;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[131]++;
        this.minimumRangeValueRow = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[132]++;
        this.minimumRangeValueColumn = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[133]++;
        this.maximumRangeValue = Double.NaN;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[134]++;
        this.maximumRangeValueRow = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[135]++;
        this.maximumRangeValueColumn = -1;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[136]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[137]++;
        int rowCount = getRowCount();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[138]++;
        int columnCount = getColumnCount();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[139]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[1]++;


int CodeCoverConditionCoverageHelper_C27;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((r < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[1]--;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[2]--;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[3]++;
}
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[140]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[4]++;


int CodeCoverConditionCoverageHelper_C28;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[4]--;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[5]--;
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.loops[6]++;
}
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[141]++;
                BoxAndWhiskerItem item = getItem(r, c);
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[142]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[53]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[143]++;
                    Number min = item.getMinOutlier();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[144]++;
int CodeCoverConditionCoverageHelper_C30;
                    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((min != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[55]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[145]++;
                        double minv = min.doubleValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[146]++;
int CodeCoverConditionCoverageHelper_C31;
                        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((Double.isNaN(minv)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[57]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[147]++;
int CodeCoverConditionCoverageHelper_C32;
                            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((minv < this.minimumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((Double.isNaN(
                                    this.minimumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[59]++;
                                this.minimumRangeValue = minv;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[148]++;
                                this.minimumRangeValueRow = r;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[149]++;
                                this.minimumRangeValueColumn = c;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[150]++;

                            } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[60]++;}

                        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[58]++;}

                    } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[56]++;}
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[151]++;
                    Number max = item.getMaxOutlier();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[152]++;
int CodeCoverConditionCoverageHelper_C33;
                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((max != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[61]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[153]++;
                        double maxv = max.doubleValue();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[154]++;
int CodeCoverConditionCoverageHelper_C34;
                        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((Double.isNaN(maxv)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[63]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[155]++;
int CodeCoverConditionCoverageHelper_C35;
                            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((maxv > this.maximumRangeValue) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((Double.isNaN(
                                    this.maximumRangeValue)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[65]++;
                                this.maximumRangeValue = maxv;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[156]++;
                                this.maximumRangeValueRow = r;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[157]++;
                                this.maximumRangeValueColumn = c;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[158]++;

                            } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[66]++;}

                        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[64]++;}

                    } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[62]++;}

                } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[54]++;}
            }
        }
    }
    
    /**
     * Tests this dataset for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[159]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[67]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[68]++;}
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[160]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultBoxAndWhiskerCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[69]++;
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[161]++;
            DefaultBoxAndWhiskerCategoryDataset dataset 
                    = (DefaultBoxAndWhiskerCategoryDataset) obj;
            return ObjectUtilities.equal(this.data, dataset.data);

        } else {
  CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.branches[70]++;}
        return false;
    }
    
    /**
     * Returns a clone of this dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not possible.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[162]++;
        DefaultBoxAndWhiskerCategoryDataset clone 
                = (DefaultBoxAndWhiskerCategoryDataset) super.clone();
        clone.data = (KeyedObjects2D) this.data.clone();
CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd.statements[163]++;
        return clone;
    }

}

class CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd ());
  }
    public static long[] statements = new long[164];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[38];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1};
    for (int i = 1; i <= 37; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$6mwvbx0xf2py0ie66pre1sbkhiczkt5j1e6zbqtnzltdxckrs6uedsjllc4rxd () {
    super("org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 163; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset.java");
      for (int i = 1; i <= 163; i++) {
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
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

