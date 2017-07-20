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
 * --------------------------
 * DefaultContourDataset.java
 * --------------------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes (from 23-Jan-2003)
 * --------------------------
 * 23-Jan-2003 : Added standard header (DG);
 * 20-May-2003 : removed member vars numX and numY, which were never used (TM);
 * 06-May-2004 : Now extends AbstractXYZDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue(), getY() with getYValue() and 
 *               getZ() with getZValue() methods (DG);
 * ------------- JFREECHART 1.0.x --------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 * 
 */

package org.jfree.data.contour;

import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.AbstractXYZDataset;
import org.jfree.data.xy.XYDataset;

/**
 * A convenience class that provides a default implementation of the 
 * {@link ContourDataset} interface.
 * 
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class DefaultContourDataset extends AbstractXYZDataset 
                                   implements ContourDataset {
  static {
    CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.ping();
  }


    /** The series name (this dataset supports only one series). */
    protected Comparable seriesKey = null;
  {
    CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[1]++;
  }

    /** Storage for the x values. */
    protected Number[] xValues = null;
  {
    CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[2]++;
  }

    /** Storage for the y values. */
    protected Number[] yValues = null;
  {
    CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[3]++;
  }

    /** Storage for the z values. */
    protected Number[] zValues = null;
  {
    CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[4]++;
  }

    /** The index for the start of each column in the data. */
    protected int[] xIndex = null;
  {
    CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[5]++;
  }

    /** Flags that track whether x, y and z are dates. */
    boolean[] dateAxis = new boolean[3];
  {
    CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[6]++;
  }

    /**
     * Creates a new dataset, initially empty.
     */
    public DefaultContourDataset() {
        super();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[7]++;
    }

    /**
     * Constructs a new dataset with the given data.
     *
     * @param seriesKey  the series key.
     * @param xData  the x values.
     * @param yData  the y values.
     * @param zData  the z values.
     */
    public DefaultContourDataset(Comparable seriesKey,
                                 Object[] xData,
                                 Object[] yData,
                                 Object[] zData) {

        this.seriesKey = seriesKey;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[8]++;
        initialize(xData, yData, zData);
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[9]++;
    }

    /**
     * Initialises the dataset.
     * 
     * @param xData  the x values.
     * @param yData  the y values.
     * @param zData  the z values.
     */
    public void initialize(Object[] xData,
                           Object[] yData,
                           Object[] zData) {

        this.xValues = new Double[xData.length];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[10]++;
        this.yValues = new Double[yData.length];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[11]++;
        this.zValues = new Double[zData.length];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[12]++;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[13]++;

        // We organise the data with the following assumption:
        // 1) the data are sorted by x then y
        // 2) that the data will be represented by a rectangle formed by
        //    using x[i+1], x, y[j+1], and y.
        // 3) we march along the y-axis at the same value of x until a new 
        //    value x is found at which point we will flag the index 
        //    where x[i+1]<>x[i]

        Vector tmpVector = new Vector();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[14]++; //create a temporary vector
        double x = 1.123452e31;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[1]++;


int CodeCoverConditionCoverageHelper_C1; // set x to some arbitary value (used below)
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((k < this.xValues.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[1]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[2]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[3]++;
}
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xData[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[1]++;
                Number xNumber;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((xData[k] instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[3]++;
                    xNumber = (Number) xData[k];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[18]++;

                }
                else {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[4]++;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[19]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((xData[k] instanceof Date) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[5]++;
                    this.dateAxis[0] = true;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[20]++;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[21]++;
                    Date xDate = (Date) xData[k];
                    xNumber = new Long(xDate.getTime());
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[22]++;
 //store data as Long
                }
                else {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[6]++;
                    xNumber = new Integer(0);
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[23]++;
                }
}
                this.xValues[k] = new Double(xNumber.doubleValue());
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[24]++;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[25]++;
int CodeCoverConditionCoverageHelper_C5; 
                    // store Number as Double

                // check if starting new column
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((x != this.xValues[k].doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[7]++;
                    tmpVector.add(new Integer(k));
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[26]++; //store index where new 
                                                   //column starts
                    x = this.xValues[k].doubleValue();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[27]++;
 
                                             // set x to most recent value
                } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[8]++;}

            } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[2]++;}
        }
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[28]++;

        Object[] inttmp = tmpVector.toArray();
        this.xIndex = new int[inttmp.length];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[29]++;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[30]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;  // create array xIndex to hold 
                                               // new column indices

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < inttmp.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[4]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[5]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[6]++;
}
            this.xIndex[i] = ((Integer) inttmp[i]).intValue();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[31]++;
        }
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((k < this.yValues.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[7]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[8]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[9]++;
} // store y and z axes 
                                                        // as Doubles
            this.yValues[k] = (Double) yData[k];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[33]++;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((zData[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[9]++;
                this.zValues[k] = (Double) zData[k];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[35]++;

            } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[10]++;}
        }
    }

    /**
     * Creates an object array from an array of doubles.
     *
     * @param data  the data.
     *
     * @return An array of <code>Double</code> objects.
     */
    public static Object[][] formObjectArray(double[][] data) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[36]++;
        Object[][] object = new Double[data.length][data[0].length];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[37]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[10]++;


