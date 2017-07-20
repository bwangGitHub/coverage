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
 * StackedXYAreaRenderer.java
 * --------------------------
 * (C) Copyright 2003-2007, by Richard Atkinson and Contributors.
 *
 * Original Author:  Richard Atkinson;
 * Contributor(s):   Christian W. Zuckschwerdt;
 *                   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 27-Jul-2003 : Initial version (RA);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 18-Aug-2003 : Now handles null values (RA);
 * 20-Aug-2003 : Implemented Cloneable, PublicCloneable and Serializable (DG);
 * 22-Sep-2003 : Changed to be a two pass renderer with optional shape Paint 
 *               and Stroke (RA);
 * 07-Oct-2003 : Added renderer state (DG);
 * 10-Feb-2004 : Updated state object and changed drawItem() method to make 
 *               overriding easier (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed 
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 10-Sep-2004 : Removed getRangeType() method (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 06-Jan-2005 : Override equals() (DG);
 * 07-Jan-2005 : Update for method name changes in DatasetUtilities (DG);
 * 28-Mar-2005 : Use getXValue() and getYValue() from dataset (DG);
 * 06-Jun-2005 : Fixed null pointer exception, plus problems with equals() and
 *               serialization (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 10-Nov-2006 : Fixed bug 1593156, NullPointerException with line 
 *               plotting (DG);
 * 02-Feb-2007 : Fixed bug 1649686, crosshairs don't stack y-values (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 22-Mar-2007 : Fire change events in setShapePaint() and setShapeStroke() 
 *               methods (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

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
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A stacked area renderer for the {@link XYPlot} class.
 * <br><br>
 * SPECIAL NOTE:  This renderer does not currently handle negative data values
 * correctly.  This should get fixed at some point, but the current workaround
 * is to use the {@link StackedXYAreaRenderer2} class instead.
 */
public class StackedXYAreaRenderer extends XYAreaRenderer 
                                   implements Cloneable, 
                                              PublicCloneable,
                                              Serializable {
  static {
    CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 5217394318178570889L;
  static {
    CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[1]++;
  }
     
     /**
     * A state object for use by this renderer.
     */
    static class StackedXYAreaRendererState extends XYItemRendererState {
        
        /** The area for the current series. */
        private Polygon seriesArea;
        
        /** The line. */
        private Line2D line;
        
        /** The points from the last series. */
        private Stack lastSeriesPoints;
        
        /** The points for the current series. */
        private Stack currentSeriesPoints;
        
        /**
         * Creates a new state for the renderer.
         * 
         * @param info  the plot rendering info.
         */
        public StackedXYAreaRendererState(PlotRenderingInfo info) {
            super(info);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[2]++;
            this.seriesArea = null;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[3]++;
            this.line = new Line2D.Double();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[4]++;
            this.lastSeriesPoints = new Stack();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[5]++;
            this.currentSeriesPoints = new Stack();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[6]++;
        }
        
        /**
         * Returns the series area.
         * 
         * @return The series area.
         */
        public Polygon getSeriesArea() {
            return this.seriesArea;
        }
        
        /**
         * Sets the series area.
         * 
         * @param area  the area.
         */
        public void setSeriesArea(Polygon area) {
            this.seriesArea = area;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[7]++;
        }
        
        /**
         * Returns the working line.
         * 
         * @return The working line.
         */
        public Line2D getLine() {
            return this.line;
        }
        
        /**
         * Returns the current series points.
         * 
         * @return The current series points.
         */
        public Stack getCurrentSeriesPoints() {
            return this.currentSeriesPoints;
        }
        
        /**
         * Sets the current series points.
         * 
         * @param points  the points.
         */
        public void setCurrentSeriesPoints(Stack points) {
            this.currentSeriesPoints = points;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[8]++;
        }
    
        /**
         * Returns the last series points.
         * 
         * @return The last series points.
         */
        public Stack getLastSeriesPoints() {
            return this.lastSeriesPoints;
        }
        
        /**
         * Sets the last series points.
         * 
         * @param points  the points.
         */
        public void setLastSeriesPoints(Stack points) {
            this.lastSeriesPoints = points;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[9]++;
        }
    
    }

    /** 
     * Custom Paint for drawing all shapes, if null defaults to series shapes 
     */
    private transient Paint shapePaint = null;
  {
    CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[10]++;
  }

    /** 
     * Custom Stroke for drawing all shapes, if null defaults to series 
     * strokes.
     */
    private transient Stroke shapeStroke = null;
  {
    CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[11]++;
  }

    /**
     * Creates a new renderer.
     */
    public StackedXYAreaRenderer() {
        this(AREA);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[12]++;
    }

    /**
     * Constructs a new renderer.
     *
     * @param type  the type of the renderer.
     */
    public StackedXYAreaRenderer(int type) {
        this(type, null, null);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[13]++;
    }

    /**
     * Constructs a new renderer.  To specify the type of renderer, use one of 
     * the constants: <code>SHAPES</code>, <code>LINES</code>, 
     * <code>SHAPES_AND_LINES</code>, <code>AREA</code> or 
     * <code>AREA_AND_SHAPES</code>.
     *
     * @param type  the type of renderer.
     * @param labelGenerator  the tool tip generator to use (<code>null</code> 
     *                        is none).
     * @param urlGenerator  the URL generator (<code>null</code> permitted).
     */
    public StackedXYAreaRenderer(int type,
                                 XYToolTipGenerator labelGenerator, 
                                 XYURLGenerator urlGenerator) {

        super(type, labelGenerator, urlGenerator);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[14]++;
    }

    /**
     * Returns the paint used for rendering shapes, or <code>null</code> if 
     * using series paints.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setShapePaint(Paint)
     */
    public Paint getShapePaint() {
        return this.shapePaint;
    }

    /**
     * Sets the paint for rendering shapes and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param shapePaint  the paint (<code>null</code> permitted).
     * 
     * @see #getShapePaint()
     */
    public void setShapePaint(Paint shapePaint) {
        this.shapePaint = shapePaint;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[15]++;
        fireChangeEvent();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[16]++;
    }

    /**
     * Returns the stroke used for rendering shapes, or <code>null</code> if 
     * using series strokes.
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setShapeStroke(Stroke)
     */
    public Stroke getShapeStroke() {
        return this.shapeStroke;
    }

    /**
     * Sets the stroke for rendering shapes and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param shapeStroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getShapeStroke()
     */
    public void setShapeStroke(Stroke shapeStroke) {
        this.shapeStroke = shapeStroke;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[17]++;
        fireChangeEvent();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[18]++;
    }

    /**
     * Initialises the renderer. This method will be called before the first
     * item is rendered, giving the renderer an opportunity to initialise any 
     * state information it wants to maintain.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return A state object that should be passed to subsequent calls to the 
     *         drawItem() method.
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset data,
                                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[19]++;

        XYItemRendererState state = new StackedXYAreaRendererState(info);
        // in the rendering process, there is special handling for item 
        // zero, so we can't support processing of visible data items only
        state.setProcessVisibleItemsOnly(false);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[20]++;
        return state;
    }

    /**
     * Returns the number of passes required by the renderer.
     * 
     * @return 2.
     */
    public int getPassCount() {
        return 2;
    }

    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range ([0.0, 0.0] if the dataset contains no values, and 
     *         <code>null</code> if the dataset is <code>null</code>).
     *         
     * @throws ClassCastException if <code>dataset</code> is not an instance
     *         of {@link TableXYDataset}.
     */
    public Range findRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[21]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[1]++;
            return DatasetUtilities.findStackedRangeBounds(
                (TableXYDataset) dataset);

        }
        else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[2]++;
            return null;
        }
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  information about crosshairs on a plot.
     * @param pass  the pass index.
     * 
     * @throws ClassCastException if <code>state</code> is not an instance of
     *         <code>StackedXYAreaRendererState</code> or <code>dataset</code>
     *         is not an instance of {@link TableXYDataset}.
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
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[22]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[23]++;
        StackedXYAreaRendererState areaState 
            = (StackedXYAreaRendererState) state;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[24]++;
        // Get the item count for the series, so that we can know which is the
        // end of the series.
        TableXYDataset tdataset = (TableXYDataset) dataset;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[25]++;
        int itemCount = tdataset.getItemCount();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[26]++;

        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[27]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[28]++;
        boolean nullPoint = false;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[29]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[3]++;
            y1 = 0.0;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[30]++;
            nullPoint = true;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[31]++;

        } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[4]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[32]++;

        //  Get height adjustment based on stack and translate to Java2D values
        double ph1 = getPreviousHeight(tdataset, series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[33]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[34]++;
        double transY1 = rangeAxis.valueToJava2D(y1 + ph1, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[35]++;

        //  Get series Paint and Stroke
        Paint seriesPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[36]++;
        Stroke seriesStroke = getItemStroke(series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[37]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[5]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[38]++;
int CodeCoverConditionCoverageHelper_C4;
            //  On first pass render the areas, line and outlines

            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((item == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[7]++;
                // Create a new Area for the series
                areaState.setSeriesArea(new Polygon());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[39]++;
                areaState.setLastSeriesPoints(
                        areaState.getCurrentSeriesPoints());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[40]++;
                areaState.setCurrentSeriesPoints(new Stack());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[41]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[42]++;

                // start from previous height (ph1)
                double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, 
                        plot.getRangeAxisEdge());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[43]++;
int CodeCoverConditionCoverageHelper_C5;

                // The first point is (x, 0)
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[9]++;
                    areaState.getSeriesArea().addPoint((int) transX1, 
                            (int) transY2);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[44]++;

                } 
                else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[10]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[45]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[11]++;
                    areaState.getSeriesArea().addPoint((int) transY2, 
                            (int) transX1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[46]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[12]++;}
}

            } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[8]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;

            // Add each point to Area (x, y)
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[13]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[48]++;
                Point point = new Point((int) transX1, (int) transY1);
                areaState.getSeriesArea().addPoint((int) point.getX(), 
                        (int) point.getY());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[49]++;
                areaState.getCurrentSeriesPoints().push(point);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[50]++;

            }
            else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[14]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[51]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[15]++;
                areaState.getSeriesArea().addPoint((int) transY1, 
                        (int) transX1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[52]++;

            } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[16]++;}
}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[53]++;
int CodeCoverConditionCoverageHelper_C9;

            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getPlotLines()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[17]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[19]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[55]++;
                    // get the previous data point...
                    double x0 = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[56]++;
                    double y0 = dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[57]++;
                    double ph0 = getPreviousHeight(tdataset, series, item - 1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[58]++;
                    double transX0 = domainAxis.valueToJava2D(x0, dataArea, 
                            plot.getDomainAxisEdge());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[59]++;
                    double transY0 = rangeAxis.valueToJava2D(y0 + ph0, 
                            dataArea, plot.getRangeAxisEdge());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[60]++;
int CodeCoverConditionCoverageHelper_C11;

                    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[21]++;
                        areaState.getLine().setLine(transX0, transY0, transX1, 
                                transY1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[61]++;

                    }
                    else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[22]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[62]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[23]++;
                        areaState.getLine().setLine(transY0, transX0, transY1, 
                                transX1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[63]++;

                    } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[24]++;}
}
                    g2.draw(areaState.getLine());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[64]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[20]++;}

            } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[18]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[65]++;
