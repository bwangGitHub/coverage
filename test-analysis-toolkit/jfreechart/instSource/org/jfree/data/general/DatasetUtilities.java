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
 * DatasetUtilities.java
 * ---------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski (bug fix);
 *                   Jonathan Nash (bug fix);
 *                   Richard Atkinson;
 *                   Andreas Schroeder;
 *
 * Changes (from 18-Sep-2001)
 * --------------------------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 15-Nov-2001 : Moved to package com.jrefinery.data.* in the JCommon class 
 *               library (DG);
 *               Changed to handle null values from datasets (DG);
 *               Bug fix (thanks to Andrzej Porebski) - initial value now set 
 *               to positive or negative infinity when iterating (DG);
 * 22-Nov-2001 : Datasets with containing no data now return null for min and 
 *               max calculations (DG);
 * 13-Dec-2001 : Extended to handle HighLowDataset and IntervalXYDataset (DG);
 * 15-Feb-2002 : Added getMinimumStackedRangeValue() and 
 *               getMaximumStackedRangeValue() (DG);
 * 28-Feb-2002 : Renamed Datasets.java --> DatasetUtilities.java (DG);
 * 18-Mar-2002 : Fixed bug in min/max domain calculation for datasets that 
 *               implement the CategoryDataset interface AND the XYDataset 
 *               interface at the same time.  Thanks to Jonathan Nash for the 
 *               fix (DG);
 * 23-Apr-2002 : Added getDomainExtent() and getRangeExtent() methods (DG);
 * 13-Jun-2002 : Modified range measurements to handle 
 *               IntervalCategoryDataset (DG);
 * 12-Jul-2002 : Method name change in DomainInfo interface (DG);
 * 30-Jul-2002 : Added pie dataset summation method (DG);
 * 01-Oct-2002 : Added a method for constructing an XYDataset from a Function2D
 *               instance (DG);
 * 24-Oct-2002 : Amendments required following changes to the CategoryDataset 
 *               interface (DG);
 * 18-Nov-2002 : Changed CategoryDataset to TableDataset (DG);
 * 04-Mar-2003 : Added isEmpty(XYDataset) method (DG);
 * 05-Mar-2003 : Added a method for creating a CategoryDataset from a 
 *               KeyedValues instance (DG);
 * 15-May-2003 : Renamed isEmpty --> isEmptyOrNull (DG);
 * 25-Jun-2003 : Added limitPieDataset methods (RA);
 * 26-Jun-2003 : Modified getDomainExtent() method to accept null datasets (DG);
 * 27-Jul-2003 : Added getStackedRangeExtent(TableXYDataset data) (RA);
 * 18-Aug-2003 : getStackedRangeExtent(TableXYDataset data) now handles null 
 *               values (RA);
 * 02-Sep-2003 : Added method to check for null or empty PieDataset (DG);
 * 18-Sep-2003 : Fix for bug 803660 (getMaximumRangeValue for 
 *               CategoryDataset) (DG);
 * 20-Oct-2003 : Added getCumulativeRangeExtent() method (DG);
 * 09-Jan-2003 : Added argument checking code to the createCategoryDataset() 
 *               method (DG);
 * 23-Mar-2004 : Fixed bug in getMaximumStackedRangeValue() method (DG);
 * 31-Mar-2004 : Exposed the extent iteration algorithms to use one of them and 
 *               applied noninstantiation pattern (AS);
 * 11-May-2004 : Renamed getPieDatasetTotal --> calculatePieDatasetTotal (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with getYValue();
 * 24-Aug-2004 : Added argument checks to createCategoryDataset() method (DG);
 * 04-Oct-2004 : Renamed ArrayUtils --> ArrayUtilities (DG);
 * 06-Oct-2004 : Renamed findDomainExtent() --> findDomainBounds(),
 *               findRangeExtent() --> findRangeBounds() (DG);
 * 07-Jan-2005 : Renamed findStackedRangeExtent() --> findStackedRangeBounds(),
 *               findCumulativeRangeExtent() --> findCumulativeRangeBounds(),
 *               iterateXYRangeExtent() --> iterateXYRangeBounds(), 
 *               removed deprecated methods (DG);
 * 03-Feb-2005 : The findStackedRangeBounds() methods now return null for 
 *               empty datasets (DG);
 * 03-Mar-2005 : Moved createNumberArray() and createNumberArray2D() methods
 *               from DatasetUtilities --> DataUtilities (DG);
 * 22-Sep-2005 : Added new findStackedRangeBounds() method that takes base
 *               argument (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 15-Mar-2007 : Added calculateStackTotal() method (DG);
 * 
 */

package org.jfree.data.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.DomainInfo;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.KeyedValues;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.function.Function2D;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ArrayUtilities;

/**
 * A collection of useful static methods relating to datasets.
 */
public final class DatasetUtilities {
  static {
    CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.ping();
  }

    
    /**
     * Private constructor for non-instanceability.
     */
    private DatasetUtilities() {
        // now try to instantiate this ;-)
    }

    /**
     * Calculates the total of all the values in a {@link PieDataset}.  If 
     * the dataset contains negative or <code>null</code> values, they are 
     * ignored. 
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The total.
     */
    public static double calculatePieDatasetTotal(PieDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[1]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[2]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[2]++;
        List keys = dataset.getKeys();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[3]++;
        double totalValue = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[4]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[1]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[2]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[3]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[6]++;
            Comparable current = (Comparable) iterator.next();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[3]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[8]++;
                Number value = dataset.getValue(current);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[9]++;
                double v = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[5]++;
                    v = value.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[11]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[6]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((v > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[7]++;
                    totalValue = totalValue + v;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[13]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[8]++;}

            } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[4]++;}
        }
        return totalValue;
    }

    /**
     * Creates a pie dataset from a table dataset by taking all the values
     * for a single row.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param rowKey  the row key.
     *
     * @return A pie dataset.
     */
    public static PieDataset createPieDatasetForRow(CategoryDataset dataset, 
                                                    Comparable rowKey) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[14]++;
        int row = dataset.getRowIndex(rowKey);
        return createPieDatasetForRow(dataset, row);
    }

    /**
     * Creates a pie dataset from a table dataset by taking all the values
     * for a single row.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param row  the row (zero-based index).
     *
     * @return A pie dataset.
     */
    public static PieDataset createPieDatasetForRow(CategoryDataset dataset, 
                                                    int row) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[15]++;
        DefaultPieDataset result = new DefaultPieDataset();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[16]++;
        int columnCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int current = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((current < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); current++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[4]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[5]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[6]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[18]++;
            Comparable columnKey = dataset.getColumnKey(current);
            result.setValue(columnKey, dataset.getValue(row, current));
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[19]++;
        }
        return result;
    }

    /**
     * Creates a pie dataset from a table dataset by taking all the values
     * for a single column.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param columnKey  the column key.
     *
     * @return A pie dataset.
     */
    public static PieDataset createPieDatasetForColumn(CategoryDataset dataset,
                                                       Comparable columnKey) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[20]++;
        int column = dataset.getColumnIndex(columnKey);
        return createPieDatasetForColumn(dataset, column);
    }

    /**
     * Creates a pie dataset from a {@link CategoryDataset} by taking all the 
     * values for a single column.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param column  the column (zero-based index).
     *
     * @return A pie dataset.
     */
    public static PieDataset createPieDatasetForColumn(CategoryDataset dataset, 
                                                       int column) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[21]++;
        DefaultPieDataset result = new DefaultPieDataset();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[22]++;
        int rowCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[23]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[7]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[8]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[9]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[24]++;
            Comparable rowKey = dataset.getRowKey(i);
            result.setValue(rowKey, dataset.getValue(i, column));
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[25]++;
        }
        return result;
    }

    /**
     * Creates a new pie dataset based on the supplied dataset, but modified
     * by aggregating all the low value items (those whose value is lower
     * than the <code>percentThreshold</code>) into a single item with the
     * key "Other".
     *
     * @param source  the source dataset (<code>null</code> not permitted).
     * @param key  a new key for the aggregated items (<code>null</code> not
     *             permitted).
     * @param minimumPercent  the percent threshold.
     * 
     * @return The pie dataset with (possibly) aggregated items.
     */
    public static PieDataset createConsolidatedPieDataset(PieDataset source, 
                                                          Comparable key,
                                                          double minimumPercent)
    {
        return DatasetUtilities.createConsolidatedPieDataset(
            source, key, minimumPercent, 2
        );
    }

    /**
     * Creates a new pie dataset based on the supplied dataset, but modified 
     * by aggregating all the low value items (those whose value is lower 
     * than the <code>percentThreshold</code>) into a single item.  The 
     * aggregated items are assigned the specified key.  Aggregation only 
     * occurs if there are at least <code>minItems</code> items to aggregate.
     *
     * @param source  the source dataset (<code>null</code> not permitted).
     * @param key  the key to represent the aggregated items.
     * @param minimumPercent  the percent threshold (ten percent is 0.10).
     * @param minItems  only aggregate low values if there are at least this 
     *                  many.
     * 
     * @return The pie dataset with (possibly) aggregated items.
     */
    public static PieDataset createConsolidatedPieDataset(PieDataset source,
                                                          Comparable key,
                                                          double minimumPercent,
                                                          int minItems) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[26]++;
        
        DefaultPieDataset result = new DefaultPieDataset();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[27]++;
        double total = DatasetUtilities.calculatePieDatasetTotal(source);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[28]++;

        //  Iterate and find all keys below threshold percentThreshold
        List keys = source.getKeys();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[29]++;
        ArrayList otherKeys = new ArrayList();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[30]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[31]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[10]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[10]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[11]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[12]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[32]++;
            Comparable currentKey = (Comparable) iterator.next();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[33]++;
            Number dataValue = source.getValue(currentKey);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dataValue != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[9]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[35]++;
                double value = dataValue.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value / total < minimumPercent) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[11]++;
                    otherKeys.add(currentKey);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[37]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[12]++;}

            } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[10]++;}
        }

        //  Create new dataset with keys above threshold percentThreshold
        iterator = keys.iterator();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[38]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[39]++;
        double otherValue = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[40]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[13]++;


