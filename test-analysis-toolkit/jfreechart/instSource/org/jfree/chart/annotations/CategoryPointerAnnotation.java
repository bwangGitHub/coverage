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
 * ------------------------------
 * CategoryPointerAnnotation.java
 * ------------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-Oct-2006 : Version 1 (DG);
 * 06-Mar-2007 : Implemented hashCode() (DG);
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * An arrow and label that can be placed on a {@link CategoryPlot}.  The arrow 
 * is drawn at a user-definable angle so that it points towards the (category, 
 * value) location for the annotation.  
 * <p>
 * The arrow length (and its offset from the (category, value) location) is 
 * controlled by the tip radius and the base radius attributes.  Imagine two 
 * circles around the (category, value) coordinate: the inner circle defined by
 * the tip radius, and the outer circle defined by the base radius.  Now, draw 
 * the arrow starting at some point on the outer circle (the point is 
 * determined by the angle), with the arrow tip being drawn at a corresponding 
 * point on the inner circle.
 *
 * @since 1.0.3
 */
public class CategoryPointerAnnotation extends CategoryTextAnnotation 
                                 implements Cloneable, PublicCloneable, 
                                            Serializable {
  static {
    CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4031161445009858551L;
  static {
    CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[1]++;
  }
    
    /** The default tip radius (in Java2D units). */
    public static final double DEFAULT_TIP_RADIUS = 10.0;
  static {
    CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[2]++;
  }
    
    /** The default base radius (in Java2D units). */
    public static final double DEFAULT_BASE_RADIUS = 30.0;
  static {
    CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[3]++;
  }
    
    /** The default label offset (in Java2D units). */
    public static final double DEFAULT_LABEL_OFFSET = 3.0;
  static {
    CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[4]++;
  }
    
    /** The default arrow length (in Java2D units). */
    public static final double DEFAULT_ARROW_LENGTH = 5.0;
  static {
    CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[5]++;
  }

    /** The default arrow width (in Java2D units). */
    public static final double DEFAULT_ARROW_WIDTH = 3.0;
  static {
    CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[6]++;
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
     * @param key  the category key.
     * @param value  the y-value (measured against the chart's range axis).
     * @param angle  the angle of the arrow's line (in radians).
     */
    public CategoryPointerAnnotation(String label, Comparable key, double value,
            double angle) {

        super(label, key, value);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[7]++;
        this.angle = angle;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[8]++;
        this.tipRadius = DEFAULT_TIP_RADIUS;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[9]++;
        this.baseRadius = DEFAULT_BASE_RADIUS;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[10]++;
        this.arrowLength = DEFAULT_ARROW_LENGTH;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[11]++;
        this.arrowWidth = DEFAULT_ARROW_WIDTH;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[12]++;
        this.labelOffset = DEFAULT_LABEL_OFFSET;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[13]++;
        this.arrowStroke = new BasicStroke(1.0f);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[14]++;
        this.arrowPaint = Color.black;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[15]++;

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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[16]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[17]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[18]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[19]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[20]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[21]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[22]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[1]++;
            throw new IllegalArgumentException("Null 'stroke' not permitted.");

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[2]++;}
        this.arrowStroke = stroke;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[23]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[4]++;}
        this.arrowPaint = paint;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[25]++;
    }
    
    /**
     * Draws the annotation.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     */
    public void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea,
            CategoryAxis domainAxis, ValueAxis rangeAxis) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[26]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[27]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[28]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[29]++;
        CategoryDataset dataset = plot.getDataset();
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[30]++;
        int catIndex = dataset.getColumnIndex(getCategory());
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[31]++;
        int catCount = dataset.getColumnCount();
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[32]++;
        double j2DX = domainAxis.getCategoryMiddle(catIndex, catCount, 
                dataArea, domainEdge);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[33]++;
        double j2DY = rangeAxis.valueToJava2D(getValue(), dataArea, rangeEdge);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[34]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[5]++;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[35]++;
            double temp = j2DX;
            j2DX = j2DY;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[36]++;
            j2DY = temp;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[37]++;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[6]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[38]++;
        double startX = j2DX + Math.cos(this.angle) * this.baseRadius;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[39]++;
        double startY = j2DY + Math.sin(this.angle) * this.baseRadius;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[40]++;

        double endX = j2DX + Math.cos(this.angle) * this.tipRadius;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[41]++;
        double endY = j2DY + Math.sin(this.angle) * this.tipRadius;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[42]++;

        double arrowBaseX = endX + Math.cos(this.angle) * this.arrowLength;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[43]++;
        double arrowBaseY = endY + Math.sin(this.angle) * this.arrowLength;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[44]++;

        double arrowLeftX = arrowBaseX 
            + Math.cos(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[45]++;
        double arrowLeftY = arrowBaseY 
            + Math.sin(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[46]++;

        double arrowRightX = arrowBaseX 
            - Math.cos(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[47]++;
        double arrowRightY = arrowBaseY 
            - Math.sin(this.angle + Math.PI / 2.0) * this.arrowWidth;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[48]++;

        GeneralPath arrow = new GeneralPath();
        arrow.moveTo((float) endX, (float) endY);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[49]++;
        arrow.lineTo((float) arrowLeftX, (float) arrowLeftY);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[50]++;
        arrow.lineTo((float) arrowRightX, (float) arrowRightY);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[51]++;
        arrow.closePath();
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[52]++;

        g2.setStroke(this.arrowStroke);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[53]++;
        g2.setPaint(this.arrowPaint);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[54]++;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[55]++;
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(line);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[56]++;
        g2.fill(arrow);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[57]++;

        // draw the label
        g2.setFont(getFont());
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[58]++;
        g2.setPaint(getPaint());
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[59]++;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[60]++;
        double labelX = j2DX 
            + Math.cos(this.angle) * (this.baseRadius + this.labelOffset);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[61]++;
        double labelY = j2DY 
            + Math.sin(this.angle) * (this.baseRadius + this.labelOffset);
        /* Rectangle2D hotspot = */ TextUtilities.drawAlignedString(getText(), 
                g2, (float) labelX, (float) labelY, getTextAnchor());
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[62]++;
        // TODO: implement the entity for the annotation
        
    }
    
    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[63]++;
int CodeCoverConditionCoverageHelper_C4;
        
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[8]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[64]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryPointerAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[10]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[65]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[12]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[66]++;
        CategoryPointerAnnotation that = (CategoryPointerAnnotation) obj;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[67]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.angle != that.angle) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[14]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[68]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.tipRadius != that.tipRadius) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[16]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[69]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.baseRadius != that.baseRadius) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[18]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[70]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.arrowLength != that.arrowLength) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[20]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[71]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.arrowWidth != that.arrowWidth) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[22]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[72]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.arrowPaint.equals(that.arrowPaint)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[24]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[73]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.arrowStroke, that.arrowStroke)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[26]++;}
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[74]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.labelOffset != that.labelOffset) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.branches[28]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[75]++;
        int result = 193;
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[76]++;
        long temp = Double.doubleToLongBits(this.angle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[77]++;
        temp = Double.doubleToLongBits(this.tipRadius);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[78]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[79]++;
        temp = Double.doubleToLongBits(this.baseRadius);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[80]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[81]++;
        temp = Double.doubleToLongBits(this.arrowLength);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[82]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[83]++;
        temp = Double.doubleToLongBits(this.arrowWidth);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[84]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[85]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.arrowPaint);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[86]++;
        result = 37 * result + this.arrowStroke.hashCode();
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[87]++;
        temp = Double.doubleToLongBits(this.labelOffset);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[88]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[89]++;
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
     * @throws IOException if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[90]++;
        SerialUtilities.writePaint(this.arrowPaint, stream);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[91]++;
        SerialUtilities.writeStroke(this.arrowStroke, stream);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[92]++;
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
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[93]++;
        this.arrowPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[94]++;
        this.arrowStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1.statements[95]++;
    }

}

class CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1 ());
  }
    public static long[] statements = new long[96];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.CategoryPointerAnnotation.java";
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

  public CodeCoverCoverageCounter$171h2mugi38prhh7gtroxy0skzss91q8f462nwz5f4l0pk1 () {
    super("org.jfree.chart.annotations.CategoryPointerAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 95; i++) {
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
    log.startNamedSection("org.jfree.chart.annotations.CategoryPointerAnnotation.java");
      for (int i = 1; i <= 95; i++) {
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

