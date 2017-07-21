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
 * XYAreaRenderer.java
 * -------------------
 * (C) Copyright 2002-2007, by Hari and Contributors.
 *
 * Original Author:  Hari (ourhari@hotmail.com);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 03-Apr-2002 : Version 1, contributed by Hari.  This class is based on the 
 *               StandardXYItemRenderer class (DG);
 * 09-Apr-2002 : Removed the translated zero from the drawItem method - 
 *               overridden the initialise() method to calculate it (DG);
 * 30-May-2002 : Added tool tip generator to constructor to match super 
 *               class (DG);
 * 25-Jun-2002 : Removed unnecessary local variable (DG);
 * 05-Aug-2002 : Small modification to drawItem method to support URLs for HTML
 *               image maps (RA);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 07-Nov-2002 : Renamed AreaXYItemRenderer --> XYAreaRenderer (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 27-Jul-2003 : Made line and polygon properties protected rather than 
 *               private (RA);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 08-Dec-2003 : Modified hotspot for chart entity (DG);
 * 10-Feb-2004 : Changed the drawItem() method to make cut-and-paste overriding
 *               easier.  Also moved state class into this class (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed 
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 19-Jan-2005 : Now accesses primitives only from dataset (DG);
 * 21-Mar-2005 : Override getLegendItem() and equals() methods (DG);
 * 20-Apr-2005 : Use generators for legend tooltips and URLs (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 14-Feb-2007 : Fixed bug in clone() (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 04-May-2007 : Set processVisibleItemsOnly flag to false (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * Area item renderer for an {@link XYPlot}.  This class can draw (a) shapes at
 * each point, or (b) lines between points, or (c) both shapes and lines, 
 * or (d) filled areas, or (e) filled areas and shapes.
 */
public class XYAreaRenderer extends AbstractXYItemRenderer 
                            implements XYItemRenderer, 
                                       Cloneable,
                                       PublicCloneable,
                                       Serializable {
  static {
    CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4481971353973876747L;
  static {
    CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[1]++;
  }
    
    /**
     * A state object used by this renderer.
     */
    static class XYAreaRendererState extends XYItemRendererState {
        
        /** Working storage for the area under one series. */
        public Polygon area;
        
        /** Working line that can be recycled. */
        public Line2D line;
        
        /**
         * Creates a new state.
         * 
         * @param info  the plot rendering info.
         */
        public XYAreaRendererState(PlotRenderingInfo info) {
            super(info);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[2]++;
            this.area = new Polygon();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[3]++;
            this.line = new Line2D.Double();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[4]++;
        }
        
    }
    
    /** Useful constant for specifying the type of rendering (shapes only). */
    public static final int SHAPES = 1;
  static {
    CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[5]++;
  }

    /** Useful constant for specifying the type of rendering (lines only). */
    public static final int LINES = 2;
  static {
    CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[6]++;
  }

    /** 
     * Useful constant for specifying the type of rendering (shapes and lines).
     */
    public static final int SHAPES_AND_LINES = 3;
  static {
    CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[7]++;
  }

    /** Useful constant for specifying the type of rendering (area only). */
    public static final int AREA = 4;
  static {
    CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[8]++;
  }

    /** 
     * Useful constant for specifying the type of rendering (area and shapes). 
     */
    public static final int AREA_AND_SHAPES = 5;
  static {
    CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[9]++;
  }

    /** A flag indicating whether or not shapes are drawn at each XY point. */
    private boolean plotShapes;

    /** A flag indicating whether or not lines are drawn between XY points. */
    private boolean plotLines;

    /** A flag indicating whether or not Area are drawn at each XY point. */
    private boolean plotArea;

    /** A flag that controls whether or not the outline is shown. */
    private boolean showOutline;

    /** 
     * The shape used to represent an area in each legend item (this should 
     * never be <code>null</code>). 
     */
    private transient Shape legendArea;

    /**
     * Constructs a new renderer.
     */
    public XYAreaRenderer() {
        this(AREA);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[10]++;
    }

    /**
     * Constructs a new renderer.
     *
     * @param type  the type of the renderer.
     */
    public XYAreaRenderer(int type) {
        this(type, null, null);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[11]++;
    }

    /**
     * Constructs a new renderer.  To specify the type of renderer, use one of 
     * the constants: <code>SHAPES</code>, <code>LINES</code>,
     * <code>SHAPES_AND_LINES</code>, <code>AREA</code> or 
     * <code>AREA_AND_SHAPES</code>.
     *
     * @param type  the type of renderer.
     * @param toolTipGenerator  the tool tip generator to use 
     *                          (<code>null</code> permitted).
     * @param urlGenerator  the URL generator (<code>null</code> permitted).
     */
    public XYAreaRenderer(int type, XYToolTipGenerator toolTipGenerator, 
                          XYURLGenerator urlGenerator) {

        super();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[12]++;
        setBaseToolTipGenerator(toolTipGenerator);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[13]++;
        setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[14]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == SHAPES) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[1]++;
            this.plotShapes = true;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[16]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[2]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type == LINES) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[3]++;
            this.plotLines = true;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[18]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[4]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type == SHAPES_AND_LINES) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[5]++;
            this.plotShapes = true;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[20]++;
            this.plotLines = true;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[21]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[6]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type == AREA) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[7]++;
            this.plotArea = true;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[23]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[8]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type == AREA_AND_SHAPES) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[9]++;
            this.plotArea = true;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[25]++;
            this.plotShapes = true;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[26]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[10]++;}
        this.showOutline = false;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[27]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[28]++;
        GeneralPath area = new GeneralPath();
        area.moveTo(0.0f, -4.0f);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[29]++;
        area.lineTo(3.0f, -2.0f);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[30]++;
        area.lineTo(4.0f, 4.0f);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[31]++;
        area.lineTo(-4.0f, 4.0f);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[32]++;
        area.lineTo(-3.0f, -2.0f);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[33]++;
        area.closePath();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[34]++;
        this.legendArea = area;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[35]++;

    }

    /**
     * Returns true if shapes are being plotted by the renderer.
     *
     * @return <code>true</code> if shapes are being plotted by the renderer.
     */
    public boolean getPlotShapes() {
        return this.plotShapes;
    }

    /**
     * Returns true if lines are being plotted by the renderer.
     *
     * @return <code>true</code> if lines are being plotted by the renderer.
     */
    public boolean getPlotLines() {
        return this.plotLines;
    }

    /**
     * Returns true if Area is being plotted by the renderer.
     *
     * @return <code>true</code> if Area is being plotted by the renderer.
     */
    public boolean getPlotArea() {
        return this.plotArea;
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
     * Sets a flag that controls whether or not outlines of the areas are drawn
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param show  the flag.
     * 
     * @see #isOutline()
     */
    public void setOutline(boolean show) {
        this.showOutline = show;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[36]++;
        fireChangeEvent();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[37]++;
    }

    /**
     * Returns the shape used to represent an area in the legend.
     * 
     * @return The legend area (never <code>null</code>).
     */
    public Shape getLegendArea() {
        return this.legendArea;   
    }
    
    /**
     * Sets the shape used as an area in each legend item and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param area  the area (<code>null</code> not permitted).
     */
    public void setLegendArea(Shape area) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((area == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[11]++;
            throw new IllegalArgumentException("Null 'area' argument.");
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[12]++;}
        this.legendArea = area;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[39]++;
        fireChangeEvent();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[40]++;
    }

    /**
     * Initialises the renderer and returns a state object that should be 
     * passed to all subsequent calls to the drawItem() method.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return A state object for use by the renderer.
     */
    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea,
            XYPlot plot, XYDataset data, PlotRenderingInfo info) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[41]++;
        XYAreaRendererState state = new XYAreaRendererState(info);
        
        // in the rendering process, there is special handling for item 
        // zero, so we can't support processing of visible data items only
        state.setProcessVisibleItemsOnly(false);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[42]++;
        return state;
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
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[43]++;
        LegendItem result = null;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[44]++;
        XYPlot xyplot = getPlot();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[45]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((xyplot != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[13]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[46]++;
            XYDataset dataset = xyplot.getDataset(datasetIndex);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[47]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[15]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[48]++;
                XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[49]++;
                String label = lg.generateLabel(dataset, series);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[50]++;
                String description = label;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[51]++;
                String toolTipText = null;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[52]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[17]++;
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[53]++;

                } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[18]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[54]++;
                String urlText = null;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[19]++;
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[56]++;

                } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[20]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[57]++;
                Paint paint = lookupSeriesPaint(series);
                result = new LegendItem(label, description, toolTipText, 
                        urlText, this.legendArea, paint);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[58]++;
                result.setDataset(dataset);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[59]++;
                result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[60]++;
                result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[61]++;
                result.setSeriesIndex(series);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[62]++;

            } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[16]++;}

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[14]++;}
        return result;
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
    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset,
            int series, int item, CrosshairState crosshairState, int pass) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
        
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[21]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[22]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[64]++;
        XYAreaRendererState areaState = (XYAreaRendererState) state;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[65]++;
        
        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[66]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[67]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[23]++;
            y1 = 0.0;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[68]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[24]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[69]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[70]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[71]++;
        
        // get the previous point and the next point so we can calculate a 
        // "hot spot" for the area (used by the chart entity)...
        int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[72]++;
        double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[73]++;
        double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[74]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[25]++;
            y0 = 0.0;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[75]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[26]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[76]++;
        double transX0 = domainAxis.valueToJava2D(x0, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[77]++;
        double transY0 = rangeAxis.valueToJava2D(y0, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[78]++;
        
        double x2 = dataset.getXValue(series, Math.min(item + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[79]++;
        double y2 = dataset.getYValue(series, Math.min(item + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[80]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Double.isNaN(y2)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[27]++;
            y2 = 0.0;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[81]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[28]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[82]++;
        double transX2 = domainAxis.valueToJava2D(x2, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[83]++;
        double transY2 = rangeAxis.valueToJava2D(y2, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[84]++;
        
        double transZero = rangeAxis.valueToJava2D(0.0, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[85]++;
        Polygon hotspot = null;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[86]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[29]++;
            hotspot = new Polygon();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[87]++;
            hotspot.addPoint((int) transZero, 
                    (int) ((transX0 + transX1) / 2.0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[88]++;
            hotspot.addPoint((int) ((transY0 + transY1) / 2.0), 
                    (int) ((transX0 + transX1) / 2.0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[89]++;
            hotspot.addPoint((int) transY1, (int) transX1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[90]++;
            hotspot.addPoint((int) ((transY1 + transY2) / 2.0), 
                    (int) ((transX1 + transX2) / 2.0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[91]++;
            hotspot.addPoint((int) transZero, 
                    (int) ((transX1 + transX2) / 2.0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[92]++;

        }
        else {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[30]++;  // vertical orientation
            hotspot = new Polygon();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[93]++;
            hotspot.addPoint((int) ((transX0 + transX1) / 2.0), 
                    (int) transZero);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[94]++;
            hotspot.addPoint((int) ((transX0 + transX1) / 2.0), 
                    (int) ((transY0 + transY1) / 2.0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[95]++;
            hotspot.addPoint((int) transX1, (int) transY1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[96]++;
            hotspot.addPoint((int) ((transX1 + transX2) / 2.0), 
                    (int) ((transY1 + transY2) / 2.0));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[97]++;
            hotspot.addPoint((int) ((transX1 + transX2) / 2.0), 
                    (int) transZero);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[98]++;
        }
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[99]++;
int CodeCoverConditionCoverageHelper_C16;
        
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((item == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[31]++;  // create a new area polygon for the series
            areaState.area = new Polygon();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[100]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[101]++;
            // the first point is (x, 0)
            double zero = rangeAxis.valueToJava2D(0.0, dataArea, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[102]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[33]++;
                areaState.area.addPoint((int) transX1, (int) zero);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[103]++;

            }
            else {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[34]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[104]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[35]++;
                areaState.area.addPoint((int) zero, (int) transX1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[105]++;

            } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[36]++;}
}

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[32]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[106]++;
int CodeCoverConditionCoverageHelper_C19;

        // Add each point to Area (x, y)
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[37]++;
            areaState.area.addPoint((int) transX1, (int) transY1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[107]++;

        }
        else {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[38]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[108]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[39]++;
            areaState.area.addPoint((int) transY1, (int) transX1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[109]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[40]++;}
}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[110]++;
        
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[111]++;
        Paint paint = getItemPaint(series, item);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[112]++;
        Stroke stroke = getItemStroke(series, item);
        g2.setPaint(paint);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[113]++;
        g2.setStroke(stroke);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[114]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[115]++;
        
        Shape shape = null;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[116]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((getPlotShapes()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[41]++;
            shape = getItemShape(series, item);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[117]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[118]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[43]++;
                shape = ShapeUtilities.createTranslatedShape(shape, transX1, 
                        transY1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[119]++;

            }
            else {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[44]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[120]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[45]++;
                shape = ShapeUtilities.createTranslatedShape(shape, transY1, 
                        transX1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[121]++;

            } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[46]++;}
}
            g2.draw(shape);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[122]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[42]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[123]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((getPlotLines()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[47]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[124]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[49]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[125]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[51]++;
                    areaState.line.setLine(transX0, transY0, transX1, transY1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[126]++;

                }
                else {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[52]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[127]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[53]++;
                    areaState.line.setLine(transY0, transX0, transY1, transX1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[128]++;

                } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[54]++;}
}
                g2.draw(areaState.line);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[129]++;

            } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[50]++;}

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[48]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[130]++;
int CodeCoverConditionCoverageHelper_C28;

        // Check if the item is the last item for the series.
        // and number of items > 0.  We can't draw an area for a single point.
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (32)) == 0 || true) &&
 ((getPlotArea()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((item == (itemCount - 1)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[55]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[131]++;
int CodeCoverConditionCoverageHelper_C29;

            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[57]++;
                // Add the last point (x,0)
                areaState.area.addPoint((int) transX1, (int) transZero);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[132]++;

            }
            else {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[58]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[133]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[59]++;
                // Add the last point (x,0)
                areaState.area.addPoint((int) transZero, (int) transX1);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[134]++;

            } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[60]++;}
}

            g2.fill(areaState.area);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[135]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[136]++;
int CodeCoverConditionCoverageHelper_C31;

            // draw an outline around the Area.
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isOutline()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[61]++;
                g2.setStroke(getItemOutlineStroke(series, item));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[137]++;
                g2.setPaint(getItemOutlinePaint(series, item));
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[138]++;
                g2.draw(areaState.area);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[139]++;

            } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[62]++;}

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[56]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[140]++;

        int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[141]++;
        int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
        updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, 
                rangeAxisIndex, transX1, transY1, orientation);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[142]++;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[143]++;
        
        // collect entity and tool tip information...
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[144]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((hotspot != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[63]++;
            addEntity(entities, hotspot, dataset, series, item, 0.0, 0.0);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[145]++;

        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[64]++;}

    }

    /**
     * Returns a clone of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[146]++;
        XYAreaRenderer clone = (XYAreaRenderer) super.clone();
        clone.legendArea = ShapeUtilities.clone(this.legendArea);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[147]++;
        return clone;
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[148]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[65]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[66]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[149]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((obj instanceof XYAreaRenderer) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[67]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[68]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[150]++;
        XYAreaRenderer that = (XYAreaRenderer) obj;
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[151]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.plotArea != that.plotArea) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[69]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[70]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[152]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.plotLines != that.plotLines) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[71]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[72]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[153]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.plotShapes != that.plotShapes) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[73]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[74]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[154]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.showOutline != that.showOutline) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[75]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[76]++;}
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[155]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendArea, that.legendArea)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[77]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.branches[78]++;}
        return true;
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
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[156]++;
        this.legendArea = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[157]++;
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
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[158]++;
        SerialUtilities.writeShape(this.legendArea, stream);
CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h.statements[159]++;
    }
}

class CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h ());
  }
    public static long[] statements = new long[160];
    public static long[] branches = new long[79];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[40];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYAreaRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 39; i++) {
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

  public CodeCoverCoverageCounter$1g8u8brwvmvpw1trjxy2sa6iwn184h () {
    super("org.jfree.chart.renderer.xy.XYAreaRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 159; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 78; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYAreaRenderer.java");
      for (int i = 1; i <= 159; i++) {
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
    for (int i = 1; i <= 39; i++) {
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

