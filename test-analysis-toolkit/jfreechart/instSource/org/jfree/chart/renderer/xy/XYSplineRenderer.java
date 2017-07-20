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
 * ---------------------
 * XYSplineRenderer.java
 * ---------------------
 * (C) Copyright 2007, by Klaus Rheinwald and Contributors.
 *
 * Original Author:  Klaus Rheinwald;
 * Contributor(s):   Tobias von Petersdorff (tvp@math.umd.edu, 
 *                       http://www.wam.umd.edu/~petersd/);
 *                   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 25-Jul-2007 : Version 1, contributed by Klaus Rheinwald (DG);
 * 03-Aug-2007 : Added new constructor (KR);
 * 25-Oct-2007 : Prevent duplicate control points (KR);
 *
 */


package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;


/**
 * A renderer that connects data points with natural cubic splines and/or 
 * draws shapes at each data point.  This renderer is designed for use with 
 * the {@link XYPlot} class.
 * 
 * @since 1.0.7
 */
public class XYSplineRenderer extends XYLineAndShapeRenderer {
  static {
    CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.ping();
  }


    /**
     * To collect data points for later splining.
     */
    private Vector points;

    /**
     * Resolution of splines (number of line segments between points)
     */
    private int precision;

    /**
     * Creates a new instance with the 'precision' attribute defaulting to 
     * 5.
     */
    public XYSplineRenderer() {
        this(5);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[1]++;
    }
    
    /**
     * Creates a new renderer with the specified precision.
     * 
     * @param precision  the number of points between data items.
     */
    public XYSplineRenderer(int precision) {
        super();
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[2]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((precision <= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[1]++;
            throw new IllegalArgumentException("Requires precision > 0.");

        } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[2]++;}
        this.precision = precision;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[4]++;
    }    
    
    /**
     * Get the resolution of splines.
     *
     * @return Number of line segments between points.
     * 
     * @see #setPrecision(int)
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * Set the resolution of splines and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param p  number of line segments between points (must be > 0).
     * 
     * @see #getPrecision()
     */
    public void setPrecision(int p) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((p <= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[3]++;
            throw new IllegalArgumentException("Requires p > 0.");

        } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[4]++;}
        this.precision = p;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[6]++;
        fireChangeEvent();
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[7]++;
    }

    /**
     * Initialises the renderer.
     * <P>
     * This method will be called before the first item is rendered, giving the
     * renderer an opportunity to initialise any state information it wants to
     * maintain.  The renderer can do nothing if it chooses.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to
     *              the caller.
     *
     * @return The renderer state.
     */
    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, 
            XYPlot plot, XYDataset data, PlotRenderingInfo info) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[8]++;

        State state = (State) super.initialise(g2, dataArea, plot, data, info);
        state.setProcessVisibleItemsOnly(false);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[9]++;
        this.points = new Vector();
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[10]++;
        setDrawSeriesLineAsPath(true);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[11]++;
        return state;
    }

    /**
     * Draws the item (first pass). This method draws the lines
     * connecting the items. Instead of drawing separate lines,
     * a GeneralPath is constructed and drawn at the end of
     * the series painting.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param plot  the plot (can be used to obtain standard color information
     *              etc).
     * @param dataset  the dataset.
     * @param pass  the pass.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataArea  the area within which the data is being drawn.
     */
    protected void drawPrimaryLineAsPath(XYItemRendererState state, 
            Graphics2D g2, XYPlot plot, XYDataset dataset, int pass, 
            int series, int item, ValueAxis domainAxis, ValueAxis rangeAxis, 
            Rectangle2D dataArea) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[12]++;

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[13]++;
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[14]++;

        // get the data points
        double x1 = dataset.getXValue(series, item);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[15]++;
        double y1 = dataset.getYValue(series, item);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[16]++;
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[17]++;
        double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;

        // collect points
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((Double.isNaN(transX1)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((Double.isNaN(transY1)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[5]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[19]++;
            ControlPoint p = new ControlPoint(plot.getOrientation() 
                                == PlotOrientation.HORIZONTAL ? (float) transY1 
                                : (float) transX1, plot.getOrientation() 
                                == PlotOrientation.HORIZONTAL ? (float) transX1 
                                        : (float) transY1);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.points.contains(p)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[7]++;
                this.points.add(p);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[21]++;

            } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[6]++;}
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((item == dataset.getItemCount(series) - 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[9]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[23]++;
            State s = (State) state;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
            // construct path
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.points.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[11]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[25]++;
                // we need at least two points to draw something
                ControlPoint cp0 = (ControlPoint) this.points.get(0);
                s.seriesPath.moveTo(cp0.x, cp0.y);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[26]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.points.size() == 2) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[13]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[28]++;
                    // we need at least 3 points to spline. Draw simple line 
                    // for two points
                    ControlPoint cp1 = (ControlPoint) this.points.get(1);
                    s.seriesPath.lineTo(cp1.x, cp1.y);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[29]++;

                } 
                else {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[14]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[30]++;
                    // construct spline
                    int np = this.points.size();
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[31]++; // number of points
                    float[] d = new float[np];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[32]++; // Newton form coefficients
                    float[] x = new float[np]; // x-coordinates of nodes
                    float y;
                    float t;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[33]++;
                    float oldy = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[34]++;
                    float oldt = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[35]++;

                    float[] a = new float[np];
                    float t1;
                    float t2;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[36]++;
                    float[] h = new float[np];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[37]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;

                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < np) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[1]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[2]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[3]++;
}
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[38]++;
                        ControlPoint cpi = (ControlPoint) this.points.get(i);
                        x[i] = cpi.x;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[39]++;
                        d[i] = cpi.y;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[40]++;
                    }
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[41]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;

                    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i <= np - 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[4]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[5]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[6]++;
}
                        h[i] = x[i] - x[i - 1];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[42]++;
                    }
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[43]++;
                    float[] sub = new float[np - 1];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[44]++;
                    float[] diag = new float[np - 1];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[45]++;
                    float[] sup = new float[np - 1];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[46]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;

                    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i <= np - 2) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[7]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[8]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[9]++;
}
                        diag[i] = (h[i] + h[i + 1]) / 3;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[47]++;
                        sup[i] = h[i + 1] / 6;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[48]++;
                        sub[i] = h[i] / 6;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[49]++;
                        a[i] = (d[i + 1] - d[i]) / h[i + 1] 
                                   - (d[i] - d[i - 1]) / h[i];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[50]++;
                    }
                    solveTridiag(sub, diag, sup, a, np - 2);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[51]++;

                    // note that a[0]=a[np-1]=0
                    // draw
                    oldt = x[0];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[52]++;
                    oldy = d[0];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[53]++;
                    s.seriesPath.moveTo(oldt, oldy);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[54]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[55]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[10]++;


