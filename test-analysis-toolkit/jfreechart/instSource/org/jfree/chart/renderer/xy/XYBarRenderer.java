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
 * XYBarRenderer.java
 * ------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *                   Bill Kelemen;
 *                   Marc van Glabbeek (bug 1775452);
 *                   Richard West, Advanced Micro Devices, Inc.;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1, makes VerticalXYBarPlot class redundant (DG);
 * 23-Jan-2002 : Added DrawInfo parameter to drawItem() method (DG);
 * 09-Apr-2002 : Removed the translated zero from the drawItem method. Override 
 *               the initialise() method to calculate it (DG);
 * 24-May-2002 : Incorporated tooltips into chart entities (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 05-Aug-2002 : Small modification to drawItem method to support URLs for HTML 
 *               image maps (RA);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 24-Aug-2003 : Added null checks in drawItem (BK);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 05-Dec-2003 : Changed call to obtain outline paint (DG);
 * 10-Feb-2004 : Added state class, updated drawItem() method to make 
 *               cut-and-paste overriding easier, and replaced property change 
 *               with RendererChangeEvent (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 26-Apr-2004 : Added gradient paint transformer (DG);
 * 19-May-2004 : Fixed bug (879709) with bar zero value for secondary axis (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 01-Sep-2004 : Added a flag to control whether or not the bar outlines are 
 *               drawn (DG);
 * 03-Sep-2004 : Added option to use y-interval from dataset to determine the 
 *               length of the bars (DG);
 * 08-Sep-2004 : Added equals() method and updated clone() method (DG);
 * 26-Jan-2005 : Added override for getLegendItem() method (DG);
 * 20-Apr-2005 : Use generators for label tooltips and URLs (DG);
 * 19-May-2005 : Added minimal item label implementation - needs improving (DG);
 * 14-Oct-2005 : Fixed rendering problem with inverted axes (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 21-Jun-2006 : Improved item label handling - see bug 1501768 (DG);
 * 24-Aug-2006 : Added crosshair support (DG);
 * 13-Dec-2006 : Updated getLegendItems() to return gradient paint 
 *               transformer (DG);
 * 02-Feb-2007 : Changed setUseYInterval() to only notify when the flag 
 *               changes (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 09-Feb-2007 : Updated getLegendItem() to observe drawBarOutline flag (DG);
 * 05-Mar-2007 : Applied patch 1671126 by Sergei Ivanov, to fix rendering with
 *               LogarithmicAxis (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 15-Jun-2007 : Changed default for drawBarOutline to false (DG);
 * 26-Sep-2007 : Fixed bug 1775452, problem with bar margins for inverted
 *               axes, thanks to Marc van Glabbeek (DG);
 * 12-Nov-2007 : Fixed NPE in drawItemLabel() method, thanks to Richard West
 *               (see patch 1827829) (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that draws bars on an {@link XYPlot} (requires an 
 * {@link IntervalXYDataset}).
 */
public class XYBarRenderer extends AbstractXYItemRenderer 
        implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 770559577251370036L;
  static {
    CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[1]++;
  }

    /**
     * The state class used by this renderer.
     */
    protected class XYBarRendererState extends XYItemRendererState {
        
        /** Base for bars against the range axis, in Java 2D space. */
        private double g2Base;
        
        /**
         * Creates a new state object.
         * 
         * @param info  the plot rendering info.
         */
        public XYBarRendererState(PlotRenderingInfo info) {
            super(info);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[2]++;
        }
        
        /**
         * Returns the base (range) value in Java 2D space.
         * 
         * @return The base value.
         */
        public double getG2Base() {
            return this.g2Base;
        }
        
        /**
         * Sets the range axis base in Java2D space.
         * 
         * @param value  the value.
         */
        public void setG2Base(double value) {
            this.g2Base = value;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[3]++;
        }
    }

    /** The default base value for the bars. */
    private double base;
    
    /** 
     * A flag that controls whether the bars use the y-interval supplied by the 
     * dataset. 
     */
    private boolean useYInterval;
    
    /** Percentage margin (to reduce the width of bars). */
    private double margin;

    /** A flag that controls whether or not bar outlines are drawn. */
    private boolean drawBarOutline;
    
    /** 
     * An optional class used to transform gradient paint objects to fit each 
     * bar. 
     */
    private GradientPaintTransformer gradientPaintTransformer; 
    
    /** 
     * The shape used to represent a bar in each legend item (this should never
     * be <code>null</code>). 
     */
    private transient Shape legendBar;
    
    /** 
     * The fallback position if a positive item label doesn't fit inside the 
     * bar. 
     */
    private ItemLabelPosition positiveItemLabelPositionFallback;
    
    /** 
     * The fallback position if a negative item label doesn't fit inside the 
     * bar. 
     */
    private ItemLabelPosition negativeItemLabelPositionFallback;

    /**
     * The default constructor.
     */
    public XYBarRenderer() {
        this(0.0);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[4]++;
    }

    /**
     * Constructs a new renderer.
     *
     * @param margin  the percentage amount to trim from the width of each bar.
     */
    public XYBarRenderer(double margin) {
        super();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[5]++;
        this.margin = margin;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[6]++;
        this.base = 0.0;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[7]++;
        this.useYInterval = false;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[8]++;
        this.gradientPaintTransformer = new StandardGradientPaintTransformer();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[9]++; 
        this.drawBarOutline = false;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[10]++;
        this.legendBar = new Rectangle2D.Double(-3.0, -5.0, 6.0, 10.0);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[11]++;
    }
    
    /**
     * Returns the base value for the bars.
     * 
     * @return The base value for the bars.
     * 
     * @see #setBase(double)
     */
    public double getBase() {
        return this.base;    
    }
    
    /**
     * Sets the base value for the bars and sends a {@link RendererChangeEvent}
     * to all registered listeners.  The base value is not used if the dataset's
     * y-interval is being used to determine the bar length.
     * 
     * @param base  the new base value.
     * 
     * @see #getBase()
     * @see #getUseYInterval()
     */
    public void setBase(double base) {
        this.base = base;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[12]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[13]++;
    }
    
    /**
     * Returns a flag that determines whether the y-interval from the dataset is
     * used to calculate the length of each bar.
     * 
     * @return A boolean.
     * 
     * @see #setUseYInterval(boolean)
     */
    public boolean getUseYInterval() {
        return this.useYInterval;
    }
    
    /**
     * Sets the flag that determines whether the y-interval from the dataset is
     * used to calculate the length of each bar, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param use  the flag.
     * 
     * @see #getUseYInterval()
     */
    public void setUseYInterval(boolean use) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.useYInterval != use) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[1]++;
            this.useYInterval = use;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[15]++;
            fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[16]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[2]++;}
    }

    /**
     * Returns the margin which is a percentage amount by which the bars are 
     * trimmed.
     *
     * @return The margin.
     * 
     * @see #setMargin(double)
     */
    public double getMargin() {
        return this.margin;
    }
    
    /**
     * Sets the percentage amount by which the bars are trimmed and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param margin  the new margin.
     * 
     * @see #getMargin()
     */
    public void setMargin(double margin) {
        this.margin = margin;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[17]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[18]++;
    }

    /**
     * Returns a flag that controls whether or not bar outlines are drawn.
     * 
     * @return A boolean.
     * 
     * @see #setDrawBarOutline(boolean)
     */
    public boolean isDrawBarOutline() {
        return this.drawBarOutline;    
    }
    
    /**
     * Sets the flag that controls whether or not bar outlines are drawn and 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param draw  the flag.
     * 
     * @see #isDrawBarOutline()
     */
    public void setDrawBarOutline(boolean draw) {
        this.drawBarOutline = draw;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[19]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[20]++;
    }
    
    /**
     * Returns the gradient paint transformer (an object used to transform 
     * gradient paint objects to fit each bar).
     * 
     * @return A transformer (<code>null</code> possible).
     * 
     * @see #setGradientPaintTransformer(GradientPaintTransformer)
     */    
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;    
    }
    
    /**
     * Sets the gradient paint transformer and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param transformer  the transformer (<code>null</code> permitted).
     * 
     * @see #getGradientPaintTransformer()
     */
    public void setGradientPaintTransformer(
            GradientPaintTransformer transformer) {
        this.gradientPaintTransformer = transformer;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[21]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[22]++;
    }
     
    /**
     * Returns the shape used to represent bars in each legend item.
     * 
     * @return The shape used to represent bars in each legend item (never 
     *         <code>null</code>).
     *         
     * @see #setLegendBar(Shape)
     */
    public Shape getLegendBar() {
        return this.legendBar;
    }
    
    /**
     * Sets the shape used to represent bars in each legend item and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param bar  the bar shape (<code>null</code> not permitted).
     * 
     * @see #getLegendBar()
     */
    public void setLegendBar(Shape bar) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((bar == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[3]++;
            throw new IllegalArgumentException("Null 'bar' argument.");

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[4]++;}
        this.legendBar = bar;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[24]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[25]++;
    }
    
    /**
     * Returns the fallback position for positive item labels that don't fit 
     * within a bar.
     * 
     * @return The fallback position (<code>null</code> possible).
     * 
     * @see #setPositiveItemLabelPositionFallback(ItemLabelPosition)
     * @since 1.0.2
     */
    public ItemLabelPosition getPositiveItemLabelPositionFallback() {
        return this.positiveItemLabelPositionFallback;
    }
    
    /**
     * Sets the fallback position for positive item labels that don't fit 
     * within a bar, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getPositiveItemLabelPositionFallback()
     * @since 1.0.2
     */
    public void setPositiveItemLabelPositionFallback(
            ItemLabelPosition position) {
        this.positiveItemLabelPositionFallback = position;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[26]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[27]++;
    }
    
    /**
     * Returns the fallback position for negative item labels that don't fit 
     * within a bar.
     * 
     * @return The fallback position (<code>null</code> possible).
     * 
     * @see #setNegativeItemLabelPositionFallback(ItemLabelPosition)
     * @since 1.0.2
     */
    public ItemLabelPosition getNegativeItemLabelPositionFallback() {
        return this.negativeItemLabelPositionFallback;
    }
    
    /**
     * Sets the fallback position for negative item labels that don't fit 
     * within a bar, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getNegativeItemLabelPositionFallback()
     * @since 1.0.2
     */
    public void setNegativeItemLabelPositionFallback(
            ItemLabelPosition position) {
        this.negativeItemLabelPositionFallback = position;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[28]++;
        fireChangeEvent();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[29]++;
    }

    /**
     * Initialises the renderer and returns a state object that should be 
     * passed to all subsequent calls to the drawItem() method.  Here we 
     * calculate the Java2D y-coordinate for zero, since all the bars have 
     * their bases fixed at zero.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param dataset  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return A state object.
     */
    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea,
            XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[30]++;

        XYBarRendererState state = new XYBarRendererState(info);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[31]++;
        ValueAxis rangeAxis = plot.getRangeAxisForDataset(plot.indexOf(
                dataset));
        state.setG2Base(rangeAxis.valueToJava2D(this.base, dataArea, 
                plot.getRangeAxisEdge()));
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[32]++;
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
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[33]++;
        LegendItem result = null;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[34]++;
        XYPlot xyplot = getPlot();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[35]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((xyplot != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[5]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[36]++;
            XYDataset dataset = xyplot.getDataset(datasetIndex);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[37]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[7]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[38]++;
                XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[39]++;
                String label = lg.generateLabel(dataset, series);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[40]++;
                String description = label;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[41]++;
                String toolTipText = null;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[42]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[9]++;
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[43]++;

                } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[10]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[44]++;
                String urlText = null;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[11]++;
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[46]++;

                } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[12]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[47]++;
                Shape shape = this.legendBar;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[48]++;
                Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[49]++;
                Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[50]++;
                Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[51]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.drawBarOutline) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[13]++;
                    result = new LegendItem(label, description, toolTipText, 
                            urlText, shape, paint, outlineStroke, outlinePaint);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[52]++;

                }
                else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[14]++;
                    result = new LegendItem(label, description, toolTipText, 
                            urlText, shape, paint);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[53]++;
                }
                result.setDataset(dataset);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[54]++;
                result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[55]++;
                result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[56]++;
                result.setSeriesIndex(series);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[57]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[58]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getGradientPaintTransformer() != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[15]++;
                    result.setFillPaintTransformer(
                            getGradientPaintTransformer());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[59]++;

                } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[16]++;}

            } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[6]++;}
        return result;
    }
    
    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects information about the drawing.
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
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[60]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[17]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[18]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[61]++;
        IntervalXYDataset intervalDataset = (IntervalXYDataset) dataset;

        double value0;
        double value1;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[62]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.useYInterval) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[19]++;
            value0 = intervalDataset.getStartYValue(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[63]++;
            value1 = intervalDataset.getEndYValue(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[64]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[20]++;
            value0 = this.base;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[65]++;
            value1 = intervalDataset.getYValue(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[66]++;
        }
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[67]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((Double.isNaN(value0)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((Double.isNaN(value1)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[21]++;
            return;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[22]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value0 <= value1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[23]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[69]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((rangeAxis.getRange().intersects(value0, value1)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[25]++;
                return;

            } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[26]++;}

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[24]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[70]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((rangeAxis.getRange().intersects(value1, value0)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[27]++;
                return;

            } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[28]++;}
        }
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[71]++;

        double translatedValue0 = rangeAxis.valueToJava2D(value0, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[72]++;
        double translatedValue1 = rangeAxis.valueToJava2D(value1, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[73]++;
        double bottom = Math.min(translatedValue0, translatedValue1);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[74]++;
        double top = Math.max(translatedValue0, translatedValue1);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[75]++;

        double startX = intervalDataset.getStartXValue(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[76]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((Double.isNaN(startX)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[29]++;
            return;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[30]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[77]++;
        double endX = intervalDataset.getEndXValue(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[78]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((Double.isNaN(endX)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[31]++;
            return;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[32]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[79]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((startX <= endX) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[33]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[80]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((domainAxis.getRange().intersects(startX, endX)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[35]++;
                return;

            } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[36]++;}

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[34]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((domainAxis.getRange().intersects(endX, startX)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[37]++;
                return;

            } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[38]++;}
        }
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[82]++;

        RectangleEdge location = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[83]++;
        double translatedStartX = domainAxis.valueToJava2D(startX, dataArea, 
                location);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[84]++;
        double translatedEndX = domainAxis.valueToJava2D(endX, dataArea, 
                location);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[85]++;

        double translatedWidth = Math.max(1, Math.abs(translatedEndX 
                - translatedStartX));
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[86]++;
        
        double left = Math.min(translatedStartX, translatedEndX);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[87]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getMargin() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[39]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[88]++;
            double cut = translatedWidth * getMargin();
            translatedWidth = translatedWidth - cut;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[89]++;
            left = left + cut / 2;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[90]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[40]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[91]++;

        Rectangle2D bar = null;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[92]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[93]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[41]++;
            // clip left and right bounds to data area
            bottom = Math.max(bottom, dataArea.getMinX());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[94]++;
            top = Math.min(top, dataArea.getMaxX());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[95]++;
            bar = new Rectangle2D.Double(
                bottom, left, top - bottom, translatedWidth);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[96]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[42]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[97]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[43]++;
            // clip top and bottom bounds to data area
            bottom = Math.max(bottom, dataArea.getMinY());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[98]++;
            top = Math.min(top, dataArea.getMaxY());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[99]++;
            bar = new Rectangle2D.Double(left, bottom, translatedWidth, 
                    top - bottom);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[100]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[44]++;}
}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[101]++;

        Paint itemPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[102]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((getGradientPaintTransformer() 
                != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[45]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[103]++;
            GradientPaint gp = (GradientPaint) itemPaint;
            itemPaint = getGradientPaintTransformer().transform(gp, bar);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[104]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[46]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[105]++;
        g2.fill(bar);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[106]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[107]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((Math.abs(translatedEndX - translatedStartX) > 3) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[47]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[108]++;
            Stroke stroke = getItemOutlineStroke(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[109]++;
            Paint paint = getItemOutlinePaint(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[110]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[49]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[111]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[112]++;
                g2.draw(bar);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[113]++;
                
            } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[50]++;}

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[48]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[114]++;
int CodeCoverConditionCoverageHelper_C26;
        
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[51]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[115]++;
            XYItemLabelGenerator generator = getItemLabelGenerator(series, 
                    item);
            drawItemLabel(g2, dataset, series, item, plot, generator, bar, 
                    value1 < 0.0);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[116]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[52]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[117]++;

        // update the crosshair point
        double x1 = (startX + endX) / 2.0;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[118]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[119]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, location);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[120]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[121]++;
        int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[122]++;
        int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
        updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, 
                rangeAxisIndex, transX1, transY1, plot.getOrientation());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[123]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[124]++;

        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[125]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[53]++;
            addEntity(entities, bar, dataset, series, item, 0.0, 0.0);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[126]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[54]++;}

    }

    /**
     * Draws an item label.  This method is provided as an alternative to
     * {@link #drawItemLabel(Graphics2D, PlotOrientation, XYDataset, int, int, 
     * double, double, boolean)} so that the bar can be used to calculate the 
     * label anchor point. 
     * 
     * @param g2  the graphics device.
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param item  the item index.
     * @param plot  the plot.
     * @param generator  the label generator (<code>null</code> permitted, in 
     *         which case the method does nothing, just returns).
     * @param bar  the bar.
     * @param negative  a flag indicating a negative value.
     */
    protected void drawItemLabel(Graphics2D g2, XYDataset dataset,
            int series, int item, XYPlot plot, XYItemLabelGenerator generator, 
            Rectangle2D bar, boolean negative) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[127]++;
int CodeCoverConditionCoverageHelper_C28;
                                     
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[55]++;
            return;
  // nothing to do
        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[56]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[128]++;
        String label = generator.generateLabel(dataset, series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[129]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[57]++;
            return;
  // nothing to do   
        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[58]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[130]++;
        
        Font labelFont = getItemLabelFont(series, item);
        g2.setFont(labelFont);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[131]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[132]++;
        Paint paint = getItemLabelPaint(series, item);
        g2.setPaint(paint);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[133]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[134]++;

        // find out where to place the label...
        ItemLabelPosition position = null;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[135]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[59]++;
            position = getPositiveItemLabelPosition(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[136]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[60]++;
            position = getNegativeItemLabelPosition(series, item);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[137]++;
        }
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[138]++;

        // work out the label anchor point...
        Point2D anchorPoint = calculateLabelAnchorPoint(
                position.getItemLabelAnchor(), bar, plot.getOrientation());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[139]++;
int CodeCoverConditionCoverageHelper_C31;
        
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isInternalAnchor(position.getItemLabelAnchor())) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[61]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[140]++;
            Shape bounds = TextUtilities.calculateRotatedStringBounds(label, 
                    g2, (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(), position.getAngle(),
                    position.getRotationAnchor());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[141]++;
int CodeCoverConditionCoverageHelper_C32;
            
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((bounds != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[63]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[142]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((bar.contains(bounds.getBounds2D())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[65]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[143]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[67]++;
                        position = getPositiveItemLabelPositionFallback();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[144]++;

                    }
                    else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[68]++;
                        position = getNegativeItemLabelPositionFallback();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[145]++;
                    }
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[146]++;
int CodeCoverConditionCoverageHelper_C35;
                    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((position != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[69]++;
                        anchorPoint = calculateLabelAnchorPoint(
                                position.getItemLabelAnchor(), bar, 
                                plot.getOrientation());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[147]++;

                    } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[70]++;}

                } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[66]++;}

            } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[64]++;}

        
        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[62]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[148]++;
int CodeCoverConditionCoverageHelper_C36;
        
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((position != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[71]++;
            TextUtilities.drawRotatedString(label, g2, 
                    (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(), position.getAngle(), 
                    position.getRotationAnchor());
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[149]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[72]++;}        
    }

    /**
     * Calculates the item label anchor point.
     *
     * @param anchor  the anchor.
     * @param bar  the bar.
     * @param orientation  the plot orientation.
     *
     * @return The anchor point.
     */
    private Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor,
            Rectangle2D bar, PlotOrientation orientation) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[150]++;

        Point2D result = null;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[151]++;
        double offset = getItemLabelAnchorOffset();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[152]++;
        double x0 = bar.getX() - offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[153]++;
        double x1 = bar.getX();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[154]++;
        double x2 = bar.getX() + offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[155]++;
        double x3 = bar.getCenterX();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[156]++;
        double x4 = bar.getMaxX() - offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[157]++;
        double x5 = bar.getMaxX();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[158]++;
        double x6 = bar.getMaxX() + offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[159]++;

        double y0 = bar.getMaxY() + offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[160]++;
        double y1 = bar.getMaxY();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[161]++;
        double y2 = bar.getMaxY() - offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[162]++;
        double y3 = bar.getCenterY();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[163]++;
        double y4 = bar.getMinY() + offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[164]++;
        double y5 = bar.getMinY();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[165]++;
        double y6 = bar.getMinY() - offset;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[166]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[73]++;
            result = new Point2D.Double(x3, y3);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[167]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[74]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[168]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE1) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[75]++;
            result = new Point2D.Double(x4, y4);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[169]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[76]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[170]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE2) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[77]++;
            result = new Point2D.Double(x4, y4);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[171]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[78]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[172]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE3) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[79]++;
            result = new Point2D.Double(x4, y3);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[173]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[80]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[174]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE4) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[81]++;
            result = new Point2D.Double(x4, y2);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[175]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[82]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[176]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE5) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[83]++;
            result = new Point2D.Double(x4, y2);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[177]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[84]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[178]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE6) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[85]++;
            result = new Point2D.Double(x3, y2);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[179]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[86]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[180]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE7) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[87]++;
            result = new Point2D.Double(x2, y2);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[181]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[88]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[182]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE8) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[89]++;
            result = new Point2D.Double(x2, y2);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[183]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[90]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[184]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE9) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[91]++;
            result = new Point2D.Double(x2, y3);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[185]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[92]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[186]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE10) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[93]++;
            result = new Point2D.Double(x2, y4);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[187]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[94]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[188]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE11) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[95]++;
            result = new Point2D.Double(x2, y4);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[189]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[96]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[190]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE12) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[97]++;
            result = new Point2D.Double(x3, y4);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[191]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[98]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[192]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE1) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[99]++;
            result = new Point2D.Double(x5, y6);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[193]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[100]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[194]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE2) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[101]++;
            result = new Point2D.Double(x6, y5);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[195]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[102]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[196]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE3) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[103]++;
            result = new Point2D.Double(x6, y3);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[197]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[104]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[198]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE4) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[105]++;
            result = new Point2D.Double(x6, y1);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[199]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[106]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[200]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE5) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[107]++;
            result = new Point2D.Double(x5, y0);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[201]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[108]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[202]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE6) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[109]++;
            result = new Point2D.Double(x3, y0);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[203]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[110]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[204]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE7) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[111]++;
            result = new Point2D.Double(x1, y0);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[205]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[112]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[206]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE8) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[113]++;
            result = new Point2D.Double(x0, y1);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[207]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[114]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[208]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE9) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[115]++;
            result = new Point2D.Double(x0, y3);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[209]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[116]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[210]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE10) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[117]++;
            result = new Point2D.Double(x0, y5);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[211]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[118]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[212]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE11) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[119]++;
            result = new Point2D.Double(x1, y6);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[213]++;

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[120]++;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[214]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE12) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[121]++;
            result = new Point2D.Double(x3, y6);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[215]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[122]++;}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}

        return result;

    }

    /**
     * Returns <code>true</code> if the specified anchor point is inside a bar.
     * 
     * @param anchor  the anchor point.
     * 
     * @return A boolean.
     */
    private boolean isInternalAnchor(ItemLabelAnchor anchor) {
        return anchor == ItemLabelAnchor.CENTER 
               || anchor == ItemLabelAnchor.INSIDE1
               || anchor == ItemLabelAnchor.INSIDE2
               || anchor == ItemLabelAnchor.INSIDE3
               || anchor == ItemLabelAnchor.INSIDE4
               || anchor == ItemLabelAnchor.INSIDE5
               || anchor == ItemLabelAnchor.INSIDE6
               || anchor == ItemLabelAnchor.INSIDE7
               || anchor == ItemLabelAnchor.INSIDE8
               || anchor == ItemLabelAnchor.INSIDE9
               || anchor == ItemLabelAnchor.INSIDE10
               || anchor == ItemLabelAnchor.INSIDE11
               || anchor == ItemLabelAnchor.INSIDE12;  
    }
    
    /**
     * Returns the lower and upper bounds (range) of the x-values in the 
     * specified dataset.  Since this renderer uses the x-interval in the 
     * dataset, this is taken into account for the range.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is 
     *         <code>null</code> or empty).
     */
    public Range findDomainBounds(XYDataset dataset) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[216]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[123]++;
            return DatasetUtilities.findDomainBounds(dataset, true);

        }
        else {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[124]++;
            return null;
        }
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[217]++;
        XYBarRenderer result = (XYBarRenderer) super.clone();
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[218]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.gradientPaintTransformer != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[125]++;
            result.gradientPaintTransformer = (GradientPaintTransformer)
                ObjectUtilities.clone(this.gradientPaintTransformer);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[219]++;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[126]++;}
        result.legendBar = ShapeUtilities.clone(this.legendBar);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[220]++;
        return result;
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[221]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[127]++;
            return true;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[128]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[222]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((obj instanceof XYBarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[129]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[130]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[223]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[131]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[132]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[224]++;
        XYBarRenderer that = (XYBarRenderer) obj;
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[225]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.base != that.base) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[133]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[134]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[226]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.drawBarOutline != that.drawBarOutline) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[135]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[136]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[227]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.margin != that.margin) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[137]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[138]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[228]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((this.useYInterval != that.useYInterval) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[139]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[140]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[229]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.gradientPaintTransformer, that.gradientPaintTransformer)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)
        ) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[141]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[142]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[230]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendBar, that.legendBar)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[143]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[144]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[231]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.positiveItemLabelPositionFallback,
                that.positiveItemLabelPositionFallback)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[145]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[146]++;}
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[232]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.negativeItemLabelPositionFallback,
                that.negativeItemLabelPositionFallback)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[147]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.branches[148]++;}        
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
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[233]++;
        this.legendBar = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[234]++;
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
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[235]++;
        SerialUtilities.writeShape(this.legendBar, stream);
CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1.statements[236]++;
    }

}

class CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1 ());
  }
    public static long[] statements = new long[237];
    public static long[] branches = new long[149];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[75];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 74; i++) {
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

  public CodeCoverCoverageCounter$7chr3n9bwnhbvo8824bc49wz4rc1 () {
    super("org.jfree.chart.renderer.xy.XYBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 236; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 148; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 74; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYBarRenderer.java");
      for (int i = 1; i <= 236; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 148; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 74; i++) {
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

