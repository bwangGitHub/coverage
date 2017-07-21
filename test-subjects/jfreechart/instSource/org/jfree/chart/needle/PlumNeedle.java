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
 * PlumNeedle.java
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
 * 08-Jun-2005 : Implemented Cloneable (DG);
 * 22-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.needle;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A needle for use with the {@link org.jfree.chart.plot.CompassPlot} class.
 */
public class PlumNeedle extends MeterNeedle 
                        implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3082660488660600718L;
  static {
    CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[1]++;
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
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[2]++;

        Arc2D shape = new Arc2D.Double(Arc2D.PIE);
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[3]++;
        double radius = plotArea.getHeight();
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[4]++;
        double halfX = plotArea.getWidth() / 2;
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[5]++;
        double diameter = 2 * radius;

        shape.setFrame(plotArea.getMinX() + halfX - radius ,
                       plotArea.getMinY() - radius,
                       diameter, diameter);
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[6]++;
        radius = Math.toDegrees(Math.asin(halfX / radius));
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[7]++;
        shape.setAngleStart(270 - radius);
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[8]++;
        shape.setAngleExtent(2 * radius);
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[9]++;
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[10]++;

        Area s = new Area(shape);
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((angle != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[1]++;
            /// we have rotation houston, please spin me
            getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[12]++;
            s.transform(getTransform());
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[13]++;

        } else {
  CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[2]++;}

        defaultDisplay(g2, s);
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[14]++;
    }
    
    /**
     * Tests another object for equality with this object.
     * 
     * @param obj  the object to test (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[4]++;}
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof PlumNeedle) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[5]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[6]++;}
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l.branches[8]++;}
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
     * @throws CloneNotSupportedException if the <code>PlumNeedle</code> 
     *     cannot be cloned (in theory, this should not happen).
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }

}

class CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.PlumNeedle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1};
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

  public CodeCoverCoverageCounter$o3qqa22k27colxw1weuwu3l () {
    super("org.jfree.chart.needle.PlumNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("org.jfree.chart.needle.PlumNeedle.java");
      for (int i = 1; i <= 17; i++) {
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

