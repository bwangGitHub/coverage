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
 * MinMaxCategoryRenderer.java
 * ---------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  Tomer Peretz;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Christian W. Zuckschwerdt;
 *                   Nicolas Brodu (for Astrium and EADS Corporate Research 
 *                   Center);
 *
 * Changes:
 * --------
 * 29-May-2002 : Version 1 (TP);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Base dataset is now TableDataset not CategoryDataset (DG);
 * 17-Jan-2003 : Moved plot classes to a separate package (DG);
 * 10-Apr-2003 : Changed CategoryDataset to KeyedValues2DDataset in drawItem() 
 *               method (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 08-Sep-2003 : Implemented Serializable (NB);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 17-Nov-2005 : Added change events and argument checks (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 09-Mar-2007 : Fixed problem with horizontal rendering (DG);
 * 28-Sep-2007 : Added equals() method override (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Icon;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.util.PaintUtilities;

/**
 * Renderer for drawing min max plot. This renderer draws all the series under 
 * the same category in the same x position using <code>objectIcon</code> and 
 * a line from the maximum value to the minimum value.
 * <p>
 * For use with the {@link org.jfree.chart.plot.CategoryPlot} class.
 */
public class MinMaxCategoryRenderer extends AbstractCategoryItemRenderer {
  static {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2935615937671064911L;
  static {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[1]++;
  }
    
    /** A flag indicating whether or not lines are drawn between XY points. */
    private boolean plotLines = false;
  {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[2]++;
  }

    /** 
     * The paint of the line between the minimum value and the maximum value.
     */
    private transient Paint groupPaint = Color.black;
  {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[3]++;
  }

    /** 
     * The stroke of the line between the minimum value and the maximum value.
     */
    private transient Stroke groupStroke = new BasicStroke(1.0f);
  {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[4]++;
  }

    /** The icon used to indicate the minimum value.*/
    private transient Icon minIcon = getIcon(new Arc2D.Double(-4, -4, 8, 8, 0,
            360, Arc2D.OPEN), null, Color.black);
  {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[5]++;
  }

    /** The icon used to indicate the maximum value.*/
    private transient Icon maxIcon = getIcon(new Arc2D.Double(-4, -4, 8, 8, 0,
            360, Arc2D.OPEN), null, Color.black);
  {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[6]++;
  }

    /** The icon used to indicate the values.*/
    private transient Icon objectIcon = getIcon(new Line2D.Double(-4, 0, 4, 0),
            false, true);
  {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[7]++;
  }

    /** The last category. */
    private int lastCategory = -1;
  {
    CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[8]++;
  }

    /** The minimum. */
    private double min;

    /** The maximum. */
    private double max;

    /**
     * Default constructor.
     */
    public MinMaxCategoryRenderer() {
        super();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[9]++;
    }

    /**
     * Gets whether or not lines are drawn between category points.
     *
     * @return boolean true if line will be drawn between sequenced categories,
     *         otherwise false.
     *         
     * @see #setDrawLines(boolean)
     */
    public boolean isDrawLines() {
        return this.plotLines;
    }

    /**
     * Sets the flag that controls whether or not lines are drawn to connect
     * the items within a series and sends a {@link RendererChangeEvent} to 
     * all registered listeners.
     *
     * @param draw  the new value of the flag.
     * 
     * @see #isDrawLines()
     */
    public void setDrawLines(boolean draw) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.plotLines != draw) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[1]++;
            this.plotLines = draw;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[11]++;
            fireChangeEvent();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[12]++;

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[2]++;}
        
    }

    /**
     * Returns the paint used to draw the line between the minimum and maximum
     * value items in each category.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setGroupPaint(Paint)
     */
    public Paint getGroupPaint() {
        return this.groupPaint;
    }

    /**
     * Sets the paint used to draw the line between the minimum and maximum
     * value items in each category and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getGroupPaint()
     */
    public void setGroupPaint(Paint paint) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[4]++;}
        this.groupPaint = paint;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[14]++;
        fireChangeEvent();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[15]++;
    }

    /**
     * Returns the stroke used to draw the line between the minimum and maximum
     * value items in each category.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setGroupStroke(Stroke)
     */
    public Stroke getGroupStroke() {
        return this.groupStroke;
    }

    /**
     * Sets the stroke of the line between the minimum value and the maximum 
     * value and sends a {@link RendererChangeEvent} to all registered 
     * listeners.
     *
     * @param stroke the new stroke (<code>null</code> not permitted).
     */
    public void setGroupStroke(Stroke stroke) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[5]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[6]++;}
        this.groupStroke = stroke;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[17]++;
        fireChangeEvent();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[18]++;
    }

    /**
     * Returns the icon drawn for each data item.
     *
     * @return The icon (never <code>null</code>).
     * 
     * @see #setObjectIcon(Icon)
     */
    public Icon getObjectIcon() {
        return this.objectIcon;
    }

    /**
     * Sets the icon drawn for each data item and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param icon  the icon.
     * 
     * @see #getObjectIcon()
     */
    public void setObjectIcon(Icon icon) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((icon == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[7]++;
            throw new IllegalArgumentException("Null 'icon' argument.");

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[8]++;}
        this.objectIcon = icon;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[20]++;
        fireChangeEvent();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[21]++;
    }

    /**
     * Returns the icon displayed for the maximum value data item within each
     * category.
     *
     * @return The icon (never <code>null</code>).
     * 
     * @see #setMaxIcon(Icon)
     */
    public Icon getMaxIcon() {
        return this.maxIcon;
    }

    /**
     * Sets the icon displayed for the maximum value data item within each
     * category and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param icon  the icon (<code>null</code> not permitted).
     * 
     * @see #getMaxIcon()
     */
    public void setMaxIcon(Icon icon) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((icon == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[9]++;
            throw new IllegalArgumentException("Null 'icon' argument.");

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[10]++;}
        this.maxIcon = icon;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[23]++;
        fireChangeEvent();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[24]++;
    }

    /**
     * Returns the icon displayed for the minimum value data item within each
     * category.
     *
     * @return The icon (never <code>null</code>).
     * 
     * @see #setMinIcon(Icon)
     */
    public Icon getMinIcon() {
        return this.minIcon;
    }

    /**
     * Sets the icon displayed for the minimum value data item within each
     * category and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param icon  the icon (<code>null</code> not permitted).
     * 
     * @see #getMinIcon()
     */
    public void setMinIcon(Icon icon) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((icon == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[11]++;
            throw new IllegalArgumentException("Null 'icon' argument.");

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[12]++;}
        this.minIcon = icon;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[26]++;
        fireChangeEvent();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[27]++;
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
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[28]++;

        // first check the number we are plotting...
        Number value = dataset.getValue(row, column);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[13]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[30]++;
            // current data point...
            double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                    dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[31]++;
            double y1 = rangeAxis.valueToJava2D(value.doubleValue(), dataArea, 
                    plot.getRangeAxisEdge());
            g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[32]++;
            g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[33]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[34]++;
            Shape shape = null;
            shape = new Rectangle2D.Double(x1 - 4, y1 - 4, 8.0, 8.0);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[35]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[36]++;
            
            PlotOrientation orient = plot.getOrientation();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orient == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[15]++;
                this.objectIcon.paintIcon(null, g2, (int) x1, (int) y1);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[38]++;

            }
            else {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[16]++;
                this.objectIcon.paintIcon(null, g2, (int) y1, (int) x1);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[39]++;
            }
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;
            
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.lastCategory == column) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[17]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.min > value.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[19]++;
                    this.min = value.doubleValue();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[42]++;

                } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[20]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[43]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.max < value.doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[21]++;
                    this.max = value.doubleValue();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[44]++;

                } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[22]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
                
                // last series, so we are ready to draw the min and max
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dataset.getRowCount() - 1 == row) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[23]++;
                    g2.setPaint(this.groupPaint);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[46]++;
                    g2.setStroke(this.groupStroke);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[47]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[48]++;
                    double minY = rangeAxis.valueToJava2D(this.min, dataArea, 
                            plot.getRangeAxisEdge());
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[49]++;
                    double maxY = rangeAxis.valueToJava2D(this.max, dataArea, 
                            plot.getRangeAxisEdge());
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[50]++;
int CodeCoverConditionCoverageHelper_C13;
                    
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orient == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[25]++;
                        g2.draw(new Line2D.Double(x1, minY, x1, maxY));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[51]++;
                        this.minIcon.paintIcon(null, g2, (int) x1, (int) minY);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[52]++;
                        this.maxIcon.paintIcon(null, g2, (int) x1, (int) maxY);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[53]++;

                    }
                    else {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[26]++;
                        g2.draw(new Line2D.Double(minY, x1, maxY, x1));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[54]++;
                        this.minIcon.paintIcon(null, g2, (int) minY, (int) x1);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[55]++;
                        this.maxIcon.paintIcon(null, g2, (int) maxY, (int) x1);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[56]++;
                    }

                } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[24]++;}

            }
            else {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[18]++;  // reset the min and max
                this.lastCategory = column;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[57]++;
                this.min = value.doubleValue();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[58]++;
                this.max = value.doubleValue();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[59]++;
            }
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[60]++;
int CodeCoverConditionCoverageHelper_C14;
            
            // connect to the previous point
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.plotLines) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[27]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((column != 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[29]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[62]++;
                    Number previousValue = dataset.getValue(row, column - 1);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[63]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((previousValue != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[31]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[64]++;
                        // previous data point...
                        double previous = previousValue.doubleValue();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[65]++;
                        double x0 = domainAxis.getCategoryMiddle(column - 1, 
                                getColumnCount(), dataArea,
                                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[66]++;
                        double y0 = rangeAxis.valueToJava2D(previous, dataArea,
                                plot.getRangeAxisEdge());
                        g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[67]++;
                        g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[68]++;
                        Line2D line;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;
                        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orient == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[33]++;
                            line = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[70]++;

                        }
                        else {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[34]++;
                            line = new Line2D.Double(y0, x0, y1, x1);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[71]++;
                        }
                        g2.draw(line);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[72]++;

                    } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[32]++;}

                } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[30]++;}

            } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[28]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[73]++;

            // add an item entity, if this information is being collected
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((shape != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[35]++;
                addItemEntity(entities, dataset, row, column, shape);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[75]++;

            } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[14]++;}
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.  The icon 
     * fields are NOT included in the test, so this implementation is a little 
     * weak.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     *
     * @since 1.0.7
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[76]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[37]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[38]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[77]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj instanceof MinMaxCategoryRenderer) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[40]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[78]++;
        MinMaxCategoryRenderer that = (MinMaxCategoryRenderer) obj;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.plotLines != that.plotLines) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[42]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[80]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.groupPaint, that.groupPaint)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[44]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[81]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.groupStroke.equals(that.groupStroke)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[46]++;}
        return super.equals(obj);
    }

    /**
     * Returns an icon.
     *
     * @param shape  the shape.
     * @param fillPaint  the fill paint.
     * @param outlinePaint  the outline paint.
     *
     * @return The icon.
     */
    private Icon getIcon(Shape shape, final Paint fillPaint, 
                        final Paint outlinePaint) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[82]++;

      final int width = shape.getBounds().width;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[83]++;
      final int height = shape.getBounds().height;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[84]++;
      final GeneralPath path = new GeneralPath(shape);
      return new Icon() {
          public void paintIcon(Component c, Graphics g, int x, int y) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[85]++;
              Graphics2D g2 = (Graphics2D) g;
              path.transform(AffineTransform.getTranslateInstance(x, y));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[86]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[87]++;
int CodeCoverConditionCoverageHelper_C24;
              if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((fillPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[47]++;
                  g2.setPaint(fillPaint);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[88]++;
                  g2.fill(path);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[89]++;

              } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[48]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[90]++;
int CodeCoverConditionCoverageHelper_C25;
              if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[49]++;
                  g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[91]++;
                  g2.draw(path);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[92]++;

              } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[50]++;}
              path.transform(AffineTransform.getTranslateInstance(-x, -y));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[93]++;
        }

        public int getIconWidth() {
            return width;
        }

        public int getIconHeight() {
            return height;
        }

      };
    }

    /**
     * Returns an icon from a shape.
     *
     * @param shape  the shape.
     * @param fill  the fill flag.
     * @param outline  the outline flag.
     *
     * @return The icon.
     */
    private Icon getIcon(Shape shape, final boolean fill, 
            final boolean outline) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[94]++;
        final int width = shape.getBounds().width;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[95]++;
        final int height = shape.getBounds().height;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[96]++;
        final GeneralPath path = new GeneralPath(shape);
        return new Icon() {
            public void paintIcon(Component c, Graphics g, int x, int y) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[97]++;
                Graphics2D g2 = (Graphics2D) g;
                path.transform(AffineTransform.getTranslateInstance(x, y));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[98]++;
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[99]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((fill) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[51]++;
                    g2.fill(path);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[100]++;

                } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[52]++;}
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[101]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((outline) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[53]++;
                    g2.draw(path);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[102]++;

                } else {
  CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.branches[54]++;}
                path.transform(AffineTransform.getTranslateInstance(-x, -y));
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[103]++;
            }

            public int getIconWidth() {
                return width;
            }

            public int getIconHeight() {
                return height;
            }
        };
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
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[104]++;
        SerialUtilities.writeStroke(this.groupStroke, stream);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[105]++;
        SerialUtilities.writePaint(this.groupPaint, stream);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[106]++;
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
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[107]++;
        this.groupStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[108]++;
        this.groupPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[109]++;
          
        this.minIcon = getIcon(new Arc2D.Double(-4, -4, 8, 8, 0, 360, 
                Arc2D.OPEN), null, Color.black);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[110]++;
        this.maxIcon = getIcon(new Arc2D.Double(-4, -4, 8, 8, 0, 360, 
                Arc2D.OPEN), null, Color.black);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[111]++;
        this.objectIcon = getIcon(new Line2D.Double(-4, 0, 4, 0), false, true);
CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9.statements[112]++;
    }
    
}

class CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9 ());
  }
    public static long[] statements = new long[113];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.MinMaxCategoryRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 27; i++) {
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

  public CodeCoverCoverageCounter$4y7rt53mm3713j99uz2w5xn4v0mj33y8x91je9heu9 () {
    super("org.jfree.chart.renderer.category.MinMaxCategoryRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 112; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 54; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.MinMaxCategoryRenderer.java");
      for (int i = 1; i <= 112; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 54; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
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

