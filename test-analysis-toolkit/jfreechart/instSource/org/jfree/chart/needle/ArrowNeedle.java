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
 * ----------------
 * ArrowNeedle.java
 * ----------------
 * (C) Copyright 2002-2007, by the Australian Antarctic Division and 
 *                          Contributors.
 *
 * Original Author:  Bryan Scott (for the Australian Antarctic Division);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 25-Sep-2002 : Version 1, contributed by Bryan Scott (DG);
 * 27-Mar-2003 : Implemented Serializable (DG);
 * 09-Sep-2003 : Added equals() method (DG);
 * 08-Jun-2005 : Implemented Cloneable (DG);
 * 22-Nov-2007 : Added hashCode() implementation (DG);
 * 
 */

package org.jfree.chart.needle;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.HashUtilities;

/**
 * A needle in the shape of an arrow.
 */
public class ArrowNeedle extends MeterNeedle 
                         implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5334056511213782357L;
  static {
    CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[1]++;
  }
    
    /** 
     * A flag controlling whether or not there is an arrow at the top of the 
     * needle. 
     */
    private boolean isArrowAtTop = true;
  {
    CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[2]++;
  }

    /**
     * Constructs a new arrow needle.
     *
     * @param isArrowAtTop  a flag that controls whether or not there is an 
     *                      arrow at the top of the needle.
     */
    public ArrowNeedle(boolean isArrowAtTop) {
        this.isArrowAtTop = isArrowAtTop;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[3]++;
    }

    /**
     * Draws the needle.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param rotate  the rotation point.
     * @param angle  the angle.
     */
    protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, 
                              Point2D rotate, double angle) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[4]++;

        Line2D shape = new Line2D.Float();
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[5]++;
        Shape d = null;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[6]++;

        float x = (float) (plotArea.getMinX() +  (plotArea.getWidth() / 2));
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[7]++;
        float minY = (float) plotArea.getMinY();
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[8]++;
        float maxY = (float) plotArea.getMaxY();
        shape.setLine(x, minY, x, maxY);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[9]++;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[10]++;

        GeneralPath shape1 = new GeneralPath();
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.isArrowAtTop) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[1]++;
            shape1.moveTo(x, minY);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[12]++;
            minY += 4 * getSize();
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[13]++;

        }
        else {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[2]++;
            shape1.moveTo(x, maxY);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[14]++;
            minY = maxY - 4 * getSize();
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[15]++;
        }
        shape1.lineTo(x + getSize(), minY);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[16]++;
        shape1.lineTo(x - getSize(), minY);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[17]++;
        shape1.closePath();
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[18]++;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((angle != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[3]++;
            getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[20]++;
            d = getTransform().createTransformedShape(shape);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[21]++;

        }
        else {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[4]++;
            d = shape;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[22]++;
        }
        defaultDisplay(g2, d);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[23]++;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((angle != 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[5]++;
            d = getTransform().createTransformedShape(shape1);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[25]++;

        }
        else {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[6]++;
            d = shape1;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[26]++;
        }
        defaultDisplay(g2, d);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[27]++;

    }

    /**
     * Tests another object for equality with this object.
     * 
     * @param obj  the object to test (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[8]++;}
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof ArrowNeedle) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[9]++;
            return false;
 
        } else {
  CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[10]++;}
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[12]++;}
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[31]++;
        ArrowNeedle that = (ArrowNeedle) obj;
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.isArrowAtTop != that.isArrowAtTop) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.branches[14]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[33]++;
        int result = super.hashCode();
        result = HashUtilities.hashCode(result, this.isArrowAtTop);
CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip.statements[34]++;
        return result; 
    }

    /**
     * Returns a clone of this needle.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the <code>ArrowNeedle</code> 
     *     cannot be cloned (in theory, this should not happen).
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }
    
}

class CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.ArrowNeedle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$3vhigwy55sm0q02yfmaxvngip () {
    super("org.jfree.chart.needle.ArrowNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.needle.ArrowNeedle.java");
      for (int i = 1; i <= 34; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

