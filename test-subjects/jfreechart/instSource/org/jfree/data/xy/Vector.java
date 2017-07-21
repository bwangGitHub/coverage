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
 * -----------
 * Vector.java
 * -----------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Added getLength() and getAngle() methods, thanks to 
 *               matinh (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.jfree.data.xy;

import java.io.Serializable;

/**
 * A vector.
 * 
 * @since 1.0.6
 */
public class Vector implements Serializable {
  static {
    CodeCoverCoverageCounter$d4g7fss18sfoojqsh.ping();
  }

    
    /** The vector x. */
    private double x;
    
    /** The vector y. */
    private double y;
    
    /** 
     * Creates a new instance of <code>Vector</code>.
     *
     * @param x  the x-component.
     * @param y  the y-component.  
     */
    public Vector(double x, double y) {
        this.x = x;
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[1]++;
        this.y = y;
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[2]++;
    }
    
    /**
     * Returns the x-value.
     * 
     * @return The x-value.
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Returns the y-value.
     * 
     * @return The y-value.
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Returns the length of the vector.
     * 
     * @return The vector length.
     */
    public double getLength() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
 
    /**
     * Returns the angle of the vector.
     * 
     * @return The angle of the vector.
     */
    public double getAngle() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Tests this vector for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> not permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[2]++;}
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof Vector) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[4]++;}
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[5]++;
        Vector that = (Vector) obj;
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.x != that.x) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[6]++;}
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.y != that.y) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$d4g7fss18sfoojqsh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$d4g7fss18sfoojqsh.branches[8]++;}
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[8]++;
        int result = 193;
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[9]++;
        long temp = Double.doubleToLongBits(this.x);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[10]++;
        temp = Double.doubleToLongBits(this.y);
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[11]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$d4g7fss18sfoojqsh.statements[12]++;
        return result;
    }
    
}

class CodeCoverCoverageCounter$d4g7fss18sfoojqsh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$d4g7fss18sfoojqsh ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.Vector.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$d4g7fss18sfoojqsh () {
    super("org.jfree.data.xy.Vector.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.Vector.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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

