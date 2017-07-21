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
 * MeterNeedle.java
 * ----------------
 * (C) Copyright 2002-2007, by the Australian Antarctic Division and 
 *                          Contributors.
 *
 * Original Author:  Bryan Scott (for the Australian Antarctic Division);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nicolas Brodu (for Astrium and EADS Corporate Research 
 *                   Center);
 *
 * Changes:
 * --------
 * 25-Sep-2002 : Version 1, contributed by Bryan Scott (DG);
 * 07-Nov-2002 : Fixed errors reported by Checkstyle (DG);
 * 01-Sep-2003 : Implemented Serialization (NB);
 * 16-Mar-2004 : Changed transform from private to protected (BRS);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * 22-Nov-2007 : Implemented hashCode() (DG);
 * 
 */

package org.jfree.chart.needle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * The base class used to represent the needle on a 
 * {@link org.jfree.chart.plot.CompassPlot}.
 */
public abstract class MeterNeedle implements Serializable {
  static {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5203064851510951052L;
  static {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[1]++;
  }
    
    /** The outline paint. */
    private transient Paint outlinePaint = Color.black;
  {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[2]++;
  }

    /** The outline stroke. */
    private transient Stroke outlineStroke = new BasicStroke(2);
  {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[3]++;
  }

    /** The fill paint. */
    private transient Paint fillPaint = null;
  {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[4]++;
  }

    /** The highlight paint. */
    private transient Paint highlightPaint = null;
  {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[5]++;
  }

    /** The size. */
    private int size = 5;
  {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[6]++;
  }

    /** Scalar to aply to locate the rotation x point. */
    private double rotateX = 0.5;
  {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[7]++;
  }

    /** Scalar to aply to locate the rotation y point. */
    private double rotateY = 0.5;
  {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[8]++;
  }

    /** A transform. */
    protected static AffineTransform transform = new AffineTransform();
  static {
    CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[9]++;
  }

    /**
     * Creates a new needle.
     */
    public MeterNeedle() {
        this(null, null, null);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[10]++;
    }

    /**
     * Creates a new needle.
     *
     * @param outline  the outline paint (<code>null</code> permitted).
     * @param fill  the fill paint (<code>null</code> permitted).
     * @param highlight  the highlight paint (<code>null</code> permitted).
     */
    public MeterNeedle(Paint outline, Paint fill, Paint highlight) {
        this.fillPaint = fill;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[11]++;
        this.highlightPaint = highlight;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[12]++;
        this.outlinePaint = outline;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[13]++;
    }

    /**
     * Returns the outline paint.
     *
     * @return The outline paint.
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }

    /**
     * Sets the outline paint.
     *
     * @param p  the new paint.
     */
    public void setOutlinePaint(Paint p) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[1]++;
            this.outlinePaint = p;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[15]++;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[2]++;}
    }

    /**
     * Returns the outline stroke.
     *
     * @return The outline stroke.
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }

    /**
     * Sets the outline stroke.
     *
     * @param s  the new stroke.
     */
    public void setOutlineStroke(Stroke s) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[3]++;
            this.outlineStroke = s;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[17]++;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[4]++;}
    }

    /**
     * Returns the fill paint.
     *
     * @return The fill paint.
     */
    public Paint getFillPaint() {
        return this.fillPaint;
    }

    /**
     * Sets the fill paint.
     *
     * @param p  the fill paint.
     */
    public void setFillPaint(Paint p) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[5]++;
            this.fillPaint = p;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[19]++;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[6]++;}
    }

    /**
     * Returns the highlight paint.
     *
     * @return The highlight paint.
     */
    public Paint getHighlightPaint() {
        return this.highlightPaint;
    }

    /**
     * Sets the highlight paint.
     *
     * @param p  the highlight paint.
     */
    public void setHighlightPaint(Paint p) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[7]++;
            this.highlightPaint = p;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[21]++;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[8]++;}
    }

    /**
     * Returns the scalar used for determining the rotation x value.
     *
     * @return The x rotate scalar.
     */
    public double getRotateX() {
        return this.rotateX;
    }

    /**
     * Sets the rotateX value.
     *
     * @param x  the new value.
     */
    public void setRotateX(double x) {
        this.rotateX = x;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[22]++;
    }

    /**
     * Sets the rotateY value.
     *
     * @param y  the new value.
     */
    public void setRotateY(double y) {
        this.rotateY = y;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[23]++;
    }

    /**
     * Returns the scalar used for determining the rotation y value.
     *
     * @return The y rotate scalar.
     */
    public double getRotateY() {
        return this.rotateY;
    }

    /**
     * Draws the needle.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     */
    public void draw(Graphics2D g2, Rectangle2D plotArea) {
        draw(g2, plotArea, 0);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[24]++;
    }

    /**
     * Draws the needle.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param angle  the angle.
     */
    public void draw(Graphics2D g2, Rectangle2D plotArea, double angle) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[25]++;

        Point2D.Double pt = new Point2D.Double();
        pt.setLocation(
            plotArea.getMinX() + this.rotateX * plotArea.getWidth(),
            plotArea.getMinY() + this.rotateY * plotArea.getHeight()
        );
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[26]++;
        draw(g2, plotArea, pt, angle);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[27]++;

    }

    /**
     * Draws the needle.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param rotate  the rotation point.
     * @param angle  the angle.
     */
    public void draw(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, 
                     double angle) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[28]++;

        Paint savePaint = g2.getColor();
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[29]++;
        Stroke saveStroke = g2.getStroke();

        drawNeedle(g2, plotArea, rotate, Math.toRadians(angle));
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[30]++;

        g2.setStroke(saveStroke);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[31]++;
        g2.setPaint(savePaint);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[32]++;

    }

    /**
     * Draws the needle.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param rotate  the rotation point.
     * @param angle  the angle.
     */
    protected abstract void drawNeedle(Graphics2D g2,
                                       Rectangle2D plotArea, Point2D rotate, 
                                       double angle);

    /**
     * Displays a shape.
     *
     * @param g2  the graphics device.
     * @param shape  the shape.
     */
    protected void defaultDisplay(Graphics2D g2, Shape shape) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.fillPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[9]++;
            g2.setPaint(this.fillPaint);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[34]++;
            g2.fill(shape);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[35]++;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[10]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[11]++;
            g2.setStroke(this.outlineStroke);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[37]++;
            g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[38]++;
            g2.draw(shape);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[39]++;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[12]++;}

    }

    /**
     * Returns the size.
     *
     * @return The size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sets the size.
     *
     * @param pixels  the new size.
     */
    public void setSize(int pixels) {
        this.size = pixels;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[40]++;
    }

    /**
     * Returns the transform.
     *
     * @return The transform.
     */
    public AffineTransform getTransform() {
        return MeterNeedle.transform;
    }

    /**
     * Tests another object for equality with this object.
     *
     * @param obj the object to test (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[14]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof MeterNeedle) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[16]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[43]++;
        MeterNeedle that = (MeterNeedle) obj;
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[18]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[20]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[22]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.highlightPaint, that.highlightPaint)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[24]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.size != that.size) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[26]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[49]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.rotateX != that.rotateX) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[28]++;}
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[50]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.rotateY != that.rotateY) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.branches[30]++;}
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[51]++;
        int result = HashUtilities.hashCode(193, this.fillPaint);
        result = HashUtilities.hashCode(result, this.highlightPaint);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[52]++;
        result = HashUtilities.hashCode(result, this.outlinePaint);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[53]++;
        result = HashUtilities.hashCode(result, this.outlineStroke);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[54]++;
        result = HashUtilities.hashCode(result, this.rotateX);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[55]++;
        result = HashUtilities.hashCode(result, this.rotateY);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[56]++;
        result = HashUtilities.hashCode(result, this.size);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[57]++;
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
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[58]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[59]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[60]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[61]++;
        SerialUtilities.writePaint(this.highlightPaint, stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[62]++;
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
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[63]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[64]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[65]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[66]++;
        this.highlightPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h.statements[67]++;
    }

}

class CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.MeterNeedle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$4kyc2bpp70m07uuz6muptnn8h () {
    super("org.jfree.chart.needle.MeterNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.needle.MeterNeedle.java");
      for (int i = 1; i <= 67; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

