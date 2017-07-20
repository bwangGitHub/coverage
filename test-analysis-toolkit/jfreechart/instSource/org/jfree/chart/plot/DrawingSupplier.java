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
 * DrawingSupplier.java
 * --------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 16-Jan-2003 : Version 1 (DG);
 * 17-Jan-2003 : Renamed PaintSupplier --> DrawingSupplier (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Jun-2007 : Added getNextOutlinePaint() method.
 * 
 */

package org.jfree.chart.plot;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * A supplier of <code>Paint</code>, <code>Stroke</code> and <code>Shape</code>
 * objects for use by plots and renderers.  By providing a central place for
 * obtaining these items, we can ensure that duplication is avoided.
 * <p>
 * To support the cloning of charts, classes that implement this interface 
 * should also implement <code>PublicCloneable</code>.
 */
public interface DrawingSupplier {

    /**
     * Returns the next paint in a sequence maintained by the supplier.
     *
     * @return The paint.
     */
    public Paint getNextPaint();

    /**
     * Returns the next outline paint in a sequence maintained by the supplier.
     *
     * @return The paint.
     */
    public Paint getNextOutlinePaint();

    /**
     * Returns the next fill paint in a sequence maintained by the supplier.
     *
     * @return The paint.
     * 
     * @since 1.0.6
     */
    public Paint getNextFillPaint();

    /**
     * Returns the next <code>Stroke</code> object in a sequence maintained by
     * the supplier.
     *
     * @return The stroke.
     */
    public Stroke getNextStroke();

    /**
     * Returns the next <code>Stroke</code> object in a sequence maintained by
     * the supplier.
     *
     * @return The stroke.
     */
    public Stroke getNextOutlineStroke();

    /**
     * Returns the next <code>Shape</code> object in a sequence maintained by
     * the supplier.
     *
     * @return The shape.
     */
    public Shape getNextShape();

}

class CodeCoverCoverageCounter$7ztz1brli5omlnk1v3qkv0hvj1e9y01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7ztz1brli5omlnk1v3qkv0hvj1e9y01 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$7ztz1brli5omlnk1v3qkv0hvj1e9y01 () {
    super("org.jfree.chart.plot.DrawingSupplier.java");
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
    log.startNamedSection("org.jfree.chart.plot.DrawingSupplier.java");
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

