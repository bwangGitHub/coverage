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
 * ---------------------------
 * ClusteredXYBarRenderer.java
 * ---------------------------
 * (C) Copyright 2003-2007, by Paolo Cova and Contributors.
 *
 * Original Author:  Paolo Cova;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Christian W. Zuckschwerdt;
 *                   Matthias Rose;
 *
 * Changes
 * -------
 * 24-Jan-2003 : Version 1, contributed by Paolo Cova (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 03-Nov-2003 : In draw method added state parameter and y==null value 
 *               handling (MR);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 01-Oct-2004 : Fixed bug where 'drawBarOutline' flag is ignored (DG);
 * 16-May-2005 : Fixed to used outline stroke for bar outlines.  Removed some 
 *               redundant code with the result that the renderer now respects 
 *               the 'base' setting from the super-class. Added an equals() 
 *               method (DG);
 * 19-May-2005 : Added minimal item label implementation - needs improving (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 11-Dec-2006 : Added support for GradientPaint (DG);
 * 12-Jun-2007 : Added override to findDomainBounds() to handle cluster offset,
 *               fixed rendering to handle inverted axes, and simplified 
 *               entity generation code (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * An extension of {@link XYBarRenderer} that displays bars for different
 * series values at the same x next to each other. The assumption here is
 * that for each x (time or else) there is a y value for each series. If
 * this is not the case, there will be spaces between bars for a given x.
 * <P>
 * This renderer does not include code to calculate the crosshair point for the
 * plot.
 */
public class ClusteredXYBarRenderer extends XYBarRenderer 
        implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 5864462149177133147L;
  static {
    CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[1]++;
  }
    
    /** Determines whether bar center should be interval start. */
    private boolean centerBarAtStartValue;

    /**
     * Default constructor. Bar margin is set to 0.0.
     */
    public ClusteredXYBarRenderer() {
        this(0.0, false);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[2]++;
    }

    /**
     * Constructs a new XY clustered bar renderer.
     *
     * @param margin  the percentage amount to trim from the width of each bar.
     * @param centerBarAtStartValue  if true, bars will be centered on the 
     *         start of the time period.
     */
    public ClusteredXYBarRenderer(double margin, 
                                  boolean centerBarAtStartValue) {
        super(margin);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[3]++;
        this.centerBarAtStartValue = centerBarAtStartValue;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[4]++;
    }

    /**
     * Returns the x-value bounds for the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The bounds (possibly <code>null</code>).
     */
    public Range findDomainBounds(XYDataset dataset) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[2]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        // need to handle cluster centering as a special case
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.centerBarAtStartValue) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[3]++;
            return findDomainBoundsWithOffset((IntervalXYDataset) dataset);

        }
        else {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[4]++;
            return super.findDomainBounds(dataset);
        }
    }
    
    /**
     * Iterates over the items in an {@link IntervalXYDataset} to find
     * the range of x-values including the interval OFFSET so that it centers
     * the interval around the start value. 
     *  
     * @param dataset  the dataset (<code>null</code> not permitted).
     *   
     * @return The range (possibly <code>null</code>).
     */
    protected Range findDomainBoundsWithOffset(IntervalXYDataset dataset) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[5]++;
            throw new IllegalArgumentException("Null 'dataset' argument.");
   
        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[6]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[8]++;
        double minimum = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[9]++;
        double maximum = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[10]++;
        int seriesCount = dataset.getSeriesCount();
        double lvalue;
        double uvalue;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int series = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[1]--;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[2]--;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[3]++;
}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[12]++;
            int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
            for (int item = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((item < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[4]--;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[5]--;
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.loops[6]++;
}
                lvalue = dataset.getStartXValue(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[14]++;
                uvalue = dataset.getEndXValue(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[15]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[16]++;
                double offset = (uvalue - lvalue) / 2.0;
                lvalue = lvalue - offset;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[17]++;
                uvalue = uvalue - offset;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[18]++;
                minimum = Math.min(minimum, lvalue);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[19]++;
                maximum = Math.max(maximum, uvalue);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[20]++;
            }
        }
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((minimum > maximum) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[7]++;
            return null;

        }
        else {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[8]++;
            return new Range(minimum, maximum);
        }
    }

    /**
     * Draws the visual representation of a single data item. This method
     * is mostly copied from the superclass, the change is that in the
     * calculated space for a singe bar we draw bars for each series next to
     * each other. The width of each bar is the available width divided by
     * the number of series. Bars for each series are drawn in order left to
     * right.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param item  the item index.
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         XYItemRendererState state,
                         Rectangle2D dataArea,
                         PlotRenderingInfo info,
                         XYPlot plot, 
                         ValueAxis domainAxis, 
                         ValueAxis rangeAxis,
                         XYDataset dataset, int series, int item,
                         CrosshairState crosshairState,
                         int pass) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[22]++;

        IntervalXYDataset intervalDataset = (IntervalXYDataset) dataset;

        double y0;
        double y1;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getUseYInterval()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[9]++;
            y0 = intervalDataset.getStartYValue(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[24]++;
            y1 = intervalDataset.getEndYValue(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[25]++;

        }
        else {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[10]++;
            y0 = getBase();
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[26]++;
            y1 = intervalDataset.getYValue(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[27]++;
        }
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[11]++;
            return;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[12]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[29]++;

        double yy0 = rangeAxis.valueToJava2D(y0, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[30]++;
        double yy1 = rangeAxis.valueToJava2D(y1, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[31]++;

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[32]++;
        double x0 = intervalDataset.getStartXValue(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[33]++;
        double xx0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[34]++;
        
        double x1 = intervalDataset.getEndXValue(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[35]++;
        double xx1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[36]++;
        
        double intervalW = xx1 - xx0;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[37]++;  // this may be negative
        double baseX = xx0;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.centerBarAtStartValue) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[13]++;
            baseX = baseX - intervalW / 2.0;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[39]++;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[14]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[40]++;
        double m = getMargin();
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((m > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[15]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[42]++;
            double cut = intervalW * getMargin();
            intervalW = intervalW - cut;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[43]++;
            baseX = baseX + (cut / 2);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[44]++;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[16]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[45]++;
        
        double intervalH = Math.abs(yy0 - yy1);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[46]++;  // we don't need the sign

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[47]++;        

        int numSeries = dataset.getSeriesCount();
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[48]++;
        double seriesBarWidth = intervalW / numSeries;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[49]++;  // may be negative

        Rectangle2D bar = null;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[17]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[51]++;
            double barY0 = baseX + (seriesBarWidth * series);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[52]++;
            double barY1 = barY0 + seriesBarWidth;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[53]++;
            double rx = Math.min(yy0, yy1);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[54]++;
            double rw = intervalH;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[55]++;
            double ry = Math.min(barY0, barY1);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[56]++;
            double rh = Math.abs(barY1 - barY0);
            bar = new Rectangle2D.Double(rx, ry, rw, rh);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[57]++;

        }
        else {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[18]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[58]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[19]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[59]++;
            double barX0 = baseX + (seriesBarWidth * series);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[60]++;
            double barX1 = barX0 + seriesBarWidth;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[61]++;
            double rx = Math.min(barX0, barX1);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[62]++;
            double rw = Math.abs(barX1 - barX0);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[63]++;
            double ry = Math.min(yy0, yy1);;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[64]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[65]++;
            double rh = intervalH;
            bar = new Rectangle2D.Double(rx, ry, rw, rh);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[66]++;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[20]++;}
}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[67]++;
        Paint itemPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[68]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((getGradientPaintTransformer() 
                != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[21]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[69]++;
            GradientPaint gp = (GradientPaint) itemPaint;
            itemPaint = getGradientPaintTransformer().transform(gp, bar);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[70]++;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[22]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[71]++;

        g2.fill(bar);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[72]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[73]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Math.abs(seriesBarWidth) > 3) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[23]++;
            g2.setStroke(getItemOutlineStroke(series, item));
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[74]++;
            g2.setPaint(getItemOutlinePaint(series, item));
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[75]++;
            g2.draw(bar);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[76]++;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[24]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[77]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[25]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[78]++;
            XYItemLabelGenerator generator = getItemLabelGenerator(series, 
                    item);
            drawItemLabel(g2, dataset, series, item, plot, generator, bar, 
                    y1 < 0.0);
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[79]++;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[26]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[80]++;
int CodeCoverConditionCoverageHelper_C16;

        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[27]++;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[81]++;
            EntityCollection entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[82]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[29]++;
                addEntity(entities, bar, dataset, series, item, 
                        bar.getCenterX(), bar.getCenterY());
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[83]++;

            } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[30]++;}

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[28]++;}

    }

    /**
     * Tests this renderer for equality with an arbitrary object, returning
     * <code>true</code> if <code>obj</code> is a 
     * <code>ClusteredXYBarRenderer</code> with the same settings as this
     * renderer, and <code>false</code> otherwise.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[84]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[31]++;
            return true;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[32]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[85]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj instanceof ClusteredXYBarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[33]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[34]++;}
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[86]++;
        ClusteredXYBarRenderer that = (ClusteredXYBarRenderer) obj;
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.statements[87]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.centerBarAtStartValue != that.centerBarAtStartValue) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[35]++;
            return false;

        } else {
  CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1.branches[36]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a clone of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}

class CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1 ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.ClusteredXYBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,1,2,2,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$4b7yuzomx5ux6mk1vuq1152jrqx5ykylf9vi93w7s1 () {
    super("org.jfree.chart.renderer.xy.ClusteredXYBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.ClusteredXYBarRenderer.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

