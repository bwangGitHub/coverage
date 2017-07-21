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
 * -------------------------
 * XYDifferenceRenderer.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard West, Advanced Micro Devices, Inc. (major rewrite 
 *                   of difference drawing algorithm);
 *
 * Changes:
 * --------
 * 30-Apr-2003 : Version 1 (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 09-Feb-2004 : Updated to support horizontal plot orientation (DG);
 * 10-Feb-2004 : Added default constructor, setter methods and updated 
 *               Javadocs (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 30-Mar-2004 : Fixed bug in getNegativePaint() method (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 25-Aug-2004 : Fixed a bug preventing the use of crosshairs (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 19-Jan-2005 : Now accesses only primitive values from dataset (DG);
 * 22-Feb-2005 : Override getLegendItem(int, int) to return "line" items (DG);
 * 13-Apr-2005 : Fixed shape positioning bug (id = 1182062) (DG);
 * 20-Apr-2005 : Use generators for legend tooltips and URLs (DG);
 * 04-May-2005 : Override equals() method, renamed get/setPlotShapes() -->
 *               get/setShapesVisible (DG);
 * 09-Jun-2005 : Updated equals() to handle GradientPaint (DG);
 * 16-Jun-2005 : Fix bug (1221021) affecting stroke used for each series (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 24-Jan-2007 : Added flag to allow rounding of x-coordinates, and fixed
 *               bug in clone() (DG);
 * 05-Feb-2007 : Added an extra call to updateCrosshairValues() in 
 *               drawItemPass1(), to fix bug 1564967 (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 08-Mar-2007 : Fixed entity generation (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 23-Apr-2007 : Rewrite of difference drawing algorithm to allow use of 
 *               series with disjoint x-values (RW);
 * 04-May-2007 : Set processVisibleItemsOnly flag to false (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 05-Nov-2007 : Draw item labels if visible (RW);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer for an {@link XYPlot} that highlights the differences between two
 * series.
 */
public class XYDifferenceRenderer extends AbstractXYItemRenderer 
                                  implements XYItemRenderer, 
                                             Cloneable,
                                             PublicCloneable,
                                             Serializable {
  static {
    CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8447915602375584857L;
  static {
    CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[1]++;
  }
    
    /** The paint used to highlight positive differences (y(0) > y(1)). */
    private transient Paint positivePaint;

    /** The paint used to highlight negative differences (y(0) < y(1)). */
    private transient Paint negativePaint;

    /** Display shapes at each point? */
    private boolean shapesVisible;
    
    /** The shape to display in the legend item. */
    private transient Shape legendLine;

    /**
     * This flag controls whether or not the x-coordinates (in Java2D space) 
     * are rounded to integers.  When set to true, this can avoid the vertical
     * striping that anti-aliasing can generate.  However, the rounding may not
     * be appropriate for output in high resolution formats (for example, 
     * vector graphics formats such as SVG and PDF).
     * 
     * @since 1.0.4
     */
    private boolean roundXCoordinates;

    /**
     * Creates a new renderer with default attributes.
     */
    public XYDifferenceRenderer() {
        this(Color.green, Color.red, false);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[2]++;
    }
    
    /**
     * Creates a new renderer.
     *
     * @param positivePaint  the highlight color for positive differences 
     *                       (<code>null</code> not permitted).
     * @param negativePaint  the highlight color for negative differences 
     *                       (<code>null</code> not permitted).
     * @param shapes  draw shapes?
     */
    public XYDifferenceRenderer(Paint positivePaint, Paint negativePaint, 
                                boolean shapes) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((positivePaint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[1]++;
            throw new IllegalArgumentException(
                    "Null 'positivePaint' argument.");

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[2]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((negativePaint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[3]++;
            throw new IllegalArgumentException(
                    "Null 'negativePaint' argument.");

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[4]++;}
        this.positivePaint = positivePaint;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[5]++;
        this.negativePaint = negativePaint;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[6]++;
        this.shapesVisible = shapes;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[7]++;
        this.legendLine = new Line2D.Double(-7.0, 0.0, 7.0, 0.0);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[8]++;
        this.roundXCoordinates = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[9]++;
    }

    /**
     * Returns the paint used to highlight positive differences.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setPositivePaint(Paint)
     */
    public Paint getPositivePaint() {
        return this.positivePaint;
    }

    /**
     * Sets the paint used to highlight positive differences and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPositivePaint()
     */
    public void setPositivePaint(Paint paint) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[5]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[6]++;}
        this.positivePaint = paint;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[11]++;
        fireChangeEvent();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[12]++;
    }

    /**
     * Returns the paint used to highlight negative differences.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setNegativePaint(Paint)
     */
    public Paint getNegativePaint() {
        return this.negativePaint;
    }
    
    /**
     * Sets the paint used to highlight negative differences.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getNegativePaint()
     */
    public void setNegativePaint(Paint paint) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[7]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[8]++;}
        this.negativePaint = paint;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[14]++;
        notifyListeners(new RendererChangeEvent(this));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[15]++;
    }

    /**
     * Returns a flag that controls whether or not shapes are drawn for each 
     * data value.
     * 
     * @return A boolean.
     * 
     * @see #setShapesVisible(boolean)
     */
    public boolean getShapesVisible() {
        return this.shapesVisible;
    }

    /**
     * Sets a flag that controls whether or not shapes are drawn for each 
     * data value, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #getShapesVisible()
     */
    public void setShapesVisible(boolean flag) {
        this.shapesVisible = flag;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[16]++;
        fireChangeEvent();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[17]++;
    }
    
    /**
     * Returns the shape used to represent a line in the legend.
     * 
     * @return The legend line (never <code>null</code>).
     * 
     * @see #setLegendLine(Shape)
     */
    public Shape getLegendLine() {
        return this.legendLine;   
    }
    
    /**
     * Sets the shape used as a line in each legend item and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param line  the line (<code>null</code> not permitted).
     * 
     * @see #getLegendLine()
     */
    public void setLegendLine(Shape line) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((line == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[9]++;
            throw new IllegalArgumentException("Null 'line' argument.");
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[10]++;}
        this.legendLine = line;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[19]++;
        fireChangeEvent();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[20]++;
    }

    /**
     * Returns the flag that controls whether or not the x-coordinates (in
     * Java2D space) are rounded to integer values.
     * 
     * @return The flag.
     * 
     * @since 1.0.4
     * 
     * @see #setRoundXCoordinates(boolean)
     */
    public boolean getRoundXCoordinates() {
        return this.roundXCoordinates;
    }
    
    /**
     * Sets the flag that controls whether or not the x-coordinates (in 
     * Java2D space) are rounded to integer values, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param round  the new flag value.
     * 
     * @since 1.0.4
     * 
     * @see #getRoundXCoordinates()
     */
    public void setRoundXCoordinates(boolean round) {
        this.roundXCoordinates = round;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[21]++;
        fireChangeEvent();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[22]++;
    }

    /**
     * Initialises the renderer and returns a state object that should be 
     * passed to subsequent calls to the drawItem() method.  This method will 
     * be called before the first item is rendered, giving the renderer an 
     * opportunity to initialise any state information it wants to maintain.  
     * The renderer can do nothing if it chooses.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return A state object.
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset data,
                                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[23]++;

        XYItemRendererState state = super.initialise(g2, dataArea, plot, data, 
                info);
        state.setProcessVisibleItemsOnly(false);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[24]++;
        return state;

    }

    /**
     * Returns <code>2</code>, the number of passes required by the renderer.  
     * The {@link XYPlot} will run through the dataset this number of times.
     * 
     * @return The number of passes required by the renderer.
     */
    public int getPassCount() {
        return 2;
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
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[11]++;
            drawItemPass0(g2, dataArea, info, plot, domainAxis, rangeAxis, 
                    dataset, series, item, crosshairState);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[26]++;

        }
        else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[12]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[27]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((pass == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[13]++;
            drawItemPass1(g2, dataArea, info, plot, domainAxis, rangeAxis, 
                    dataset, series, item, crosshairState);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[28]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[14]++;}
}

    }

    /**
     * Draws the visual representation of a single data item, first pass.
     *
     * @param x_graphics  the graphics device.
     * @param x_dataArea  the area within which the data is being drawn.
     * @param x_info  collects information about the drawing.
     * @param x_plot  the plot (can be used to obtain standard color 
     *                information etc).
     * @param x_domainAxis  the domain (horizontal) axis.
     * @param x_rangeAxis  the range (vertical) axis.
     * @param x_dataset  the dataset.
     * @param x_series  the series index (zero-based).
     * @param x_item  the item index (zero-based).
     * @param x_crosshairState  crosshair information for the plot 
     *                          (<code>null</code> permitted).
     */
    protected void drawItemPass0(Graphics2D x_graphics,
                                 Rectangle2D x_dataArea,
                                 PlotRenderingInfo x_info,
                                 XYPlot x_plot,
                                 ValueAxis x_domainAxis,
                                 ValueAxis x_rangeAxis,
                                 XYDataset x_dataset,
                                 int x_series,
                                 int x_item,
                                 CrosshairState x_crosshairState) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!((
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((0 == x_series) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((0 == x_item) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[15]++;
            return;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[16]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[30]++;

        boolean b_impliedZeroSubtrahend = (1 == x_dataset.getSeriesCount());
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;

        // check if either series is a degenerate case (i.e. less than 2 points)
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isEitherSeriesDegenerate(x_dataset, b_impliedZeroSubtrahend)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[17]++;
            return;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[18]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;

        // check if series are disjoint (i.e. domain-spans do not overlap)
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((b_impliedZeroSubtrahend) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((areSeriesDisjoint(x_dataset)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[19]++;
            return;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[20]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[33]++;

        // polygon definitions
        LinkedList l_minuendXs    = new LinkedList();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[34]++;
        LinkedList l_minuendYs    = new LinkedList();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[35]++;
        LinkedList l_subtrahendXs = new LinkedList();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[36]++;
        LinkedList l_subtrahendYs = new LinkedList();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[37]++;
        LinkedList l_polygonXs    = new LinkedList();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[38]++;
        LinkedList l_polygonYs    = new LinkedList();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[39]++;

        // state
        int l_minuendItem      = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[40]++;
        int l_minuendItemCount = x_dataset.getItemCount(0);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[41]++;
        Double l_minuendCurX   = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[42]++;
        Double l_minuendNextX  = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[43]++;
        Double l_minuendCurY   = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[44]++;
        Double l_minuendNextY  = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[45]++;
        double l_minuendMaxY   = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[46]++;
        double l_minuendMinY   = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[47]++;

        int l_subtrahendItem      = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[48]++;
        int l_subtrahendItemCount = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[49]++; // actual value set below
        Double l_subtrahendCurX   = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[50]++;
        Double l_subtrahendNextX  = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[51]++;
        Double l_subtrahendCurY   = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[52]++;
        Double l_subtrahendNextY  = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[53]++;
        double l_subtrahendMaxY   = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[54]++;
        double l_subtrahendMinY   = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[55]++;
int CodeCoverConditionCoverageHelper_C11;

        // if a subtrahend is not specified, assume it is zero
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((b_impliedZeroSubtrahend) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[21]++;
            l_subtrahendItem      = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[56]++;
            l_subtrahendItemCount = 2;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[57]++;
            l_subtrahendCurX      = new Double(x_dataset.getXValue(0, 0));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[58]++;
            l_subtrahendNextX     = new Double(x_dataset.getXValue(0, 
                    (l_minuendItemCount - 1)));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[59]++;
            l_subtrahendCurY      = new Double(0.0);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[60]++;
            l_subtrahendNextY     = new Double(0.0);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[61]++;
            l_subtrahendMaxY      = 0.0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[62]++;
            l_subtrahendMinY      = 0.0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[63]++;

            l_subtrahendXs.add(l_subtrahendCurX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[64]++;
            l_subtrahendYs.add(l_subtrahendCurY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[65]++;

        }
        else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[22]++;
            l_subtrahendItemCount = x_dataset.getItemCount(1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[66]++;
        }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[67]++;

        boolean b_minuendDone           = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[68]++;
        boolean b_minuendAdvanced       = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[69]++;
        boolean b_minuendAtIntersect    = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[70]++;
        boolean b_minuendFastForward    = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[71]++;
        boolean b_subtrahendDone        = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[72]++;
        boolean b_subtrahendAdvanced    = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[73]++;
        boolean b_subtrahendAtIntersect = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[74]++;
        boolean b_subtrahendFastForward = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[75]++;
        boolean b_colinear              = false;

        boolean b_positive;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[76]++;

        // coordinate pairs
        double l_x1 = 0.0, l_y1 = 0.0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[77]++; // current minuend point
        double l_x2 = 0.0, l_y2 = 0.0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[78]++; // next minuend point
        double l_x3 = 0.0, l_y3 = 0.0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[79]++; // current subtrahend point
        double l_x4 = 0.0, l_y4 = 0.0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[80]++; // next subtrahend point

        // fast-forward through leading tails
        boolean b_fastForwardDone = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[81]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
        while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((b_fastForwardDone) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[1]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[2]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[3]++;
}
            // get the x and y coordinates
            l_x1 = x_dataset.getXValue(0, l_minuendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[82]++;
            l_y1 = x_dataset.getYValue(0, l_minuendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[83]++;
            l_x2 = x_dataset.getXValue(0, l_minuendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[84]++;
            l_y2 = x_dataset.getYValue(0, l_minuendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[85]++;

            l_minuendCurX  = new Double(l_x1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[86]++;
            l_minuendCurY  = new Double(l_y1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[87]++;
            l_minuendNextX = new Double(l_x2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[88]++;
            l_minuendNextY = new Double(l_y2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[89]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[90]++;
int CodeCoverConditionCoverageHelper_C13;

            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((b_impliedZeroSubtrahend) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[23]++;
                l_x3 = l_subtrahendCurX.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[91]++;
                l_y3 = l_subtrahendCurY.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[92]++;
                l_x4 = l_subtrahendNextX.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[93]++;
                l_y4 = l_subtrahendNextY.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[94]++;

            }
            else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[24]++;
                l_x3 = x_dataset.getXValue(1, l_subtrahendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[95]++;
                l_y3 = x_dataset.getYValue(1, l_subtrahendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[96]++;
                l_x4 = x_dataset.getXValue(1, l_subtrahendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[97]++;
                l_y4 = x_dataset.getYValue(1, l_subtrahendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[98]++;

                l_subtrahendCurX  = new Double(l_x3);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[99]++;
                l_subtrahendCurY  = new Double(l_y3);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[100]++;
                l_subtrahendNextX = new Double(l_x4);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[101]++;
                l_subtrahendNextY = new Double(l_y4);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[102]++;
            }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[103]++;
int CodeCoverConditionCoverageHelper_C14;

            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((l_x2 <= l_x3) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[25]++;
                // minuend needs to be fast forwarded
                l_minuendItem++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[104]++;
                b_minuendFastForward = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[105]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[106]++;
                continue;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[26]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[107]++;
int CodeCoverConditionCoverageHelper_C15;

            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((l_x4 <= l_x1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[27]++;
                // subtrahend needs to be fast forwarded
                l_subtrahendItem++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[108]++;
                b_subtrahendFastForward = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[109]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[110]++;
                continue;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[28]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[111]++;
int CodeCoverConditionCoverageHelper_C16;

            // check if initial polygon needs to be clipped
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((l_x3 < l_x1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((l_x1 < l_x4) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[29]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[112]++;
                // project onto subtrahend
                double l_slope   = (l_y4 - l_y3) / (l_x4 - l_x3);
                l_subtrahendCurX = l_minuendCurX;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[113]++;
                l_subtrahendCurY = new Double((l_slope * l_x1) 
                        + (l_y3 - (l_slope * l_x3)));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[114]++;

                l_subtrahendXs.add(l_subtrahendCurX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[115]++;
                l_subtrahendYs.add(l_subtrahendCurY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[116]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[30]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[117]++;
int CodeCoverConditionCoverageHelper_C17;

            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((l_x1 < l_x3) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((l_x3 < l_x2) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[31]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[118]++;
                // project onto minuend
                double l_slope = (l_y2 - l_y1) / (l_x2 - l_x1);
                l_minuendCurX  = l_subtrahendCurX;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[119]++;
                l_minuendCurY  = new Double((l_slope * l_x3) 
                        + (l_y1 - (l_slope * l_x1)));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[120]++;

                l_minuendXs.add(l_minuendCurX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[121]++;
                l_minuendYs.add(l_minuendCurY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[122]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[32]++;}

            l_minuendMaxY    = l_minuendCurY.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[123]++;
            l_minuendMinY    = l_minuendCurY.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[124]++;
            l_subtrahendMaxY = l_subtrahendCurY.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[125]++;
            l_subtrahendMinY = l_subtrahendCurY.doubleValue();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[126]++;

            b_fastForwardDone = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[127]++;
        }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[128]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;

        // start of algorithm
        while ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((b_minuendDone) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((b_subtrahendDone) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[4]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[5]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[6]++;
}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[129]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((b_minuendDone) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((b_minuendFastForward) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((b_minuendAdvanced) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[33]++;
                l_x1 = x_dataset.getXValue(0, l_minuendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[130]++;
                l_y1 = x_dataset.getYValue(0, l_minuendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[131]++;
                l_minuendCurX = new Double(l_x1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[132]++;
                l_minuendCurY = new Double(l_y1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[133]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[134]++;
int CodeCoverConditionCoverageHelper_C20;

                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((b_minuendAtIntersect) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[35]++;
                    l_minuendXs.add(l_minuendCurX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[135]++;
                    l_minuendYs.add(l_minuendCurY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[136]++;

                } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[36]++;}

                l_minuendMaxY = Math.max(l_minuendMaxY, l_y1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[137]++;
                l_minuendMinY = Math.min(l_minuendMinY, l_y1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[138]++;

                l_x2 = x_dataset.getXValue(0, l_minuendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[139]++;
                l_y2 = x_dataset.getYValue(0, l_minuendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[140]++;
                l_minuendNextX = new Double(l_x2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[141]++;
                l_minuendNextY = new Double(l_y2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[142]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[34]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[143]++;
int CodeCoverConditionCoverageHelper_C21;

            // never updated the subtrahend if it is implied to be zero
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (128)) == 0 || true) &&
 ((b_impliedZeroSubtrahend) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((b_subtrahendDone) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((b_subtrahendFastForward) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((b_subtrahendAdvanced) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 4) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 4) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[37]++;
                l_x3 = x_dataset.getXValue(1, l_subtrahendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[144]++;
                l_y3 = x_dataset.getYValue(1, l_subtrahendItem);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[145]++;
                l_subtrahendCurX = new Double(l_x3);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[146]++;
                l_subtrahendCurY = new Double(l_y3);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[147]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[148]++;
int CodeCoverConditionCoverageHelper_C22;

                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((b_subtrahendAtIntersect) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[39]++;
                    l_subtrahendXs.add(l_subtrahendCurX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[149]++;
                    l_subtrahendYs.add(l_subtrahendCurY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[150]++;

                } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[40]++;}

                l_subtrahendMaxY = Math.max(l_subtrahendMaxY, l_y3);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[151]++;
                l_subtrahendMinY = Math.min(l_subtrahendMinY, l_y3);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[152]++;

                l_x4 = x_dataset.getXValue(1, l_subtrahendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[153]++;
                l_y4 = x_dataset.getYValue(1, l_subtrahendItem + 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[154]++;
                l_subtrahendNextX = new Double(l_x4);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[155]++;
                l_subtrahendNextY = new Double(l_y4);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[156]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[38]++;}

            // deassert b_*FastForward (only matters for 1st time through loop)
            b_minuendFastForward    = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[157]++;
            b_subtrahendFastForward = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[158]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[159]++;

            Double l_intersectX = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[160]++;
            Double l_intersectY = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[161]++;
            boolean b_intersect = false;

            b_minuendAtIntersect    = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[162]++;
            b_subtrahendAtIntersect = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[163]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[164]++;
int CodeCoverConditionCoverageHelper_C23;

            // check for intersect
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((l_x2 == l_x4) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((l_y2 == l_y4) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[41]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[165]++;
int CodeCoverConditionCoverageHelper_C24;
                // check if line segments are colinear
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((l_x1 == l_x3) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((l_y1 == l_y3) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[43]++;
                    b_colinear = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[166]++;

                }
                else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[44]++;
                    // the intersect is at the next point for both the minuend 
                    // and subtrahend
                    l_intersectX = new Double(l_x2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[167]++;
                    l_intersectY = new Double(l_y2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[168]++;

                    b_intersect             = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[169]++;
                    b_minuendAtIntersect    = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[170]++;
                    b_subtrahendAtIntersect = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[171]++;
                 }

            }
            else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[42]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[172]++;
                // compute common denominator
                double l_denominator = ((l_y4 - l_y3) * (l_x2 - l_x1)) 
                        - ((l_x4 - l_x3) * (l_y2 - l_y1));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[173]++;

                // compute common deltas
                double l_deltaY = l_y1 - l_y3;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[174]++;
                double l_deltaX = l_x1 - l_x3;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[175]++;

                // compute numerators
                double l_numeratorA = ((l_x4 - l_x3) * l_deltaY) 
                        - ((l_y4 - l_y3) * l_deltaX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[176]++;
                double l_numeratorB = ((l_x2 - l_x1) * l_deltaY) 
                        - ((l_y2 - l_y1) * l_deltaX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[177]++;
int CodeCoverConditionCoverageHelper_C25;

                // check if line segments are colinear
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((0 == l_numeratorA) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((0 == l_numeratorB) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((0 == l_denominator) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[45]++;
                    b_colinear = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[178]++;

                }
                else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[46]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[179]++;
int CodeCoverConditionCoverageHelper_C26;
                    // check if previously colinear
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((b_colinear) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[47]++;
                        // clear colinear points and flag
                        l_minuendXs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[180]++;
                        l_minuendYs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[181]++;
                        l_subtrahendXs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[182]++;
                        l_subtrahendYs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[183]++;
                        l_polygonXs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[184]++;
                        l_polygonYs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[185]++;

                        b_colinear = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[186]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[187]++;

                        // set new starting point for the polygon
                        boolean b_useMinuend = ((l_x3 <= l_x1) 
                                && (l_x1 <= l_x4));
                        l_polygonXs.add(b_useMinuend ? l_minuendCurX 
                                : l_subtrahendCurX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[188]++;
                        l_polygonYs.add(b_useMinuend ? l_minuendCurY 
                                : l_subtrahendCurY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[189]++;

                    } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[48]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[190]++;

                    // compute slope components
                    double l_slopeA = l_numeratorA / l_denominator;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[191]++;
                    double l_slopeB = l_numeratorB / l_denominator;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[192]++;
int CodeCoverConditionCoverageHelper_C27;

                    // check if the line segments intersect
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C27 |= (128)) == 0 || true) &&
 ((0 < l_slopeA) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (64)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C27 |= (32)) == 0 || true) &&
 ((l_slopeA <= 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((0 < l_slopeB) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((l_slopeB <= 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[49]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[193]++;
                        // compute the point of intersection
                        double l_xi = l_x1 + (l_slopeA * (l_x2 - l_x1));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[194]++;
                        double l_yi = l_y1 + (l_slopeA * (l_y2 - l_y1));

                        l_intersectX            = new Double(l_xi);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[195]++;
                        l_intersectY            = new Double(l_yi);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[196]++;
                        b_intersect             = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[197]++;
                        b_minuendAtIntersect    = ((l_xi == l_x2) 
                                && (l_yi == l_y2));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[198]++;
                        b_subtrahendAtIntersect = ((l_xi == l_x4) 
                                && (l_yi == l_y4));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[199]++;

                        // advance minuend and subtrahend to intesect
                        l_minuendCurX    = l_intersectX;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[200]++;
                        l_minuendCurY    = l_intersectY;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[201]++;
                        l_subtrahendCurX = l_intersectX;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[202]++;
                        l_subtrahendCurY = l_intersectY;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[203]++;

                    } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[50]++;}
                }
            }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[204]++;
int CodeCoverConditionCoverageHelper_C28;

            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((b_intersect) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[51]++;
                // create the polygon
                // add the minuend's points to polygon
                l_polygonXs.addAll(l_minuendXs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[205]++;
                l_polygonYs.addAll(l_minuendYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[206]++;

                // add intersection point to the polygon
                l_polygonXs.add(l_intersectX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[207]++;
                l_polygonYs.add(l_intersectY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[208]++;

                // add the subtrahend's points to the polygon in reverse
                Collections.reverse(l_subtrahendXs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[209]++;
                Collections.reverse(l_subtrahendYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[210]++;
                l_polygonXs.addAll(l_subtrahendXs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[211]++;
                l_polygonYs.addAll(l_subtrahendYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[212]++;

                // create an actual polygon
                b_positive = (l_subtrahendMaxY <= l_minuendMaxY) 
                        && (l_subtrahendMinY <= l_minuendMinY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[213]++;
                createPolygon(x_graphics, x_dataArea, x_plot, x_domainAxis, 
                        x_rangeAxis, b_positive, l_polygonXs, l_polygonYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[214]++;

                // clear the point vectors
                l_minuendXs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[215]++;
                l_minuendYs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[216]++;
                l_subtrahendXs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[217]++;
                l_subtrahendYs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[218]++;
                l_polygonXs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[219]++;
                l_polygonYs.clear();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[220]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[221]++;

                // set the maxY and minY values to intersect y-value
                double l_y       = l_intersectY.doubleValue();
                l_minuendMaxY    = l_y;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[222]++;
                l_subtrahendMaxY = l_y;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[223]++;
                l_minuendMinY    = l_y;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[224]++;
                l_subtrahendMinY = l_y;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[225]++;

                // add interection point to new polygon
                l_polygonXs.add(l_intersectX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[226]++;
                l_polygonYs.add(l_intersectY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[227]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[52]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[228]++;
int CodeCoverConditionCoverageHelper_C29;

            // advance the minuend if needed
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((l_x2 <= l_x4) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[53]++;
                l_minuendItem++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[229]++;
                b_minuendAdvanced = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[230]++;

            }
            else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[54]++;
                b_minuendAdvanced = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[231]++;
            }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[232]++;
int CodeCoverConditionCoverageHelper_C30;

            // advance the subtrahend if needed
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((l_x4 <= l_x2) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[55]++;
                l_subtrahendItem++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[233]++;
                b_subtrahendAdvanced = true;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[234]++;

            }
            else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[56]++;
                b_subtrahendAdvanced = false;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[235]++;
            }

            b_minuendDone    = (l_minuendItem == (l_minuendItemCount - 1));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[236]++;
            b_subtrahendDone = (l_subtrahendItem == (l_subtrahendItemCount 
                    - 1));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[237]++;
        }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[238]++;
int CodeCoverConditionCoverageHelper_C31;

        // check if the final polygon needs to be clipped
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((b_minuendDone) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((l_x3 < l_x2) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((l_x2 < l_x4) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[57]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[239]++;
            // project onto subtrahend
            double l_slope    = (l_y4 - l_y3) / (l_x4 - l_x3);
            l_subtrahendNextX = l_minuendNextX;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[240]++;
            l_subtrahendNextY = new Double((l_slope * l_x2) 
                    + (l_y3 - (l_slope * l_x3)));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[241]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[58]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[242]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (32)) == 0 || true) &&
 ((b_subtrahendDone) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((l_x1 < l_x4) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((l_x4 < l_x2) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[59]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[243]++;
            // project onto minuend
            double l_slope = (l_y2 - l_y1) / (l_x2 - l_x1);
            l_minuendNextX = l_subtrahendNextX;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[244]++;
            l_minuendNextY = new Double((l_slope * l_x4) 
                    + (l_y1 - (l_slope * l_x1)));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[245]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[60]++;}

        // consider last point of minuend and subtrahend for determining 
        // positivity
        l_minuendMaxY    = Math.max(l_minuendMaxY, 
                l_minuendNextY.doubleValue());
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[246]++;
        l_subtrahendMaxY = Math.max(l_subtrahendMaxY, 
                l_subtrahendNextY.doubleValue());
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[247]++;
        l_minuendMinY    = Math.min(l_minuendMinY, 
                l_minuendNextY.doubleValue());
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[248]++;
        l_subtrahendMinY = Math.min(l_subtrahendMinY, 
                l_subtrahendNextY.doubleValue());
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[249]++;

        // add the last point of the minuned and subtrahend
        l_minuendXs.add(l_minuendNextX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[250]++;
        l_minuendYs.add(l_minuendNextY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[251]++;
        l_subtrahendXs.add(l_subtrahendNextX);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[252]++;
        l_subtrahendYs.add(l_subtrahendNextY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[253]++;

        // create the polygon
        // add the minuend's points to polygon
        l_polygonXs.addAll(l_minuendXs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[254]++;
        l_polygonYs.addAll(l_minuendYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[255]++;

        // add the subtrahend's points to the polygon in reverse
        Collections.reverse(l_subtrahendXs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[256]++;
        Collections.reverse(l_subtrahendYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[257]++;
        l_polygonXs.addAll(l_subtrahendXs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[258]++;
        l_polygonYs.addAll(l_subtrahendYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[259]++;

        // create an actual polygon
        b_positive = (l_subtrahendMaxY <= l_minuendMaxY) 
                && (l_subtrahendMinY <= l_minuendMinY);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[260]++;
        createPolygon(x_graphics, x_dataArea, x_plot, x_domainAxis, 
                x_rangeAxis, b_positive, l_polygonXs, l_polygonYs);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[261]++;
    }

    /**
     * Draws the visual representation of a single data item, second pass.  In 
     * the second pass, the renderer draws the lines and shapes for the 
     * individual points in the two series.
     *
     * @param x_graphics  the graphics device.
     * @param x_dataArea  the area within which the data is being drawn.
     * @param x_info  collects information about the drawing.
     * @param x_plot  the plot (can be used to obtain standard color 
     *         information etc).
     * @param x_domainAxis  the domain (horizontal) axis.
     * @param x_rangeAxis  the range (vertical) axis.
     * @param x_dataset  the dataset.
     * @param x_series  the series index (zero-based).
     * @param x_item  the item index (zero-based).
     * @param x_crosshairState  crosshair information for the plot 
     *                          (<code>null</code> permitted).
     */
    protected void drawItemPass1(Graphics2D x_graphics,
                                 Rectangle2D x_dataArea,
                                 PlotRenderingInfo x_info,
                                 XYPlot x_plot,
                                 ValueAxis x_domainAxis,
                                 ValueAxis x_rangeAxis,
                                 XYDataset x_dataset,
                                 int x_series,
                                 int x_item,
                                 CrosshairState x_crosshairState) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[262]++;

        Shape l_entityArea = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[263]++;
        EntityCollection l_entities = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[264]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((null != x_info) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[61]++;
            l_entities = x_info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[265]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[62]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[266]++;

        Paint l_seriesPaint   = getItemPaint(x_series, x_item);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[267]++;
        Stroke l_seriesStroke = getItemStroke(x_series, x_item);
        x_graphics.setPaint(l_seriesPaint);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[268]++;
        x_graphics.setStroke(l_seriesStroke);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[269]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[270]++;

        PlotOrientation l_orientation      = x_plot.getOrientation();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[271]++;
        RectangleEdge l_domainAxisLocation = x_plot.getDomainAxisEdge();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[272]++;
        RectangleEdge l_rangeAxisLocation  = x_plot.getRangeAxisEdge();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[273]++;

        double l_x0 = x_dataset.getXValue(x_series, x_item);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[274]++;
        double l_y0 = x_dataset.getYValue(x_series, x_item);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[275]++;
        double l_x1 = x_domainAxis.valueToJava2D(l_x0, x_dataArea, 
                l_domainAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[276]++;
        double l_y1 = x_rangeAxis.valueToJava2D(l_y0, x_dataArea, 
                l_rangeAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[277]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((getShapesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[63]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[278]++;
            Shape l_shape = getItemShape(x_series, x_item);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[279]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((l_orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[65]++;
                l_shape = ShapeUtilities.createTranslatedShape(l_shape, 
                        l_y1, l_x1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[280]++;

            }
            else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[66]++;
                l_shape = ShapeUtilities.createTranslatedShape(l_shape, 
                        l_x1, l_y1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[281]++;
            }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[282]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((l_shape.intersects(x_dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[67]++;
                x_graphics.setPaint(getItemPaint(x_series, x_item));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[283]++;
                x_graphics.fill(l_shape);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[284]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[68]++;}
            l_entityArea = l_shape;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[285]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[64]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[286]++;
int CodeCoverConditionCoverageHelper_C37;

        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((null != l_entities) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[69]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[287]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((null == l_entityArea) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[71]++;
                l_entityArea = new Rectangle2D.Double((l_x1 - 2), (l_y1 - 2), 
                        4, 4);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[288]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[72]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[289]++;
            String l_tip = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[290]++;
            XYToolTipGenerator l_tipGenerator = getToolTipGenerator(x_series, 
                    x_item);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[291]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((null != l_tipGenerator) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[73]++;
                l_tip = l_tipGenerator.generateToolTip(x_dataset, x_series, 
                        x_item);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[292]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[74]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[293]++;
            String l_url = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[294]++;
            XYURLGenerator l_urlGenerator = getURLGenerator();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[295]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((null != l_urlGenerator) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[75]++;
                l_url = l_urlGenerator.generateURL(x_dataset, x_series, 
                        x_item);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[296]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[76]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[297]++;
            XYItemEntity l_entity = new XYItemEntity(l_entityArea, x_dataset, 
                    x_series, x_item, l_tip, l_url);
            l_entities.add(l_entity);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[298]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[70]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[299]++;
int CodeCoverConditionCoverageHelper_C41;

        // draw the item label if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(x_series, x_item)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[77]++;
            drawItemLabel(x_graphics, l_orientation, x_dataset, x_series,
                          x_item, l_x1, l_y1, (l_y1 < 0.0));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[300]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[78]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[301]++;

        int l_domainAxisIndex = x_plot.getDomainAxisIndex(x_domainAxis);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[302]++;
        int l_rangeAxisIndex  = x_plot.getRangeAxisIndex(x_rangeAxis);
        updateCrosshairValues(x_crosshairState, l_x0, l_y0, l_domainAxisIndex,
                              l_rangeAxisIndex, l_x1, l_y1, l_orientation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[303]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[304]++;
int CodeCoverConditionCoverageHelper_C42;

        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((0 == x_item) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[79]++;
            return;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[80]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[305]++;

        double l_x2 = x_domainAxis.valueToJava2D(x_dataset.getXValue(x_series, 
                (x_item - 1)), x_dataArea, l_domainAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[306]++;
        double l_y2 = x_rangeAxis.valueToJava2D(x_dataset.getYValue(x_series, 
                (x_item - 1)), x_dataArea, l_rangeAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[307]++;

        Line2D l_line = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[308]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((PlotOrientation.HORIZONTAL == l_orientation) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[81]++;
            l_line = new Line2D.Double(l_y1, l_x1, l_y2, l_x2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[309]++;

        }
        else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[82]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[310]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((PlotOrientation.VERTICAL == l_orientation) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[83]++;
            l_line = new Line2D.Double(l_x1, l_y1, l_x2, l_y2);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[311]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[84]++;}
}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[312]++;
int CodeCoverConditionCoverageHelper_C45;
 
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((null != l_line) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((l_line.intersects(x_dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[85]++;
            x_graphics.setPaint(getItemPaint(x_series, x_item));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[313]++;
            x_graphics.setStroke(getItemStroke(x_series, x_item));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[314]++;
            x_graphics.draw(l_line);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[315]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[86]++;}
    }

    /**
     * Determines if a dataset is degenerate.  A degenerate dataset is a 
     * dataset where either series has less than two (2) points.
     *
     * @param x_dataset  the dataset.
     * @param x_impliedZeroSubtrahend  if false, do not check the subtrahend
     *
     * @return true if the dataset is degenerate.
     */
    private boolean isEitherSeriesDegenerate(XYDataset x_dataset, 
            boolean x_impliedZeroSubtrahend) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[316]++;
int CodeCoverConditionCoverageHelper_C46;

        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((x_impliedZeroSubtrahend) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[87]++;
            return (x_dataset.getItemCount(0) < 2);

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[88]++;}

        return ((x_dataset.getItemCount(0) < 2) 
                || (x_dataset.getItemCount(1) < 2));
    }

    /**
     * Determines if the two (2) series are disjoint.
     * Disjoint series do not overlap in the domain space.
     *
     * @param x_dataset  the dataset.
     *
     * @return true if the dataset is degenerate.
     */
    private boolean areSeriesDisjoint(XYDataset x_dataset) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[317]++;

        int l_minuendItemCount = x_dataset.getItemCount(0);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[318]++;
        double l_minuendFirst  = x_dataset.getXValue(0, 0);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[319]++;
        double l_minuendLast   = x_dataset.getXValue(0, l_minuendItemCount - 1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[320]++;

        int l_subtrahendItemCount = x_dataset.getItemCount(1);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[321]++;
        double l_subtrahendFirst  = x_dataset.getXValue(1, 0);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[322]++;
        double l_subtrahendLast   = x_dataset.getXValue(1, 
                l_subtrahendItemCount - 1);

        return ((l_minuendLast < l_subtrahendFirst) 
                || (l_subtrahendLast < l_minuendFirst));
    }

    /**
     * Draws the visual representation of a polygon
     *
     * @param x_graphics  the graphics device.
     * @param x_dataArea  the area within which the data is being drawn.
     * @param x_plot  the plot (can be used to obtain standard color
     *                information etc).
     * @param x_domainAxis  the domain (horizontal) axis.
     * @param x_rangeAxis  the range (vertical) axis.
     * @param x_positive  indicates if the polygon is positive (true) or 
     *                    negative (false).
     * @param x_xValues  a linked list of the x values (expects values to be 
     *                   of type Double).
     * @param x_yValues  a linked list of the y values (expects values to be 
     *                   of type Double).
     */
    private void createPolygon (Graphics2D x_graphics,
                                Rectangle2D x_dataArea,
                                XYPlot x_plot,
                                ValueAxis x_domainAxis,
                                ValueAxis x_rangeAxis,
                                boolean x_positive,
                                LinkedList x_xValues,
                                LinkedList x_yValues) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[323]++;

        PlotOrientation l_orientation      = x_plot.getOrientation();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[324]++;
        RectangleEdge l_domainAxisLocation = x_plot.getDomainAxisEdge();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[325]++;
        RectangleEdge l_rangeAxisLocation  = x_plot.getRangeAxisEdge();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[326]++;

        Object[] l_xValues = x_xValues.toArray();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[327]++;
        Object[] l_yValues = x_yValues.toArray();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[328]++;

        GeneralPath l_path = new GeneralPath();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[329]++;
int CodeCoverConditionCoverageHelper_C47;

        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((PlotOrientation.VERTICAL == l_orientation) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[89]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[330]++;
            double l_x = x_domainAxis.valueToJava2D((
                    (Double) l_xValues[0]).doubleValue(), x_dataArea, 
                    l_domainAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[331]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((this.roundXCoordinates) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[91]++;
                l_x = Math.rint(l_x);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[332]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[92]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[333]++;

            double l_y = x_rangeAxis.valueToJava2D((
                    (Double) l_yValues[0]).doubleValue(), x_dataArea, 
                    l_rangeAxisLocation);

            l_path.moveTo((float) l_x, (float) l_y);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[334]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[335]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[7]++;


int CodeCoverConditionCoverageHelper_C49;
            for (int i = 1;(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((i < l_xValues.length) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[7]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[8]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[9]++;
}
                l_x = x_domainAxis.valueToJava2D((
                        (Double) l_xValues[i]).doubleValue(), x_dataArea, 
                        l_domainAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[336]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[337]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.roundXCoordinates) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[93]++;
                    l_x = Math.rint(l_x);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[338]++;

                } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[94]++;}

                l_y = x_rangeAxis.valueToJava2D((
                        (Double) l_yValues[i]).doubleValue(), x_dataArea, 
                        l_rangeAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[339]++;
                l_path.lineTo((float) l_x, (float) l_y);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[340]++;
            }
            l_path.closePath();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[341]++;

        }
        else {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[90]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[342]++;
            double l_x = x_domainAxis.valueToJava2D((
                    (Double) l_xValues[0]).doubleValue(), x_dataArea, 
                    l_domainAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[343]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.roundXCoordinates) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[95]++;
                l_x = Math.rint(l_x);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[344]++;

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[96]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[345]++;

            double l_y = x_rangeAxis.valueToJava2D((
                    (Double) l_yValues[0]).doubleValue(), x_dataArea, 
                    l_rangeAxisLocation);

            l_path.moveTo((float) l_y, (float) l_x);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[346]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[347]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[10]++;


int CodeCoverConditionCoverageHelper_C52;
            for (int i = 1;(((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((i < l_xValues.length) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[10]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[11]--;
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.loops[12]++;
}
                l_x = x_domainAxis.valueToJava2D((
                        (Double) l_xValues[i]).doubleValue(), x_dataArea, 
                        l_domainAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[348]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[349]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this.roundXCoordinates) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[97]++;
                    l_x = Math.rint(l_x);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[350]++;

                } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[98]++;}

                l_y = x_rangeAxis.valueToJava2D((
                        (Double) l_yValues[i]).doubleValue(), x_dataArea, 
                        l_rangeAxisLocation);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[351]++;
                l_path.lineTo((float) l_y, (float) l_x);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[352]++;
            }
            l_path.closePath();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[353]++;
        }
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[354]++;
int CodeCoverConditionCoverageHelper_C54;

        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((l_path.intersects(x_dataArea)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[99]++;
            x_graphics.setPaint(x_positive ? getPositivePaint() 
                    : getNegativePaint());
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[355]++;
            x_graphics.fill(l_path);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[356]++;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[100]++;}
    }

    /**
     * Returns a default legend item for the specified series.  Subclasses 
     * should override this method to generate customised items.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return A legend item for the series.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[357]++;
        LegendItem result = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[358]++;
        XYPlot p = getPlot();
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[359]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[101]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[360]++;
            XYDataset dataset = p.getDataset(datasetIndex);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[361]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[103]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[362]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((getItemVisible(series, 0)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[105]++;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[363]++;
                    String label = getLegendItemLabelGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[364]++;
                    String description = label;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[365]++;
                    String toolTipText = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[366]++;
int CodeCoverConditionCoverageHelper_C58;
                    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[107]++;
                        toolTipText 
                            = getLegendItemToolTipGenerator().generateLabel(
                                    dataset, series);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[367]++;

                    } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[108]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[368]++;
                    String urlText = null;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[369]++;
int CodeCoverConditionCoverageHelper_C59;
                    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[109]++;
                        urlText = getLegendItemURLGenerator().generateLabel(
                                dataset, series);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[370]++;

                    } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[110]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[371]++;
                    Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[372]++;
                    Stroke stroke = lookupSeriesStroke(series);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[373]++;
                    // TODO:  the following hard-coded line needs generalising
                    Line2D line = new Line2D.Double(-7.0, 0.0, 7.0, 0.0);
                    result = new LegendItem(label, description, 
                            toolTipText, urlText, line, stroke, paint);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[374]++;
                    result.setDataset(dataset);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[375]++;
                    result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[376]++;
                    result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[377]++;
                    result.setSeriesIndex(series);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[378]++;

                } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[106]++;}

            } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[104]++;}


        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[102]++;}

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
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[379]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[111]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[112]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[380]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((obj instanceof XYDifferenceRenderer) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[113]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[114]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[381]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[115]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[116]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[382]++;
        XYDifferenceRenderer that = (XYDifferenceRenderer) obj;
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[383]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.positivePaint, that.positivePaint)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[117]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[118]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[384]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.negativePaint, that.negativePaint)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[119]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[120]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[385]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this.shapesVisible != that.shapesVisible) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[121]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[122]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[386]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendLine, that.legendLine)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[123]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[124]++;}
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[387]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.roundXCoordinates != that.roundXCoordinates) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[125]++;
            return false;

        } else {
  CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.branches[126]++;}
        return true;
    }
    
    /**
     * Returns a clone of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[388]++;
        XYDifferenceRenderer clone = (XYDifferenceRenderer) super.clone();
        clone.legendLine = ShapeUtilities.clone(this.legendLine);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[389]++;
        return clone;
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
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[390]++;
        SerialUtilities.writePaint(this.positivePaint, stream);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[391]++;
        SerialUtilities.writePaint(this.negativePaint, stream);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[392]++;
        SerialUtilities.writeShape(this.legendLine, stream);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[393]++;
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
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[394]++;
        this.positivePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[395]++;
        this.negativePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[396]++;
        this.legendLine = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1.statements[397]++;
    }

}

class CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1 ());
  }
    public static long[] statements = new long[398];
    public static long[] branches = new long[127];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[68];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYDifferenceRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,2,2,2,3,1,3,1,2,2,3,1,3,1,1,1,3,3,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 67; i++) {
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

  public CodeCoverCoverageCounter$40ss1fye25fdc5u18jwjnpb86hku4rzwqdj26w1 () {
    super("org.jfree.chart.renderer.xy.XYDifferenceRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 397; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 126; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 67; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYDifferenceRenderer.java");
      for (int i = 1; i <= 397; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 126; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 67; i++) {
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

