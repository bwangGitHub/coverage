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
 * XYTextAnnotation.java
 * ---------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 28-Aug-2002 : Version 1 (DG);
 * 07-Nov-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Jan-2003 : Reviewed Javadocs (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 02-Jul-2003 : Added new text alignment and rotation options (DG);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 17-Jan-2003 : Added fix for bug 878706, where the annotation is placed 
 *               incorrectly for a plot with horizontal orientation (thanks to
 *               Ed Yu for the fix) (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 26-Jan-2006 : Fixed equals() method (bug 1415480) (DG);
 * 06-Mar-2007 : Added argument checks, re-implemented hashCode() method (DG);
 *
 */

package org.jfree.chart.annotations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
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
import org.jfree.ui.TextAnchor;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A text annotation that can be placed at a particular (x, y) location on an 
 * {@link XYPlot}.
 */
public class XYTextAnnotation extends AbstractXYAnnotation
                              implements Cloneable, PublicCloneable, 
                                         Serializable {
  static {
    CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2946063342782506328L;
  static {
    CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[1]++;
  }
    
    /** The default font. */
    public static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 
            10);
  static {
    CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[2]++;
  }

    /** The default paint. */
    public static final Paint DEFAULT_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[3]++;
  }
    
    /** The default text anchor. */
    public static final TextAnchor DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;
  static {
    CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[4]++;
  }

    /** The default rotation anchor. */    
    public static final TextAnchor DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;
  static {
    CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[5]++;
  }
    
    /** The default rotation angle. */
    public static final double DEFAULT_ROTATION_ANGLE = 0.0;
  static {
    CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[6]++;
  }

    /** The text. */
    private String text;

    /** The font. */
    private Font font;

    /** The paint. */
    private transient Paint paint;
    
    /** The x-coordinate. */
    private double x;

    /** The y-coordinate. */
    private double y;

    /** The text anchor (to be aligned with (x, y)). */
    private TextAnchor textAnchor;
    
    /** The rotation anchor. */
    private TextAnchor rotationAnchor;
    
    /** The rotation angle. */
    private double rotationAngle;
    
    /**
     * Creates a new annotation to be displayed at the given coordinates.  The
     * coordinates are specified in data space (they will be converted to 
     * Java2D space for display).
     *
     * @param text  the text (<code>null</code> not permitted).
     * @param x  the x-coordinate (in data space).
     * @param y  the y-coordinate (in data space).
     */
    public XYTextAnnotation(String text, double x, double y) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[1]++;
            throw new IllegalArgumentException("Null 'text' argument.");

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[2]++;}
        this.text = text;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[8]++;
        this.font = DEFAULT_FONT;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[9]++;
        this.paint = DEFAULT_PAINT;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[10]++;
        this.x = x;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[11]++;
        this.y = y;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[12]++;
        this.textAnchor = DEFAULT_TEXT_ANCHOR;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[13]++;
        this.rotationAnchor = DEFAULT_ROTATION_ANCHOR;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[14]++;
        this.rotationAngle = DEFAULT_ROTATION_ANGLE;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[15]++;
    }
    
    /**
     * Returns the text for the annotation.
     *
     * @return The text (never <code>null</code>).
     * 
     * @see #setText(String)
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets the text for the annotation.
     * 
     * @param text  the text (<code>null</code> not permitted).
     * 
     * @see #getText()
     */
    public void setText(String text) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[3]++;
            throw new IllegalArgumentException("Null 'text' argument.");

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[4]++;}
        this.text = text;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[17]++;
    }
    
    /**
     * Returns the font for the annotation.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setFont(Font)
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Sets the font for the annotation.
     * 
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getFont()
     */
    public void setFont(Font font) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[5]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[6]++;}
        this.font = font;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[19]++;
    }
    
    /**
     * Returns the paint for the annotation.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Sets the paint for the annotation.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[7]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[8]++;}
        this.paint = paint;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[21]++;
    }

    /**
     * Returns the text anchor.
     * 
     * @return The text anchor (never <code>null</code>).
     * 
     * @see #setTextAnchor(TextAnchor)
     */
    public TextAnchor getTextAnchor() {
        return this.textAnchor;
    }
    
    /**
     * Sets the text anchor (the point on the text bounding rectangle that is 
     * aligned to the (x, y) coordinate of the annotation).
     * 
     * @param anchor  the anchor point (<code>null</code> not permitted).
     * 
     * @see #getTextAnchor()
     */
    public void setTextAnchor(TextAnchor anchor) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[9]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[10]++;}
        this.textAnchor = anchor;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[23]++;
    }
    
    /**
     * Returns the rotation anchor.
     * 
     * @return The rotation anchor point (never <code>null</code>).
     * 
     * @see #setRotationAnchor(TextAnchor)
     */
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }
    
    /**
     * Sets the rotation anchor point.
     * 
     * @param anchor  the anchor (<code>null</code> not permitted).
     * 
     * @see #getRotationAnchor()
     */
    public void setRotationAnchor(TextAnchor anchor) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[11]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[12]++;}
        this.rotationAnchor = anchor;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[25]++;    
    }
    
    /**
     * Returns the rotation angle.
     * 
     * @return The rotation angle.
     * 
     * @see #setRotationAngle(double)
     */
    public double getRotationAngle() {
        return this.rotationAngle; 
    }
    
    /**
     * Sets the rotation angle.  The angle is measured clockwise in radians.
     * 
     * @param angle  the angle (in radians).
     * 
     * @see #getRotationAngle()
     */
    public void setRotationAngle(double angle) {
        this.rotationAngle = angle;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[26]++;    
    }
    
    /**
     * Returns the x coordinate for the text anchor point (measured against the
     * domain axis).
     * 
     * @return The x coordinate (in data space).
     * 
     * @see #setX(double)
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Sets the x coordinate for the text anchor point (measured against the 
     * domain axis).
     * 
     * @param x  the x coordinate (in data space).
     * 
     * @see #getX()
     */
    public void setX(double x) {
        this.x = x;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[27]++;
    }
    
    /**
     * Returns the y coordinate for the text anchor point (measured against the
     * range axis).
     * 
     * @return The y coordinate (in data space).
     * 
     * @see #setY(double)
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Sets the y coordinate for the text anchor point (measured against the
     * range axis).
     * 
     * @param y  the y coordinate.
     * 
     * @see #getY()
     */
    public void setY(double y) {
        this.y = y;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[28]++;
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
     * @param info  an optional info object that will be populated with
     *              entity information.
     */
    public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis, 
                     int rendererIndex,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[29]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[30]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[31]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[32]++;

        float anchorX = (float) domainAxis.valueToJava2D(
                this.x, dataArea, domainEdge);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[33]++;
        float anchorY = (float) rangeAxis.valueToJava2D(
                this.y, dataArea, rangeEdge);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[13]++;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[35]++;
            float tempAnchor = anchorX;
            anchorX = anchorY;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[36]++;
            anchorY = tempAnchor;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[37]++;

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[14]++;}
        
        g2.setFont(getFont());
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[38]++;
        g2.setPaint(getPaint());
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[39]++;
        TextUtilities.drawRotatedString(getText(), g2, anchorX, anchorY,
                getTextAnchor(), getRotationAngle(), getRotationAnchor());
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[40]++;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[41]++;
        Shape hotspot = TextUtilities.calculateRotatedStringBounds(
                getText(), g2, anchorX, anchorY, getTextAnchor(), 
                getRotationAngle(), getRotationAnchor());
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[42]++;
        
        String toolTip = getToolTipText();
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[43]++;
        String url = getURL();
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((toolTip != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((url != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[15]++;
            addEntity(info, hotspot, rendererIndex, toolTip, url);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[45]++;

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[16]++;}

    }
    
    /**
     * Tests this annotation for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[17]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[18]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj instanceof XYTextAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[19]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[20]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[22]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[49]++;
        XYTextAnnotation that = (XYTextAnnotation) obj;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.text.equals(that.text)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[23]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[24]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.x != that.x) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[26]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.y != that.y) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[28]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.font.equals(that.font)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[29]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[30]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[54]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[31]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[32]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.rotationAnchor.equals(that.rotationAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[33]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[34]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[56]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.rotationAngle != that.rotationAngle) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[36]++;}
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[57]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.textAnchor.equals(that.textAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[37]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.branches[38]++;}
        return true;   
    }
    
    /**
     * Returns a hash code for the object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[58]++;
        int result = 193;
        result = 37 * this.text.hashCode();
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[59]++;
        result = 37 * this.font.hashCode();
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[60]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[61]++;
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[62]++;
        long temp = Double.doubleToLongBits(this.x);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[63]++;
        temp = Double.doubleToLongBits(this.y);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[64]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[65]++;
        result = 37 * result + this.textAnchor.hashCode();
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[66]++;
        result = 37 * result + this.rotationAnchor.hashCode();
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[67]++;
        temp = Double.doubleToLongBits(this.rotationAngle);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[68]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[69]++;
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
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[70]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[71]++;
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
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[72]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1.statements[73]++;
    }

}

class CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1 ());
  }
    public static long[] statements = new long[74];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.XYTextAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$21dyfhtvl62zsd64l9zfjkxs2tdqcv6g1 () {
    super("org.jfree.chart.annotations.XYTextAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 73; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.annotations.XYTextAnnotation.java");
      for (int i = 1; i <= 73; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

