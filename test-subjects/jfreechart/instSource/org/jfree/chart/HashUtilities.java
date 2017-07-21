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
 * ------------------
 * HashUtilities.java
 * ------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited;
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Oct-2006 : Version 1 (DG);
 * 06-Mar-2007 : Fix for hashCodeForDoubleArray() method (DG);
 * 13-Nov-2007 : Added new utility methods (DG);
 * 22-Nov-2007 : Added hashCode() method for 'int' (DG);
 *
 */

package org.jfree.chart;

import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Stroke;

/**
 * Some utility methods for calculating hash codes.  
 * 
 * @since 1.0.3
 */
public class HashUtilities {
  static {
    CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.ping();
  }

    
    /**
     * Returns a hash code for a <code>Paint</code> instance.  If 
     * <code>p</code> is <code>null</code>, this method returns zero.
     * 
     * @param p  the paint (<code>null</code> permitted).
     * 
     * @return The hash code.
     */
    public static int hashCodeForPaint(Paint p) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.branches[1]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.branches[2]++;}
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[2]++;
        int result = 0;
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        // handle GradientPaint as a special case
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((p instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.branches[3]++;
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[4]++;
            GradientPaint gp = (GradientPaint) p;
            result = 193;
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[5]++;
            result = 37 * result + gp.getColor1().hashCode();
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[6]++;
            result = 37 * result + gp.getPoint1().hashCode();
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[7]++;
            result = 37 * result + gp.getColor2().hashCode();
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[8]++;
            result = 37 * result + gp.getPoint2().hashCode();
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[9]++;

        }
        else {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.branches[4]++;
            // we assume that all other Paint instances implement equals() and
            // hashCode()...of course that might not be true, but what can we
            // do about it?
            result = p.hashCode();
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[10]++;
        }
        return result;
    }
    
    /**
     * Returns a hash code for a <code>double[]</code> instance.  If the array
     * is <code>null</code>, this method returns zero.
     * 
     * @param a  the array (<code>null</code> permitted).
     * 
     * @return The hash code.
     */
    public static int hashCodeForDoubleArray(double[] a) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((a == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.branches[5]++; 
            return 0;

        } else {
  CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.branches[6]++;}
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[12]++;
        int result = 193;
        long temp;
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < a.length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.loops[1]--;
  CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.loops[2]--;
  CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.loops[3]++;
}
            temp = Double.doubleToLongBits(a[i]);
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[14]++;
            result = 29 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[15]++;
        }
        return result;
    }
    
    /**
     * Returns a hash value based on a seed value and the value of a boolean
     * primitive.
     * 
     * @param pre  the seed value.
     * @param b  the boolean value.
     * 
     * @return A hash value.
     * 
     * @since 1.0.7
     */
    public static int hashCode(int pre, boolean b) {
        return 37 * pre + (b ? 0 : 1);
    }
    
    /**
     * Returns a hash value based on a seed value and the value of an int
     * primitive.
     * 
     * @param pre  the seed value.
     * @param i  the int value.
     * 
     * @return A hash value.
     * 
     * @since 1.0.8
     */
    public static int hashCode(int pre, int i) {
        return 37 * pre + i;
    }

    /**
     * Returns a hash value based on a seed value and the value of a double
     * primitive.
     * 
     * @param pre  the seed value.
     * @param d  the double value.
     * 
     * @return A hash value.
     * 
     * @since 1.0.7
     */
    public static int hashCode(int pre, double d) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[16]++;
        long l = Double.doubleToLongBits(d);
        return 37 * pre + (int) (l ^ (l >>> 32));
    }
    
    /**
     * Returns a hash value based on a seed value and a paint instance.
     * 
     * @param pre  the seed value.
     * @param p  the paint (<code>null</code> permitted).
     * 
     * @return A hash value.
     * 
     * @since 1.0.7
     */
    public static int hashCode(int pre, Paint p) {
        return 37 * pre + hashCodeForPaint(p);
    }

    /**
     * Returns a hash value based on a seed value and a stroke instance.
     * 
     * @param pre  the seed value.
     * @param s  the stroke (<code>null</code> permitted).
     * 
     * @return A hash value.
     * 
     * @since 1.0.7
     */
    public static int hashCode(int pre, Stroke s) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[17]++;
        int h = (s != null ? s.hashCode() : 0);
        return 37 * pre + h;
    }

    /**
     * Returns a hash value based on a seed value and a string instance.
     * 
     * @param pre  the seed value.
     * @param s  the string (<code>null</code> permitted).
     * 
     * @return A hash value.
     * 
     * @since 1.0.7
     */
    public static int hashCode(int pre, String s) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[18]++;
        int h = (s != null ? s.hashCode() : 0);
        return 37 * pre + h;
    }

    /**
     * Returns a hash value based on a seed value and a <code>Comparable</code>
     * instance.
     * 
     * @param pre  the seed value.
     * @param c  the comparable (<code>null</code> permitted).
     * 
     * @return A hash value.
     * 
     * @since 1.0.7
     */
    public static int hashCode(int pre, Comparable c) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[19]++;
        int h = (c != null ? c.hashCode() : 0);
        return 37 * pre + h;
    }

    /**
     * Returns a hash value based on a seed value and an <code>Object</code>
     * instance.
     * 
     * @param pre  the seed value.
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A hash value.
     * 
     * @since 1.0.8
     */
    public static int hashCode(int pre, Object obj) {
CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t.statements[20]++;
        int h = (obj != null ? obj.hashCode() : 0);
        return 37 * pre + h;
    }

}

class CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.HashUtilities.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$60ot0hb9c7wg5ehp0wguqakxxe1t () {
    super("org.jfree.chart.HashUtilities.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.HashUtilities.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

