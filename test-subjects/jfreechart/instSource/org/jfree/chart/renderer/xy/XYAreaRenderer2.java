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
 * --------------------
 * XYAreaRenderer2.java
 * --------------------
 * (C) Copyright 2004-2007, by Hari and Contributors.
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
 * 05-Aug-2002 : Small modification to drawItem method to support URLs for 
 *               HTML image maps (RA);
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
 * 10-Feb-2004 : Changed the drawItem() method to make cut-and-paste 
 *               overriding easier.  Also moved state class into this 
 *               class (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState.  Renamed 
 *               XYToolTipGenerator --> XYItemLabelGenerator (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities to translate shapes (DG);
 * 19-Jan-2005 : Now accesses only primitives from the dataset (DG);
 * 21-Mar-2005 : Override getLegendItem() (DG);
 * 20-Apr-2005 : Use generators for legend tooltips and URLs (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 30-Nov-2006 : Fixed equals() and clone() implementations (DG);
 * 06-Feb-2007 : Fixed bug 1086307, crosshairs with multiple axes (DG);
 * 20-Apr-2007 : Updated getLegendItem() and drawItem() for renderer 
 *               change (DG); 
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
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
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
 * Area item renderer for an {@link XYPlot}.  
 */
public class XYAreaRenderer2 extends AbstractXYItemRenderer 
                             implements XYItemRenderer, 
                                        Cloneable,
                                        PublicCloneable,
                                        Serializable {
  static {
    CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7378069681579984133L;
  static {
    CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[1]++;
  }

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
    public XYAreaRenderer2() {
        this(null, null);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[2]++;
    }

    /**
     * Constructs a new renderer.
     *
     * @param labelGenerator  the tool tip generator to use.  <code>null</code> 
     *                        is none.
     * @param urlGenerator  the URL generator (null permitted).
     */
    public XYAreaRenderer2(XYToolTipGenerator labelGenerator, 
                           XYURLGenerator urlGenerator) {
        super();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[3]++;
        this.showOutline = false;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[4]++;
        setBaseToolTipGenerator(labelGenerator);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[5]++;
        setURLGenerator(urlGenerator);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[6]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[7]++;
        GeneralPath area = new GeneralPath();
        area.moveTo(0.0f, -4.0f);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[8]++;
        area.lineTo(3.0f, -2.0f);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[9]++;
        area.lineTo(4.0f, 4.0f);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[10]++;
        area.lineTo(-4.0f, 4.0f);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[11]++;
        area.lineTo(-3.0f, -2.0f);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[12]++;
        area.closePath();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[13]++;
        this.legendArea = area;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[14]++;
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
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[15]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[16]++;
    }

    /**
     * This method should not be used.
     *
     * @return <code>false</code> always.
     * 
     * @deprecated This method was included in the API by mistake and serves
     *     no useful purpose.  It has always returned <code>false</code>.
     *   
     */
    public boolean getPlotLines() {
        return false;
    }

    /**
     * Returns the shape used to represent an area in the legend.
     * 
     * @return The legend area (never <code>null</code>).
     * 
     * @see #setLegendArea(Shape)
     */
    public Shape getLegendArea() {
        return this.legendArea;   
    }
    
    /**
     * Sets the shape used as an area in each legend item and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param area  the area (<code>null</code> not permitted).
     * 
     * @see #getLegendArea()
     */
    public void setLegendArea(Shape area) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((area == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[1]++;
            throw new IllegalArgumentException("Null 'area' argument.");
   
        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[2]++;}
        this.legendArea = area;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[18]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[19]++;
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
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[20]++;
        LegendItem result = null;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[21]++;
        XYPlot xyplot = getPlot();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xyplot != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[3]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[23]++;
            XYDataset dataset = xyplot.getDataset(datasetIndex);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[5]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[25]++;
                XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[26]++;
                String label = lg.generateLabel(dataset, series);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[27]++;
                String description = label;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[28]++;
                String toolTipText = null;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[29]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[7]++;
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[30]++;

                } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[8]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[31]++;
                String urlText = null;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[32]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[9]++;
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[33]++;

                } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[10]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[34]++;
                Paint paint = lookupSeriesPaint(series);
                result = new LegendItem(label, description, toolTipText, 
                        urlText, this.legendArea, paint);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[35]++;
                result.setDataset(dataset);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[36]++;
                result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[37]++;
                result.setSeriesKey(dataset.getSeriesKey(series));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[38]++;
                result.setSeriesIndex(series);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[39]++;

            } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[4]++;}
        return result;
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
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[40]++;
int CodeCoverConditionCoverageHelper_C6;
        
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[11]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[12]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[41]++;
        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[42]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[43]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[13]++;
            y1 = 0.0;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[44]++;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[14]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[45]++;
        
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[46]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[47]++;
        
        // get the previous point and the next point so we can calculate a 
        // "hot spot" for the area (used by the chart entity)...
        double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[48]++;
        double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[49]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[15]++;
            y0 = 0.0;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[50]++;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[16]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[51]++;
        double transX0 = domainAxis.valueToJava2D(x0, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[52]++;
        double transY0 = rangeAxis.valueToJava2D(y0, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[53]++;
        
        int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[54]++;
        double x2 = dataset.getXValue(series, Math.min(item + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[55]++;
        double y2 = dataset.getYValue(series, Math.min(item + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[56]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Double.isNaN(y2)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[17]++;
            y2 = 0.0;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[57]++;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[18]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[58]++;
        double transX2 = domainAxis.valueToJava2D(x2, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[59]++;
        double transY2 = rangeAxis.valueToJava2D(y2, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[60]++;
        
        double transZero = rangeAxis.valueToJava2D(0.0, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[61]++;
        Polygon hotspot = null;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[62]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[19]++;
            hotspot = new Polygon();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[63]++;
            hotspot.addPoint((int) transZero, 
                    (int) ((transX0 + transX1) / 2.0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[64]++;
            hotspot.addPoint((int) ((transY0 + transY1) / 2.0), 
                    (int) ((transX0 + transX1) / 2.0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[65]++;
            hotspot.addPoint((int) transY1, (int) transX1);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[66]++;
            hotspot.addPoint((int) ((transY1 + transY2) / 2.0), 
                    (int) ((transX1 + transX2) / 2.0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[67]++;
            hotspot.addPoint((int) transZero, 
                    (int) ((transX1 + transX2) / 2.0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[68]++;

        }
        else {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[20]++;  // vertical orientation
            hotspot = new Polygon();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[69]++;
            hotspot.addPoint((int) ((transX0 + transX1) / 2.0), 
                    (int) transZero);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[70]++;
            hotspot.addPoint((int) ((transX0 + transX1) / 2.0), 
                    (int) ((transY0 + transY1) / 2.0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[71]++;
            hotspot.addPoint((int) transX1, (int) transY1);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[72]++;
            hotspot.addPoint((int) ((transX1 + transX2) / 2.0), 
                    (int) ((transY1 + transY2) / 2.0));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[73]++;
            hotspot.addPoint((int) ((transX1 + transX2) / 2.0), 
                    (int) transZero);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[74]++;
        }
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[75]++;
                
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[76]++;
        Paint paint = getItemPaint(series, item);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[77]++;
        Stroke stroke = getItemStroke(series, item);
        g2.setPaint(paint);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[78]++;
        g2.setStroke(stroke);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[79]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[80]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getPlotLines()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[21]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[81]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((item > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[23]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[82]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[25]++;
                    state.workingLine.setLine(transX0, transY0, transX1, 
                            transY1);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[83]++;

                }
                else {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[26]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[84]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[27]++;
                    state.workingLine.setLine(transY0, transX0, transY1, 
                            transX1);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[85]++;

                } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[28]++;}
}
                g2.draw(state.workingLine);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[86]++;

            } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[22]++;}

        // Check if the item is the last item for the series.
        // and number of items > 0.  We can't draw an area for a single point.
        g2.fill(hotspot);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[87]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[88]++;
int CodeCoverConditionCoverageHelper_C15;

        // draw an outline around the Area.
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isOutline()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[29]++;
            g2.setStroke(lookupSeriesOutlineStroke(series));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[89]++;
            g2.setPaint(lookupSeriesOutlinePaint(series));
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[90]++;
            g2.draw(hotspot);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[91]++;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[30]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[92]++;
        int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[93]++;
        int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
        updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, 
                rangeAxisIndex, transX1, transY1, orientation);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[94]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[95]++;
int CodeCoverConditionCoverageHelper_C16;
        
        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[31]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[96]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[97]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((hotspot != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[33]++;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[98]++;
                String tip = null;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[99]++;
                XYToolTipGenerator generator = getToolTipGenerator(
                    series, item
                );
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[100]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[35]++;
                    tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[101]++;

                } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[36]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[102]++;
                String url = null;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[103]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[37]++;
                    url = getURLGenerator().generateURL(dataset, series, item);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[104]++;

                } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[38]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[105]++;
                XYItemEntity entity = new XYItemEntity(hotspot, dataset, 
                        series, item, tip, url);
                entities.add(entity);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[106]++;

            } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[34]++;}

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[32]++;}

    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> not permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[107]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[39]++;    
            return true;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[40]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[108]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj instanceof XYAreaRenderer2) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[42]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[109]++;
        XYAreaRenderer2 that = (XYAreaRenderer2) obj;
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[110]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.showOutline != that.showOutline) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[44]++;}
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[111]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendArea, that.legendArea)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.branches[46]++;}
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
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[112]++;
        XYAreaRenderer2 clone = (XYAreaRenderer2) super.clone();
        clone.legendArea = ShapeUtilities.clone(this.legendArea);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[113]++;
        return clone;
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
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[114]++;
        this.legendArea = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[115]++;
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
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[116]++;
        SerialUtilities.writeShape(this.legendArea, stream);
CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp.statements[117]++;
    }

}

class CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp ());
  }
    public static long[] statements = new long[118];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYAreaRenderer2.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$abiuz7qhsypk3wznxtebt4fva82dmqp () {
    super("org.jfree.chart.renderer.xy.XYAreaRenderer2.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 117; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYAreaRenderer2.java");
      for (int i = 1; i <= 117; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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


