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
 * ---------------------------------
 * AbstractCategoryItemRenderer.java
 * ---------------------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *
 * Changes:
 * --------
 * 29-May-2002 : Version 1 (DG);
 * 06-Jun-2002 : Added accessor methods for the tool tip generator (DG);
 * 11-Jun-2002 : Made constructors protected (DG);
 * 26-Jun-2002 : Added axis to initialise method (DG);
 * 05-Aug-2002 : Added urlGenerator member variable plus accessors (RA);
 * 22-Aug-2002 : Added categoriesPaint attribute, based on code submitted by
 *               Janet Banks.  This can be used when there is only one series,
 *               and you want each category item to have a different color (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 29-Oct-2002 : Fixed bug where background image for plot was not being
 *               drawn (DG);
 * 05-Nov-2002 : Replaced references to CategoryDataset with TableDataset (DG);
 * 26-Nov 2002 : Replaced the isStacked() method with getRangeType() (DG);
 * 09-Jan-2003 : Renamed grid-line methods (DG);
 * 17-Jan-2003 : Moved plot classes into separate package (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 12-May-2003 : Modified to take into account the plot orientation (DG);
 * 12-Aug-2003 : Very minor javadoc corrections (DB)
 * 13-Aug-2003 : Implemented Cloneable (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 05-Nov-2003 : Fixed marker rendering bug (833623) (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 11-Feb-2004 : Modified labelling for markers (DG);
 * 12-Feb-2004 : Updated clone() method (DG);
 * 15-Apr-2004 : Created a new CategoryToolTipGenerator interface (DG);
 * 05-May-2004 : Fixed bug (948310) where interval markers extend outside axis
 *               range (DG);
 * 14-Jun-2004 : Fixed bug in drawRangeMarker() method - now uses 'paint' and
 *               'stroke' rather than 'outlinePaint' and 'outlineStroke' (DG);
 * 15-Jun-2004 : Interval markers can now use GradientPaint (DG);
 * 30-Sep-2004 : Moved drawRotatedString() from RefineryUtilities
 *               --> TextUtilities (DG);
 * 01-Oct-2004 : Fixed bug 1029697, problem with label alignment in
 *               drawRangeMarker() method (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds() (DG);
 * 21-Jan-2005 : Modified return type of calculateRangeMarkerTextAnchorPoint()
 *               method (DG);
 * 08-Mar-2005 : Fixed positioning of marker labels (DG);
 * 20-Apr-2005 : Added legend label, tooltip and URL generators (DG);
 * 01-Jun-2005 : Handle one dimension of the marker label adjustment
 *               automatically (DG);
 * 09-Jun-2005 : Added utility method for adding an item entity (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Mar-2006 : Updated getLegendItems() to check seriesVisibleInLegend
 *               flags (DG);
 * 20-Jul-2006 : Set dataset and series indices in LegendItem (DG);
 * 23-Oct-2006 : Draw outlines for interval markers (DG);
 * 24-Oct-2006 : Respect alpha setting in markers, as suggested by Sergei
 *               Ivanov in patch 1567843 (DG);
 * 30-Nov-2006 : Added a check for series visibility in the getLegendItem()
 *               method (DG);
 * 07-Dec-2006 : Fix for equals() method (DG);
 * 22-Feb-2007 : Added createState() method (DG);
 * 01-Mar-2007 : Fixed interval marker drawing (patch 1670686 thanks to 
 *               Sergei Ivanov) (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change, and deprecated
 *               itemLabelGenerator, toolTipGenerator and itemURLGenerator
 *               override fields (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.AlphaComposite;
import java.awt.Composite;
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
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategorySeriesLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectList;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * An abstract base class that you can use to implement a new
 * {@link CategoryItemRenderer}.  When you create a new
 * {@link CategoryItemRenderer} you are not required to extend this class,
 * but it makes the job easier.
 */
public abstract class AbstractCategoryItemRenderer extends AbstractRenderer
    implements CategoryItemRenderer, Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1247553218442497391L;
  static {
    CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[1]++;
  }

    /** The plot that the renderer is assigned to. */
    private CategoryPlot plot;

    /** 
     * The item label generator for ALL series. 
     * 
     * @deprecated This field is redundant and deprecated as of version 1.0.6.
     */
    private CategoryItemLabelGenerator itemLabelGenerator;

    /** A list of item label generators (one per series). */
    private ObjectList itemLabelGeneratorList;

    /** The base item label generator. */
    private CategoryItemLabelGenerator baseItemLabelGenerator;

    /** 
     * The tool tip generator for ALL series. 
     * 
     * @deprecated This field is redundant and deprecated as of version 1.0.6.
     */
    private CategoryToolTipGenerator toolTipGenerator;

    /** A list of tool tip generators (one per series). */
    private ObjectList toolTipGeneratorList;

    /** The base tool tip generator. */
    private CategoryToolTipGenerator baseToolTipGenerator;

    /** 
     * The URL generator. 
     * 
     * @deprecated This field is redundant and deprecated as of version 1.0.6.
     */
    private CategoryURLGenerator itemURLGenerator;

    /** A list of item label generators (one per series). */
    private ObjectList itemURLGeneratorList;

    /** The base item label generator. */
    private CategoryURLGenerator baseItemURLGenerator;

    /** The legend item label generator. */
    private CategorySeriesLabelGenerator legendItemLabelGenerator;

    /** The legend item tool tip generator. */
    private CategorySeriesLabelGenerator legendItemToolTipGenerator;

    /** The legend item URL generator. */
    private CategorySeriesLabelGenerator legendItemURLGenerator;

    /** The number of rows in the dataset (temporary record). */
    private transient int rowCount;

    /** The number of columns in the dataset (temporary record). */
    private transient int columnCount;

    /**
     * Creates a new renderer with no tool tip generator and no URL generator.
     * The defaults (no tool tip or URL generators) have been chosen to
     * minimise the processing required to generate a default chart.  If you
     * require tool tips or URLs, then you can easily add the required
     * generators.
     */
    protected AbstractCategoryItemRenderer() {
        this.itemLabelGenerator = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[2]++;
        this.itemLabelGeneratorList = new ObjectList();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[3]++;
        this.toolTipGenerator = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[4]++;
        this.toolTipGeneratorList = new ObjectList();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[5]++;
        this.itemURLGenerator = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[6]++;
        this.itemURLGeneratorList = new ObjectList();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[7]++;
        this.legendItemLabelGenerator
            = new StandardCategorySeriesLabelGenerator();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[8]++;
    }

    /**
     * Returns the number of passes through the dataset required by the
     * renderer.  This method returns <code>1</code>, subclasses should
     * override if they need more passes.
     *
     * @return The pass count.
     */
    public int getPassCount() {
        return 1;
    }

    /**
     * Returns the plot that the renderer has been assigned to (where
     * <code>null</code> indicates that the renderer is not currently assigned
     * to a plot).
     *
     * @return The plot (possibly <code>null</code>).
     *
     * @see #setPlot(CategoryPlot)
     */
    public CategoryPlot getPlot() {
        return this.plot;
    }

    /**
     * Sets the plot that the renderer has been assigned to.  This method is
     * usually called by the {@link CategoryPlot}, in normal usage you
     * shouldn't need to call this method directly.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     *
     * @see #getPlot()
     */
    public void setPlot(CategoryPlot plot) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[1]++;
            throw new IllegalArgumentException("Null 'plot' argument.");

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[2]++;}
        this.plot = plot;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[10]++;
    }

    // ITEM LABEL GENERATOR

    /**
     * Returns the item label generator for a data item.  This implementation
     * simply passes control to the {@link #getSeriesItemLabelGenerator(int)}
     * method.  If, for some reason, you want a different generator for
     * individual items, you can override this method.
     *
     * @param row  the row index (zero based).
     * @param column  the column index (zero based).
     *
     * @return The generator (possibly <code>null</code>).
     */
    public CategoryItemLabelGenerator getItemLabelGenerator(int row,
            int column) {
        return getSeriesItemLabelGenerator(row);
    }

    /**
     * Returns the item label generator for a series.
     *
     * @param series  the series index (zero based).
     *
     * @return The generator (possibly <code>null</code>).
     *
     * @see #setSeriesItemLabelGenerator(int, CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getSeriesItemLabelGenerator(int series) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;

        // return the generator for ALL series, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.itemLabelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[3]++;
            return this.itemLabelGenerator;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[4]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[12]++;

        // otherwise look up the generator table
        CategoryItemLabelGenerator generator = (CategoryItemLabelGenerator)
            this.itemLabelGeneratorList.get(series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[5]++;
            generator = this.baseItemLabelGenerator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[14]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[6]++;}
        return generator;

    }

    /**
     * Sets the item label generator for ALL series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemLabelGenerator(int, 
     *     CategoryItemLabelGenerator)} and 
     *     {@link #setBaseItemLabelGenerator(CategoryItemLabelGenerator)}.
     */
    public void setItemLabelGenerator(CategoryItemLabelGenerator generator) {
        this.itemLabelGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[15]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[16]++;
    }

    /**
     * Sets the item label generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero based).
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getSeriesItemLabelGenerator(int)
     */
    public void setSeriesItemLabelGenerator(int series,
                                        CategoryItemLabelGenerator generator) {
        this.itemLabelGeneratorList.set(series, generator);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[17]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[18]++;
    }

    /**
     * Returns the base item label generator.
     *
     * @return The generator (possibly <code>null</code>).
     *
     * @see #setBaseItemLabelGenerator(CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getBaseItemLabelGenerator() {
        return this.baseItemLabelGenerator;
    }

    /**
     * Sets the base item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getBaseItemLabelGenerator()
     */
    public void setBaseItemLabelGenerator(
            CategoryItemLabelGenerator generator) {
        this.baseItemLabelGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[19]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[20]++;
    }

    // TOOL TIP GENERATOR

    /**
     * Returns the tool tip generator that should be used for the specified
     * item.  This method looks up the generator using the "three-layer"
     * approach outlined in the general description of this interface.  You
     * can override this method if you want to return a different generator per
     * item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The generator (possibly <code>null</code>).
     */
    public CategoryToolTipGenerator getToolTipGenerator(int row, int column) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[21]++;

        CategoryToolTipGenerator result = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.toolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[7]++;
            result = this.toolTipGenerator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[23]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[8]++;
            result = getSeriesToolTipGenerator(row);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[24]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[9]++;
                result = this.baseToolTipGenerator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[26]++;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[10]++;}
        }
        return result;
    }

    /**
     * Returns the tool tip generator that will be used for ALL items in the
     * dataset (the "layer 0" generator).
     *
     * @return A tool tip generator (possibly <code>null</code>).
     *
     * @see #setToolTipGenerator(CategoryToolTipGenerator)
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #getSeriesToolTipGenerator(int)} 
     *     and {@link #getBaseToolTipGenerator()}.
     */
    public CategoryToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }

    /**
     * Sets the tool tip generator for ALL series and sends a
     * {@link org.jfree.chart.event.RendererChangeEvent} to all registered
     * listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getToolTipGenerator()
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesToolTipGenerator(int, 
     *     CategoryToolTipGenerator)} and 
     *     {@link #setBaseToolTipGenerator(CategoryToolTipGenerator)}.
     */
    public void setToolTipGenerator(CategoryToolTipGenerator generator) {
        this.toolTipGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[27]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[28]++;
    }

    /**
     * Returns the tool tip generator for the specified series (a "layer 1"
     * generator).
     *
     * @param series  the series index (zero-based).
     *
     * @return The tool tip generator (possibly <code>null</code>).
     *
     * @see #setSeriesToolTipGenerator(int, CategoryToolTipGenerator)
     */
    public CategoryToolTipGenerator getSeriesToolTipGenerator(int series) {
        return (CategoryToolTipGenerator) this.toolTipGeneratorList.get(series);
    }

    /**
     * Sets the tool tip generator for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getSeriesToolTipGenerator(int)
     */
    public void setSeriesToolTipGenerator(int series,
                                          CategoryToolTipGenerator generator) {
        this.toolTipGeneratorList.set(series, generator);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[29]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[30]++;
    }

    /**
     * Returns the base tool tip generator (the "layer 2" generator).
     *
     * @return The tool tip generator (possibly <code>null</code>).
     *
     * @see #setBaseToolTipGenerator(CategoryToolTipGenerator)
     */
    public CategoryToolTipGenerator getBaseToolTipGenerator() {
        return this.baseToolTipGenerator;
    }

    /**
     * Sets the base tool tip generator and sends a {@link RendererChangeEvent}
     * to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getBaseToolTipGenerator()
     */
    public void setBaseToolTipGenerator(CategoryToolTipGenerator generator) {
        this.baseToolTipGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[31]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[32]++;
    }

    // URL GENERATOR

    /**
     * Returns the URL generator for a data item.  This method just calls the
     * getSeriesItemURLGenerator method, but you can override this behaviour if
     * you want to.
     *
     * @param row  the row index (zero based).
     * @param column  the column index (zero based).
     *
     * @return The URL generator.
     */
    public CategoryURLGenerator getItemURLGenerator(int row, int column) {
        return getSeriesItemURLGenerator(row);
    }

    /**
     * Returns the URL generator for a series.
     *
     * @param series  the series index (zero based).
     *
     * @return The URL generator for the series.
     *
     * @see #setSeriesItemURLGenerator(int, CategoryURLGenerator)
     */
    public CategoryURLGenerator getSeriesItemURLGenerator(int series) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;

        // return the generator for ALL series, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.itemURLGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[11]++;
            return this.itemURLGenerator;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[12]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[34]++;

        // otherwise look up the generator table
        CategoryURLGenerator generator
            = (CategoryURLGenerator) this.itemURLGeneratorList.get(series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[13]++;
            generator = this.baseItemURLGenerator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[36]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[14]++;}
        return generator;

    }

    /**
     * Sets the item URL generator for ALL series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator.
     * 
     * @deprecated This method should no longer be used (as of version 1.0.6). 
     *     It is sufficient to rely on {@link #setSeriesItemURLGenerator(int, 
     *     CategoryURLGenerator)} and 
     *     {@link #setBaseItemURLGenerator(CategoryURLGenerator)}.
     */
    public void setItemURLGenerator(CategoryURLGenerator generator) {
        this.itemURLGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[37]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[38]++;
    }

    /**
     * Sets the URL generator for a series and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero based).
     * @param generator  the generator.
     *
     * @see #getSeriesItemURLGenerator(int)
     */
    public void setSeriesItemURLGenerator(int series,
                                          CategoryURLGenerator generator) {
        this.itemURLGeneratorList.set(series, generator);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[39]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[40]++;
    }

    /**
     * Returns the base item URL generator.
     *
     * @return The item URL generator.
     *
     * @see #setBaseItemURLGenerator(CategoryURLGenerator)
     */
    public CategoryURLGenerator getBaseItemURLGenerator() {
        return this.baseItemURLGenerator;
    }

    /**
     * Sets the base item URL generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the item URL generator (<code>null</code> permitted).
     *
     * @see #getBaseItemURLGenerator()
     */
    public void setBaseItemURLGenerator(CategoryURLGenerator generator) {
        this.baseItemURLGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[41]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[42]++;
    }

    /**
     * Returns the number of rows in the dataset.  This value is updated in the
     * {@link AbstractCategoryItemRenderer#initialise} method.
     *
     * @return The row count.
     */
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Returns the number of columns in the dataset.  This value is updated in
     * the {@link AbstractCategoryItemRenderer#initialise} method.
     *
     * @return The column count.
     */
    public int getColumnCount() {
        return this.columnCount;
    }

    /**
     * Creates a new state instance---this method is called from the
     * {@link #initialise(Graphics2D, Rectangle2D, CategoryPlot, int,
     * PlotRenderingInfo)} method.  Subclasses can override this method if
     * they need to use a subclass of {@link CategoryItemRendererState}.
     *
     * @param info  collects plot rendering info (<code>null</code> permitted).
     *
     * @return The new state instance (never <code>null</code>).
     *
     * @since 1.0.5
     */
    protected CategoryItemRendererState createState(PlotRenderingInfo info) {
        return new CategoryItemRendererState(info);
    }

    /**
     * Initialises the renderer and returns a state object that will be used
     * for the remainder of the drawing process for a single chart.  The state
     * object allows for the fact that the renderer may be used simultaneously
     * by multiple threads (each thread will work with a separate state object).
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param plot  the plot.
     * @param rendererIndex  the renderer index.
     * @param info  an object for returning information about the structure of
     *              the plot (<code>null</code> permitted).
     *
     * @return The renderer state.
     */
    public CategoryItemRendererState initialise(Graphics2D g2,
                                                Rectangle2D dataArea,
                                                CategoryPlot plot,
                                                int rendererIndex,
                                                PlotRenderingInfo info) {

        setPlot(plot);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[43]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[44]++;
        CategoryDataset data = plot.getDataset(rendererIndex);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[15]++;
            this.rowCount = data.getRowCount();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[46]++;
            this.columnCount = data.getColumnCount();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[47]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[16]++;
            this.rowCount = 0;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[48]++;
            this.columnCount = 0;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[49]++;
        }
        return createState(info);

    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return The range (or <code>null</code> if the dataset is
     *         <code>null</code> or empty).
     */
    public Range findRangeBounds(CategoryDataset dataset) {
        return DatasetUtilities.findRangeBounds(dataset);
    }

    /**
     * Draws a background for the data area.  The default implementation just
     * gets the plot to draw the background, but some renderers will override 
     * this behaviour.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     */
    public void drawBackground(Graphics2D g2,
                               CategoryPlot plot,
                               Rectangle2D dataArea) {

        plot.drawBackground(g2, dataArea);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[50]++;

    }

    /**
     * Draws an outline for the data area.  The default implementation just
     * gets the plot to draw the outline, but some renderers will override this
     * behaviour.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     */
    public void drawOutline(Graphics2D g2,
                            CategoryPlot plot,
                            Rectangle2D dataArea) {

        plot.drawOutline(g2, dataArea);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[51]++;

    }

    /**
     * Draws a grid line against the domain axis.
     * <P>
     * Note that this default implementation assumes that the horizontal axis
     * is the domain axis. If this is not the case, you will need to override
     * this method.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the area for plotting data (not yet adjusted for any
     *                  3D effect).
     * @param value  the Java2D value at which the grid line should be drawn.
     *
     * @see #drawRangeGridline(Graphics2D, CategoryPlot, ValueAxis,
     *     Rectangle2D, double)
     */
    public void drawDomainGridline(Graphics2D g2,
                                   CategoryPlot plot,
                                   Rectangle2D dataArea,
                                   double value) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[52]++;

        Line2D line = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[53]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[17]++;
            line = new Line2D.Double(dataArea.getMinX(), value,
                    dataArea.getMaxX(), value);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[55]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[18]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[56]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[19]++;
            line = new Line2D.Double(value, dataArea.getMinY(), value,
                    dataArea.getMaxY());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[57]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[20]++;}
}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[58]++;

        Paint paint = plot.getDomainGridlinePaint();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[59]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[21]++;
            paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[60]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[22]++;}
        g2.setPaint(paint);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[61]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[62]++;

        Stroke stroke = plot.getDomainGridlineStroke();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[63]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[23]++;
            stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[64]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[24]++;}
        g2.setStroke(stroke);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[65]++;

        g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[66]++;

    }

    /**
     * Draws a grid line against the range axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param axis  the value axis.
     * @param dataArea  the area for plotting data (not yet adjusted for any
     *                  3D effect).
     * @param value  the value at which the grid line should be drawn.
     *
     * @see #drawDomainGridline(Graphics2D, CategoryPlot, Rectangle2D, double)
     *
     */
    public void drawRangeGridline(Graphics2D g2,
                                  CategoryPlot plot,
                                  ValueAxis axis,
                                  Rectangle2D dataArea,
                                  double value) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[67]++;

        Range range = axis.getRange();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[68]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[25]++;
            return;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[26]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[69]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[70]++;
        double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[71]++;
        Line2D line = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[72]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[27]++;
            line = new Line2D.Double(v, dataArea.getMinY(), v,
                    dataArea.getMaxY());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[73]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[28]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[74]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[29]++;
            line = new Line2D.Double(dataArea.getMinX(), v,
                    dataArea.getMaxX(), v);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[75]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[30]++;}
}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[76]++;

        Paint paint = plot.getRangeGridlinePaint();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[31]++;
            paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[78]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[32]++;}
        g2.setPaint(paint);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[79]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[80]++;

        Stroke stroke = plot.getRangeGridlineStroke();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[81]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[33]++;
            stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[82]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[34]++;}
        g2.setStroke(stroke);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[83]++;

        g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[84]++;

    }

    /**
     * Draws a marker for the domain axis.
     *
     * @param g2  the graphics device (not <code>null</code>).
     * @param plot  the plot (not <code>null</code>).
     * @param axis  the range axis (not <code>null</code>).
     * @param marker  the marker to be drawn (not <code>null</code>).
     * @param dataArea  the area inside the axes (not <code>null</code>).
     *
     * @see #drawRangeMarker(Graphics2D, CategoryPlot, ValueAxis, Marker,
     *     Rectangle2D)
     */
    public void drawDomainMarker(Graphics2D g2,
                                 CategoryPlot plot,
                                 CategoryAxis axis,
                                 CategoryMarker marker,
                                 Rectangle2D dataArea) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[85]++;

        Comparable category = marker.getKey();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[86]++;
        CategoryDataset dataset = plot.getDataset(plot.getIndexOf(this));
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[87]++;
        int columnIndex = dataset.getColumnIndex(category);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[88]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((columnIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[35]++;
            return;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[36]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[89]++;

        final Composite savedComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, marker.getAlpha()));
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[90]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[91]++;

        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[92]++;
        Rectangle2D bounds = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[93]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((marker.getDrawAsLine()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[37]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[94]++;
            double v = axis.getCategoryMiddle(columnIndex,
                    dataset.getColumnCount(), dataArea,
                    plot.getDomainAxisEdge());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[95]++;
            Line2D line = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[96]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[39]++;
                line = new Line2D.Double(dataArea.getMinX(), v,
                        dataArea.getMaxX(), v);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[97]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[40]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[98]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[41]++;
                line = new Line2D.Double(v, dataArea.getMinY(), v,
                        dataArea.getMaxY());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[99]++;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[42]++;}
}
            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[100]++;
            g2.setStroke(marker.getStroke());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[101]++;
            g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[102]++;
            bounds = line.getBounds2D();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[103]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[38]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[104]++;
            double v0 = axis.getCategoryStart(columnIndex,
                    dataset.getColumnCount(), dataArea,
                    plot.getDomainAxisEdge());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[105]++;
            double v1 = axis.getCategoryEnd(columnIndex,
                    dataset.getColumnCount(), dataArea,
                    plot.getDomainAxisEdge());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[106]++;
            Rectangle2D area = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[107]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[43]++;
                area = new Rectangle2D.Double(dataArea.getMinX(), v0,
                        dataArea.getWidth(), (v1 - v0));
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[108]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[44]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[109]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[45]++;
                area = new Rectangle2D.Double(v0, dataArea.getMinY(),
                        (v1 - v0), dataArea.getHeight());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[110]++;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[46]++;}
}
            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[111]++;
            g2.fill(area);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[112]++;
            bounds = area;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[113]++;
        }
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[114]++;

        String label = marker.getLabel();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[115]++;
        RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[116]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[47]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[117]++;
            Font labelFont = marker.getLabelFont();
            g2.setFont(labelFont);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[118]++;
            g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[119]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[120]++;
            Point2D coordinates = calculateDomainMarkerTextAnchorPoint(
                    g2, orientation, dataArea, bounds, marker.getLabelOffset(),
                    marker.getLabelOffsetType(), anchor);
            TextUtilities.drawAlignedString(label, g2,
                    (float) coordinates.getX(), (float) coordinates.getY(),
                    marker.getLabelTextAnchor());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[121]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[48]++;}
        g2.setComposite(savedComposite);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[122]++;
    }

    /**
     * Draws a marker for the range axis.
     *
     * @param g2  the graphics device (not <code>null</code>).
     * @param plot  the plot (not <code>null</code>).
     * @param axis  the range axis (not <code>null</code>).
     * @param marker  the marker to be drawn (not <code>null</code>).
     * @param dataArea  the area inside the axes (not <code>null</code>).
     *
     * @see #drawDomainMarker(Graphics2D, CategoryPlot, CategoryAxis,
     *     CategoryMarker, Rectangle2D)
     */
    public void drawRangeMarker(Graphics2D g2,
                                CategoryPlot plot,
                                ValueAxis axis,
                                Marker marker,
                                Rectangle2D dataArea) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[123]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((marker instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[49]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[124]++;
            ValueMarker vm = (ValueMarker) marker;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[125]++;
            double value = vm.getValue();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[126]++;
            Range range = axis.getRange();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[127]++;
int CodeCoverConditionCoverageHelper_C26;

            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[51]++;
                return;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[52]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[128]++;

            final Composite savedComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, marker.getAlpha()));
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[129]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[130]++;

            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[131]++;
            double v = axis.valueToJava2D(value, dataArea,
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[132]++;
            Line2D line = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[133]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[53]++;
                line = new Line2D.Double(v, dataArea.getMinY(), v,
                        dataArea.getMaxY());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[134]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[54]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[135]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[55]++;
                line = new Line2D.Double(dataArea.getMinX(), v,
                        dataArea.getMaxX(), v);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[136]++;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[56]++;}
}

            g2.setPaint(marker.getPaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[137]++;
            g2.setStroke(marker.getStroke());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[138]++;
            g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[139]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[140]++;

            String label = marker.getLabel();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[141]++;
            RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[142]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[57]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[143]++;
                Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[144]++;
                g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[145]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[146]++;
                Point2D coordinates = calculateRangeMarkerTextAnchorPoint(
                        g2, orientation, dataArea, line.getBounds2D(),
                        marker.getLabelOffset(), LengthAdjustmentType.EXPAND,
                        anchor);
                TextUtilities.drawAlignedString(label, g2,
                        (float) coordinates.getX(), (float) coordinates.getY(),
                        marker.getLabelTextAnchor());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[147]++;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[58]++;}
            g2.setComposite(savedComposite);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[148]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[50]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[149]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((marker instanceof IntervalMarker) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[59]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[150]++;
            IntervalMarker im = (IntervalMarker) marker;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[151]++;
            double start = im.getStartValue();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[152]++;
            double end = im.getEndValue();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[153]++;
            Range range = axis.getRange();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[154]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((range.intersects(start, end)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[61]++;
                return;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[62]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[155]++;

            final Composite savedComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, marker.getAlpha()));
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[156]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[157]++;

            double start2d = axis.valueToJava2D(start, dataArea,
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[158]++;
            double end2d = axis.valueToJava2D(end, dataArea,
                    plot.getRangeAxisEdge());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[159]++;
            double low = Math.min(start2d, end2d);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[160]++;
            double high = Math.max(start2d, end2d);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[161]++;

            PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[162]++;
            Rectangle2D rect = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[163]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[63]++;
                // clip left and right bounds to data area
                low = Math.max(low, dataArea.getMinX());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[164]++;
                high = Math.min(high, dataArea.getMaxX());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[165]++;
                rect = new Rectangle2D.Double(low,
                        dataArea.getMinY(), high - low,
                        dataArea.getHeight());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[166]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[64]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[167]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[65]++;
                // clip top and bottom bounds to data area
                low = Math.max(low, dataArea.getMinY());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[168]++;
                high = Math.min(high, dataArea.getMaxY());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[169]++;
                rect = new Rectangle2D.Double(dataArea.getMinX(),
                        low, dataArea.getWidth(),
                        high - low);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[170]++;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[66]++;}
}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[171]++;
            Paint p = marker.getPaint();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[172]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((p instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[67]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[173]++;
                GradientPaint gp = (GradientPaint) p;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[174]++;
                GradientPaintTransformer t = im.getGradientPaintTransformer();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[175]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[69]++;
                    gp = t.transform(gp, rect);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[176]++;

                } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[70]++;}
                g2.setPaint(gp);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[177]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[68]++;
                g2.setPaint(p);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[178]++;
            }
            g2.fill(rect);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[179]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[180]++;