int CodeCoverConditionCoverageHelper_C11;
                    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i <= np - 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[10]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[11]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[12]++;
}
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[56]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[13]++;


int CodeCoverConditionCoverageHelper_C12;
                        // loop over intervals between nodes
                        for (int j = 1;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((j <= this.precision) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[13]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[14]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[15]++;
}
                            t1 = (h[i] * j) / this.precision;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[57]++;
                            t2 = h[i] - t1;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[58]++;
                            y = ((-a[i - 1] / 6 * (t2 + h[i]) * t1 + d[i - 1]) 
                                    * t2 + (-a[i] / 6 * (t1 + h[i]) * t2 
                                    + d[i]) * t1) / h[i];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[59]++;
                            t = x[i - 1] + t1;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[60]++;
                            s.seriesPath.lineTo(t, y);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[61]++;
                            oldt = t;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[62]++;
                            oldy = y;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[63]++;
                        }
                    }
                }
                // draw path
                drawFirstPassShape(g2, pass, series, item, s.seriesPath);
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[64]++;

            } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[12]++;}

            // reset points vector
            this.points = new Vector();
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[65]++;

        } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[10]++;}
    }

    /**
     * Document me!
     * 
     * @param sub
     * @param diag
     * @param sup
     * @param b
     * @param n
     */
    private void solveTridiag(float[] sub, float[] diag, float[] sup, 
            float[] b, int n) {
/*      solve linear system with tridiagonal n by n matrix a
        using Gaussian elimination *without* pivoting
        where   a(i,i-1) = sub[i]  for 2<=i<=n
        a(i,i)   = diag[i] for 1<=i<=n
        a(i,i+1) = sup[i]  for 1<=i<=n-1
        (the values sub[1], sup[n] are ignored)
        right hand side vector b[1:n] is overwritten with solution
        NOTE: 1...n is used in all arrays, 0 is unused */
        int i;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[66]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[16]++;


int CodeCoverConditionCoverageHelper_C13;
/*                  factorization and forward substitution */
        for (i = 2;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i <= n) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[16]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[17]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[18]++;
}
            sub[i] = sub[i] / diag[i - 1];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[67]++;
            diag[i] = diag[i] - sub[i] * sup[i - 1];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[68]++;
            b[i] = b[i] - sub[i] * b[i - 1];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[69]++;
        }
        b[n] = b[n] / diag[n];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[70]++;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[71]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[19]++;


int CodeCoverConditionCoverageHelper_C14;
        for (i = n - 1;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i >= 1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[19]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[20]--;
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.loops[21]++;
}
            b[i] = (b[i] - sup[i] * b[i + 1]) / diag[i];
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[72]++;
        }
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[73]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[15]++;
            return true;

        } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[16]++;}
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj instanceof XYSplineRenderer) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[18]++;}
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[75]++;
        XYSplineRenderer that = (XYSplineRenderer) obj;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[76]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.precision != that.precision) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[20]++;}
        return super.equals(obj);
    }

    /**
     * Represents a control point.
     */
    class ControlPoint {
        
        /** The x-coordinate. */
        public float x;
        
        /** The y-coordinate. */
        public float y;

        /**
         * Creates a new control point.
         * 
         * @param x  the x-coordinate.
         * @param y  the y-coordinate.
         */
        public ControlPoint(float x, float y) {
            this.x = x;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[77]++;
            this.y = y;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[78]++;
        }

        /**
         * Tests this point for equality with an arbitrary object.
         * 
         * @param obj  the object (<code>null</code> permitted.
         * 
         * @return A boolean.
         */
        public boolean equals(Object obj) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[79]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[21]++;
                return true;

            } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[22]++;}
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[80]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj instanceof ControlPoint) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[23]++;
                return false;

            } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[24]++;}
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[81]++;
            ControlPoint that = (ControlPoint) obj;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[82]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.x != that.x) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[25]++;
                return false;

            } else {
  CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.branches[26]++;}
            /*&& y == ((ControlPoint) obj).y*/;
CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41.statements[83]++;
            return true;
        }

    }
}

class CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41 ());
  }
    public static long[] statements = new long[84];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.XYSplineRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$21dyexg9pnk474lsrwu6iysxsn5bubx41 () {
    super("org.jfree.chart.renderer.xy.XYSplineRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 83; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.XYSplineRenderer.java");
      for (int i = 1; i <= 83; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
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
      for (int i = 1; i <= 7; i++) {
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

