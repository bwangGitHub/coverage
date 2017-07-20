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
 * StandardDialFrame.java
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
 * 29-Oct-2007 : Renamed StandardDialFrame (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
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
 * A simple circular frame for the {@link DialPlot} class.
 * 
 * @since 1.0.7
 */
public class StandardDialFrame extends AbstractDialLayer implements DialFrame, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = 1016585407507121596L;
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[1]++;
  }
    
    /** The outer radius, relative to the framing rectangle. */
    private double radius;
    
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
     * Creates a new instance of <code>StandardDialFrame</code>.
     */
    public StandardDialFrame() {
        this.backgroundPaint = Color.gray;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[2]++;
        this.foregroundPaint = Color.black;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[3]++;
        this.stroke = new BasicStroke(2.0f);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[4]++;
        this.radius = 0.95;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[5]++;   
    }
    
    /**
     * Returns the radius, relative to the framing rectangle.
     *
     * @return The radius. 
     * 
     * @see #setRadius(double)
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * Sets the radius and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     *
     * @param radius  the radius (must be positive).
     * 
     * @see #getRadius()
     */
    public void setRadius(double radius) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((radius <= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[1]++; 
            throw new IllegalArgumentException(
                    "The 'radius' must be positive.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[2]++;}
        this.radius = radius;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[7]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[8]++;
    }

    /**
     * Returns the background paint.
     * 
     * @return The background paint (never <code>null</code>).
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[4]++;}
        this.backgroundPaint = paint;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[10]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[11]++;
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[5]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[6]++;}
        this.foregroundPaint = paint;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[13]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[14]++;
    }
    
    /**
     * Returns the stroke for the frame.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setStroke(Stroke)
     */
    public Stroke getStroke() {
        return this.stroke;
    }
    
    /**
     * Sets the stroke and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getStroke()
     */
    public void setStroke(Stroke stroke) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[7]++; 
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[8]++;}
        this.stroke = stroke;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[16]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[17]++;
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[18]++; 
        Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, 
                this.radius);
        return new Ellipse2D.Double(f.getX(), f.getY(), f.getWidth(), 
                f.getHeight());  
    }
     
    /**
     * Returns <code>false</code> to indicate that this dial layer is not
     * clipped to the dial window.
     *
     * @return A boolean.
     */
    public boolean isClippedToWindow() {
        return false;
    }
    
    /**
     * Draws the frame.  This method is called by the {@link DialPlot} class,
     * you shouldn't need to call it directly.
     *
     * @param g2  the graphics target (<code>null</code> not permitted).
     * @param plot  the plot (<code>null</code> not permitted).
     * @param frame  the frame (<code>null</code> not permitted).
     * @param view  the view (<code>null</code> not permitted).
     */
    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
            Rectangle2D view) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[19]++;
        
        Shape window = getWindow(frame);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[20]++;
        
        Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius + 0.02, 
                this.radius + 0.02);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[21]++;
        Ellipse2D e = new Ellipse2D.Double(f.getX(), f.getY(), f.getWidth(), 
                f.getHeight());
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[22]++;
        
        Area area = new Area(e);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[23]++;
        Area area2 = new Area(window);
        area.subtract(area2);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[24]++;
        g2.setPaint(this.backgroundPaint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[25]++;
        g2.fill(area);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[26]++;
        
        g2.setStroke(this.stroke);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[27]++;
        g2.setPaint(this.foregroundPaint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[28]++;
        g2.draw(window);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[29]++;
        g2.draw(e);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[30]++;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[10]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof StandardDialFrame) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[12]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[33]++;
        StandardDialFrame that = (StandardDialFrame) obj;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[14]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.foregroundPaint, that.foregroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[16]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.radius != that.radius) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[18]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.stroke.equals(that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.branches[20]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[38]++;
        int result = 193;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[39]++;
        long temp = Double.doubleToLongBits(this.radius);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[40]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.backgroundPaint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[41]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.foregroundPaint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[42]++;
        result = 37 * result + this.stroke.hashCode();
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[43]++;
        return result;
    }
    
    /**
     * Returns a clone of this instance.
     *
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if any of the frame's attributes 
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[44]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[45]++;
        SerialUtilities.writePaint(this.foregroundPaint, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[46]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[47]++;
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[48]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[49]++;
        this.foregroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[50]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5.statements[51]++;
    }
    
}

class CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5 ());
  }
    public static long[] statements = new long[52];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.StandardDialFrame.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6lxy1203ksyefmn5 () {
    super("org.jfree.chart.plot.dial.StandardDialFrame.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 51; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.StandardDialFrame.java");
      for (int i = 1; i <= 51; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

