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
 * ScatterRenderer.java
 * --------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   David Forslund;
 *
 * Changes
 * -------
 * 08-Oct-2007 : Version 1, based on patch 1780779 by David Forslund (DG);
 * 11-Oct-2007 : Renamed ScatterRenderer (DG);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.MultiValueCategoryDataset;
import org.jfree.util.BooleanList;
import org.jfree.util.BooleanUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that handles the multiple values from a 
 * {@link MultiValueCategoryDataset} by plotting a shape for each value for 
 * each given item in the dataset.
 * 
 * @since 1.0.7
 */
public class ScatterRenderer extends AbstractCategoryItemRenderer
        implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.ping();
  }


    /**
     * A table of flags that control (per series) whether or not shapes are
     * filled.
     */
    private BooleanList seriesShapesFilled;

    /**
     * The default value returned by the getShapeFilled() method.
     */
    private boolean baseShapesFilled;

    /**
     * A flag that controls whether the fill paint is used for filling
     * shapes.
     */
    private boolean useFillPaint;

    /**
     * A flag that controls whether outlines are drawn for shapes.
     */
    private boolean drawOutlines;

    /**
     * A flag that controls whether the outline paint is used for drawing shape
     * outlines - if not, the regular series paint is used.
     */
    private boolean useOutlinePaint;

    /**
     * A flag that controls whether or not the x-position for each item is
     * offset within the category according to the series.
     */
    private boolean useSeriesOffset;

    /**
     * The item margin used for series offsetting - this allows the positioning
     * to match the bar positions of the {@link BarRenderer} class.
     */
    private double itemMargin;

    /**
     * Constructs a new renderer.
     */
    public ScatterRenderer() {
        this.seriesShapesFilled = new BooleanList();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[1]++;
        this.baseShapesFilled = true;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[2]++;
        this.useFillPaint = false;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[3]++;
        this.drawOutlines = false;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[4]++;
        this.useOutlinePaint = false;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[5]++;
        this.useSeriesOffset = true;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[6]++;
        this.itemMargin = 0.20;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[7]++;
    }

    /**
     * Returns the flag that controls whether or not the x-position for each
     * data item is offset within the category according to the series.
     * 
     * @return A boolean.
     * 
     * @see #setUseSeriesOffset(boolean)
     */
    public boolean getUseSeriesOffset() {
        return this.useSeriesOffset;
    }
    
    /**
     * Sets the flag that controls whether or not the x-position for each 
     * data item is offset within its category according to the series, and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param offset  the offset.
     * 
     * @see #getUseSeriesOffset()
     */
    public void setUseSeriesOffset(boolean offset) {
        this.useSeriesOffset = offset;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[8]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[9]++;
    }
    
    /**
     * Returns the item margin, which is the gap between items within a 
     * category (expressed as a percentage of the overall category width).  
     * This can be used to match the offset alignment with the bars drawn by 
     * a {@link BarRenderer}).
     * 
     * @return The item margin.
     * 
     * @see #setItemMargin(double)
     * @see #getUseSeriesOffset()
     */
    public double getItemMargin() {
        return this.itemMargin;
    }
    
    /**
     * Sets the item margin, which is the gap between items within a category
     * (expressed as a percentage of the overall category width), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param margin  the margin (0.0 <= margin < 1.0).
     * 
     * @see #getItemMargin()
     * @see #getUseSeriesOffset()
     */
    public void setItemMargin(double margin) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((margin < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((margin >= 1.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[1]++;
            throw new IllegalArgumentException("Requires 0.0 <= margin < 1.0.");

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[2]++;}
        this.itemMargin = margin;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[11]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[12]++;
    }

    /**
     * Returns <code>true</code> if outlines should be drawn for shapes, and
     * <code>false</code> otherwise.
     *
     * @return A boolean.
     * 
     * @see #setDrawOutlines(boolean)
     */
    public boolean getDrawOutlines() {
        return this.drawOutlines;
    }

    /**
     * Sets the flag that controls whether outlines are drawn for
     * shapes, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * <p/>
     * In some cases, shapes look better if they do NOT have an outline, but
     * this flag allows you to set your own preference.
     *
     * @param flag the flag.
     * 
     * @see #getDrawOutlines()
     */
    public void setDrawOutlines(boolean flag) {
        this.drawOutlines = flag;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[13]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[14]++;
    }

    /**
     * Returns the flag that controls whether the outline paint is used for
     * shape outlines.  If not, the regular series paint is used.
     *
     * @return A boolean.
     * 
     * @see #setUseOutlinePaint(boolean)
     */
    public boolean getUseOutlinePaint() {
        return this.useOutlinePaint;
    }

    /**
     * Sets the flag that controls whether the outline paint is used for shape
     * outlines, and sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     *
     * @param use the flag.
     * 
     * @see #getUseOutlinePaint()
     */
    public void setUseOutlinePaint(boolean use) {
        this.useOutlinePaint = use;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[15]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[16]++;
    }

    // SHAPES FILLED

    /**
     * Returns the flag used to control whether or not the shape for an item
     * is filled. The default implementation passes control to the
     * <code>getSeriesShapesFilled</code> method. You can override this method
     * if you require different behaviour.
     *
     * @param series the series index (zero-based).
     * @param item   the item index (zero-based).
     * @return A boolean.
     */
    public boolean getItemShapeFilled(int series, int item) {
        return getSeriesShapesFilled(series);
    }

    /**
     * Returns the flag used to control whether or not the shapes for a series
     * are filled.
     *
     * @param series the series index (zero-based).
     * @return A boolean.
     */
    public boolean getSeriesShapesFilled(int series) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[17]++;
        Boolean flag = this.seriesShapesFilled.getBoolean(series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[3]++;
            return flag.booleanValue();

        } 
        else {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[4]++;
            return this.baseShapesFilled;
        }

    }

    /**
     * Sets the 'shapes filled' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param filled the flag.
     */
    public void setSeriesShapesFilled(int series, Boolean filled) {
        this.seriesShapesFilled.setBoolean(series, filled);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[19]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[20]++;
    }

    /**
     * Sets the 'shapes filled' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series the series index (zero-based).
     * @param filled the flag.
     */
    public void setSeriesShapesFilled(int series, boolean filled) {
        this.seriesShapesFilled.setBoolean(series, 
                BooleanUtilities.valueOf(filled));
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[21]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[22]++;
    }

    /**
     * Returns the base 'shape filled' attribute.
     *
     * @return The base flag.
     */
    public boolean getBaseShapesFilled() {
        return this.baseShapesFilled;
    }

    /**
     * Sets the base 'shapes filled' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag the flag.
     */
    public void setBaseShapesFilled(boolean flag) {
        this.baseShapesFilled = flag;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[23]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[24]++;
    }

    /**
     * Returns <code>true</code> if the renderer should use the fill paint
     * setting to fill shapes, and <code>false</code> if it should just
     * use the regular paint.
     *
     * @return A boolean.
     */
    public boolean getUseFillPaint() {
        return this.useFillPaint;
    }

    /**
     * Sets the flag that controls whether the fill paint is used to fill
     * shapes, and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param flag the flag.
     */
    public void setUseFillPaint(boolean flag) {
        this.useFillPaint = flag;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[25]++;
        fireChangeEvent();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[26]++;
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
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2, CategoryItemRendererState state,
            Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryDataset dataset, int row, int column,
            int pass) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((getItemVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[5]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[6]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[28]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[29]++;

        MultiValueCategoryDataset d = (MultiValueCategoryDataset) dataset;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[30]++;
        List values = d.getValues(row, column);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[7]++;
            return;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[8]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[32]++;
        int valueCount = values.size();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[33]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < valueCount) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.loops[1]--;
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.loops[2]--;
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.loops[3]++;
}
            // current data point...
            double x1;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.useSeriesOffset) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[9]++;
                x1 = domainAxis.getCategorySeriesMiddle(dataset.getColumnKey(
                        column), dataset.getRowKey(row), dataset, 
                        this.itemMargin, dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[35]++;

            }
            else {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[10]++;
                x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                        dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[36]++;
            }
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[37]++;
            Number n = (Number) values.get(i);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[38]++;
            double value = n.doubleValue();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[39]++;
            double y1 = rangeAxis.valueToJava2D(value, dataArea, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[40]++;

            Shape shape = getItemShape(row, column);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[11]++;
                shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[42]++;

            }
            else {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[12]++;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[43]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[13]++;
                shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[44]++;

            } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[14]++;}
}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getItemShapeFilled(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[15]++;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.useFillPaint) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[17]++;
                    g2.setPaint(getItemFillPaint(row, column));
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[47]++;

                }
                else {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[18]++;
                    g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[48]++;   
                }
                g2.fill(shape);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[49]++;

            } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[16]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[19]++;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[51]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[21]++;
                    g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[52]++;
   
                }
                else {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[22]++;
                    g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[53]++;
                }
                g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[54]++;
                g2.draw(shape);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[55]++;

            } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[20]++;}
        }

    }
    
    /**
     * Returns a legend item for a series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return The legend item.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[56]++;

        CategoryPlot cp = getPlot();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((cp == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[23]++;
            return null;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[24]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[58]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((isSeriesVisible(series)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(series)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[25]++;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[59]++;
            CategoryDataset dataset = cp.getDataset(datasetIndex);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[60]++;
            String label = getLegendItemLabelGenerator().generateLabel(
                    dataset, series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[61]++;
            String description = label;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[62]++;
            String toolTipText = null;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[63]++;
int CodeCoverConditionCoverageHelper_C15; 
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[27]++;
                toolTipText = getLegendItemToolTipGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[64]++;
   
            } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[28]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[65]++;
            String urlText = null;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[66]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[29]++;
                urlText = getLegendItemURLGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[67]++;
   
            } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[30]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[68]++;
            Shape shape = lookupSeriesShape(series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[69]++;
            Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[70]++;
            Paint fillPaint = (this.useFillPaint 
                    ? getItemFillPaint(series, 0) : paint);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[71]++;
            boolean shapeOutlineVisible = this.drawOutlines;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[72]++;
            Paint outlinePaint = (this.useOutlinePaint 
                    ? getItemOutlinePaint(series, 0) : paint);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[73]++;
            Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[74]++;
            LegendItem result = new LegendItem(label, description, toolTipText, 
                    urlText, true, shape, getItemShapeFilled(series, 0),
                    fillPaint, shapeOutlineVisible, outlinePaint, outlineStroke,
                    false, new Line2D.Double(-7.0, 0.0, 7.0, 0.0),
                    getItemStroke(series, 0), getItemPaint(series, 0));
            result.setDataset(dataset);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[75]++;
            result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[76]++;
            result.setSeriesKey(dataset.getRowKey(series));
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[77]++;
            result.setSeriesIndex(series);
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[78]++;
            return result;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[26]++;}
        return null;

    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj the object (<code>null</code> permitted).
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[79]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[31]++;
            return true;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[32]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[80]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj instanceof ScatterRenderer) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[34]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[81]++;
        ScatterRenderer that = (ScatterRenderer) obj;
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[82]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesShapesFilled,
                that.seriesShapesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[36]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[83]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.baseShapesFilled != that.baseShapesFilled) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[38]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[84]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.useFillPaint != that.useFillPaint) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[40]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[85]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.drawOutlines != that.drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[42]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[86]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint != that.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[44]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[87]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.useSeriesOffset != that.useSeriesOffset) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[46]++;}
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[88]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.itemMargin != that.itemMargin) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.branches[48]++;}
        return super.equals(obj);
    }

    /**
     * Returns an independent copy of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  should not happen.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[89]++;
        ScatterRenderer clone = (ScatterRenderer) super.clone();
        clone.seriesShapesFilled 
                = (BooleanList) this.seriesShapesFilled.clone();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[90]++;
        return clone;
    }

    /**
     * Provides serialization support.
     *
     * @param stream the output stream.
     * @throws java.io.IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[91]++;

    }

    /**
     * Provides serialization support.
     *
     * @param stream the input stream.
     * @throws java.io.IOException    if there is an I/O error.
     * @throws ClassNotFoundException if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx.statements[92]++;

    }

}

class CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx ());
  }
    public static long[] statements = new long[93];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.ScatterRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$9qnwtlejh5spqn016u85whhg6ujb4gx () {
    super("org.jfree.chart.renderer.category.ScatterRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 92; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.ScatterRenderer.java");
      for (int i = 1; i <= 92; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

