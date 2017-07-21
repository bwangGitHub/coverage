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
 * GridArrangement.java
 * --------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 08-Feb-2005 : Version 1 (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.ui.Size2D;

/**
 * Arranges blocks in a grid within their container.
 */
public class GridArrangement implements Arrangement, Serializable {
  static {
    CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -2563758090144655938L;
  static {
    CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[1]++;
  }
    
    /** The rows. */
    private int rows;
    
    /** The columns. */
    private int columns;
    
    /**
     * Creates a new grid arrangement.
     * 
     * @param rows  the row count.
     * @param columns  the column count.
     */
    public GridArrangement(int rows, int columns) {
        this.rows = rows;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[2]++;
        this.columns = columns;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[3]++;
    }
    
    /**
     * Adds a block and a key which can be used to determine the position of 
     * the block in the arrangement.  This method is called by the container 
     * (you don't need to call this method directly) and gives the arrangement
     * an opportunity to record the details if they are required.
     * 
     * @param block  the block.
     * @param key  the key (<code>null</code> permitted).
     */
    public void add(Block block, Object key) {
        // can safely ignore   
    }
    
    /**
     * Arranges the blocks within the specified container, subject to the given
     * constraint.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size following the arrangement.
     */
    public Size2D arrange(BlockContainer container, Graphics2D g2,
                          RectangleConstraint constraint) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[4]++;
        LengthConstraintType w = constraint.getWidthConstraintType();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[5]++;
        LengthConstraintType h = constraint.getHeightConstraintType();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[1]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[3]++;
                return arrangeNN(container, g2);
  
            }
            else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[4]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[8]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[5]++;
                
                throw new RuntimeException("Not yet implemented.");
  
            }
            else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[6]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[9]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[7]++;
                // find optimum height, then map to range
                throw new RuntimeException("Not yet implemented.");
  
            } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[8]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[2]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[10]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[9]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[11]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[11]++;
                // find optimum height
                return arrangeFN(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[12]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[12]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[13]++;
                return arrangeFF(container, g2, constraint);

            }
            else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[14]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[13]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[15]++;
                // find optimum height and map to range
                return arrangeFR(container, g2, constraint);
  
            } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[16]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[10]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[14]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[17]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[15]++;
int CodeCoverConditionCoverageHelper_C10;
            // find optimum width and map to range
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[19]++;
                // find optimum height
                throw new RuntimeException("Not yet implemented.");
  
            }
            else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[20]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[16]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[21]++;
                // fixed width
                throw new RuntimeException("Not yet implemented.");
  
            }
            else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[22]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[17]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[23]++;
                throw new RuntimeException("Not yet implemented.");
  
            } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[24]++;}
}
}

        } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[18]++;}
}
}
        return new Size2D();  // TODO: complete this
    }
    
    /**
     * Arranges the container with no constraint on the width or height.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * 
     * @return The size.
     */
    protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[18]++;
        double maxW = 0.0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[19]++;
        double maxH = 0.0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[20]++;
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[21]++;
        Iterator iterator = blocks.iterator();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[22]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[1]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[2]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[3]++;
}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[23]++;
            Block b = (Block) iterator.next();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[24]++;
            Size2D s = b.arrange(g2, RectangleConstraint.NONE);
            maxW = Math.max(maxW, s.width);
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[25]++;
            maxH = Math.max(maxH, s.height);
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[26]++;
        }
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[27]++;
        double width = this.columns * maxW;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[28]++;
        double height = this.rows * maxH;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[29]++;
        RectangleConstraint c = new RectangleConstraint(width, height);
        return arrangeFF(container, g2, c);
    }
    
    /**
     * Arranges the container with a fixed overall width and height.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeFF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[30]++;
        double width = constraint.getWidth() /  this.columns;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[31]++;
        double height = constraint.getHeight() / this.rows;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[32]++;
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[33]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int c = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c < this.columns) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[4]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[5]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[6]++;
}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[7]++;


int CodeCoverConditionCoverageHelper_C15;
            for (int r = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((r < this.rows) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[7]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[8]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[9]++;
}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[35]++;
                int index = r * this.columns + c;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[36]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((index == blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[25]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[37]++;
                    break;
   
                } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[26]++;}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[38]++;
                Block b = (Block) blocks.get(index);
                b.setBounds(new Rectangle2D.Double(
                    c * width, r * height, width, height
                ));
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[39]++;
            }
        }
        return new Size2D(this.columns * width, this.rows * height);
    }

    /**
     * Arrange with a fixed width and a height within a given range.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size of the arrangement.
     */
    protected Size2D arrangeFR(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[40]++;
        
        RectangleConstraint c1 = constraint.toUnconstrainedHeight();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[41]++;
        Size2D size1 = arrange(container, g2, c1);
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[42]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((constraint.getHeightRange().contains(size1.getHeight())) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[27]++;
            return size1;
   
        }
        else {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[28]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[43]++;
            double h = constraint.getHeightRange().constrain(size1.getHeight());
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[44]++;
            RectangleConstraint c2 = constraint.toFixedHeight(h);
            return arrange(container, g2, c2);
        }
    }

    /**
     * Arrange with a fixed width and a height within a given range.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size of the arrangement.
     */
    protected Size2D arrangeFN(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[45]++;
        
        double width = constraint.getWidth() /  this.columns;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[46]++;
        RectangleConstraint constraint2 = constraint.toFixedWidth(width);
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[47]++;
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[48]++;
        double maxH = 0.0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[49]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[10]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int r = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((r < this.rows) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); r++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[10]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[11]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[12]++;
}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[50]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;
            for (int c = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c < this.columns) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[13]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[14]--;
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.loops[15]++;
}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[51]++;
                int index = r * this.columns + c;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[52]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((index == blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[29]++;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[53]++;
                    break;
   
                } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[30]++;}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[54]++;
                Block b = (Block) blocks.get(index);
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[55]++;
                Size2D s = b.arrange(g2, constraint2);
                maxH = Math.max(maxH, s.getHeight());
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[56]++;
            }
        }
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[57]++;
        RectangleConstraint constraint3 = constraint.toFixedHeight(
            maxH * this.rows
        );
        return arrange(container, g2, constraint3);
    }

    /**
     * Clears any cached layout information retained by the arrangement.
     */
    public void clear() {
        // nothing to clear   
    }
    
    /**
     * Compares this layout manager for equality with an arbitrary object.
     * 
     * @param obj  the object.
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[58]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[31]++;
            return true;

        } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[32]++;}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[59]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((obj instanceof GridArrangement) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[33]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[34]++;}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[60]++;
        GridArrangement that = (GridArrangement) obj;
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[61]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.columns != that.columns) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[36]++;}
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.statements[62]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.rows != that.rows) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[37]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht.branches[38]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht ());
  }
    public static long[] statements = new long[63];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.GridArrangement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 24; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$8cg5ipgikqikt4ngqbkv7aguffy72ht () {
    super("org.jfree.chart.block.GridArrangement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 62; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.GridArrangement.java");
      for (int i = 1; i <= 62; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

