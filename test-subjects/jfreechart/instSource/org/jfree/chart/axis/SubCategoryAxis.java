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
 * --------------------
 * SubCategoryAxis.java
 * --------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Adriaan Joubert;
 *
 * Changes
 * -------
 * 12-May-2004 : Version 1 (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities 
 *               --> TextUtilities (DG);
 * 26-Apr-2005 : Removed logger (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-Aug-2006 : Fix for bug drawing category labels, thanks to Adriaan
 *               Joubert (1277726) (DG);
 * 30-May-2007 : Added argument check and event notification to 
 *               addSubCategory() (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.category.CategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * A specialised category axis that can display sub-categories.
 */
public class SubCategoryAxis extends CategoryAxis 
                             implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -1279463299793228344L;
  static {
    CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[1]++;
  }
    
    /** Storage for the sub-categories (these need to be set manually). */
    private List subCategories;
    
    /** The font for the sub-category labels. */
    private Font subLabelFont = new Font("SansSerif", Font.PLAIN, 10);
  {
    CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[2]++;
  }
    
    /** The paint for the sub-category labels. */
    private transient Paint subLabelPaint = Color.black;
  {
    CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[3]++;
  }
    
    /**
     * Creates a new axis.
     * 
     * @param label  the axis label.
     */
    public SubCategoryAxis(String label) {
        super(label);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[4]++;
        this.subCategories = new java.util.ArrayList();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[5]++;
    }

    /**
     * Adds a sub-category to the axis and sends an {@link AxisChangeEvent} to
     * all registered listeners.
     * 
     * @param subCategory  the sub-category (<code>null</code> not permitted).
     */
    public void addSubCategory(Comparable subCategory) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((subCategory == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[1]++;
            throw new IllegalArgumentException("Null 'subcategory' axis.");

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[2]++;}
        this.subCategories.add(subCategory);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[7]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[8]++;        
    }
    
    /**
     * Returns the font used to display the sub-category labels.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setSubLabelFont(Font)
     */
    public Font getSubLabelFont() {
        return this.subLabelFont;   
    }
    
    /**
     * Sets the font used to display the sub-category labels and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getSubLabelFont()
     */
    public void setSubLabelFont(Font font) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[3]++;
            throw new IllegalArgumentException("Null 'font' argument.");
   
        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[4]++;}
        this.subLabelFont = font;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[10]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[11]++;
    }
    
    /**
     * Returns the paint used to display the sub-category labels.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setSubLabelPaint(Paint)
     */
    public Paint getSubLabelPaint() {
        return this.subLabelPaint;   
    }
    
    /**
     * Sets the paint used to display the sub-category labels and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getSubLabelPaint()
     */
    public void setSubLabelPaint(Paint paint) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[5]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[6]++;}
        this.subLabelPaint = paint;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[13]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[14]++;
    }
    
    /**
     * Estimates the space required for the axis, given a specific drawing area.
     *
     * @param g2  the graphics device (used to obtain font information).
     * @param plot  the plot that the axis belongs to.
     * @param plotArea  the area within which the axis should be drawn.
     * @param edge  the axis location (top or bottom).
     * @param space  the space already reserved.
     *
     * @return The space required to draw the axis.
     */
    public AxisSpace reserveSpace(Graphics2D g2, Plot plot, 
                                  Rectangle2D plotArea, 
                                  RectangleEdge edge, AxisSpace space) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;

        // create a new space object if one wasn't supplied...
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[7]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[16]++;

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[8]++;}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        
        // if the axis is not visible, no additional space is required...
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[9]++;
            return space;

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[10]++;}

        space = super.reserveSpace(g2, plot, plotArea, edge, space);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[18]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[19]++;
        double maxdim = getMaxDim(g2, edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[11]++;
            space.add(maxdim, edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[21]++;

        }
        else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[12]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[22]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[13]++;
            space.add(maxdim, edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[23]++;

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[14]++;}
}
        return space;
    }
    
    /**
     * Returns the maximum of the relevant dimension (height or width) of the 
     * subcategory labels.
     * 
     * @param g2  the graphics device.
     * @param edge  the edge.
     * 
     * @return The maximum dimension.
     */
    private double getMaxDim(Graphics2D g2, RectangleEdge edge) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[24]++;
        double result = 0.0;
        g2.setFont(this.subLabelFont);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[25]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[26]++;
        FontMetrics fm = g2.getFontMetrics();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[27]++;
        Iterator iterator = this.subCategories.iterator();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[1]--;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[2]--;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[3]++;
}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[29]++;
            Comparable subcategory = (Comparable) iterator.next();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[30]++;
            String label = subcategory.toString();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[31]++;
            Rectangle2D bounds = TextUtilities.getTextBounds(label, g2, fm);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[32]++;
            double dim = 0.0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[15]++;
                dim = bounds.getWidth();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[34]++;
   
            }
            else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[16]++;  // must be top or bottom
                dim = bounds.getHeight();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[35]++;
            }
            result = Math.max(result, dim);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[36]++;
        }   
        return result;
    }
    
    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the axis should be drawn 
     *                  (<code>null</code> not permitted).
     * @param dataArea  the area within which the plot is being drawn 
     *                  (<code>null</code> not permitted).
     * @param edge  the location of the axis (<code>null</code> not permitted).
     * @param plotState  collects information about the plot 
     *                   (<code>null</code> permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Graphics2D g2, 
                          double cursor, 
                          Rectangle2D plotArea, 
                          Rectangle2D dataArea,
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
        
        // if the axis is not visible, don't draw it...
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[17]++;
            return new AxisState(cursor);

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[18]++;}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[38]++;
int CodeCoverConditionCoverageHelper_C11;
        
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isAxisLineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[19]++;
            drawAxisLine(g2, cursor, dataArea, edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[39]++;

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[20]++;}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[40]++;

        // draw the category labels and axis label
        AxisState state = new AxisState(cursor);
        state = drawSubCategoryLabels(
            g2, plotArea, dataArea, edge, state, plotState
        );
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[41]++;
        state = drawCategoryLabels(g2, plotArea, dataArea, edge, state, 
                plotState);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[42]++;
        state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[43]++;
    
        return state;

    }
    
    /**
     * Draws the category labels and returns the updated axis state.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param plotArea  the plot area (<code>null</code> not permitted).
     * @param dataArea  the area inside the axes (<code>null</code> not 
     *                  permitted).
     * @param edge  the axis location (<code>null</code> not permitted).
     * @param state  the axis state (<code>null</code> not permitted).
     * @param plotState  collects information about the plot (<code>null</code> 
     *                   permitted).
     * 
     * @return The updated axis state (never <code>null</code>).
     */
    protected AxisState drawSubCategoryLabels(Graphics2D g2,
                                              Rectangle2D plotArea,
                                              Rectangle2D dataArea,
                                              RectangleEdge edge,
                                              AxisState state,
                                              PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[44]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((state == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[21]++;
            throw new IllegalArgumentException("Null 'state' argument.");

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[22]++;}

        g2.setFont(this.subLabelFont);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[45]++;
        g2.setPaint(this.subLabelPaint);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[46]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[47]++;
        CategoryPlot plot = (CategoryPlot) getPlot();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[48]++;
        CategoryDataset dataset = plot.getDataset();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[49]++;
        int categoryCount = dataset.getColumnCount();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[50]++;

        double maxdim = getMaxDim(g2, edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[51]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int categoryIndex = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((categoryIndex < categoryCount) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); 
             categoryIndex++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[4]--;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[5]--;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[6]++;
}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[52]++;

            double x0 = 0.0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[53]++;
            double x1 = 0.0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[54]++;
            double y0 = 0.0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[55]++;
            double y1 = 0.0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[23]++;
                x0 = getCategoryStart(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[57]++;
                x1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[58]++;
                y1 = state.getCursor();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[59]++;
                y0 = y1 - maxdim;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[60]++;

            }
            else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[24]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[61]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[25]++;
                x0 = getCategoryStart(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[62]++;
                x1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[63]++; 
                y0 = state.getCursor();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[64]++;                   
                y1 = y0 + maxdim;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[65]++;

            }
            else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[26]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[66]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[27]++;
                y0 = getCategoryStart(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[67]++;
                y1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[68]++;
                x1 = state.getCursor();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[69]++;
                x0 = x1 - maxdim;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[70]++;

            }
            else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[28]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[71]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[29]++;
                y0 = getCategoryStart(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[72]++;
                y1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, 
                        edge);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[73]++;
                x0 = state.getCursor();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[74]++;
                x1 = x0 + maxdim;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[75]++;

            } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[30]++;}
}
}
}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[76]++;
            Rectangle2D area = new Rectangle2D.Double(x0, y0, (x1 - x0), 
                    (y1 - y0));
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[77]++;
            int subCategoryCount = this.subCategories.size();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[78]++;
            float width = (float) ((x1 - x0) / subCategoryCount);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[79]++;
            float height = (float) ((y1 - y0) / subCategoryCount);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[80]++;
            float xx = 0.0f;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[81]++;
            float yy = 0.0f;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[82]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < subCategoryCount) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[7]--;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[8]--;
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.loops[9]++;
}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[83]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[31]++;
                    xx = (float) (x0 + (i + 0.5) * width);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[84]++;
                    yy = (float) area.getCenterY();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[85]++;

                }
                else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[32]++;
                    xx = (float) area.getCenterX();
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[86]++;
                    yy = (float) (y0 + (i + 0.5) * height);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[87]++;                   
                }
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[88]++;
                String label = this.subCategories.get(i).toString();
                TextUtilities.drawRotatedString(label, g2, xx, yy, 
                        TextAnchor.CENTER, 0.0, TextAnchor.CENTER);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[89]++;
            }
        }
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[90]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((edge.equals(RectangleEdge.TOP)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[33]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[91]++;
            double h = maxdim;
            state.cursorUp(h);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[92]++;

        }
        else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[34]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[93]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((edge.equals(RectangleEdge.BOTTOM)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[35]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[94]++;
            double h = maxdim;
            state.cursorDown(h);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[95]++;

        }
        else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[36]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[96]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[37]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[97]++;
            double w = maxdim;
            state.cursorLeft(w);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[98]++;

        }
        else {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[38]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[99]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[39]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[100]++;
            double w = maxdim;
            state.cursorRight(w);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[101]++;

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[40]++;}
}
}
}
        return state;
    }
    
    /**
     * Tests the axis for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[102]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[41]++;
            return true;

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[42]++;}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[103]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((obj instanceof SubCategoryAxis) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[43]++;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[104]++;
            SubCategoryAxis axis = (SubCategoryAxis) obj;
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[105]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.subCategories.equals(axis.subCategories)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[45]++;
                return false;

            } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[46]++;}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[106]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.subLabelFont.equals(axis.subLabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[47]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[48]++;}
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[107]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.subLabelPaint.equals(axis.subLabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[49]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[50]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.branches[44]++;}
        return false;        
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
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[108]++;
        SerialUtilities.writePaint(this.subLabelPaint, stream);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[109]++;
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
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[110]++;
        this.subLabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5.statements[111]++;
    }
  
}

class CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5 ());
  }
    public static long[] statements = new long[112];
    public static long[] branches = new long[51];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.SubCategoryAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$9qyk36exdr7fl1bwd5ov77s3mby9pb5 () {
    super("org.jfree.chart.axis.SubCategoryAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 111; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 50; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.SubCategoryAxis.java");
      for (int i = 1; i <= 111; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 50; i++) {
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
      for (int i = 1; i <= 3; i++) {
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

