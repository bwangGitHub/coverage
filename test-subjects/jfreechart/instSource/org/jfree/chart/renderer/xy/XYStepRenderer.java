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
 * XYStepRenderer.java
 * -------------------
 * (C) Copyright 2002-2007, by Roger Studner and Contributors.
 *
 * Original Author:  Roger Studner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Matthias Rose;
 *                   Gerald Struck (fix for bug 1569094);
 *
 * Changes
 * -------
 * 13-May-2002 : Version 1, contributed by Roger Studner (DG);
 * 25-Jun-2002 : Updated import statements (DG);
 * 22-Jul-2002 : Added check for null data items (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 28-Oct-2003 : Added tooltips, code contributed by Matthias Rose 
 *               (RFE 824857) (DG);
 * 10-Feb-2004 : Removed working line (use line from state object instead) (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed 
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 19-Jan-2005 : Now accesses only primitives from dataset (DG);
 * 15-Mar-2005 : Fix silly bug in drawItem() method (DG);
 * 19-Sep-2005 : Extend XYLineAndShapeRenderer (fixes legend shapes), added 
 *               support for series visibility, and use getDefaultEntityRadius()
 *               for entity hotspot size (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 15-Jun-2006 : Added basic support for item labels (DG);
 * 11-Oct-2006 : Fixed rendering with horizontal orientation (see bug 1569094),
 *               thanks to Gerald Struck (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * Line/Step item renderer for an {@link XYPlot}.  This class draws lines 
 * between data points, only allowing horizontal or vertical lines (steps).
 */
public class XYStepRenderer extends XYLineAndShapeRenderer 
                            implements XYItemRenderer, 
                                       Cloneable,
                                       PublicCloneable,
                                       Serializable {
  static {
    CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8918141928884796108L;
  static {
    CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[1]++;
  }
    
    /**
     * Constructs a new renderer with no tooltip or URL generation.
     */
    public XYStepRenderer() {
        this(null, null);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[2]++;
    }

    /**
     * Constructs a new renderer with the specified tool tip and URL 
     * generators.
     *
     * @param toolTipGenerator  the item label generator (<code>null</code> 
     *     permitted).
     * @param urlGenerator  the URL generator (<code>null</code> permitted).
     */
    public XYStepRenderer(XYToolTipGenerator toolTipGenerator,
                          XYURLGenerator urlGenerator) {
        super();
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[3]++;
        setBaseToolTipGenerator(toolTipGenerator);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[4]++;
        setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[5]++;
        setShapesVisible(false);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[6]++;
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the vertical axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index (ignored here).
     */
    public void drawItem(Graphics2D g2, 
                         XYItemRendererState state,
                         Rectangle2D dataArea, 
                         PlotRenderingInfo info,
                         XYPlot plot, 
                         ValueAxis domainAxis, 
                         ValueAxis rangeAxis,
                         XYDataset dataset, 
                         int series, 
                         int item,
                         CrosshairState crosshairState, 
                         int pass) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[1]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[2]++;}
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[8]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[9]++;
        
        Paint seriesPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[10]++;
        Stroke seriesStroke = getItemStroke(series, item);
        g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[11]++;
        g2.setStroke(seriesStroke);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[12]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[13]++;

        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[14]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[3]++;
            return;

        } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[4]++;}
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[16]++;

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[17]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[18]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[19]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[5]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[21]++;
            // get the previous data point...
            double x0 = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[22]++;
            double y0 = dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[7]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[24]++;
                double transX0 = domainAxis.valueToJava2D(x0, dataArea, 
                        xAxisLocation);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[25]++;
                double transY0 = rangeAxis.valueToJava2D(y0, dataArea, 
                        yAxisLocation);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[26]++;

                Line2D line = state.workingLine;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[9]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((transY0 == transY1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[11]++; //this represents the situation 
                                              // for drawing a horizontal bar.
                        line.setLine(transY0, transX0, transY1, transX1);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[29]++;
                        g2.draw(line);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[30]++;

                    }
                    else {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[12]++;  //this handles the need to perform a 'step'.
                        line.setLine(transY0, transX0, transY0, transX1);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[31]++;
                        g2.draw(line);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[32]++;
                        line.setLine(transY0, transX1, transY1, transX1);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[33]++;
                        g2.draw(line);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[34]++;
                    }

                }
                else {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[10]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[35]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[13]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((transY0 == transY1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[15]++; // this represents the situation 
                                              // for drawing a horizontal bar.
                        line.setLine(transX0, transY0, transX1, transY1);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[37]++;
                        g2.draw(line);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[38]++;

                    }
                    else {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[16]++;  //this handles the need to perform a 'step'.
                        line.setLine(transX0, transY0, transX1, transY0);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[39]++;
                        g2.draw(line);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[40]++;
                        line.setLine(transX1, transY0, transX1, transY1);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[41]++;
                        g2.draw(line);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[42]++;
                    }

                } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[14]++;}
}


            } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[6]++;}
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;

        // draw the item label if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[17]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[44]++;
            double xx = transX1;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[45]++;
            double yy = transY1;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[19]++;
                xx = transY1;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[47]++;
                yy = transX1;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[48]++;

            } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[20]++;}          
            drawItemLabel(g2, orientation, dataset, series, item, xx, yy, 
                    (y1 < 0.0));
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[49]++;

        } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[18]++;}
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[50]++;

        int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[51]++;
        int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
        updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, 
                rangeAxisIndex, transX1, transY1, orientation);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[52]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
        
        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[21]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[54]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[55]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[23]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[56]++;
                int r = getDefaultEntityRadius();
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[57]++;
                Shape shape = orientation == PlotOrientation.VERTICAL
                    ? new Rectangle2D.Double(transX1 - r, transY1 - r, 2 * r, 
                            2 * r)
                    : new Rectangle2D.Double(transY1 - r, transX1 - r, 2 * r, 
                            2 * r);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[58]++;
int CodeCoverConditionCoverageHelper_C13;           
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((shape != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[25]++;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[59]++;
                    String tip = null;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[60]++;
                    XYToolTipGenerator generator 
                        = getToolTipGenerator(series, item);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[61]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[27]++;
                        tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[62]++;

                    } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[28]++;}
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[63]++;
                    String url = null;
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[64]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[29]++;
                        url = getURLGenerator().generateURL(dataset, series, 
                                item);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[65]++;

                    } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[30]++;}
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[66]++;
                    XYItemEntity entity = new XYItemEntity(shape, dataset, 
                            series, item, tip, url);
                    entities.add(entity);
CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.statements[67]++;

                } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[26]++;}

            } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp.branches[22]++;}
    }

    /**
     * Returns a clone of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

class CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYStepRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1g8ufwp5b3ayu6ep4zwv3f6nk7k3mp () {
    super("org.jfree.chart.renderer.xy.XYStepRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYStepRenderer.java");
      for (int i = 1; i <= 67; i++) {
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
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

