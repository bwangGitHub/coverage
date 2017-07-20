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
 * XYShapeAnnotation.java
 * ----------------------
 * (C) Copyright 2003-2007, by Ondax, Inc. and Contributors.
 *
 * Original Author:  Greg Steckman (for Ondax, Inc.);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 15-Aug-2003 : Version 1, adapted from 
 *               org.jfree.chart.annotations.XYLineAnnotation (GS);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 20-Apr-2004 : Added new constructor and fixed bug 934258 (DG);
 * 29-Sep-2004 : Added 'fillPaint' to allow for colored shapes, now extends
 *               AbstractXYAnnotation to add tool tip and URL support, and 
 *               implemented equals() and Cloneable (DG);
 * 21-Jan-2005 : Modified constructor for consistency with other 
 *               constructors (DG);
 * 06-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 24-Oct-2006 : Calculate AffineTransform on shape's bounding rectangle 
 *               rather than sample points (0, 0) and (1, 1) (DG);
 * 06-Mar-2007 : Implemented hashCode() (DG);
 * 
 */
 
package org.jfree.chart.annotations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A simple <code>Shape</code> annotation that can be placed on an 
 * {@link XYPlot}.  The shape coordinates are specified in data space.
 */
public class XYShapeAnnotation extends AbstractXYAnnotation
                               implements Cloneable, PublicCloneable, 
                                          Serializable {
  static {
    CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -8553218317600684041L;
  static {
    CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[1]++;
  }
    
    /** The shape. */
    private transient Shape shape;

    /** The stroke used to draw the shape's outline. */
    private transient Stroke stroke;

    /** The paint used to draw the shape's outline. */
    private transient Paint outlinePaint;
    
    /** The paint used to fill the shape. */
    private transient Paint fillPaint;

    /**
     * Creates a new annotation (where, by default, the shape is drawn 
     * with a black outline).
     * 
     * @param shape  the shape (coordinates in data space, <code>null</code> 
     *     not permitted).
     */
    public XYShapeAnnotation(Shape shape) {
        this(shape, new BasicStroke(1.0f), Color.black);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[2]++;
    }
    
    /**
     * Creates a new annotation where the shape is drawn as an outline using
     * the specified <code>stroke</code> and <code>outlinePaint</code>.
     *
     * @param shape  the shape (<code>null</code> not permitted).
     * @param stroke  the shape stroke (<code>null</code> permitted).
     * @param outlinePaint  the shape color (<code>null</code> permitted).
     */
    public XYShapeAnnotation(Shape shape, Stroke stroke, Paint outlinePaint) {
        this(shape, stroke, outlinePaint, null);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[3]++;
    }

    /**
     * Creates a new annotation.
     *
     * @param shape  the shape (<code>null</code> not permitted).
     * @param stroke  the shape stroke (<code>null</code> permitted).
     * @param outlinePaint  the shape color (<code>null</code> permitted).
     * @param fillPaint  the paint used to fill the shape (<code>null</code> 
     *                   permitted.
     */
    public XYShapeAnnotation(Shape shape, Stroke stroke, Paint outlinePaint, 
                             Paint fillPaint) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[1]++;
            throw new IllegalArgumentException("Null 'shape' argument.");
   
        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[2]++;}
        this.shape = shape;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[5]++;
        this.stroke = stroke;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[6]++;
        this.outlinePaint = outlinePaint;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[7]++;
        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[8]++;
    }

    /**
     * Draws the annotation.  This method is usually called by the 
     * {@link XYPlot} class, you shouldn't need to call it directly.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  the plot rendering info.
     */
    public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis, 
                     int rendererIndex,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[9]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[10]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[11]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[12]++;

        // compute transform matrix elements via sample points. Assume no 
        // rotation or shear.
        Rectangle2D bounds = this.shape.getBounds2D();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[13]++;
        double x0 = bounds.getMinX();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[14]++;
        double x1 = bounds.getMaxX();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[15]++;
        double xx0 = domainAxis.valueToJava2D(x0, dataArea, domainEdge);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[16]++;
        double xx1 = domainAxis.valueToJava2D(x1, dataArea, domainEdge);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[17]++;
        double m00 = (xx1 - xx0) / (x1 - x0);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[18]++;
        double m02 = xx0 - x0 * m00;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[19]++;
        
        double y0 = bounds.getMaxY();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[20]++;
        double y1 = bounds.getMinY();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[21]++;
        double yy0 = rangeAxis.valueToJava2D(y0, dataArea, rangeEdge);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[22]++;
        double yy1 = rangeAxis.valueToJava2D(y1, dataArea, rangeEdge);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[23]++;
        double m11 = (yy1 - yy0) / (y1 - y0);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[24]++;
        double m12 = yy0 - m11 * y0;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[25]++;

        //  create transform & transform shape
        Shape s = null;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[3]++;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[27]++;
            AffineTransform t1 = new AffineTransform(0.0f, 1.0f, 1.0f, 0.0f, 
                    0.0f, 0.0f);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[28]++;
            AffineTransform t2 = new AffineTransform(m11, 0.0f, 0.0f, m00, 
                    m12, m02);
            s = t1.createTransformedShape(this.shape);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[29]++;
            s = t2.createTransformedShape(s);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[30]++;

        }
        else {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[4]++;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[31]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[5]++;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[32]++;
            AffineTransform t = new AffineTransform(m00, 0, 0, m11, m02, m12);
            s = t.createTransformedShape(this.shape);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[33]++;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[6]++;}
}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[34]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.fillPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[7]++;
            g2.setPaint(this.fillPaint);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[35]++;
            g2.fill(s);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[36]++;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[8]++;}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((this.stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[9]++;
            g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[38]++;
            g2.setStroke(this.stroke);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[39]++;
            g2.draw(s);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[40]++;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[10]++;}
        addEntity(info, s, rendererIndex, getToolTipText(), getURL());
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[41]++;
        
    }
        
    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[12]++;}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[43]++;
int CodeCoverConditionCoverageHelper_C7;
        // now try to reject equality
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[14]++;}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof XYShapeAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[16]++;}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[45]++;
        XYShapeAnnotation that = (XYShapeAnnotation) obj;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.shape.equals(that.shape)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[18]++;}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[20]++;}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[22]++;}
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[24]++;}
        // seem to be the same
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[50]++;
        int result = 193;
        result = 37 * result + this.shape.hashCode();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[51]++;
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[25]++;
            result = 37 * result + this.stroke.hashCode();
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[53]++;

        } else {
  CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.branches[26]++;}
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.outlinePaint);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[54]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.fillPaint);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[55]++;
        return result;
    }
    
    /**
     * Returns a clone.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException ???.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[56]++;
        SerialUtilities.writeShape(this.shape, stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[57]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[58]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[59]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[60]++;
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
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[61]++;
        this.shape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[62]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[63]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[64]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch.statements[65]++;
    }

}

class CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch ());
  }
    public static long[] statements = new long[66];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYShapeAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$ehv8q14h17yf9p9ec8cspysfrmelq59mch () {
    super("org.jfree.chart.annotations.XYShapeAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 65; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.annotations.XYShapeAnnotation.java");
      for (int i = 1; i <= 65; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

