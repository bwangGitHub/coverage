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
 * CrosshairState.java
 * -------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 24-Jan-2002 : Version 1 (DG);
 * 05-Mar-2002 : Added Javadoc comments (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 19-Sep-2003 : Modified crosshair distance calculation (DG);
 * 04-Dec-2003 : Crosshair anchor point now stored outside chart since it is
 *               dependent on the display target (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo --> CrosshairState (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 13-Oct-2006 : Fixed initialisation of CrosshairState - see bug report 
 *               1565168 (DG);
 * 06-Feb-2007 : Added new fields and methods to fix bug 1086307 (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.geom.Point2D;

/**
 * Maintains state information about crosshairs on a plot between successive 
 * calls to the renderer's draw method.  This class is used internally by 
 * JFreeChart - it is not intended for external use.
 */
public class CrosshairState {
  static {
    CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.ping();
  }


    /** 
     * A flag that controls whether the distance is calculated in data space 
     * or Java2D space.
     */
    private boolean calculateDistanceInDataSpace = false;
  {
    CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[1]++;
  }

    /** The x-value (in data space) for the anchor point. */
    private double anchorX;

    /** The y-value (in data space) for the anchor point. */
    private double anchorY;
    
    /** The anchor point in Java2D space - if null, don't update crosshair. */
    private Point2D anchor;
    
    /** The x-value for the current crosshair point. */
    private double crosshairX;

    /** The y-value for the current crosshair point. */
    private double crosshairY;

    /**
     * The index of the domain axis that the crosshair x-value is measured
     * against.
     * 
     * @since 1.0.4
     */
    private int domainAxisIndex;
    
    /**
     * The index of the range axis that the crosshair y-value is measured
     * against.
     * 
     * @since 1.0.4
     */
    private int rangeAxisIndex;
    
    /** 
     * The smallest distance (so far) between the anchor point and a data 
     * point. 
     */
    private double distance;

    /**
     * Creates a new <code>CrosshairState</code> instance that calculates
     * distance in Java2D space.
     */
    public CrosshairState() {
        this(false);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[2]++;
    }

    /**
     * Creates a new <code>CrosshairState</code> instance.
     * 
     * @param calculateDistanceInDataSpace  a flag that controls whether the 
     *                                      distance is calculated in data 
     *                                      space or Java2D space.
     */
    public CrosshairState(boolean calculateDistanceInDataSpace) {
        this.calculateDistanceInDataSpace = calculateDistanceInDataSpace;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[3]++;
    }

    /**
     * Returns the distance between the anchor point and the current crosshair
     * point.
     * 
     * @return The distance.
     * 
     * @see #setCrosshairDistance(double)
     * @since 1.0.3
     */
    public double getCrosshairDistance() {
        return this.distance;
    }
    
    /**
     * Sets the distance between the anchor point and the current crosshair 
     * point.  As each data point is processed, its distance to the anchor 
     * point is compared with this value and, if it is closer, the data point 
     * becomes the new crosshair point.
     *
     * @param distance  the distance.
     * 
     * @see #getCrosshairDistance()
     */
    public void setCrosshairDistance(double distance) {
        this.distance = distance;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[4]++;
    }

    /**
     * Evaluates a data point and if it is the closest to the anchor point it
     * becomes the new crosshair point.
     * <P>
     * To understand this method, you need to know the context in which it will
     * be called.  An instance of this class is passed to an 
     * {@link org.jfree.chart.renderer.xy.XYItemRenderer} as
     * each data point is plotted.  As the point is plotted, it is passed to
     * this method to see if it should be the new crosshair point.
     *
     * @param x  x coordinate (measured against the domain axis).
     * @param y  y coordinate (measured against the range axis).
     * @param transX  x translated into Java2D space.
     * @param transY  y translated into Java2D space.
     * @param orientation  the plot orientation.
     * 
     * @deprecated Use {@link #updateCrosshairPoint(double, double, int, int, 
     *     double, double, PlotOrientation)}.  See bug report 1086307.
     */
    public void updateCrosshairPoint(double x, double y, 
                                     double transX, double transY, 
                                     PlotOrientation orientation) {
        updateCrosshairPoint(x, y, 0, 0, transX, transY, orientation);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[5]++;
    }
    
    /**
     * Evaluates a data point and if it is the closest to the anchor point it
     * becomes the new crosshair point.
     * <P>
     * To understand this method, you need to know the context in which it will
     * be called.  An instance of this class is passed to an 
     * {@link org.jfree.chart.renderer.xy.XYItemRenderer} as
     * each data point is plotted.  As the point is plotted, it is passed to
     * this method to see if it should be the new crosshair point.
     *
     * @param x  x coordinate (measured against the domain axis).
     * @param y  y coordinate (measured against the range axis).
     * @param domainAxisIndex  the index of the domain axis for this point.
     * @param rangeAxisIndex  the index of the range axis for this point.
     * @param transX  x translated into Java2D space.
     * @param transY  y translated into Java2D space.
     * @param orientation  the plot orientation.
     * 
     * @since 1.0.4
     */
    public void updateCrosshairPoint(double x, double y, int domainAxisIndex,
            int rangeAxisIndex, double transX, double transY, 
            PlotOrientation orientation) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.anchor != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[1]++;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[7]++;
            double d = 0.0;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.calculateDistanceInDataSpace) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[3]++;
                d = (x - this.anchorX) * (x - this.anchorX)
                  + (y - this.anchorY) * (y - this.anchorY);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[9]++;

            }
            else {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[4]++;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[10]++;
                double xx = this.anchor.getX();
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[11]++;
                double yy = this.anchor.getY();
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[5]++;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[13]++;
                    double temp = yy;
                    yy = xx;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[14]++;
                    xx = temp;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[15]++;

                } else {
  CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[6]++;}
                d = (transX - xx) * (transX - xx) 
                    + (transY - yy) * (transY - yy);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[16]++;            
            }
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;

            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((d < this.distance) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[7]++;
                this.crosshairX = x;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[18]++;
                this.crosshairY = y;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[19]++;
                this.domainAxisIndex = domainAxisIndex;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[20]++;
                this.rangeAxisIndex = rangeAxisIndex;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[21]++;
                this.distance = d;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[22]++;

            } else {
  CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[2]++;}

    }

    /**
     * Evaluates an x-value and if it is the closest to the anchor x-value it
     * becomes the new crosshair value.
     * <P>
     * Used in cases where only the x-axis is numerical.
     *
     * @param candidateX  x position of the candidate for the new crosshair 
     *                    point.
     *                    
     * @deprecated Use {@link #updateCrosshairX(double, int)}.  See bug report 
     *     1086307.
     */
    public void updateCrosshairX(double candidateX) {
        updateCrosshairX(candidateX, 0);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[23]++;
    }
    
    /**
     * Evaluates an x-value and if it is the closest to the anchor x-value it
     * becomes the new crosshair value.
     * <P>
     * Used in cases where only the x-axis is numerical.
     *
     * @param candidateX  x position of the candidate for the new crosshair 
     *                    point.
     * @param domainAxisIndex  the index of the domain axis for this x-value.
     * 
     * @since 1.0.4
     */
    public void updateCrosshairX(double candidateX, int domainAxisIndex) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[24]++;

        double d = Math.abs(candidateX - this.anchorX);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((d < this.distance) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[9]++;
            this.crosshairX = candidateX;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[26]++;
            this.domainAxisIndex = domainAxisIndex;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[27]++;
            this.distance = d;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[28]++;

        } else {
  CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[10]++;}

    }

    /**
     * Evaluates a y-value and if it is the closest to the anchor y-value it
     * becomes the new crosshair value.
     * <P>
     * Used in cases where only the y-axis is numerical.
     *
     * @param candidateY  y position of the candidate for the new crosshair 
     *                    point.
     *                    
     * @deprecated Use {@link #updateCrosshairY(double, int)}.  See bug report 
     *     1086307.
     */
    public void updateCrosshairY(double candidateY) {
        updateCrosshairY(candidateY, 0);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[29]++;
    }

    /**
     * Evaluates a y-value and if it is the closest to the anchor y-value it
     * becomes the new crosshair value.
     * <P>
     * Used in cases where only the y-axis is numerical.
     *
     * @param candidateY  y position of the candidate for the new crosshair 
     *                    point.
     * @param rangeAxisIndex  the index of the range axis for this y-value.
     * 
     * @since 1.0.4
     */
    public void updateCrosshairY(double candidateY, int rangeAxisIndex) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[30]++;
        double d = Math.abs(candidateY - this.anchorY);
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((d < this.distance) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[11]++;
            this.crosshairY = candidateY;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[32]++;
            this.rangeAxisIndex = rangeAxisIndex;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[33]++;
            this.distance = d;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[34]++;

        } else {
  CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.branches[12]++;}

    }

    /**
     * Returns the anchor point.
     * 
     * @return The anchor point.
     * 
     * @see #setAnchor(Point2D)
     * @since 1.0.3
     */
    public Point2D getAnchor() {
        return this.anchor;    
    }
    
    /** 
     * Sets the anchor point.  This is usually the mouse click point in a chart
     * panel, and the crosshair point will often be the data item that is 
     * closest to the anchor point.
     * <br><br>
     * Note that the x and y coordinates (in data space) are not updated by 
     * this method - the caller is responsible for ensuring that this happens
     * in sync.
     * 
     * @param anchor  the anchor point (<code>null</code> permitted).
     * 
     * @see #getAnchor()
     */
    public void setAnchor(Point2D anchor) {
        this.anchor = anchor;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[35]++;
    }
    
    /**
     * Returns the x-coordinate (in data space) for the anchor point.
     * 
     * @return The x-coordinate of the anchor point.
     * 
     * @since 1.0.3
     */
    public double getAnchorX() {
        return this.anchorX;    
    }
    
    /**
     * Sets the x-coordinate (in data space) for the anchor point.  Note that
     * this does NOT update the anchor itself - the caller is responsible for
     * ensuring this is done in sync.
     * 
     * @param x  the x-coordinate.
     * 
     * @since 1.0.3
     */
    public void setAnchorX(double x) {
        this.anchorX = x;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[36]++;
    }
    
    /**
     * Returns the y-coordinate (in data space) for the anchor point.
     * 
     * @return The y-coordinate of teh anchor point.
     * 
     * @since 1.0.3
     */
    public double getAnchorY() {
        return this.anchorY;    
    }
    
    /**
     * Sets the y-coordinate (in data space) for the anchor point.  Note that
     * this does NOT update the anchor itself - the caller is responsible for
     * ensuring this is done in sync.
     * 
     * @param y  the y-coordinate.
     * 
     * @since 1.0.3
     */
    public void setAnchorY(double y) {
        this.anchorY = y;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[37]++;
    }
    
    /**
     * Get the x-value for the crosshair point.
     *
     * @return The x position of the crosshair point.
     * 
     * @see #setCrosshairX(double)
     */
    public double getCrosshairX() {
        return this.crosshairX;
    }
    
    /**
     * Sets the x coordinate for the crosshair.  This is the coordinate in data
     * space measured against the domain axis.
     * 
     * @param x the coordinate.
     * 
     * @see #getCrosshairX()
     * @see #setCrosshairY(double)
     * @see #updateCrosshairPoint(double, double, double, double, 
     * PlotOrientation)
     */
    public void setCrosshairX(double x) {
        this.crosshairX = x;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[38]++;
    }

    /**
     * Get the y-value for the crosshair point.  This is the coordinate in data
     * space measured against the range axis.
     *
     * @return The y position of the crosshair point.
     * 
     * @see #setCrosshairY(double)
     */
    public double getCrosshairY() {
        return this.crosshairY;
    }

    /**
     * Sets the y coordinate for the crosshair.
     * 
     * @param y  the y coordinate.
     * 
     * @see #getCrosshairY()
     * @see #setCrosshairX(double)
     * @see #updateCrosshairPoint(double, double, double, double, 
     * PlotOrientation)
     */
    public void setCrosshairY(double y) {
        this.crosshairY = y;
CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh.statements[39]++;
    }
    
    /**
     * Returns the domain axis index for the crosshair x-value.
     * 
     * @return The domain axis index.
     * 
     * @since 1.0.4
     */
    public int getDomainAxisIndex() {
        return this.domainAxisIndex;
    }

    /**
     * Returns the range axis index for the crosshair y-value.
     * 
     * @return The range axis index.
     * 
     * @since 1.0.4
     */
    public int getRangeAxisIndex() {
        return this.rangeAxisIndex;
    }
}

class CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CrosshairState.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$13vv96meir1pu29ueobuswd55gj5gh () {
    super("org.jfree.chart.plot.CrosshairState.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.CrosshairState.java");
      for (int i = 1; i <= 39; i++) {
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

