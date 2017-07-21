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
 * -------------------
 * BlockContainer.java
 * -------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 02-Feb-2005 : Added isEmpty() method (DG);
 * 04-Feb-2005 : Added equals(), clone() and implemented Serializable (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Jul-2006 : Perform translation directly on drawing area, not via 
 *               Graphics2D (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.ui.Size2D;
import org.jfree.util.PublicCloneable;

/**
 * A container for a collection of {@link Block} objects.  The container uses 
 * an {@link Arrangement} object to handle the position of each block.
 */
public class BlockContainer extends AbstractBlock 
                            implements Block, 
                                       Cloneable, PublicCloneable,
                                       Serializable {
  static {
    CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 8199508075695195293L;
  static {
    CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[1]++;
  }
    
    /** The blocks within the container. */
    private List blocks;
    
    /** The object responsible for laying out the blocks. */
    private Arrangement arrangement;
    
    /**
     * Creates a new instance with default settings.
     */
    public BlockContainer() {
        this(new BorderArrangement());
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[2]++;
    }
    
    /**
     * Creates a new instance with the specified arrangement.
     * 
     * @param arrangement  the arrangement manager (<code>null</code> not 
     *                     permitted).
     */
    public BlockContainer(Arrangement arrangement) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((arrangement == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[1]++;
            throw new IllegalArgumentException("Null 'arrangement' argument.");

        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[2]++;}
        this.arrangement = arrangement;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[4]++;
        this.blocks = new ArrayList();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[5]++;
    }    

    /**
     * Returns the arrangement (layout) manager for the container.
     * 
     * @return The arrangement manager (never <code>null</code>).
     */
    public Arrangement getArrangement() {
        return this.arrangement;    
    }
    
    /**
     * Sets the arrangement (layout) manager.
     * 
     * @param arrangement  the arrangement (<code>null</code> not permitted).
     */
    public void setArrangement(Arrangement arrangement) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((arrangement == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[3]++;
            throw new IllegalArgumentException("Null 'arrangement' argument.");

        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[4]++;}
        this.arrangement = arrangement;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[7]++;   
    }
    
    /**
     * Returns <code>true</code> if there are no blocks in the container, and
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     */
    public boolean isEmpty() {
        return this.blocks.isEmpty();   
    }
    
    /**
     * Returns an unmodifiable list of the {@link Block} objects managed by 
     * this arrangement.
     * 
     * @return A list of blocks.
     */
    public List getBlocks() {
        return Collections.unmodifiableList(this.blocks);
    }
    
    /**
     * Adds a block to the container.
     * 
     * @param block  the block (<code>null</code> permitted).
     */
    public void add(Block block) {
        add(block, null);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[8]++;
    }
    
    /**
     * Adds a block to the container.
     * 
     * @param block  the block (<code>null</code> permitted).
     * @param key  the key (<code>null</code> permitted).
     */
    public void add(Block block, Object key) {
        this.blocks.add(block);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[9]++;
        this.arrangement.add(block, key);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[10]++;
    }
    
    /**
     * Clears all the blocks from the container.
     */
    public void clear() {
        this.blocks.clear();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[11]++;
        this.arrangement.clear();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[12]++;
    }
    
    /**
     * Arranges the contents of the block, within the given constraints, and 
     * returns the block size.
     * 
     * @param g2  the graphics device.
     * @param constraint  the constraint (<code>null</code> not permitted).
     * 
     * @return The block size (in Java2D units, never <code>null</code>).
     */
    public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
        return this.arrangement.arrange(this, g2, constraint);
    }

    /**
     * Draws the container and all the blocks within it.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        draw(g2, area, null);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[13]++;
    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  passed on to blocks within the container 
     *                (<code>null</code> permitted).
     * 
     * @return An instance of {@link EntityBlockResult}, or <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[14]++;
        // check if we need to collect chart entities from the container
        EntityBlockParams ebp = null;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[15]++;
        StandardEntityCollection sec = null;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((params instanceof EntityBlockParams) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[5]++;
            ebp = (EntityBlockParams) params;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[17]++;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ebp.getGenerateEntities()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[7]++;
                sec = new StandardEntityCollection();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[19]++;
   
            } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[6]++;}
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[20]++;
        Rectangle2D contentArea = (Rectangle2D) area.clone();
        contentArea = trimMargin(contentArea);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[21]++;
        drawBorder(g2, contentArea);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[22]++;
        contentArea = trimBorder(contentArea);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[23]++;
        contentArea = trimPadding(contentArea);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[24]++;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[25]++;
        Iterator iterator = this.blocks.iterator();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[26]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.loops[1]--;
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.loops[2]--;
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.loops[3]++;
}
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[27]++;
            Block block = (Block) iterator.next();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[28]++;
            Rectangle2D bounds = block.getBounds();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[29]++;
            Rectangle2D drawArea = new Rectangle2D.Double(bounds.getX() 
                    + area.getX(), bounds.getY() + area.getY(), 
                    bounds.getWidth(), bounds.getHeight());
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[30]++;
            Object r = block.draw(g2, drawArea, params);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((sec != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[9]++;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((r instanceof EntityBlockResult) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[11]++;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[33]++;
                    EntityBlockResult ebr = (EntityBlockResult) r;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[34]++;
                    EntityCollection ec = ebr.getEntityCollection();
                    sec.addAll(ec);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[35]++;

                } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[12]++;}

            } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[10]++;}
        }
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[36]++;
        BlockResult result = null;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((sec != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[13]++;
            result = new BlockResult();
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[38]++;
            result.setEntityCollection(sec);
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[39]++;

        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[14]++;}
        return result;
    }

    /**
     * Tests this container for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[15]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[16]++;}
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((obj instanceof BlockContainer) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[17]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[18]++;}
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[19]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[20]++;}
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[43]++;
        BlockContainer that = (BlockContainer) obj;
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[44]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.arrangement.equals(that.arrangement)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[21]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[22]++;}
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[45]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.blocks.equals(that.blocks)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[23]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.branches[24]++;}
        return true;
    }
    
    /**
     * Returns a clone of the container.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt.statements[46]++;
        BlockContainer clone = (BlockContainer) super.clone();
        // TODO : complete this
        return clone;
    }
    
}

class CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt ());
  }
    public static long[] statements = new long[47];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "org.jfree.chart.block.BlockContainer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$13a2w6jj2d8ua561bl1v25k2hf5mxt () {
    super("org.jfree.chart.block.BlockContainer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 46; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.BlockContainer.java");
      for (int i = 1; i <= 46; i++) {
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
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

