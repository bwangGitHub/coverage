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
 * ----------------------
 * StandardDialRange.java
 * ----------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 08-Mar-2007 : Fix in hashCode() (DG);
 * 17-Oct-2007 : Removed increment attribute (DG);
 * 24-Oct-2007 : Added scaleIndex (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A layer that draws a range highlight on a dial plot.
 * 
 * @since 1.0.7
 */
public class StandardDialRange extends AbstractDialLayer implements DialLayer, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = 345515648249364904L;
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[1]++;
  }

    /** The scale index. */
    private int scaleIndex;
    
    /** The minimum data value for the scale. */
    private double lowerBound;
    
    /** The maximum data value for the scale. */
    private double upperBound;

    /**
     * The paint used to draw the range highlight.  This field is transient
     * because it requires special handling for serialization.
     */
    private transient Paint paint;
    
    /** 
     * The factor (in the range 0.0 to 1.0) that determines the inside limit
     * of the range highlight.
     */
    private double innerRadius;

    /**
     * The factor (in the range 0.0 to 1.0) that determines the outside limit 
     * of the range highlight.
     */
    private double outerRadius;
    
    /** 
     * Creates a new instance of <code>StandardDialRange</code>.
     */
    public StandardDialRange() {
        this(0.0, 100.0, Color.white);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[2]++;
    }
    
    /** 
     * Creates a new instance of <code>StandardDialRange</code>.
     *
     * @param lower  the lower bound.
     * @param upper  the upper bound.
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public StandardDialRange(double lower, double upper, Paint paint) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[2]++;}
        this.scaleIndex = 0;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[4]++;
        this.lowerBound = lower;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[5]++;
        this.upperBound = upper;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[6]++;
        this.innerRadius = 0.48;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[7]++;
        this.outerRadius = 0.52;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[8]++;
        this.paint = paint;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[9]++;
    }
    
    /**
     * Returns the scale index.
     * 
     * @return The scale index.
     * 
     * @see #setScaleIndex(int)
     */
    public int getScaleIndex() {
        return this.scaleIndex;
    }
    
    /**
     * Sets the scale index and sends a {@link DialLayerChangeEvent} to all
     * registered listeners.
     * 
     * @param index  the scale index.
     * 
     * @see #getScaleIndex()
     */
    public void setScaleIndex(int index) {
        this.scaleIndex = index;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[10]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[11]++;
    }
    
    /**
     * Returns the lower bound (a data value) of the dial range.
     * 
     * @return The lower bound of the dial range.
     * 
     * @see #setLowerBound(double)
     */
    public double getLowerBound() {
        return this.lowerBound;
    }
    
    /**
     * Sets the lower bound of the dial range and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param bound  the lower bound.
     * 
     * @see #getLowerBound()
     */
    public void setLowerBound(double bound) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((bound >= this.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[3]++;
            throw new IllegalArgumentException(
                    "Lower bound must be less than upper bound.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[4]++;}
        this.lowerBound = bound;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[13]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[14]++;
    }
    
    /**
     * Returns the upper bound of the dial range.
     * 
     * @return The upper bound.
     * 
     * @see #setUpperBound(double)
     */
    public double getUpperBound() {
        return this.upperBound;
    }
    
    /**
     * Sets the upper bound of the dial range and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param bound  the upper bound.
     * 
     * @see #getUpperBound()
     */
    public void setUpperBound(double bound) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((bound <= this.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[5]++;
            throw new IllegalArgumentException(
                    "Lower bound must be less than upper bound.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[6]++;}
        this.upperBound = bound;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[16]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[17]++;
    }
    
    /**
     * Sets the bounds for the range and sends a {@link DialLayerChangeEvent} 
     * to all registered listeners.
     * 
     * @param lower  the lower bound.
     * @param upper  the upper bound.
     */
    public void setBounds(double lower, double upper) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lower >= upper) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[7]++;
            throw new IllegalArgumentException(
                    "Lower must be less than upper.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[8]++;}
        this.lowerBound = lower;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[19]++;
        this.upperBound = upper;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[20]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[21]++;
    }
        
    /**
     * Returns the paint used to highlight the range.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Sets the paint used to highlight the range and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[9]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[10]++;}
        this.paint = paint;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[23]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[24]++;
    }
    
    /**
     * Returns the inner radius.
     * 
     * @return The inner radius.
     * 
     * @see #setInnerRadius(double)
     */
    public double getInnerRadius() {
        return this.innerRadius;
    }
    
    /**
     * Sets the inner radius and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param radius  the radius.
     * 
     * @see #getInnerRadius()
     */
    public void setInnerRadius(double radius) {
        this.innerRadius = radius;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[25]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[26]++;
    }
    
    /**
     * Returns the outer radius.
     * 
     * @return The outer radius.
     * 
     * @see #setOuterRadius(double)
     */
    public double getOuterRadius() {
        return this.outerRadius;
    }
    
    /**
     * Sets the outer radius and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param radius  the radius.
     * 
     * @see #getOuterRadius()
     */
    public void setOuterRadius(double radius) {
        this.outerRadius = radius;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[27]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[28]++;
    }
    
    /**
     * Returns <code>true</code> to indicate that this layer should be 
     * clipped within the dial window. 
     * 
     * @return <code>true</code>.
     */
    public boolean isClippedToWindow() {
        return true;
    }
    
    /**
     * Draws the range.
     * 
     * @param g2  the graphics target.
     * @param plot  the plot.
     * @param frame  the dial's reference frame (in Java2D space).
     * @param view  the dial's view rectangle (in Java2D space).
     */
    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
            Rectangle2D view) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[29]++;
        
        Rectangle2D arcRectInner = DialPlot.rectangleByRadius(frame, 
                this.innerRadius, this.innerRadius);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[30]++;
        Rectangle2D arcRectOuter = DialPlot.rectangleByRadius(frame, 
                this.outerRadius, this.outerRadius);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[31]++;
        
        DialScale scale = plot.getScale(this.scaleIndex);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((scale == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[11]++;
            throw new RuntimeException("No scale for scaleIndex = " 
                    + this.scaleIndex);

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[12]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[33]++;
        double angleMin = scale.valueToAngle(this.lowerBound);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[34]++;
        double angleMax = scale.valueToAngle(this.upperBound);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[35]++;

        Arc2D arcInner = new Arc2D.Double(arcRectInner, angleMin, 
                angleMax - angleMin, Arc2D.OPEN);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[36]++;
        Arc2D arcOuter = new Arc2D.Double(arcRectOuter, angleMax, 
                angleMin - angleMax, Arc2D.OPEN);
        
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[37]++;
        g2.setStroke(new BasicStroke(2.0f));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[38]++;
        g2.draw(arcInner);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[39]++;
        g2.draw(arcOuter);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[40]++;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[14]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof StandardDialRange) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[16]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[43]++;
        StandardDialRange that = (StandardDialRange) obj;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.scaleIndex != that.scaleIndex) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[18]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.lowerBound != that.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[20]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.upperBound != that.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[22]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[24]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.innerRadius != that.innerRadius) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[26]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[49]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.outerRadius != that.outerRadius) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.branches[28]++;}
        return super.equals(obj); 
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[50]++;
        int result = 193;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[51]++;     
        long temp = Double.doubleToLongBits(this.lowerBound);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[52]++;        
        temp = Double.doubleToLongBits(this.upperBound);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[53]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[54]++;        
        temp = Double.doubleToLongBits(this.innerRadius);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[55]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[56]++;        
        temp = Double.doubleToLongBits(this.outerRadius);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[57]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[58]++;        
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[59]++;
        return result;
    }
    
    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if any of the attributes of this 
     *     instance cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[60]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[61]++;
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[62]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap.statements[63]++;
    }

}

class CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap ());
  }
    public static long[] statements = new long[64];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.StandardDialRange.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 14; i++) {
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

  public CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6v4bhj8fm6r17yap () {
    super("org.jfree.chart.plot.dial.StandardDialRange.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 63; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.StandardDialRange.java");
      for (int i = 1; i <= 63; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 14; i++) {
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

