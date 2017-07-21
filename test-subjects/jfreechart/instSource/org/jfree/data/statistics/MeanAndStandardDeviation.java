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
 * -----------------------------
 * MeanAndStandardDeviation.java
 * -----------------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 05-Feb-2002 : Version 1 (DG);
 * 05-Feb-2005 : Added equals() method and implemented Serializable (DG);
 * 02-Oct-2007 : Added getMeanValue() and getStandardDeviationValue() methods
 *               for convenience, and toString() method for debugging (DG);
 *
 */

package org.jfree.data.statistics;

import java.io.Serializable;

import org.jfree.util.ObjectUtilities;

/**
 * A simple data structure that holds a mean value and a standard deviation 
 * value.  This is used in the 
 * {@link org.jfree.data.statistics.DefaultStatisticalCategoryDataset} class.
 */
public class MeanAndStandardDeviation implements Serializable {
  static {
    CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7413468697315721515L;
  static {
    CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[1]++;
  }
    
    /** The mean. */
    private Number mean;

    /** The standard deviation. */
    private Number standardDeviation;

    /**
     * Creates a new mean and standard deviation record.
     * 
     * @param mean  the mean.
     * @param standardDeviation  the standard deviation.
     */
    public MeanAndStandardDeviation(double mean, double standardDeviation) {
        this(new Double(mean), new Double(standardDeviation));
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[2]++;   
    }
    
    /**
     * Creates a new mean and standard deviation record.
     *
     * @param mean  the mean (<code>null</code> permitted).
     * @param standardDeviation  the standard deviation (<code>null</code>
     *                           permitted.
     */
    public MeanAndStandardDeviation(Number mean, Number standardDeviation) {
        this.mean = mean;
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[3]++;
        this.standardDeviation = standardDeviation;
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[4]++;
    }

    /**
     * Returns the mean.
     *
     * @return The mean.
     */
    public Number getMean() {
        return this.mean;
    }
    
    /**
     * Returns the mean as a double primitive.  If the underlying mean is
     * <code>null</code>, this method will return <code>Double.NaN</code>.
     * 
     * @return The mean.
     * 
     * @see #getMean()
     * 
     * @since 1.0.7
     */
    public double getMeanValue() {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[5]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.mean != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[1]++;
            result = this.mean.doubleValue();
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[7]++;

        } else {
  CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[2]++;}
        return result;
    }

    /**
     * Returns the standard deviation.
     *
     * @return The standard deviation.
     */
    public Number getStandardDeviation() {
        return this.standardDeviation;
    }

    /**
     * Returns the standard deviation as a double primitive.  If the underlying
     * standard deviation is <code>null</code>, this method will return 
     * <code>Double.NaN</code>.
     * 
     * @return The standard deviation.
     * 
     * @since 1.0.7
     */
    public double getStandardDeviationValue() {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[8]++;
        double result = Double.NaN;
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.standardDeviation != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[3]++;
            result = this.standardDeviation.doubleValue();
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[10]++;

        } else {
  CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[4]++;}
        return result;
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[5]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[6]++;}
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof MeanAndStandardDeviation) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[8]++;}
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[13]++;
        MeanAndStandardDeviation that = (MeanAndStandardDeviation) obj;
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.mean, that.mean)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[9]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[10]++;}
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.standardDeviation, that.standardDeviation)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)
        ) {
CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[11]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld.branches[12]++;}
        return true;
    }
    
    /**
     * Returns a string representing this instance.
     * 
     * @return A string.
     * 
     * @since 1.0.7
     */
    public String toString() {
        return "[" + this.mean + ", " + this.standardDeviation + "]";
    }

}

class CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.data.statistics.MeanAndStandardDeviation.java";
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

  public CodeCoverCoverageCounter$6ya5ubc1s6gnurfkinffxp53rvy6jtvot7i9asasa52ld () {
    super("org.jfree.data.statistics.MeanAndStandardDeviation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
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
    log.startNamedSection("org.jfree.data.statistics.MeanAndStandardDeviation.java");
      for (int i = 1; i <= 15; i++) {
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
