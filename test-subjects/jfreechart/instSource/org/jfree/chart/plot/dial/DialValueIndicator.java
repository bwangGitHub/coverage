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
 * DialValueIndicator.java
 * -----------------------
 * (C) Copyright 2006-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 17-Oct-2007 : Updated equals() (DG);
 * 24-Oct-2007 : Added default constructor and missing event notification (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.HashUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.ui.TextAnchor;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A value indicator for a {@link DialPlot}.
 * 
 * @since 1.0.7
 */
public class DialValueIndicator extends AbstractDialLayer implements DialLayer, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = 803094354130942585L;
  static {
    CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[1]++;
  }

    /** The dataset index. */
    private int datasetIndex;
    
    /** The angle that defines the anchor point. */
    private double angle;
    
    /** The radius that defines the anchor point. */
    private double radius;
    
    /** The frame anchor. */
    private RectangleAnchor frameAnchor;
    
    /** The template value. */
    private Number templateValue;
    
    /** The formatter. */
    private NumberFormat formatter;

    /** The font. */
    private Font font;
    
    /** The paint. */
    private transient Paint paint;
    
    /** The background paint. */
    private transient Paint backgroundPaint;
    
    /** The outline stroke. */
    private transient Stroke outlineStroke;
    
    /** The outline paint. */
    private transient Paint outlinePaint;
    
    /** The insets. */
    private RectangleInsets insets;
    
    /** The value anchor. */
    private RectangleAnchor valueAnchor;
    
    /** The text anchor for displaying the value. */
    private TextAnchor textAnchor;
    
    /**
     * Creates a new instance of <code>DialValueIndicator</code>.
     */
    public DialValueIndicator() {
        this(0);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[2]++;
    }
    
    /** 
     * Creates a new instance of <code>DialValueIndicator</code>.
     * 
     * @param datasetIndex  the dataset index.
     */
    public DialValueIndicator(int datasetIndex) {
        this.datasetIndex = datasetIndex;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[3]++;
        this.angle = -90.0;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[4]++;
        this.radius = 0.3;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[5]++;
        this.frameAnchor = RectangleAnchor.CENTER;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[6]++;
        this.templateValue = new Double(100.0);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[7]++;
        this.formatter = new DecimalFormat("0.0");
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[8]++;
        this.font = new Font("Dialog", Font.BOLD, 14);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[9]++;
        this.paint = Color.black;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[10]++;
        this.backgroundPaint = Color.white;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[11]++;
        this.outlineStroke = new BasicStroke(1.0f);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[12]++;
        this.outlinePaint = Color.blue;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[13]++;
        this.insets = new RectangleInsets(4, 4, 4, 4);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[14]++;
        this.valueAnchor = RectangleAnchor.RIGHT;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[15]++;
        this.textAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[16]++;
    }
    
    /**
     * Returns the index of the dataset from which this indicator fetches its 
     * current value.
     * 
     * @return The dataset index.
     * 
     * @see #setDatasetIndex(int)
     */
    public int getDatasetIndex() {
        return this.datasetIndex;
    }
    
    /**
     * Sets the dataset index and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param index  the index.
     * 
     * @see #getDatasetIndex()
     */
    public void setDatasetIndex(int index) {
        this.datasetIndex = index;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[17]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[18]++;
    }
    
    /**
     * Returns the angle for the anchor point.  The angle is specified in 
     * degrees using the same orientation as Java's <code>Arc2D</code> class.
     * 
     * @return The angle (in degrees).
     * 
     * @see #setAngle(double)
     */
    public double getAngle() {
        return this.angle;
    }
    
    /**
     * Sets the angle for the anchor point and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param angle  the angle (in degrees).
     * 
     * @see #getAngle()
     */
    public void setAngle(double angle) {
        this.angle = angle;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[19]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[20]++;
    }
    
    /**
     * Returns the radius.
     * 
     * @return The radius.
     * 
     * @see #setRadius(double)
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * Sets the radius and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param radius  the radius.
     * 
     * @see #getRadius()
     */
    public void setRadius(double radius) {
        this.radius = radius;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[21]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[22]++;
    }
    
    /**
     * Returns the frame anchor.
     * 
     * @return The frame anchor.
     * 
     * @see #setFrameAnchor(RectangleAnchor)
     */
    public RectangleAnchor getFrameAnchor() {
        return this.frameAnchor;
    }
    
    /**
     * Sets the frame anchor and sends a {@link DialLayerChangeEvent} to all
     * registered listeners.
     * 
     * @param anchor  the anchor (<code>null</code> not permitted).
     * 
     * @see #getFrameAnchor()
     */
    public void setFrameAnchor(RectangleAnchor anchor) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[23]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[1]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[2]++;}
        this.frameAnchor = anchor;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[24]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[25]++;
    }
    
    /**
     * Returns the template value.
     * 
     * @return The template value (never <code>null</code>).
     * 
     * @see #setTemplateValue(Number)
     */
    public Number getTemplateValue() {
        return this.templateValue;
    }
    
    /**
     * Sets the template value and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     * 
     * @param value  the value (<code>null</code> not permitted).
     * 
     * @see #setTemplateValue(Number)
     */
    public void setTemplateValue(Number value) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[3]++;
            throw new IllegalArgumentException("Null 'value' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[4]++;}
        this.templateValue = value;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[27]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[28]++;
    }
    
    /**
     * Returns the formatter used to format the value.
     * 
     * @return The formatter (never <code>null</code>).
     * 
     * @see #setNumberFormat(NumberFormat)
     */
    public NumberFormat getNumberFormat() {
        return this.formatter;
    }
    
    /**
     * Sets the formatter used to format the value and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param formatter  the formatter (<code>null</code> not permitted).
     * 
     * @see #getNumberFormat()
     */
    public void setNumberFormat(NumberFormat formatter) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[29]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[5]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[6]++;}
        this.formatter = formatter;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[30]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[31]++;
    }
    
    /**
     * Returns the font.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #getFont()
     */
    public Font getFont() {
        return this.font;
    }
    
    /**
     * Sets the font and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     */
    public void setFont(Font font) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[7]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[8]++;}
        this.font = font;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[33]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[34]++;
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
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[9]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[10]++;}
        this.paint = paint;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[36]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[37]++;
    }
    
    /**
     * Returns the background paint.
     * 
     * @return The background paint.
     * 
     * @see #setBackgroundPaint(Paint)
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    /**
     * Sets the background paint and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBackgroundPaint()
     */
    public void setBackgroundPaint(Paint paint) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[11]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[12]++;}
        this.backgroundPaint = paint;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[39]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[40]++;      
    }
    
    /**
     * Returns the outline stroke.
     * 
     * @return The outline stroke (never <code>null</code>).
     * 
     * @see #setOutlineStroke(Stroke)
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
     
    /**
     * Sets the outline stroke and sends a {@link DialLayerChangeEvent} to
     * all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getOutlineStroke()
     */
    public void setOutlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[13]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[14]++;}
        this.outlineStroke = stroke;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[42]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[43]++;
    }
    
    /**
     * Returns the outline paint.
     * 
     * @return The outline paint (never <code>null</code>).
     *
     * @see #setOutlinePaint(Paint)
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    /**
     * Sets the outline paint and sends a {@link DialLayerChangeEvent} to all
     * registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getOutlinePaint()
     */
    public void setOutlinePaint(Paint paint) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[15]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[16]++;}
        this.outlinePaint = paint;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[45]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[46]++;
    }
    
    /**
     * Returns the insets.
     * 
     * @return The insets (never <code>null</code>).
     * 
     * @see #setInsets(RectangleInsets)
     */
    public RectangleInsets getInsets() {
        return this.insets;
    }
    
    /**
     * Sets the insets and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param insets  the insets (<code>null</code> not permitted).
     * 
     * @see #getInsets()
     */
    public void setInsets(RectangleInsets insets) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((insets == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[17]++;
            throw new IllegalArgumentException("Null 'insets' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[18]++;}
        this.insets = insets;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[48]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[49]++;        
    }
    
    /**
     * Returns the value anchor.
     * 
     * @return The value anchor (never <code>null</code>).
     * 
     * @see #setValueAnchor(RectangleAnchor)
     */
    public RectangleAnchor getValueAnchor() {
        return this.valueAnchor;
    }
    
    /**
     * Sets the value anchor and sends a {@link DialLayerChangeEvent} to all
     * registered listeners.
     * 
     * @param anchor  the anchor (<code>null</code> not permitted).
     * 
     * @see #getValueAnchor()
     */
    public void setValueAnchor(RectangleAnchor anchor) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[50]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[19]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[20]++;}
        this.valueAnchor = anchor;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[51]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[52]++;                
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
     * Sets the text anchor and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param anchor  the anchor (<code>null</code> not permitted).
     * 
     * @see #getTextAnchor()
     */
    public void setTextAnchor(TextAnchor anchor) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[21]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[22]++;}
        this.textAnchor = anchor;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[54]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[55]++;                        
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
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[56]++;

        // work out the anchor point
        Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, 
                this.radius);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[57]++;
        Arc2D arc = new Arc2D.Double(f, this.angle, 0.0, Arc2D.OPEN);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[58]++;
        Point2D pt = arc.getStartPoint();
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[59]++;
        
        // calculate the bounds of the template value
        FontMetrics fm = g2.getFontMetrics(this.font);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[60]++;
        String s = this.formatter.format(this.templateValue);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[61]++;
        Rectangle2D tb = TextUtilities.getTextBounds(s, g2, fm);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[62]++;

        // align this rectangle to the frameAnchor
        Rectangle2D bounds = RectangleAnchor.createRectangle(new Size2D(
                tb.getWidth(), tb.getHeight()), pt.getX(), pt.getY(), 
                this.frameAnchor);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[63]++;
        
        // add the insets
        Rectangle2D fb = this.insets.createOutsetRectangle(bounds);

        // draw the background
        g2.setPaint(this.backgroundPaint);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[64]++;
        g2.fill(fb);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[65]++;

        // draw the border
        g2.setStroke(this.outlineStroke);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[66]++;
        g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[67]++;
        g2.draw(fb);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[68]++;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[69]++;
        
        
        // now find the text anchor point
        double value = plot.getValue(this.datasetIndex);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[70]++;
        String valueStr = this.formatter.format(value);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[71]++;
        Point2D pt2 = RectangleAnchor.coordinates(bounds, this.valueAnchor);
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[72]++;
        g2.setFont(this.font);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[73]++;
        TextUtilities.drawAlignedString(valueStr, g2, (float) pt2.getX(), 
                (float) pt2.getY(), this.textAnchor);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[74]++;
        
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[75]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[24]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[76]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((obj instanceof DialValueIndicator) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[26]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[77]++;
        DialValueIndicator that = (DialValueIndicator) obj;
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[78]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.datasetIndex != that.datasetIndex) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[28]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[79]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.angle != that.angle) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[30]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[80]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.radius != that.radius) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[31]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[32]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.frameAnchor.equals(that.frameAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[34]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[82]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.templateValue.equals(that.templateValue)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[36]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[83]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.font.equals(that.font)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[38]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[84]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[40]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[85]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[42]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[86]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.outlineStroke.equals(that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[44]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[87]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[46]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[88]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.insets.equals(that.insets)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[48]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[89]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.valueAnchor.equals(that.valueAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[50]++;}
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[90]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.textAnchor.equals(that.textAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.branches[52]++;}
        
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[91]++;
        int result = 193;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[92]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.backgroundPaint);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[93]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(
                this.outlinePaint);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[94]++;
        result = 37 * result + this.outlineStroke.hashCode();
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[95]++;
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
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[96]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[97]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[98]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[99]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[100]++;
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
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[101]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[102]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[103]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[104]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh.statements[105]++;
    }

}

class CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh ());
  }
    public static long[] statements = new long[106];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.DialValueIndicator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 26; i++) {
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

  public CodeCoverCoverageCounter$27tlp61ox8fubfdzjba9w1y97wk4t6uk8hgh () {
    super("org.jfree.chart.plot.dial.DialValueIndicator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 105; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.DialValueIndicator.java");
      for (int i = 1; i <= 105; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
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

