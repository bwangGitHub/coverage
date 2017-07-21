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
 * ---------------
 * LabelBlock.java
 * ---------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Pierre-Marie Le Biot;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 19-Apr-2005 : Added optional tooltip and URL text items,
 *               draw() method now returns entities if 
 *               requested (DG);
 * 13-May-2005 : Added methods to set the font (DG);
 * 01-Sep-2005 : Added paint management (PMLB);
 *               Implemented equals() and clone() (PublicCloneable) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Fixed entity area in draw() method (DG);
 * 16-Mar-2007 : Fixed serialization when using GradientPaint (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextBlock;
import org.jfree.text.TextBlockAnchor;
import org.jfree.text.TextUtilities;
import org.jfree.ui.Size2D;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A block containing a label.
 */
public class LabelBlock extends AbstractBlock 
                                implements Block, PublicCloneable {
  static {
    CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = 249626098864178017L;
  static {
    CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[1]++;
  }

    /** 
     * The text for the label - retained in case the label needs 
     * regenerating (for example, to change the font). 
     */
    private String text;
    
    /** The label. */
    private TextBlock label;
    
    /** The font. */
    private Font font;
    
    /** The tool tip text (can be <code>null</code>). */
    private String toolTipText;
    
    /** The URL text (can be <code>null</code>). */
    private String urlText;
    
    /** The default color. */
    public static final Paint DEFAULT_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[2]++;
  }

    /** The paint. */
    private transient Paint paint;
    
    /**
     * Creates a new label block.
     * 
     * @param label  the label (<code>null</code> not permitted).
     */
    public LabelBlock(String label) {
        this(label, new Font("SansSerif", Font.PLAIN, 10), DEFAULT_PAINT);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[3]++;
    }
    
    /**
     * Creates a new label block.
     * 
     * @param text  the text for the label (<code>null</code> not permitted).
     * @param font  the font (<code>null</code> not permitted).
     */
    public LabelBlock(String text, Font font) {        
        this(text, font, DEFAULT_PAINT);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[4]++;
    }
    
    /**
     * Creates a new label block.
     *
     * @param text  the text for the label (<code>null</code> not permitted).
     * @param font  the font (<code>null</code> not permitted).
     * @param paint the paint (<code>null</code> not permitted).
     */
    public LabelBlock(String text, Font font, Paint paint) {        
        this.text = text;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[5]++;
        this.paint = paint;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[6]++; 
        this.label = TextUtilities.createTextBlock(text, font, this.paint);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[7]++; 
        this.font = font;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[8]++;
        this.toolTipText = null;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[9]++;
        this.urlText = null;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[10]++;
    }
    
    /**
     * Returns the font.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setFont(Font)
     */
    public Font getFont() {
        return this.font;    
    }
    
    /**
     * Sets the font and regenerates the label.
     *
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getFont()
     */
    public void setFont(Font font) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[1]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[2]++;}
        this.font = font;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[12]++;
        this.label = TextUtilities.createTextBlock(this.text, font, this.paint);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[13]++;
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
     * Sets the paint and regenerates the label.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[4]++;}
        this.paint = paint;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[15]++;
        this.label = TextUtilities.createTextBlock(this.text, this.font, 
                this.paint);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[16]++;
    }

    /**
     * Returns the tool tip text.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     * 
     * @see #setToolTipText(String)
     */
    public String getToolTipText() {
        return this.toolTipText;
    }
    
    /**
     * Sets the tool tip text.
     * 
     * @param text  the text (<code>null</code> permitted).
     * 
     * @see #getToolTipText()
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[17]++;   
    }
    
    /**
     * Returns the URL text.
     * 
     * @return The URL text (possibly <code>null</code>).
     * 
     * @see #setURLText(String)
     */
    public String getURLText() {
        return this.urlText;
    }
    
    /**
     * Sets the URL text.
     * 
     * @param text  the text (<code>null</code> permitted).
     * 
     * @see #getURLText()
     */
    public void setURLText(String text) {
        this.urlText = text;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[18]++;   
    }
    
    /**
     * Arranges the contents of the block, within the given constraints, and 
     * returns the block size.
     * 
     * @param g2  the graphics device.
     * @param constraint  the constraint (<code>null</code> not permitted).
     * 
     * @return The block size (in Java2D units, never <code>null</code>).
     */
    public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
        g2.setFont(this.font);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[19]++;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[20]++;
        Size2D s = this.label.calculateDimensions(g2);
        return new Size2D(calculateTotalWidth(s.getWidth()), 
                calculateTotalHeight(s.getHeight()));
    }
    
    /**
     * Draws the block.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        draw(g2, area, null);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[21]++;
    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     * 
     * @return Always <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
        area = trimMargin(area);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[22]++;
        drawBorder(g2, area);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[23]++;
        area = trimBorder(area);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[24]++;
        area = trimPadding(area);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[25]++;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[26]++;
        
        // check if we need to collect chart entities from the container
        EntityBlockParams ebp = null;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[27]++;
        StandardEntityCollection sec = null;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[28]++;
        Shape entityArea = null;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[29]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((params instanceof EntityBlockParams) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[5]++;
            ebp = (EntityBlockParams) params;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[30]++;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ebp.getGenerateEntities()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[7]++;
                sec = new StandardEntityCollection();
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[32]++;
                entityArea = (Shape) area.clone();
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[33]++;

            } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[6]++;}
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[34]++;
        g2.setFont(this.font);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[35]++;
        this.label.draw(g2, (float) area.getX(), (float) area.getY(), 
                TextBlockAnchor.TOP_LEFT);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[36]++;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[37]++;
        BlockResult result = null;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[38]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((ebp != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((sec != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[9]++;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((this.toolTipText != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.urlText != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[11]++;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[40]++;
                ChartEntity entity = new ChartEntity(entityArea, 
                        this.toolTipText, this.urlText);   
                sec.add(entity);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[41]++;
                result = new BlockResult();
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[42]++;
                result.setEntityCollection(sec);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[43]++;

            } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[10]++;}
        return result;
    }
    
    /**
     * Tests this <code>LabelBlock</code> for equality with an arbitrary 
     * object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof LabelBlock) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[14]++;}
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[45]++;
        LabelBlock that = (LabelBlock) obj;
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[46]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.text.equals(that.text)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[16]++;}
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.font.equals(that.font)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[18]++;}
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[20]++;}
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipText, that.toolTipText)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[22]++;}
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.urlText, that.urlText)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.branches[24]++;}
        return super.equals(obj);
    }

    /**
     * Returns a clone of this <code>LabelBlock</code> instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
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
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[51]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[52]++;
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
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[53]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd.statements[54]++;
    }

}

class CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd ());
  }
    public static long[] statements = new long[55];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.LabelBlock.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,2,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$mw47vd5hot02tqtjvknbxpd () {
    super("org.jfree.chart.block.LabelBlock.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 54; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.LabelBlock.java");
      for (int i = 1; i <= 54; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

