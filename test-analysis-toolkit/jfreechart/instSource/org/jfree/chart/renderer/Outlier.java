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
 * ------------
 * Outlier.java
 * ------------
 * (C) Copyright 2003-2007, by David Browning and Contributors.
 *
 * Original Author:  David Browning (for Australian Institute of Marine 
 *                   Science);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 05-Aug-2003 : Version 1, contributed by David Browning (DG);
 * 28-Aug-2003 : Minor tidy-up (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags from all over JFreeChart sources (DG);
 * 21-Nov-2007 : Implemented equals() to shut up FindBugs (DG);
 *
 */

package org.jfree.chart.renderer;

import java.awt.geom.Point2D;

/**
 * Represents one outlier in the box and whisker plot.
 * <P>
 * All the coordinates in this class are in Java2D space.
 */
public class Outlier implements Comparable {
  static {
    CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.ping();
  }


    /** 
     * The xy coordinates of the bounding box containing the outlier ellipse. 
     */
    private Point2D point;

    /** The radius of the ellipse */
    private double radius;

    /**
     * Constructs an outlier item consisting of a point and the radius of the 
     * outlier ellipse
     *
     * @param xCoord  the x coordinate of the point.
     * @param yCoord  the y coordinate of the point.
     * @param radius  the radius of the ellipse.
     */
    public Outlier(double xCoord, double yCoord, double radius) {
        this.point = new Point2D.Double(xCoord - radius, yCoord - radius);
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[1]++;
        this.radius = radius;
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[2]++;
    }

    /**
     * Returns the xy coordinates of the bounding box containing the outlier 
     * ellipse.
     *
     * @return The location of the outlier ellipse.
     */
    public Point2D getPoint() {
        return this.point;
    }

    /**
     * Sets the xy coordinates of the bounding box containing the outlier 
     * ellipse.
     *
     * @param point  the location.
     */
    public void setPoint(Point2D point) {
        this.point = point;
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[3]++;
    }

    /**
     * Returns the x coordinate of the bounding box containing the outlier 
     * ellipse.
     *
     * @return The x coordinate.
     */
    public double getX() {
        return getPoint().getX();
    }

    /**
     * Returns the y coordinate of the bounding box containing the outlier 
     * ellipse.
     *
     * @return The y coordinate.
     */
    public double getY() {
        return getPoint().getY();
    }

    /**
     * Returns the radius of the outlier ellipse.
     *
     * @return The radius.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Sets the radius of the outlier ellipse.
     *
     * @param radius  the new radius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[4]++;
    }

    /**
     * Compares this object with the specified object for order, based on
     * the outlier's point.
     *
     * @param   o the Object to be compared.
     * @return A negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the specified object.
     *
     */
    public int compareTo(Object o) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[5]++;
        Outlier outlier = (Outlier) o;
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[6]++;
        Point2D p1 = getPoint();
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[7]++;
        Point2D p2 = outlier.getPoint();
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p1.equals(p2)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[1]++;
            return 0;

        } 
        else {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[2]++;
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[9]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((p1.getX() < p2.getX()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((p1.getY() < p2.getY()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[3]++;
            return -1;

        } 
        else {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[4]++;
            return 1;
        }
} 
    }

    /**
     * Returns a true if outlier is overlapped and false if it is not.
     * Overlapping is determined by the respective bounding boxes plus
     * a small margin.
     *
     * @param other  the other outlier.
     * 
     * @return A <code>boolean</code> indicating whether or not an overlap has 
     *         occurred.
     */
    public boolean overlaps(Outlier other) {
        return ((other.getX() >= getX() - (this.radius * 1.1)) 
                && (other.getX() <= getX() + (this.radius * 1.1)) 
                && (other.getY() >= getY() - (this.radius * 1.1)) 
                && (other.getY() <= getY() + (this.radius * 1.1)));
    }

    /**
     * Tests this outlier for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[6]++;}
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof Outlier) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[8]++;}
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[12]++;
        Outlier that = (Outlier) obj;
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.point.equals(that.point)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[10]++;}
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.radius != that.radius) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2dtvotlfa236jq8o02p.branches[12]++;}
        return true;
    }
    
    /**
     * Returns a textual representation of the outlier.
     *
     * @return A <code>String</code> representing the outlier.
     */
    public String toString() {
        return "{" + getX() + "," + getY() + "}";
    }

}

class CodeCoverCoverageCounter$2dtvotlfa236jq8o02p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2dtvotlfa236jq8o02p ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.Outlier.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$2dtvotlfa236jq8o02p () {
    super("org.jfree.chart.renderer.Outlier.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.Outlier.java");
      for (int i = 1; i <= 14; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

