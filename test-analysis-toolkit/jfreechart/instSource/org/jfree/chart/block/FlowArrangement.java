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
 * FlowArrangement.java
 * --------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 04-Feb-2005 : Implemented equals() and made serializable (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint (DG);
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
 * Arranges blocks in a flow layout.  This class is immutable.
 */
public class FlowArrangement implements Arrangement, Serializable {
  static {
    CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 4543632485478613800L;
  static {
    CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[1]++;
  }
    
    /** The horizontal alignment of blocks. */
    private HorizontalAlignment horizontalAlignment;
    
    /** The vertical alignment of blocks within each row. */
    private VerticalAlignment verticalAlignment;
    
    /** The horizontal gap between items within rows. */
    private double horizontalGap;
    
    /** The vertical gap between rows. */
    private double verticalGap;
    
    /**
     * Creates a new instance.
     */
    public FlowArrangement() {   
        this(HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 2.0, 2.0);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[2]++;
    }
     
    /**
     * Creates a new instance.
     * 
     * @param hAlign  the horizontal alignment (currently ignored).
     * @param vAlign  the vertical alignment (currently ignored).
     * @param hGap  the horizontal gap.
     * @param vGap  the vertical gap.
     */
    public FlowArrangement(HorizontalAlignment hAlign, VerticalAlignment vAlign,
                           double hGap, double vGap) {   
        this.horizontalAlignment = hAlign;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[3]++;
        this.verticalAlignment = vAlign;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[4]++;
        this.horizontalGap = hGap;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[5]++;
        this.verticalGap = vGap;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[6]++;
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
        // since the flow layout is relatively straightforward, 
        // no information needs to be recorded here
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
     * @return The size of the container after arrangement of the contents.
     */
    public Size2D arrange(BlockContainer container, Graphics2D g2,
                          RectangleConstraint constraint) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[7]++;
        
        LengthConstraintType w = constraint.getWidthConstraintType();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[8]++;
        LengthConstraintType h = constraint.getHeightConstraintType();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[1]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[3]++;
                return arrangeNN(container, g2);
  
            }
            else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[4]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[11]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[5]++;
                return arrangeNF(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[6]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[12]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[7]++;
                throw new RuntimeException("Not implemented.");
  
            } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[8]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[2]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[13]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[9]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[11]++;
                return arrangeFN(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[12]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[15]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[13]++;
                return arrangeFF(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[14]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[16]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[15]++;
                return arrangeFR(container, g2, constraint);
  
            } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[16]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[10]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[17]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[17]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[18]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[19]++;
                return arrangeRN(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[20]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[19]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[21]++;
                return arrangeRF(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[22]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[20]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[23]++;
                return arrangeRR(container, g2, constraint);
   
            } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[24]++;}
}
}

        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[18]++;}
}
}
        throw new RuntimeException("Unrecognised constraint type.");
        
    }

    /**
     * Arranges the blocks in the container with a fixed width and no height 
     * constraint.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size.
     */
    protected Size2D arrangeFN(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[21]++;
        
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[22]++;
        double width = constraint.getWidth();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[23]++;
        
        double x = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[24]++;
        double y = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[25]++;
        double maxHeight = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[26]++;
        List itemsInRow = new ArrayList();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[27]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[1]--;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[2]--;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[3]++;
}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[28]++;
            Block block = (Block) blocks.get(i);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[29]++;
            Size2D size = block.arrange(g2, RectangleConstraint.NONE);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[30]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((x + size.width <= width) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[25]++;
                itemsInRow.add(block);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[31]++;
                block.setBounds(
                    new Rectangle2D.Double(x, y, size.width, size.height)
                );
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[32]++;
                x = x + size.width + this.horizontalGap;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[33]++;
                maxHeight = Math.max(maxHeight, size.height);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[34]++;

            }
            else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[26]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[35]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((itemsInRow.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[27]++;
                    // place in this row (truncated) anyway
                    block.setBounds(
                        new Rectangle2D.Double(
                            x, y, Math.min(size.width, width - x), size.height
                        )
                    );
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[36]++;
                    x = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[37]++;
                    y = y + size.height + this.verticalGap;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[38]++;

                }
                else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[28]++;
                    // start new row
                    itemsInRow.clear();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[39]++;
                    x = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[40]++;
                    y = y + maxHeight + this.verticalGap;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[41]++;
                    maxHeight = size.height;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[42]++;
                    block.setBounds(
                        new Rectangle2D.Double(
                            x, y, Math.min(size.width, width), size.height
                        )
                    );
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[43]++;
                    x = size.width + this.horizontalGap;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[44]++;
                    itemsInRow.add(block);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[45]++;
                }
            }
        }
        return new Size2D(constraint.getWidth(), y + maxHeight);  
    }
    
    /**
     * Arranges the blocks in the container with a fixed width and a range
     * constraint on the height.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeFR(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[46]++;

        Size2D s = arrangeFN(container, g2, constraint);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[47]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((constraint.getHeightRange().contains(s.height)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[29]++;
            return s;
   
        }
        else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[30]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[48]++;
            RectangleConstraint c = constraint.toFixedHeight(
                constraint.getHeightRange().constrain(s.getHeight())
            );
            return arrangeFF(container, g2, c);
        }
    }

    /**
     * Arranges the blocks in the container with the overall height and width
     * specified as fixed constraints.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeFF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {

        // TODO: implement this properly
        return arrangeFN(container, g2, constraint);
    }

    /**
     * Arranges the blocks with the overall width and height to fit within 
     * specified ranges.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size after the arrangement.
     */
    protected Size2D arrangeRR(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[49]++;

        // first arrange without constraints, and see if this fits within
        // the required ranges...
        Size2D s1 = arrangeNN(container, g2);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[50]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((constraint.getWidthRange().contains(s1.width)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[31]++;
            return s1;
  // TODO: we didn't check the height yet
        }
        else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[32]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[51]++;
            RectangleConstraint c = constraint.toFixedWidth(
                constraint.getWidthRange().getUpperBound()
            );
            return arrangeFR(container, g2, c);
        }
    }
    
    /**
     * Arranges the blocks in the container with a range constraint on the
     * width and a fixed height.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeRF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[52]++;

        Size2D s = arrangeNF(container, g2, constraint);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[53]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((constraint.getWidthRange().contains(s.width)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[33]++;
            return s;
   
        }
        else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[34]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[54]++;
            RectangleConstraint c = constraint.toFixedWidth(
                constraint.getWidthRange().constrain(s.getWidth())
            );
            return arrangeFF(container, g2, c);
        }
    }

    /**
     * Arranges the block with a range constraint on the width, and no 
     * constraint on the height.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeRN(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[55]++;
        // first arrange without constraints, then see if the width fits
        // within the required range...if not, call arrangeFN() at max width
        Size2D s1 = arrangeNN(container, g2);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[56]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((constraint.getWidthRange().contains(s1.width)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[35]++;
            return s1;
   
        }
        else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[36]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[57]++;
            RectangleConstraint c = constraint.toFixedWidth(
                constraint.getWidthRange().getUpperBound()
            );
            return arrangeFN(container, g2, c);
        }
    }
    
    /**
     * Arranges the blocks without any constraints.  This puts all blocks
     * into a single row.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * 
     * @return The size after the arrangement.
     */
    protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[58]++;
        double x = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[59]++;
        double width = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[60]++;
        double maxHeight = 0.0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[61]++;
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[62]++;
        int blockCount = blocks.size();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[63]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((blockCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[37]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[64]++;
            Size2D[] sizes = new Size2D[blocks.size()];
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[65]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[4]++;


int CodeCoverConditionCoverageHelper_C21;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[4]--;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[5]--;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[6]++;
}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[66]++;
                Block block = (Block) blocks.get(i);
                sizes[i] = block.arrange(g2, RectangleConstraint.NONE);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[67]++;
                width = width + sizes[i].getWidth();
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[68]++;
                maxHeight = Math.max(sizes[i].height, maxHeight);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[69]++;
                block.setBounds(
                    new Rectangle2D.Double(
                        x, 0.0, sizes[i].width, sizes[i].height
                    )
                );
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[70]++;
                x = x + sizes[i].width + this.horizontalGap;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[71]++;
            }
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[72]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((blockCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[39]++;
                width = width + this.horizontalGap * (blockCount - 1);
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[73]++;
   
            } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[40]++;}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[74]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.verticalAlignment != VerticalAlignment.TOP) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[41]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[75]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[7]++;


