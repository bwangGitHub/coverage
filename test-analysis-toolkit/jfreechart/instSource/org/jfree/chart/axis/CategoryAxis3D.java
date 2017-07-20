/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2006, by Object Refinery Limited and Contributors.
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
 * CategoryAxis3D.java
 * -------------------
 * (C) Copyright 2003-2006, by Klaus Rheinwald and Contributors.
 *
 * Original Author:  Klaus Rheinwald;
 * Contributor(s):   Tin Luu,
 *                   David Gilbert (for Object Refinery Limited);
 *                   Adriaan Joubert;
 *
 * Changes
 * -------
 * 19-Feb-2003 : File creation;
 * 21-Mar-2003 : Added to JFreeChart CVS, see bug id 685501 for code 
 *               contribution from KR (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 13-May-2003 : Renamed HorizontalCategoryAxis3D --> CategoryAxis3D, and 
 *               modified to take into account the plot orientation (DG);
 * 14-Aug-2003 : Implemented Cloneable (DG);
 * 21-Aug-2003 : Fixed draw() method bugs (DG);
 * 22-Mar-2004 : Added workaround for bug 920959 (null pointer exception with 
 *               no renderer) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 18-Aug-2006 : Fix for bug drawing category labels, thanks to Adriaan
 *               Joubert (1277726) (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.Effect3D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.ui.RectangleEdge;

/**
 * An axis that displays categories and has a 3D effect.
 * Used for bar charts and line charts.
 */
public class CategoryAxis3D extends CategoryAxis 
                            implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 4114732251353700972L;
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[1]++;
  }
    
    /**
     * Creates a new axis.
     */
    public CategoryAxis3D() {
        this(null);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[2]++;
    }
    
    /**
     * Creates a new axis using default attribute values.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    public CategoryAxis3D(String label) {
        super(label);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[3]++;
    }

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the axis should be drawn 
     *                  (<code>null</code> not permitted).
     * @param dataArea  the area within which the plot is being drawn 
     *                  (<code>null</code> not permitted).
     * @param edge  the location of the axis (<code>null</code> not permitted).
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Graphics2D g2, 
                          double cursor,
                          Rectangle2D plotArea, 
                          Rectangle2D dataArea, 
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

        // if the axis is not visible, don't draw it...
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[1]++;
            return new AxisState(cursor);

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[2]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[5]++;

        // calculate the adjusted data area taking into account the 3D effect...
        // this assumes that there is a 3D renderer, all this 3D effect is a 
        // bit of an ugly hack...
        CategoryPlot plot = (CategoryPlot) getPlot();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[6]++;

        Rectangle2D adjustedDataArea = new Rectangle2D.Double();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((plot.getRenderer() instanceof Effect3D) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[3]++;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[8]++;
            Effect3D e3D = (Effect3D) plot.getRenderer();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[9]++;
            double adjustedX = dataArea.getMinX();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[10]++;
            double adjustedY = dataArea.getMinY();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[11]++;
            double adjustedW = dataArea.getWidth() - e3D.getXOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[12]++;
            double adjustedH = dataArea.getHeight() - e3D.getYOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;

            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[5]++;
                adjustedY += e3D.getYOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[14]++;

            }
            else {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[6]++;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[15]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[7]++;
                adjustedX += e3D.getXOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[16]++;

            } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[8]++;}
}
            adjustedDataArea.setRect(adjustedX, adjustedY, adjustedW, 
                    adjustedH);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[17]++;

        }
        else {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[4]++;
            adjustedDataArea.setRect(dataArea);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[18]++;   
        }
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[19]++;

        // draw the category labels and axis label
        AxisState state = new AxisState(cursor);
        state = drawCategoryLabels(g2, plotArea, adjustedDataArea, edge, 
                state, plotState);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[20]++;
        state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[21]++;

        return state;
        
    }
    
    /**
     * Returns the Java 2D coordinate for a category.
     * 
     * @param anchor  the anchor point.
     * @param category  the category index.
     * @param categoryCount  the category count.
     * @param area  the data area.
     * @param edge  the location of the axis.
     * 
     * @return The coordinate.
     */
    public double getCategoryJava2DCoordinate(CategoryAnchor anchor, 
                                              int category, 
                                              int categoryCount, 
                                              Rectangle2D area,
                                              RectangleEdge edge) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[22]++;
    
        double result = 0.0;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[23]++;
        Rectangle2D adjustedArea = area;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[24]++;
        CategoryPlot plot = (CategoryPlot) getPlot();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[25]++;
        CategoryItemRenderer renderer = plot.getRenderer();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((renderer instanceof Effect3D) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[9]++;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[27]++;
            Effect3D e3D = (Effect3D) renderer;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[28]++;
            double adjustedX = area.getMinX();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[29]++;
            double adjustedY = area.getMinY();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[30]++;
            double adjustedW = area.getWidth() - e3D.getXOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[31]++;
            double adjustedH = area.getHeight() - e3D.getYOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;

            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[11]++;
                adjustedY += e3D.getYOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[33]++;

            }
            else {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[12]++;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[34]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[13]++;
                adjustedX += e3D.getXOffset();
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[35]++;

            } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[14]++;}
}
            adjustedArea = new Rectangle2D.Double(adjustedX, adjustedY, 
                    adjustedW, adjustedH);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[36]++;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[10]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((anchor == CategoryAnchor.START) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[15]++;
            result = getCategoryStart(category, categoryCount, adjustedArea, 
                    edge);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[38]++;

        }
        else {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[16]++;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[39]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((anchor == CategoryAnchor.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[17]++;
            result = getCategoryMiddle(category, categoryCount, adjustedArea, 
                    edge);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[40]++;

        }
        else {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[18]++;
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[41]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((anchor == CategoryAnchor.END) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[19]++;
            result = getCategoryEnd(category, categoryCount, adjustedArea, 
                    edge);
CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.statements[42]++;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl.branches[20]++;}
}
}
        return result;
                                                      
    }
                                              
    /**
     * Returns a clone of the axis.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException If the axis is not cloneable for 
     *         some reason.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}

class CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CategoryAxis3D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,2,1,2,2,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$13ugf3fy51kl4aq5ikfmaelb1chbvl () {
    super("org.jfree.chart.axis.CategoryAxis3D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.CategoryAxis3D.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

