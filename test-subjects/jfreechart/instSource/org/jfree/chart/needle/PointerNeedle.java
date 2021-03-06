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
 * PointerNeedle.java
 * ------------------
 * (C) Copyright 2002-2007, by the Australian Antarctic Division and 
 *                          Contributors.
 *
 * Original Author:  Bryan Scott (for the Australian Antarctic Division);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 25-Sep-2002 : Version 1, contributed by Bryan Scott (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 09-Sep-2003 : Added equals() method (DG);
 * 08-Jun-2005 : Implemented Cloneable (DG);
 * 22-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.needle;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A needle in the shape of a pointer, for use with the
 * {@link org.jfree.chart.plot.CompassPlot} class.
 */
public class PointerNeedle extends MeterNeedle 
                           implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4744677345334729606L;
  static {
    CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[1]++;
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
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[2]++;

        GeneralPath shape1 = new GeneralPath();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[3]++;
        GeneralPath shape2 = new GeneralPath();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[4]++;
        float minX = (float) plotArea.getMinX();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[5]++;
        float minY = (float) plotArea.getMinY();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[6]++;
        float maxX = (float) plotArea.getMaxX();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[7]++;
        float maxY = (float) plotArea.getMaxY();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[8]++;
        float midX = (float) (minX + (plotArea.getWidth() / 2));
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[9]++;
        float midY = (float) (minY + (plotArea.getHeight() / 2));

        shape1.moveTo(minX, midY);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[10]++;
        shape1.lineTo(midX, minY);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[11]++;
        shape1.lineTo(maxX, midY);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[12]++;
        shape1.closePath();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[13]++;

        shape2.moveTo(minX, midY);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[14]++;
        shape2.lineTo(midX, maxY);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[15]++;
        shape2.lineTo(maxX, midY);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[16]++;
        shape2.closePath();
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[17]++;
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[18]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((rotate != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((angle != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[1]++;
            /// we have rotation huston, please spin me
            getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[19]++;
            shape1.transform(getTransform());
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[20]++;
            shape2.transform(getTransform());
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[21]++;

        } else {
  CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[2]++;}
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getFillPaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[3]++;
            g2.setPaint(getFillPaint());
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[23]++;
            g2.fill(shape1);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[24]++;

        } else {
  CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[4]++;}
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((getHighlightPaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[5]++;
            g2.setPaint(getHighlightPaint());
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[26]++;
            g2.fill(shape2);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[27]++;

        } else {
  CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[6]++;}
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getOutlinePaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[7]++;
            g2.setStroke(getOutlineStroke());
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[29]++;
            g2.setPaint(getOutlinePaint());
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[30]++;
            g2.draw(shape1);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[31]++;
            g2.draw(shape2);
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[32]++;

        } else {
  CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[8]++;}
    }

    /**
     * Tests another object for equality with this object.
     * 
     * @param obj  the object to test (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[10]++;}
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof PointerNeedle) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[11]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[12]++;}
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5.branches[14]++;}
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
     * @throws CloneNotSupportedException if the <code>PointerNeedle</code> 
     *     cannot be cloned (in theory, this should not happen).
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();   
    }

}

class CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5 ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.needle.PointerNeedle.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$6osvq0n61mkcamch9ab7rtjq65b5 () {
    super("org.jfree.chart.needle.PointerNeedle.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
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
    log.startNamedSection("org.jfree.chart.needle.PointerNeedle.java");
      for (int i = 1; i <= 35; i++) {
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

