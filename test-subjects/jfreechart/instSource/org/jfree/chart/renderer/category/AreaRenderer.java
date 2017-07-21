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
 * -----------------
 * AreaRenderer.java
 * -----------------
 * (C) Copyright 2002-2007, by Jon Iles and Contributors.
 *
 * Original Author:  Jon Iles;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Christian W. Zuckschwerdt;
 *
 * Changes:
 * --------
 * 21-May-2002 : Version 1, contributed by John Iles (DG);
 * 29-May-2002 : Now extends AbstractCategoryItemRenderer (DG);
 * 11-Jun-2002 : Updated Javadoc comments (DG);
 * 25-Jun-2002 : Removed unnecessary imports (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 10-Oct-2002 : Added constructors and basic entity support (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 06-Nov-2002 : Renamed drawCategoryItem() --> drawItem() and now using axis 
 *               for category spacing.  Renamed AreaCategoryItemRenderer 
 *               --> AreaRenderer (DG);
 * 17-Jan-2003 : Moved plot classes into a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 10-Apr-2003 : Changed CategoryDataset to KeyedValues2DDataset in 
 *               drawItem() method (DG);
 * 12-May-2003 : Modified to take into account the plot orientation (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 20-Apr-2005 : Apply tooltips and URLs to legend items (DG);
 * 09-Jun-2005 : Use addItemEntity() method from superclass (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 11-Oct-2006 : Fixed bug in equals() method (DG);
 * 30-Nov-2006 : Added checks for series visibility (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A category item renderer that draws area charts.  You can use this renderer 
 * with the {@link org.jfree.chart.plot.CategoryPlot} class.
 */
public class AreaRenderer extends AbstractCategoryItemRenderer 
                          implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -4231878281385812757L;
  static {
    CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[1]++;
  }
    
    /** A flag that controls how the ends of the areas are drawn. */
    private AreaRendererEndType endType;
    
    /**
     * Creates a new renderer.
     */
    public AreaRenderer() {
        super();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[2]++;
        this.endType = AreaRendererEndType.TAPER;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[3]++;
    }

    /**
     * Returns a token that controls how the renderer draws the end points.
     * The default value is {@link AreaRendererEndType#TAPER}.
     * 
     * @return The end type (never <code>null</code>).
     *
     * @see #setEndType
     */
    public AreaRendererEndType getEndType() {
        return this.endType;   
    }
    
    /**
     * Sets a token that controls how the renderer draws the end points, and 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param type  the end type (<code>null</code> not permitted).
     *
     * @see #getEndType()
     */
    public void setEndType(AreaRendererEndType type) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[1]++;
            throw new IllegalArgumentException("Null 'type' argument.");
   
        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[2]++;}
        this.endType = type;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[5]++;
        fireChangeEvent();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[6]++;
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
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[7]++;

        // if there is no plot, there is no dataset to access...
        CategoryPlot cp = getPlot();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cp == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[3]++;
            return null;

        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[4]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        
        // check that a legend item needs to be displayed...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((isSeriesVisible(series)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(series)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[5]++;
            return null;

        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[6]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[10]++;

        CategoryDataset dataset = cp.getDataset(datasetIndex);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[11]++;
        String label = getLegendItemLabelGenerator().generateLabel(dataset, 
                series);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[12]++;
        String description = label;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[13]++;
        String toolTipText = null;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[14]++;
int CodeCoverConditionCoverageHelper_C4; 
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[7]++;
            toolTipText = getLegendItemToolTipGenerator().generateLabel(
                    dataset, series);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[15]++;
   
        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[8]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[16]++;
        String urlText = null;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[9]++;
            urlText = getLegendItemURLGenerator().generateLabel(dataset, 
                    series);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[18]++;
   
        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[10]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[19]++;
        Shape shape = new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[20]++;
        Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[21]++;
        Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[22]++;
        Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[23]++;

        LegendItem result = new LegendItem(label, description, toolTipText, 
                urlText, shape, paint, outlineStroke, outlinePaint);
        result.setDataset(dataset);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[24]++;
        result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[25]++;
        result.setSeriesKey(dataset.getRowKey(series));
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[26]++;
        result.setSeriesIndex(series);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[27]++;
        return result;

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
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((getItemVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[11]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[12]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[29]++;

        // plot non-null values only...
        Number value = dataset.getValue(row, column);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[13]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[31]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[32]++;
            RectangleEdge axisEdge = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[33]++;
            int count = dataset.getColumnCount();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[34]++;
            float x0 = (float) domainAxis.getCategoryStart(column, count, 
                    dataArea, axisEdge);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[35]++;
            float x1 = (float) domainAxis.getCategoryMiddle(column, count, 
                    dataArea, axisEdge);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[36]++;
            float x2 = (float) domainAxis.getCategoryEnd(column, count, 
                    dataArea, axisEdge);

            x0 = Math.round(x0);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[37]++;
            x1 = Math.round(x1);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[38]++;
            x2 = Math.round(x2);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[39]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;

            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.endType == AreaRendererEndType.TRUNCATE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[15]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[41]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((column == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[17]++;
                    x0 = x1;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[42]++;
   
                }
                else {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[18]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[43]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((column == getColumnCount() - 1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[19]++;
                    x2 = x1;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[44]++;
   
                } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[20]++;}
}

            } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[16]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[45]++;
            
            double yy1 = value.doubleValue();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[46]++;

            double yy0 = 0.0;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((column > 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[21]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[48]++;
                Number n0 = dataset.getValue(row, column - 1);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n0 != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[23]++;
                    yy0 = (n0.doubleValue() + yy1) / 2.0;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[50]++;

                } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[24]++;}

            } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[22]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[51]++;

            double yy2 = 0.0;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((column < dataset.getColumnCount() - 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[25]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[53]++;
                Number n2 = dataset.getValue(row, column + 1);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n2 != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[27]++;
                    yy2 = (n2.doubleValue() + yy1) / 2.0;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[55]++;

                } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[28]++;}

            } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[26]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[56]++;

            RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[57]++;
            float y0 = (float) rangeAxis.valueToJava2D(yy0, dataArea, edge);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[58]++;
            float y1 = (float) rangeAxis.valueToJava2D(yy1, dataArea, edge);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[59]++;
            float y2 = (float) rangeAxis.valueToJava2D(yy2, dataArea, edge);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[60]++;
            float yz = (float) rangeAxis.valueToJava2D(0.0, dataArea, edge);

            g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[61]++;
            g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[62]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[63]++;

            GeneralPath area = new GeneralPath();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[64]++;
int CodeCoverConditionCoverageHelper_C15;

            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[29]++;
                area.moveTo(x0, yz);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[65]++;
                area.lineTo(x0, y0);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[66]++;
                area.lineTo(x1, y1);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[67]++;
                area.lineTo(x2, y2);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[68]++;
                area.lineTo(x2, yz);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[69]++;

            }
            else {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[30]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[70]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[31]++;
                area.moveTo(yz, x0);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[71]++;
                area.lineTo(y0, x0);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[72]++;
                area.lineTo(y1, x1);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[73]++;
                area.lineTo(y2, x2);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[74]++;
                area.lineTo(yz, x2);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[75]++;

            } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[32]++;}
}
            area.closePath();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[76]++;

            g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[77]++;
            g2.fill(area);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[78]++;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[79]++;
int CodeCoverConditionCoverageHelper_C17;

            // draw the item labels if there are any...
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[33]++;
                drawItemLabel(g2, orientation, dataset, row, column, x1, y1, 
                        (value.doubleValue() < 0.0));
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[80]++;

            } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[34]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[81]++;

            // add an item entity, if this information is being collected
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[82]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[35]++;
                addItemEntity(entities, dataset, row, column, area);
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[83]++;

            } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[14]++;}

    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the object to test (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[84]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[37]++;    
            return true;

        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[38]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[85]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj instanceof AreaRenderer) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[40]++;}
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[86]++;
        AreaRenderer that = (AreaRenderer) obj;
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.endType.equals(that.endType)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh.branches[42]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns an independent copy of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  should not happen.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

class CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.AreaRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$rjwfffabzhaadkdxt3ozfw25wh () {
    super("org.jfree.chart.renderer.category.AreaRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.AreaRenderer.java");
      for (int i = 1; i <= 87; i++) {
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
    for (int i = 1; i <= 21; i++) {
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

