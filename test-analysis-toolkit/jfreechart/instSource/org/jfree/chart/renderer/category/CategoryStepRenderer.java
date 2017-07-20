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
 * -------------------------
 * CategoryStepRenderer.java
 * -------------------------
 *
 * (C) Copyright 2004-2007, by Brian Cole and Contributors.
 *
 * Original Author:  Brian Cole;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 21-Apr-2004 : Version 1, contributed by Brian Cole (DG);
 * 22-Apr-2004 : Fixed Checkstyle complaints (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 08-Mar-2005 : Added equals() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 30-Nov-2006 : Added checks for series visibility (DG);
 * 22-Feb-2007 : Use new state object for reusable line, enable chart entities 
 *               (for tooltips, URLs), added new getLegendItem() override (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.util.PublicCloneable;

/**
 * A "step" renderer similar to {@link XYStepRenderer} but
 * that can be used with the {@link CategoryPlot} class.
 */
public class CategoryStepRenderer extends AbstractCategoryItemRenderer
                                  implements Cloneable, PublicCloneable, 
                                             Serializable {
  static {
    CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.ping();
  }


    /**
     * State information for the renderer.
     */
    protected static class State extends CategoryItemRendererState {

        /** 
         * A working line for re-use to avoid creating large numbers of
         * objects.
         */
        public Line2D line;
        
        /**
         * Creates a new state instance.
         * 
         * @param info  collects plot rendering information (<code>null</code> 
         *              permitted).
         */
        public State(PlotRenderingInfo info) {
            super(info);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[1]++;
            this.line = new Line2D.Double();
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[2]++;
        }
        
    }
    
    /** For serialization. */
    private static final long serialVersionUID = -5121079703118261470L;
  static {
    CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[3]++;
  }
    
    /** The stagger width. */
    public static final int STAGGER_WIDTH = 5;
  static {
    CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[4]++;
  } // could make this configurable
  
    /** 
     * A flag that controls whether or not the steps for multiple series are 
     * staggered. 
     */
    private boolean stagger = false;
  {
    CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[5]++;
  }

    /** 
     * Creates a new renderer (stagger defaults to <code>false</code>).
     */
    public CategoryStepRenderer() {
        this(false);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[6]++;
    }
    
    /**
     * Creates a new renderer.
     *  
     * @param stagger  should the horizontal part of the step be staggered by 
     *                 series? 
     */
    public CategoryStepRenderer(boolean stagger) {
        this.stagger = stagger;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[7]++;
    }
  
    /**
     * Returns the flag that controls whether the series steps are staggered.
     * 
     * @return A boolean.
     */
    public boolean getStagger() {
        return this.stagger;
    }
    
    /**
     * Sets the flag that controls whether or not the series steps are 
     * staggered and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param shouldStagger  a boolean.
     */
    public void setStagger(boolean shouldStagger) {
        this.stagger = shouldStagger;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[8]++;
        fireChangeEvent();
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[9]++;
    }

    /**
     * Returns a legend item for a series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return The legend item.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[10]++;

        CategoryPlot p = getPlot();
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[2]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;

        // check that a legend item needs to be displayed...
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((isSeriesVisible(series)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(series)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[3]++;
            return null;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[4]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[13]++;

        CategoryDataset dataset = p.getDataset(datasetIndex);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[14]++;
        String label = getLegendItemLabelGenerator().generateLabel(dataset, 
                series);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[15]++;
        String description = label;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[16]++;
        String toolTipText = null;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[17]++;
int CodeCoverConditionCoverageHelper_C3; 
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[5]++;
            toolTipText = getLegendItemToolTipGenerator().generateLabel(
                    dataset, series);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[18]++;
   
        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[6]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[19]++;
        String urlText = null;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[7]++;
            urlText = getLegendItemURLGenerator().generateLabel(dataset, 
                    series);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[21]++;
   
        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[8]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[22]++;
        Shape shape = new Rectangle2D.Double(-4.0, -3.0, 8.0, 6.0);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[23]++;
        Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[24]++;
     
        LegendItem item = new LegendItem(label, description, toolTipText, 
                urlText, shape, paint);
        item.setSeriesKey(dataset.getRowKey(series));
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[25]++;
        item.setSeriesIndex(series);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[26]++;
        item.setDataset(dataset);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[27]++;
        item.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[28]++;
        return item;
    }

    /**
     * Creates a new state instance.  This method is called from 
     * {@link #initialise(Graphics2D, Rectangle2D, CategoryPlot, int, 
     * PlotRenderingInfo)}, and we override it to ensure that the state
     * contains a working Line2D instance.
     * 
     * @param info  the plot rendering info (<code>null</code> is permitted).
     * 
     * @return A new state instance.
     */
    protected CategoryItemRendererState createState(PlotRenderingInfo info) {
        return new State(info);
    }

    /**
     * Draws a line taking into account the specified orientation.
     * <p>
     * In version 1.0.5, the signature of this method was changed by the 
     * addition of the 'state' parameter.  This is an incompatible change, but
     * is considered a low risk because it is unlikely that anyone has 
     * subclassed this renderer.  If this *does* cause trouble for you, please
     * report it as a bug.
     * 
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param orientation  the plot orientation.
     * @param x0  the x-coordinate for the start of the line.
     * @param y0  the y-coordinate for the start of the line.
     * @param x1  the x-coordinate for the end of the line.
     * @param y1  the y-coordinate for the end of the line.
     */
    protected void drawLine(Graphics2D g2, State state, 
            PlotOrientation orientation, double x0, double y0, double x1, 
            double y1) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
     
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[9]++;
            state.line.setLine(x0, y0, x1, y1);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[30]++;
            g2.draw(state.line);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[31]++;

        }
        else {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[10]++;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[32]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[11]++;
            state.line.setLine(y0, x0, y1, x1);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[33]++; // switch x and y
            g2.draw(state.line);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[34]++;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[12]++;}
}

    }

    /**
     * Draw a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area in which the data is drawn.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         CategoryItemRendererState state,
                         Rectangle2D dataArea,
                         CategoryPlot plot,
                         CategoryAxis domainAxis,
                         ValueAxis rangeAxis,
                         CategoryDataset dataset,
                         int row,
                         int column,
                         int pass) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getItemVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[13]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[14]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[36]++;
        
        Number value = dataset.getValue(row, column);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[15]++;
            return;

        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[16]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[38]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[39]++;

        // current data point...
        double x1s = domainAxis.getCategoryStart(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[40]++;
        double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[41]++;
        double x1e = 2 * x1 - x1s;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[42]++; // or: x1s + 2*(x1-x1s)
        double y1 = rangeAxis.valueToJava2D(value.doubleValue(), dataArea, 
                plot.getRangeAxisEdge());
        g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[43]++;
        g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[44]++;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((column != 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[17]++;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[46]++;
            Number previousValue = dataset.getValue(row, column - 1);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((previousValue != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[19]++;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[48]++;
                // previous data point...
                double previous = previousValue.doubleValue();
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[49]++;
                double x0s = domainAxis.getCategoryStart(column - 1, 
                        getColumnCount(), dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[50]++;
                double x0 = domainAxis.getCategoryMiddle(column - 1, 
                        getColumnCount(), dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[51]++;
                double x0e = 2 * x0 - x0s;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[52]++; // or: x0s + 2*(x0-x0s)
                double y0 = rangeAxis.valueToJava2D(previous, dataArea, 
                        plot.getRangeAxisEdge());
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getStagger()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[21]++;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[54]++;
                    int xStagger = row * STAGGER_WIDTH;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[55]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((xStagger > (x1s - x0e)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[23]++;
                        xStagger = (int) (x1s - x0e);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[56]++;

                    } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[24]++;}
                    x1s = x0e + xStagger;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[57]++;

                } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[22]++;}
                drawLine(g2, (State) state, orientation, x0e, y0, x1s, y0);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[58]++; 
                // extend x0's flat bar

                drawLine(g2, (State) state, orientation, x1s, y0, x1s, y1);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[59]++;
 
                // upright bar
           } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[20]++;}

       } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[18]++;}
       drawLine(g2, (State) state, orientation, x1s, y1, x1e, y1);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[60]++;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[61]++;
int CodeCoverConditionCoverageHelper_C13; 
       // x1's flat bar

       // draw the item labels if there are any...
       if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[25]++;
            drawItemLabel(g2, orientation, dataset, row, column, x1, y1, 
                    (value.doubleValue() < 0.0));
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[62]++;

       } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[26]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[63]++;

       // add an item entity, if this information is being collected
       EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
       if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[27]++;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[65]++;
           Rectangle2D hotspot = new Rectangle2D.Double();
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;
           if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[29]++;
               hotspot.setRect(x1s, y1, x1e - x1s, 4.0);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[67]++;

           }
           else {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[30]++;
               hotspot.setRect(y1 - 2.0, x1s, 4.0, x1e - x1s);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[68]++;
           }
           addItemEntity(entities, dataset, row, column, hotspot);
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[69]++;

       } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[28]++;}

    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[70]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[31]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[32]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[71]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryStepRenderer) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[33]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[34]++;}
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[72]++;
        CategoryStepRenderer that = (CategoryStepRenderer) obj;
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.stagger != that.stagger) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[35]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx.branches[36]++;}
        return super.equals(obj);
    }

}

class CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx ());
  }
    public static long[] statements = new long[74];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.CategoryStepRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$32fm7p0jmh74c6ui2skh0ll1j4wbskbmv14cggx () {
    super("org.jfree.chart.renderer.category.CategoryStepRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 73; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.CategoryStepRenderer.java");
      for (int i = 1; i <= 73; i++) {
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
    for (int i = 1; i <= 18; i++) {
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

