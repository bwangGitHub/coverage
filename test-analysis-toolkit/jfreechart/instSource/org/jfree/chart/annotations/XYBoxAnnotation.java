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
 * XYBoxAnnotation.java
 * --------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Jan-2005 : Version 1 (DG);
 * 06-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * 
 */
 
package org.jfree.chart.annotations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
 * A box annotation that can be placed on an {@link XYPlot}.  The 
 * box coordinates are specified in data space.
 */
public class XYBoxAnnotation extends AbstractXYAnnotation
                             implements Cloneable, 
                                        PublicCloneable, 
                                        Serializable {
  static {
    CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 6764703772526757457L;
  static {
    CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[1]++;
  }
    
    /** The lower x-coordinate. */
    private double x0;
    
    /** The lower y-coordinate. */
    private double y0;

    /** The upper x-coordinate. */
    private double x1;
    
    /** The upper y-coordinate. */
    private double y1;

    /** The stroke used to draw the box outline. */
    private transient Stroke stroke;

    /** The paint used to draw the box outline. */
    private transient Paint outlinePaint;
    
    /** The paint used to fill the box. */
    private transient Paint fillPaint;

    /**
     * Creates a new annotation (where, by default, the box is drawn 
     * with a black outline).
     * 
     * @param x0  the lower x-coordinate of the box (in data space).
     * @param y0  the lower y-coordinate of the box (in data space).
     * @param x1  the upper x-coordinate of the box (in data space).
     * @param y1  the upper y-coordinate of the box (in data space).
     */
    public XYBoxAnnotation(double x0, double y0, double x1, double y1) {
        this(x0, y0, x1, y1, new BasicStroke(1.0f), Color.black);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[2]++;
    }
    
    /**
     * Creates a new annotation where the box is drawn as an outline using
     * the specified <code>stroke</code> and <code>outlinePaint</code>.
     *
     * @param x0  the lower x-coordinate of the box (in data space).
     * @param y0  the lower y-coordinate of the box (in data space).
     * @param x1  the upper x-coordinate of the box (in data space).
     * @param y1  the upper y-coordinate of the box (in data space).
     * @param stroke  the shape stroke (<code>null</code> permitted).
     * @param outlinePaint  the shape color (<code>null</code> permitted).
     */
    public XYBoxAnnotation(double x0, double y0, double x1, double y1, 
                           Stroke stroke, Paint outlinePaint) {
        this(x0, y0, x1, y1, stroke, outlinePaint, null);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[3]++;
    }

    /**
     * Creates a new annotation.
     *
     * @param x0  the lower x-coordinate of the box (in data space).
     * @param y0  the lower y-coordinate of the box (in data space).
     * @param x1  the upper x-coordinate of the box (in data space).
     * @param y1  the upper y-coordinate of the box (in data space).
     * @param stroke  the shape stroke (<code>null</code> permitted).
     * @param outlinePaint  the shape color (<code>null</code> permitted).
     * @param fillPaint  the paint used to fill the shape (<code>null</code> 
     *                   permitted).
     */
    public XYBoxAnnotation(double x0, double y0, double x1, double y1, 
                           Stroke stroke, Paint outlinePaint, Paint fillPaint) {
        this.x0 = x0;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[4]++;
        this.y0 = y0;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[5]++;
        this.x1 = x1;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[6]++;
        this.y1 = y1;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[7]++;
        this.stroke = stroke;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[8]++;
        this.outlinePaint = outlinePaint;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[9]++;
        this.fillPaint = fillPaint;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[10]++;
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
                     int rendererIndex, PlotRenderingInfo info) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[11]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[12]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[13]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[14]++;

        double transX0 = domainAxis.valueToJava2D(this.x0, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[15]++; 
        double transY0 = rangeAxis.valueToJava2D(this.y0, dataArea, rangeEdge);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[16]++; 
        double transX1 = domainAxis.valueToJava2D(this.x1, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[17]++; 
        double transY1 = rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[18]++; 

        Rectangle2D box = null;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[1]++;
            box = new Rectangle2D.Double(transY0, transX1, transY1 - transY0, 
                    transX0 - transX1);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[20]++;

        }
        else {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[2]++;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[21]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[3]++;
            box = new Rectangle2D.Double(transX0, transY1, transX1 - transX0, 
                    transY0 - transY1);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[22]++;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[4]++;}
}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.fillPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[5]++;
            g2.setPaint(this.fillPaint);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[24]++;
            g2.fill(box);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[25]++;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[6]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((this.stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[7]++;
            g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[27]++;
            g2.setStroke(this.stroke);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[28]++;
            g2.draw(box);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[29]++;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[8]++;}
        addEntity(info, box, rendererIndex, getToolTipText(), getURL());
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[30]++;
        
    }
        
    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[10]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
        // now try to reject equality
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[12]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof XYBoxAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[14]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[34]++;
        XYBoxAnnotation that = (XYBoxAnnotation) obj;
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.x0 == that.x0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[16]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.y0 == that.y0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[18]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.x1 == that.x1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[20]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[38]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.y1 == that.y1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[22]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[39]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[24]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[40]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[26]++;}
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[41]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.fillPaint, that.fillPaint)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.branches[28]++;}
        // seem to be the same
        return true;
    }
    
    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.x0);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[42]++;
        result = (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[43]++;
        temp = Double.doubleToLongBits(this.x1);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[44]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[45]++;
        temp = Double.doubleToLongBits(this.y0);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[46]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[47]++;
        temp = Double.doubleToLongBits(this.y1);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[48]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[49]++;
        return result;
    }

    /**
     * Returns a clone.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException not thrown by this class, but may be
     *                                    by subclasses.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream (<code>null</code> not permitted).
     *
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[50]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[51]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[52]++;
        SerialUtilities.writePaint(this.fillPaint, stream);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[53]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream (<code>null</code> not permitted).
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        
        stream.defaultReadObject();
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[54]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[55]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[56]++;
        this.fillPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9.statements[57]++;
    }

}

class CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9 ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYBoxAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$abiv26a18kqsk5svhrc7oemi31b1pi9 () {
    super("org.jfree.chart.annotations.XYBoxAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
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
    log.startNamedSection("org.jfree.chart.annotations.XYBoxAnnotation.java");
      for (int i = 1; i <= 57; i++) {
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

