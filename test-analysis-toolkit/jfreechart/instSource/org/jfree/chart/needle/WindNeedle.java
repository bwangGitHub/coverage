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
 * WindNeedle.java
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
 * 09-Sep-2003 : Added equals() method (DG);
 * 22-Nov-2007 : Implemented hashCode() (DG)
 *
 */

package org.jfree.chart.needle;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A needle that indicates wind direction, for use with the
 * {@link org.jfree.chart.plot.CompassPlot} class.
 */
public class WindNeedle extends ArrowNeedle 
                                implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -2861061368907167834L;
  static {
    CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[1]++;
  }
    
    /**
     * Default constructor.
     */
    public WindNeedle() {
        super(false);
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[2]++;  // isArrowAtTop
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

        super.drawNeedle(g2, plotArea, rotate, angle);
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[3]++;
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((plotArea != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[1]++;
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[5]++;

            int spacing = getSize() * 3;
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[6]++;
            Rectangle2D newArea = new Rectangle2D.Double();
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[7]++;

            Point2D newRotate = rotate;
            newArea.setRect(plotArea.getMinX() - spacing, plotArea.getMinY(),
                    plotArea.getWidth(), plotArea.getHeight());
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[8]++;
            super.drawNeedle(g2, newArea, newRotate, angle);
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[9]++;

            newArea.setRect(plotArea.getMinX() + spacing, 
                    plotArea.getMinY(), plotArea.getWidth(), 
                    plotArea.getHeight());
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[10]++;
            super.drawNeedle(g2, newArea, newRotate, angle);
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[11]++;


        } else {
  CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[2]++;}
    }

    /**
     * Tests another object for equality with this object.
     * 
     * @param object  the object to test.
     * 
     * @return A boolean.
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[4]++;}
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((object == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[6]++;}
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((super.equals(object)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((object instanceof WindNeedle) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x.branches[8]++;}
        return false;
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
        return super.hashCode();
    }

}

class CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.WindNeedle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,2};
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

  public CodeCoverCoverageCounter$q754j8rtbwyq4dm75lr6z4x () {
    super("org.jfree.chart.needle.WindNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
    log.startNamedSection("org.jfree.chart.needle.WindNeedle.java");
      for (int i = 1; i <= 14; i++) {
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


