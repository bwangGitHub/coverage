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
 * ------------------------------
 * GroupedStackedBarRenderer.java
 * ------------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 29-Apr-2004 : Version 1 (DG);
 * 08-Jul-2004 : Added equals() method (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 22-Sep-2005 : Renamed getMaxBarWidth() --> getMaximumBarWidth() (DG);
 * 
 */
 
package org.jfree.chart.renderer.category;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
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
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that draws stacked bars within groups.  This will probably be 
 * merged with the {@link StackedBarRenderer} class at some point.
 */
public class GroupedStackedBarRenderer extends StackedBarRenderer 
                                       implements Cloneable, PublicCloneable, 
                                                  Serializable {
  static {
    CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.ping();
  }

            
    /** For serialization. */
    private static final long serialVersionUID = -2725921399005922939L;
  static {
    CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[1]++;
  }
    
    /** A map used to assign each series to a group. */
    private KeyToGroupMap seriesToGroupMap;
    
    /**
     * Creates a new renderer.
     */
    public GroupedStackedBarRenderer() {
        super();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[2]++;
        this.seriesToGroupMap = new KeyToGroupMap();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[3]++;
    }
    
    /**
     * Updates the map used to assign each series to a group, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param map  the map (<code>null</code> not permitted).
     */
    public void setSeriesToGroupMap(KeyToGroupMap map) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((map == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[1]++;
            throw new IllegalArgumentException("Null 'map' argument.");
   
        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[2]++;}
        this.seriesToGroupMap = map;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[5]++;   
        fireChangeEvent();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[6]++;
    }
    
    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (or <code>null</code> if the dataset is 
     *         <code>null</code> or empty).
     */
    public Range findRangeBounds(CategoryDataset dataset) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[7]++;
        Range r = DatasetUtilities.findStackedRangeBounds(
                dataset, this.seriesToGroupMap);
        return r;
    }

    /**
     * Calculates the bar width and stores it in the renderer state.  We 
     * override the method in the base class to take account of the 
     * series-to-group mapping.
     * 
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param rendererIndex  the renderer index.
     * @param state  the renderer state.
     */
    protected void calculateBarWidth(CategoryPlot plot, 
                                     Rectangle2D dataArea, 
                                     int rendererIndex,
                                     CategoryItemRendererState state) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[8]++;

        // calculate the bar width
        CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[9]++;
        CategoryDataset data = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[3]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[11]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[12]++;
            double space = 0.0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[5]++;
                space = dataArea.getHeight();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[14]++;

            }
            else {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[6]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[15]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[7]++;
                space = dataArea.getWidth();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[16]++;

            } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[8]++;}
}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[17]++;
            double maxWidth = space * getMaximumBarWidth();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[18]++;
            int groups = this.seriesToGroupMap.getGroupCount();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[19]++;
            int categories = data.getColumnCount();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[20]++;
            int columns = groups * categories;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[21]++;
            double categoryMargin = 0.0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[22]++;
            double itemMargin = 0.0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((categories > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[9]++;
                categoryMargin = xAxis.getCategoryMargin();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[24]++;

            } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[10]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((groups > 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[11]++;
                itemMargin = getItemMargin();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[26]++;
   
            } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[12]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[27]++;

            double used = space * (1 - xAxis.getLowerMargin() 
                                     - xAxis.getUpperMargin()
                                     - categoryMargin - itemMargin);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((columns > 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[13]++;
                state.setBarWidth(Math.min(used / columns, maxWidth));
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[29]++;

            }
            else {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[14]++;
                state.setBarWidth(Math.min(used, maxWidth));
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[30]++;
            }

        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[4]++;}

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
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[31]++;
        // calculate bar width...
        double space = 0.0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[15]++;
            space = dataArea.getHeight();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[33]++;

        }
        else {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[16]++;
            space = dataArea.getWidth();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[34]++;
        }
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[35]++;
        double barW0 = domainAxis.getCategoryStart(
            column, getColumnCount(), dataArea, plot.getDomainAxisEdge()
        );
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[36]++;
        int groupCount = this.seriesToGroupMap.getGroupCount();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[37]++;
        int groupIndex = this.seriesToGroupMap.getGroupIndex(
            this.seriesToGroupMap.getGroup(plot.getDataset().getRowKey(row))
        );
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[38]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((groupCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[17]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[40]++;
            double groupGap = space * getItemMargin() 
                              / (categoryCount * (groupCount - 1));
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[41]++;
            double groupW = calculateSeriesWidth(
                space, domainAxis, categoryCount, groupCount
            );
            barW0 = barW0 + groupIndex * (groupW + groupGap) 
                          + (groupW / 2.0) - (state.getBarWidth() / 2.0);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[42]++;

        }
        else {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[18]++;
            barW0 = domainAxis.getCategoryMiddle(
                column, getColumnCount(), dataArea, plot.getDomainAxisEdge()
            ) - state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[43]++;
        }
        return barW0;
    }
    
    /**
     * Draws a stacked bar for a specific item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain (category) axis.
     * @param rangeAxis  the range (value) axis.
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
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[44]++;
     
        // nothing is drawn for null values...
        Number dataValue = dataset.getValue(row, column);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[19]++;
            return;

        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[20]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[46]++;
        
        double value = dataValue.doubleValue();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[47]++;
        Comparable group 
            = this.seriesToGroupMap.getGroup(dataset.getRowKey(row));
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[48]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[49]++;
        double barW0 = calculateBarW0(
            plot, orientation, dataArea, domainAxis, 
            state, row, column
        );
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[50]++;

        double positiveBase = 0.0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[51]++;
        double negativeBase = 0.0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[52]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < row) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.loops[1]--;
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.loops[2]--;
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.loops[3]++;
}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((group.equals(this.seriesToGroupMap.getGroup(
                    dataset.getRowKey(i)))) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[21]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[54]++;
                Number v = dataset.getValue(i, column);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[23]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[56]++;
                    double d = v.doubleValue();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[57]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((d > 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[25]++;
                        positiveBase = positiveBase + d;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[58]++;

                    }
                    else {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[26]++;
                        negativeBase = negativeBase + d;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[59]++;
                    }

                } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[24]++;}

            } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[22]++;}
        }

        double translatedBase;
        double translatedValue;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[60]++;
        RectangleEdge location = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((value > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[27]++;
            translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, 
                    location);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[62]++;
            translatedValue = rangeAxis.valueToJava2D(positiveBase + value, 
                    dataArea, location);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[63]++;

        }
        else {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[28]++;
            translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, 
                    location);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[64]++;
            translatedValue = rangeAxis.valueToJava2D(negativeBase + value, 
                    dataArea, location);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[65]++;
        }
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[66]++;
        double barL0 = Math.min(translatedBase, translatedValue);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[67]++;
        double barLength = Math.max(Math.abs(translatedValue - translatedBase),
                getMinimumBarLength());
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[68]++;

        Rectangle2D bar = null;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[69]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[29]++;
            bar = new Rectangle2D.Double(barL0, barW0, barLength, 
                    state.getBarWidth());
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[70]++;

        }
        else {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[30]++;
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), 
                    barLength);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[71]++;
        }
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[72]++;
        Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[73]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((getGradientPaintTransformer() != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[31]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[74]++;
            GradientPaint gp = (GradientPaint) itemPaint;
            itemPaint = getGradientPaintTransformer().transform(gp, bar);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[75]++;

        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[32]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[76]++;
        g2.fill(bar);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[77]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[78]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[33]++;
            g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[79]++;
            g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[80]++;
            g2.draw(bar);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[81]++;

        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[34]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[82]++;

        CategoryItemLabelGenerator generator 
            = getItemLabelGenerator(row, column);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[83]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[35]++;
            drawItemLabel(
                g2, dataset, row, column, plot, generator, bar, 
                (value < 0.0)
            );
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[84]++;

        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[36]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[85]++;
int CodeCoverConditionCoverageHelper_C20;        
                
        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[37]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[86]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[39]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[88]++;
                String tip = null;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[89]++;
                CategoryToolTipGenerator tipster = getToolTipGenerator(row, 
                        column);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[90]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[41]++;
                    tip = tipster.generateToolTip(dataset, row, column);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[91]++;

                } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[42]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[92]++;
                String url = null;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[93]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[43]++;
                    url = getItemURLGenerator(row, column).generateURL(
                            dataset, row, column);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[94]++;

                } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[44]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[95]++;
                CategoryItemEntity entity = new CategoryItemEntity(
                        bar, tip, url, dataset, dataset.getRowKey(row), 
                        dataset.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[96]++;

            } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[40]++;}

        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[38]++;}
        
    }
   
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[97]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[45]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[46]++;}
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[98]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((obj instanceof GroupedStackedBarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[47]++;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[99]++;
            GroupedStackedBarRenderer r = (GroupedStackedBarRenderer) obj;
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.statements[100]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((r.seriesToGroupMap.equals(this.seriesToGroupMap)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[49]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[50]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt.branches[48]++;}
        return false;
    }
    
}

class CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt ());
  }
    public static long[] statements = new long[101];
    public static long[] branches = new long[51];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.GroupedStackedBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,2,1};
    for (int i = 1; i <= 26; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$19mzdtbs9zo0btnnc1njsxuttuy4grfzmkmtrpsai2ywjtt () {
    super("org.jfree.chart.renderer.category.GroupedStackedBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 100; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 50; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.GroupedStackedBarRenderer.java");
      for (int i = 1; i <= 100; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 50; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