int CodeCoverConditionCoverageHelper_C11;
        while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[13]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[14]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[15]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[41]++;
            Comparable currentKey = (Comparable) iterator.next();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[42]++;
            Number dataValue = source.getValue(currentKey);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[43]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dataValue != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[13]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[44]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((otherKeys.contains(currentKey)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((otherKeys.size() >= minItems) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[15]++;
                    //  Do not add key to dataset
                    otherValue += dataValue.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[45]++;

                }
                else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[16]++;
                    //  Add key to dataset
                    result.setValue(currentKey, dataValue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[46]++;
                }

            } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[14]++;}
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[47]++;
int CodeCoverConditionCoverageHelper_C14;
        //  Add other category if applicable
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((otherKeys.size() >= minItems) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[17]++;
            result.setValue(key, otherValue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[48]++;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[18]++;}
        return result;
    }

    /**
     * Creates a {@link CategoryDataset} that contains a copy of the data in an
     * array (instances of <code>Double</code> are created to represent the 
     * data items).
     * <p>
     * Row and column keys are created by appending 0, 1, 2, ... to the 
     * supplied prefixes.
     *
     * @param rowKeyPrefix  the row key prefix.
     * @param columnKeyPrefix  the column key prefix.
     * @param data  the data.
     *
     * @return The dataset.
     */
    public static CategoryDataset createCategoryDataset(String rowKeyPrefix,
                                                        String columnKeyPrefix,
                                                        double[][] data) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[49]++;

        DefaultCategoryDataset result = new DefaultCategoryDataset();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[50]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[16]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((r < data.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[16]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[17]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[18]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[51]++;
            String rowKey = rowKeyPrefix + (r + 1);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[52]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[19]++;


int CodeCoverConditionCoverageHelper_C16;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c < data[r].length) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[19]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[20]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[21]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[53]++;
                String columnKey = columnKeyPrefix + (c + 1);
                result.addValue(new Double(data[r][c]), rowKey, columnKey);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[54]++;
            }
        }
        return result;

    }

    /**
     * Creates a {@link CategoryDataset} that contains a copy of the data in 
     * an array.
     * <p>
     * Row and column keys are created by appending 0, 1, 2, ... to the 
     * supplied prefixes.
     *
     * @param rowKeyPrefix  the row key prefix.
     * @param columnKeyPrefix  the column key prefix.
     * @param data  the data.
     *
     * @return The dataset.
     */
    public static CategoryDataset createCategoryDataset(String rowKeyPrefix,
                                                        String columnKeyPrefix,
                                                        Number[][] data) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[55]++;

        DefaultCategoryDataset result = new DefaultCategoryDataset();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[56]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[22]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((r < data.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[22]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[23]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[24]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[57]++;
            String rowKey = rowKeyPrefix + (r + 1);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[58]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[25]++;


int CodeCoverConditionCoverageHelper_C18;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c < data[r].length) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[25]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[26]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[27]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[59]++;
                String columnKey = columnKeyPrefix + (c + 1);
                result.addValue(data[r][c], rowKey, columnKey);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[60]++;
            }
        }
        return result;

    }

    /**
     * Creates a {@link CategoryDataset} that contains a copy of the data in 
     * an array (instances of <code>Double</code> are created to represent the 
     * data items).
     * <p>
     * Row and column keys are taken from the supplied arrays.
     *
     * @param rowKeys  the row keys (<code>null</code> not permitted).
     * @param columnKeys  the column keys (<code>null</code> not permitted).
     * @param data  the data.
     *
     * @return The dataset.
     */
    public static CategoryDataset createCategoryDataset(Comparable[] rowKeys,
                                                        Comparable[] columnKeys,
                                                        double[][] data) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[61]++;
