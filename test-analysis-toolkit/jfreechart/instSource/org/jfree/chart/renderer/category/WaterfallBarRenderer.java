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
 * -------------------------
 * WaterfallBarRenderer.java
 * -------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Darshan Shah;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 20-Oct-2003 : Version 1, contributed by Darshan Shah (DG);
 * 06-Nov-2003 : Changed order of parameters in constructor, and added support 
 *               for GradientPaint (DG);
 * 10-Feb-2004 : Updated drawItem() method to make cut-and-paste overriding 
 *               easier.  Also fixed a bug that meant the minimum bar length 
 *               was being ignored (DG);
 * 04-Oct-2004 : Reworked equals() method and renamed PaintUtils 
 *               --> PaintUtilities (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds (DG);
 * 23-Feb-2005 : Added argument checking (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 09-Jun-2005 : Use addItemEntity() from superclass (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.Color;
import java.awt.GradientPaint;
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
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that handles the drawing of waterfall bar charts, for use with 
 * the {@link CategoryPlot} class.  Note that the bar colors are defined 
 * using special methods in this class - the inherited methods (for example,
 * {@link AbstractRenderer#setSeriesPaint(int, Paint)}) are ignored.
 */
public class WaterfallBarRenderer extends BarRenderer 
                                  implements Cloneable, PublicCloneable, 
                                             Serializable {
  static {
    CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2482910643727230911L;
  static {
    CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[1]++;
  }
    
    /** The paint used to draw the first bar. */
    private transient Paint firstBarPaint;

    /** The paint used to draw the last bar. */
    private transient Paint lastBarPaint;

    /** The paint used to draw bars having positive values. */
    private transient Paint positiveBarPaint;

    /** The paint used to draw bars having negative values. */
    private transient Paint negativeBarPaint;

    /**
     * Constructs a new renderer with default values for the bar colors.
     */
    public WaterfallBarRenderer() {
        this(new GradientPaint(0.0f, 0.0f, new Color(0x22, 0x22, 0xFF), 
                0.0f, 0.0f, new Color(0x66, 0x66, 0xFF)), 
                new GradientPaint(0.0f, 0.0f, new Color(0x22, 0xFF, 0x22), 
                0.0f, 0.0f, new Color(0x66, 0xFF, 0x66)), 
                new GradientPaint(0.0f, 0.0f, new Color(0xFF, 0x22, 0x22), 
                0.0f, 0.0f, new Color(0xFF, 0x66, 0x66)),
                new GradientPaint(0.0f, 0.0f, new Color(0xFF, 0xFF, 0x22), 
                0.0f, 0.0f, new Color(0xFF, 0xFF, 0x66)));
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[2]++;
    }

    /**
     * Constructs a new waterfall renderer.
     *
     * @param firstBarPaint  the color of the first bar (<code>null</code> not 
     *                       permitted).
     * @param positiveBarPaint  the color for bars with positive values 
     *                          (<code>null</code> not permitted).
     * @param negativeBarPaint  the color for bars with negative values 
     *                          (<code>null</code> not permitted).
     * @param lastBarPaint  the color of the last bar (<code>null</code> not 
     *                      permitted).
     */
    public WaterfallBarRenderer(Paint firstBarPaint, 
                                Paint positiveBarPaint, 
                                Paint negativeBarPaint,
                                Paint lastBarPaint) {
        super();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[3]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((firstBarPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[1]++;
            throw new IllegalArgumentException("Null 'firstBarPaint' argument");

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[2]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((positiveBarPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[3]++;
            throw new IllegalArgumentException(
                    "Null 'positiveBarPaint' argument");
   
        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[4]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((negativeBarPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'negativeBarPaint' argument");
   
        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[6]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lastBarPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[7]++;
            throw new IllegalArgumentException("Null 'lastBarPaint' argument");

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[8]++;}
        this.firstBarPaint = firstBarPaint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[8]++;
        this.lastBarPaint = lastBarPaint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[9]++;
        this.positiveBarPaint = positiveBarPaint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[10]++;
        this.negativeBarPaint = negativeBarPaint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[11]++;
        setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.CENTER_VERTICAL));
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[12]++;
        setMinimumBarLength(1.0);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[13]++;
    }

    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> not permitted).
     * 
     * @return The range (or <code>null</code> if the dataset is empty).
     */
    public Range findRangeBounds(CategoryDataset dataset) {
        return DatasetUtilities.findCumulativeRangeBounds(dataset);   
    }

    /**
     * Returns the paint used to draw the first bar.
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getFirstBarPaint() {
        return this.firstBarPaint;
    }
    
    /**
     * Sets the paint that will be used to draw the first bar and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setFirstBarPaint(Paint paint) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[9]++;
            throw new IllegalArgumentException("Null 'paint' argument");
   
        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[10]++;}
        this.firstBarPaint = paint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[15]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[16]++;
    }

    /**
     * Returns the paint used to draw the last bar.
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getLastBarPaint() {
        return this.lastBarPaint;
    }
    
    /**
     * Sets the paint that will be used to draw the last bar and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setLastBarPaint(Paint paint) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[11]++;
            throw new IllegalArgumentException("Null 'paint' argument");
   
        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[12]++;}
        this.lastBarPaint = paint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[18]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[19]++;
    }

    /**
     * Returns the paint used to draw bars with positive values.
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getPositiveBarPaint() {
        return this.positiveBarPaint;
    }
    
    /**
     * Sets the paint that will be used to draw bars having positive values.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setPositiveBarPaint(Paint paint) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[13]++;
            throw new IllegalArgumentException("Null 'paint' argument");
   
        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[14]++;}
        this.positiveBarPaint = paint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[21]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[22]++;
    }

    /**
     * Returns the paint used to draw bars with negative values.
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getNegativeBarPaint() {
        return this.negativeBarPaint;
    }
    
    /**
     * Sets the paint that will be used to draw bars having negative values,
     * and sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setNegativeBarPaint(Paint paint) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[15]++;
            throw new IllegalArgumentException("Null 'paint' argument");
   
        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[16]++;}
        this.negativeBarPaint = paint;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[24]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[25]++;
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
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[26]++;

        double previous = state.getSeriesRunningTotal();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((column == dataset.getColumnCount() - 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[17]++;
            previous = 0.0;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[28]++;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[18]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[29]++;
        double current = 0.0;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[30]++;
        Number n = dataset.getValue(row, column);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[19]++;
            current = previous + n.doubleValue();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[32]++;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[20]++;}
        state.setSeriesRunningTotal(current);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[33]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[34]++;
        
        int seriesCount = getRowCount();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[35]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[36]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[37]++;
        
        double rectX = 0.0;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[38]++;
        double rectY = 0.0;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[39]++;

        RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[40]++;
        RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[41]++;
        
        // Y0
        double j2dy0 = rangeAxis.valueToJava2D(previous, dataArea, 
                rangeAxisLocation);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[42]++;

        // Y1
        double j2dy1 = rangeAxis.valueToJava2D(current, dataArea, 
                rangeAxisLocation);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[43]++;

        double valDiff = current - previous;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((j2dy1 < j2dy0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[21]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[45]++;
            double temp = j2dy1;
            j2dy1 = j2dy0;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[46]++;
            j2dy0 = temp;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[47]++;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[22]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[48]++;

        // BAR WIDTH
        double rectWidth = state.getBarWidth();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[49]++;

        // BAR HEIGHT
        double rectHeight = Math.max(getMinimumBarLength(), 
                Math.abs(j2dy1 - j2dy0));
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[23]++;
            // BAR Y
            rectY = domainAxis.getCategoryStart(column, getColumnCount(), 
                    dataArea, domainAxisLocation);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[51]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[25]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[53]++;
                double seriesGap = dataArea.getHeight() * getItemMargin()
                                   / (categoryCount * (seriesCount - 1));
                rectY = rectY + row * (state.getBarWidth() + seriesGap);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[54]++;

            }
            else {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[26]++;
                rectY = rectY + row * state.getBarWidth();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[55]++;
            }
             
            rectX = j2dy0;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[56]++;
            rectHeight = state.getBarWidth();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[57]++;
            rectWidth = Math.max(getMinimumBarLength(), 
                    Math.abs(j2dy1 - j2dy0));
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[58]++;


        }
        else {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[24]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[59]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[27]++;
            // BAR X
            rectX = domainAxis.getCategoryStart(column, getColumnCount(), 
                    dataArea, domainAxisLocation);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[60]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;

            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[29]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[62]++;
                double seriesGap = dataArea.getWidth() * getItemMargin()
                                   / (categoryCount * (seriesCount - 1));
                rectX = rectX + row * (state.getBarWidth() + seriesGap);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[63]++;

            }
            else {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[30]++;
                rectX = rectX + row * state.getBarWidth();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[64]++;
            }

            rectY = j2dy0;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[65]++;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[28]++;}
}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[66]++;
        Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, 
                rectHeight);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[67]++;
        Paint seriesPaint = getFirstBarPaint();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[68]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[31]++;
            seriesPaint = getFirstBarPaint();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[69]++;

        }
        else {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[32]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[70]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((column == categoryCount - 1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[33]++;
            seriesPaint = getLastBarPaint();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[71]++;
    
        } 
        else {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[34]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((valDiff < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[35]++;
                seriesPaint = getNegativeBarPaint();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[73]++;

            } 
            else {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[36]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[74]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((valDiff > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[37]++;
                seriesPaint = getPositiveBarPaint();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[75]++;

            } 
            else {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[38]++;
                seriesPaint = getLastBarPaint();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[76]++;
            }
}
        }
}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[77]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((getGradientPaintTransformer() != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((seriesPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[39]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[78]++;
            GradientPaint gp = (GradientPaint) seriesPaint;
            seriesPaint = getGradientPaintTransformer().transform(gp, bar);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[79]++;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[40]++;}
        g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[80]++;
        g2.fill(bar);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[81]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[82]++;
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
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[41]++;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[83]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[84]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[85]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[43]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[86]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[87]++;
                g2.draw(bar);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[88]++;

            } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[42]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[89]++;
        
        CategoryItemLabelGenerator generator 
            = getItemLabelGenerator(row, column);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[90]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[45]++;
            drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                    (valDiff < 0.0));
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[91]++;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[46]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[92]++;        

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[93]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[47]++;
            addItemEntity(entities, dataset, row, column, bar);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[94]++;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[48]++;}

    }
    
    /**
     * Tests an object for equality with this instance.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[95]++;
int CodeCoverConditionCoverageHelper_C25;
        
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[49]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[50]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[96]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[52]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[97]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj instanceof WaterfallBarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[54]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[98]++;
        WaterfallBarRenderer that = (WaterfallBarRenderer) obj;
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[99]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.firstBarPaint, that.firstBarPaint)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[56]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[100]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.lastBarPaint, that.lastBarPaint)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[58]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[101]++;
int CodeCoverConditionCoverageHelper_C30;             
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.positiveBarPaint, 
                that.positiveBarPaint)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[60]++;}
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[102]++;
int CodeCoverConditionCoverageHelper_C31;             
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.negativeBarPaint, 
                that.negativeBarPaint)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.branches[62]++;}             
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
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[103]++;
        SerialUtilities.writePaint(this.firstBarPaint, stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[104]++;
        SerialUtilities.writePaint(this.lastBarPaint, stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[105]++;
        SerialUtilities.writePaint(this.positiveBarPaint, stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[106]++;
        SerialUtilities.writePaint(this.negativeBarPaint, stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[107]++;
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
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[108]++;
        this.firstBarPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[109]++;
        this.lastBarPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[110]++;
        this.positiveBarPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[111]++;
        this.negativeBarPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp.statements[112]++;
    }

}

class CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp ());
  }
    public static long[] statements = new long[113];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.WaterfallBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
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

  public CodeCoverCoverageCounter$3z7nw0cpg3lbnk03b439htxympz2ewzv7pnrsyp () {
    super("org.jfree.chart.renderer.category.WaterfallBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 112; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.WaterfallBarRenderer.java");
      for (int i = 1; i <= 112; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
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

