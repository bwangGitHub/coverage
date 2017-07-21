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
 * LegendGraphic.java
 * ------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 26-Oct-2004 : Version 1 (DG);
 * 21-Jan-2005 : Modified return type of RectangleAnchor.coordinates() 
 *               method (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 13-May-2005 : Fixed to respect margin, border and padding settings (DG);
 * 01-Sep-2005 : Implemented PublicCloneable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Dec-2006 : Added fillPaintTransformer attribute, so legend graphics can
 *               display gradient paint correctly, updated equals() and 
 *               corrected clone() (DG);
 * 01-Aug-2007 : Updated API docs (DG);
 * 
 */

package org.jfree.chart.title;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jfree.chart.block.AbstractBlock;
import org.jfree.chart.block.Block;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.Size2D;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * The graphical item within a legend item.
 */
public class LegendGraphic extends AbstractBlock 
                           implements Block, PublicCloneable {
  static {
    CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = -1338791523854985009L;
  static {
    CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[1]++;
  }

    /** 
     * A flag that controls whether or not the shape is visible - see also 
     * lineVisible. 
     */
    private boolean shapeVisible;
    
    /** 
     * The shape to display.  To allow for accurate positioning, the center
     * of the shape should be at (0, 0). 
     */
    private transient Shape shape;
    
    /**
     * Defines the location within the block to which the shape will be aligned.
     */
    private RectangleAnchor shapeLocation;
    
    /** 
     * Defines the point on the shape's bounding rectangle that will be 
     * aligned to the drawing location when the shape is rendered.
     */
    private RectangleAnchor shapeAnchor;
    
    /** A flag that controls whether or not the shape is filled. */
    private boolean shapeFilled;
    
    /** The fill paint for the shape. */
    private transient Paint fillPaint;
    
    /**
     * The fill paint transformer (used if the fillPaint is an instance of
     * GradientPaint).
     * 
     * @since 1.0.4
     */
    private GradientPaintTransformer fillPaintTransformer;
    
    /** A flag that controls whether or not the shape outline is visible. */
    private boolean shapeOutlineVisible;
    
    /** The outline paint for the shape. */
    private transient Paint outlinePaint;
    
    /** The outline stroke for the shape. */
    private transient Stroke outlineStroke;
    
    /** 
     * A flag that controls whether or not the line is visible - see also 
     * shapeVisible. 
     */
    private boolean lineVisible;
    
    /** The line. */
    private transient Shape line;
    
    /** The line stroke. */
    private transient Stroke lineStroke;
    
    /** The line paint. */
    private transient Paint linePaint;
    
    /**
     * Creates a new legend graphic.
     * 
     * @param shape  the shape (<code>null</code> not permitted).
     * @param fillPaint  the fill paint (<code>null</code> not permitted).
     */
    public LegendGraphic(Shape shape, Paint fillPaint) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[1]++;
            throw new IllegalArgumentException("Null 'shape' argument.");

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[2]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((fillPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[3]++;
            throw new IllegalArgumentException("Null 'fillPaint' argument.");

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[4]++;}
        this.shapeVisible = true;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[4]++;
        this.shape = shape;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[5]++;
        this.shapeAnchor = RectangleAnchor.CENTER;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[6]++;
        this.shapeLocation = RectangleAnchor.CENTER;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[7]++;
        this.shapeFilled = true;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[8]++;
        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[9]++;
        this.fillPaintTransformer = new StandardGradientPaintTransformer();
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[10]++;
        setPadding(2.0, 2.0, 2.0, 2.0);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[11]++;
    }
    
    /**
     * Returns a flag that controls whether or not the shape
     * is visible.
     * 
     * @return A boolean.
     * 
     * @see #setShapeVisible(boolean)
     */
    public boolean isShapeVisible() {
        return this.shapeVisible;
    }
    
    /**
     * Sets a flag that controls whether or not the shape is 
     * visible.
     * 
     * @param visible  the flag.
     * 
     * @see #isShapeVisible()
     */
    public void setShapeVisible(boolean visible) {
        this.shapeVisible = visible;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[12]++;
    }
    
    /**
     * Returns the shape.
     * 
     * @return The shape.
     * 
     * @see #setShape(Shape)
     */
    public Shape getShape() {
        return this.shape;
    }
    
    /**
     * Sets the shape.
     * 
     * @param shape  the shape.
     * 
     * @see #getShape()
     */
    public void setShape(Shape shape) {
        this.shape = shape;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[13]++;
    }

    /**
     * Returns a flag that controls whether or not the shapes
     * are filled.
     * 
     * @return A boolean.
     * 
     * @see #setShapeFilled(boolean)
     */
    public boolean isShapeFilled() {
        return this.shapeFilled;
    }
    
    /**
     * Sets a flag that controls whether or not the shape is
     * filled.
     * 
     * @param filled  the flag.
     * 
     * @see #isShapeFilled()
     */
    public void setShapeFilled(boolean filled) {
        this.shapeFilled = filled;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[14]++;
    }

    /**
     * Returns the paint used to fill the shape.
     * 
     * @return The fill paint.
     * 
     * @see #setFillPaint(Paint)
     */
    public Paint getFillPaint() {
        return this.fillPaint;
    }
    
    /**
     * Sets the paint used to fill the shape.
     * 
     * @param paint  the paint.
     * 
     * @see #getFillPaint()
     */
    public void setFillPaint(Paint paint) {
        this.fillPaint = paint;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[15]++;
    }
    
    /**
     * Returns the transformer used when the fill paint is an instance of 
     * <code>GradientPaint</code>.
     * 
     * @return The transformer (never <code>null</code>).
     * 
     * @since 1.0.4.
     * 
     * @see #setFillPaintTransformer(GradientPaintTransformer)
     */
    public GradientPaintTransformer getFillPaintTransformer() {
        return this.fillPaintTransformer;
    }
    
    /**
     * Sets the transformer used when the fill paint is an instance of 
     * <code>GradientPaint</code>.
     * 
     * @param transformer  the transformer (<code>null</code> not permitted).
     * 
     * @since 1.0.4
     * 
     * @see #getFillPaintTransformer()
     */
    public void setFillPaintTransformer(GradientPaintTransformer transformer) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((transformer == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[5]++;
            throw new IllegalArgumentException("Null 'transformer' argument.");

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[6]++;}
        this.fillPaintTransformer = transformer;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[17]++;
    }
    
    /**
     * Returns a flag that controls whether the shape outline is visible.
     * 
     * @return A boolean.
     * 
     * @see #setShapeOutlineVisible(boolean)
     */
    public boolean isShapeOutlineVisible() {
        return this.shapeOutlineVisible;
    }
    
    /**
     * Sets a flag that controls whether or not the shape outline
     * is visible.
     * 
     * @param visible  the flag.
     * 
     * @see #isShapeOutlineVisible()
     */
    public void setShapeOutlineVisible(boolean visible) {
        this.shapeOutlineVisible = visible;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[18]++;
    }
    
    /**
     * Returns the outline paint.
     * 
     * @return The paint.
     * 
     * @see #setOutlinePaint(Paint)
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    /**
     * Sets the outline paint.
     * 
     * @param paint  the paint.
     * 
     * @see #getOutlinePaint()
     */
    public void setOutlinePaint(Paint paint) {
        this.outlinePaint = paint;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[19]++;
    }

    /**
     * Returns the outline stroke.
     * 
     * @return The stroke.
     * 
     * @see #setOutlineStroke(Stroke)
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    /**
     * Sets the outline stroke.
     * 
     * @param stroke  the stroke.
     * 
     * @see #getOutlineStroke()
     */
    public void setOutlineStroke(Stroke stroke) {
        this.outlineStroke = stroke;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[20]++;
    }

    /**
     * Returns the shape anchor.
     * 
     * @return The shape anchor.
     * 
     * @see #getShapeAnchor()
     */
    public RectangleAnchor getShapeAnchor() {
        return this.shapeAnchor;
    }
    
    /**
     * Sets the shape anchor.  This defines a point on the shapes bounding
     * rectangle that will be used to align the shape to a location.
     * 
     * @param anchor  the anchor (<code>null</code> not permitted).
     * 
     * @see #setShapeAnchor(RectangleAnchor)
     */
    public void setShapeAnchor(RectangleAnchor anchor) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[7]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[8]++;}
        this.shapeAnchor = anchor;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[22]++;    
    }
    
    /**
     * Returns the shape location.
     * 
     * @return The shape location.
     * 
     * @see #setShapeLocation(RectangleAnchor)
     */
    public RectangleAnchor getShapeLocation() {
        return this.shapeLocation;
    }
    
    /**
     * Sets the shape location.  This defines a point within the drawing
     * area that will be used to align the shape to.
     * 
     * @param location  the location (<code>null</code> not permitted).
     * 
     * @see #getShapeLocation()
     */
    public void setShapeLocation(RectangleAnchor location) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[9]++;
            throw new IllegalArgumentException("Null 'location' argument.");

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[10]++;}
        this.shapeLocation = location;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[24]++;
    }
    
    /**
     * Returns the flag that controls whether or not the line is visible.
     * 
     * @return A boolean.
     * 
     * @see #setLineVisible(boolean)
     */
    public boolean isLineVisible() {
        return this.lineVisible;
    }
    
    /**
     * Sets the flag that controls whether or not the line is visible.
     * 
     * @param visible  the flag.
     * 
     * @see #isLineVisible()
     */
    public void setLineVisible(boolean visible) {
        this.lineVisible = visible;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[25]++;
    }

    /**
     * Returns the line centered about (0, 0).
     * 
     * @return The line.
     * 
     * @see #setLine(Shape)
     */
    public Shape getLine() {
        return this.line;
    }
    
    /**
     * Sets the line.  A Shape is used here, because then you can use Line2D, 
     * GeneralPath or any other Shape to represent the line.
     * 
     * @param line  the line.
     * 
     * @see #getLine()
     */
    public void setLine(Shape line) {
        this.line = line;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[26]++;
    }
    
    /**
     * Returns the line paint.
     * 
     * @return The paint.
     * 
     * @see #setLinePaint(Paint)
     */
    public Paint getLinePaint() {
        return this.linePaint;
    }
    
    /**
     * Sets the line paint.
     * 
     * @param paint  the paint.
     * 
     * @see #getLinePaint()
     */
    public void setLinePaint(Paint paint) {
        this.linePaint = paint;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[27]++;
    }
    
    /**
     * Returns the line stroke.
     * 
     * @return The stroke.
     * 
     * @see #setLineStroke(Stroke)
     */
    public Stroke getLineStroke() {
        return this.lineStroke;
    }
    
    /**
     * Sets the line stroke.
     * 
     * @param stroke  the stroke.
     * 
     * @see #getLineStroke()
     */
    public void setLineStroke(Stroke stroke) {
        this.lineStroke = stroke;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[28]++;
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
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[29]++;
        RectangleConstraint contentConstraint = toContentConstraint(constraint);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[30]++;
        LengthConstraintType w = contentConstraint.getWidthConstraintType();
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[31]++;
        LengthConstraintType h = contentConstraint.getHeightConstraintType();
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[32]++;
        Size2D contentSize = null;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[11]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[13]++;
                contentSize = arrangeNN(g2);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[35]++;

            }
            else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[14]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[36]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[15]++;
                throw new RuntimeException("Not yet implemented.");

            }
            else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[16]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[37]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[17]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[18]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[12]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[38]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[19]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[39]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[21]++;
                throw new RuntimeException("Not yet implemented.");

            }
            else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[22]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[40]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[23]++;
                throw new RuntimeException("Not yet implemented.");

            }
            else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[24]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[41]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[25]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[26]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[20]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[42]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[27]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[43]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[29]++;
                throw new RuntimeException("Not yet implemented.");

            }
            else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[30]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[44]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[31]++;
                throw new RuntimeException("Not yet implemented.");

            }
            else {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[32]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[45]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[33]++;   
                contentSize = new Size2D(
                    contentConstraint.getWidth(),
                    contentConstraint.getHeight()
                );
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[46]++;

            } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[34]++;}
}
}
            
        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[28]++;}
}
}
        return new Size2D(
            calculateTotalWidth(contentSize.getWidth()), 
            calculateTotalHeight(contentSize.getHeight())
        );
    }
    
    /**
     * Performs the layout with no constraint, so the content size is 
     * determined by the bounds of the shape and/or line drawn to represent 
     * the series.
     * 
     * @param g2  the graphics device.
     * 
     * @return  The content size.
     */
    protected Size2D arrangeNN(Graphics2D g2) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[47]++;
        Rectangle2D contentSize = new Rectangle2D.Double();
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.line != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[35]++;
            contentSize.setRect(this.line.getBounds2D());
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[49]++;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[36]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[50]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.shape != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[37]++;
            contentSize = contentSize.createUnion(this.shape.getBounds2D());
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[51]++;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[38]++;}
        return new Size2D(contentSize.getWidth(), contentSize.getHeight());
    }

    /**
     * Draws the graphic item within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        
        area = trimMargin(area);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[52]++;
        drawBorder(g2, area);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[53]++;
        area = trimBorder(area);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[54]++;
        area = trimPadding(area);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[55]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[56]++;
int CodeCoverConditionCoverageHelper_C20;
        
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.lineVisible) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[39]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[57]++;
            Point2D location = RectangleAnchor.coordinates(area, 
                    this.shapeLocation);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[58]++;
            Shape aLine = ShapeUtilities.createTranslatedShape(getLine(), 
                    this.shapeAnchor, location.getX(), location.getY());
            g2.setPaint(this.linePaint);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[59]++;
            g2.setStroke(this.lineStroke);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[60]++;
            g2.draw(aLine);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[61]++;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[40]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[62]++;
int CodeCoverConditionCoverageHelper_C21;
        
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.shapeVisible) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[41]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[63]++;
            Point2D location = RectangleAnchor.coordinates(area, 
                    this.shapeLocation);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[64]++;
            
            Shape s = ShapeUtilities.createTranslatedShape(this.shape, 
                    this.shapeAnchor, location.getX(), location.getY());
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[65]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.shapeFilled) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[43]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[66]++;
                Paint p = this.fillPaint;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[67]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((p instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[45]++;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[68]++;
                    GradientPaint gp = (GradientPaint) this.fillPaint;
                    p = this.fillPaintTransformer.transform(gp, s);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[69]++;

                } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[46]++;}
                g2.setPaint(p);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[70]++;
                g2.fill(s);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[71]++;

            } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[44]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[72]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.shapeOutlineVisible) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[47]++;
                g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[73]++;
                g2.setStroke(this.outlineStroke);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[74]++;
                g2.draw(s);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[75]++;

            } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[48]++;}

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[42]++;}
        
    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     * 
     * @return Always <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
        draw(g2, area);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[76]++;
        return null;
    }
    
    /**
     * Tests this <code>LegendGraphic</code> instance for equality with an
     * arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[77]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((obj instanceof LegendGraphic) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[50]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[78]++;
        LegendGraphic that = (LegendGraphic) obj;
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[79]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.shapeVisible != that.shapeVisible) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[52]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[80]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.shape, that.shape)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[54]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[81]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.shapeFilled != that.shapeFilled) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[56]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[82]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[58]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[83]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.fillPaintTransformer, 
                that.fillPaintTransformer)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[60]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[84]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.shapeOutlineVisible != that.shapeOutlineVisible) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[62]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[85]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[64]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[86]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[66]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[87]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.shapeAnchor != that.shapeAnchor) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[68]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[88]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.shapeLocation != that.shapeLocation) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[70]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[89]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.lineVisible != that.lineVisible) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[72]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[90]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.line, that.line)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[74]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[91]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.linePaint, that.linePaint)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[76]++;}
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[92]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.lineStroke, that.lineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.branches[78]++;}
        return super.equals(obj);    
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[93]++;
        int result = 193;   
        result = 37 * result + ObjectUtilities.hashCode(this.fillPaint);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[94]++;
        // FIXME: use other fields too
        return result;
    }
    
    /**
     * Returns a clone of this <code>LegendGraphic</code> instance.
     * 
     * @return A clone of this <code>LegendGraphic</code> instance.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[95]++;
        LegendGraphic clone = (LegendGraphic) super.clone();
        clone.shape = ShapeUtilities.clone(this.shape);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[96]++;
        clone.line = ShapeUtilities.clone(this.line);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[97]++;
        return clone;
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
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[98]++;
        SerialUtilities.writeShape(this.shape, stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[99]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[100]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[101]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[102]++;
        SerialUtilities.writeShape(this.line, stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[103]++;
        SerialUtilities.writePaint(this.linePaint, stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[104]++;
        SerialUtilities.writeStroke(this.lineStroke, stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[105]++;
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
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[106]++;
        this.shape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[107]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[108]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[109]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[110]++;
        this.line = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[111]++;
        this.linePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[112]++;
        this.lineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735.statements[113]++;
    }

}

class CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735 ());
  }
    public static long[] statements = new long[114];
    public static long[] branches = new long[79];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[40];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.LegendGraphic.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 39; i++) {
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

  public CodeCoverCoverageCounter$6cpkhga300ngn1dkfizpszgbe735 () {
    super("org.jfree.chart.title.LegendGraphic.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 113; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 78; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.title.LegendGraphic.java");
      for (int i = 1; i <= 113; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 78; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 39; i++) {
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

