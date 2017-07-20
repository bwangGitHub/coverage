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
 * ---------------------------------
 * NormalDistributionFunction2D.java
 * ---------------------------------
 * (C)opyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 25-May-2004 : Version 1 (DG);
 * 21-Nov-2005 : Added getters for the mean and standard deviation (DG);
 * 
 */

package org.jfree.data.function;

/**
 * A normal distribution function.
 */
public class NormalDistributionFunction2D implements Function2D {
  static {
    CodeCoverCoverageCounter$dwgib94z34oapnpslio7joy384fs7x8yiw2f6242zwem6k9s95t.ping();
  }


    /** The mean. */
    private double mean;

    /** The standard deviation. */
    private double std;

    /**
     * Constructs a new normal distribution function.
     *
     * @param mean  the mean.
     * @param std  the standard deviation.
     */
    public NormalDistributionFunction2D(double mean, double std) {
        this.mean = mean;
CodeCoverCoverageCounter$dwgib94z34oapnpslio7joy384fs7x8yiw2f6242zwem6k9s95t.statements[1]++;
        this.std = std;
CodeCoverCoverageCounter$dwgib94z34oapnpslio7joy384fs7x8yiw2f6242zwem6k9s95t.statements[2]++;
    }
    
    /**
     * Returns the mean for the function.
     * 
     * @return The mean.
     */
    public double getMean() {
        return this.mean;
    }
    
    /**
     * Returns the standard deviation for the function.
     * 
     * @return The standard deviation.
     */
    public double getStandardDeviation() {
        return this.std;
    }

    /**
     * Returns the function value.
     *
     * @param x  the x-value.
     *
     * @return The value.
     */
    public double getValue(double x) {
        return Math.exp(-1.0 * (x - this.mean) * (x - this.mean) 
                / (2 * this.std * this.std)) / Math.sqrt(2 * Math.PI 
                * this.std * this.std);
    }

}

class CodeCoverCoverageCounter$dwgib94z34oapnpslio7joy384fs7x8yiw2f6242zwem6k9s95t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dwgib94z34oapnpslio7joy384fs7x8yiw2f6242zwem6k9s95t ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$dwgib94z34oapnpslio7joy384fs7x8yiw2f6242zwem6k9s95t () {
    super("org.jfree.data.function.NormalDistributionFunction2D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
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
    log.startNamedSection("org.jfree.data.function.NormalDistributionFunction2D.java");
      for (int i = 1; i <= 2; i++) {
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

