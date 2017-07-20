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
 * ----------------------
 * DeviationRenderer.java
 * ----------------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 21-Feb-2007 : Version 1 (DG);
 * 04-May-2007 : Set processVisibleItemsOnly flag to false (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;

/**
 * A specialised subclass of the {@link XYLineAndShapeRenderer} that requires
 * an {@link IntervalXYDataset} and represents the y-interval by shading an 
 * area behind the y-values on the chart.
 * 
 * @since 1.0.5
 */
public class DeviationRenderer extends XYLineAndShapeRenderer {
  static {
    CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.ping();
  }


    /**
     * A state object that is passed to each call to <code>drawItem</code>.
     */
    public static class State extends XYLineAndShapeRenderer.State {
        
        /** 
         * A list of coordinates for the upper y-values in the current series 
         * (after translation into Java2D space).
         */
        public List upperCoordinates;
        
        /** 
         * A list of coordinates for the lower y-values in the current series 
         * (after translation into Java2D space).
         */
        public List lowerCoordinates;
        
        /**
         * Creates a new state instance.
         * 
         * @param info  the plot rendering info.
         */
        public State(PlotRenderingInfo info) {
            super(info);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[1]++;
            this.lowerCoordinates = new java.util.ArrayList();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[2]++;
            this.upperCoordinates = new java.util.ArrayList();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[3]++;
        }
        
    }
    
    /** The alpha transparency for the interval shading. */
    private float alpha;

    /**
     * Creates a new renderer that displays lines and shapes for the data 
     * items, as well as the shaded area for the y-interval.
     */
    public DeviationRenderer() {
        this(true, true);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[4]++;
    }
    
    /**
     * Creates a new renderer.
     * 
     * @param lines  show lines between data items?
     * @param shapes  show a shape for each data item?
     */
    public DeviationRenderer(boolean lines, boolean shapes) {
        super(lines, shapes);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[5]++;
        super.setDrawSeriesLineAsPath(true);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[6]++;
        this.alpha = 0.5f;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[7]++;
    }
    
    /**
     * Returns the alpha transparency for the background shading.
     * 
     * @return The alpha transparency.
     * 
     * @see #setAlpha(float)
     */
    public float getAlpha() {
        return this.alpha;
    }

    /**
     * Sets the alpha transparency for the background shading, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param alpha   the alpha (in the range 0.0f to 1.0f).
     * 
     * @see #getAlpha()
     */
    public void setAlpha(float alpha) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((alpha < 0.0f) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((alpha > 1.0f) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[1]++;
            throw new IllegalArgumentException(
                    "Requires 'alpha' in the range 0.0 to 1.0.");

        } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[2]++;}
        this.alpha = alpha;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[9]++;
        fireChangeEvent();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[10]++;
    }

    /**
     * This method is overridden so that this flag cannot be changed---it is
     * set to <code>true</code> for this renderer.
     * 
     * @param flag  ignored.
     */
    public void setDrawSeriesLineAsPath(boolean flag) {
        // ignore
    }

    /**
     * Initialises and returns a state object that can be passed to each
     * invocation of the {@link #drawItem} method.
     * 
     * @param g2  the graphics target.
     * @param dataArea  the data area.
     * @param plot  the plot.
     * @param dataset  the dataset.
     * @param info  the plot rendering info.
     * 
     * @return A newly initialised state object.
     */
    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, 
            XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[11]++;
        State state = new State(info);
        state.seriesPath = new GeneralPath();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[12]++;
        state.setProcessVisibleItemsOnly(false);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[13]++;
        return state;
    }

    /**
     * Returns the number of passes (through the dataset) used by this 
     * renderer.
     * 
     * @return <code>3</code>.
     */
    public int getPassCount() {
        return 3;
    }

    /**
     * Returns <code>true</code> if this is the pass where the shapes are
     * drawn.
     * 
     * @param pass  the pass index.
     * 
     * @return A boolean.
     * 
     * @see #isLinePass(int)
     */
    protected boolean isItemPass(int pass) {
        return (pass == 2);
    }

    /**
     * Returns <code>true</code> if this is the pass where the lines are
     * drawn.
     * 
     * @param pass  the pass index.
     * 
     * @return A boolean.
     * 
     * @see #isItemPass(int)
     */
    protected boolean isLinePass(int pass) {
        return (pass == 1);
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
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
                         XYDataset dataset,
                         int series,
                         int item,
                         CrosshairState crosshairState,
                         int pass) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getItemVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[3]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[4]++;}
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;

        // first pass draws the shading
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[5]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[16]++;
            IntervalXYDataset intervalDataset = (IntervalXYDataset) dataset;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[17]++;
            State drState = (State) state;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[18]++;

            double x = intervalDataset.getXValue(series, item);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[19]++;
            double yLow = intervalDataset.getStartYValue(series, item);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[20]++;
            double yHigh  = intervalDataset.getEndYValue(series, item);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[21]++;

            RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[22]++;
            RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[23]++;
            
            double xx = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[24]++;
            double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, 
                    yAxisLocation);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[25]++;
            double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, 
                    yAxisLocation);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[26]++;

            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[7]++;
                drState.lowerCoordinates.add(new double[] {yyLow, xx});
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[28]++;
                drState.upperCoordinates.add(new double[] {yyHigh, xx});
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[29]++;

            }
            else {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[8]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[30]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[9]++;
                drState.lowerCoordinates.add(new double[] {xx, yyLow});
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[31]++;
                drState.upperCoordinates.add(new double[] {xx, yyHigh});
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[32]++;

            } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[10]++;}
}
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;

            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((item == (dataset.getItemCount(series) - 1)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[11]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[34]++;
                // last item in series, draw the lot...
                // set up the alpha-transparency...
                Composite originalComposite = g2.getComposite();
                g2.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, this.alpha));
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[35]++;
                g2.setPaint(getItemFillPaint(series, item));
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[36]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[37]++;
                GeneralPath area = new GeneralPath();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[38]++;
                double[] coords = (double[]) drState.lowerCoordinates.get(0);
                area.moveTo((float) coords[0], (float) coords[1]);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[39]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[40]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
                for (int i = 1;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < drState.lowerCoordinates.size()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[1]--;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[2]--;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[3]++;
}
                    coords = (double[]) drState.lowerCoordinates.get(i);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[41]++;
                    area.lineTo((float) coords[0], (float) coords[1]);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[42]++;
                }
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[43]++;
                int count = drState.upperCoordinates.size();
                coords = (double[]) drState.upperCoordinates.get(count - 1);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[44]++;
                area.lineTo((float) coords[0], (float) coords[1]);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[45]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[46]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
                for (int i = count - 2;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[4]--;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[5]--;
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.loops[6]++;
}
                    coords = (double[]) drState.upperCoordinates.get(i);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[47]++;
                    area.lineTo((float) coords[0], (float) coords[1]);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[48]++;
                }
                area.closePath();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[49]++;
                g2.fill(area);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[50]++;
                g2.setComposite(originalComposite);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[51]++;
                
                drState.lowerCoordinates.clear();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[52]++;
                drState.upperCoordinates.clear();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[53]++;

            } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[12]++;}
            
        } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[6]++;}
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isLinePass(pass)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[13]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
            
            // the following code handles the line for the y-values...it's
            // all done by code in the super class
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((item == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[15]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[56]++;
                State s = (State) state;
                s.seriesPath.reset();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[57]++;
                s.setLastPointGood(false);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[58]++;
     
            } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[16]++;}
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[59]++;
int CodeCoverConditionCoverageHelper_C11;

            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getItemLineVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[17]++;
                drawPrimaryLineAsPath(state, g2, plot, dataset, pass, 
                        series, item, domainAxis, rangeAxis, dataArea);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[60]++;

            } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[18]++;}

        }
        
        // second pass adds shapes where the items are ..
        else {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[14]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[61]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isItemPass(pass)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[19]++;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[62]++;

            // setup for collecting optional entity info...
            EntityCollection entities = null;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[63]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[21]++;
                entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[64]++;

            } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[22]++;}

            drawSecondaryPass(g2, plot, dataset, pass, series, item, 
                    domainAxis, dataArea, rangeAxis, crosshairState, entities);
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[65]++;

        } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[20]++;}
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
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[66]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[23]++;
            return true;

        } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[24]++;}
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[67]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((obj instanceof DeviationRenderer) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[26]++;}
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[68]++;
        DeviationRenderer that = (DeviationRenderer) obj;
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.statements[69]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.alpha != that.alpha) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip.branches[28]++;}
        return super.equals(obj);
    }

}

class CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip ());
  }
    public static long[] statements = new long[70];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.DeviationRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 16; i++) {
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

  public CodeCoverCoverageCounter$b80cxsp1x6qu4tobg76cv5zx5zf3fgjgip () {
    super("org.jfree.chart.renderer.xy.DeviationRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 69; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.DeviationRenderer.java");
      for (int i = 1; i <= 69; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
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

