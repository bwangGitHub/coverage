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
 * StatisticalBarRenderer.java
 * ---------------------------
 * (C) Copyright 2002-2007, by Pascal Collet and Contributors.
 *
 * Original Author:  Pascal Collet;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 21-Aug-2002 : Version 1, contributed by Pascal Collet (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 24-Oct-2002 : Changes to dataset interface (DG);
 * 05-Nov-2002 : Base dataset is now TableDataset not CategoryDataset (DG);
 * 05-Feb-2003 : Updates for new DefaultStatisticalCategoryDataset (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 06-Oct-2003 : Corrected typo in exception message (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 15-Jun-2005 : Added errorIndicatorPaint attribute (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 19-May-2006 : Added support for tooltips and URLs (DG);
 * 12-Jul-2006 : Added support for item labels (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 28-Aug-2007 : Fixed NullPointerException - see bug 1779941 (DG);
 * 14-Nov-2007 : Added errorIndicatorStroke, and fixed bugs with drawBarOutline
 *               and gradientPaintTransformer attributes being ignored (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.StatisticalCategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that handles the drawing a bar plot where
 * each bar has a mean value and a standard deviation line.
 */
public class StatisticalBarRenderer extends BarRenderer
                                    implements CategoryItemRenderer, 
                                               Cloneable, PublicCloneable, 
                                               Serializable {
  static {
    CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4986038395414039117L;
  static {
    CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[1]++;
  }
    
    /** The paint used to show the error indicator. */
    private transient Paint errorIndicatorPaint;
    
    /**
     * The stroke used to draw the error indicators. 
     * 
     * @since 1.0.8
     */
    private transient Stroke errorIndicatorStroke;
    
    /**
     * Default constructor.
     */
    public StatisticalBarRenderer() {
        super();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[2]++;
        this.errorIndicatorPaint = Color.gray;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[3]++;
        this.errorIndicatorStroke = new BasicStroke(1.0f);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[4]++;
    }

    /**
     * Returns the paint used for the error indicators.
     * 
     * @return The paint used for the error indicators (possibly 
     *         <code>null</code>).
     *         
     * @see #setErrorIndicatorPaint(Paint)
     */
    public Paint getErrorIndicatorPaint() {
        return this.errorIndicatorPaint;   
    }

    /**
     * Sets the paint used for the error indicators (if <code>null</code>, 
     * the item outline paint is used instead) and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getErrorIndicatorPaint()
     */
    public void setErrorIndicatorPaint(Paint paint) {
        this.errorIndicatorPaint = paint;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[5]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[6]++;
    }
    
    /**
     * Returns the stroke used to draw the error indicators.  If this is 
     * <code>null</code>, the renderer will use the item outline stroke).
     * 
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setErrorIndicatorStroke(Stroke)
     *
     * @since 1.0.8
     */
    public Stroke getErrorIndicatorStroke() {
        return this.errorIndicatorStroke;
    }
    
    /**
     * Sets the stroke used to draw the error indicators, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  If you set
     * this to <code>null</code>, the renderer will use the item outline
     * stroke.
     * 
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getErrorIndicatorStroke()
     * 
     * @since 1.0.8
     */
    public void setErrorIndicatorStroke(Stroke stroke) {
        this.errorIndicatorStroke = stroke;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[7]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[8]++;
    }
    
    /**
     * Draws the bar with its standard deviation line range for a single 
     * (series, category) data item.
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
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;

        // defensive check
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((data instanceof StatisticalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[1]++;
            throw new IllegalArgumentException(
                "Requires StatisticalCategoryDataset.");

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[2]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[10]++;
        StatisticalCategoryDataset statData = (StatisticalCategoryDataset) data;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[11]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[3]++;
            drawHorizontalItem(g2, state, dataArea, plot, domainAxis, 
                    rangeAxis, statData, row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[13]++;

        }
        else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[4]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[14]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[5]++;
            drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, 
                    statData, row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[15]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[6]++;}
}
    }
                
    /**
     * Draws an item for a plot with a horizontal orientation.
     * 
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data area.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    protected void drawHorizontalItem(Graphics2D g2,
                                      CategoryItemRendererState state,
                                      Rectangle2D dataArea,
                                      CategoryPlot plot,
                                      CategoryAxis domainAxis,
                                      ValueAxis rangeAxis,
                                      StatisticalCategoryDataset dataset,
                                      int row,
                                      int column) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[16]++;
                                     
        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[17]++;
        
        // BAR Y
        double rectY = domainAxis.getCategoryStart(column, getColumnCount(), 
                dataArea, xAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[18]++;

        int seriesCount = getRowCount();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[19]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[7]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[21]++;
            double seriesGap = dataArea.getHeight() * getItemMargin()
                               / (categoryCount * (seriesCount - 1));
            rectY = rectY + row * (state.getBarWidth() + seriesGap);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[22]++;

        }
        else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[8]++;
            rectY = rectY + row * state.getBarWidth();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[23]++;
        }
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[24]++;

        // BAR X
        Number meanValue = dataset.getMeanValue(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((meanValue == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[9]++;
            return;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[10]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[26]++;
        double value = meanValue.doubleValue();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[27]++;
        double base = 0.0;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[28]++;
        double lclip = getLowerClip();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[29]++;
        double uclip = getUpperClip();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((uclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[11]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;  // cases 1, 2, 3 and 4
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[13]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[14]++;}
            base = uclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[32]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[15]++;
                value = lclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[34]++;

            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[16]++;}

        }
        else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[12]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[35]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((lclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[17]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[36]++;
int CodeCoverConditionCoverageHelper_C10; // cases 5, 6, 7 and 8
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[19]++;
                value = uclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[37]++;

            }
            else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[20]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[38]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[21]++;
                    value = lclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[39]++;

                } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[22]++;}
            }

        }
        else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[18]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[40]++;
int CodeCoverConditionCoverageHelper_C12; // cases 9, 10, 11 and 12
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[23]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[24]++;}
            base = getLowerClip();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[41]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[25]++;
               value = uclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[43]++;

            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[26]++;}
        }
}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[44]++;

        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[45]++;
        double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[46]++;
        double transY2 = rangeAxis.valueToJava2D(value, dataArea, 
                yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[47]++;
        double rectX = Math.min(transY2, transY1);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[48]++;

        double rectHeight = state.getBarWidth();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[49]++;
        double rectWidth = Math.abs(transY2 - transY1);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[50]++;

        Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, 
                rectHeight);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[51]++;
        Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[52]++;
        GradientPaintTransformer t = getGradientPaintTransformer();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[27]++;
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[54]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[28]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[55]++;
        g2.fill(bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[56]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
        
        // draw the outline...
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[29]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[58]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[59]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[31]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[61]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[62]++;
                g2.draw(bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[63]++;

            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[30]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[64]++;

        // standard deviation lines
        Number n = dataset.getStdDevValue(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[65]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[33]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[66]++;
            double valueDelta = n.doubleValue();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[67]++;
            double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() 
                    + valueDelta, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[68]++;
            double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() 
                    - valueDelta, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;

            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.errorIndicatorPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[35]++;
                g2.setPaint(this.errorIndicatorPaint);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[70]++;
  
            }
            else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[36]++;
                g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[71]++;   
            }
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[72]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.errorIndicatorStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[37]++;
                g2.setStroke(this.errorIndicatorStroke);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[73]++;

            }
            else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[38]++;
                g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[74]++;
            }
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[75]++;
            Line2D line = null;
            line = new Line2D.Double(lowVal, rectY + rectHeight / 2.0d, 
                                     highVal, rectY + rectHeight / 2.0d);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[76]++;
            g2.draw(line);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[77]++;
            line = new Line2D.Double(highVal, rectY + rectHeight * 0.25, 
                                     highVal, rectY + rectHeight * 0.75);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[78]++;
            g2.draw(line);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[79]++;
            line = new Line2D.Double(lowVal, rectY + rectHeight * 0.25, 
                                     lowVal, rectY + rectHeight * 0.75);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[80]++;
            g2.draw(line);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[81]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[34]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[82]++;
        
        CategoryItemLabelGenerator generator = getItemLabelGenerator(row, 
                column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[83]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[39]++;
            drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                    (value < 0.0));
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[84]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[40]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[85]++;        

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[86]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[41]++;
            addItemEntity(entities, dataset, row, column, bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[87]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[42]++;}

    }

    /**
     * Draws an item for a plot with a vertical orientation.
     * 
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data area.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    protected void drawVerticalItem(Graphics2D g2,
                                    CategoryItemRendererState state,
                                    Rectangle2D dataArea,
                                    CategoryPlot plot,
                                    CategoryAxis domainAxis,
                                    ValueAxis rangeAxis,
                                    StatisticalCategoryDataset dataset,
                                    int row,
                                    int column) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[88]++;
                                     
        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[89]++;
        
        // BAR X
        double rectX = domainAxis.getCategoryStart(column, getColumnCount(), 
                dataArea, xAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[90]++;

        int seriesCount = getRowCount();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[91]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[92]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[43]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[93]++;
            double seriesGap = dataArea.getWidth() * getItemMargin()
                               / (categoryCount * (seriesCount - 1));
            rectX = rectX + row * (state.getBarWidth() + seriesGap);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[94]++;

        }
        else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[44]++;
            rectX = rectX + row * state.getBarWidth();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[95]++;
        }
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[96]++;

        // BAR Y
        Number meanValue = dataset.getMeanValue(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[97]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((meanValue == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[45]++;
            return;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[46]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[98]++;

        double value = meanValue.doubleValue();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[99]++;
        double base = 0.0;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[100]++;
        double lclip = getLowerClip();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[101]++;
        double uclip = getUpperClip();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[102]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((uclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[47]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[103]++;
int CodeCoverConditionCoverageHelper_C25;  // cases 1, 2, 3 and 4
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[49]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[50]++;}
            base = uclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[104]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[105]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[51]++;
                value = lclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[106]++;

            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[52]++;}

        }
        else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[48]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[107]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((lclip <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[53]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[108]++;
int CodeCoverConditionCoverageHelper_C28; // cases 5, 6, 7 and 8
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[55]++;
                value = uclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[109]++;

            }
            else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[56]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[110]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[57]++;
                    value = lclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[111]++;

                } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[58]++;}
            }

        }
        else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[54]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[112]++;
int CodeCoverConditionCoverageHelper_C30; // cases 9, 10, 11 and 12
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((value <= lclip) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[59]++;
                return;
 // bar is not visible
            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[60]++;}
            base = getLowerClip();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[113]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[114]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((value >= uclip) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[61]++;
               value = uclip;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[115]++;

            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[62]++;}
        }
}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[116]++;

        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[117]++;
        double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[118]++;
        double transY2 = rangeAxis.valueToJava2D(value, dataArea, 
                yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[119]++;
        double rectY = Math.min(transY2, transY1);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[120]++;

        double rectWidth = state.getBarWidth();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[121]++;
        double rectHeight = Math.abs(transY2 - transY1);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[122]++;

        Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, 
                rectHeight);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[123]++;
        Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[124]++;
        GradientPaintTransformer t = getGradientPaintTransformer();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[125]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[63]++;
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[126]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[64]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[127]++;
        g2.fill(bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[128]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[129]++;
int CodeCoverConditionCoverageHelper_C33;
        // draw the outline...
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[65]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[130]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[131]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[132]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[67]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[133]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[134]++;
                g2.draw(bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[135]++;

            } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[68]++;}

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[66]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[136]++;

        // standard deviation lines
        Number n = dataset.getStdDevValue(row, column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[137]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[69]++;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[138]++;
            double valueDelta = n.doubleValue();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[139]++;
            double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() 
                    + valueDelta, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[140]++;
            double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() 
                    - valueDelta, dataArea, yAxisLocation);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[141]++;
int CodeCoverConditionCoverageHelper_C36;

            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.errorIndicatorPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[71]++;
                g2.setPaint(this.errorIndicatorPaint);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[142]++;
  
            }
            else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[72]++;
                g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[143]++;   
            }
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[144]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.errorIndicatorStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[73]++;
                g2.setStroke(this.errorIndicatorStroke);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[145]++;

            }
            else {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[74]++;
                g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[146]++;
            }
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[147]++;
            
            Line2D line = null;
            line = new Line2D.Double(rectX + rectWidth / 2.0d, lowVal,
                                     rectX + rectWidth / 2.0d, highVal);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[148]++;
            g2.draw(line);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[149]++;
            line = new Line2D.Double(rectX + rectWidth / 2.0d - 5.0d, highVal,
                                     rectX + rectWidth / 2.0d + 5.0d, highVal);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[150]++;
            g2.draw(line);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[151]++;
            line = new Line2D.Double(rectX + rectWidth / 2.0d - 5.0d, lowVal,
                                     rectX + rectWidth / 2.0d + 5.0d, lowVal);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[152]++;
            g2.draw(line);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[153]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[70]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[154]++;
        
        CategoryItemLabelGenerator generator = getItemLabelGenerator(row, 
                column);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[155]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[75]++;
            drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                    (value < 0.0));
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[156]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[76]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[157]++;        

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[158]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[77]++;
            addItemEntity(entities, dataset, row, column, bar);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[159]++;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[78]++;}
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[160]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[79]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[80]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[161]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((obj instanceof StatisticalBarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[81]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[82]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[162]++;
        StatisticalBarRenderer that = (StatisticalBarRenderer) obj;
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[163]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.errorIndicatorPaint, 
                that.errorIndicatorPaint)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[84]++;}
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[164]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.errorIndicatorStroke, 
                that.errorIndicatorStroke)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.branches[86]++;}
        return super.equals(obj);
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
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[165]++;
        SerialUtilities.writePaint(this.errorIndicatorPaint, stream);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[166]++;
        SerialUtilities.writeStroke(this.errorIndicatorStroke, stream);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[167]++;
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
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[168]++;
        this.errorIndicatorPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[169]++;
        this.errorIndicatorStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9.statements[170]++;
    }

}

class CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9 ());
  }
    public static long[] statements = new long[171];
    public static long[] branches = new long[87];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[44];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.StatisticalBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 43; i++) {
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

  public CodeCoverCoverageCounter$5c4l64m6fpb63s0f5av96r863roxr3j03mbllh4km9 () {
    super("org.jfree.chart.renderer.category.StatisticalBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 170; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 86; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.StatisticalBarRenderer.java");
      for (int i = 1; i <= 170; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 86; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 43; i++) {
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

