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
 * ------------------------
 * StackedAreaRenderer.java
 * ------------------------
 * (C) Copyright 2002-2007, by Dan Rivett (d.rivett@ukonline.co.uk) and 
 *                          Contributors.
 *
 * Original Author:  Dan Rivett (adapted from AreaCategoryItemRenderer);
 * Contributor(s):   Jon Iles;
 *                   David Gilbert (for Object Refinery Limited);
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 20-Sep-2002 : Version 1, contributed by Dan Rivett;
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 01-Nov-2002 : Added tooltips (DG);
 * 06-Nov-2002 : Renamed drawCategoryItem() --> drawItem() and now using axis 
 *               for category spacing. Renamed StackedAreaCategoryItemRenderer 
 *               --> StackedAreaRenderer (DG);
 * 26-Nov-2002 : Switched CategoryDataset --> TableDataset (DG);
 * 26-Nov-2002 : Replaced isStacked() method with getRangeType() method (DG);
 * 17-Jan-2003 : Moved plot classes to a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 13-May-2003 : Modified to take into account the plot orientation (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 07-Oct-2003 : Added renderer state (DG);
 * 29-Apr-2004 : Added getRangeExtent() override (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 11-Oct-2006 : Added support for rendering data values as percentages,
 *               and added a second pass for drawing item labels (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.DataUtilities;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that draws stacked area charts for a 
 * {@link org.jfree.chart.plot.CategoryPlot}.
 */
public class StackedAreaRenderer extends AreaRenderer 
                                 implements Cloneable, PublicCloneable, 
                                            Serializable {
  static {
    CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3595635038460823663L;
  static {
    CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[1]++;
  }
     
    /** A flag that controls whether the areas display values or percentages. */
    private boolean renderAsPercentages;
    
    /**
     * Creates a new renderer.
     */
    public StackedAreaRenderer() {
        this(false);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[2]++;
    }
    
    /**
     * Creates a new renderer.
     * 
     * @param renderAsPercentages  a flag that controls whether the data values
     *                             are rendered as percentages.
     */
    public StackedAreaRenderer(boolean renderAsPercentages) {
        super();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[3]++;
        this.renderAsPercentages = renderAsPercentages;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[4]++;
    }

    /**
     * Returns <code>true</code> if the renderer displays each item value as
     * a percentage (so that the stacked areas add to 100%), and 
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     *
     * @since 1.0.3
     */
    public boolean getRenderAsPercentages() {
        return this.renderAsPercentages;   
    }
    
    /**
     * Sets the flag that controls whether the renderer displays each item
     * value as a percentage (so that the stacked areas add to 100%), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param asPercentages  the flag.
     *
     * @since 1.0.3
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        this.renderAsPercentages = asPercentages;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[5]++; 
        fireChangeEvent();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[6]++;
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
     * @param dataset  the dataset (<code>null</code> not permitted).
     * 
     * @return The range (or <code>null</code> if the dataset is empty).
     */
    public Range findRangeBounds(CategoryDataset dataset) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[1]++;
            return new Range(0.0, 1.0);
   
        }
        else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[2]++;
            return DatasetUtilities.findStackedRangeBounds(dataset);
        }
    }

    /**
     * Draw a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
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
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[8]++;

        // setup for collecting optional entity info...
        Shape entityArea = null;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[9]++;
        EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[10]++;
        
        double y1 = 0.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[11]++;
        Number n = dataset.getValue(row, column);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[3]++;
            y1 = n.doubleValue();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[13]++;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[4]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[14]++;        
        double[] stack1 = getStackValues(dataset, row, column);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[15]++;


        // leave the y values (y1, y0) untranslated as it is going to be be 
        // stacked up later by previous series values, after this it will be 
        // translated.
        double xx1 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[16]++;
        
        
        // get the previous point and the next point so we can calculate a 
        // "hot spot" for the area (used by the chart entity)...
        double y0 = 0.0;
        n = dataset.getValue(row, Math.max(column - 1, 0));
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[17]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[5]++;
            y0 = n.doubleValue();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[19]++;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[6]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[20]++;
        double[] stack0 = getStackValues(dataset, row, Math.max(column - 1, 0));
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[21]++;

        // FIXME: calculate xx0
        double xx0 = domainAxis.getCategoryStart(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[22]++;
        
        int itemCount = dataset.getColumnCount();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[23]++;
        double y2 = 0.0;
        n = dataset.getValue(row, Math.min(column + 1, itemCount - 1));
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[24]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[7]++;
            y2 = n.doubleValue();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[26]++;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[8]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[27]++;
        double[] stack2 = getStackValues(dataset, row, Math.min(column + 1, 
                itemCount - 1));
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[28]++;

        double xx2 = domainAxis.getCategoryEnd(column, getColumnCount(), 
                dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[29]++;
        
        // FIXME: calculate xxLeft and xxRight
        double xxLeft = xx0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[30]++;
        double xxRight = xx2;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[31]++;
        
        double[] stackLeft = averageStackValues(stack0, stack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[32]++;
        double[] stackRight = averageStackValues(stack1, stack2);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[33]++;
        double[] adjStackLeft = adjustedStackValues(stack0, stack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[34]++;
        double[] adjStackRight = adjustedStackValues(stack1, stack2);

        float transY1;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[35]++;
        
        RectangleEdge edge1 = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[36]++;
        
        GeneralPath left = new GeneralPath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[37]++;
        GeneralPath right = new GeneralPath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[38]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((y1 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[9]++;  // handle positive value
            transY1 = (float) rangeAxis.valueToJava2D(y1 + stack1[1], dataArea, 
                    edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[39]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[40]++;
            float transStack1 = (float) rangeAxis.valueToJava2D(stack1[1], 
                    dataArea, edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[41]++;
            float transStackLeft = (float) rangeAxis.valueToJava2D(
                    adjStackLeft[1], dataArea, edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
            
            // LEFT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((y0 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[11]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[43]++;
                double yleft = (y0 + y1) / 2.0 + stackLeft[1];
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[44]++;
                float transYLeft 
                    = (float) rangeAxis.valueToJava2D(yleft, dataArea, edge1);
                left.moveTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[45]++;
                left.lineTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[46]++;
                left.lineTo((float) xxLeft, transStackLeft);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[47]++;
                left.lineTo((float) xxLeft, transYLeft);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[48]++;
                left.closePath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[49]++;

            }
            else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[12]++;
                left.moveTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[50]++;
                left.lineTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[51]++;
                left.lineTo((float) xxLeft, transStackLeft);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[52]++;
                left.closePath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[53]++;
            }
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[54]++;

            float transStackRight = (float) rangeAxis.valueToJava2D(
                    adjStackRight[1], dataArea, edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[55]++;
int CodeCoverConditionCoverageHelper_C7;
            // RIGHT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((y2 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[13]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[56]++;
                double yright = (y1 + y2) / 2.0 + stackRight[1];
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[57]++;
                float transYRight 
                    = (float) rangeAxis.valueToJava2D(yright, dataArea, edge1);
                right.moveTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[58]++;
                right.lineTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[59]++;
                right.lineTo((float) xxRight, transYRight);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[60]++;
                right.lineTo((float) xxRight, transStackRight);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[61]++;
                right.closePath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[62]++;

            }
            else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[14]++;
                right.moveTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[63]++;
                right.lineTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[64]++;
                right.lineTo((float) xxRight, transStackRight);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[65]++;
                right.closePath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[66]++;
            }

        }
        else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[10]++;  // handle negative value 
            transY1 = (float) rangeAxis.valueToJava2D(y1 + stack1[0], dataArea,
                    edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[67]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[68]++;
            float transStack1 = (float) rangeAxis.valueToJava2D(stack1[0], 
                    dataArea, edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[69]++;
            float transStackLeft = (float) rangeAxis.valueToJava2D(
                    adjStackLeft[0], dataArea, edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[70]++;
int CodeCoverConditionCoverageHelper_C8;

            // LEFT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((y0 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[15]++;
                left.moveTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[71]++;
                left.lineTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[72]++;
                left.lineTo((float) xxLeft, transStackLeft);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[73]++;
                left.clone();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[74]++;

            }
            else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[16]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[75]++;
                double yleft = (y0 + y1) / 2.0 + stackLeft[0];
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[76]++;
                float transYLeft = (float) rangeAxis.valueToJava2D(yleft, 
                        dataArea, edge1);
                left.moveTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[77]++;
                left.lineTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[78]++;
                left.lineTo((float) xxLeft, transStackLeft);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[79]++;
                left.lineTo((float) xxLeft, transYLeft);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[80]++;
                left.closePath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[81]++;
            }
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[82]++;
            float transStackRight = (float) rangeAxis.valueToJava2D(
                    adjStackRight[0], dataArea, edge1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[83]++;
int CodeCoverConditionCoverageHelper_C9;
            
            // RIGHT POLYGON
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((y2 >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[17]++;
                right.moveTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[84]++;
                right.lineTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[85]++;
                right.lineTo((float) xxRight, transStackRight);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[86]++;
                right.closePath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[87]++;

            }
            else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[18]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[88]++;
                double yright = (y1 + y2) / 2.0 + stackRight[0];
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[89]++;
                float transYRight = (float) rangeAxis.valueToJava2D(yright, 
                        dataArea, edge1);
                right.moveTo((float) xx1, transStack1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[90]++;
                right.lineTo((float) xx1, transY1);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[91]++;
                right.lineTo((float) xxRight, transYRight);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[92]++;
                right.lineTo((float) xxRight, transStackRight);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[93]++;
                right.closePath();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[94]++;
            }
        }

        g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[95]++;
        g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[96]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[97]++;

        //  Get series Paint and Stroke
        Paint itemPaint = getItemPaint(row, column);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[98]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[19]++;
            g2.setPaint(itemPaint);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[99]++;
            g2.fill(left);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[100]++;
            g2.fill(right);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[101]++;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[20]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[102]++;
int CodeCoverConditionCoverageHelper_C11; 
        
        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[21]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[103]++;
            GeneralPath gp = new GeneralPath(left);
            gp.append(right, false);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[104]++;
            entityArea = gp;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[105]++;
            addItemEntity(entities, dataset, row, column, entityArea);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[106]++;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[22]++;}
        
    }

    /**
     * Calculates the stacked value of the all series up to, but not including 
     * <code>series</code> for the specified category, <code>category</code>.  
     * It returns 0.0 if <code>series</code> is the first series, i.e. 0.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param series  the series.
     * @param category  the category.
     *
     * @return double returns a cumulative value for all series' values up to 
     *         but excluding <code>series</code> for Object 
     *         <code>category</code>.
     */
    protected double getPreviousHeight(CategoryDataset dataset, 
                                       int series, int category) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[107]++;

        double result = 0.0;
        Number n;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[108]++;
        double total = 0.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[109]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[23]++;
            total = DataUtilities.calculateColumnTotal(dataset, category);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[110]++;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[24]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[111]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < series) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[1]--;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[2]--;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[3]++;
}
            n = dataset.getValue(i, category);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[112]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[113]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[25]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[114]++;
                double v = n.doubleValue();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[115]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[27]++;
                    v = v / total;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[116]++;

                } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[28]++;}
                result += v;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[117]++;

            } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[26]++;}
        }
        return result;

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
    protected double[] getStackValues(CategoryDataset dataset, 
            int series, int index) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[118]++;
        double[] result = new double[2];
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[119]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < series) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[4]--;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[5]--;
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.loops[6]++;
}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[120]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isSeriesVisible(i)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[29]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[121]++;
                double v = 0.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[122]++;
                Number n = dataset.getValue(i, index);
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[123]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[31]++;
                    v = n.doubleValue();
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[124]++;

                } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[32]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[125]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((Double.isNaN(v)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[33]++;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[126]++;
int CodeCoverConditionCoverageHelper_C20;
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((v >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[35]++;
                        result[1] += v;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[127]++;
   
                    }
                    else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[36]++;
                        result[0] += v;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[128]++;   
                    }

                } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[34]++;}

            } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[30]++;}
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
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[129]++;
        double[] result = new double[2];
        result[0] = (stack1[0] + stack2[0]) / 2.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[130]++;
        result[1] = (stack1[1] + stack2[1]) / 2.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[131]++;
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
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[132]++;
        double[] result = new double[2];
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[133]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((stack1[0] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((stack2[0] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[37]++;
            result[0] = 0.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[134]++;
   
        }
        else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[38]++;
            result[0] = (stack1[0] + stack2[0]) / 2.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[135]++;
        }
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[136]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((stack1[1] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((stack2[1] == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[39]++;
            result[1] = 0.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[137]++;
   
        }
        else {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[40]++;
            result[1] = (stack1[1] + stack2[1]) / 2.0;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[138]++;
        }
        return result;
    }

    /**
     * Checks this instance for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> not permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[139]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[41]++;
            return true;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[42]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[140]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((obj instanceof StackedAreaRenderer) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[44]++;}
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[141]++;
        StackedAreaRenderer that = (StackedAreaRenderer) obj;
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.statements[142]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages != that.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl.branches[46]++;}
        return super.equals(obj);
    }
}

class CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl ());
  }
    public static long[] statements = new long[143];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.StackedAreaRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$j8ftyl8vyglgfv0p3dyypicatl8rsczw8jazl () {
    super("org.jfree.chart.renderer.category.StackedAreaRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 142; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.StackedAreaRenderer.java");
      for (int i = 1; i <= 142; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

