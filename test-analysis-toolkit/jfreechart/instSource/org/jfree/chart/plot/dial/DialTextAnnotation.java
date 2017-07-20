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
 * -----------------------
 * DialTextAnnotation.java
 * -----------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 08-Mar-2007 : Fix in hashCode() (DG);
 * 17-Oct-2007 : Updated equals() (DG);
 * 24-Oct-2007 : Added getAnchor() and setAnchor() methods (DG);
 *
 */

package org.jfree.chart.plot.dial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A text annotation for a {@link DialPlot}.
 * 
 * @since 1.0.7
 */
public class DialTextAnnotation extends AbstractDialLayer implements DialLayer, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = 3065267524054428071L;
  static {
    CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[1]++;
  }

    /** The label text. */
    private String label;
    
    /** The font. */
    private Font font;
    
    /** 
     * The paint for the label.  This field is transient because it requires
     * special handling for serialization.
     */
    private transient Paint paint;

    /** The angle that defines the anchor point for the annotation. */
    private double angle;
    
    /** The radius that defines the anchor point for the annotation. */
    private double radius;
        
    /** The text anchor to be aligned to the annotation's anchor point. */
    private TextAnchor anchor;
    
    /** 
     * Creates a new instance of <code>DialTextAnnotation</code>.
     * 
     * @param label  the label (<code>null</code> not permitted).
     */
    public DialTextAnnotation(String label) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[1]++;
            throw new IllegalArgumentException("Null 'label' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[2]++;}
        this.angle = -90.0;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[3]++;
        this.radius = 0.3;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[4]++;
        this.font = new Font("Dialog", Font.BOLD, 14);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[5]++;
        this.paint = Color.black;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[6]++;
        this.label = label;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[7]++;
        this.anchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[8]++;
    }
    
    /**
     * Returns the label text.
     * 
     * @return The label text (never <code>null</code).
     * 
     * @see #setLabel(String)
     */
    public String getLabel() {
        return this.label;
    }
    
    /**
     * Sets the label and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * 
     * @see #getLabel()
     */
    public void setLabel(String label) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[3]++;
            throw new IllegalArgumentException("Null 'label' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[4]++;}
        this.label = label;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[10]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[11]++;
    }
    
    /**
     * Returns the font used to display the label.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setFont(Font)
     */
    public Font getFont() {
        return this.font;
    }
    
    /**
     * Sets the font used to display the label and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getFont()
     */
    public void setFont(Font font) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[5]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[6]++;}
        this.font = font;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[13]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[14]++;
    }
    
    /**
     * Returns the paint used to display the label.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Sets the paint used to display the label and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[7]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[8]++;}
        this.paint = paint;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[16]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[17]++;
    }
    
    /**
     * Returns the angle used to calculate the anchor point.
     * 
     * @return The angle (in degrees).
     * 
     * @see #setAngle(double)
     * @see #getRadius()
     */
    public double getAngle() {
        return this.angle;
    }
    
    /**
     * Sets the angle used to calculate the anchor point and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param angle  the angle (in degrees).
     * 
     * @see #getAngle()
     * @see #setRadius(double)
     */
    public void setAngle(double angle) {
        this.angle = angle;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[18]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[19]++;
    }
    
    /**
     * Returns the radius used to calculate the anchor point.  This is 
     * specified as a percentage relative to the dial's framing rectangle.
     * 
     * @return The radius.
     * 
     * @see #setRadius(double)
     * @see #getAngle()
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * Sets the radius used to calculate the anchor point and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param radius  the radius (as a percentage of the dial's framing 
     *                rectangle).
     * 
     * @see #getRadius()
     * @see #setAngle(double)
     */
    public void setRadius(double radius) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((radius < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[9]++;
            throw new IllegalArgumentException(
                    "The 'radius' cannot be negative.");

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[10]++;}
        this.radius = radius;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[21]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[22]++;
    }
    
    /**
     * Returns the text anchor point that will be aligned to the position
     * specified by {@link #getAngle()} and {@link #getRadius()}.
     * 
     * @return The anchor point.
     * 
     * @see #setAnchor(TextAnchor)
     */
    public TextAnchor getAnchor() {
        return this.anchor;
    }
    
    /**
     * Sets the text anchor point and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     * 
     * @param anchor  the anchor point (<code>null</code> not permitted).
     * 
     * @see #getAnchor()
     */
    public void setAnchor(TextAnchor anchor) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[11]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[12]++;}
        this.anchor = anchor;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[24]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[25]++;        
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
     * Draws the background to the specified graphics device.  If the dial
     * frame specifies a window, the clipping region will already have been 
     * set to this window before this method is called.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param plot  the plot (ignored here).
     * @param frame  the dial frame (ignored here).
     * @param view  the view rectangle (<code>null</code> not permitted). 
     */
    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
            Rectangle2D view) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[26]++;

        // work out the anchor point
        Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, 
                this.radius);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[27]++;
        Arc2D arc = new Arc2D.Double(f, this.angle, 0.0, Arc2D.OPEN);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[28]++;
        Point2D pt = arc.getStartPoint();
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[29]++;
        g2.setFont(this.font);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[30]++;
        TextUtilities.drawAlignedString(this.label, g2, (float) pt.getX(), 
                (float) pt.getY(), this.anchor);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[31]++;
        
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[14]++;}
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof DialTextAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[16]++;}
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[34]++;
        DialTextAnnotation that = (DialTextAnnotation) obj;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.label.equals(that.label)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[18]++;}
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.font.equals(that.font)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[20]++;}
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[22]++;}
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.radius != that.radius) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[24]++;}
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[39]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.angle != that.angle) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[26]++;}
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[40]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.anchor.equals(that.anchor)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.branches[28]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[41]++;
        int result = 193;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[42]++;
        result = 37 * result + this.font.hashCode();
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[43]++;
        result = 37 * result + this.label.hashCode();
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[44]++;
        result = 37 * result + this.anchor.hashCode();
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[45]++;
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[46]++;
        long temp = Double.doubleToLongBits(this.angle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[47]++;
        temp = Double.doubleToLongBits(this.radius);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[48]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[49]++;
        return result;
    }
    
    /**
     * Returns a clone of this instance.
     *
     * @return The clone.
     *
     * @throws CloneNotSupportedException if some attribute of this instance
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
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[50]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[51]++;
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
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[52]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd.statements[53]++;
    }
    
}

class CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd ());
  }
    public static long[] statements = new long[54];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.DialTextAnnotation.java";
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

  public CodeCoverCoverageCounter$27tlp61nqzrc3fkp3bdv3rxz1xu5w7yacktd () {
    super("org.jfree.chart.plot.dial.DialTextAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 53; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.dial.DialTextAnnotation.java");
      for (int i = 1; i <= 53; i++) {
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

