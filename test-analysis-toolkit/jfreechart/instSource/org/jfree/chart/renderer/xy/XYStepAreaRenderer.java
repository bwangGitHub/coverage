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
 * -----------------------
 * XYStepAreaRenderer.java
 * -----------------------
 * (C) Copyright 2003-2007, by Matthias Rose and Contributors.
 *
 * Original Author:  Matthias Rose (based on XYAreaRenderer.java);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 07-Oct-2003 : Version 1, contributed by Matthias Rose (DG);
 * 10-Feb-2004 : Added some getter and setter methods (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed 
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 06-Jul-2005 : Renamed get/setPlotShapes() --> get/setShapesVisible() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Jul-2006 : Modified to call dataset methods that return double 
 *               primitives only (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 14-Feb-2007 : Added equals() method override (DG);
 * 04-May-2007 : Set processVisibleItemsOnly flag to false (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

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
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A step chart renderer that fills the area between the step and the x-axis.
 */
public class XYStepAreaRenderer extends AbstractXYItemRenderer 
                                implements XYItemRenderer, 
                                           Cloneable,
                                           PublicCloneable,
                                           Serializable {
  static {
    CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7311560779702649635L;
  static {
    CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[1]++;
  }
    
    /** Useful constant for specifying the type of rendering (shapes only). */
    public static final int SHAPES = 1;
  static {
    CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[2]++;
  }

    /** Useful constant for specifying the type of rendering (area only). */
    public static final int AREA = 2;
  static {
    CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[3]++;
  }

    /** 
     * Useful constant for specifying the type of rendering (area and shapes). 
     */
    public static final int AREA_AND_SHAPES = 3;
  static {
    CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[4]++;
  }

    /** A flag indicating whether or not shapes are drawn at each XY point. */
    private boolean shapesVisible;

    /** A flag that controls whether or not shapes are filled for ALL series. */
    private boolean shapesFilled;

    /** A flag indicating whether or not Area are drawn at each XY point. */
    private boolean plotArea;

    /** A flag that controls whether or not the outline is shown. */
    private boolean showOutline;

    /** Area of the complete series */
    protected transient Polygon pArea = null;
  {
    CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[5]++;
  }

    /** 
     * The value on the range axis which defines the 'lower' border of the 
     * area. 
     */
    private double rangeBase;

    /**
     * Constructs a new renderer.
     */
    public XYStepAreaRenderer() {
        this(AREA);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[6]++;
    }

    /**
     * Constructs a new renderer.
     *
     * @param type  the type of the renderer.
     */
    public XYStepAreaRenderer(int type) {
        this(type, null, null);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[7]++;
    }

    /**
     * Constructs a new renderer.
     * <p>
     * To specify the type of renderer, use one of the constants:
     * AREA, SHAPES or AREA_AND_SHAPES.
     *
     * @param type  the type of renderer.
     * @param toolTipGenerator  the tool tip generator to use 
     *                          (<code>null</code> permitted).
     * @param urlGenerator  the URL generator (<code>null</code> permitted).
     */
    public XYStepAreaRenderer(int type,
                              XYToolTipGenerator toolTipGenerator, 
                              XYURLGenerator urlGenerator) {

        super();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[8]++;
        setBaseToolTipGenerator(toolTipGenerator);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[9]++;
        setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[10]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == AREA) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[1]++;
            this.plotArea = true;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[12]++;

        }
        else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[2]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[13]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type == SHAPES) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[3]++;
            this.shapesVisible = true;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[14]++;

        }
        else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[4]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[15]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type == AREA_AND_SHAPES) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[5]++;
            this.plotArea = true;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[16]++;
            this.shapesVisible = true;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[17]++;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[6]++;}
}
}
        this.showOutline = false;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[18]++;
    }

    /**
     * Returns a flag that controls whether or not outlines of the areas are 
     * drawn.
     *
     * @return The flag.
     * 
     * @see #setOutline(boolean)
     */
    public boolean isOutline() {
        return this.showOutline;
    }

    /**
     * Sets a flag that controls whether or not outlines of the areas are 
     * drawn, and sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     *
     * @param show  the flag.
     * 
     * @see #isOutline()
     */
    public void setOutline(boolean show) {
        this.showOutline = show;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[19]++;
        fireChangeEvent();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[20]++;
    }

    /**
     * Returns true if shapes are being plotted by the renderer.
     *
     * @return <code>true</code> if shapes are being plotted by the renderer.
     * 
     * @see #setShapesVisible(boolean)
     */
    public boolean getShapesVisible() {
        return this.shapesVisible;
    }
    
    /**
     * Sets the flag that controls whether or not shapes are displayed for each 
     * data item, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #getShapesVisible()
     */
    public void setShapesVisible(boolean flag) {
        this.shapesVisible = flag;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[21]++;
        fireChangeEvent();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[22]++;
    }

    /**
     * Returns the flag that controls whether or not the shapes are filled.
     * 
     * @return A boolean.
     * 
     * @see #setShapesFilled(boolean)
     */
    public boolean isShapesFilled() {
        return this.shapesFilled;
    }
    
    /**
     * Sets the 'shapes filled' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param filled  the flag.
     * 
     * @see #isShapesFilled()
     */
    public void setShapesFilled(boolean filled) {
        this.shapesFilled = filled;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[23]++;
        fireChangeEvent();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[24]++;
    }

    /**
     * Returns true if Area is being plotted by the renderer.
     *
     * @return <code>true</code> if Area is being plotted by the renderer.
     * 
     * @see #setPlotArea(boolean)
     */
    public boolean getPlotArea() {
        return this.plotArea;
    }

    /**
     * Sets a flag that controls whether or not areas are drawn for each data 
     * item and sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #getPlotArea()
     */
    public void setPlotArea(boolean flag) {
        this.plotArea = flag;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[25]++;
        fireChangeEvent();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[26]++;
    }
    
    /**
     * Returns the value on the range axis which defines the 'lower' border of
     * the area.
     *
     * @return <code>double</code> the value on the range axis which defines 
     *         the 'lower' border of the area.
     *         
     * @see #setRangeBase(double)
     */
    public double getRangeBase() {
        return this.rangeBase;
    }

    /**
     * Sets the value on the range axis which defines the default border of the 
     * area, and sends a {@link RendererChangeEvent} to all registered 
     * listeners.  E.g. setRangeBase(Double.NEGATIVE_INFINITY) lets areas always
     * reach the lower border of the plotArea. 
     * 
     * @param val  the value on the range axis which defines the default border
     *             of the area.
     *             
     * @see #getRangeBase()
     */
    public void setRangeBase(double val) {
        this.rangeBase = val;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[27]++;
        fireChangeEvent();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[28]++;
    }

    /**
     * Initialises the renderer.  Here we calculate the Java2D y-coordinate for
     * zero, since all the bars have their bases fixed at zero.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return The number of passes required by the renderer.
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset data,
                                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[29]++;

        
        XYItemRendererState state = super.initialise(g2, dataArea, plot, data, 
                info);
        // disable visible items optimisation - it doesn't work for this
        // renderer...
        state.setProcessVisibleItemsOnly(false);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[30]++;
        return state;

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
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[31]++;
                             
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[32]++;
        
        // Get the item count for the series, so that we can know which is the 
        // end of the series.
        int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[33]++;

        Paint paint = getItemPaint(series, item);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[34]++;
        Stroke seriesStroke = getItemStroke(series, item);
        g2.setPaint(paint);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[35]++;
        g2.setStroke(seriesStroke);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[36]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[37]++;

        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[38]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[39]++;
        double x = x1;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[40]++;
        double y = Double.isNaN(y1) ? getRangeBase() : y1;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[41]++;
        double transX1 = domainAxis.valueToJava2D(x, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[42]++;
        double transY1 = rangeAxis.valueToJava2D(y, dataArea, 
                plot.getRangeAxisEdge());
                                                          
        // avoid possible sun.dc.pr.PRException: endPath: bad path
        transY1 = restrictValueToDataArea(transY1, plot, dataArea);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[43]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[44]++;
int CodeCoverConditionCoverageHelper_C4;         

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((this.pArea == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[7]++;

            // Create a new Area for the series
            this.pArea = new Polygon();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[45]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[46]++;
        
            // start from Y = rangeBase
            double transY2 = rangeAxis.valueToJava2D(getRangeBase(), dataArea,
                    plot.getRangeAxisEdge());
        
            // avoid possible sun.dc.pr.PRException: endPath: bad path
            transY2 = restrictValueToDataArea(transY2, plot, dataArea);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[47]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[48]++;
int CodeCoverConditionCoverageHelper_C5;         
        
            // The first point is (x, this.baseYValue)
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[9]++;
                this.pArea.addPoint((int) transX1, (int) transY2);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[49]++;

            }
            else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[10]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[50]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[11]++;
                this.pArea.addPoint((int) transY2, (int) transX1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[51]++;

            } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[12]++;}
}

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[8]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[52]++;

        double transX0 = 0;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[53]++;
        double transY0 = restrictValueToDataArea(getRangeBase(), plot, 
                dataArea);
        
        double x0;
        double y0;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[54]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[13]++;
            // get the previous data point...
            x0 = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[55]++;
            y0 = Double.isNaN(y1) ? y1 : dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[56]++;

            x = x0;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[57]++;
            y = Double.isNaN(y0) ? getRangeBase() : y0;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[58]++;
            transX0 = domainAxis.valueToJava2D(x, dataArea, 
                    plot.getDomainAxisEdge());
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[59]++;
            transY0 = rangeAxis.valueToJava2D(y, dataArea, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[60]++;

            // avoid possible sun.dc.pr.PRException: endPath: bad path
            transY0 = restrictValueToDataArea(transY0, plot, dataArea);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[61]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[62]++;
int CodeCoverConditionCoverageHelper_C8;
                        
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[15]++;
                // NULL value -> insert point on base line
                // instead of 'step point'
                transX1 = transX0;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[63]++;
                transY0 = transY1;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[64]++;
          
            } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[16]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[65]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((transY0 != transY1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[17]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[66]++;
int CodeCoverConditionCoverageHelper_C10;
                // not just a horizontal bar but need to perform a 'step'.
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[19]++;
                    this.pArea.addPoint((int) transX1, (int) transY0);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[67]++;

                }
                else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[20]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[68]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[21]++;
                    this.pArea.addPoint((int) transY0, (int) transX1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[69]++;

                } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[22]++;}
}

            } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[14]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[70]++;           

        Shape shape = null;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[71]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[23]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[72]++;
int CodeCoverConditionCoverageHelper_C13;
            // Add each point to Area (x, y)
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[25]++;
                this.pArea.addPoint((int) transX1, (int) transY1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[73]++;

            }
            else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[26]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[74]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[27]++;
                this.pArea.addPoint((int) transY1, (int) transX1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[75]++;

            } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[28]++;}
}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[76]++;
int CodeCoverConditionCoverageHelper_C15;

            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getShapesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[29]++;
                shape = getItemShape(series, item);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[77]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[78]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[31]++;
                    shape = ShapeUtilities.createTranslatedShape(shape, 
                            transX1, transY1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[79]++;

                }
                else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[32]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[80]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[33]++;
                    shape = ShapeUtilities.createTranslatedShape(shape, 
                            transY1, transX1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[81]++;

                } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[34]++;}
}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[82]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isShapesFilled()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[35]++;
                    g2.fill(shape);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[83]++;

                }   
                else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[36]++;
                    g2.draw(shape);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[84]++;
                }
   
            }
            else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[30]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[85]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[37]++;
                    shape = new Rectangle2D.Double(transX1 - 2, transY1 - 2, 
                            4.0, 4.0);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[86]++;

                }
                else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[38]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[87]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[39]++;
                    shape = new Rectangle2D.Double(transY1 - 2, transX1 - 2, 
                            4.0, 4.0);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[88]++;

                } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[40]++;}
}
            }

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[24]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[89]++;
int CodeCoverConditionCoverageHelper_C21;

        // Check if the item is the last item for the series or if it
        // is a NULL value and number of items > 0.  We can't draw an area for 
        // a single point.
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (512)) == 0 || true) &&
 ((getPlotArea()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (128)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((this.pArea != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((item == (itemCount - 1)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 5) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 5) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[41]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[90]++;

            double transY2 = rangeAxis.valueToJava2D(getRangeBase(), dataArea, 
                    plot.getRangeAxisEdge());

            // avoid possible sun.dc.pr.PRException: endPath: bad path
            transY2 = restrictValueToDataArea(transY2, plot, dataArea);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[91]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[92]++;
int CodeCoverConditionCoverageHelper_C22;         

            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[43]++;
                // Add the last point (x,0)
                this.pArea.addPoint((int) transX1, (int) transY2);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[93]++;

            }
            else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[44]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[94]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[45]++;
                // Add the last point (x,0)
                this.pArea.addPoint((int) transY2, (int) transX1);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[95]++;

            } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[46]++;}
}

            // fill the polygon
            g2.fill(this.pArea);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[96]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[97]++;
int CodeCoverConditionCoverageHelper_C24;

            // draw an outline around the Area.
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isOutline()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[47]++;
                g2.setStroke(plot.getOutlineStroke());
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[98]++;
                g2.setPaint(plot.getOutlinePaint());
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[99]++;
                g2.draw(this.pArea);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[100]++;

            } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[48]++;}

            // start new area when needed (see above)
            this.pArea = null;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[101]++;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[42]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[102]++;
int CodeCoverConditionCoverageHelper_C25;

        // do we need to update the crosshair values?
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[49]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[103]++;
            int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[104]++;
            int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
            updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, 
                    rangeAxisIndex, transX1, transY1, orientation);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[105]++;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[50]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[106]++;
int CodeCoverConditionCoverageHelper_C26;

        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[51]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[107]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[108]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((shape != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[53]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[109]++;
                String tip = null;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[110]++;
                XYToolTipGenerator generator 
                    = getToolTipGenerator(series, item);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[111]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[55]++;
                    tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[112]++;

                } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[56]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[113]++;
                String url = null;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[114]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[57]++;
                    url = getURLGenerator().generateURL(dataset, series, item);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[115]++;

                } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[58]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[116]++;
                XYItemEntity entity = new XYItemEntity(shape, dataset, series, 
                        item, tip, url);
                entities.add(entity);
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[117]++;

            } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[54]++;}

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[52]++;}
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[118]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[59]++;    
            return true;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[60]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[119]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((obj instanceof XYStepAreaRenderer) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[62]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[120]++;
        XYStepAreaRenderer that = (XYStepAreaRenderer) obj;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[121]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.showOutline != that.showOutline) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[64]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[122]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((this.shapesVisible != that.shapesVisible) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[66]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[123]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.shapesFilled != that.shapesFilled) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[68]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[124]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.plotArea != that.plotArea) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[70]++;}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[125]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.rangeBase != that.rangeBase) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[72]++;}
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
     * Helper method which returns a value if it lies
     * inside the visible dataArea and otherwise the corresponding
     * coordinate on the border of the dataArea. The PlotOrientation
     * is taken into account. 
     * Useful to avoid possible sun.dc.pr.PRException: endPath: bad path
     * which occurs when trying to draw lines/shapes which in large part
     * lie outside of the visible dataArea.
     * 
     * @param value the value which shall be 
     * @param dataArea  the area within which the data is being drawn.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @return <code>double</code> value inside the data area.
     */
    protected static double restrictValueToDataArea(double value, 
                                                    XYPlot plot, 
                                                    Rectangle2D dataArea) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[126]++;
        double min = 0;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[127]++;
        double max = 0;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[128]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[73]++;
            min = dataArea.getMinY();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[129]++;
            max = dataArea.getMaxY();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[130]++;

        } 
        else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[74]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[131]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((plot.getOrientation() ==  PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[75]++;
            min = dataArea.getMinX();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[132]++;
            max = dataArea.getMaxX();
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[133]++;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[76]++;}
}
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[134]++;
int CodeCoverConditionCoverageHelper_C39;       
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((value < min) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[77]++;
            value = min;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[135]++;

        }
        else {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[78]++;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[136]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((value > max) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[79]++;
            value = max;
CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.statements[137]++;

        } else {
  CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl.branches[80]++;}
}
        return value;
    }

}

class CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl ());
  }
    public static long[] statements = new long[138];
    public static long[] branches = new long[81];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[41];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYStepAreaRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 40; i++) {
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

  public CodeCoverCoverageCounter$2v3262iitkh1rftlhrugqje58wh5jiw4lvfl () {
    super("org.jfree.chart.renderer.xy.XYStepAreaRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 137; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 80; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYStepAreaRenderer.java");
      for (int i = 1; i <= 137; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 80; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 40; i++) {
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

