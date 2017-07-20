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
 * ---------------------
 * PaintScaleLegend.java
 * ---------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Jan-2007 : Version 1 (DG);
 * 
 */

package org.jfree.chart.title;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.Size2D;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A legend that shows a range of values and their associated colors, driven
 * by an underlying {@link PaintScale} implementation.
 * 
 * @since 1.0.4
 */
public class PaintScaleLegend extends Title implements PublicCloneable {
  static {
    CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -1365146490993227503L;
  static {
    CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[1]++;
  }
    
    /** The paint scale (never <code>null</code>). */
    private PaintScale scale;
    
    /** The value axis (never <code>null</code>). */
    private ValueAxis axis;
    
    /** 
     * The axis location (handles both orientations, never 
     * <code>null</code>). 
     */
    private AxisLocation axisLocation;

    /** The offset between the axis and the paint strip (in Java2D units). */
    private double axisOffset;
    
    /** The thickness of the paint strip (in Java2D units). */
    private double stripWidth;
   
    /** 
     * A flag that controls whether or not an outline is drawn around the
     * paint strip.
     */
    private boolean stripOutlineVisible;
    
    /** The paint used to draw an outline around the paint strip. */
    private transient Paint stripOutlinePaint;
    
    /** The stroke used to draw an outline around the paint strip. */
    private transient Stroke stripOutlineStroke;
    
    /** The background paint (never <code>null</code>). */
    private transient Paint backgroundPaint;
    
    /**
     * Creates a new instance.
     * 
     * @param scale  the scale (<code>null</code> not permitted).
     * @param axis  the axis (<code>null</code> not permitted).
     */
    public PaintScaleLegend(PaintScale scale, ValueAxis axis) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[1]++;
            throw new IllegalArgumentException("Null 'axis' argument.");

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[2]++;}
        this.scale = scale;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[3]++;
        this.axis = axis;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[4]++;
        this.axisLocation = AxisLocation.BOTTOM_OR_LEFT;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[5]++;
        this.axisOffset = 0.0;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[6]++;
        this.stripWidth = 15.0;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[7]++;
        this.stripOutlineVisible = false;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[8]++;
        this.stripOutlinePaint = Color.gray;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[9]++;
        this.stripOutlineStroke = new BasicStroke(0.5f);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[10]++;
        this.backgroundPaint = Color.white;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[11]++;
    }
    
    /**
     * Returns the scale used to convert values to colors.
     * 
     * @return The scale (never <code>null</code>).
     * 
     * @see #setScale(PaintScale)
     */
    public PaintScale getScale() {
        return this.scale;    
    }
    
    /**
     * Sets the scale and sends a {@link TitleChangeEvent} to all registered
     * listeners.
     * 
     * @param scale  the scale (<code>null</code> not permitted).
     * 
     * @see #getScale()
     */
    public void setScale(PaintScale scale) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((scale == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[3]++;
            throw new IllegalArgumentException("Null 'scale' argument.");

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[4]++;}
        this.scale = scale;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[13]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[14]++;
    }
    
    /**
     * Returns the axis for the paint scale.
     * 
     * @return The axis (never <code>null</code>).
     * 
     * @see #setAxis(ValueAxis)
     */
    public ValueAxis getAxis() {
        return this.axis;
    }
    
    /**
     * Sets the axis for the paint scale and sends a {@link TitleChangeEvent}
     * to all registered listeners.
     * 
     * @param axis  the axis (<code>null</code> not permitted).
     * 
     * @see #getAxis()
     */
    public void setAxis(ValueAxis axis) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[5]++;
            throw new IllegalArgumentException("Null 'axis' argument.");

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[6]++;}
        this.axis = axis;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[16]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[17]++;
    }
    
    /**
     * Returns the axis location.
     * 
     * @return The axis location (never <code>null</code>).
     * 
     * @see #setAxisLocation(AxisLocation)
     */
    public AxisLocation getAxisLocation() {
        return this.axisLocation;
    }
    
    /**
     * Sets the axis location and sends a {@link TitleChangeEvent} to all 
     * registered listeners.
     * 
     * @param location  the location (<code>null</code> not permitted).
     * 
     * @see #getAxisLocation()
     */
    public void setAxisLocation(AxisLocation location) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[7]++;
            throw new IllegalArgumentException("Null 'location' argument.");

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[8]++;}
        this.axisLocation = location;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[19]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[20]++;
    }
    
    /**
     * Returns the offset between the axis and the paint strip.
     * 
     * @return The offset between the axis and the paint strip.
     * 
     * @see #setAxisOffset(double)
     */
    public double getAxisOffset() {
        return this.axisOffset;
    }
    
    /**
     * Sets the offset between the axis and the paint strip and sends a 
     * {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param offset  the offset.
     */
    public void setAxisOffset(double offset) {
        this.axisOffset = offset;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[21]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[22]++;
    }
    
    /**
     * Returns the width of the paint strip, in Java2D units.
     * 
     * @return The width of the paint strip.
     * 
     * @see #setStripWidth(double)
     */
    public double getStripWidth() {
        return this.stripWidth;
    }
    
    /**
     * Sets the width of the paint strip and sends a {@link TitleChangeEvent}
     * to all registered listeners.
     * 
     * @param width  the width.
     * 
     * @see #getStripWidth()
     */
    public void setStripWidth(double width) {
        this.stripWidth = width;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[23]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[24]++;
    }
    
    /**
     * Returns the flag that controls whether or not an outline is drawn 
     * around the paint strip.
     * 
     * @return A boolean.
     * 
     * @see #setStripOutlineVisible(boolean)
     */
    public boolean isStripOutlineVisible() {
        return this.stripOutlineVisible;
    }
    
    /**
     * Sets the flag that controls whether or not an outline is drawn around
     * the paint strip, and sends a {@link TitleChangeEvent} to all registered
     * listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #isStripOutlineVisible()
     */
    public void setStripOutlineVisible(boolean visible) {
        this.stripOutlineVisible = visible;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[25]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[26]++;
    }
    
    /**
     * Returns the paint used to draw the outline of the paint strip.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setStripOutlinePaint(Paint)
     */
    public Paint getStripOutlinePaint() {
        return this.stripOutlinePaint;
    }
    
    /**
     * Sets the paint used to draw the outline of the paint strip, and sends
     * a {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getStripOutlinePaint()
     */
    public void setStripOutlinePaint(Paint paint) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[9]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[10]++;}
        this.stripOutlinePaint = paint;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[28]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[29]++;
    }
    
    /**
     * Returns the stroke used to draw the outline around the paint strip.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setStripOutlineStroke(Stroke)
     */
    public Stroke getStripOutlineStroke() {
        return this.stripOutlineStroke;
    }
    
    /**
     * Sets the stroke used to draw the outline around the paint strip and 
     * sends a {@link TitleChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getStripOutlineStroke()
     */
    public void setStripOutlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[11]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[12]++;}
        this.stripOutlineStroke = stroke;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[31]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[32]++;
    }
    
    /**
     * Returns the background paint.
     * 
     * @return The background paint.
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    /**
     * Sets the background paint and sends a {@link TitleChangeEvent} to all
     * registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     */
    public void setBackgroundPaint(Paint paint) {
        this.backgroundPaint = paint;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[33]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[34]++;
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
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[35]++;
        RectangleConstraint cc = toContentConstraint(constraint);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[36]++;
        LengthConstraintType w = cc.getWidthConstraintType();
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[37]++;
        LengthConstraintType h = cc.getHeightConstraintType();
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[38]++;
        Size2D contentSize = null;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[13]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[15]++;
                contentSize = new Size2D(getWidth(), getHeight());
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[41]++;
 
            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[16]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[42]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[17]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[18]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[43]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[19]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[20]++;}
}
}
            
        }
        else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[14]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[44]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[21]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[23]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[24]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[46]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[25]++;
                contentSize = arrangeRR(g2, cc.getWidthRange(), 
                        cc.getHeightRange());
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[47]++;
 
            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[26]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[48]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[27]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[28]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[22]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[49]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[29]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[50]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[31]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[32]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[51]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[33]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[34]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[52]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[35]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[36]++;}
}
}

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[30]++;}
}
}
        return new Size2D(calculateTotalWidth(contentSize.getWidth()),
                calculateTotalHeight(contentSize.getHeight()));
    }
    
    /**
     * Returns the content size for the title.  This will reflect the fact that
     * a text title positioned on the left or right of a chart will be rotated
     * 90 degrees.
     * 
     * @param g2  the graphics device.
     * @param widthRange  the width range.
     * @param heightRange  the height range.
     * 
     * @return The content size.
     */
    protected Size2D arrangeRR(Graphics2D g2, Range widthRange, 
            Range heightRange) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[53]++;
        
        RectangleEdge position = getPosition();
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[54]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[37]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[55]++;
            
            
            float maxWidth = (float) widthRange.getUpperBound();
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[56]++;
            
            // determine the space required for the axis
            AxisSpace space = this.axis.reserveSpace(g2, null, 
                    new Rectangle2D.Double(0, 0, maxWidth, 100), 
                    RectangleEdge.BOTTOM, null);
            
            return new Size2D(maxWidth, this.stripWidth + this.axisOffset 
                    + space.getTop() + space.getBottom());

        }
        else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[38]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[57]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((position 
                == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[39]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[58]++;
            float maxHeight = (float) heightRange.getUpperBound();
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[59]++;
            AxisSpace space = this.axis.reserveSpace(g2, null, 
                    new Rectangle2D.Double(0, 0, 100, maxHeight), 
                    RectangleEdge.RIGHT, null);
            return new Size2D(this.stripWidth + this.axisOffset 
                    + space.getLeft() + space.getRight(), maxHeight);

        }
        else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[40]++;
            throw new RuntimeException("Unrecognised position.");
        }
}
    }

    /**
     * Draws the legend within the specified area.
     * 
     * @param g2  the graphics target (<code>null</code> not permitted).
     * @param area  the drawing area (<code>null</code> not permitted).
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        draw(g2, area, null);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[60]++;
    }

    /** 
     * The number of subdivisions to use when drawing the paint strip.  Maybe
     * this need to be user controllable? 
     */
    private static final int SUBDIVISIONS = 200;
  static {
    CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[61]++;
  }
    
    /**
     * Draws the legend within the specified area.
     * 
     * @param g2  the graphics target (<code>null</code> not permitted).
     * @param area  the drawing area (<code>null</code> not permitted).
     * @param params  drawing parameters (ignored here).
     * 
     * @return <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[62]++;
        
        Rectangle2D target = (Rectangle2D) area.clone();
        target = trimMargin(target);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[63]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[64]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[41]++;
            g2.setPaint(this.backgroundPaint);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[65]++;
            g2.fill(target);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[66]++;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[42]++;}
        getBorder().draw(g2, target);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[67]++;
        getBorder().getInsets().trim(target);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[68]++;
        target = trimPadding(target);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[69]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[70]++;
        double base = this.axis.getLowerBound();
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[71]++;
        double increment = this.axis.getRange().getLength() / SUBDIVISIONS;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[72]++;
        Rectangle2D r = new Rectangle2D.Double();
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[73]++;
int CodeCoverConditionCoverageHelper_C22;
        
        
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(getPosition())) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[43]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[74]++;
            RectangleEdge axisEdge = Plot.resolveRangeAxisLocation(
                    this.axisLocation, PlotOrientation.HORIZONTAL);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[75]++;
            double ww = Math.ceil(target.getWidth() / SUBDIVISIONS);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[76]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((axisEdge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[45]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[77]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[1]++;


int CodeCoverConditionCoverageHelper_C24;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < SUBDIVISIONS) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[1]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[2]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[3]++;
}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[78]++;
                    double v = base + (i * increment);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[79]++;
                    Paint p = this.scale.getPaint(v);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[80]++;
                    double vv = this.axis.valueToJava2D(v, target, 
                            RectangleEdge.BOTTOM);
                    r.setRect(vv, target.getMaxY() - this.stripWidth, ww, 
                            this.stripWidth);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[81]++;
                    g2.setPaint(p);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[82]++;
                    g2.fill(r);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[83]++;                  
                }
                g2.setPaint(this.stripOutlinePaint);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[84]++;
                g2.setStroke(this.stripOutlineStroke);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[85]++;
                g2.draw(new Rectangle2D.Double(target.getMinX(), 
                        target.getMaxY() - this.stripWidth, target.getWidth(), 
                        this.stripWidth));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[86]++;
                this.axis.draw(g2, target.getMaxY() - this.stripWidth 
                        - this.axisOffset, target, target, RectangleEdge.TOP, 
                        null);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[87]++;
                
            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[46]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[88]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((axisEdge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[47]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[89]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[4]++;


int CodeCoverConditionCoverageHelper_C26;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < SUBDIVISIONS) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[4]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[5]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[6]++;
}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[90]++;
                    double v = base + (i * increment);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[91]++;
                    Paint p = this.scale.getPaint(v);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[92]++;
                    double vv = this.axis.valueToJava2D(v, target, 
                            RectangleEdge.BOTTOM);
                    r.setRect(vv, target.getMinY(), ww, this.stripWidth);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[93]++;
                    g2.setPaint(p);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[94]++;
                    g2.fill(r);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[95]++;
                }
                g2.setPaint(this.stripOutlinePaint);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[96]++;
                g2.setStroke(this.stripOutlineStroke);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[97]++;
                g2.draw(new Rectangle2D.Double(target.getMinX(), 
                        target.getMinY(), target.getWidth(), this.stripWidth));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[98]++;
                this.axis.draw(g2, target.getMinY() + this.stripWidth 
                        + this.axisOffset, target, target, 
                        RectangleEdge.BOTTOM, null);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[99]++;
                
            } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[48]++;}
}

        }
        else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[44]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[100]++;
            RectangleEdge axisEdge = Plot.resolveRangeAxisLocation(
                    this.axisLocation, PlotOrientation.VERTICAL);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[101]++;
            double hh = Math.ceil(target.getHeight() / SUBDIVISIONS);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[102]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((axisEdge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[49]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[103]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[7]++;


int CodeCoverConditionCoverageHelper_C28;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i < SUBDIVISIONS) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[7]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[8]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[9]++;
}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[104]++;
                    double v = base + (i * increment);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[105]++;
                    Paint p = this.scale.getPaint(v);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[106]++;
                    double vv = this.axis.valueToJava2D(v, target, 
                            RectangleEdge.LEFT);
                    r.setRect(target.getMaxX() - this.stripWidth, vv - hh, 
                            this.stripWidth, hh);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[107]++;
                    g2.setPaint(p);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[108]++;
                    g2.fill(r);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[109]++;
                }
                g2.setPaint(this.stripOutlinePaint);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[110]++;
                g2.setStroke(this.stripOutlineStroke);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[111]++;
                g2.draw(new Rectangle2D.Double(target.getMaxX() 
                        - this.stripWidth, target.getMinY(), this.stripWidth, 
                        target.getHeight()));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[112]++;
                this.axis.draw(g2, target.getMaxX() - this.stripWidth 
                        - this.axisOffset, target, target, RectangleEdge.LEFT, 
                        null);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[113]++;

            }
            else {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[50]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[114]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((axisEdge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[51]++;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[115]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[10]++;


int CodeCoverConditionCoverageHelper_C30;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i < SUBDIVISIONS) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[10]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[11]--;
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.loops[12]++;
}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[116]++;
                    double v = base + (i * increment);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[117]++;
                    Paint p = this.scale.getPaint(v);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[118]++;
                    double vv = this.axis.valueToJava2D(v, target, 
                            RectangleEdge.LEFT);
                    r.setRect(target.getMinX(), vv - hh, this.stripWidth, hh);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[119]++;
                    g2.setPaint(p);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[120]++;
                    g2.fill(r);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[121]++;
                }
                g2.setPaint(this.stripOutlinePaint);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[122]++;
                g2.setStroke(this.stripOutlineStroke);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[123]++;
                g2.draw(new Rectangle2D.Double(target.getMinX(), 
                        target.getMinY(), this.stripWidth, target.getHeight()));
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[124]++;
                this.axis.draw(g2, target.getMinX() + this.stripWidth 
                        + this.axisOffset, target, target, RectangleEdge.RIGHT,
                        null);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[125]++;
                
            } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[52]++;}
}
        }
        return null;
    }
    
    /**
     * Tests this legend for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[126]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((obj instanceof PaintScaleLegend) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[54]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[127]++;
        PaintScaleLegend that = (PaintScaleLegend) obj;
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[128]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.scale.equals(that.scale)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[56]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[129]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((this.axis.equals(that.axis)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[58]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[130]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.axisLocation.equals(that.axisLocation)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[60]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[131]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.axisOffset != that.axisOffset) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[62]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[132]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.stripWidth != that.stripWidth) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[64]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[133]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.stripOutlineVisible != that.stripOutlineVisible) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[66]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[134]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.stripOutlinePaint, 
                that.stripOutlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[68]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[135]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.stripOutlineStroke.equals(that.stripOutlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[70]++;}
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[136]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.branches[72]++;}
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
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[137]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[138]++;
        SerialUtilities.writePaint(this.stripOutlinePaint, stream);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[139]++;
        SerialUtilities.writeStroke(this.stripOutlineStroke, stream);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[140]++;
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
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[141]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[142]++;
        this.stripOutlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[143]++;
        this.stripOutlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh.statements[144]++;
    }

}

class CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh ());
  }
    public static long[] statements = new long[145];
    public static long[] branches = new long[73];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[41];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.PaintScaleLegend.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 40; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$1uro5xi4i9n4713e4kcuvwb8ssngd6gkh () {
    super("org.jfree.chart.title.PaintScaleLegend.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 144; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 72; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.title.PaintScaleLegend.java");
      for (int i = 1; i <= 144; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 72; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

