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
 * ----------------
 * Arrangement.java
 * ----------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 11-Feb-2005 : Modified arrange() method to return Size2D (DG);
 * 22-Apr-2005 : Reordered arguments in arrange() method (DG);
 * 
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;

import org.jfree.ui.Size2D;

/**
 * An object that is responsible for arranging a collection of {@link Block}s
 * within a {@link BlockContainer}.
 */
public interface Arrangement {
    
    /**
     * Adds a block and a key which can be used to determine the position of 
     * the block in the arrangement.  This method is called by the container 
     * (you don't need to call this method directly) and gives the arrangement
     * an opportunity to record the details if they are required.
     * 
     * @param block  the block.
     * @param key  the key (<code>null</code> permitted).
     */
    public void add(Block block, Object key);
    
    /**
     * Arranges the blocks within the specified container, subject to the given
     * constraint.
     * 
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     * 
     * @return The container size after the arrangement.
     */
    public Size2D arrange(BlockContainer container, 
                          Graphics2D g2,
                          RectangleConstraint constraint);

    /**
     * Clears any cached layout information retained by the arrangement.
     */
    public void clear();
    
}

class CodeCoverCoverageCounter$3vhigtyc65ms5yuqn2vqta0kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3vhigtyc65ms5yuqn2vqta0kh ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3vhigtyc65ms5yuqn2vqta0kh () {
    super("org.jfree.chart.block.Arrangement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.block.Arrangement.java");
      for (int i = 1; i <= -1; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

