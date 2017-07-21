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
 * XYBubbleRenderer.java
 * ---------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 28-Jan-2003 : Version 1 (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 10-Feb-2004 : Small change to drawItem() method to make cut-and-paste 
 *               overriding easier (DG);
 * 15-Jul-2004 : Switched getZ() and getZValue() methods (DG);
 * 19-Jan-2005 : Now accesses only primitives from dataset (DG);
 * 28-Feb-2005 : Modify renderer to use circles in legend (DG);
 * 17-Mar-2005 : Fixed bug in bubble bounds calculation (DG);
 * 20-Apr-2005 : Use generators for legend tooltips and URLs (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Dec-2005 : Added support for item labels (bug 1373371) (DG);
 * 20-Jan-2006 : Check flag for drawing item labels (DG);
 * 21-Sep-2006 : Respect the outline paint and stroke settings (DG);
 * 24-Jan-2007 : Added new equals() override (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 13-Jun-2007 : Fixed seriesVisibility bug (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that draws a circle at each data point with a diameter that is
 * determined by the z-value in the dataset (the renderer requires the dataset 
 * to be an instance of {@link XYZDataset}.
 */
public class XYBubbleRenderer extends AbstractXYItemRenderer 
                              implements XYItemRenderer, 
                                         Cloneable,
                                         PublicCloneable,
                                         Serializable {
  static {
    CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.ping();
  }


    /** For serialization. */
    public static final long serialVersionUID = -5221991598674249125L;
  static {
    CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[1]++;
  }
    
    /** 
     * A constant to specify that the bubbles drawn by this renderer should be 
     * scaled on both axes (see {@link #XYBubbleRenderer(int)}). 
     */
    public static final int SCALE_ON_BOTH_AXES = 0;
  static {
    CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[2]++;
  }

    /** 
     * A constant to specify that the bubbles drawn by this renderer should be 
     * scaled on the domain axis (see {@link #XYBubbleRenderer(int)}). 
     */
    public static final int SCALE_ON_DOMAIN_AXIS = 1;
  static {
    CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[3]++;
  }

    /** 
     * A constant to specify that the bubbles drawn by this renderer should be 
     * scaled on the range axis (see {@link #XYBubbleRenderer(int)}). 
     */
    public static final int SCALE_ON_RANGE_AXIS = 2;
  static {
    CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[4]++;
  }

    /** Controls how the width and height of the bubble are scaled. */
    private int scaleType;

    /**
     * Constructs a new renderer.
     */
    public XYBubbleRenderer() {
        this(SCALE_ON_BOTH_AXES);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[5]++; 
    }

    /**
     * Constructs a new renderer with the specified type of scaling. 
     *
     * @param scaleType  the type of scaling (must be one of: 
     *        {@link #SCALE_ON_BOTH_AXES}, {@link #SCALE_ON_DOMAIN_AXIS}, 
     *        {@link #SCALE_ON_RANGE_AXIS}).
     */
    public XYBubbleRenderer(int scaleType) {
        super();
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[6]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((scaleType < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scaleType > 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[1]++;
            throw new IllegalArgumentException("Invalid 'scaleType'.");

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[2]++;}
        this.scaleType = scaleType;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[8]++;
    }

    /**
     * Returns the scale type that was set when the renderer was constructed.
     *
     * @return The scale type (one of: {@link #SCALE_ON_BOTH_AXES}, 
     *         {@link #SCALE_ON_DOMAIN_AXIS}, {@link #SCALE_ON_RANGE_AXIS}).
     */
    public int getScaleType() {
        return this.scaleType;
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
     * @param domainAxis  the domain (horizontal) axis.
     * @param rangeAxis  the range (vertical) axis.
     * @param dataset  the dataset (an {@link XYZDataset} is expected).
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, 
            int series, int item, CrosshairState crosshairState, int pass) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

        // return straight away if the item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[3]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[4]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[10]++;
        
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[11]++;
        
        // get the data point...
        double x = dataset.getXValue(series, item);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[12]++;
        double y = dataset.getYValue(series, item);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[13]++;
        double z = Double.NaN;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof XYZDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[5]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[15]++;
            XYZDataset xyzData = (XYZDataset) dataset;
            z = xyzData.getZValue(series, item);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[16]++;

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[6]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(z)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[7]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[18]++;
            RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[19]++;
            RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[20]++;
            double transX = domainAxis.valueToJava2D(x, dataArea, 
                    domainAxisLocation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[21]++;
            double transY = rangeAxis.valueToJava2D(y, dataArea, 
                    rangeAxisLocation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[22]++;

            double transDomain = 0.0;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[23]++;
            double transRange = 0.0;
            double zero;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[24]++;

            switch(getScaleType()) {
                case SCALE_ON_DOMAIN_AXIS:
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[9]++;
                    zero = domainAxis.valueToJava2D(0.0, dataArea, 
                            domainAxisLocation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[25]++;
                    transDomain = domainAxis.valueToJava2D(z, dataArea, 
                            domainAxisLocation) - zero;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[26]++;
                    transRange = transDomain;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[27]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[28]++;
                    break;
                case SCALE_ON_RANGE_AXIS:
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[10]++;
                    zero = rangeAxis.valueToJava2D(0.0, dataArea, 
                            rangeAxisLocation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[29]++;
                    transRange = zero - rangeAxis.valueToJava2D(z, dataArea, 
                            rangeAxisLocation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[30]++;
                    transDomain = transRange;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[31]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[32]++;
                    break;
                default:
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[11]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[33]++;
                    double zero1 = domainAxis.valueToJava2D(0.0, dataArea, 
                            domainAxisLocation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[34]++;
                    double zero2 = rangeAxis.valueToJava2D(0.0, dataArea, 
                            rangeAxisLocation);
                    transDomain = domainAxis.valueToJava2D(z, dataArea, 
                            domainAxisLocation) - zero1;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[35]++;
                    transRange = zero2 - rangeAxis.valueToJava2D(z, dataArea, 
                            rangeAxisLocation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[36]++;
            }
            transDomain = Math.abs(transDomain);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[37]++;
            transRange = Math.abs(transRange);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[38]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[39]++;
            Ellipse2D circle = null;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[40]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[12]++;
                circle = new Ellipse2D.Double(transX - transDomain / 2.0, 
                        transY - transRange / 2.0, transDomain, transRange);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[41]++;

            }
            else {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[13]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[42]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[14]++;
                circle = new Ellipse2D.Double(transY - transRange / 2.0, 
                        transX - transDomain / 2.0, transRange, transDomain);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[43]++;

            } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[15]++;}
}
            g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[44]++;
            g2.fill(circle);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[45]++;
            g2.setStroke(getItemOutlineStroke(series, item));
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[46]++;
            g2.setPaint(getItemOutlinePaint(series, item));
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[47]++;
            g2.draw(circle);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[48]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[49]++;
int CodeCoverConditionCoverageHelper_C7;

            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[16]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[50]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[18]++;
                    drawItemLabel(g2, orientation, dataset, series, item, 
                            transX, transY, false);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[51]++;

                }
                else {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[19]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[52]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[20]++;
                    drawItemLabel(g2, orientation, dataset, series, item, 
                            transY, transX, false);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[53]++;
                
                } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[21]++;}
}

            } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[17]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[54]++;
            
            // add an entity if this info is being collected
            EntityCollection entities = null;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[22]++;
                entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[56]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((circle.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[24]++;
                    addEntity(entities, circle, dataset, series, item, 
                            circle.getCenterX(), circle.getCenterY());
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[58]++;

                } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[25]++;}

            } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[23]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[59]++;

            int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[60]++;
            int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
            updateCrosshairValues(crosshairState, x, y, domainAxisIndex, 
                    rangeAxisIndex, transX, transY, orientation);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[61]++;

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[8]++;}

    }

    /**
     * Returns a legend item for the specified series.  The default method
     * is overridden so that the legend displays circles for all series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return A legend item for the series.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[62]++;
        LegendItem result = null;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[63]++;
        XYPlot plot = getPlot();
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[64]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[26]++;
            return null;

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[27]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[65]++;
           
        XYDataset dataset = plot.getDataset(datasetIndex);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[66]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[28]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((getItemVisible(series, 0)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[30]++;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[68]++;
                String label = getLegendItemLabelGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[69]++;
                String description = label;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[70]++;
                String toolTipText = null;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[71]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[32]++;
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[72]++;

                } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[33]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[73]++;
                String urlText = null;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[34]++;
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[75]++;

                } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[35]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[76]++;
                Shape shape = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[77]++;
                Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[78]++;
                Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[79]++;
                Stroke outlineStroke = lookupSeriesOutlineStroke(series);
                result = new LegendItem(label, description, toolTipText, 
                        urlText, shape, paint, outlineStroke, outlinePaint);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[80]++;
                result.setDataset(dataset);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[81]++;
                result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[82]++;
                result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[83]++;
                result.setSeriesIndex(series);
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[84]++;

            } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[31]++;}

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[29]++;}
        return result;
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[85]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[36]++;
            return true;

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[37]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[86]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj instanceof XYBubbleRenderer) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[38]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[39]++;}
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[87]++;
        XYBubbleRenderer that = (XYBubbleRenderer) obj;
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.statements[88]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.scaleType != that.scaleType) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[40]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl.branches[41]++;}
        return super.equals(obj);
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

class CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl ());
  }
    public static long[] statements = new long[89];
    public static long[] branches = new long[42];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYBubbleRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$21dy4vycso3bmgjkb1pxc49tidg31arfl () {
    super("org.jfree.chart.renderer.xy.XYBubbleRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 88; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 41; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYBubbleRenderer.java");
      for (int i = 1; i <= 88; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 41; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

