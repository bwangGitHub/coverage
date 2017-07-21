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
 * BoxAndWhiskerRenderer.java
 * --------------------------
 * (C) Copyright 2003-2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for the Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Tim Bardzil;
 *
 * Changes
 * -------
 * 21-Aug-2003 : Version 1, contributed by David Browning (for the Australian 
 *               Institute of Marine Science);
 * 01-Sep-2003 : Incorporated outlier and farout symbols for low values 
 *               also (DG);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 12-Nov-2003 : Fixed casting bug reported by Tim Bardzil (DG);
 * 13-Nov-2003 : Added drawHorizontalItem() method contributed by Tim 
 *               Bardzil (DG);
 * 25-Apr-2004 : Added fillBox attribute, equals() method and added 
 *               serialization code (DG);
 * 29-Apr-2004 : Changed drawing of upper and lower shadows - see bug report 
 *               944011 (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 09-Mar-2005 : Override getLegendItem() method so that legend item shapes
 *               are shown as blocks (DG);
 * 20-Apr-2005 : Generate legend labels, tooltips and URLs (DG);
 * 09-Jun-2005 : Updated equals() to handle GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 12-Oct-2006 : Source reformatting and API doc updates (DG);
 * 12-Oct-2006 : Fixed bug 1572478, potential NullPointerException (DG);
 * 05-Feb-2006 : Added event notifications to a couple of methods (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 11-May-2007 : Added check for visibility in getLegendItem() (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 *
 */

package org.jfree.chart.renderer.category;

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

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.Outlier;
import org.jfree.chart.renderer.OutlierList;
import org.jfree.chart.renderer.OutlierListCollection;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A box-and-whisker renderer.  This renderer requires a 
 * {@link BoxAndWhiskerCategoryDataset} and is for use with the 
 * {@link CategoryPlot} class.
 */
public class BoxAndWhiskerRenderer extends AbstractCategoryItemRenderer 
                                   implements Cloneable, PublicCloneable, 
                                              Serializable {
  static {
    CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 632027470694481177L;
  static {
    CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[1]++;
  }
    
    /** The color used to paint the median line and average marker. */
    private transient Paint artifactPaint;

    /** A flag that controls whether or not the box is filled. */
    private boolean fillBox;
    
    /** The margin between items (boxes) within a category. */
    private double itemMargin;

    /**
     * Default constructor.
     */
    public BoxAndWhiskerRenderer() {
        this.artifactPaint = Color.black;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[2]++;
        this.fillBox = true;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[3]++;
        this.itemMargin = 0.20;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[4]++;
    }

    /**
     * Returns the paint used to color the median and average markers.
     * 
     * @return The paint used to draw the median and average markers (never
     *     <code>null</code>).
     *
     * @see #setArtifactPaint(Paint)
     */
    public Paint getArtifactPaint() {
        return this.artifactPaint;
    }

    /**
     * Sets the paint used to color the median and average markers and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getArtifactPaint()
     */
    public void setArtifactPaint(Paint paint) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[2]++;}
        this.artifactPaint = paint;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[6]++;
        fireChangeEvent();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[7]++;
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
     * @see #getFillBox()
     */
    public void setFillBox(boolean flag) {
        this.fillBox = flag;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[8]++;
        fireChangeEvent();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[9]++;
    }

    /**
     * Returns the item margin.  This is a percentage of the available space 
     * that is allocated to the space between items in the chart.
     * 
     * @return The margin.
     *
     * @see #setItemMargin(double)
     */
    public double getItemMargin() {
        return this.itemMargin;
    }

    /**
     * Sets the item margin and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * 
     * @param margin  the margin (a percentage).
     *
     * @see #getItemMargin()
     */
    public void setItemMargin(double margin) {
        this.itemMargin = margin;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[10]++;
        fireChangeEvent();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[11]++;
    }

    /**
     * Returns a legend item for a series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return The legend item (possibly <code>null</code>).
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[12]++;

        CategoryPlot cp = getPlot();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cp == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[3]++;
            return null;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[4]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

        // check that a legend item needs to be displayed...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((isSeriesVisible(series)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(series)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[5]++;
            return null;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[6]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[15]++;

        CategoryDataset dataset = cp.getDataset(datasetIndex);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[16]++;
        String label = getLegendItemLabelGenerator().generateLabel(dataset, 
                series);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[17]++;
        String description = label;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[18]++;
        String toolTipText = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[19]++;
int CodeCoverConditionCoverageHelper_C4; 
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[7]++;
            toolTipText = getLegendItemToolTipGenerator().generateLabel(
                    dataset, series);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[20]++;
   
        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[8]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[21]++;
        String urlText = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[9]++;
            urlText = getLegendItemURLGenerator().generateLabel(dataset, 
                    series);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[23]++;
   
        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[10]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[24]++;
        Shape shape = new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[25]++;
        Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[26]++;
        Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[27]++;
        Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[28]++;
        LegendItem result = new LegendItem(label, description, toolTipText, 
                urlText, shape, paint, outlineStroke, outlinePaint);
        result.setDataset(dataset);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[29]++;
        result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[30]++;
        result.setSeriesKey(dataset.getRowKey(series));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[31]++;
        result.setSeriesIndex(series);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[32]++;
        return result;

    }

    /**
     * Initialises the renderer.  This method gets called once at the start of 
     * the process of drawing a chart.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data is to be plotted.
     * @param plot  the plot.
     * @param rendererIndex  the renderer index.
     * @param info  collects chart rendering information for return to caller.
     *
     * @return The renderer state.
     */
    public CategoryItemRendererState initialise(Graphics2D g2,
                                                Rectangle2D dataArea,
                                                CategoryPlot plot,
                                                int rendererIndex,
                                                PlotRenderingInfo info) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[33]++;

        CategoryItemRendererState state = super.initialise(g2, dataArea, plot,
                rendererIndex, info);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[34]++;

        // calculate the box width
        CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[35]++;
        CategoryDataset dataset = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[11]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[37]++;
            int columns = dataset.getColumnCount();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[38]++;
            int rows = dataset.getRowCount();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[39]++;
            double space = 0.0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[40]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[13]++;
                space = dataArea.getHeight();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[42]++;

            }
            else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[14]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[43]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[15]++;
                space = dataArea.getWidth();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[44]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[16]++;}
}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[45]++;
            double categoryMargin = 0.0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[46]++;
            double currentItemMargin = 0.0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((columns > 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[17]++;
                categoryMargin = domainAxis.getCategoryMargin();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[48]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[18]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[49]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((rows > 1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[19]++;
                currentItemMargin = getItemMargin();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[50]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[20]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[51]++;
            double used = space * (1 - domainAxis.getLowerMargin() 
                                     - domainAxis.getUpperMargin()
                                     - categoryMargin - currentItemMargin);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 (((rows * columns) > 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[21]++;
                state.setBarWidth(used / (dataset.getColumnCount() 
                        * dataset.getRowCount()));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[53]++;

            }
            else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[22]++;
                state.setBarWidth(used);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[54]++;
            }

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[12]++;}
        
        return state;

    }

    /**
     * Draw a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area in which the data is drawn.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         CategoryItemRendererState state,
                         Rectangle2D dataArea,
                         CategoryPlot plot,
                         CategoryAxis domainAxis,
                         ValueAxis rangeAxis,
                         CategoryDataset dataset,
                         int row,
                         int column,
                         int pass) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[55]++;
int CodeCoverConditionCoverageHelper_C12;
                             
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dataset instanceof BoxAndWhiskerCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[23]++;
            throw new IllegalArgumentException(
                    "BoxAndWhiskerRenderer.drawItem() : the data should be " 
                    + "of type BoxAndWhiskerCategoryDataset only.");

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[24]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[56]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[25]++;
            drawHorizontalItem(g2, state, dataArea, plot, domainAxis, 
                    rangeAxis, dataset, row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[58]++;

        } 
        else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[26]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[59]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[27]++;
            drawVerticalItem(g2, state, dataArea, plot, domainAxis, 
                    rangeAxis, dataset, row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[60]++;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[28]++;}
}
        
    }

    /**
     * Draws the visual representation of a single data item when the plot has 
     * a horizontal orientation.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    public void drawHorizontalItem(Graphics2D g2,
                                   CategoryItemRendererState state,
                                   Rectangle2D dataArea,
                                   CategoryPlot plot,
                                   CategoryAxis domainAxis,
                                   ValueAxis rangeAxis,
                                   CategoryDataset dataset,
                                   int row,
                                   int column) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[61]++;

        BoxAndWhiskerCategoryDataset bawDataset 
                = (BoxAndWhiskerCategoryDataset) dataset;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[62]++;

        double categoryEnd = domainAxis.getCategoryEnd(column, 
                getColumnCount(), dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[63]++;
        double categoryStart = domainAxis.getCategoryStart(column, 
                getColumnCount(), dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[64]++;
        double categoryWidth = Math.abs(categoryEnd - categoryStart);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[65]++;

        double yy = categoryStart;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[66]++;
        int seriesCount = getRowCount();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[67]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[68]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[29]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[69]++;
            double seriesGap = dataArea.getWidth() * getItemMargin()
                               / (categoryCount * (seriesCount - 1));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[70]++;
            double usedWidth = (state.getBarWidth() * seriesCount) 
                               + (seriesGap * (seriesCount - 1));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[71]++;
            // offset the start of the boxes if the total width used is smaller
            // than the category width
            double offset = (categoryWidth - usedWidth) / 2;
            yy = yy + offset + (row * (state.getBarWidth() + seriesGap));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[72]++;

        } 
        else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[30]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[73]++;
            // offset the start of the box if the box width is smaller than 
            // the category width
            double offset = (categoryWidth - state.getBarWidth()) / 2;
            yy = yy + offset;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[74]++;
        }
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[75]++;

        Paint p = getItemPaint(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[31]++;
            g2.setPaint(p);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[77]++;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[32]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[78]++;
        Stroke s = getItemStroke(row, column);
        g2.setStroke(s);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[79]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[80]++;

        RectangleEdge location = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[81]++;

        Number xQ1 = bawDataset.getQ1Value(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[82]++;
        Number xQ3 = bawDataset.getQ3Value(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[83]++;
        Number xMax = bawDataset.getMaxRegularValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[84]++;
        Number xMin = bawDataset.getMinRegularValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[85]++;

        Shape box = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[86]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (128)) == 0 || true) &&
 ((xQ1 != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((xQ3 != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((xMax != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((xMin != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 4) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 4) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[33]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[87]++;

            double xxQ1 = rangeAxis.valueToJava2D(xQ1.doubleValue(), dataArea, 
                    location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[88]++;
            double xxQ3 = rangeAxis.valueToJava2D(xQ3.doubleValue(), dataArea,
                    location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[89]++;
            double xxMax = rangeAxis.valueToJava2D(xMax.doubleValue(), dataArea,
                    location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[90]++;
            double xxMin = rangeAxis.valueToJava2D(xMin.doubleValue(), dataArea,
                    location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[91]++;
            double yymid = yy + state.getBarWidth() / 2.0;
            
            // draw the upper shadow...
            g2.draw(new Line2D.Double(xxMax, yymid, xxQ3, yymid));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[92]++;
            g2.draw(new Line2D.Double(xxMax, yy, xxMax, 
                    yy + state.getBarWidth()));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[93]++;

            // draw the lower shadow...
            g2.draw(new Line2D.Double(xxMin, yymid, xxQ1, yymid));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[94]++;
            g2.draw(new Line2D.Double(xxMin, yy, xxMin,
                    yy + state.getBarWidth()));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[95]++;

            // draw the box...
            box = new Rectangle2D.Double(Math.min(xxQ1, xxQ3), yy, 
                    Math.abs(xxQ1 - xxQ3), state.getBarWidth());
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[96]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[97]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.fillBox) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[35]++;
                g2.fill(box);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[98]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[36]++;} 
            g2.draw(box);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[99]++;


        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[34]++;}

        g2.setPaint(this.artifactPaint);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[100]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[101]++;
        double aRadius = 0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[102]++;                 // average radius

        // draw mean - SPECIAL AIMS REQUIREMENT...
        Number xMean = bawDataset.getMeanValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[103]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((xMean != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[37]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[104]++;
            double xxMean = rangeAxis.valueToJava2D(xMean.doubleValue(), 
                    dataArea, location);
            aRadius = state.getBarWidth() / 4;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[105]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[106]++;
            Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xxMean 
                    - aRadius, yy + aRadius, aRadius * 2, aRadius * 2);
            g2.fill(avgEllipse);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[107]++;
            g2.draw(avgEllipse);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[108]++;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[38]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[109]++;

        // draw median...
        Number xMedian = bawDataset.getMedianValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[110]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((xMedian != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[39]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[111]++;
            double xxMedian = rangeAxis.valueToJava2D(xMedian.doubleValue(), 
                    dataArea, location);
            g2.draw(new Line2D.Double(xxMedian, yy, xxMedian, 
                    yy + state.getBarWidth()));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[112]++;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[40]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[113]++;
int CodeCoverConditionCoverageHelper_C21;
        
        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((box != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[41]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[114]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[115]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[43]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[116]++;
                String tip = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[117]++;
                CategoryToolTipGenerator tipster 
                        = getToolTipGenerator(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[118]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[45]++;
                    tip = tipster.generateToolTip(dataset, row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[119]++;

                } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[46]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[120]++;
                String url = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[121]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[47]++;
                    url = getItemURLGenerator(row, column).generateURL(
                            dataset, row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[122]++;

                } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[48]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[123]++;
                CategoryItemEntity entity = new CategoryItemEntity(box, tip, 
                        url, dataset, dataset.getRowKey(row), 
                        dataset.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[124]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[42]++;}

    } 
        
    /**
     * Draws the visual representation of a single data item when the plot has 
     * a vertical orientation.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    public void drawVerticalItem(Graphics2D g2, 
                                 CategoryItemRendererState state,
                                 Rectangle2D dataArea,
                                 CategoryPlot plot, 
                                 CategoryAxis domainAxis, 
                                 ValueAxis rangeAxis,
                                 CategoryDataset dataset, 
                                 int row, 
                                 int column) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[125]++;

        BoxAndWhiskerCategoryDataset bawDataset 
                = (BoxAndWhiskerCategoryDataset) dataset;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[126]++;
        
        double categoryEnd = domainAxis.getCategoryEnd(column, 
                getColumnCount(), dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[127]++;
        double categoryStart = domainAxis.getCategoryStart(column, 
                getColumnCount(), dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[128]++;
        double categoryWidth = categoryEnd - categoryStart;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[129]++;

        double xx = categoryStart;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[130]++;
        int seriesCount = getRowCount();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[131]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[132]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[49]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[133]++;
            double seriesGap = dataArea.getWidth() * getItemMargin() 
                               / (categoryCount * (seriesCount - 1));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[134]++;
            double usedWidth = (state.getBarWidth() * seriesCount) 
                               + (seriesGap * (seriesCount - 1));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[135]++;
            // offset the start of the boxes if the total width used is smaller
            // than the category width
            double offset = (categoryWidth - usedWidth) / 2;
            xx = xx + offset + (row * (state.getBarWidth() + seriesGap));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[136]++;

        } 
        else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[50]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[137]++;
            // offset the start of the box if the box width is smaller than the 
            // category width
            double offset = (categoryWidth - state.getBarWidth()) / 2;
            xx = xx + offset;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[138]++;
        }
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[139]++; 
        
        double yyAverage = 0.0;
        double yyOutlier;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[140]++;

        Paint p = getItemPaint(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[141]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[51]++;
            g2.setPaint(p);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[142]++;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[52]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[143]++;
        Stroke s = getItemStroke(row, column);
        g2.setStroke(s);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[144]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[145]++;

        double aRadius = 0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[146]++;                 // average radius

        RectangleEdge location = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[147]++;

        Number yQ1 = bawDataset.getQ1Value(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[148]++;
        Number yQ3 = bawDataset.getQ3Value(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[149]++;
        Number yMax = bawDataset.getMaxRegularValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[150]++;
        Number yMin = bawDataset.getMinRegularValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[151]++;
        Shape box = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[152]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (128)) == 0 || true) &&
 ((yQ1 != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (32)) == 0 || true) &&
 ((yQ3 != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((yMax != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((yMin != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 4) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[53]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[153]++;

            double yyQ1 = rangeAxis.valueToJava2D(yQ1.doubleValue(), dataArea,
                    location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[154]++;
            double yyQ3 = rangeAxis.valueToJava2D(yQ3.doubleValue(), dataArea, 
                    location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[155]++;
            double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), 
                    dataArea, location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[156]++;
            double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), 
                    dataArea, location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[157]++;
            double xxmid = xx + state.getBarWidth() / 2.0;
            
            // draw the upper shadow...
            g2.draw(new Line2D.Double(xxmid, yyMax, xxmid, yyQ3));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[158]++;
            g2.draw(new Line2D.Double(xx, yyMax, xx + state.getBarWidth(), 
                    yyMax));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[159]++;

            // draw the lower shadow...
            g2.draw(new Line2D.Double(xxmid, yyMin, xxmid, yyQ1));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[160]++;
            g2.draw(new Line2D.Double(xx, yyMin, xx + state.getBarWidth(), 
                    yyMin));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[161]++;

            // draw the body...
            box = new Rectangle2D.Double(xx, Math.min(yyQ1, yyQ3), 
                    state.getBarWidth(), Math.abs(yyQ1 - yyQ3));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[162]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[163]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.fillBox) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[55]++;
                g2.fill(box);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[164]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[56]++;}
            g2.draw(box);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[165]++;

  
        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[54]++;}
        
        g2.setPaint(this.artifactPaint);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[166]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[167]++;

        // draw mean - SPECIAL AIMS REQUIREMENT...
        Number yMean = bawDataset.getMeanValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[168]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((yMean != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[57]++;
            yyAverage = rangeAxis.valueToJava2D(yMean.doubleValue(), 
                    dataArea, location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[169]++;
            aRadius = state.getBarWidth() / 4;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[170]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[171]++;
            Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xx + aRadius, 
                    yyAverage - aRadius, aRadius * 2, aRadius * 2);
            g2.fill(avgEllipse);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[172]++;
            g2.draw(avgEllipse);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[173]++;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[58]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[174]++;

        // draw median...
        Number yMedian = bawDataset.getMedianValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[175]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((yMedian != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[59]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[176]++;
            double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), 
                    dataArea, location);
            g2.draw(new Line2D.Double(xx, yyMedian, xx + state.getBarWidth(), 
                    yyMedian));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[177]++;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[60]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[178]++;
        
        // draw yOutliers...
        double maxAxisValue = rangeAxis.valueToJava2D(
                rangeAxis.getUpperBound(), dataArea, location) + aRadius;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[179]++;
        double minAxisValue = rangeAxis.valueToJava2D(
                rangeAxis.getLowerBound(), dataArea, location) - aRadius;

        g2.setPaint(p);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[180]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[181]++;

        // draw outliers
        double oRadius = state.getBarWidth() / 3;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[182]++;    // outlier radius
        List outliers = new ArrayList();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[183]++;
        OutlierListCollection outlierListCollection 
                = new OutlierListCollection();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[184]++;

        // From outlier array sort out which are outliers and put these into a 
        // list If there are any farouts, set the flag on the 
        // OutlierListCollection
        List yOutliers = bawDataset.getOutliers(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[185]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((yOutliers != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[61]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[186]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[1]++;


int CodeCoverConditionCoverageHelper_C32;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < yOutliers.size()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[1]--;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[2]--;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[3]++;
}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[187]++;
                double outlier = ((Number) yOutliers.get(i)).doubleValue();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[188]++;
                Number minOutlier = bawDataset.getMinOutlier(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[189]++;
                Number maxOutlier = bawDataset.getMaxOutlier(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[190]++;
                Number minRegular = bawDataset.getMinRegularValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[191]++;
                Number maxRegular = bawDataset.getMaxRegularValue(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[192]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((outlier > maxOutlier.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[63]++;
                    outlierListCollection.setHighFarOut(true);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[193]++;

                } 
                else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[64]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[194]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((outlier < minOutlier.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[65]++;
                    outlierListCollection.setLowFarOut(true);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[195]++;

                }
                else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[66]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[196]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((outlier > maxRegular.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[67]++;
                    yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, 
                            location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[197]++;
                    outliers.add(new Outlier(xx + state.getBarWidth() / 2.0, 
                            yyOutlier, oRadius));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[198]++;

                }
                else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[68]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[199]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((outlier < minRegular.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[69]++;
                    yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, 
                            location);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[200]++;
                    outliers.add(new Outlier(xx + state.getBarWidth() / 2.0, 
                            yyOutlier, oRadius));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[201]++;

                } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[70]++;}
}
}
}
                Collections.sort(outliers);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[202]++;
            }
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[203]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[4]++;


int CodeCoverConditionCoverageHelper_C37;

            // Process outliers. Each outlier is either added to the 
            // appropriate outlier list or a new outlier list is made
            for (Iterator iterator = outliers.iterator();(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[4]--;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[5]--;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[6]++;
}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[204]++;
                Outlier outlier = (Outlier) iterator.next();
                outlierListCollection.add(outlier);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[205]++;
            }
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[206]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[7]++;


int CodeCoverConditionCoverageHelper_C38;

            for (Iterator iterator = outlierListCollection.iterator();(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[7]--;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[8]--;
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.loops[9]++;
}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[207]++;
                OutlierList list = (OutlierList) iterator.next();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[208]++;
                Outlier outlier = list.getAveragedOutlier();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[209]++;
                Point2D point = outlier.getPoint();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[210]++;
int CodeCoverConditionCoverageHelper_C39;

                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((list.isMultiple()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[71]++;
                    drawMultipleEllipse(point, state.getBarWidth(), oRadius, 
                            g2);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[211]++;

                } 
                else {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[72]++;
                    drawEllipse(point, oRadius, g2);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[212]++;
                }
            }
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[213]++;
int CodeCoverConditionCoverageHelper_C40;

            // draw farout indicators
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((outlierListCollection.isHighFarOut()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[73]++;
                drawHighFarOut(aRadius / 2.0, g2, 
                        xx + state.getBarWidth() / 2.0, maxAxisValue);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[214]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[74]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[215]++;
int CodeCoverConditionCoverageHelper_C41;
        
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((outlierListCollection.isLowFarOut()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[75]++;
                drawLowFarOut(aRadius / 2.0, g2, 
                        xx + state.getBarWidth() / 2.0, minAxisValue);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[216]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[76]++;}

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[62]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[217]++;
int CodeCoverConditionCoverageHelper_C42;
        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((box != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[77]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[218]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[219]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[79]++;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[220]++;
                String tip = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[221]++;
                CategoryToolTipGenerator tipster 
                        = getToolTipGenerator(row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[222]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[81]++;
                    tip = tipster.generateToolTip(dataset, row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[223]++;

                } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[82]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[224]++;
                String url = null;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[225]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[83]++;
                    url = getItemURLGenerator(row, column).generateURL(dataset,
                            row, column);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[226]++;

                } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[84]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[227]++;
                CategoryItemEntity entity = new CategoryItemEntity(box, tip, 
                        url, dataset, dataset.getRowKey(row), 
                        dataset.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[228]++;

            } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[80]++;}

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[78]++;}

    }

    /**
     * Draws a dot to represent an outlier. 
     * 
     * @param point  the location.
     * @param oRadius  the radius.
     * @param g2  the graphics device.
     */
    private void drawEllipse(Point2D point, double oRadius, Graphics2D g2) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[229]++;
        Ellipse2D dot = new Ellipse2D.Double(point.getX() + oRadius / 2, 
                point.getY(), oRadius, oRadius);
        g2.draw(dot);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[230]++;
    }

    /**
     * Draws two dots to represent the average value of more than one outlier.
     * 
     * @param point  the location
     * @param boxWidth  the box width.
     * @param oRadius  the radius.
     * @param g2  the graphics device.
     */
    private void drawMultipleEllipse(Point2D point, double boxWidth, 
                                     double oRadius, Graphics2D g2)  {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[231]++;
                                         
        Ellipse2D dot1 = new Ellipse2D.Double(point.getX() - (boxWidth / 2) 
                + oRadius, point.getY(), oRadius, oRadius);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[232]++;
        Ellipse2D dot2 = new Ellipse2D.Double(point.getX() + (boxWidth / 2), 
                point.getY(), oRadius, oRadius);
        g2.draw(dot1);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[233]++;
        g2.draw(dot2);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[234]++;
    }

    /**
     * Draws a triangle to indicate the presence of far-out values.
     * 
     * @param aRadius  the radius.
     * @param g2  the graphics device.
     * @param xx  the x coordinate.
     * @param m  the y coordinate.
     */
    private void drawHighFarOut(double aRadius, Graphics2D g2, double xx, 
                                double m) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[235]++;
        double side = aRadius * 2;
        g2.draw(new Line2D.Double(xx - side, m + side, xx + side, m + side));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[236]++;
        g2.draw(new Line2D.Double(xx - side, m + side, xx, m));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[237]++;
        g2.draw(new Line2D.Double(xx + side, m + side, xx, m));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[238]++;
    }

    /**
     * Draws a triangle to indicate the presence of far-out values.
     * 
     * @param aRadius  the radius.
     * @param g2  the graphics device.
     * @param xx  the x coordinate.
     * @param m  the y coordinate.
     */
    private void drawLowFarOut(double aRadius, Graphics2D g2, double xx, 
                               double m) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[239]++;
        double side = aRadius * 2;
        g2.draw(new Line2D.Double(xx - side, m - side, xx + side, m - side));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[240]++;
        g2.draw(new Line2D.Double(xx - side, m - side, xx, m));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[241]++;
        g2.draw(new Line2D.Double(xx + side, m - side, xx, m));
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[242]++;
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[243]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[85]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[86]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[244]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((obj instanceof BoxAndWhiskerRenderer) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[87]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[88]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[245]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[90]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[246]++;
        BoxAndWhiskerRenderer that = (BoxAndWhiskerRenderer) obj;
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[247]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.artifactPaint, that.artifactPaint)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[92]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[248]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.fillBox == that.fillBox) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[93]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[94]++;}
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[249]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.itemMargin == that.itemMargin) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[95]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.branches[96]++;}
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
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[250]++;
        SerialUtilities.writePaint(this.artifactPaint, stream);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[251]++;
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
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[252]++;
        this.artifactPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x.statements[253]++;
    }
   
}

class CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x ());
  }
    public static long[] statements = new long[254];
    public static long[] branches = new long[97];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[52];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.BoxAndWhiskerRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,2,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 51; i++) {
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

  public CodeCoverCoverageCounter$liagagwg2lhtnsu348a881qyrztsh25g77pick0x () {
    super("org.jfree.chart.renderer.category.BoxAndWhiskerRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 253; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 96; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 51; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.BoxAndWhiskerRenderer.java");
      for (int i = 1; i <= 253; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 96; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 51; i++) {
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