int CodeCoverConditionCoverageHelper_C24;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < blocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[7]--;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[8]--;
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.loops[9]++;
}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[76]++;
int CodeCoverConditionCoverageHelper_C25;
                    //Block b = (Block) blocks.get(i);
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.verticalAlignment == VerticalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[43]++;

                        //TODO: shift block down by half
                    }
                    else {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[44]++;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[77]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.verticalAlignment 
                            == VerticalAlignment.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[45]++;

                        //TODO: shift block down to bottom
                    } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[46]++;}
}
                }
            
            } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[42]++;}

        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[38]++;}
        return new Size2D(width, maxHeight);
    }
    
    /**
     * Arranges the blocks with no width constraint and a fixed height 
     * constraint.  This puts all blocks into a single row.
     * 
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     * 
     * @return The size after the arrangement.
     */
    protected Size2D arrangeNF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
        // TODO: for now we are ignoring the height constraint
        return arrangeNN(container, g2);
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
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[78]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[47]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[48]++;}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[79]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((obj instanceof FlowArrangement) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[49]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[50]++;}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[80]++;
        FlowArrangement that = (FlowArrangement) obj;
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[81]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.horizontalAlignment != that.horizontalAlignment) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[52]++;}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[82]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.verticalAlignment != that.verticalAlignment) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[54]++;}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[83]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.horizontalGap != that.horizontalGap) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[55]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[56]++;}
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.statements[84]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.verticalGap != that.verticalGap) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[57]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt.branches[58]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt ());
  }
    public static long[] statements = new long[85];
    public static long[] branches = new long[59];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[33];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.FlowArrangement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 32; i++) {
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

  public CodeCoverCoverageCounter$8858cmacs9oo6kn2u5jfip0xflel3dt () {
    super("org.jfree.chart.block.FlowArrangement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 84; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 58; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.FlowArrangement.java");
      for (int i = 1; i <= 84; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 58; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 32; i++) {
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