int CodeCoverConditionCoverageHelper_C13;

            // Check if the item is the last item for the series and number of 
            // items > 0.  We can't draw an area for a single point.
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((getPlotArea()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((item == (itemCount - 1)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[25]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[66]++;

                double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, 
                        plot.getRangeAxisEdge());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;

                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[27]++;
                    // Add the last point (x,0)
                    areaState.getSeriesArea().addPoint((int) transX1, 
                            (int) transY2);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[68]++;

                }
                else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[28]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[69]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[29]++;
                    // Add the last point (x,0)
                    areaState.getSeriesArea().addPoint((int) transY2, 
                            (int) transX1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[70]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[30]++;}
}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[71]++;
int CodeCoverConditionCoverageHelper_C16;

                // Add points from last series to complete the base of the 
                // polygon
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((series != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[31]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[72]++;
                    Stack points = areaState.getLastSeriesPoints();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[73]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[1]++;


int CodeCoverConditionCoverageHelper_C17;
                    while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((points.empty()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[1]--;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[2]--;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[3]++;
}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[74]++;
                        Point point = (Point) points.pop();
                        areaState.getSeriesArea().addPoint((int) point.getX(), 
                                (int) point.getY());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[75]++;
                    }

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[32]++;}

                //  Fill the polygon
                g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[76]++;
                g2.setStroke(seriesStroke);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[77]++;
                g2.fill(areaState.getSeriesArea());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[78]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[79]++;
int CodeCoverConditionCoverageHelper_C18;

                //  Draw an outline around the Area.
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isOutline()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[33]++;
                    g2.setStroke(lookupSeriesOutlineStroke(series));
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[80]++;
                    g2.setPaint(lookupSeriesOutlinePaint(series));
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[81]++;
                    g2.draw(areaState.getSeriesArea());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[82]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[34]++;}

            } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[26]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[83]++;

            int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[84]++;
            int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
            updateCrosshairValues(crosshairState, x1, ph1 + y1, domainAxisIndex,
                    rangeAxisIndex, transX1, transY1, orientation);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[85]++;


        } 
        else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[6]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[86]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((pass == 1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[35]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[87]++;
            // On second pass render shapes and collect entity and tooltip 
            // information

            Shape shape = null;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[88]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getPlotShapes()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[37]++;
                shape = getItemShape(series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[89]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[90]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[39]++;
                    shape = ShapeUtilities.createTranslatedShape(shape, 
                            transX1, transY1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[91]++;

                } 
                else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[40]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[92]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[41]++;
                    shape = ShapeUtilities.createTranslatedShape(shape, 
                            transY1, transX1);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[93]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[42]++;}
}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[94]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((nullPoint) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[43]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[95]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((getShapePaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[45]++;
                        g2.setPaint(getShapePaint());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[96]++;

                    } 
                    else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[46]++;
                        g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[97]++;
                    }
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[98]++;
int CodeCoverConditionCoverageHelper_C25;
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((getShapeStroke() != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[47]++;
                        g2.setStroke(getShapeStroke());
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[99]++;

                    } 
                    else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[48]++;
                        g2.setStroke(seriesStroke);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[100]++;
                    }
                    g2.draw(shape);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[101]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[44]++;}

            } 
            else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[38]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[102]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[49]++;
                    shape = new Rectangle2D.Double(transX1 - 3, transY1 - 3, 
                            6.0, 6.0);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[103]++;

                } 
                else {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[50]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[104]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[51]++;
                    shape = new Rectangle2D.Double(transY1 - 3, transX1 - 3, 
                            6.0, 6.0);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[105]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[52]++;}
}
            }
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[106]++;
int CodeCoverConditionCoverageHelper_C28;

            // collect entity and tool tip information...
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[53]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[107]++;
                EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[108]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((shape != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((nullPoint) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[55]++;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[109]++;
                    String tip = null;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[110]++;
                    XYToolTipGenerator generator 
                        = getToolTipGenerator(series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[111]++;
int CodeCoverConditionCoverageHelper_C30;
                    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[57]++;
                        tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[112]++;

                    } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[58]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[113]++;
                    String url = null;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[114]++;
int CodeCoverConditionCoverageHelper_C31;
                    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[59]++;
                        url = getURLGenerator().generateURL(dataset, series, 
                                item);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[115]++;

                    } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[60]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[116]++;
                    XYItemEntity entity = new XYItemEntity(shape, dataset, 
                            series, item, tip, url);
                    entities.add(entity);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[117]++;

                } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[56]++;}

            } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[54]++;}


        } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[36]++;}
}
    }

    /**
     * Calculates the stacked value of the all series up to, but not including 
     * <code>series</code> for the specified item. It returns 0.0 if 
     * <code>series</code> is the first series, i.e. 0.
     *
     * @param dataset  the dataset.
     * @param series  the series.
     * @param index  the index.
     *
     * @return The cumulative value for all series' values up to but excluding 
     *         <code>series</code> for <code>index</code>.
     */
    protected double getPreviousHeight(TableXYDataset dataset, 
                                       int series, int index) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[118]++;
        double result = 0.0;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[119]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[4]++;


