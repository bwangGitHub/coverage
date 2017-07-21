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
 * -----------------
 * ArcDialFrame.java
 * -----------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 08-Mar-2007 : Fix in hashCode() (DG);
 * 17-Oct-2007 : Updated equals() (DG);
 * 24-Oct-2007 : Added argument checks and API docs, and renamed 
 *               StandardDialFrame --> ArcDialFrame (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
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
 * A standard frame for the {@link DialPlot} class.
 * 
 * @since 1.0.7
 */
public class ArcDialFrame extends AbstractDialLayer implements DialFrame, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = -4089176959553523499L;
  static {
    CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[1]++;
  }

    /**
     * The color used for the front of the panel.  This field is transient
     * because it requires special handling for serialization.
     */
    private transient Paint backgroundPaint;
    
    /**
     * The color used for the border around the window. This field is transient
     * because it requires special handling for serialization.
     */
    private transient Paint foregroundPaint;
    
    /**
     * The stroke for drawing the frame outline.  This field is transient
     * because it requires special handling for serialization.
     */
    private transient Stroke stroke;
       
    /**
     * The start angle.
     */
    private double startAngle;
    
    /**
     * The end angle.
     */
    private double extent;
    
    /** The inner radius, relative to the framing rectangle. */
    private double innerRadius;
    
    /** The outer radius, relative to the framing rectangle. */
    private double outerRadius;
   
    /** 
     * Creates a new instance of <code>ArcDialFrame</code> that spans
     * 180 degrees. 
     */
    public ArcDialFrame() {
        this(0, 180);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[2]++;
    }
    
    /**
     * Creates a new instance of <code>ArcDialFrame</code> that spans
     * the arc specified.
     *
     * @param startAngle  the startAngle (in degrees).
     * @param extent  the extent of the arc (in degrees, counter-clockwise).
     */
    public ArcDialFrame(double startAngle, double extent) {
        this.backgroundPaint = Color.gray;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[3]++;
        this.foregroundPaint = new Color(100, 100, 150);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[4]++;
        this.stroke = new BasicStroke(2.0f);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[5]++;
        this.innerRadius = 0.25;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[6]++;
        this.outerRadius = 0.75;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[7]++;
        this.startAngle = startAngle;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[8]++;
        this.extent = extent;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[9]++;        
    }
    
    /**
     * Returns the background paint (never <code>null</code>).
     * 
     * @return The background paint.
     * 
     * @see #setBackgroundPaint(Paint)
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    /**
     * Sets the background paint and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBackgroundPaint()
     */
    public void setBackgroundPaint(Paint paint) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[2]++;}
        this.backgroundPaint = paint;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[11]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[12]++;
    }
    
    /**
     * Returns the foreground paint.
     * 
     * @return The foreground paint (never <code>null</code>).
     * 
     * @see #setForegroundPaint(Paint)
     */
    public Paint getForegroundPaint() {
        return this.foregroundPaint;
    }
    
    /**
     * Sets the foreground paint and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getForegroundPaint()
     */
    public void setForegroundPaint(Paint paint) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[4]++;}
        this.foregroundPaint = paint;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[14]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[15]++;
    }
    
    /**
     * Returns the stroke.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setStroke(Stroke)
     */
    public Stroke getStroke() {
        return this.stroke;
    }
    
    /**
     * Sets the stroke and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getStroke()
     */
    public void setStroke(Stroke stroke) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[5]++; 
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[6]++;}
        this.stroke = stroke;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[17]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[18]++;
    }

    /**
     * Returns the inner radius, relative to the framing rectangle.
     *
     * @return The inner radius. 
     * 
     * @see #setInnerRadius(double)
     */
    public double getInnerRadius() {
        return this.innerRadius;
    }
    
    /**
     * Sets the inner radius and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     *
     * @param radius  the inner radius.
     * 
     * @see #getInnerRadius()
     */
    public void setInnerRadius(double radius) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((radius < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[7]++;
            throw new IllegalArgumentException("Negative 'radius' argument.");

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[8]++;}
        this.innerRadius = radius;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[20]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[21]++;
    }
    
    /**
     * Returns the outer radius, relative to the framing rectangle.
     *
     * @return The outer radius.
     * 
     * @see #setOuterRadius(double)
     */
    public double getOuterRadius() {
        return this.outerRadius;
    }
    
    /**
     * Sets the outer radius and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     *
     * @param radius  the outer radius.
     * 
     * @see #getOuterRadius()
     */
    public void setOuterRadius(double radius) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((radius < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[9]++;
            throw new IllegalArgumentException("Negative 'radius' argument.");

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[10]++;}
        this.outerRadius = radius;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[23]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[24]++;
    }
    
    /**
     * Returns the start angle.
     * 
     * @return The start angle.
     * 
     * @see #setStartAngle(double)
     */
    public double getStartAngle() {
        return this.startAngle;
    }
    
    /**
     * Sets the start angle and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     * 
     * @param angle  the angle.
     * 
     * @see #getStartAngle()
     */
    public void setStartAngle(double angle) {
        this.startAngle = angle;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[25]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[26]++;
    }
    
    /**
     * Returns the extent.
     * 
     * @return The extent.
     * 
     * @see #setExtent(double)
     */
    public double getExtent() {
        return this.extent;
    }
    
    /**
     * Sets the extent and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     * 
     * @param extent  the extent.
     * 
     * @see #getExtent()
     */
    public void setExtent(double extent) {
        this.extent = extent;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[27]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[28]++;
    }
    
    /**
     * Returns the shape for the window for this dial.  Some dial layers will
     * request that their drawing be clipped within this window.
     *
     * @param frame  the reference frame (<code>null</code> not permitted).
     *
     * @return The shape of the dial's window.
     */
    public Shape getWindow(Rectangle2D frame) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[29]++;
        
        Rectangle2D innerFrame = DialPlot.rectangleByRadius(frame, 
                this.innerRadius, this.innerRadius);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[30]++;
        Rectangle2D outerFrame = DialPlot.rectangleByRadius(frame, 
                this.outerRadius, this.outerRadius);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[31]++;
        Arc2D inner = new Arc2D.Double(innerFrame, this.startAngle, 
                this.extent, Arc2D.OPEN);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[32]++;
        Arc2D outer = new Arc2D.Double(outerFrame, this.startAngle 
                + this.extent, -this.extent, Arc2D.OPEN);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[33]++;
        GeneralPath p = new GeneralPath();
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[34]++;
        Point2D point1 = inner.getStartPoint();
        p.moveTo((float) point1.getX(), (float) point1.getY());
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[35]++;
        p.append(inner, true);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[36]++;
        p.append(outer, true);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[37]++;
        p.closePath();
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[38]++;
        return p;
        
    }
    
    /**
     * Returns the outer window.
     * 
     * @param frame  the frame.
     * 
     * @return The outer window.
     */
    protected Shape getOuterWindow(Rectangle2D frame) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[39]++;
        double radiusMargin = 0.02;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[40]++;
        double angleMargin = 1.5;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[41]++;
        Rectangle2D innerFrame = DialPlot.rectangleByRadius(frame, 
                this.innerRadius - radiusMargin, this.innerRadius 
                - radiusMargin);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[42]++;
        Rectangle2D outerFrame = DialPlot.rectangleByRadius(frame, 
                this.outerRadius + radiusMargin, this.outerRadius 
                + radiusMargin);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[43]++;
        Arc2D inner = new Arc2D.Double(innerFrame, this.startAngle 
                - angleMargin, this.extent + 2 * angleMargin, Arc2D.OPEN);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[44]++;
        Arc2D outer = new Arc2D.Double(outerFrame, this.startAngle 
                + angleMargin + this.extent, -this.extent - 2 * angleMargin, 
                Arc2D.OPEN);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[45]++;
        GeneralPath p = new GeneralPath();
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[46]++;
        Point2D point1 = inner.getStartPoint();
        p.moveTo((float) point1.getX(), (float) point1.getY());
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[47]++;
        p.append(inner, true);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[48]++;
        p.append(outer, true);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[49]++;
        p.closePath();
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[50]++;
        return p;
    }
    
    /**
     * Draws the frame.
     * 
     * @param g2  the graphics target.
     * @param plot  the plot.
     * @param frame  the dial's reference frame.
     * @param view  the dial's view rectangle.
     */
    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
            Rectangle2D view) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[51]++;
        
        Shape window = getWindow(frame);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[52]++;
        Shape outerWindow = getOuterWindow(frame);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[53]++;

        Area area1 = new Area(outerWindow);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[54]++;
        Area area2 = new Area(window);
        area1.subtract(area2);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[55]++;
        g2.setPaint(Color.lightGray);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[56]++;
        g2.fill(area1);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[57]++;
        
        g2.setStroke(this.stroke);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[58]++;
        g2.setPaint(this.foregroundPaint);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[59]++;
        g2.draw(window);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[60]++;
        g2.draw(outerWindow);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[61]++;
        
        
    }

    /**
     * Returns <code>false</code> to indicate that this dial layer is not
     * clipped to the dial window.
     *
     * @return <code>false</code>.
     */
    public boolean isClippedToWindow() {
        return false;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[62]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[12]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[63]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof ArcDialFrame) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[14]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[64]++;
        ArcDialFrame that = (ArcDialFrame) obj;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[65]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[16]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[66]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.foregroundPaint, that.foregroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[18]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[67]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.startAngle != that.startAngle) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[20]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[68]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.extent != that.extent) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[22]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[69]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.innerRadius != that.innerRadius) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[24]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[70]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.outerRadius != that.outerRadius) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[26]++;}
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[71]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.stroke.equals(that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.branches[28]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[72]++;
        int result = 193;
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[73]++;
        long temp = Double.doubleToLongBits(this.startAngle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[74]++;
        temp = Double.doubleToLongBits(this.extent);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[75]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[76]++;
        temp = Double.doubleToLongBits(this.innerRadius);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[77]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[78]++;
        temp = Double.doubleToLongBits(this.outerRadius);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[79]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[80]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.backgroundPaint);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[81]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.foregroundPaint);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[82]++;
        result = 37 * result + this.stroke.hashCode();
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[83]++;
        return result;
    }
    
    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if any attribute of this instance
     *     cannot be cloned.
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
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[84]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[85]++;
        SerialUtilities.writePaint(this.foregroundPaint, stream);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[86]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[87]++;
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
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[88]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[89]++;
        this.foregroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[90]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9.statements[91]++;
    }
    
}

class CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9 ());
  }
    public static long[] statements = new long[92];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.ArcDialFrame.java";
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

  public CodeCoverCoverageCounter$rjwesmkflfuopqjzwzmkc7syu9 () {
    super("org.jfree.chart.plot.dial.ArcDialFrame.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 91; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.dial.ArcDialFrame.java");
      for (int i = 1; i <= 91; i++) {
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

