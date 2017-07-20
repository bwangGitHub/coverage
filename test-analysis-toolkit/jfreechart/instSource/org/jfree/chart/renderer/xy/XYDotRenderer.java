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
 * ------------------
 * XYDotRenderer.java
 * ------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Christian W. Zuckschwerdt;
 *
 * Changes (from 29-Oct-2002)
 * --------------------------
 * 29-Oct-2002 : Added standard header (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 19-Jan-2005 : Now uses only primitives from dataset (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 10-Jul-2006 : Added dotWidth and dotHeight attributes (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 09-Nov-2007 : Added legend shape attribute, plus override for 
 *               getLegendItem() (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that draws a small dot at each data point for an {@link XYPlot}.
 */
public class XYDotRenderer extends AbstractXYItemRenderer 
                           implements XYItemRenderer, 
                                      Cloneable,
                                      PublicCloneable,
                                      Serializable {
  static {
    CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2764344339073566425L;
  static {
    CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[1]++;
  }
    
    /** The dot width. */
    private int dotWidth;
    
    /** The dot height. */
    private int dotHeight;
    
    /** 
     * The shape that is used to represent an item in the legend. 
     * 
     * @since 1.0.7
     */
    private transient Shape legendShape;

    /**
     * Constructs a new renderer.
     */
    public XYDotRenderer() {
        super();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[2]++;
        this.dotWidth = 1;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[3]++;
        this.dotHeight = 1;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[4]++;
        this.legendShape = new Rectangle2D.Double(-3.0, -3.0, 6.0, 6.0);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[5]++;
    }

    /**
     * Returns the dot width (the default value is 1).
     * 
     * @return The dot width.
     * 
     * @since 1.0.2
     * @see #setDotWidth(int)
     */
    public int getDotWidth() {
        return this.dotWidth;
    }
    
    /**
     * Sets the dot width and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param w  the new width (must be greater than zero).
     * 
     * @throws IllegalArgumentException if <code>w</code> is less than one.
     * 
     * @since 1.0.2
     * @see #getDotWidth()
     */
    public void setDotWidth(int w) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((w < 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[1]++;
            throw new IllegalArgumentException("Requires w > 0.");

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[2]++;}
        this.dotWidth = w;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[7]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[8]++;
    }
    
    /**
     * Returns the dot height (the default value is 1).
     * 
     * @return The dot height.
     * 
     * @since 1.0.2
     * @see #setDotHeight(int)
     */
    public int getDotHeight() {
        return this.dotHeight;
    }
    
    /**
     * Sets the dot height and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param h  the new height (must be greater than zero).
     * 
     * @throws IllegalArgumentException if <code>h</code> is less than one.
     * 
     * @since 1.0.2
     * @see #getDotHeight()
     */
    public void setDotHeight(int h) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((h < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[3]++;
            throw new IllegalArgumentException("Requires h > 0.");

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[4]++;}
        this.dotHeight = h;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[10]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[11]++;
    }
    
    /**
     * Returns the shape used to represent an item in the legend.
     * 
     * @return The legend shape (never <code>null</code>).
     * 
     * @see #setLegendShape(Shape)
     * 
     * @since 1.0.7
     */
    public Shape getLegendShape() {
        return this.legendShape;   
    }
    
    /**
     * Sets the shape used as a line in each legend item and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param shape  the shape (<code>null</code> not permitted).
     * 
     * @see #getLegendShape()
     * 
     * @since 1.0.7
     */
    public void setLegendShape(Shape shape) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[5]++;
            throw new IllegalArgumentException("Null 'shape' argument.");
   
        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[6]++;}
        this.legendShape = shape;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[13]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[14]++;
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
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[15]++;

        // get the data point...
        double x = dataset.getXValue(series, item);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[16]++;
        double y = dataset.getYValue(series, item);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[17]++;
        double adjx = (this.dotWidth - 1) / 2.0;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[18]++;
        double adjy = (this.dotHeight - 1) / 2.0;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(y)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[7]++;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[20]++;
            RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[21]++;
            RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[22]++;
            double transX = domainAxis.valueToJava2D(x, dataArea, 
                    xAxisLocation) - adjx;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[23]++;
            double transY = rangeAxis.valueToJava2D(y, dataArea, yAxisLocation) 
                    - adjy;

            g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[24]++;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[25]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[9]++;
                g2.fillRect((int) transY, (int) transX, this.dotHeight, 
                        this.dotWidth);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[27]++;

            }
            else {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[10]++;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[28]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[11]++;
                g2.fillRect((int) transX, (int) transY, this.dotWidth, 
                        this.dotHeight);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[29]++;

            } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[12]++;}
}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[30]++;

            int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[31]++;
            int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
            updateCrosshairValues(crosshairState, x, y, domainAxisIndex, 
                    rangeAxisIndex, transX, transY, orientation);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[32]++;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[8]++;}

    }

    /**
     * Returns a legend item for the specified series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return A legend item for the series (possibly <code>null</code>).
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[33]++;

        // if the renderer isn't assigned to a plot, then we don't have a
        // dataset...
        XYPlot plot = getPlot();
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[13]++;
            return null;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[14]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[35]++;

        XYDataset dataset = plot.getDataset(datasetIndex);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[15]++;
            return null;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[16]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[37]++;

        LegendItem result = null;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getItemVisible(series, 0)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[17]++;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[39]++;
            String label = getLegendItemLabelGenerator().generateLabel(dataset,
                    series);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[40]++;
            String description = label;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[41]++;
            String toolTipText = null;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[19]++;
                toolTipText = getLegendItemToolTipGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[43]++;

            } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[20]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[44]++;
            String urlText = null;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[21]++;
                urlText = getLegendItemURLGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[46]++;

            } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[22]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[47]++;
            Paint fillPaint = lookupSeriesPaint(series);
            result = new LegendItem(label, description, toolTipText, urlText, 
                    getLegendShape(), fillPaint);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[48]++;
            result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[49]++;
            result.setSeriesIndex(series);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[50]++;
            result.setDataset(dataset);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[51]++;
            result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[52]++;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[18]++;}

        return result;

    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.  This method
     * returns <code>true</code> if and only if:
     * 
     * <ul>
     * <li><code>obj</code> is not <code>null</code>;</li>
     * <li><code>obj</code> is an instance of <code>XYDotRenderer</code>;</li>
     * <li>both renderers have the same attribute values.
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[24]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((obj instanceof XYDotRenderer) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[26]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[55]++;
        XYDotRenderer that = (XYDotRenderer) obj;
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.dotWidth != that.dotWidth) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[28]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.dotHeight != that.dotHeight) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[30]++;}
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[58]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendShape, that.legendShape)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.branches[32]++;}
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
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[59]++;
        this.legendShape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[60]++;
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
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[61]++;
        SerialUtilities.writeShape(this.legendShape, stream);
CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1.statements[62]++;
    }

}

class CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1 ());
  }
    public static long[] statements = new long[63];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYDotRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 16; i++) {
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

  public CodeCoverCoverageCounter$7chr80wrl2ez3mesr9uf2kej6qg1 () {
    super("org.jfree.chart.renderer.xy.XYDotRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 62; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYDotRenderer.java");
      for (int i = 1; i <= 62; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
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