int CodeCoverConditionCoverageHelper_C32;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < series) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[4]--;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[5]--;
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.loops[6]++;
}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[120]++;
            double value = dataset.getYValue(i, index);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[121]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[61]++;
                result += value;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[122]++;

            } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[62]++;}
        }
        return result;
    }
    
    /**
     * Tests the renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[123]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[63]++;
            return true;

        } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[64]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[124]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((obj instanceof StackedXYAreaRenderer) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[66]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[125]++;
        StackedXYAreaRenderer that = (StackedXYAreaRenderer) obj;
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[126]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.shapePaint, that.shapePaint)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[68]++;}
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[127]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shapeStroke, that.shapeStroke)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.branches[70]++;}
        return true;
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if the renderer cannot be cloned.
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
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[128]++;
        this.shapePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[129]++;
        this.shapeStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[130]++;
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
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[131]++;
        SerialUtilities.writePaint(this.shapePaint, stream);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[132]++;
        SerialUtilities.writeStroke(this.shapeStroke, stream);
CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9.statements[133]++;
    }

}

class CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9 ());
  }
    public static long[] statements = new long[134];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[38];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.StackedXYAreaRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,2,1,1};
    for (int i = 1; i <= 37; i++) {
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

  public CodeCoverCoverageCounter$r0n85q1irkepo93ae7ma6xaunvlbgh97t4wrsrq9 () {
    super("org.jfree.chart.renderer.xy.StackedXYAreaRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 133; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.StackedXYAreaRenderer.java");
      for (int i = 1; i <= 133; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 70; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 37; i++) {
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