int CodeCoverConditionCoverageHelper_C19;

        // check arguments...
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((rowKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[19]++;
            throw new IllegalArgumentException("Null 'rowKeys' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[20]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[62]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((columnKeys == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[21]++;
            throw new IllegalArgumentException("Null 'columnKeys' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[22]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[63]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ArrayUtilities.hasDuplicateItems(rowKeys)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[23]++;
            throw new IllegalArgumentException("Duplicate items in 'rowKeys'.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[24]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[64]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ArrayUtilities.hasDuplicateItems(columnKeys)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[25]++;
            throw new IllegalArgumentException(
                "Duplicate items in 'columnKeys'."
            );

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[26]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[65]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((rowKeys.length != data.length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[27]++;
            throw new IllegalArgumentException(
                "The number of row keys does not match the number of rows in "
                + "the data array."
            );

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[28]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[66]++;
        int columnCount = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[67]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[28]++;


int CodeCoverConditionCoverageHelper_C24;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((r < data.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[28]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[29]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[30]++;
}
            columnCount = Math.max(columnCount, data[r].length);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[68]++;
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[69]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((columnKeys.length != columnCount) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[29]++;
            throw new IllegalArgumentException(
                "The number of column keys does not match the number of "
                + "columns in the data array."
            );

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[30]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[70]++;
        
        // now do the work...
        DefaultCategoryDataset result = new DefaultCategoryDataset();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[71]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[31]++;


int CodeCoverConditionCoverageHelper_C26;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((r < data.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[31]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[32]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[33]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[72]++;
            Comparable rowKey = rowKeys[r];
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[73]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[34]++;


int CodeCoverConditionCoverageHelper_C27;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c < data[r].length) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[34]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[35]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[36]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[74]++;
                Comparable columnKey = columnKeys[c];
                result.addValue(new Double(data[r][c]), rowKey, columnKey);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[75]++;
            }
        }
        return result;

    }

    /**
     * Creates a {@link CategoryDataset} by copying the data from the supplied 
     * {@link KeyedValues} instance.
     *
     * @param rowKey  the row key (<code>null</code> not permitted).
     * @param rowData  the row data (<code>null</code> not permitted).
     *
     * @return A dataset.
     */
    public static CategoryDataset createCategoryDataset(Comparable rowKey, 
                                                        KeyedValues rowData) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[76]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((rowKey == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[31]++;
            throw new IllegalArgumentException("Null 'rowKey' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[32]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[77]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((rowData == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[33]++;
            throw new IllegalArgumentException("Null 'rowData' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[34]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[78]++;
        DefaultCategoryDataset result = new DefaultCategoryDataset();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[79]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[37]++;


int CodeCoverConditionCoverageHelper_C30;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i < rowData.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[37]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[38]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[39]++;
}
            result.addValue(rowData.getValue(i), rowKey, rowData.getKey(i));
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[80]++;
        }
        return result;

    }

    /**
     * Creates an {@link XYDataset} by sampling the specified function over a 
     * fixed range.
     *
     * @param f  the function (<code>null</code> not permitted).
     * @param start  the start value for the range.
     * @param end  the end value for the range.
     * @param samples  the number of sample points (must be > 1).
     * @param seriesKey  the key to give the resulting series 
     *                   (<code>null</code> not permitted).
     *
     * @return A dataset.
     */
    public static XYDataset sampleFunction2D(Function2D f, 
                                             double start, 
                                             double end, 
                                             int samples,
                                             Comparable seriesKey) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[81]++;
int CodeCoverConditionCoverageHelper_C31;

        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[35]++;
            throw new IllegalArgumentException("Null 'f' argument.");
   
        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[36]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[82]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((seriesKey == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[37]++;
            throw new IllegalArgumentException("Null 'seriesKey' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[38]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[83]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((start >= end) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[39]++;
            throw new IllegalArgumentException("Requires 'start' < 'end'.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[40]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[84]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((samples < 2) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[41]++;
            throw new IllegalArgumentException("Requires 'samples' > 1");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[42]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[85]++;

        XYSeries series = new XYSeries(seriesKey);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[86]++;
        double step = (end - start) / samples;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[87]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[40]++;


int CodeCoverConditionCoverageHelper_C35;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i <= samples) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[40]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[41]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[42]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[88]++;
            double x = start + (step * i);
            series.add(x, f.getValue(x));
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[89]++;
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[90]++;
        XYSeriesCollection collection = new XYSeriesCollection(series);
        return collection;

    }

    /**
     * Returns <code>true</code> if the dataset is empty (or <code>null</code>),
     * and <code>false</code> otherwise.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean isEmptyOrNull(PieDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[91]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[43]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[44]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[92]++;

        int itemCount = dataset.getItemCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[93]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((itemCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[45]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[46]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[94]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[43]++;


int CodeCoverConditionCoverageHelper_C38;

        for (int item = 0;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[43]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[44]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[45]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[95]++;
            Number y = dataset.getValue(item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[96]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((y != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[47]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[97]++;
                double yy = y.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[98]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((yy > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[49]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[50]++;}

            } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[48]++;}
        }

        return true;

    }

    /**
     * Returns <code>true</code> if the dataset is empty (or <code>null</code>),
     * and <code>false</code> otherwise.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean isEmptyOrNull(CategoryDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[99]++;
int CodeCoverConditionCoverageHelper_C41;

        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[51]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[52]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[100]++;

        int rowCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[101]++;
        int columnCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[102]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((rowCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((columnCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[53]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[54]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[103]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[46]++;


int CodeCoverConditionCoverageHelper_C43;

        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((r < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[46]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[47]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[48]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[104]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[49]++;


int CodeCoverConditionCoverageHelper_C44;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((c < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[49]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[50]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[51]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[105]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((dataset.getValue(r, c) != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[55]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[56]++;}

            }
        }

        return true;

    }

    /**
     * Returns <code>true</code> if the dataset is empty (or <code>null</code>),
     * and <code>false</code> otherwise.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public static boolean isEmptyOrNull(XYDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[106]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[57]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[107]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[52]++;


int CodeCoverConditionCoverageHelper_C47;
            for (int s = 0;(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((s < dataset.getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[52]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[53]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[54]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[108]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((dataset.getItemCount(s) > 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[59]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[60]++;}
            }

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[58]++;}
        return true;
    }

    /**
     * Returns the range of values in the domain (x-values) of a dataset.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The range of values (possibly <code>null</code>).
     */
    public static Range findDomainBounds(XYDataset dataset) {
        return findDomainBounds(dataset, true);
    }

    /**
     * Returns the range of values in the domain (x-values) of a dataset.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param includeInterval  determines whether or not the x-interval is taken
     *                         into account (only applies if the dataset is an
     *                         {@link IntervalXYDataset}).
     *
     * @return The range of values (possibly <code>null</code>).
     */
    public static Range findDomainBounds(XYDataset dataset, 
                                         boolean includeInterval) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[109]++;
int CodeCoverConditionCoverageHelper_C49;

        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[61]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[62]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[110]++;

        Range result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[111]++;
int CodeCoverConditionCoverageHelper_C50;
        // if the dataset implements DomainInfo, life is easier
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((dataset instanceof DomainInfo) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[63]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[112]++;
            DomainInfo info = (DomainInfo) dataset;
            result = info.getDomainBounds(includeInterval);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[113]++;

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[64]++;
            result = iterateDomainBounds(dataset, includeInterval);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[114]++;
        }
        return result;
        
    }

    /**
     * Iterates over the items in an {@link XYDataset} to find
     * the range of x-values. 
     *  
     * @param dataset  the dataset (<code>null</code> not permitted).
     * 
     * @return The range (possibly <code>null</code>).
     */
    public static Range iterateDomainBounds(XYDataset dataset) {
        return iterateDomainBounds(dataset, true);
    }

    /**
     * Iterates over the items in an {@link XYDataset} to find
     * the range of x-values. 
     *  
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param includeInterval  a flag that determines, for an IntervalXYDataset,
     *                         whether the x-interval or just the x-value is 
     *                         used to determine the overall range.
     *   
     * @return The range (possibly <code>null</code>).
     */
    public static Range iterateDomainBounds(XYDataset dataset, 
                                            boolean includeInterval) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[115]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[65]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");
   
        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[66]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[116]++;
        double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[117]++;
        double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[118]++;
        int seriesCount = dataset.getSeriesCount();
        double lvalue;
        double uvalue;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[119]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((includeInterval) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[67]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[120]++;
            IntervalXYDataset intervalXYData = (IntervalXYDataset) dataset;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[121]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[55]++;


int CodeCoverConditionCoverageHelper_C53;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[55]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[56]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[57]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[122]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[123]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[58]++;


int CodeCoverConditionCoverageHelper_C54;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[58]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[59]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[60]++;
}
                    lvalue = intervalXYData.getStartXValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[124]++;
                    uvalue = intervalXYData.getEndXValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[125]++;
                    minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[126]++;
                    maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[127]++;
                }
            }

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[68]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[128]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[61]++;


int CodeCoverConditionCoverageHelper_C55;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[61]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[62]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[63]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[129]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[130]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[64]++;


int CodeCoverConditionCoverageHelper_C56;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[64]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[65]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[66]++;
}
                    lvalue = dataset.getXValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[131]++;
                    uvalue = lvalue;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[132]++;
                    minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[133]++;
                    maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[134]++;
                }
            }
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[135]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((minimum > maximum) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[69]++;
            return null;

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[70]++;
            return new Range(minimum, maximum);
        }
    }
    
    /**
     * Returns the range of values in the range for the dataset.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The range (possibly <code>null</code>).
     */
    public static Range findRangeBounds(CategoryDataset dataset) {
        return findRangeBounds(dataset, true);
    }
    
    /**
     * Returns the range of values in the range for the dataset.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The range (possibly <code>null</code>).
     */
    public static Range findRangeBounds(CategoryDataset dataset, 
                                        boolean includeInterval) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[136]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[71]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[72]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[137]++;
        Range result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[138]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((dataset instanceof RangeInfo) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[73]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[139]++;
            RangeInfo info = (RangeInfo) dataset;
            result = info.getRangeBounds(includeInterval);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[140]++;

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[74]++;
            result = iterateCategoryRangeBounds(dataset, includeInterval);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[141]++;
        }
        return result;
    }
    
    /**
     * Returns the range of values in the range for the dataset.  This method
     * is the partner for the {@link #findDomainBounds(XYDataset)} method.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The range (possibly <code>null</code>).
     */
    public static Range findRangeBounds(XYDataset dataset) {
        return findRangeBounds(dataset, true);
    }
    
    /**
     * Returns the range of values in the range for the dataset.  This method
     * is the partner for the {@link #findDomainBounds(XYDataset)} method.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     *
     * @return The range (possibly <code>null</code>).
     */
    public static Range findRangeBounds(XYDataset dataset, 
                                        boolean includeInterval) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[142]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[75]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[76]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[143]++;
        Range result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[144]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((dataset instanceof RangeInfo) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[77]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[145]++;
            RangeInfo info = (RangeInfo) dataset;
            result = info.getRangeBounds(includeInterval);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[146]++;

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[78]++;
            result = iterateXYRangeBounds(dataset);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[147]++;
        }
        return result;
    }
    
    /**
     * Iterates over the data item of the category dataset to find
     * the range bounds.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     * 
     * @return The range (possibly <code>null</code>).
     */
    public static Range iterateCategoryRangeBounds(CategoryDataset dataset, 
            boolean includeInterval) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[148]++;
        double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[149]++;
        double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[150]++;
        boolean interval = includeInterval 
                           && dataset instanceof IntervalCategoryDataset;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[151]++;
        int rowCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[152]++;
        int columnCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[153]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[67]++;


int CodeCoverConditionCoverageHelper_C62;
        for (int row = 0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((row < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); row++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[67]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[68]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[69]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[154]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[70]++;


int CodeCoverConditionCoverageHelper_C63;
            for (int column = 0;(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((column < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[70]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[71]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[72]++;
}
                Number lvalue;
                Number uvalue;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[155]++;
int CodeCoverConditionCoverageHelper_C64;
                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((interval) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[79]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[156]++;
                    IntervalCategoryDataset icd 
                        = (IntervalCategoryDataset) dataset;
                    lvalue = icd.getStartValue(row, column);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[157]++;
                    uvalue = icd.getEndValue(row, column);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[158]++;

                }
                else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[80]++;
                    lvalue = dataset.getValue(row, column);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[159]++;
                    uvalue = lvalue;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[160]++;
                }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[161]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((lvalue != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[81]++;
                    minimum = Math.min(minimum, lvalue.doubleValue());
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[162]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[82]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[163]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((uvalue != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[83]++;
                    maximum = Math.max(maximum, uvalue.doubleValue());
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[164]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[84]++;}
            }
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[165]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((minimum == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[85]++;
            return null;

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[86]++;
            return new Range(minimum, maximum);
        }
    }
    
    /**
     * Iterates over the data item of the xy dataset to find
     * the range bounds.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * 
     * @return The range (possibly <code>null</code>).
     */
    public static Range iterateXYRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[166]++;
        double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[167]++;
        double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[168]++;
        int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[169]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[73]++;


int CodeCoverConditionCoverageHelper_C68;
        for (int series = 0;(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[73]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[74]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[75]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[170]++;
            int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[171]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[76]++;


int CodeCoverConditionCoverageHelper_C69;
            for (int item = 0;(((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[76]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[77]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[78]++;
}
                double lvalue;
                double uvalue;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[172]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[87]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[173]++;
                    IntervalXYDataset intervalXYData 
                        = (IntervalXYDataset) dataset;
                    lvalue = intervalXYData.getStartYValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[174]++;
                    uvalue = intervalXYData.getEndYValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[175]++;

                }
                else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[88]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[176]++;
int CodeCoverConditionCoverageHelper_C71; if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((dataset instanceof OHLCDataset) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[89]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[177]++;
                    OHLCDataset highLowData = (OHLCDataset) dataset;
                    lvalue = highLowData.getLowValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[178]++;
                    uvalue = highLowData.getHighValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[179]++;

                }
                else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[90]++;
                    lvalue = dataset.getYValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[180]++;
                    uvalue = lvalue;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[181]++;
                }
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[182]++;
int CodeCoverConditionCoverageHelper_C72;
                if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((Double.isNaN(lvalue)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[91]++;
                    minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[183]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[92]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[184]++;
int CodeCoverConditionCoverageHelper_C73;
                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((Double.isNaN(uvalue)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[93]++;     
                    maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[185]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[94]++;}
            }
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[186]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((minimum == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[95]++;
            return null;

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[96]++;
            return new Range(minimum, maximum);
        }
    }

    /**
     * Finds the minimum domain (or X) value for the specified dataset.  This 
     * is easy if the dataset implements the {@link DomainInfo} interface (a 
     * good idea if there is an efficient way to determine the minimum value).
     * Otherwise, it involves iterating over the entire data-set.
     * <p>
     * Returns <code>null</code> if all the data values in the dataset are 
     * <code>null</code>.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The minimum value (possibly <code>null</code>).
     */
    public static Number findMinimumDomainValue(XYDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[187]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[97]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[98]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[188]++;
        Number result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[189]++;
int CodeCoverConditionCoverageHelper_C76;
        // if the dataset implements DomainInfo, life is easy
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((dataset instanceof DomainInfo) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[99]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[190]++;
            DomainInfo info = (DomainInfo) dataset;
            return new Double(info.getDomainLowerBound(true));

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[100]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[191]++;
            double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[192]++;
            int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[193]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[79]++;


int CodeCoverConditionCoverageHelper_C77;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[79]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[80]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[81]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[194]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[195]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[82]++;


int CodeCoverConditionCoverageHelper_C78;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[82]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[83]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[84]++;
}

                    double value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[196]++;
int CodeCoverConditionCoverageHelper_C79;
                    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[101]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[197]++;
                        IntervalXYDataset intervalXYData 
                            = (IntervalXYDataset) dataset;
                        value = intervalXYData.getStartXValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[198]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[102]++;
                        value = dataset.getXValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[199]++;
                    }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[200]++;
int CodeCoverConditionCoverageHelper_C80;
                    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[103]++;
                        minimum = Math.min(minimum, value);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[201]++;

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[104]++;}

                }
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[202]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((minimum == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[105]++;
                result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[203]++;

            }
            else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[106]++;
                result = new Double(minimum);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[204]++;
            }
        }

        return result;
    }
    
    /**
     * Returns the maximum domain value for the specified dataset.  This is 
     * easy if the dataset implements the {@link DomainInfo} interface (a good 
     * idea if there is an efficient way to determine the maximum value).  
     * Otherwise, it involves iterating over the entire data-set.  Returns 
     * <code>null</code> if all the data values in the dataset are 
     * <code>null</code>.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The maximum value (possibly <code>null</code>).
     */
    public static Number findMaximumDomainValue(XYDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[205]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[107]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[108]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[206]++;
        Number result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[207]++;
int CodeCoverConditionCoverageHelper_C83;
        // if the dataset implements DomainInfo, life is easy
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((dataset instanceof DomainInfo) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[109]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[208]++;
            DomainInfo info = (DomainInfo) dataset;
            return new Double(info.getDomainUpperBound(true));

        }

        // hasn't implemented DomainInfo, so iterate...
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[110]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[209]++;
            double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[210]++;
            int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[211]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[85]++;


int CodeCoverConditionCoverageHelper_C84;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[85]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[86]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[87]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[212]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[213]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[88]++;


int CodeCoverConditionCoverageHelper_C85;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[88]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[89]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[90]++;
}

                    double value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[214]++;
int CodeCoverConditionCoverageHelper_C86;
                    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[111]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[215]++;
                        IntervalXYDataset intervalXYData 
                            = (IntervalXYDataset) dataset;
                        value = intervalXYData.getEndXValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[216]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[112]++;
                        value = dataset.getXValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[217]++;
                    }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[218]++;
int CodeCoverConditionCoverageHelper_C87;
                    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[113]++;
                        maximum = Math.max(maximum, value);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[219]++;

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[114]++;}
                }
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[220]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((maximum == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[115]++;
                result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[221]++;

            }
            else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[116]++;
                result = new Double(maximum);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[222]++;
            }

        }
        
        return result;
    }

    /**
     * Returns the minimum range value for the specified dataset.  This is 
     * easy if the dataset implements the {@link RangeInfo} interface (a good
     * idea if there is an efficient way to determine the minimum value).  
     * Otherwise, it involves iterating over the entire data-set.  Returns 
     * <code>null</code> if all the data values in the dataset are 
     * <code>null</code>.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The minimum value (possibly <code>null</code>).
     */
    public static Number findMinimumRangeValue(CategoryDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[223]++;
int CodeCoverConditionCoverageHelper_C89;

        // check parameters...
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[117]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[118]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[224]++;
int CodeCoverConditionCoverageHelper_C90;

        // work out the minimum value...
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((dataset instanceof RangeInfo) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[119]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[225]++;
            RangeInfo info = (RangeInfo) dataset;
            return new Double(info.getRangeLowerBound(true));

        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[120]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[226]++;
            double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[227]++;
            int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[228]++;
            int itemCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[229]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[91]++;


int CodeCoverConditionCoverageHelper_C91;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[91]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[92]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[93]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[230]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[94]++;


int CodeCoverConditionCoverageHelper_C92;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[94]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[95]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[96]++;
}
                    Number value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[231]++;
int CodeCoverConditionCoverageHelper_C93;
                    if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[121]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[232]++;
                        IntervalCategoryDataset icd 
                            = (IntervalCategoryDataset) dataset;
                        value = icd.getStartValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[233]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[122]++;
                        value = dataset.getValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[234]++;
                    }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[235]++;
int CodeCoverConditionCoverageHelper_C94;
                    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[123]++;
                        minimum = Math.min(minimum, value.doubleValue());
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[236]++;

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[124]++;}
                }
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[237]++;
int CodeCoverConditionCoverageHelper_C95;
            if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((minimum == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[125]++;
                return null;

            }
            else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[126]++;
                return new Double(minimum);
            }

        }

    }

    /**
     * Returns the minimum range value for the specified dataset.  This is 
     * easy if the dataset implements the {@link RangeInfo} interface (a good
     * idea if there is an efficient way to determine the minimum value).  
     * Otherwise, it involves iterating over the entire data-set.  Returns 
     * <code>null</code> if all the data values in the dataset are 
     * <code>null</code>.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The minimum value (possibly <code>null</code>).
     */
    public static Number findMinimumRangeValue(XYDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[238]++;
int CodeCoverConditionCoverageHelper_C96;

        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[127]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[128]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[239]++;
int CodeCoverConditionCoverageHelper_C97;

        // work out the minimum value...
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((dataset instanceof RangeInfo) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[129]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[240]++;
            RangeInfo info = (RangeInfo) dataset;
            return new Double(info.getRangeLowerBound(true));

        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[130]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[241]++;
            double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[242]++;
            int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[243]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[97]++;


int CodeCoverConditionCoverageHelper_C98;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[97]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[98]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[99]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[244]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[245]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[100]++;


int CodeCoverConditionCoverageHelper_C99;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[100]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[101]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[102]++;
}

                    double value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[246]++;
int CodeCoverConditionCoverageHelper_C100;
                    if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[131]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[247]++;
                        IntervalXYDataset intervalXYData 
                            = (IntervalXYDataset) dataset;
                        value = intervalXYData.getStartYValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[248]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[132]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[249]++;
int CodeCoverConditionCoverageHelper_C101; if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((dataset instanceof OHLCDataset) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[133]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[250]++;
                        OHLCDataset highLowData = (OHLCDataset) dataset;
                        value = highLowData.getLowValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[251]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[134]++;
                        value = dataset.getYValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[252]++;
                    }
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[253]++;
int CodeCoverConditionCoverageHelper_C102;
                    if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[135]++;
                        minimum = Math.min(minimum, value);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[254]++;

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[136]++;}

                }
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[255]++;
int CodeCoverConditionCoverageHelper_C103;
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((minimum == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[137]++;
                return null;

            }
            else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[138]++;
                return new Double(minimum);
            }

        }

    }

    /**
     * Returns the maximum range value for the specified dataset.  This is easy
     * if the dataset implements the {@link RangeInfo} interface (a good idea 
     * if there is an efficient way to determine the maximum value).  
     * Otherwise, it involves iterating over the entire data-set.  Returns 
     * <code>null</code> if all the data values are <code>null</code>.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The maximum value (possibly <code>null</code>).
     */
    public static Number findMaximumRangeValue(CategoryDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[256]++;
int CodeCoverConditionCoverageHelper_C104;

        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[139]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[140]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[257]++;
int CodeCoverConditionCoverageHelper_C105;

        // work out the minimum value...
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((dataset instanceof RangeInfo) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[141]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[258]++;
            RangeInfo info = (RangeInfo) dataset;
            return new Double(info.getRangeUpperBound(true));

        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[142]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[259]++;

            double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[260]++;
            int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[261]++;
            int itemCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[262]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[103]++;


int CodeCoverConditionCoverageHelper_C106;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[103]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[104]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[105]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[263]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[106]++;


int CodeCoverConditionCoverageHelper_C107;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[106]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[107]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[108]++;
}
                    Number value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[264]++;
int CodeCoverConditionCoverageHelper_C108;
                    if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[143]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[265]++;
                        IntervalCategoryDataset icd 
                            = (IntervalCategoryDataset) dataset;
                        value = icd.getEndValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[266]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[144]++;
                        value = dataset.getValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[267]++;
                    }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[268]++;
