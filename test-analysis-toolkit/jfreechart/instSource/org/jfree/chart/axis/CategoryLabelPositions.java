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
 * CategoryLabelPositions.java
 * ---------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Jan-2004 : Version 1 (DG);
 * 17-Feb-2004 : Added equals() method (DG);
 * 05-Nov-2004 : Adjusted settings for UP_90 and DOWN_90 (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;

import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * Records the label positions for a category axis.  Instances of this class 
 * are immutable.
 */
public class CategoryLabelPositions implements Serializable {
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8999557901920364580L;
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[1]++;
  }
    
    /** STANDARD category label positions. */
    public static final CategoryLabelPositions 
        STANDARD = new CategoryLabelPositions(
            new CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_CENTER
            ), // TOP
            new CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.TOP_CENTER
            ), // BOTTOM
            new CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.CENTER_RIGHT, 
                CategoryLabelWidthType.RANGE, 0.30f
            ), // LEFT
            new CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT, 
                CategoryLabelWidthType.RANGE, 0.30f
            ) // RIGHT
        );
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[2]++;
  }
    
    /** UP_90 category label positions. */
    public static final CategoryLabelPositions 
        UP_90 = new CategoryLabelPositions(
            new CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_LEFT, 
                TextAnchor.CENTER_LEFT, -Math.PI / 2.0,
                CategoryLabelWidthType.RANGE, 0.30f
            ), // TOP
            new CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.CENTER_RIGHT, 
                TextAnchor.CENTER_RIGHT, -Math.PI / 2.0,
                CategoryLabelWidthType.RANGE, 0.30f
            ), // BOTTOM
            new CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_CENTER, 
                TextAnchor.BOTTOM_CENTER, -Math.PI / 2.0,
                CategoryLabelWidthType.CATEGORY, 0.9f
            ), // LEFT
            new CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.TOP_CENTER, 
                TextAnchor.TOP_CENTER, -Math.PI / 2.0,
                CategoryLabelWidthType.CATEGORY, 0.90f
            ) // RIGHT
        );
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[3]++;
  }
    
    /** DOWN_90 category label positions. */
    public static final CategoryLabelPositions 
        DOWN_90 = new CategoryLabelPositions(
            new CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_RIGHT, 
                TextAnchor.CENTER_RIGHT, Math.PI / 2.0,
                CategoryLabelWidthType.RANGE, 0.30f
            ), // TOP
            new CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.CENTER_LEFT, 
                TextAnchor.CENTER_LEFT, Math.PI / 2.0,
                CategoryLabelWidthType.RANGE, 0.30f
            ), // BOTTOM
            new CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.TOP_CENTER, 
                TextAnchor.TOP_CENTER, Math.PI / 2.0,
                CategoryLabelWidthType.CATEGORY, 0.90f
            ), // LEFT
            new CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_CENTER, 
                TextAnchor.BOTTOM_CENTER, Math.PI / 2.0,
                CategoryLabelWidthType.CATEGORY, 0.90f
            ) // RIGHT
        );
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[4]++;
  }
    
    /** UP_45 category label positions. */
    public static final CategoryLabelPositions UP_45 
        = createUpRotationLabelPositions(Math.PI / 4.0);
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[5]++;
  }
    
    /** DOWN_45 category label positions. */
    public static final CategoryLabelPositions DOWN_45 
        = createDownRotationLabelPositions(Math.PI / 4.0);
  static {
    CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[6]++;
  }
    
    /**
     * Creates a new instance where the category labels angled upwards by the 
     * specified amount.
     * 
     * @param angle  the rotation angle (should be < Math.PI / 2.0).
     * 
     * @return A category label position specification.
     */
    public static CategoryLabelPositions createUpRotationLabelPositions(
            double angle) {
        return new CategoryLabelPositions(
            new CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_LEFT, 
                TextAnchor.BOTTOM_LEFT, -angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ), // TOP
            new CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.TOP_RIGHT, 
                TextAnchor.TOP_RIGHT, -angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ), // BOTTOM
            new CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_RIGHT, 
                TextAnchor.BOTTOM_RIGHT, -angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ), // LEFT
            new CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.TOP_LEFT, 
                TextAnchor.TOP_LEFT, -angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ) // RIGHT
        );
    }
    
    /**
     * Creates a new instance where the category labels angled downwards by the
     * specified amount.
     * 
     * @param angle  the rotation angle (should be < Math.PI / 2.0).
     * 
     * @return A category label position specification.
     */
    public static CategoryLabelPositions createDownRotationLabelPositions(
            double angle) {
        return new CategoryLabelPositions(
            new CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_RIGHT, 
                TextAnchor.BOTTOM_RIGHT, angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ), // TOP
            new CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.TOP_LEFT, 
                TextAnchor.TOP_LEFT, angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ), // BOTTOM
            new CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.TOP_RIGHT, 
                TextAnchor.TOP_RIGHT, angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ), // LEFT
            new CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_LEFT, 
                TextAnchor.BOTTOM_LEFT, angle,
                CategoryLabelWidthType.RANGE, 0.50f
            ) // RIGHT
        );
    }
    
    /** 
     * The label positioning details used when an axis is at the top of a 
     * chart. 
     */
    private CategoryLabelPosition positionForAxisAtTop;
    
    /** 
     * The label positioning details used when an axis is at the bottom of a 
     * chart. 
     */
    private CategoryLabelPosition positionForAxisAtBottom;
    
    /** 
     * The label positioning details used when an axis is at the left of a 
     * chart. 
     */
    private CategoryLabelPosition positionForAxisAtLeft;
    
    /** 
     * The label positioning details used when an axis is at the right of a 
     * chart. 
     */
    private CategoryLabelPosition positionForAxisAtRight;
 
    /**
     * Default constructor.
     */
    public CategoryLabelPositions() {
        this.positionForAxisAtTop = new CategoryLabelPosition();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[7]++;
        this.positionForAxisAtBottom = new CategoryLabelPosition();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[8]++;
        this.positionForAxisAtLeft = new CategoryLabelPosition();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[9]++;
        this.positionForAxisAtRight = new CategoryLabelPosition();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[10]++;
    }
    
    /**
     * Creates a new position specification.
     * 
     * @param top  the label position info used when an axis is at the top 
     *             (<code>null</code> not permitted).
     * @param bottom  the label position info used when an axis is at the 
     *                bottom (<code>null</code> not permitted).
     * @param left  the label position info used when an axis is at the left 
     *              (<code>null</code> not permitted).
     * @param right  the label position info used when an axis is at the right 
     *               (<code>null</code> not permitted).
     */
    public CategoryLabelPositions(CategoryLabelPosition top,
                                  CategoryLabelPosition bottom,
                                  CategoryLabelPosition left,
                                  CategoryLabelPosition right) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((top == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[1]++;
            throw new IllegalArgumentException("Null 'top' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[2]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((bottom == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[3]++;
            throw new IllegalArgumentException("Null 'bottom' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[4]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[5]++;
            throw new IllegalArgumentException("Null 'left' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[6]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((right == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[7]++;
            throw new IllegalArgumentException("Null 'right' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[8]++;}
        
        this.positionForAxisAtTop = top;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[15]++;
        this.positionForAxisAtBottom = bottom;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[16]++;
        this.positionForAxisAtLeft = left;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[17]++;
        this.positionForAxisAtRight = right;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[18]++;
    
    }
    
    /**
     * Returns the category label position specification for an axis at the 
     * given location.
     * 
     * @param edge  the axis location.
     * 
     * @return The category label position specification.
     */
    public CategoryLabelPosition getLabelPosition(RectangleEdge edge) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[19]++;
        CategoryLabelPosition result = null;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[9]++;
            result = this.positionForAxisAtTop;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[21]++;

        }
        else {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[10]++;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[22]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[11]++;
            result = this.positionForAxisAtBottom;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[23]++;

        }
        else {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[12]++;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[24]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[13]++;
            result = this.positionForAxisAtLeft;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[25]++;

        }
        else {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[14]++;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[26]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[15]++;
            result = this.positionForAxisAtRight;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[27]++;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[16]++;}
}
}
}
        return result;
    }
    
    /**
     * Returns a new instance based on an existing instance but with the top 
     * position changed.
     * 
     * @param base  the base (<code>null</code> not permitted).
     * @param top  the top position (<code>null</code> not permitted).
     * 
     * @return A new instance (never <code>null</code>).
     */
    public static CategoryLabelPositions replaceTopPosition(
            CategoryLabelPositions base, CategoryLabelPosition top) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[17]++;
            throw new IllegalArgumentException("Null 'base' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[18]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((top == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[19]++;
            throw new IllegalArgumentException("Null 'top' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[20]++;}
        
        return new CategoryLabelPositions(
            top, 
            base.getLabelPosition(RectangleEdge.BOTTOM),
            base.getLabelPosition(RectangleEdge.LEFT),
            base.getLabelPosition(RectangleEdge.RIGHT)
        );
    }
    
    /**
     * Returns a new instance based on an existing instance but with the bottom
     * position changed.
     * 
     * @param base  the base (<code>null</code> not permitted).
     * @param bottom  the bottom position (<code>null</code> not permitted).
     * 
     * @return A new instance (never <code>null</code>).
     */
    public static CategoryLabelPositions replaceBottomPosition(
            CategoryLabelPositions base, CategoryLabelPosition bottom) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
        
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[21]++;
            throw new IllegalArgumentException("Null 'base' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[22]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[31]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((bottom == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[23]++;
            throw new IllegalArgumentException("Null 'bottom' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[24]++;}
        
        return new CategoryLabelPositions(
            base.getLabelPosition(RectangleEdge.TOP),
            bottom,
            base.getLabelPosition(RectangleEdge.LEFT),
            base.getLabelPosition(RectangleEdge.RIGHT)
        );
    }
    
    /**
     * Returns a new instance based on an existing instance but with the left 
     * position changed.
     * 
     * @param base  the base (<code>null</code> not permitted).
     * @param left  the left position (<code>null</code> not permitted).
     * 
     * @return A new instance (never <code>null</code>).
     */
    public static CategoryLabelPositions replaceLeftPosition(
            CategoryLabelPositions base, CategoryLabelPosition left) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[32]++;
int CodeCoverConditionCoverageHelper_C13;
        
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[25]++;
            throw new IllegalArgumentException("Null 'base' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[26]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[33]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[27]++;
            throw new IllegalArgumentException("Null 'left' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[28]++;}
        
        return new CategoryLabelPositions(
            base.getLabelPosition(RectangleEdge.TOP),
            base.getLabelPosition(RectangleEdge.BOTTOM),
            left,
            base.getLabelPosition(RectangleEdge.RIGHT)
        );
    }
    
    /**
     * Returns a new instance based on an existing instance but with the right 
     * position changed.
     * 
     * @param base  the base (<code>null</code> not permitted).
     * @param right  the right position (<code>null</code> not permitted).
     * 
     * @return A new instance (never <code>null</code>).
     */
    public static CategoryLabelPositions replaceRightPosition(
            CategoryLabelPositions base, CategoryLabelPosition right) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[34]++;
int CodeCoverConditionCoverageHelper_C15;
        
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[29]++;
            throw new IllegalArgumentException("Null 'base' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[30]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[35]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((right == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[31]++;
            throw new IllegalArgumentException("Null 'right' argument.");

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[32]++;}
        
        return new CategoryLabelPositions(
            base.getLabelPosition(RectangleEdge.TOP),
            base.getLabelPosition(RectangleEdge.BOTTOM),
            base.getLabelPosition(RectangleEdge.LEFT),
            right
        );
    }
    
    /**
     * Returns <code>true</code> if this object is equal to the specified 
     * object, and <code>false</code> otherwise.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[36]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[33]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[34]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[37]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryLabelPositions) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[36]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[38]++;

        CategoryLabelPositions that = (CategoryLabelPositions) obj;
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[39]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.positionForAxisAtTop.equals(that.positionForAxisAtTop)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[38]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[40]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.positionForAxisAtBottom.equals(
                that.positionForAxisAtBottom)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[40]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[41]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.positionForAxisAtLeft.equals(that.positionForAxisAtLeft)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[42]++;}
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[42]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.positionForAxisAtRight.equals(that.positionForAxisAtRight)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.branches[44]++;}
  
        return true;

    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[43]++;
        int result = 19;
        result = 37 * result + this.positionForAxisAtTop.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[44]++;
        result = 37 * result + this.positionForAxisAtBottom.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[45]++;
        result = 37 * result + this.positionForAxisAtLeft.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[46]++;
        result = 37 * result + this.positionForAxisAtRight.hashCode();
CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl.statements[47]++;
        return result;
    }
}

class CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl ());
  }
    public static long[] statements = new long[48];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CategoryLabelPositions.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 22; i++) {
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

  public CodeCoverCoverageCounter$4b4elyw2od3fme0ze6pbf37rwkn0ihsylr3bhdmezl () {
    super("org.jfree.chart.axis.CategoryLabelPositions.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 47; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.CategoryLabelPositions.java");
      for (int i = 1; i <= 47; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
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

