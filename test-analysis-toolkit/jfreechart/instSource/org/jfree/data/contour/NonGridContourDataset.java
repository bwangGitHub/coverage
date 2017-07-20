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
 * NonGridContourDataset.java
 * --------------------------
 * (C) Copyright 2002-2007, by David M. O'Donnell.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes (from 24-Jul-2003)
 * --------------------------
 * 24-Jul-2003 : Added standard header (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Jan-2007 : Deprecated (DG);
 *
 */

package org.jfree.data.contour;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.Range;

/**
 * A convenience class that extends the {@link DefaultContourDataset} to 
 * accommodate non-grid data.
 * 
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class NonGridContourDataset extends DefaultContourDataset {
  static {
    CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.ping();
  }


    /** Default number of x values. */
    static final int DEFAULT_NUM_X = 50;
  static {
    CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[1]++;
  }
    
    /** Default number of y values. */
    static final int DEFAULT_NUM_Y = 50;
  static {
    CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[2]++;
  }
    
    /** Default power. */
    static final int DEFAULT_POWER = 4;
  static {
    CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[3]++;
  }

    /**
     * Default constructor.
     */
    public NonGridContourDataset() {
        super();
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[4]++;
    }

    /**
     * Constructor for NonGridContourDataset.  Uses default values for grid 
     * dimensions and weighting.
     * 
     * @param seriesName  the series name.
     * @param xData  the x values.
     * @param yData  the y values.
     * @param zData  the z values.
     */
    public NonGridContourDataset(String seriesName,
                                 Object[] xData, Object[] yData, 
                                 Object[] zData) {
        super(seriesName, xData, yData, zData);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[5]++;
        buildGrid(DEFAULT_NUM_X, DEFAULT_NUM_Y, DEFAULT_POWER);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[6]++;
    }

    /**
     * Constructor for NonGridContourDataset.
     * 
     * @param seriesName  the series name.
     * @param xData  the x values.
     * @param yData  the y values.
     * @param zData  the z values.
     * @param numX  number grid cells in along the x-axis
     * @param numY  number grid cells in along the y-axis
     * @param power  exponent for inverse distance weighting
     */
    public NonGridContourDataset(String seriesName, 
                                 Object[] xData, Object[] yData, 
                                 Object[] zData,
                                 int numX, int numY, int power) {
        super(seriesName, xData, yData, zData);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[7]++;
        buildGrid(numX, numY, power);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[8]++;
    }

    /**
     * Builds a regular grid.  Maps the non-grid data into the regular grid 
     * using an inverse distance between grid and non-grid points.  Weighting 
     * of distance can be controlled by setting through the power parameter 
     * that controls the exponent used on the distance weighting 
     * (e.g., distance^power).
     * 
     * @param numX  number grid points in along the x-axis
     * @param numY  number grid points in along the y-axis
     * @param power  exponent for inverse distance weighting
     */
    protected void buildGrid(int numX, int numY, int power) {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[9]++;

        int numValues = numX * numY;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[10]++;
        double[] xGrid = new double[numValues];
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[11]++;
        double[] yGrid = new double [numValues];
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[12]++;
        double[] zGrid = new double [numValues];
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[13]++;

        // Find min, max for the x and y axes
        double xMin = 1.e20;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((k < this.xValues.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[1]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[2]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[3]++;
}
            xMin = Math.min(xMin, this.xValues[k].doubleValue());
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[15]++;
        }
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[16]++;

        double xMax = -1.e20;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((k < this.xValues.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[4]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[5]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[6]++;
}
            xMax = Math.max(xMax, this.xValues[k].doubleValue());
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[18]++;
        }
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[19]++;

        double yMin = 1.e20;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[20]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[7]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((k < this.yValues.length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[7]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[8]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[9]++;
}
            yMin = Math.min(yMin, this.yValues[k].doubleValue());
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[21]++;
        }
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[22]++;

        double yMax = -1.e20;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[23]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[10]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((k < this.yValues.length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[10]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[11]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[12]++;
}
            yMax = Math.max(yMax, this.yValues[k].doubleValue());
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[24]++;
        }
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[25]++;

        Range xRange = new Range(xMin, xMax);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[26]++;
        Range yRange = new Range(yMin, yMax);

        xRange.getLength();
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[27]++;
        yRange.getLength();
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[28]++;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[29]++;

        // Determine the cell size
        double dxGrid = xRange.getLength() / (numX - 1);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[30]++;
        double dyGrid = yRange.getLength() / (numY - 1);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[31]++;

        // Generate the grid
        double x = 0.0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[32]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[13]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < numX) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[13]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[14]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[15]++;
}
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[1]++;
                x = xMin;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[34]++;

            }
            else {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[2]++;
                x += dxGrid;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[35]++;
            }
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[36]++;
            double y = 0.0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[37]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[16]++;