int CodeCoverConditionCoverageHelper_C109;
                    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[145]++;
                        maximum = Math.max(maximum, value.doubleValue());
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[269]++;

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[146]++;}
                }
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[270]++;
int CodeCoverConditionCoverageHelper_C110;
            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((maximum == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[147]++;
                return null;

            }
            else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[148]++;
                return new Double(maximum);
            }

        }

    }

    /**
     * Returns the maximum range value for the specified dataset.  This is 
     * easy if the dataset implements the {@link RangeInfo} interface (a good 
     * idea if there is an efficient way to determine the maximum value).  
     * Otherwise, it involves iterating over the entire data-set.  Returns 
     * <code>null</code> if all the data values are <code>null</code>.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The maximum value (possibly <code>null</code>).
     */
    public static Number findMaximumRangeValue(XYDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[271]++;
int CodeCoverConditionCoverageHelper_C111;

        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[149]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[150]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[272]++;
int CodeCoverConditionCoverageHelper_C112;

        // work out the minimum value...
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((dataset instanceof RangeInfo) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[151]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[273]++;
            RangeInfo info = (RangeInfo) dataset;
            return new Double(info.getRangeUpperBound(true));

        }

        // hasn't implemented RangeInfo, so we'll have to iterate...
        else  {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[152]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[274]++;

            double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[275]++;
            int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[276]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[109]++;


int CodeCoverConditionCoverageHelper_C113;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[109]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[110]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[111]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[277]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[278]++;
byte CodeCoverTryBranchHelper_L38 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[112]++;


int CodeCoverConditionCoverageHelper_C114;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L38 == 0) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[112]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[113]++;
} else if (CodeCoverTryBranchHelper_L38 == 1) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[113]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[114]++;
}
                    double value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[279]++;
