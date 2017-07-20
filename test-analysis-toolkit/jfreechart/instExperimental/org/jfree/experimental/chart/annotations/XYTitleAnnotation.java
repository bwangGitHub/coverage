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
 * XYTitleAnnotation.java
 * ----------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-Feb-2007 : Version 1 (DG);
 * 30-Apr-2007 : Fixed equals() method (DG);
 * 
 */

package org.jfree.experimental.chart.annotations;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.annotations.AbstractXYAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockParams;
import org.jfree.chart.block.EntityBlockResult;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.Title;
import org.jfree.data.Range;
import org.jfree.experimental.chart.util.XYCoordinateType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.Size2D;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * An annotation that allows any {@link Title} to be placed at a location on 
 * an {@link XYPlot}.
 */
public class XYTitleAnnotation extends AbstractXYAnnotation
                               implements Cloneable, PublicCloneable, 
                                          Serializable {
  static {
    CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4364694501921559958L;
  static {
    CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[1]++;
  }
    
    /** The coordinate type. */
    private XYCoordinateType coordinateType;
    
    /** The x-coordinate (in data space). */
    private double x;

    /** The y-coordinate (in data space). */
    private double y;
    
    private double maxWidth;
    
    private double maxHeight;

    /** The title. */
    private Title title;

    /** 
     * The title anchor point. 
     */
    private RectangleAnchor anchor;
    
    /**
     * Creates a new annotation to be displayed at the specified (x, y) 
     * location.
     *
     * @param x  the x-coordinate (in data space).
     * @param y  the y-coordinate (in data space).
     * @param title  the title (<code>null</code> not permitted).
     */
    public XYTitleAnnotation(double x, double y, Title title) {
        this(x, y, title, RectangleAnchor.CENTER);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[2]++;
    }
    
    /**
     * Creates a new annotation to be displayed at the specified (x, y) 
     * location.
     *
     * @param x  the x-coordinate (in data space).
     * @param y  the y-coordinate (in data space).
     * @param title  the title (<code>null</code> not permitted).
     * @param anchor  the title anchor (<code>null</code> not permitted).
     */
    public XYTitleAnnotation(double x, double y, Title title, 
            RectangleAnchor anchor) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((title == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[1]++;
            throw new IllegalArgumentException("Null 'title' argument.");
      
        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[2]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[3]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[4]++;}
        this.coordinateType = XYCoordinateType.RELATIVE;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[5]++;
        this.x = x;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[6]++;
        this.y = y;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[7]++;
        this.maxWidth = 0.0;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[8]++;
        this.maxHeight = 0.0;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[9]++;
        this.title = title;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[10]++;
        this.anchor = anchor;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[11]++;
    }    
    
    /**
     * Returns the coordinate type (set in the constructor).
     * 
     * @return The coordinate type (never <code>null</code>).
     */
    public XYCoordinateType getCoordinateType() {
        return this.coordinateType;
    }
    
    /**
     * Returns the x-coordinate for the annotation.
     * 
     * @return The x-coordinate.
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Returns the y-coordinate for the annotation.
     * 
     * @return The y-coordinate.
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Returns the title for the annotation.
     * 
     * @return The title.
     */
    public Title getTitle() {
        return this.title;
    }
    
    /**
     * Returns the title anchor for the annotation.
     * 
     * @return The title anchor.
     */
    public RectangleAnchor getTitleAnchor() {
        return this.anchor;
    }
    
    /**
     * Returns the maximum width.
     * 
     * @return The maximum width.
     */
    public double getMaxWidth() {
        return this.maxWidth;
    }
    
    /**
     * Sets the maximum width.
     * 
     * @param max  the maximum width (0.0 or less means no maximum).
     */
    public void setMaxWidth(double max) {
        this.maxWidth = max;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[12]++;
    }
    
    /**
     * Returns the maximum height.
     * 
     * @return The maximum height.
     */
    public double getMaxHeight() {
        return this.maxHeight;
    }
    
    /**
     * Sets the maximum height.
     * 
     * @param max  the maximum height.
     */
    public void setMaxHeight(double max) {
        this.maxHeight = max;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[13]++;
    }

    /**
     * Draws the annotation.  This method is called by the drawing code in the 
     * {@link XYPlot} class, you don't normally need to call this method 
     * directly.
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
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[14]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[15]++;
        AxisLocation domainAxisLocation = plot.getDomainAxisLocation();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[16]++;
        AxisLocation rangeAxisLocation = plot.getRangeAxisLocation();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[17]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                domainAxisLocation, orientation);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[18]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                rangeAxisLocation, orientation);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[19]++;
        Range xRange = domainAxis.getRange();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[20]++;
        Range yRange = rangeAxis.getRange();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[21]++;
        double anchorX = 0.0;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[22]++;
        double anchorY = 0.0;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.coordinateType == XYCoordinateType.RELATIVE) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[5]++;
            anchorX = xRange.getLowerBound() + (this.x * xRange.getLength());
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[24]++;
            anchorY = yRange.getLowerBound() + (this.y * yRange.getLength());
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[25]++;

        }
        else {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[6]++;
            anchorX = domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[26]++;
            anchorY = rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[27]++;
        }
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[28]++;
        
        float j2DX = (float) domainAxis.valueToJava2D(anchorX, dataArea, 
                domainEdge);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[29]++;
        float j2DY = (float) rangeAxis.valueToJava2D(anchorY, dataArea, 
                rangeEdge);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[30]++;
        float xx = 0.0f;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[31]++;
        float yy = 0.0f;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[7]++;
            xx = j2DY;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[33]++;
            yy = j2DX;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[34]++;

        }
        else {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[8]++;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[35]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[9]++;
            xx = j2DX;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[36]++;
            yy = j2DY;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[37]++;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[10]++;}
}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[38]++;
        
        double maxW = dataArea.getWidth();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[39]++;
        double maxH = dataArea.getHeight();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[40]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.coordinateType == XYCoordinateType.RELATIVE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[11]++;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.maxWidth > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[13]++;
                maxW = maxW * this.maxWidth;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[42]++;

            } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[14]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.maxHeight > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[15]++;
                maxH = maxH * this.maxHeight;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[44]++;

            } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[16]++;}

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[12]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.coordinateType == XYCoordinateType.DATA) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[17]++;
            maxW = this.maxWidth;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[46]++;
            maxH = this.maxHeight;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[47]++;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[18]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[48]++;
        RectangleConstraint rc = new RectangleConstraint(
                new Range(0, maxW), new Range(0, maxH));
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[49]++;
        
        Size2D size = this.title.arrange(g2, rc);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[50]++;
        Rectangle2D titleRect = new Rectangle2D.Double(0, 0, size.width, 
                size.height);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[51]++;
        Point2D anchorPoint = RectangleAnchor.coordinates(titleRect, 
                this.anchor);
        xx = xx - (float) anchorPoint.getX();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[52]++;
        yy = yy - (float) anchorPoint.getY();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[53]++;
        titleRect.setRect(xx, yy, titleRect.getWidth(), titleRect.getHeight());
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[54]++;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[55]++;
        BlockParams p = new BlockParams();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[56]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[19]++;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((info.getOwner().getEntityCollection() != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[21]++;
                p.setGenerateEntities(true);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[58]++;

            } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[22]++;}

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[20]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[59]++;
        Object result = this.title.draw(g2, titleRect, p);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[60]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((result instanceof EntityBlockResult) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[23]++;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[61]++;
            EntityBlockResult ebr = (EntityBlockResult) result;
            info.getOwner().getEntityCollection().addAll(
                    ebr.getEntityCollection());
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[62]++;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[24]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[63]++;
        String toolTip = getToolTipText();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[64]++;
        String url = getURL();
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[65]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((toolTip != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((url != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[25]++;
            addEntity(info, new Rectangle2D.Float(xx, yy, (float) size.width, 
                    (float) size.height), rendererIndex, toolTip, url);
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[66]++;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[26]++;}
    }

    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[27]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[28]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[68]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj instanceof XYTitleAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[30]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[69]++;
        XYTitleAnnotation that = (XYTitleAnnotation) obj;
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[70]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.coordinateType != that.coordinateType) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[32]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[71]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.x != that.x) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[34]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.y != that.y) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[36]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[73]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.maxWidth != that.maxWidth) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[38]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[74]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.maxHeight != that.maxHeight) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[40]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[75]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.title, that.title)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[42]++;}
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.statements[76]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.anchor.equals(that.anchor)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd.branches[44]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        // FIXME: do better than this
        return this.title.hashCode();
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

}

class CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd ());
  }
    public static long[] statements = new long[77];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.jfree.experimental.chart.annotations.XYTitleAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 22; i++) {
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

  public CodeCoverCoverageCounter$ehv8u95a5fqnozwksqz5jkm2d290fffotd () {
    super("org.jfree.experimental.chart.annotations.XYTitleAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 76; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.experimental.chart.annotations.XYTitleAnnotation.java");
      for (int i = 1; i <= 76; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
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

