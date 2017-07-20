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
 * ---------------------------
 * CategoryTextAnnotation.java
 * ---------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-Apr-2003 : Version 1 (DG);
 * 02-Jul-2003 : Added new text alignment and rotation options (DG);
 * 04-Jul-2003 : Added a category anchor option (DG);
 * 19-Aug-2003 : Added equals() method and implemented Cloneable (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities 
 *               --> TextUtilities (DG);
 * ------------- JFREECHART 1.0.x -------------------------------------------
 * 06-Mar-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.annotations;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;

/**
 * A text annotation that can be placed on a {@link CategoryPlot}.
 */
public class CategoryTextAnnotation extends TextAnnotation
                                    implements CategoryAnnotation, 
                                               Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 3333360090781320147L;
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[1]++;
  }
    
    /** The category. */
    private Comparable category;

    /** The category anchor (START, MIDDLE, or END). */
    private CategoryAnchor categoryAnchor;
     
    /** The value. */
    private double value;

    /**
     * Creates a new annotation to be displayed at the given location.
     *
     * @param text  the text (<code>null</code> not permitted).
     * @param category  the category (<code>null</code> not permitted).
     * @param value  the value.
     */
    public CategoryTextAnnotation(String text, Comparable category, 
                                  double value) {
        super(text);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[2]++;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[1]++;
            throw new IllegalArgumentException("Null 'category' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[2]++;}
        this.category = category;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[4]++;
        this.value = value;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[5]++;
        this.categoryAnchor = CategoryAnchor.MIDDLE;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[6]++;
    }

    /**
     * Returns the category.
     * 
     * @return The category (never <code>null</code>).
     * 
     * @see #setCategory(Comparable)
     */
    public Comparable getCategory() {
        return this.category;
    }
    
    /**
     * Sets the category that the annotation attaches to.
     * 
     * @param category  the category (<code>null</code> not permitted).
     * 
     * @see #getCategory()
     */
    public void setCategory(Comparable category) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((category == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[3]++;
            throw new IllegalArgumentException("Null 'category' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[4]++;}
        this.category = category;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[8]++;
    }
    
    /**
     * Returns the category anchor point.
     * 
     * @return The category anchor point.
     * 
     * @see #setCategoryAnchor(CategoryAnchor)
     */
    public CategoryAnchor getCategoryAnchor() {
        return this.categoryAnchor;
    }
    
    /**
     * Sets the category anchor point.
     * 
     * @param anchor  the anchor point (<code>null</code> not permitted).
     * 
     * @see #getCategoryAnchor()
     */
    public void setCategoryAnchor(CategoryAnchor anchor) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((anchor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[5]++;
            throw new IllegalArgumentException("Null 'anchor' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[6]++;}
        this.categoryAnchor = anchor;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[10]++;    
    }
    
    /**
     * Returns the value that the annotation attaches to.
     * 
     * @return The value.
     * 
     * @see #setValue(double)
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * Sets the value.
     * 
     * @param value  the value.
     * 
     * @see #getValue()
     */
    public void setValue(double value) {
        this.value = value;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[11]++;    
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
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[12]++;

        CategoryDataset dataset = plot.getDataset();
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[13]++;
        int catIndex = dataset.getColumnIndex(this.category);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[14]++;
        int catCount = dataset.getColumnCount();
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[15]++;

        float anchorX = 0.0f;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[16]++;
        float anchorY = 0.0f;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[17]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[18]++;
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[19]++;
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[7]++;
            anchorY = (float) domainAxis.getCategoryJava2DCoordinate(
                    this.categoryAnchor, catIndex, catCount, dataArea, 
                    domainEdge);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[21]++;
            anchorX = (float) rangeAxis.valueToJava2D(this.value, dataArea, 
                    rangeEdge);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[22]++;

        }
        else {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[8]++;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[23]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[9]++;
            anchorX = (float) domainAxis.getCategoryJava2DCoordinate(
                    this.categoryAnchor, catIndex, catCount, dataArea, 
                    domainEdge);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[24]++;
            anchorY = (float) rangeAxis.valueToJava2D(this.value, dataArea, 
                    rangeEdge);
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[25]++;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[10]++;}
}
        g2.setFont(getFont());
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[26]++;
        g2.setPaint(getPaint());
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[27]++;
        TextUtilities.drawRotatedString(getText(), g2, anchorX, anchorY,
                getTextAnchor(), getRotationAngle(), getRotationAnchor());
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[28]++;

    }

    /**
     * Tests this object for equality with another.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[12]++;}
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryTextAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[14]++;}
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[31]++;
        CategoryTextAnnotation that = (CategoryTextAnnotation) obj;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[16]++;}
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.category.equals(that.getCategory())) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[18]++;}
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[34]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.categoryAnchor.equals(that.getCategoryAnchor())) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[20]++;}
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[35]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.value != that.getValue()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[21]++;
            return false;
    
        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.branches[22]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[36]++;
        int result = super.hashCode();
        result = 37 * result + this.category.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[37]++;
        result = 37 * result + this.categoryAnchor.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[38]++;
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[39]++;
        long temp = Double.doubleToLongBits(this.value);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl.statements[40]++;
        return result;
    }
    
    /**
     * Returns a clone of the annotation.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();    
    }
    
}

class CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl ());
  }
    public static long[] statements = new long[41];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.jfree.chart.annotations.CategoryTextAnnotation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$4b4elyw2od3fmirmt6wip83i5fya4qa9tvnmuiksbl () {
    super("org.jfree.chart.annotations.CategoryTextAnnotation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 40; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.annotations.CategoryTextAnnotation.java");
      for (int i = 1; i <= 40; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

