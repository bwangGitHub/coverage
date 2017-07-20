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
 * ----------------
 * BarRenderer.java
 * ----------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 14-Mar-2002 : Version 1 (DG);
 * 23-May-2002 : Added tooltip generator to renderer (DG);
 * 29-May-2002 : Moved tooltip generator to abstract super-class (DG);
 * 25-Jun-2002 : Changed constructor to protected and removed redundant 
 *               code (DG);
 * 26-Jun-2002 : Added axis to initialise method, and record upper and lower 
 *               clip values (DG);
 * 24-Sep-2002 : Added getLegendItem() method (DG);
 * 09-Oct-2002 : Modified constructor to include URL generator (DG);
 * 05-Nov-2002 : Base dataset is now TableDataset not CategoryDataset (DG);
 * 10-Jan-2003 : Moved get/setItemMargin() method up from subclasses (DG);
 * 17-Jan-2003 : Moved plot classes into a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified clipping to allow for dual axes and datasets (DG);
 * 12-May-2003 : Merged horizontal and vertical bar renderers (DG);
 * 12-Jun-2003 : Updates for item labels (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 02-Sep-2003 : Changed initialise method to fix bug 790407 (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 27-Oct-2003 : Merged drawHorizontalItem() and drawVerticalItem() 
 *               methods (DG);
 * 28-Oct-2003 : Added support for gradient paint on bars (DG);
 * 14-Nov-2003 : Added 'maxBarWidth' attribute (DG);
 * 10-Feb-2004 : Small changes inside drawItem() method to ease cut-and-paste 
 *               overriding (DG);
 * 19-Mar-2004 : Fixed bug introduced with separation of tool tip and item 
 *               label generators.  Fixed equals() method (DG);
 * 11-May-2004 : Fix for null pointer exception (bug id 951127) (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 26-Jan-2005 : Provided override for getLegendItem() method (DG);
 * 20-Apr-2005 : Generate legend labels, tooltips and URLs (DG);
 * 18-May-2005 : Added configurable base value (DG);
 * 09-Jun-2005 : Use addItemEntity() method from superclass (DG);
 * 01-Dec-2005 : Update legend item to use/not use outline (DG);
 * ------------: JFreeChart 1.0.x ---------------------------------------------
 * 06-Dec-2005 : Fixed bug 1374222 (JDK 1.4 specific code) (DG);
 * 11-Jan-2006 : Fixed bug 1401856 (bad rendering for non-zero base) (DG);
 * 04-Aug-2006 : Fixed bug 1467706 (missing item labels for zero value 
 *               bars) (DG);
 * 04-Dec-2006 : Fixed bug in rendering to non-primary axis (DG);
 * 13-Dec-2006 : Add support for GradientPaint display in legend items (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 11-May-2007 : Check for visibility in getLegendItem() (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A {@link CategoryItemRenderer} that draws individual data items as bars.
 */
public class BarRenderer extends AbstractCategoryItemRenderer 
                         implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 6000649414965887481L;
  static {
    CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[1]++;
  }
    
    /** The default item margin percentage. */
    public static final double DEFAULT_ITEM_MARGIN = 0.20;
  static {
    CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[2]++;
  }

    /** 
     * Constant that controls the minimum width before a bar has an outline 
     * drawn. 
     */
    public static final double BAR_OUTLINE_WIDTH_THRESHOLD = 3.0;
  static {
    CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[3]++;
  }

    /** The margin between items (bars) within a category. */
    private double itemMargin;

    /** A flag that controls whether or not bar outlines are drawn. */
    private boolean drawBarOutline;
    
    /** The maximum bar width as a percentage of the available space. */
    private double maximumBarWidth;
    
    /** The minimum bar length (in Java2D units). */
    private double minimumBarLength;
    
    /** 
     * An optional class used to transform gradient paint objects to fit each 
     * bar. 
     */
    private GradientPaintTransformer gradientPaintTransformer;
    
    /** 
     * The fallback position if a positive item label doesn't fit inside the 
     * bar. 
     */
    private ItemLabelPosition positiveItemLabelPositionFallback;
    
    /** 
     * The fallback position if a negative item label doesn't fit inside the 
     * bar. 
     */
    private ItemLabelPosition negativeItemLabelPositionFallback;
    
    /** The upper clip (axis) value for the axis. */
    private double upperClip;  
    // TODO:  this needs to move into the renderer state

    /** The lower clip (axis) value for the axis. */
    private double lowerClip;  
    // TODO:  this needs to move into the renderer state

    /** The base value for the bars (defaults to 0.0). */
    private double base;
    
    /** 
     * A flag that controls whether the base value is included in the range
     * returned by the findRangeBounds() method.
     */
    private boolean includeBaseInRange;
    
    /**
     * Creates a new bar renderer with default settings.
     */
    public BarRenderer() {
        super();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[4]++;
        this.base = 0.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[5]++;
        this.includeBaseInRange = true;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[6]++;
        this.itemMargin = DEFAULT_ITEM_MARGIN;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[7]++;
        this.drawBarOutline = false;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[8]++;
        this.maximumBarWidth = 1.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[9]++;  
            // 100 percent, so it will not apply unless changed
        this.positiveItemLabelPositionFallback = null;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[10]++;
        this.negativeItemLabelPositionFallback = null;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[11]++;
        this.gradientPaintTransformer = new StandardGradientPaintTransformer();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[12]++;
        this.minimumBarLength = 0.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[13]++;
    }

    /**
     * Returns the base value for the bars.  The default value is 
     * <code>0.0</code>.
     * 
     * @return The base value for the bars.
     * 
     * @see #setBase(double)
     */
    public double getBase() {
        return this.base;    
    }
    
    /**
     * Sets the base value for the bars and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     * 
     * @param base  the new base value.
     * 
     * @see #getBase()
     */
    public void setBase(double base) {
        this.base = base;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[14]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[15]++;
    }
    
    /**
     * Returns the item margin as a percentage of the available space for all 
     * bars.
     *
     * @return The margin percentage (where 0.10 is ten percent).
     * 
     * @see #setItemMargin(double)
     */
    public double getItemMargin() {
        return this.itemMargin;
    }

    /**
     * Sets the item margin and sends a {@link RendererChangeEvent} to all 
     * registered listeners.  The value is expressed as a percentage of the 
     * available width for plotting all the bars, with the resulting amount to 
     * be distributed between all the bars evenly.
     *
     * @param percent  the margin (where 0.10 is ten percent).
     * 
     * @see #getItemMargin()
     */
    public void setItemMargin(double percent) {
        this.itemMargin = percent;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[16]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[17]++;
    }

    /**
     * Returns a flag that controls whether or not bar outlines are drawn.
     * 
     * @return A boolean.
     * 
     * @see #setDrawBarOutline(boolean)
     */
    public boolean isDrawBarOutline() {
        return this.drawBarOutline;    
    }
    
    /**
     * Sets the flag that controls whether or not bar outlines are drawn and 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param draw  the flag.
     * 
     * @see #isDrawBarOutline()
     */
    public void setDrawBarOutline(boolean draw) {
        this.drawBarOutline = draw;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[18]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[19]++;
    }
    
    /**
     * Returns the maximum bar width, as a percentage of the available drawing 
     * space.
     * 
     * @return The maximum bar width.
     * 
     * @see #setMaximumBarWidth(double)
     */
    public double getMaximumBarWidth() {
        return this.maximumBarWidth;
    }
    
    /**
     * Sets the maximum bar width, which is specified as a percentage of the 
     * available space for all bars, and sends a {@link RendererChangeEvent} to
     * all registered listeners.
     * 
     * @param percent  the percent (where 0.05 is five percent).
     * 
     * @see #getMaximumBarWidth()
     */
    public void setMaximumBarWidth(double percent) {
        this.maximumBarWidth = percent;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[20]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[21]++;
    }

    /**
     * Returns the minimum bar length (in Java2D units).
     * 
     * @return The minimum bar length.
     * 
     * @see #setMinimumBarLength(double)
     */
    public double getMinimumBarLength() {
        return this.minimumBarLength;
    }
    
    /**
     * Sets the minimum bar length and sends a {@link RendererChangeEvent} to 
     * all registered listeners.  The minimum bar length is specified in Java2D
     * units, and can be used to prevent bars that represent very small data 
     * values from disappearing when drawn on the screen.
     * 
     * @param min  the minimum bar length (in Java2D units).
     * 
     * @see #getMinimumBarLength()
     */
    public void setMinimumBarLength(double min) {
        this.minimumBarLength = min;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[22]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[23]++;
    }
    
    /**
     * Returns the gradient paint transformer (an object used to transform 
     * gradient paint objects to fit each bar).
     * 
     * @return A transformer (<code>null</code> possible).
     * 
     * @see #setGradientPaintTransformer(GradientPaintTransformer)
     */    
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;    
    }
    
    /**
     * Sets the gradient paint transformer and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param transformer  the transformer (<code>null</code> permitted).
     * 
     * @see #getGradientPaintTransformer()
     */
    public void setGradientPaintTransformer(
            GradientPaintTransformer transformer) {
        this.gradientPaintTransformer = transformer;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[24]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[25]++;
    }
    
    /**
     * Returns the fallback position for positive item labels that don't fit 
     * within a bar.
     * 
     * @return The fallback position (<code>null</code> possible).
     * 
     * @see #setPositiveItemLabelPositionFallback(ItemLabelPosition)
     */
    public ItemLabelPosition getPositiveItemLabelPositionFallback() {
        return this.positiveItemLabelPositionFallback;
    }
    
    /**
     * Sets the fallback position for positive item labels that don't fit 
     * within a bar, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getPositiveItemLabelPositionFallback()
     */
    public void setPositiveItemLabelPositionFallback(
            ItemLabelPosition position) {
        this.positiveItemLabelPositionFallback = position;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[26]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[27]++;
    }
    
    /**
     * Returns the fallback position for negative item labels that don't fit 
     * within a bar.
     * 
     * @return The fallback position (<code>null</code> possible).
     * 
     * @see #setPositiveItemLabelPositionFallback(ItemLabelPosition)
     */
    public ItemLabelPosition getNegativeItemLabelPositionFallback() {
        return this.negativeItemLabelPositionFallback;
    }
    
    /**
     * Sets the fallback position for negative item labels that don't fit 
     * within a bar, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     * 
     * @param position  the position (<code>null</code> permitted).
     * 
     * @see #getNegativeItemLabelPositionFallback()
     */
    public void setNegativeItemLabelPositionFallback(
            ItemLabelPosition position) {
        this.negativeItemLabelPositionFallback = position;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[28]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[29]++;
    }
    
    /**
     * Returns the flag that controls whether or not the base value for the 
     * bars is included in the range calculated by 
     * {@link #findRangeBounds(CategoryDataset)}.
     * 
     * @return <code>true</code> if the base is included in the range, and
     *         <code>false</code> otherwise.
     * 
     * @since 1.0.1
     * 
     * @see #setIncludeBaseInRange(boolean)
     */
    public boolean getIncludeBaseInRange() {
        return this.includeBaseInRange;
    }
    
    /**
     * Sets the flag that controls whether or not the base value for the bars 
     * is included in the range calculated by 
     * {@link #findRangeBounds(CategoryDataset)}.  If the flag is changed,
     * a {@link RendererChangeEvent} is sent to all registered listeners.
     * 
     * @param include  the new value for the flag.
     * 
     * @since 1.0.1
     * 
     * @see #getIncludeBaseInRange()
     */
    public void setIncludeBaseInRange(boolean include) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[30]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.includeBaseInRange != include) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[1]++;
            this.includeBaseInRange = include;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[31]++;
            fireChangeEvent();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[32]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[2]++;}
    }
    
    /**
     * Returns the lower clip value.  This value is recalculated in the 
     * initialise() method.
     *
     * @return The value.
     */
    public double getLowerClip() {
        // TODO:  this attribute should be transferred to the renderer state.
        return this.lowerClip;
    }

    /**
     * Returns the upper clip value.  This value is recalculated in the 
     * initialise() method.
     *
     * @return The value.
     */
    public double getUpperClip() {
        // TODO:  this attribute should be transferred to the renderer state.
        return this.upperClip;
    }

    /**
     * Initialises the renderer and returns a state object that will be passed 
     * to subsequent calls to the drawItem method.  This method gets called 
     * once at the start of the process of drawing a chart.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data is to be plotted.
     * @param plot  the plot.
     * @param rendererIndex  the renderer index.
     * @param info  collects chart rendering information for return to caller.
     * 
     * @return The renderer state.
     */
    public CategoryItemRendererState initialise(Graphics2D g2,
                                                Rectangle2D dataArea,
                                                CategoryPlot plot,
                                                int rendererIndex,
                                                PlotRenderingInfo info) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[33]++;

        CategoryItemRendererState state = super.initialise(g2, dataArea, plot, 
                rendererIndex, info);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[34]++;

        // get the clipping values...
        ValueAxis rangeAxis = plot.getRangeAxisForDataset(rendererIndex);
        this.lowerClip = rangeAxis.getRange().getLowerBound();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[35]++;
        this.upperClip = rangeAxis.getRange().getUpperBound();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[36]++;

        // calculate the bar width
        calculateBarWidth(plot, dataArea, rendererIndex, state);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[37]++;

        return state;
        
    }
    
    /**
     * Calculates the bar width and stores it in the renderer state.
     * 
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param rendererIndex  the renderer index.
     * @param state  the renderer state.
     */
    protected void calculateBarWidth(CategoryPlot plot, 
                                     Rectangle2D dataArea, 
                                     int rendererIndex,
                                     CategoryItemRendererState state) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[38]++;
                                         
        CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[39]++;
        CategoryDataset dataset = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[40]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[3]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[41]++;
            int columns = dataset.getColumnCount();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[42]++;
            int rows = dataset.getRowCount();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[43]++;
            double space = 0.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[44]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[45]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[5]++;
                space = dataArea.getHeight();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[46]++;

            }
            else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[6]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[47]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[7]++;
                space = dataArea.getWidth();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[48]++;

            } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[8]++;}
}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[49]++;
            double maxWidth = space * getMaximumBarWidth();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[50]++;
            double categoryMargin = 0.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[51]++;
            double currentItemMargin = 0.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[52]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((columns > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[9]++;
                categoryMargin = domainAxis.getCategoryMargin();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[53]++;

            } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[10]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[54]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rows > 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[11]++;
                currentItemMargin = getItemMargin();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[55]++;

            } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[12]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[56]++;
            double used = space * (1 - domainAxis.getLowerMargin() 
                                     - domainAxis.getUpperMargin()
                                     - categoryMargin - currentItemMargin);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[57]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 (((rows * columns) > 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[13]++;
                state.setBarWidth(Math.min(used / (rows * columns), maxWidth));
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[58]++;

            }
            else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[14]++;
                state.setBarWidth(Math.min(used, maxWidth));
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[59]++;
            }

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[4]++;}
    }

    /**
     * Calculates the coordinate of the first "side" of a bar.  This will be 
     * the minimum x-coordinate for a vertical bar, and the minimum 
     * y-coordinate for a horizontal bar.
     *
     * @param plot  the plot.
     * @param orientation  the plot orientation.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param state  the renderer state (has the bar width precalculated).
     * @param row  the row index.
     * @param column  the column index.
     * 
     * @return The coordinate.
     */
    protected double calculateBarW0(CategoryPlot plot, 
                                    PlotOrientation orientation, 
                                    Rectangle2D dataArea,
                                    CategoryAxis domainAxis,
                                    CategoryItemRendererState state,
                                    int row,
                                    int column) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[60]++;
        // calculate bar width...
        double space = 0.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[61]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[15]++;
            space = dataArea.getHeight();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[62]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[16]++;
            space = dataArea.getWidth();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[63]++;
        }
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[64]++;
        double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[65]++;
        int seriesCount = getRowCount();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[66]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[67]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[17]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[68]++;
            double seriesGap = space * getItemMargin() 
                               / (categoryCount * (seriesCount - 1));
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[69]++;
            double seriesW = calculateSeriesWidth(space, domainAxis, 
                    categoryCount, seriesCount);
            barW0 = barW0 + row * (seriesW + seriesGap) 
                          + (seriesW / 2.0) - (state.getBarWidth() / 2.0);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[70]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[18]++;
            barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                    dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() 
                    / 2.0;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[71]++;
        }
        return barW0;
    }
    
    /**
     * Calculates the coordinates for the length of a single bar.
     * 
     * @param value  the value represented by the bar.
     * 
     * @return The coordinates for each end of the bar (or <code>null</code> if 
     *         the bar is not visible for the current axis range).
     */
    protected double[] calculateBarL0L1(double value) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[72]++;
        double lclip = getLowerClip();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[73]++;
        double uclip = getUpperClip();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[74]++;
        double barLow = Math.min(this.base, value);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[75]++;
        double barHigh = Math.max(this.base, value);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[76]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((barHigh < lclip) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[19]++;  // bar is not visible
            return null;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[20]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[77]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((barLow > uclip) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[21]++;   // bar is not visible
            return null;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[22]++;}
        barLow = Math.max(barLow, lclip);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[78]++;
        barHigh = Math.min(barHigh, uclip);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[79]++;
        return new double[] {barLow, barHigh};
    }

    /**
     * Returns the range of values the renderer requires to display all the 
     * items from the specified dataset.  This takes into account the range
     * of values in the dataset, plus the flag that determines whether or not
     * the base value for the bars should be included in the range.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (or <code>null</code> if the dataset is 
     *         <code>null</code> or empty).
     */
    public Range findRangeBounds(CategoryDataset dataset) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[80]++;
        Range result = DatasetUtilities.findRangeBounds(dataset);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[81]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[23]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[82]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.includeBaseInRange) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[25]++;
                result = Range.expandToInclude(result, this.base);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[83]++;

            } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[24]++;}
        return result;
    }

    /**
     * Returns a legend item for a series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return The legend item (possibly <code>null</code>).
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[84]++;

        CategoryPlot cp = getPlot();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[85]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((cp == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[27]++;
            return null;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[28]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[86]++;
int CodeCoverConditionCoverageHelper_C15;

        // check that a legend item needs to be displayed...
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((isSeriesVisible(series)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(series)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[29]++;
            return null;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[30]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[87]++;

        CategoryDataset dataset = cp.getDataset(datasetIndex);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[88]++;
        String label = getLegendItemLabelGenerator().generateLabel(dataset, 
                series);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[89]++;
        String description = label;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[90]++;
        String toolTipText = null;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[91]++;
int CodeCoverConditionCoverageHelper_C16; 
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[31]++;
            toolTipText = getLegendItemToolTipGenerator().generateLabel(
                    dataset, series);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[92]++;
   
        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[32]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[93]++;
        String urlText = null;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[94]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[33]++;
            urlText = getLegendItemURLGenerator().generateLabel(dataset, 
                    series);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[95]++;
   
        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[34]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[96]++;
        Shape shape = new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[97]++;
        Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[98]++;
        Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[99]++;
        Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[100]++;

        LegendItem result = new LegendItem(label, description, toolTipText, 
                urlText, true, shape, true, paint, isDrawBarOutline(), 
                outlinePaint, outlineStroke, false, new Line2D.Float(), 
                new BasicStroke(1.0f), Color.black);
        result.setDataset(dataset);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[101]++;
        result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[102]++;
        result.setSeriesKey(dataset.getRowKey(series));
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[103]++;
        result.setSeriesIndex(series);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[104]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[105]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.gradientPaintTransformer != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[35]++;
            result.setFillPaintTransformer(this.gradientPaintTransformer);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[106]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[36]++;}
        return result;
    }

    /**
     * Draws the bar for a single (series, category) data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data area.
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
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[107]++;

        // nothing is drawn for null values...
        Number dataValue = dataset.getValue(row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[108]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[37]++;
            return;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[38]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[109]++;
        
        double value = dataValue.doubleValue();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[110]++;
        
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[111]++;
        double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, 
                state, row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[112]++;
        double[] barL0L1 = calculateBarL0L1(value);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[113]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((barL0L1 == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[39]++;
            return;
  // the bar is not visible
        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[40]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[114]++;
        
        RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[115]++;
        double transL0 = rangeAxis.valueToJava2D(barL0L1[0], dataArea, edge);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[116]++;
        double transL1 = rangeAxis.valueToJava2D(barL0L1[1], dataArea, edge);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[117]++;
        double barL0 = Math.min(transL0, transL1);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[118]++;
        double barLength = Math.max(Math.abs(transL1 - transL0), 
                getMinimumBarLength());
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[119]++;

        // draw the bar...
        Rectangle2D bar = null;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[120]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[41]++;
            bar = new Rectangle2D.Double(barL0, barW0, barLength, 
                    state.getBarWidth());
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[121]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[42]++;
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), 
                    barLength);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[122]++;
        }
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[123]++;
        Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[124]++;
        GradientPaintTransformer t = getGradientPaintTransformer();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[125]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[43]++;
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[126]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[44]++;}
        g2.setPaint(itemPaint);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[127]++;
        g2.fill(bar);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[128]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[129]++;
int CodeCoverConditionCoverageHelper_C23;

        // draw the outline...
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[45]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[130]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[131]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[132]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[47]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[133]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[134]++;
                g2.draw(bar);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[135]++;

            } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[48]++;}

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[46]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[136]++;

        CategoryItemLabelGenerator generator 
            = getItemLabelGenerator(row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[137]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[49]++;
            drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                    (value < 0.0));
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[138]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[50]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[139]++;        

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[140]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[51]++;
            addItemEntity(entities, dataset, row, column, bar);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[141]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[52]++;}

    }

    /**
     * Calculates the available space for each series.
     * 
     * @param space  the space along the entire axis (in Java2D units).
     * @param axis  the category axis.
     * @param categories  the number of categories.
     * @param series  the number of series.
     * 
     * @return The width of one series.
     */
    protected double calculateSeriesWidth(double space, CategoryAxis axis, 
                                          int categories, int series) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[142]++;
        double factor = 1.0 - getItemMargin() - axis.getLowerMargin() 
                            - axis.getUpperMargin();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[143]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((categories > 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[53]++;
            factor = factor - axis.getCategoryMargin();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[144]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[54]++;}
        return (space * factor) / (categories * series);
    }
    
    /**
     * Draws an item label.  This method is overridden so that the bar can be 
     * used to calculate the label anchor point.
     * 
     * @param g2  the graphics device.
     * @param data  the dataset.
     * @param row  the row.
     * @param column  the column.
     * @param plot  the plot.
     * @param generator  the label generator.
     * @param bar  the bar.
     * @param negative  a flag indicating a negative value.
     */
    protected void drawItemLabel(Graphics2D g2,
                                 CategoryDataset data,
                                 int row,
                                 int column,
                                 CategoryPlot plot,
                                 CategoryItemLabelGenerator generator,
                                 Rectangle2D bar,
                                 boolean negative) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[145]++;
                                     
        String label = generator.generateLabel(data, row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[146]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[55]++;
            return;
  // nothing to do   
        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[56]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[147]++;
        
        Font labelFont = getItemLabelFont(row, column);
        g2.setFont(labelFont);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[148]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[149]++;
        Paint paint = getItemLabelPaint(row, column);
        g2.setPaint(paint);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[150]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[151]++;

        // find out where to place the label...
        ItemLabelPosition position = null;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[152]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[57]++;
            position = getPositiveItemLabelPosition(row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[153]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[58]++;
            position = getNegativeItemLabelPosition(row, column);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[154]++;
        }
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[155]++;

        // work out the label anchor point...
        Point2D anchorPoint = calculateLabelAnchorPoint(
                position.getItemLabelAnchor(), bar, plot.getOrientation());
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[156]++;
int CodeCoverConditionCoverageHelper_C30;
        
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isInternalAnchor(position.getItemLabelAnchor())) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[59]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[157]++;
            Shape bounds = TextUtilities.calculateRotatedStringBounds(label, 
                    g2, (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(), position.getAngle(),
                    position.getRotationAnchor());
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[158]++;
int CodeCoverConditionCoverageHelper_C31;
            
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((bounds != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[61]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[159]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((bar.contains(bounds.getBounds2D())) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[63]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[160]++;
int CodeCoverConditionCoverageHelper_C33;
                    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[65]++;
                        position = getPositiveItemLabelPositionFallback();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[161]++;

                    }
                    else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[66]++;
                        position = getNegativeItemLabelPositionFallback();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[162]++;
                    }
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[163]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((position != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[67]++;
                        anchorPoint = calculateLabelAnchorPoint(
                                position.getItemLabelAnchor(), bar, 
                                plot.getOrientation());
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[164]++;

                    } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[68]++;}

                } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[64]++;}

            } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[62]++;}

        
        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[60]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[165]++;
