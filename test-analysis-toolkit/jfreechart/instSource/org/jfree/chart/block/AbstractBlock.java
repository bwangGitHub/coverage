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
 * AbstractBlock.java
 * ------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 02-Feb-2005 : Added accessor methods for margin (DG);
 * 04-Feb-2005 : Added equals() method and implemented Serializable (DG);
 * 03-May-2005 : Added null argument checks (DG);
 * 06-May-2005 : Added convenience methods for setting margin, border and 
 *               padding (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 16-Mar-2007 : Changed border from BlockBorder to BlockFrame, updated 
 *               equals(), and implemented Cloneable (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A convenience class for creating new classes that implement 
 * the {@link Block} interface.
 */
public class AbstractBlock implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7689852412141274563L;
  static {
    CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[1]++;
  }
    
    /** The id for the block. */
    private String id;
    
    /** The margin around the outside of the block. */
    private RectangleInsets margin;
    
    /** The frame (or border) for the block. */
    private BlockFrame frame;

    /** The padding between the block content and the border. */
    private RectangleInsets padding;
    
    /** 
     * The natural width of the block (may be overridden if there are 
     * constraints in sizing).
     */
    private double width;
    
    /** 
     * The natural height of the block (may be overridden if there are 
     * constraints in sizing).
     */
    private double height;
    
    /**
     * The current bounds for the block (position of the block in Java2D space).
     */
    private transient Rectangle2D bounds;
    
    /**
     * Creates a new block.
     */
    protected AbstractBlock() {
        this.id = null;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[2]++;
        this.width = 0.0;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[3]++;
        this.height = 0.0;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[4]++;
        this.bounds = new Rectangle2D.Float();
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[5]++;
        this.margin = RectangleInsets.ZERO_INSETS;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[6]++;
        this.frame = BlockBorder.NONE;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[7]++; 
        this.padding = RectangleInsets.ZERO_INSETS;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[8]++;
    }
    
    /**
     * Returns the id.
     * 
     * @return The id (possibly <code>null</code>).
     * 
     * @see #setID(String)
     */
    public String getID() {
        return this.id;   
    }
    
    /**
     * Sets the id for the block.
     * 
     * @param id  the id (<code>null</code> permitted).
     * 
     * @see #getID()
     */
    public void setID(String id) {
        this.id = id;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[9]++;   
    }
    
    /**
     * Returns the natural width of the block, if this is known in advance.
     * The actual width of the block may be overridden if layout constraints
     * make this necessary.  
     * 
     * @return The width.
     * 
     * @see #setWidth(double)
     */
    public double getWidth() {
        return this.width;
    }
    
    /**
     * Sets the natural width of the block, if this is known in advance.
     * 
     * @param width  the width (in Java2D units)
     * 
     * @see #getWidth()
     */
    public void setWidth(double width) {
        this.width = width;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[10]++;
    }
    
    /**
     * Returns the natural height of the block, if this is known in advance.
     * The actual height of the block may be overridden if layout constraints
     * make this necessary.  
     * 
     * @return The height.
     * 
     * @see #setHeight(double)
     */
    public double getHeight() {
        return this.height;
    }
    
    /**
     * Sets the natural width of the block, if this is known in advance.
     * 
     * @param height  the width (in Java2D units)
     * 
     * @see #getHeight()
     */
    public void setHeight(double height) {
        this.height = height;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[11]++;
    }
    
    /**
     * Returns the margin.
     * 
     * @return The margin (never <code>null</code>).
     * 
     * @see #getMargin()
     */
    public RectangleInsets getMargin() {
        return this.margin;
    }
        
    /**
     * Sets the margin (use {@link RectangleInsets#ZERO_INSETS} for no 
     * padding).
     * 
     * @param margin  the margin (<code>null</code> not permitted).
     * 
     * @see #getMargin()
     */
    public void setMargin(RectangleInsets margin) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((margin == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[1]++;
            throw new IllegalArgumentException("Null 'margin' argument.");
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[2]++;}
        this.margin = margin;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[13]++;
    }

    /**
     * Sets the margin.
     * 
     * @param top  the top margin.
     * @param left  the left margin.
     * @param bottom  the bottom margin.
     * @param right  the right margin.
     * 
     * @see #getMargin()
     */
    public void setMargin(double top, double left, double bottom, 
                          double right) {
        setMargin(new RectangleInsets(top, left, bottom, right));
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[14]++;
    }

    /**
     * Returns the border.
     * 
     * @return The border (never <code>null</code>).
     * 
     * @deprecated Use {@link #getFrame()} instead.
     */
    public BlockBorder getBorder() {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.frame instanceof BlockBorder) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[3]++;
            return (BlockBorder) this.frame;

        }
        else {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[4]++;
            return null;
        }
    }
    
    /**
     * Sets the border for the block (use {@link BlockBorder#NONE} for
     * no border).
     * 
     * @param border  the border (<code>null</code> not permitted).
     * 
     * @see #getBorder()
     * 
     * @deprecated Use {@link #setFrame(BlockFrame)} instead.
     */
    public void setBorder(BlockBorder border) {
        setFrame(border);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[16]++;
    }
    
    /**
     * Sets a black border with the specified line widths.
     * 
     * @param top  the top border line width.
     * @param left  the left border line width.
     * @param bottom  the bottom border line width.
     * @param right  the right border line width.
     */
    public void setBorder(double top, double left, double bottom, 
                          double right) {
        setFrame(new BlockBorder(top, left, bottom, right));
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[17]++;
    }
    
    /**
     * Returns the current frame (border).
     * 
     * @return The frame.
     * 
     * @since 1.0.5
     * @see #setFrame(BlockFrame)
     */
    public BlockFrame getFrame() {
        return this.frame;
    }
    
    /**
     * Sets the frame (or border).
     * 
     * @param frame  the frame (<code>null</code> not permitted).
     * 
     * @since 1.0.5
     * @see #getFrame()
     */
    public void setFrame(BlockFrame frame) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((frame == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[5]++;
            throw new IllegalArgumentException("Null 'frame' argument.");
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[6]++;}
        this.frame = frame;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[19]++;
    }
    
    /**
     * Returns the padding.
     * 
     * @return The padding (never <code>null</code>).
     * 
     * @see #setPadding(RectangleInsets)
     */
    public RectangleInsets getPadding() {
        return this.padding;
    }
    
    /**
     * Sets the padding (use {@link RectangleInsets#ZERO_INSETS} for no 
     * padding).
     * 
     * @param padding  the padding (<code>null</code> not permitted).
     * 
     * @see #getPadding()
     */
    public void setPadding(RectangleInsets padding) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[7]++;
            throw new IllegalArgumentException("Null 'padding' argument.");
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[8]++;}
        this.padding = padding;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[21]++;
    }

    /**
     * Sets the padding.
     * 
     * @param top  the top padding.
     * @param left  the left padding.
     * @param bottom  the bottom padding.
     * @param right  the right padding.
     */
    public void setPadding(double top, double left, double bottom, 
                           double right) {
        setPadding(new RectangleInsets(top, left, bottom, right));
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[22]++;
    }
    
    /**
     * Returns the x-offset for the content within the block.
     * 
     * @return The x-offset.
     * 
     * @see #getContentYOffset()
     */
    public double getContentXOffset() {
        return this.margin.getLeft() + this.frame.getInsets().getLeft() 
            + this.padding.getLeft();    
    }
    
    /**
     * Returns the y-offset for the content within the block.
     * 
     * @return The y-offset.
     * 
     * @see #getContentXOffset()
     */
    public double getContentYOffset() {
        return this.margin.getTop() + this.frame.getInsets().getTop() 
            + this.padding.getTop();   
    }
    
    /**
     * Arranges the contents of the block, with no constraints, and returns 
     * the block size.
     * 
     * @param g2  the graphics device.
     * 
     * @return The block size (in Java2D units, never <code>null</code>).
     */
    public Size2D arrange(Graphics2D g2) {  
        return arrange(g2, RectangleConstraint.NONE);
    }

    /**
     * Arranges the contents of the block, within the given constraints, and 
     * returns the block size.
     * 
     * @param g2  the graphics device.
     * @param constraint  the constraint (<code>null</code> not permitted).
     * 
     * @return The block size (in Java2D units, never <code>null</code>).
     */
    public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[23]++;
        Size2D base = new Size2D(getWidth(), getHeight());
        return constraint.calculateConstrainedSize(base);
    }

    /**
     * Returns the current bounds of the block.
     * 
     * @return The bounds.
     * 
     * @see #setBounds(Rectangle2D)
     */
    public Rectangle2D getBounds() {
        return this.bounds;
    }
    
    /**
     * Sets the bounds of the block.
     * 
     * @param bounds  the bounds (<code>null</code> not permitted).
     * 
     * @see #getBounds()
     */
    public void setBounds(Rectangle2D bounds) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((bounds == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[9]++;
            throw new IllegalArgumentException("Null 'bounds' argument.");

        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[10]++;}
        this.bounds = bounds;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[25]++;
    }
    
    /**
     * Calculate the width available for content after subtracting 
     * the margin, border and padding space from the specified fixed 
     * width.
     * 
     * @param fixedWidth  the fixed width.
     * 
     * @return The available space.
     * 
     * @see #trimToContentHeight(double)
     */
    protected double trimToContentWidth(double fixedWidth) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[26]++;
        double result = this.margin.trimWidth(fixedWidth);
        result = this.frame.getInsets().trimWidth(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[27]++;
        result = this.padding.trimWidth(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[28]++;
        return Math.max(result, 0.0);
    }

    /**
     * Calculate the height available for content after subtracting 
     * the margin, border and padding space from the specified fixed 
     * height.
     * 
     * @param fixedHeight  the fixed height.
     * 
     * @return The available space.
     * 
     * @see #trimToContentWidth(double)
     */
    protected double trimToContentHeight(double fixedHeight) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[29]++;
        double result = this.margin.trimHeight(fixedHeight);
        result = this.frame.getInsets().trimHeight(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[30]++;
        result = this.padding.trimHeight(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[31]++;
        return Math.max(result, 0.0);
    }
    
    /**
     * Returns a constraint for the content of this block that will result in
     * the bounds of the block matching the specified constraint.
     * 
     * @param c  the outer constraint (<code>null</code> not permitted).
     * 
     * @return The content constraint.
     */
    protected RectangleConstraint toContentConstraint(RectangleConstraint c) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[11]++;
            throw new IllegalArgumentException("Null 'c' argument.");

        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[12]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c.equals(RectangleConstraint.NONE)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[13]++;
            return c;

        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[14]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[34]++;
        double w = c.getWidth();
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[35]++;
        Range wr = c.getWidthRange();
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[36]++;
        double h = c.getHeight();
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[37]++;
        Range hr = c.getHeightRange();
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[38]++;
        double ww = trimToContentWidth(w);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[39]++;
        double hh = trimToContentHeight(h);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[40]++;
        Range wwr = trimToContentWidth(wr);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[41]++;
        Range hhr = trimToContentHeight(hr);
        return new RectangleConstraint(
            ww, wwr, c.getWidthConstraintType(), 
            hh, hhr, c.getHeightConstraintType()
        );
    }

    private Range trimToContentWidth(Range r) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[15]++;
            return null;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[16]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[43]++;
        double lowerBound = 0.0;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[44]++;
        double upperBound = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((r.getLowerBound() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[17]++;
            lowerBound = trimToContentWidth(r.getLowerBound());
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[46]++;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[18]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((r.getUpperBound() < Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[19]++;
            upperBound = trimToContentWidth(r.getUpperBound());
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[48]++;

        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[20]++;}
        return new Range(lowerBound, upperBound);
    }
    
    private Range trimToContentHeight(Range r) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[21]++;
            return null;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[22]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[50]++;
        double lowerBound = 0.0;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[51]++;
        double upperBound = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[52]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((r.getLowerBound() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[23]++;
            lowerBound = trimToContentHeight(r.getLowerBound());
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[53]++;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[24]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((r.getUpperBound() < Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[25]++;
            upperBound = trimToContentHeight(r.getUpperBound());
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[55]++;

        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[26]++;}
        return new Range(lowerBound, upperBound);
    }
    
    /**
     * Adds the margin, border and padding to the specified content width.
     * 
     * @param contentWidth  the content width.
     * 
     * @return The adjusted width.
     */
    protected double calculateTotalWidth(double contentWidth) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[56]++;
        double result = contentWidth;
        result = this.padding.extendWidth(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[57]++;
        result = this.frame.getInsets().extendWidth(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[58]++;
        result = this.margin.extendWidth(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[59]++;
        return result;
    }

    /**
     * Adds the margin, border and padding to the specified content height.
     * 
     * @param contentHeight  the content height.
     * 
     * @return The adjusted height.
     */
    protected double calculateTotalHeight(double contentHeight) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[60]++;
        double result = contentHeight;
        result = this.padding.extendHeight(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[61]++;
        result = this.frame.getInsets().extendHeight(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[62]++;
        result = this.margin.extendHeight(result);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[63]++;
        return result;
    }

    /**
     * Reduces the specified area by the amount of space consumed 
     * by the margin.
     * 
     * @param area  the area (<code>null</code> not permitted).
     * 
     * @return The trimmed area.
     */
    protected Rectangle2D trimMargin(Rectangle2D area) {
        // defer argument checking...
        this.margin.trim(area);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[64]++;
        return area;
    }
    
    /**
     * Reduces the specified area by the amount of space consumed 
     * by the border.
     * 
     * @param area  the area (<code>null</code> not permitted).
     * 
     * @return The trimmed area.
     */
    protected Rectangle2D trimBorder(Rectangle2D area) {
        // defer argument checking...
        this.frame.getInsets().trim(area);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[65]++;
        return area;
    }

    /**
     * Reduces the specified area by the amount of space consumed 
     * by the padding.
     * 
     * @param area  the area (<code>null</code> not permitted).
     * 
     * @return The trimmed area.
     */
    protected Rectangle2D trimPadding(Rectangle2D area) {
        // defer argument checking...
        this.padding.trim(area);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[66]++;
        return area;
    }

    /**
     * Draws the border around the perimeter of the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     */
    protected void drawBorder(Graphics2D g2, Rectangle2D area) {
        this.frame.draw(g2, area);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[67]++;
    }
    
    /**
     * Tests this block for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[68]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[27]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[28]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[69]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractBlock) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[29]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[30]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[70]++;
        AbstractBlock that = (AbstractBlock) obj;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[71]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.id, that.id)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[32]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[72]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.frame.equals(that.frame)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[33]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[34]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.bounds.equals(that.bounds)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[36]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[74]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.margin.equals(that.margin)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[37]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[38]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[75]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.padding.equals(that.padding)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[39]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[40]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[76]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.height != that.height) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[41]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[42]++;}
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[77]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.width != that.width) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[43]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[44]++;}
        return true;
    }
    
    /**
     * Returns a clone of this block.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem creating the
     *         clone.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[78]++;
        AbstractBlock clone = (AbstractBlock) super.clone();
        clone.bounds = (Rectangle2D) ShapeUtilities.clone(this.bounds);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[79]++;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[80]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.frame instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[45]++;
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[81]++;
            PublicCloneable pc = (PublicCloneable) this.frame;
            clone.frame = (BlockFrame) pc.clone();
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[82]++;

        } else {
  CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.branches[46]++;}
        return clone;
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[83]++;
        SerialUtilities.writeShape(this.bounds, stream);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[84]++;
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
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[85]++;
        this.bounds = (Rectangle2D) SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h.statements[86]++;
    }

}

class CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h ());
  }
    public static long[] statements = new long[87];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.AbstractBlock.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$5fqsxyifvvimr1ilv20hhrie1o4h () {
    super("org.jfree.chart.block.AbstractBlock.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 86; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.AbstractBlock.java");
      for (int i = 1; i <= 86; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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

