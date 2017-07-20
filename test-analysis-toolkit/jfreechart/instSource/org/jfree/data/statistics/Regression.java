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
 * ---------------
 * Regression.java
 * ---------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Sep-2002 : Version 1 (DG);
 * 18-Aug-2003 : Added 'abstract' (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 *
 */

package org.jfree.data.statistics;

import org.jfree.data.xy.XYDataset;

/**
 * A utility class for fitting regression curves to data.
 */
public abstract class Regression {
  static {
    CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.ping();
  }


    /**
     * Returns the parameters 'a' and 'b' for an equation y = a + bx, fitted to
     * the data using ordinary least squares regression.  The result is 
     * returned as a double[], where result[0] --> a, and result[1] --> b.
     *
     * @param data  the data.
     *
     * @return The parameters.
     */
    public static double[] getOLSRegression(double[][] data) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[1]++;

        int n = data.length;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n < 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[1]++;
            throw new IllegalArgumentException("Not enough data.");

        } else {
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[2]++;}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[3]++;

        double sumX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[4]++;
        double sumY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[5]++;
        double sumXX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[6]++;
        double sumXY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[1]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[2]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[3]++;
}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[8]++;
            double x = data[i][0];
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[9]++;
            double y = data[i][1];
            sumX += x;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[10]++;
            sumY += y;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[11]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[12]++;
            double xx = x * x;
            sumXX += xx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[13]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[14]++;
            double xy = x * y;
            sumXY += xy;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[15]++;
        }
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[16]++;
        double sxx = sumXX - (sumX * sumX) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[17]++;
        double sxy = sumXY - (sumX * sumY) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[18]++;
        double xbar = sumX / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[19]++;
        double ybar = sumY / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[20]++;

        double[] result = new double[2];
        result[1] = sxy / sxx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[21]++;
        result[0] = ybar - result[1] * xbar;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[22]++;

        return result;

    }

    /**
     * Returns the parameters 'a' and 'b' for an equation y = a + bx, fitted to 
     * the data using ordinary least squares regression. The result is returned 
     * as a double[], where result[0] --> a, and result[1] --> b.
     *
     * @param data  the data.
     * @param series  the series (zero-based index).
     *
     * @return The parameters.
     */
    public static double[] getOLSRegression(XYDataset data, int series) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[23]++;

        int n = data.getItemCount(series);
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n < 2) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[3]++;
            throw new IllegalArgumentException("Not enough data.");

        } else {
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[4]++;}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[25]++;

        double sumX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[26]++;
        double sumY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[27]++;
        double sumXX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[28]++;
        double sumXY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[29]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[4]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[5]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[6]++;
}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[30]++;
            double x = data.getXValue(series, i);
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[31]++;
            double y = data.getYValue(series, i);
            sumX += x;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[32]++;
            sumY += y;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[33]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[34]++;
            double xx = x * x;
            sumXX += xx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[35]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[36]++;
            double xy = x * y;
            sumXY += xy;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[37]++;
        }
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[38]++;
        double sxx = sumXX - (sumX * sumX) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[39]++;
        double sxy = sumXY - (sumX * sumY) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[40]++;
        double xbar = sumX / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[41]++;
        double ybar = sumY / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[42]++;

        double[] result = new double[2];
        result[1] = sxy / sxx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[43]++;
        result[0] = ybar - result[1] * xbar;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[44]++;

        return result;

    }

    /**
     * Returns the parameters 'a' and 'b' for an equation y = ax^b, fitted to 
     * the data using a power regression equation.  The result is returned as 
     * an array, where double[0] --> a, and double[1] --> b.
     *
     * @param data  the data.
     *
     * @return The parameters.
     */
    public static double[] getPowerRegression(double[][] data) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[45]++;

        int n = data.length;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[46]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n < 2) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[5]++;
            throw new IllegalArgumentException("Not enough data.");

        } else {
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[6]++;}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[47]++;

        double sumX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[48]++;
        double sumY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[49]++;
        double sumXX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[50]++;
        double sumXY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[51]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[7]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[8]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[9]++;
}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[52]++;
            double x = Math.log(data[i][0]);
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[53]++;
            double y = Math.log(data[i][1]);
            sumX += x;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[54]++;
            sumY += y;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[55]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[56]++;
            double xx = x * x;
            sumXX += xx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[57]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[58]++;
            double xy = x * y;
            sumXY += xy;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[59]++;
        }
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[60]++;
        double sxx = sumXX - (sumX * sumX) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[61]++;
        double sxy = sumXY - (sumX * sumY) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[62]++;
        double xbar = sumX / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[63]++;
        double ybar = sumY / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[64]++;

        double[] result = new double[2];
        result[1] = sxy / sxx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[65]++;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[66]++;

        return result;

    }

    /**
     * Returns the parameters 'a' and 'b' for an equation y = ax^b, fitted to 
     * the data using a power regression equation.  The result is returned as 
     * an array, where double[0] --> a, and double[1] --> b.
     *
     * @param data  the data.
     * @param series  the series to fit the regression line against.
     *
     * @return The parameters.
     */
    public static double[] getPowerRegression(XYDataset data, int series) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[67]++;

        int n = data.getItemCount(series);
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[68]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n < 2) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[7]++;
            throw new IllegalArgumentException("Not enough data.");

        } else {
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.branches[8]++;}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[69]++;

        double sumX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[70]++;
        double sumY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[71]++;
        double sumXX = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[72]++;
        double sumXY = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[73]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[10]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[10]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[11]--;
  CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.loops[12]++;
}
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[74]++;
            double x = Math.log(data.getXValue(series, i));
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[75]++;
            double y = Math.log(data.getYValue(series, i));
            sumX += x;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[76]++;
            sumY += y;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[77]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[78]++;
            double xx = x * x;
            sumXX += xx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[79]++;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[80]++;
            double xy = x * y;
            sumXY += xy;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[81]++;
        }
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[82]++;
        double sxx = sumXX - (sumX * sumX) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[83]++;
        double sxy = sumXY - (sumX * sumY) / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[84]++;
        double xbar = sumX / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[85]++;
        double ybar = sumY / n;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[86]++;

        double[] result = new double[2];
        result[1] = sxy / sxx;
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[87]++;
        result[0] = Math.pow(Math.exp(1.0), ybar - result[1] * xbar);
CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl.statements[88]++;

        return result;

    }

}

class CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl ());
  }
    public static long[] statements = new long[89];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.Regression.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$op0voryc1otd6ijqquwz3vl () {
    super("org.jfree.data.statistics.Regression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 88; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.statistics.Regression.java");
      for (int i = 1; i <= 88; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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