int CodeCoverConditionCoverageHelper_C35;
        
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((position != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[69]++;
            TextUtilities.drawRotatedString(label, g2, 
                    (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(), position.getAngle(), 
                    position.getRotationAnchor());
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[166]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[70]++;}        
    }
    
    /**
     * Calculates the item label anchor point.
     *
     * @param anchor  the anchor.
     * @param bar  the bar.
     * @param orientation  the plot orientation.
     *
     * @return The anchor point.
     */
    private Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor,
                                              Rectangle2D bar, 
                                              PlotOrientation orientation) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[167]++;

        Point2D result = null;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[168]++;
        double offset = getItemLabelAnchorOffset();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[169]++;
        double x0 = bar.getX() - offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[170]++;
        double x1 = bar.getX();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[171]++;
        double x2 = bar.getX() + offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[172]++;
        double x3 = bar.getCenterX();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[173]++;
        double x4 = bar.getMaxX() - offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[174]++;
        double x5 = bar.getMaxX();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[175]++;
        double x6 = bar.getMaxX() + offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[176]++;

        double y0 = bar.getMaxY() + offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[177]++;
        double y1 = bar.getMaxY();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[178]++;
        double y2 = bar.getMaxY() - offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[179]++;
        double y3 = bar.getCenterY();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[180]++;
        double y4 = bar.getMinY() + offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[181]++;
        double y5 = bar.getMinY();
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[182]++;
        double y6 = bar.getMinY() - offset;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[183]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[71]++;
            result = new Point2D.Double(x3, y3);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[184]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[72]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[185]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[73]++;
            result = new Point2D.Double(x4, y4);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[186]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[74]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[187]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE2) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[75]++;
            result = new Point2D.Double(x4, y4);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[188]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[76]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[189]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE3) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[77]++;
            result = new Point2D.Double(x4, y3);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[190]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[78]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[191]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE4) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[79]++;
            result = new Point2D.Double(x4, y2);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[192]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[80]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[193]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE5) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[81]++;
            result = new Point2D.Double(x4, y2);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[194]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[82]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[195]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE6) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[83]++;
            result = new Point2D.Double(x3, y2);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[196]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[84]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[197]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE7) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[85]++;
            result = new Point2D.Double(x2, y2);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[198]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[86]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[199]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE8) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[87]++;
            result = new Point2D.Double(x2, y2);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[200]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[88]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[201]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE9) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[89]++;
            result = new Point2D.Double(x2, y3);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[202]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[90]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[203]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE10) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[91]++;
            result = new Point2D.Double(x2, y4);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[204]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[92]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[205]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE11) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[93]++;
            result = new Point2D.Double(x2, y4);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[206]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[94]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[207]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.INSIDE12) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[95]++;
            result = new Point2D.Double(x3, y4);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[208]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[96]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[209]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE1) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[97]++;
            result = new Point2D.Double(x5, y6);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[210]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[98]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[211]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE2) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[99]++;
            result = new Point2D.Double(x6, y5);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[212]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[100]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[213]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE3) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[101]++;
            result = new Point2D.Double(x6, y3);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[214]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[102]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[215]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE4) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[103]++;
            result = new Point2D.Double(x6, y1);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[216]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[104]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[217]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE5) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[105]++;
            result = new Point2D.Double(x5, y0);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[218]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[106]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[219]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE6) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[107]++;
            result = new Point2D.Double(x3, y0);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[220]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[108]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[221]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE7) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[109]++;
            result = new Point2D.Double(x1, y0);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[222]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[110]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[223]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE8) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[111]++;
            result = new Point2D.Double(x0, y1);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[224]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[112]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[225]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE9) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[113]++;
            result = new Point2D.Double(x0, y3);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[226]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[114]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[227]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE10) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[115]++;
            result = new Point2D.Double(x0, y5);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[228]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[116]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[229]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE11) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[117]++;
            result = new Point2D.Double(x1, y6);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[230]++;

        }
        else {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[118]++;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[231]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((anchor == ItemLabelAnchor.OUTSIDE12) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[119]++;
            result = new Point2D.Double(x3, y6);
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[232]++;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[120]++;}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}

        return result;

    }
    
    /**
     * Returns <code>true</code> if the specified anchor point is inside a bar.
     * 
     * @param anchor  the anchor point.
     * 
     * @return A boolean.
     */
    private boolean isInternalAnchor(ItemLabelAnchor anchor) {
        return anchor == ItemLabelAnchor.CENTER 
               || anchor == ItemLabelAnchor.INSIDE1
               || anchor == ItemLabelAnchor.INSIDE2
               || anchor == ItemLabelAnchor.INSIDE3
               || anchor == ItemLabelAnchor.INSIDE4
               || anchor == ItemLabelAnchor.INSIDE5
               || anchor == ItemLabelAnchor.INSIDE6
               || anchor == ItemLabelAnchor.INSIDE7
               || anchor == ItemLabelAnchor.INSIDE8
               || anchor == ItemLabelAnchor.INSIDE9
               || anchor == ItemLabelAnchor.INSIDE10
               || anchor == ItemLabelAnchor.INSIDE11
               || anchor == ItemLabelAnchor.INSIDE12;  
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[233]++;
int CodeCoverConditionCoverageHelper_C61;
        
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[121]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[122]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[234]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((obj instanceof BarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[123]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[124]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[235]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[125]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[126]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[236]++;
        BarRenderer that = (BarRenderer) obj;
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[237]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((this.base != that.base) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[127]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[128]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[238]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this.itemMargin != that.itemMargin) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[129]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[130]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[239]++;
int CodeCoverConditionCoverageHelper_C66;              
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((this.drawBarOutline != that.drawBarOutline) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[131]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[132]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[240]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.maximumBarWidth != that.maximumBarWidth) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[133]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[134]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[241]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.minimumBarLength != that.minimumBarLength) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[135]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[136]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[242]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.gradientPaintTransformer, 
                that.gradientPaintTransformer)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[137]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[138]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[243]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.positiveItemLabelPositionFallback, 
            that.positiveItemLabelPositionFallback)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[139]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[140]++;}
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.statements[244]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.negativeItemLabelPositionFallback, 
            that.negativeItemLabelPositionFallback)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[141]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1.branches[142]++;}
        return true;
        
    }

}

class CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1 ());
  }
    public static long[] statements = new long[245];
    public static long[] branches = new long[143];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[72];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.BarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 71; i++) {
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

  public CodeCoverCoverageCounter$3xh56tc1b8j8mcl64x6boc7s1 () {
    super("org.jfree.chart.renderer.category.BarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 244; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 142; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 71; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.BarRenderer.java");
      for (int i = 1; i <= 244; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 142; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 71; i++) {
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