int CodeCoverConditionCoverageHelper_C115;
                    if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[153]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[280]++;
                        IntervalXYDataset intervalXYData 
                            = (IntervalXYDataset) dataset;
                        value = intervalXYData.getEndYValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[281]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[154]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[282]++;
int CodeCoverConditionCoverageHelper_C116; if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((dataset instanceof OHLCDataset) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[155]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[283]++;
                        OHLCDataset highLowData = (OHLCDataset) dataset;
                        value = highLowData.getHighValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[284]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[156]++;
                        value = dataset.getYValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[285]++;
                    }
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[286]++;
int CodeCoverConditionCoverageHelper_C117;
                    if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[157]++;
                        maximum = Math.max(maximum, value);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[287]++;

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[158]++;}
                }
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[288]++;
int CodeCoverConditionCoverageHelper_C118;
            if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((maximum == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[159]++;
                return null;

            }
            else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[160]++;
                return new Double(maximum);
            }

        }

    }

    /**
     * Returns the minimum and maximum values for the dataset's range 
     * (y-values), assuming that the series in one category are stacked.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The range (<code>null</code> if the dataset contains no values).
     */
    public static Range findStackedRangeBounds(CategoryDataset dataset) {
        return findStackedRangeBounds(dataset, 0.0);
    }

    /**
     * Returns the minimum and maximum values for the dataset's range 
     * (y-values), assuming that the series in one category are stacked.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param base  the base value for the bars.
     *
     * @return The range (<code>null</code> if the dataset contains no values).
     */
    public static Range findStackedRangeBounds(CategoryDataset dataset, 
            double base) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[289]++;
