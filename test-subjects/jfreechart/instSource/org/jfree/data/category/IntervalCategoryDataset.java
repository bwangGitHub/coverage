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
 * ----------------------------
 * IntervalCategoryDataset.java
 * ----------------------------
 * (C) Copyright 2002-2007, by Eduard Martinescu and Contributors.
 *
 * Original Author:  Eduard Martinescu;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Mar-2002 : Version 1 contributed by Eduard Martinescu.  The interface 
 *               name and method names have been renamed to be consistent with 
 *               existing interfaces (DG);
 * 06-Jun-2002 : Updated Javadoc comments (DG);
 * 24-Oct-2002 : Categories and series are now indexed by int or Comparable, 
 *               following changes made to the CategoryDataset interface (DG);
 *
 */

package org.jfree.data.category;


/**
 * A category dataset that defines a value range for each series/category 
 * combination.
 */
public interface IntervalCategoryDataset extends CategoryDataset {

    /**
     * Returns the start value for the interval for a given series and category.
     *
     * @param series  the series (zero-based index).
     * @param category  the category (zero-based index).
     *
     * @return The start value (possibly <code>null</code>).
     */
    public Number getStartValue(int series, int category);

    /**
     * Returns the start value for the interval for a given series and category.
     *
     * @param series  the series key.
     * @param category  the category key.
     *
     * @return The start value (possibly <code>null</code>).
     */
    public Number getStartValue(Comparable series, Comparable category);

    /**
     * Returns the end value for the interval for a given series and category.
     *
     * @param series  the series (zero-based index).
     * @param category  the category (zero-based index).
     *
     * @return The end value (possibly <code>null</code>).
     */
    public Number getEndValue(int series, int category);

    /**
     * Returns the end value for the interval for a given series and category.
     *
     * @param series  the series key.
     * @param category  the category key.
     *
     * @return The end value (possibly <code>null</code>).
     */
    public Number getEndValue(Comparable series, Comparable category);

}

class CodeCoverCoverageCounter$xe5elk4cr3zogc3bvvhjwivsrhvwxqkoz40noo2j2ox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$xe5elk4cr3zogc3bvvhjwivsrhvwxqkoz40noo2j2ox ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$xe5elk4cr3zogc3bvvhjwivsrhvwxqkoz40noo2j2ox () {
    super("org.jfree.data.category.IntervalCategoryDataset.java");
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
    log.startNamedSection("org.jfree.data.category.IntervalCategoryDataset.java");
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

