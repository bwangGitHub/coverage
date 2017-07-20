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
 * ------------------
 * DataUtilities.java
 * ------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Mar-2003 : Version 1 (DG);
 * 03-Mar-2005 : Moved createNumberArray() and createNumberArray2D() methods
 *               from the DatasetUtilities class (DG);
 * 17-May-2005 : Added calculateColumnTotal() and calculateRowTotal() 
 *               methods (DG);
 * 
 */

package org.jfree.data;

import org.jfree.data.general.DatasetUtilities;

/**
 * Utility methods for use with some of the data classes (but not the datasets, 
 * see {@link DatasetUtilities}).
 */
public abstract class DataUtilities {
  static {
    CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.ping();
  }


    /**
     * Returns the total of the values in one column of the supplied data
     * table.
     * 
     * @param data  the table of values (<code>null</code> not permitted).
     * @param column  the column index (zero-based).
     * 
     * @return The total of the values in the specified column.
     */
    public static double calculateColumnTotal(Values2D data, int column) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[1]++;
        double total = 0.0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[2]++;
        int rowCount = data.getRowCount();
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((r < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[1]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[2]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[3]++;
}
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[4]++;
            Number n = data.getValue(r, column);
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[1]++;
                total += n.doubleValue();
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[6]++;
   
            } else {
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[2]++;}
        }
        return total;
    }
    
    /**
     * Returns the total of the values in one row of the supplied data
     * table.
     * 
     * @param data  the table of values (<code>null</code> not permitted).
     * @param row  the row index (zero-based).
     * 
     * @return The total of the values in the specified row.
     */
    public static double calculateRowTotal(Values2D data, int row) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[7]++;
        double total = 0.0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[8]++;
        int columnCount = data.getColumnCount();
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[9]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int c = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[4]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[5]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[6]++;
}
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[10]++;
            Number n = data.getValue(row, c);
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[3]++;
                total += n.doubleValue();
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[12]++;
   
            } else {
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[4]++;}
        }
        return total;
    }
    
    /**
     * Constructs an array of <code>Number</code> objects from an array of 
     * <code>double</code> primitives.
     *
     * @param data  the data (<code>null</code> not permitted).
     *
     * @return An array of <code>Double</code>.
     */
    public static Number[] createNumberArray(double[] data) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[5]++;
            throw new IllegalArgumentException("Null 'data' argument.");
   
        } else {
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[6]++;}
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[14]++;
        Number[] result = new Number[data.length];
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[15]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < data.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[7]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[8]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[9]++;
}
            result[i] = new Double(data[i]);
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[16]++;
        }
        return result;
    }

    /**
     * Constructs an array of arrays of <code>Number</code> objects from a 
     * corresponding structure containing <code>double</code> primitives.
     *
     * @param data  the data (<code>null</code> not permitted).
     *
     * @return An array of <code>Double</code>.
     */
    public static Number[][] createNumberArray2D(double[][] data) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[7]++;
            throw new IllegalArgumentException("Null 'data' argument.");
   
        } else {
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[8]++;}
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[18]++;
        int l1 = data.length;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[19]++;
        Number[][] result = new Number[l1][];
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[20]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[10]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < l1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[10]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[11]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[12]++;
}
            result[i] = createNumberArray(data[i]);
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[21]++;
        }
        return result;
    }

    /**
     * Returns a {@link KeyedValues} instance that contains the cumulative 
     * percentage values for the data in another {@link KeyedValues} instance.
     * <p>
     * The percentages are values between 0.0 and 1.0 (where 1.0 = 100%).
     *
     * @param data  the data (<code>null</code> not permitted).
     *
     * @return The cumulative percentages.
     */
    public static KeyedValues getCumulativePercentages(KeyedValues data) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[9]++;
            throw new IllegalArgumentException("Null 'data' argument.");
   
        } else {
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[10]++;}
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[23]++;
        DefaultKeyedValues result = new DefaultKeyedValues();
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[24]++;
        double total = 0.0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[25]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[13]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < data.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[13]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[14]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[15]++;
}
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[26]++;
            Number v = data.getValue(i);
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[27]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[11]++;
                total = total + v.doubleValue();
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[28]++;

            } else {
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[12]++;}
        }
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[29]++;
        double runningTotal = 0.0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[30]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < data.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[16]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[17]--;
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.loops[18]++;
}
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[31]++;
            Number v = data.getValue(i);
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[32]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[13]++;
                runningTotal = runningTotal + v.doubleValue();
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[33]++;

            } else {
  CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.branches[14]++;}
            result.addValue(data.getKey(i), new Double(runningTotal / total));
CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp.statements[34]++;
        }
        return result;
    }

}

class CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.data.DataUtilities.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$5oppif5mt7ypjhfrfv34ti4w8zmp () {
    super("org.jfree.data.DataUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.DataUtilities.java");
      for (int i = 1; i <= 34; i++) {
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
    for (int i = 1; i <= 13; i++) {
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

