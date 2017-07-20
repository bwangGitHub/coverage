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
 * ------------------------------------
 * StatisticalLineAndShapeRenderer.java
 * ------------------------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Mofeed Shahin;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 01-Feb-2005 : Version 1, contributed by Mofeed Shahin (DG);
 * 16-Jun-2005 : Added errorIndicatorPaint to be consistent with
 *               StatisticalBarRenderer (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 11-Apr-2006 : Fixed bug 1468794, error bars drawn incorrectly when rendering
 *               plots with horizontal orientation (DG);
 * 25-Sep-2006 : Fixed bug 1562759, constructor ignoring arguments (DG);
 * 01-Jun-2007 : Return early from drawItem() method if item is not
 *               visible (DG);
 * 14-Jun-2007 : If the dataset is not a StatisticalCategoryDataset, revert
 *               to the drawing behaviour of LineAndShapeRenderer (DG);
 * 27-Sep-2007 : Added offset option to match new option in 
 *               LineAndShapeRenderer (DG);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.StatisticalCategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that draws shapes for each data item, and lines between data
 * items.  Each point has a mean value and a standard deviation line. For use
 * with the {@link CategoryPlot} class.
 */
public class StatisticalLineAndShapeRenderer extends LineAndShapeRenderer
        implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3557517173697777579L;
  static {
    CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[1]++;
  }

    /** The paint used to show the error indicator. */
    private transient Paint errorIndicatorPaint;

    /**
     * Constructs a default renderer (draws shapes and lines).
     */
    public StatisticalLineAndShapeRenderer() {
        this(true, true);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[2]++;
    }

    /**
     * Constructs a new renderer.
     *
     * @param linesVisible  draw lines?
     * @param shapesVisible  draw shapes?
     */
    public StatisticalLineAndShapeRenderer(boolean linesVisible,
                                           boolean shapesVisible) {
        super(linesVisible, shapesVisible);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[3]++;
        this.errorIndicatorPaint = null;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[4]++;
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
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[5]++;
        fireChangeEvent();
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[6]++;
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
     * @param dataset  the dataset (a {@link StatisticalCategoryDataset} is
     *                 required).
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass.
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
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getItemVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[1]++;
            return;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[2]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[8]++;

        // nothing is drawn for null...
        Number v = dataset.getValue(row, column);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[3]++;
            return;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[4]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;

        // if the dataset is not a StatisticalCategoryDataset then just revert
        // to the superclass (LineAndShapeRenderer) behaviour...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof StatisticalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[5]++;
            super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis,
                    dataset, row, column, pass);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[11]++;
            return;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[6]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[12]++;

        StatisticalCategoryDataset statData
                = (StatisticalCategoryDataset) dataset;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[13]++;

        Number meanValue = statData.getMeanValue(row, column);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[14]++;

        PlotOrientation orientation = plot.getOrientation();

        // current data point...
        double x1;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getUseSeriesOffset()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[7]++;
            x1 = domainAxis.getCategorySeriesMiddle(dataset.getColumnKey(
                    column), dataset.getRowKey(row), dataset, getItemMargin(), 
                    dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[16]++;
            
        }
        else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[8]++;
            x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                    dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[17]++;
        }
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[18]++;

        double y1 = rangeAxis.valueToJava2D(meanValue.doubleValue(), dataArea,
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[19]++;

        Shape shape = getItemShape(row, column);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[9]++;
            shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[21]++;

        }
        else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[10]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[22]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[11]++;
            shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[23]++;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[12]++;}
}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getItemShapeVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[13]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;

            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getItemShapeFilled(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[15]++;
                g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[26]++;
                g2.fill(shape);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[27]++;

            }
            else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[16]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getUseOutlinePaint()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[17]++;
                    g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[29]++;

                }
                else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[18]++;
                    g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[30]++;
                }
                g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[31]++;
                g2.draw(shape);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[32]++;
            }

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[14]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((getItemLineVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[19]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[34]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((column != 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[21]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[35]++;

                Number previousValue = statData.getValue(row, column - 1);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[36]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((previousValue != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[23]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[37]++;

                    // previous data point...
                    double previous = previousValue.doubleValue();
                    double x0;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[38]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((getUseSeriesOffset()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[25]++;
                        x0 = domainAxis.getCategorySeriesMiddle(
                                dataset.getColumnKey(column - 1), 
                                dataset.getRowKey(row), dataset, 
                                getItemMargin(), dataArea, 
                                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[39]++;

                    }
                    else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[26]++;
                        x0 = domainAxis.getCategoryMiddle(column - 1, 
                                getColumnCount(), dataArea, 
                                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[40]++;
                    }
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[41]++;
                    double y0 = rangeAxis.valueToJava2D(previous, dataArea,
                            plot.getRangeAxisEdge());
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[42]++;

                    Line2D line = null;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[43]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[27]++;
                        line = new Line2D.Double(y0, x0, y1, x1);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[44]++;

                    }
                    else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[28]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[45]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[29]++;
                        line = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[46]++;

                    } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[30]++;}
}
                    g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[47]++;
                    g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[48]++;
                    g2.draw(line);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[49]++;

                } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[24]++;}

            } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[22]++;}

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[20]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[50]++;

        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[51]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[52]++;

        //standard deviation lines
        double valueDelta = statData.getStdDevValue(row, column).doubleValue();

        double highVal, lowVal;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[53]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (((meanValue.doubleValue() + valueDelta)
                > rangeAxis.getRange().getUpperBound()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[31]++;
            highVal = rangeAxis.valueToJava2D(
                    rangeAxis.getRange().getUpperBound(), dataArea,
                    yAxisLocation);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[54]++;

        }
        else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[32]++;
            highVal = rangeAxis.valueToJava2D(meanValue.doubleValue()
                    + valueDelta, dataArea, yAxisLocation);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[55]++;
        }
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[56]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 (((meanValue.doubleValue() + valueDelta)
                < rangeAxis.getRange().getLowerBound()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[33]++;
            lowVal = rangeAxis.valueToJava2D(
                    rangeAxis.getRange().getLowerBound(), dataArea,
                    yAxisLocation);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[57]++;

        }
        else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[34]++;
            lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue()
                    - valueDelta, dataArea, yAxisLocation);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[58]++;
        }
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[59]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.errorIndicatorPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[35]++;
            g2.setPaint(this.errorIndicatorPaint);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[60]++;

        }
        else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[36]++;
            g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[61]++;
        }
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[62]++;
        Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[63]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[37]++;
            line.setLine(lowVal, x1, highVal, x1);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[64]++;
            g2.draw(line);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[65]++;
            line.setLine(lowVal, x1 - 5.0d, lowVal, x1 + 5.0d);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[66]++;
            g2.draw(line);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[67]++;
            line.setLine(highVal, x1 - 5.0d, highVal, x1 + 5.0d);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[68]++;
            g2.draw(line);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[69]++;

        }
        else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[38]++;  // PlotOrientation.VERTICAL
            line.setLine(x1, lowVal, x1, highVal);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[70]++;
            g2.draw(line);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[71]++;
            line.setLine(x1 - 5.0d, highVal, x1 + 5.0d, highVal);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[72]++;
            g2.draw(line);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[73]++;
            line.setLine(x1 - 5.0d, lowVal, x1 + 5.0d, lowVal);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[74]++;
            g2.draw(line);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[75]++;
        }
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[76]++;
int CodeCoverConditionCoverageHelper_C20;

        // draw the item label if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[39]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[77]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[41]++;
                drawItemLabel(g2, orientation, dataset, row, column,
                        y1, x1, (meanValue.doubleValue() < 0.0));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[78]++;

            }
            else {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[42]++;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[79]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[43]++;
                drawItemLabel(g2, orientation, dataset, row, column,
                        x1, y1, (meanValue.doubleValue() < 0.0));
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[80]++;

            } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[44]++;}
}

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[40]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[81]++;

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[82]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((shape != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[45]++;
            addItemEntity(entities, dataset, row, column, shape);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[83]++;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[46]++;}

    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[84]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[47]++;
            return true;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[48]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[85]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((obj instanceof StatisticalLineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[50]++;}
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[86]++;
        StatisticalLineAndShapeRenderer that
                = (StatisticalLineAndShapeRenderer) obj;
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[87]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.errorIndicatorPaint,
                that.errorIndicatorPaint)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.branches[52]++;}
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
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[88]++;
        SerialUtilities.writePaint(this.errorIndicatorPaint, stream);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[89]++;
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
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[90]++;
        this.errorIndicatorPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh.statements[91]++;
    }

}

class CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh ());
  }
    public static long[] statements = new long[92];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$43qu39oyauco8u1f514tk9ygkw1kjp677yiozitbtz2oik8r2cj4stwh () {
    super("org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 91; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer.java");
      for (int i = 1; i <= 91; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
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

