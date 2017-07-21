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
 * GanttRenderer.java
 * ------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 16-Sep-2003 : Version 1 (DG);
 * 23-Sep-2003 : Fixed Checkstyle issues (DG);
 * 21-Oct-2003 : Bar width moved into CategoryItemRendererState (DG);
 * 03-Feb-2004 : Added get/set methods for attributes (DG);
 * 12-Aug-2004 : Fixed rendering problem with maxBarWidth attribute (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 01-Dec-2005 : Fix for bug 1369954, drawBarOutline flag ignored (DG);
 * ------------- JFREECHART 1.0.x --------------------------------------------
 * 17-Jan-2006 : Set includeBaseInRange flag to false (DG);
 * 20-Mar-2007 : Implemented equals() and fixed serialization (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.gantt.GanttCategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;

/**
 * A renderer for simple Gantt charts.
 */
public class GanttRenderer extends IntervalBarRenderer
                           implements Serializable {
  static {
    CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -4010349116350119512L;
  static {
    CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[1]++;
  }
    
    /** The paint for displaying the percentage complete. */
    private transient Paint completePaint;
    
    /** The paint for displaying the incomplete part of a task. */
    private transient Paint incompletePaint;
    
    /** 
     * Controls the starting edge of the progress indicator (expressed as a 
     * percentage of the overall bar width).
     */
    private double startPercent;
    
    /**
     * Controls the ending edge of the progress indicator (expressed as a 
     * percentage of the overall bar width). 
     */
    private double endPercent;
    
    /**
     * Creates a new renderer.
     */
    public GanttRenderer() {
        super();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[2]++;
        setIncludeBaseInRange(false);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[3]++;
        this.completePaint = Color.green;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[4]++;
        this.incompletePaint = Color.red;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[5]++;
        this.startPercent = 0.35;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[6]++;
        this.endPercent = 0.65;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[7]++;
    }
    
    /**
     * Returns the paint used to show the percentage complete.
     * 
     * @return The paint (never <code>null</code>.
     * 
     * @see #setCompletePaint(Paint)
     */
    public Paint getCompletePaint() {
        return this.completePaint;
    }
    
    /**
     * Sets the paint used to show the percentage complete and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getCompletePaint()
     */
    public void setCompletePaint(Paint paint) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[2]++;}
        this.completePaint = paint;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[9]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[10]++;
    }
    
    /**
     * Returns the paint used to show the percentage incomplete.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setCompletePaint(Paint)
     */
    public Paint getIncompletePaint() {
        return this.incompletePaint;
    }
    
    /**
     * Sets the paint used to show the percentage incomplete and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getIncompletePaint()
     */
    public void setIncompletePaint(Paint paint) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[4]++;}
        this.incompletePaint = paint;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[12]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[13]++;
    }
    
    /**
     * Returns the position of the start of the progress indicator, as a 
     * percentage of the bar width.
     * 
     * @return The start percent.
     * 
     * @see #setStartPercent(double)
     */
    public double getStartPercent() {
        return this.startPercent;
    }
    
    /**
     * Sets the position of the start of the progress indicator, as a 
     * percentage of the bar width, and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     * 
     * @param percent  the percent.
     * 
     * @see #getStartPercent()
     */
    public void setStartPercent(double percent) {
        this.startPercent = percent;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[14]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[15]++;
    }
    
    /**
     * Returns the position of the end of the progress indicator, as a 
     * percentage of the bar width.
     * 
     * @return The end percent.
     * 
     * @see #setEndPercent(double)
     */
    public double getEndPercent() {
        return this.endPercent;
    }
    
    /**
     * Sets the position of the end of the progress indicator, as a percentage 
     * of the bar width, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param percent  the percent.
     * 
     * @see #getEndPercent()
     */
    public void setEndPercent(double percent) {
        this.endPercent = percent;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[16]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[17]++;
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
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;

         if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof GanttCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[5]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[19]++;
             GanttCategoryDataset gcd = (GanttCategoryDataset) dataset;
             drawTasks(g2, state, dataArea, plot, domainAxis, rangeAxis, gcd, 
                     row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[20]++;

         }
         else {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[6]++;  // let the superclass handle it...
             super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, 
                     dataset, row, column, pass);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[21]++;
         }
 
     }
                          
    /**
     * Draws the tasks/subtasks for one item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    protected void drawTasks(Graphics2D g2,
                             CategoryItemRendererState state,
                             Rectangle2D dataArea,
                             CategoryPlot plot,
                             CategoryAxis domainAxis,
                             ValueAxis rangeAxis,
                             GanttCategoryDataset dataset,
                             int row,
                             int column) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[22]++;

        int count = dataset.getSubIntervalCount(row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[7]++;
            drawTask(g2, state, dataArea, plot, domainAxis, rangeAxis, 
                    dataset, row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[24]++;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[8]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[25]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;

        for (int subinterval = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((subinterval < count) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); subinterval++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.loops[1]--;
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.loops[2]--;
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.loops[3]++;
}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[26]++;
            
            RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[27]++;

            // value 0
            Number value0 = dataset.getStartValue(row, column, subinterval);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value0 == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[9]++;
                return;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[10]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[29]++;
            double translatedValue0 = rangeAxis.valueToJava2D(
                    value0.doubleValue(), dataArea, rangeAxisLocation);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[30]++;
    
            // value 1
            Number value1 = dataset.getEndValue(row, column, subinterval);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value1 == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[11]++;
                return;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[12]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[32]++;
            double translatedValue1 = rangeAxis.valueToJava2D(
                    value1.doubleValue(), dataArea, rangeAxisLocation);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
    
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((translatedValue1 < translatedValue0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[13]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[34]++;
                double temp = translatedValue1;
                translatedValue1 = translatedValue0;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[35]++;
                translatedValue0 = temp;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[36]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[14]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[37]++;
    
            double rectStart = calculateBarW0(plot, plot.getOrientation(), 
                    dataArea, domainAxis, state, row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[38]++;
            double rectLength = Math.abs(translatedValue1 - translatedValue0);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[39]++;
            double rectBreadth = state.getBarWidth();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[40]++;
    
            // DRAW THE BARS...
            Rectangle2D bar = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[41]++;
int CodeCoverConditionCoverageHelper_C9;
            
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[15]++;
                bar = new Rectangle2D.Double(translatedValue0, rectStart, 
                        rectLength, rectBreadth);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[42]++;

            }
            else {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[16]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[43]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[17]++;
                bar = new Rectangle2D.Double(rectStart, translatedValue0, 
                        rectBreadth, rectLength);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[44]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[18]++;}
}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[45]++;
    
            Rectangle2D completeBar = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[46]++;
            Rectangle2D incompleteBar = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[47]++;
            Number percent = dataset.getPercentComplete(row, column, 
                    subinterval);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[48]++;
            double start = getStartPercent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[49]++;
            double end = getEndPercent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((percent != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[19]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[51]++;
                double p = percent.doubleValue();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[52]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[21]++;
                    completeBar = new Rectangle2D.Double(translatedValue0, 
                            rectStart + start * rectBreadth, rectLength * p, 
                            rectBreadth * (end - start));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[53]++;
                    incompleteBar = new Rectangle2D.Double(translatedValue0 
                            + rectLength * p, rectStart + start * rectBreadth, 
                            rectLength * (1 - p), rectBreadth * (end - start));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[54]++;

                }
                else {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[22]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[55]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[23]++;
                    completeBar = new Rectangle2D.Double(rectStart + start 
                            * rectBreadth, translatedValue0 + rectLength 
                            * (1 - p), rectBreadth * (end - start), 
                            rectLength * p);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[56]++;
                    incompleteBar = new Rectangle2D.Double(rectStart + start 
                            * rectBreadth, translatedValue0, rectBreadth 
                            * (end - start), rectLength * (1 - p));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[57]++;

                } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[24]++;}
}

                
            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[20]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[58]++;

            Paint seriesPaint = getItemPaint(row, column);
            g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[59]++;
            g2.fill(bar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[60]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[61]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((completeBar != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[25]++;
                g2.setPaint(getCompletePaint());
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[62]++;
                g2.fill(completeBar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[63]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[26]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[64]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((incompleteBar != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[27]++;
                g2.setPaint(getIncompletePaint());
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[65]++;
                g2.fill(incompleteBar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[66]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[28]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[29]++;
                g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[68]++;
                g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[69]++;
                g2.draw(bar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[70]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[30]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[71]++;
int CodeCoverConditionCoverageHelper_C17;
    
            // collect entity and tool tip information...
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[31]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[72]++;
                EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[33]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[74]++;
                    String tip = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[75]++;
int CodeCoverConditionCoverageHelper_C19;
                    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getToolTipGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[35]++;
                        tip = getToolTipGenerator(row, column).generateToolTip(
                                dataset, row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[76]++;

                    } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[36]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[77]++;
                    String url = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[78]++;
int CodeCoverConditionCoverageHelper_C20;
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[37]++;
                        url = getItemURLGenerator(row, column).generateURL(
                                dataset, row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[79]++;

                    } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[38]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[80]++;
                    CategoryItemEntity entity = new CategoryItemEntity(
                            bar, tip, url, dataset, dataset.getRowKey(row), 
                            dataset.getColumnKey(column));
                    entities.add(entity);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[81]++;

                } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[34]++;}

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[32]++;}
        }
    }
    
    /**
     * Draws a single task.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    protected void drawTask(Graphics2D g2,
                            CategoryItemRendererState state,
                            Rectangle2D dataArea,
                            CategoryPlot plot,
                            CategoryAxis domainAxis,
                            ValueAxis rangeAxis,
                            GanttCategoryDataset dataset,
                            int row,
                            int column) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[82]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[83]++;

        RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[84]++;
        
        // Y0
        Number value0 = dataset.getEndValue(row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[85]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((value0 == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[39]++;
            return;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[40]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[86]++;
        double java2dValue0 = rangeAxis.valueToJava2D(value0.doubleValue(), 
                dataArea, rangeAxisLocation);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[87]++;

        // Y1
        Number value1 = dataset.getStartValue(row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[88]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((value1 == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[41]++;
            return;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[42]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[89]++;
        double java2dValue1 = rangeAxis.valueToJava2D(value1.doubleValue(), 
                dataArea, rangeAxisLocation);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[90]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((java2dValue1 < java2dValue0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[43]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[91]++;
            double temp = java2dValue1;
            java2dValue1 = java2dValue0;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[92]++;
            java2dValue0 = temp;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[93]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[94]++;
            Number tempNum = value1;
            value1 = value0;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[95]++;
            value0 = tempNum;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[96]++;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[44]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[97]++;

        double rectStart = calculateBarW0(plot, orientation, dataArea, 
                domainAxis, state, row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[98]++;
        double rectBreadth = state.getBarWidth();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[99]++;
        double rectLength = Math.abs(java2dValue1 - java2dValue0);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[100]++;
        
        Rectangle2D bar = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[101]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[45]++;
            bar = new Rectangle2D.Double(java2dValue0, rectStart, rectLength, 
                    rectBreadth);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[102]++;

        }
        else {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[46]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[103]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[47]++;
            bar = new Rectangle2D.Double(rectStart, java2dValue1, rectBreadth, 
                    rectLength);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[104]++;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[48]++;}
}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[105]++;

        Rectangle2D completeBar = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[106]++;
        Rectangle2D incompleteBar = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[107]++;
        Number percent = dataset.getPercentComplete(row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[108]++;
        double start = getStartPercent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[109]++;
        double end = getEndPercent();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[110]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((percent != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[49]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[111]++;
            double p = percent.doubleValue();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[112]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[51]++;
                completeBar = new Rectangle2D.Double(java2dValue0, 
                        rectStart + start * rectBreadth, rectLength * p, 
                        rectBreadth * (end - start));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[113]++;
                incompleteBar = new Rectangle2D.Double(java2dValue0 
                        + rectLength * p, rectStart + start * rectBreadth, 
                        rectLength * (1 - p), rectBreadth * (end - start));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[114]++;

            }
            else {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[52]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[115]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((plot.getOrientation() == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[53]++;
                completeBar = new Rectangle2D.Double(rectStart + start 
                        * rectBreadth, java2dValue1 + rectLength * (1 - p), 
                        rectBreadth * (end - start), rectLength * p);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[116]++;
                incompleteBar = new Rectangle2D.Double(rectStart + start 
                        * rectBreadth, java2dValue1, rectBreadth * (end 
                        - start), rectLength * (1 - p));
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[117]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[54]++;}
}

                
        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[50]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[118]++;

        Paint seriesPaint = getItemPaint(row, column);
        g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[119]++;
        g2.fill(bar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[120]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[121]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((completeBar != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[55]++;
            g2.setPaint(getCompletePaint());
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[122]++;
            g2.fill(completeBar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[123]++;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[56]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[124]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((incompleteBar != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[57]++;
            g2.setPaint(getIncompletePaint());
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[125]++;
            g2.fill(incompleteBar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[126]++;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[58]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[127]++;
int CodeCoverConditionCoverageHelper_C31;
        
        // draw the outline...
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[59]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[128]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[129]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[130]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[61]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[131]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[132]++;
                g2.draw(bar);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[133]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[62]++;}

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[60]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[134]++;
        
        CategoryItemLabelGenerator generator = getItemLabelGenerator(row, 
                column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[135]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[63]++;
            drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                    false);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[136]++;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[64]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[137]++;
int CodeCoverConditionCoverageHelper_C34;        

        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[65]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[138]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[139]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[67]++;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[140]++;
                String tip = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[141]++;
                CategoryToolTipGenerator tipster = getToolTipGenerator(row, 
                        column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[142]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[69]++;
                    tip = tipster.generateToolTip(dataset, row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[143]++;

                } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[70]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[144]++;
                String url = null;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[145]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[71]++;
                    url = getItemURLGenerator(row, column).generateURL(
                            dataset, row, column);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[146]++;

                } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[72]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[147]++;
                CategoryItemEntity entity = new CategoryItemEntity(bar, tip, 
                        url, dataset, dataset.getRowKey(row), 
                        dataset.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[148]++;

            } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[68]++;}

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[66]++;}

    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[149]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[73]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[74]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[150]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((obj instanceof GanttRenderer) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[76]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[151]++;
        GanttRenderer that = (GanttRenderer) obj;
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[152]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.completePaint, that.completePaint)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[78]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[153]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.incompletePaint, that.incompletePaint)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[80]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[154]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.startPercent != that.startPercent) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[82]++;}
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[155]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.endPercent != that.endPercent) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.branches[84]++;}
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
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[156]++;
        SerialUtilities.writePaint(this.completePaint, stream);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[157]++;
        SerialUtilities.writePaint(this.incompletePaint, stream);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[158]++;
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
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[159]++;
        this.completePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[160]++;
        this.incompletePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9.statements[161]++;
    }
    
}

class CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9 ());
  }
    public static long[] statements = new long[162];
    public static long[] branches = new long[85];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[44];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.GanttRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$5xp0tw48f4c9gttq3jdd0phxtuu9 () {
    super("org.jfree.chart.renderer.category.GanttRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 161; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 84; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.GanttRenderer.java");
      for (int i = 1; i <= 161; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 84; i++) {
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

