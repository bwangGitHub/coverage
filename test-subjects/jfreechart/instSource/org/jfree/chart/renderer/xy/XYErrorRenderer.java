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
 * XYErrorRenderer.java
 * --------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 25-Oct-2006 : Version 1 (DG);
 * 23-Mar-2007 : Check item visibility before drawing error bars - see bug
 *               1686178 (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;

/**
 * A line and shape renderer that can also display x and/or y-error values.  
 * This renderer expects an {@link IntervalXYDataset}, otherwise it reverts
 * to the behaviour of the super class.
 * 
 * @since 1.0.3
 */
public class XYErrorRenderer extends XYLineAndShapeRenderer {
  static {
    CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = 5162283570955172424L;
  static {
    CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[1]++;
  }
    
    /** A flag that controls whether or not the x-error bars are drawn. */
    private boolean drawXError;
    
    /** A flag that controls whether or not the y-error bars are drawn. */
    private boolean drawYError;
    
    /** The length of the cap at the end of the error bars. */
    private double capLength;
    
    /** 
     * The paint used to draw the error bars (if <code>null</code> we use the
     * series paint).
     */
    private transient Paint errorPaint;
    
    /**
     * Creates a new <code>XYErrorRenderer</code> instance.
     */
    public XYErrorRenderer() {
        super(false, true);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[2]++;
        this.drawXError = true;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[3]++;
        this.drawYError = true;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[4]++;
        this.errorPaint = null;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[5]++;
        this.capLength = 4.0;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[6]++;
    }
    
    /**
     * Returns the flag that controls whether or not the renderer draws error
     * bars for the x-values.
     * 
     * @return A boolean.
     * 
     * @see #setDrawXError(boolean)
     */
    public boolean getDrawXError() {
        return this.drawXError;
    }
    
    /**
     * Sets the flag that controls whether or not the renderer draws error
     * bars for the x-values and, if the flag changes, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param draw  the flag value.
     * 
     * @see #getDrawXError()
     */
    public void setDrawXError(boolean draw) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.drawXError != draw) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[1]++;
            this.drawXError = draw;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[8]++;
            fireChangeEvent();
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[9]++;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[2]++;}
    }
    
    /**
     * Returns the flag that controls whether or not the renderer draws error
     * bars for the y-values.
     * 
     * @return A boolean.
     * 
     * @see #setDrawYError(boolean)
     */
    public boolean getDrawYError() {
        return this.drawYError;
    }
    
    /**
     * Sets the flag that controls whether or not the renderer draws error
     * bars for the y-values and, if the flag changes, sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param draw  the flag value.
     * 
     * @see #getDrawYError()
     */
    public void setDrawYError(boolean draw) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.drawYError != draw) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[3]++;
            this.drawYError = draw;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[11]++;
            fireChangeEvent();
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[12]++;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[4]++;}
    }
    
    /**
     * Returns the length (in Java2D units) of the cap at the end of the error 
     * bars.
     * 
     * @return The cap length.
     * 
     * @see #setCapLength(double)
     */
    public double getCapLength() {
        return this.capLength;
    }
    
    /**
     * Sets the length of the cap at the end of the error bars, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param length  the length (in Java2D units).
     * 
     * @see #getCapLength()
     */
    public void setCapLength(double length) {
        this.capLength = length;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[13]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[14]++;
    }
    
    /**
     * Returns the paint used to draw the error bars.  If this is 
     * <code>null</code> (the default), the item paint is used instead.
     * 
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setErrorPaint(Paint)
     */
    public Paint getErrorPaint() {
        return this.errorPaint;
    }
    
    /**
     * Sets the paint used to draw the error bars and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getErrorPaint()
     */
    public void setErrorPaint(Paint paint) {
        this.errorPaint = paint;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[15]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[16]++;
    }
    
    /**
     * Returns the range required by this renderer to display all the domain
     * values in the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range, or <code>null</code> if the dataset is 
     *     <code>null</code>.
     */
    public Range findDomainBounds(XYDataset dataset) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[5]++;
            return DatasetUtilities.findDomainBounds(dataset, true);

        }
        else {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[6]++;
            return null;
        }
    }

    /**
     * Returns the range required by this renderer to display all the range
     * values in the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range, or <code>null</code> if the dataset is 
     *     <code>null</code>.
     */
    public Range findRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[7]++;
            return DatasetUtilities.findRangeBounds(dataset, true);

        }
        else {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[8]++;
            return null;
        }
    }

    /**
     * Draws the visual representation for one data item.
     * 
     * @param g2  the graphics output target.
     * @param state  the renderer state.
     * @param dataArea  the data area.
     * @param info  the plot rendering info.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param item  the item index.
     * @param crosshairState  the crosshair state.
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2, XYItemRendererState state, 
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, 
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, 
            int series, int item, CrosshairState crosshairState, int pass) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[9]++;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[20]++;
            IntervalXYDataset ixyd = (IntervalXYDataset) dataset;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[21]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.drawXError) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[11]++;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[23]++;
                // draw the error bar for the x-interval
                double x0 = ixyd.getStartXValue(series, item);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[24]++;
                double x1 = ixyd.getEndXValue(series, item);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[25]++;
                double y = ixyd.getYValue(series, item);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[26]++;
                RectangleEdge edge = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[27]++;
                double xx0 = domainAxis.valueToJava2D(x0, dataArea, edge);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[28]++;
                double xx1 = domainAxis.valueToJava2D(x1, dataArea, edge);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[29]++;
                double yy = rangeAxis.valueToJava2D(y, dataArea, 
                        plot.getRangeAxisEdge());
                Line2D line;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[30]++;
                Line2D cap1 = null;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[31]++;
                Line2D cap2 = null;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[32]++;
                double adj = this.capLength / 2.0;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[13]++;
                    line = new Line2D.Double(xx0, yy, xx1, yy);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[34]++;
                    cap1 = new Line2D.Double(xx0, yy - adj, xx0, yy + adj);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[35]++;
                    cap2 = new Line2D.Double(xx1, yy - adj, xx1, yy + adj);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[36]++;

                }
                else {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[14]++;  // PlotOrientation.HORIZONTAL
                    line = new Line2D.Double(yy, xx0, yy, xx1);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[37]++;
                    cap1 = new Line2D.Double(yy - adj, xx0, yy + adj, xx0);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[38]++;
                    cap2 = new Line2D.Double(yy - adj, xx1, yy + adj, xx1);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[39]++;
                }
                g2.setStroke(new BasicStroke(1.0f));
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[40]++;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.errorPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[15]++;
                    g2.setPaint(this.errorPaint);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[42]++;
    
                }
                else {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[16]++;
                    g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[43]++;
                }
                g2.draw(line);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[44]++;
                g2.draw(cap1);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[45]++;
                g2.draw(cap2);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[46]++;

            } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[12]++;}
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.drawYError) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[17]++;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[48]++;
                // draw the error bar for the y-interval
                double y0 = ixyd.getStartYValue(series, item);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[49]++;
                double y1 = ixyd.getEndYValue(series, item);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[50]++;
                double x = ixyd.getXValue(series, item);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[51]++;
                RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[52]++;
                double yy0 = rangeAxis.valueToJava2D(y0, dataArea, edge);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[53]++;
                double yy1 = rangeAxis.valueToJava2D(y1, dataArea, edge);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[54]++;
                double xx = domainAxis.valueToJava2D(x, dataArea, 
                        plot.getDomainAxisEdge());
                Line2D line;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[55]++;
                Line2D cap1 = null;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[56]++;
                Line2D cap2 = null;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[57]++;
                double adj = this.capLength / 2.0;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[58]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[19]++;
                    line = new Line2D.Double(xx, yy0, xx, yy1);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[59]++;
                    cap1 = new Line2D.Double(xx - adj, yy0, xx + adj, yy0);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[60]++;
                    cap2 = new Line2D.Double(xx - adj, yy1, xx + adj, yy1);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[61]++;

                }
                else {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[20]++;  // PlotOrientation.HORIZONTAL
                    line = new Line2D.Double(yy0, xx, yy1, xx);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[62]++;
                    cap1 = new Line2D.Double(yy0, xx - adj, yy0, xx + adj);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[63]++;
                    cap2 = new Line2D.Double(yy1, xx - adj, yy1, xx + adj);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[64]++;
                }
                g2.setStroke(new BasicStroke(1.0f));
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[65]++;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[66]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.errorPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[21]++;
                    g2.setPaint(this.errorPaint);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[67]++;
    
                }
                else {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[22]++;
                    g2.setPaint(getItemPaint(series, item));
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[68]++;
                }
                g2.draw(line);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[69]++;                    
                g2.draw(cap1);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[70]++;                    
                g2.draw(cap2);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[71]++;
                    
            } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[10]++;}
        super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, 
                dataset, series, item, crosshairState, pass);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[72]++;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[73]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[24]++;}
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[74]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((obj instanceof XYErrorRenderer) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[26]++;}
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[75]++;
        XYErrorRenderer that = (XYErrorRenderer) obj;
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[76]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.drawXError != that.drawXError) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[28]++;}
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[77]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.drawYError != that.drawYError) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[30]++;}
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[78]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.capLength != that.capLength) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[32]++;}
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[79]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.errorPaint, that.errorPaint)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.branches[34]++;}
        return super.equals(obj);
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
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[80]++;
        this.errorPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[81]++;
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
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[82]++;
        SerialUtilities.writePaint(this.errorPaint, stream);
CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx.statements[83]++;
    }
    
}

class CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx ());
  }
    public static long[] statements = new long[84];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYErrorRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$abivb6utrfa61ll60a9i26el68s4cwx () {
    super("org.jfree.chart.renderer.xy.XYErrorRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 83; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYErrorRenderer.java");
      for (int i = 1; i <= 83; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
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

