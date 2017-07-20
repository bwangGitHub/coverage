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
 * ----------------
 * DialPointer.java
 * ----------------
 * (C) Copyright 2006-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 17-Oct-2007 : Added equals() overrides (DG);
 * 24-Oct-2007 : Implemented PublicCloneable, changed default radius,
 *               and added argument checks (DG);
 * 23-Nov-2007 : Added fillPaint and outlinePaint attributes to 
 *               DialPointer.Pointer (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
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
 * A base class for the pointer in a {@link DialPlot}.
 * 
 * @since 1.0.7
 */
public abstract class DialPointer extends AbstractDialLayer 
        implements DialLayer, Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.ping();
  }

    
    /** The needle radius. */
    double radius;
    
    /**
     * The dataset index for the needle.
     */
    int datasetIndex;
    
    /** 
     * Creates a new <code>DialPointer</code> instance.
     */
    protected DialPointer() {
        this(0);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[1]++;
    }
    
    /**
     * Creates a new pointer for the specified dataset.
     * 
     * @param datasetIndex  the dataset index.
     */
    protected DialPointer(int datasetIndex) {
        this.radius = 0.9;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[2]++;
        this.datasetIndex = datasetIndex;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[3]++;
    }
    
    /**
     * Returns the dataset index that the pointer maps to.
     * 
     * @return The dataset index.
     * 
     * @see #getDatasetIndex()
     */
    public int getDatasetIndex() {
        return this.datasetIndex;
    }
    
    /**
     * Sets the dataset index for the pointer and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param index  the index.
     * 
     * @see #getDatasetIndex()
     */
    public void setDatasetIndex(int index) {
        this.datasetIndex = index;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[4]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[5]++;
    }
    
    /**
     * Returns the radius of the pointer, as a percentage of the dial's
     * framing rectangle.
     * 
     * @return The radius.
     * 
     * @see #setRadius(double)
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * Sets the radius of the pointer and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param radius  the radius.
     * 
     * @see #getRadius()
     */
    public void setRadius(double radius) {
        this.radius = radius;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[6]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[7]++;
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
     * Checks this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> not permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[2]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof DialPointer) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[4]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[10]++;
        DialPointer that = (DialPointer) obj;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.datasetIndex != that.datasetIndex) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[6]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.radius != that.radius) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[8]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[13]++;
        int result = 23;
        result = HashUtilities.hashCode(result, this.radius);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[14]++;
        return result;
    }
    
    /**
     * Returns a clone of the pointer.
     * 
     * @return a clone.
     * 
     * @throws CloneNotSupportedException if one of the attributes cannot
     *     be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * A dial pointer that draws a thin line (like a pin).
     */
    public static class Pin extends DialPointer {
    
        /** For serialization. */
        static final long serialVersionUID = -8445860485367689750L;

        /** The paint. */
        private transient Paint paint;
    
        /** The stroke. */
        private transient Stroke stroke;
        
        /**
         * Creates a new instance.
         */
        public Pin() {
            this(0);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[15]++;
        }
        
        /**
         * Creates a new instance.
         * 
         * @param datasetIndex  the dataset index.
         */
        public Pin(int datasetIndex) {
            super(datasetIndex);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[16]++;
            this.paint = Color.red;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[17]++;
            this.stroke = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, 
                    BasicStroke.JOIN_BEVEL);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[18]++;
        }
        
        /**
         * Returns the paint.
         * 
         * @return The paint (never <code>null</code>).
         * 
         * @see #setPaint(Paint)
         */
        public Paint getPaint() {
            return this.paint;
        }
        
        /**
         * Sets the paint and sends a {@link DialLayerChangeEvent} to all 
         * registered listeners.
         * 
         * @param paint  the paint (<code>null</code> not permitted).
         * 
         * @see #getPaint()
         */
        public void setPaint(Paint paint) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[9]++;
                throw new IllegalArgumentException("Null 'paint' argument.");

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[10]++;}
            this.paint = paint;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[20]++;
            notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[21]++;
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
         * Sets the stroke and sends a {@link DialLayerChangeEvent} to all 
         * registered listeners.
         * 
         * @param stroke  the stroke (<code>null</code> not permitted).
         * 
         * @see #getStroke()
         */
        public void setStroke(Stroke stroke) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[11]++;
                throw new IllegalArgumentException("Null 'stroke' argument.");

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[12]++;}
            this.stroke = stroke;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[23]++;
            notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[24]++;
        }
        
        /**
         * Draws the pointer.
         * 
         * @param g2  the graphics target.
         * @param plot  the plot.
         * @param frame  the dial's reference frame.
         * @param view  the dial's view.
         */
        public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
            Rectangle2D view) {
        
            g2.setPaint(this.paint);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[25]++;
            g2.setStroke(this.stroke);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[26]++;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[27]++;
            Rectangle2D arcRect = DialPlot.rectangleByRadius(frame, 
                    this.radius, this.radius);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[28]++;

            double value = plot.getValue(this.datasetIndex);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[29]++;
            DialScale scale = plot.getScaleForDataset(this.datasetIndex);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[30]++;
            double angle = scale.valueToAngle(value);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[31]++;
        
            Arc2D arc = new Arc2D.Double(arcRect, angle, 0, Arc2D.OPEN);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[32]++;
            Point2D pt = arc.getEndPoint();
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[33]++;
        
            Line2D line = new Line2D.Double(frame.getCenterX(), 
                    frame.getCenterY(), pt.getX(), pt.getY());
            g2.draw(line);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[34]++;
        }
        
        /**
         * Tests this pointer for equality with an arbitrary object.
         * 
         * @param obj  the object (<code>null</code> permitted).
         * 
         * @return A boolean.
         */
        public boolean equals(Object obj) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[13]++;
                return true;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[14]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof DialPointer.Pin) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[15]++;
                return false;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[16]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[37]++;
            DialPointer.Pin that = (DialPointer.Pin) obj;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[17]++;
                return false;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[18]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.stroke.equals(that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[19]++;
                return false;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[20]++;}
            return super.equals(obj);
        }
        
        /**
         * Returns a hash code for this instance.
         * 
         * @return A hash code.
         */
        public int hashCode() {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[40]++;
            int result = super.hashCode();
            result = HashUtilities.hashCode(result, this.paint);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[41]++;
            result = HashUtilities.hashCode(result, this.stroke);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[42]++;
            return result;
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
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[43]++;
            SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[44]++;
            SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[45]++;
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
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[46]++;
            this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[47]++;
            this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[48]++;
        }
        
    }
    
    /**
     * A dial pointer.
     */
    public static class Pointer extends DialPointer {
        
        /** For serialization. */
        static final long serialVersionUID = -4180500011963176960L;
        
        /**
         * The radius that defines the width of the pointer at the base.
         */
        private double widthRadius;
    
        /** 
         * The fill paint.
         * 
         * @since 1.0.8
         */
        private transient Paint fillPaint;
        
        /** 
         * The outline paint.
         * 
         * @since 1.0.8
         */
        private transient Paint outlinePaint;

        /**
         * Creates a new instance.
         */
        public Pointer() {
            this(0);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[49]++;
        }
        
        /**
         * Creates a new instance.
         * 
         * @param datasetIndex  the dataset index.
         */
        public Pointer(int datasetIndex) {
            super(datasetIndex);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[50]++;
            this.widthRadius = 0.05;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[51]++;
            this.fillPaint = Color.gray;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[52]++;
            this.outlinePaint = Color.black;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[53]++;
        }
        
        /**
         * Returns the width radius.
         * 
         * @return The width radius.
         * 
         * @see #setWidthRadius(double)
         */
        public double getWidthRadius() {
            return this.widthRadius;
        }
        
        /**
         * Sets the width radius and sends a {@link DialLayerChangeEvent} to 
         * all registered listeners.
         * 
         * @param radius  the radius
         * 
         * @see #getWidthRadius()
         */
        public void setWidthRadius(double radius) {
            this.widthRadius = radius;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[54]++;
            notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[55]++;
        }
        
        /**
         * Returns the fill paint.
         * 
         * @return The paint (never <code>null</code>).
         * 
         * @see #setFillPaint(Paint)
         * 
         * @since 1.0.8
         */
        public Paint getFillPaint() {
            return this.fillPaint;
        }
        
        /**
         * Sets the fill paint and sends a {@link DialLayerChangeEvent} to all 
         * registered listeners.
         * 
         * @param paint  the paint (<code>null</code> not permitted).
         * 
         * @see #getFillPaint()
         * 
         * @since 1.0.8
         */
        public void setFillPaint(Paint paint) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[21]++;
                throw new IllegalArgumentException("Null 'paint' argument.");

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[22]++;}
            this.fillPaint = paint;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[57]++;
            notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[58]++;
        }
        
        /**
         * Returns the outline paint.
         * 
         * @return The paint (never <code>null</code>).
         * 
         * @see #setOutlinePaint(Paint)
         * 
         * @since 1.0.8
         */
        public Paint getOutlinePaint() {
            return this.outlinePaint;
        }
        
        /**
         * Sets the outline paint and sends a {@link DialLayerChangeEvent} to 
         * all registered listeners.
         * 
         * @param paint  the paint (<code>null</code> not permitted).
         * 
         * @see #getOutlinePaint()
         * 
         * @since 1.0.8
         */
        public void setOutlinePaint(Paint paint) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[59]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[23]++;
                throw new IllegalArgumentException("Null 'paint' argument.");

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[24]++;}
            this.outlinePaint = paint;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[60]++;
            notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[61]++;
        }
        
        /**
         * Draws the pointer.
         * 
         * @param g2  the graphics target.
         * @param plot  the plot.
         * @param frame  the dial's reference frame.
         * @param view  the dial's view.
         */
        public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
                Rectangle2D view) {
        
            g2.setPaint(Color.blue);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[62]++;
            g2.setStroke(new BasicStroke(1.0f));
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[63]++;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[64]++;
            Rectangle2D lengthRect = DialPlot.rectangleByRadius(frame, 
                    this.radius, this.radius);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[65]++;
            Rectangle2D widthRect = DialPlot.rectangleByRadius(frame, 
                    this.widthRadius, this.widthRadius);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[66]++;
            double value = plot.getValue(this.datasetIndex);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[67]++;
            DialScale scale = plot.getScaleForDataset(this.datasetIndex);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[68]++;
            double angle = scale.valueToAngle(value);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[69]++;
        
            Arc2D arc1 = new Arc2D.Double(lengthRect, angle, 0, Arc2D.OPEN);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[70]++;
            Point2D pt1 = arc1.getEndPoint();
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[71]++;
            Arc2D arc2 = new Arc2D.Double(widthRect, angle - 90.0, 180.0, 
                    Arc2D.OPEN);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[72]++;
            Point2D pt2 = arc2.getStartPoint();
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[73]++;
            Point2D pt3 = arc2.getEndPoint();
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[74]++;
            Arc2D arc3 = new Arc2D.Double(widthRect, angle - 180.0, 0.0, 
                    Arc2D.OPEN);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[75]++;
            Point2D pt4 = arc3.getStartPoint();
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[76]++;
        
            GeneralPath gp = new GeneralPath();
            gp.moveTo((float) pt1.getX(), (float) pt1.getY());
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[77]++;
            gp.lineTo((float) pt2.getX(), (float) pt2.getY());
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[78]++;
            gp.lineTo((float) pt4.getX(), (float) pt4.getY());
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[79]++;
            gp.lineTo((float) pt3.getX(), (float) pt3.getY());
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[80]++;
            gp.closePath();
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[81]++;
            g2.setPaint(this.fillPaint);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[82]++;
            g2.fill(gp);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[83]++;
        
            g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[84]++;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[85]++;
            Line2D line = new Line2D.Double(frame.getCenterX(), 
                    frame.getCenterY(), pt1.getX(), pt1.getY());
            g2.draw(line);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[86]++;
        
            line.setLine(pt2, pt3);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[87]++;
            g2.draw(line);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[88]++;
        
            line.setLine(pt3, pt1);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[89]++;
            g2.draw(line);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[90]++;
        
            line.setLine(pt2, pt1);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[91]++;
            g2.draw(line);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[92]++;
        
            line.setLine(pt2, pt4);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[93]++;
            g2.draw(line);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[94]++;

            line.setLine(pt3, pt4);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[95]++;
            g2.draw(line);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[96]++;
        }
        
        /**
         * Tests this pointer for equality with an arbitrary object.
         * 
         * @param obj  the object (<code>null</code> permitted).
         * 
         * @return A boolean.
         */
        public boolean equals(Object obj) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[97]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[25]++;
                return true;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[26]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[98]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((obj instanceof DialPointer.Pointer) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[27]++;
                return false;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[28]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[99]++;
            DialPointer.Pointer that = (DialPointer.Pointer) obj;
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[100]++;
int CodeCoverConditionCoverageHelper_C15;
            
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.widthRadius != that.widthRadius) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[29]++;
                return false;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[30]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[101]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[31]++;
                return false;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[32]++;}
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[102]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[33]++;
                return false;

            } else {
  CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.branches[34]++;}
            return super.equals(obj);
        }
        
        /**
         * Returns a hash code for this instance.
         * 
         * @return A hash code.
         */
        public int hashCode() {
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[103]++;
            int result = super.hashCode();
            result = HashUtilities.hashCode(result, this.widthRadius);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[104]++;
            result = HashUtilities.hashCode(result, this.fillPaint);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[105]++;
            result = HashUtilities.hashCode(result, this.outlinePaint);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[106]++;
            return result;
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
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[107]++;
            SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[108]++;
            SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[109]++;
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
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[110]++;
            this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[111]++;
            this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d.statements[112]++;
        }
       
    }

}

class CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d ());
  }
    public static long[] statements = new long[113];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.DialPointer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$41sz3979ag84esa5vq6kugq5d () {
    super("org.jfree.chart.plot.dial.DialPointer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 112; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.DialPointer.java");
      for (int i = 1; i <= 112; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
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

