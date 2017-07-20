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
 * -----------------
 * MatrixSeries.java
 * -----------------
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh;;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Zhitao Wang;
 *
 * Changes
 * -------
 * 10-Jul-2003 : Version 1 contributed by Barak Naveh (DG);
 * 10-Feb-2004 : Fixed Checkstyle complaints (DG);
 * 21-May-2004 : Fixed bug 940188 - problem in getItemColumn() and 
 *               getItemRow() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 27-Nov-2006 : Fixed bug in equals() method (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;

import org.jfree.data.general.Series;

/**
 * Represents a dense matrix M[i,j] where each Mij item of the matrix has a
 * value (default is 0).
 */
public class MatrixSeries extends Series implements Serializable {
  static {
    CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 7934188527308315704L;
  static {
    CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[1]++;
  }    
    
    /** Series matrix values */
    protected double[][] data;

    /**
     * Constructs a new matrix series.
     * <p>
     * By default, all matrix items are initialzed to 0.
     * </p>
     *
     * @param name  series name (<code>null</code> not permitted).
     * @param rows  the number of rows.
     * @param columns  the number of columns.
     */
    public MatrixSeries(String name, int rows, int columns) {
        super(name);
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[2]++;
        this.data = new double[rows][columns];
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[3]++;
        zeroAll();
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[4]++;
    }

    /**
     * Returns the number of columns in this matrix series.
     *
     * @return The number of columns in this matrix series.
     */
    public int getColumnsCount() {
        return this.data[0].length;
    }


    /**
     * Return the matrix item at the specified index.  Note that this method
     * creates a new <code>Double</code> instance every time it is called.
     *
     * @param itemIndex item index.
     *
     * @return The matrix item at the specified index.
     * 
     * @see #get(int, int)
     */
    public Number getItem(int itemIndex) {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[5]++;
        int i = getItemRow(itemIndex);
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[6]++;
        int j = getItemColumn(itemIndex);
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[7]++;

        Number n = new Double(get(i, j));

        return n;
    }


    /**
     * Returns the column of the specified item.
     *
     * @param itemIndex the index of the item.
     *
     * @return The column of the specified item.
     */
    public int getItemColumn(int itemIndex) {
        //assert itemIndex >= 0 && itemIndex < getItemCount();
        return itemIndex % getColumnsCount();
    }


    /**
     * Returns the number of items in the series.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return getRowCount() * getColumnsCount();
    }


    /**
     * Returns the row of the specified item.
     *
     * @param itemIndex the index of the item.
     *
     * @return The row of the specified item.
     */
    public int getItemRow(int itemIndex) {
        //assert itemIndex >= 0 && itemIndex < getItemCount();
        return itemIndex / getColumnsCount();
    }


    /**
     * Returns the number of rows in this matrix series.
     *
     * @return The number of rows in this matrix series.
     */
    public int getRowCount() {
        return this.data.length;
    }


    /**
     * Returns the value of the specified item in this matrix series.
     *
     * @param i the row of the item.
     * @param j the column of the item.
     *
     * @return The value of the specified item in this matrix series.
     * 
     * @see #getItem(int)
     * @see #update(int, int, double)
     */
    public double get(int i, int j) {
        return this.data[i][j];
    }


    /**
     * Updates the value of the specified item in this matrix series.
     *
     * @param i the row of the item.
     * @param j the column of the item.
     * @param mij the new value for the item.
     * 
     * @see #get(int, int)
     */
    public void update(int i, int j, double mij) {
        this.data[i][j] = mij;
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[8]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[9]++;
    }


    /**
     * Sets all matrix values to zero and sends a 
     * {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     */
    public void zeroAll() {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[10]++;
        int rows = getRowCount();
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[11]++;
        int columns = getColumnsCount();
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

        for (int row = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((row < rows) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); row++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[1]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[2]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[3]++;
}
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
            for (int column = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((column < columns) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[4]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[5]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[6]++;
}
                this.data[row][column] = 0.0;
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[14]++;
            }
        }
        fireSeriesChanged();
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[15]++;
    }
    
    /**
     * Tests this object instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[1]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[2]++;}
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof MatrixSeries) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[4]++;}
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[18]++;
        MatrixSeries that = (MatrixSeries) obj;
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getRowCount() == that.getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[6]++;}
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((getColumnsCount() == that.getColumnsCount()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[8]++;}
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[21]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((r < getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[7]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[8]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[9]++;
}
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[22]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[10]++;


int CodeCoverConditionCoverageHelper_C8;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((c < getColumnsCount()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[10]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[11]--;
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.loops[12]++;
}
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.statements[23]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((get(r, c) != that.get(r, c)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[9]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch.branches[10]++;}
            }
        }
        return super.equals(obj);
    }
    
}

class CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.MatrixSeries.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$wkrmwhn49tj0k4c37fvt4e96ch () {
    super("org.jfree.data.xy.MatrixSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.MatrixSeries.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