int CodeCoverConditionCoverageHelper_C119;
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[161]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[162]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[290]++;
        Range result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[291]++;
        double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[292]++;
        double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[293]++;
        int categoryCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[294]++;
byte CodeCoverTryBranchHelper_L39 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[115]++;


int CodeCoverConditionCoverageHelper_C120;
        for (int item = 0;(((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((item < categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L39 == 0) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[115]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[116]++;
} else if (CodeCoverTryBranchHelper_L39 == 1) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[116]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[117]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[295]++;
            double positive = base;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[296]++;
            double negative = base;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[297]++;
            int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[298]++;
byte CodeCoverTryBranchHelper_L40 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[118]++;


int CodeCoverConditionCoverageHelper_C121;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L40 == 0) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[118]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[119]++;
} else if (CodeCoverTryBranchHelper_L40 == 1) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[119]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[120]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[299]++;
                Number number = dataset.getValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[300]++;
int CodeCoverConditionCoverageHelper_C122;
                if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((number != null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[163]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[301]++;
                    double value = number.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[302]++;
int CodeCoverConditionCoverageHelper_C123;
                    if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((value > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[165]++;
                        positive = positive + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[303]++;

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[166]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[304]++;
int CodeCoverConditionCoverageHelper_C124;
                    if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((value < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[167]++;
                        negative = negative + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[305]++;
  
                        // '+', remember value is negative
                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[168]++;}

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[164]++;}
            }
            minimum = Math.min(minimum, negative);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[306]++;
            maximum = Math.max(maximum, positive);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[307]++;
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[308]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((minimum <= maximum) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[169]++;
            result = new Range(minimum, maximum);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[309]++;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[170]++;}
        return result;

    }

    /**
     * Returns the minimum and maximum values for the dataset's range 
     * (y-values), assuming that the series in one category are stacked.
     *
     * @param dataset  the dataset.
     * @param map  a structure that maps series to groups.
     *
     * @return The value range (<code>null</code> if the dataset contains no 
     *         values).
     */
    public static Range findStackedRangeBounds(CategoryDataset dataset,
                                               KeyToGroupMap map) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[310]++;
    
        Range result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[311]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[171]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[312]++;
            
            // create an array holding the group indices...
            int[] groupIndex = new int[dataset.getRowCount()];
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[313]++;
byte CodeCoverTryBranchHelper_L41 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[121]++;


int CodeCoverConditionCoverageHelper_C127;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((i < dataset.getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L41 == 0) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[121]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[122]++;
} else if (CodeCoverTryBranchHelper_L41 == 1) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[122]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[123]++;
}
                groupIndex[i] = map.getGroupIndex(
                    map.getGroup(dataset.getRowKey(i))
                );
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[314]++;   
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[315]++;
            
            // minimum and maximum for each group...
            int groupCount = map.getGroupCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[316]++;
            double[] minimum = new double[groupCount];
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[317]++;
            double[] maximum = new double[groupCount];
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[318]++;
            
            int categoryCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[319]++;
byte CodeCoverTryBranchHelper_L42 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[124]++;


int CodeCoverConditionCoverageHelper_C128;
            for (int item = 0;(((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((item < categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L42 == 0) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[124]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[125]++;
} else if (CodeCoverTryBranchHelper_L42 == 1) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[125]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[126]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[320]++;
                double[] positive = new double[groupCount];
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[321]++;
                double[] negative = new double[groupCount];
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[322]++;
                int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[323]++;
byte CodeCoverTryBranchHelper_L43 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[127]++;


int CodeCoverConditionCoverageHelper_C129;
                for (int series = 0;(((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L43 == 0) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[127]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[128]++;
} else if (CodeCoverTryBranchHelper_L43 == 1) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[128]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[129]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[324]++;
                    Number number = dataset.getValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[325]++;
int CodeCoverConditionCoverageHelper_C130;
                    if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((number != null) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[173]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[326]++;
                        double value = number.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[327]++;
int CodeCoverConditionCoverageHelper_C131;
                        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((value > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[175]++;
                            positive[groupIndex[series]] 
                                 = positive[groupIndex[series]] + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[328]++;

                        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[176]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[329]++;
int CodeCoverConditionCoverageHelper_C132;
                        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((value < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[177]++;
                            negative[groupIndex[series]] 
                                 = negative[groupIndex[series]] + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[330]++;

                                 // '+', remember value is negative
                        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[178]++;}

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[174]++;}
                }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[331]++;
byte CodeCoverTryBranchHelper_L44 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[130]++;


int CodeCoverConditionCoverageHelper_C133;
                for (int g = 0;(((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((g < groupCount) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false); g++) {
if (CodeCoverTryBranchHelper_L44 == 0) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[130]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[131]++;
} else if (CodeCoverTryBranchHelper_L44 == 1) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[131]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[132]++;
}
                    minimum[g] = Math.min(minimum[g], negative[g]);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[332]++;
                    maximum[g] = Math.max(maximum[g], positive[g]);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[333]++;
                }
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[334]++;
byte CodeCoverTryBranchHelper_L45 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[133]++;


int CodeCoverConditionCoverageHelper_C134;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((j < groupCount) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L45 == 0) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[133]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[134]++;
} else if (CodeCoverTryBranchHelper_L45 == 1) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[134]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[135]++;
}
                result = Range.combine(
                    result, new Range(minimum[j], maximum[j])
                );
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[335]++;
            }

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[172]++;}
        return result;

    }

    /**
     * Returns the minimum value in the dataset range, assuming that values in
     * each category are "stacked".
     *
     * @param dataset  the dataset.
     *
     * @return The minimum value.
     */
    public static Number findMinimumStackedRangeValue(CategoryDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[336]++;

        Number result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[337]++;
int CodeCoverConditionCoverageHelper_C135;
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[179]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[338]++;
            double minimum = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[339]++;
            int categoryCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[340]++;
byte CodeCoverTryBranchHelper_L46 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[136]++;


int CodeCoverConditionCoverageHelper_C136;
            for (int item = 0;(((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((item < categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L46 == 0) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[136]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[137]++;
} else if (CodeCoverTryBranchHelper_L46 == 1) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[137]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[138]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[341]++;
                double total = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[342]++;

                int seriesCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[343]++;
byte CodeCoverTryBranchHelper_L47 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[139]++;


int CodeCoverConditionCoverageHelper_C137;
                for (int series = 0;(((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L47 == 0) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[139]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[140]++;
} else if (CodeCoverTryBranchHelper_L47 == 1) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[140]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[141]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[344]++;
                    Number number = dataset.getValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[345]++;
int CodeCoverConditionCoverageHelper_C138;
                    if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((number != null) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[181]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[346]++;
                        double value = number.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[347]++;
int CodeCoverConditionCoverageHelper_C139;
                        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((value < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[183]++;
                            total = total + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[348]++;
  
                            // '+', remember value is negative
                        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[184]++;}

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[182]++;}
                }
                minimum = Math.min(minimum, total);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[349]++;

            }
            result = new Double(minimum);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[350]++;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[180]++;}
        return result;

    }

    /**
     * Returns the maximum value in the dataset range, assuming that values in
     * each category are "stacked".
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return The maximum value (possibly <code>null</code>).
     */
    public static Number findMaximumStackedRangeValue(CategoryDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[351]++;

        Number result = null;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[352]++;
int CodeCoverConditionCoverageHelper_C140;

        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[185]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[353]++;
            double maximum = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[354]++;
            int categoryCount = dataset.getColumnCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[355]++;
byte CodeCoverTryBranchHelper_L48 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[142]++;


int CodeCoverConditionCoverageHelper_C141;
            for (int item = 0;(((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((item < categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L48 == 0) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[142]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[143]++;
} else if (CodeCoverTryBranchHelper_L48 == 1) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[143]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[144]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[356]++;
                double total = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[357]++;
                int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[358]++;
byte CodeCoverTryBranchHelper_L49 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[145]++;


int CodeCoverConditionCoverageHelper_C142;
                for (int series = 0;(((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L49 == 0) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[145]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[146]++;
} else if (CodeCoverTryBranchHelper_L49 == 1) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[146]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[147]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[359]++;
                    Number number = dataset.getValue(series, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[360]++;
int CodeCoverConditionCoverageHelper_C143;
                    if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((number != null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[187]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[361]++;
                        double value = number.doubleValue();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[362]++;
int CodeCoverConditionCoverageHelper_C144;
                        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((value > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[189]++;
                            total = total + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[363]++;

                        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[190]++;}

                    } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[188]++;}
                }
                maximum = Math.max(maximum, total);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[364]++;
            }
            result = new Double(maximum);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[365]++;

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[186]++;}

        return result;

    }

    /**
     * Returns the minimum and maximum values for the dataset's range,
     * assuming that the series are stacked.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * 
     * @return The range ([0.0, 0.0] if the dataset contains no values).
     */
    public static Range findStackedRangeBounds(TableXYDataset dataset) {
        return findStackedRangeBounds(dataset, 0.0);
    }
    
    /**
     * Returns the minimum and maximum values for the dataset's range,
     * assuming that the series are stacked, using the specified base value.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param base  the base value.
     * 
     * @return The range (<code>null</code> if the dataset contains no values).
     */
    public static Range findStackedRangeBounds(TableXYDataset dataset, 
                                               double base) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[366]++;
int CodeCoverConditionCoverageHelper_C145;
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[191]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[192]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[367]++;
        double minimum = base;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[368]++;
        double maximum = base;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[369]++;
byte CodeCoverTryBranchHelper_L50 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[148]++;


int CodeCoverConditionCoverageHelper_C146;
        for (int itemNo = 0;(((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((itemNo < dataset.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false); itemNo++) {
if (CodeCoverTryBranchHelper_L50 == 0) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[148]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[149]++;
} else if (CodeCoverTryBranchHelper_L50 == 1) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[149]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[150]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[370]++;
            double positive = base;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[371]++;
            double negative = base;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[372]++;
            int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[373]++;
byte CodeCoverTryBranchHelper_L51 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[151]++;


int CodeCoverConditionCoverageHelper_C147;
            for (int seriesNo = 0;(((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((seriesNo < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false); seriesNo++) {
if (CodeCoverTryBranchHelper_L51 == 0) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[151]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[152]++;
} else if (CodeCoverTryBranchHelper_L51 == 1) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[152]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[153]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[374]++;
                double y = dataset.getYValue(seriesNo, itemNo);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[375]++;
int CodeCoverConditionCoverageHelper_C148;
                if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((Double.isNaN(y)) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[193]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[376]++;
int CodeCoverConditionCoverageHelper_C149;
                    if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((y > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[195]++;
                        positive += y;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[377]++;

                    }
                    else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[196]++;
                        negative += y;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[378]++;
                    }

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[194]++;}
            }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[379]++;
int CodeCoverConditionCoverageHelper_C150;
            if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((positive > maximum) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[197]++;
                maximum = positive;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[380]++;

            } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[198]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[381]++;
int CodeCoverConditionCoverageHelper_C151; 
            if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((negative < minimum) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[199]++;
                minimum = negative;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[382]++;

            } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[200]++;} 
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[383]++;
int CodeCoverConditionCoverageHelper_C152;
        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((minimum <= maximum) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[201]++;
            return new Range(minimum, maximum);

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[202]++;
            return null;   
        }
    }
    
    /**
     * Calculates the total for the y-values in all series for a given item
     * index.
     * 
     * @param dataset  the dataset.
     * @param item  the item index.
     * 
     * @return The total.
     * 
     * @since 1.0.5
     */
    public static double calculateStackTotal(TableXYDataset dataset, int item) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[384]++;
        double total = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[385]++;
        int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[386]++;
byte CodeCoverTryBranchHelper_L52 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[154]++;


int CodeCoverConditionCoverageHelper_C153;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((s < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L52 == 0) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[154]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[155]++;
} else if (CodeCoverTryBranchHelper_L52 == 1) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[155]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[156]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[387]++;
            double value = dataset.getYValue(s, item);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[388]++;
int CodeCoverConditionCoverageHelper_C154;
            if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[203]++;
                total = total + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[389]++;

            } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[204]++;}
        }
        return total;
    }

    /**
     * Calculates the range of values for a dataset where each item is the 
     * running total of the items for the current series.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * 
     * @return The range.
     * 
     * @see #findRangeBounds(CategoryDataset)
     */
    public static Range findCumulativeRangeBounds(CategoryDataset dataset) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[390]++;
int CodeCoverConditionCoverageHelper_C155;
        
        if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[205]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");

        } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[206]++;}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[391]++;
        
        boolean allItemsNull = true;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[392]++; // we'll set this to false if there is at 
                                     // least one non-null data item... 
        double minimum = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[393]++;
        double maximum = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[394]++;
byte CodeCoverTryBranchHelper_L53 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[157]++;


int CodeCoverConditionCoverageHelper_C156;
        for (int row = 0;(((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((row < dataset.getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false); row++) {
if (CodeCoverTryBranchHelper_L53 == 0) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[157]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[158]++;
} else if (CodeCoverTryBranchHelper_L53 == 1) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[158]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[159]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[395]++;
            double runningTotal = 0.0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[396]++;
byte CodeCoverTryBranchHelper_L54 = 0;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[160]++;


int CodeCoverConditionCoverageHelper_C157;
            for (int column = 0;(((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((column < dataset.getColumnCount() - 1) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false); 
                 column++) {
if (CodeCoverTryBranchHelper_L54 == 0) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[160]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[161]++;
} else if (CodeCoverTryBranchHelper_L54 == 1) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[161]--;
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.loops[162]++;
}
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[397]++;
                Number n = dataset.getValue(row, column);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[398]++;
int CodeCoverConditionCoverageHelper_C158;
                if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[207]++;
                    allItemsNull = false;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[399]++;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[400]++;
                    double value = n.doubleValue();
                    runningTotal = runningTotal + value;
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[401]++;
                    minimum = Math.min(minimum, runningTotal);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[402]++;
                    maximum = Math.max(maximum, runningTotal);
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[403]++;

                } else {
  CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[208]++;}
            }    
        }
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.statements[404]++;
int CodeCoverConditionCoverageHelper_C159;
        if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((allItemsNull) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[209]++;
            return new Range(minimum, maximum);

        }
        else {
CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl.branches[210]++;
            return null;
        }
        
    }

}

class CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl ());
  }
    public static long[] statements = new long[405];
    public static long[] branches = new long[211];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[160];
  static {
    final String SECTION_NAME = "org.jfree.data.general.DatasetUtilities.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 159; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[163];

  public CodeCoverCoverageCounter$1kstyr3g4cxq43mzze60h773yub1wrejl () {
    super("org.jfree.data.general.DatasetUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 404; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 210; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 159; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 162; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.general.DatasetUtilities.java");
      for (int i = 1; i <= 404; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 210; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 159; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 54; i++) {
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

