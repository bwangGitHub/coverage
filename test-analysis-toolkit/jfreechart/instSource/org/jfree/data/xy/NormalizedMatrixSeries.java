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
 * NormalizedMatrixSeries.java
 * ---------------------------
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh;;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 10-Jul-2003 : Version 1 contributed by Barak Naveh (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */
 
package org.jfree.data.xy;


/**
 * Represents a dense normalized matrix M[i,j] where each Mij item of the
 * matrix has a value (default is 0). When a matrix item is observed using
 * <code>getItem</code> method, it is normalized, that is, divided by the
 * total sum of all items. It can be also be scaled by setting a scale factor.
 */
public class NormalizedMatrixSeries extends MatrixSeries {
  static {
    CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.ping();
  }

    
    /** The default scale factor. */
    public static final double DEFAULT_SCALE_FACTOR = 1.0;
  static {
    CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[1]++;
  }

    /**
     * A factor that multiplies each item in this series when observed using 
     * getItem method.
     */
    private double m_scaleFactor = DEFAULT_SCALE_FACTOR;
  {
    CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[2]++;
  }

    /** The sum of all items in this matrix */
    private double m_totalSum;

    /**
     * Constructor for NormalizedMatrixSeries.
     *
     * @param name  the series name.
     * @param rows  the number of rows.
     * @param columns  the number of columns.
     */
    public NormalizedMatrixSeries(String name, int rows, int columns) {
        super(name, rows, columns);
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[3]++;

        /*
         * we assum super is always initialized to all-zero matrix, so the
         * total sum should be 0 upon initialization. However, we set it to
         * Double.MIN_VALUE to get the same effect and yet avoid division by 0
         * upon initialization.
         */
        this.m_totalSum = Double.MIN_VALUE;
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[4]++;
    }

    /**
     * Returns an item.
     * 
     * @param itemIndex  the index.
     * 
     * @return The value.
     * 
     * @see org.jfree.data.xy.MatrixSeries#getItem(int)
     */
    public Number getItem(int itemIndex) {
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[5]++;
        int i = getItemRow(itemIndex);
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[6]++;
        int j = getItemColumn(itemIndex);
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[7]++;

        double mij = get(i, j) * this.m_scaleFactor;
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[8]++;
        Number n = new Double(mij / this.m_totalSum);

        return n;
    }

    /**
     * Sets the factor that multiplies each item in this series when observed
     * using getItem mehtod.
     *
     * @param factor new factor to set.
     *
     * @see #DEFAULT_SCALE_FACTOR
     */
    public void setScaleFactor(double factor) {
        this.m_scaleFactor = factor;
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[9]++;
        // FIXME: this should generate a series change event
    }


    /**
     * Returns the factor that multiplies each item in this series when
     * observed using getItem mehtod.
     *
     * @return The factor
     */
    public double getScaleFactor() {
        return this.m_scaleFactor;
    }


    /**
     * Updates the value of the specified item in this matrix series.
     *
     * @param i the row of the item.
     * @param j the column of the item.
     * @param mij the new value for the item.
     * 
     * @see #get(int, int)
     */
    public void update(int i, int j, double mij) {
        this.m_totalSum -= get(i, j);
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[10]++;
        this.m_totalSum += mij;
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[11]++;

        super.update(i, j, mij);
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[12]++;
    }

    /**
     * @see org.jfree.data.xy.MatrixSeries#zeroAll()
     */
    public void zeroAll() {
        this.m_totalSum = 0;
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[13]++;
        super.zeroAll();
CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl.statements[14]++;
    }
}

class CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$50kljsk4iy3xjgp3o39yixjlwdtupragkknfiti9nl () {
    super("org.jfree.data.xy.NormalizedMatrixSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
    log.startNamedSection("org.jfree.data.xy.NormalizedMatrixSeries.java");
      for (int i = 1; i <= 14; i++) {
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