int CodeCoverConditionCoverageHelper_C7;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((j < numY) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[16]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[17]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[18]++;
}
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[38]++;
                int k = numY * i + j;
                xGrid[k] = x;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[39]++;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[3]++;
                    y = yMin;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[41]++;

                }
                else {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[4]++;
                    y += dyGrid;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[42]++;
                }
                yGrid[k] = y;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[43]++;
            }
        }
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[44]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[19]++;


int CodeCoverConditionCoverageHelper_C9;

        // Map the nongrid data into the new regular grid
        for (int kGrid = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((kGrid < xGrid.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); kGrid++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[19]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[20]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[21]++;
}
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[45]++;
            double dTotal = 0.0;
            zGrid[kGrid] = 0.0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[46]++;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[47]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[22]++;


int CodeCoverConditionCoverageHelper_C10;
            for (int k = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((k < this.xValues.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[22]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[23]--;
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.loops[24]++;
}
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[48]++;
                double xPt = this.xValues[k].doubleValue();
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[49]++;
                double yPt = this.yValues[k].doubleValue();
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[50]++;
                double d = distance(xPt, yPt, xGrid[kGrid], yGrid[kGrid]);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((power != 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[5]++;
                    d = Math.pow(d, power);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[52]++;

                } else {
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[6]++;}
                d = Math.sqrt(d);
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[53]++;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((d > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[7]++;
                    d = 1.0 / d;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[55]++;

                }
                else {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[8]++; // if d is real small set the inverse to a large number 
                       // to avoid INF
                    d = 1.e20;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[56]++;
                }
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.zValues[k] != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[9]++;
                    // scale by the inverse of distance^power
                    zGrid[kGrid] += this.zValues[k].doubleValue() * d;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[58]++;
 
                } else {
  CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.branches[10]++;}
                dTotal += d;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[59]++;
            }
            zGrid[kGrid] = zGrid[kGrid] / dTotal;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[60]++;  //remove distance of the sum
        }

        //initalize xValues, yValues, and zValues arrays.
        initialize(
            formObjectArray(xGrid), formObjectArray(yGrid), 
            formObjectArray(zGrid)
        );
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[61]++;

    }

    /**
     * Calculates the distance between two points.
     * 
     * @param xDataPt  the x coordinate.
     * @param yDataPt  the y coordinate.
     * @param xGrdPt  the x grid coordinate.
     * @param yGrdPt  the y grid coordinate.
     * 
     * @return The distance between two points.
     */
    protected double distance(double xDataPt, 
                              double yDataPt, 
                              double xGrdPt, 
                              double yGrdPt) {
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[62]++;
        double dx = xDataPt - xGrdPt;
CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t.statements[63]++;
        double dy = yDataPt - yGrdPt;
        return Math.sqrt(dx * dx + dy * dy);
    }

}

class CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t ());
  }
    public static long[] statements = new long[64];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.data.contour.NonGridContourDataset.java";
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
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$pe59519sx3ev2h78fror7l953k36qb5apyzr9e1t () {
    super("org.jfree.data.contour.NonGridContourDataset.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 63; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.contour.NonGridContourDataset.java");
      for (int i = 1; i <= 63; i++) {
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
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

