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
 * ----------------------
 * ColumnArrangement.java
 * ----------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 04-Feb-2005 : Added equals() and implemented Serializable (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.Size2D;
import org.jfree.ui.VerticalAlignment;

/**
 * Arranges blocks in a column layout.  This class is immutable.
 */
public class ColumnArrangement implements Arrangement, Serializable {
  static {
    CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5315388482898581555L;
  static {
    CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[1]++;
  }
    
    /** The horizontal alignment of blocks. */
    private HorizontalAlignment horizontalAlignment;
    
    /** The vertical alignment of blocks within each row. */
    private VerticalAlignment verticalAlignment;
    
    /** The horizontal gap between columns. */
    private double horizontalGap;
    
    /** The vertical gap between items in a column. */
    private double verticalGap;
    
    /**
     * Creates a new instance.
     */
    public ColumnArrangement() {   
    }
    
    /**
     * Creates a new instance.
     * 
     * @param hAlign  the horizontal alignment (currently ignored).
     * @param vAlign  the vertical alignment (currently ignored).
     * @param hGap  the horizontal gap.
     * @param vGap  the vertical gap.
     */
    public ColumnArrangement(HorizontalAlignment hAlign, 
                             VerticalAlignment vAlign,
                             double hGap, double vGap) {        
        this.horizontalAlignment = hAlign;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[2]++;
        this.verticalAlignment = vAlign;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[3]++;
        this.horizontalGap = hGap;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[4]++;
        this.verticalGap = vGap;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[5]++;
    }
    
    /**
     * Adds a block to be managed by this instance.  This method is usually 
     * called by the {@link BlockContainer}, you shouldn't need to call it 
     * directly.
     * 
     * @param block  the block.
     * @param key  a key that controls the position of the block.
     */
    public void add(Block block, Object key) {
        // since the flow layout is relatively straightforward, no information
        // needs to be recorded here
    }
    
    /**
     * Calculates and sets the bounds of all the items in the specified 
     * container, subject to the given constraint.  The <code>Graphics2D</code>
     * can be used by some items (particularly items containing text) to 
     * calculate sizing parameters.
     * 
     * @param container  the container whose items are being arranged.
     * @param g2  the graphics device.
     * @param constraint  the size constraint.
     * 
     * @return The size of the container after arrangement of the contents.
     */
    public Size2D arrange(BlockContainer container, Graphics2D g2,
                          RectangleConstraint constraint) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[6]++;
        
        LengthConstraintType w = constraint.getWidthConstraintType();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[7]++;
        LengthConstraintType h = constraint.getHeightConstraintType();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[1]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[3]++;
                return arrangeNN(container, g2);
  
            }
            else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[4]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[10]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[5]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[6]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[7]++;
                throw new RuntimeException("Not implemented.");
  
            } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[8]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[2]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[12]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[9]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[11]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[12]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[14]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[13]++;
                return arrangeFF(container, g2, constraint);
 
            }
            else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[14]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[15]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[15]++;
                throw new RuntimeException("Not implemented.");
  
            } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[16]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[10]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[16]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[17]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[17]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[19]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[20]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[18]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[21]++;
                return arrangeRF(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[22]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[19]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[23]++;
                return arrangeRR(container, g2, constraint);
  
            } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[24]++;}
}
}

        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[18]++;}
}
}
        return new Size2D();  // TODO: complete this
        
    }

    /**
     * Calculates and sets the bounds of all the items in the specified 
     * container, subject to the given constraint.  The <code>Graphics2D</code>
     * can be used by some items (particularly items containing text) to 
     * calculate sizing parameters.
     * 
     * @param container  the container whose items are being arranged.
     * @param g2  the graphics device.
     * @param constraint  the size constraint.
     * 
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeFF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
        // TODO: implement properly
        return arrangeNF(container, g2, constraint);
    }
    
    /**
     * Calculates and sets the bounds of all the items in the specified 
     * container, subject to the given constraint.  The <code>Graphics2D</code>
     * can be used by some items (particularly items containing text) to 
     * calculate sizing parameters.
     * 
     * @param container  the container whose items are being arranged.
     * @param constraint  the size constraint.
     * @param g2  the graphics device.
     * 
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeNF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[20]++;
    
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[21]++;
        
        double height = constraint.getHeight();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[22]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((height <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[25]++;
            height = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[23]++;

        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[26]++;}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[24]++;
        
        double x = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[25]++;
        double y = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[26]++;
        double maxWidth = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[27]++;
        List itemsInColumn = new ArrayList();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[1]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[1]--;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[2]--;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[3]++;
}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[29]++;
            Block block = (Block) blocks.get(i);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[30]++;
            Size2D size = block.arrange(g2, RectangleConstraint.NONE);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[31]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((y + size.height <= height) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[27]++;
                itemsInColumn.add(block);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[32]++;
                block.setBounds(
                    new Rectangle2D.Double(x, y, size.width, size.height)
                );
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[33]++;
                y = y + size.height + this.verticalGap;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[34]++;
                maxWidth = Math.max(maxWidth, size.width);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[35]++;

            }
            else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[28]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[36]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((itemsInColumn.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[29]++;
                    // place in this column (truncated) anyway
                    block.setBounds(
                        new Rectangle2D.Double(
                            x, y, size.width, Math.min(size.height, height - y)
                        )
                    );
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[37]++;
                    y = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[38]++;
                    x = x + size.width + this.horizontalGap;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[39]++;

                }
                else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[30]++;
                    // start new column
                    itemsInColumn.clear();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[40]++;
                    x = x + maxWidth + this.horizontalGap;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[41]++;
                    y = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[42]++;
                    maxWidth = size.width;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[43]++;
                    block.setBounds(
                        new Rectangle2D.Double(
                            x, y, size.width, Math.min(size.height, height)
                        )
                    );
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[44]++;
                    y = size.height + this.verticalGap;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[45]++;
                    itemsInColumn.add(block);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[46]++;
                }
            }
        }
        return new Size2D(x + maxWidth, constraint.getHeight());  
    }

    protected Size2D arrangeRR(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[47]++;

        // first arrange without constraints, and see if this fits within
        // the required ranges...
        Size2D s1 = arrangeNN(container, g2);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[48]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((constraint.getHeightRange().contains(s1.height)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[31]++;
            return s1;
  // TODO: we didn't check the width yet
        }
        else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[32]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[49]++;
            RectangleConstraint c = constraint.toFixedHeight(
                constraint.getHeightRange().getUpperBound()
            );
            return arrangeRF(container, g2, c);
        }
    }
    
    /**
     * Arranges the blocks in the container using a fixed height and a
     * range for the width.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size of the container after arrangement.
     */
    protected Size2D arrangeRF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[50]++;

        Size2D s = arrangeNF(container, g2, constraint);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[51]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((constraint.getWidthRange().contains(s.width)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[33]++;
            return s;
   
        }
        else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[34]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[52]++;
            RectangleConstraint c = constraint.toFixedWidth(
                constraint.getWidthRange().constrain(s.getWidth())
            );
            return arrangeFF(container, g2, c);
        }
    }

    /**
     * Arranges the blocks without any constraints.  This puts all blocks
     * into a single column.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * 
     * @return The size after the arrangement.
     */
    protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[53]++;
        double y = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[54]++;
        double height = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[55]++;
        double maxWidth = 0.0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[56]++;
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[57]++;
        int blockCount = blocks.size();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[58]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((blockCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[35]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[59]++;
            Size2D[] sizes = new Size2D[blocks.size()];
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[60]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[4]--;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[5]--;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[6]++;
}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[61]++;
                Block block = (Block) blocks.get(i);
                sizes[i] = block.arrange(g2, RectangleConstraint.NONE);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[62]++;
                height = height + sizes[i].getHeight();
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[63]++;
                maxWidth = Math.max(sizes[i].width, maxWidth);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[64]++;
                block.setBounds(
                    new Rectangle2D.Double(
                        0.0, y, sizes[i].width, sizes[i].height
                    )
                );
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[65]++;
                y = y + sizes[i].height + this.verticalGap;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[66]++;
            }
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[67]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((blockCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[37]++;
                height = height + this.verticalGap * (blockCount - 1);
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[68]++;
   
            } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[38]++;}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[69]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.horizontalAlignment != HorizontalAlignment.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[39]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[70]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[7]++;


