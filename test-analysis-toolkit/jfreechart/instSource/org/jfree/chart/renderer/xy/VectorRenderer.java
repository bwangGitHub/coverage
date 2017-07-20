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
 * -------------------
 * VectorRenderer.java
 * -------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Updated for method name changes (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.xy.VectorXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 * A renderer that represents data from an {@link VectorXYDataset} by drawing a
 * line with an arrow at each (x, y) point.
 * 
 * @since 1.0.6
 */
public class VectorRenderer extends AbstractXYItemRenderer 
        implements XYItemRenderer, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.ping();
  }

    
    /** The length of the base. */
    private double baseLength = 0.10;
  {
    CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[1]++;
  }
    
    /** The length of the head. */
    private double headLength = 0.14;
  {
    CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[2]++;
  }
    
    
    /**
     * Creates a new <code>XYBlockRenderer</code> instance with default 
     * attributes.
     */
    public VectorRenderer() {
    }
    
    /**
     * Returns the lower and upper bounds (range) of the x-values in the 
     * specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     */
    public Range findDomainBounds(XYDataset dataset) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[1]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");
   
        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[2]++;}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[4]++;
        double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[5]++;
        double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[6]++;
        int seriesCount = dataset.getSeriesCount();
        double lvalue;
        double uvalue;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset instanceof VectorXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[3]++;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[8]++;
            VectorXYDataset vdataset = (VectorXYDataset) dataset;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[1]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[2]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[3]++;
}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[10]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[11]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[4]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[5]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[6]++;
}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[12]++;
                    double delta = vdataset.getVectorXValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((delta < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[5]++;
                        uvalue = vdataset.getXValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[14]++;
                        lvalue = uvalue + delta;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[15]++;

                    }
                    else {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[6]++;
                        lvalue = vdataset.getXValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[16]++;
                        uvalue = lvalue + delta;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[17]++;
                    }
                    minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[18]++;
                    maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[19]++;
                }
            }

        }
        else {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[4]++;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[20]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[7]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[8]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[9]++;
}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[21]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[22]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[10]++;


int CodeCoverConditionCoverageHelper_C7;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[10]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[11]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[12]++;
}
                    lvalue = dataset.getXValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[23]++;
                    uvalue = lvalue;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[24]++;
                    minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[25]++;
                    maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[26]++;
                }
            }
        }
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((minimum > maximum) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[7]++;
            return null;

        }
        else {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[8]++;
            return new Range(minimum, maximum);
        }
    }
    
    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code> 
     *         or empty).
     */
    public Range findRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[9]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");
   
        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[10]++;}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[29]++;
        double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[30]++;
        double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[31]++;
        int seriesCount = dataset.getSeriesCount();
        double lvalue;
        double uvalue;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((dataset instanceof VectorXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[11]++;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[33]++;
            VectorXYDataset vdataset = (VectorXYDataset) dataset;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[34]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[13]++;


int CodeCoverConditionCoverageHelper_C11;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[13]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[14]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[15]++;
}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[35]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[36]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[16]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[17]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[18]++;
}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[37]++;
                    double delta = vdataset.getVectorYValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[38]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((delta < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[13]++;
                        uvalue = vdataset.getYValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[39]++;
                        lvalue = uvalue + delta;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[40]++;

                    }
                    else {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[14]++;
                        lvalue = vdataset.getYValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[41]++;
                        uvalue = lvalue + delta;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[42]++;
                    }
                    minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[43]++;
                    maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[44]++;
                }
            }

        }
        else {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[12]++;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[45]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[19]++;


int CodeCoverConditionCoverageHelper_C14;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[19]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[20]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[21]++;
}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[46]++;
                int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[47]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[22]++;


