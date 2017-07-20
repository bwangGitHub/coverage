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
 * VectorDataItem.java
 * -------------------
 * (C) Copyright 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 30-Jan-2007 : Version 1 (DG);
 * 24-May-2007 : Added getVector(), renamed getDeltaX() --> getVectorX(), 
 *               and likewise for getDeltaY() (DG);
 * 25-May-2007 : Moved from experimental to the main source tree (DG);
 *
 */

package org.jfree.data.xy;

import org.jfree.data.ComparableObjectItem;

/**
 * A data item representing data in the form (x, y, deltaX, deltaY), intended
 * for use by the {@link VectorSeries} class.
 * 
 * @since 1.0.6
 */
public class VectorDataItem extends ComparableObjectItem {
  static {
    CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.ping();
  }


    /** 
     * Creates a new instance of <code>YIntervalItem</code>.
     *
     * @param x  the x-value.
     * @param y  the y-value.
     * @param deltaX  the vector x.
     * @param deltaY  the vector y.
     */
    public VectorDataItem(double x, double y, double deltaX, double deltaY) {
        super(new XYCoordinate(x, y), new Vector(deltaX, deltaY));
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.statements[1]++;
    }
    
    /**
     * Returns the x-value.
     *
     * @return The x-value (never <code>null</code>).
     */
    public double getXValue() {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.statements[2]++;
        XYCoordinate xy = (XYCoordinate) getComparable();
        return xy.getX();
    }
    
    /**
     * Returns the y-value.
     *
     * @return The y-value.
     */
    public double getYValue() {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.statements[3]++;
        XYCoordinate xy = (XYCoordinate) getComparable();
        return xy.getY();
    }
    
    /**
     * Returns the vector.
     * 
     * @return The vector (possibly <code>null</code>).
     */
    public Vector getVector() {
        return (Vector) getObject();
    }
    
    /**
     * Returns the x-component for the vector.
     * 
     * @return The x-component.
     */
    public double getVectorX() {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.statements[4]++;
        Vector vi = (Vector) getObject();
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((vi != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.branches[1]++;
            return vi.getX();

        }
        else {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.branches[2]++;
            return Double.NaN;
        }
    }
    
    /**
     * Returns the y-component for the vector.
     * 
     * @return The y-component.
     */
    public double getVectorY() {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.statements[6]++;
        Vector vi = (Vector) getObject();
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((vi != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.branches[3]++;
            return vi.getY();

        }
        else {
CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5.branches[4]++;
            return Double.NaN;
        }
    }
    
}

class CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5 ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.data.xy.VectorDataItem.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$1f39raadaym1wu6otqwfasvsm89lr5 () {
    super("org.jfree.data.xy.VectorDataItem.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.xy.VectorDataItem.java");
      for (int i = 1; i <= 7; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

