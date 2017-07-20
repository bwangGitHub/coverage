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
 * ---------------------------------
 * XYSmoothLineAndShapeRenderer.java
 * ---------------------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  -;
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 14-Jun-2007 : Version 1;
 *
 */

package org.jfree.experimental.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;

/**
 * A line and shape renderer that performs line smoothing.  See
 * http://www.jfree.org/phpBB2/viewtopic.php?t=20671
 * 
 * WARNING: THIS CLASS IS NOT PART OF THE STANDARD JFREECHART API AND IS 
 * SUBJECT TO ALTERATION OR REMOVAL.  DO NOT RELY ON THIS CLASS FOR 
 * PRODUCTION USE.  Please experiment with this code and provide feedback.
 */
public class XYSmoothLineAndShapeRenderer extends XYLineAndShapeRenderer {
  static {
    CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.ping();
  }

   
    protected void drawPrimaryLine(XYItemRendererState state, Graphics2D g2,
            XYPlot plot, XYDataset dataset, int pass, int series, int item,
            ValueAxis domainAxis, ValueAxis rangeAxis, Rectangle2D dataArea) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
           
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((item == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[1]++;
            return;

        } else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[2]++;}
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[2]++;
           
        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[3]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Double.isNaN(x1)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[3]++;
            return;

        } else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[4]++;}
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[5]++;

        double x0 = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[6]++;
        double y0 = dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((Double.isNaN(x0)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[5]++;
            return;

        } else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[6]++;}
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[8]++;

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[9]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[10]++;

        double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[11]++;
        double transY0 = rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[12]++;

        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[13]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;

        // only draw if we have good values
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (128)) == 0 || true) &&
 ((Double.isNaN(transX0)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((Double.isNaN(transY0)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((Double.isNaN(transX1)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(transY1)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[7]++;
            return;

        } else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[8]++;}
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[15]++;
             
        Point2D.Double point0 = new Point2D.Double();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[16]++;
        Point2D.Double point1 = new Point2D.Double();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[17]++;
        Point2D.Double point2 = new Point2D.Double();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[18]++;
        Point2D.Double point3 = new Point2D.Double();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
           
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((item == 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[9]++;
            point0 = null;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[20]++;

        } 
        else {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[10]++;
            point0.x = domainAxis.valueToJava2D(dataset.getXValue(series, 
                    item - 2), dataArea, xAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[21]++;
            point0.y = rangeAxis.valueToJava2D(dataset.getYValue(series, 
                    item - 2), dataArea, yAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[22]++;
        }
           
        point1.x = transX0;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[23]++;
        point1.y = transY0;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[24]++;
           
        point2.x = transX1;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[25]++;
        point2.y = transY1;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[26]++;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
           
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (((item + 1) == dataset.getItemCount(series)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[11]++;
            point3 = null;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[28]++;

        } 
        else {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[12]++;
            point3.x = domainAxis.valueToJava2D(dataset.getXValue(series, 
                    item + 1), dataArea, xAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[29]++;
            point3.y = rangeAxis.valueToJava2D(dataset.getYValue(series, 
                    item + 1), dataArea, yAxisLocation);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[30]++;
        }
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[31]++;

        int steps = ((int) ((point2.x - point1.x) / 0.2) < 30) 
                ? (int) ((point2.x - point1.x) / 0.2) : 30;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[32]++;
           
        Point2D.Double[] points = getBezierCurve(point0, point1, point2, 
                point3, 1, steps);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[33]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
           
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < points.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[1]--;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[2]--;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[3]++;
}
            transX0 = points[i - 1].x;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[34]++;
            transY0 = points[i - 1].y;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[35]++;
            transX1 = points[i].x;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[36]++;
            transY1 = points[i].y;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[37]++;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[38]++;
             
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[13]++;
                state.workingLine.setLine(transY0, transX0, transY1, transX1);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[40]++;

            }
            else {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[14]++;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[41]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[15]++;
                state.workingLine.setLine(transX0, transY0, transX1, transY1);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[42]++;

            } else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[16]++;}
}
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[43]++;
int CodeCoverConditionCoverageHelper_C10;
 
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((state.workingLine.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[17]++;
                drawFirstPassShape(g2, pass, series, item, state.workingLine);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[44]++;

            } else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[18]++;}
        }
    }
         
    protected void drawSecondaryPass(Graphics2D g2, XYPlot plot,
            XYDataset dataset, int pass, int series, int item,
            ValueAxis domainAxis, Rectangle2D dataArea,
            ValueAxis rangeAxis, CrosshairState crosshairState,
            EntityCollection entities) {
        // super.drawSecondaryPass(g2, plot, dataset, pass, series, item, 
        // domainAxis, dataArea, rangeAxis, crosshairState, entities);
    }
     
    /**
     * Updates the control points.
     * 
     * @param point0
     * @param point1
     * @param point2
     * @param point3
     * @param control1
     * @param control2
     * @param smooth
     */
    public static void getControlPoints(Point2D.Double point0, 
            Point2D.Double point1, Point2D.Double point2, 
            Point2D.Double point3, Point2D.Double control1,
            Point2D.Double control2, double smooth) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
         
        // Reference: http://www.antigrain.com/research/bezier_interpolation/
        
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((point0 == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[19]++; point0 = point1;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[46]++;
} else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[20]++;}
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[47]++;
int CodeCoverConditionCoverageHelper_C12; //new Point2D.Double(0, 0);
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((point3 == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[21]++; point3 = point2;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[48]++;
} else {
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.branches[22]++;}
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[49]++; //new Point2D.Double(0, 0);
        
        Point2D.Double c1 = new Point2D.Double(
               (point0.x + point1.x) / 2.0, (point0.y + point1.y) / 2.0);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[50]++;
        Point2D.Double c2 = new Point2D.Double(
               (point1.x + point2.x) / 2.0, (point1.y + point2.y) / 2.0);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[51]++;
        Point2D.Double c3 = new Point2D.Double(
               (point2.x + point3.x) / 2.0, (point2.y + point3.y) / 2.0);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[52]++;
        
        double len1 = point1.distance(point0);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[53]++;
        double len2 = point2.distance(point1);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[54]++;
        double len3 = point3.distance(point2);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[55]++;
        
        double k1 = len1 / (len1 + len2);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[56]++;
        double k2 = len2 / (len2 + len3);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[57]++;
        
        Point2D.Double m1 = new Point2D.Double(
               c1.x + (c2.x - c1.x) * k1, c1.y + (c2.y - c1.y) * k1);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[58]++;
        Point2D.Double m2 = new Point2D.Double(
               c2.x + (c3.x - c2.x) * k2, c2.y + (c3.y - c2.y) * k2);
        
        control1.setLocation(new Point2D.Double(
               m1.x + (c2.x - m1.x) * smooth + point1.x - m1.x,
               m1.y + (c2.y - m1.y) * smooth + point1.y - m1.y));
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[59]++;
        control2.setLocation(new Point2D.Double(
               m2.x + (c2.x - m2.x) * smooth + point2.x - m2.x,
               m2.y + (c2.y - m2.y) * smooth + point2.y - m2.y));
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[60]++;
    }
      
    /**
     * Returns the points for a bezier curve.
     * 
     * @param point0
     * @param point1
     * @param point2
     * @param point3
     * @param smooth
     * @param steps
     * 
     * @return The curve points.
     */
    public static Point2D.Double[] getBezierCurve(Point2D.Double point0,
            Point2D.Double point1, Point2D.Double point2, 
            Point2D.Double point3, double smooth, int steps) {
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[61]++;
        Point2D.Double control1 = new Point2D.Double();
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[62]++;
        Point2D.Double control2 = new Point2D.Double();
        
        getControlPoints(point0, point1, point2, point3, control1, control2, 
                smooth);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[63]++;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[64]++;
        
        Point2D.Double C = new Point2D.Double(
               3 * (control1.x - point1.x), 3 * (control1.y - point1.y));
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[65]++;
        Point2D.Double B = new Point2D.Double(3 * (control2.x - control1.x) 
                - C.x, 3 * (control2.y - control1.y) - C.y);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[66]++;
        Point2D.Double A = new Point2D.Double(point2.x - point1.x - C.x - B.x, 
                point2.y - point1.y - C.y - B.y);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[67]++;

        Point2D.Double[] res = new Point2D.Double[steps + 1];
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[68]++;
        double stepSize = 1.0 / steps;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[69]++;
        double step = stepSize;
      
        res[0] = point1;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[70]++;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[71]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < steps) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[4]--;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[5]--;
  CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.loops[6]++;
}
            res[i] = new Point2D.Double(A.x * Math.pow(step, 3) + B.x 
                    * Math.pow(step, 2) + C.x * step + point1.x, A.y 
                    * Math.pow(step, 3) + B.y * Math.pow(step, 2) + C.y * step 
                    + point1.y);
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[72]++;
            //System.out.println(step + " : " + res[i]);
            step += stepSize;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[73]++;
        }
        res[steps] = point2;
CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1.statements[74]++;
        
        return res;
    }

}

class CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1 ());
  }
    public static long[] statements = new long[75];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.experimental.chart.renderer.xy.XYSmoothLineAndShapeRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,3,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$fnpnd1okyecj360ehl4ya7uq0cyujcqn161jmnl6ie93fhyajc1 () {
    super("org.jfree.experimental.chart.renderer.xy.XYSmoothLineAndShapeRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 74; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.experimental.chart.renderer.xy.XYSmoothLineAndShapeRenderer.java");
      for (int i = 1; i <= 74; i++) {
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
    for (int i = 1; i <= 13; i++) {
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