int CodeCoverConditionCoverageHelper_C15;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[22]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[23]--;
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.loops[24]++;
}
                    lvalue = dataset.getYValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[48]++;
                    uvalue = lvalue;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[49]++;
                    minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[50]++;
                    maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[51]++;
                }
            }
        }
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((minimum > maximum) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[15]++;
            return null;

        }
        else {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[16]++;
            return new Range(minimum, maximum);
        }
    }
    
    /**
     * Draws the block representing the specified item.
     * 
     * @param g2  the graphics device.
     * @param state  the state.
     * @param dataArea  the data area.
     * @param info  the plot rendering info.
     * @param plot  the plot.
     * @param domainAxis  the x-axis.
     * @param rangeAxis  the y-axis.
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param item  the item index.
     * @param crosshairState  the crosshair state.
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2, XYItemRendererState state, 
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, 
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, 
            int series, int item, CrosshairState crosshairState, int pass) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[53]++;
        
        double x = dataset.getXValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[54]++;
        double y = dataset.getYValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[55]++;
        double dx = 0.0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[56]++;
        double dy = 0.0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[57]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((dataset instanceof VectorXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[17]++;
            dx = ((VectorXYDataset) dataset).getVectorXValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[58]++;
            dy = ((VectorXYDataset) dataset).getVectorYValue(series, item);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[59]++;

        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[18]++;}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[60]++;
        double xx0 = domainAxis.valueToJava2D(x, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[61]++;
        double yy0 = rangeAxis.valueToJava2D(y, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[62]++;
        double xx1 = domainAxis.valueToJava2D(x + dx, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[63]++;
        double yy1 = rangeAxis.valueToJava2D(y + dy, dataArea, 
                plot.getRangeAxisEdge());
        Line2D line;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[64]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[65]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((orientation.equals(PlotOrientation.HORIZONTAL)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[19]++;
            line = new Line2D.Double(yy0, xx0, yy1, xx1);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[66]++;

        }
        else {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[20]++;
            line = new Line2D.Double(xx0, yy0, xx1, yy1);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[67]++;
        }
        g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[68]++;
        g2.setStroke(getItemStroke(series, item));
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[69]++;
        g2.draw(line);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[70]++;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[71]++;
        
        // calculate the arrow head and draw it...
        double dxx = (xx1 - xx0);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[72]++;
        double dyy = (yy1 - yy0);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[73]++;
        double bx = xx0 + (1.0 - this.baseLength) * dxx;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[74]++;
        double by = yy0 + (1.0 - this.baseLength) * dyy;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[75]++;
        
        double cx = xx0 + (1.0 - this.headLength) * dxx;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[76]++;
        double cy = yy0 + (1.0 - this.headLength) * dyy;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[77]++;
 
        double angle = 0.0;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[78]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((dxx != 0.0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[21]++;
            angle = Math.PI / 2.0 - Math.atan(dyy / dxx);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[79]++;

        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[22]++;}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[80]++;
        double deltaX = 2.0 * Math.cos(angle);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[81]++;
        double deltaY = 2.0 * Math.sin(angle);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[82]++;
        
        double leftx = cx + deltaX;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[83]++;
        double lefty = cy - deltaY;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[84]++;
        double rightx = cx - deltaX;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[85]++;
        double righty = cy + deltaY;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[86]++;
        
        GeneralPath p = new GeneralPath();
        p.moveTo((float) xx1, (float) yy1);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[87]++;
        p.lineTo((float) rightx, (float) righty);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[88]++;
        p.lineTo((float) bx, (float) by);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[89]++;
        p.lineTo((float) leftx, (float) lefty);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[90]++;
        p.closePath();
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[91]++;
        g2.draw(p);
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[92]++;
        
        
    }
    
    /**
     * Tests this <code>VectorRenderer</code> for equality with an arbitrary
     * object.  This method returns <code>true</code> if and only if:
     * <ul>
     * <li><code>obj</code> is an instance of <code>VectorRenderer</code> (not
     *     <code>null</code>);</li>
     * <li><code>obj</code> has the same field values as this 
     *     <code>VectorRenderer</code>;</li>
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[93]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[24]++;}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[94]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj instanceof VectorRenderer) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[26]++;}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[95]++;
        VectorRenderer that = (VectorRenderer) obj;
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[96]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.baseLength != that.baseLength) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[28]++;}
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.statements[97]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.headLength != that.headLength) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x.branches[30]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a clone of this renderer.
     * 
     * @return A clone of this renderer.
     * 
     * @throws CloneNotSupportedException if there is a problem creating the 
     *     clone.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

class CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x ());
  }
    public static long[] statements = new long[98];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.VectorRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$1f39raadayp1kia1xbi2uy2fqmn80x () {
    super("org.jfree.chart.renderer.xy.VectorRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 97; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.VectorRenderer.java");
      for (int i = 1; i <= 97; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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

