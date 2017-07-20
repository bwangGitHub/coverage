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
 * ---------------------------
 * AbstractXYItemRenderer.java
 * ---------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Focus Computer Services Limited;
 *                   Tim Bardzil;
 *                   Sergei Ivanov;
 *
 * Changes:
 * --------
 * 15-Mar-2002 : Version 1 (DG);
 * 09-Apr-2002 : Added a getToolTipGenerator() method reflecting the change in
 *               the XYItemRenderer interface (DG);
 * 05-Aug-2002 : Added a urlGenerator member variable to support HTML image
 *               maps (RA);
 * 20-Aug-2002 : Added property change events for the tooltip and URL
 *               generators (DG);
 * 22-Aug-2002 : Moved property change support into AbstractRenderer class (DG);
 * 23-Sep-2002 : Fixed errors reported by Checkstyle tool (DG);
 * 18-Nov-2002 : Added methods for drawing grid lines (DG);
 * 17-Jan-2003 : Moved plot classes into a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified initialise() return type and drawItem() method
 *               signature (DG);
 * 15-May-2003 : Modified to take into account the plot orientation (DG);
 * 21-May-2003 : Added labels to markers (DG);
 * 05-Jun-2003 : Added domain and range grid bands (sponsored by Focus Computer
 *               Services Ltd) (DG);
 * 27-Jul-2003 : Added getRangeType() to support stacked XY area charts (RA);
 * 31-Jul-2003 : Deprecated all but the default constructor (DG);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 05-Nov-2003 : Fixed marker rendering bug (833623) (DG);
 * 11-Feb-2004 : Updated labelling for markers (DG);
 * 25-Feb-2004 : Added updateCrosshairValues() method.  Moved deprecated code
 *               to bottom of source file (DG);
 * 16-Apr-2004 : Added support for IntervalMarker in drawRangeMarker() method
 *               - thanks to Tim Bardzil (DG);
 * 05-May-2004 : Fixed bug (948310) where interval markers extend beyond axis
 *               range (DG);
 * 03-Jun-2004 : Fixed more bugs in drawing interval markers (DG);
 * 26-Aug-2004 : Added the addEntity() method (DG);
 * 29-Sep-2004 : Added annotation support (with layers) (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities -->
 *               TextUtilities (DG);
 * 06-Oct-2004 : Added findDomainBounds() method and renamed
 *               getRangeExtent() --> findRangeBounds() (DG);
 * 07-Jan-2005 : Removed deprecated code (DG);
 * 27-Jan-2005 : Modified getLegendItem() to omit hidden series (DG);
 * 24-Feb-2005 : Added getLegendItems() method (DG);
 * 08-Mar-2005 : Fixed positioning of marker labels (DG);
 * 20-Apr-2005 : Renamed XYLabelGenerator --> XYItemLabelGenerator and
 *               added generators for legend labels, tooltips and URLs (DG);
 * 01-Jun-2005 : Handle one dimension of the marker label adjustment
 *               automatically (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Set dataset and series indices in LegendItem (DG);
 * 24-Oct-2006 : Respect alpha setting in markers (see patch 1567843 by Sergei
 *               Ivanov) (DG);
 * 24-Oct-2006 : Added code to draw outlines for interval markers (DG);
 * 24-Nov-2006 : Fixed cloning for legend item generators (DG);
 * 06-Feb-2007 : Added new updateCrosshairValues() method that takes into
 *               account multiple axis plots (see bug 1086307) (DG);
 * 20-Feb-2007 : Fixed equals() method implementation (DG);
 * 01-Mar-2007 : Fixed interval marker drawing (patch 1670686 thanks to 
 *               Sergei Ivanov) (DG);
 * 22-Mar-2007 : Modified the tool tip generator look up (DG);
 * 23-Mar-2007 : Added drawDomainLine() method (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change, and deprecated
 *               itemLabelGenerator and toolTipGenerator override fields (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 12-Nov-2007 : Fixed domain and range band drawing methods (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.text.TextUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectList;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A base class that can be used to create new {@link XYItemRenderer}
 * implementations.
 */
public abstract class AbstractXYItemRenderer extends AbstractRenderer
                                             implements XYItemRenderer,
                                                        Cloneable,
                                                        Serializable {
  static {
    CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8019124836026607990L;
  static {
    CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[1]++;
  }

    /** The plot. */
    private XYPlot plot;

    /** 
     * The item label generator for ALL series.
     * 
     * @deprecated This field is redundant, use itemLabelGeneratorList and
     *     baseItemLabelGenerator instead.  Deprecated as of version 1.0.6.
     */
    private XYItemLabelGenerator itemLabelGenerator;

    /** A list of item label generators (one per series). */
    private ObjectList itemLabelGeneratorList;

    /** The base item label generator. */
    private XYItemLabelGenerator baseItemLabelGenerator;

    /** 
     * The tool tip generator for ALL series. 
     * 
     * @deprecated This field is redundant, use tooltipGeneratorList and
     *     baseToolTipGenerator instead.  Deprecated as of version 1.0.6.
     */
    private XYToolTipGenerator toolTipGenerator;

    /** A list of tool tip generators (one per series). */
    private ObjectList toolTipGeneratorList;

    /** The base tool tip generator. */
    private XYToolTipGenerator baseToolTipGenerator;

    /** The URL text generator. */
    private XYURLGenerator urlGenerator;

    /**
     * Annotations to be drawn in the background layer ('underneath' the data
     * items).
     */
    private List backgroundAnnotations;

    /**
     * Annotations to be drawn in the foreground layer ('on top' of the data
     * items).
     */
    private List foregroundAnnotations;

    /** The default radius for the entity 'hotspot' */
    private int defaultEntityRadius;

    /** The legend item label generator. */
    private XYSeriesLabelGenerator legendItemLabelGenerator;

    /** The legend item tool tip generator. */
    private XYSeriesLabelGenerator legendItemToolTipGenerator;

    /** The legend item URL generator. */
    private XYSeriesLabelGenerator legendItemURLGenerator;

    /**
     * Creates a renderer where the tooltip generator and the URL generator are
     * both <code>null</code>.
     */
    protected AbstractXYItemRenderer() {
        super();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[2]++;
        this.itemLabelGenerator = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[3]++;
        this.itemLabelGeneratorList = new ObjectList();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[4]++;
        this.toolTipGenerator = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[5]++;
        this.toolTipGeneratorList = new ObjectList();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[6]++;
        this.urlGenerator = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[7]++;
        this.backgroundAnnotations = new java.util.ArrayList();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[8]++;
        this.foregroundAnnotations = new java.util.ArrayList();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[9]++;
        this.defaultEntityRadius = 3;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[10]++;
        this.legendItemLabelGenerator = new StandardXYSeriesLabelGenerator(
                "{0}");
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[11]++;
    }

    /**
     * Returns the number of passes through the data that the renderer requires
     * in order to draw the chart.  Most charts will require a single pass, but
     * some require two passes.
     *
     * @return The pass count.
     */
    public int getPassCount() {
        return 1;
    }

    /**
     * Returns the plot that the renderer is assigned to.
     *
     * @return The plot (possibly <code>null</code>).
     */
    public XYPlot getPlot() {
        return this.plot;
    }

    /**
     * Sets the plot that the renderer is assigned to.
     *
     * @param plot  the plot (<code>null</code> permitted).
     */
    public void setPlot(XYPlot plot) {
        this.plot = plot;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[12]++;
    }

    /**
     * Initialises the renderer and returns a state object that should be
     * passed to all subsequent calls to the drawItem() method.
     * <P>
     * This method will be called before the first item is rendered, giving the
     * renderer an opportunity to initialise any state information it wants to
     * maintain.  The renderer can do nothing if it chooses.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to
     *              the caller.
     *
     * @return The renderer state (never <code>null</code>).
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset data,
                                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[13]++;

        XYItemRendererState state = new XYItemRendererState(info);
        return state;

    }

    // ITEM LABEL GENERATOR

    /**
     * Returns the label generator for a data item.  This implementation simply
     * passes control to the {@link #getSeriesItemLabelGenerator(int)} method.
     * If, for some reason, you want a different generator for individual
     * items, you can override this method.
     *
     * @param series  the series index (zero based).
     * @param item  the item index (zero based).
     *
     * @return The generator (possibly <code>null</code>).
     */
    public XYItemLabelGenerator getItemLabelGenerator(int series, int item) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        // return the generator for ALL series, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.itemLabelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[1]++;
            return this.itemLabelGenerator;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[2]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[15]++;

        // otherwise look up the generator table
        XYItemLabelGenerator generator
            = (XYItemLabelGenerator) this.itemLabelGeneratorList.get(series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[3]++;
            generator = this.baseItemLabelGenerator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[17]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[4]++;}
        return generator;
    }

    /**
     * Returns the item label generator for a series.
     *
     * @param series  the series index (zero based).
     *
     * @return The generator (possibly <code>null</code>).
     */
    public XYItemLabelGenerator getSeriesItemLabelGenerator(int series) {
        return (XYItemLabelGenerator) this.itemLabelGeneratorList.get(series);
    }

    /**
     * Returns the item label generator override.
     * 
     * @return The generator (possibly <code>null</code>).
     * 
     * @since 1.0.5
     * 
     * @see #setItemLabelGenerator(XYItemLabelGenerator)
     * 
     * @deprecated As of version 1.0.6, this override setting should not be
     *     used.  You can use the base setting instead 
     *     ({@link #getBaseItemLabelGenerator()}).
     */
    public XYItemLabelGenerator getItemLabelGenerator() {
        return this.itemLabelGenerator;    
    }
    
    /**
     * Sets the item label generator for ALL series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getItemLabelGenerator()
     * 
     * @deprecated As of version 1.0.6, this override setting should not be
     *     used.  You can use the base setting instead 
     *     ({@link #setBaseItemLabelGenerator(XYItemLabelGenerator)}).
     */
    public void setItemLabelGenerator(XYItemLabelGenerator generator) {
        this.itemLabelGenerator = generator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[18]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[19]++;
    }

    /**
     * Sets the item label generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero based).
     * @param generator  the generator (<code>null</code> permitted).
     */
    public void setSeriesItemLabelGenerator(int series,
                                            XYItemLabelGenerator generator) {
        this.itemLabelGeneratorList.set(series, generator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[20]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[21]++;
    }

    /**
     * Returns the base item label generator.
     *
     * @return The generator (possibly <code>null</code>).
     */
    public XYItemLabelGenerator getBaseItemLabelGenerator() {
        return this.baseItemLabelGenerator;
    }

    /**
     * Sets the base item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     */
    public void setBaseItemLabelGenerator(XYItemLabelGenerator generator) {
        this.baseItemLabelGenerator = generator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[22]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[23]++;
    }

    // TOOL TIP GENERATOR

    /**
     * Returns the tool tip generator for a data item.  If, for some reason, 
     * you want a different generator for individual items, you can override 
     * this method.
     *
     * @param series  the series index (zero based).
     * @param item  the item index (zero based).
     *
     * @return The generator (possibly <code>null</code>).
     */
    public XYToolTipGenerator getToolTipGenerator(int series, int item) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        // return the generator for ALL series, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.toolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[5]++;
            return this.toolTipGenerator;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[6]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[25]++;

        // otherwise look up the generator table
        XYToolTipGenerator generator
                = (XYToolTipGenerator) this.toolTipGeneratorList.get(series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[7]++;
            generator = this.baseToolTipGenerator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[27]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[8]++;}
        return generator;
    }

    /**
     * Returns the override tool tip generator.
     * 
     * @return The tool tip generator (possible <code>null</code>).
     * 
     * @since 1.0.5
     * 
     * @see #setToolTipGenerator(XYToolTipGenerator)
     * 
     * @deprecated As of version 1.0.6, this override setting should not be
     *     used.  You can use the base setting instead 
     *     ({@link #getBaseToolTipGenerator()}).
     */
    public XYToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }
    
    /**
     * Sets the tool tip generator for ALL series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getToolTipGenerator()
     * 
     * @deprecated As of version 1.0.6, this override setting should not be
     *     used.  You can use the base setting instead 
     *     ({@link #setBaseToolTipGenerator(XYToolTipGenerator)}).
     */
    public void setToolTipGenerator(XYToolTipGenerator generator) {
        this.toolTipGenerator = generator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[28]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[29]++;
    }

    /**
     * Returns the tool tip generator for a series.
     *
     * @param series  the series index (zero based).
     *
     * @return The generator (possibly <code>null</code>).
     */
    public XYToolTipGenerator getSeriesToolTipGenerator(int series) {
        return (XYToolTipGenerator) this.toolTipGeneratorList.get(series);
    }

    /**
     * Sets the tool tip generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero based).
     * @param generator  the generator (<code>null</code> permitted).
     */
    public void setSeriesToolTipGenerator(int series,
                                          XYToolTipGenerator generator) {
        this.toolTipGeneratorList.set(series, generator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[30]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[31]++;
    }

    /**
     * Returns the base tool tip generator.
     *
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setBaseToolTipGenerator(XYToolTipGenerator)
     */
    public XYToolTipGenerator getBaseToolTipGenerator() {
        return this.baseToolTipGenerator;
    }

    /**
     * Sets the base tool tip generator and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getBaseToolTipGenerator()
     */
    public void setBaseToolTipGenerator(XYToolTipGenerator generator) {
        this.baseToolTipGenerator = generator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[32]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[33]++;
    }

    // URL GENERATOR

    /**
     * Returns the URL generator for HTML image maps.
     *
     * @return The URL generator (possibly <code>null</code>).
     */
    public XYURLGenerator getURLGenerator() {
        return this.urlGenerator;
    }

    /**
     * Sets the URL generator for HTML image maps and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param urlGenerator  the URL generator (<code>null</code> permitted).
     */
    public void setURLGenerator(XYURLGenerator urlGenerator) {
        this.urlGenerator = urlGenerator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[34]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[35]++;
    }

    /**
     * Adds an annotation and sends a {@link RendererChangeEvent} to all
     * registered listeners.  The annotation is added to the foreground
     * layer.
     *
     * @param annotation  the annotation (<code>null</code> not permitted).
     */
    public void addAnnotation(XYAnnotation annotation) {
        // defer argument checking
        addAnnotation(annotation, Layer.FOREGROUND);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[36]++;
    }

    /**
     * Adds an annotation to the specified layer and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param annotation  the annotation (<code>null</code> not permitted).
     * @param layer  the layer (<code>null</code> not permitted).
     */
    public void addAnnotation(XYAnnotation annotation, Layer layer) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[9]++;
            throw new IllegalArgumentException("Null 'annotation' argument.");

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[10]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((layer.equals(Layer.FOREGROUND)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[11]++;
            this.foregroundAnnotations.add(annotation);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[39]++;
            fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[40]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[12]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[41]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((layer.equals(Layer.BACKGROUND)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[13]++;
            this.backgroundAnnotations.add(annotation);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[42]++;
            fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[43]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[14]++;
            // should never get here
            throw new RuntimeException("Unknown layer.");
        }
}
    }
    /**
     * Removes the specified annotation and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param annotation  the annotation to remove (<code>null</code> not
     *                    permitted).
     *
     * @return A boolean to indicate whether or not the annotation was
     *         successfully removed.
     */
    public boolean removeAnnotation(XYAnnotation annotation) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[44]++;
        boolean removed = this.foregroundAnnotations.remove(annotation);
        removed = removed & this.backgroundAnnotations.remove(annotation);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[45]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[46]++;
        return removed;
    }

    /**
     * Removes all annotations and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     */
    public void removeAnnotations() {
        this.foregroundAnnotations.clear();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[47]++;
        this.backgroundAnnotations.clear();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[48]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[49]++;
    }

    /**
     * Returns the radius of the circle used for the default entity area
     * when no area is specified.
     *
     * @return A radius.
     */
    public int getDefaultEntityRadius() {
        return this.defaultEntityRadius;
    }

    /**
     * Sets the radius of the circle used for the default entity area
     * when no area is specified.
     *
     * @param radius  the radius.
     */
    public void setDefaultEntityRadius(int radius) {
        this.defaultEntityRadius = radius;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[50]++;
    }

    /**
     * Returns the legend item label generator.
     *
     * @return The label generator (never <code>null</code>).
     *
     * @see #setLegendItemLabelGenerator(XYSeriesLabelGenerator)
     */
    public XYSeriesLabelGenerator getLegendItemLabelGenerator() {
        return this.legendItemLabelGenerator;
    }

    /**
     * Sets the legend item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> not permitted).
     *
     * @see #getLegendItemLabelGenerator()
     */
    public void setLegendItemLabelGenerator(XYSeriesLabelGenerator generator) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[51]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[15]++;
            throw new IllegalArgumentException("Null 'generator' argument.");

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[16]++;}
        this.legendItemLabelGenerator = generator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[52]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[53]++;
    }

    /**
     * Returns the legend item tool tip generator.
     *
     * @return The tool tip generator (possibly <code>null</code>).
     *
     * @see #setLegendItemToolTipGenerator(XYSeriesLabelGenerator)
     */
    public XYSeriesLabelGenerator getLegendItemToolTipGenerator() {
        return this.legendItemToolTipGenerator;
    }

    /**
     * Sets the legend item tool tip generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getLegendItemToolTipGenerator()
     */
    public void setLegendItemToolTipGenerator(
            XYSeriesLabelGenerator generator) {
        this.legendItemToolTipGenerator = generator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[54]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[55]++;
    }

    /**
     * Returns the legend item URL generator.
     *
     * @return The URL generator (possibly <code>null</code>).
     *
     * @see #setLegendItemURLGenerator(XYSeriesLabelGenerator)
     */
    public XYSeriesLabelGenerator getLegendItemURLGenerator() {
        return this.legendItemURLGenerator;
    }

    /**
     * Sets the legend item URL generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getLegendItemURLGenerator()
     */
    public void setLegendItemURLGenerator(XYSeriesLabelGenerator generator) {
        this.legendItemURLGenerator = generator;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[56]++;
        fireChangeEvent();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[57]++;
    }

    /**
     * Returns the lower and upper bounds (range) of the x-values in the
     * specified dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     */
    public Range findDomainBounds(XYDataset dataset) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[58]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[17]++;
            return DatasetUtilities.findDomainBounds(dataset, false);

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[18]++;
            return null;
        }
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     */
    public Range findRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[19]++;
            return DatasetUtilities.findRangeBounds(dataset, false);

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[20]++;
            return null;
        }
    }

    /**
     * Returns a (possibly empty) collection of legend items for the series
     * that this renderer is responsible for drawing.
     *
     * @return The legend item collection (never <code>null</code>).
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[60]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.plot == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[21]++;
            return new LegendItemCollection();

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[22]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[61]++;
        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[62]++;
        int index = this.plot.getIndexOf(this);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[63]++;
        XYDataset dataset = this.plot.getDataset(index);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[64]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[23]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[65]++;
            int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[66]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[1]--;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[2]--;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[3]++;
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(i)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[25]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[68]++;
                    LegendItem item = getLegendItem(index, i);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[69]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[27]++;
                        result.add(item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[70]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[28]++;}

                } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[26]++;}
            }


        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[24]++;}
        return result;
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
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[71]++;
        LegendItem result = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[72]++;
        XYPlot xyplot = getPlot();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[73]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((xyplot != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[29]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[74]++;
            XYDataset dataset = xyplot.getDataset(datasetIndex);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[75]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[31]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[76]++;
                String label = this.legendItemLabelGenerator.generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[77]++;
                String description = label;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[78]++;
                String toolTipText = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[79]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[33]++;
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[80]++;

                } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[34]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[81]++;
                String urlText = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[82]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[35]++;
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[83]++;

                } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[36]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[84]++;
                Shape shape = lookupSeriesShape(series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[85]++;
                Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[86]++;
                Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[87]++;
                Stroke outlineStroke = lookupSeriesOutlineStroke(series);
                result = new LegendItem(label, description, toolTipText,
                        urlText, shape, paint, outlineStroke, outlinePaint);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[88]++;
                result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[89]++;
                result.setSeriesIndex(series);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[90]++;
                result.setDataset(dataset);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[91]++;
                result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[92]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[30]++;}
        return result;
    }

    /**
     * Fills a band between two values on the axis.  This can be used to color
     * bands between the grid lines.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the domain axis.
     * @param dataArea  the data area.
     * @param start  the start value.
     * @param end  the end value.
     */
    public void fillDomainGridBand(Graphics2D g2, XYPlot plot, ValueAxis axis,
            Rectangle2D dataArea, double start, double end) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[93]++;

        double x1 = axis.valueToJava2D(start, dataArea,
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[94]++;
        double x2 = axis.valueToJava2D(end, dataArea,
                plot.getDomainAxisEdge());
        Rectangle2D band;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[95]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[37]++;
            band = new Rectangle2D.Double(Math.min(x1, x2), dataArea.getMinY(), 
                    Math.abs(x2 - x1), dataArea.getWidth());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[96]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[38]++;
            band = new Rectangle2D.Double(dataArea.getMinX(), Math.min(x1, x2), 
                    dataArea.getWidth(), Math.abs(x2 - x1));
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[97]++;
        }
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[98]++;
        Paint paint = plot.getDomainTickBandPaint();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[99]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[39]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[100]++;
            g2.fill(band);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[101]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[40]++;}

    }

    /**
     * Fills a band between two values on the range axis.  This can be used to
     * color bands between the grid lines.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the range axis.
     * @param dataArea  the data area.
     * @param start  the start value.
     * @param end  the end value.
     */
    public void fillRangeGridBand(Graphics2D g2, XYPlot plot, ValueAxis axis,
            Rectangle2D dataArea, double start, double end) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[102]++;

        double y1 = axis.valueToJava2D(start, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[103]++;
        double y2 = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
        Rectangle2D band;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[104]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[41]++;
            band = new Rectangle2D.Double(dataArea.getMinX(), Math.min(y1, y2),
                dataArea.getWidth(), Math.abs(y2 - y1));
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[105]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[42]++;
            band = new Rectangle2D.Double(Math.min(y1, y2), dataArea.getMinY(),
                    Math.abs(y2 - y1), dataArea.getHeight());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[106]++;
        }
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[107]++;
        Paint paint = plot.getRangeTickBandPaint();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[108]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[43]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[109]++;
            g2.fill(band);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[110]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[44]++;}

    }

    /**
     * Draws a grid line against the range axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param dataArea  the area for plotting data (not yet adjusted for any
     *                  3D effect).
     * @param value  the value at which the grid line should be drawn.
     */
    public void drawDomainGridLine(Graphics2D g2,
                                   XYPlot plot,
                                   ValueAxis axis,
                                   Rectangle2D dataArea,
                                   double value) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[111]++;

        Range range = axis.getRange();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[112]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[45]++;
            return;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[46]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[113]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[114]++;
        double v = axis.valueToJava2D(value, dataArea,
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[115]++;
        Line2D line = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[116]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[47]++;
            line = new Line2D.Double(dataArea.getMinX(), v,
                    dataArea.getMaxX(), v);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[117]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[48]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[118]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[49]++;
            line = new Line2D.Double(v, dataArea.getMinY(), v,
                    dataArea.getMaxY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[119]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[50]++;}
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[120]++;

        Paint paint = plot.getDomainGridlinePaint();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[121]++;
        Stroke stroke = plot.getDomainGridlineStroke();
        g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[122]++;
        g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[123]++;
        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[124]++;

    }

    /**
     * Draws a line perpendicular to the domain axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param dataArea  the area for plotting data (not yet adjusted for any 3D
     *                  effect).
     * @param value  the value at which the grid line should be drawn.
     * @param paint  the paint.
     * @param stroke  the stroke.
     * 
     * @since 1.0.5
     */
    public void drawDomainLine(Graphics2D g2, XYPlot plot, ValueAxis axis,
            Rectangle2D dataArea, double value, Paint paint, Stroke stroke) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[125]++;

        Range range = axis.getRange();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[126]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[51]++;
            return;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[52]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[127]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[128]++;
        Line2D line = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[129]++;
        double v = axis.valueToJava2D(value, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[130]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[53]++;
            line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), 
                    v);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[131]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[54]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[132]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[55]++;
            line = new Line2D.Double(v, dataArea.getMinY(), v, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[133]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[56]++;}
}

        g2.setPaint(paint);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[134]++;
        g2.setStroke(stroke);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[135]++;
        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[136]++;

    }

    /**
     * Draws a line perpendicular to the range axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param dataArea  the area for plotting data (not yet adjusted for any 3D
     *                  effect).
     * @param value  the value at which the grid line should be drawn.
     * @param paint  the paint.
     * @param stroke  the stroke.
     */
    public void drawRangeLine(Graphics2D g2,
                              XYPlot plot,
                              ValueAxis axis,
                              Rectangle2D dataArea,
                              double value,
                              Paint paint,
                              Stroke stroke) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[137]++;

        Range range = axis.getRange();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[138]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[57]++;
            return;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[58]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[139]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[140]++;
        Line2D line = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[141]++;
        double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[142]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[59]++;
            line = new Line2D.Double(v, dataArea.getMinY(), v,
                    dataArea.getMaxY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[143]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[60]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[144]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[61]++;
            line = new Line2D.Double(dataArea.getMinX(), v,
                    dataArea.getMaxX(), v);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[145]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[62]++;}
}

        g2.setPaint(paint);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[146]++;
        g2.setStroke(stroke);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[147]++;
        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[148]++;

    }

    /**
     * Draws a vertical line on the chart to represent a 'range marker'.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param marker  the marker line.
     * @param dataArea  the axis data area.
     */
    public void drawDomainMarker(Graphics2D g2,
                                 XYPlot plot,
                                 ValueAxis domainAxis,
                                 Marker marker,
                                 Rectangle2D dataArea) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[149]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((marker instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[63]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[150]++;
            ValueMarker vm = (ValueMarker) marker;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[151]++;
            double value = vm.getValue();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[152]++;
            Range range = domainAxis.getRange();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[153]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[65]++;
                return;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[66]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[154]++;

            double v = domainAxis.valueToJava2D(value, dataArea,
                    plot.getDomainAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[155]++;

            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[156]++;
            Line2D line = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[157]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[67]++;
                line = new Line2D.Double(dataArea.getMinX(), v,
                        dataArea.getMaxX(), v);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[158]++;

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[68]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[159]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[69]++;
                line = new Line2D.Double(v, dataArea.getMinY(), v,
                        dataArea.getMaxY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[160]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[70]++;}
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[161]++;

            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, marker.getAlpha()));
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[162]++;
            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[163]++;
            g2.setStroke(marker.getStroke());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[164]++;
            g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[165]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[166]++;

            String label = marker.getLabel();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[167]++;
            RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[168]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[71]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[169]++;
                Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[170]++;
                g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[171]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[172]++;
                Point2D coordinates = calculateDomainMarkerTextAnchorPoint(
                        g2, orientation, dataArea, line.getBounds2D(),
                        marker.getLabelOffset(),
                        LengthAdjustmentType.EXPAND, anchor);
                TextUtilities.drawAlignedString(label, g2,
                        (float) coordinates.getX(), (float) coordinates.getY(),
                        marker.getLabelTextAnchor());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[173]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[72]++;}
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[174]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[64]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[175]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((marker instanceof IntervalMarker) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[73]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[176]++;
            IntervalMarker im = (IntervalMarker) marker;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[177]++;
            double start = im.getStartValue();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[178]++;
            double end = im.getEndValue();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[179]++;
            Range range = domainAxis.getRange();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[180]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((range.intersects(start, end)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[75]++;
                return;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[76]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[181]++;

            double start2d = domainAxis.valueToJava2D(start, dataArea,
                    plot.getDomainAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[182]++;
            double end2d = domainAxis.valueToJava2D(end, dataArea,
                    plot.getDomainAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[183]++;
            double low = Math.min(start2d, end2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[184]++;
            double high = Math.max(start2d, end2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[185]++;

            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[186]++;
            Rectangle2D rect = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[187]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[77]++;
                // clip top and bottom bounds to data area
                low = Math.max(low, dataArea.getMinY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[188]++;
                high = Math.min(high, dataArea.getMaxY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[189]++;
                rect = new Rectangle2D.Double(dataArea.getMinX(),
                        low, dataArea.getWidth(),
                        high - low);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[190]++;

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[78]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[191]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[79]++;
                // clip left and right bounds to data area
                low = Math.max(low, dataArea.getMinX());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[192]++;
                high = Math.min(high, dataArea.getMaxX());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[193]++;
                rect = new Rectangle2D.Double(low,
                        dataArea.getMinY(), high - low,
                        dataArea.getHeight());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[194]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[80]++;}
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[195]++;

            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, marker.getAlpha()));
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[196]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[197]++;
            Paint p = marker.getPaint();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[198]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((p instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[81]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[199]++;
                GradientPaint gp = (GradientPaint) p;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[200]++;
                GradientPaintTransformer t = im.getGradientPaintTransformer();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[201]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[83]++;
                    gp = t.transform(gp, rect);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[202]++;

                } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[84]++;}
                g2.setPaint(gp);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[203]++;

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[82]++;
                g2.setPaint(p);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[204]++;
            }
            g2.fill(rect);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[205]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[206]++;
int CodeCoverConditionCoverageHelper_C44;

            // now draw the outlines, if visible...
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((im.getOutlinePaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((im.getOutlineStroke() != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[85]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[207]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[87]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[208]++;
                    Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[209]++;
                    double y0 = dataArea.getMinY();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[210]++;
                    double y1 = dataArea.getMaxY();
                    g2.setPaint(im.getOutlinePaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[211]++;
                    g2.setStroke(im.getOutlineStroke());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[212]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[213]++;
int CodeCoverConditionCoverageHelper_C46;
                    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((range.contains(start)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[89]++;
                        line.setLine(start2d, y0, start2d, y1);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[214]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[215]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[90]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[216]++;
int CodeCoverConditionCoverageHelper_C47;
                    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((range.contains(end)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[91]++;
                        line.setLine(end2d, y0, end2d, y1);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[217]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[218]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[92]++;}

                }
                else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[88]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[219]++; // PlotOrientation.HORIZONTAL
                    Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[220]++;
                    double x0 = dataArea.getMinX();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[221]++;
                    double x1 = dataArea.getMaxX();
                    g2.setPaint(im.getOutlinePaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[222]++;
                    g2.setStroke(im.getOutlineStroke());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[223]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[224]++;
int CodeCoverConditionCoverageHelper_C48;
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((range.contains(start)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[93]++;
                        line.setLine(x0, start2d, x1, start2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[225]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[226]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[94]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[227]++;
int CodeCoverConditionCoverageHelper_C49;
                    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((range.contains(end)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[95]++;
                        line.setLine(x0, end2d, x1, end2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[228]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[229]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[96]++;}
                }

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[86]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[230]++;

            String label = marker.getLabel();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[231]++;
            RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[232]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[97]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[233]++;
                Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[234]++;
                g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[235]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[236]++;
                Point2D coordinates = calculateDomainMarkerTextAnchorPoint(
                        g2, orientation, dataArea, rect,
                        marker.getLabelOffset(), marker.getLabelOffsetType(),
                        anchor);
                TextUtilities.drawAlignedString(label, g2,
                        (float) coordinates.getX(), (float) coordinates.getY(),
                        marker.getLabelTextAnchor());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[237]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[98]++;}
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[238]++;


        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[74]++;}
}

    }

    /**
     * Calculates the (x, y) coordinates for drawing a marker label.
     *
     * @param g2  the graphics device.
     * @param orientation  the plot orientation.
     * @param dataArea  the data area.
     * @param markerArea  the rectangle surrounding the marker area.
     * @param markerOffset  the marker label offset.
     * @param labelOffsetType  the label offset type.
     * @param anchor  the label anchor.
     *
     * @return The coordinates for drawing the marker label.
     */
    protected Point2D calculateDomainMarkerTextAnchorPoint(Graphics2D g2,
            PlotOrientation orientation,
            Rectangle2D dataArea,
            Rectangle2D markerArea,
            RectangleInsets markerOffset,
            LengthAdjustmentType labelOffsetType,
            RectangleAnchor anchor) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[239]++;

        Rectangle2D anchorRect = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[240]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[99]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetType);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[241]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[100]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[242]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[101]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    labelOffsetType, LengthAdjustmentType.CONTRACT);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[243]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[102]++;}
}
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Draws a horizontal line across the chart to represent a 'range marker'.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param rangeAxis  the range axis.
     * @param marker  the marker line.
     * @param dataArea  the axis data area.
     */
    public void drawRangeMarker(Graphics2D g2,
                                XYPlot plot,
                                ValueAxis rangeAxis,
                                Marker marker,
                                Rectangle2D dataArea) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[244]++;
int CodeCoverConditionCoverageHelper_C53;

        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((marker instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[103]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[245]++;
            ValueMarker vm = (ValueMarker) marker;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[246]++;
            double value = vm.getValue();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[247]++;
            Range range = rangeAxis.getRange();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[248]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[105]++;
                return;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[106]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[249]++;

            double v = rangeAxis.valueToJava2D(value, dataArea,
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[250]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[251]++;
            Line2D line = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[252]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[107]++;
                line = new Line2D.Double(v, dataArea.getMinY(), v,
                        dataArea.getMaxY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[253]++;

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[108]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[254]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[109]++;
                line = new Line2D.Double(dataArea.getMinX(), v,
                        dataArea.getMaxX(), v);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[255]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[110]++;}
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[256]++;

            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, marker.getAlpha()));
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[257]++;
            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[258]++;
            g2.setStroke(marker.getStroke());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[259]++;
            g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[260]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[261]++;

            String label = marker.getLabel();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[262]++;
            RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[263]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[111]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[264]++;
                Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[265]++;
                g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[266]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[267]++;
                Point2D coordinates = calculateRangeMarkerTextAnchorPoint(
                        g2, orientation, dataArea, line.getBounds2D(),
                        marker.getLabelOffset(),
                        LengthAdjustmentType.EXPAND, anchor);
                TextUtilities.drawAlignedString(label, g2,
                        (float) coordinates.getX(), (float) coordinates.getY(),
                        marker.getLabelTextAnchor());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[268]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[112]++;}
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[269]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[104]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[270]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((marker instanceof IntervalMarker) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[113]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[271]++;
            IntervalMarker im = (IntervalMarker) marker;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[272]++;
            double start = im.getStartValue();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[273]++;
            double end = im.getEndValue();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[274]++;
            Range range = rangeAxis.getRange();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[275]++;
int CodeCoverConditionCoverageHelper_C59;
            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((range.intersects(start, end)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[115]++;
                return;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[116]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[276]++;

            double start2d = rangeAxis.valueToJava2D(start, dataArea,
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[277]++;
            double end2d = rangeAxis.valueToJava2D(end, dataArea,
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[278]++;
            double low = Math.min(start2d, end2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[279]++;
            double high = Math.max(start2d, end2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[280]++;

            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[281]++;
            Rectangle2D rect = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[282]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[117]++;
                // clip left and right bounds to data area
                low = Math.max(low, dataArea.getMinX());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[283]++;
                high = Math.min(high, dataArea.getMaxX());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[284]++;
                rect = new Rectangle2D.Double(low,
                        dataArea.getMinY(), high - low,
                        dataArea.getHeight());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[285]++;

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[118]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[286]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[119]++;
                // clip top and bottom bounds to data area
                low = Math.max(low, dataArea.getMinY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[287]++;
                high = Math.min(high, dataArea.getMaxY());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[288]++;
                rect = new Rectangle2D.Double(dataArea.getMinX(),
                        low, dataArea.getWidth(),
                        high - low);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[289]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[120]++;}
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[290]++;

            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, marker.getAlpha()));
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[291]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[292]++;
            Paint p = marker.getPaint();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[293]++;
int CodeCoverConditionCoverageHelper_C62;
            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((p instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[121]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[294]++;
                GradientPaint gp = (GradientPaint) p;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[295]++;
                GradientPaintTransformer t = im.getGradientPaintTransformer();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[296]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[123]++;
                    gp = t.transform(gp, rect);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[297]++;

                } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[124]++;}
                g2.setPaint(gp);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[298]++;

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[122]++;
                g2.setPaint(p);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[299]++;
            }
            g2.fill(rect);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[300]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[301]++;
int CodeCoverConditionCoverageHelper_C64;

            // now draw the outlines, if visible...
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((im.getOutlinePaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((im.getOutlineStroke() != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[125]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[302]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[127]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[303]++;
                    Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[304]++;
                    double x0 = dataArea.getMinX();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[305]++;
                    double x1 = dataArea.getMaxX();
                    g2.setPaint(im.getOutlinePaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[306]++;
                    g2.setStroke(im.getOutlineStroke());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[307]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[308]++;
int CodeCoverConditionCoverageHelper_C66;
                    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((range.contains(start)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[129]++;
                        line.setLine(x0, start2d, x1, start2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[309]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[310]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[130]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[311]++;
int CodeCoverConditionCoverageHelper_C67;
                    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((range.contains(end)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[131]++;
                        line.setLine(x0, end2d, x1, end2d);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[312]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[313]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[132]++;}

                }
                else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[128]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[314]++; // PlotOrientation.HORIZONTAL
                    Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[315]++;
                    double y0 = dataArea.getMinY();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[316]++;
                    double y1 = dataArea.getMaxY();
                    g2.setPaint(im.getOutlinePaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[317]++;
                    g2.setStroke(im.getOutlineStroke());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[318]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[319]++;
int CodeCoverConditionCoverageHelper_C68;
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((range.contains(start)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[133]++;
                        line.setLine(start2d, y0, start2d, y1);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[320]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[321]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[134]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[322]++;
int CodeCoverConditionCoverageHelper_C69;
                    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((range.contains(end)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[135]++;
                        line.setLine(end2d, y0, end2d, y1);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[323]++;
                        g2.draw(line);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[324]++;

                    } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[136]++;}
                }

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[126]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[325]++;

            String label = marker.getLabel();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[326]++;
            RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[327]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[137]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[328]++;
                Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[329]++;
                g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[330]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[331]++;
                Point2D coordinates = calculateRangeMarkerTextAnchorPoint(
                        g2, orientation, dataArea, rect,
                        marker.getLabelOffset(), marker.getLabelOffsetType(),
                        anchor);
                TextUtilities.drawAlignedString(label, g2,
                        (float) coordinates.getX(), (float) coordinates.getY(),
                        marker.getLabelTextAnchor());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[332]++;

            } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[138]++;}
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[333]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[114]++;}
}
    }

    /**
     * Calculates the (x, y) coordinates for drawing a marker label.
     *
     * @param g2  the graphics device.
     * @param orientation  the plot orientation.
     * @param dataArea  the data area.
     * @param markerArea  the marker area.
     * @param markerOffset  the marker offset.
     * @param labelOffsetForRange  ??
     * @param anchor  the label anchor.
     *
     * @return The coordinates for drawing the marker label.
     */
    private Point2D calculateRangeMarkerTextAnchorPoint(Graphics2D g2,
                                      PlotOrientation orientation,
                                      Rectangle2D dataArea,
                                      Rectangle2D markerArea,
                                      RectangleInsets markerOffset,
                                      LengthAdjustmentType labelOffsetForRange,
                                      RectangleAnchor anchor) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[334]++;

        Rectangle2D anchorRect = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[335]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[139]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    labelOffsetForRange, LengthAdjustmentType.CONTRACT);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[336]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[140]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[337]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[141]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetForRange);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[338]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[142]++;}
}
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if the renderer does not support
     *         cloning.
     */
    protected Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[339]++;
        AbstractXYItemRenderer clone = (AbstractXYItemRenderer) super.clone();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[340]++;
int CodeCoverConditionCoverageHelper_C73;
        // 'plot' : just retain reference, not a deep copy

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((this.itemLabelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((this.itemLabelGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[143]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[341]++;
            PublicCloneable pc = (PublicCloneable) this.itemLabelGenerator;
            clone.itemLabelGenerator = (XYItemLabelGenerator) pc.clone();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[342]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[144]++;}
        clone.itemLabelGeneratorList
                = (ObjectList) this.itemLabelGeneratorList.clone();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[343]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[344]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((this.baseItemLabelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((this.baseItemLabelGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[145]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[345]++;
            PublicCloneable pc = (PublicCloneable) this.baseItemLabelGenerator;
            clone.baseItemLabelGenerator = (XYItemLabelGenerator) pc.clone();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[346]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[146]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[347]++;
int CodeCoverConditionCoverageHelper_C75;

        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((this.toolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.toolTipGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[147]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[348]++;
            PublicCloneable pc = (PublicCloneable) this.toolTipGenerator;
            clone.toolTipGenerator = (XYToolTipGenerator) pc.clone();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[349]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[148]++;}
        clone.toolTipGeneratorList
                = (ObjectList) this.toolTipGeneratorList.clone();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[350]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[351]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (8)) == 0 || true) &&
 ((this.baseToolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((this.baseToolTipGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[149]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[352]++;
            PublicCloneable pc = (PublicCloneable) this.baseToolTipGenerator;
            clone.baseToolTipGenerator = (XYToolTipGenerator) pc.clone();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[353]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[150]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[354]++;
int CodeCoverConditionCoverageHelper_C77;

        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((clone.legendItemLabelGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[151]++;
            clone.legendItemLabelGenerator = (XYSeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemLabelGenerator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[355]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[152]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[356]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((clone.legendItemToolTipGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[153]++;
            clone.legendItemToolTipGenerator = (XYSeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemToolTipGenerator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[357]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[154]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[358]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((clone.legendItemURLGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[155]++;
            clone.legendItemURLGenerator = (XYSeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemURLGenerator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[359]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[156]++;}

        clone.foregroundAnnotations = (List) ObjectUtilities.deepClone(
                this.foregroundAnnotations);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[360]++;
        clone.backgroundAnnotations = (List) ObjectUtilities.deepClone(
                this.backgroundAnnotations);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[361]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[362]++;
int CodeCoverConditionCoverageHelper_C80;

        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((clone.legendItemLabelGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[157]++;
            clone.legendItemLabelGenerator = (XYSeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemLabelGenerator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[363]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[158]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[364]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((clone.legendItemToolTipGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[159]++;
            clone.legendItemToolTipGenerator = (XYSeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemToolTipGenerator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[365]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[160]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[366]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((clone.legendItemURLGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[161]++;
            clone.legendItemURLGenerator = (XYSeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemURLGenerator);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[367]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[162]++;}

        return clone;
    }

    /**
     * Tests this renderer for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[368]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[163]++;
            return true;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[164]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[369]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractXYItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[165]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[166]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[370]++;
        AbstractXYItemRenderer that = (AbstractXYItemRenderer) obj;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[371]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelGenerator,
                that.itemLabelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[167]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[168]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[372]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((this.itemLabelGeneratorList.equals(that.itemLabelGeneratorList)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[169]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[170]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[373]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseItemLabelGenerator,
                that.baseItemLabelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[171]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[172]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[374]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipGenerator,
                that.toolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[173]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[174]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[375]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((this.toolTipGeneratorList.equals(that.toolTipGeneratorList)) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[175]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[176]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[376]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseToolTipGenerator,
                that.baseToolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[177]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[178]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[377]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.urlGenerator, that.urlGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[179]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[180]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[378]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((this.foregroundAnnotations.equals(that.foregroundAnnotations)) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[181]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[182]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[379]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((this.backgroundAnnotations.equals(that.backgroundAnnotations)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[183]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[184]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[380]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((this.defaultEntityRadius != that.defaultEntityRadius) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[185]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[186]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[381]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendItemLabelGenerator,
                that.legendItemLabelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[187]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[188]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[382]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendItemToolTipGenerator,
                that.legendItemToolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[189]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[190]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[383]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendItemURLGenerator,
                that.legendItemURLGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[191]++;
            return false;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[192]++;}
        return super.equals(obj);
    }

    /**
     * Returns the drawing supplier from the plot.
     *
     * @return The drawing supplier (possibly <code>null</code>).
     */
    public DrawingSupplier getDrawingSupplier() {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[384]++;
        DrawingSupplier result = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[385]++;
        XYPlot p = getPlot();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[386]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[193]++;
            result = p.getDrawingSupplier();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[387]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[194]++;}
        return result;
    }

    /**
     * Considers the current (x, y) coordinate and updates the crosshair point
     * if it meets the criteria (usually means the (x, y) coordinate is the
     * closest to the anchor point so far).
     *
     * @param crosshairState  the crosshair state (<code>null</code> permitted,
     *                        but the method does nothing in that case).
     * @param x  the x-value (in data space).
     * @param y  the y-value (in data space).
     * @param transX  the x-value translated to Java2D space.
     * @param transY  the y-value translated to Java2D space.
     * @param orientation  the plot orientation (<code>null</code> not
     *                     permitted).
     *
     * @deprecated Use {@link #updateCrosshairValues(CrosshairState, double,
     *         double, int, int, double, double, PlotOrientation)} -- see bug
     *         report 1086307.
     */
    protected void updateCrosshairValues(CrosshairState crosshairState,
            double x, double y, double transX, double transY,
            PlotOrientation orientation) {
        updateCrosshairValues(crosshairState, x, y, 0, 0, transX, transY,
                orientation);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[388]++;
    }

    /**
     * Considers the current (x, y) coordinate and updates the crosshair point
     * if it meets the criteria (usually means the (x, y) coordinate is the
     * closest to the anchor point so far).
     *
     * @param crosshairState  the crosshair state (<code>null</code> permitted,
     *                        but the method does nothing in that case).
     * @param x  the x-value (in data space).
     * @param y  the y-value (in data space).
     * @param domainAxisIndex  the index of the domain axis for the point.
     * @param rangeAxisIndex  the index of the range axis for the point.
     * @param transX  the x-value translated to Java2D space.
     * @param transY  the y-value translated to Java2D space.
     * @param orientation  the plot orientation (<code>null</code> not
     *                     permitted).
     *
     * @since 1.0.4
     */
    protected void updateCrosshairValues(CrosshairState crosshairState,
            double x, double y, int domainAxisIndex, int rangeAxisIndex,
            double transX, double transY, PlotOrientation orientation) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[389]++;
int CodeCoverConditionCoverageHelper_C99;

        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[195]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[196]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[390]++;
int CodeCoverConditionCoverageHelper_C100;

        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((crosshairState != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[197]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[391]++;
int CodeCoverConditionCoverageHelper_C101;
            // do we need to update the crosshair values?
            if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((this.plot.isDomainCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[199]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[392]++;
int CodeCoverConditionCoverageHelper_C102;
                if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((this.plot.isRangeCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[201]++;
                    // both axes
                    crosshairState.updateCrosshairPoint(x, y, domainAxisIndex,
                            rangeAxisIndex, transX, transY, orientation);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[393]++;

                }
                else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[202]++;
                    // just the domain axis...
                    crosshairState.updateCrosshairX(x, domainAxisIndex);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[394]++;
                }

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[200]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[395]++;
int CodeCoverConditionCoverageHelper_C103;
                if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((this.plot.isRangeCrosshairLockedOnData()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[203]++;
                    // just the range axis...
                    crosshairState.updateCrosshairY(y, rangeAxisIndex);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[396]++;

                } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[204]++;}
            }

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[198]++;}

    }

    /**
     * Draws an item label.
     *
     * @param g2  the graphics device.
     * @param orientation  the orientation.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param x  the x coordinate (in Java2D space).
     * @param y  the y coordinate (in Java2D space).
     * @param negative  indicates a negative value (which affects the item
     *                  label position).
     */
    protected void drawItemLabel(Graphics2D g2, PlotOrientation orientation,
            XYDataset dataset, int series, int item, double x, double y,
            boolean negative) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[397]++;

        XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[398]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[205]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[399]++;
            Font labelFont = getItemLabelFont(series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[400]++;
            Paint paint = getItemLabelPaint(series, item);
            g2.setFont(labelFont);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[401]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[402]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[403]++;
            String label = generator.generateLabel(dataset, series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[404]++;

            // get the label position..
            ItemLabelPosition position = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[405]++;
int CodeCoverConditionCoverageHelper_C105;
            if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[207]++;
                position = getPositiveItemLabelPosition(series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[406]++;

            }
            else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[208]++;
                position = getNegativeItemLabelPosition(series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[407]++;
            }
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[408]++;

            // work out the label anchor point...
            Point2D anchorPoint = calculateLabelAnchorPoint(
                    position.getItemLabelAnchor(), x, y, orientation);
            TextUtilities.drawRotatedString(label, g2,
                    (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(), position.getAngle(),
                    position.getRotationAnchor());
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[409]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[206]++;}

    }

    /**
     * Draws all the annotations for the specified layer.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param layer  the layer.
     * @param info  the plot rendering info.
     */
    public void drawAnnotations(Graphics2D g2,
                                Rectangle2D dataArea,
                                ValueAxis domainAxis,
                                ValueAxis rangeAxis,
                                Layer layer,
                                PlotRenderingInfo info) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[410]++;

        Iterator iterator = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[411]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((layer.equals(Layer.FOREGROUND)) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[209]++;
            iterator = this.foregroundAnnotations.iterator();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[412]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[210]++;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[413]++;
int CodeCoverConditionCoverageHelper_C107; if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((layer.equals(Layer.BACKGROUND)) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[211]++;
            iterator = this.backgroundAnnotations.iterator();
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[414]++;

        }
        else {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[212]++;
            // should not get here
            throw new RuntimeException("Unknown layer.");
        }
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[415]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[4]++;


int CodeCoverConditionCoverageHelper_C108;
        while ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[4]--;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[5]--;
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.loops[6]++;
}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[416]++;
            XYAnnotation annotation = (XYAnnotation) iterator.next();
            annotation.draw(g2, this.plot, dataArea, domainAxis, rangeAxis,
                    0, info);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[417]++;
        }

    }

    /**
     * Adds an entity to the collection.
     *
     * @param entities  the entity collection being populated.
     * @param area  the entity area (if <code>null</code> a default will be
     *              used).
     * @param dataset  the dataset.
     * @param series  the series.
     * @param item  the item.
     * @param entityX  the entity's center x-coordinate in user space.
     * @param entityY  the entity's center y-coordinate in user space.
     */
    protected void addEntity(EntityCollection entities, Shape area,
                             XYDataset dataset, int series, int item,
                             double entityX, double entityY) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[418]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((getItemCreateEntity(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[213]++;
            return;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[214]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[419]++;
int CodeCoverConditionCoverageHelper_C110;
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((area == null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[215]++;
            area = new Ellipse2D.Double(entityX - this.defaultEntityRadius,
                    entityY - this.defaultEntityRadius,
                    this.defaultEntityRadius * 2, this.defaultEntityRadius * 2);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[420]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[216]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[421]++;
        String tip = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[422]++;
        XYToolTipGenerator generator = getToolTipGenerator(series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[423]++;
int CodeCoverConditionCoverageHelper_C111;
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[217]++;
            tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[424]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[218]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[425]++;
        String url = null;
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[426]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[219]++;
            url = getURLGenerator().generateURL(dataset, series, item);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[427]++;

        } else {
  CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.branches[220]++;}
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[428]++;
        XYItemEntity entity = new XYItemEntity(area, dataset, series, item,
                tip, url);
        entities.add(entity);
CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201.statements[429]++;
    }

}

class CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201 ());
  }
    public static long[] statements = new long[430];
    public static long[] branches = new long[221];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[113];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.AbstractXYItemRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 112; i++) {
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

  public CodeCoverCoverageCounter$46iyz2ya8nx9atsshvw3xr9z15y3t60f50tku55201 () {
    super("org.jfree.chart.renderer.xy.AbstractXYItemRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 429; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 220; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 112; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.AbstractXYItemRenderer.java");
      for (int i = 1; i <= 429; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 220; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 112; i++) {
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

