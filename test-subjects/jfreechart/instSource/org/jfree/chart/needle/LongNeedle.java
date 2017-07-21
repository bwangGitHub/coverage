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
 * ---------------
 * LongNeedle.java
 * ---------------
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
 * 16-Mar-2004 : Implemented Rotation;
 * 22-Nov-2007 : Implemented hashCode() (DG);
 * 
 */

package org.jfree.chart.needle;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A needle that is represented by a long line.
 */
public class LongNeedle extends MeterNeedle 
                        implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4319985779783688159L;
  static {
    CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[1]++;
  }
    
    /**
     * Default constructor.
     */
    public LongNeedle() {
        super();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[2]++;
        setRotateY(0.8);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[3]++;
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
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[4]++;

        GeneralPath shape1 = new GeneralPath();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[5]++;
        GeneralPath shape2 = new GeneralPath();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[6]++;
        GeneralPath shape3 = new GeneralPath();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[7]++;

        float minX = (float) plotArea.getMinX();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[8]++;
        float minY = (float) plotArea.getMinY();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[9]++;
        float maxX = (float) plotArea.getMaxX();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[10]++;
        float maxY = (float) plotArea.getMaxY();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[11]++;
        //float midX = (float) (minX + (plotArea.getWidth() * getRotateX()));
        //float midY = (float) (minY + (plotArea.getHeight() * getRotateY()));
        float midX = (float) (minX + (plotArea.getWidth() * 0.5));
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[12]++;
        float midY = (float) (minY + (plotArea.getHeight() * 0.8));
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[13]++;
        float y = maxY - (2 * (maxY - midY));
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((y < minY) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[1]++;
            y = minY;
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[15]++;

        } else {
  CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[2]++;}
        shape1.moveTo(minX, midY);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[16]++;
        shape1.lineTo(midX, minY);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[17]++;
        shape1.lineTo(midX, y);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[18]++;
        shape1.closePath();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[19]++;

        shape2.moveTo(maxX, midY);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[20]++;
        shape2.lineTo(midX, minY);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[21]++;
        shape2.lineTo(midX, y);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[22]++;
        shape2.closePath();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[23]++;

        shape3.moveTo(minX, midY);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[24]++;
        shape3.lineTo(midX, maxY);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[25]++;
        shape3.lineTo(maxX, midY);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[26]++;
        shape3.lineTo(midX, y);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[27]++;
        shape3.closePath();
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[28]++;
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[29]++;

        Shape s1 = shape1;
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[30]++;
        Shape s2 = shape2;
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[31]++;
        Shape s3 = shape3;
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[32]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((angle != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[3]++;
            /// we have rotation huston, please spin me
            getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[33]++;
            s1 = shape1.createTransformedShape(transform);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[34]++;
            s2 = shape2.createTransformedShape(transform);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[35]++;
            s3 = shape3.createTransformedShape(transform);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[36]++;

        } else {
  CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[4]++;}
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[37]++;
int CodeCoverConditionCoverageHelper_C3;


        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((getHighlightPaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[5]++;
            g2.setPaint(getHighlightPaint());
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[38]++;
            g2.fill(s3);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[39]++;

        } else {
  CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[6]++;}
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[40]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getFillPaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[7]++;
            g2.setPaint(getFillPaint());
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[41]++;
            g2.fill(s1);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[42]++;
            g2.fill(s2);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[43]++;

        } else {
  CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[8]++;}
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[44]++;
int CodeCoverConditionCoverageHelper_C5;


        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getOutlinePaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[9]++;
            g2.setStroke(getOutlineStroke());
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[45]++;
            g2.setPaint(getOutlinePaint());
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[46]++;
            g2.draw(s1);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[47]++;
            g2.draw(s2);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[48]++;
            g2.draw(s3);
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[49]++;

        } else {
  CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[10]++;}
    }

    /**
     * Tests another object for equality with this object.
     *
     * @param obj  the object to test (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[50]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[12]++;}
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.statements[51]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof LongNeedle) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[13]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1.branches[14]++;}
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns a clone of this needle.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the <code>LongNeedle</code> 
     *     cannot be cloned (in theory, this should not happen).
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }

}

class CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1 ());
  }
    public static long[] statements = new long[52];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.LongNeedle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$mwpj5d76ooro8ldynnkh5k1 () {
    super("org.jfree.chart.needle.LongNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 51; i++) {
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
    log.startNamedSection("org.jfree.chart.needle.LongNeedle.java");
      for (int i = 1; i <= 51; i++) {
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

