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
 * LayeredBarRenderer.java
 * -----------------------
 * (C) Copyright 2003-2007, by Arnaud Lelievre and Contributors.
 *
 * Original Author:  Arnaud Lelievre (for Garden);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Zoheb Borbora;
 *
 * Changes
 * -------
 * 28-Aug-2003 : Version 1 (AL);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 21-Oct-2003 : Bar width moved to renderer state (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 17-Nov-2005 : Added support for gradient paint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-Aug-2006 : Fixed the bar width calculation to respect the maximum bar 
 *               width setting (thanks to Zoheb Borbora) (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectList;

/**
 * A {@link CategoryItemRenderer} that represents data using bars which are 
 * superimposed.
 */
public class LayeredBarRenderer extends BarRenderer 
                                implements Serializable {
  static {
    CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -8716572894780469487L;
  static {
    CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[1]++;
  }

    /** A list of the width of each series bar. */
    protected ObjectList seriesBarWidthList;

    /**
     * Default constructor.
     */
    public LayeredBarRenderer() {
        super();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[2]++;
        this.seriesBarWidthList = new ObjectList();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[3]++;
    }

    /**
     * Returns the bar width for a series, or <code>Double.NaN</code> if no
     * width has been set.
     *
     * @param series  the series index (zero based).
     *
     * @return The width for the series (1.0=100%, it is the maximum).
     */
    public double getSeriesBarWidth(int series) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[4]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[5]++;
        Number n = (Number) this.seriesBarWidthList.get(series);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[1]++;
            result = n.doubleValue();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[7]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[2]++;}
        return result;
    }

    /**
     * Sets the width of the bars of a series.
     *
     * @param series  the series index (zero based).
     * @param width  the width of the series bar in percentage (1.0=100%, it is 
     *               the maximum).
     */ 
    public void setSeriesBarWidth(int series, double width) {
        this.seriesBarWidthList.set(series, new Double(width));
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[8]++;
    }

    /**
     * Calculates the bar width and stores it in the renderer state.
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
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[9]++;

        // calculate the bar width - this calculation differs from the
        // BarRenderer calculation because the bars are layered on top of one
        // another, so there is effectively only one bar per category for
        // the purpose of the bar width calculation
        CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[10]++;
        CategoryDataset dataset = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[3]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[12]++;
            int columns = dataset.getColumnCount();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[13]++;
            int rows = dataset.getRowCount();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[14]++;
            double space = 0.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[15]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[5]++;
                space = dataArea.getHeight();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[17]++;

            }
            else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[6]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[18]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[7]++;
                space = dataArea.getWidth();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[19]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[8]++;}
}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[20]++;
            double maxWidth = space * getMaximumBarWidth();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[21]++;
            double categoryMargin = 0.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((columns > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[9]++;
                categoryMargin = domainAxis.getCategoryMargin();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[23]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[10]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[24]++;
            double used = space * (1 - domainAxis.getLowerMargin() 
                - domainAxis.getUpperMargin() - categoryMargin);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (((rows * columns) > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[11]++;
                state.setBarWidth(Math.min(used / (dataset.getColumnCount()), 
                        maxWidth));
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[26]++;

            } 
            else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[12]++;
                state.setBarWidth(Math.min(used, maxWidth));
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[27]++;
            }

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[4]++;}
    }

    /**
     * Draws the bar for one item in the dataset.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain (category) axis.
     * @param rangeAxis  the range (value) axis.
     * @param data  the data.
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
                         CategoryDataset data,
                         int row,
                         int column,
                         int pass) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[28]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[13]++;
            drawHorizontalItem(g2, state, dataArea, plot, domainAxis, 
                    rangeAxis, data, row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[30]++;

        }
        else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[14]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[31]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[15]++;
            drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, 
                    data, row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[32]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[16]++;}
}

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
     * @param data  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    protected void drawHorizontalItem(Graphics2D g2,
                                      CategoryItemRendererState state,
                                      Rectangle2D dataArea,
                                      CategoryPlot plot,
                                      CategoryAxis domainAxis,
                                      ValueAxis rangeAxis,
                                      CategoryDataset data,
                                      int row,
                                      int column) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[33]++;

        // nothing is drawn for null values...
        Number dataValue = data.getValue(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[17]++;
            return;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[18]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[35]++;

        // X
        double value = dataValue.doubleValue();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[36]++;
        double base = 0.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[37]++;
        double lclip = getLowerClip();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[38]++;
        double uclip = getUpperClip();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((uclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[19]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;  // cases 1, 2, 3 and 4
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[21]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[22]++;}
            base = uclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[41]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[42]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[23]++;
                value = lclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[43]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[24]++;}

        }
        else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[20]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[44]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((lclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[25]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[45]++;
int CodeCoverConditionCoverageHelper_C14; // cases 5, 6, 7 and 8
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[27]++;
                value = uclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[46]++;

            }
            else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[28]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[47]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[29]++;
                    value = lclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[48]++;

                } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[30]++;}
            }

        }
        else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[26]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[49]++;
int CodeCoverConditionCoverageHelper_C16; // cases 9, 10, 11 and 12
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[31]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[32]++;}
            base = lclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[50]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[51]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[33]++;
                value = uclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[52]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[34]++;}
        }
}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[53]++;

        RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[54]++;
        double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[55]++;
        double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[56]++;
        double rectX = Math.min(transX1, transX2);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[57]++;
        double rectWidth = Math.abs(transX2 - transX1);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[58]++;

        // Y
        double rectY = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[59]++;

        int seriesCount = getRowCount();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[60]++;

        // draw the bar...
        double shift = 0.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[61]++;
        double rectHeight = 0.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[62]++;
        double widthFactor = 1.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[63]++;
        double seriesBarWidth = getSeriesBarWidth(row);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[64]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((Double.isNaN(seriesBarWidth)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[35]++;
            widthFactor = seriesBarWidth;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[65]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[36]++;} 
        rectHeight = widthFactor * state.getBarWidth();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[66]++;
        rectY = rectY + (1 - widthFactor) * state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[67]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[37]++;
            shift = rectHeight * 0.20 / (seriesCount - 1);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[69]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[38]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[70]++;

        Rectangle2D bar = new Rectangle2D.Double(rectX, 
                (rectY + ((seriesCount - 1 - row) * shift)), rectWidth, 
                (rectHeight - (seriesCount - 1 - row) * shift * 2));
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[71]++;

        Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[72]++;
        GradientPaintTransformer t = getGradientPaintTransformer();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[73]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[39]++;
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[74]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[40]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[75]++;
        g2.fill(bar);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[76]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[77]++;
int CodeCoverConditionCoverageHelper_C21;

        // draw the outline...
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[41]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[78]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[79]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[80]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[43]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[81]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[82]++;
                g2.draw(bar);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[83]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[42]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[84]++;

        CategoryItemLabelGenerator generator 
            = getItemLabelGenerator(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[85]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[45]++;
            drawItemLabel(g2, data, row, column, plot, generator, bar, 
                    (transX1 > transX2));
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[86]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[46]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[87]++;
int CodeCoverConditionCoverageHelper_C24;        

        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[47]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[88]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[89]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[49]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[90]++;
                String tip = null;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[91]++;
                CategoryToolTipGenerator tipster 
                    = getToolTipGenerator(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[92]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[51]++;
                    tip = tipster.generateToolTip(data, row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[93]++;

                } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[52]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[94]++;
                String url = null;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[95]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[53]++;
                    url = getItemURLGenerator(row, column).generateURL(data, 
                            row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[96]++;

                } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[54]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[97]++;
                CategoryItemEntity entity = new CategoryItemEntity(bar, tip, 
                        url, data, data.getRowKey(row), 
                        data.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[98]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[50]++;}

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[48]++;}
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
     * @param data  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    protected void drawVerticalItem(Graphics2D g2,
                                    CategoryItemRendererState state,
                                    Rectangle2D dataArea,
                                    CategoryPlot plot,
                                    CategoryAxis domainAxis,
                                    ValueAxis rangeAxis,
                                    CategoryDataset data,
                                    int row,
                                    int column) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[99]++;

        // nothing is drawn for null values...
        Number dataValue = data.getValue(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[100]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[55]++;
            return;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[56]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[101]++;

        // BAR X
        double rectX = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[102]++;

        int seriesCount = getRowCount();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[103]++;

        // BAR Y
        double value = dataValue.doubleValue();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[104]++;
        double base = 0.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[105]++;
        double lclip = getLowerClip();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[106]++;
        double uclip = getUpperClip();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[107]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((uclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[57]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[108]++;
int CodeCoverConditionCoverageHelper_C30;  // cases 1, 2, 3 and 4
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[59]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[60]++;}
            base = uclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[109]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[110]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[61]++;
                value = lclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[111]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[62]++;}

        }
        else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[58]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[112]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((lclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[63]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[113]++;
int CodeCoverConditionCoverageHelper_C33; // cases 5, 6, 7 and 8
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[65]++;
                value = uclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[114]++;

            }
            else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[66]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[115]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[67]++;
                    value = lclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[116]++;

                } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[68]++;}
            }

        }
        else {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[64]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[117]++;
int CodeCoverConditionCoverageHelper_C35; // cases 9, 10, 11 and 12
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[69]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[70]++;}
            base = getLowerClip();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[118]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[119]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[71]++;
               value = uclip;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[120]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[72]++;}
        }
}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[121]++;

        RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[122]++;
        double transY1 = rangeAxis.valueToJava2D(base, dataArea, edge);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[123]++;
        double transY2 = rangeAxis.valueToJava2D(value, dataArea, edge);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[124]++;
        double rectY = Math.min(transY2, transY1);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[125]++;

        double rectWidth = state.getBarWidth();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[126]++;
        double rectHeight = Math.abs(transY2 - transY1);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[127]++;

        // draw the bar...
        double shift = 0.0;
        rectWidth = 0.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[128]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[129]++;
        double widthFactor = 1.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[130]++;
        double seriesBarWidth = getSeriesBarWidth(row);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[131]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((Double.isNaN(seriesBarWidth)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[73]++;
            widthFactor = seriesBarWidth;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[132]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[74]++;} 
        rectWidth = widthFactor * state.getBarWidth();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[133]++;
        rectX = rectX + (1 - widthFactor) * state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[134]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[135]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[75]++;
            // needs to be improved !!!
            shift = rectWidth * 0.20 / (seriesCount - 1);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[136]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[76]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[137]++;

        Rectangle2D bar = new Rectangle2D.Double(
            (rectX + ((seriesCount - 1 - row) * shift)), rectY,
            (rectWidth - (seriesCount - 1 - row) * shift * 2), rectHeight);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[138]++;
        Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[139]++;
        GradientPaintTransformer t = getGradientPaintTransformer();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[140]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[77]++;
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[141]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[78]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[142]++;
        g2.fill(bar);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[143]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[144]++;
int CodeCoverConditionCoverageHelper_C40;

        // draw the outline...
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[79]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[145]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[146]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[147]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[81]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[148]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[149]++;
                g2.draw(bar);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[150]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[82]++;}

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[80]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[151]++;

        // draw the item labels if there are any...
        double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[152]++;
        double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[153]++;

        CategoryItemLabelGenerator generator 
            = getItemLabelGenerator(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[154]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[83]++;
            drawItemLabel(g2, data, row, column, plot, generator, bar, 
                    (transX1 > transX2));
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[155]++;

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[84]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[156]++;
int CodeCoverConditionCoverageHelper_C43;        

        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[85]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[157]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[158]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[87]++;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[159]++;
                String tip = null;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[160]++;
                CategoryToolTipGenerator tipster 
                    = getToolTipGenerator(row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[161]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[89]++;
                    tip = tipster.generateToolTip(data, row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[162]++;

                } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[90]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[163]++;
                String url = null;
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[164]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[91]++;
                    url = getItemURLGenerator(row, column).generateURL(
                        data, row, column);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[165]++;

                } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[92]++;}
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[166]++;
                CategoryItemEntity entity = new CategoryItemEntity(bar, tip, 
                        url, data, data.getRowKey(row), 
                        data.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.statements[167]++;

            } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[88]++;}

        } else {
  CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l.branches[86]++;}
    }

}

class CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l ());
  }
    public static long[] statements = new long[168];
    public static long[] branches = new long[93];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[47];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.LayeredBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1};
    for (int i = 1; i <= 46; i++) {
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

  public CodeCoverCoverageCounter$2h4cbg2a72x4fkeh120l9t0qjon0erp4vy3l () {
    super("org.jfree.chart.renderer.category.LayeredBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 167; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 92; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 46; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.LayeredBarRenderer.java");
      for (int i = 1; i <= 167; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 92; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 46; i++) {
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