int CodeCoverConditionCoverageHelper_C9;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < object.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[10]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[11]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[12]++;
}
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[38]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[13]++;


int CodeCoverConditionCoverageHelper_C10;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((j < object[i].length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[13]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[14]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[15]++;
}
                object[i][j] = new Double(data[i][j]);
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[39]++;
            }
        }
        return object;
    }

    /**
     * Creates an object array from an array of doubles.
     *
     * @param data  the data.
     *
     * @return An array of <code>Double</code> objects.
     */
    public static Object[] formObjectArray(double[] data) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[40]++;
        Object[] object = new Double[data.length];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[41]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[16]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < object.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[16]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[17]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[18]++;
}
            object[i] = new Double(data[i]);
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[42]++;
        }
        return object;
    }

    /**
     * Returns the number of items in the specified series.  This method 
     * is provided to satisfy the {@link XYDataset} interface implementation.
     *
     * @param series  must be zero, as this dataset only supports one series.
     *
     * @return The item count.
     */
    public int getItemCount(int series) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[43]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((series > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[11]++;
            throw new IllegalArgumentException("Only one series for contour");

        } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[12]++;}
        return this.zValues.length;
    }

    /**
     * Returns the maximum z-value.
     *
     * @return The maximum z-value.
     */
    public double getMaxZValue() {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[44]++;
        double zMax = -1.e20;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[45]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[19]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((k < this.zValues.length) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[19]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[20]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[21]++;
}
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[46]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.zValues[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[13]++;
                zMax = Math.max(zMax, this.zValues[k].doubleValue());
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[47]++;

            } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[14]++;}
        }
        return zMax;
    }

    /**
     * Returns the minimum z-value.
     *
     * @return The minimum z-value.
     */
    public double getMinZValue() {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[48]++;
        double zMin = 1.e20;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[49]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[22]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((k < this.zValues.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[22]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[23]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[24]++;
}
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[50]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.zValues[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[15]++;
                zMin = Math.min(zMin, this.zValues[k].doubleValue());
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[51]++;

            } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[16]++;}
        }
        return zMin;
    }

    /**
     * Returns the maximum z-value within visible region of plot.
     *
     * @param x  the x range.
     * @param y  the y range.
     *
     * @return The z range.
     */
    public Range getZValueRange(Range x, Range y) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[52]++;

        double minX = x.getLowerBound();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[53]++;
        double minY = y.getLowerBound();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[54]++;
        double maxX = x.getUpperBound();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[55]++;
        double maxY = y.getUpperBound();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[56]++;

        double zMin = 1.e20;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[57]++;
        double zMax = -1.e20;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[58]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[25]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((k < this.zValues.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[25]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[26]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[27]++;
}
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[59]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (128)) == 0 || true) &&
 ((this.xValues[k].doubleValue() >= minX) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((this.xValues[k].doubleValue() <= maxX) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((this.yValues[k].doubleValue() >= minY) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.yValues[k].doubleValue() <= maxY) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[17]++;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[60]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.zValues[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[19]++;
                    zMin = Math.min(zMin, this.zValues[k].doubleValue());
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[61]++;
                    zMax = Math.max(zMax, this.zValues[k].doubleValue());
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[62]++;

                } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[20]++;}

            } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[18]++;}
        }

        return new Range(zMin, zMax);
    }

    /**
     * Returns the minimum z-value.
     *
     * @param minX  the minimum x value.
     * @param minY  the minimum y value.
     * @param maxX  the maximum x value.
     * @param maxY  the maximum y value.
     *
     * @return The minimum z-value.
     */
    public double getMinZValue(double minX, 
                               double minY, 
                               double maxX, 
                               double maxY) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[63]++;

        double zMin = 1.e20;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[64]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[28]++;


