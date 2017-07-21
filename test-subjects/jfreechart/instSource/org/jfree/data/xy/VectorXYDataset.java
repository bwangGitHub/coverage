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
 * VectorXYDataset.java
 * --------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Renamed getDeltaXValue() as getVectorXValue(), and likewise
 *               for getDeltaYValue(), and replaced getDeltaX()/getDeltaY()
 *               with getVector() (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.jfree.data.xy;



/**
 * An extension of the {@link XYDataset} interface that allows a vector to be
 * defined at a specific (x, y) location.
 * 
 * @since 1.0.6
 */
public interface VectorXYDataset extends XYDataset {

    /**
     * Returns the x-component of the vector for an item in a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The x-component of the vector.
     */
    public double getVectorXValue(int series, int item);

    /**
     * Returns the y-component of the vector for an item in a series.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The y-component of the vector.
     */
    public double getVectorYValue(int series, int item);
    
    /**
     * Returns the vector for an item in a series.  Depending on the particular
     * dataset implementation, this may involve creating a new {@link Vector}
     * instance --- if you are just interested in the x and y components,
     * use the {@link #getVectorXValue(int, int)} and 
     * {@link #getVectorYValue(int, int)} methods instead.
     * 
     * @param series  the series index.
     * @param item  the item index.
     * 
     * @return The vector (possibly <code>null</code>).
     */
    public Vector getVector(int series, int item);

}

class CodeCoverCoverageCounter$a3b9e15qlyz4avao4g0yo8mvmmagsip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$a3b9e15qlyz4avao4g0yo8mvmmagsip ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$a3b9e15qlyz4avao4g0yo8mvmmagsip () {
    super("org.jfree.data.xy.VectorXYDataset.java");
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
    log.startNamedSection("org.jfree.data.xy.VectorXYDataset.java");
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

