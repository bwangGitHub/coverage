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
 * XYBlockRenderer.java
 * --------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Jul-2006 : Version 1 (DG);
 * 02-Feb-2007 : Added getPaintScale() method (DG);
 * 09-Mar-2007 : Fixed cloning (DG);
 * 03-Aug-2007 : Fix for bug 1766646 (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that represents data from an {@link XYZDataset} by drawing a
 * color block at each (x, y) point, where the color is a function of the
 * z-value from the dataset.
 * 
 * @since 1.0.4
 */
public class XYBlockRenderer extends AbstractXYItemRenderer 
        implements XYItemRenderer, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.ping();
  }


    /**
     * The block width (defaults to 1.0).
     */
    private double blockWidth = 1.0;
  {
    CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[1]++;
  }
    
    /**
     * The block height (defaults to 1.0).
     */
    private double blockHeight = 1.0;
  {
    CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[2]++;
  }
    
    /**
     * The anchor point used to align each block to its (x, y) location.  The
     * default value is <code>RectangleAnchor.CENTER</code>.
     */
    private RectangleAnchor blockAnchor = RectangleAnchor.CENTER;
  {
    CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[3]++;
  }
    
    /** Temporary storage for the x-offset used to align the block anchor. */
    private double xOffset;
    
    /** Temporary storage for the y-offset used to align the block anchor. */
    private double yOffset;
    
    /** The paint scale. */
    private PaintScale paintScale;
    
    /**
     * Creates a new <code>XYBlockRenderer</code> instance with default 
     * attributes.
     */
    public XYBlockRenderer() {
        updateOffsets();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[4]++;
        this.paintScale = new LookupPaintScale();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[5]++;
    }
    
    /**
     * Returns the block width, in data/axis units.
     * 
     * @return The block width.
     * 
     * @see #setBlockWidth(double)
     */
    public double getBlockWidth() {
        return this.blockWidth;
    }
    
    /**
     * Sets the width of the blocks used to represent each data item and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param width  the new width, in data/axis units (must be > 0.0).
     * 
     * @see #getBlockWidth()
     */
    public void setBlockWidth(double width) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((width <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[1]++;
            throw new IllegalArgumentException(
                    "The 'width' argument must be > 0.0");

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[2]++;}
        this.blockWidth = width;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[7]++;
        updateOffsets();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[8]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[9]++;
    }
    
    /**
     * Returns the block height, in data/axis units.
     * 
     * @return The block height.
     * 
     * @see #setBlockHeight(double)
     */
    public double getBlockHeight() {
        return this.blockHeight;
    }
    
    /**
     * Sets the height of the blocks used to represent each data item and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param height  the new height, in data/axis units (must be > 0.0).
     * 
     * @see #getBlockHeight()
     */
    public void setBlockHeight(double height) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((height <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[3]++;
            throw new IllegalArgumentException(
                    "The 'height' argument must be > 0.0");

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[4]++;}
        this.blockHeight = height;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[11]++;
        updateOffsets();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[12]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[13]++;
    }
    
    /**
     * Returns the anchor point used to align a block at its (x, y) location.
     * The default values is {@link RectangleAnchor#CENTER}.
     * 
     * @return The anchor point (never <code>null</code>).
     * 
     * @see #setBlockAnchor(RectangleAnchor)
     */
    public RectangleAnchor getBlockAnchor() {
        return this.blockAnchor;
    }
    
    /**
     * Sets the anchor point used to align a block at its (x, y) location and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param anchor  the anchor.
     * 
     * @see #getBlockAnchor()
     */
    public void setBlockAnchor(RectangleAnchor anchor) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[5]++; 
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[6]++;}
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(anchor)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[7]++;
            return;
  // no change
        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[8]++;}
        this.blockAnchor = anchor;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[16]++;
        updateOffsets();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[17]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[18]++;
    }
    
    /**
     * Returns the paint scale used by the renderer.
     * 
     * @return The paint scale (never <code>null</code>).
     * 
     * @see #setPaintScale(PaintScale)
     * @since 1.0.4
     */
    public PaintScale getPaintScale() {
        return this.paintScale;
    }
    
    /**
     * Sets the paint scale used by the renderer and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param scale  the scale (<code>null</code> not permitted).
     * 
     * @see #getPaintScale()
     * @since 1.0.4
     */
    public void setPaintScale(PaintScale scale) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((scale == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[9]++;
            throw new IllegalArgumentException("Null 'scale' argument.");

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[10]++;}
        this.paintScale = scale;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[20]++;
        fireChangeEvent();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[21]++;
    }
    
    /**
     * Updates the offsets to take into account the block width, height and
     * anchor.
     */
    private void updateOffsets() {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.BOTTOM_LEFT)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[11]++;
            this.xOffset = 0.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[23]++;
            this.yOffset = 0.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[24]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[12]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[25]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.BOTTOM)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[13]++;
            this.xOffset = -this.blockWidth / 2.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[26]++;
            this.yOffset = 0.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[27]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[14]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[28]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.BOTTOM_RIGHT)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[15]++;
            this.xOffset = -this.blockWidth;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[29]++;
            this.yOffset = 0.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[30]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[16]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[31]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.LEFT)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[17]++;
            this.xOffset = 0.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[32]++;
            this.yOffset = -this.blockHeight / 2.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[33]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[18]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[34]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.CENTER)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[19]++;
            this.xOffset = -this.blockWidth / 2.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[35]++;
            this.yOffset = -this.blockHeight / 2.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[36]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[20]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[37]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.RIGHT)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[21]++;
            this.xOffset = -this.blockWidth;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[38]++;
            this.yOffset = -this.blockHeight / 2.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[39]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[22]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[40]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.TOP_LEFT)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[23]++;
            this.xOffset = 0.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[41]++;
            this.yOffset = -this.blockHeight;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[42]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[24]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[43]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.TOP)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[25]++;
            this.xOffset = -this.blockWidth / 2.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[44]++;
            this.yOffset = -this.blockHeight;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[45]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[26]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[46]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(RectangleAnchor.TOP_RIGHT)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[27]++;
            this.xOffset = -this.blockWidth;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[47]++;
            this.yOffset = -this.blockHeight;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[48]++;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[28]++;}
}
}
}
}
}
}
}
}        
    }
    
    /**
     * Returns the lower and upper bounds (range) of the x-values in the 
     * specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     *         
     * @see #findRangeBounds(XYDataset)
     */
    public Range findDomainBounds(XYDataset dataset) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[49]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[29]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[50]++;
            Range r = DatasetUtilities.findDomainBounds(dataset, false);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[51]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[31]++;
                return null;
 
            }
            else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[32]++;
                return new Range(r.getLowerBound() + this.xOffset, 
                        r.getUpperBound() + this.blockWidth + this.xOffset);
            }

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[30]++;
            return null;
        }
    }

    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code> 
     *         or empty).
     *         
     * @see #findDomainBounds(XYDataset)
     */
    public Range findRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[52]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[33]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[53]++;
            Range r = DatasetUtilities.findRangeBounds(dataset, false);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[54]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[35]++;
                return null;
 
            }
            else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[36]++;
                return new Range(r.getLowerBound() + this.yOffset, 
                        r.getUpperBound() + this.blockHeight + this.yOffset);
            }

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[34]++;
            return null;
        }
    }
    
    /**
     * Draws the block representing the specified item.
     * 
     * @param g2  the graphics device.
     * @param state  the state.
     * @param dataArea  the data area.
     * @param info  the plot rendering info.
     * @param plot  the plot.
     * @param domainAxis  the x-axis.
     * @param rangeAxis  the y-axis.
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
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[55]++;
        
        double x = dataset.getXValue(series, item);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[56]++;
        double y = dataset.getYValue(series, item);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[57]++;
        double z = 0.0;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[58]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((dataset instanceof XYZDataset) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[37]++;
            z = ((XYZDataset) dataset).getZValue(series, item);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[59]++;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[38]++;}
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[60]++;
        Paint p = this.paintScale.getPaint(z);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[61]++;
        double xx0 = domainAxis.valueToJava2D(x + this.xOffset, dataArea, 
                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[62]++;
        double yy0 = rangeAxis.valueToJava2D(y + this.yOffset, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[63]++;
        double xx1 = domainAxis.valueToJava2D(x + this.blockWidth 
                + this.xOffset, dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[64]++;
        double yy1 = rangeAxis.valueToJava2D(y + this.blockHeight 
                + this.yOffset, dataArea, plot.getRangeAxisEdge());
        Rectangle2D block;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[65]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[66]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((orientation.equals(PlotOrientation.HORIZONTAL)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[39]++;
            block = new Rectangle2D.Double(Math.min(yy0, yy1), 
                    Math.min(xx0, xx1), Math.abs(yy1 - yy0), 
                    Math.abs(xx0 - xx1));
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[67]++;

        }
        else {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[40]++;
            block = new Rectangle2D.Double(Math.min(xx0, xx1), 
                    Math.min(yy0, yy1), Math.abs(xx1 - xx0), 
                    Math.abs(yy1 - yy0));
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[68]++;            
        }
        g2.setPaint(p);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[69]++;
        g2.fill(block);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[70]++;
        g2.setStroke(new BasicStroke(1.0f));
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[71]++;
        g2.draw(block);
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[72]++;
    }
    
    /**
     * Tests this <code>XYBlockRenderer</code> for equality with an arbitrary
     * object.  This method returns <code>true</code> if and only if:
     * <ul>
     * <li><code>obj</code> is an instance of <code>XYBlockRenderer</code> (not
     *     <code>null</code>);</li>
     * <li><code>obj</code> has the same field values as this 
     *     <code>XYBlockRenderer</code>;</li>
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[73]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[41]++;
            return true;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[42]++;}
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[74]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((obj instanceof XYBlockRenderer) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[44]++;}
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[75]++;
        XYBlockRenderer that = (XYBlockRenderer) obj;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[76]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.blockHeight != that.blockHeight) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[46]++;}
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[77]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.blockWidth != that.blockWidth) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[48]++;}
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[78]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.blockAnchor.equals(that.blockAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[50]++;}
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[79]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.paintScale.equals(that.paintScale)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[52]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a clone of this renderer.
     * 
     * @return A clone of this renderer.
     * 
     * @throws CloneNotSupportedException if there is a problem creating the 
     *     clone.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[80]++;
        XYBlockRenderer clone = (XYBlockRenderer) super.clone();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[81]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.paintScale instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[53]++;
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[82]++;
            PublicCloneable pc = (PublicCloneable) this.paintScale;
            clone.paintScale = (PaintScale) pc.clone();
CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.statements[83]++;

        } else {
  CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h.branches[54]++;}
        return clone;
    }

}

class CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h ());
  }
    public static long[] statements = new long[84];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYBlockRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$abiv2501iedft36cjfpkcvw6t2aub8h () {
    super("org.jfree.chart.renderer.xy.XYBlockRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 83; i++) {
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
    log.startNamedSection("org.jfree.chart.renderer.xy.XYBlockRenderer.java");
      for (int i = 1; i <= 83; i++) {
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

