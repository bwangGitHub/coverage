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
 * -----------------
 * NumberAxis3D.java
 * -----------------
 * (C) Copyright 2001-2007, by Serge V. Grachov and Contributors.
 *
 * Original Author:  Serge V. Grachov;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Jonathan Nash;
 *                   Richard Atkinson;
 *                   Tin Luu;
 *
 * Changes
 * -------
 * 31-Oct-2001 : Version 1 contributed by Serge V. Grachov (DG);
 * 23-Nov-2001 : Overhauled auto tick unit code for all axes (DG);
 * 12-Dec-2001 : Minor change due to grid lines bug fix (DG);
 * 08-Jan-2002 : Added flag allowing the axis to be 'inverted'.  That is, run 
 *               from positive to negative.  Added default values to 
 *               constructors (DG);
 * 16-Jan-2002 : Added an optional crosshair, based on the implementation by 
 *               Jonathan Nash (DG);
 * 25-Feb-2002 : Updated constructors for new autoRangeStickyZero flag (DG);
 * 19-Apr-2002 : drawVerticalString() is now drawRotatedString() in 
 *               RefineryUtilities (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 25-Jul-2002 : Changed order of parameters in ValueAxis constructor (DG);
 * 06-Aug-2002 : Modified draw method to not draw axis label if label is empty 
 *               String (RA);
 * 05-Sep-2002 : Updated constructor for changes in the Axis class, and changed
 *               draw method to observe tickMarkPaint (DG);
 * 22-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 20-Jan-2003 : Removed unnecessary constructors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 13-May-2003 : Merged HorizontalNumberAxis3D and VerticalNumberAxis3D (DG);
 * 21-Aug-2003 : Updated draw() method signature (DG);
 * 07-Nov-2003 : Modified refreshTicks method signature (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-Jan-2006 : Fixed bug 1408904 (axis assumes CategoryPlot) (DG):
 *
 */

package org.jfree.chart.axis;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.List;

import org.jfree.chart.Effect3D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.ui.RectangleEdge;

/**
 * A standard linear value axis with a 3D effect corresponding to the
 * offset specified by some renderers.
 */
public class NumberAxis3D extends NumberAxis implements Serializable {
  static {
    CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1790205852569123512L;
  static {
    CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[1]++;
  }
    
    /**
     * Default constructor.
     */
    public NumberAxis3D() {
        this(null);
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[2]++;    
    }
    
    /**
     * Constructs a new axis.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    public NumberAxis3D(String label) {
        super(label);
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[3]++;
        setAxisLineVisible(false);
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[4]++;
    }

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param cursor  the cursor.
     * @param plotArea  the area for drawing the axes and data.
     * @param dataArea  the area for drawing the data (a subset of the 
     *                  plotArea).
     * @param edge  the axis location.
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     * 
     * @return The updated cursor value.
     */
    public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, 
            Rectangle2D dataArea, RectangleEdge edge, 
            PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;

        // if the axis is not visible, don't draw it...
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[1]++;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[6]++;
            AxisState state = new AxisState(cursor);
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[7]++;
            // even though the axis is not visible, we need ticks for the 
            // gridlines...
            List ticks = refreshTicks(g2, state, dataArea, edge); 
            state.setTicks(ticks);
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[8]++;
            return state;

        } else {
  CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[2]++;}
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[9]++;

        // calculate the adjusted data area taking into account the 3D effect...
        double xOffset = 0.0;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[10]++;
        double yOffset = 0.0;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[11]++;
        Plot plot = getPlot();
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((plot instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[3]++;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[13]++;
            CategoryPlot cp = (CategoryPlot) plot;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[14]++;
            CategoryItemRenderer r = cp.getRenderer();
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((r instanceof Effect3D) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[5]++;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[16]++;
                Effect3D e3D = (Effect3D) r;
                xOffset = e3D.getXOffset();
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[17]++;
                yOffset = e3D.getYOffset();
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[18]++;

            } else {
  CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[4]++;}
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[19]++;

        double adjustedX = dataArea.getMinX();
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[20]++;
        double adjustedY = dataArea.getMinY();
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[21]++;
        double adjustedW = dataArea.getWidth() - xOffset;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[22]++;
        double adjustedH = dataArea.getHeight() - yOffset;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[7]++;
            adjustedY += yOffset;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[24]++;

        }
        else {
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[8]++;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[25]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[9]++;
            adjustedX += xOffset;
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[26]++;

        } else {
  CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.branches[10]++;}
}
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[27]++;
        Rectangle2D adjustedDataArea = new Rectangle2D.Double(adjustedX, 
                adjustedY, adjustedW, adjustedH);
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[28]++;

        // draw the tick marks and labels...
        AxisState info = drawTickMarksAndLabels(g2, cursor, plotArea, 
                adjustedDataArea, edge);
       
        // draw the axis label...
        info = drawLabel(getLabel(), g2, plotArea, dataArea, edge, info);
CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l.statements[29]++;

        return info;
        
    }

}

class CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.NumberAxis3D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,2};
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

  public CodeCoverCoverageCounter$x13t961w5rrpncfs1u2xzq4e3l () {
    super("org.jfree.chart.axis.NumberAxis3D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
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
    log.startNamedSection("org.jfree.chart.axis.NumberAxis3D.java");
      for (int i = 1; i <= 29; i++) {
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

