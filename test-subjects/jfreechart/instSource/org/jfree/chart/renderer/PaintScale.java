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
 * ---------------
 * PaintScale.java
 * ---------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Jul-2006 : Version 1 (DG);
 * 31-Jan-2007 : Added getLowerBound() and getUpperBound() methods (DG);
 * 
 */

package org.jfree.chart.renderer;

import java.awt.Paint;

import org.jfree.chart.renderer.xy.XYBlockRenderer;

/**
 * A source for <code>Paint</code> instances, used by the 
 * {@link XYBlockRenderer}.  
 * <br><br>
 * NOTE: Classes that implement this interface should also implement 
 * <code>PublicCloneable</code> and <code>Serializable</code>, so 
 * that any renderer (or other object instance) that references an instance of 
 * this interface can still be cloned or serialized.
 * 
 * @since 1.0.4
 */
public interface PaintScale {
    
    /**
     * Returns the lower bound for the scale.
     * 
     * @return The lower bound.
     * 
     * @see #getUpperBound()
     */
    public double getLowerBound();
    
    /**
     * Returns the upper bound for the scale.
     * 
     * @return The upper bound.
     * 
     * @see #getLowerBound()
     */
    public double getUpperBound();
    
    /**
     * Returns a <code>Paint</code> instance for the specified value.
     * 
     * @param value  the value.
     * 
     * @return A <code>Paint</code> instance (never <code>null</code>).
     */
    public Paint getPaint(double value);
    
}

class CodeCoverCoverageCounter$o39yvdsy1m8kwx30sy5f5gh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$o39yvdsy1m8kwx30sy5f5gh ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$o39yvdsy1m8kwx30sy5f5gh () {
    super("org.jfree.chart.renderer.PaintScale.java");
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
    log.startNamedSection("org.jfree.chart.renderer.PaintScale.java");
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

