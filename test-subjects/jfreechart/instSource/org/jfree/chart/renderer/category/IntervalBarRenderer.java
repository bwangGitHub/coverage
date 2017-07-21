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
 * IntervalBarRenderer.java
 * ------------------------
 * (C) Copyright 2002-2007, by Jeremy Bowman.
 *
 * Original Author:  Jeremy Bowman;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 29-Apr-2002 : Version 1, contributed by Jeremy Bowman (DG);
 * 11-May-2002 : Use CategoryPlot.getLabelsVisible() (JB);
 * 29-May-2002 : Added constructors (DG);
 * 26-Jun-2002 : Added axis to initialise method (DG);
 * 20-Sep-2002 : Added basic support for chart entities (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Base dataset is now TableDataset not CategoryDataset (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 19-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 08-Sep-2003 : Added checks for null values (DG);
 * 07-Oct-2003 : Added renderer state (DG);
 * 21-Oct-2003 : Bar width moved into renderer state (DG);
 * 23-Dec-2003 : Removed the deprecated MultiIntervalCategoryDataset 
 *               interface (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that handles the drawing of bars for a bar plot where
 * each bar has a high and low value.
 * <p>
 * For use with the {@link CategoryPlot} class.
 */
public class IntervalBarRenderer extends BarRenderer
                                 implements CategoryItemRenderer, 
                                            Cloneable, 
                                            PublicCloneable, 
                                            Serializable {
  static {
    CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5068857361615528725L;
  static {
    CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[1]++;
  }
    
    /**
     * Constructs a new renderer.
     */
    public IntervalBarRenderer() {
        super();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[2]++;
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
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;

         if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset instanceof IntervalCategoryDataset) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[1]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[4]++;
             IntervalCategoryDataset d = (IntervalCategoryDataset) dataset;
             drawInterval(g2, state, dataArea, plot, domainAxis, rangeAxis, 
                     d, row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[5]++;

         }
         else {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[2]++;
             super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, 
                     dataset, row, column, pass);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[6]++;
         } 
         
     }
                          
     /**
      * Draws a single interval.
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
      */
     protected void drawInterval(Graphics2D g2,
                                 CategoryItemRendererState state,
                                 Rectangle2D dataArea,
                                 CategoryPlot plot,
                                 CategoryAxis domainAxis,
                                 ValueAxis rangeAxis,
                                 IntervalCategoryDataset dataset,
                                 int row,
                                 int column) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[7]++;

        int seriesCount = getRowCount();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[8]++;
        int categoryCount = getColumnCount();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[9]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[10]++;
        
        double rectX = 0.0;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[11]++;
        double rectY = 0.0;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[12]++;

        RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[13]++;
        RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[14]++;
        
        // Y0
        Number value0 = dataset.getEndValue(row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value0 == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[3]++;
            return;

        } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[4]++;}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[16]++;
        double java2dValue0 = rangeAxis.valueToJava2D(
            value0.doubleValue(), dataArea, rangeAxisLocation
        );
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[17]++;

        // Y1
        Number value1 = dataset.getStartValue(row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value1 == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[5]++;
            return;

        } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[6]++;}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[19]++;
        double java2dValue1 = rangeAxis.valueToJava2D(
                value1.doubleValue(), dataArea, rangeAxisLocation);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((java2dValue1 < java2dValue0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[7]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[21]++;
            double temp = java2dValue1;
            java2dValue1 = java2dValue0;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[22]++;
            java2dValue0 = temp;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[23]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[24]++;
            Number tempNum = value1;
            value1 = value0;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[25]++;
            value0 = tempNum;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[26]++;

        } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[8]++;}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[27]++;

        // BAR WIDTH
        double rectWidth = state.getBarWidth();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[28]++;

        // BAR HEIGHT
        double rectHeight = Math.abs(java2dValue1 - java2dValue0);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[9]++;
            // BAR Y
            rectY = domainAxis.getCategoryStart(
                column, getColumnCount(), dataArea, domainAxisLocation
            );
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[30]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[11]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[32]++;
                double seriesGap = dataArea.getHeight() * getItemMargin()
                                   / (categoryCount * (seriesCount - 1));
                rectY = rectY + row * (state.getBarWidth() + seriesGap);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[33]++;

            }
            else {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[12]++;
                rectY = rectY + row * state.getBarWidth();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[34]++;
            }
            
            rectX = java2dValue0;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[35]++;

            rectHeight = state.getBarWidth();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[36]++;
            rectWidth = Math.abs(java2dValue1 - java2dValue0);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[37]++;


        }
        else {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[10]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[38]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[13]++;
            // BAR X
            rectX = domainAxis.getCategoryStart(column, getColumnCount(), 
                    dataArea, domainAxisLocation);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[39]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;

            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((seriesCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[15]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[41]++;
                double seriesGap = dataArea.getWidth() * getItemMargin()
                                   / (categoryCount * (seriesCount - 1));
                rectX = rectX + row * (state.getBarWidth() + seriesGap);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[42]++;

            }
            else {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[16]++;
                rectX = rectX + row * state.getBarWidth();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[43]++;
            }

            rectY = java2dValue0;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[44]++;


        } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[14]++;}
}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[45]++;
        Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, 
                rectHeight);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[46]++;
        Paint seriesPaint = getItemPaint(row, column);
        g2.setPaint(seriesPaint);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[47]++;
        g2.fill(bar);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[48]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
        
        // draw the outline...
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[17]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[50]++;
            Stroke stroke = getItemOutlineStroke(row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[51]++;
            Paint paint = getItemOutlinePaint(row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[52]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[19]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[53]++;
                g2.setPaint(paint);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[54]++;
                g2.draw(bar);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[55]++;

            } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[20]++;}

        } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[18]++;}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[56]++;
        
        CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
                column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[21]++;
            drawItemLabel(g2, dataset, row, column, plot, generator, bar, 
                    false);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[58]++;

        } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[22]++;}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[59]++;
int CodeCoverConditionCoverageHelper_C12;        

        // collect entity and tool tip information...
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[23]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[60]++;
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[25]++;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[62]++;
                String tip = null;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[63]++;
                CategoryToolTipGenerator tipster 
                        = getToolTipGenerator(row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[27]++;
                    tip = tipster.generateToolTip(dataset, row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[65]++;

                } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[28]++;}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[66]++;
                String url = null;
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[67]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((getItemURLGenerator(row, column) != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[29]++;
                    url = getItemURLGenerator(row, column).generateURL(
                            dataset, row, column);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[68]++;

                } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[30]++;}
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[69]++;
                CategoryItemEntity entity = new CategoryItemEntity(bar, tip, 
                        url, dataset, dataset.getRowKey(row), 
                        dataset.getColumnKey(column));
                entities.add(entity);
CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.statements[70]++;

            } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d.branches[24]++;}

    }

}

class CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d ());
  }
    public static long[] statements = new long[71];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.IntervalBarRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$gx9xb7eimefdqazvtnvyk3ydmrrqgbsnitd9d () {
    super("org.jfree.chart.renderer.category.IntervalBarRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 70; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.IntervalBarRenderer.java");
      for (int i = 1; i <= 70; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

