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
 * LevelRenderer.java
 * ------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 09-Jan-2004 : Version 1 (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 23-Jan-2006 : Renamed getMaxItemWidth() --> getMaximumItemWidth() (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A {@link CategoryItemRenderer} that draws individual data items as 
 * horizontal lines, spaced in the same way as bars in a bar chart.
 */
public class LevelRenderer extends AbstractCategoryItemRenderer 
                           implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8204856624355025117L;
  static {
    CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[1]++;
  }
    
    /** The default item margin percentage. */
    public static final double DEFAULT_ITEM_MARGIN = 0.20;
  static {
    CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[2]++;
  }

    /** The margin between items within a category. */
    private double itemMargin;

    /** The maximum item width as a percentage of the available space. */
    private double maxItemWidth;
    
    /**
     * Creates a new renderer with default settings.
     */
    public LevelRenderer() {
        super();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[3]++;
        this.itemMargin = DEFAULT_ITEM_MARGIN;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[4]++;
        this.maxItemWidth = 1.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[5]++;  // 100 percent, so it will not apply unless 
                                  // changed
    }

    /**
     * Returns the item margin.
     *
     * @return The margin.
     */
    public double getItemMargin() {
        return this.itemMargin;
    }

    /**
     * Sets the item margin and sends a {@link RendererChangeEvent} to all
     * registered listeners.  The value is expressed as a percentage of the 
     * available width for plotting all the bars, with the resulting amount to 
     * be distributed between all the bars evenly.
     *
     * @param percent  the new margin.
     */
    public void setItemMargin(double percent) {
        this.itemMargin = percent;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[6]++;
        fireChangeEvent();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[7]++;
    }
    
    /**
     * Returns the maximum width, as a percentage of the available drawing 
     * space.
     * 
     * @return The maximum width.
     * 
     * @deprecated Use {@link #getMaximumItemWidth()} instead.
     */
    public double getMaxItemWidth() {
        return this.maxItemWidth;
    }
    
    /**
     * Sets the maximum item width, which is specified as a percentage of the 
     * available space for all items, and sends a {@link RendererChangeEvent} 
     * to all registered listeners.
     * 
     * @param percent  the percent.
     * 
     * @deprecated Use {@link #setMaximumItemWidth(double)} instead.
     */
    public void setMaxItemWidth(double percent) {
        this.maxItemWidth = percent;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[8]++;
        fireChangeEvent();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[9]++;
    }

    /**
     * Returns the maximum width, as a percentage of the available drawing 
     * space.
     * 
     * @return The maximum width.
     */
    public double getMaximumItemWidth() {
        return getMaxItemWidth();
    }
    
    /**
     * Sets the maximum item width, which is specified as a percentage of the 
     * available space for all items, and sends a {@link RendererChangeEvent} 
     * to all registered listeners.
     * 
     * @param percent  the percent.
     */
    public void setMaximumItemWidth(double percent) {
        setMaxItemWidth(percent);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[10]++;
    }

    /**
     * Initialises the renderer and returns a state object that will be passed 
     * to subsequent calls to the drawItem method.
     * <p>
     * This method gets called once at the start of the process of drawing a 
     * chart.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data is to be plotted.
     * @param plot  the plot.
     * @param rendererIndex  the renderer index.
     * @param info  collects chart rendering information for return to caller.
     * 
     * @return The renderer state.
     *
     */
    public CategoryItemRendererState initialise(Graphics2D g2,
                                                Rectangle2D dataArea,
                                                CategoryPlot plot,
                                                int rendererIndex,
                                                PlotRenderingInfo info) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[11]++;

        CategoryItemRendererState state = super.initialise(g2, dataArea, plot, 
                rendererIndex, info);
        calculateItemWidth(plot, dataArea, rendererIndex, state);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[12]++;
        return state;
        
    }
    
    /**
     * Calculates the bar width and stores it in the renderer state.
     * 
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param rendererIndex  the renderer index.
     * @param state  the renderer state.
     */
    protected void calculateItemWidth(CategoryPlot plot, 
                                      Rectangle2D dataArea, 
                                      int rendererIndex,
                                      CategoryItemRendererState state) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[13]++;
                                         
        CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[14]++;
        CategoryDataset dataset = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[1]++;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[16]++;
            int columns = dataset.getColumnCount();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[17]++;
            int rows = dataset.getRowCount();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[18]++;
            double space = 0.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[19]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[3]++;
                space = dataArea.getHeight();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[21]++;

            }
            else {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[4]++;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[22]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[5]++;
                space = dataArea.getWidth();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[23]++;

            } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[6]++;}
}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[24]++;
            double maxWidth = space * getMaxItemWidth();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[25]++;
            double categoryMargin = 0.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[26]++;
            double currentItemMargin = 0.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((columns > 1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[7]++;
                categoryMargin = domainAxis.getCategoryMargin();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[28]++;

            } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[8]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((rows > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[9]++;
                currentItemMargin = getItemMargin();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[30]++;

            } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[10]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[31]++;
            double used = space * (1 - domainAxis.getLowerMargin() 
                                     - domainAxis.getUpperMargin()
                                     - categoryMargin - currentItemMargin);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (((rows * columns) > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[11]++;
                state.setBarWidth(Math.min(used / (rows * columns), maxWidth));
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[33]++;

            }
            else {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[12]++;
                state.setBarWidth(Math.min(used, maxWidth));
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[34]++;
            }

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[2]++;}
    }

    /**
     * Calculates the coordinate of the first "side" of a bar.  This will be 
     * the minimum x-coordinate for a vertical bar, and the minimum 
     * y-coordinate for a horizontal bar.
     * 
     * @param plot  the plot.
     * @param orientation  the plot orientation.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param state  the renderer state (has the bar width precalculated).
     * @param row  the row index.
     * @param column  the column index.
     * 
     * @return The coordinate.
     */
    protected double calculateBarW0(CategoryPlot plot, 
                                    PlotOrientation orientation, 
                                    Rectangle2D dataArea,
                                    CategoryAxis domainAxis,
                                    CategoryItemRendererState state,
                                    int row,
                                    int column) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[35]++;
        // calculate bar width...
        double space = 0.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[13]++;
            space = dataArea.getHeight();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[37]++;

        }
        else {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[14]++;
            space = dataArea.getWidth();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[38]++;
        }
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[39]++;
        double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[40]++;
        int seriesCount = getRowCount();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[41]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[15]++;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[43]++;
            double seriesGap = space * getItemMargin() 
                    / (categoryCount * (seriesCount - 1));
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[44]++;
            double seriesW = calculateSeriesWidth(space, domainAxis, 
                    categoryCount, seriesCount);
            barW0 = barW0 + row * (seriesW + seriesGap) 
                          + (seriesW / 2.0) - (state.getBarWidth() / 2.0);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[45]++;

        }
        else {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[16]++;
            barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                    dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() 
                    / 2.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[46]++;
        }
        return barW0;
    }
    
    /**
     * Draws the bar for a single (series, category) data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data area.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2, CategoryItemRendererState state,
            Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryDataset dataset, int row, int column,
            int pass) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[47]++;

        // nothing is drawn for null values...
        Number dataValue = dataset.getValue(row, column);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[17]++;
            return;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[18]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[49]++;
        
        double value = dataValue.doubleValue();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[50]++;
        
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[51]++;
        double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, 
                state, row, column);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[52]++;
        RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[53]++;
        double barL = rangeAxis.valueToJava2D(value, dataArea, edge);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[54]++;

        // draw the bar...
        Line2D line = null;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[55]++;
        double x = 0.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[56]++;
        double y = 0.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[57]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[19]++;
            x = barL;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[58]++;
            y = barW0 + state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[59]++;
            line = new Line2D.Double(barL, barW0, barL, 
                    barW0 + state.getBarWidth());
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[60]++;

        }
        else {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[20]++;
            x = barW0 + state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[61]++;
            y = barL;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[62]++;
            line = new Line2D.Double(barW0, barL, barW0 + state.getBarWidth(), 
                    barL);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[63]++;
        }
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[64]++;
        Stroke itemStroke = getItemStroke(row, column);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[65]++;
        Paint itemPaint = getItemPaint(row, column);
        g2.setStroke(itemStroke);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[66]++;
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[67]++;
        g2.draw(line);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[68]++;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[69]++;

        CategoryItemLabelGenerator generator = getItemLabelGenerator(row, 
                column);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[70]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[21]++;
            drawItemLabel(g2, orientation, dataset, row, column, x, y, 
                    (value < 0.0));
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[71]++;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[22]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[72]++;
int CodeCoverConditionCoverageHelper_C12;        
                
        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[23]++;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[73]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[74]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[25]++;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[75]++;
                String tip = null;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[76]++;
                CategoryToolTipGenerator tipster = getToolTipGenerator(row, 
                        column);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[77]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[27]++;
                    tip = tipster.generateToolTip(dataset, row, column);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[78]++;

                } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[28]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[79]++;
                String url = null;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[80]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[29]++;
                    url = getItemURLGenerator(row, column).generateURL(dataset,
                            row, column);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[81]++;

                } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[30]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[82]++;
                CategoryItemEntity entity = new CategoryItemEntity(
                        line.getBounds(), tip, url, dataset, 
                        dataset.getRowKey(row), dataset.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[83]++;

            } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[26]++;}


        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[24]++;}

    }

    /**
     * Calculates the available space for each series.
     * 
     * @param space  the space along the entire axis (in Java2D units).
     * @param axis  the category axis.
     * @param categories  the number of categories.
     * @param series  the number of series.
     * 
     * @return The width of one series.
     */
    protected double calculateSeriesWidth(double space, CategoryAxis axis, 
                                          int categories, int series) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[84]++;
        double factor = 1.0 - getItemMargin() - axis.getLowerMargin() 
                        - axis.getUpperMargin();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[85]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((categories > 1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[31]++;
            factor = factor - axis.getCategoryMargin();
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[86]++;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[32]++;}
        return (space * factor) / (categories * series);
    }
    
    /**
     * Tests an object for equality with this instance.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[87]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[33]++;
            return true;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[34]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[88]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj instanceof LevelRenderer) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[36]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[89]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[38]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[90]++;
        LevelRenderer that = (LevelRenderer) obj;
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[91]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.itemMargin != that.itemMargin) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[39]++;              
            return false;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[40]++;}
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.statements[92]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.maxItemWidth != that.maxItemWidth) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl.branches[42]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl ());
  }
    public static long[] statements = new long[93];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.LevelRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$6cpldf67udd9x8beb7guzfh4r7fl () {
    super("org.jfree.chart.renderer.category.LevelRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 92; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.LevelRenderer.java");
      for (int i = 1; i <= 92; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
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

