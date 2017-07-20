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
 * -------------------
 * TextAnnotation.java
 * -------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 28-Aug-2002 : Version 1 (DG);
 * 07-Nov-2002 : Fixed errors reported by Checkstyle, added accessor 
 *               methods (DG);
 * 13-Jan-2003 : Reviewed Javadocs (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 02-Jun-2003 : Added anchor and rotation settings (DG);
 * 19-Aug-2003 : Added equals() method and implemented Cloneable (DG);
 * 29-Sep-2004 : Updated equals() method (DG);
 * 06-Jun-2005 : Fixed equals() method to work with GradientPaint (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 16-Jan-2007 : Added argument checks, fixed hashCode() method and updated
 *               API docs (DG);
 * 
 */

package org.jfree.chart.annotations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A base class for text annotations.  This class records the content but not 
 * the location of the annotation.
 */
public class TextAnnotation implements Serializable {
  static {
    CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7008912287533127432L;
  static {
    CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[1]++;
  }
    
    /** The default font. */
    public static final Font DEFAULT_FONT 
        = new Font("SansSerif", Font.PLAIN, 10);
  static {
    CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[2]++;
  }

    /** The default paint. */
    public static final Paint DEFAULT_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[3]++;
  }
    
    /** The default text anchor. */
    public static final TextAnchor DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;
  static {
    CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[4]++;
  }

    /** The default rotation anchor. */    
    public static final TextAnchor DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;
  static {
    CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[5]++;
  }
    
    /** The default rotation angle. */
    public static final double DEFAULT_ROTATION_ANGLE = 0.0;
  static {
    CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[6]++;
  }

    /** The text. */
    private String text;

    /** The font. */
    private Font font;

    /** The paint. */
    private transient Paint paint;
    
    /** The text anchor. */
    private TextAnchor textAnchor;
    
    /** The rotation anchor. */
    private TextAnchor rotationAnchor;
    
    /** The rotation angle. */
    private double rotationAngle;

    /**
     * Creates a text annotation with default settings.
     *
     * @param text  the text (<code>null</code> not permitted).
     */
    protected TextAnnotation(String text) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[1]++;
            throw new IllegalArgumentException("Null 'text' argument.");

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[2]++;}
        this.text = text;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[8]++;
        this.font = DEFAULT_FONT;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[9]++;
        this.paint = DEFAULT_PAINT;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[10]++;
        this.textAnchor = DEFAULT_TEXT_ANCHOR;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[11]++;
        this.rotationAnchor = DEFAULT_ROTATION_ANCHOR;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[12]++;
        this.rotationAngle = DEFAULT_ROTATION_ANGLE;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[13]++;
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
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[3]++;
            throw new IllegalArgumentException("Null 'text' argument.");

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[4]++;}
        this.text = text;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[15]++;
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
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[5]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[6]++;}
        this.font = font;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[17]++;
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
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[7]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[8]++;}
        this.paint = paint;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[19]++;
    }

    /**
     * Returns the text anchor.
     * 
     * @return The text anchor.
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
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[9]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[10]++;}
        this.textAnchor = anchor;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[21]++;
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
        this.rotationAnchor = anchor;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[22]++;    
    }
    
    /**
     * Returns the rotation angle in radians.
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
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[23]++;    
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[12]++;}
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        // now try to reject equality...
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof TextAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[14]++;}
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[26]++;
        TextAnnotation that = (TextAnnotation) obj;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.text, that.getText())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[16]++;}
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.font, that.getFont())) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[18]++;}
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.getPaint())) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[20]++;}
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.textAnchor, that.getTextAnchor())) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[22]++;}
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[31]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rotationAnchor, 
                that.getRotationAnchor())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[24]++;}
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[32]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.rotationAngle != that.getRotationAngle()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.branches[26]++;}
        
        // seem to be the same...
        return true;
            
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[33]++;
        int result = 193;
        result = 37 * result + this.font.hashCode();
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[34]++;
        result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[35]++;
        result = 37 * result + this.rotationAnchor.hashCode();
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[36]++;
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[37]++;
        long temp = Double.doubleToLongBits(this.rotationAngle);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[38]++;
        result = 37 * result + this.text.hashCode();
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[39]++;
        result = 37 * result + this.textAnchor.hashCode();
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[40]++;
        return result;
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
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[41]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[42]++;
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
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[43]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1.statements[44]++;
    }

}

class CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1 ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.TextAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$1dwp7gyrv7wd6s7z3f066exylsxqw1 () {
    super("org.jfree.chart.annotations.TextAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
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
    log.startNamedSection("org.jfree.chart.annotations.TextAnnotation.java");
      for (int i = 1; i <= 44; i++) {
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

