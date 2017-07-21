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
 * MultiplePiePlot.java
 * --------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 29-Jan-2004 : Version 1 (DG);
 * 31-Mar-2004 : Added setPieIndex() call during drawing (DG);
 * 20-Apr-2005 : Small change for update to LegendItem constructors (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 16-Jun-2005 : Added get/setDataset() and equals() methods (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Apr-2006 : Fixed bug 1190647 - legend and section colors not consistent
 *               when aggregation limit is specified (DG);
 * 27-Sep-2006 : Updated draw() method for deprecated code (DG);
 * 17-Jan-2007 : Updated prefetchSectionPaints() to check settings in
 *               underlying PiePlot (DG);
 * 17-May-2007 : Added argument check to setPieChart() (DG);
 * 18-May-2007 : Set dataset for LegendItem (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.TableOrder;

/**
 * A plot that displays multiple pie plots using data from a 
 * {@link CategoryDataset}.
 */
public class MultiplePiePlot extends Plot implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -355377800470807389L;
  static {
    CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[1]++;
  }
    
    /** The chart object that draws the individual pie charts. */
    private JFreeChart pieChart;
    
    /** The dataset. */
    private CategoryDataset dataset;
    
    /** The data extract order (by row or by column). */
    private TableOrder dataExtractOrder;
    
    /** The pie section limit percentage. */
    private double limit = 0.0;
  {
    CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[2]++;
  }
    
    /** 
     * The key for the aggregated items. 
     * @since 1.0.2
     */
    private Comparable aggregatedItemsKey;
    
    /** 
     * The paint for the aggregated items. 
     * @since 1.0.2
     */
    private transient Paint aggregatedItemsPaint;
    
    /** 
     * The colors to use for each section. 
     * @since 1.0.2
     */
    private transient Map sectionPaints;
    
    /**
     * Creates a new plot with no data.
     */
    public MultiplePiePlot() {
        this(null);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[3]++;
    }
    
    /**
     * Creates a new plot.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public MultiplePiePlot(CategoryDataset dataset) {
        super();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[4]++;
        this.dataset = dataset;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[5]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[6]++;
        PiePlot piePlot = new PiePlot(null);
        this.pieChart = new JFreeChart(piePlot);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[7]++;
        this.pieChart.removeLegend();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[8]++;
        this.dataExtractOrder = TableOrder.BY_COLUMN;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[9]++;
        this.pieChart.setBackgroundPaint(null);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[10]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[11]++;
        TextTitle seriesTitle = new TextTitle("Series Title", 
                new Font("SansSerif", Font.BOLD, 12));
        seriesTitle.setPosition(RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[12]++;
        this.pieChart.setTitle(seriesTitle);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[13]++;
        this.aggregatedItemsKey = "Other";
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[14]++;
        this.aggregatedItemsPaint = Color.lightGray;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[15]++;
        this.sectionPaints = new HashMap();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[16]++;
    }
    
    /**
     * Returns the dataset used by the plot.
     * 
     * @return The dataset (possibly <code>null</code>).
     */
    public CategoryDataset getDataset() {
        return this.dataset;   
    }
    
    /**
     * Sets the dataset used by the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public void setDataset(CategoryDataset dataset) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        // if there is an existing dataset, remove the plot from the list of 
        // change listeners...
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[1]++;
            this.dataset.removeChangeListener(this);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[18]++;

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[2]++;}

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[19]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[3]++;
            setDatasetGroup(dataset.getGroup());
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[21]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[22]++;

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[4]++;}

        // send a dataset change event to self to trigger plot change event
        datasetChanged(new DatasetChangeEvent(this, dataset));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[23]++;
    }
    
    /**
     * Returns the pie chart that is used to draw the individual pie plots.
     * 
     * @return The pie chart (never <code>null</code>).
     * 
     * @see #setPieChart(JFreeChart)
     */
    public JFreeChart getPieChart() {
        return this.pieChart;
    }
    
    /**
     * Sets the chart that is used to draw the individual pie plots.  The
     * chart's plot must be an instance of {@link PiePlot}.
     * 
     * @param pieChart  the pie chart (<code>null</code> not permitted).
     *
     * @see #getPieChart()
     */
    public void setPieChart(JFreeChart pieChart) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pieChart == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[5]++;
            throw new IllegalArgumentException("Null 'pieChart' argument.");

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[6]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((pieChart.getPlot() instanceof PiePlot) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[7]++;
            throw new IllegalArgumentException("The 'pieChart' argument must "
                    + "be a chart based on a PiePlot.");

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[8]++;}
        this.pieChart = pieChart;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[26]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[27]++;
    }
    
    /**
     * Returns the data extract order (by row or by column).
     * 
     * @return The data extract order (never <code>null</code>).
     */
    public TableOrder getDataExtractOrder() {
        return this.dataExtractOrder;
    }
    
    /**
     * Sets the data extract order (by row or by column) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param order  the order (<code>null</code> not permitted).
     */
    public void setDataExtractOrder(TableOrder order) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[9]++;
            throw new IllegalArgumentException("Null 'order' argument");

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[10]++;}
        this.dataExtractOrder = order;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[29]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[30]++;
    }
    
    /**
     * Returns the limit (as a percentage) below which small pie sections are 
     * aggregated.
     * 
     * @return The limit percentage.
     */
    public double getLimit() {
        return this.limit;
    }
    
    /**
     * Sets the limit below which pie sections are aggregated.  
     * Set this to 0.0 if you don't want any aggregation to occur.
     * 
     * @param limit  the limit percent.
     */
    public void setLimit(double limit) {
        this.limit = limit;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[31]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[32]++;
    }
    
    /**
     * Returns the key for aggregated items in the pie plots, if there are any.
     * The default value is "Other".
     * 
     * @return The aggregated items key.
     * 
     * @since 1.0.2
     */
    public Comparable getAggregatedItemsKey() {
        return this.aggregatedItemsKey;
    }
    
    /**
     * Sets the key for aggregated items in the pie plots.  You must ensure 
     * that this doesn't clash with any keys in the dataset.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @since 1.0.2
     */
    public void setAggregatedItemsKey(Comparable key) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[11]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[12]++;}
        this.aggregatedItemsKey = key;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[34]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[35]++;
    }
    
    /**
     * Returns the paint used to draw the pie section representing the 
     * aggregated items.  The default value is <code>Color.lightGray</code>.
     * 
     * @return The paint.
     * 
     * @since 1.0.2
     */
    public Paint getAggregatedItemsPaint() {
        return this.aggregatedItemsPaint;
    }
    
    /**
     * Sets the paint used to draw the pie section representing the aggregated
     * items and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @since 1.0.2
     */
    public void setAggregatedItemsPaint(Paint paint) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[13]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[14]++;}
        this.aggregatedItemsPaint = paint;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[37]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[38]++;
    }
    
    /**
     * Returns a short string describing the type of plot.
     *
     * @return The plot type.
     */
    public String getPlotType() {
        return "Multiple Pie Plot";  
         // TODO: need to fetch this from localised resources
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the state from the parent plot, if there is one.
     * @param info  collects info about the drawing.
     */
    public void draw(Graphics2D g2, 
                     Rectangle2D area,
                     Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[39]++;
        
       
        // adjust the drawing area for the plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[40]++;
        drawBackground(g2, area);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[41]++;
        drawOutline(g2, area);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[42]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
        
        // check that there is some data to display...
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((DatasetUtilities.isEmptyOrNull(this.dataset)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[15]++;
            drawNoDataMessage(g2, area);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[44]++;
            return;

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[16]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[45]++;

        int pieCount = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[17]++;
            pieCount = this.dataset.getRowCount();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[47]++;

        }
        else {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[18]++;
            pieCount = this.dataset.getColumnCount();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[48]++;
        }
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[49]++;

        // the columns variable is always >= rows
        int displayCols = (int) Math.ceil(Math.sqrt(pieCount));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[50]++;
        int displayRows 
            = (int) Math.ceil((double) pieCount / (double) displayCols);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[51]++;
int CodeCoverConditionCoverageHelper_C10;

        // swap rows and columns to match plotArea shape
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((displayCols > displayRows) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((area.getWidth() < area.getHeight()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[19]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[52]++;
            int temp = displayCols;
            displayCols = displayRows;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[53]++;
            displayRows = temp;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[54]++;

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[20]++;}

        prefetchSectionPaints();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[55]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[56]++;
        
        int x = (int) area.getX();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[57]++;
        int y = (int) area.getY();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[58]++;
        int width = ((int) area.getWidth()) / displayCols;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[59]++;
        int height = ((int) area.getHeight()) / displayRows;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[60]++;
        int row = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[61]++;
        int column = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[62]++;
        int diff = (displayRows * displayCols) - pieCount;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[63]++;
        int xoffset = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[64]++;
        Rectangle rect = new Rectangle();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[65]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;

        for (int pieIndex = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((pieIndex < pieCount) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); pieIndex++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[1]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[2]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[3]++;
}
            rect.setBounds(x + xoffset + (width * column), y + (height * row), 
                    width, height);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[66]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[67]++;

            String title = null;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[21]++;
                title = this.dataset.getRowKey(pieIndex).toString();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[69]++;

            }
            else {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[22]++;
                title = this.dataset.getColumnKey(pieIndex).toString();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[70]++;
            }
            this.pieChart.setTitle(title);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[71]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[72]++;
            
            PieDataset piedataset = null;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[73]++;
            PieDataset dd = new CategoryToPieDataset(this.dataset, 
                    this.dataExtractOrder, pieIndex);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[74]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.limit > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[23]++;
                piedataset = DatasetUtilities.createConsolidatedPieDataset(
                        dd, this.aggregatedItemsKey, this.limit);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[75]++;

            }
            else {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[24]++;
                piedataset = dd;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[76]++;
            }
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[77]++;
            PiePlot piePlot = (PiePlot) this.pieChart.getPlot();
            piePlot.setDataset(piedataset);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[78]++;
            piePlot.setPieIndex(pieIndex);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[79]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[80]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;
            
            // update the section colors to match the global colors...
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < piedataset.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[4]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[5]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[6]++;
}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[81]++;
                Comparable key = piedataset.getKey(i);
                Paint p;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[82]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((key.equals(this.aggregatedItemsKey)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[25]++;
                    p = this.aggregatedItemsPaint;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[83]++;

                }
                else {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[26]++;
                    p = (Paint) this.sectionPaints.get(key);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[84]++;
                }
                piePlot.setSectionPaint(key, p);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[85]++;
            }
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[86]++;
            
            ChartRenderingInfo subinfo = null;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[87]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[27]++;
                subinfo = new ChartRenderingInfo();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[88]++;

            } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[28]++;}
            this.pieChart.draw(g2, rect, subinfo);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[89]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[90]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[29]++;
                info.getOwner().getEntityCollection().addAll(
                        subinfo.getEntityCollection());
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[91]++;
                info.addSubplotInfo(subinfo.getPlotInfo());
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[92]++;

            } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[30]++;}
            
            ++column;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[93]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[94]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((column == displayCols) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[31]++;
                column = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[95]++;
                ++row;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[96]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[97]++;
int CodeCoverConditionCoverageHelper_C19;

                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((row == displayRows - 1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((diff != 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[33]++;
                    xoffset = (diff * width) / 2;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[98]++;

                } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[34]++;}

            } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[32]++;}
        }

    }
    
    /**
     * For each key in the dataset, check the <code>sectionPaints</code>
     * cache to see if a paint is associated with that key and, if not, 
     * fetch one from the drawing supplier.  These colors are cached so that
     * the legend and all the subplots use consistent colors.
     */
    private void prefetchSectionPaints() {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[99]++;
        
        // pre-fetch the colors for each key...this is because the subplots
        // may not display every key, but we need the coloring to be
        // consistent...
        
        PiePlot piePlot = (PiePlot) getPieChart().getPlot();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[100]++;
int CodeCoverConditionCoverageHelper_C20;
        
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[35]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[101]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[7]++;


int CodeCoverConditionCoverageHelper_C21;
            // column keys provide potential keys for individual pies
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((c < this.dataset.getColumnCount()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[7]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[8]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[9]++;
}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[102]++;
                Comparable key = this.dataset.getColumnKey(c);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[103]++;
                Paint p = piePlot.getSectionPaint(key);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[104]++;
int CodeCoverConditionCoverageHelper_C22; 
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[37]++;
                    p = (Paint) this.sectionPaints.get(key);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[105]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[106]++;
int CodeCoverConditionCoverageHelper_C23;
                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[39]++;
                        p = getDrawingSupplier().getNextPaint();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[107]++;

                    } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[40]++;}

                } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[38]++;}
                this.sectionPaints.put(key, p);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[108]++;
            }

        }
        else {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[36]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[109]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[10]++;


int CodeCoverConditionCoverageHelper_C24;
            // row keys provide potential keys for individual pies            
            for (int r = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((r < this.dataset.getRowCount()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[10]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[11]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[12]++;
}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[110]++;
                Comparable key = this.dataset.getRowKey(r);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[111]++;
                Paint p = piePlot.getSectionPaint(key);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[112]++;
int CodeCoverConditionCoverageHelper_C25; 
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[41]++;
                    p = (Paint) this.sectionPaints.get(key);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[113]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[114]++;
int CodeCoverConditionCoverageHelper_C26;
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[43]++;
                        p = getDrawingSupplier().getNextPaint();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[115]++;

                    } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[44]++;}

                } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[42]++;}
                this.sectionPaints.put(key, p);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[116]++;
            }
        }
        
    }
    
    /**
     * Returns a collection of legend items for the pie chart.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[117]++;

        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[118]++;
int CodeCoverConditionCoverageHelper_C27;
        
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[45]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[119]++;
            List keys = null;
      
            prefetchSectionPaints();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[120]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[121]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[47]++;
                keys = this.dataset.getColumnKeys();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[122]++;

            }
            else {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[48]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[123]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[49]++;
                keys = this.dataset.getRowKeys();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[124]++;

            } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[50]++;}
}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[125]++;
int CodeCoverConditionCoverageHelper_C30;

            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[51]++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[126]++;
                int section = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[127]++;
                Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[128]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[13]++;


int CodeCoverConditionCoverageHelper_C31;
                while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[13]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[14]--;
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.loops[15]++;
}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[129]++;
                    Comparable key = (Comparable) iterator.next();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[130]++;
                    String label = key.toString();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[131]++;
                    String description = label;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[132]++;
                    Paint paint = (Paint) this.sectionPaints.get(key);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[133]++;
                    LegendItem item = new LegendItem(label, description, 
                            null, null, Plot.DEFAULT_LEGEND_ITEM_CIRCLE, 
                            paint, Plot.DEFAULT_OUTLINE_STROKE, paint);
                    item.setDataset(getDataset());
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[134]++;
                    result.add(item);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[135]++;
                    section++;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[136]++;
                }

            } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[52]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[137]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.limit > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[53]++;
                result.add(new LegendItem(this.aggregatedItemsKey.toString(), 
                        this.aggregatedItemsKey.toString(), null, null, 
                        Plot.DEFAULT_LEGEND_ITEM_CIRCLE, 
                        this.aggregatedItemsPaint,
                        Plot.DEFAULT_OUTLINE_STROKE, 
                        this.aggregatedItemsPaint));
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[138]++;

            } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[54]++;}

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[46]++;}
        return result;
    }
    
    /**
     * Tests this plot for equality with an arbitrary object.  Note that the 
     * plot's dataset is not considered in the equality test.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> if this plot is equal to <code>obj</code>, and
     *     <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[139]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[55]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[56]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[140]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((obj instanceof MultiplePiePlot) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[57]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[58]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[141]++;
        MultiplePiePlot that = (MultiplePiePlot) obj;
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[142]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder != that.dataExtractOrder) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[59]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[60]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[143]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.limit != that.limit) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[61]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[62]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[144]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.aggregatedItemsKey.equals(that.aggregatedItemsKey)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[64]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[145]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.aggregatedItemsPaint, 
                that.aggregatedItemsPaint)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[66]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[146]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.pieChart, that.pieChart)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[67]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[68]++;}
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[147]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[69]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.branches[70]++;}
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
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[148]++;
        SerialUtilities.writePaint(this.aggregatedItemsPaint, stream);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[149]++;
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
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[150]++;
        this.aggregatedItemsPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[151]++;
        this.sectionPaints = new HashMap();
CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5.statements[152]++;
    }

    
}

class CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5 ());
  }
    public static long[] statements = new long[153];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[41];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.MultiplePiePlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$91q9adoodzyqptqpqgkdjfb7kzswwv5 () {
    super("org.jfree.chart.plot.MultiplePiePlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 152; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.MultiplePiePlot.java");
      for (int i = 1; i <= 152; i++) {
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
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

