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
 * CompositeTitle.java
 * -------------------
 * (C) Copyright 2005, 2007, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Nov-2004 : Version 1 (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 04-Feb-2005 : Implemented MAXIMUM_WIDTH in calculateSize (DG);
 * 20-Apr-2005 : Added new draw() method (DG);
 * 03-May-2005 : Implemented equals() method (DG);
 *
 */

package org.jfree.chart.title;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.ui.Size2D;

/**
 * A title that contains multiple titles within a {@link BlockContainer}.
 */
public class CompositeTitle extends Title implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -6770854036232562290L;
  static {
    CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[1]++;
  }
    
    /** A container for the individual titles. */
    private BlockContainer container;
    
    /**
     * Creates a new composite title with a default border arrangement.
     */
    public CompositeTitle() {
        this(new BlockContainer(new BorderArrangement()));
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[2]++;   
    }
    
    /**
     * Creates a new title using the specified container. 
     * 
     * @param container  the container (<code>null</code> not permitted).
     */
    public CompositeTitle(BlockContainer container) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((container == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[1]++;
            throw new IllegalArgumentException("Null 'container' argument.");

        } else {
  CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[2]++;}
        this.container = container;
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[4]++;
    }
    
    /**
     * Returns the container holding the titles.
     * 
     * @return The title container (never <code>null</code>).
     */
    public BlockContainer getContainer() {
        return this.container;
    }
    
    /**
     * Sets the title container.
     * 
     * @param container  the container (<code>null</code> not permitted).
     */
    public void setTitleContainer(BlockContainer container) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((container == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[3]++;
            throw new IllegalArgumentException("Null 'container' argument.");

        } else {
  CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[4]++;}
        this.container = container;
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[6]++;    
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
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[7]++;
        RectangleConstraint contentConstraint = toContentConstraint(constraint);
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[8]++;
        Size2D contentSize = this.container.arrange(g2, contentConstraint);
        return new Size2D(
            calculateTotalWidth(contentSize.getWidth()), 
            calculateTotalHeight(contentSize.getHeight())
        );
    }
    
    /**
     * Draws the title on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the area allocated for the title.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        area = trimMargin(area);
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[9]++;
        drawBorder(g2, area);
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[10]++;
        area = trimBorder(area);
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[11]++;
        area = trimPadding(area);
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[12]++;
        this.container.draw(g2, area);
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[13]++;
    }
    
    /**
     * Draws the block within the specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  ignored (<code>null</code> permitted).
     * 
     * @return Always <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
        draw(g2, area);
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[14]++;
        return null;
    }
    
    /**
     * Tests this title for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[5]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[6]++;}
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof CompositeTitle) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[8]++;}
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[9]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[10]++;}
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[18]++;
        CompositeTitle that = (CompositeTitle) obj;
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.container.equals(that.container)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[11]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish.branches[12]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.title.CompositeTitle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$13vm90gnolbeds04kz35x7kc7xhish () {
    super("org.jfree.chart.title.CompositeTitle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.title.CompositeTitle.java");
      for (int i = 1; i <= 19; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

