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
 * BorderArrangement.java
 * ----------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint (DG);
 * 24-Feb-2005 : Improved arrangeRR() method (DG);
 * 03-May-2005 : Implemented Serializable and added equals() method (DG);
 * 13-May-2005 : Fixed bugs in the arrange() method (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.data.Range;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.Size2D;
import org.jfree.util.ObjectUtilities;

/**
 * An arrangement manager that lays out blocks in a similar way to
 * Swing's BorderLayout class.
 */
public class BorderArrangement implements Arrangement, Serializable {
  static {
    CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 506071142274883745L;
  static {
    CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[1]++;
  }
    
    /** The block (if any) at the center of the layout. */
    private Block centerBlock;

    /** The block (if any) at the top of the layout. */
    private Block topBlock;
    
    /** The block (if any) at the bottom of the layout. */
    private Block bottomBlock;
    
    /** The block (if any) at the left of the layout. */
    private Block leftBlock;
    
    /** The block (if any) at the right of the layout. */
    private Block rightBlock;
    
    /**
     * Creates a new instance.
     */
    public BorderArrangement() {
    }
    
    /**
     * Adds a block to the arrangement manager at the specified edge.
     * 
     * @param block  the block (<code>null</code> permitted).
     * @param key  the edge (an instance of {@link RectangleEdge}) or 
     *             <code>null</code> for the center block.
     */
    public void add(Block block, Object key) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[1]++;
            this.centerBlock = block;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[3]++;

        }
        else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[2]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[4]++;
            RectangleEdge edge = (RectangleEdge) key;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[3]++;
                this.topBlock = block;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[6]++;

            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[4]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[7]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[5]++;
                this.bottomBlock = block;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[8]++;

            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[6]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[9]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[7]++;
                this.leftBlock = block;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[10]++;

            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[8]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[11]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[9]++;
                this.rightBlock = block;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[12]++;

            } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[10]++;}
}
}
}
        }
    }
    
    /**
     * Arranges the items in the specified container, subject to the given 
     * constraint.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The block size.
     */
    public Size2D arrange(BlockContainer container, 
                          Graphics2D g2, 
                          RectangleConstraint constraint) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[13]++;
        RectangleConstraint contentConstraint 
            = container.toContentConstraint(constraint);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[14]++;
        Size2D contentSize = null;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[15]++;
        LengthConstraintType w = contentConstraint.getWidthConstraintType();
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[16]++;
        LengthConstraintType h = contentConstraint.getHeightConstraintType();
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[11]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[13]++;
                contentSize = arrangeNN(container, g2);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[19]++;
  
            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[14]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[20]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[15]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[16]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[21]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[17]++;
                throw new RuntimeException("Not implemented.");
  
            } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[18]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[12]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[22]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[19]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[23]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[21]++;
                contentSize = arrangeFN(container, g2, constraint.getWidth());
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[24]++;
  
            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[22]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[25]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[23]++;
                contentSize = arrangeFF(container, g2, constraint);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[26]++;
  
            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[24]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[27]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[25]++;
                contentSize = arrangeFR(container, g2, constraint);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[28]++;
  
            } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[26]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[20]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[29]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[27]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[30]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[29]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[30]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[31]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[31]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[32]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[32]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[33]++;
                contentSize = arrangeRR(
                    container, constraint.getWidthRange(),
                    constraint.getHeightRange(), g2
                );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[33]++;
  
            } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[34]++;}
}
}

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[28]++;}
}
}
        return new Size2D(
            container.calculateTotalWidth(contentSize.getWidth()),
            container.calculateTotalHeight(contentSize.getHeight())
        );
    }
    
    /**
     * Performs an arrangement without constraints.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * 
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[34]++;
        double[] w = new double[5];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[35]++;
        double[] h = new double[5];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[36]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.topBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[35]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[37]++;
            Size2D size = this.topBlock.arrange(
                g2, RectangleConstraint.NONE
            );
            w[0] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[38]++;
            h[0] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[39]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[36]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[40]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.bottomBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[37]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[41]++;
            Size2D size = this.bottomBlock.arrange(
                g2, RectangleConstraint.NONE
            );
            w[1] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[42]++;
            h[1] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[43]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[38]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[44]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.leftBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[39]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[45]++;
            Size2D size = this.leftBlock.arrange(
                g2, RectangleConstraint.NONE
            );
            w[2] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[46]++;
            h[2] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[47]++;

       } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[40]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[48]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.rightBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[41]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[49]++;
            Size2D size = this.rightBlock.arrange(
                g2, RectangleConstraint.NONE
            );
            w[3] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[50]++;
            h[3] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[51]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[42]++;}
        
        h[2] = Math.max(h[2], h[3]);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[52]++;
        h[3] = h[2];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[53]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[54]++;
int CodeCoverConditionCoverageHelper_C22;
        
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.centerBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[43]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[55]++;
            Size2D size = this.centerBlock.arrange(
                g2, RectangleConstraint.NONE
            );
            w[4] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[56]++;
            h[4] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[57]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[44]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[58]++;
        double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[59]++;
        double centerHeight = Math.max(h[2], Math.max(h[3], h[4]));
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[60]++;
        double height = h[0] + h[1] + centerHeight;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[61]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.topBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[45]++;
            this.topBlock.setBounds(
                new Rectangle2D.Double(0.0, 0.0, width, h[0])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[62]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[46]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[63]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.bottomBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[47]++;
            this.bottomBlock.setBounds(
                new Rectangle2D.Double(0.0, height - h[1], width, h[1])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[64]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[48]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[65]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.leftBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[49]++;
            this.leftBlock.setBounds(
                new Rectangle2D.Double(0.0, h[0], w[2], centerHeight)
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[66]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[50]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[67]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.rightBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[51]++;
            this.rightBlock.setBounds(
                new Rectangle2D.Double(width - w[3], h[0], w[3], centerHeight)
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[68]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[52]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[69]++;
int CodeCoverConditionCoverageHelper_C27;
        
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.centerBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[53]++;
            this.centerBlock.setBounds(
                new Rectangle2D.Double(
                    w[2], h[0], width - w[2] - w[3], centerHeight
                )
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[70]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[54]++;}
        return new Size2D(width, height);
    }

    /**
     * Performs an arrangement with a fixed width and a range for the height.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeFR(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[71]++;
        Size2D size1 = arrangeFN(container, g2, constraint.getWidth());
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[72]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((constraint.getHeightRange().contains(size1.getHeight())) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[55]++;
            return size1;
   
        }
        else {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[56]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[73]++;
            double h = constraint.getHeightRange().constrain(size1.getHeight());
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[74]++;
            RectangleConstraint c2 = constraint.toFixedHeight(h);
            return arrange(container, g2, c2);   
        }
    }
    
    /** 
     * Arranges the container width a fixed width and no constraint on the 
     * height.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param width  the fixed width.
     * 
     * @return The container size after arranging the contents.
     */
    protected Size2D arrangeFN(BlockContainer container, Graphics2D g2,
                               double width) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[75]++;
        double[] w = new double[5];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[76]++;
        double[] h = new double[5];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[77]++;
        RectangleConstraint c1 = new RectangleConstraint(
            width, null, LengthConstraintType.FIXED,
            0.0, null, LengthConstraintType.NONE
        );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[78]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.topBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[57]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[79]++;
            Size2D size = this.topBlock.arrange(g2, c1);
            w[0] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[80]++;
            h[0] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[81]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[58]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[82]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.bottomBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[59]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[83]++;
            Size2D size = this.bottomBlock.arrange(g2, c1);
            w[1] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[84]++;
            h[1] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[85]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[60]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[86]++;
        RectangleConstraint c2 = new RectangleConstraint(
            0.0, new Range(0.0, width), LengthConstraintType.RANGE,
            0.0, null, LengthConstraintType.NONE
        );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[87]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.leftBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[61]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[88]++;
            Size2D size = this.leftBlock.arrange(g2, c2);
            w[2] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[89]++;
            h[2] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[90]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[62]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[91]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.rightBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[63]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[92]++;
            double maxW = Math.max(width - w[2], 0.0);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[93]++;
            RectangleConstraint c3 = new RectangleConstraint(
                0.0, new Range(Math.min(w[2], maxW), maxW), 
                LengthConstraintType.RANGE,
                0.0, null, LengthConstraintType.NONE
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[94]++;    
            Size2D size = this.rightBlock.arrange(g2, c3);
            w[3] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[95]++;
            h[3] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[96]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[64]++;}
        
        h[2] = Math.max(h[2], h[3]);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[97]++;
        h[3] = h[2];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[98]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[99]++;
