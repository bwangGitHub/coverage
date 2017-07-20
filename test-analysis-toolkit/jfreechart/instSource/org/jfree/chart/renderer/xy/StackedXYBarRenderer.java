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
 * StackedXYBarRenderer.java
 * -------------------------
 * (C) Copyright 2004-2007, by Andreas Schroeder and Contributors.
 *
 * Original Author:  Andreas Schroeder;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 01-Apr-2004 : Version 1 (AS);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * 15-Aug-2004 : Added drawBarOutline to control draw/don't-draw bar 
 *               outlines (BN);
 * 10-Sep-2004 : drawBarOutline attribute is now inherited from XYBarRenderer 
 *               and double primitives are retrieved from the dataset rather 
 *               than Number objects (DG);
 * 07-Jan-2005 : Updated for method name change in DatasetUtilities (DG);
 * 25-Jan-2005 : Modified to handle negative values correctly (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Dec-2006 : Added support for GradientPaint (DG);
 * 15-Mar-2007 : Added renderAsPercentages option (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * A bar renderer that displays the series items stacked.
 * The dataset used together with this renderer must be a
 * {@link org.jfree.data.xy.IntervalXYDataset} and a
 * {@link org.jfree.data.xy.TableXYDataset}. For example, the
 * dataset class {@link org.jfree.data.xy.CategoryTableXYDataset}
 * implements both interfaces.
 */
public class StackedXYBarRenderer extends XYBarRenderer {
  static {
    CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.ping();
  }

  
    /** For serialization. */
    private static final long serialVersionUID = -7049101055533436444L;
  static {
    CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[1]++;
  }
    
    /** A flag that controls whether the bars display values or percentages. */
    private boolean renderAsPercentages;

    /**
     * Creates a new renderer.
     */
    public StackedXYBarRenderer() {
        this(0.0);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[2]++;
    }

    /**
     * Creates a new renderer.
     *
     * @param margin  the percentual amount of the bars that are cut away.
     */
    public StackedXYBarRenderer(double margin) {
        super(margin);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[3]++;
        this.renderAsPercentages = false;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[4]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[5]++;
        
        // set the default item label positions, which will only be used if 
        // the user requests visible item labels...
        ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, 
                TextAnchor.CENTER);
        setBasePositiveItemLabelPosition(p);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[6]++;
        setBaseNegativeItemLabelPosition(p);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[7]++;
        setPositiveItemLabelPositionFallback(null);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[8]++;
        setNegativeItemLabelPositionFallback(null);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[9]++;
    }

    /**
     * Returns <code>true</code> if the renderer displays each item value as
     * a percentage (so that the stacked bars add to 100%), and 
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #setRenderAsPercentages(boolean)
     * 
     * @since 1.0.5
     */
    public boolean getRenderAsPercentages() {
        return this.renderAsPercentages;   
    }
    
    /**
     * Sets the flag that controls whether the renderer displays each item
     * value as a percentage (so that the stacked bars add to 100%), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param asPercentages  the flag.
     * 
     * @see #getRenderAsPercentages()
     * 
     * @since 1.0.5
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        this.renderAsPercentages = asPercentages;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[10]++; 
        fireChangeEvent();
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[11]++;
    }

    /**
     * Returns <code>2</code> to indicate that this renderer requires two 
     * passes for drawing (item labels are drawn in the second pass so that 
     * they always appear in front of all the bars).
     * 
     * @return <code>2</code>.
     */
    public int getPassCount() {
        return 2;
    }

    /**
     * Initialises the renderer and returns a state object that should be 
     * passed to all subsequent calls to the drawItem() method. Here there is 
     * nothing to do.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to
     *              the caller.
     *
     * @return A state object.
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset data,
                                          PlotRenderingInfo info) {
        return new XYBarRendererState(info);
    }

    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (<code>null</code> if the dataset is <code>null</code>
     *         or empty).
     */
    public Range findRangeBounds(XYDataset dataset) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[1]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[3]++;
                return new Range(0.0, 1.0);

            }
            else {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[4]++;
                return DatasetUtilities.findStackedRangeBounds(
                        (TableXYDataset) dataset);
            }

        }
        else {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[2]++;
            return null;
        }
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
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
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((dataset instanceof IntervalXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset instanceof TableXYDataset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[5]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[15]++;
            String message = "dataset (type " + dataset.getClass().getName() 
                + ") has wrong type:";
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[16]++;
            boolean and = false;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((IntervalXYDataset.class.isAssignableFrom(dataset.getClass())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[7]++;
                message += " it is no IntervalXYDataset";
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[18]++;
                and = true;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[19]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[8]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((TableXYDataset.class.isAssignableFrom(dataset.getClass())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[9]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((and) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[11]++;
                    message += " and";
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[22]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[12]++;}
                message += " it is no TableXYDataset";
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[23]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[10]++;}

            throw new IllegalArgumentException(message);

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[6]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[24]++;

        IntervalXYDataset intervalDataset = (IntervalXYDataset) dataset;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[25]++;
        double value = intervalDataset.getYValue(series, item);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[13]++;
            return;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[14]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[27]++;
        
        // if we are rendering the values as percentages, we need to calculate
        // the total for the current item.  Unfortunately here we end up 
        // repeating the calculation more times than is strictly necessary -
        // hopefully I'll come back to this and find a way to add the 
        // total(s) to the renderer state.  The other problem is we implicitly
        // assume the dataset has no negative values...perhaps that can be
        // fixed too.
        double total = 0.0;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;  
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[15]++;
            total = DatasetUtilities.calculateStackTotal(
                    (TableXYDataset) dataset, item);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[29]++;
            value = value / total;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[30]++;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[16]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[31]++;
        
        double positiveBase = 0.0;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[32]++;
        double negativeBase = 0.0;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[33]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
        
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < series) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.loops[1]--;
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.loops[2]--;
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.loops[3]++;
}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[34]++;
            double v = dataset.getYValue(i, item);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((Double.isNaN(v)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[17]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[19]++;
                    v = v / total;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[37]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[20]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((v > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[21]++;
                    positiveBase = positiveBase + v;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[39]++;

                }
                else {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[22]++;
                    negativeBase = negativeBase + v;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[40]++;
                }

            } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[18]++;}
        }

        double translatedBase;
        double translatedValue;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[41]++;
        RectangleEdge edgeR = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[23]++;
            translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, 
                    edgeR);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[43]++;
            translatedValue = rangeAxis.valueToJava2D(positiveBase + value, 
                    dataArea, edgeR);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[44]++;

        }
        else {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[24]++;
            translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, 
                    edgeR);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[45]++;
            translatedValue = rangeAxis.valueToJava2D(negativeBase + value, 
                    dataArea, edgeR);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[46]++;
        }
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[47]++;

        RectangleEdge edgeD = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[48]++;
        double startX = intervalDataset.getStartXValue(series, item);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[49]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Double.isNaN(startX)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[25]++;
            return;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[26]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[50]++;
        double translatedStartX = domainAxis.valueToJava2D(startX, dataArea, 
                edgeD);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[51]++;

        double endX = intervalDataset.getEndXValue(series, item);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((Double.isNaN(endX)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[27]++;
            return;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[28]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[53]++;
        double translatedEndX = domainAxis.valueToJava2D(endX, dataArea, edgeD);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[54]++;

        double translatedWidth = Math.max(1, Math.abs(translatedEndX 
                - translatedStartX));
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[55]++;
        double translatedHeight = Math.abs(translatedValue - translatedBase);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getMargin() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[29]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[57]++;
            double cut = translatedWidth * getMargin();
            translatedWidth = translatedWidth - cut;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[58]++;
            translatedStartX = translatedStartX + cut / 2;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[59]++;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[30]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[60]++;

        Rectangle2D bar = null;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[61]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[31]++;
            bar = new Rectangle2D.Double(Math.min(translatedBase, 
                    translatedValue), translatedEndX, translatedHeight,
                    translatedWidth);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[63]++;

        }
        else {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[32]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[64]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[33]++;
            bar = new Rectangle2D.Double(translatedStartX,
                    Math.min(translatedBase, translatedValue),
                    translatedWidth, translatedHeight);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[65]++;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[34]++;}
}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[66]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[35]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[67]++;
            Paint itemPaint = getItemPaint(series, item);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[68]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((getGradientPaintTransformer() 
                    != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[37]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[69]++;
                GradientPaint gp = (GradientPaint) itemPaint;
                itemPaint = getGradientPaintTransformer().transform(gp, bar);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[70]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[38]++;}
            g2.setPaint(itemPaint);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[71]++;
            g2.fill(bar);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[72]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[73]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((Math.abs(translatedEndX - translatedStartX) > 3) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[39]++;
                g2.setStroke(getItemStroke(series, item));
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[74]++;
                g2.setPaint(getItemOutlinePaint(series, item));
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[75]++;
                g2.draw(bar);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[76]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[40]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[77]++;
int CodeCoverConditionCoverageHelper_C22;

            // add an entity for the item...
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[41]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[78]++;
                EntityCollection entities = info.getOwner()
                        .getEntityCollection();
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[79]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[43]++;
                    addEntity(entities, bar, dataset, series, item, 
                            bar.getCenterX(), bar.getCenterY());
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[80]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[44]++;}

            } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[42]++;}

        }
        else {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[36]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[81]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((pass == 1) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[45]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[82]++;
int CodeCoverConditionCoverageHelper_C25;
            // handle item label drawing, now that we know all the bars have
            // been drawn...
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, item)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[47]++;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[83]++;
                XYItemLabelGenerator generator = getItemLabelGenerator(series, 
                        item);
                drawItemLabel(g2, dataset, series, item, plot, generator, bar, 
                        value < 0.0);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[84]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[48]++;}

        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[46]++;}
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
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[85]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[49]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[50]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[86]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj instanceof StackedXYBarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[51]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[52]++;}
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[87]++;
        StackedXYBarRenderer that = (StackedXYBarRenderer) obj;
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[88]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages != that.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[53]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.branches[54]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[89]++;
        int result = super.hashCode();
        result = result * 37 + (this.renderAsPercentages ? 1 : 0);
CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh.statements[90]++;
        return result;
    }
    
}

class CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh ());
  }
    public static long[] statements = new long[91];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.StackedXYBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 28; i++) {
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

  public CodeCoverCoverageCounter$3ss0l1z37922hh12w0ps1zt5cpo02yqvi5p0xgh () {
    super("org.jfree.chart.renderer.xy.StackedXYBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 90; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 54; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.StackedXYBarRenderer.java");
      for (int i = 1; i <= 90; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 54; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
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

