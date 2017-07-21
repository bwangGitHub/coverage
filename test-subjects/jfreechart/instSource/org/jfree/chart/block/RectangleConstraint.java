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
 * ------------------------
 * RectangleConstraint.java
 * ------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 02-Feb-2005 : Added toString() method (DG);
 * 08-Feb-2005 : Separated height and width constraints (DG);
 * 13-May-2005 : Added convenience constructor and new methods for 
 *               transforming constraints (DG);
 * 
 */

package org.jfree.chart.block;

import org.jfree.data.Range;
import org.jfree.ui.Size2D;

/**
 * A description of a constraint for resizing a rectangle.  Constraints are
 * immutable.
 */
public class RectangleConstraint {
  static {
    CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.ping();
  }


    /**
     * An instance representing no constraint. 
     */
    public static final RectangleConstraint NONE = new RectangleConstraint(
            0.0, null, LengthConstraintType.NONE, 
            0.0, null, LengthConstraintType.NONE);
  static {
    CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[1]++;
  }
    
    /** The width. */
    private double width;
    
    /** The width range. */
    private Range widthRange;
    
    /** The width constraint type. */
    private LengthConstraintType widthConstraintType;
    
    /** The fixed or maximum height. */
    private double height;
    
    private Range heightRange;
    
    /** The constraint type. */
    private LengthConstraintType heightConstraintType;
    
    /**
     * Creates a new "fixed width and height" instance.
     * 
     * @param w  the fixed width.
     * @param h  the fixed height.
     */
    public RectangleConstraint(double w, double h) {
        this(w, null, LengthConstraintType.FIXED, 
                h, null, LengthConstraintType.FIXED);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[2]++;  
    }
    
    /**
     * Creates a new "range width and height" instance.
     * 
     * @param w  the width range.
     * @param h  the height range.
     */
    public RectangleConstraint(Range w, Range h) {
        this(0.0, w, LengthConstraintType.RANGE, 
                0.0, h, LengthConstraintType.RANGE);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[3]++;   
    }
    
    /**
     * Creates a new constraint with a range for the width and a
     * fixed height.
     * 
     * @param w  the width range.
     * @param h  the fixed height.
     */
    public RectangleConstraint(Range w, double h) {
        this(0.0, w, LengthConstraintType.RANGE, 
                h, null, LengthConstraintType.FIXED);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[4]++;   
    }
    
    /**
     * Creates a new constraint with a fixed width and a range for
     * the height.
     * 
     * @param w  the fixed width.
     * @param h  the height range.
     */
    public RectangleConstraint(double w, Range h) {
        this(w, null, LengthConstraintType.FIXED, 
                0.0, h, LengthConstraintType.RANGE);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[5]++;   
    }

