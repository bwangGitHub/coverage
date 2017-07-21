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
 * -----------
 * Marker.java
 * -----------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *
 * Changes
 * -------
 * 02-Jul-2002 : Added extra constructor, standard header and Javadoc 
 *               comments (DG);
 * 20-Aug-2002 : Added the outline stroke attribute (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 16-Oct-2002 : Added new constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 21-May-2003 : Added labels (DG);
 * 11-Sep-2003 : Implemented Cloneable (NB);
 * 05-Nov-2003 : Added checks to ensure some attributes are never null (DG);
 * 11-Feb-2003 : Moved to org.jfree.chart.plot package, plus significant API 
 *               changes to support IntervalMarker in plots (DG);
 * 14-Jun-2004 : Updated equals() method (DG);
 * 21-Jan-2005 : Added settings to control direction of horizontal and 
 *               vertical label offsets (DG);
 * 01-Jun-2005 : Modified to use only one label offset type - this will be 
 *               applied to the domain or range axis as appropriate (DG);
 * 06-Jun-2005 : Fix equals() method to handle GradientPaint (DG);
 * 19-Aug-2005 : Changed constructor from public --> protected (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added MarkerChangeListener support (DG);
 * 26-Sep-2007 : Fix for serialization bug 1802195 (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

import org.jfree.chart.event.MarkerChangeEvent;
import org.jfree.chart.event.MarkerChangeListener;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * The base class for markers that can be added to plots to highlight a value 
 * or range of values.
 * <br><br>
 * An event notification mechanism was added to this class in JFreeChart 
 * version 1.0.3.
 */
public abstract class Marker implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$br5emo76k03vkjfq9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -734389651405327166L;
  static {
    CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[1]++;
  }

    /** The paint. */
    private transient Paint paint;

    /** The stroke. */
    private transient Stroke stroke;
    
    /** The outline paint. */
    private transient Paint outlinePaint;

    /** The outline stroke. */
    private transient Stroke outlineStroke;

    /** The alpha transparency. */
    private float alpha;

    /** The label. */
    private String label = null;
  {
    CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[2]++;
  }

    /** The label font. */
    private Font labelFont;

    /** The label paint. */
    private transient Paint labelPaint;

    /** The label position. */
    private RectangleAnchor labelAnchor;
    
    /** The text anchor for the label. */
    private TextAnchor labelTextAnchor;

    /** The label offset from the marker rectangle. */
    private RectangleInsets labelOffset;
    
    /** 
     * The offset type for the domain or range axis (never <code>null</code>). 
     */
    private LengthAdjustmentType labelOffsetType;
    
    /** Storage for registered change listeners. */
    private transient EventListenerList listenerList;

    /**
     * Creates a new marker with default attributes.
     */
    protected Marker() {
        this(Color.gray);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[3]++;
    }

    /**
     * Constructs a new marker.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    protected Marker(Paint paint) {
        this(paint, new BasicStroke(0.5f), Color.gray, new BasicStroke(0.5f), 
                0.80f);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[4]++;
    }

    /**
     * Constructs a new marker.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * @param stroke  the stroke (<code>null</code> not permitted).
     * @param outlinePaint  the outline paint (<code>null</code> permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> permitted).
     * @param alpha  the alpha transparency (must be in the range 0.0f to 
     *     1.0f).
     *     
     * @throws IllegalArgumentException if <code>paint</code> or 
     *     <code>stroke</code> is <code>null</code>, or <code>alpha</code> is 
     *     not in the specified range.
     */
    protected Marker(Paint paint, Stroke stroke, 
                     Paint outlinePaint, Stroke outlineStroke, 
                     float alpha) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[2]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[3]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[4]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((alpha < 0.0f) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((alpha > 1.0f) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[5]++;
            throw new IllegalArgumentException(
                    "The 'alpha' value must be in the range 0.0f to 1.0f");
} else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[6]++;}
        
        this.paint = paint;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[8]++;
        this.stroke = stroke;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[9]++;
        this.outlinePaint = outlinePaint;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[10]++;
        this.outlineStroke = outlineStroke;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[11]++;
        this.alpha = alpha;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[12]++;
        
        this.labelFont = new Font("SansSerif", Font.PLAIN, 9);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[13]++;
        this.labelPaint = Color.black;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[14]++;
        this.labelAnchor = RectangleAnchor.TOP_LEFT;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[15]++;
        this.labelOffset = new RectangleInsets(3.0, 3.0, 3.0, 3.0);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[16]++;
        this.labelOffsetType = LengthAdjustmentType.CONTRACT;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[17]++;
        this.labelTextAnchor = TextAnchor.CENTER;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[18]++;
        
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[19]++;
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
     * Sets the paint and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[7]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[8]++;}
        this.paint = paint;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[21]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[22]++;
    }

    /**
     * Returns the stroke.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setStroke(Stroke)
     */
    public Stroke getStroke() {
        return this.stroke;
    }
    
    /**
     * Sets the stroke and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getStroke()
     */
    public void setStroke(Stroke stroke) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[9]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[10]++;}
        this.stroke = stroke;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[24]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[25]++;
    }

    /**
     * Returns the outline paint.
     *
     * @return The outline paint (possibly <code>null</code>).
     * 
     * @see #setOutlinePaint(Paint)
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    /**
     * Sets the outline paint and sends a {@link MarkerChangeEvent} to all 
     * registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getOutlinePaint()
     */
    public void setOutlinePaint(Paint paint) {
        this.outlinePaint = paint;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[26]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[27]++;
    }

    /**
     * Returns the outline stroke.
     *
     * @return The outline stroke (possibly <code>null</code>).
     * 
     * @see #setOutlineStroke(Stroke)
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    /**
     * Sets the outline stroke and sends a {@link MarkerChangeEvent} to all 
     * registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getOutlineStroke()
     */
    public void setOutlineStroke(Stroke stroke) {
        this.outlineStroke = stroke;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[28]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[29]++;
    }

    /**
     * Returns the alpha transparency.
     *
     * @return The alpha transparency.
     * 
     * @see #setAlpha(float)
     */
    public float getAlpha() {
        return this.alpha;
    }
    
    /**
     * Sets the alpha transparency that should be used when drawing the 
     * marker, and sends a {@link MarkerChangeEvent} to all registered 
     * listeners.  The alpha transparency is a value in the range 0.0f 
     * (completely transparent) to 1.0f (completely opaque).
     * 
     * @param alpha  the alpha transparency (must be in the range 0.0f to 
     *     1.0f).
     *     
     * @throws IllegalArgumentException if <code>alpha</code> is not in the
     *     specified range.
     *     
     * @see #getAlpha()
     */
    public void setAlpha(float alpha) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((alpha < 0.0f) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((alpha > 1.0f) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[11]++;
            throw new IllegalArgumentException(
                    "The 'alpha' value must be in the range 0.0f to 1.0f");
} else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[12]++;}
        this.alpha = alpha;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[31]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[32]++;
    }

    /**
     * Returns the label (if <code>null</code> no label is displayed).
     *
     * @return The label (possibly <code>null</code>).
     * 
     * @see #setLabel(String)
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets the label (if <code>null</code> no label is displayed) and sends a
     * {@link MarkerChangeEvent} to all registered listeners.
     *
     * @param label  the label (<code>null</code> permitted).
     * 
     * @see #getLabel()
     */
    public void setLabel(String label) {
        this.label = label;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[33]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[34]++;
    }

    /**
     * Returns the label font.
     *
     * @return The label font (never <code>null</code>).
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the label font and sends a {@link MarkerChangeEvent} to all 
     * registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[13]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[14]++;}
        this.labelFont = font;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[36]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[37]++;
    }

    /**
     * Returns the label paint.
     *
     * @return The label paint (never </code>null</code>).
     * 
     * @see #setLabelPaint(Paint)
     */
    public Paint getLabelPaint() {
        return this.labelPaint;
    }

    /**
     * Sets the label paint and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelPaint()
     */
    public void setLabelPaint(Paint paint) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[15]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[16]++;}
        this.labelPaint = paint;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[39]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[40]++;
    }

    /**
     * Returns the label anchor.  This defines the position of the label 
     * anchor, relative to the bounds of the marker.
     *
     * @return The label anchor (never <code>null</code>).
     * 
     * @see #setLabelAnchor(RectangleAnchor)
     */
    public RectangleAnchor getLabelAnchor() {
        return this.labelAnchor;
    }

    /**
     * Sets the label anchor and sends a {@link MarkerChangeEvent} to all 
     * registered listeners.  The anchor defines the position of the label 
     * anchor, relative to the bounds of the marker.
     *
     * @param anchor  the anchor (<code>null</code> not permitted).
     * 
     * @see #getLabelAnchor()
     */
    public void setLabelAnchor(RectangleAnchor anchor) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[41]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[17]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[18]++;}
        this.labelAnchor = anchor;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[42]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[43]++;
    }

    /**
     * Returns the label offset.
     * 
     * @return The label offset (never <code>null</code>).
     * 
     * @see #setLabelOffset(RectangleInsets)
     */
    public RectangleInsets getLabelOffset() {
        return this.labelOffset;
    }
    
    /**
     * Sets the label offset and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     * 
     * @param offset  the label offset (<code>null</code> not permitted).
     * 
     * @see #getLabelOffset()
     */
    public void setLabelOffset(RectangleInsets offset) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((offset == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[19]++;
            throw new IllegalArgumentException("Null 'offset' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[20]++;}
        this.labelOffset = offset;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[45]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[46]++;
    }
    
    /**
     * Returns the label offset type.
     * 
     * @return The type (never <code>null</code>).
     * 
     * @see #setLabelOffsetType(LengthAdjustmentType)
     */
    public LengthAdjustmentType getLabelOffsetType() {
        return this.labelOffsetType;   
    }
    
    /**
     * Sets the label offset type and sends a {@link MarkerChangeEvent} to all
     * registered listeners.
     * 
     * @param adj  the type (<code>null</code> not permitted).
     * 
     * @see #getLabelOffsetType()
     */
    public void setLabelOffsetType(LengthAdjustmentType adj) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((adj == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[21]++;
            throw new IllegalArgumentException("Null 'adj' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[22]++;}
        this.labelOffsetType = adj;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[48]++;    
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[49]++;
    }
        
    /**
     * Returns the label text anchor.
     * 
     * @return The label text anchor (never <code>null</code>).
     * 
     * @see #setLabelTextAnchor(TextAnchor)
     */
    public TextAnchor getLabelTextAnchor() {
        return this.labelTextAnchor;
    }
    
    /**
     * Sets the label text anchor and sends a {@link MarkerChangeEvent} to 
     * all registered listeners.
     * 
     * @param anchor  the label text anchor (<code>null</code> not permitted).
     * 
     * @see #getLabelTextAnchor()
     */
    public void setLabelTextAnchor(TextAnchor anchor) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[23]++; 
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[24]++;}
        this.labelTextAnchor = anchor;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[51]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[52]++;
    }
    
    /**
     * Registers an object for notification of changes to the marker.
     *
     * @param listener  the object to be registered.
     * 
     * @see #removeChangeListener(MarkerChangeListener)
     * 
     * @since 1.0.3
     */
    public void addChangeListener(MarkerChangeListener listener) {
        this.listenerList.add(MarkerChangeListener.class, listener);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[53]++;
    }

    /**
     * Unregisters an object for notification of changes to the marker.
     *
     * @param listener  the object to be unregistered.
     * 
     * @see #addChangeListener(MarkerChangeListener)
     * 
     * @since 1.0.3
     */
    public void removeChangeListener(MarkerChangeListener listener) {
        this.listenerList.remove(MarkerChangeListener.class, listener);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[54]++;
    }

    /**
     * Notifies all registered listeners that the marker has been modified.
     *
     * @param event  information about the change event.
     * 
     * @since 1.0.3
     */
    public void notifyListeners(MarkerChangeEvent event) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[55]++;

        Object[] listeners = this.listenerList.getListenerList();
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[56]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.loops[1]--;
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.loops[2]--;
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.loops[3]++;
}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[57]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((listeners[i] == MarkerChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[25]++;
                ((MarkerChangeListener) listeners[i + 1]).markerChanged(event);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[58]++;

            } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[26]++;}
        }

    }

    /**
     * Returns an array containing all the listeners of the specified type.
     * 
     * @param listenerType  the listener type.
     * 
     * @return The array of listeners.
     * 
     * @since 1.0.3
     */
    public EventListener[] getListeners(Class listenerType) {
        return this.listenerList.getListeners(listenerType);    
    }
    
    /**
     * Tests the marker for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[27]++;
            return true;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[28]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj instanceof Marker) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[30]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[61]++;
        Marker that = (Marker) obj;
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[31]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[32]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[63]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.stroke, that.stroke)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[34]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[64]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[36]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[65]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[38]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[66]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.alpha != that.alpha) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[40]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[67]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.label, that.label)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[42]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[68]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelFont, that.labelFont)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[44]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[69]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelPaint, that.labelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[46]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[70]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.labelAnchor != that.labelAnchor) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[48]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[71]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.labelTextAnchor != that.labelTextAnchor) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[49]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[50]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[72]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelOffset, that.labelOffset)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[52]++;}
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[73]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.labelOffsetType.equals(that.labelOffsetType)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$br5emo76k03vkjfq9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$br5emo76k03vkjfq9.branches[54]++;}
        return true;
    }
    
    /**
     * Creates a clone of the marker.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException never.
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
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[74]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[75]++;
        SerialUtilities.writeStroke(this.stroke, stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[76]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[77]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[78]++;
        SerialUtilities.writePaint(this.labelPaint, stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[79]++;
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
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[80]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[81]++;
        this.stroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[82]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[83]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[84]++;
        this.labelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[85]++;
        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$br5emo76k03vkjfq9.statements[86]++;
    }

}

class CodeCoverCoverageCounter$br5emo76k03vkjfq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$br5emo76k03vkjfq9 ());
  }
    public static long[] statements = new long[87];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.Marker.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 28; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$br5emo76k03vkjfq9 () {
    super("org.jfree.chart.plot.Marker.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 86; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 54; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.Marker.java");
      for (int i = 1; i <= 86; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 54; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

