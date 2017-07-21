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
 * StackedXYAreaRenderer2.java
 * ---------------------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited), based on 
 *                   the StackedXYAreaRenderer class by Richard Atkinson;
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 30-Apr-2004 : Version 1 (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 10-Sep-2004 : Removed getRangeType() method (DG);
 * 06-Jan-2004 : Renamed getRangeExtent() --> findRangeBounds (DG);
 * 28-Mar-2005 : Use getXValue() and getYValue() from dataset (DG);
 * 03-Oct-2005 : Add entity generation to drawItem() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 22-Aug-2006 : Handle null and empty datasets correctly in the 
 *               findRangeBounds() method (DG);
 * 22-Sep-2006 : Added a flag to allow rounding of x-coordinates (after 
 *               translation to Java2D space) in order to avoid the striping
 *               that can result from anti-aliasing (thanks to Doug 
 *               Clayton) (DG);
 * 30-Nov-2006 : Added accessor methods for the roundXCoordinates flag (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A stacked area renderer for the {@link XYPlot} class.
 */
public class StackedXYAreaRenderer2 extends XYAreaRenderer2 
                                    implements Cloneable, 
                                               PublicCloneable,
                                               Serializable {
  static {
    CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7752676509764539182L;
  static {
    CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[1]++;
  }
    
    /**
     * This flag controls whether or not the x-coordinates (in Java2D space) 
     * are rounded to integers.  When set to true, this can avoid the vertical
     * striping that anti-aliasing can generate.  However, the rounding may not
     * be appropriate for output in high resolution formats (for example, 
     * vector graphics formats such as SVG and PDF).
     * 
     * @since 1.0.3
     */
    private boolean roundXCoordinates;
    
    /**
     * Creates a new renderer.
     */
    public StackedXYAreaRenderer2() {
        this(null, null);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[2]++;
    }

    /**
     * Constructs a new renderer.
     *
     * @param labelGenerator  the tool tip generator to use.  <code>null</code>
     *                        is none.
     * @param urlGenerator  the URL generator (<code>null</code> permitted).
     */
    public StackedXYAreaRenderer2(XYToolTipGenerator labelGenerator, 
                                  XYURLGenerator urlGenerator) {
        super(labelGenerator, urlGenerator);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[3]++;
        this.roundXCoordinates = true;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[4]++;
    }
    
    /**
     * Returns the flag that controls whether or not the x-coordinates (in
     * Java2D space) are rounded to integer values.
     * 
     * @return The flag.
     * 
     * @since 1.0.4
     * 
     * @see #setRoundXCoordinates(boolean)
     */
    public boolean getRoundXCoordinates() {
        return this.roundXCoordinates;
    }
    
    /**
     * Sets the flag that controls whether or not the x-coordinates (in 
     * Java2D space) are rounded to integer values, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param round  the new flag value.
     * 
     * @since 1.0.4
     * 
     * @see #getRoundXCoordinates()
     */
    public void setRoundXCoordinates(boolean round) {
        this.roundXCoordinates = round;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[5]++;
        fireChangeEvent();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[6]++;
    }

    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (or <code>null</code> if the dataset is 
     *         <code>null</code> or empty).
     */
    public Range findRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[2]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[8]++;
        double min = Double.POSITIVE_INFINITY;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[9]++;
        double max = Double.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[10]++;
        TableXYDataset d = (TableXYDataset) dataset;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[11]++;
        int itemCount = d.getItemCount();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[1]--;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[2]--;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[3]++;
}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[13]++;
            double[] stackValues = getStackValues((TableXYDataset) dataset, 
                    d.getSeriesCount(), i);
            min = Math.min(min, stackValues[0]);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[14]++;
            max = Math.max(max, stackValues[1]);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[15]++;
        }
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((min == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[3]++;
            return null;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[4]++;}
        return new Range(min, max);
    }

    /**
     * Returns the number of passes required by the renderer.
     * 
     * @return 1.
     */
    public int getPassCount() {
        return 1;
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  information about crosshairs on a plot.
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         XYItemRendererState state,
                         Rectangle2D dataArea,
                         PlotRenderingInfo info,
                         XYPlot plot,
                         ValueAxis domainAxis,
                         ValueAxis rangeAxis,
                         XYDataset dataset,
                         int series,
                         int item,
                         CrosshairState crosshairState,
                         int pass) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[17]++;

        // setup for collecting optional entity info...
        Shape entityArea = null;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[18]++;
        EntityCollection entities = null;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[5]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[20]++;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[6]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[21]++;

        TableXYDataset tdataset = (TableXYDataset) dataset;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[22]++;
        
        // get the data point...
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[23]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Double.isNaN(y1)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[7]++;
            y1 = 0.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[25]++;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[8]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[26]++;        
        double[] stack1 = getStackValues(tdataset, series, item);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[27]++;
        
        // get the previous point and the next point so we can calculate a 
        // "hot spot" for the area (used by the chart entity)...
        double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[28]++;
        double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((Double.isNaN(y0)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[9]++;
            y0 = 0.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[30]++;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[10]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[31]++;
        double[] stack0 = getStackValues(tdataset, series, Math.max(item - 1, 
                0));
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[32]++;
        
        int itemCount = dataset.getItemCount(series);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[33]++;
        double x2 = dataset.getXValue(series, Math.min(item + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[34]++;
        double y2 = dataset.getYValue(series, Math.min(item + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((Double.isNaN(y2)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[11]++;
            y2 = 0.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[36]++;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[12]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[37]++;
        double[] stack2 = getStackValues(tdataset, series, Math.min(item + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[38]++;

        double xleft = (x0 + x1) / 2.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[39]++;
        double xright = (x1 + x2) / 2.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[40]++;
        double[] stackLeft = averageStackValues(stack0, stack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[41]++;
        double[] stackRight = averageStackValues(stack1, stack2);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[42]++;
        double[] adjStackLeft = adjustedStackValues(stack0, stack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[43]++;
        double[] adjStackRight = adjustedStackValues(stack1, stack2);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[44]++;
        
        RectangleEdge edge0 = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[45]++;
        
        float transX1 = (float) domainAxis.valueToJava2D(x1, dataArea, edge0);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[46]++;
        float transXLeft = (float) domainAxis.valueToJava2D(xleft, dataArea, 
                edge0);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[47]++;
        float transXRight = (float) domainAxis.valueToJava2D(xright, dataArea, 
                edge0);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
        
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.roundXCoordinates) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[13]++;
            transX1 = Math.round(transX1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[49]++;
            transXLeft = Math.round(transXLeft);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[50]++;
            transXRight = Math.round(transXRight);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[51]++;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[14]++;}
        float transY1;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[52]++;
        
        RectangleEdge edge1 = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[53]++;
        
        GeneralPath left = new GeneralPath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[54]++;
        GeneralPath right = new GeneralPath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[55]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((y1 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[15]++;  // handle positive value
            transY1 = (float) rangeAxis.valueToJava2D(y1 + stack1[1], dataArea, 
                    edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[56]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[57]++;
            float transStack1 = (float) rangeAxis.valueToJava2D(stack1[1], 
                    dataArea, edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[58]++;
            float transStackLeft = (float) rangeAxis.valueToJava2D(
                    adjStackLeft[1], dataArea, edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
            
            // LEFT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((y0 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[17]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[60]++;
                double yleft = (y0 + y1) / 2.0 + stackLeft[1];
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[61]++;
                float transYLeft 
                    = (float) rangeAxis.valueToJava2D(yleft, dataArea, edge1);
                left.moveTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[62]++;
                left.lineTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[63]++;
                left.lineTo(transXLeft, transStackLeft);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[64]++;
                left.lineTo(transXLeft, transYLeft);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[65]++;
                left.closePath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[66]++;

            }
            else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[18]++;
                left.moveTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[67]++;
                left.lineTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[68]++;
                left.lineTo(transXLeft, transStackLeft);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[69]++;
                left.closePath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[70]++;
            }
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[71]++;

            float transStackRight = (float) rangeAxis.valueToJava2D(
                    adjStackRight[1], dataArea, edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[72]++;
int CodeCoverConditionCoverageHelper_C11;
            // RIGHT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((y2 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[19]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[73]++;
                double yright = (y1 + y2) / 2.0 + stackRight[1];
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[74]++;
                float transYRight 
                    = (float) rangeAxis.valueToJava2D(yright, dataArea, edge1);
                right.moveTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[75]++;
                right.lineTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[76]++;
                right.lineTo(transXRight, transYRight);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[77]++;
                right.lineTo(transXRight, transStackRight);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[78]++;
                right.closePath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[79]++;

            }
            else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[20]++;
                right.moveTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[80]++;
                right.lineTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[81]++;
                right.lineTo(transXRight, transStackRight);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[82]++;
                right.closePath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[83]++;
            }

        }
        else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[16]++;  // handle negative value 
            transY1 = (float) rangeAxis.valueToJava2D(y1 + stack1[0], dataArea,
                    edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[84]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[85]++;
            float transStack1 = (float) rangeAxis.valueToJava2D(stack1[0], 
                    dataArea, edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[86]++;
            float transStackLeft = (float) rangeAxis.valueToJava2D(
                    adjStackLeft[0], dataArea, edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[87]++;
int CodeCoverConditionCoverageHelper_C12;

            // LEFT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((y0 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[21]++;
                left.moveTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[88]++;
                left.lineTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[89]++;
                left.lineTo(transXLeft, transStackLeft);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[90]++;
                left.clone();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[91]++;

            }
            else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[22]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[92]++;
                double yleft = (y0 + y1) / 2.0 + stackLeft[0];
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[93]++;
                float transYLeft = (float) rangeAxis.valueToJava2D(yleft, 
                        dataArea, edge1);
                left.moveTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[94]++;
                left.lineTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[95]++;
                left.lineTo(transXLeft, transStackLeft);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[96]++;
                left.lineTo(transXLeft, transYLeft);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[97]++;
                left.closePath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[98]++;
            }
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[99]++;
            float transStackRight = (float) rangeAxis.valueToJava2D(
                    adjStackRight[0], dataArea, edge1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[100]++;
int CodeCoverConditionCoverageHelper_C13;
            
            // RIGHT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((y2 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[23]++;
                right.moveTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[101]++;
                right.lineTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[102]++;
                right.lineTo(transXRight, transStackRight);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[103]++;
                right.closePath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[104]++;

            }
            else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[24]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[105]++;
                double yright = (y1 + y2) / 2.0 + stackRight[0];
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[106]++;
                float transYRight = (float) rangeAxis.valueToJava2D(yright, 
                        dataArea, edge1);
                right.moveTo(transX1, transStack1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[107]++;
                right.lineTo(transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[108]++;
                right.lineTo(transXRight, transYRight);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[109]++;
                right.lineTo(transXRight, transStackRight);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[110]++;
                right.closePath();
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[111]++;
            }
        }
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[112]++;

        //  Get series Paint and Stroke
        Paint itemPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[113]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[25]++;
            g2.setPaint(itemPaint);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[114]++;
            g2.fill(left);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[115]++;
            g2.fill(right);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[116]++;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[26]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[117]++;
int CodeCoverConditionCoverageHelper_C15; 
        
        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[27]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[118]++;
            GeneralPath gp = new GeneralPath(left);
            gp.append(right, false);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[119]++;
            entityArea = gp;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[120]++;
            addEntity(entities, entityArea, dataset, series, item, 
                    transX1, transY1);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[121]++;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[28]++;}

    }

    /**
     * Calculates the stacked values (one positive and one negative) of all 
     * series up to, but not including, <code>series</code> for the specified 
     * item. It returns [0.0, 0.0] if <code>series</code> is the first series.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series index.
     * @param index  the item index.
     *
     * @return An array containing the cumulative negative and positive values
     *     for all series values up to but excluding <code>series</code> 
     *     for <code>index</code>.
     */
    private double[] getStackValues(TableXYDataset dataset, 
                                    int series, int index) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[122]++;
        double[] result = new double[2];
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[123]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < series) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[4]--;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[5]--;
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.loops[6]++;
}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[124]++;
            double v = dataset.getYValue(i, index);
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[125]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((Double.isNaN(v)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[29]++;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[126]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((v >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[31]++;
                    result[1] += v;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[127]++;
   
                }
                else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[32]++;
                    result[0] += v;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[128]++;   
                }

            } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[30]++;}
        }
        return result;
    }
    
    /**
     * Returns a pair of "stack" values calculated as the mean of the two 
     * specified stack value pairs.
     * 
     * @param stack1  the first stack pair.
     * @param stack2  the second stack pair.
     * 
     * @return A pair of average stack values.
     */
    private double[] averageStackValues(double[] stack1, double[] stack2) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[129]++;
        double[] result = new double[2];
        result[0] = (stack1[0] + stack2[0]) / 2.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[130]++;
        result[1] = (stack1[1] + stack2[1]) / 2.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[131]++;
        return result;
    }

    /**
     * Calculates adjusted stack values from the supplied values.  The value is
     * the mean of the supplied values, unless either of the supplied values
     * is zero, in which case the adjusted value is zero also.
     * 
     * @param stack1  the first stack pair.
     * @param stack2  the second stack pair.
     * 
     * @return A pair of average stack values.
     */
    private double[] adjustedStackValues(double[] stack1, double[] stack2) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[132]++;
        double[] result = new double[2];
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[133]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((stack1[0] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((stack2[0] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[33]++;
            result[0] = 0.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[134]++;
   
        }
        else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[34]++;
            result[0] = (stack1[0] + stack2[0]) / 2.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[135]++;
        }
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[136]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((stack1[1] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((stack2[1] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[35]++;
            result[1] = 0.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[137]++;
   
        }
        else {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[36]++;
            result[1] = (stack1[1] + stack2[1]) / 2.0;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[138]++;
        }
        return result;
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[139]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[37]++;
            return true;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[38]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[140]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((obj instanceof StackedXYAreaRenderer2) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[40]++;}
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[141]++;
        StackedXYAreaRenderer2 that = (StackedXYAreaRenderer2) obj;
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.statements[142]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.roundXCoordinates != that.roundXCoordinates) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt.branches[42]++;}
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

class CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt ());
  }
    public static long[] statements = new long[143];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.StackedXYAreaRenderer2.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$5c4l60p6tg14mkgndx26gh8t61snlh6pjknr5y91lt () {
    super("org.jfree.chart.renderer.xy.StackedXYAreaRenderer2.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 142; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.StackedXYAreaRenderer2.java");
      for (int i = 1; i <= 142; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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

