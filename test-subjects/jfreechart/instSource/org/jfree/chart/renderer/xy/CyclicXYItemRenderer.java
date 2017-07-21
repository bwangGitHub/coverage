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
 * CyclicXYItemRenderer.java
 * ---------------------------
 * (C) Copyright 2003-2007, by Nicolas Brodu and Contributors.
 *
 * Original Author:  Nicolas Brodu;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Nov-2003 : Initial import to JFreeChart from the JSynoptic project (NB);
 * 23-Dec-2003 : Added missing Javadocs (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * ------------- JFREECHART 1.0.0 ---------------------------------------------
 * 06-Jul-2006 : Modified to call only dataset methods that return double
 *               primitives (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CyclicNumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

/**
 * The Cyclic XY item renderer is specially designed to handle cyclic axis. 
 * While the standard renderer would draw a line across the plot when a cycling 
 * occurs, the cyclic renderer splits the line at each cycle end instead. This 
 * is done by interpolating new points at cycle boundary. Thus, correct 
 * appearance is restored. 
 * 
 * The Cyclic XY item renderer works exactly like a standard XY item renderer 
 * with non-cyclic axis. 
 */
public class CyclicXYItemRenderer extends StandardXYItemRenderer 
                                  implements Serializable {
  static {
    CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 4035912243303764892L;
  static {
    CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[1]++;
  }
    
    /**
     * Default constructor.
     */
    public CyclicXYItemRenderer() {
        super();
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[2]++;
    }

    /**
     * Creates a new renderer.
     * 
     * @param type  the renderer type.
     */
    public CyclicXYItemRenderer(int type) {
        super(type);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[3]++;
    }

    /**
     * Creates a new renderer.
     * 
     * @param type  the renderer type.
     * @param labelGenerator  the tooltip generator.
     */
    public CyclicXYItemRenderer(int type, XYToolTipGenerator labelGenerator) {
        super(type, labelGenerator);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[4]++;
    }

    /**
     * Creates a new renderer.
     * 
     * @param type  the renderer type.
     * @param labelGenerator  the tooltip generator.
     * @param urlGenerator  the url generator.
     */
    public CyclicXYItemRenderer(int type, 
                                XYToolTipGenerator labelGenerator,
                                XYURLGenerator urlGenerator) {
        super(type, labelGenerator, urlGenerator);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[5]++;
    }

    
    /** 
     * Draws the visual representation of a single data item.
     * When using cyclic axis, do not draw a line from right to left when 
     * cycling as would a standard XY item renderer, but instead draw a line 
     * from the previous point to the cycle bound in the last cycle, and a line
     * from the cycle bound to current point in the current cycle.  
     * 
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data area.
     * @param info  the plot rendering info.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index.
     * @param item  the item index.
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the current pass index.
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
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((!
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((getPlotLines()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
) || ((!(
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((domainAxis instanceof CyclicNumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
)) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((rangeAxis instanceof CyclicNumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
))) || (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((item <= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[1]++;
            super.drawItem(g2, state, dataArea, info, plot, domainAxis, 
                    rangeAxis, dataset, series, item, crosshairState, pass);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[7]++;
            return;

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[2]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[8]++;

        // get the previous data point...
        double xn = dataset.getXValue(series, item - 1);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[9]++;
        double yn = dataset.getYValue(series, item - 1);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        // If null, don't draw line => then delegate to parent
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Double.isNaN(yn)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[3]++;
            super.drawItem(g2, state, dataArea, info, plot, domainAxis, 
                    rangeAxis, dataset, series, item, crosshairState, pass);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[11]++;
            return;

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[4]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[12]++;
        double[] x = new double[2];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[13]++;
        double[] y = new double[2];
        x[0] = xn;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[14]++;
        y[0] = yn;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[15]++;
        
        // get the data point...
        xn = dataset.getXValue(series, item);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[16]++;
        yn = dataset.getYValue(series, item);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[17]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        // If null, don't draw line at all
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((Double.isNaN(yn)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[5]++;
            return;

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[6]++;}
        x[1] = xn;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[19]++;
        y[1] = yn;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[20]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[21]++;

        // Now split the segment as needed
        double xcycleBound = Double.NaN;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[22]++;
        double ycycleBound = Double.NaN;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[23]++;
        boolean xBoundMapping = false, yBoundMapping = false;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[24]++;
        CyclicNumberAxis cnax = null, cnay = null;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((domainAxis instanceof CyclicNumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[7]++;
            cnax = (CyclicNumberAxis) domainAxis;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[26]++;
            xcycleBound = cnax.getCycleBound();
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[27]++;
            xBoundMapping = cnax.isBoundMappedToLastCycle();
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[28]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
            // If the segment must be splitted, insert a new point
            // Strict test forces to have real segments (not 2 equal points) 
            // and avoids division by 0 
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C5 |= (512)) == 0 || true) &&
 ((x[0] != x[1]) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (256)) == 0 || true)))
) && ((
(((CodeCoverConditionCoverageHelper_C5 |= (128)) == 0 || true) &&
 ((xcycleBound >= x[0]) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (64)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((xcycleBound <= x[1]) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((xcycleBound >= x[1]) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((xcycleBound <= x[0]) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 5) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 5) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[9]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[30]++;
                double[] nx = new double[3];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[31]++;
                double[] ny = new double[3];
                nx[0] = x[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[32]++; nx[2] = x[1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[33]++; ny[0] = y[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[34]++; ny[2] = y[1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[35]++;
                nx[1] = xcycleBound;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[36]++;
                ny[1] = (y[1] - y[0]) * (xcycleBound - x[0]) 
                        / (x[1] - x[0]) + y[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[37]++;
                x = nx;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[38]++; y = ny;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[39]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[8]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[40]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rangeAxis instanceof CyclicNumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[11]++;
            cnay = (CyclicNumberAxis) rangeAxis;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[41]++;
            ycycleBound = cnay.getCycleBound();
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[42]++;
            yBoundMapping = cnay.isBoundMappedToLastCycle();
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[43]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[44]++;
int CodeCoverConditionCoverageHelper_C7;
            // The split may occur in either x splitted segments, if any, but 
            // not in both
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C7 |= (512)) == 0 || true) &&
 ((y[0] != y[1]) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (256)) == 0 || true)))
) && ((
(((CodeCoverConditionCoverageHelper_C7 |= (128)) == 0 || true) &&
 ((ycycleBound >= y[0]) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (64)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((ycycleBound <= y[1]) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((ycycleBound >= y[1]) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ycycleBound <= y[0]) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 5) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 5) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[13]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[45]++;
                double[] nx = new double[x.length + 1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[46]++;
                double[] ny = new double[y.length + 1];
                nx[0] = x[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[47]++; nx[2] = x[1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[48]++; ny[0] = y[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[49]++; ny[2] = y[1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[50]++;
                ny[1] = ycycleBound;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[51]++;
                nx[1] = (x[1] - x[0]) * (ycycleBound - y[0]) 
                        / (y[1] - y[0]) + x[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[52]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[53]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((x.length == 3) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[15]++; 
                    nx[3] = x[2];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[54]++; ny[3] = y[2];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[55]++;
 
                } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[16]++;}
                x = nx;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[56]++; y = ny;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[57]++;

            }
            else {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[14]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[58]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C9 |= (2048)) == 0 || true) &&
 ((x.length == 3) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1024)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C9 |= (512)) == 0 || true) &&
 ((y[1] != y[2]) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (256)) == 0 || true)))
) && ((
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 ((ycycleBound >= y[1]) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((ycycleBound <= y[2]) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((ycycleBound >= y[2]) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ycycleBound <= y[1]) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 6) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 6) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[17]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[59]++;
                double[] nx = new double[4];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[60]++;
                double[] ny = new double[4];
                nx[0] = x[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[61]++; nx[1] = x[1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[62]++; nx[3] = x[2];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[63]++; 
                ny[0] = y[0];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[64]++; ny[1] = y[1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[65]++; ny[3] = y[2];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[66]++;
                ny[2] = ycycleBound;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[67]++;
                nx[2] = (x[2] - x[1]) * (ycycleBound - y[1]) 
                        / (y[2] - y[1]) + x[1];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[68]++;
                x = nx;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[69]++; y = ny;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[70]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[18]++;}
}

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[12]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[71]++;
int CodeCoverConditionCoverageHelper_C10;
        
        // If the line is not wrapping, then parent is OK
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((x.length == 2) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[19]++;
            super.drawItem(g2, state, dataArea, info, plot, domainAxis, 
                    rangeAxis, dataset, series, item, crosshairState, pass);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[72]++;
            return;

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[20]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[73]++;

        OverwriteDataSet newset = new OverwriteDataSet(x, y, dataset);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[74]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((cnax != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[21]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[75]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((xcycleBound == x[0]) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[23]++;
                cnax.setBoundMappedToLastCycle(x[1] <= xcycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[76]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[24]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[77]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((xcycleBound == x[1]) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[25]++;
                cnax.setBoundMappedToLastCycle(x[0] <= xcycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[78]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[22]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[79]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((cnay != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[27]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[80]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((ycycleBound == y[0]) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[29]++;
                cnay.setBoundMappedToLastCycle(y[1] <= ycycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[81]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[30]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[82]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ycycleBound == y[1]) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[31]++;
                cnay.setBoundMappedToLastCycle(y[0] <= ycycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[83]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[28]++;}
        super.drawItem(
            g2, state, dataArea, info, plot, domainAxis, rangeAxis, 
            newset, series, 1, crosshairState, pass
        );
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[84]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[85]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((cnax != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[33]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[86]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((xcycleBound == x[1]) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[35]++;
                cnax.setBoundMappedToLastCycle(x[2] <= xcycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[87]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[36]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[88]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((xcycleBound == x[2]) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[37]++;
                cnax.setBoundMappedToLastCycle(x[1] <= xcycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[89]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[38]++;}

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[34]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[90]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((cnay != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[39]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[91]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ycycleBound == y[1]) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[41]++;
                cnay.setBoundMappedToLastCycle(y[2] <= ycycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[92]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[42]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[93]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ycycleBound == y[2]) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[43]++;
                cnay.setBoundMappedToLastCycle(y[1] <= ycycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[94]++;

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[40]++;}
        super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, 
                newset, series, 2, crosshairState, pass);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[95]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[96]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((x.length == 4) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[45]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[97]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((cnax != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[47]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[98]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((xcycleBound == x[2]) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[49]++;
                    cnax.setBoundMappedToLastCycle(x[3] <= xcycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[99]++;

                } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[50]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[100]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((xcycleBound == x[3]) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[51]++;
                    cnax.setBoundMappedToLastCycle(x[2] <= xcycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[101]++;

                } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[52]++;}

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[48]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[102]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((cnay != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[53]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[103]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((ycycleBound == y[2]) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[55]++;
                    cnay.setBoundMappedToLastCycle(y[3] <= ycycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[104]++;

                } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[56]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[105]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((ycycleBound == y[3]) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[57]++;
                    cnay.setBoundMappedToLastCycle(y[2] <= ycycleBound);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[106]++;

                } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[58]++;}

            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[54]++;}
            super.drawItem(g2, state, dataArea, info, plot, domainAxis, 
                    rangeAxis, newset, series, 3, crosshairState, pass);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[107]++;

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[46]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[108]++;
int CodeCoverConditionCoverageHelper_C30;
        
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((cnax != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[59]++;
            cnax.setBoundMappedToLastCycle(xBoundMapping);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[109]++;

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[60]++;}
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[110]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((cnay != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[61]++;
            cnay.setBoundMappedToLastCycle(yBoundMapping);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[111]++;

        } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[62]++;}
    }

    /** 
     * A dataset to hold the interpolated points when drawing new lines. 
     */
    protected static class OverwriteDataSet implements XYDataset {
        
        /** The delegate dataset. */
        protected XYDataset delegateSet;
        
        /** Storage for the x and y values. */
        Double[] x, y;
        
        /**
         * Creates a new dataset.
         * 
         * @param x  the x values.
         * @param y  the y values.
         * @param delegateSet  the dataset.
         */
        public OverwriteDataSet(double [] x, double[] y, 
                                XYDataset delegateSet) {
            this.delegateSet = delegateSet;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[112]++;
            this.x = new Double[x.length];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[113]++; this.y = new Double[y.length];
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[114]++;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[115]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.loops[1]++;


int CodeCoverConditionCoverageHelper_C32;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < x.length) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.loops[1]--;
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.loops[2]--;
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.loops[3]++;
} 
                this.x[i] = new Double(x[i]);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[116]++;
                this.y[i] = new Double(y[i]);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[117]++;
            }
        }

        /**
         * Returns the order of the domain (X) values.
         * 
         * @return The domain order.
         */
        public DomainOrder getDomainOrder() {
            return DomainOrder.NONE;
        }
        
        /**
         * Returns the number of items for the given series.
         * 
         * @param series  the series index (zero-based).
         * 
         * @return The item count.
         */
        public int getItemCount(int series) {
            return this.x.length;
        }

        /**
         * Returns the x-value.
         * 
         * @param series  the series index (zero-based).
         * @param item  the item index (zero-based).
         * 
         * @return The x-value.
         */
        public Number getX(int series, int item) {
            return this.x[item];
        }

        /**
         * Returns the x-value (as a double primitive) for an item within a 
         * series.
         * 
         * @param series  the series (zero-based index).
         * @param item  the item (zero-based index).
         * 
         * @return The x-value.
         */
        public double getXValue(int series, int item) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[118]++;
            double result = Double.NaN;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[119]++;
            Number x = getX(series, item);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[120]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[63]++;
                result = x.doubleValue();
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[121]++;
   
            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[64]++;}
            return result;   
        }

        /**
         * Returns the y-value.
         * 
         * @param series  the series index (zero-based).
         * @param item  the item index (zero-based).
         * 
         * @return The y-value.
         */
        public Number getY(int series, int item) {
            return this.y[item];
        }

        /**
         * Returns the y-value (as a double primitive) for an item within a 
         * series.
         * 
         * @param series  the series (zero-based index).
         * @param item  the item (zero-based index).
         * 
         * @return The y-value.
         */
        public double getYValue(int series, int item) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[122]++;
            double result = Double.NaN;
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[123]++;
            Number y = getY(series, item);
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[124]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((y != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[65]++;
                result = y.doubleValue();
CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.statements[125]++;
   
            } else {
  CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt.branches[66]++;}
            return result;   
        }

        /**
         * Returns the number of series in the dataset.
         * 
         * @return The series count.
         */
        public int getSeriesCount() {
            return this.delegateSet.getSeriesCount();
        }

        /**
         * Returns the name of the given series.
         * 
         * @param series  the series index (zero-based).
         * 
         * @return The series name.
         */
        public Comparable getSeriesKey(int series) {
            return this.delegateSet.getSeriesKey(series);
        }

        /**
         * Returns the index of the named series, or -1.
         * 
         * @param seriesName  the series name.
         * 
         * @return The index.
         */
        public int indexOf(Comparable seriesName) {
            return this.delegateSet.indexOf(seriesName);
        }

        /**
         * Does nothing.
         * 
         * @param listener  ignored.
         */
        public void addChangeListener(DatasetChangeListener listener) {
            // unused in parent
        }

        /**
         * Does nothing.
         * 
         * @param listener  ignored.
         */
        public void removeChangeListener(DatasetChangeListener listener) {
            // unused in parent
        }

        /**
         * Returns the dataset group.
         * 
         * @return The dataset group.
         */
        public DatasetGroup getGroup() {
            // unused but must return something, so while we are at it...
            return this.delegateSet.getGroup();
        }

        /**
         * Does nothing.
         * 
         * @param group  ignored.
         */
        public void setGroup(DatasetGroup group) {
            // unused in parent
        }
        
    }
    
}

class CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt ());
  }
    public static long[] statements = new long[126];
    public static long[] branches = new long[67];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[35];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.CyclicXYItemRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,3,1,3,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 34; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$32l4so9jkne010eyxs5hzr6gjivq7eeyhgugjtt () {
    super("org.jfree.chart.renderer.xy.CyclicXYItemRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 125; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 66; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.CyclicXYItemRenderer.java");
      for (int i = 1; i <= 125; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 66; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 34; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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



