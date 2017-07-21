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
 * --------------
 * PinNeedle.java
 * --------------
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
 * 22-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.needle;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A needle that is drawn as a pin shape.
 */
public class PinNeedle extends MeterNeedle 
                       implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3787089953079863373L;
  static {
    CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[1]++;
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

        Area shape;
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[2]++;
        GeneralPath pointer = new GeneralPath();
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[3]++;

        int minY = (int) (plotArea.getMinY());
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[4]++;
        //int maxX = (int) (plotArea.getMaxX());
        int maxY = (int) (plotArea.getMaxY());
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[5]++;
        int midX = (int) (plotArea.getMinX() + (plotArea.getWidth() / 2));
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[6]++;
        //int midY = (int) (plotArea.getMinY() + (plotArea.getHeight() / 2));
        int lenX = (int) (plotArea.getWidth() / 10);
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((lenX < 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[1]++;
            lenX = 2;
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[8]++;

        } else {
  CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[2]++;}

        pointer.moveTo(midX - lenX, maxY - lenX);
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[9]++;
        pointer.lineTo(midX + lenX, maxY - lenX);
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[10]++;
        pointer.lineTo(midX, minY + lenX);
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[11]++;
        pointer.closePath();
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[12]++;

        lenX = 4 * lenX;
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[13]++;
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[14]++;
        Ellipse2D circle = new Ellipse2D.Double(midX - lenX / 2, 
                plotArea.getMaxY() - lenX, lenX, lenX);

        shape = new Area(circle);
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[15]++;
        shape.add(new Area(pointer));
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[16]++;
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((angle != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[3]++;
            /// we have rotation
            getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[18]++;
            shape.transform(getTransform());
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[19]++;

        } else {
  CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[4]++;}

        defaultDisplay(g2, shape);
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[20]++;

    }

    /**
     * Tests another object for equality with this object.
     * 
     * @param obj  the object to test (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[6]++;}
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof PinNeedle) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[8]++;}
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1.branches[10]++;}
        return true;
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
     * @throws CloneNotSupportedException if the <code>PinNeedle</code> 
     *     cannot be cloned (in theory, this should not happen).
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }

}

class CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1 ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.PinNeedle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1};
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

  public CodeCoverCoverageCounter$3e0at9mqb3xvhwbe6uifs1 () {
    super("org.jfree.chart.needle.PinNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
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
    log.startNamedSection("org.jfree.chart.needle.PinNeedle.java");
      for (int i = 1; i <= 23; i++) {
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

