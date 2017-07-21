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
 * --------------------------
 * CategoryLabelPosition.java
 * --------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 31-Oct-2003 : Version 1 (DG);
 * 17-Feb-2004 : Added new constructor (DG);
 * 23-Mar-2004 : Added width calculation parameters (DG);
 * 07-Jan-2005 : Fixed bug in equals() method (DG);
 * 11-Jan-2005 : Removed deprecated constructor in preparation for the 1.0.0 
 *               release (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;

import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

/**
 * The attributes that control the position of the labels for the categories on 
 * a {@link CategoryAxis}. Instances of this class are immutable and other 
 * JFreeChart classes rely upon this.
 */
public class CategoryLabelPosition implements Serializable {
  static {
    CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5168681143844183864L;
  static {
    CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[1]++;
  }
    
    /** The category anchor point. */
    private RectangleAnchor categoryAnchor;
    
    /** The text block anchor. */
    private TextBlockAnchor labelAnchor;
    
    /** The rotation anchor. */
    private TextAnchor rotationAnchor;

    /** The rotation angle (in radians). */    
    private double angle;
    
    /** The width calculation type. */
    private CategoryLabelWidthType widthType;
    
    /** 
     * The maximum label width as a percentage of the category space or the 
     * range space. 
     */
    private float widthRatio;
    
    /**
     * Creates a new position record with default settings.
     */
    public CategoryLabelPosition() {
        this(RectangleAnchor.CENTER, TextBlockAnchor.BOTTOM_CENTER, 
                TextAnchor.CENTER, 0.0, CategoryLabelWidthType.CATEGORY, 0.95f);
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[2]++;
    }
    
    /**
     * Creates a new category label position record.
     * 
     * @param categoryAnchor  the category anchor (<code>null</code> not 
     *                        permitted).
     * @param labelAnchor  the label anchor (<code>null</code> not permitted).
     */
    public CategoryLabelPosition(RectangleAnchor categoryAnchor, 
                                 TextBlockAnchor labelAnchor) {
        // argument checking delegated...
        this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0, 
                CategoryLabelWidthType.CATEGORY, 0.95f);
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[3]++;
    }
    
    /**
     * Creates a new category label position record.
     * 
     * @param categoryAnchor  the category anchor (<code>null</code> not 
     *                        permitted).
     * @param labelAnchor  the label anchor (<code>null</code> not permitted).
     * @param widthType  the width type (<code>null</code> not permitted).
     * @param widthRatio  the maximum label width as a percentage (of the 
     *                    category space or the range space).
     */
    public CategoryLabelPosition(RectangleAnchor categoryAnchor, 
                                 TextBlockAnchor labelAnchor,
                                 CategoryLabelWidthType widthType,
                                 float widthRatio) {
        // argument checking delegated...
        this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0, widthType, 
                widthRatio);
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[4]++;
    }
    
    /**
     * Creates a new position record.  The item label anchor is a point 
     * relative to the data item (dot, bar or other visual item) on a chart.  
     * The item label is aligned by aligning the text anchor with the item 
     * label anchor.
     * 
     * @param categoryAnchor  the category anchor (<code>null</code> not 
     *                        permitted).
     * @param labelAnchor  the label anchor (<code>null</code> not permitted).
     * @param rotationAnchor  the rotation anchor (<code>null</code> not 
     *                        permitted).
     * @param angle  the rotation angle (<code>null</code> not permitted).
     * @param widthType  the width type (<code>null</code> not permitted).
     * @param widthRatio  the maximum label width as a percentage (of the 
     *                    category space or the range space).
     */
    public CategoryLabelPosition(RectangleAnchor categoryAnchor, 
                                 TextBlockAnchor labelAnchor,
                                 TextAnchor rotationAnchor,
                                 double angle,
                                 CategoryLabelWidthType widthType,
                                 float widthRatio) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((categoryAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[1]++;
            throw new IllegalArgumentException(
                    "Null 'categoryAnchor' argument.");

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[2]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((labelAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[3]++;
            throw new IllegalArgumentException(
                    "Null 'labelAnchor' argument.");

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[4]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((rotationAnchor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[5]++;
            throw new IllegalArgumentException(
                    "Null 'rotationAnchor' argument.");

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[6]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((widthType == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[7]++;
            throw new IllegalArgumentException("Null 'widthType' argument.");
   
        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[8]++;}
        
        this.categoryAnchor = categoryAnchor;
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[9]++;
        this.labelAnchor = labelAnchor;
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[10]++;
        this.rotationAnchor = rotationAnchor;
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[11]++;
        this.angle = angle;
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[12]++;
        this.widthType = widthType;
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[13]++;
        this.widthRatio = widthRatio;
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[14]++;
    
    }
    
    /**
     * Returns the item label anchor.
     * 
     * @return The item label anchor (never <code>null</code>).
     */
    public RectangleAnchor getCategoryAnchor() {
        return this.categoryAnchor;
    }
    
    /**
     * Returns the text block anchor.
     * 
     * @return The text block anchor (never <code>null</code>).
     */
    public TextBlockAnchor getLabelAnchor() {
        return this.labelAnchor;
    }
    
    /**
     * Returns the rotation anchor point.
     * 
     * @return The rotation anchor point (never <code>null</code>).
     */
    public TextAnchor getRotationAnchor() {
        return this.rotationAnchor;
    }
    
    /**
     * Returns the angle of rotation for the label.
     * 
     * @return The angle (in radians).
     */
    public double getAngle() {
        return this.angle;
    }
    
    /**
     * Returns the width calculation type.
     * 
     * @return The width calculation type (never <code>null</code>).
     */
    public CategoryLabelWidthType getWidthType() {
        return this.widthType;   
    }
    
    /**
     * Returns the ratio used to calculate the maximum category label width.
     * 
     * @return The ratio.
     */
    public float getWidthRatio() {
        return this.widthRatio;   
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[10]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryLabelPosition) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[12]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[17]++;
        CategoryLabelPosition that = (CategoryLabelPosition) obj;
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.categoryAnchor.equals(that.categoryAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[14]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.labelAnchor.equals(that.labelAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[16]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[20]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.rotationAnchor.equals(that.rotationAnchor)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[18]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[21]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.angle != that.angle) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[20]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[22]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.widthType != that.widthType) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[22]++;}
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[23]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.widthRatio != that.widthRatio) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.branches[24]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[24]++;
        int result = 19;
        result = 37 * result + this.categoryAnchor.hashCode();
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[25]++;
        result = 37 * result + this.labelAnchor.hashCode();
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[26]++;
        result = 37 * result + this.rotationAnchor.hashCode();
CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh.statements[27]++;
        return result;
    }

}

class CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CategoryLabelPosition.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$ltb1ypvvjuamu3ih6kfo25140nhicmmv2uq231wh () {
    super("org.jfree.chart.axis.CategoryLabelPosition.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.CategoryLabelPosition.java");
      for (int i = 1; i <= 27; i++) {
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

