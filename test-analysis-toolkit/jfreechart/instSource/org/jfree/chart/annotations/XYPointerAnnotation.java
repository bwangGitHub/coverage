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
 * ------------------------
 * XYPointerAnnotation.java
 * ------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 21-May-2003 : Version 1 (DG);
 * 10-Jun-2003 : Changed BoundsAnchor to TextAnchor (DG);
 * 02-Jul-2003 : Added accessor methods and simplified constructor (DG);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 13-Oct-2003 : Fixed bug where arrow paint is not set correctly (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 29-Sep-2004 : Changes to draw() method signature (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Feb-2006 : Correction for equals() method (fixes bug 1435160) (DG);
 * 12-Jul-2006 : Fix drawing for PlotOrientation.HORIZONTAL, thanks to 
 *               Skunk (DG);
 *
 */

package org.jfree.chart.annotations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
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
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * An arrow and label that can be placed on an 
 * {@link org.jfree.chart.plot.XYPlot}.  The arrow is drawn at a user-definable 
 * angle so that it points towards the (x, y) location for the annotation.  
 * <p>
 * The arrow length (and its offset from the (x, y) location) is controlled by 
 * the tip radius and the base radius attributes.  Imagine two circles around 
 * the (x, y) coordinate: the inner circle defined by the tip radius, and the 
 * outer circle defined by the base radius.  Now, draw the arrow starting at 
 * some point on the outer circle (the point is determined by the angle), with 
 * the arrow tip being drawn at a corresponding point on the inner circle.
 *
 */
public class XYPointerAnnotation extends XYTextAnnotation 
                                 implements Cloneable, PublicCloneable, 
                                            Serializable {
  static {
    CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4031161445009858551L;
  static {
    CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[1]++;
  }
    
    /** The default tip radius (in Java2D units). */
    public static final double DEFAULT_TIP_RADIUS = 10.0;
  static {
    CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[2]++;
  }
    
    /** The default base radius (in Java2D units). */
    public static final double DEFAULT_BASE_RADIUS = 30.0;
  static {
    CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[3]++;
  }
    
    /** The default label offset (in Java2D units). */
    public static final double DEFAULT_LABEL_OFFSET = 3.0;
  static {
    CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[4]++;
  }
    
    /** The default arrow length (in Java2D units). */
    public static final double DEFAULT_ARROW_LENGTH = 5.0;
  static {
    CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[5]++;
  }

    /** The default arrow width (in Java2D units). */
    public static final double DEFAULT_ARROW_WIDTH = 3.0;
  static {
    CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[6]++;
  }
    
    /** The angle of the arrow's line (in radians). */
    private double angle;

    /** 
     * The radius from the (x, y) point to the tip of the arrow (in Java2D 
     * units). 
     */
    private double tipRadius;

    /** 
     * The radius from the (x, y) point to the start of the arrow line (in 
     * Java2D units). 
     */
    private double baseRadius;

    /** The length of the arrow head (in Java2D units). */
    private double arrowLength;

    /** The arrow width (in Java2D units, per side). */
    private double arrowWidth;
    
    /** The arrow stroke. */
    private transient Stroke arrowStroke;

    /** The arrow paint. */
    private transient Paint arrowPaint;
    
    /** The radius from the base point to the anchor point for the label. */
    private double labelOffset;

    /**
     * Creates a new label and arrow annotation.
     *
     * @param label  the label (<code>null</code> permitted).
     * @param x  the x-coordinate (measured against the chart's domain axis).
     * @param y  the y-coordinate (measured against the chart's range axis).
     * @param angle  the angle of the arrow's line (in radians).
     */
    public XYPointerAnnotation(String label, double x, double y, double angle) {

        super(label, x, y);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[7]++;
        this.angle = angle;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[8]++;
        this.tipRadius = DEFAULT_TIP_RADIUS;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[9]++;
        this.baseRadius = DEFAULT_BASE_RADIUS;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[10]++;
        this.arrowLength = DEFAULT_ARROW_LENGTH;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[11]++;
        this.arrowWidth = DEFAULT_ARROW_WIDTH;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[12]++;
        this.labelOffset = DEFAULT_LABEL_OFFSET;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[13]++;
        this.arrowStroke = new BasicStroke(1.0f);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[14]++;
        this.arrowPaint = Color.black;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[15]++;

    }
    
    /**
     * Returns the angle of the arrow.
     * 
     * @return The angle (in radians).
     * 
     * @see #setAngle(double)
     */
    public double getAngle() {
        return this.angle;
    }
    
    /**
     * Sets the angle of the arrow.
     * 
     * @param angle  the angle (in radians).
     * 
     * @see #getAngle()
     */
    public void setAngle(double angle) {
        this.angle = angle;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[16]++;
    }
    
    /**
     * Returns the tip radius.
     * 
     * @return The tip radius (in Java2D units).
     * 
     * @see #setTipRadius(double)
     */
    public double getTipRadius() {
        return this.tipRadius;
    }
    
    /**
     * Sets the tip radius.
     * 
     * @param radius  the radius (in Java2D units).
     * 
     * @see #getTipRadius()
     */
    public void setTipRadius(double radius) {
        this.tipRadius = radius;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[17]++;
    }
    
    /**
     * Returns the base radius.
     * 
     * @return The base radius (in Java2D units).
     * 
     * @see #setBaseRadius(double)
     */
    public double getBaseRadius() {
        return this.baseRadius;
    }
    
    /**
     * Sets the base radius.
     * 
     * @param radius  the radius (in Java2D units).
     * 
     * @see #getBaseRadius()
     */
    public void setBaseRadius(double radius) {
        this.baseRadius = radius;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[18]++;
    }

    /**
     * Returns the label offset.
     * 
     * @return The label offset (in Java2D units).
     * 
     * @see #setLabelOffset(double)
     */
    public double getLabelOffset() {
        return this.labelOffset;
    }
    
    /**
     * Sets the label offset (from the arrow base, continuing in a straight 
     * line, in Java2D units).
     * 
     * @param offset  the offset (in Java2D units).
     * 
     * @see #getLabelOffset()
     */
    public void setLabelOffset(double offset) {
        this.labelOffset = offset;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[19]++;
    }
    
    /**
     * Returns the arrow length.
     * 
     * @return The arrow length.
     * 
     * @see #setArrowLength(double)
     */
    public double getArrowLength() {
        return this.arrowLength;
    }
    
    /**
     * Sets the arrow length.
     * 
     * @param length  the length.
     * 
     * @see #getArrowLength()
     */
    public void setArrowLength(double length) {
        this.arrowLength = length;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[20]++;
    }

    /**
     * Returns the arrow width.
     * 
     * @return The arrow width (in Java2D units).
     * 
     * @see #setArrowWidth(double)
     */
    public double getArrowWidth() {
        return this.arrowWidth;
    }
    
    /**
     * Sets the arrow width.
     * 
     * @param width  the width (in Java2D units).
     * 
     * @see #getArrowWidth()
     */
    public void setArrowWidth(double width) {
        this.arrowWidth = width;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[21]++;
    }
    
    /** 
     * Returns the stroke used to draw the arrow line.
     * 
     * @return The arrow stroke (never <code>null</code>).
     * 
     * @see #setArrowStroke(Stroke)
     */
    public Stroke getArrowStroke() {
        return this.arrowStroke;
    }

    /** 
     * Sets the stroke used to draw the arrow line.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getArrowStroke()
     */
    public void setArrowStroke(Stroke stroke) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[22]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[1]++;
            throw new IllegalArgumentException("Null 'stroke' not permitted.");

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[2]++;}
        this.arrowStroke = stroke;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[23]++;
    }
    
    /**
     * Returns the paint used for the arrow.
     * 
     * @return The arrow paint (never <code>null</code>).
     * 
     * @see #setArrowPaint(Paint)
     */
    public Paint getArrowPaint() {
        return this.arrowPaint;
    }
    
    /**
     * Sets the paint used for the arrow.
     * 
     * @param paint  the arrow paint (<code>null</code> not permitted).
     * 
     * @see #getArrowPaint()
     */
    public void setArrowPaint(Paint paint) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[4]++;}
        this.arrowPaint = paint;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[25]++;
    }
    
    /**
     * Draws the annotation.
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
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[26]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[27]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[28]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[29]++;
        double j2DX = domainAxis.valueToJava2D(getX(), dataArea, domainEdge);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[30]++;
        double j2DY = rangeAxis.valueToJava2D(getY(), dataArea, rangeEdge);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[31]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[5]++;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[32]++;
            double temp = j2DX;
            j2DX = j2DY;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[33]++;
            j2DY = temp;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[34]++;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[6]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[35]++;
        double startX = j2DX + Math.cos(this.angle) * this.baseRadius;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[36]++;
        double startY = j2DY + Math.sin(this.angle) * this.baseRadius;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[37]++;

        double endX = j2DX + Math.cos(this.angle) * this.tipRadius;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[38]++;
        double endY = j2DY + Math.sin(this.angle) * this.tipRadius;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[39]++;

        double arrowBaseX = endX + Math.cos(this.angle) * this.arrowLength;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[40]++;
        double arrowBaseY = endY + Math.sin(this.angle) * this.arrowLength;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[41]++;

        double arrowLeftX = arrowBaseX 
            + Math.cos(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[42]++;
        double arrowLeftY = arrowBaseY 
            + Math.sin(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[43]++;

        double arrowRightX = arrowBaseX 
            - Math.cos(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[44]++;
        double arrowRightY = arrowBaseY 
            - Math.sin(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[45]++;

        GeneralPath arrow = new GeneralPath();
        arrow.moveTo((float) endX, (float) endY);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[46]++;
        arrow.lineTo((float) arrowLeftX, (float) arrowLeftY);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[47]++;
        arrow.lineTo((float) arrowRightX, (float) arrowRightY);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[48]++;
        arrow.closePath();
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[49]++;

        g2.setStroke(this.arrowStroke);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[50]++;
        g2.setPaint(this.arrowPaint);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[51]++;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[52]++;
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(line);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[53]++;
        g2.fill(arrow);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[54]++;

        // draw the label
        g2.setFont(getFont());
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[55]++;
        g2.setPaint(getPaint());
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[56]++;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[57]++;
        double labelX = j2DX 
            + Math.cos(this.angle) * (this.baseRadius + this.labelOffset);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[58]++;
        double labelY = j2DY 
            + Math.sin(this.angle) * (this.baseRadius + this.labelOffset);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[59]++;
        Rectangle2D hotspot = TextUtilities.drawAlignedString(getText(), 
                g2, (float) labelX, (float) labelY, getTextAnchor());
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[60]++;

        String toolTip = getToolTipText();
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[61]++;
        String url = getURL();
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[62]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((toolTip != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((url != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[7]++;
            addEntity(info, hotspot, rendererIndex, toolTip, url);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[63]++;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[8]++;}
        
    }
    
    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[64]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[10]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[65]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof XYPointerAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[12]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[66]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[14]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[67]++;
        XYPointerAnnotation that = (XYPointerAnnotation) obj;
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[68]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.angle != that.angle) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[16]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[69]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.tipRadius != that.tipRadius) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[18]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[70]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.baseRadius != that.baseRadius) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[20]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[71]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.arrowLength != that.arrowLength) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[22]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[72]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.arrowWidth != that.arrowWidth) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[24]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[73]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.arrowPaint.equals(that.arrowPaint)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[26]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[74]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.arrowStroke, that.arrowStroke)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[28]++;}
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[75]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.labelOffset != that.labelOffset) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.branches[30]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[76]++;
        int result = super.hashCode();
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[77]++;
        long temp = Double.doubleToLongBits(this.angle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[78]++;
        temp = Double.doubleToLongBits(this.tipRadius);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[79]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[80]++;
        temp = Double.doubleToLongBits(this.baseRadius);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[81]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[82]++;
        temp = Double.doubleToLongBits(this.arrowLength);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[83]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[84]++;
        temp = Double.doubleToLongBits(this.arrowWidth);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[85]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[86]++;
        result = result * 37 + HashUtilities.hashCodeForPaint(this.arrowPaint);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[87]++;
        result = result * 37 + this.arrowStroke.hashCode();
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[88]++;
        temp = Double.doubleToLongBits(this.labelOffset);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[89]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[90]++;
        return super.hashCode();
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
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[91]++;
        SerialUtilities.writePaint(this.arrowPaint, stream);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[92]++;
        SerialUtilities.writeStroke(this.arrowStroke, stream);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[93]++;
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
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[94]++;
        this.arrowPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[95]++;
        this.arrowStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh.statements[96]++;
    }

}

class CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh ());
  }
    public static long[] statements = new long[97];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYPointerAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$kd1qxbsnp9zwque9jkefoxg5td4jty4qgcmsh () {
    super("org.jfree.chart.annotations.XYPointerAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 96; i++) {
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
    log.startNamedSection("org.jfree.chart.annotations.XYPointerAnnotation.java");
      for (int i = 1; i <= 96; i++) {
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

