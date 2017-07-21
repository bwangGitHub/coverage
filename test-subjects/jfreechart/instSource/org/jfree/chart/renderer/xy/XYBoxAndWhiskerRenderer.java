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
 * ----------------------------
 * XYBoxAndWhiskerRenderer.java
 * ----------------------------
 * (C) Copyright 2003, 2004, 2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning.  Based on code in the
 *               CandlestickRenderer class.  Additional modifications by David 
 *               Gilbert to make the code work with 0.9.10 changes (DG);
 * 08-Aug-2003 : Updated some of the Javadoc
 *               Allowed BoxAndwhiskerDataset Average value to be null - the 
 *               average value is an AIMS requirement
 *               Allow the outlier and farout coefficients to be set - though 
 *               at the moment this only affects the calculation of farouts.
 *               Added artifactPaint variable and setter/getter
 * 12-Aug-2003   Rewrote code to sort out and process outliers to take 
 *               advantage of changes in DefaultBoxAndWhiskerDataset
 *               Added a limit of 10% for width of box should no width be 
 *               specified...maybe this should be setable???
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 23-Apr-2004 : Added fillBox attribute, extended equals() method and fixed 
 *               serialization issue (DG);
 * 29-Apr-2004 : Fixed problem with drawing upper and lower shadows - bug id 
 *               944011 (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 01-Oct-2004 : Renamed 'paint' --> 'boxPaint' to avoid conflict with 
 *               inherited attribute (DG);
 * 10-Jun-2005 : Updated equals() to handle GradientPaint (DG);
 * 06-Oct-2005 : Removed setPaint() call in drawItem(), it is causing a 
 *               loop (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 05-Feb-2007 : Added event notifications and fixed drawing for horizontal 
 *               plot orientation (DG);
 * 13-Jun-2007 : Replaced deprecated method call (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.BoxAndWhiskerXYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.Outlier;
import org.jfree.chart.renderer.OutlierList;
import org.jfree.chart.renderer.OutlierListCollection;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that draws box-and-whisker items on an {@link XYPlot}.  This 
 * renderer requires a {@link BoxAndWhiskerXYDataset}).
 * <P>
 * This renderer does not include any code to calculate the crosshair point.
 */
public class XYBoxAndWhiskerRenderer extends AbstractXYItemRenderer 
                                     implements XYItemRenderer, 
                                                Cloneable,
                                                PublicCloneable,
                                                Serializable {
  static {
    CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8020170108532232324L;
  static {
    CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[1]++;
  }
    
    /** The box width. */
    private double boxWidth;

    /** The paint used to fill the box. */
    private transient Paint boxPaint;

    /** A flag that controls whether or not the box is filled. */
    private boolean fillBox;
    
    /** 
     * The paint used to draw various artifacts such as outliers, farout 
     * symbol, average ellipse and median line. 
     */
    private transient Paint artifactPaint = Color.black;
  {
    CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[2]++;
  }

    /**
     * Creates a new renderer for box and whisker charts.
     */
    public XYBoxAndWhiskerRenderer() {
        this(-1.0);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[3]++;
    }

    /**
     * Creates a new renderer for box and whisker charts.
     * <P>
     * Use -1 for the box width if you prefer the width to be calculated 
     * automatically.
     *
     * @param boxWidth  the box width.
     */
    public XYBoxAndWhiskerRenderer(double boxWidth) {
        super();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[4]++;
        this.boxWidth = boxWidth;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[5]++;
        this.boxPaint = Color.green;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[6]++;
        this.fillBox = true;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[7]++;
        setBaseToolTipGenerator(new BoxAndWhiskerXYToolTipGenerator());
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[8]++;
    }

    /**
     * Returns the width of each box.
     *
     * @return The box width.
     * 
     * @see #setBoxWidth(double)
     */
    public double getBoxWidth() {
        return this.boxWidth;
    }

    /**
     * Sets the box width and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * <P>
     * If you set the width to a negative value, the renderer will calculate
     * the box width automatically based on the space available on the chart.
     *
     * @param width  the width.
     * 
     * @see #getBoxWidth()
     */
    public void setBoxWidth(double width) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((width != this.boxWidth) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[1]++;
            this.boxWidth = width;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[10]++;
            fireChangeEvent();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[11]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[2]++;}
    }

    /**
     * Returns the paint used to fill boxes.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setBoxPaint(Paint)
     */
    public Paint getBoxPaint() {
        return this.boxPaint;
    }

    /**
     * Sets the paint used to fill boxes and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getBoxPaint()
     */
    public void setBoxPaint(Paint paint) {
        this.boxPaint = paint;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[12]++;
        fireChangeEvent();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[13]++;
    }
    
    /**
     * Returns the flag that controls whether or not the box is filled.
     * 
     * @return A boolean.
     * 
     * @see #setFillBox(boolean)
     */
    public boolean getFillBox() {
        return this.fillBox;   
    }
    
    /**
     * Sets the flag that controls whether or not the box is filled and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #setFillBox(boolean)
     */
    public void setFillBox(boolean flag) {
        this.fillBox = flag;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[14]++;
        fireChangeEvent();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[15]++;
    }

    /**
     * Returns the paint used to paint the various artifacts such as outliers, 
     * farout symbol, median line and the averages ellipse.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setArtifactPaint(Paint)
     */
    public Paint getArtifactPaint() {
        return this.artifactPaint;
    }

    /**
     * Sets the paint used to paint the various artifacts such as outliers, 
     * farout symbol, median line and the averages ellipse, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getArtifactPaint()
     */
    public void setArtifactPaint(Paint paint) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[4]++;}
        this.artifactPaint = paint;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[17]++;
        fireChangeEvent();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[18]++;
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects info about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
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
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[19]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[5]++;
            drawHorizontalItem(g2, dataArea, info, plot, domainAxis, rangeAxis,
                    dataset, series, item, crosshairState, pass);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[21]++;

        }
        else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[6]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[22]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[7]++;
            drawVerticalItem(g2, dataArea, info, plot, domainAxis, rangeAxis,
                    dataset, series, item, crosshairState, pass);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[23]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[8]++;}
}

    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects info about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawHorizontalItem(Graphics2D g2, 
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
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[24]++;

        // setup for collecting optional entity info...
        EntityCollection entities = null;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[9]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[26]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[10]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[27]++;

        BoxAndWhiskerXYDataset boxAndWhiskerData 
                = (BoxAndWhiskerXYDataset) dataset;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[28]++;

        Number x = boxAndWhiskerData.getX(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[29]++;
        Number yMax = boxAndWhiskerData.getMaxRegularValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[30]++;
        Number yMin = boxAndWhiskerData.getMinRegularValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[31]++;
        Number yMedian = boxAndWhiskerData.getMedianValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[32]++;
        Number yAverage = boxAndWhiskerData.getMeanValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[33]++;
        Number yQ1Median = boxAndWhiskerData.getQ1Value(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[34]++;
        Number yQ3Median = boxAndWhiskerData.getQ3Value(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[35]++;
        
        double xx = domainAxis.valueToJava2D(x.doubleValue(), dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[36]++;

        RectangleEdge location = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[37]++;
        double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, 
                location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[38]++;
        double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, 
                location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[39]++;
        double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), 
                dataArea, location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[40]++;
        double yyAverage = 0.0;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((yAverage != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[11]++;
            yyAverage = rangeAxis.valueToJava2D(yAverage.doubleValue(), 
                    dataArea, location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[42]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[12]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[43]++;
        double yyQ1Median = rangeAxis.valueToJava2D(yQ1Median.doubleValue(), 
                dataArea, location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[44]++;
        double yyQ3Median = rangeAxis.valueToJava2D(yQ3Median.doubleValue(), 
                dataArea, location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[45]++;
        
        double exactBoxWidth = getBoxWidth();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[46]++;
        double width = exactBoxWidth;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[47]++;
        double dataAreaX = dataArea.getHeight();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[48]++;
        double maxBoxPercent = 0.1;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[49]++;
        double maxBoxWidth = dataAreaX * maxBoxPercent;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[50]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((exactBoxWidth <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[13]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[51]++;
            int itemCount = boxAndWhiskerData.getItemCount(series);
            exactBoxWidth = dataAreaX / itemCount * 4.5 / 7;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[52]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[53]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((exactBoxWidth < 3) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[15]++;
                width = 3;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[54]++;

            }
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[16]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[55]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((exactBoxWidth > maxBoxWidth) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[17]++;
                width = maxBoxWidth;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[56]++;

            }
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[18]++;
                width = exactBoxWidth;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[57]++;
            }
}

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[14]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[58]++;

        Paint p = getBoxPaint();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[19]++;
            g2.setPaint(p);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[60]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[20]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[61]++;
        Stroke s = getItemStroke(series, item);
        g2.setStroke(s);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[62]++;

        // draw the upper shadow
        g2.draw(new Line2D.Double(yyMax, xx, yyQ3Median, xx));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[63]++;
        g2.draw(new Line2D.Double(yyMax, xx - width / 2, yyMax, 
                xx + width / 2));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[64]++;

        // draw the lower shadow
        g2.draw(new Line2D.Double(yyMin, xx, yyQ1Median, xx));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[65]++;
        g2.draw(new Line2D.Double(yyMin, xx - width / 2, yyMin, 
                xx + width / 2));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[66]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[67]++;

        // draw the body
        Shape box = null;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[68]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((yyQ1Median < yyQ3Median) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[21]++;
            box = new Rectangle2D.Double(yyQ1Median, xx - width / 2, 
                    yyQ3Median - yyQ1Median, width);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[69]++;

        }
        else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[22]++;
            box = new Rectangle2D.Double(yyQ3Median, xx - width / 2, 
                    yyQ1Median - yyQ3Median, width);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[70]++;
        }
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[71]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((getBoxPaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[23]++;
            g2.setPaint(getBoxPaint());
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[72]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[24]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[73]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.fillBox) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[25]++;
            g2.fill(box);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[74]++;
   
        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[26]++;}
        g2.draw(box);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[75]++;

        // draw median
        g2.setPaint(getArtifactPaint());
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[76]++;
        g2.draw(new Line2D.Double(yyMedian, 
                xx - width / 2, yyMedian, xx + width / 2));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[77]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[78]++;
int CodeCoverConditionCoverageHelper_C14;
        
        // draw average - SPECIAL AIMS REQUIREMENT
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((yAverage != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[27]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[79]++;
            double aRadius = width / 4;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[80]++;
            Ellipse2D.Double avgEllipse = new Ellipse2D.Double(
                    yyAverage - aRadius, xx - aRadius, aRadius * 2, 
                    aRadius * 2);
            g2.fill(avgEllipse);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[81]++;
            g2.draw(avgEllipse);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[82]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[28]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[83]++;
int CodeCoverConditionCoverageHelper_C15;
        
        // FIXME: draw outliers
        
        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((box.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[29]++;
            addEntity(entities, box, dataset, series, item, yyAverage, xx);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[84]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[30]++;}

    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects info about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawVerticalItem(Graphics2D g2, 
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
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[85]++;

        // setup for collecting optional entity info...
        EntityCollection entities = null;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[86]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[31]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[87]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[32]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[88]++;

        BoxAndWhiskerXYDataset boxAndWhiskerData 
            = (BoxAndWhiskerXYDataset) dataset;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[89]++;

        Number x = boxAndWhiskerData.getX(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[90]++;
        Number yMax = boxAndWhiskerData.getMaxRegularValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[91]++;
        Number yMin = boxAndWhiskerData.getMinRegularValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[92]++;
        Number yMedian = boxAndWhiskerData.getMedianValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[93]++;
        Number yAverage = boxAndWhiskerData.getMeanValue(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[94]++;
        Number yQ1Median = boxAndWhiskerData.getQ1Value(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[95]++;
        Number yQ3Median = boxAndWhiskerData.getQ3Value(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[96]++;
        List yOutliers = boxAndWhiskerData.getOutliers(series, item);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[97]++;

        double xx = domainAxis.valueToJava2D(x.doubleValue(), dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[98]++;

        RectangleEdge location = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[99]++;
        double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, 
                location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[100]++;
        double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, 
                location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[101]++;
        double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), 
                dataArea, location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[102]++;
        double yyAverage = 0.0;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[103]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((yAverage != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[33]++;
            yyAverage = rangeAxis.valueToJava2D(yAverage.doubleValue(), 
                    dataArea, location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[104]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[34]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[105]++;
        double yyQ1Median = rangeAxis.valueToJava2D(yQ1Median.doubleValue(), 
                dataArea, location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[106]++;
        double yyQ3Median = rangeAxis.valueToJava2D(yQ3Median.doubleValue(), 
                dataArea, location);
        double yyOutlier;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[107]++;


        double exactBoxWidth = getBoxWidth();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[108]++;
        double width = exactBoxWidth;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[109]++;
        double dataAreaX = dataArea.getMaxX() - dataArea.getMinX();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[110]++;
        double maxBoxPercent = 0.1;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[111]++;
        double maxBoxWidth = dataAreaX * maxBoxPercent;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[112]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((exactBoxWidth <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[35]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[113]++;
            int itemCount = boxAndWhiskerData.getItemCount(series);
            exactBoxWidth = dataAreaX / itemCount * 4.5 / 7;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[114]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[115]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((exactBoxWidth < 3) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[37]++;
                width = 3;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[116]++;

            } 
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[38]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[117]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((exactBoxWidth > maxBoxWidth) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[39]++;
                width = maxBoxWidth;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[118]++;

            } 
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[40]++;
                width = exactBoxWidth;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[119]++;
            }
}

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[36]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[120]++;

        Paint p = getBoxPaint();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[121]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[41]++;
            g2.setPaint(p);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[122]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[42]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[123]++;
        Stroke s = getItemStroke(series, item);

        g2.setStroke(s);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[124]++;

        // draw the upper shadow
        g2.draw(new Line2D.Double(xx, yyMax, xx, yyQ3Median));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[125]++;
        g2.draw(new Line2D.Double(xx - width / 2, yyMax, xx + width / 2, 
                yyMax));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[126]++;

        // draw the lower shadow
        g2.draw(new Line2D.Double(xx, yyMin, xx, yyQ1Median));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[127]++;
        g2.draw(new Line2D.Double(xx - width / 2, yyMin, xx + width / 2, 
                yyMin));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[128]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[129]++;
        
        // draw the body
        Shape box = null;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[130]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((yyQ1Median > yyQ3Median) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[43]++;
            box = new Rectangle2D.Double(xx - width / 2, yyQ3Median, width, 
                    yyQ1Median - yyQ3Median);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[131]++;

        }
        else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[44]++;
            box = new Rectangle2D.Double(xx - width / 2, yyQ1Median, width, 
                    yyQ3Median - yyQ1Median);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[132]++;
        }
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[133]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.fillBox) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[45]++;
            g2.fill(box);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[134]++;
   
        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[46]++;}
        g2.draw(box);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[135]++;

        // draw median
        g2.setPaint(getArtifactPaint());
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[136]++;
        g2.draw(new Line2D.Double(xx - width / 2, yyMedian, xx + width / 2, 
                yyMedian));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[137]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[138]++;

        double aRadius = 0;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[139]++;                 // average radius
        double oRadius = width / 3;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[140]++;
int CodeCoverConditionCoverageHelper_C24;    // outlier radius

        // draw average - SPECIAL AIMS REQUIREMENT
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((yAverage != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[47]++;
            aRadius = width / 4;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[141]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[142]++;
            Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xx - aRadius, 
                    yyAverage - aRadius, aRadius * 2, aRadius * 2);
            g2.fill(avgEllipse);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[143]++;
            g2.draw(avgEllipse);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[144]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[48]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[145]++;

        List outliers = new ArrayList();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[146]++;
        OutlierListCollection outlierListCollection 
                = new OutlierListCollection();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[147]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[1]++;


int CodeCoverConditionCoverageHelper_C25;

        /* From outlier array sort out which are outliers and put these into 
         * an arraylist. If there are any farouts, set the flag on the 
         * OutlierListCollection
         */

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i < yOutliers.size()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[1]--;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[2]--;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[3]++;
}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[148]++;
            double outlier = ((Number) yOutliers.get(i)).doubleValue();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[149]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((outlier > boxAndWhiskerData.getMaxOutlier(series, 
                    item).doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[49]++;
                outlierListCollection.setHighFarOut(true);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[150]++;

            } 
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[50]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[151]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((outlier < boxAndWhiskerData.getMinOutlier(series, 
                    item).doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[51]++;
                outlierListCollection.setLowFarOut(true);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[152]++;

            } 
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[52]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[153]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((outlier > boxAndWhiskerData.getMaxRegularValue(series, 
                    item).doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[53]++;
                yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, 
                        location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[154]++;
                outliers.add(new Outlier(xx, yyOutlier, oRadius));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[155]++;

            }
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[54]++;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[156]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((outlier < boxAndWhiskerData.getMinRegularValue(series, 
                    item).doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[55]++;
                yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, 
                        location);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[157]++;
                outliers.add(new Outlier(xx, yyOutlier, oRadius));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[158]++;

            } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[56]++;}
}
}
}
            Collections.sort(outliers);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[159]++;
        }
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[160]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[4]++;


int CodeCoverConditionCoverageHelper_C30;

        // Process outliers. Each outlier is either added to the appropriate 
        // outlier list or a new outlier list is made
        for (Iterator iterator = outliers.iterator();(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[4]--;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[5]--;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[6]++;
}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[161]++;
            Outlier outlier = (Outlier) iterator.next();
            outlierListCollection.add(outlier);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[162]++;
        }
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[163]++;

        // draw yOutliers
        double maxAxisValue = rangeAxis.valueToJava2D(rangeAxis.getUpperBound(),
                dataArea, location) + aRadius;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[164]++;
        double minAxisValue = rangeAxis.valueToJava2D(rangeAxis.getLowerBound(),
                dataArea, location) - aRadius;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[165]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[7]++;


int CodeCoverConditionCoverageHelper_C31;

        // draw outliers
        for (Iterator iterator = outlierListCollection.iterator();(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[7]--;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[8]--;
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.loops[9]++;
}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[166]++;
            OutlierList list = (OutlierList) iterator.next();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[167]++;
            Outlier outlier = list.getAveragedOutlier();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[168]++;
            Point2D point = outlier.getPoint();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[169]++;
int CodeCoverConditionCoverageHelper_C32;

            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((list.isMultiple()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[57]++;
                drawMultipleEllipse(point, width, oRadius, g2);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[170]++;

            } 
            else {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[58]++;
                drawEllipse(point, oRadius, g2);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[171]++;
            }
        }
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[172]++;
int CodeCoverConditionCoverageHelper_C33;

        // draw farout
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((outlierListCollection.isHighFarOut()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[59]++;
            drawHighFarOut(aRadius, g2, xx, maxAxisValue);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[173]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[60]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[174]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((outlierListCollection.isLowFarOut()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[61]++;
            drawLowFarOut(aRadius, g2, xx, minAxisValue);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[175]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[62]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[176]++;
int CodeCoverConditionCoverageHelper_C35;
        
        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((box.intersects(dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[63]++;
            addEntity(entities, box, dataset, series, item, xx, yyAverage);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[177]++;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[64]++;}

    }

    /**
     * Draws an ellipse to represent an outlier.
     * 
     * @param point  the location.
     * @param oRadius  the radius.
     * @param g2  the graphics device.
     */
    protected void drawEllipse(Point2D point, double oRadius, Graphics2D g2) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[178]++;
        Ellipse2D.Double dot = new Ellipse2D.Double(point.getX() + oRadius / 2,
                point.getY(), oRadius, oRadius);
        g2.draw(dot);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[179]++;
    }

    /**
     * Draws two ellipses to represent overlapping outliers.
     * 
     * @param point  the location.
     * @param boxWidth  the box width.
     * @param oRadius  the radius.
     * @param g2  the graphics device.
     */
    protected void drawMultipleEllipse(Point2D point, double boxWidth, 
                                       double oRadius, Graphics2D g2) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[180]++;
                                         
        Ellipse2D.Double dot1 = new Ellipse2D.Double(point.getX() 
                - (boxWidth / 2) + oRadius, point.getY(), oRadius, oRadius);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[181]++;
        Ellipse2D.Double dot2 = new Ellipse2D.Double(point.getX() 
                + (boxWidth / 2), point.getY(), oRadius, oRadius);
        g2.draw(dot1);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[182]++;
        g2.draw(dot2);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[183]++;
        
    }

    /**
     * Draws a triangle to indicate the presence of far out values.
     * 
     * @param aRadius  the radius.
     * @param g2  the graphics device.
     * @param xx  the x value.
     * @param m  the max y value.
     */
    protected void drawHighFarOut(double aRadius, Graphics2D g2, double xx, 
            double m) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[184]++;
        double side = aRadius * 2;
        g2.draw(new Line2D.Double(xx - side, m + side, xx + side, m + side));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[185]++;
        g2.draw(new Line2D.Double(xx - side, m + side, xx, m));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[186]++;
        g2.draw(new Line2D.Double(xx + side, m + side, xx, m));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[187]++;
    }

    /**
     * Draws a triangle to indicate the presence of far out values.
     * 
     * @param aRadius  the radius.
     * @param g2  the graphics device.
     * @param xx  the x value.
     * @param m  the min y value.
     */
    protected void drawLowFarOut(double aRadius, Graphics2D g2, double xx, 
            double m) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[188]++;
        double side = aRadius * 2;
        g2.draw(new Line2D.Double(xx - side, m - side, xx + side, m - side));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[189]++;
        g2.draw(new Line2D.Double(xx - side, m - side, xx, m));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[190]++;
        g2.draw(new Line2D.Double(xx + side, m - side, xx, m));
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[191]++;
    }

    /**
     * Tests this renderer for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[192]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[65]++;
            return true;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[66]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[193]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((obj instanceof XYBoxAndWhiskerRenderer) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[68]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[194]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[70]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[195]++;
        XYBoxAndWhiskerRenderer that = (XYBoxAndWhiskerRenderer) obj;
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[196]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.boxWidth != that.getBoxWidth()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[72]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[197]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.boxPaint, that.boxPaint)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[74]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[198]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.artifactPaint, that.artifactPaint)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[76]++;}
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[199]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.fillBox != that.fillBox) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.branches[78]++;}
        return true;

    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[200]++;
        SerialUtilities.writePaint(this.boxPaint, stream);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[201]++;
        SerialUtilities.writePaint(this.artifactPaint, stream);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[202]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {

        stream.defaultReadObject();
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[203]++;
        this.boxPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[204]++;
        this.artifactPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x.statements[205]++;
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

class CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x ());
  }
    public static long[] statements = new long[206];
    public static long[] branches = new long[79];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[43];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 42; i++) {
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

  public CodeCoverCoverageCounter$146cz6q9iwyylohjjadtrf2rudvg70c3oapi3nhgaa8x () {
    super("org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 205; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 78; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 42; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer.java");
      for (int i = 1; i <= 205; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 78; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 42; i++) {
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

