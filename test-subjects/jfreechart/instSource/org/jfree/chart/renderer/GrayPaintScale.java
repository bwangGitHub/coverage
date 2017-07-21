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
 * -------------------
 * GrayPaintScale.java
 * -------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 05-Jul-2006 : Version 1 (DG);
 * 31-Jan-2007 : Renamed min and max to lowerBound and upperBound (DG);
 * 26-Sep-2007 : Fixed bug 1767315, problem in getPaint() method (DG);
 * 
 */

package org.jfree.chart.renderer;

import java.awt.Color;
import java.awt.Paint;
import java.io.Serializable;

import org.jfree.util.PublicCloneable;

/**
 * A paint scale that returns shades of gray.
 * 
 * @since 1.0.4
 */
public class GrayPaintScale 
        implements PaintScale, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.ping();
  }


    /** The lower bound. */
    private double lowerBound;
    
    /** The upper bound. */
    private double upperBound;
    
    /**
     * Creates a new <code>GrayPaintScale</code> instance with default values.
     */
    public GrayPaintScale() {
        this(0.0, 1.0);
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[1]++;
    }
    
    /**
     * Creates a new paint scale for values in the specified range.
     * 
     * @param lowerBound  the lower bound.
     * @param upperBound  the upper bound.
     * 
     * @throws IllegalArgumentException if <code>lowerBound</code> is not
     *       less than <code>upperBound</code>.
     */
    public GrayPaintScale(double lowerBound, double upperBound) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((lowerBound >= upperBound) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[1]++;
            throw new IllegalArgumentException(
                    "Requires lowerBound < upperBound.");

        } else {
  CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[2]++;}
        this.lowerBound = lowerBound;
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[3]++;
        this.upperBound = upperBound;
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[4]++;
    }
    
    /**
     * Returns the lower bound.
     * 
     * @return The lower bound.
     * 
     * @see #getUpperBound()
     */
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Returns the upper bound.
     * 
     * @return The upper bound.
     * 
     * @see #getLowerBound()
     */
    public double getUpperBound() {
        return this.upperBound;
    }

    /**
     * Returns a paint for the specified value.
     * 
     * @param value  the value (must be within the range specified by the
     *         lower and upper bounds for the scale).
     * 
     * @return A paint for the specified value.
     */
    public Paint getPaint(double value) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[5]++;
        double v = Math.max(value, this.lowerBound);
        v = Math.min(v, this.upperBound);
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[6]++;
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[7]++;
        int g = (int) ((v - this.lowerBound) / (this.upperBound 
                - this.lowerBound) * 255.0);
        return new Color(g, g, g);
    }
    
    /**
     * Tests this <code>GrayPaintScale</code> instance for equality with an
     * arbitrary object.  This method returns <code>true</code> if and only
     * if:
     * <ul>
     * <li><code>obj</code> is not <code>null</code>;</li>
     * <li><code>obj</code> is an instance of <code>GrayPaintScale</code>;</li>
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[4]++;}
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof GrayPaintScale) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[6]++;}
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[10]++;
        GrayPaintScale that = (GrayPaintScale) obj;
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.lowerBound != that.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[8]++;}
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.upperBound != that.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9.branches[10]++;}
        return true;    
    }
    
    /**
     * Returns a clone of this <code>GrayPaintScale</code> instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning this
     *     instance.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}

class CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9 ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.GrayPaintScale.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$1690olye1xkjvjn0th5i04m2ghb3a9 () {
    super("org.jfree.chart.renderer.GrayPaintScale.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.GrayPaintScale.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