int CodeCoverConditionCoverageHelper_C36;

            // now draw the outlines, if visible...
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((im.getOutlinePaint() != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((im.getOutlineStroke() != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[71]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[181]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[73]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[182]++;
                    Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[183]++;
                    double x0 = dataArea.getMinX();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[184]++;
                    double x1 = dataArea.getMaxX();
                    g2.setPaint(im.getOutlinePaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[185]++;
                    g2.setStroke(im.getOutlineStroke());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[186]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[187]++;
int CodeCoverConditionCoverageHelper_C38;
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((range.contains(start)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[75]++;
                        line.setLine(x0, start2d, x1, start2d);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[188]++;
                        g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[189]++;

                    } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[76]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[190]++;
int CodeCoverConditionCoverageHelper_C39;
                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((range.contains(end)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[77]++;
                        line.setLine(x0, end2d, x1, end2d);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[191]++;
                        g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[192]++;

                    } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[78]++;}

                }
                else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[74]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[193]++; // PlotOrientation.HORIZONTAL
                    Line2D line = new Line2D.Double();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[194]++;
                    double y0 = dataArea.getMinY();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[195]++;
                    double y1 = dataArea.getMaxY();
                    g2.setPaint(im.getOutlinePaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[196]++;
                    g2.setStroke(im.getOutlineStroke());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[197]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[198]++;
int CodeCoverConditionCoverageHelper_C40;
                    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((range.contains(start)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[79]++;
                        line.setLine(start2d, y0, start2d, y1);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[199]++;
                        g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[200]++;

                    } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[80]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[201]++;
int CodeCoverConditionCoverageHelper_C41;
                    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((range.contains(end)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[81]++;
                        line.setLine(end2d, y0, end2d, y1);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[202]++;
                        g2.draw(line);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[203]++;

                    } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[82]++;}
                }

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[72]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[204]++;

            String label = marker.getLabel();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[205]++;
            RectangleAnchor anchor = marker.getLabelAnchor();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[206]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[83]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[207]++;
                Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[208]++;
                g2.setPaint(marker.getLabelPaint());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[209]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[210]++;
                Point2D coordinates = calculateRangeMarkerTextAnchorPoint(
                        g2, orientation, dataArea, rect,
                        marker.getLabelOffset(), marker.getLabelOffsetType(),
                        anchor);
                TextUtilities.drawAlignedString(label, g2,
                        (float) coordinates.getX(), (float) coordinates.getY(),
                        marker.getLabelTextAnchor());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[211]++;

            } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[84]++;}
            g2.setComposite(savedComposite);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[212]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[60]++;}
}
    }

    /**
     * Calculates the (x, y) coordinates for drawing the label for a marker on
     * the range axis.
     *
     * @param g2  the graphics device.
     * @param orientation  the plot orientation.
     * @param dataArea  the data area.
     * @param markerArea  the rectangle surrounding the marker.
     * @param markerOffset  the marker offset.
     * @param labelOffsetType  the label offset type.
     * @param anchor  the label anchor.
     *
     * @return The coordinates for drawing the marker label.
     */
    protected Point2D calculateDomainMarkerTextAnchorPoint(Graphics2D g2,
                                      PlotOrientation orientation,
                                      Rectangle2D dataArea,
                                      Rectangle2D markerArea,
                                      RectangleInsets markerOffset,
                                      LengthAdjustmentType labelOffsetType,
                                      RectangleAnchor anchor) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[213]++;

        Rectangle2D anchorRect = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[214]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[85]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetType);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[215]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[86]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[216]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[87]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    labelOffsetType, LengthAdjustmentType.CONTRACT);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[217]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[88]++;}
}
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Calculates the (x, y) coordinates for drawing a marker label.
     *
     * @param g2  the graphics device.
     * @param orientation  the plot orientation.
     * @param dataArea  the data area.
     * @param markerArea  the rectangle surrounding the marker.
     * @param markerOffset  the marker offset.
     * @param labelOffsetType  the label offset type.
     * @param anchor  the label anchor.
     *
     * @return The coordinates for drawing the marker label.
     */
    protected Point2D calculateRangeMarkerTextAnchorPoint(Graphics2D g2,
                                      PlotOrientation orientation,
                                      Rectangle2D dataArea,
                                      Rectangle2D markerArea,
                                      RectangleInsets markerOffset,
                                      LengthAdjustmentType labelOffsetType,
                                      RectangleAnchor anchor) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[218]++;

        Rectangle2D anchorRect = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[219]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[89]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    labelOffsetType, LengthAdjustmentType.CONTRACT);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[220]++;

        }
        else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[90]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[221]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[91]++;
            anchorRect = markerOffset.createAdjustedRectangle(markerArea,
                    LengthAdjustmentType.CONTRACT, labelOffsetType);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[222]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[92]++;}
}
        return RectangleAnchor.coordinates(anchorRect, anchor);

    }

    /**
     * Returns a legend item for a series.  This default implementation will
     * return <code>null</code> if {@link #isSeriesVisible(int)} or 
     * {@link #isSeriesVisibleInLegend(int)} returns <code>false</code>.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return The legend item (possibly <code>null</code>).
     *
     * @see #getLegendItems()
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[223]++;

        CategoryPlot p = getPlot();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[224]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[93]++;
            return null;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[94]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[225]++;
int CodeCoverConditionCoverageHelper_C48;

        // check that a legend item needs to be displayed...
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((isSeriesVisible(series)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(series)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[95]++;
            return null;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[96]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[226]++;

        CategoryDataset dataset = p.getDataset(datasetIndex);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[227]++;
        String label = this.legendItemLabelGenerator.generateLabel(dataset,
                series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[228]++;
        String description = label;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[229]++;
        String toolTipText = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[230]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((this.legendItemToolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[97]++;
            toolTipText = this.legendItemToolTipGenerator.generateLabel(
                    dataset, series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[231]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[98]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[232]++;
        String urlText = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[233]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.legendItemURLGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[99]++;
            urlText = this.legendItemURLGenerator.generateLabel(dataset,
                    series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[234]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[100]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[235]++;
        Shape shape = lookupSeriesShape(series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[236]++;
        Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[237]++;
        Paint outlinePaint = lookupSeriesOutlinePaint(series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[238]++;
        Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[239]++;

        LegendItem item = new LegendItem(label, description, toolTipText,
                urlText, shape, paint, outlineStroke, outlinePaint);
        item.setSeriesKey(dataset.getRowKey(series));
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[240]++;
        item.setSeriesIndex(series);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[241]++;
        item.setDataset(dataset);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[242]++;
        item.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[243]++;
        return item;
    }

    /**
     * Tests this renderer for equality with another object.
     *
     * @param obj  the object.
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[244]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[101]++;
            return true;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[102]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[245]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((obj instanceof AbstractCategoryItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[103]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[104]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[246]++;
        AbstractCategoryItemRenderer that = (AbstractCategoryItemRenderer) obj;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[247]++;
int CodeCoverConditionCoverageHelper_C53;

        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelGenerator,
                that.itemLabelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[105]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[106]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[248]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemLabelGeneratorList,
                that.itemLabelGeneratorList)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[107]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[108]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[249]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseItemLabelGenerator,
                that.baseItemLabelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[109]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[110]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[250]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipGenerator,
                that.toolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[111]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[112]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[251]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipGeneratorList,
                that.toolTipGeneratorList)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[114]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[252]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseToolTipGenerator,
                that.baseToolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[115]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[116]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[253]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemURLGenerator,
                that.itemURLGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[117]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[118]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[254]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.itemURLGeneratorList,
                that.itemURLGeneratorList)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[119]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[120]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[255]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.baseItemURLGenerator,
                that.baseItemURLGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[121]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[122]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[256]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendItemLabelGenerator,
                that.legendItemLabelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[123]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[124]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[257]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendItemToolTipGenerator,
                that.legendItemToolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[125]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[126]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[258]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendItemURLGenerator,
                that.legendItemURLGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[127]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[128]++;}
        return super.equals(obj);
    }

    /**
     * Returns a hash code for the renderer.
     *
     * @return The hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[259]++;
        int result = super.hashCode();
        return result;
    }

    /**
     * Returns the drawing supplier from the plot.
     *
     * @return The drawing supplier (possibly <code>null</code>).
     */
    public DrawingSupplier getDrawingSupplier() {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[260]++;
        DrawingSupplier result = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[261]++;
        CategoryPlot cp = getPlot();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[262]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((cp != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[129]++;
            result = cp.getDrawingSupplier();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[263]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[130]++;}
        return result;
    }

    /**
     * Draws an item label.
     *
     * @param g2  the graphics device.
     * @param orientation  the orientation.
     * @param dataset  the dataset.
     * @param row  the row.
     * @param column  the column.
     * @param x  the x coordinate (in Java2D space).
     * @param y  the y coordinate (in Java2D space).
     * @param negative  indicates a negative value (which affects the item
     *                  label position).
     */
    protected void drawItemLabel(Graphics2D g2,
                                 PlotOrientation orientation,
                                 CategoryDataset dataset,
                                 int row, int column,
                                 double x, double y,
                                 boolean negative) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[264]++;

        CategoryItemLabelGenerator generator
            = getItemLabelGenerator(row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[265]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[131]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[266]++;
            Font labelFont = getItemLabelFont(row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[267]++;
            Paint paint = getItemLabelPaint(row, column);
            g2.setFont(labelFont);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[268]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[269]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[270]++;
            String label = generator.generateLabel(dataset, row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[271]++;
            ItemLabelPosition position = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[272]++;
int CodeCoverConditionCoverageHelper_C67;
            if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[133]++;
                position = getPositiveItemLabelPosition(row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[273]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[134]++;
                position = getNegativeItemLabelPosition(row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[274]++;
            }
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[275]++;
            Point2D anchorPoint = calculateLabelAnchorPoint(
                    position.getItemLabelAnchor(), x, y, orientation);
            TextUtilities.drawRotatedString(label, g2,
                    (float) anchorPoint.getX(), (float) anchorPoint.getY(),
                    position.getTextAnchor(),
                    position.getAngle(), position.getRotationAnchor());
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[276]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[132]++;}

    }

    /**
     * Returns an independent copy of the renderer.  The <code>plot</code>
     * reference is shallow copied.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  can be thrown if one of the objects
     *         belonging to the renderer does not support cloning (for example,
     *         an item label generator).
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[277]++;

        AbstractCategoryItemRenderer clone
            = (AbstractCategoryItemRenderer) super.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[278]++;
int CodeCoverConditionCoverageHelper_C68;

        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.itemLabelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[135]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[279]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.itemLabelGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[137]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[280]++;
                PublicCloneable pc = (PublicCloneable) this.itemLabelGenerator;
                clone.itemLabelGenerator
                        = (CategoryItemLabelGenerator) pc.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[281]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[138]++;
                throw new CloneNotSupportedException(
                        "ItemLabelGenerator not cloneable.");
            }

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[136]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[282]++;
int CodeCoverConditionCoverageHelper_C70;

        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((this.itemLabelGeneratorList != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[139]++;
            clone.itemLabelGeneratorList
                    = (ObjectList) this.itemLabelGeneratorList.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[283]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[140]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[284]++;
int CodeCoverConditionCoverageHelper_C71;

        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((this.baseItemLabelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[141]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[285]++;
int CodeCoverConditionCoverageHelper_C72;
            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((this.baseItemLabelGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[143]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[286]++;
                PublicCloneable pc
                        = (PublicCloneable) this.baseItemLabelGenerator;
                clone.baseItemLabelGenerator
                        = (CategoryItemLabelGenerator) pc.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[287]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[144]++;
                throw new CloneNotSupportedException(
                        "ItemLabelGenerator not cloneable.");
            }

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[142]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[288]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((this.toolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[145]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[289]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((this.toolTipGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[147]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[290]++;
                PublicCloneable pc = (PublicCloneable) this.toolTipGenerator;
                clone.toolTipGenerator = (CategoryToolTipGenerator) pc.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[291]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[148]++;
                throw new CloneNotSupportedException(
                        "Tool tip generator not cloneable.");
            }

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[146]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[292]++;
int CodeCoverConditionCoverageHelper_C75;

        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.toolTipGeneratorList != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[149]++;
            clone.toolTipGeneratorList
                    = (ObjectList) this.toolTipGeneratorList.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[293]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[150]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[294]++;
int CodeCoverConditionCoverageHelper_C76;

        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((this.baseToolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[151]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[295]++;
int CodeCoverConditionCoverageHelper_C77;
            if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((this.baseToolTipGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[153]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[296]++;
                PublicCloneable pc
                        = (PublicCloneable) this.baseToolTipGenerator;
                clone.baseToolTipGenerator
                        = (CategoryToolTipGenerator) pc.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[297]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[154]++;
                throw new CloneNotSupportedException(
                        "Base tool tip generator not cloneable.");
            }

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[152]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[298]++;
int CodeCoverConditionCoverageHelper_C78;

        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((this.itemURLGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[155]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[299]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((this.itemURLGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[157]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[300]++;
                PublicCloneable pc = (PublicCloneable) this.itemURLGenerator;
                clone.itemURLGenerator = (CategoryURLGenerator) pc.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[301]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[158]++;
                throw new CloneNotSupportedException(
                        "Item URL generator not cloneable.");
            }

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[156]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[302]++;
int CodeCoverConditionCoverageHelper_C80;

        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((this.itemURLGeneratorList != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[159]++;
            clone.itemURLGeneratorList
                    = (ObjectList) this.itemURLGeneratorList.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[303]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[160]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[304]++;
int CodeCoverConditionCoverageHelper_C81;

        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((this.baseItemURLGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[161]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[305]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((this.baseItemURLGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[163]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[306]++;
                PublicCloneable pc
                        = (PublicCloneable) this.baseItemURLGenerator;
                clone.baseItemURLGenerator = (CategoryURLGenerator) pc.clone();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[307]++;

            }
            else {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[164]++;
                throw new CloneNotSupportedException(
                        "Base item URL generator not cloneable.");
            }

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[162]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[308]++;
int CodeCoverConditionCoverageHelper_C83;

        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((this.legendItemLabelGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[165]++;
            clone.legendItemLabelGenerator = (CategorySeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemLabelGenerator);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[309]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[166]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[310]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((this.legendItemToolTipGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[167]++;
            clone.legendItemToolTipGenerator = (CategorySeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemToolTipGenerator);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[311]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[168]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[312]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((this.legendItemURLGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[169]++;
            clone.legendItemURLGenerator = (CategorySeriesLabelGenerator)
                    ObjectUtilities.clone(this.legendItemURLGenerator);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[313]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[170]++;}
        return clone;
    }

    /**
     * Returns a domain axis for a plot.
     *
     * @param plot  the plot.
     * @param index  the axis index.
     *
     * @return A domain axis.
     */
    protected CategoryAxis getDomainAxis(CategoryPlot plot, int index) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[314]++;
        CategoryAxis result = plot.getDomainAxis(index);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[315]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[171]++;
            result = plot.getDomainAxis();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[316]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[172]++;}
        return result;
    }

    /**
     * Returns a range axis for a plot.
     *
     * @param plot  the plot.
     * @param index  the axis index.
     *
     * @return A range axis.
     */
    protected ValueAxis getRangeAxis(CategoryPlot plot, int index) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[317]++;
        ValueAxis result = plot.getRangeAxis(index);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[318]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[173]++;
            result = plot.getRangeAxis();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[319]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[174]++;}
        return result;
    }

    /**
     * Returns a (possibly empty) collection of legend items for the series
     * that this renderer is responsible for drawing.
     *
     * @return The legend item collection (never <code>null</code>).
     *
     * @see #getLegendItem(int, int)
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[320]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((this.plot == null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[175]++;
            return new LegendItemCollection();

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[176]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[321]++;
        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[322]++;
        int index = this.plot.getIndexOf(this);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[323]++;
        CategoryDataset dataset = this.plot.getDataset(index);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[324]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[177]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[325]++;
            int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[326]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.loops[1]++;


int CodeCoverConditionCoverageHelper_C90;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((i < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.loops[1]--;
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.loops[2]--;
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.loops[3]++;
}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[327]++;
int CodeCoverConditionCoverageHelper_C91;
                if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(i)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[179]++;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[328]++;
                    LegendItem item = getLegendItem(index, i);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[329]++;
int CodeCoverConditionCoverageHelper_C92;
                    if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[181]++;
                        result.add(item);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[330]++;

                    } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[182]++;}

                } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[180]++;}
            }


        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[178]++;}
        return result;
    }

    /**
     * Returns the legend item label generator.
     *
     * @return The label generator (never <code>null</code>).
     *
     * @see #setLegendItemLabelGenerator(CategorySeriesLabelGenerator)
     */
    public CategorySeriesLabelGenerator getLegendItemLabelGenerator() {
        return this.legendItemLabelGenerator;
    }

    /**
     * Sets the legend item label generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> not permitted).
     *
     * @see #getLegendItemLabelGenerator()
     */
    public void setLegendItemLabelGenerator(
            CategorySeriesLabelGenerator generator) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[331]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[183]++;
            throw new IllegalArgumentException("Null 'generator' argument.");

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[184]++;}
        this.legendItemLabelGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[332]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[333]++;
    }

    /**
     * Returns the legend item tool tip generator.
     *
     * @return The tool tip generator (possibly <code>null</code>).
     *
     * @see #setLegendItemToolTipGenerator(CategorySeriesLabelGenerator)
     */
    public CategorySeriesLabelGenerator getLegendItemToolTipGenerator() {
        return this.legendItemToolTipGenerator;
    }

    /**
     * Sets the legend item tool tip generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #setLegendItemToolTipGenerator(CategorySeriesLabelGenerator)
     */
    public void setLegendItemToolTipGenerator(
            CategorySeriesLabelGenerator generator) {
        this.legendItemToolTipGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[334]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[335]++;
    }

    /**
     * Returns the legend item URL generator.
     *
     * @return The URL generator (possibly <code>null</code>).
     *
     * @see #setLegendItemURLGenerator(CategorySeriesLabelGenerator)
     */
    public CategorySeriesLabelGenerator getLegendItemURLGenerator() {
        return this.legendItemURLGenerator;
    }

    /**
     * Sets the legend item URL generator and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     *
     * @see #getLegendItemURLGenerator()
     */
    public void setLegendItemURLGenerator(
            CategorySeriesLabelGenerator generator) {
        this.legendItemURLGenerator = generator;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[336]++;
        fireChangeEvent();
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[337]++;
    }

    /**
     * Adds an entity with the specified hotspot.
     *
     * @param entities  the entity collection.
     * @param dataset  the dataset.
     * @param row  the row index.
     * @param column  the column index.
     * @param hotspot  the hotspot.
     */
    protected void addItemEntity(EntityCollection entities,
                                 CategoryDataset dataset, int row, int column,
                                 Shape hotspot) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[338]++;

        String tip = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[339]++;
        CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[340]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((tipster != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[185]++;
            tip = tipster.generateToolTip(dataset, row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[341]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[186]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[342]++;
        String url = null;
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[343]++;
        CategoryURLGenerator urlster = getItemURLGenerator(row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[344]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((urlster != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[187]++;
            url = urlster.generateURL(dataset, row, column);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[345]++;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.branches[188]++;}
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[346]++;
        CategoryItemEntity entity = new CategoryItemEntity(hotspot, tip, url,
                dataset, dataset.getRowKey(row), dataset.getColumnKey(column));
        entities.add(entity);
CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9.statements[347]++;

    }


}

class CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9 ());
  }
    public static long[] statements = new long[348];
    public static long[] branches = new long[189];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[96];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.AbstractCategoryItemRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 95; i++) {
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

  public CodeCoverCoverageCounter$bl6rgcoxr7y9zfn0v9zvffsh2yr56nuf7v97bmu9he9diofy3q9 () {
    super("org.jfree.chart.renderer.category.AbstractCategoryItemRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 347; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 188; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 95; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.AbstractCategoryItemRenderer.java");
      for (int i = 1; i <= 347; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 188; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 95; i++) {
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

