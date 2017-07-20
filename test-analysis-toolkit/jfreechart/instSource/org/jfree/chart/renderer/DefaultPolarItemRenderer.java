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
 * -----------------------------
 * DefaultPolarItemRenderer.java
 * -----------------------------
 * (C) Copyright 2004, 2006, 2007, by Solution Engineering, Inc. and 
 *     Contributors.
 *
 * Original Author:  Daniel Bridenbecker, Solution Engineering, Inc.;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Jan-2004 : Version 1, contributed by DB with minor changes by DG (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 04-Oct-2004 : Renamed BooleanUtils --> BooleanUtilities (DG);
 * 20-Apr-2005 : Update for change to LegendItem class (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 04-Aug-2006 : Implemented equals() and clone() (DG);
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 14-Mar-2007 : Fixed clone() method (DG);
 * 04-May-2007 : Fixed lookup for series paint and stroke (DG);
 * 18-May-2007 : Set dataset for LegendItem (DG);
 *
 */

package org.jfree.chart.renderer;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.text.TextUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.BooleanList;
import org.jfree.util.BooleanUtilities;

/**
 * A renderer that can be used with the {@link PolarPlot} class.
 */
public class DefaultPolarItemRenderer extends AbstractRenderer  
                                      implements PolarItemRenderer {
  static {
    CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.ping();
  }

       
    /** The plot that the renderer is assigned to. */
    private PolarPlot plot;

    /** Flags that control whether the renderer fills each series or not. */
    private BooleanList seriesFilled;
   
    /**
     * Creates a new instance of DefaultPolarItemRenderer
     */
    public DefaultPolarItemRenderer() {
        this.seriesFilled = new BooleanList();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[1]++;
    }
   
    /**
     * Set the plot associated with this renderer.
     * 
     * @param plot  the plot.
     * 
     * @see #getPlot()
     */
    public void setPlot(PolarPlot plot) {
        this.plot = plot;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[2]++;
    }

    /**
     * Return the plot associated with this renderer.
     * 
     * @return The plot.
     * 
     * @see #setPlot(PolarPlot)
     */
    public PolarPlot getPlot() {
        return this.plot;
    }

    /** 
     * Returns the drawing supplier from the plot.
     *
     * @return The drawing supplier.
     */
    public DrawingSupplier getDrawingSupplier() {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[3]++;
        DrawingSupplier result = null;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[4]++;
        PolarPlot p = getPlot();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[1]++;
            result = p.getDrawingSupplier();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[6]++;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[2]++;}
        return result;
    }

    /**
     * Returns <code>true</code> if the renderer should fill the specified 
     * series, and <code>false</code> otherwise.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return A boolean.
     */
    public boolean isSeriesFilled(int series) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[7]++;
        boolean result = false;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[8]++;
        Boolean b = this.seriesFilled.getBoolean(series);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((b != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[3]++;
            result = b.booleanValue();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[10]++;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[4]++;}
        return result;
    }

    /**
     * Sets a flag that controls whether or not a series is filled.
     * 
     * @param series  the series index.
     * @param filled  the flag.
     */
    public void setSeriesFilled(int series, boolean filled) {
        this.seriesFilled.setBoolean(series, BooleanUtilities.valueOf(filled));
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[11]++;
    }
    
    /**
     * Plots the data for a given series.
     * 
     * @param g2  the drawing surface.
     * @param dataArea  the data area.
     * @param info  collects plot rendering info.
     * @param plot  the plot.
     * @param dataset  the dataset.
     * @param seriesIndex  the series index.
     */
    public void drawSeries(Graphics2D g2, 
                           Rectangle2D dataArea, 
                           PlotRenderingInfo info,
                           PolarPlot plot,
                           XYDataset dataset,
                           int seriesIndex) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[12]++;
        
        Polygon poly = new Polygon();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[13]++;
        int numPoints = dataset.getItemCount(seriesIndex);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < numPoints) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[1]--;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[2]--;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[3]++;
}
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[15]++;
            double theta = dataset.getXValue(seriesIndex, i);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[16]++;
            double radius = dataset.getYValue(seriesIndex, i);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[17]++;
            Point p = plot.translateValueThetaRadiusToJava2D(theta, radius, 
                    dataArea);
            poly.addPoint(p.x, p.y);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[18]++;
        }
        g2.setPaint(lookupSeriesPaint(seriesIndex));
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[19]++;
        g2.setStroke(lookupSeriesStroke(seriesIndex));
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[20]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isSeriesFilled(seriesIndex)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[5]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[22]++;
            Composite savedComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f));
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[23]++;
            g2.fill(poly);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[24]++;
            g2.setComposite(savedComposite);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[25]++;

        }
        else {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[6]++;
            g2.draw(poly);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[26]++;
        }
    }
    
    /**
     * Draw the angular gridlines - the spokes.
     * 
     * @param g2  the drawing surface.
     * @param plot  the plot.
     * @param ticks  the ticks.
     * @param dataArea  the data area.
     */
    public void drawAngularGridLines(Graphics2D g2, 
                                     PolarPlot plot, 
                                     List ticks,
                                     Rectangle2D dataArea) {
        
        g2.setFont(plot.getAngleLabelFont());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[27]++;
        g2.setStroke(plot.getAngleGridlineStroke());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[28]++;
        g2.setPaint(plot.getAngleGridlinePaint());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[29]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[30]++;
      
        double axisMin = plot.getAxis().getLowerBound();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[31]++;
        double maxRadius = plot.getMaxRadius();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[32]++;

        Point center = plot.translateValueThetaRadiusToJava2D(axisMin, axisMin,
                dataArea);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[33]++;
        Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[4]--;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[5]--;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[6]++;
}
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[35]++;
            NumberTick tick = (NumberTick) iterator.next();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[36]++;
            Point p = plot.translateValueThetaRadiusToJava2D(
                    tick.getNumber().doubleValue(), maxRadius, dataArea);
            g2.setPaint(plot.getAngleGridlinePaint());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[37]++;
            g2.drawLine(center.x, center.y, p.x, p.y);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[38]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((plot.isAngleLabelsVisible()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[7]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[40]++;
                int x = p.x;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[41]++;
                int y = p.y;
                g2.setPaint(plot.getAngleLabelPaint());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[42]++;
                TextUtilities.drawAlignedString(tick.getText(), g2, x, y, 
                        TextAnchor.CENTER);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[43]++;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[8]++;}
        }
     }

    /**
     * Draw the radial gridlines - the rings.
     * 
     * @param g2  the drawing surface.
     * @param plot  the plot.
     * @param radialAxis  the radial axis.
     * @param ticks  the ticks.
     * @param dataArea  the data area.
     */
    public void drawRadialGridLines(Graphics2D g2, 
                                    PolarPlot plot,
                                    ValueAxis radialAxis,
                                    List ticks,
                                    Rectangle2D dataArea) {
        
        g2.setFont(radialAxis.getTickLabelFont());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[44]++;
        g2.setPaint(plot.getRadiusGridlinePaint());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[45]++;
        g2.setStroke(plot.getRadiusGridlineStroke());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[46]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[47]++;

        double axisMin = radialAxis.getLowerBound();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[48]++;
        Point center = plot.translateValueThetaRadiusToJava2D(axisMin, axisMin,
                dataArea);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[49]++;
        
        Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[50]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
        while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[7]--;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[8]--;
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.loops[9]++;
}
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[51]++;
            NumberTick tick = (NumberTick) iterator.next();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[52]++;
            Point p = plot.translateValueThetaRadiusToJava2D(90.0, 
                    tick.getNumber().doubleValue(), dataArea);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[53]++;
            int r = p.x - center.x;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[54]++;
            int upperLeftX = center.x - r;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[55]++;
            int upperLeftY = center.y - r;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[56]++;
            int d = 2 * r;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[57]++;
            Ellipse2D ring = new Ellipse2D.Double(upperLeftX, upperLeftY, d, d);
            g2.setPaint(plot.getRadiusGridlinePaint());
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[58]++;
            g2.draw(ring);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[59]++;
        }
    }

    /**
     * Return the legend for the given series.
     * 
     * @param series  the series index.
     * 
     * @return The legend item.
     */
    public LegendItem getLegendItem(int series) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[60]++;
        LegendItem result = null;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[61]++;
        PolarPlot polarPlot = getPlot();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[62]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((polarPlot != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[9]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[63]++;
            XYDataset dataset = polarPlot.getDataset();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[64]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[11]++;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[65]++;
                String label = dataset.getSeriesKey(series).toString();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[66]++;
                String description = label;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[67]++;
                Shape shape = lookupSeriesShape(series);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[68]++;
                Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[69]++;
                Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[70]++;
                Stroke outlineStroke = lookupSeriesOutlineStroke(series);
                result = new LegendItem(label, description, null, null, 
                        shape, paint, outlineStroke, outlinePaint);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[71]++;
                result.setDataset(dataset);
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[72]++;

            } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[10]++;}
        return result;
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> not permitted).
     * 
     * @return <code>true</code> if this renderer is equal to <code>obj</code>,
     *     and <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[73]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[14]++;}
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[74]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((obj instanceof DefaultPolarItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[16]++;}
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[75]++;
        DefaultPolarItemRenderer that = (DefaultPolarItemRenderer) obj;
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[76]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.seriesFilled.equals(that.seriesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.branches[18]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[77]++;
        DefaultPolarItemRenderer clone 
                = (DefaultPolarItemRenderer) super.clone();
        clone.seriesFilled = (BooleanList) this.seriesFilled.clone();
CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1.statements[78]++;
        return clone;
    }

}

class CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1 ());
  }
    public static long[] statements = new long[79];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.DefaultPolarItemRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$656f9cjx0e7nhzxn9mfaxhu4hvlzunlwryckmjns1kns1 () {
    super("org.jfree.chart.renderer.DefaultPolarItemRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 78; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.DefaultPolarItemRenderer.java");
      for (int i = 1; i <= 78; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

