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
 * -----------------------
 * StackedBarRenderer.java
 * -----------------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Thierry Saura;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 19-Oct-2001 : Version 1 (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 23-Oct-2001 : Changed intro and trail gaps on bar plots to use percentage of 
 *               available space rather than a fixed number of units (DG);
 * 15-Nov-2001 : Modified to allow for null data values (DG);
 * 22-Nov-2001 : Modified to allow for negative data values (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Fixed bug for single category datasets (DG);
 * 15-Feb-2002 : Added isStacked() method (DG);
 * 14-Mar-2002 : Modified to implement the CategoryItemRenderer interface (DG);
 * 24-May-2002 : Incorporated tooltips into chart entities (DG);
 * 11-Jun-2002 : Added check for (permitted) null info object, bug and fix 
 *               reported by David Basten.  Also updated Javadocs. (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 26-Jun-2002 : Small change to entity (DG);
 * 05-Aug-2002 : Small modification to drawCategoryItem method to support URLs 
 *               for HTML image maps (RA);
 * 08-Aug-2002 : Added optional linking lines, contributed by Thierry 
 *               Saura (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 26-Nov-2002 : Replaced isStacked() method with getRangeType() method (DG);
 * 17-Jan-2003 : Moved plot classes to a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 12-May-2003 : Merged horizontal and vertical stacked bar renderers (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 08-Sep-2003 : Fixed bug 799668 (isBarOutlineDrawn() ignored) (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 21-Oct-2003 : Moved bar width into renderer state (DG);
 * 26-Nov-2003 : Added code to respect maxBarWidth attribute (DG);
 * 05-Nov-2004 : Changed to a two-pass renderer so that item labels are not 
 *               overwritten by other bars (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds() (DG);
 * 29-Mar-2005 : Modified drawItem() method so that a zero value is handled 
 *               within the code for positive rather than negative values (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 17-May-2005 : Added flag to allow rendering values as percentages - inspired
 *               by patch 1200886 submitted by John Xiao (DG);
 * 09-Jun-2005 : Added accessor methods for the renderAsPercentages flag,
 *               provided equals() method, and use addItemEntity from 
 *               superclass (DG);
 * 09-Jun-2005 : Added support for GradientPaint - see bug report 1215670 (DG);
 * 22-Sep-2005 : Renamed getMaxBarWidth() --> getMaximumBarWidth() (DG);
 * 29-Sep-2005 : Use outline stroke in drawItem method - see bug report 
 *               1304139 (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 11-Oct-2006 : Source reformatting (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DataUtilities;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.PublicCloneable;

/**
 * A stacked bar renderer for use with the 
 * {@link org.jfree.chart.plot.CategoryPlot} class.
 */
public class StackedBarRenderer extends BarRenderer 
                                implements Cloneable, PublicCloneable, 
                                           Serializable {
  static {
    CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = 6402943811500067531L;
  static {
    CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[1]++;
  }
    
    /** A flag that controls whether the bars display values or percentages. */
    private boolean renderAsPercentages;
    
    /**
     * Creates a new renderer.  By default, the renderer has no tool tip 
     * generator and no URL generator.  These defaults have been chosen to 
     * minimise the processing required to generate a default chart.  If you 
     * require tool tips or URLs, then you can easily add the required 
     * generators.
     */
    public StackedBarRenderer() {
        this(false);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[2]++;
    }
    
    /**
     * Creates a new renderer.
     * 
     * @param renderAsPercentages  a flag that controls whether the data values
     *                             are rendered as percentages.
     */
    public StackedBarRenderer(boolean renderAsPercentages) {
        super();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[3]++;
        this.renderAsPercentages = renderAsPercentages;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[4]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[5]++;
        
        // set the default item label positions, which will only be used if 
        // the user requests visible item labels...
        ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, 
                TextAnchor.CENTER);
        setBasePositiveItemLabelPosition(p);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[6]++;
        setBaseNegativeItemLabelPosition(p);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[7]++;
        setPositiveItemLabelPositionFallback(null);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[8]++;
        setNegativeItemLabelPositionFallback(null);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[9]++;
    }

    /**
     * Returns <code>true</code> if the renderer displays each item value as
     * a percentage (so that the stacked bars add to 100%), and 
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #setRenderAsPercentages(boolean)
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
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        this.renderAsPercentages = asPercentages;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[10]++; 
        fireChangeEvent();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[11]++;
    }
    
    /**
     * Returns the number of passes (<code>2</code>) required by this renderer. 
     * The first pass is used to draw the bars, the second pass is used to
     * draw the item labels (if visible).
     * 
     * @return The number of passes required by the renderer.
     */
    public int getPassCount() {
        return 2;
    }
    
    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The range (or <code>null</code> if the dataset is empty).
     */
    public Range findRangeBounds(CategoryDataset dataset) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[1]++;
            return new Range(0.0, 1.0);
   
        }
        else {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[2]++;
            return DatasetUtilities.findStackedRangeBounds(dataset, getBase());
        }
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
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[13]++;

        // calculate the bar width
        CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[14]++;
        CategoryDataset data = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[3]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[16]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[17]++;
            double space = 0.0;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[5]++;
                space = dataArea.getHeight();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[19]++;

            }
            else {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[6]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[20]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[7]++;
                space = dataArea.getWidth();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[21]++;

            } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[8]++;}
}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[22]++;
            double maxWidth = space * getMaximumBarWidth();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[23]++;
            int columns = data.getColumnCount();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[24]++;
            double categoryMargin = 0.0;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((columns > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[9]++;
                categoryMargin = xAxis.getCategoryMargin();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[26]++;

            } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[10]++;}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[27]++;

            double used = space * (1 - xAxis.getLowerMargin() 
                                     - xAxis.getUpperMargin()
                                     - categoryMargin);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((columns > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[11]++;
                state.setBarWidth(Math.min(used / columns, maxWidth));
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[29]++;

            }
            else {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[12]++;
                state.setBarWidth(Math.min(used, maxWidth));
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[30]++;
            }

        } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[4]++;}

    }

    /**
     * Draws a stacked bar for a specific item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain (category) axis.
     * @param rangeAxis  the range (value) axis.
     * @param dataset  the data.
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
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[31]++;
     
        // nothing is drawn for null values...
        Number dataValue = dataset.getValue(row, column);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dataValue == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[13]++;
            return;

        } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[14]++;}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[33]++;
        
        double value = dataValue.doubleValue();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[34]++;
        double total = 0.0;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;  // only needed if calculating percentages
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[15]++;
            total = DataUtilities.calculateColumnTotal(dataset, column);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[36]++;
            value = value / total;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[37]++;

        } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[16]++;}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[38]++;
        
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[39]++;
        double barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge()) 
                - state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[40]++;

        double positiveBase = getBase();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[41]++;
        double negativeBase = positiveBase;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[42]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < row) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.loops[1]--;
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.loops[2]--;
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.loops[3]++;
}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[43]++;
            Number v = dataset.getValue(i, column);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[17]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[45]++;
                double d = v.doubleValue();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[19]++;
                    d = d / total;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[47]++;

                } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[20]++;}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((d > 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[21]++;
                    positiveBase = positiveBase + d;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[49]++;

                }
                else {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[22]++;
                    negativeBase = negativeBase + d;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[50]++;
                }

            } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[18]++;}
        }

        double translatedBase;
        double translatedValue;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[51]++;
        RectangleEdge location = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[23]++;
            translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, 
                    location);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[53]++;
            translatedValue = rangeAxis.valueToJava2D(positiveBase + value, 
                    dataArea, location);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[54]++;

        }
        else {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[24]++;
            translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, 
                    location);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[55]++;
            translatedValue = rangeAxis.valueToJava2D(negativeBase + value, 
                    dataArea, location);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[56]++;
        }
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[57]++;
        double barL0 = Math.min(translatedBase, translatedValue);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[58]++;
        double barLength = Math.max(Math.abs(translatedValue - translatedBase),
                getMinimumBarLength());
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[59]++;

        Rectangle2D bar = null;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[60]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[25]++;
            bar = new Rectangle2D.Double(barL0, barW0, barLength, 
                    state.getBarWidth());
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[61]++;

        }
        else {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[26]++;
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), 
                    barLength);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[62]++;
        }
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[27]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[64]++;
            Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[65]++;
            GradientPaintTransformer t = getGradientPaintTransformer();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[66]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((itemPaint instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[29]++;
                itemPaint = t.transform((GradientPaint) itemPaint, bar);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[67]++;

            } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[30]++;}
            g2.setPaint(itemPaint);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[68]++;
            g2.fill(bar);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[69]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((isDrawBarOutline()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[31]++;
                g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[71]++;
                g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[72]++;
                g2.draw(bar);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[73]++;

            } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[32]++;}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[74]++;

            // add an item entity, if this information is being collected
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[75]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[33]++;
                addItemEntity(entities, dataset, row, column, bar);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[76]++;

            } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[34]++;}

        }
        else {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[28]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[77]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((pass == 1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[35]++;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[78]++;
            CategoryItemLabelGenerator generator = getItemLabelGenerator(row, 
                    column);
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[79]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[37]++;
                drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                        (value < 0.0));
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[80]++;

            } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[38]++;}

        } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[36]++;}
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
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[81]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[39]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[40]++;}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[82]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((obj instanceof StackedBarRenderer) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[41]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[42]++;}
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[83]++;
        StackedBarRenderer that = (StackedBarRenderer) obj;
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.statements[84]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages != that.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[43]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt.branches[44]++;}
        return super.equals(obj);
    }

}

class CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt ());
  }
    public static long[] statements = new long[85];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.StackedBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,2,1,1,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$2pdh5fd28s7iogy1zjaqbdme370d69p8myxt () {
    super("org.jfree.chart.renderer.category.StackedBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 84; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.StackedBarRenderer.java");
      for (int i = 1; i <= 84; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
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