    /**
     * Creates a new constraint.
     * 
     * @param w  the fixed or maximum width.
     * @param widthRange  the width range.
     * @param widthConstraintType  the width type.
     * @param h  the fixed or maximum height.
     * @param heightRange  the height range.
     * @param heightConstraintType  the height type.
     */
    public RectangleConstraint(double w, Range widthRange, 
                               LengthConstraintType widthConstraintType,
                               double h, Range heightRange, 
                               LengthConstraintType heightConstraintType) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((widthConstraintType == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[1]++;
            throw new IllegalArgumentException("Null 'widthType' argument.");

        } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[2]++;}
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((heightConstraintType == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[3]++;
            throw new IllegalArgumentException("Null 'heightType' argument.");
 
        } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[4]++;}
        this.width = w;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[8]++;
        this.widthRange = widthRange;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[9]++;
        this.widthConstraintType = widthConstraintType;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[10]++;
        this.height = h;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[11]++;
        this.heightRange = heightRange;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[12]++;
        this.heightConstraintType = heightConstraintType;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[13]++;
    }
    
    /**
     * Returns the fixed width.
     * 
     * @return The width.
     */
    public double getWidth() {
        return this.width;
    }
    
    /**
     * Returns the width range.
     * 
     * @return The range (possibly <code>null</code>).
     */
    public Range getWidthRange() {
        return this.widthRange;   
    }
    
    /**
     * Returns the constraint type.
     * 
     * @return The constraint type (never <code>null</code>).
     */
    public LengthConstraintType getWidthConstraintType() {
        return this.widthConstraintType;
    }
    
    /**
     * Returns the fixed height.
     * 
     * @return The height.
     */
    public double getHeight() {
        return this.height;
    }
    
    /**
     * Returns the width range.
     * 
     * @return The range (possibly <code>null</code>).
     */
    public Range getHeightRange() {
        return this.heightRange;   
    }
    
    /**
     * Returns the constraint type.
     * 
     * @return The constraint type (never <code>null</code>).
     */
    public LengthConstraintType getHeightConstraintType() {
        return this.heightConstraintType;
    }
    
    /**
     * Returns a constraint that matches this one on the height attributes,
     * but has no width constraint.
     * 
     * @return A new constraint.
     */
    public RectangleConstraint toUnconstrainedWidth() {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.widthConstraintType == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[5]++;
            return this;
   
        }
        else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[6]++;
            return new RectangleConstraint(this.width, this.widthRange, 
                    LengthConstraintType.NONE, this.height, this.heightRange, 
                    this.heightConstraintType);
        }
    }
    
    /**
     * Returns a constraint that matches this one on the width attributes,
     * but has no height constraint.
     * 
     * @return A new constraint.
     */
    public RectangleConstraint toUnconstrainedHeight() {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[7]++;
            return this;
   
        }
        else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[8]++;
            return new RectangleConstraint(this.width, this.widthRange, 
                    this.widthConstraintType, 0.0, this.heightRange, 
                    LengthConstraintType.NONE);
        }
    }
    
    /**
     * Returns a constraint that matches this one on the height attributes,
     * but has a fixed width constraint.
     * 
     * @param width  the fixed width.
     * 
     * @return A new constraint.
     */
    public RectangleConstraint toFixedWidth(double width) {
        return new RectangleConstraint(width, this.widthRange, 
                LengthConstraintType.FIXED, this.height, this.heightRange, 
                this.heightConstraintType);
    }
    
    /**
     * Returns a constraint that matches this one on the width attributes,
     * but has a fixed height constraint.
     * 
     * @param height  the fixed height.
     * 
     * @return A new constraint.
     */
    public RectangleConstraint toFixedHeight(double height) {
        return new RectangleConstraint(this.width, this.widthRange, 
                this.widthConstraintType, height, this.heightRange, 
                LengthConstraintType.FIXED);
    }
    
    /**
     * Returns a constraint that matches this one on the height attributes,
     * but has a range width constraint.
     * 
     * @param range  the width range (<code>null</code> not permitted).
     * 
     * @return A new constraint.
     */
    public RectangleConstraint toRangeWidth(Range range) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[9]++;
            throw new IllegalArgumentException("Null 'range' argument.");
   
        } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[10]++;}
        return new RectangleConstraint(range.getUpperBound(), range, 
                LengthConstraintType.RANGE, this.height, this.heightRange, 
                this.heightConstraintType);
    }
    
    /**
     * Returns a constraint that matches this one on the width attributes,
     * but has a range height constraint.
     * 
     * @param range  the height range (<code>null</code> not permitted).
     * 
     * @return A new constraint.
     */
    public RectangleConstraint toRangeHeight(Range range) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[11]++;
            throw new IllegalArgumentException("Null 'range' argument.");
   
        } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[12]++;}
        return new RectangleConstraint(this.width, this.widthRange, 
                this.widthConstraintType, range.getUpperBound(), range, 
                LengthConstraintType.RANGE);
    }
    
    /**
     * Returns a string representation of this instance, mostly used for
     * debugging purposes.
     * 
     * @return A string.
     */
    public String toString() {
        return "RectangleConstraint[" 
                + this.widthConstraintType.toString() + ": width=" 
                + this.width + ", height=" + this.height + "]";   
    }
    
    /**
     * Returns the new size that reflects the constraints defined by this 
     * instance.
     * 
     * @param base  the base size.
     * 
     * @return The constrained size.
     */
    public Size2D calculateConstrainedSize(Size2D base) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[18]++;
        Size2D result = new Size2D();
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.widthConstraintType == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[13]++;
            result.width = base.width;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[20]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[15]++;
               result.height = base.height;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[22]++;

            }
            else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[16]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[23]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[17]++;
               result.height = this.heightRange.constrain(base.height);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[24]++;

            }
            else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[18]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[25]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[19]++;
               result.height = this.height;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[26]++;

            } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[20]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[14]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[27]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.widthConstraintType == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[21]++;
            result.width = this.widthRange.constrain(base.width);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[28]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[29]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[23]++;
                result.height = base.height;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[30]++;

            }
            else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[24]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[31]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[25]++;
                result.height = this.heightRange.constrain(base.height);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[32]++;

            }
            else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[26]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[33]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[27]++;
                result.height = this.height;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[34]++;

            } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[28]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[22]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[35]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.widthConstraintType == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[29]++;
            result.width = this.width;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[36]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[37]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[31]++;
                result.height = base.height;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[38]++;

            }
            else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[32]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[39]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[33]++;
                result.height = this.heightRange.constrain(base.height);
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[40]++;

            }
            else {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[34]++;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[41]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.heightConstraintType == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[35]++;
                result.height = this.height;
CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.statements[42]++;

            } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[36]++;}
}
}

        } else {
  CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl.branches[30]++;}
}
}
        return result;
    }
    
}

class CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.RectangleConstraint.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$iznn21uubt97qug2yqawsns7gnzlinnryq4bl () {
    super("org.jfree.chart.block.RectangleConstraint.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.RectangleConstraint.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
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

