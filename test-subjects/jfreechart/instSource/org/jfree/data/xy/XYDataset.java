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
 * --------------
 * XYDataset.java
 * --------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes (from 18-Sep-2001)
 * --------------------------
 * 18-Sep-2001 : Added standard header and fixed DOS encoding problem (DG);
 * 15-Oct-2001 : Moved to a new package (com.jrefinery.data.*) (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 17-Nov-2001 : Now extends SeriesDataset (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 29-Jul-2004 : Added getDomainOrder() method (DG);
 * 18-Aug-2004 : Moved from org.jfree.data --> org.jfree.data.xy (DG);
 * 
 */

package org.jfree.data.xy;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.SeriesDataset;

/**
 * An interface through which data in the form of (x, y) items can be accessed.
 */
public interface XYDataset extends SeriesDataset {

    /**
     * Returns the order of the domain (or X) values returned by the dataset.
     * 
     * @return The order (never <code>null</code>).
     */
    public DomainOrder getDomainOrder();
    
    /**
     * Returns the number of items in a series.
     * <br><br>
     * It is recommended that classes that implement this method should throw 
     * an <code>IllegalArgumentException</code> if the <code>series</code> 
     * argument is outside the specified range. 
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     *
     * @return The item count.
     */
    public int getItemCount(int series);

    /**
     * Returns the x-value for an item within a series.  The x-values may or 
     * may not be returned in ascending order, that is up to the class 
     * implementing the interface.
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *
     * @return The x-value (never <code>null</code>).
     */
    public Number getX(int series, int item);

    /**
     * Returns the x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     * 
     * @return The x-value.
     */
    public double getXValue(int series, int item);
    
    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *
     * @return The y-value (possibly <code>null</code>).
     */
    public Number getY(int series, int item);

    /**
     * Returns the y-value (as a double primitive) for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     * 
     * @return The y-value.
     */
    public double getYValue(int series, int item);
    
}

class CodeCoverCoverageCounter$3q1ty8fsx9ws3vdn8airmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3q1ty8fsx9ws3vdn8airmp ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3q1ty8fsx9ws3vdn8airmp () {
    super("org.jfree.data.xy.XYDataset.java");
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
    log.startNamedSection("org.jfree.data.xy.XYDataset.java");
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
