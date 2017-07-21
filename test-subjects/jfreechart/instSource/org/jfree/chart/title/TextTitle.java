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
 * --------------
 * TextTitle.java
 * --------------
 * (C) Copyright 2000-2007, by David Berry and Contributors.
 *
 * Original Author:  David Berry;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nicolas Brodu;
 *
 * Changes (from 18-Sep-2001)
 * --------------------------
 * 18-Sep-2001 : Added standard header (DG);
 * 07-Nov-2001 : Separated the JCommon Class Library classes, JFreeChart now 
 *               requires jcommon.jar (DG);
 * 09-Jan-2002 : Updated Javadoc comments (DG);
 * 07-Feb-2002 : Changed Insets --> Spacer in AbstractTitle.java (DG);
 * 06-Mar-2002 : Updated import statements (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 18-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 28-Oct-2002 : Small modifications while changing JFreeChart class (DG);
 * 13-Mar-2003 : Changed width used for relative spacing to fix bug 703050 (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 15-Jul-2003 : Fixed null pointer exception (DG);
 * 11-Sep-2003 : Implemented Cloneable (NB)
 * 22-Sep-2003 : Added checks for null values and throw nullpointer 
 *               exceptions (TM); 
 *               Background paint was not serialized.
 * 07-Oct-2003 : Added fix for exception caused by empty string in title (DG);
 * 29-Oct-2003 : Added workaround for text alignment in PDF output (DG);
 * 03-Feb-2004 : Fixed bug in getPreferredWidth() method (DG);
 * 17-Feb-2004 : Added clone() method and fixed bug in equals() method (DG);
 * 01-Apr-2004 : Changed java.awt.geom.Dimension2D to org.jfree.ui.Size2D 
 *               because of JDK bug 4976448 which persists on JDK 1.3.1.  Also
 *               fixed bug in getPreferredHeight() method (DG);
 * 29-Apr-2004 : Fixed bug in getPreferredWidth() method - see bug id 
 *               944173 (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint class (DG);
 * 11-Feb-2005 : Implemented PublicCloneable (DG);
 * 20-Apr-2005 : Added support for tooltips (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 06-Jun-2005 : Modified equals() to handle GradientPaint (DG);
 * 06-Jul-2005 : Added flag to control whether or not the title expands to
 *               fit the available space (DG);
 * 07-Oct-2005 : Added textAlignment attribute (DG);
 * ------------- JFREECHART 1.0.x RELEASED ------------------------------------
 * 13-Dec-2005 : Fixed bug 1379331 - incorrect drawing with LEFT or RIGHT 
 *               title placement (DG);
 * 
 */

package org.jfree.chart.title;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.block.BlockResult;
import org.jfree.chart.block.EntityBlockParams;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.text.TextBlock;
import org.jfree.text.TextBlockAnchor;
import org.jfree.text.TextUtilities;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A chart title that displays a text string with automatic wrapping as 
 * required.
 */
public class TextTitle extends Title 
                       implements Serializable, Cloneable, PublicCloneable {
  static {
    CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8372008692127477443L;
  static {
    CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[1]++;
  }
    
    /** The default font. */
    public static final Font DEFAULT_FONT = new Font("SansSerif", Font.BOLD, 
            12);
  static {
    CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[2]++;
  }

    /** The default text color. */
    public static final Paint DEFAULT_TEXT_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[3]++;
  }

    /** The title text. */
    private String text;

    /** The font used to display the title. */
    private Font font;
    
    /** The text alignment. */
    private HorizontalAlignment textAlignment;

    /** The paint used to display the title text. */
    private transient Paint paint;

    /** The background paint. */
    private transient Paint backgroundPaint;

    /** The tool tip text (can be <code>null</code>). */
    private String toolTipText;
    
    /** The URL text (can be <code>null</code>). */
    private String urlText;
    
    /** The content. */
    private TextBlock content;
    
    /** 
     * A flag that controls whether the title expands to fit the available
     * space..
     */
    private boolean expandToFitSpace = false;
  {
    CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[4]++;
  }
    
    /**
     * Creates a new title, using default attributes where necessary.
     */
    public TextTitle() {
        this("");
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[5]++;
    }

    /**
     * Creates a new title, using default attributes where necessary.
     *
     * @param text  the title text (<code>null</code> not permitted).
     */
    public TextTitle(String text) {
        this(text, TextTitle.DEFAULT_FONT, TextTitle.DEFAULT_TEXT_PAINT,
                Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT,
                Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[6]++;
    }

    /**
     * Creates a new title, using default attributes where necessary.
     *
     * @param text  the title text (<code>null</code> not permitted).
     * @param font  the title font (<code>null</code> not permitted).
     */
    public TextTitle(String text, Font font) {
        this(text, font, TextTitle.DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION,
                Title.DEFAULT_HORIZONTAL_ALIGNMENT, 
                Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[7]++;
    }

    /**
     * Creates a new title.
     *
     * @param text  the text for the title (<code>null</code> not permitted).
     * @param font  the title font (<code>null</code> not permitted).
     * @param paint  the title paint (<code>null</code> not permitted).
     * @param position  the title position (<code>null</code> not permitted).
     * @param horizontalAlignment  the horizontal alignment (<code>null</code> 
     *                             not permitted).
     * @param verticalAlignment  the vertical alignment (<code>null</code> not 
     *                           permitted).
     * @param padding  the space to leave around the outside of the title.
     */
    public TextTitle(String text, Font font, Paint paint, 
                     RectangleEdge position, 
                     HorizontalAlignment horizontalAlignment, 
                     VerticalAlignment verticalAlignment,
                     RectangleInsets padding) {

        super(position, horizontalAlignment, verticalAlignment, padding);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[8]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[1]++;
            throw new NullPointerException("Null 'text' argument.");

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[2]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[3]++;
            throw new NullPointerException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[4]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[5]++;
            throw new NullPointerException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[6]++;}
        this.text = text;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[12]++;
        this.font = font;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[13]++;
        this.paint = paint;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[14]++;
        // the textAlignment and the horizontalAlignment are separate things,
        // but it makes sense for the default textAlignment to match the
        // title's horizontal alignment...
        this.textAlignment = horizontalAlignment;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[15]++;
        this.backgroundPaint = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[16]++;
        this.content = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[17]++;
        this.toolTipText = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[18]++;
        this.urlText = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[19]++;
        
    }

    /**
     * Returns the title text.
     *
     * @return The text (never <code>null</code>).
     * 
     * @see #setText(String)
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets the title to the specified text and sends a 
     * {@link TitleChangeEvent} to all registered listeners.
     *
     * @param text  the text (<code>null</code> not permitted).
     */
    public void setText(String text) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((text == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[7]++;
            throw new IllegalArgumentException("Null 'text' argument.");

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[8]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.text.equals(text)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[9]++;
            this.text = text;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[22]++;
            notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[23]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[10]++;}
    }

    /**
     * Returns the text alignment.  This controls how the text is aligned 
     * within the title's bounds, whereas the title's horizontal alignment
     * controls how the title's bounding rectangle is aligned within the 
     * drawing space.
     * 
     * @return The text alignment.
     */
    public HorizontalAlignment getTextAlignment() {
        return this.textAlignment;
    }
    
    /**
     * Sets the text alignment.
     * 
     * @param alignment  the alignment (<code>null</code> not permitted).
     */
    public void setTextAlignment(HorizontalAlignment alignment) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((alignment == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[11]++;
            throw new IllegalArgumentException("Null 'alignment' argument.");

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[12]++;}
        this.textAlignment = alignment;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[25]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[26]++;
    }
    
    /**
     * Returns the font used to display the title string.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setFont(Font)
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Sets the font used to display the title string.  Registered listeners 
     * are notified that the title has been modified.
     *
     * @param font  the new font (<code>null</code> not permitted).
     * 
     * @see #getFont()
     */
    public void setFont(Font font) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[13]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[14]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.font.equals(font)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[15]++;
            this.font = font;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[29]++;
            notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[30]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[16]++;}
    }

    /**
     * Returns the paint used to display the title string.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }

    /**
     * Sets the paint used to display the title string.  Registered listeners 
     * are notified that the title has been modified.
     *
     * @param paint  the new paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[17]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[18]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.paint.equals(paint)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[19]++;
            this.paint = paint;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[33]++;
            notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[34]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[20]++;}
    }

    /**
     * Returns the background paint.
     *
     * @return The paint (possibly <code>null</code>).
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    /**
     * Sets the background paint and sends a {@link TitleChangeEvent} to all 
     * registered listeners.  If you set this attribute to <code>null</code>, 
     * no background is painted (which makes the title background transparent).
     *
     * @param paint  the background paint (<code>null</code> permitted).
     */
    public void setBackgroundPaint(Paint paint) {
        this.backgroundPaint = paint;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[35]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[36]++;
    }
    
    /**
     * Returns the tool tip text.
     *
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * Sets the tool tip text to the specified text and sends a 
     * {@link TitleChangeEvent} to all registered listeners.
     *
     * @param text  the text (<code>null</code> permitted).
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[37]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[38]++;
    }

    /**
     * Returns the URL text.
     *
     * @return The URL text (possibly <code>null</code>).
     */
    public String getURLText() {
        return this.urlText;
    }

    /**
     * Sets the URL text to the specified text and sends a 
     * {@link TitleChangeEvent} to all registered listeners.
     *
     * @param text  the text (<code>null</code> permitted).
     */
    public void setURLText(String text) {
        this.urlText = text;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[39]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[40]++;
    }
    
    /**
     * Returns the flag that controls whether or not the title expands to fit
     * the available space.
     * 
     * @return The flag.
     */
    public boolean getExpandToFitSpace() {
        return this.expandToFitSpace;   
    }
    
    /**
     * Sets the flag that controls whether the title expands to fit the 
     * available space, and sends a {@link TitleChangeEvent} to all registered
     * listeners.
     * 
     * @param expand  the flag.
     */
    public void setExpandToFitSpace(boolean expand) {
        this.expandToFitSpace = expand;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[41]++;
        notifyListeners(new TitleChangeEvent(this));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[42]++;        
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
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[43]++;
        RectangleConstraint cc = toContentConstraint(constraint);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[44]++;
        LengthConstraintType w = cc.getWidthConstraintType();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[45]++;
        LengthConstraintType h = cc.getHeightConstraintType();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[46]++;
        Size2D contentSize = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[21]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[23]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[24]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[49]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[25]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[26]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[50]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[27]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[28]++;}
}
}
            
        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[22]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[51]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[29]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[31]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[32]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[53]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[33]++;
                contentSize = arrangeRR(g2, cc.getWidthRange(), 
                        cc.getHeightRange());
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[54]++;
 
            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[34]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[55]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[35]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[36]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[30]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[56]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[37]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[57]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[39]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[40]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[58]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[41]++;
                throw new RuntimeException("Not yet implemented.");
 
            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[42]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[59]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[43]++;
                throw new RuntimeException("Not yet implemented.");

            } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[44]++;}
}
}

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[38]++;}
}
}
        return new Size2D(calculateTotalWidth(contentSize.getWidth()),
                calculateTotalHeight(contentSize.getHeight()));
    }
    
    /**
     * Returns the content size for the title.  This will reflect the fact that
     * a text title positioned on the left or right of a chart will be rotated
     * 90 degrees.
     * 
     * @param g2  the graphics device.
     * @param widthRange  the width range.
     * @param heightRange  the height range.
     * 
     * @return The content size.
     */
    protected Size2D arrangeRR(Graphics2D g2, Range widthRange, 
            Range heightRange) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[60]++;
        RectangleEdge position = getPosition();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[61]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[45]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[62]++;
            float maxWidth = (float) widthRange.getUpperBound();
            g2.setFont(this.font);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[63]++;
            this.content = TextUtilities.createTextBlock(this.text, this.font, 
                    this.paint, maxWidth, new G2TextMeasurer(g2));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[64]++;
            this.content.setLineAlignment(this.textAlignment);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[65]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[66]++;
            Size2D contentSize = this.content.calculateDimensions(g2);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[67]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.expandToFitSpace) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[47]++;
                return new Size2D(maxWidth, contentSize.getHeight());

            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[48]++;
                return contentSize;
            }

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[46]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[68]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((position 
                == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[49]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[69]++;
            float maxWidth = (float) heightRange.getUpperBound();
            g2.setFont(this.font);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[70]++;
            this.content = TextUtilities.createTextBlock(this.text, this.font, 
                    this.paint, maxWidth, new G2TextMeasurer(g2));
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[71]++;
            this.content.setLineAlignment(this.textAlignment);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[72]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[73]++;
            Size2D contentSize = this.content.calculateDimensions(g2);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[74]++;
int CodeCoverConditionCoverageHelper_C26;
            
            // transpose the dimensions, because the title is rotated
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.expandToFitSpace) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[51]++;
                return new Size2D(contentSize.getHeight(), maxWidth);

            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[52]++;
                return new Size2D(contentSize.height, contentSize.width);
            }

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[50]++;
            throw new RuntimeException("Unrecognised exception.");
        }
}
    }
    
    /**
     * Draws the title on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the area allocated for the title.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        draw(g2, area, null);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[75]++;
    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  if this is an instance of {@link EntityBlockParams} it
     *                is used to determine whether or not an 
     *                {@link EntityCollection} is returned by this method.
     * 
     * @return An {@link EntityCollection} containing a chart entity for the
     *         title, or <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[76]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.content == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[53]++;
            return null;
   
        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[54]++;}
        area = trimMargin(area);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[77]++;
        drawBorder(g2, area);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[78]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[79]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.text.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[55]++;
            return null;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[56]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[80]++;
        ChartEntity entity = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[81]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((params instanceof EntityBlockParams) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[57]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[82]++;
            EntityBlockParams p = (EntityBlockParams) params;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[83]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((p.getGenerateEntities()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[59]++;
                entity = new ChartEntity(area, this.toolTipText, this.urlText);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[84]++;

            } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[60]++;}

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[58]++;}
        area = trimBorder(area);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[85]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[86]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[61]++;
            g2.setPaint(this.backgroundPaint);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[87]++;
            g2.fill(area);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[88]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[62]++;}
        area = trimPadding(area);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[89]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[90]++;
        RectangleEdge position = getPosition();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[91]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[63]++;
            drawHorizontal(g2, area);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[92]++;

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[64]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[93]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((position == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[65]++;
            drawVertical(g2, area);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[94]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[66]++;}
}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[95]++;
        BlockResult result = new BlockResult();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[96]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((entity != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[67]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[97]++;
            StandardEntityCollection sec = new StandardEntityCollection();
            sec.add(entity);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[98]++;
            result.setEntityCollection(sec);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[99]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[68]++;}
        return result;
    }

    /**
     * Draws a the title horizontally within the specified area.  This method 
     * will be called from the {@link #draw(Graphics2D, Rectangle2D) draw} 
     * method.
     * 
     * @param g2  the graphics device.
     * @param area  the area for the title.
     */
    protected void drawHorizontal(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[100]++;
        Rectangle2D titleArea = (Rectangle2D) area.clone();
        g2.setFont(this.font);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[101]++;
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[102]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[103]++;
        TextBlockAnchor anchor = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[104]++;
        float x = 0.0f;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[105]++;
        HorizontalAlignment horizontalAlignment = getHorizontalAlignment();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[106]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[69]++;
            x = (float) titleArea.getX();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[107]++;
            anchor = TextBlockAnchor.TOP_LEFT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[108]++;

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[70]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[109]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[71]++;
            x = (float) titleArea.getMaxX();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[110]++;
            anchor = TextBlockAnchor.TOP_RIGHT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[111]++;

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[72]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[112]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[73]++;
            x = (float) titleArea.getCenterX();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[113]++;
            anchor = TextBlockAnchor.TOP_CENTER;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[114]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[74]++;}
}
}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[115]++;
        float y = 0.0f;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[116]++;
        RectangleEdge position = getPosition();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[117]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[75]++;
            y = (float) titleArea.getY();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[118]++;

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[76]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[119]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[77]++;
            y = (float) titleArea.getMaxY();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[120]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[121]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[79]++;
                anchor = TextBlockAnchor.BOTTOM_LEFT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[122]++;

            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[80]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[123]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[81]++;
                anchor = TextBlockAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[124]++;

            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[82]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[125]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((horizontalAlignment == HorizontalAlignment.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[83]++;
                anchor = TextBlockAnchor.BOTTOM_RIGHT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[126]++;

            } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[84]++;}
}
}

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[78]++;}
}
        this.content.draw(g2, x, y, anchor);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[127]++;
    }
    
    /**
     * Draws a the title vertically within the specified area.  This method 
     * will be called from the {@link #draw(Graphics2D, Rectangle2D) draw} 
     * method.
     * 
     * @param g2  the graphics device.
     * @param area  the area for the title.
     */
    protected void drawVertical(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[128]++;
        Rectangle2D titleArea = (Rectangle2D) area.clone();
        g2.setFont(this.font);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[129]++;
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[130]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[131]++;
        TextBlockAnchor anchor = null;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[132]++;
        float y = 0.0f;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[133]++;
        VerticalAlignment verticalAlignment = getVerticalAlignment();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[134]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((verticalAlignment == VerticalAlignment.TOP) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[85]++;
            y = (float) titleArea.getY();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[135]++;
            anchor = TextBlockAnchor.TOP_RIGHT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[136]++;

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[86]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[137]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((verticalAlignment == VerticalAlignment.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[87]++;
            y = (float) titleArea.getMaxY();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[138]++;
            anchor = TextBlockAnchor.TOP_LEFT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[139]++;

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[88]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[140]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((verticalAlignment == VerticalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[89]++;
            y = (float) titleArea.getCenterY();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[141]++;
            anchor = TextBlockAnchor.TOP_CENTER;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[142]++;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[90]++;}
}
}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[143]++;
        float x = 0.0f;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[144]++;
        RectangleEdge position = getPosition();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[145]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[91]++;
            x = (float) titleArea.getX();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[146]++;

        }
        else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[92]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[147]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[93]++;
            x = (float) titleArea.getMaxX();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[148]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[149]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((verticalAlignment == VerticalAlignment.TOP) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[95]++;
                anchor = TextBlockAnchor.BOTTOM_RIGHT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[150]++;

            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[96]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[151]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((verticalAlignment == VerticalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[97]++;
                anchor = TextBlockAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[152]++;

            }
            else {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[98]++;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[153]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((verticalAlignment == VerticalAlignment.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[99]++;
                anchor = TextBlockAnchor.BOTTOM_LEFT;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[154]++;

            } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[100]++;}
}
}

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[94]++;}
}
        this.content.draw(g2, x, y, anchor, x, y, -Math.PI / 2.0);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[155]++;
    }

    /**
     * Tests this title for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[156]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[101]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[102]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[157]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((obj instanceof TextTitle) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[103]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[104]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[158]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[105]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[106]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[159]++;
        TextTitle that = (TextTitle) obj;
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[160]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.text, that.text)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[107]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[108]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[161]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.font, that.font)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[109]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[110]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[162]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[111]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[112]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[163]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.textAlignment != that.textAlignment) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[114]++;}
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[164]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[115]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.branches[116]++;}
        return true;
    }

    /**
     * Returns a hash code.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[165]++;
        int result = super.hashCode();
        result = 29 * result + (this.text != null ? this.text.hashCode() : 0);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[166]++;
        result = 29 * result + (this.font != null ? this.font.hashCode() : 0);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[167]++;
        result = 29 * result + (this.paint != null ? this.paint.hashCode() : 0);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[168]++;
        result = 29 * result + (this.backgroundPaint != null 
                ? this.backgroundPaint.hashCode() : 0);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[169]++;
        return result;
    }

    /**
     * Returns a clone of this object.
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
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[170]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[171]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[172]++;
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
        throws IOException, ClassNotFoundException 
    {
        stream.defaultReadObject();
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[173]++;
        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[174]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd.statements[175]++;
    }

}

class CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd ());
  }
    public static long[] statements = new long[176];
    public static long[] branches = new long[117];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[59];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.TextTitle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 58; i++) {
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

  public CodeCoverCoverageCounter$3k1y1goeyd0khfol9ju3xd () {
    super("org.jfree.chart.title.TextTitle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 175; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 116; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 58; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.title.TextTitle.java");
      for (int i = 1; i <= 175; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 116; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 58; i++) {
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


