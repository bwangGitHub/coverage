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
 * ---------------------------
 * BoxAndWhiskerXYDataset.java
 * ---------------------------
 * (C) Copyright 2003, 2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 12-Aug-2003 : Added new methods: getMaxNonOutlierValue
 *                                  getMaxNonFaroutValue
 *                                  getOutlierCoefficient
 *                                  setOutlierCoefficient
 *                                  getFaroutCoefficient
 *                                  setFaroutCoefficient
 *                                  getInterquartileRange (DB)
 * 27-Aug-2003 : Renamed BoxAndWhiskerDataset --> BoxAndWhiskerXYDataset, and 
 *               cut down methods (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 
 */

package org.jfree.data.statistics;

import java.util.List;

import org.jfree.data.xy.XYDataset;

/**
 * An interface that defines data in the form of (x, max, min, average, median) 
 * tuples.
 * <P>
 * Example: JFreeChart uses this interface to obtain data for AIMS 
 * max-min-average-median plots.
 */
public interface BoxAndWhiskerXYDataset extends XYDataset {

    /**
     * Returns the mean for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The mean for the specified series and item.
     */
    public Number getMeanValue(int series, int item);

    /**
     * Returns the median-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The median-value for the specified series and item.
     */
    public Number getMedianValue(int series, int item);

    /**
     * Returns the Q1 median-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The Q1 median-value for the specified series and item.
     */
    public Number getQ1Value(int series, int item);

    /**
     * Returns the Q3 median-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The Q3 median-value for the specified series and item.
     */
    public Number getQ3Value(int series, int item);

    /**
     * Returns the min-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The min-value for the specified series and item.
     */
    public Number getMinRegularValue(int series, int item);

    /**
     * Returns the max-value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The max-value for the specified series and item.
     */
    public Number getMaxRegularValue(int series, int item);

    /**
     * Returns the minimum value which is not a farout.
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return A <code>Number</code> representing the maximum non-farout value.
     */
    public Number getMinOutlier(int series, int item);

    /**
     * Returns the maximum value which is not a farout, ie Q3 + (interquartile 
     * range * farout coefficient).
     * 
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return A <code>Number</code> representing the maximum non-farout value.
     */
    public Number getMaxOutlier(int series, int item);
    
    /**
     * Returns an array of outliers for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return The array of outliers for the specified series and item.
     */
    public List getOutliers(int series, int item);

    /**
     * Returns the value used as the outlier coefficient. The outlier 
     * coefficient gives an indication of the degree of certainty in an 
     * unskewed distribution.  Increasing the coefficient increases the number 
     * of values included.  Currently only used to ensure farout coefficient 
     * is greater than the outlier coefficient
     *
     * @return A <code>double</code> representing the value used to calculate 
     *         outliers
     */
    public double getOutlierCoefficient();

    /**
     * Returns the value used as the farout coefficient. The farout coefficient
     * allows the calculation of which values will be off the graph.
     *
     * @return A <code>double</code> representing the value used to calculate 
     *         farouts
     */
    public double getFaroutCoefficient();

}

class CodeCoverCoverageCounter$48y2bug6qaguqx91y62wqq9ksm97cppmxkuinqm4ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$48y2bug6qaguqx91y62wqq9ksm97cppmxkuinqm4ip ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$48y2bug6qaguqx91y62wqq9ksm97cppmxkuinqm4ip () {
    super("org.jfree.data.statistics.BoxAndWhiskerXYDataset.java");
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
    log.startNamedSection("org.jfree.data.statistics.BoxAndWhiskerXYDataset.java");
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