int CodeCoverConditionCoverageHelper_C23;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[7]--;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[8]--;
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.loops[9]++;
}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[71]++;
int CodeCoverConditionCoverageHelper_C24;
                    //Block b = (Block) blocks.get(i);
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.horizontalAlignment 
                            == HorizontalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[41]++;

                        //TODO: shift block right by half
                    }
                    else {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[42]++;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[72]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.horizontalAlignment 
                            == HorizontalAlignment.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[43]++;

                        //TODO: shift block over to right
                    } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[44]++;}
}
                }
            
            } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[40]++;}

        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[36]++;}
        return new Size2D(maxWidth, height);
    }

    /**
     * Clears any cached information.
     */
    public void clear() {
        // no action required.
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[73]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[45]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[46]++;}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[74]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj instanceof ColumnArrangement) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[47]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[48]++;}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[75]++;
        ColumnArrangement that = (ColumnArrangement) obj;
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[76]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.horizontalAlignment != that.horizontalAlignment) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[50]++;}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[77]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.verticalAlignment != that.verticalAlignment) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[52]++;}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[78]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.horizontalGap != that.horizontalGap) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[53]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[54]++;}
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.statements[79]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.verticalGap != that.verticalGap) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[55]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl.branches[56]++;}
        return true;
    }
    

}

class CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl ());
  }
    public static long[] statements = new long[80];
    public static long[] branches = new long[57];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.ColumnArrangement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
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

  public CodeCoverCoverageCounter$b2bzh62a4hr0zmt6w29bxh0r9i4gfnsmzl () {
    super("org.jfree.chart.block.ColumnArrangement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 79; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 56; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.ColumnArrangement.java");
      for (int i = 1; i <= 79; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 56; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
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

