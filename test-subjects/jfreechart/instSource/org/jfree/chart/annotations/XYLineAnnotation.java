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
 * XYLineAnnotation.java
 * ---------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-Apr-2003 : Version 1 (DG);
 * 19-Aug-2003 : Added equals method, implemented Cloneable, and applied 
 *               serialization fixes (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 14-Apr-2004 : Fixed draw() method to handle plot orientation correctly (DG);
 * 29-Sep-2004 : Added support for tool tips and URLS, now extends 
 *               AbstractXYAnnotation (DG);
 * 04-Oct-2004 : Renamed ShapeUtils --> ShapeUtilities (DG);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint() (DG);
 * 
 */

package org.jfree.chart.annotations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
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
import org.jfree.util.ShapeUtilities;

/**
 * A simple line annotation that can be placed on an {@link XYPlot}.
 */
public class XYLineAnnotation extends AbstractXYAnnotation
                              implements Cloneable, PublicCloneable, 
                                         Serializable {
  static {
    CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -80535465244091334L;
  static {
    CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[1]++;
  }
    
    /** The x-coordinate. */
    private double x1;

    /** The y-coordinate. */
    private double y1;

    /** The x-coordinate. */
    private double x2;

    /** The y-coordinate. */
    private double y2;

    /** The line stroke. */
    private transient Stroke stroke;

    /** The line color. */
    private transient Paint paint;

    /**
     * Creates a new annotation that draws a line from (x1, y1) to (x2, y2) 
     * where the coordinates are measured in data space (that is, against the 
     * plot's axes).
     * 
     * @param x1  the x-coordinate for the start of the line.
     * @param y1  the y-coordinate for the start of the line.
     * @param x2  the x-coordinate for the end of the line.
     * @param y2  the y-coordinate for the end of the line.
     */
    public XYLineAnnotation(double x1, double y1, double x2, double y2) {
        this(x1, y1, x2, y2, new BasicStroke(1.0f), Color.black);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[2]++;
    }
    
    /**
     * Creates a new annotation that draws a line from (x1, y1) to (x2, y2) 
     * where the coordinates are measured in data space (that is, against the 
     * plot's axes).
     *
     * @param x1  the x-coordinate for the start of the line.
     * @param y1  the y-coordinate for the start of the line.
     * @param x2  the x-coordinate for the end of the line.
     * @param y2  the y-coordinate for the end of the line.
     * @param stroke  the line stroke (<code>null</code> not permitted).
     * @param paint  the line color (<code>null</code> not permitted).
     */
    public XYLineAnnotation(double x1, double y1, double x2, double y2,
                            Stroke stroke, Paint paint) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[1]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[2]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[4]++;}
        this.x1 = x1;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[5]++;
        this.y1 = y1;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[6]++;
        this.x2 = x2;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[7]++;
        this.y2 = y2;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[8]++;
        this.stroke = stroke;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[9]++;
        this.paint = paint;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[10]++;

    }

    /**
     * Draws the annotation.  This method is called by the {@link XYPlot} 
     * class, you won't normally need to call it yourself.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  if supplied, this info object will be populated with
     *              entity information.
     */
    public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis, 
                     int rendererIndex,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[11]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[12]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[13]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[14]++;
        float j2DX1 = 0.0f;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[15]++;
        float j2DX2 = 0.0f;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[16]++;
        float j2DY1 = 0.0f;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[17]++;
        float j2DY2 = 0.0f;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[5]++;
            j2DX1 = (float) domainAxis.valueToJava2D(this.x1, dataArea, 
                    domainEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[19]++;
            j2DY1 = (float) rangeAxis.valueToJava2D(this.y1, dataArea, 
                    rangeEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[20]++;
            j2DX2 = (float) domainAxis.valueToJava2D(this.x2, dataArea, 
                    domainEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[21]++;
            j2DY2 = (float) rangeAxis.valueToJava2D(this.y2, dataArea, 
                    rangeEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[22]++;

        }
        else {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[6]++;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[23]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[7]++;
            j2DY1 = (float) domainAxis.valueToJava2D(this.x1, dataArea, 
                    domainEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[24]++;
            j2DX1 = (float) rangeAxis.valueToJava2D(this.y1, dataArea, 
                    rangeEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[25]++;
            j2DY2 = (float) domainAxis.valueToJava2D(this.x2, dataArea, 
                    domainEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[26]++;
            j2DX2 = (float) rangeAxis.valueToJava2D(this.y2, dataArea, 
                    rangeEdge);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[27]++;
                
        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[8]++;}
}
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[28]++;
        g2.setStroke(this.stroke);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[29]++;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[30]++;
        Line2D line = new Line2D.Float(j2DX1, j2DY1, j2DX2, j2DY2);
        g2.draw(line);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[31]++;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[32]++;

        String toolTip = getToolTipText();
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[33]++;
        String url = getURL();
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((toolTip != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((url != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[9]++;
            addEntity(info, ShapeUtilities.createLineRegion(line, 1.0f), 
                    rendererIndex, toolTip, url);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[35]++;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[10]++;}
    }

    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[12]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[14]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof XYLineAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[16]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[39]++;
        XYLineAnnotation that = (XYLineAnnotation) obj;
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.x1 != that.x1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[18]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.y1 != that.y1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[20]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.x2 != that.x2) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[22]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[43]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.y2 != that.y2) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[24]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[44]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[26]++;}
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[45]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.branches[28]++;}
        // seems to be the same...
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
        temp = Double.doubleToLongBits(this.x1);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[46]++;
        result = (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[47]++;
        temp = Double.doubleToLongBits(this.x2);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[48]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[49]++;
        temp = Double.doubleToLongBits(this.y1);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[50]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[51]++;
        temp = Double.doubleToLongBits(this.y2);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[52]++;
        result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[53]++;
        return result;
    }

    /**
     * Returns a clone of the annotation.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the annotation can't be cloned.
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
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[54]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[55]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[56]++;
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
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[57]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[58]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt.statements[59]++;
    }

}

class CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt ());
  }
    public static long[] statements = new long[60];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYLineAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$21dyarujty3qh1g1dg1rtibxx6toi7ntt () {
    super("org.jfree.chart.annotations.XYLineAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 59; i++) {
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
    log.startNamedSection("org.jfree.chart.annotations.XYLineAnnotation.java");
      for (int i = 1; i <= 59; i++) {
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

