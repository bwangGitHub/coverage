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
 * ShipNeedle.java
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
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A needle in the shape of a ship, for use with the 
 * {@link org.jfree.chart.plot.CompassPlot} class.
 */
public class ShipNeedle extends MeterNeedle 
                        implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 149554868169435612L;
  static {
    CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[1]++;
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
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[2]++;

        GeneralPath shape = new GeneralPath();
        shape.append(new Arc2D.Double(-9.0, -7.0, 10, 14, 0.0, 25.5, 
                Arc2D.OPEN), true);
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[3]++;
        shape.append(new Arc2D.Double(0.0, -7.0, 10, 14, 154.5, 25.5, 
                Arc2D.OPEN), true);
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[4]++;
        shape.closePath();
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[5]++;
        getTransform().setToTranslation(plotArea.getMinX(), plotArea.getMaxY());
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[6]++;
        getTransform().scale(plotArea.getWidth(), plotArea.getHeight() / 3);
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[7]++;
        shape.transform(getTransform());
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[8]++;
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((angle != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[1]++;
            /// we have rotation
            getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[10]++;
            shape.transform(getTransform());
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[11]++;

        } else {
  CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[2]++;}

        defaultDisplay(g2, shape);
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[12]++;
    }

    /**
     * Tests another object for equality with this object.
     * 
     * @param object  the object to test.
     * 
     * @return A boolean.
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[4]++;}
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((object == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[6]++;}
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((super.equals(object)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((object instanceof ShipNeedle) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h.branches[8]++;}
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

    /**
     * Returns a clone of this needle.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the <code>ShipNeedle</code> 
     *     cannot be cloned (in theory, this should not happen).
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }

}

class CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.ShipNeedle.java";
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

  public CodeCoverCoverageCounter$ozxvct225tt1e0fvpz7bp0h () {
    super("org.jfree.chart.needle.ShipNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
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
    log.startNamedSection("org.jfree.chart.needle.ShipNeedle.java");
      for (int i = 1; i <= 15; i++) {
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


