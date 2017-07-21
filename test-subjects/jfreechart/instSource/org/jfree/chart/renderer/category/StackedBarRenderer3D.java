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
 * StackedBarRenderer3D.java
 * -------------------------
 * (C) Copyright 2000-2007, by Serge V. Grachov and Contributors.
 *
 * Original Author:  Serge V. Grachov;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *                   Max Herfort (patch 1459313);
 *
 * Changes
 * -------
 * 31-Oct-2001 : Version 1, contributed by Serge V. Grachov (DG);
 * 15-Nov-2001 : Modified to allow for null data values (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 15-Feb-2002 : Added isStacked() method (DG);
 * 24-May-2002 : Incorporated tooltips into chart entities (DG);
 * 19-Jun-2002 : Added check for null info in drawCategoryItem method (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 26-Jun-2002 : Small change to entity (DG);
 * 05-Aug-2002 : Small modification to drawCategoryItem method to support URLs 
 *               for HTML image maps (RA);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 26-Nov-2002 : Replaced isStacked() method with getRangeType() method (DG);
 * 17-Jan-2003 : Moved plot classes to a separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Added default constructor (bug 726235) and fixed bug 
 *               726260) (DG);
 * 13-May-2003 : Renamed StackedVerticalBarRenderer3D 
 *               --> StackedBarRenderer3D (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 07-Oct-2003 : Added renderer state (DG);
 * 21-Nov-2003 : Added a new constructor (DG);
 * 27-Nov-2003 : Modified code to respect maxBarWidth setting (DG);
 * 11-Aug-2004 : Fixed bug where isDrawBarOutline() was ignored (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds (DG);
 * 18-Mar-2005 : Override for getPassCount() method (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 09-Jun-2005 : Use addItemEntity() method from superclass (DG);
 * 22-Sep-2005 : Renamed getMaxBarWidth() --> getMaximumBarWidth() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 31-Mar-2006 : Added renderAsPercentages option - see patch 1459313 submitted
 *               by Max Herfort (DG);
 * 16-Jan-2007 : Replaced rendering code to draw whole stack at once (DG);
 * 18-Jan-2007 : Fixed bug handling null values in createStackedValueList() 
 *               method (DG);
 * 18-Jan-2007 : Updated block drawing code to take account of inverted axes,
 *               see bug report 1599652 (DG);
 * 08-May-2007 : Fixed bugs 1713401 (drawBarOutlines flag) and  1713474 
 *               (shading) (DG);
 *               
 */

package org.jfree.chart.renderer.category;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DataUtilities;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.util.BooleanUtilities;
import org.jfree.util.PublicCloneable;

/**
 * Renders stacked bars with 3D-effect, for use with the 
 * {@link org.jfree.chart.plot.CategoryPlot} class.
 */
public class StackedBarRenderer3D extends BarRenderer3D 
                                  implements Cloneable, PublicCloneable, 
                                             Serializable {
  static {
    CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -5832945916493247123L;
  static {
    CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[1]++;
  }
    
    /** A flag that controls whether the bars display values or percentages. */
    private boolean renderAsPercentages;
    
    /**
     * Creates a new renderer with no tool tip generator and no URL generator.
     * <P>
     * The defaults (no tool tip or URL generators) have been chosen to 
     * minimise the processing required to generate a default chart.  If you 
     * require tool tips or URLs, then you can easily add the required 
     * generators.
     */
    public StackedBarRenderer3D() {
        this(false);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[2]++;
    }

    /**
     * Constructs a new renderer with the specified '3D effect'.
     *
     * @param xOffset  the x-offset for the 3D effect.
     * @param yOffset  the y-offset for the 3D effect.
     */
    public StackedBarRenderer3D(double xOffset, double yOffset) {
        super(xOffset, yOffset);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[3]++;
    }
    
    /**
     * Creates a new renderer.
     * 
     * @param renderAsPercentages  a flag that controls whether the data values
     *                             are rendered as percentages.
     * 
     * @since 1.0.2
     */
    public StackedBarRenderer3D(boolean renderAsPercentages) {
        super();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[4]++;
        this.renderAsPercentages = renderAsPercentages;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[5]++;
    }
    
    /**
     * Constructs a new renderer with the specified '3D effect'.
     *
     * @param xOffset  the x-offset for the 3D effect.
     * @param yOffset  the y-offset for the 3D effect.
     * @param renderAsPercentages  a flag that controls whether the data values
     *                             are rendered as percentages.
     * 
     * @since 1.0.2
     */
    public StackedBarRenderer3D(double xOffset, double yOffset, 
            boolean renderAsPercentages) {
        super(xOffset, yOffset);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[6]++;
        this.renderAsPercentages = renderAsPercentages;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[7]++;
    }
    
    /**
     * Returns <code>true</code> if the renderer displays each item value as
     * a percentage (so that the stacked bars add to 100%), and 
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     *
     * @since 1.0.2
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
     * @since 1.0.2
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        this.renderAsPercentages = asPercentages;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[8]++; 
        fireChangeEvent();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[9]++;
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
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[1]++;
            return new Range(0.0, 1.0);
   
        }
        else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[2]++;
            return DatasetUtilities.findStackedRangeBounds(dataset);
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
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[11]++;

        // calculate the bar width
        CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[12]++;
        CategoryDataset data = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[3]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[14]++;
            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[15]++;
            double space = 0.0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[5]++;
                space = dataArea.getHeight();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[17]++;

            }
            else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[6]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[18]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[7]++;
                space = dataArea.getWidth();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[19]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[8]++;}
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[20]++;
            double maxWidth = space * getMaximumBarWidth();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[21]++;
            int columns = data.getColumnCount();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[22]++;
            double categoryMargin = 0.0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((columns > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[9]++;
                categoryMargin = domainAxis.getCategoryMargin();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[24]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[10]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[25]++;

            double used = space * (1 - domainAxis.getLowerMargin() 
                                     - domainAxis.getUpperMargin()
                                     - categoryMargin);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((columns > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[11]++;
                state.setBarWidth(Math.min(used / columns, maxWidth));
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[27]++;

            }
            else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[12]++;
                state.setBarWidth(Math.min(used, maxWidth));
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[28]++;
            }

        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[4]++;}

    }
    
    /**
     * Returns a list containing the stacked values for the specified series
     * in the given dataset, plus the supplied base value.
     *  
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param category  the category key (<code>null</code> not permitted).
     * @param base  the base value.
     * @param asPercentages  a flag that controls whether the values in the
     *     list are converted to percentages of the total.
     *     
     * @return The value list.
     *
     * @since 1.0.4
     */
    protected static List createStackedValueList(CategoryDataset dataset, 
            Comparable category, double base, boolean asPercentages) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[29]++;
        
        List result = new ArrayList();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[30]++;
        double posBase = base;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[31]++;
        double negBase = base;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[32]++;
        double total = 0.0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((asPercentages) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[13]++;
            total = DataUtilities.calculateColumnTotal(dataset, 
                    dataset.getColumnIndex(category));
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[34]++;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[14]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[35]++;

        int baseIndex = -1;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[36]++;
        int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[37]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int s = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); s++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[1]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[2]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[3]++;
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[38]++;
            Number n = dataset.getValue(dataset.getRowKey(s), category);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[15]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[40]++;
                continue;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[16]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[41]++;
            double v = n.doubleValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[42]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((asPercentages) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[17]++;
                v = v / total;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[43]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[18]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((v >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[19]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((baseIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[21]++;
                    result.add(new Object[] {null, new Double(base)});
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[46]++;
                    baseIndex = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[47]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[22]++;}
                posBase = posBase + v;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[48]++;
                result.add(new Object[] {new Integer(s), new Double(posBase)});
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[49]++;

            }
            else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[20]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[50]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((v < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[23]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((baseIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[25]++;
                    result.add(new Object[] {null, new Double(base)});
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[52]++;
                    baseIndex = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[53]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[26]++;}
                negBase = negBase + v;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[54]++; // '+' because v is negative
                result.add(0, new Object[] {new Integer(-s), 
                        new Double(negBase)});
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[55]++;
                baseIndex++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[56]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[24]++;}
}
        }
        return result;
        
    }
    
    /**
     * Draws the visual representation of one data item from the chart (in 
     * fact, this method does nothing until it reaches the last item for each
     * category, at which point it draws all the items for that category).
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
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;

        // wait till we are at the last item for the row then draw the
        // whole stack at once
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((row < dataset.getRowCount() - 1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[27]++;
            return;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[28]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[58]++;
        Comparable category = dataset.getColumnKey(column);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[59]++;
        
        List values = createStackedValueList(dataset, 
                dataset.getColumnKey(column), getBase(), 
                this.renderAsPercentages);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[60]++;
        
        Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), 
                dataArea.getY() + getYOffset(), 
                dataArea.getWidth() - getXOffset(), 
                dataArea.getHeight() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[61]++;


        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[62]++;
int CodeCoverConditionCoverageHelper_C16;

        // handle rendering separately for the two plot orientations...
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[29]++;
            drawStackHorizontal(values, category, g2, state, adjusted, plot, 
                    domainAxis, rangeAxis, dataset);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[63]++;

        }
        else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[30]++;
            drawStackVertical(values, category, g2, state, adjusted, plot, 
                    domainAxis, rangeAxis, dataset);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[64]++;
        }

    }
    
    /**
     * Draws a stack of bars for one category, with a horizontal orientation.
     * 
     * @param values  the value list.
     * @param category  the category.
     * @param g2  the graphics device.
     * @param state  the state.
     * @param dataArea  the data area (adjusted for the 3D effect).
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     *
     * @since 1.0.4
     */
    protected void drawStackHorizontal(List values, Comparable category, 
            Graphics2D g2, CategoryItemRendererState state, 
            Rectangle2D dataArea, CategoryPlot plot, 
            CategoryAxis domainAxis, ValueAxis rangeAxis, 
            CategoryDataset dataset) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[65]++;
        
        int column = dataset.getColumnIndex(category);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[66]++;
        double barX0 = domainAxis.getCategoryMiddle(column, 
                dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge()) 
                - state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[67]++;
        double barW = state.getBarWidth();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[68]++;
        
        // a list to store the series index and bar region, so we can draw
        // all the labels at the end...
        List itemLabelList = new ArrayList();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[69]++;
        
        // draw the blocks
        boolean inverted = rangeAxis.isInverted();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[70]++;
        int blockCount = values.size() - 1;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[71]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((k < blockCount) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[4]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[5]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[6]++;
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[72]++;
            int index = (inverted ? blockCount - k - 1 : k);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[73]++;
            Object[] prev = (Object[]) values.get(index);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[74]++;
            Object[] curr = (Object[]) values.get(index + 1);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[75]++;
            int series = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[76]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((curr[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[31]++;
                series = -((Integer) prev[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[77]++;

            }
            else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[32]++;
                series = ((Integer) curr[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[78]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[79]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[33]++;
                    series = -((Integer) prev[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[80]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[34]++;}
            }
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[81]++;
            double v0 = ((Double) prev[1]).doubleValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[82]++;
            double vv0 = rangeAxis.valueToJava2D(v0, dataArea, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[83]++;

            double v1 = ((Double) curr[1]).doubleValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[84]++;
            double vv1 = rangeAxis.valueToJava2D(v1, dataArea, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[85]++;

            Shape[] faces = createHorizontalBlock(barX0, barW, vv0, vv1, 
                    inverted);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[86]++;
            Paint fillPaint = getItemPaint(series, column);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[87]++;
            Paint fillPaintDark = fillPaint;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[88]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((fillPaintDark instanceof Color) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[35]++;
                fillPaintDark = ((Color) fillPaint).darker();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[89]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[36]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[90]++;
            boolean drawOutlines = isDrawBarOutline();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[91]++;
            Paint outlinePaint = fillPaint;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[92]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[37]++;
                outlinePaint = getItemOutlinePaint(series, column);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[93]++;
                g2.setStroke(getItemOutlineStroke(series, column));
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[94]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[38]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[95]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
            for (int f = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((f < 6) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); f++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[7]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[8]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[9]++;
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[96]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((f == 5) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[39]++;
                    g2.setPaint(fillPaint);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[97]++;

                }
                else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[40]++;
                    g2.setPaint(fillPaintDark);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[98]++;
                }
                g2.fill(faces[f]);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[99]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[100]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[41]++;
                    g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[101]++;
                    g2.draw(faces[f]);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[102]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[42]++;}
            }
                        
            itemLabelList.add(new Object[] {new Integer(series), 
                    faces[5].getBounds2D(), 
                    BooleanUtilities.valueOf(v0 < getBase())});
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[103]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[104]++;

            // add an item entity, if this information is being collected
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[105]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[43]++;
                addItemEntity(entities, dataset, series, column, faces[5]);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[106]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[44]++;}

        }
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[107]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[10]++;


int CodeCoverConditionCoverageHelper_C26;        

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < itemLabelList.size()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[10]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[11]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[12]++;
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[108]++;
            Object[] record = (Object[]) itemLabelList.get(i);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[109]++;
            int series = ((Integer) record[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[110]++;
            Rectangle2D bar = (Rectangle2D) record[1];
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[111]++;
            boolean neg = ((Boolean) record[2]).booleanValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[112]++;
            CategoryItemLabelGenerator generator 
                    = getItemLabelGenerator(series, column);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[113]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, column)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[45]++;
                drawItemLabel(g2, dataset, series, column, plot, generator, 
                        bar, neg);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[114]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[46]++;}

        }
    }
    
    /**
     * Creates an array of shapes representing the six sides of a block in a
     * horizontal stack.
     * 
     * @param x0  left edge of bar (in Java2D space).
     * @param width  the width of the bar (in Java2D units).
     * @param y0  the base of the block (in Java2D space).
     * @param y1  the top of the block (in Java2D space).
     * @param inverted  a flag indicating whether or not the block is inverted
     *     (this changes the order of the faces of the block).
     * 
     * @return The sides of the block.
     */
    private Shape[] createHorizontalBlock(double x0, double width, double y0, 
            double y1, boolean inverted) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[115]++;
        Shape[] result = new Shape[6];
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[116]++;
        Point2D p00 = new Point2D.Double(y0, x0);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[117]++;
        Point2D p01 = new Point2D.Double(y0, x0 + width);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[118]++;
        Point2D p02 = new Point2D.Double(p01.getX() + getXOffset(), 
                p01.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[119]++;
        Point2D p03 = new Point2D.Double(p00.getX() + getXOffset(), 
                p00.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[120]++;

        Point2D p0 = new Point2D.Double(y1, x0);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[121]++;
        Point2D p1 = new Point2D.Double(y1, x0 + width);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[122]++;
        Point2D p2 = new Point2D.Double(p1.getX() + getXOffset(), 
                p1.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[123]++;
        Point2D p3 = new Point2D.Double(p0.getX() + getXOffset(), 
                p0.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[124]++;
        
        GeneralPath bottom = new GeneralPath();
        bottom.moveTo((float) p1.getX(), (float) p1.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[125]++;
        bottom.lineTo((float) p01.getX(), (float) p01.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[126]++;
        bottom.lineTo((float) p02.getX(), (float) p02.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[127]++;
        bottom.lineTo((float) p2.getX(), (float) p2.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[128]++;
        bottom.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[129]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[130]++;
        
        GeneralPath top = new GeneralPath();
        top.moveTo((float) p0.getX(), (float) p0.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[131]++;
        top.lineTo((float) p00.getX(), (float) p00.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[132]++;
        top.lineTo((float) p03.getX(), (float) p03.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[133]++;
        top.lineTo((float) p3.getX(), (float) p3.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[134]++;
        top.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[135]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[136]++;

        GeneralPath back = new GeneralPath();
        back.moveTo((float) p2.getX(), (float) p2.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[137]++;
        back.lineTo((float) p02.getX(), (float) p02.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[138]++;
        back.lineTo((float) p03.getX(), (float) p03.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[139]++;
        back.lineTo((float) p3.getX(), (float) p3.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[140]++;
        back.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[141]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[142]++;
        
        GeneralPath front = new GeneralPath();
        front.moveTo((float) p0.getX(), (float) p0.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[143]++;
        front.lineTo((float) p1.getX(), (float) p1.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[144]++;
        front.lineTo((float) p01.getX(), (float) p01.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[145]++;
        front.lineTo((float) p00.getX(), (float) p00.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[146]++;
        front.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[147]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[148]++;

        GeneralPath left = new GeneralPath();
        left.moveTo((float) p0.getX(), (float) p0.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[149]++;
        left.lineTo((float) p1.getX(), (float) p1.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[150]++;
        left.lineTo((float) p2.getX(), (float) p2.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[151]++;
        left.lineTo((float) p3.getX(), (float) p3.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[152]++;
        left.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[153]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[154]++;
        
        GeneralPath right = new GeneralPath();
        right.moveTo((float) p00.getX(), (float) p00.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[155]++;
        right.lineTo((float) p01.getX(), (float) p01.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[156]++;
        right.lineTo((float) p02.getX(), (float) p02.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[157]++;
        right.lineTo((float) p03.getX(), (float) p03.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[158]++;
        right.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[159]++;
        result[0] = bottom;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[160]++;
        result[1] = back;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[161]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[162]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((inverted) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[47]++;
            result[2] = right;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[163]++;
            result[3] = left;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[164]++;

        }
        else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[48]++;
            result[2] = left;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[165]++;
            result[3] = right;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[166]++;
        }
        result[4] = top;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[167]++;
        result[5] = front;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[168]++;
        return result;
    }
    
    /**
     * Draws a stack of bars for one category, with a vertical orientation.
     * 
     * @param values  the value list.
     * @param category  the category.
     * @param g2  the graphics device.
     * @param state  the state.
     * @param dataArea  the data area (adjusted for the 3D effect).
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     *
     * @since 1.0.4
     */
    protected void drawStackVertical(List values, Comparable category, 
            Graphics2D g2, CategoryItemRendererState state, 
            Rectangle2D dataArea, CategoryPlot plot, 
            CategoryAxis domainAxis, ValueAxis rangeAxis, 
            CategoryDataset dataset) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[169]++;
        
        int column = dataset.getColumnIndex(category);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[170]++;
        double barX0 = domainAxis.getCategoryMiddle(column, 
                dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge()) 
                - state.getBarWidth() / 2.0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[171]++;
        double barW = state.getBarWidth();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[172]++;

        // a list to store the series index and bar region, so we can draw
        // all the labels at the end...
        List itemLabelList = new ArrayList();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[173]++;
        
        // draw the blocks
        boolean inverted = rangeAxis.isInverted();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[174]++;
        int blockCount = values.size() - 1;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[175]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[13]++;


int CodeCoverConditionCoverageHelper_C29;
        for (int k = 0;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((k < blockCount) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); k++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[13]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[14]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[15]++;
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[176]++;
            int index = (inverted ? blockCount - k - 1 : k);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[177]++;
            Object[] prev = (Object[]) values.get(index);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[178]++;
            Object[] curr = (Object[]) values.get(index + 1);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[179]++;
            int series = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[180]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((curr[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[49]++;
                series = -((Integer) prev[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[181]++;

            }
            else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[50]++;
                series = ((Integer) curr[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[182]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[183]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((series < 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[51]++;
                    series = -((Integer) prev[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[184]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[52]++;}
            }
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[185]++;
            double v0 = ((Double) prev[1]).doubleValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[186]++;
            double vv0 = rangeAxis.valueToJava2D(v0, dataArea, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[187]++;

            double v1 = ((Double) curr[1]).doubleValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[188]++;
            double vv1 = rangeAxis.valueToJava2D(v1, dataArea, 
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[189]++;
            
            Shape[] faces = createVerticalBlock(barX0, barW, vv0, vv1, 
                    inverted);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[190]++;
            Paint fillPaint = getItemPaint(series, column);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[191]++;
            Paint fillPaintDark = fillPaint;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[192]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((fillPaintDark instanceof Color) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[53]++;
                fillPaintDark = ((Color) fillPaint).darker();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[193]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[54]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[194]++;
            boolean drawOutlines = isDrawBarOutline();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[195]++;
            Paint outlinePaint = fillPaint;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[196]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[55]++;
                outlinePaint = getItemOutlinePaint(series, column);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[197]++;
                g2.setStroke(getItemOutlineStroke(series, column));
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[198]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[56]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[199]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[16]++;


int CodeCoverConditionCoverageHelper_C34;
            
            for (int f = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((f < 6) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); f++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[16]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[17]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[18]++;
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[200]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((f == 5) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[57]++;
                    g2.setPaint(fillPaint);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[201]++;

                }
                else {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[58]++;
                    g2.setPaint(fillPaintDark);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[202]++;
                }
                g2.fill(faces[f]);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[203]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[204]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[59]++;
                    g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[205]++;
                    g2.draw(faces[f]);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[206]++;

                } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[60]++;}
            }

            itemLabelList.add(new Object[] {new Integer(series), 
                    faces[5].getBounds2D(), 
                    BooleanUtilities.valueOf(v0 < getBase())});
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[207]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[208]++;
            
            // add an item entity, if this information is being collected
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[209]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[61]++;
                addItemEntity(entities, dataset, series, column, faces[5]);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[210]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[62]++;}

        }
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[211]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[19]++;


int CodeCoverConditionCoverageHelper_C38;
        
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((i < itemLabelList.size()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[19]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[20]--;
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.loops[21]++;
}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[212]++;
            Object[] record = (Object[]) itemLabelList.get(i);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[213]++;
            int series = ((Integer) record[0]).intValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[214]++;
            Rectangle2D bar = (Rectangle2D) record[1];
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[215]++;
            boolean neg = ((Boolean) record[2]).booleanValue();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[216]++;
            CategoryItemLabelGenerator generator 
                    = getItemLabelGenerator(series, column);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[217]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(series, column)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[63]++;
                drawItemLabel(g2, dataset, series, column, plot, generator, 
                        bar, neg);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[218]++;

            } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[64]++;}

        }
    }
    
    /**
     * Creates an array of shapes representing the six sides of a block in a
     * vertical stack.
     * 
     * @param x0  left edge of bar (in Java2D space).
     * @param width  the width of the bar (in Java2D units).
     * @param y0  the base of the block (in Java2D space).
     * @param y1  the top of the block (in Java2D space).
     * @param inverted  a flag indicating whether or not the block is inverted
     *     (this changes the order of the faces of the block).
     * 
     * @return The sides of the block.
     */
    private Shape[] createVerticalBlock(double x0, double width, double y0, 
            double y1, boolean inverted) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[219]++;
        Shape[] result = new Shape[6];
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[220]++;
        Point2D p00 = new Point2D.Double(x0, y0);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[221]++;
        Point2D p01 = new Point2D.Double(x0 + width, y0);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[222]++;
        Point2D p02 = new Point2D.Double(p01.getX() + getXOffset(), 
                p01.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[223]++;
        Point2D p03 = new Point2D.Double(p00.getX() + getXOffset(), 
                p00.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[224]++;


        Point2D p0 = new Point2D.Double(x0, y1);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[225]++;
        Point2D p1 = new Point2D.Double(x0 + width, y1);
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[226]++;
        Point2D p2 = new Point2D.Double(p1.getX() + getXOffset(), 
                p1.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[227]++;
        Point2D p3 = new Point2D.Double(p0.getX() + getXOffset(), 
                p0.getY() - getYOffset());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[228]++;
        
        GeneralPath right = new GeneralPath();
        right.moveTo((float) p1.getX(), (float) p1.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[229]++;
        right.lineTo((float) p01.getX(), (float) p01.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[230]++;
        right.lineTo((float) p02.getX(), (float) p02.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[231]++;
        right.lineTo((float) p2.getX(), (float) p2.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[232]++;
        right.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[233]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[234]++;
        
        GeneralPath left = new GeneralPath();
        left.moveTo((float) p0.getX(), (float) p0.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[235]++;
        left.lineTo((float) p00.getX(), (float) p00.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[236]++;
        left.lineTo((float) p03.getX(), (float) p03.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[237]++;
        left.lineTo((float) p3.getX(), (float) p3.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[238]++;
        left.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[239]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[240]++;

        GeneralPath back = new GeneralPath();
        back.moveTo((float) p2.getX(), (float) p2.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[241]++;
        back.lineTo((float) p02.getX(), (float) p02.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[242]++;
        back.lineTo((float) p03.getX(), (float) p03.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[243]++;
        back.lineTo((float) p3.getX(), (float) p3.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[244]++;
        back.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[245]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[246]++;
        
        GeneralPath front = new GeneralPath();
        front.moveTo((float) p0.getX(), (float) p0.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[247]++;
        front.lineTo((float) p1.getX(), (float) p1.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[248]++;
        front.lineTo((float) p01.getX(), (float) p01.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[249]++;
        front.lineTo((float) p00.getX(), (float) p00.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[250]++;
        front.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[251]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[252]++;

        GeneralPath top = new GeneralPath();
        top.moveTo((float) p0.getX(), (float) p0.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[253]++;
        top.lineTo((float) p1.getX(), (float) p1.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[254]++;
        top.lineTo((float) p2.getX(), (float) p2.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[255]++;
        top.lineTo((float) p3.getX(), (float) p3.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[256]++;
        top.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[257]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[258]++;
        
        GeneralPath bottom = new GeneralPath();
        bottom.moveTo((float) p00.getX(), (float) p00.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[259]++;
        bottom.lineTo((float) p01.getX(), (float) p01.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[260]++;
        bottom.lineTo((float) p02.getX(), (float) p02.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[261]++;
        bottom.lineTo((float) p03.getX(), (float) p03.getY());
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[262]++;
        bottom.closePath();
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[263]++;
        
        result[0] = bottom;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[264]++;
        result[1] = back;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[265]++;
        result[2] = left;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[266]++;
        result[3] = right;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[267]++;
        result[4] = top;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[268]++;
        result[5] = front;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[269]++;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[270]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((inverted) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[65]++;
            result[0] = top;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[271]++;
            result[4] = bottom;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[272]++;

        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[66]++;}
        return result;
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[273]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[67]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[68]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[274]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((obj instanceof StackedBarRenderer3D) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[69]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[70]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[275]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[71]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[72]++;}
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[276]++;
        StackedBarRenderer3D that = (StackedBarRenderer3D) obj;
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.statements[277]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.renderAsPercentages != that.getRenderAsPercentages()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[73]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip.branches[74]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip ());
  }
    public static long[] statements = new long[278];
    public static long[] branches = new long[75];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[45];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.StackedBarRenderer3D.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 44; i++) {
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

  public CodeCoverCoverageCounter$3ss0l1z37920npfl7lff36xq27rtmk97s60l8ip () {
    super("org.jfree.chart.renderer.category.StackedBarRenderer3D.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 277; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 74; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.StackedBarRenderer3D.java");
      for (int i = 1; i <= 277; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 74; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 44; i++) {
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