int CodeCoverConditionCoverageHelper_C33;
        
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((this.centerBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[65]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[100]++;
            RectangleConstraint c4 = new RectangleConstraint(
                width - w[2] - w[3], null, LengthConstraintType.FIXED,
                0.0, null, LengthConstraintType.NONE
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[101]++;    
            Size2D size = this.centerBlock.arrange(g2, c4);
            w[4] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[102]++;
            h[4] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[103]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[66]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[104]++;
        double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
        return arrange(container, g2, new RectangleConstraint(width, height));
    }

    /**
     * Performs an arrangement with range constraints on both the vertical 
     * and horizontal sides.
     * 
     * @param container  the container.
     * @param widthRange  the allowable range for the container width.
     * @param heightRange  the allowable range for the container height.
     * @param g2  the graphics device.
     * 
     * @return The container size.
     */
    protected Size2D arrangeRR(BlockContainer container, 
                               Range widthRange, Range heightRange, 
                               Graphics2D g2) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[105]++;
        double[] w = new double[5];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[106]++;
        double[] h = new double[5];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[107]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.topBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[67]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[108]++;
            RectangleConstraint c1 = new RectangleConstraint(
                widthRange, heightRange
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[109]++;
            Size2D size = this.topBlock.arrange(g2, c1);
            w[0] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[110]++;
            h[0] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[111]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[68]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[112]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.bottomBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[69]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[113]++;
            Range heightRange2 = Range.shift(heightRange, -h[0], false);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[114]++;
            RectangleConstraint c2 = new RectangleConstraint(
                widthRange, heightRange2
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[115]++;  
            Size2D size = this.bottomBlock.arrange(g2, c2);
            w[1] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[116]++;
            h[1] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[117]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[70]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[118]++;
        Range heightRange3 = Range.shift(heightRange, -(h[0] + h[1]));
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[119]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.leftBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[71]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[120]++;
            RectangleConstraint c3 = new RectangleConstraint(
                widthRange, heightRange3
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[121]++;
            Size2D size = this.leftBlock.arrange(g2, c3);
            w[2] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[122]++;
            h[2] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[123]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[72]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[124]++;
        Range widthRange2 = Range.shift(widthRange, -w[2], false);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[125]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.rightBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[73]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[126]++;
            RectangleConstraint c4 = new RectangleConstraint(
                widthRange2, heightRange3
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[127]++;
            Size2D size = this.rightBlock.arrange(g2, c4);
            w[3] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[128]++;
            h[3] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[129]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[74]++;}
        
        h[2] = Math.max(h[2], h[3]);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[130]++;
        h[3] = h[2];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[131]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[132]++;
        Range widthRange3 = Range.shift(widthRange, -(w[2] + w[3]), false);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[133]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.centerBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[75]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[134]++;
            RectangleConstraint c5 = new RectangleConstraint(
                widthRange3, heightRange3
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[135]++;
            // TODO:  the width and height ranges should be reduced by the 
            // height required for the top and bottom, and the width required
            // by the left and right 
            Size2D size = this.centerBlock.arrange(g2, c5);
            w[4] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[136]++;
            h[4] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[137]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[76]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[138]++;
        double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[139]++;
        double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[140]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.topBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[77]++;
            this.topBlock.setBounds(
                new Rectangle2D.Double(0.0, 0.0, width, h[0])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[141]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[78]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[142]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.bottomBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[79]++;
            this.bottomBlock.setBounds(
                new Rectangle2D.Double(0.0, height - h[1], width, h[1])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[143]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[80]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[144]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.leftBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[81]++;
            this.leftBlock.setBounds(
                new Rectangle2D.Double(0.0, h[0], w[2], h[2])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[145]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[82]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[146]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.rightBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[83]++;
            this.rightBlock.setBounds(
                new Rectangle2D.Double(width - w[3], h[0], w[3], h[3])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[147]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[84]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[148]++;
int CodeCoverConditionCoverageHelper_C43;
        
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.centerBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[85]++;
            this.centerBlock.setBounds(
                new Rectangle2D.Double(
                    w[2], h[0], width - w[2] - w[3], height - h[0] - h[1]
                )
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[149]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[86]++;}
        return new Size2D(width, height);
    }

    /**
     * Arranges the items within a container.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeFF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[150]++;
        double[] w = new double[5];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[151]++;
        double[] h = new double[5];
        w[0] = constraint.getWidth();
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[152]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[153]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.topBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[87]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[154]++;
            RectangleConstraint c1 = new RectangleConstraint(
                w[0], null, LengthConstraintType.FIXED,
                0.0, new Range(0.0, constraint.getHeight()), 
                LengthConstraintType.RANGE
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[155]++;
            Size2D size = this.topBlock.arrange(g2, c1);
            h[0] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[156]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[88]++;}
        w[1] = w[0];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[157]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[158]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.bottomBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[89]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[159]++;
            RectangleConstraint c2 = new RectangleConstraint(
                w[0], null, LengthConstraintType.FIXED,
                0.0, new Range(0.0, constraint.getHeight() - h[0]), 
                LengthConstraintType.RANGE
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[160]++;
            Size2D size = this.bottomBlock.arrange(g2, c2);
            h[1] = size.height;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[161]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[90]++;}
        h[2] = constraint.getHeight() - h[1] - h[0];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[162]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[163]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.leftBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[91]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[164]++;
            RectangleConstraint c3 = new RectangleConstraint(
                0.0, new Range(0.0, constraint.getWidth()), 
                LengthConstraintType.RANGE,
                h[2], null, LengthConstraintType.FIXED
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[165]++;
            Size2D size = this.leftBlock.arrange(g2, c3);
            w[2] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[166]++;
            
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[92]++;}
        h[3] = h[2];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[167]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[168]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.rightBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[93]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[169]++;
            RectangleConstraint c4 = new RectangleConstraint(
                0.0, new Range(0.0, constraint.getWidth() - w[2]), 
                LengthConstraintType.RANGE,
                h[2], null, LengthConstraintType.FIXED
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[170]++;
            Size2D size = this.rightBlock.arrange(g2, c4);
            w[3] = size.width;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[171]++;
            
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[94]++;}
        h[4] = h[2];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[172]++;
        w[4] = constraint.getWidth() - w[3] - w[2];
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[173]++;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[174]++;
        RectangleConstraint c5 = new RectangleConstraint(w[4], h[4]);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[175]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((this.centerBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[95]++;
            this.centerBlock.arrange(g2, c5);
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[176]++;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[96]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[177]++;
int CodeCoverConditionCoverageHelper_C49;
       
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((this.topBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[97]++;
            this.topBlock.setBounds(
                new Rectangle2D.Double(0.0, 0.0, w[0], h[0])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[178]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[98]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[179]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.bottomBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[99]++;
            this.bottomBlock.setBounds(
                new Rectangle2D.Double(0.0, h[0] + h[2], w[1], h[1])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[180]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[100]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[181]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.leftBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[101]++;
            this.leftBlock.setBounds(
                new Rectangle2D.Double(0.0, h[0], w[2], h[2])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[182]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[102]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[183]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((this.rightBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[103]++;
            this.rightBlock.setBounds(
                new Rectangle2D.Double(w[2] + w[4], h[0], w[3], h[3])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[184]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[104]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[185]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this.centerBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[105]++;
            this.centerBlock.setBounds(
                new Rectangle2D.Double(w[2], h[0], w[4], h[4])
            );
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[186]++;

        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[106]++;}
        return new Size2D(constraint.getWidth(), constraint.getHeight());
    }
    
    /**
     * Clears the layout.
     */
    public void clear() {
        this.centerBlock = null;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[187]++;
        this.topBlock = null;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[188]++;
        this.bottomBlock = null;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[189]++;
        this.leftBlock = null;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[190]++;
        this.rightBlock = null;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[191]++;
    }
    
    /**
     * Tests this arrangement for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[192]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[107]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[108]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[193]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((obj instanceof BorderArrangement) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[109]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[110]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[194]++;
        BorderArrangement that = (BorderArrangement) obj;
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[195]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.topBlock, that.topBlock)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[111]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[112]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[196]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.bottomBlock, that.bottomBlock)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[113]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[114]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[197]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.leftBlock, that.leftBlock)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[115]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[116]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[198]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rightBlock, that.rightBlock)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[117]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[118]++;}
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.statements[199]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.centerBlock, that.centerBlock)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[119]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75.branches[120]++;}
        return true;
    }
}

class CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75 ());
  }
    public static long[] statements = new long[200];
    public static long[] branches = new long[121];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[61];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.BorderArrangement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 60; i++) {
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

  public CodeCoverCoverageCounter$awfcu11y2ygt82goewl7ajxa3j0bg3ue75 () {
    super("org.jfree.chart.block.BorderArrangement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 199; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 120; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 60; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.BorderArrangement.java");
      for (int i = 1; i <= 199; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 120; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 60; i++) {
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

