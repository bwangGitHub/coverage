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
 * CenterArrangement.java
 * ----------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 08-Mar-2005 : Version 1 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Set bounds of contained block when arranging (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.List;

import org.jfree.ui.Size2D;

/**
 * Arranges a block in the center of its container.  This class is immutable.
 */
public class CenterArrangement implements Arrangement, Serializable {
  static {
    CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -353308149220382047L;
  static {
    CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[1]++;
  } 
    
    /**
     * Creates a new instance.
     */
    public CenterArrangement() {   
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
     * @param g2  the graphics device.
     * @param constraint  the size constraint.
     * 
     * @return The size of the container after arrangement of the contents.
     */
    public Size2D arrange(BlockContainer container, Graphics2D g2,
                          RectangleConstraint constraint) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[2]++;
        
        LengthConstraintType w = constraint.getWidthConstraintType();
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[3]++;
        LengthConstraintType h = constraint.getHeightConstraintType();
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[1]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[3]++;
                return arrangeNN(container, g2);
  
            }
            else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[4]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[6]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[5]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[6]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[7]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[7]++;
                throw new RuntimeException("Not implemented.");
  
            } else {
  CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[8]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[2]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[8]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[9]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[9]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[11]++;
                return arrangeFN(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[12]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[10]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[13]++;
                throw new RuntimeException("Not implemented.");
  
            }
            else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[14]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[11]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[15]++;
                throw new RuntimeException("Not implemented.");
  
            } else {
  CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[16]++;}
}
}

        }
        else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[10]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[12]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((w == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[17]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[13]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.NONE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[19]++;
                return arrangeRN(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[20]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[14]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.FIXED) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[21]++;
                return arrangeRF(container, g2, constraint);
  
            }
            else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[22]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[15]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((h == LengthConstraintType.RANGE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[23]++;
                return arrangeRR(container, g2, constraint);
   
            } else {
  CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[24]++;}
}
}

        } else {
  CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[18]++;}
}
}
        throw new IllegalArgumentException("Unknown LengthConstraintType.");
        
    }

    /**
     * Arranges the blocks in the container with a fixed width and no height 
     * constraint.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size.
     */
    protected Size2D arrangeFN(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[16]++;
        
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[17]++;
        Block b = (Block) blocks.get(0);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[18]++;
        Size2D s = b.arrange(g2, RectangleConstraint.NONE);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[19]++;
        double width = constraint.getWidth();
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[20]++;
        Rectangle2D bounds = new Rectangle2D.Double((width - s.width) / 2.0, 
                0.0, s.width, s.height);
        b.setBounds(bounds);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[21]++;
        return new Size2D((width - s.width) / 2.0, s.height);  
    }
    
    /**
     * Arranges the blocks in the container with a fixed with and a range
     * constraint on the height.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeFR(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[22]++;

        Size2D s = arrangeFN(container, g2, constraint);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[23]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((constraint.getHeightRange().contains(s.height)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[25]++;
            return s;
   
        }
        else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[26]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[24]++;
            RectangleConstraint c = constraint.toFixedHeight(
                    constraint.getHeightRange().constrain(s.getHeight()));
            return arrangeFF(container, g2, c);
        }
    }

    /**
     * Arranges the blocks in the container with the overall height and width
     * specified as fixed constraints.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
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
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size after the arrangement.
     */
    protected Size2D arrangeRR(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[25]++;

        // first arrange without constraints, and see if this fits within
        // the required ranges...
        Size2D s1 = arrangeNN(container, g2);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[26]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((constraint.getWidthRange().contains(s1.width)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[27]++;
            return s1;
  // TODO: we didn't check the height yet
        }
        else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[28]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[27]++;
            RectangleConstraint c = constraint.toFixedWidth(
                    constraint.getWidthRange().getUpperBound());
            return arrangeFR(container, g2, c);
        }
    }
    
    /**
     * Arranges the blocks in the container with a range constraint on the
     * width and a fixed height.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeRF(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[28]++;

        Size2D s = arrangeNF(container, g2, constraint);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[29]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((constraint.getWidthRange().contains(s.width)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[29]++;
            return s;
   
        }
        else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[30]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[30]++;
            RectangleConstraint c = constraint.toFixedWidth(
                    constraint.getWidthRange().constrain(s.getWidth()));
            return arrangeFF(container, g2, c);
        }
    }

    /**
     * Arranges the block with a range constraint on the width, and no 
     * constraint on the height.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The size following the arrangement.
     */
    protected Size2D arrangeRN(BlockContainer container, Graphics2D g2,
                               RectangleConstraint constraint) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[31]++;
        // first arrange without constraints, then see if the width fits
        // within the required range...if not, call arrangeFN() at max width
        Size2D s1 = arrangeNN(container, g2);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[32]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((constraint.getWidthRange().contains(s1.width)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[31]++;
            return s1;
   
        }
        else {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[32]++;
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[33]++;
            RectangleConstraint c = constraint.toFixedWidth(
                    constraint.getWidthRange().getUpperBound());
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
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[34]++;
        List blocks = container.getBlocks();
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[35]++;
        Block b = (Block) blocks.get(0);
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[36]++;
        Size2D s = b.arrange(g2, RectangleConstraint.NONE);
        b.setBounds(new Rectangle2D.Double(0.0, 0.0, s.width, s.height));
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[37]++;
        return new Size2D(s.width, s.height);  
    }
    
    /**
     * Arranges the blocks with no width constraint and a fixed height 
     * constraint.  This puts all blocks into a single row.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
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
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[38]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[33]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[34]++;}
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.statements[39]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj instanceof CenterArrangement) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h.branches[36]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.CenterArrangement.java";
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

  public CodeCoverCoverageCounter$b23oo8umhl2tg97ozzlwygx4tib9s0ik4h () {
    super("org.jfree.chart.block.CenterArrangement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
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
    log.startNamedSection("org.jfree.chart.block.CenterArrangement.java");
      for (int i = 1; i <= 39; i++) {
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