int CodeCoverConditionCoverageHelper_C20;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((k < this.zValues.length) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[28]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[29]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[30]++;
}
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[65]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.zValues[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[21]++;
                zMin = Math.min(zMin, this.zValues[k].doubleValue());
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[66]++;

            } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[22]++;}
        }
        return zMin;

    }

    /**
     * Returns the number of series.
     * <P>
     * Required by XYDataset interface (this will always return 1)
     *
     * @return 1.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the name of the specified series.
     *
     * Method provided to satisfy the XYDataset interface implementation
     *
     * @param series must be zero.
     *
     * @return The series name.
     */
    public Comparable getSeriesKey(int series) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[67]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((series > 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[23]++;
            throw new IllegalArgumentException("Only one series for contour");

        } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[24]++;}
        return this.seriesKey;
    }

    /**
     * Returns the index of the xvalues.
     *
     * @return The x values.
     */
    public int[] getXIndices() {
        return this.xIndex;
    }

    /**
     * Returns the x values.
     *
     * @return The x values.
     */
    public Number[] getXValues() {
        return this.xValues;
    }

    /**
     * Returns the x value for the specified series and index (zero-based 
     * indices).  Required by the {@link XYDataset}.
     *
     * @param series  must be zero;
     * @param item  the item index (zero-based).
     *
     * @return The x value.
     */
    public Number getX(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[68]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((series > 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[25]++;
            throw new IllegalArgumentException("Only one series for contour");

        } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[26]++;}
        return this.xValues[item];
    }

    /**
     * Returns an x value.
     *
     * @param item  the item index (zero-based).
     *
     * @return The X value.
     */
    public Number getXValue(int item) {
        return this.xValues[item];
    }

    /**
     * Returns a Number array containing all y values.
     *
     * @return The Y values.
     */
    public Number[] getYValues() {
        return this.yValues;
    }

    /**
     * Returns the y value for the specified series and index (zero-based 
     * indices).  Required by the {@link XYDataset}.
     *
     * @param series  the series index (must be zero for this dataset).
     * @param item  the item index (zero-based).
     *
     * @return The Y value.
     */
    public Number getY(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[69]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((series > 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[27]++;
            throw new IllegalArgumentException("Only one series for contour");

        } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[28]++;}
        return this.yValues[item];
    }

    /**
     * Returns a Number array containing all z values.
     *
     * @return The Z values.
     */
    public Number[] getZValues() {
        return this.zValues;
    }

    /**
     * Returns the z value for the specified series and index (zero-based 
     * indices).  Required by the {@link XYDataset}
     *
     * @param series  the series index (must be zero for this dataset).
     * @param item  the item index (zero-based).
     *
     * @return The Z value.
     */
    public Number getZ(int series, int item) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[70]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((series > 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[29]++;
            throw new IllegalArgumentException("Only one series for contour");

        } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[30]++;}
        return this.zValues[item];
    }

    /**
     * Returns an int array contain the index into the x values.
     *
     * @return The X values.
     */
    public int[] indexX() {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[71]++;
        int[] index = new int[this.xValues.length];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[72]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[31]++;


int CodeCoverConditionCoverageHelper_C26;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((k < index.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[31]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[32]--;
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.loops[33]++;
}
            index[k] = indexX(k);
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[73]++;
        }
        return index;
    }

    /**
     * Given index k, returns the column index containing k.
     *
     * @param k index of interest.
     *
     * @return The column index.
     */
    public int indexX(int k) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[74]++;
        int i = Arrays.binarySearch(this.xIndex, k);
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[75]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[31]++;
            return i;

        } 
        else {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[32]++;
            return -1 * i - 2;
        }
    }


    /**
     * Given index k, return the row index containing k.
     *
     * @param k index of interest.
     *
     * @return The row index.
     */
    public int indexY(int k) { // this may be obsolete (not used anywhere)
        return (k / this.xValues.length);
    }

    /**
     * Given column and row indices, returns the k index.
     *
     * @param i index of along x-axis.
     * @param j index of along y-axis.
     *
     * @return The Z index.
     */
    public int indexZ(int i, int j) {
        return this.xValues.length * j + i;
    }

    /**
     * Returns true if axis are dates.
     * 
     * @param axisNumber The axis where 0-x, 1-y, and 2-z.
     * 
     * @return A boolean.
     */
    public boolean isDateAxis(int axisNumber) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[76]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((axisNumber < 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((axisNumber > 2) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[33]++;
            return false;
 // bad axisNumber
        } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[34]++;}
        return this.dateAxis[axisNumber];
    }

    /**
     * Sets the names of the series in the data source.
     *
     * @param seriesKeys  the keys of the series in the data source.
     */
    public void setSeriesKeys(Comparable[] seriesKeys) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[77]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((seriesKeys.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[35]++;
            throw new IllegalArgumentException(
                    "Contours only support one series");

        } else {
  CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.branches[36]++;}
        this.seriesKey = seriesKeys[0];
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[78]++;
        fireDatasetChanged();
CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip.statements[79]++;
    }

}

class CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip ());
  }
    public static long[] statements = new long[80];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[30];
  static {
    final String SECTION_NAME = "org.jfree.data.contour.DefaultContourDataset.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,2,1};
    for (int i = 1; i <= 29; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$m553c7l0laov7q0gi2nfpvo4uinj8ducw5cpdoip () {
    super("org.jfree.data.contour.DefaultContourDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 79; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 29; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.contour.DefaultContourDataset.java");
      for (int i = 1; i <= 79; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 29; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 11; i++) {
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

